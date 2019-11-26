/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;
import java.util.List;
import modele.Produit;
/**
 *
 * @author rebecca
 */
public interface ProduitDao        
{
    public List<Produit> getAll();
    void ajouterProduit (Produit produit);
    void supprimerProduit (int ref);
    void modifierProduit (Produit produit);
}
