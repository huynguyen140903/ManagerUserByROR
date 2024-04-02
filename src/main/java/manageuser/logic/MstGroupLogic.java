/**
 * luvina softwware JSC, 2022
 * MstGroup.java,HuyNQ
 */
package manageuser.logic;

import java.util.List;

import manageuser.entities.MstGroupEntity;
/**
 * Class này dùng để khai báo các phương thức dùng chung
 * @author HuyNQ
 *
 */
public interface MstGroupLogic {
	/**
	 * Phương thức lấy toàn bộ group trong DB
	 * 
	 * @return List<MstGroupEntity>
	 */
	public List<MstGroupEntity> getAllMstGroup();

	/**
	 * Phương thức lấy tên của group thông qua group id
	 * 
	 * @param groupId
	 * @return
	 */
	public String getGroupNameByGroupId(int groupId);
}
