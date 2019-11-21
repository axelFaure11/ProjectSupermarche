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
public class Client 
{

     
    private String code;
    private String societe;
    private String contact;
    private String fonction;
    private String adresse;
    private String tel;
    private String fax;          
    private String password;
    
    //getters
     public String getCode()
    {
        return code;
    }
    
    public String getSociete()
    {
        return societe;
    }
    
    public String getContact()
    {
        return contact;
    }
    
    public String getFonction()
    {
        return fonction;
    }
    
    public String getAdresse()
    {
        return adresse;
    }
    
        public String getTel()
    {
        return tel;
    }
        
        public String getFax()
    {
        return fax;
    }
        public String getPassword()
    { 
        return password;
    }
    
    //setters
    
    public void setCode(String Code)
    {
        this.code= code;
    }

    public void setSociete(String societe)
    {
        this.societe=societe;
    }
    
        public void setContact(String contact)
    {
        this.contact=contact;
    } 
    
       public void setFonction(String fonction)
    {
        this.fonction=fonction;
    }
       
    public void setAdresse(String adresse)
    {
        this.adresse=adresse;
    }
    
     public void setTel(String tel)
    {
        this.tel=tel;
    }

    public void setFax(String fax)
    {
        this.fax=fax;
    }
        
    public void setPassword(String password)
    {
        this.password=password;
    }
    
    
}
  
    