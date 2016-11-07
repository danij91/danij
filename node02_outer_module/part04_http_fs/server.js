/**
 * http://usejsdoc.org/
 */
// 각 모들 추출
var http = require('http');
var url = require('url');
var fs = require('fs');

var server = http.createServer(function(request, response) {
	var dirname = __dirname;
	var filepath = "";
	var userpath = request.url;
	if (userpath === '/') {
		filepath = dirname + "\\index.html";
		fs.readFile(filepath, function(error, data) {
			response.writeHead(200, {'Content-Type' : 'text/html;charset=UTF-8'});
			response.end(data);
		});
	} else if (userpath === '/otherPage') {
		filepath = dirname + "\\otherPage.html";
		fs.readFile(filepath, function(error, data) {
			response.writeHead(200, {'Content-Type' : 'text/html;charset=UTF-8'});
			response.end(data);
		});
	}
	
});

server.listen(3000, function() {
	console.log('server running at http://127.0.0.1:3000');
});