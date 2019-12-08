<%-- 
    Document   : login
    Author     : Axel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page de connexion</title>
    </head>
    <body>
        <form action="afficheProduits.html">
         <input type="submit" value="<" />
        </form>
        <table id="toptable">
            <td><div id="categories"></div></td>
            <td><form action="<c:url value="/login" />" method="POST">
                <fieldset><legend>Saisissez vos identifiants</legend>
                    Identifiant : <input id="id" name="id" required><br />
                    Mot de passe : <input type="password" id="pass" name="pass" required><br />
                    <input id="action" name="action" type="submit" value="login">
                </form></td>
        </table>
    </body>
</html>
