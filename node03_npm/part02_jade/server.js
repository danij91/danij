/**
 * http://usejsdoc.org/
 */
/*
 *전역설치 방법 npm install -g jade
 *			npm link jade
 *지역설치 방법 npm install jade 
 */

var http=require('http');
var url=require('url');
var fs=require('fs');
var jade=require('jade');

var server=http.createServer(function(req,res){
	var urlObj=url.parse(req.url,true);
	if(urlObj.pathname==="/"){
		fs.readFile('part02_jade/page1.jade', 'UTF-8', function(err,data){
			res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			//jade페이지를 HTML로 변환해주는 작업(render)
			res.end(jade.render(data));
		});
	}else if(urlObj.pathname==="/memInsert"){
		var fname=urlObj.query.fname;
		fs.readFile('part02_jade/page2.jade', 'UTF-8', function(err,data){
			res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			//jade페이지를 HTML로 변환해주는 작업(render)
			res.end(jade.render(data,{fname:fname}));
		});
	}
});//end server//////////////////////////////////////

server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});