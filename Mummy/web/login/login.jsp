<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="j_security_check" method=post>
            <strong>Please Enter Your User Name: </strong>
            <input type="text" name="j_username" size="25">
            <br/>
            <strong>Please Enter Your Password: </strong>
            <input type="password" size="15" name="j_password">
            <br/>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>   
    </body>
</html>
