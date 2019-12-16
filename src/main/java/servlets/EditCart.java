package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Pair;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.DAO.ProduitDaoImpl;
import modele.Produit;
import modele.tempPanier;

/**
 *
 * @author Axel
 */
@WebServlet(name = "EditCart", urlPatterns = {"/EditCart"})
public class EditCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String act = request.getParameter("action");
        String ref = request.getParameter("ref");
        
        HttpSession session = request.getSession();
        tempPanier panier = (tempPanier) session.getAttribute("panier");
        ProduitDaoImpl dao = new ProduitDaoImpl();
        Pair lig;
        Iterator it;
        boolean updated;
        
        //Cette servlet peut effectuer 3 actions différentes :
        switch(act){
            
                //Ajouter un produit au panier
                case "add":
                    Produit pr = dao.getProduit(Integer.parseInt(ref));
                    //On vérifie que ce produit est en stock
                    if(pr.getUnitesEnStock()==0){
                        break;
                    }
                    
                    //Si il l'est en stock, on va vérifier si le produit est déjà dans le panier
                    lig = new Pair(pr, 1);
                    updated = false;
                    for(int i = 0; i<panier.size(); i++){
                        //Si il est dans le panier, on incrémente la quantité commandée de 1
                        if(panier.get(i).getKey().equals(lig.getKey())){
                            if(pr.getUnitesEnStock()>panier.get(i).getQuantity()){
                                panier.set(i, new Pair(panier.get(i).getKey(), panier.get(i).getQuantity() + 1));
                                panier.updatePrixTotal(pr.getPrix());
                            }
                            updated = true;
                        }
                    }
                    //Si le produit n'a produit aucune mise à jour, c'est qu'il n'est pas dans le panier
                    if(!updated){
                        panier.add(lig);
                        panier.updatePrixTotal(pr.getPrix());
                    }
                    break;
                
                //Supprimer un produit du panier
                case "del":
                    pr = dao.getProduit(Integer.parseInt(ref));
                    
                    lig = new Pair(pr, 1);
                    it = panier.iterator();
                    updated = false;
                    int updNbr = 0;
                    //On cherche dans le panier le produit à enlever complètement
                    while(it.hasNext()){
                        Pair currLig = (Pair) it.next();
                        if(currLig.getKey().equals(lig.getKey())){
                            updNbr = currLig.getQuantity();
                            it.remove();
                            updated = true;
                        }
                    }
                    //Si un produit a été enlevé, on met à jour le prix total du panier
                    if(updated){
                       panier.updatePrixTotal(-(updNbr*pr.getPrix()));
                    }
                    break;
                
                //Réduire la quantité d'un certain produit dans le panier
                case "delOne":
                    pr = dao.getProduit(Integer.parseInt(ref));
                    
                    lig = new Pair(pr, 1);
                    updated = false;
                    //On cherche dans le panier le produit à réduire
                    for(int i = 0; i<panier.size(); i++){
                        if(panier.get(i).getKey().equals(lig.getKey())){
                            if(panier.get(i).getQuantity() > 1){
                                panier.set(i, new Pair(panier.get(i).getKey(), panier.get(i).getQuantity() - 1));
                                updated = true;
                            } else {
                                panier.remove(i);
                                updated = true;
                            }
                        }
                    }
                    //Si une unite du produit a été enlevé, on met à jour le prix total du panier
                    if(updated){
                       panier.updatePrixTotal(-pr.getPrix());
                    }
                    break;
                
                //Supprimer tous les produits du panier
                case "delAll":
                    panier.viderPanier();
                    break;
                    
                default:
                    break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
