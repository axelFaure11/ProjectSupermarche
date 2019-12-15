<%-- 
    Document   : adminPage
    Created on : 15 déc. 2019, 16:36:52
    Author     : Axel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
        <title>Page d'administration</title>
        <script>
            $(document).ready(
                function (){
                    
                });
        
            function showError(xhr, status, message) {
                alert("" + xhr.status + " " + status + " " + message);
            }
            
            function logOut(){
                $.ajax({
                    url: "/login",
                    data: {"action": "logout"},
                    dataType: "text",
                    error: showError,
                    success:
                            function (result){
                                if($.trim(result)==="Disconnected"){
                                    window.location.assign("/");
                                }
                            }
                });
            }
            
            function dataVisualisation(){
                $.ajax({
                    url: "/dataVisualisation",
                    data: {},
                    dataType: "json",
                    error: showError,
                    success:
                            function (result){
                                
                            }
                });
            }
        </script>
    </head>
    <body>
        <button id="logout" onclick="logOut()">Se déconnecter</button>
        <div style="display: flex;">
            <div id="editProd"></div>
            <div id="dataVisual">
                <form id="selectVisu" action="dataVisualisation()" method="GET">
                    <span>Chiffre d'affaire par : </span>
                    <select id="opt" name="opt">
                        <option value="cat">Catégorie</option>
                        <option value="pays">Pays</option>
                        <option value="client">Client</option>
                    </select>
                    <span>entre </span>
                    <input type="date" id="datedeb" name="datedeb" min="1990-01-01" max="2021-01-01">
                    <span> et </span>
                    <input type="date" id="datefin" name="datefin" min="1990-01-01" max="2021-01-01">
                    <input type="submit" value="Charger">
                </form>
                <div id="graph"></div>
            </div>
        </div>
    </body>
</html>
