require "test_helper"

class AddUsersControllerTest < ActionDispatch::IntegrationTest
  setup do
    @add_user = add_users(:one)
  end

  test "should get index" do
    get add_users_url
    assert_response :success
  end

  test "should get new" do
    get new_add_user_url
    assert_response :success
  end

  test "should create add_user" do
    assert_difference("AddUser.count") do
      post add_users_url, params: { add_user: {  } }
    end

    assert_redirected_to add_user_url(AddUser.last)
  end

  test "should show add_user" do
    get add_user_url(@add_user)
    assert_response :success
  end

  test "should get edit" do
    get edit_add_user_url(@add_user)
    assert_response :success
  end

  test "should update add_user" do
    patch add_user_url(@add_user), params: { add_user: {  } }
    assert_redirected_to add_user_url(@add_user)
  end

  test "should destroy add_user" do
    assert_difference("AddUser.count", -1) do
      delete add_user_url(@add_user)
    end

    assert_redirected_to add_users_url
  end
end
