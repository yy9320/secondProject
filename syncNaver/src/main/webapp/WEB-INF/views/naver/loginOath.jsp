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
</head>
<body>
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
	function naverSignInCallback() {
	    alert(naver_id_login.getProfileData('email'));
	    alert(naver_id_login.getProfileData('birthday'));
	    alert(naver_id_login.getProfileData('gender'));
	    alert(naver_id_login.getProfileData('id'));
	    alert(naver_id_login.getProfileData('nickname'));
	    alert(naver_id_login.getProfileData('profile_image'));
	    alert(naver_id_login.getProfileData('name'));
	    $('#email').val(naver_id_login.getProfileData('email'));
	    $('#birthday').val(naver_id_login.getProfileData('birthday'));
	    $('#gender').val(naver_id_login.getProfileData('gender'));
	    $('#id').val(naver_id_login.getProfileData('id'));
	    $('#nickname').val(naver_id_login.getProfileData('nickname'));
	    $('#name').val(naver_id_login.getProfileData('name'));
	    $('#profile').attr('src',naver_id_login.getProfileData('profile_image'));
	}
</script>

<div>
	<table>
		<tr>
		  <th>정보</th>
		  <th>값</th>
		</tr>
		<tr>
		  <td>email</td>
		  <td> <input  id ='email'></td>
		</tr>
		<tr>
		  <td>birthday</td>
		  <td ><input id ='birthday'></td>
		</tr>
		<tr>
		  <td>gender</td>
		  <td><input id ='gender'></td>
		</tr>
		<tr>
		  <td>id</td>
		  <td> <input id = 'id'></td>
		</tr>
		<tr>
		  <td>nickname</td>
		  <td ><input id = 'nickname'></td>
		</tr>
		<tr>
		  <td>name</td>
		  <td > <input id ='name'/></td>
		</tr>
		
	</table>
	<img id ='profile' alt="프로필이미지" src="">
</div>
</body>
</html>
		