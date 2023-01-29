<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$("#btnjoin").on("click", function(){
		if($("#memberId").val()==""){
			alert("아이디를 입력해주세요.");
			$("#memberId").focus();
			return false;
		}
		if($("#memberPw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#memberPw").focus();
			return false;
		}
		if($("#memberEmail").val()==""){
			alert("이메일을 입력해주세요.");
			$("#memberEmail").focus();
			return false;
		}
		if($("#memberName").val()==""){
			alert("성명을 입력해주세요.");
			$("#memberName").focus();
			return false;
		}
		if($("#memberPhone").val()==""){
			alert("연락처 입력해주세요.");
			$("#memberPhone").focus();
			return false;
		}
		var idChkVal = $("#idchk").val();
		if(idChkVal == "N"){
			alert("중복확인 버튼을 눌러주세요.");
		}else if(idChkVal == "Y"){
			$("#joinForm").submit();
		}
	})
//카카오 회원가입
Kakao.init('ebc8fc53624cdd3b21b34f099837f10c');
console.log(Kakao.isInitialized()); // sdk초기화여부판단
function kakaoLogin() {
    $.ajax({
        url: '/login/getKakaoAuthUrl',
        type: 'get',
        async: false,
        dataType: 'text',
        success: function (res) {
            location.href = res;
        }
    });
  }
  
//-------------------------------------------
      var kakaoInfo = '${kakaoInfo}';
      if(kakaoInfo != ""){
          var data = JSON.parse(kakaoInfo);
          alert("카카오로그인 성공 \n accessToken : " + data['accessToken']);
          alert(
          "user : \n" + "email : "
          + data['email']  
          + "\n nickname : " 
          + data['nickname']);
      }
  
 //휴대폰 번호 자동 하이픈 추가 
 
	   $("#memberPhone").keydown(function(event) {   //입력창에 숫자 입력
	       var key = event.charCode || event.keyCode || 0;
	       $text = $(this); 
	      
	       if (key !== 8 && key !== 9) {
	           if ($text.val().length === 3) {
	               $text.val($text.val() + '-');
	           }
	           if ($text.val().length === 8) {
	               $text.val($text.val() + '-');
	           }
	       }
	   })
  
 
//아이디 중복 체크
	$("#idchk").click(function(e){
		console.log($("#memberId").val())
		e.preventDefault()
	   $.ajax({
	      url : "/login/idchk",
	      type : "post",
	      dataType : "text",
	  //    contentType: 'application/json',
	      data : {
	         "memberId" : $("#memberId").val()
	      },
	      success : function(data) {
	    	  
			console.log("data", data)
	         if (data === '1') {
	            alert('중복된 아이디입니다.');	
	         } else if (data === '0'){
	            $("#idchk").attr("value", "Y");
	            alert('사용가능한 아이디입니다.');
	            } else {
				alert('요청중 에러가 발생하였습니다');
				}
	         }
	      , error: function(e){
	    	    console.log(e)
	    	  }
	      })
	})
   
//비밀번호 일치 확인
	$('#pwchk').click(function(e){
		e.preventDefault()
		// 비밀번호 입력 값 가져오기
		var pw = $('#memberPw').val();
		
		// 비밀번호 확인 입력 값 가져오기
		var confirmPw = $('#memberPwck').val();
			
		if(pw == confirmPw){
			alert("비밀번호가 일치합니다.");
		}else{
			alert("비밀번호가 일치하지 않습니다");
		}
	})
// 가입 전 유효성 검사
	$('#btnjoin').click(function(e) {
	//가입 전에 입력한 항목이 규칙에 따라 정확히 입력되어있는지 확인하기 위해       
	// submit 이벤트 막아주기
	e.preventDefault();
	
	// 아이디 입력 값 가져오기
	var id = $('#memberId').val();
	
	// 비밀번호 입력 값 가져오기
	var pw = $('#memberPw').val();
	
	// 비밀번호 확인 입력 값 가져오기
	var confirmPw = $('#memberPwck').val();
	
	// 핸드폰번호 입력 값 가져오기
	var phone = $('#memberPhone').val();
	
	//이메일 입력 값 가져오기
	var email = $('#memberEmail').val();
	
	// 정규식 일치여부 등을 저장할 회원가입 혹은 회원정보 수정의 항목 success 변수 정의하기
	var idSucc;
	var pwSucc;
	var confirmPwSucc;
	var phoneSucc;
	var emailSucc;
	
	// 정규식 등으로 정확히 입력했는지, 입력하지 않았다면 띄울 문구와 해당 엘리먼트에 focus 지정하기
	
	let reg_id1 = /^[a-z0-9_-]{4,20}$/; // 소문자 + 숫자 + 언더바/하이픈 허용 4~20자리
	let reg_pw2 = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/; // 문자와 특수문자 조합의 8~16 자리
	let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/; // 휴대폰 번호
	let reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; // (이메일) 길이까지 확실한 검증
	
	//아이디 체크
	if (id.length > 21 || id.length < 5) {
		$('#memid').focus()
		$('#idMsg').text('아이디는 4~20자 소문자 + 숫자 + _ , -')
	}
	
	//pw 체크
	if (pw.length > 17 || pw.length < 7) {
		$('#mempw').focus()
		$('#pwMsg').text('비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자')
	
		} else {
			idSucc = true;
			pwSucc = true;
			confirmPwSucc = true;
			phoneSucc = true;
			emailSucc = true;
		}
	
	// 모든 항목에 정상 값을 입력했을 경우 모든 success변수가 true라면 submit
	
	if (idSucc && pwSucc && confirmPwSuc && phoneSucc && emailSucc) {
		// 폼 엘리먼트.submit();
		$('#joinForm').submit();
		}
	})
});
	
	
</script>

