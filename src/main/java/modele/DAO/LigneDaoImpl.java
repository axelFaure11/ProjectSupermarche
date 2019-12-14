/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Commande;
import modele.Ligne;
import modele.Produit;

/**
 *
 * @author rebecca
 */
public class LigneDaoImpl 
{
    
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addLigne(Ligne ligne) throws SQLException {     
        String sql= "INSERT INTO Ligne VALUES(?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, ligne.getCommande());
            db.getPstm().setInt(2, ligne.getProduit().getRef());
            db.getPstm().setInt(3, ligne.getQuantite());
         
            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    public int updateLigne (Ligne ligne ) throws SQLException{
        String sql="UPDATE ligne SET quantite=? WHERE commande=? AND produit=? ";
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setInt(1, ligne.getQuantite());
            db.getPstm().setInt(2, ligne.getCommande());
            db.getPstm().setInt(3, ligne.getProduit().getRef());
            

        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    
    public int deleteLigne(Commande commande,Produit produit) throws Exception {
        String sql="DELETE FROM ligne WHERE commande=? AND produit = ?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setInt(1, commande.getNumCommande());
            db.getPstm().setInt(2, produit.getRef());
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;     
    }
       
    
        public Ligne getLigne(Commande commande,Produit produit) throws SQLException {
        Ligne ligne = null;
        String sql = "SELECT * FROM ligne WHERE commande = ? AND produit = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, commande.getNumCommande());
            db.getPstm().setInt(2, produit.getRef());
            rs=db.executeSelect();
            if (rs.next())
            {
                ligne=new Ligne();
                  ligne.setCommande(new CommandeDaoImpl().getCommande(rs.getInt(1)).getNumCommande());
                  ligne.setProduit(new ProduitDaoImpl().getProduit(rs.getInt(1)));
             ligne.setQuantite(rs.getInt(3));             
            }
        }
            catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ligne;
        }
        
        public List<Ligne> getLignes(Commande commande) throws SQLException {
        List<Ligne> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne WHERE commande = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, commande.getNumCommande());
            rs=db.executeSelect();
            Ligne ligne;
            while (rs.next())
            {
                ligne=new Ligne();
                ligne.setCommande(commande.getNumCommande());
                ligne.setProduit(new ProduitDaoImpl().getProduit(rs.getInt(2)));
                ligne.setQuantite(rs.getInt(3));  
                lignes.add(ligne);
            }
        }
            catch(Exception e)
        {
            throw new SQLException(e);
        }
        return lignes;
        }
    

    public List<Ligne> liste() throws SQLException {
        List<Ligne> lignes = new ArrayList<>();
        String sql= "SELECT * FROM ligne";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Ligne ligne = new Ligne();
                ligne.setCommande(new CommandeDaoImpl().getCommande(rs.getInt(1)).getNumCommande());
                ligne.setProduit(new ProduitDaoImpl().getProduit(rs.getInt(1)));
                ligne.setQuantite(rs.getInt(3));
                lignes.add(ligne);
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return lignes;
    }
    
}
