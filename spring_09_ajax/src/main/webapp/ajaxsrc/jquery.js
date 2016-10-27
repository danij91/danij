$(document).ready(function(){
	$('#btn').on('click',process);
});
function process() {
	$.ajax({
		type:'GET',//기본이 get이라 안써줘도 상관없음
		dataType:'text',//기본이 text라 안써줘도 상관없음
		url:'loginPro.do?name='+$('#name').val(),
		success:function(res){
			$('div').html(res);
		}
		
	})
}//end process()