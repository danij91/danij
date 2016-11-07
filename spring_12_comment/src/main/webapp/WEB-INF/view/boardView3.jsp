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

.fileDrop {
	width: 200px;
	height: 50px;
	border: 1px dotted blue;
}
</style>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript">
	var urno = '';
	/* 여러개면 배열로, 컬럼이 하나면 변수로 */
	var fileList = '';

	$(document).ready(function() {
		$('#modifyModal').addClass('modifyHide');
		$('#replyAddBtn').on('click', result_list);
		$(document).on('click', '.timeline button', reply_update_delete);
		$('#btnModify').on('click', reply_update_send);

		$('[href]').on('click', reply_flie_download);

		$('#btnClose').on('click', function() {
			$('#modifyModal').removeClass('modifyShow');
			$('#modifyModal').addClass('modifyHide');
			urno = '';
		});
		
		$('#userFileDel').on('click', function(){
			//내PC부분 파일삭제 구현
			
		});
		
		
		//내PC 클릭시 파일찾기 첨부파일 창 띄우기 시작////////////////
		var userfile='';
		$('#userpc').on('click', function(){
			/* 강제적으로 첨부파일창이 뜨드록 이벤트설정 */
			var userfile=$('<input type="file" id = "userfile"/>');
			userfile.click();
			/* input type의 값이 바뀌면 change 이벤트 발생 */
			userfile.change(function(e){
				/* 현재 이벤트가 발생된곳에서 첨부파일 (0번째 인덱스의 값)을 가져옴 */
				console.log($(userfile[0]).val());
				var partname = $(userfile[0]).val().substring(
														$(userfile[0]).val().lastIndexOf("\\")+1);
				str = "<p style='background-color:skyblue'>"+partname+"</p>";
				$(".fileDrop").append(str);
				/* 파일을 오브젝트가 아닌 파일객체로 가져오기위해 files를 사용함 */
				var dataList = userfile[0].files[0];
				fileList = dataList;
			});
		});
		
		//내PC 클릭시 파일찾기 창 띄우기 끝////////////////
		

		////첨부파일 드래그 시작
		var obj = $('.fileDrop');
		obj.on('dragenter', function(e) {
			e.preventDefault();
			$(this).css('border', '2px solid #0B85A1');

		});

		obj.on('dragover', function(e) {
			e.preventDefault();
		});
		
		/* 드랍(마우스를놓으면) 첨부파일로 변환*/
		/* div는 그냥 text를 넣어놓는 영역이기 때문에 첨부파일형식으로 변환해야함 */
		obj.on('drop', function(e){
			e.preventDefault();
			/* e.originalEvent.dataTransfer.files
			input Type file이면 저절로 변환이 일어나지만
			지금 input type file을 쓰는것이 아니기때문에 직접 첨부파일 형식으로 변환을 해야함*/
			var files = e.originalEvent.dataTransfer.files;
			for(var i = 0; i<files.length; i++){
				/* 파일이름이 나와야해서 <p>요소에 추가시킴 */
				str = '<p style="background-color:yellow">'+files[i].name+'</p>';
				obj.append(str);
				fileList = files[i];
				
			}
		});
		////첨부파일 드래그 끝

	}); //end document.ready////////////////

	function reply_flie_download() {
		$.ajax({
			type : 'POST',
			dataType : 'JSON',
			url : 'fileDownLoad.do',
			data : '&rno=${ReplyDTO.rno}',
			success : reply_list_result

		});
	}//end reply_file_download()/////////////////////////

	function reply_update_send() {

		var form_update_data = new FormData();
		form_update_data.append('bno', '${boardDTO.bno}');
		form_update_data.append('rno', urno);
		if ($('#updateReplyText').val() != null)
			form_update_data.append('replytext', $('#updateReplyText').val());
		if ($('#newFileName')[0].files[0] != undefined)
			form_update_data.append('filename', $('#newFileName')[0].files[0]);

		$.ajax({
			type : 'POST',
			dataType : 'JSON',
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			url : 'replyUpdate.do',
			data : form_update_data,
			success : reply_list_result

		});

		$('#updateReplyText').val('');
		$('#modifyModal').removeClass('modifyShow');
		$('#modifyModal').addClass('modifyHide');
		urno = '';
	}////////end reply_update_send()////////////////

	function reply_update_delete(res) {
		if ($(this).text() == "delete") {
			var drno = $(this).prop('id');
			$.ajax({
				type : 'GET',
				dataType : 'JSON',
				url : 'replyDelete.do?rno=' + drno + '&bno=${boardDTO.bno}',
				success : reply_list_result
			});
		} else if ($(this).text() == 'update') {
			urno = $(this).prop('id');
			$('#modifyModal').removeClass('modifyHide');
			$('#modifyModal').addClass('modifyShow');
		}
	}////////end reply_update_delete///////////////////

	Handlebars.registerHelper("newDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "-" + month + "-" + date;
	});//end////////////////////////////////

	Handlebars.registerHelper("newUpload", function(uploadFile) {
		if (uploadFile != null)
			return uploadFile.substring(uploadFile.indexOf("_") + 1);
		else
			return uploadFile;
	});//end//////////////////////////////////////

	Handlebars.registerHelper("newDownload", function(downloadFile) {

	}); //end download///////// 

	function result_list() {

		if ($('#newReplyWriter').val() == '') {
			alert('작성자를 입력하세요');
			return false;
		}
		if ($('#newReplyText').val() == '') {
			alert('내용을 입력하세요');
			return false;
		}

		//enctype이 multipart일시에만 (첨부파일이 있을때만) 이런 방법으로 넘김
		var form_data = new FormData();
		form_data.append('bno', '${boardDTO.bno}');
		form_data.append('replyer', $('#newReplyWriter').val());
		form_data.append('replytext', $('#newReplyText').val());
		/* console.log('filename', $('filename')[0].files[0]); */
		/* if ($('#filename')[0].files[0] != undefined) */
			//form_data.append('filename', $('#filename')[0].files[0]);
		if(fileList!='')
		form_data.append('filename', fileList);

		/* contentType, enctype, processData 항상 같이 쓰임 */
		$.ajax({
			type : 'POST',
			dataType : 'JSON',
			//contnentType : true면 어플리케이션으로 전송
			//false면 multipart로 전송 (enctype은 없어도 multipart로 전송)
			contentType : false,
			enctype : 'multipart/form-data',
			//processData : 값이 다르기때문에 false로 설정(multipart)
			processData : false,
			url : 'replyInsertList.do',
			data : form_data,
			success : reply_list_result

		});
		$('#newReplyWriter').val();
		$('#newReplyText').val();
		/* $('#filename').val(); */
		$('.fileDrop').empty();
		fileList='';
	}

	function reply_list_result(res) {
		$('.timeline .time_sub').remove();
		$('#replycntSmall').text('[ ' + res.length + ' ]');
		$
				.each(
						res,
						function(index, value) {
							/* 	$('.timeline').append('<li class="time_sub"  id="'+${replyDTO.rno}+'"><p>'
										+value.replyer+'</p><p>'+value.replytext+'</p><p>'+value.regdate
										+'</p><p><a href="replyDelete.do?bno=${boardDTO.bno}&rno=${replyDTO.rno}">delete</a></p></li>'); */
							var source = "<li class='time_sub' id='{{rno}}'><p>{{replyer}}</p><p>{{replytext}}</p><p>{{newDate regdate}}</p><p><a href='fileDownLoad.do?rno={{rno}}'>{{newUpload rupload}}</a></p><p><button id='{{rno}}'>delete</button> <button id='{{rno}}'>update</button></p></li>";
							var template = Handlebars.compile(source);
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
					placeholder="REPLY TEXT" id="newReplyText">
				<!-- <label
					for="filename">Upload</label> <input type="file" id="filename"
					name="filename" /> -->
				<div>
					<span id="userpc" style="cursor: pointer">내PC</span>
					<button id = "userFileDel">삭제</button>
				</div>
				<div class='fileDrop'></div>
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
					</p> <c:if test="${!empty replyDTO.rupload}">
						<a href="fileDownLoad.do?rno=${replyDTO.rno}">
							${fn:substringAfter(replyDTO.rupload, "_")} </a>
					</c:if>

					<p>
						<button id="${replyDTO.rno}">delete</button>
						<button id="${replyDTO.rno }">update</button>
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
					id="updateReplyText"> <input type="file" id="newFileName"
					name="newFileName" />
			</p>
			<p>
				<button id="btnModify">Modify</button>
				<button id="btnClose">Close</button>
			</p>
		</div>


	</div>
</body>
</html>