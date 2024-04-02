/**
 * luvina softwware JSC, 2022
 * MstJapanDao.java,HuyNQ

 */
package manageuser.dao;

import java.util.List;

import manageuser.entities.MstJapanEntity;

/**
 * Class này dùng để khai báo các phương thức dùng chung
 * @author HuyNQ
 *
 */
public interface MstJapanDao {
	/**
	 * Lấy toàn bộ list trình độ tiếng nhật trong DB
	 * @return List<MstJapanEntity>: danh sách trình độ tiếng nhật có trong DB
	 */
	public List<MstJapanEntity> getAllMstJapan();
}
