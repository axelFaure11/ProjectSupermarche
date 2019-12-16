/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Axel
 */
public class ChartEntry {
    
    ArrayList<Value> c;

    public ChartEntry() {
        this.c = new ArrayList<Value>();
    }

    public ArrayList<Value> getC() {
        return c;
    }

    public void setC(ArrayList<Value> c) {
        this.c = c;
    }
    
}
