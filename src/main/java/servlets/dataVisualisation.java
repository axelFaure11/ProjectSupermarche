/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Categorie;
import modele.ChartCols;
import modele.ChartEntry;
import modele.DAO.CommandeDaoImpl;

/**
 *
 * @author Axel
 */
@WebServlet(name = "dataVisualisation", urlPatterns = {"/dataVisualisation"})
public class dataVisualisation extends HttpServlet {

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
        
        System.out.println(request.getParameter("opt"));
        System.out.println(request.getParameter("datedeb"));
        System.out.println(request.getParameter("datefin"));
        
        String opt = request.getParameter("opt");
        Date datedeb = java.sql.Date.valueOf(request.getParameter("datedeb"));
        Date datefin = java.sql.Date.valueOf(request.getParameter("datefin"));
        
        Properties resultat = new Properties();
        
        CommandeDaoImpl comdao = new CommandeDaoImpl();
        Gson gson = new Gson();
        String json = "";
           
        switch (opt) {
            case "cat":
        {
            try {
                ArrayList<ChartEntry> data = comdao.chiffreAffCategorie(datedeb, datefin);
                
                Iterator<ChartEntry> it = data.iterator();
                ArrayList<ChartCols> coldata = new ArrayList<ChartCols>();
                coldata.add(new ChartCols("", "Catégorie", "", "string"));
                coldata.add(new ChartCols("", "Chiffre d'affaire", "", "number"));
                
                resultat.put("cols", coldata);
                resultat.put("rows", data);
                json = gson.toJson(resultat);
                System.out.println(resultat);
                
            } catch (SQLException ex) {
                Logger.getLogger(dataVisualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            
            case "pays":
        {
            try {
                ArrayList<ChartEntry> data = comdao.chiffreAffPays(datedeb, datefin);
                
                Iterator<ChartEntry> it = data.iterator();
                ArrayList<ChartCols> coldata = new ArrayList<ChartCols>();
                coldata.add(new ChartCols("", "Pays", "", "string"));
                coldata.add(new ChartCols("", "Chiffre d'affaire", "", "number"));
                
                resultat.put("cols", coldata);
                resultat.put("rows", data);
                json = gson.toJson(resultat);
                System.out.println(resultat);
                
            } catch (SQLException ex) {
                Logger.getLogger(dataVisualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
                
            case "client":
        {
            try {
                ArrayList<ChartEntry> data = comdao.chiffreAffClient(datedeb, datefin);
                
                Iterator<ChartEntry> it = data.iterator();
                ArrayList<ChartCols> coldata = new ArrayList<ChartCols>();
                coldata.add(new ChartCols("", "Societe", "", "string"));
                coldata.add(new ChartCols("", "Chiffre d'affaire", "", "number"));
                
                resultat.put("cols", coldata);
                resultat.put("rows", data);
                json = gson.toJson(resultat);
                System.out.println(resultat);
                
            } catch (SQLException ex) {
                Logger.getLogger(dataVisualisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            
            default:
                break;
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println(json);
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
