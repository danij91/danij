<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.modifyShow {
	display: block;
	position: absolute;
	top: 150px;
	left: 200px;
	width: 400px;
	height: 150px;
	z-index: 1000;
	border: 1px solid black;
	background-color: yellow;
	text-align: center;
}

.modifyHide {
	visibility: hidden;
	width: 0px;
	height: 0px;
}
</style>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript">

var urno='';
$(document).ready(function(){
	$('#modifyModal').addClass('modifyHide');
	$('#replyAddBtn').on('click', process);
	$(document).on('click','.timeline button',reply_update_delete);
	$('#btnModify').on('click',reply_update_send);
	
	$('#btnClose').on('click',function(){
		$('#modifyModal').removeClass('modifyShow');
		$('#modifyModal').addClass('modifyHide');
		urno='';
	});
});

function reply_update_send(){
	if($('#updateReplyText').val()==''){
		alert('내용을 입력하세요');
		return false;
	}
	var form_data2 = new FormData();
	form_data2.append('bno','${boardDTO.bno}');
	form_data2.append('rno',urno);
	form_data2.append('replytext',$('#updateReplyText').val());
	if ($('#filename2')[0].files[0]!=undefined) {
		form_data2.append('filename',$('#filename2')[0].files[0]);
	}
	$.ajax({
		type:'POST',
		dataType:'JSON',
		url:'replyUpdate.do',
		data:form_data2,
		contentType: false,
		enctype:'multipart/form-data',
		processData:false,
		success:reply_list_result
	});
	
	$('#updateReplyText').val('');
	$('#modifyModal').removeClass('modifyShow');
	$('#modifyModal').addClass('modifyHide');
	urno='';
}

function reply_update_delete(res){
	if($(this).text()=="delete"){
		var drno = $(this).prop('id');
		$.ajax({
			type:'GET',
			dataType:'JSON',
			url:'replyDelete.do?rno='+drno+'&bno=${boardDTO.bno}',
			success:reply_list_result
		});
	}else if($(this).text()=='update'){
		urno = $(this).prop('id');
		$('#modifyModal').removeClass('modifyHide');
		$('#modifyModal').addClass('modifyShow');
	}
}
Handlebars.registerHelper("newDate",function(timeValue){
	var dateObj = new Date(timeValue);
	var year=dateObj.getFullYear();
	var month=dateObj.getMonth()+1;
	var date=dateObj.getDate();
	return year+"-"+month+"-"+date;
});//end////////////////////////////////
Handlebars.registerHelper("newUpload",function(uploadFile){
	if(uploadFile!=null)
		return uploadFile.substring(uploadFile.indexOf("_")+1);
	else
		return uploadFile;
});

function process(){
	
	if($('#newReplyWriter').val()==''){
		alert('작성자를 입력하세요');
		return false;
	}
	if($('#newReplyText').val()==''){
		alert('내용을 입력하세요');
		return false;
	}
	//
	var form_data = new FormData();
	form_data.append('bno','${boardDTO.bno}');
	form_data.append('replyer',$('#newReplyWriter').val());
	form_data.append('replytext',$('#newReplyText').val());
	if ($('#filename')[0].files[0]!=undefined) {
		form_data.append('filename',$('#filename')[0].files[0]);
	}
	
	
	$.ajax({
		type:'POST',
		dataType:'JSON',
		contentType: false,
		enctype:'multipart/form-data',
		processData:false,
		url:'replyInsertList.do',
		data: form_data,
		success:reply_list_result
			
	});
	$('#newReplyWriter').val();
	$('#newReplyText').val();
}

function reply_list_result(res){
	$('.timeline .time_sub').remove();
	$('#replycntSmall').text('[ '+res.length+' ]');
	$.each(res,function(index, value){
		/* 	$('.timeline').append('<li class="time_sub"  id="'+${replyDTO.rno}+'"><p>'
					+value.replyer+'</p><p>'+value.replytext+'</p><p>'+value.regdate
					+'</p><p><a href="replyDelete.do?bno=${boardDTO.bno}&rno=${replyDTO.rno}">delete</a></p></li>'); */
	
	var source='<li class="time_sub" id="{{rno}}"><p>{{replyer}}</p><p>{{replytext}}</p>'
			  	+'<p>{{newDate regdate}}</p><p><a href="replyDownload.do?rno={{rno}}">{{newUpload rupload}}</a></p>'
				+'<p><button id="{{rno}}">delete</button> <button id="{{rno}}">update</button></p></li>';
	var template=Handlebars.compile(source);
	$('.timeline').append(template(value));
	});
}//end reply_list_result
</script>
</head>
<body>
	<div class="wrap">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label> <input type="text"
					name='title' class="form-control" value="${boardDTO.title}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Content</label>
				<textarea class="form-control" name="content" rows="3"
					readonly="readonly">${boardDTO.content}</textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Writer</label> <input type="text"
					name="writer" class="form-control" value="${boardDTO.writer}"
					readonly="readonly">
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
			<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
			<button type="submit" class="btn btn-primary" id="goListBtn">GO
				LIST</button>
		</div>
		<hr />

		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>
			<div class="box-body">
				<label for="exampleInputEmail1">Writer</label> <input
					class="form-control" type="text" placeholder="USER ID"
					id="newReplyWriter"> <label for="exampleInputEmail1">Reply
					Text</label> <input class="form-control" type="text"
					placeholder="REPLY TEXT" id="newReplyText"> <label
					for="filename">Upload</label> <input type="file" id="filename"
					name="filename" />
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
					REPLY</button>
			</div>
		</div>


		<!-- The time line -->
		<ul class="timeline">
			<!-- timeline time label -->
			<li class="time-label" id="repliesDiv"><span class="bg-green">
					Replies List <small id='replycntSmall'> [
						${fn:length(boardDTO.replyList)} ] </small>
			</span></li>

			<c:forEach items="${boardDTO.replyList}" var="replyDTO">
				<li class="time_sub" id="${replyDTO.rno}">
					<p>${replyDTO.replyer}</p>
					<p>${replyDTO.replytext }</p>
					<p>
						<fmt:formatDate pattern="yyyy-MM-dd" dateStyle="short"
							value="${replyDTO.regdate}" />
					</p>
					<p>
						<c:if test="${!empty replyDTO.rupload}">
							<a href="replyDownload.do?rno=${replyDTO.rno}">
								${fn:substringAfter(replyDTO.rupload,"_")}</a>
						</c:if>
					<p>
						<button id="${replyDTO.rno}">delete</button>
						<button id="${replyDTO.rno}">update</button>
					</p>
				</li>
			</c:forEach>
		</ul>

		<div class='text-center'>
			<ul id="pagination" class="pagination pagination-sm no-margin ">

			</ul>
		</div>

		<!-- Modal -->
		<div id="modifyModal">
			<p>
				<label for="updateReplyText">Reply Text</label> <input
					class="form-control" type="text" placeholder="REPLY TEXT"
					id="updateReplyText"> <label for="filename">Upload</label>
				<input type="file" id="filename2" name="filename2" />
			</p>
			<p>
				<button id="btnModify">Modify</button>
				<button id="btnClose">Close</button>
			</p>
		</div>


	</div>
</body>
</html>