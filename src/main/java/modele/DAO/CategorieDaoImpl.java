package modele.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Categorie;

/**
 *
 * @author rebecca
 */
public class CategorieDaoImpl {
    
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    
    public int addCategorie (Categorie categorie) throws SQLException{
        String sql= "INSERT INTO categorie VALUES (?,?,?)";
        try 
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            //Passage de valeurs
            db.getPstm().setInt(1, categorie.getCode());
            db.getPstm().setString(2, categorie.getLibelle());
            db.getPstm().setString(3, categorie.getDescription());
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
    
    public int deleteCategorie(int code) throws SQLException{
        String sql= "DELETE FROM categorie WHERE code = ?";
        ok=0;
        try
        {   
           // Initialisation de la requete
           db.initPrepare(sql);
           // Passage des valeurs 
           db.getPstm().setInt(1, code);
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
    
    public int updateCategorie(Categorie categorie) throws SQLException{
        String sql= "UPDATE categorie SET libelle = ?, description =?, WHERE code=?";
        ok=0;
        try
        {
           // Initalisation de la requete 
            db.initPrepare(sql);
           //  Passage des valeurs
           db.getPstm().setString(1, categorie.getLibelle());
           db.getPstm().setString(2, categorie.getDescription());
           db.getPstm().setInt(3, categorie.getCode());
            db.getCnx().close();

        }  
        catch(Exception e)
        {
           throw new SQLException(e);

        }
        return ok;
    }
    
    public Categorie getCategorie(int code) throws SQLException{
        Categorie categorie = null;  
        String sql= "SELECT * FROM categorie WHERE code= ?";
        try
        {
            db.initPrepare(sql);
            db.getPstm().setInt(1, code);
            rs = db.executeSelect();
            if(rs.next()){
                categorie = new Categorie(rs.getInt("code"), rs.getString("libelle"), rs.getString("description"));
            }
            db.getCnx().close();
            rs.close();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return categorie;  
    
    }
    
    public List<Categorie> liste() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM categorie";
        try
        {
                db.initPrepare(sql);
                rs = db.executeSelect();
                while(rs.next())
                {
                        Categorie categorie = new Categorie();
                        categorie.setCode(rs.getInt(1));
                        categorie.setLibelle(rs.getString(2));
                        categorie.setDescription (rs.getString(3));
                        categories.add(categorie);
                }
                rs.close();
                db.getCnx().close();
        }
        catch(Exception e)
        {
                throw new SQLException(e);
        }
        return categories;
	}

}
