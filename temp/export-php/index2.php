<?php
/**
 * Created by PhpStorm.
 * User: jgutierrez
 * Date: 4/11/19
 * Time: 9:20
 */
?>
<!DOCTYPE html>
<html>
<head>
    <title>
        Async file upload with jQuery
    </title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>

<body>
<div align="center">
    <form method="post" action="" enctype="multipart/form-data"
          id="myform">

        <div >
            <input type="file" id="uploadedFile" name="uploadedFile" />
            <input type="button" class="button" value="Upload"
                   id="but_upload">
        </div>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#but_upload").click(function() {
            var fd = new FormData();
            var files = $('#uploadedFile')[0].files[0];
            fd.append('uploadedFile', files);

            $.ajax({
                url: 'upload.php',
                type: 'post',
                data: fd,
                contentType: false,
                processData: false,
                success: function(response){
                    if(response != 0){
                        alert('file uploaded');
                    }
                    else{
                        alert('file not uploaded');
                    }
                },
            });
        });
    });
</script>
</body>

</html>