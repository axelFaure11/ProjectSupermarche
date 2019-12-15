<%-- 
    Document   : confirmCommand
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
        <title>Confirmer commande</title>
        <script>
            $(document).ready(
                function (){
                    showSCart();
                });
            
            function showError(xhr, status, message) {
                alert("" + xhr.status + " " + status + " " + message);
            }
            
            function showSCart() {
                $.ajax({
                    url: "/showPanier",
                    dataType: "json",
                    error: showError,
                    success:
                           function (result){
                                setTimeout(function () {
                                    console.log(result);
                                    var template = $("#cartTemplate").html();
                                    var processedTemplate = Mustache.to_html(template, result);
                                    $("#cart_placeholder").html(processedTemplate);
                                }, 100);
                            }
                });

            } 
            
            function EditCart(op, ref) {
                $.ajax({
                    url: "/EditCart",
                    data: {"action": $(op).val(),
                           "ref": ref
                          },
                    error: showError,
                    success:
                           function(){
                               showSCart();
                           }
                });
            }
            
            function commandFailed(){
                $("#error").html("Une erreur est survenue lors de la commande.");
            }
            
            function confirmingCommand(){
                $.ajax({
                    url: "/confirmCommand",
                    dataType: "html",
                    error: commandFailed,
                    success:
                            function(result){
                                $("#titre").html(result);
                                $("#cart_placeholder").html("");
                                $("#valid").remove();
                            }
                });
            }
        </script>
    </head>
    
    <body>
        <button id="return" onclick="window.location.assign('/')">Retour</button>
        <h1 id="titre">Votre panier :</h1>
        <div id="cart_placeholder"></div>
        <button id="valid" onclick="confirmingCommand()">Valider votre commande</button>
        <script id="cartTemplate" type="text/template">
            <table class="carttab">
                    <tr><td>Total: {{total}}¥</td>
                        <td><button class="removeAll" value="delAll" onclick="EditCart(this, 'null')">Vider le panier</button></td>
                    </tr>
                    {{#records}}
                        <tr>
                            <td><button class="remove" value="del" onclick="EditCart(this, {{pr.ref}})">X</button>
                            </td>
                            <td>
                                <p>{{pr.nom}}    (x{{quantity}}) : {{pr.prix}}*{{quantity}}¥</p>
                            </td>
                            <td>
                                <button class="addOne" value="add" onclick="EditCart(this, {{pr.ref}})"> + </button> 
                                <button class="remOne" value="delOne" onclick="EditCart(this, {{pr.ref}})"> - </button>
                            </td>
                        </tr>
                    {{/records}}
            </table>
        </script>>
    </body>
</html>
