package modele;

/**
 *
 * @author rebecca
 */
public class Utilisateur {
    
    private String userId;
    private String password;
    private String email;
    private String role;
 
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
     public String getRole()
    {
        return role;
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
        public void setRole(String role)
    {
        this.role=role;
    } 
    
    
     
    public boolean isAdmin()
    {
        return this.role == "administrateur";
    }
    
    
}
