/**
 * http://usejsdoc.org/
 */

//동기화-요청이 들어와서 응답할 때까지 어떠한 동작을 못함
//비동기화-클라이언트가 요청을 했을 때 서버의 응답과는 상관 없이 클라이언트가 새로운 동작을 취할 수 있음.
var http=require('http');
var fs=require('fs');
var server=http.createServer(function(req,resp){
	if(req.method=='GET'){
		fs.readFile('part06_method/main.html', function(err, data) {
			resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			resp.end(data);
		});
	}else if(req.method=='POST'){
		var res='';
		//이벤트 등록
		req.on('data',function(data){
			res+=data;
		});
	}req.on('end',function(){
		console.log(typeof(res));
		resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
		resp.end(res);
	});
});//end server

server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});