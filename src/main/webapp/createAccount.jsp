<%-- 
    Document   : signin
    Created on : 7 jul 2025, 18:28:53
    Author     : compi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="infoDiv">
            <h1>STORE SYSTEM</h1>
            <p>Welcome!!!</p>
        </div>

        <div class="createAccountDiv">

            <h2><b>Account Info</b></h2>

            <form action="CreateAccountServlet" method="POST" class="createForm">

                <p> <input type="text" name="nameField" placeholder="Your Name" class="field"> </p>

                <p> <input type="text" name="paternalLastnameField" placeholder="Paternal Lastname" class="field"> </p>

                <p> <input type="text" name="maternalLastnameField" placeholder="Maternal Lastname" class="field"> </p>

                <p> <input type="text" name="phoneNumberField" placeholder="Phone Number" class="field"> </p>

                <p> <input type="text" name="nicknameField" placeholder="Nickname" class="field"> </p>
                
                <p> <input type="text" name="emailField" placeholder="Email" class="field"> </p>

                <p> <input type="password" name="passwordField" placeholder="Password" class="field"> </p>

                <p> <button type="submit" class="createButton"><b>Create Account</b></button> </p>

            </form>

        </div>

    </body>
</html>
