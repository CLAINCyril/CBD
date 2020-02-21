<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page isELIgnored="false"%>

<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Browse attribute computers -->

	<c:forEach items="${list}" var="item" varStatus="status">
		<tr>
			<td class="editMode"><input type="checkbox" name="cb"
				class="cb" value="${item.id}"></td>
			<td>
				<form action="EditComputer" method="GET">
					<input type="hidden" name="computerId" value="${item.id}">
					<input type="submit" value="${item.name}" class="btn btn-primary">
				</form>
			</td>
			<td><c:out value="${item.introduced}" /></td>
			<td><c:out value="${item.discontinued}" /></td>
			<td><c:out value="${item.companyId.id}" /></td>
		</tr>
		
	</c:forEach>

</body>
</html>
