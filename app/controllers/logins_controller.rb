class LoginsController < ApplicationController
  # before_action :set_login, only: %i[ show edit update destroy ]

  # GET /logins or /logins.json
  def index
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
      session[:user_id] = user.user_id
      redirect_to list_users_path
    else
      flash[:notice] = [Constant::Error::ERR_0016]
      redirect_to logins_path
    end
  end

  # PATCH/PUT /logins/1 or /logins/1.json
  def update

  end

  # DELETE /logins/1 or /logins/1.json
  def destroy
    # logout: check co user đang login thì xóa khỏi session
    if session[:user_id].present?
      session[:user_id] = nil
    end
    redirect_to logins_path
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

