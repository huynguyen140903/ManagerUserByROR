require "application_system_test_case"

class AddUsersTest < ApplicationSystemTestCase
  setup do
    @add_user = add_users(:one)
  end

  test "visiting the index" do
    visit add_users_url
    assert_selector "h1", text: "Add users"
  end

  test "should create add user.js" do
    visit add_users_url
    click_on "New add user.js"

    click_on "Create Add user.js"

    assert_text "Add user.js was successfully created"
    click_on "Back"
  end

  test "should update Add user.js" do
    visit add_user_url(@add_user)
    click_on "Edit this add user.js", match: :first

    click_on "Update Add user.js"

    assert_text "Add user.js was successfully updated"
    click_on "Back"
  end

  test "should destroy Add user.js" do
    visit add_user_url(@add_user)
    click_on "Destroy this add user.js", match: :first

    assert_text "Add user.js was successfully destroyed"
  end
end
