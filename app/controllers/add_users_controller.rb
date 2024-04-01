class AddUsersController < ApplicationController
  before_action :set_add_user, only: %i[ show edit update destroy ]

  # GET /add_users or /add_users.json
  def index
    @add_users = AddUser.all
  end

  # GET /add_users/1 or /add_users/1.json
  def show
  end

  # GET /add_users/new
  def new
    @add_user = AddUser.new
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
