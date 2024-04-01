class CreateSystemErrors < ActiveRecord::Migration[7.1]
  def change
    create_table :system_errors do |t|

      t.timestamps
    end
  end
end
