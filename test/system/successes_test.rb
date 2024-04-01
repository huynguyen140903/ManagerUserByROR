require "application_system_test_case"

class SuccessesTest < ApplicationSystemTestCase
  setup do
    @success = successes(:one)
  end

  test "visiting the index" do
    visit successes_url
    assert_selector "h1", text: "Successes"
  end

  test "should create success" do
    visit successes_url
    click_on "New success"

    click_on "Create Success"

    assert_text "Success was successfully created"
    click_on "Back"
  end

  test "should update Success" do
    visit success_url(@success)
    click_on "Edit this success", match: :first

    click_on "Update Success"

    assert_text "Success was successfully updated"
    click_on "Back"
  end

  test "should destroy Success" do
    visit success_url(@success)
    click_on "Destroy this success", match: :first

    assert_text "Success was successfully destroyed"
  end
end
