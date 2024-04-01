class UserDetailsController < ApplicationController
   before_action :set_user_detail, only: %i[ show edit update destroy ]
  # before_action :set_user_detail, only: [:show]
  # GET /user_details or /user_details.json
  def index
  end

  # GET /user_details/1 or /user_details/1.json
  def show
    @user_details = ActiveRecord::Base.connection.execute("SELECT * from tbl_users as u
      JOIN tbl_detail_user_japans as dtj on u.user_id = dtj.tbl_user_id
      JOIN mst_japans as mst ON dtj.code_level = mst.code_level
      JOIN mst_groups as g ON u.mst_group_id = g.group_id WHERE user_id = #{params[:id]}").first

  end

  # GET /user_details/new
  def new
    @user_detail = UserDetail.new
  end

  # GET /user_details/1/edit
  def edit
    puts "edit o day"
  end

  # POST /user_details or /user_details.json
  def create
    @user_detail = UserDetail.new(user_detail_params)

    respond_to do |format|
      if @user_detail.save
        format.html { redirect_to user_detail_url(@user_detail), notice: "User detail was successfully created." }
        format.json { render :show, status: :created, location: @user_detail }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @user_detail.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /user_details/1 or /user_details/1.json
  def update
    respond_to do |format|
      if @user_detail.update(user_detail_params)
        format.html { redirect_to user_detail_url(@user_detail), notice: "User detail was successfully updated." }
        format.json { render :show, status: :ok, location: @user_detail }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @user_detail.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /user_details/1 or /user_details/1.json
  def destroy
    if params[:delete].present?
      #  @delete = TblDetailUserJapan.where(tbl_user_id: params[:id]).first
      # @delete.destroy
      #  @delete1 = TblUser.where(user_id: params[:id]).first
      #  @delete1.destroy
      redirect_to successes_path
    elsif  params[:update].present?
      redirect_to edit_user_detail_path
    end

  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user_detail
      @user_detail = TblUser.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def user_detail_params
      params.fetch(:user_detail, {})
    end
end
