/**
 * http://usejsdoc.org/
 */
//url모듈 추출(import)
var url=require('url');

var urlStr="http://idols.com/q?group=EXIT&name=kim";
//urlStr=url정보
//true=파라미터 사용여부(true는 사용, false면 사용안함)
//url.parse():urlStr문자열을 object로 변경
var parsed=url.parse(urlStr,true);
console.log(parsed);
console.log("==================================");

console.log('protocol=>'+parsed.protocol);
console.log('path=>'+parsed.path);
console.log('query.group=>'+parsed.query.group);
console.log('query.name=>'+parsed.query.name);
console.log("==================================");

var querystring=require('querystring');
var queryParsed=querystring.parse(parsed.query);
console.log(queryParsed);

var queryParsed2=querystring.parse(parsed.search);
console.log(queryParsed2);