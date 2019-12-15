/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import modele.tempPanier;

/**
 *
 * @author Axel
 */
@WebServlet(name = "confirmCommand", urlPatterns = {"/confirmCommand"})
public class confirmCommand extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        tempPanier panier = (tempPanier) session.getAttribute("panier");
        CommandeDaoImpl cmdao = new CommandeDaoImpl();
        ClientDaoImpl cldao = new ClientDaoImpl();
        try(PrintWriter out = response.getWriter()){
            if(!panier.isEmpty()){
                try {
                    Client cl = cldao.getClient((String) session.getAttribute("code"));
                    Commande commande = new Commande(0, cl, 100.0, cl.getContact(), cl.getAdresse(), cl.getVille(), cl.getRegion(), cl.getCodePostal(), cl.getPays(), 0.0);
                    cmdao.addCommande(commande, panier);
                    panier.viderPanier();
                    out.println("Votre commande a bien été prise en compte.");
                } catch (SQLException ex) {
                    Logger.getLogger(confirmCommand.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("An error occured.");
                }
            } else {
                out.println("Votre panier était vide... Aucune opération effectuée.");
            }
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
