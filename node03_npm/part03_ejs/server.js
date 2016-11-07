/**
 * http://usejsdoc.org/
 */
var http=require('http');
var url=require('url');
var fs=require('fs');
var ejs=require('ejs');

var server=http.createServer(function(req,res){
	var urlObj=url.parse(req.url,true);
	if(urlObj.pathname==="/"){
		fs.readFile('part03_ejs/page1.ejs', 'UTF-8', function(err,data){
			res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			
			res.end(ejs.render(data));
		});
	}else if(urlObj.pathname==="/memInsert"){
		var fname=urlObj.query.fname;
		fs.readFile('part03_ejs/page2.ejs', 'UTF-8', function(err,data){
			res.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			
			res.end(ejs.render(data,{fname:fname}));
		});
	}
});//end server//////////////////////////////////////

server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});