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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>STORE SYSTEM</h1>
        
        <h2>LOGIN</h2>


        <form action="LoginSv" method="POST">

            <p>
                <label>Nickname/Email</label> <input type="text" name="identifierField">
            </p>

            <p>
                <label>Password</label> <input type="password" name="passwordField">
            </p>

            <p>
                <button type="submit" >SignIn</button>
            </p>

        </form>
        
    </body>
</html>
