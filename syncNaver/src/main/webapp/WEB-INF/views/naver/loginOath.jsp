<!doctype html>
<%@ page pageEncoding="euc-kr"%>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<style>
	input {
		border-width: 0;
	}
</style>
<script type="text/javascript">
	var naver_id_login = new naver_id_login("2OckOgKg6tKGkEyvzWXs", "http://127.0.0.1/loginoath.do");
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
// 	  var token = naver_id_login.oauthParams.access_token;
// 		  var url = 'profileimage.do';
// 		  $.post(url, token, function(data){
// 			    alert(data.token);    //gddong
// 			}, "json");

	  // 접근 토큰 값 출력
	  // 네이버 사용자 프로필 조회
	  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	alert(naver_id_login.oauthParams.access_token);
	alert(naver_id_login.oauthParams.refresh_token);
	function naverSignInCallback() {
	    $('#email').text(naver_id_login.getProfileData('email'));
// 	    $('#email').text('메롱이다');
	    $('#birthday').text(naver_id_login.getProfileData('birthday'));
	    $('#gender').text(naver_id_login.getProfileData('gender'));
	    $('#id').text(naver_id_login.getProfileData('id'));
	    $('#nickname').text(naver_id_login.getProfileData('nickname'));
	    $('#name').text(naver_id_login.getProfileData('name'));
	    $('#profile').text('src',naver_id_login.getProfileData('profile_image'));
	}
	function tokenDelete (){
		var token = naver_id_login.oauthParams.access_token;
    	var url = 'tokenDelete.do'
	    $.post(url, token, function(data){
		    alert(data.token);    //gddong
		}, "json");
    	
    }
	function tokenReload(){
		var token = naver_id_login.oauthParams.refresh_token;
    	var url = 'tokenReload.do'
	    $.post(url, token, function(data){
		    alert(data.token);    //gddong
		}, "json");
    	
    }
</script>

</head>
<body>
	
<div>
	<table>
		<tr>
		  <th>정보</th>
		  <th>값</th>
		</tr>
		<tr>
		  <td>email</td>
		  <td id ='email'></td>
		</tr>
		<tr>
		  <td>birthday</td>
		  <td id ='birthday'></td>
		</tr>
		<tr>
		  <td>gender</td>
		  <td id ='gender'></td>
		</tr>
		<tr>
		  <td>id</td>
		  <td id = 'id'></td>
		</tr>
		<tr>
		  <td>nickname</td>
		  <td id = 'nickname'></td>
		</tr>
		<tr>
		  <td>name</td>
		  <td id ='name'></td>
		</tr>
		
	</table>
	<img id ='profile' alt="프로필이미지" src="">
	
  <button onClick = 'tokenDelete()'>삭제</button>
  <button onClick = 'tokenReload()'>갱신</button>
</div>
</body>
</html>
		