<#ftl encoding='UTF-8'>
<html>
<head>
    <title>Title</title>
    <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<style>
    .titleOB{
        font-size: 40px;
    }
    .login h1 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }
    .login h2 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }
    .login h3 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }
    .login h4 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }
    .login h5 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }

    body{
        color: white;
    }
    .btn{
        margin-bottom: 20px;
    }
    a{
        color: white;
    }
</style>
<body>
<#if error??>
    <div class="alert alert-danger" role="alert">Login and password are incorrect</div>
</#if>
<div class="login">
    <h1 class="titleOB">Online Banking</h1>
    <h3>Login</h3>
    <form method="post" action="/login">
        <input type="email" name="mail" placeholder="E-Mail" required="required" />
        <input type="password" name="password" placeholder="Password" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
        <label>Don't have an account? Sign up <a href="/signUp">here</a></label>
    </form>
</div>
</body>
</html>