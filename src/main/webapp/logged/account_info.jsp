<%-- 
    Document   : account_info
    Author     : Axel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href=".../CSS/account.css"> 
        <title>Info client</title>
        <script>
                $(document).ready(
                        function (){
                            getClientInfo();
                            getClientCommands();
                });
                
                function showError(xhr, status, message){
                        console.log(xhr.status + " " + status + " " + message);
                }
                
                function getClientInfo(){
                    $.ajax({
                        url: "/showClientInfo",
                        dataType: "json",
                        error: showError,
                        success:
                                function (result){
                                    var template = $("#infoTemplate").html();
                                    var processedTemplate = Mustache.to_html(template, result);
                                    $("#info_placeholder").html(processedTemplate);
                                }
                    });
                }
                
                function getClientCommands(){
                    $.ajax({
                       url: "/showClientCommands",
                       dataType: "json",
                       error: showError,
                       success:
                               function (result){
                                   console.log(result);
                                   var template = $("#comTemplate").html();
                                   var processedTemplate = Mustache.to_html(template, result);
                                   $("#commands_placeholder").html(processedTemplate);
                               }
                    });
                }
        </script>
    </head>
    <body>
        <button id="return" onclick="window.location.assign('/')">Retour</button>
        <div>
            <h1>Vos informations :</h1>
            <div id="info_placeholder"></div>
            <h1>Vos commandes passées :</h1>
            <div id="commands_placeholder"></div>
        </div>
    <script id="infoTemplate" type="text/template">
            {{#info_client}}
                <form action="<c:url value='${pageContext.request.contextPath}/updateClientInfo' />" method="POST">
                    <fieldset>
                        Contact : <input value='{{contact}}' id="id" name="id" required><br />
                        Societe : <input value='{{societe}}' id="societe" name="societe" required><br />
                        Fonction : <input value='{{fonction}}' id="fonction" name="fonction"><br />
                        Tel. : <input value='{{tel}}' id="tel" name="tel"><br />
                        Fax : <input value='{{fax}}' id="fax" name="fax"><br />
                        Adresse : <input value='{{adresse}}' id="adress" name="adress"><br />
                        Ville : <input value='{{ville}}' id="ville" name="ville"><br />
                        Code postal : <input value='{{codePostal}}' id="codePostal" name='codePostal'><br />
                        Region : <input value='{{region}}' id="region" name="region"><br />
                        Pays : <input value='{{pays}}' id="pays" name="pays"><br />
                        <input id="action" name="action" type="submit" value="Mettre à jour">
                </form>
            {{/info_client}}
    </script>
    <script id="comTemplate" type="text/template">
        {{#allCom}}
                <table class="singleCommand">
                    <tbody>
                    <tr><td>Numero commande : {{key.numCommande}}<td></tr>
                    <tr><td>Date de création : {{key.saisieLe}}</td></tr>
                    <tr><td>Date d'envoi : {{key.envoyeeLe}}</td></tr>
                    <tr><td class="commandEntries"><br><u>Contenu</u> :
                    <table>
                        <tbody>
                        {{#value}}
                            <tr><td>&emsp;&emsp;{{produit.nom}} (x {{quantite}})</td></tr>
                        {{/value}}
                        </tbody>
                    </table>
                    </td></tr>
                    </tbody>
                </table>
        {{/allCom}}
    </script>
    </body>
</html>
