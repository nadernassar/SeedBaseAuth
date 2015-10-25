<%@ page import="edu.pace.csis.phd.sbauth.generator.*" %>
<html>
  <head>
    <title>Sample Application JSP Page</title>
    
    <style>
  #byte_content {
    margin: 5px 0;
    max-height: 100px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  #byte_range { margin-top: 5px; }
</style>
  </head>

  <body bgcolor=white>

  <div> <h1>Welcome to Seed-Base Auth</h1>
     </div>

  <br />
  <p>Please enter your root file</p>
 <p> <output id="thumb"></output> </p>
  
  
 

<input type="file" id="files" name="file" /> Read bytes: 
<span class="readBytesButtons">
   <button>entire file</button>
</span>

<div id="byte_content"></div>

<script>

function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    var f = files[0];
      // Only process image files.
      if (!f.type.match('image.*')) {
        return;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.createElement('span');
          span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), 
                            '" width=50 , height=50""/>'].join('');
          document.getElementById('thumb').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  

  document.getElementById('files').addEventListener('change', handleFileSelect, false);
  
function readBlob(evt) {

    var files = document.getElementById('files').files;
    if (!files.length) {
      alert('Please select a file!');
      return;
    }

    var file = files[0];
      var reader = new FileReader();

    // If we use onloadend, we need to check the readyState.
    reader.onloadend = function(evt) {
      if (evt.target.readyState == FileReader.DONE) { // DONE == 2
        
        console.log("***Starting***");
	        var base64Image = reader.result;
	       // var binaryImg = atob(base64Image);
	        var length = file.length;
        var ab = new ArrayBuffer(length);
        console.log("ArrayBuffer" + ab.byteLength);
        var ua = new Uint16Array(ab);
        for (var i = 0; i < length; i++) {
            ua[i] = binaryImg.charCodeAt(i);
        }

        var blob = new Blob([ab], {
            type: "image/jpeg"
        });
       
        console.log(URL.createObjectURL(blob)); // Works!!!!
      }
    };

    
    reader.readAsDataURL(file);
    console.log(URL.createObjectURL(file)); // Works
    
   // var blob = file.slice(start, stop + 1);
    reader.readAsBinaryString(file);
   // var buf = new ArrayBuffer(file.length*2);
   //  buf = reader.readAsArrayBuffer(blob);
   // console.log(buf.size());
  //  var bufView = new Uint64Array(buf);
 	// for (var i=0, strLen=str.length; i<strLen; i++) {
 	//   bufView[i] = str.charCodeAt(i);
 	//  }
    console.log(reader.readAsDataURL(file));
  }
  
  document.querySelector('.readBytesButtons').addEventListener('click', function(evt) {
    if (evt.target.tagName.toLowerCase() == 'button') {
    // var startByte = evt.target.getAttribute('data-startbyte');
     // var endByte = evt.target.getAttribute('data-endbyte');
      readBlob();
    }
  }, false);
</script>
</form>

  

  </body>
</html> 