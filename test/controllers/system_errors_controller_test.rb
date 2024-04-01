require "test_helper"

class SystemErrorsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @system_error = system_errors(:one)
  end

  test "should get index" do
    get system_errors_url
    assert_response :success
  end

  test "should get new" do
    get new_system_error_url
    assert_response :success
  end

  test "should create system_error" do
    assert_difference("SystemError.count") do
      post system_errors_url, params: { system_error: {  } }
    end

    assert_redirected_to system_error_url(SystemError.last)
  end

  test "should show system_error" do
    get system_error_url(@system_error)
    assert_response :success
  end

  test "should get edit" do
    get edit_system_error_url(@system_error)
    assert_response :success
  end

  test "should update system_error" do
    patch system_error_url(@system_error), params: { system_error: {  } }
    assert_redirected_to system_error_url(@system_error)
  end

  test "should destroy system_error" do
    assert_difference("SystemError.count", -1) do
      delete system_error_url(@system_error)
    end

    assert_redirected_to system_errors_url
  end
end
