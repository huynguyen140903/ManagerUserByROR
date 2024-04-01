class CreateConfirms < ActiveRecord::Migration[7.1]
  def change
    create_table :confirms do |t|

      t.timestamps
    end
  end
end
