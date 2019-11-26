/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import modele.Categorie;

/**
 *
 * @author rebecca
 */
public interface CategorieDao 
{
    public void ajouterCategorie (Categorie categorie);
    public void supprimerCategorie (Categorie categorie);
    public void modifierCategorie (Categorie categorie);
    
}
