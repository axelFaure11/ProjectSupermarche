/*
 *THIS CLASS IS A TEMPORARY PLACEHOLDER. 
 *DO NOT USE IT IN FINAL VERSION.
 *CLASS 'DB' WILL REPLACE THIS ONE.
 */
package modele.DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Categorie;
import modele.Client;
import modele.Commande;
import modele.Produit;

public class DAO {
    
    protected final DataSource appDataSource;
    
    public DAO(DataSource dataSource){
        this.appDataSource = dataSource;
    }

    public List<Produit> getAllProd() throws SQLException {
        List<Produit> result = new ArrayList();
        try(Connection con = appDataSource.getConnection();
            Statement stmt = con.createStatement()){
            
            String sql = "SELECT * FROM PRODUIT";
            ResultSet rs = stmt.executeQuery(sql);
            Produit pr;
            while(rs.next()){
                pr = new Produit();
                pr.setRef(rs.getInt("Reference"));
                pr.setNom(rs.getString("Nom"));
                pr.setCodeFournisseur(rs.getInt("Fournisseur"));
                pr.setCategorie(rs.getInt("Categorie"));
                pr.setQuantite(rs.getString("Quantite_par_unite"));
                pr.setPrix(rs.getInt("Prix_unitaire"));
                pr.setUnitesEnStock(rs.getInt("Unites_en_stock"));
                pr.setUnitesCommandees(rs.getInt("Unites_commandees"));
                pr.setNiveauReapprovi(rs.getInt("Niveau_de_reappro"));
                result.add(pr);
            }
        }
        return result;
    }
    
    public List<Categorie> getAllCat() throws SQLException {
        List<Categorie> result = new ArrayList();
        try(Connection con = appDataSource.getConnection();
            Statement stmt = con.createStatement()){
            
            String sql = "SELECT * FROM CATEGORIE";
            ResultSet rs = stmt.executeQuery(sql);
            Categorie ct;
            while(rs.next()){
                ct = new Categorie();
                ct.setCode(rs.getInt("Code"));
                ct.setDescription(rs.getString("Description"));
                ct.setLibelle(rs.getString("Libelle"));
                result.add(ct);
            }
        }
        return result;
    }
    
    public List<Commande> getClientCommands(String client_id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Client getClientInfo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Produit> getProdsInCat(String cat) throws SQLException {
        List<Produit> result = new ArrayList();
        String sql = "SELECT * "
                + "FROM PRODUIT P "
                + "WHERE CATEGORIE = ?";
        try(Connection con = appDataSource.getConnection();
            PreparedStatement pStmt = con.prepareStatement(sql)){
            pStmt.setInt(1, Integer.parseInt(cat));
            
            ResultSet rs = pStmt.executeQuery();
            Produit pr;
            while(rs.next()){
                pr = new Produit();
                pr.setRef(rs.getInt("Reference"));
                pr.setNom(rs.getString("Nom"));
                pr.setCodeFournisseur(rs.getInt("Fournisseur"));
                pr.setCategorie(rs.getInt("Categorie"));
                pr.setQuantite(rs.getString("Quantite_par_unite"));
                pr.setPrix(rs.getInt("Prix_unitaire"));
                pr.setUnitesEnStock(rs.getInt("Unites_en_stock"));
                pr.setUnitesCommandees(rs.getInt("Unites_commandees"));
                pr.setNiveauReapprovi(rs.getInt("Niveau_de_reappro"));
                result.add(pr);
            }
        }
        return result;
    }
}
