class CreateParentAndChildTables < ActiveRecord::Migration[7.1]
  def change
    create_table :parent_and_child_tables do |t|

      t.timestamps
    end
  end
end
