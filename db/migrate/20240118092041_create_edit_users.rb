class CreateEditUsers < ActiveRecord::Migration[7.1]
  def change
    create_table :edit_users do |t|

      t.timestamps
    end
  end
end
