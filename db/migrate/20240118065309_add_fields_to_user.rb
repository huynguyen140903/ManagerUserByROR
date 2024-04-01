class AddFieldsToUser < ActiveRecord::Migration[7.1]
  def change
    add_column :users, :user_id, :integer, null:false
    add_column :users, :group_id, :integer, null:false
    add_column :users, :login_name, :string, null:false
    add_column :users, :password, :string, null:false
    add_column :users, :full_name, :string, null:false
    add_column :users, :full_name_kana, :string
    add_column :users, :email, :string, null:false
    add_column :users, :tel, :string, null:false
    add_column :users, :birthday, :date, null:false
  end
end
