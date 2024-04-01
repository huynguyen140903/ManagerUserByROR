class EditUsersController < ApplicationController
  before_action :set_edit_user, only: %i[ show edit update destroy ]

  # GET /edit_users or /edit_users.json
  def index
    @edit_users = EditUser.all
  end

  # GET /edit_users/1 or /edit_users/1.json
  def show
  end

  # GET /edit_users/new
  def new
    @edit_user = EditUser.new
  end

  # GET /edit_users/1/edit
  def edit
  end

  # POST /edit_users or /edit_users.json
  def create
    @edit_user = EditUser.new(edit_user_params)

    respond_to do |format|
      if @edit_user.save
        format.html { redirect_to edit_user_url(@edit_user), notice: "Edit user.js was successfully created." }
        format.json { render :show, status: :created, location: @edit_user }
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @edit_user.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /edit_users/1 or /edit_users/1.json
  def update
    respond_to do |format|
      if @edit_user.update(edit_user_params)
        format.html { redirect_to edit_user_url(@edit_user), notice: "Edit user.js was successfully updated." }
        format.json { render :show, status: :ok, location: @edit_user }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @edit_user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /edit_users/1 or /edit_users/1.json
  def destroy
    @edit_user.destroy!

    respond_to do |format|
      format.html { redirect_to edit_users_url, notice: "Edit user.js was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_edit_user
      @edit_user = EditUser.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def edit_user_params
      params.fetch(:edit_user, {})
    end
end
