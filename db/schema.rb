# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `bin/rails
# db:schema:load`. When creating a new database, `bin/rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema[7.1].define(version: 2024_01_18_094257) do
  create_table "add_users", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "articles", force: :cascade do |t|
    t.string "title"
    t.text "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "confirms", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "edit_users", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "list_users", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "logins", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

# Could not dump table "mst_group" because of following StandardError
#   Unknown type 'string' for column 'group_name'

# Could not dump table "mst_japan" because of following StandardError
#   Unknown type 'string' for column 'code_level'

  create_table "successes", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "system_errors", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

# Could not dump table "tbl_detail_user_japan" because of following StandardError
#   Unknown type 'string' for column 'code_level'

  create_table "tbl_user", primary_key: "user_id", force: :cascade do |t|
    t.integer "id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer "group_id", null: false
    t.string "login_name", null: false
    t.string "password", null: false
    t.string "full_name", null: false
    t.string "full_name_kana"
    t.string "email", null: false
    t.string "tel", null: false
    t.date "birthday", null: false
  end

  create_table "user_details", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_foreign_key "tbl_detail_user_japan", "mst_japan", column: "code_level", primary_key: "id"
  add_foreign_key "tbl_detail_user_japan", "tbl_user", column: "user_id", primary_key: "id"
  add_foreign_key "tbl_user", "mst_group", column: "group_id", primary_key: "id"
end
