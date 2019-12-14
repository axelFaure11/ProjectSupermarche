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
    private String ville;
    private String region;
    private String codePostal;
    private String pays;
    private String tel;
    private String fax;

    public Client() {
    }

    public Client(String code, String societe, String contact, String fonction, String adresse, String ville, String region, String codePostal, String pays, String tel, String fax) {
        this.code = code;
        this.societe = societe;
        this.contact = contact;
        this.fonction = fonction;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.codePostal = codePostal;
        this.pays = pays;
        this.tel = tel;
        this.fax = fax;
    }
    
    
    
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
    
    
    public String getVille()
    {
        return ville;
    }    
         
    public String getRegion()
    {
        return region;
    }
    
    public String getCodePostal()
    {
        return codePostal;
    }
    
    public String getPays()
    {
        return pays;
    }
    
    public String getTel()
    {
        return tel;
    }
        
    public String getFax()
    {
        return fax;
    }

    
    //setters
    
    public void setCode(String code)
    {
        this.code=code;
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
    
    public void setVille(String ville)
    {
        this.ville=ville;
    }
    
    public void setRegion(String region)
    {
        this.region=region;
    }
    
    public void setCodePostal(String codePostal)
    {
        this.codePostal=codePostal;
    }
    
    public void setPays(String pays)
    {
        this.pays=pays;
    }
     public void setTel(String tel)
    {
        this.tel=tel;
    }

    public void setFax(String fax)
    {
        this.fax=fax;
    }

}
  
    
