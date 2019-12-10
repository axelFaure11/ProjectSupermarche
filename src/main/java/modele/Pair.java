/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Axel
 */
public class Pair {
    
    Produit pr;
    int quantity;
    
    public Pair(Produit p, int q) {
        pr = p;
        quantity = q;
    }
    
    public Produit getKey(){
        return pr;
    }
    
    public int getQuantity(){
        return quantity;
    }
}
