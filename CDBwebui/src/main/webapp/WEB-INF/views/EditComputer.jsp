
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@ page isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	
	<div class="container">
		<br></br>
	
		<a class="navbar-brand" href="ListComputer"> Application -
			Computer Database </a>
	</div>
	

	<script type="text/javascript">
		function googleTranslateElementInit() {
			new google.translate.TranslateElement({
				pageLanguage : 'en'
			}, 'google_translate_element');
		}
	</script>

	<script type="text/javascript"
		src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>



	<script type="text/javascript"
		src="https://translate.google.com/translate_a/element.js? 
        cb=googleTranslateElementInit">
		
	</script>
	</header>
	
	
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id:
					${computerDTO.id}</div>
				<h1>Edit Computer</h1>

				<form action="EditComputer" method="POST">
					<input type="hidden" value="${computerDTO.id}" name="computerId"
						id="computerId" />
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" name="computerName"
								id="computerName" placeholder="${computerDTO.name}" required
								maxlength="255">
						</div>

						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="text" name="introduced" class="form-control"
								id="introduced">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="text" name="discontinued" class="form-control"
								id="discontinued">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" name="companyId" id="companyId">
								<c:forEach items="${companysDTO}" var="company">
									<c:if test="${ company.id==computerToUpdate.company.id}">
										<option value="${company.id}" selected><c:out
												value="${company.name}"></c:out></option>
									</c:if>
									<c:if test="${company.id!=computerToUpdate.company.id}">
										<option value="${company.id}">
											<c:out value="${company.name}"></c:out>
										</option>
									</c:if>
								</c:forEach>

							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary">
						or <a href="ListComputer" class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script src="js/dateValidation.js"></script>

</body>
</html>