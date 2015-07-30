function login(){
	//console.log("enter");
	var username = $("#username").val();
	var password = $("#password").val();
	var input = {};
	input.username = username;
	input.password = password;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	$.ajax({
		url:'/SimTech/admin/GetUserByUsernameServlet?input='+inputStr,
		method:'POST',
		dataType: 'json',
		error: function(err){
			console.log(err);
			$("#message").html("system failed to login in");
		},
		success: function(data){
			console.log(data);
			var status = data.status;
			var message = data.message;
			if(status== 1){
				var user = message;
				var companyId = user.companyId;
				var officerId = user.officerId;
				if(companyId != null){
					localStorage.setItem('companyId', companyId);
					//TODO Remove the comment after success testing
					window.location = "../index.html";
				}
				if(officerId != null){
					localStorage.setItem('officerId', officerId);
					//TODO Remove the comment after success testing
					//window.location = "../index.html";
				}
			}else{
				$("#message").html("Invalid Eamil/Password");
				console.log(message);
			}
		}
	});
}

function register(){
	$("#message").html('');
	$(".my-loading").removeClass('sr-only' );
	var input = {};
	
	var name = $("#name").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	//var symplAppIndex = $("#registerSymplAppIndex").val();
	var agreeTerms = false;
	if ($('#agreeTerms').is(":checked")){
		agreeTerms = true;
		if(password != password2){
			$("#message").html("Please confirm your password again!");
			//$(".my-loading").addClass('sr-only');
		}else{
			input.name = name;
			input.username = username;
			input.password = password;
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
			
			$.ajax({
				url: '/Payroll/admin/CreateApplicationServlet?input='+inputStr,
				method: 'GET',
				dataType: 'json',
				error: function(err){
					console.log(err);
					$(".my-loading").addClass('sr-only');
				},
				success: function(data){
					console.log(data);
					var status = data.status;
					var message = data.message;
					if(status == 1){
						var companyId = message.companyId;
						localStorage.setItem('companyId', companyId);
						//TODO Remove the comment after success testing
						//window.location = "pages.index.html";
					}else{
						$("#message").html(message);
					}
					$(".my-loading").addClass('sr-only');
				}
			});
		}
	}else{
		$("#message").html("Please accept our terms before we can continue.");
	}
}