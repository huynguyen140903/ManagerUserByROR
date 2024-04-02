/**
 * luvina softwware JSC, 2022
 * MstGroup.java,HuyNQ
 */
package manageuser.logic;

import java.util.List;

import manageuser.entities.MstJapanEntity;
/**
 * Class này dùng để khai báo các phương thức dùng chung
 * @author HuyNQ
 *
 */
public interface MstJapanLogic {
	/**
	 * Lấy danh sách trình độ tiếng nhật
	 * 
	 * @return
	 */
	public List<MstJapanEntity> getAllMstJapan();

	/**
	 * Lấy tên trình độ tiếng nhật thông qua codelevel
	 * 
	 * @param codeLevel
	 * @return
	 */
	public String getNameLevelByCodeLevel(String codeLevel);
}
