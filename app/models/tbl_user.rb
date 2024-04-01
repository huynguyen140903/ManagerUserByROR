class TblUser < ApplicationRecord
  validates :login_name, presence: true
  validates :password, presence: true

  belongs_to :mst_group
  has_many :tbl_detail_user_japans
end
