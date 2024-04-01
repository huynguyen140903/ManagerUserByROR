require "application_system_test_case"

class SystemErrorsTest < ApplicationSystemTestCase
  setup do
    @system_error = system_errors(:one)
  end

  test "visiting the index" do
    visit system_errors_url
    assert_selector "h1", text: "System errors"
  end

  test "should create system error" do
    visit system_errors_url
    click_on "New system error"

    click_on "Create System error"

    assert_text "System error was successfully created"
    click_on "Back"
  end

  test "should update System error" do
    visit system_error_url(@system_error)
    click_on "Edit this system error", match: :first

    click_on "Update System error"

    assert_text "System error was successfully updated"
    click_on "Back"
  end

  test "should destroy System error" do
    visit system_error_url(@system_error)
    click_on "Destroy this system error", match: :first

    assert_text "System error was successfully destroyed"
  end
end
