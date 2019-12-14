/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Commande;
import modele.DAO.ClientDaoImpl;
import modele.DAO.CommandeDaoImpl;
import modele.DAO.LigneDaoImpl;
import modele.Ligne;
import modele.Pair;
import modele.tempPanier;

/**
 *
 * @author pedago
 */
@WebServlet(name = "createCommand", urlPatterns = {"/createCommand"})
public class createCommand extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*
        //On récupère le panier stocké dans la session
        HttpSession session = request.getSession();
        tempPanier panier = (tempPanier) session.getAttribute("panier");
        String codeCli = (String) session.getAttribute("code");
        
        //Si le panier n'est pas vide
        if(!panier.isEmpty()){
            CommandeDaoImpl comdao = new CommandeDaoImpl();
            ClientDaoImpl clidao = new ClientDaoImpl();
            LigneDaoImpl lidao = new LigneDaoImpl();
            
            try {
                Client client = clidao.getClient(codeCli);
                Commande commande = new Commande();
                int num;
                try {
                    num = comdao.getNextCommandeNum();
                    Iterator<Pair> it = panier.iterator();
                    ArrayList<Ligne> lignes = new ArrayList();
                    Pair paire; 
                    Ligne ligne;
                    while(it.hasNext()){
                        ligne = new Ligne();
                        paire = it.next();
                        ligne.setCommande(num);
                        ligne.setProduit(paire.getKey().getRef());
                        ligne.setQuantite(paire.getQuantity());
                        lignes.add(ligne);
                    }
                    commande.setNumCommande(num);
                    commande.setClient(client);
                    commande.setAdresseLivraison(client.getAdresse());
                    commande.setDestinataire(client.getContact());
                    commande.setPort(50.0);
                    commande.setRemise(0.00);
                    
                    comdao.addCommande(commande);
                    lidao.addLignes(lignes);
                } catch (Exception e){
                    throw e;
                }
            } catch (SQLException ex) {
                Logger.getLogger(createCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } */
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
        processRequest(request, response);
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
        processRequest(request, response);
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
