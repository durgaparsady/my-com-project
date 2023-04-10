<!DOCTYPE html>
<html lang="en-US" xmlns:th="http://www.thymeleaf.org">
<head>

    <title>PDF Genrator</title>
    <script src="https://cdn.tiny.cloud/1/cpj6rwmsm0dxc4uxjp2m40xdxxg2vwffq072f9huajdc3xaf/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>

<body >
  
<div class="container mt-4 p-3 my-5 border border-dark" >
   <div th:if="${session.msg}">
		<div class="alert alert-success" role="alert" style="text-align:center" th:text="${session.msg}">

		</div>
		<th:block th:text="${#session.removeAttribute('msg')}"></th:block>
	</div> 
<h2> PDF Template</h2> 
 <form action="#" th:action="@{/pdfGenerate}"  th:object="${pdfRequest}" method="post">
     
        <div class="row">
            <div class="col-md-9 mt-2">
                <label><strong>Template Name</strong></label>
                <div>
                    <input type="text" th:field="*{templateName}"  class="form-control" style="width:135%;" maxlength="128" readonly="readonly"/>
                </div>
            </div>
        </div> 
            <div class="row">
            <div class="col-md-9 mt-2"> 	
                  <label><strong>Editor</strong></label>
                <textarea  id="body" th:field="*{jsonText}" style="width:135%; height:300px"  placeholder="[&#10;  {&#10;   'Variable Name':'Enter Value',&#10;   'Variable Name':'Enter Value'&#10;   'List Name':[&#10;                        {&#10;                           'Variable Name':'Enter Value',&#10;                           'Variable Name':'Enter Value'&#10;                        }&#10;                    ] &#10;   }&#10;] "></textarea>
                
            </div>
            </div>
           
      
        <div class="row">
            <div class="col-md-9 my-3 mt-2">
                <input type="submit" value="Genrate PDF" class="btn btn-primary"/>
                 <button type="button"  class="btn btn-warning"><a style=" text-decoration: none"  th:href="@{/home}">Cancel</a></button>
            </div>
        </div>    
      
    </form>    
     
</div>

</body>
</html>