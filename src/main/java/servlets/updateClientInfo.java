/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.DAO.ClientDaoImpl;
/**
 *
 * @author pedago
 */
@WebServlet(name = "updateClientInfo", urlPatterns = {"/updateClientInfo"})
public class updateClientInfo extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        
        response.setContentType("application/json;charset='UTF-8'");
        request.setCharacterEncoding("UTF-8");
        ClientDaoImpl dao = new ClientDaoImpl();
        HttpSession session = request.getSession(false);
        
        Client cl = dao.getClient((String) session.getAttribute("code"));
        String contact = request.getParameter("id");
        String societe = request.getParameter("societe");
        String fonction = request.getParameter("fonction");
        String tel = request.getParameter("tel");
        String fax = request.getParameter("fax");
        String adresse = request.getParameter("adress");
        String ville = request.getParameter("ville");
        String cp = request.getParameter("codePostal");
        String region = request.getParameter("region");
        String pays = request.getParameter("pays");
        
        if(contact!=null){cl.setContact(contact);}
        if(societe!=null){cl.setSociete(societe);}
        if(fonction!=null){cl.setFonction(fonction);}
        if(tel!=null){cl.setTel(tel);}
        if(fax!=null){cl.setFax(fax);}
        if(adresse!=null){cl.setAdresse(adresse);}
        if(ville!=null){cl.setVille(ville);}
        if(cp!=null){cl.setCodePostal(cp);}
        if(region!=null){cl.setRegion(region);}
        if(pays!=null){cl.setPays(pays);}
        int r = dao.updateClient(cl);
        
        
        response.setHeader("Refresh", "0.1; URL=logged/account_info.jsp");
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
            Logger.getLogger(updateClientInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateClientInfo.class.getName()).log(Level.SEVERE, null, ex);
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
