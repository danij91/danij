/**
 * http://usejsdoc.org/
 */
var http=require('http');
var express=require('express');

//express 서버 생성
var app=express();
app.get('/',function(req,res){
	res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
	res.end('<p>express test2</p>');
});
//웹 서버 실행
//http와 express연동처리
http.createServer(app).listen(3000, function(){
	console.log('server running at http://127.0.0.1:3000');
});