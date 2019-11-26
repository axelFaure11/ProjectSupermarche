/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import modele.Panier;

/**
 *
 * @author rebecca
 */
public interface PanierDao 
{
    void modifierPanier (Panier panier);
    void validerPanier (Panier panier);
    void supprimerPanier (Panier panier);
}
