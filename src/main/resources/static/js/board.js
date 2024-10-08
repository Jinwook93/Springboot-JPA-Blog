let index = {
	//굳이 function(){}을 사용하겠다면
	//let _this = this;
	init: function() {
		$("#btn-save").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.save();
			//_this.save(); // function(){}을 사용할 경우
		});

		$("#btn-delete").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.deleteById();
		});
		$("#btn-update").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.update();
		});
		$("#btn-reply-save").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.replySave();
		});
		$("#btn-reply-delete").on("click", () => {		// function(){}를 사용하지 않고,  ()=>{}를 사용한 이유 : this를 바인딩 하기 위해서
			this.replyDelete();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
		$.ajax({
			type: "POST",
			url: "/auth/board",
			//			url : "/api/user",				스프링 시큐리티 쓰기 전
			data: JSON.stringify(data),		//http body 데이터
			contentType: "application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
			//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다");
			//	alert(resp);
			console.log(data);
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	deleteById: function() {
		let id = $("#id").text();
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			//			url : "/api/user",				스프링 시큐리티 쓰기 전
			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
			//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
		}).done(function(resp) {
			alert("삭제가 완료되었습니다");
			//	alert(resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	update: function() {
		let data = {
			id: $("#id").val(),
			title: $("#title").val(),
			content: $("#content").val(),
		}
		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!!!!
		$.ajax({
			type: "PUT",
			url: "/api/board/" + data.id,
			//			url : "/api/user",				스프링 시큐리티 쓰기 전
			data: JSON.stringify(data),		//http body 데이터
			contentType: "application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
			dataType: "json"	//요청을 서버로 해서 응답이 나왔을 떄 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 바꿔준다.
			//dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정 
		}).done(function(resp) {
			alert("수정이 완료되었습니다");
			//	alert(resp);
			console.log(data);
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};

		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("댓글 삭제가 완료되었습니다.");
			location.href = `/board/${boardId}`;
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