/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Axel
 */
public class tempPanier extends ArrayList<Pair<Produit, Integer>> {
    
    public double prixTotal;
    
    public tempPanier() {
        super();
    }
    
    public void updatePrixTotal(double val){
        this.prixTotal = this.prixTotal + val;
    }
    
    public void viderPanier(){
        this.clear();
        this.prixTotal = 0;
    }
    
    public double getPrixTotal(){
        return prixTotal;
    }
    
}
