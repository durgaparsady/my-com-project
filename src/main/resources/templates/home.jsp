
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="ISO-8859-1">
<title>Pdf Builder</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body  >
   
	<div class="container  p-3 mt-5" >
		<div th:if="${session.msg}">
			<div class="alert alert-success" role="alert" style="text-align:center" th:text="${session.msg}">
	
			</div>
			<th:block th:text="${#session.removeAttribute('msg')}"></th:block>
		</div>
		<h2 >Templates List </h2>

	
<form th:action="@{/home}">
   <lable > <strong>Filter:</strong> </lable> <input type="text" name="keyword" id="keyword" size="50" th:value="${keyword}"  />
    &nbsp;
    <input type="submit" value="Search"  class="btn btn-primary btn-sm mb-2" />
    &nbsp;
        <button id="btnClear" class="btn btn-primary btn-sm mb-2"  ><a style=" text-decoration: none;color:white"  th:href="@{/home}">Clear</a></button>
    </form>
<script>

function deleteTemplate(){
	return confirm('Are you sure you want to Remove Template?');
}


</script>

		<table border="1" class="table table-responsive-md">
			<thead>
				<tr>
					<th style="width:75%;" ><h4>Template Name</h4></th>
					<th style="width:25%;" ><h4>Action</h4></th>

				</tr>
			</thead>
			<tbody >
				<tr th:each="templates : ${getTemplateList}">
					<td th:text="${templates.templateName}"></td>
					<td><a
						th:href="@{/updateTemplate/{id}(id=${templates.id})}">
						<i class='fas fa-edit' style='font-size:20px'></i></a>&nbsp;&nbsp;
						 <a
						th:href="@{/deleteTemplate/{id}(id=${templates.id})}"
					 onclick="return deleteTemplate()"><i class="fa fa-trash" style="font-size:20px;color:red"></i></a>&nbsp;&nbsp;
						
						<a th:href="@{/pdfTemplatePage/{id}(id=${templates.id})}">
						<i class="fa fa-file-pdf-o" style="font-size:20px;color:orange"></i></a></td>

				</tr>
			</tbody>
		</table>
		<a th:href="@{/createTemplate}" class="btn btn-primary btn-sm mb-3">
			Add Template </a>

		<div th:if="${totalPages > 1}">
			<div class="row col-sm-10">
				<div class="col-sm-4">Total Templates: [[${totalItems}]]</div>
				<div class="col-sm-3">
					<span th:each="i: ${#numbers.sequence(0, totalPages-1)}"> <a
						th:if="${pageNo != i}"
						th:href="@{'/home?pageNo=' + ${i}+ '&sortBy=' + ${sortBy} }">[[${i+1}]]</a>
						<span th:unless="${pageNo != i}">[[${i+1}]]</span> &nbsp; &nbsp;
					</span>
				</div>
				<div class="col-sm-1">
					<a th:if="${pageNo < totalPages-1}"
						th:href="@{'/home?pageNo=' + ${pageNo + 1}+ '&sortBy=' + ${sortBy}}">Next</a>
					<span th:unless="${pageNo < totalPages-1}">Next</span>
				</div>

				<div class="col-sm-1">
					<a th:if="${pageNo > 0}"
						th:href="@{'/home?pageNo=' + ${pageNo - 1}+ '&sortBy=' + ${sortBy}}">Previous</a>
							<span th:unless="${pageNo > 0}">Previous</span>
				</div>
			</div>
		</div>
	</div>
 
</body>
</html>


