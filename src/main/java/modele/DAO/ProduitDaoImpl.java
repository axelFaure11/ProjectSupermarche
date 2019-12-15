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
import modele.Produit;

/**
 *
 * @author rebecca
 */
public class ProduitDaoImpl {
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int addProduit (Produit produit) throws SQLException{
        String sql= "INSERT INTO produit VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            db.getPstm().setInt(10, produit.getIndisponible());
            

            //Execution de la requete
            ok = db.executeMaj();
            db.getCnx().close();
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return ok;  
    }
    
    public int deleteProduit(int ref) throws SQLException{
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
           db.getCnx().close();
        }
        catch(Exception e)
        {
           throw new SQLException(e);

        }
        return ok;            
    }
    
    public int updateProduit(Produit produit) throws SQLException{
        String sql= "UPDATE produit SET reference = ?, nom =?, fournisseur =?, categorie =?, quantite_par_unite =?, prix_unitaire =?,"
                + "unites_en_stock =?, unites_commandees=?, niveau_de_reapprovi =?, indisponile=? WHERE ref=?";
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
           db.getPstm().setInt(10, produit.getIndisponible());
           db.getCnx().close();
        }  
        catch(Exception e)
        {
           throw new SQLException(e);

        }
        return ok;
    }
    
      public int updateQteProduit(Produit produit) throws SQLException{
        String sql= "UPDATE produit SET quantite_par_unite =? WHERE reference=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs        
           db.getPstm().setInt(5, Integer.parseInt(produit.getQuantite()));
             db.getPstm().setInt(1, produit.getRef());
            db.getCnx().close();
         
        }  
        catch(Exception e)
        {
           throw new SQLException(e);

        }
        return ok;
    }
      
      public int updateStockProduit(Produit produit, int quantity) throws SQLException{
        String sql= "UPDATE produit SET unites_en_stock =? WHERE reference=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs        
           db.getPstm().setInt(1, produit.getUnitesEnStock() - quantity);
             db.getPstm().setInt(2, produit.getRef());
            ok=db.executeMaj();
         
        }  
        catch(Exception e)
        {
           throw new SQLException(e);

        }
        return ok;
    }

    
    public Produit getProduit(int ref) throws SQLException{
        Produit produit = null;  
        String sql= "SELECT * FROM produit WHERE reference = ?";
        try
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, ref);
            rs = db.executeSelect();
            if(rs.next()){
                produit = new Produit();
                produit.setRef(rs.getInt(1));
                produit.setNom(rs.getString(2));
                produit.setCodeFournisseur(rs.getInt(3));
                produit.setCategorie(rs.getInt(4));
                produit.setQuantite(rs.getString(5));
                produit.setPrix((int) rs.getDouble(6));
                produit.setUnitesEnStock(rs.getInt(7));
                produit.setUnitesCommandees(rs.getInt(8));
                produit.setNiveauReapprovi(rs.getInt(9));
                produit.setIndisponible(rs.getInt(9));
            }
            rs.close();
            db.getCnx().close();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return produit;  
    
    }
    
     public List<Produit> getProduitInCat(int ref) throws SQLException{
        List<Produit> produits = new ArrayList<>();  
        String sql= "SELECT * FROM produit WHERE categorie = ?";
        try
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, ref);
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
                                produit.setIndisponible(rs.getInt(9));
				produits.add(produit);
			}
            rs.close();
            db.getCnx().close();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return produits;  
    
    }
    
    public List<Produit> liste() throws SQLException {
		List<Produit> produits = new ArrayList<>();
		String sql = "SELECT * FROM produit";
		try
		{
			db.initPrepare(sql);
                        System.out.println(db);
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
                                produit.setIndisponible(rs.getInt(9));
                                
				produits.add(produit);
			}
            rs.close();
            db.getCnx().close();
		}
		catch(Exception e)
		{
			throw new SQLException(e);
		}
		return produits;
	}
    
}
