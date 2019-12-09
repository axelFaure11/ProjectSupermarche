/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
