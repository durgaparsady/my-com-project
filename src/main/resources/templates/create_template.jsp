<!DOCTYPE html>
<html lang="en-US" xmlns:th="http://www.thymeleaf.org">

<head>

  <title>Create Template </title>
  <script src="https://cdn.tiny.cloud/1/cpj6rwmsm0dxc4uxjp2m40xdxxg2vwffq072f9huajdc3xaf/tinymce/5/tinymce.min.js"
    referrerpolicy="origin"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>


  <div class="container mt-4 p-3 my-5 border border-dark " style="background-color:white">
    <div th:if="${session.msg}">
      <div class="alert alert-success" role="alert" style="text-align:center" th:text="${session.msg}">

      </div>
      <th:block th:text="${#session.removeAttribute('msg')}"></th:block>
    </div>
    <h2> Create Template</h2>
    <form action="#" th:action="@{/addTemplate}" th:object="${template}" method="post">

      <div class="row">
        <div class="col-md-9 mt-2">
          <label><strong>Template Name</strong></label>
          <div>
            <input type="text" th:field="*{templateName}" class="form-control" style="width:135%;" maxlength="200"
              placeholder="Enter Template Name Here" required="required" />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-9 mt-2">
          <label><strong>Header</strong></label>
          <textarea id="body1" th:field="*{header}" style="width:135%; height:300px"></textarea>
          <script>
            tinymce.init({
              selector: '#body1',
              plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
              toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
              tinycomments_mode: 'embedded',
              tinycomments_author: 'Author name',

            });
          </script>
        </div>
      </div>

      <div class="row">
        <div class="col-md-9 mt-2">
          <label><strong>Editor</strong></label>
          <textarea id="body" th:field="*{content}" style="width:135%; height:300px"
            placeholder="editor place holder"></textarea>
          <script>
            tinymce.init({
              selector: '#body',
              plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
              toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
              tinycomments_mode: 'embedded',
              tinycomments_author: 'Author name',

            });
          </script>
        </div>
      </div>
      <div class="row">
        <div class="col-md-9 mt-2">
          <label><strong>Footer</strong></label>
          <textarea id="body2" th:field="*{footer}" style="width:135%; height:300px"></textarea>
          <script>
            tinymce.init({
              selector: '#body2',
              plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
              toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
              tinycomments_mode: 'embedded',
              tinycomments_author: 'Author name',

            });
          </script>



        </div>
      </div>
      <div class="row">
        <div class="col-md-9 my-4">
          <input type="submit" value="Save" class="btn btn-primary" />
          <button type="button" class="btn btn-warning"><a style=" text-decoration: none"
              th:href="@{/home}">Cancel</a></button>
        </div>


      </div>
    </form>
  </div>

</body>

</html>