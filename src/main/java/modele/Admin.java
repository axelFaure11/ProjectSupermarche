/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author rebecca
 */
public class Admin {
    
    private String adminId;
    private String username;
    private String password;
    
    //getters
    public String getAdminId()
    {
        return adminId;
    }
    public String getUsername()
    {
        return username;
    }
    
        public String getPassword()
    {
        return password;
    }
        
    //setters
        
    public void setAdminId(String adminId)
    {
        this.adminId=adminId;
    }
    
        public void setUsername(String username)
    {
        this.username=username;
    }
    
   
    public void setPassword(String password)
    {
        this.password=password;
    }
    
}
