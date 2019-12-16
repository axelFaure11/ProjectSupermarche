package modele.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Admin;

/**
 *
 * @author rebecca
 */
public class AdminDaoImpl {
    
    private DB db = new DB();
	private int ok;
	private ResultSet rs;
              
    public int addAdmin(Admin admin) throws SQLException {     
        String sql= "INSERT INTO Admin VALUES(?,?,?) ";
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, admin.getAdminId());
            db.getPstm().setString(2, admin.getUsername());
            db.getPstm().setString(3, admin.getPassword());
         
            // Execution de la requete
            ok=db.executeMaj();
        
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    public int updateAdmin (Admin admin ) throws SQLException{
        String sql="UPDATE admin SET adminId=?, username=?, password=? ";
        ok=0;
        try
        {
            //Initialisation de la requete
            db.initPrepare(sql);
            // Passage des valeurs
            db.getPstm().setString(1, admin.getAdminId());
            db.getPstm().setString(2, admin.getUsername());
            db.getPstm().setString(3, admin.getPassword());

        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return ok;
    }
    
    
    public int deleteAdmin(String adminId) throws SQLException {
        String sql="DELETE FROM admin WHERE adminId=?";
        ok=0;
        try
        {
            // Initialisation de la requete
            db.initPrepare(sql);
            // Passage de valeurs
            db.getPstm().setString(1, adminId);
            ok=db.executeMaj();
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
        return ok;     
    }
       
    
        public Admin getAdmin(String adminId) throws SQLException {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE adminId = ?";
        try 
        {
            db.initPrepare(sql);
            db.getPstm().setString(1, adminId);
            rs=db.executeSelect();
            if (rs.next())
            {
                admin=new Admin();
                admin.setAdminId(rs.getString(1));
                admin.setUsername(rs.getString(2));
                admin.setPassword(rs.getString(3));               
            }
        }
            catch(Exception e)
        {
            throw new SQLException(e);
        }
        return admin;
        }
    

    public List<Admin> liste() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql= "SELECT * FROM admin";
        try 
        {
            db.initPrepare(sql);
            rs= db.executeSelect();
            while(rs.next())
            {
                Admin admin = new Admin();
                admin.setAdminId(rs.getString(1));
                admin.setUsername(rs.getString(2));
                admin.setPassword(rs.getString(3));               
                admins.add(admin);
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e);
        }
        return admins;
    }
    
}

