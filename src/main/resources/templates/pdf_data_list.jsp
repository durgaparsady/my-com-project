<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
 
<meta charset="ISO-8859-1">
 
<title>Test !!</title> 
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel=”stylesheet” href=”css/bootstrap.css”>
<link rel=”stylesheet” href=”css/bootstrap-responsive.css”>
</head>

<body>
 <section id="navbar">
<div class="mt-5 ml-5 mr-5">
	<div th:if="${session.msg}">
		<div class="alert alert-success" role="alert" style="text-align:center" th:text="${session.msg}">

		</div>
		<th:block th:text="${#session.removeAttribute('msg')}"></th:block>
	</div>
	<h1>PDF Data List !!</h1>
<div class="table-responsive">
	<table border="1" class="table table-striped table-responsive-md">
		<thead> 
		
			<span th:each="map : ${mapList[0]}"> 
				<th th:each="data : ${map}"><span th:text="${data.key}"></span></th>

			</span>
		</thead>
		<tbody>
			<tr th:each="map : ${mapList}">
				<td th:each="data : ${map}"><span th:text="${data.value}"></span></td>

			</tr>
		</tbody>
		
     
	</table>
	</div>
	    <div class="row">
            <div class="col mt-5"> 
                 <button type="button"  class="btn btn-warning"><a style=" text-decoration: none"  th:href="@{/home}">Back To Home</a></button>
            </div>
        </div>   

</div>
 </section>
</body>
</html>