class CreateListUsers < ActiveRecord::Migration[7.1]
  def change
    create_table :list_users do |t|

      t.timestamps
    end
  end
end
