/**
 * http://usejsdoc.org/
 */
var http=require('http');
var formidable=require('formidable');
var fs=require('fs');

//http://127.0.0.1:3000/list
var paints=[];
//웹 서버 생성
var server=http.createServer(function(req,res){
	if(req.url=='/list' && req.method =='GET'){
	      var html='<html><head><meta charset="UTF-8"></head>';
	      html+='<body><h1>Favorite</h1><ul>';
	      for(var i = 0; i<paints.length; i++){
	         html+='<li><img src = "http://127.0.0.1:3000/'+paints[i].image_url+'">'+paints[i].title+'</li>';
	      }
	      html+='</ul><hr/>';
	      
	      html+='<form method = "post" action="/upload" enctype="multipart/form-data">';
	      html+='작품이름:<input type="text" name="title">';
	      html+='<br/><input type="file"name="file" />';
	      html+='<br/><input type = "submit" value="upload" />';
	      
	      html+='</form></body></html>';
	      res.end(html);
	}else if(req.url=='/upload' && req.method=='POST'){
		//multipart/form.data
		var form=formidable.IncomingForm();
		form.encoding="UTF-8";
		//확장자 보존
		form.keepExtensions="true";
		//업로드시킬 경로 지정
		form.uploadDir="./upload";
		//err=에러를 받음,fields=파일 외의 것을 받음 ,files=파일을 받음
		form.parse(req,function(err,fields,files){
			var File=files.file;
			var filename=File['path'];//파일명 추출
			//console.log(filename);
			//역슬래시를 슬래시로 변경
			filename.replace(/\\/gm,'/');
			//배열에 추가
			paints.push({title:fields.title,image_url:filename});
			//리스트페이지로 리다이렉트한다
			res.statusCode=302;
			res.setHeader('Location','/list');
			res.end();
		});
	}else{
		//img태그가 보여주는 부분을 요청하기 떄문에 아래와 같은 구문을 넣어줘야지 이미지를 불러올 수 있다
		var dirname=__dirname.substring(0,__dirname.lastIndexOf('\\'));
		var path=dirname+req.url;
		fs.exists(path,function(exist){
			if(exist){
				res.writeHead(200,{'Content-Type':'image/*'});
				fs.createReadStream(path).pipe(res);
			}else{
				res.status=404;
				res.end('Not Fount')
			}
		});
	}
});//end server////////


//웹 서버 실행
server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});