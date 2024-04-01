require "application_system_test_case"

class ListUsersTest < ApplicationSystemTestCase
  setup do
    @list_user = list_users(:one)
  end

  test "visiting the index" do
    visit list_users_url
    assert_selector "h1", text: "List users"
  end

  test "should create list user.js" do
    visit list_users_url
    click_on "New list user.js"

    click_on "Create List user.js"

    assert_text "List user.js was successfully created"
    click_on "Back"
  end

  test "should update List user.js" do
    visit list_user_url(@list_user)
    click_on "Edit this list user.js", match: :first

    click_on "Update List user.js"

    assert_text "List user.js was successfully updated"
    click_on "Back"
  end

  test "should destroy List user.js" do
    visit list_user_url(@list_user)
    click_on "Destroy this list user.js", match: :first

    assert_text "List user.js was successfully destroyed"
  end
end
