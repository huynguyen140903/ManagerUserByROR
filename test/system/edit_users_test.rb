require "application_system_test_case"

class EditUsersTest < ApplicationSystemTestCase
  setup do
    @edit_user = edit_users(:one)
  end

  test "visiting the index" do
    visit edit_users_url
    assert_selector "h1", text: "Edit users"
  end

  test "should create edit user.js" do
    visit edit_users_url
    click_on "New edit user.js"

    click_on "Create Edit user.js"

    assert_text "Edit user.js was successfully created"
    click_on "Back"
  end

  test "should update Edit user.js" do
    visit edit_user_url(@edit_user)
    click_on "Edit this edit user.js", match: :first

    click_on "Update Edit user.js"

    assert_text "Edit user.js was successfully updated"
    click_on "Back"
  end

  test "should destroy Edit user.js" do
    visit edit_user_url(@edit_user)
    click_on "Destroy this edit user.js", match: :first

    assert_text "Edit user.js was successfully destroyed"
  end
end
