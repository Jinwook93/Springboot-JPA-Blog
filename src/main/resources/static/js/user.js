let index = {
	//굳이 function(){}을 사용하겠다면
	//let _this = this;
	init: function() {
		$("#btn-save").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.save();
			//_this.save(); // function(){}을 사용할 경우
		});
		
		$("#btn-update").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.update();
		});
		// 폼로그인 사용함
		//		$("#btn-login").on("click",() =>{		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
		//			this.login();
		//		});
		
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			//			url : "/api/user",				스프링 시큐리티 쓰기 전
			data: JSON.stringify(data),		//http body 데이터
			contentType: "application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
			//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
		}).done(function(resp) {
			if(resp.status ===500){
					alert("회원가입에 실패했습니다");
			}else{
			alert("회원가입이 완료되었습니다");
			}
			//	alert(resp);
			console.log(data);
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},


	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),		//http body 데이터
			contentType: "application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
			//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다");
			//	alert(resp);
			console.log(data);
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	// 이 로그인 방식을 사용하지 않고, 스프링 시큐리티 로그인을 사용할 것임. 	
	//	login: function(){
	//		let data ={
	//			username: $("#username").val(),
	//			password: $("#password").val(),
	//		}
	//		//console.log(data);
	//		
	//		//ajax 호출시 default가 비동기 호출
	//		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
	//		$.ajax({
	//			type: "POST",
	//			url : "/api/user/login",
	//			data : JSON.stringify(data),		//http body 데이터
	//			contentType: "application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
	//			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
	//											//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
	//			}).done(function(resp){
	//				alert("로그인이 완료되었습니다");
	//			//	alert(resp);
	//				console.log(data);
	//				console.log(resp);
	//				location.href="/";
	//			}) .fail(function(error){
	//				alert(JSON.stringify(error));
	//			});
	//			}
}


index.init();