/*
 *THIS CLASS IS A TEMPORARY PLACEHOLDER. 
 *DO NOT USE IT IN FINAL VERSION.
 *CLASS 'DB' WILL REPLACE THIS ONE.
 */
package modele.DAO;



import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
