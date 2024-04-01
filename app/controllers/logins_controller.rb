class LoginsController < ApplicationController
  # before_action :set_login, only: %i[ show edit update destroy ]

  # GET /logins or /logins.json
  def index
    @logins = Login.all

  end

  # GET /logins/1 or /logins/1.json
  def show
  end

  # GET /logins/new
  def new
    # @login = Login.new

  end

  # GET /logins/1/edit
  def edit
  end

  # POST /logins or /logins.json
  def create
    user = TblUser.find_by login_name: params[:loginId]
    if params[:loginId].blank? && params[:password].blank?
      flash[:notice] = [Constant::Error::ERR_001_LOGIN_NAME, Constant::Error::ERR_001_PASSWORD]
      redirect_to logins_path
    elsif user.present? && params[:password] == user.password
      redirect_to list_users_path
    else
      flash[:notice] = [Constant::Error::ERR_0016]
      redirect_to logins_path
    end
  end

  # PATCH/PUT /logins/1 or /logins/1.json
  def update
    respond_to do |format|
      if @login.update(login_params)
        format.html { redirect_to login_url(@login), notice: "Login was successfully updated." }
        format.json { render :show, status: :ok, location: @login }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @login.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /logins/1 or /logins/1.json
  def destroy
    @login.destroy!

    respond_to do |format|
      format.html { redirect_to logins_url, notice: "Login was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_login
      @login = Login.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def login_params
      params.fetch(:login, {})
    end
  end

