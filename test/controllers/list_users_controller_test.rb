require "test_helper"

class ListUsersControllerTest < ActionDispatch::IntegrationTest
  setup do
    @list_user = list_users(:one)
  end

  test "should get index" do
    get list_users_url
    assert_response :success
  end

  test "should get new" do
    get new_list_user_url
    assert_response :success
  end

  test "should create list_user" do
    assert_difference("ListUser.count") do
      post list_users_url, params: { list_user: {  } }
    end

    assert_redirected_to list_user_url(ListUser.last)
  end

  test "should show list_user" do
    get list_user_url(@list_user)
    assert_response :success
  end

  test "should get edit" do
    get edit_list_user_url(@list_user)
    assert_response :success
  end

  test "should update list_user" do
    patch list_user_url(@list_user), params: { list_user: {  } }
    assert_redirected_to list_user_url(@list_user)
  end

  test "should destroy list_user" do
    assert_difference("ListUser.count", -1) do
      delete list_user_url(@list_user)
    end

    assert_redirected_to list_users_url
  end
end
