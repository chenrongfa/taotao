var TT = TAOTAO = {
	checkLogin : function(){
		
		var _ticket = $.cookie("TT_TOKEN");
		alert(_ticket);
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8083/taotao-sso/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				console.log(data);
				
				if(data.status == 200){
					var username = data.dataBean.data.username;
					var html = username + "，欢迎来到淘淘！<a href=javascript:logout() class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
				
			}
		});
	}
}
function logout(){
	$.ajax({
		url:"http://localhost:8083/taotao-sso/user/logout/"+$.cookie("TT_TOKEN"),
		dataType: "jsonp",
		type: "GET",
		success : function(data){
			if(data.status == 200){
				alert("退出成功");
				location.href="http://localhost:8082/taotao-portal"
			}else{
				alert("退出成功");
			}
		}
	})
	
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});