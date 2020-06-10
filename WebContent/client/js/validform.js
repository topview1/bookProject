/**
 * 为了验证表单的js文件
 */



var username = document.getElementById("username");
var password = document.getElementById("password");
var checkedPassword = document.getElementById("checkedPassword");
var telephone = document.getElementById("telephone");
var email = document.getElementById("email");
var introduce = document.getElementById("introduce");
var verCode = document.getElementById("verCode");

//确认用户名
function checkUserName() {
    var name_null = document.getElementById("tip1");
    var username_error = document.getElementById("tip2");
    if (username.value == null || username.value == "") {
        username_error.style.display = "none";
        name_null.style.display = "block";
        return false;
    }
    //用户名至少需要三位
    var username_patt = /^\w{3,}$/;
    if (!username_patt.test(username.value)) {
        name_null.style.display = "none";
        username_error.style.display = "block";

    }
    return true;
}

//确认密码
function checkPassword() {
    var password_null = document.getElementById("tip3");
    var password_error = document.getElementById("tip4");
    if (password.value == null || password.value == "") {
        password_error.style.display = "none";
        password_null.style.display = "block";
        return false;
    }
    //密码为4到8位
    var password_patt = /^\w{4,8}$/;
    if (!password_patt.test(password.value)) {
        password_null.style.display = "none";
        password_error.style.display = "block";

    }
    return true;
}


//验证密码 
function ConfirmPassword() {

    if ((password.value) != (checkedPassword.value) || checkedPassword.value == null || checkedPassword.value == "") {
        var checkedpassword_error = document.getElementById("tip5");
        checkedpassword_error.style.display = "block";
        return false;
    }
    return true;
}

//确认电话
function checkTelephone() {

    var telephone_error = document.getElementById("tip6");
    var telephone_null = document.getElementById("tip7");
    if (telephone.value == "" || telephone.value == null) {
        telephone_error.style.display = "none";
        telephone_null.style.display = "block";
        return false;
    }
    var telephone_patt = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    if (!telephone_patt.test(telephone.value)) {
        telephone_null.style.display = "none";
        telephone_error.style.display = "block";
        return false;
    }

    return true;
}

//确认邮箱
function checkEmail() {
    var email_null = document.getElementById("tip9");
    var email_error = document.getElementById("tip8");
    if (email.value == "" || email.value == null) {
        email_error.style.display = "none";
        email_null.style.display = "block";
        return false;
    }

    var email_patt = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (!email_patt.test(email.value)) {
        email_null.style.display = "none";
        email_error.style.display = "block";
        return false;
    }

    return true;
}

//验证验证码
function checkVerCode() {
    if (verCode.value == "" || verCode.value == null) {
        var verCode_null = document.getElementById("tip10");
        verCode_null.style.display = "block";
        return false;
    }
    return true;
}

//注册的验证方法
function validateForm() {
    var usernametip = checkUserName();
    var passtip = checkPassword();
    var conpasstip = ConfirmPassword();
    var phonetip = checkTelephone();
    var emailtip = checkEmail();
    var checkVctip = checkVerCode();
    return usernametip && passtip && conpasstip && phonetip && emailtip && checkVctip;
}



//登录的验证方法
function valiLoginForm(){
	 var usernametip = checkUserName();
	 var passtip = checkPassword();
	 return usernametip && passtip;
}

//修改用户信息的验证方法
function valiUserInfoForm(){
	  var passtip = checkPassword();
	  var conpasstip = ConfirmPassword();
	  var phonetip = checkTelephone();
	 return passtip && conpasstip && phonetip;
}

