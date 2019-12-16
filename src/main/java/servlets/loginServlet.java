package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
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
 * @author Axel
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
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        if (null != action) {

            switch (action) {
                //On vérifie si l'user est connecté
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
                //On demande une nouvelle authentification 
                case "login":
                    checkLogin(request);

                    String userName = findUserInSession(request);
                    String view;

                    if (null == userName) { // L'utilisateur n'est pas connecté
                        // On choisit la page de login
                        view = "login.jsp";
                        request.setAttribute("log", "unlog");

                    } else { // L'utilisateur est connecté
                        // On choisit la page d'affichage
                        view = "afficheProduits.html";
                        request.setAttribute("log", "log");
                    }

                    //On redirige l'utilisateur en fonction de la page d'appel
                    if(view.equals("afficheProduits.html")){
                        response.sendRedirect("afficheProduits.html");
                    } else {
                        boolean ajax = "XMLHttpRequest".equals(
                        request.getHeader("X-Requested-With"));
                        if(ajax){
                            try(PrintWriter out = response.getWriter()){
                                out.write("login.jsp");
                            }
                        } else {
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    }
                    break;
                //On demande la déconnection d'une session
                case "logout":
                    doLogout(request);
                    try(PrintWriter out = response.getWriter()){
                        out.write("Disconnected");
                    }
                    break;
                default:
                    break;
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

    @Override
    public void init(ServletConfig config )throws ServletException {
        super.init(config);
    }
    
    private void checkLogin(HttpServletRequest request) throws SQLException {
		// Les paramètres transmis dans la requête
		String loginParam = request.getParameter("id");
		String passwordParam = request.getParameter("pass");

		// Le login/password défini dans web.xml
		String login = getInitParameter("login");
		String password = getInitParameter("password");
		String userName = "Mr. Super-User";
                
                ClientDaoImpl dao = new ClientDaoImpl();
                if(login.equals(loginParam) && password.equals(passwordParam)){
                    HttpSession session = request.getSession(false);
                    session.setAttribute("userName", login);
                    session.setAttribute("superUser", true);
                } else {
                
                    Client cl = dao.getClient(passwordParam);

                    if(cl!=null){
                        if (((passwordParam.equals(cl.getCode())))&&loginParam.equals(cl.getContact())) {
                            // On a trouvé la combinaison login / password
                            // On stocke l'information dans la session
                            HttpSession session = request.getSession(false); // récupération de la session
                            session.setAttribute("userName", loginParam);
                            session.setAttribute("superUser", false);
                            /*Exceptionnellement, on stocke le code qui sert de mot de passe
                            * puisque il s'agit de la clé primaire utilisée dans la base
                            * de données donnée dans la table client.
                            * Sinon, ne jamais stocker un mot de passe dans la session semble évident.
                            */
                            session.setAttribute("code", passwordParam);
                        } else {
                            request.setAttribute("errorMessage", "Login/Password incorrect");
                        }
                    } else { // On positionne un message d'erreur pour l'afficher dans la JSP
                            request.setAttribute("errorMessage", "Login/Password incorrect");
                        }
                }
	}

	private void doLogout(HttpServletRequest request) {
		// On termine la session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("userName");
                        session.removeAttribute("code");
                        session.setAttribute("superUser", false);
		}
	}

	private String findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session == null) ? null : (String) session.getAttribute("userName");
	}

}
