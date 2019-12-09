/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modele.Ligne;

/**
 *
 * @author rebecca
 */
public class LigneDaoImpl 
{
    
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addLigne(Ligne ligne) {     
        String sql= "INSERT INTO Ligne VALUES(?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, ligne.getCommande());
            db.getPstm().setInt(2, ligne.getProduit());
            db.getPstm().setInt(3, ligne.getQuantite());
         
            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    public int updateLigne (Ligne ligne ){
        String sql="UPDATE ligne SET commande=?, produit=?, quantite=? ";
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setInt(1, ligne.getCommande());
            db.getPstm().setInt(2, ligne.getProduit());
            db.getPstm().setInt(3, ligne.getQuantite());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;
    }
    
    
    public int deleteLigne(int commande, int produit) {
        String sql="DELETE FROM ligne WHERE commande=? AND produit = ?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, commande);
            db.getPstm().setInt(2, produit);
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ok;     
    }
       
    
        public Ligne getLigne(int commande, int produit) {
        Ligne ligne = null;
        String sql = "SELECT * FROM ligne WHERE commande = ? AND produit = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, commande);
            db.getPstm().setInt(1, produit);
            rs=db.executeSelect();
            if (rs.next())
            {
                ligne=new Ligne();
                ligne.setCommande(rs.getInt(1));
                ligne.setProduit(rs.getInt(2));
                ligne.setQuantite(rs.getInt(3));               
            }
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
        return ligne;
        }
    

    public List<Ligne> liste() {
        List<Ligne> lignes = new ArrayList<>();
        String sql= "SELECT * FROM ligne";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Ligne ligne = new Ligne();
                ligne.setCommande(rs.getInt(1));
                ligne.setProduit(rs.getInt(2));
                ligne.setQuantite(rs.getInt(3));               
                lignes.add(ligne);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //
        }
        return lignes;
    }
    
}
