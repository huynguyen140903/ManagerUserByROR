class CreateAddUsers < ActiveRecord::Migration[7.1]
  def change
    create_table :add_users do |t|

      t.timestamps
    end
  end
end
