require 'date'

class AddUsersController < ApplicationController
  include AddUsersHelper

  # GET /add_users or /add_users.json
  def index
    @action = params[:action_type]
    error = params[:error]
    user_info = params[:user_info]
    if user_info.present?
      if @action == 'edit'
        @login_name = user_info[:login_name]
        @group_name = user_info[:group_id]
        @full_name = user_info[:full_name]
        @name_kana = user_info[:name_kana]
        @birthday = user_info[:birthday].to_s.gsub('-', '/').split('/')
        @email = user_info[:email]
        @tel = user_info[:tel]
        @pass = user_info[:pass]
        @pass_confirm = user_info[:pass_confirm]
        @name_level = user_info[:code_level]
        @start_date = user_info[:start_date].to_s.gsub('-', '/').split('/')
        @end_date = user_info[:end_date].to_s.gsub('-', '/').split('/')
        @total = user_info[:total]
      else
        @login_name = user_info[:login_name]
        group = MstGroup.where(group_id: user_info[:group_id]).first
        @group_name = group.present? ? group.group_name : nil
        @full_name = user_info[:full_name]
        @name_kana = user_info[:name_kana]
        @birthday = user_info[:birthday].to_s.gsub('-', '/')
        @email = user_info[:email]
        @tel = user_info[:tel]
        @pass = user_info[:pass]
        @pass_confirm = user_info[:pass_confirm]
        level = MstJapan.where(code_level: user_info[:code_level]).first
        @name_level = level.present? ? level.name_level : nil
        @start_date = user_info[:start_date].to_s.gsub('-', '/')
        @end_date = user_info[:end_date].to_s.gsub('-', '/')
        @total = user_info[:total]
      end
    end
    flash[:notice] = nil
    if error.present?
      flash[:notice] = error
    end
    @groups = MstGroup.where.not(group_id: 0)
    @detail_japans = MstJapan.all
  end

  # GET /add_users/1 or /add_users/1.json
  def show
    user_id = params[:id]

    @user_details = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
      LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
      LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
      LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id WHERE user_id = #{user_id}").first
  end

  # GET /add_users/new
  def new
    user_info = params[:user_info]
    if user_info.present?
      @login_name = user_info[:login_name]
      @group_name = MstGroup.where(group_id: user_info[:group_id]).first.group_name
      @full_name = user_info[:full_name]
      @name_kana = user_info[:name_kana]
      @birthday = user_info[:birthday].to_s.gsub('-', '/')
      @email = user_info[:email]
      @tel = user_info[:tel]
      @pass = user_info[:pass]
      @pass_confirm = user_info[:pass_confirm]
      @name_level = MstJapan.where(code_level: user_info[:code_level]).first.name_level
      @start_date = user_info[:start_date].to_s.gsub('-', '/')
      @end_date = user_info[:end_date].to_s.gsub('-', '/')
      @total = user_info[:total]
    end
  end

  def save
    login_name = params[:login_name]
    group_id = MstGroup.where(group_name: params[:group_name]).first.group_id
    full_name = params[:full_name]
    name_kana = params[:name_kana]
    birthday = params[:birthday]
    email = params[:email]
    tel = params[:tel]
    pass = params[:pass]
    pass_confirm = params[:pass_confirm]
    code_level = MstJapan.where(name_level: params[:name_level]).first.code_level
    start_date = params[:start_date]
    end_date = params[:end_date]
    total = params[:total]
    ActiveRecord::Base.transaction do
      user = TblUser.create(
          mst_group_id: group_id,
          login_name: login_name,
          password: pass,
          full_name: full_name,
          full_name_kana: name_kana,
          email: email,
          tel: tel,
          birthday: birthday
      )
      detail = TblDetailUserJapan.new(
          tbl_user_id: user.user_id,
          code_level: code_level,
          start_date: start_date,
          end_date: end_date,
          total: total
      )
      fail ActiveRecord::Rollback unless detail.save
    end
    redirect_to successes_path
  end

  # GET /add_users/1/edit
  def edit
    user_id = params[:id]
    user_details = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
      LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
      LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
      LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id WHERE user_id = #{user_id}").first
    user_info = {
        login_name: user_details['login_name'],
        group_id: user_details['group_id'],
        full_name: user_details['full_name'],
        name_kana: user_details['full_name_kana'],
        birthday: user_details['birthday'],
        email: user_details['email'],
        tel: user_details['tel'],
        pass: user_details['password'],
        pass_confirm: user_details['password'],
        code_level: user_details['code_level'],
        start_date: user_details['start_date'],
        end_date: user_details['end_date'],
        total: user_details['total']
    }
    redirect_to add_users_path(user_info: user_info, action_type: 'edit')
  end

  # POST /add_users
  def create
    action_type = params[:action_type]
    login_name = params[:login_name]
    group_id = params[:group_name].to_i
    full_name = params[:full_name]
    name_kana = params[:name_kana]
    year_birth = params[:year_birth].to_i
    month_birth = params[:month_birth].to_i
    day_birth = params[:day_birth].to_i
    email = params[:email]
    tel = params[:tel]
    pass = params[:pass]
    pass_confirm = params[:pass_confirm]
    code_level = params[:name_level]
    year_start = params[:year_start].to_i
    month_start = params[:month_start].to_i
    day_start = params[:day_start].to_i
    year_end = params[:year_end].to_i
    month_end = params[:month_end].to_i
    day_end = params[:day_end].to_i
    total = params[:total]

    error = []
    error << validate_login_name(login_name, action_type)
    error << validate_group(group_id)
    error << validate_full_name(full_name)
    error << validate_full_name_kana(name_kana)
    error << validate_birth_day(day_birth, month_birth, year_birth)
    error << validate_email(email, action_type)
    error << validate_tel(tel)
    error << validate_pass_and_pass_confirm(pass, pass_confirm)
    error << validate_name_level(code_level)
    error << validate_start_day(day_start, month_start, year_start, code_level)
    error << validate_end_day(day_start, month_start, year_start, code_level)
    error << validate_total(total, code_level)

    if error.reject!(&:blank?).present?
      user_info = {
          login_name: login_name,
          group_id: group_id,
          full_name: full_name,
          name_kana: name_kana,
          year_birth: year_birth,
          month_birth: month_birth,
          day_birth: day_birth,
          email: email,
          tel: tel,
          pass: pass,
          pass_confirm: pass_confirm,
          code_level: code_level,
          year_start: year_start,
          month_start: month_start,
          day_start: day_start,
          year_end: year_end,
          month_end: month_end,
          day_end: day_end,
          total: total
      }
      redirect_to add_users_path(error: error, user_info: user_info, action_type: action_type)
    else
      birthday = Date.new(year_birth, month_birth, day_birth)
      start_date = Date.parse("#{year_start}-#{month_start}-#{day_start}")
      end_date = Date.parse("#{year_end}-#{month_end}-#{day_end}")
      user_info = {
          login_name: login_name,
          group_id: group_id,
          full_name: full_name,
          name_kana: name_kana,
          birthday: birthday,
          email: email,
          tel: tel,
          pass: pass,
          pass_confirm: pass_confirm,
          code_level: code_level,
          start_date: start_date,
          end_date: end_date,
          total: total
      }
      redirect_to new_add_user_path(user_info: user_info, action_type: params[:action_type])
    end
  end

  # PATCH/PUT /add_users/1 or /add_users/1.json
  def update

  end

  # DELETE /add_users/1 or /add_users/1.json
  def destroy
    user_id = params[:id]
    ActiveRecord::Base.transaction do
      detail = TblDetailUserJapan.where(tbl_user_id: user_id).first
      detail.destroy if detail.present?
      user = TblUser.where(user_id: user_id).first
      user.destroy if user.present?
    end
    redirect_to successes_path
  end
end
