require "test_helper"

class EditUsersControllerTest < ActionDispatch::IntegrationTest
  setup do
    @edit_user = edit_users(:one)
  end

  test "should get index" do
    get edit_users_url
    assert_response :success
  end

  test "should get new" do
    get new_edit_user_url
    assert_response :success
  end

  test "should create edit_user" do
    assert_difference("EditUser.count") do
      post edit_users_url, params: { edit_user: {  } }
    end

    assert_redirected_to edit_user_url(EditUser.last)
  end

  test "should show edit_user" do
    get edit_user_url(@edit_user)
    assert_response :success
  end

  test "should get edit" do
    get edit_edit_user_url(@edit_user)
    assert_response :success
  end

  test "should update edit_user" do
    patch edit_user_url(@edit_user), params: { edit_user: {  } }
    assert_redirected_to edit_user_url(@edit_user)
  end

  test "should destroy edit_user" do
    assert_difference("EditUser.count", -1) do
      delete edit_user_url(@edit_user)
    end

    assert_redirected_to edit_users_url
  end
end
