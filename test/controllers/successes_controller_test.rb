require "test_helper"

class SuccessesControllerTest < ActionDispatch::IntegrationTest
  setup do
    @success = successes(:one)
  end

  test "should get index" do
    get successes_url
    assert_response :success
  end

  test "should get new" do
    get new_success_url
    assert_response :success
  end

  test "should create success" do
    assert_difference("Success.count") do
      post successes_url, params: { success: {  } }
    end

    assert_redirected_to success_url(Success.last)
  end

  test "should show success" do
    get success_url(@success)
    assert_response :success
  end

  test "should get edit" do
    get edit_success_url(@success)
    assert_response :success
  end

  test "should update success" do
    patch success_url(@success), params: { success: {  } }
    assert_redirected_to success_url(@success)
  end

  test "should destroy success" do
    assert_difference("Success.count", -1) do
      delete success_url(@success)
    end

    assert_redirected_to successes_url
  end
end
