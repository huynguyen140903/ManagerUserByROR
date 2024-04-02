<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/view.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<jsp:include page="header.jsp" />
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form action="
	<c:if test="${user.getUserId() == 0}">AddUserValidate.do</c:if>
	<c:if test="${user.getUserId() > 0}">EditUserValidate.do</c:if>"
		method="post" name="inputform">
		<input type="hidden" name="userId" value="${user.userId}"></input>
		<input name="action" value="validate" type="hidden" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<c:forEach items=" ${listError} " var="error">
				<tr>
					<td class="errMsg">
						<div style="padding-left: 120px">
							<c:out value="${error}"></c:out>
						</div>
					</td>
				</tr>
			</c:forEach>


			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="loginName"
									value="<c:out value="${user.getLoginName()}"></c:out>"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';"
									<c:if test = "${user.getUserId() gt 0}">readonly</c:if> />
								</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="group_id">
										<option value="0">選択してください</option>
										<c:forEach items="${listGroup}" var="group">
											<option value="${group.getGroupId()}"
												<c:if test="${group.getGroupId() == user.getGroupId()}">selected="selected"</c:if>>
												<c:if test="${fn:length(group.getGroupName())>25 }">
													<c:out
														value="${fn:substring(group.getGroupName(),0,25)}..."></c:out>
												</c:if>
												<c:if test="${fn:length(group.getGroupName())<=25 }">
													<c:out value="${group.getGroupName()}"></c:out>
												</c:if>



											</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="name"
									value="<c:out value="${user.getFullName()}"></c:out>" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="nameKana"
									value="<c:out value="${user.getFullNameKana()}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="yearBirth">

										<c:forEach items="${year}" var="year">
											<option value="${year}"
												<c:if test="${year ==user.getYearBirth()}">selected="selected"</c:if>>
												<c:out value="${year}"></c:out>
											</option>
										</c:forEach>


								</select>年 <select name="monthBirth">

										<c:forEach items="${month}" var="month">
											<option value="${month}"
												<c:if test="${month == user.getMonthBirth()}">selected="selected"</c:if>>
												<c:out value="${month}"></c:out>
											</option>
										</c:forEach>

								</select>月 <select name="dayBirth">
										<c:forEach items="${day}" var="day">
											<option value="${day}"
												<c:if test="${day == user.getDayBirth()}">selected="selected"</c:if>>
												<c:out value="${day}"></c:out>
											</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="<c:out value="${user.getEmail()}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="<c:out value="${user.getTel()}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr
								<c:if test="${user.getUserId() gt 0}">style = "display: none"</c:if>>
								
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="pass" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr
								<c:if test="${user.getUserId() gt 0}">style = "display: none"</c:if>>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="passConfirm" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="#" onclick="Show()">日本語能力</a></th>
							</tr>
						</table>
					</div>
					<div id="mydiv" style="padding-left: 100px; display: none">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="level">
										<option value="0">選択してください</option>
										<c:forEach items="${listJapan}" var="listJapan">
											<option value="${listJapan.getCodeLevel()}"
												<c:if test="${listJapan.getCodeLevel() == user.getCodeLevel()}">selected="selected"</c:if>>

												<c:if test="${fn:length(listJapan.getNameLevel())>25 }">
													<c:out
														value="${fn:substring(listJapan.getNameLevel(),0,25)}..."></c:out>
												</c:if>
												<c:if test="${fn:length(listJapan.getNameLevel())<=25 }">
													<c:out value="${listJapan.getNameLevel()}"></c:out>
												</c:if>


											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="yearStart">
										<c:forEach items="${year}" var="year">
											<option value="${year}"
												<c:if test="${year ==user.getYearStart()}">selected="selected"</c:if>>
												<c:out value="${year}"></c:out>
											</option>
										</c:forEach>
								</select>年 <select name="monthStart">
										<c:forEach items="${month}" var="month">
											<option value="${month}"
												<c:if test="${month == user.getMonthStart()}">selected="selected"</c:if>>
												<c:out value="${month}"></c:out>
											</option>
										</c:forEach>
								</select>月 <select name="dayStart">
										<c:forEach items="${day}" var="day">
											<option value="${day}"
												<c:if test="${day == user.getDayStart()}">selected="selected"</c:if>>
												<c:out value="${day}"></c:out>
											</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="yearEnd">
										<c:forEach items="${yearEndDate}" var="yearEndDate">
											<option value="${yearEndDate}"
												<c:if test="${yearEndDate == user.getYearEnd()}">selected="selected"</c:if>>
												<c:out value="${yearEndDate}"></c:out>
											</option>
										</c:forEach>
								</select>年 <select name="monthEnd">
										<c:forEach items="${month}" var="month">
											<option value="${month}"
												<c:if test="${month == user.getMonthEnd()}">selected="selected"</c:if>>
												<c:out value="${month}"></c:out>
											</option>
										</c:forEach>
								</select>月 <select name="dayEnd">
										<c:forEach items="${day}" var="day">
											<option value="${day}"
												<c:if test="${day == user.getDayEnd()}">selected="selected"</c:if>>
												<c:out value="${day}"></c:out>
											</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total"
									value="<c:if test="${user.getTotal()==0}"></c:if><c:if
										test="${user.getTotal()>0}">${user.getTotal()}</c:if>"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
						</table>



					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="
						<c:if test="${user.getUserId() eq 0}">window.location='listUser.do?actionType=back'</c:if>
						<c:if test="${user.getUserId() > 0}">window.location='ShowUser.do?action=back&key=${key}&userId=${user.getUserId() } '</c:if>					
						" /></td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>

</html>