<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="/sign" method="post">
    用户名：<input type="text" name="name"/><br/>
    密码： <input type="password" name="password"/><br/>
    Email: <input type="email" name="email"><br/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
