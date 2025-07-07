<%-- 
    Document   : PruebaJSP
    Created on : 5 jul 2025, 19:09:25
    Author     : compi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Store System Login</title>
    </head>
    <body>
        <div class="infoDiv">
            <h1>STORE SYSTEM</h1>
            <p>Welcome!!!</p>
        </div>
        
        <div class="login">

            <h2><b>LOGIN</b></h2>

            <form action="LoginSv" method="POST" class="loginForm">

                <p> <input type="text" name="identifierField" placeholder="nickname or email" class="field"> </p>

                <p> <input type="password" name="passwordField" placeholder="password" class="field"> </p>

                <p> <button type="submit" class="enterButton"><b>Enter</b></button> </p>

            </form>

        </div>
        
    </body>
</html>
