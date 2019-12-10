/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Produit;
import modele.tempPanier;

/**
 *
 * @author Axel
 */
@WebServlet(name = "showPanier", urlPatterns = {"/showPanier"})
public class showPanier extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        tempPanier panier = (tempPanier) session.getAttribute("panier");
        Properties resultat = new Properties();
        
        if(panier == null){
            panier = new tempPanier();
            session.setAttribute("panier", panier);
            resultat.put("records", Collections.EMPTY_LIST);
            resultat.put("total", 0);
            
        } else {
            try {
                resultat.put("records", panier);
                resultat.put("total", panier.prixTotal);
            } catch (NullPointerException e) {
                resultat.put("records", Collections.EMPTY_LIST);
                resultat.put("total", 0);
            }
        }
        try(PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            String gsonData = gson.toJson(resultat);

            out.println(gsonData);
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
