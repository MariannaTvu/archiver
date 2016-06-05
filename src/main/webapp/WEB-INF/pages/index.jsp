<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog.kiev.ua</title>
          <title>Prog.kiev.ua</title>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
      <title>margin-top</title>
      <style>
          .form-group1  {
            margin-top: 10%;
          }
      </style>
        </head>
  <body>
  <div class="block" align="center">
  <form action="/add" enctype="multipart/form-data" method="POST">
         <div class="form-group1" >
                <label for="exampleInputFile">File input</label>
                <input type="file" id="exampleInputFile"  name="file1">
                <p class="help-block">Add first file.</p>
            </div>
            <div class="form-group2">
                <input type="file" id="exampleInputFile1"  name="file2">
                <p class="help-block">Add second file.</p>
            </div>
            <div class="form-group3">
                <input type="file" id="exampleInputFile2"  name="file3">
                <p class="help-block">Add third file.</p>
            </div>
      <button type="submit" class="btn btn-default">Add</button>
      </form>

  </div>
  </body>
</html>
