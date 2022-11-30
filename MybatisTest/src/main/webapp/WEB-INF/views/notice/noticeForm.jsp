<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="smarteditor2/js/HuskyEZCreator.js" charset="UTF-8"></script>
</head>
<body onload="smartEditor()">
<div align="center">
	<form action="#" onsubmit="return submitPost()" method="post">
		<div id="smarted" style="align-content: center;">
			<textarea rows="20"
					id="contents" name="contents"
					style="width: 700px"></textarea>
		</div>
		<input type="submit" value="제출"/>
		<button type="button" onclick="sessionConfirm()">세션확인</button>
	</form>
</div>

<script type="text/javascript">
	var oEditor = [];
	
	smartEditor = function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditor,
			elPlaceHolder : "contents",
			sSkinURI : "smarteditor2/SmartEditor2Skin.html",
			fCreator : "createSEditor2"
		});
	}

	submitPost = function() {
		  oEditor.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
		  let content = document.getElementById("contents").value;
		  let str='<p>&nbsp;</p>';
		  alert(content);
		  if(content == str) {
		    alert("내용을 입력해주세요.");
		    oEditor.getById["contents"].exec("FOCUS");
		    return false;
		  } else {
			console.log(content);
		  }
	}
	
	sessionConfirm = function(){   //자바스크립트에서 세션값가져오기
		let sessionId = '${id}';  //el 표현식으로 한다.
		let sessionName = '${name}';
		alert(sessionId +" : "+sessionName);
	}
	
</script>
</body>
</html>