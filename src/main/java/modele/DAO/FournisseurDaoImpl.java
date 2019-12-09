/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modele.Client;
import modele.Fournisseur;

/**
 *
 * @author rebecca
 */
public class FournisseurDaoImpl 
{
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addFournisseur(Fournisseur fournisseur) {     
        String sql= "INSERT INTO Fournisseur VALUES(?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, fournisseur.getCodeFournisseur());
            db.getPstm().setString(2, fournisseur.getAdresse());
            db.getPstm().setString(3, fournisseur.getNom());
            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    public int updateFournisseur (Fournisseur fournisseur){
        String sql="UPDATE fournisseur SET nom=?, adresse=?"
            + "WHERE codeFournisseur=?";
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setInt(1, fournisseur.getCodeFournisseur());
            db.getPstm().setString(2, fournisseur.getAdresse());
            db.getPstm().setString(3, fournisseur.getNom());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    
    public int deleteFournisseur(int codeFournisseur) {
        String sql="DELETE FROM fournisseur WHERE codeFournisseur=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, codeFournisseur);
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;     
    }
       
    
        public Fournisseur getFournisseur(int codeFournisseur) {
        Fournisseur fournisseur = null;
        String sql = "SELECT * FROM fournisseur WHERE codeFournisseur = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, codeFournisseur);
            rs=db.executeSelect();
            if (rs.next())
            {
                fournisseur=new Fournisseur();
                fournisseur.setCodeFournisseur(rs.getInt(1));
                fournisseur.setAdresse(rs.getString(2));
                fournisseur.setNom(rs.getString(3));
           ;
      
            }
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
        return fournisseur;
        }
    

    public List<Fournisseur> liste() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql= "SELECT * FROM fournisseur";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setCodeFournisseur(rs.getInt(1));
                fournisseur.setAdresse(rs.getString(2));
                fournisseur.setNom(rs.getString(3));
                fournisseurs.add(fournisseur);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return fournisseurs;
    
    
    }    
}
