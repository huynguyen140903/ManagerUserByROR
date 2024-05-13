class ListUsersController < ApplicationController
  before_action :set_list_user, only: %i[ show edit update destroy ]
  before_action :authorized

  def authorized
    redirect_to logins_path unless logged_in?
  end

  # GET /list_users or /list_users.json
  def index
    keyword = params[:keyword]
    group_display = params[:group_display]
    sort_by_fullname = params[:sort_by_fullname]
    sort_by_code_level = params[:sort_by_code_level]
    sort_by_end_date = params[:sort_by_end_date]
    sort_by = params[:sort_by]
    current_page = params[:current_page]
    if keyword.present? || group_display.present? || sort_by.present? ||
        sort_by_fullname.present? || sort_by_code_level.present? || sort_by_end_date.present? || current_page.present?
      list_users = $list_users
      $list_users = nil
      @keyword = keyword
      @group_display = group_display.to_i
      @sort_by_fullname = sort_by_fullname
      @sort_by_code_level = sort_by_code_level
      @sort_by_end_date = sort_by_end_date
      @sort_by = sort_by
    else
<<<<<<< HEAD
      query = "SELECT * from tbl_users as u
                LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
                LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
                LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id"
      list_users = ActiveRecord::Base.connection.execute(query).to_a
=======
      @list_users = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
      JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
      JOIN mst_japans as mst ON dtj.code_level = mst.code_level
      JOIN mst_groups as g ON u.mst_group_id = g.group_id").to_a
    end
    unless @list_users.present?
      @list_users = []
      flash[:notice] = Constant::Error::ERR_0013
