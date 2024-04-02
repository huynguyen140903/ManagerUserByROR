/**
 * luvina softwware JSC, 2022
 * TblUserLogic.java,HuyNQ

 */
package manageuser.logic.impl;

import java.util.List;

import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.logic.MstGroupLogic;
/**
 * Class này dùng để định nghĩa lại các phương thức dùng chung của lớp InterFace  
 * @author HuyNQ
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	@Override
	public List<MstGroupEntity> getAllMstGroup() {
		MstGroupDaoImpl groupDaoImpl = new MstGroupDaoImpl();
		return groupDaoImpl.getAllMstGroup();
	}

	@Override
	public String getGroupNameByGroupId(int groupId) {
		MstGroupDaoImpl groupDaoImpl = new MstGroupDaoImpl();
		List<MstGroupEntity> mstGroupEntities = groupDaoImpl.getAllMstGroup();
		// Nếu trong list mstGroup có groupId == groupId tham số thì trả ra 1 groupName
		for (int i = 0; i < mstGroupEntities.size(); i++) {
			if (mstGroupEntities.get(i).getGroupId() == groupId) {
				return mstGroupEntities.get(i).getGroupName();
			}
		}
		return "";
	}
}
