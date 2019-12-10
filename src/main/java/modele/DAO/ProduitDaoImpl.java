/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modele.Produit;

/**
 *
 * @author rebecca
 */
public class ProduitDaoImpl {
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int addProduit (Produit produit){
        String sql= "INSERT INTO produit VALUES (?,?,?,?,?,?,?,?,?)";
        try 
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            //Passage de valeurs
            db.getPstm().setInt(1, produit.getRef());
            db.getPstm().setString(2, produit.getNom());
            db.getPstm().setInt(3, produit.getCodeFournisseur());
            db.getPstm().setInt(4, produit.getCategorie());
            db.getPstm().setString(5, produit.getQuantite());
            db.getPstm().setDouble(6, produit.getPrix());
            db.getPstm().setInt(7, produit.getUnitesEnStock());
            db.getPstm().setInt(8, produit.getUnitesCommandees());
            db.getPstm().setInt(9, produit.getNiveauReapprovi());

            

            //Execution de la requete
            ok = db.executeMaj();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ok;  
    }
    
    public int deleteProduit(int ref){
        String sql= "DELETE FROM produit WHERE ref = ?";
        ok=0;
        try
        {   
           // Initialisation de la requete
           db.initPrepare(sql);
           // Passage des valeurs 
           db.getPstm().setInt(1, ref);
           // Execution  de la requete
           ok= db.executeMaj();
        }
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;            
    }
    
    public int updateProduit(Produit produit){
        String sql= "UPDATE produit SET ref = ?, nom =?, codeFournisseur =?, categorie =?, quantite =?, prix =?,"
                + "unitesEnStock =?, unitesCoommandees=?, niveauReapprovi =?,WHERE ref=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs
            db.getPstm().setInt(1, produit.getRef());
           db.getPstm().setString(2, produit.getNom());
           db.getPstm().setInt(3, produit.getCodeFournisseur());
           db.getPstm().setInt(4, produit.getCategorie());
           db.getPstm().setString(5, produit.getQuantite());
           db.getPstm().setDouble(6, produit.getPrix()); 
           db.getPstm().setInt(7, produit.getUnitesEnStock());
           db.getPstm().setInt(8, produit.getUnitesCommandees());
           db.getPstm().setInt(9, produit.getNiveauReapprovi());

        }  
        catch(Exception e)
        {
           e.printStackTrace(); 

        }
        return ok;
    }
    
    public Produit getProduit(int ref){
        Produit produit = null;  
        String sql= "SELECT * FROM produit WHERE ref= ?";
        try
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, ref);
            ok = db.executeMaj();
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return produit;  
    
    }
    
    public List<Produit> liste() {
		List<Produit> produits = new ArrayList<>();
		String sql = "SELECT * FROM produit";
		try
		{
			db.initPrepare(sql);
			rs = db.executeSelect();
			while(rs.next())
			{
				Produit produit = new Produit();
				produit.setRef(rs.getInt(1));
				produit.setNom(rs.getString(2));
				produit.setCodeFournisseur(rs.getInt(3));
                                produit.setCategorie(rs.getInt(4));
                                produit.setQuantite(rs.getString(5));
                                produit.setPrix((int) rs.getDouble(6));
                                produit.setUnitesEnStock(rs.getInt(7));
                                produit.setUnitesCommandees(rs.getInt(8));
                                produit.setNiveauReapprovi(rs.getInt(9));
                                
				produits.add(produit);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return produits;
	}
    
}