<style type="text/css">
.padding {
	padding: 10rem !important
}
body {
	background-color: #f9f9fa
}
/* card css */
.card {
	border: 0;
	border-radius: 0px;
	margin-bottom: 30px;
	-webkit-box-shadow: 0 2px 3px rgba(0, 0, 0, 0.03);
	box-shadow: 0 2px 3px rgba(0, 0, 0, 0.03);
	-webkit-transition: .5s;
	transition: .5s;
}
.card-title {
	font-family: Roboto, sans-serif;
	font-weight: 300;
	line-height: 3.2;
	margin-bottom: 0;
	padding: 15px 20px;
	border-bottom: 1px solid rgba(77, 82, 89, 0.07);
	margin: 0 auto;
}
.card-body {
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	padding: 1.25rem;
	line-height: 2.5;
	text-align: center;
}
/* card 내부 */
.errorMsg {
color:red;
font-size: 9pt;
}
#idMsg {
margin-left: -11%;
}
#pwMsg {
margin-left: -7%;
}
.fw-400 {
	font-weight: 400 !important;
}
.col-md-6 {
	flex: 0 0 auto;
	width: 60%;
}
.form-id, .form-phone, .form-email {
	width: 370px;
	color: #323232;
	font-size: 10pt;
	font-weight: 540; 
}
.form-pwck {
	width: 380px;
	color: #323232;
	font-size: 10pt;
	font-weight: 540;
}
.form-pw {
	width: 370px;
	color: #323232;
	font-size: 10pt;
	font-weight: 540;
}
.form-name {
	width: 330px;
	color: #323232;
	font-size: 10pt;
	font-weight: 540;
}
.form-group {
	padding: 8px;
}
.form-group-id {
	height: 72px;
}
.form-control {
	border-color: #ebebeb;
	border-radius: 2px;
	color: #8b95a5;
	padding: 1px 1px;
	font-size: 12px;
	line-height: inherit;
	-webkit-transition: 0.2s linear;
	transition: 0.2s linear;
	width: 50%;
	margin: 0 auto;
}
.card-body>*:last-child {
	margin-bottom: 0;
}
.form-control:focus {
	border-color: #6CC4DC;
	color: #4d5259;
	-webkit-box-shadow: 0 0 0 0.1rem rgba(51, 202, 187, 0.15);
	box-shadow: 0 0 0 0.1rem rgba(51, 202, 187, 0.15);
}
	
/* button css */
#btn {
	width: 50%;
	padding: 12px;
	margin-bottom: 10px;
}
#btnjoin {
	width: 50%;
	padding: 12px;
	margin-bottom: 10px;
}
#idchk {
	width: 10%;
	padding: 6px;
	display: inline;
	margin-left: 500px;
	margin-top: -65px;
}
#pwchk {
	width: 11%;
	padding: 6px;
	display: inline;
	margin-left: 420px;
	margin-top: -65px;
}
.btn-primary {
	background-color: #6CC4DC;
	border-color: #6CC4DC;
	color: #fff;
}
.btn-bold {
	font-family: Roboto, sans-serif;
	text-transform: uppercase;
	font-weight: 500;
	font-size: 12px;
}
.btn-primary:hover {
	background-color: #3F92B7;
	border-color: #3F92B7;
	color: #fff;
}
#btn:hover {
	cursor: pointer;
}
</style>

</head>
<body>


	<div class="page-content page-container" id="page-content">
		<div class="padding">
			<div class="row container d-flex justify-content-center">
				<div class="col-md-6 col-lg-4">
					<form action="/join" method="post" class="card" id="joinForm">
						<h4 class="card-title fw-400">JOIN</h4>
						<br>
						
						<div class="card-body">
							<div class="form-group-id">
								<div class="form-id">아이디</div>
								<div>
									<input class="form-control" type="text" id="memberId" name="memberId"
										placeholder="  아이디는 4~20자 소문자 + 숫자 + _ , -" >
									 <span id="idMsg" class="errorMsg"></span>
									<button type="button" class="btn btn-bold btn-primary btnsm" id="idchk" value="N">중복확인</button>
									<br>
								</div>
							</div><br>

							<div class="form-group">
								<div class="form-pw">비밀번호</div>
								<input class="form-control" type="password" id="memberPw" name="memberPw"
									placeholder="  영문, 숫자, 특수문자를 포함하여 8자리 이상">	
								 <span id="pwMsg" class="errorMsg"></span>    
																	
							</div>
							<div class="form-group">
								<div class="form-pwck">비밀번호확인</div>
								<input class="form-control" type="password" id="memberPwck" name="memberPwck">
								<button class="btn btn-bold btn-primary btnsm" id="pwchk" type="button" value="N">일치확인</button>
								<br>
							</div>
							<div class="form-group">
								<div class="form-name">이름</div>
								<input class="form-control" type="text" id="memberName" name="memberName">
							</div>
							<div class="form-group">
								<div class="form-phone">연락처</div>
								<input class="form-control" type="text" id="memberPhone" name="memberPhone">
							</div>
							<div class="form-group">
								<div class="form-email">이메일</div>
								<input class="form-control" type="text" id="memberEmail" name="memberEmail">
							</div>
							<div class="form-group">
								<div class="form-email">닉네임</div>
								<input class="form-control" type="text" id="memberNick" name="memberNick">
							</div>
							<br>


							<!-- 회원가입 버튼 -->

							<div class=buts>
								<button class="btn btn-bold btn-primary" id="btnjoin">JOIN</button>
								<img src="https://ifh.cc/g/7XpWOg.png" alt="카카오계정 로그인" onclick="kakaoLogin();"
								style="height: 46px; width: 50%; padding-left: 5px;"> <a href="javascript:void(0)"></a> <br>							</div>
							<br>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>