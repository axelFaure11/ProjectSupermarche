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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {
                packages: ['corechart'],
                callback: function () {
                  //drawChart();
                }
              });
            function drawChart(){
                $.ajax({
                    url: "/dataVisualisation",
                    data: {"opt": $("#opt").val(),
                           "datedeb": $("#datedeb").val(),
                           "datefin": $("#datefin").val()},
                    dataType: "json",
                    error: showError,
                    success:
                            function(result){
                                $("#graph").html("");
                                var vdata = new google.visualization.DataTable(result);
                                var chart = new google.visualization.ColumnChart(document.getElementById('graph'));
                                setTimeout(chart.draw(vdata, {width: 1000, height: 1000}), 100);
                            }
                });
            }
            $(document).ready(
                function (){
                    $("#selectVisu").submit(function(e){
                        e.preventDefault();
                        drawChart();
                    });
                    showAllProd();
                    showAllCat();
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
            
            function emptyForm(){
                $("#ref").val("");
                $("#nom").val("");
                $("#four").val("");
                $("#cate").val("");
                $("#qtu").val("");
                $("#prix").val("");
                $("#ues").val("");
                $("#uc").val("");
                $("#ndr").val("");
                $("#ind").val("");
            }
            
            function fillForm(ref){
                $.ajax({
                   url: "/showProductDetails",
                   data: {"ref": $(ref).val()},
                   dataType: "json",
                   error: showError,
                   success:
                           function (result){
                                var template = $("#formTemplate").html();
                                var processedTemplate = Mustache.to_html(template, result);
                                $("#form_placeholder").html(processedTemplate);
                                $("#input").submit(function(e) { 
                                    e.preventDefault();
                                    var val = $("input[type=submit][clicked=true]").val();
                                    editProductTable(val);
                                });
                                $("#input input[type=submit]").click(function() {
                                    $("input[type=submit]", $(this).parents("form")).removeAttr("clicked");
                                    $(this).attr("clicked", "true");
                                });
                                       }
                });
            }
            
            function showAllProd() {
                $.ajax({
                    url: "/showAllProd",
                    dataType: "json",
                    error: showError,
                    success:
                            function(result) {
                                var template = $("#prodTemplate").html();
                                var processedTemplate = Mustache.to_html(template, result);
                                $("#prods").html(processedTemplate);
                            }
                });
            }
            function showProdInCat(cat) {
                $.ajax({
                    url: "/showProdInCat",
                    data: {"cat": cat},
                    dataType: "json",
                    error: showError,
                    success:
                            function(result) {
                                var template = $("#prodTemplate").html();
                                var processedTemplate = Mustache.to_html(template, result);
                                $("#prods").html(processedTemplate);
                            }
                });
            }
            function showAllCat() {
                $.ajax({
                    url: "/showAllCat",
                    dataType: "json",
                    error: showError,
                    success:
                            function(result) {
                                var template = $("#catTemplate").html();
                                var processedTemplate = Mustache.to_html(template, result);
                                $("#cats").html(processedTemplate);
                            }
                });
            } 
            
            function editProductTable(action){
                $.ajax({
                   url: "/editProductTable",
                   data: {"action": action,
                          "ref": $("#ref").val(),
                          "nom": $("#nom").val(),
                          "four": $("#four").val(),
                          "cat": $("#cate").val(),
                          "qtu": $("#qtu").val(),
                          "prix": $("#prix").val(),
                          "ues": $("#ues").val(),
                          "uc": $("#uc").val(),
                          "ndr": $("#ndr").val(),
                          "ind": $("#ind").val()},
                   error: showError,
                   success:
                          showAllProd()
                });
                
            }
            
        </script>
    </head>
    <body>
        <button id="logout" onclick="logOut()">Se déconnecter</button>
        <div style="display: flex;">
            <div id="editProd">
                <div>
                <br />
                    <div id="cats" ></div>
                    <div id="prods" style="overflow-y: scroll; height: 500px;"></div>
                </div><br /><br />
                <button id="empty_form" onclick="emptyForm()">Vider le formulaire</button><br />
                <div id="form_placeholder">
                    <form id="input" method="POST">
                        Reference : <input type="number" id="ref" name="ref" value="" readOnly><br />
                        Nom : <input type="text" id="nom" name="nom" value="" required><br />
                        Fournisseur : <input type="number" id="four" name="four" value="" min="0" required><br />
                        Catégorie : <input type="number" id="cate" name="cate" value="" min="0" required><br />
                        Quantite par unité : <input type="text" id="qtu" name="qtu" value="" required><br />
                        Prix unitaire : <input type="number" id="prix" name="prix" value="" min="0" required><br />
                        Unites en stock : <input type="number" id="ues" name="ues" value="" min="0" required><br />
                        Unites commandes :  <input type="number" id="uc" name="uc" value="" min="0" required><br />
                        Niveau de reapprovisionnement : <input type="number" id="ndr" name="ndr" value="" min="0" required><br />
                        Indisponible : <input type="number" id="ind" name="ind" value="" min="0" max="1" required><br />
                        <input type="submit" value="Ajouter">
                        <input type="submit" value="Update">
                        <input type="submit" value="Supprimer">
                    </form>
                </div>
            </div>
            <div id="dataVisual">
                <form id="selectVisu" method="GET">
                    <span>Chiffre d'affaire par : </span>
                    <select id="opt" name="opt">
                        <option type="text" value="cat">Catégorie</option>
                        <option type="text" value="pays">Pays</option>
                        <option type="text" value="client">Client</option>
                    </select>
                    <span>entre </span>
                    <input type="date" id="datedeb" name="datedeb" value="1990-01-01" min="1990-01-01" max="2021-01-01">
                    <span> et </span>
                    <input type="date" id="datefin" name="datefin" value="2021-01-01" min="1990-01-01" max="2021-01-01">
                    <input type="submit" value="Charger">
                </form>
                <div id="graph"></div>
            </div>
        </div>
        <script id="catTemplate" type="text/template">
                    <table class="cattab"><tr>
                            <td><button class="catb" onclick="showAllProd()">Tout</button></td>
                            {{#records}}
                                <td><button class="catb" onclick="showProdInCat('{{code}}')">{{libelle}}</button></td>
                            {{/records}}
                    </tr></table>
        </script>
        <script id="prodTemplate" type="text/template">
                    <table class="prodtab">
                            {{#records}}
                            <tr>
                                <td class="prodl">
                                    <button class="prodb" value="{{ref}}" onclick="fillForm(this)">{{nom}}</button>
                                </td>
                            </tr>
                            {{/records}}
                    </table>
        </script>
        <script id="formTemplate" type="text/template"><br />
            {{#records}}
                <form id="input" method="POST">
                    Reference : <input type="number" id="ref" name="ref" value="{{ref}}" readOnly><br />
                    Nom : <input type="text" id="nom" name="nom" value="{{nom}}" required><br />
                    Fournisseur : <input type="number" id="four" name="four" value="{{codeFournisseur}}" min="0" required><br />
                    Catégorie : <input type="number" id="cate" name="cate" value="{{categorie}}" min="0" required><br />
                    Quantite par unité : <input type="text" id="qtu" name="qtu" value="{{quantite}}"><br />
                    Prix unitaire : <input type="number" id="prix" name="prix" value="{{prix}}" min="0" required><br />
                    Unites en stock : <input type="number" id="ues" name="ues" value="{{unitesEnStock}}" min="0" required><br />
                    Unites commandes :  <input type="number" id="uc" name="uc" value="{{unitesCommandees}}" min="0" required><br />
                    Niveau de reapprovisionnement : <input type="number" id="ndr" name="ndr" value="{{niveauReapprovi}}" min="0" required><br />
                    Indisponible : <input type="number" id="ind" name="ind" value="{{indisponible}}" min="0" max="1" required><br />
                    <input type="submit" value="Ajouter">
                    <input type="submit" value="Update">
                    <input type="submit" value="Supprimer">
                </form>
            {{/records}}
        </script>
    </body>
</html>
