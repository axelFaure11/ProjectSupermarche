<%-- 
    Document   : account_info
    Author     : Axel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info client</title>
        <script>
                $(document).ready(
                        function (){
                            getClientInfo();
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
                                    console.log(result);
                                    var template = $("#infoTemplate").html();
                                    var processedTemplate = Mustache.to_html(template, result);
                                    $("#info_placeholder").html(processedTemplate);
                                }
                    });
                }
        </script>
    </head>
    <body>
        <button id="return" onclick="window.location.assign('/afficheProduits.html')">Retour</button>
        <h1>Vos information :</h1>
        <div id="info_placeholder"></div>
    <script id="infoTemplate" type="text/template">
            {{#info_client}}
            <form action="<c:url value='/updateClientInfo' />" method="POST">
                <fieldset><legend>Vos informations</legend>
                    Contact : <input value='{{contact}}' id="id" name="id" required><br />
                    Fonction : <input value='{{fonction}}' id="fonction" name="fonction" required><br />
                    Tel. : <input value='{{tel}}' id="tel" name="tel" required><br />
                    Fax : <input value='{{fax}}' id="fax" name="fax" required><br />
                    Adresse : <input value='{{adresse}}' id="adress" name="adress" required><br />
                    Ville : <input value='{{ville}}' id="ville" name="ville" required><br />
                    Region : <input value='{{region}}' id="region" name="region" required><br />
                    Pays : <input value='{{pays}}' id="pays" name="pays" required><br />
                    <input id="action" name="action" type="submit" value="Mettre à jour">
            </form>
            {{/info_client}}
    </script>
    </body>
</html>
