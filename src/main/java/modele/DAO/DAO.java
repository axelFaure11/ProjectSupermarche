/*
 *THIS CLASS IS A TEMPORARY PLACEHOLDER. 
 *DO NOT USE IT IN FINAL VERSION.
 *CLASS 'DB' WILL REPLACE THIS ONE.
 */
package modele.DAO;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
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
            
            String sql = "SELECT * FROM Produit";
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
    
    public List<Commande> getClientCommands(String client_id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Client getClientInfo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Produit> getProdsInCat(String cat) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
