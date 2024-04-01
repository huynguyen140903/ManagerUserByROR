class CreateSuccesses < ActiveRecord::Migration[7.1]
  def change
    create_table :successes do |t|

      t.timestamps
    end
  end
end
