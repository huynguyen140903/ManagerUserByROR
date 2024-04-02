<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<form action="EditUserInput.do" method="get" id = "viewform">
		<input name="userId" value="${userInforEntity.getUserId()}"
			type="hidden" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">情報確認</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>

			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left"><c:out
										value="${userInforEntity.getLoginName()}"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left"><c:out
										value="${userInforEntity.getGroup() }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left"><c:out
										value="${userInforEntity.getFullName() }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><c:out
										value="${userInforEntity.getFullNameKana() }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left"><c:out
										value="${fn:replace(userInforEntity.getBirthday(), '-', '/') }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left"><c:out
										value="${userInforEntity.getEmail() }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left"><c:out
										value="${userInforEntity.getTel() }"></c:out></td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="Show()">日本語能力</a></th>
							</tr>
						</table>
					</div>
					<div id="mydiv" style="padding-left: 100px; display: none;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><c:out
										value="${userInforEntity.getJapanLevel() }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><c:out
										value="${fn:replace(userInforEntity.getStartDate(), '-', '/')}"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><c:out
										value="${fn:replace(userInforEntity.getEndDate(), '-', '/') }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><c:if
										test="${userInforEntity.getTotal()==0}"></c:if> <c:if
										test="${userInforEntity.getTotal()>0}">${userInforEntity.getTotal()}</c:if>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 100px;"></div>

		<table border="0" cellpadding="4" cellspacing="0" width="300px">
			<tr>
				<th width="200px" align="center">&nbsp;</th>
				<td><input class="btn" type="submit" value="編集" /></td>

				<td><fmt:setBundle basename="manageuser.properties.message_ja"
						var="messageConfirm" /> 
					<input class="btn" type="button" value="削除" onclick="confirmDelete('<fmt:message key='MSG004' bundle='${messageConfirm}' />')" />
				</td>

				<td><a href="./listUser.do?actionType=back"><input
						class="btn" type="button" value="戻る" /></a></td>
			</tr>
		</table>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>

</html>