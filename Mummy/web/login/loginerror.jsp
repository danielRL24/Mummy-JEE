<%-- 
    Document   : loginerror
    Created on : 18 mars 2017, 18:43:39
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>Login Failed | Mummy</title>
        <!--<h:outputStylesheet library="css" name="jsfcrud.css"/>-->
        
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
       
        <!--Import materialize.css-->
        <link rel="stylesheet" href="/Mummy/resources/css/materialize.css"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        
        <style>
        html,
        body {
        height: 100%;
        }
        html {
        display: table;
        margin: auto;
        }
        body {
        display: table-cell;
        vertical-align: middle;
        background-image: url("/Mummy/resources/imgs/home-bg.jpeg");
        background-position-x: center;
        background-position-y: center;
        background-attachment: fixed;
        background-repeat: no-repeat;
        background-size: cover;
        text-align: center;
        }
        </style>
    </head>
    <body>
        <div id="login-page" class="row">            
            <div class="col s12 z-depth-6 card-panel">
                <div class="row">
                    <div class="input-field col s12 center">
                        <a href="/Mummy/index.xhtml" title="home"><h1>Mummy</h1></a>
                    </div>
                </div>
                <div class="row">
                    Login Failed !
                </div>
                <div class="row">
                    <a class="waves-effect waves-light btn" href="/Mummy/login/login.xhtml"><i class="material-icons left">arrow_back</i>Login</a>
                </div>
            </div>
        </div>
        
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="/Mummy/resources/js/materialize.js" />
    </body>
</html>
