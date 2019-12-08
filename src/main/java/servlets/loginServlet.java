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
import modele.DAO.DAO;
import modele.DataSourceFactory;

/**
 *
 * @author pedago
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {

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
        
        response.setContentType("text/plain;charset=UTF-8");
        
        String action = request.getParameter("action");
        System.out.println("action : " + action);
		if (null != action) {
			switch (action) {
                                case "check":
                                        try(PrintWriter out = response.getWriter()){
                                            String str = findUserInSession(request);
                                            if(null != str){
                                                out.write(str);
                                            } else {
                                                out.write("not connected");
                                            }
                                        }
                                        break;
				case "login":
					checkLogin(request);
                                        
                                        String userName = findUserInSession(request);
                                        String view;

                                        if (null == userName) { // L'utilisateur n'est pas connecté
                                                // On choisit la page de login
                                                view = "login.jsp";

                                        } else { // L'utilisateur est connecté
                                                // On choisit la page d'affichage
                                                view = "afficheProduits.html";
                                        }
                                        System.out.println(view);
                                        if(view.equals("afficheProduits.html")){
                                            try(PrintWriter out = response.getWriter()){
                                                request.getRequestDispatcher(view).forward(request, response);
                                            }
                                        }else {
                                            try(PrintWriter out = response.getWriter()){
                                                out.write("login.jsp");
                                            }
                                        }
					break;
				case "logout":
					doLogout(request);
                                        try(PrintWriter out = response.getWriter()){
                                            out.write("Disconnected");
                                        }
					break;
                                default:
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void checkLogin(HttpServletRequest request) throws SQLException {
		// Les paramètres transmis dans la requête
		String loginParam = request.getParameter("id");
		String passwordParam = request.getParameter("pass");

		// Le login/password défini dans web.xml
		String login = getInitParameter("login");
		String password = getInitParameter("password");
		String userName = getInitParameter("userName");
                
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                
                Client cl = dao.getClientInfo(loginParam);
                
                if(cl!=null){
                    System.out.println("Client found!");
                    if (((passwordParam.equals(cl.getCode())))) {
			// On a trouvé la combinaison login / password
			// On stocke l'information dans la session
			HttpSession session = request.getSession(false); // démarre la session
			session.setAttribute("userName", loginParam);
                        System.out.println("Attributed!");
                    } else {
                        request.setAttribute("errorMessage", "Login/Password incorrect");
                        System.out.println("Failed!");
                    }
                } else { // On positionne un message d'erreur pour l'afficher dans la JSP
			request.setAttribute("errorMessage", "Login/Password incorrect");
                        System.out.println("Failed!");
                    }
	}

	private void doLogout(HttpServletRequest request) {
		// On termine la session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("userName");
		}
	}

	private String findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session == null) ? null : (String) session.getAttribute("userName");
	}

}
