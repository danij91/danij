/**
 * http://usejsdoc.org/
 */

var http=require('http');
var fs=require('fs');
var socketio=require('socket.io');

var server=http.createServer();
//서버에 소켓 연결
var io=socketio.listen(server);

server.on('request',function(req,res){
	fs.readFile('chat.html',function(error,data){
		res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
		res.end(data);
	});
});//end server.on

//클라이언트가 소켓(채팅)에 연결을 하면
io.sockets.on('connection',function(socket){
	//on()이벤트 등록
	socket.on('message',function(data){
		//emit() 이벤트 강제 발생
		io.sockets.emit('message',data);
	});//end socket.on
});//end sockets.on

server.listen(3000, function(){
	console.log('server running at http://127.0.0.1:3000');
});