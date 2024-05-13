class ListUsersController < ApplicationController
  before_action :set_list_user, only: %i[ show edit update destroy ]

  # GET /list_users or /list_users.json
  def index
    if params[:keyword].present? || params[:group].present?
      @list_users = params[:list_users]
      @keyword = params[:keyword]
      @group_display = MstGroup.where(group_id: params[:group].to_i).first.group_id
    else
      @list_users = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
      JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
      JOIN mst_japans as mst ON dtj.code_level = mst.code_level
      JOIN mst_groups as g ON u.mst_group_id = g.group_id").to_a
    end
    unless @list_users.present?
      @list_users = []
      flash[:notice] = Constant::Error::ERR_0013
    end
    @groups = MstGroup.all
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
              JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
              JOIN mst_japans as mst ON dtj.code_level = mst.code_level
              JOIN mst_groups as g ON u.mst_group_id = g.group_id"

    keyword = params[:name]
    group = params[:group_name].to_i
    if keyword.present? && group != 0
      query = query + " WHERE u.full_name LIKE '%#{keyword}%' AND g.group_id = #{group}"
    elsif keyword.present?
      query = query + " WHERE u.full_name LIKE '%#{keyword}%'"
    elsif group != 0
      query = query + " WHERE g.group_id = #{group}"
    end

    list_users = ActiveRecord::Base.connection.execute(query).to_a
    redirect_to list_users_path(list_users: list_users, keyword: keyword, group: group)
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
