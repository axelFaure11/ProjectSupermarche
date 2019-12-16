package modele.DAO;

import java.util.List;
import modele.Admin;

/**
 *
 * @author rebecca
 */
public interface AdminDao 
{
    public int addAdmin (Admin admin);
    public int updateAdmin(Admin admin);
    public int deleteAdmin(String adminId);
    public int getClient(String adminId);
    public List<Admin> liste();
}