>>>>>>> d98e53e0eef15018eb90d383fb260ffaf6402415
    end
    @groups = MstGroup.all

    flash[:notice] = nil
    unless list_users.present?
      @list_users = []
      flash[:notice] = Constant::Error::ERR_0013
    end

    @current_page = current_page || 1
    @list_users = get_paging(list_users, @current_page.to_i)
  end

  def paging
    keyword = params[:keyword]
    group_display = params[:group_display].to_i
    sort_by_fullname = params[:sort_by_fullname]
    sort_by_code_level = params[:sort_by_code_level]
    sort_by_end_date = params[:sort_by_end_date]
    sort_by = params[:sort_by]
    current_page = params[:current_page]
    query = "SELECT * from tbl_users as u
                LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
                LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
                LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id"
    if keyword.present? && group_display != 0
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%' AND g.group_id = #{group_display}"
    elsif keyword.present?
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%'"
    elsif group_display != 0
      query = query + " WHERE g.group_id = #{group_display}"
    end

    case sort_by
    when 'fullname'
      if sort_by_fullname == 'asc'
        query = query + " ORDER BY full_name ASC"
      else
        query = query + " ORDER BY full_name DESC"
      end
    when 'code_level'
      if sort_by_code_level == 'asc'
        query = query + " ORDER BY code_level ASC"
      else
        query = query + " ORDER BY code_level DESC"
      end
    when 'end_date'
      if sort_by_end_date == 'asc'
        query = query + " ORDER BY end_date ASC"
      else
        query = query + " ORDER BY end_date DESC"
      end
    end
    $list_users = ActiveRecord::Base.connection.execute(query).to_a
    redirect_to list_users_path(keyword: keyword,
                                group_display: group_display,
                                sort_by: sort_by,
                                sort_by_fullname: sort_by_fullname,
                                sort_by_code_level: sort_by_code_level,
                                sort_by_end_date: sort_by_end_date,
                                current_page: current_page)
  end

  def get_paging(list_users, current_page)
    total_record = list_users.size
    return [] if total_record == 0

    record_per_page = Constant::Paging::RECORD_PER_PAGE
    page_size = Constant::Paging::PAGE_SIZE
    @total_page = (total_record % record_per_page == 0) ? (total_record / record_per_page) : (total_record / record_per_page + 1)
    total_list_page = (@total_page % page_size == 0) ? (@total_page / page_size) : (@total_page / page_size + 1)
    current_list_page = (current_page % page_size == 0) ? (current_page / page_size) : (current_page / page_size + 1)
    if current_list_page < total_list_page
      @list_page = ((page_size * (current_list_page - 1) + 1)..(page_size * current_list_page)).to_a
    elsif current_list_page == total_list_page
      @list_page = ((page_size * (current_list_page - 1) + 1)..@total_page).to_a
    else
      @list_page = []
    end
    offset = (current_page - 1) * record_per_page
    list_users[offset, record_per_page]
  end

  def sort
    keyword = params[:keyword]
    group_display = params[:group_display].to_i
    sort_by_fullname = params[:sort_by_fullname]
    sort_by_code_level = params[:sort_by_code_level]
    sort_by_end_date = params[:sort_by_end_date]
    sort_by = params[:sort_by]
    query = "SELECT * from tbl_users as u
                LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
                LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
                LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id"
    if keyword.present? && group_display != 0
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%' AND g.group_id = #{group_display}"
    elsif keyword.present?
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%'"
    elsif group_display != 0
      query = query + " WHERE g.group_id = #{group_display}"
    end

    case sort_by
    when 'fullname'
      if sort_by_fullname == 'asc'
        sort_by_fullname = 'desc'
        query = query + " ORDER BY full_name ASC"
      else
        sort_by_fullname = 'asc'
        query = query + " ORDER BY full_name DESC"
      end
    when 'code_level'
      if sort_by_code_level == 'asc'
        sort_by_code_level = 'desc'
        query = query + " ORDER BY code_level ASC"
      else
        sort_by_code_level = 'asc'
        query = query + " ORDER BY code_level DESC"
      end
    when 'end_date'
      if sort_by_end_date == 'asc'
        sort_by_end_date = 'desc'
        query = query + " ORDER BY end_date ASC"
      else
        sort_by_end_date = 'asc'
        query = query + " ORDER BY end_date DESC"
      end
    end
    $list_users = ActiveRecord::Base.connection.execute(query).to_a
    redirect_to list_users_path(keyword: keyword,
                                group_display: group_display,
                                sort_by: sort_by,
                                sort_by_fullname: sort_by_fullname,
                                sort_by_code_level: sort_by_code_level,
                                sort_by_end_date: sort_by_end_date)
  end

  # GET /list_users/1 or /list_users/1.json
  def show
  end

  # GET /list_users/new
  def new
    @list_user = ListUser.new
  end

  # GET /list_users/1/edit
  def edit
  end

  # POST /list_users or /list_users.json
  def create
    query = "SELECT * from tbl_users as u
              LEFT JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
              LEFT JOIN mst_japans as mst ON dtj.code_level = mst.code_level
              LEFT JOIN mst_groups as g ON u.mst_group_id = g.group_id"

    keyword = params[:name]
    group = params[:group_name].to_i
    if keyword.present? && group != 0
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%' AND g.group_id = #{group}"
    elsif keyword.present?
      query = query + " WHERE u.full_name LIKE '%#{keyword.gsub('%', '\%')}%'"
    elsif group != 0
      query = query + " WHERE g.group_id = #{group}"
    end
    $list_users = ActiveRecord::Base.connection.execute(query).to_a
    redirect_to list_users_path(keyword: keyword, group_display: group)
  end

  # PATCH/PUT /list_users/1 or /list_users/1.json
  def update
    respond_to do |format|
      if @list_user.update(list_user_params)
        format.html { redirect_to list_user_url(@list_user), notice: "List user.js was successfully updated." }
        format.json { render :show, status: :ok, location: @list_user }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @list_user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /list_users/1 or /list_users/1.json
  def destroy
    @list_user.destroy!

    respond_to do |format|
      format.html { redirect_to list_users_url, notice: "List user.js was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_list_user
      @list_user = ListUser.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def list_user_params
      params.fetch(:list_user, {})
    end
end
