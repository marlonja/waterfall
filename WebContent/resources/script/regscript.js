$(document).ready(function(){
		var errorsJsonFormat = $("#error-msg").html();
		var errorMessages = JSON.parse(errorsJsonFormat);
		var errorStyle = "input-error";
		console.log(errorMessages);
		
		for(var i = 0; i < errorMessages.length; i++){
			console.log(errorMessages[i]);
			$(document.getElementById("reg-form:" + errorMessages[i])).addClass(errorStyle);
			
//			if(errorMessages[i] = "usernameNotUnique"){
//				$(document.getElementById("reg-form:username")).addClass(errorStyle);
//				console.log("username not unique");
//			}
//			if(errorMessages[i] = "usernameIsNull"){
//				$(document.getElementById("reg-form:username")).addClass(errorStyle);
//				console.log("username is null");
//			}
//			if(errorMessages[i] = "emailFormatIncorrect"){
//				$(document.getElementById("reg-form:email")).addClass(errorStyle);
//				console.log("email format incorrect");
//			}
//			if(errorMessages[i] = "emailNotUnique"){
//				$(document.getElementById("reg-form:email")).addClass(errorStyle);
//				console.log("email not unique");
//			}
//			
		}

});