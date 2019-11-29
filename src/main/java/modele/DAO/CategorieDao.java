/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import java.util.List;
import modele.Categorie;

/**
 *
 * @author rebecca
 */
public interface CategorieDao 
{
    public int addCategorie (Categorie categorie);
    public int deleteCategorie (int code);
    public int updateCategorie (Categorie categorie);
    public Categorie get(int code);
    public List<Categorie> liste();
    
    
}
