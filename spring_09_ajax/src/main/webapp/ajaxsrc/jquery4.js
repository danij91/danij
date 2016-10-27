$(document).ready(function() {
	$('#btn').click(function(){
		$.ajax({
			type:'GET',
			dataType:'xml',
			url:'searchOpen.do?search='+$('#search').val(),
			success:viewMessage,
			error:function(xhr,textStatus,error){
				alert(xhr.status+","+textStatus+","+error);
			}
		});
	});
});
/*function viewMessage(data){
	var data=data.replace(/&lt;/gi,"<");
	data=data.replace(/&gt;/gi,">");
	$('#wrap').empty();
	$('#wrap').append(data);
}*/
function viewMessage(data){
	$('#wrap').empty();
	$('#wrap').append('<table><tr><td>title</td><td>image</td><td>author</td><td>price</td></tr></table>');
	$('item',data).each(function(){
		$('#wrap table').append('<tr><td><a href="'+$('link',this).text()+'">'+$('title',this).text()+'</a></td><td><img src="'+$('cover_s_url',this).text()+'"/></td><td>'+$('author',this).text()+'</td><td>'+$('sale_price',this).text()+'</td></tr>');
	});
}