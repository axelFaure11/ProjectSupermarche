/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modele.Utilisateur;
import modele.Utilisateur;

/**
 *
 * @author rebecca
 */
public class UtilisateurDaoImpl {
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addUser(Utilisateur utilisateur) {     
        String sql= "INSERT INTO Utilisateur VALUES(?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, utilisateur.getUserId());
            db.getPstm().setString(2, utilisateur.getPassword());
            db.getPstm().setString(3, utilisateur.getEmail());

            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    public int updateUtilisateur (Utilisateur utilisateur){
        String sql="UPDATE utilisateur SET email = ?, password=? "
                +"WHERE userId =? ";
        
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setString(1, utilisateur.getUserId());
            db.getPstm().setString(2, utilisateur.getPassword());
            db.getPstm().setString(3, utilisateur.getEmail());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    
    public int deleteUtilisateur(String userId) {
        String sql="DELETE FROM utilisateur WHERE userId=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, userId);
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;     
    }
       
    
        public Utilisateur getUser(String userId) {
        Utilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateur WHERE userId = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setString(1, userId);
            rs=db.executeSelect();
            if (rs.next())
            {
                utilisateur =new Utilisateur();
                utilisateur.setUserId(rs.getString(1));
                utilisateur.setPassword(rs.getString(2));
                utilisateur.setEmail(rs.getString(3));
      
            }
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
        return utilisateur;
        }
    

    public List<Utilisateur> liste() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql= "SELECT * FROM utilisateur";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setUserId(rs.getString(1));
                utilisateur.setPassword(rs.getString(2));
                utilisateur.setEmail(rs.getString(3));
                utilisateurs.add(utilisateur);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return utilisateurs;
    }
    
}
