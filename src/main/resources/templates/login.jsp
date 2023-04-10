<!DOCTYPE html>
<html lang="en-US" xmlns:th="http://www.thymeleaf.org">
<head>
    
    <title>Add/Edit Post | Carey Development</title>
    <script src="https://cdn.tiny.cloud/1/cpj6rwmsm0dxc4uxjp2m40xdxxg2vwffq072f9huajdc3xaf/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>

<body >
  
 
<div class="container mt-4 p-3 my-5 border border-dark" > 
	<div th:if="${session.msg}">
		<div class="alert alert-success" role="alert" th:text="${session.msg}">

		</div>
		<th:block th:text="${#session.removeAttribute('msg')}"></th:block>
	</div>
<h2>Login Here</h2>
 <form action="#" th:action="@{/login}"  method="post">
     
       
          <div class="row">
            <div class="col-md-9 mt-2">
                <label for="userName"><strong>User Name</strong></label>
                <div>
                    <input type="text" name="userName" id="userName" class="form-control" style="width:135%;" maxlength="128"  placeholder="Enter User Name here" required="required"/>
                </div>
            </div>
        </div>
          <div class="row">
            <div class="col-md-9 mt-2">
                <label for="myPassword"><strong>Password</strong></label>
                <div>
                    <input type="password" id="myPassword" name="password"   class="form-control" style="width:135%;" maxlength="128"  placeholder="Enter Password here" required="required"/>
                 
                <input type="checkbox" onclick="myFunction()" id="showPassword">
                <label for="showPassword"><strong>Show Password</strong></label>
                </div>
            </div>
        </div>
    
             
      
        <div class="row">
            <div class="col-md-9 mt-3">
                <input type="submit" value="Login" class="btn btn-primary" />
            </div>
            
            
        </div>    
    </form>    
     
</div>
 <script>
function myFunction() {
  var x = document.getElementById("myPassword");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>
</body>
</html>