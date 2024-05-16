class AddUsersController < ApplicationController
  before_action :set_add_user, only: %i[ show edit update destroy ]

  # GET /add_users or /add_users.json
  def index
    login_name = params[:login_name]
    group_name = params[:group_name].to_i
    full_name = params[:full_name]
    name_kana = params[:name_kana]
    year_birth = params[:year_birth]
    month_birth = params[:month_birth]
    day_birth = params[:day_birth]
    email = params[:email]
    tel = params[:tel]
    password = params[:password]
    passConfirm = params[:passConfirm]
    name_level = params[:name_level]
    year_start = params[:year_start]
    month_start = params[:month_start]
    day_start = params[:day_start]
    year_end = params[:year_end]
    month_end = params[:month_end]
    day_end = params[:day_end]
    total = params[:total]


    @groups = MstGroup.all
    @detail_japans = MstJapan.all
  end

  # GET /add_users/1 or /add_users/1.json
  def show
  end

  # GET /add_users/new
  def new

    # @list_users = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
    #   JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
    #   JOIN mst_japans as mst ON dtj.code_level = mst.code_level
    #   JOIN mst_groups as g ON u.mst_group_id = g.group_id").to_a
    #
    # @groups = MstGroup.all
    # puts @groups.present?
  end

  # GET /add_users/1/edit
  def edit
  end

  # POST /add_users or /add_users.json
  def create
    @add_user = AddUser.new(add_user_params)

    respond_to do |format|
      if @add_user.save
        format.html { redirect_to add_user_url(@add_user), notice: "Add user.js was successfully created." }
        format.json { render :show, status: :created, location: @add_user }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @add_user.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /add_users/1 or /add_users/1.json
  def update
    respond_to do |format|
      if @add_user.update(add_user_params)
        format.html { redirect_to add_user_url(@add_user), notice: "Add user.js was successfully updated." }
        format.json { render :show, status: :ok, location: @add_user }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @add_user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /add_users/1 or /add_users/1.json
  def destroy
    @add_user.destroy!

    respond_to do |format|
      format.html { redirect_to add_users_url, notice: "Add user.js was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_add_user
      @add_user = AddUser.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def add_user_params
      params.fetch(:add_user, {})
    end
end
