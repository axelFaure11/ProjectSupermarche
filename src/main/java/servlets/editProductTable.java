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
import modele.DAO.ProduitDaoImpl;
import modele.Produit;

/**
 *
 * @author Axel
 */
@WebServlet(name = "editProductTable", urlPatterns = {"/editProductTable"})
public class editProductTable extends HttpServlet {

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
        
        String action = request.getParameter("action");
        int ref = Integer.parseInt(request.getParameter("ref"));
        String nom = request.getParameter("nom");
        int four = Integer.parseInt(request.getParameter("four"));
        int cat = Integer.parseInt(request.getParameter("cat"));
        String qtu = request.getParameter("qtu");
        Double prix = Double.parseDouble(request.getParameter("prix"));
        int ues = Integer.parseInt(request.getParameter("ues"));
        int uc = Integer.parseInt(request.getParameter("uc"));
        int ndr = Integer.parseInt(request.getParameter("ndr"));
        int ind = Integer.parseInt(request.getParameter("ind"));
        
        System.out.println(action);
        System.out.println(ref);
        System.out.println(nom);
        
        ProduitDaoImpl dao = new ProduitDaoImpl();
        
        switch (action){
            
            case "Ajouter":
                break;
                
            case "Update":
        {
            try {
                dao.updateProduit(new Produit(ref, nom, four, cat, qtu, prix, ues, uc, ndr, ind));
            } catch (SQLException ex) {
                Logger.getLogger(editProductTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
                
            case "Supprimer":
                break;
            
            default:
                break;
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
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