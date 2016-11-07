/**
 * http://usejsdoc.org/
 */
var express=require('express');

//express 서버 생성
var app=express();
app.get('/',function(req,res){
	var output=[];
	for(var i=0;i<3;i++){
		output.push({count:1, name:'name-'+i});
	}
	//res.writeHead(200,{'Content-Type':'text/plain;charset=UTF-8'});
	res.send(200,output);
	//res.end(JSON.stringify(output));
});
//웹 서버 실행
app.listen(3000, function(){
	console.log('server running at http://127.0.0.1:3000');
});