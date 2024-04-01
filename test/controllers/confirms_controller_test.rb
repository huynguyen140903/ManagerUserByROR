require "test_helper"

class ConfirmsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @confirm = confirms(:one)
  end

  test "should get index" do
    get confirms_url
    assert_response :success
  end

  test "should get new" do
    get new_confirm_url
    assert_response :success
  end

  test "should create confirm" do
    assert_difference("Confirm.count") do
      post confirms_url, params: { confirm: {  } }
    end

    assert_redirected_to confirm_url(Confirm.last)
  end

  test "should show confirm" do
    get confirm_url(@confirm)
    assert_response :success
  end

  test "should get edit" do
    get edit_confirm_url(@confirm)
    assert_response :success
  end

  test "should update confirm" do
    patch confirm_url(@confirm), params: { confirm: {  } }
    assert_redirected_to confirm_url(@confirm)
  end

  test "should destroy confirm" do
    assert_difference("Confirm.count", -1) do
      delete confirm_url(@confirm)
    end

    assert_redirected_to confirms_url
  end
end
