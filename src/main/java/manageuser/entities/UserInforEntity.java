/**
 * luvina softwware JSC, 2022
 * UserInforEntity.java,HuyNQ
 */
package manageuser.entities;

import java.sql.Date;
/**
 * Class định nghĩa đối tượng userInfor
 * @author HuyNQ
 *
 */
public class UserInforEntity {
	// thuộc tính
	private int userId;
	private int groupId;
	private String fullName;
	private Date birthday;
	private String group;
	private String email;
	private String tel;
	private String codeLevel;
	private String japanLevel;
	private Date endDate;
	private Date startDate;
	private int total;
	private String loginName;
	private String fullNameKana;
	private String pass;
	private String passConfirm;
	private int dayBirth;
	private int monthBirth;
	private int yearBirth;
	private int dayStart;
	private int monthStart;
	private int yearStart;
	private int dayEnd;
	private int monthEnd;
	private int yearEnd;
private String salt;
private int rule;
/**
 * @return the userId
 */
public int getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(int userId) {
	this.userId = userId;
}
/**
 * @return the groupId
 */
public int getGroupId() {
	return groupId;
}
/**
 * @param groupId the groupId to set
 */
public void setGroupId(int groupId) {
	this.groupId = groupId;
}
/**
 * @return the fullName
 */
public String getFullName() {
	return fullName;
}
/**
 * @param fullName the fullName to set
 */
public void setFullName(String fullName) {
	this.fullName = fullName;
}
/**
 * @return the birthday
 */
public Date getBirthday() {
	return birthday;
}
/**
 * @param birthday the birthday to set
 */
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
/**
 * @return the group
 */
public String getGroup() {
	return group;
}
/**
 * @param group the group to set
 */
public void setGroup(String group) {
	this.group = group;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the tel
 */
public String getTel() {
	return tel;
}
/**
 * @param tel the tel to set
 */
public void setTel(String tel) {
	this.tel = tel;
}
/**
 * @return the codeLevel
 */
public String getCodeLevel() {
	return codeLevel;
}
/**
 * @param codeLevel the codeLevel to set
 */
public void setCodeLevel(String codeLevel) {
	this.codeLevel = codeLevel;
}
/**
 * @return the japanLevel
 */
public String getJapanLevel() {
	return japanLevel;
}
/**
 * @param japanLevel the japanLevel to set
 */
public void setJapanLevel(String japanLevel) {
	this.japanLevel = japanLevel;
}
/**
 * @return the endDate
 */
public Date getEndDate() {
	return endDate;
}
/**
 * @param endDate the endDate to set
 */
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
/**
 * @return the startDate
 */
public Date getStartDate() {
	return startDate;
}
/**
 * @param startDate the startDate to set
 */
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
/**
 * @return the total
 */
public int getTotal() {
	return total;
}
/**
 * @param total the total to set
 */
public void setTotal(int total) {
	this.total = total;
}
/**
 * @return the loginName
 */
public String getLoginName() {
	return loginName;
}
/**
 * @param loginName the loginName to set
 */
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
/**
 * @return the fullNameKana
 */
public String getFullNameKana() {
	return fullNameKana;
}
/**
 * @param fullNameKana the fullNameKana to set
 */
public void setFullNameKana(String fullNameKana) {
	this.fullNameKana = fullNameKana;
}
/**
 * @return the pass
 */
public String getPass() {
	return pass;
}
/**
 * @param pass the pass to set
 */
public void setPass(String pass) {
	this.pass = pass;
}
/**
 * @return the passConfirm
 */
public String getPassConfirm() {
	return passConfirm;
}
/**
 * @param passConfirm the passConfirm to set
 */
public void setPassConfirm(String passConfirm) {
	this.passConfirm = passConfirm;
}
/**
 * @return the dayBirth
 */
public int getDayBirth() {
	return dayBirth;
}
/**
 * @param dayBirth the dayBirth to set
 */
public void setDayBirth(int dayBirth) {
	this.dayBirth = dayBirth;
}
/**
 * @return the monthBirth
 */
public int getMonthBirth() {
	return monthBirth;
}
/**
 * @param monthBirth the monthBirth to set
 */
public void setMonthBirth(int monthBirth) {
	this.monthBirth = monthBirth;
}
/**
 * @return the yearBirth
 */
public int getYearBirth() {
	return yearBirth;
}
/**
 * @param yearBirth the yearBirth to set
 */
public void setYearBirth(int yearBirth) {
	this.yearBirth = yearBirth;
}
/**
 * @return the dayStart
 */
public int getDayStart() {
	return dayStart;
}
/**
 * @param dayStart the dayStart to set
 */
public void setDayStart(int dayStart) {
	this.dayStart = dayStart;
}
/**
 * @return the monthStart
 */
public int getMonthStart() {
	return monthStart;
}
/**
 * @param monthStart the monthStart to set
 */
public void setMonthStart(int monthStart) {
	this.monthStart = monthStart;
}
/**
 * @return the yearStart
 */
public int getYearStart() {
	return yearStart;
}
/**
 * @param yearStart the yearStart to set
 */
public void setYearStart(int yearStart) {
	this.yearStart = yearStart;
}
/**
 * @return the dayEnd
 */
public int getDayEnd() {
	return dayEnd;
}
/**
 * @param dayEnd the dayEnd to set
 */
public void setDayEnd(int dayEnd) {
	this.dayEnd = dayEnd;
}
/**
 * @return the monthEnd
 */
public int getMonthEnd() {
	return monthEnd;
}
/**
 * @param monthEnd the monthEnd to set
 */
public void setMonthEnd(int monthEnd) {
	this.monthEnd = monthEnd;
}
/**
 * @return the yearEnd
 */
public int getYearEnd() {
	return yearEnd;
}
/**
 * @param yearEnd the yearEnd to set
 */
public void setYearEnd(int yearEnd) {
	this.yearEnd = yearEnd;
}
/**
 * @return the salt
 */
public String getSalt() {
	return salt;
}
/**
 * @param salt the salt to set
 */
public void setSalt(String salt) {
	this.salt = salt;
}
/**
 * @return the rule
 */
public int getRule() {
	return rule;
}
/**
 * @param rule the rule to set
 */
public void setRule(int rule) {
	this.rule = rule;
}
	

	

}
