/**
 * http://usejsdoc.org/
 */


var http = require('http');
var url = require('url');
var fs = require('fs');

var server = http.createServer(function(req, resp) {
	
	//C:\study\workspace_spring\node02_outer_module\part05_http_fs/
	//console.log(userpath);
	if(req.url=="/"){
		resp.writeHeader(200,{'Content-Type':'text/html;charset=UTF-8'});
        resp.end('<html><body><h1>Hello Image</h1><img src="images/aa.jpg"/><img src="images/bb.png"/></body></html>');
		return;
	}
	//C:\study\workspace_spring\node02_outer_module\part05_http_fs/images/aa.jpg
	//console.log(userpath);
	var userpath=__dirname+req.url;
	//해당 이미지파일이 존재하는지 확인
	fs.exists(userpath,function(exists){
		//이미지가 존재하면
		if(exists){
			resp.writeHead(200,{'Content-Type':'image/*'});
			//이미지 파일을 스트림으로 읽고 클라이언트에 응답한다.
			fs.createReadStream(userpath).pipe(resp);
		}else{
			resp.statusCode=404;
			resp.end('Not Found');
		}
	});
});

server.listen(3000, function() {
	console.log('server running at http://127.0.0.1:3000');
});