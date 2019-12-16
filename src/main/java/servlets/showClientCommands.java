package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

/**
 *
 * @author Axel
 */
@WebServlet(name = "showClientCommands", urlPatterns = {"/showClientCommands"})
public class showClientCommands extends HttpServlet {

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
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            
            ClientDaoImpl cldao = new ClientDaoImpl();
            CommandeDaoImpl comdao = new CommandeDaoImpl();
            LigneDaoImpl lidao = new LigneDaoImpl();
            
            
            Properties commandes = new Properties();
            try {
                Client cl = cldao.getClient((String) session.getAttribute("code"));
                ArrayList<Map.Entry<Commande, List<Ligne>>> allCom = new ArrayList<>();
                
                List<Commande> listCom = comdao.getCommandeFromClient(cl);
                Iterator<Commande> commande = listCom.iterator();
                Commande c;
                
                while(commande.hasNext()){
                    c = commande.next();
                    allCom.add(new AbstractMap.SimpleEntry<Commande, List<Ligne>>(c, lidao.getLignes(c)));
                }
                commandes.put("allCom", allCom);
            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		commandes.put("allCom", Collections.EMPTY_LIST);
		commandes.put("message", e.getMessage());
            }
            
            Gson gson = new Gson();
            out.println(gson.toJson(commandes));
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
