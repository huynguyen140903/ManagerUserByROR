/**
 * luvina softwware JSC, 2022
 * MstGroupDao.java,HuyNQ

 */
package manageuser.dao;

import java.util.List;

import manageuser.entities.MstGroupEntity;

/**
 * Class này dùng để khai báo các phương thức dùng chung
 * @author HuyNQ
 *
 */
public interface MstGroupDao{
	/**
	 * Phương thức lấy dữ liệu trong DB 
	 * @param loginName : Tên đăng nhập
	 * @return
	 */
	public List<MstGroupEntity> getAllMstGroup();
}
