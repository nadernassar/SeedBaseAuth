<html>
  <head>
    <title>Sample Application JSP Page</title>
  </head>

  <body bgcolor=white>

  <div> <h1>Welcome to Seed-Base Auth</h1>
     </div>

  <br />
  <p>Please enter your root file</p>
  

<br/>

<h2>Input</h2>  
<form class="input-group" id="img2b64">
    <input 
        type="url" 
        name="url" 
        class="form-control"
        placeholder="Insert an IMAGE-URL" 
        value="http://upload.wikimedia.org/wikipedia/commons/4/4a/Logo_2013_Google.png"  
        required>
    <span class="input-group-btn">
        <input 
            type="submit" 
            class="btn btn-default">
    </span>
</form>
            
<hr>
    
<h2>Output</h2>        
<div class="output">
    <textarea class="form-control"></textarea><br>
    <a></a><br><br>
    <img><br>
</div>


<form>
<script>

/**
 * convertImgToBase64
 * @param  {String}   url
 * @param  {Function} callback
 * @param  {String}   [outputFormat='image/png']
 * @author HaNdTriX
 * @example
	convertImgToBase64('http://goo.gl/AOxHAL', function(base64Img){
		console.log('IMAGE:',base64Img);
	})
 */
function convertImgToBase64(url, callback, outputFormat){
	var canvas = document.createElement('CANVAS');
	var ctx = canvas.getContext('2d');
	var img = new Image;
	img.crossOrigin = 'Anonymous';
	img.onload = function(){
		canvas.height = img.height;
		canvas.width = img.width;
	  	ctx.drawImage(img,0,0);
	  	var dataURL = canvas.toDataURL(outputFormat || 'image/png');
	  	callback.call(this, dataURL);
        // Clean up
	  	canvas = null; 
	};
	img.src = url;
	consol.log(dataURL);
}


img2b64.submit(function(event){
    var imageUrl = this.find('input[name=url]').val();
    console.log('imageUrl', imageUrl);
    convertImgToBase64(imageUrl, function(base64Img){
        $('.output')
            .find('textarea')
                .val(base64Img)
                .end()
            .find('a')
                .attr('href', base64Img)
                .text(base64Img)
                .end()
            .find('img')
                .attr('src', base64Img);
    });
    
    event.preventDefault();
});


</script>
</form>

  

  </body>
</html> 