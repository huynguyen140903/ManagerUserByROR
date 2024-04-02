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
	<jsp:include page="header.jsp" />

	<!-- Begin vung dieu kien tim kiem -->
	<form action="listUser.do" method="get" name="mainform">
		<input name="actionType" value="search" type="hidden" />
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="name" value="<c:out value="${fullName}"></c:out>"
								size="20" onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:forEach items="${listGroup}" var="group">
										<option value="${group.getGroupId()}"
											${groupId == group.getGroupId()?'selected':'' }>
											<c:if test="${fn:length(group.getGroupName())>25 }">
												<c:out value="${fn:substring(group.getGroupName(),0,25)}..."></c:out>
											</c:if>
											<c:if test="${fn:length(group.getGroupName())<=25 }">
												<c:out value="${group.getGroupName()}"></c:out>
											</c:if>


										</option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button"
								onclick="window.location='AddUserInput.do'" value="新規追加" /></td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<c:if test="${totalUsers > 0}">
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="80%">

			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<th align="left">氏名 <a
					href="?actionType=sort&sortType=FullName&name=<c:out value="${fullName}"></c:out>&group_id=${groupId}&sortByFullName=	
				<c:if test="${sortByFullName eq 'desc'}">asc</c:if>
				<c:if test="${sortByFullName eq 'asc'}">desc</c:if>">
						<c:if test="${sortByFullName eq 'desc'}">△▼</c:if> <c:if
							test="${sortByFullName eq 'asc'}">▲▽</c:if>
				</a>
				</th>


				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<th align="left">日本語能力 <a
					href="${requestScope['javax.servlet.forward.request_uri']}?actionType=sort&sortType=CodeLevel&name=<c:out value="${fullName}"></c:out>&group_id=${groupId}&sortByCodeLevel=	
				<c:if test="${sortByCodeLevel eq 'desc'}">asc</c:if>
				<c:if test="${sortByCodeLevel eq 'asc'}">desc</c:if>">
						<c:if test="${sortByCodeLevel eq 'desc'}">▲▽</c:if> <c:if
							test="${sortByCodeLevel eq 'asc'}">△▼</c:if>
				</a>
				</th>
				<th align="left">失効日 <a
					href="${requestScope['javax.servlet.forward.request_uri']}?actionType=sort&sortType=EndDate&name=<c:out value="${fullName}"></c:out>&group_id=${groupId}&sortByEndDate=	
				<c:if test="${sortByEndDate eq 'desc'}">asc</c:if>
				<c:if test="${sortByEndDate eq 'asc'}">desc</c:if>">
						<c:if test="${sortByEndDate eq 'desc'}">△▼</c:if> <c:if
							test="${sortByEndDate eq 'asc'}">▲▽</c:if>
				</a>
				</th>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${listUserInfors}" var="user">


				<tr>
					<td align="right"><a href="ShowUser.do?userId=${user.getUserId()}"><c:out
								value="${user.getUserId()}"></c:out></a></td>
					<td><c:if test="${fn:length(user.getFullName())>25 }">
							<c:out value="${fn:substring(user.getFullName(),0,25)}..."></c:out>
						</c:if> <c:if test="${fn:length(user.getFullName())<=25 }">
							<c:out value="${user.getFullName()}"></c:out>
						</c:if></td>
					<td align="center"><c:out
							value="${fn:replace(user.getBirthday(), '-', '/')}"></c:out></td>
					<td><c:if test="${fn:length(user.getGroup())>25 }">
							<c:out value="${fn:substring(user.getGroup(),0,25)}..."></c:out>
						</c:if> <c:if test="${fn:length(user.getGroup())<=25 }">
							<c:out value="${user.getGroup()}"></c:out>
						</c:if></td>
					<td><c:if test="${fn:length(user.getEmail())>25 }">
							<c:out value="${fn:substring(user.getEmail(),0,25)}..."></c:out>
						</c:if> <c:if test="${fn:length(user.getEmail())<=25 }">
							<c:out value="${user.getEmail()}"></c:out>
						</c:if></td>
					<td><c:out value="${user.getTel()}"></c:out></td>
					<td><c:if test="${fn:length(user.getJapanLevel())>25 }">
							<c:out value="${fn:substring(user.getJapanLevel(),0,25)}..."></c:out>
						</c:if> <c:if test="${fn:length(user.getJapanLevel())<=25 }">
							<c:out value="${user.getJapanLevel()}"></c:out>
						</c:if></td>
					<td align="center"><c:out
							value="${fn:replace(user.getEndDate(), '-', '/')}"></c:out></td>
					<td align="right"><c:if test="${user.getTotal()>0}">
							<c:out value="${user.getTotal ()}"></c:out>
						</c:if></td>
				</tr>

			</c:forEach>

		</table>
	</c:if>
	<c:if test="${totalUsers < 1}">
		<b style="margin-left: 40%"><c:out value="${MSG005}"></c:out></b>
	</c:if>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<c:if test="${totalPage>0}">
		<table>

			<tr>
				<td class="lbl_paging"><a
					href="
					<c:if test="${sortType eq 'default'}">?actionType=sort&sortType=default&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&currentPage=${listPaging.get(0)-pageLimit}</c:if>
					<c:if test="${sortType eq 'FullName'}">?actionType=sort&sortType=FullName&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByFullName=${sortByFullName}&currentPage=${listPaging.get(0)-pageLimit}</c:if>
					<c:if test="${sortType eq 'CodeLevel'}">?actionType=sort&sortType=CodeLevel&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByCodeLevel=${sortByCodeLevel}&currentPage=${listPaging.get(0)-pageLimit}</c:if>
					<c:if test="${sortType eq 'EndDate'}">?actionType=sort&sortType=EndDate&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByEndDate=${sortByEndDate}&currentPage=${listPaging.get(0)-pageLimit}</c:if>			
			"><c:if
							test="${currentPage>pageLimit}"><<</c:if></a> <c:forEach
						items="${listPaging}" var="page">
						<a
							href="
					<c:if test="${sortType eq 'default'}">?actionType=sort&sortType=default&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&currentPage=${page}</c:if>
					<c:if test="${sortType eq 'FullName'}">?actionType=sort&sortType=FullName&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByFullName=${sortByFullName}&currentPage=${page}</c:if>
					<c:if test="${sortType eq 'CodeLevel'}">?actionType=sort&sortType=CodeLevel&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByCodeLevel=${sortByCodeLevel}&currentPage=${page}</c:if>
					<c:if test="${sortType eq 'EndDate'}">?actionType=sort&sortType=EndDate&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByEndDate=${sortByEndDate}&currentPage=${page}</c:if>
					">
							<c:out value="${page}"></c:out>
						</a> &nbsp;

			</c:forEach> <a
					href="
					<c:if test="${sortType eq 'default'}">?actionType=sort&sortType=default&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&currentPage=${listPaging.get(listPaging.size()-1)+1}</c:if>
					<c:if test="${sortType eq 'FullName'}">?actionType=sort&sortType=FullName&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByFullName=${sortByFullName}&currentPage=${listPaging.get(listPaging.size()-1)+1}</c:if>
					<c:if test="${sortType eq 'CodeLevel'}">?actionType=sort&sortType=CodeLevel&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByCodeLevel=${sortByCodeLevel}&currentPage=${listPaging.get(listPaging.size()-1)+1}</c:if>
					<c:if test="${sortType eq 'EndDate'}">?actionType=sort&sortType=EndDate&name=<c:out value="${fn:replace(fullName, '%', '%25')}"></c:out>&group_id=${groupId}&sortByEndDate=${sortByEndDate}&currentPage=${listPaging.get(listPaging.size()-1)+1}</c:if>
					
			
			"><c:if
							test="${totalPage-listPaging.get(0)>=pageLimit}">>></c:if></a></td>


			</tr>
		</table>
	</c:if>
	<!-- End vung paging -->
	<jsp:include page="footer.jsp" />


</body>

</html>