/**
 * luvina softwware JSC, 2022
 * TblUserLogic.java,HuyNQ

 */
package manageuser.logic.impl;

import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapanEntity;
import manageuser.logic.MstJapanLogic;

/**
 * 
 * @author HuyNQ
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	@Override
	public List<MstJapanEntity> getAllMstJapan() {
		MstJapanDao mstJapanDao = new MstJapanDaoImpl();
		return mstJapanDao.getAllMstJapan();
	}

	@Override
	public String getNameLevelByCodeLevel(String codeLevel) {
		MstJapanDao mstJapanDao = new MstJapanDaoImpl();
		List<MstJapanEntity> mstJapanEntities = new ArrayList<MstJapanEntity>();
		mstJapanEntities = mstJapanDao.getAllMstJapan();
		// Nếu trong list mstJapanEntities có codeLevel == codeLevel tham số thì trả ra
		// 1 Namelevel
		for (int i = 0; i < mstJapanEntities.size(); i++) {
			if (codeLevel.equals(mstJapanEntities.get(i).getCodeLevel())) {
				return mstJapanEntities.get(i).getNameLevel();
			}
		}
		return null;
	}

}
