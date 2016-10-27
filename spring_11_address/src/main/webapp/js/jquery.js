/*클릭시 process 실행*/
$(document).ready(function() {
	$('#btn').on('click', process);
});

function process() {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'customer.do?name='+$('#name').val(),
		success:function(res){
			$('div').html(res);
		}
	});
}// end process()
