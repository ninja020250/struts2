
function login(){
    var loginForm = document.getElementById("login-form");
   var username = document.getElementById("username").value;
   var  password = document.getElementById("password").value;
   if(!(username === "") && !(password === "")){
      loginForm.submit();
   }else{
     document.getElementById("error-login-message").innerHTML = "username or password must be inputed";
   }
  
}