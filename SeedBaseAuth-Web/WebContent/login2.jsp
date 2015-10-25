<html>
  <head>
    <title>Sample Application JSP Page</title>
  </head>

  <body bgcolor=white>

  <div> <h1>Welcome to Seed-Base Auth</h1>
     </div>

  <br />
  <p>Please enter your root file</p>
  
<input id="base64File" type="file" />
<br/>
<button id="base64Button">blah</button>
<script>
$("#base64Button").on("click", function () {
    var file = $("#base64File")[0].files[0]
    var reader = new FileReader();

    // callback for readAsDataURL
    reader.onload = function (encodedFile) {
        console.log("reader.onload");
        var base64Image = encodedFile.srcElement.result.split("data:image/jpeg;base64,")[1];
        var binaryImg = atob(base64Image);
        var length = binaryImg.length;
        var ab = new ArrayBuffer(length);
        var ua = new Uint8Array(ab);
        for (var i = 0; i < length; i++) {
            ua[i] = binaryImg.charCodeAt(i);
        }

        var blob = new Blob([ab], {
            type: "image/jpeg"
        });

        console.log(URL.createObjectURL(blob)); // Works!!!!

    };
    reader.readAsDataURL(file);
    console.log(URL.createObjectURL(file)); // Works
});
</script>
</form>

  

  </body>
</html> 

