require "application_system_test_case"

class ConfirmsTest < ApplicationSystemTestCase
  setup do
    @confirm = confirms(:one)
  end

  test "visiting the index" do
    visit confirms_url
    assert_selector "h1", text: "Confirms"
  end

  test "should create confirm" do
    visit confirms_url
    click_on "New confirm"

    click_on "Create Confirm"

    assert_text "Confirm was successfully created"
    click_on "Back"
  end

  test "should update Confirm" do
    visit confirm_url(@confirm)
    click_on "Edit this confirm", match: :first

    click_on "Update Confirm"

    assert_text "Confirm was successfully updated"
    click_on "Back"
  end

  test "should destroy Confirm" do
    visit confirm_url(@confirm)
    click_on "Destroy this confirm", match: :first

    assert_text "Confirm was successfully destroyed"
  end
end
