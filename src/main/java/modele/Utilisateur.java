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
public class Utilisateur {
    
    private String userId;
    private String password;
    private String email;

    
    //getters
     public String getUserId()
    {
        return userId;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }
    

    
    
    
    //setters
    
    public void setUserId(String userId)
    {
        this.userId=userId;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }
    
        public void setEmail(String email)
    {
        this.email=email;
    } 
    
    
  
    
}
