class ApplicationController < ActionController::Base
  helper_method :current_user
  helper_method :logged_in?
  protect_from_forgery with: :null_session

  # get user hien tai dang login
  def current_user
    TblUser.find_by(user_id: session[:user_id])
  end

  # check user dang login co ton tai ko
  def logged_in?
    current_user.present?
  end
end
