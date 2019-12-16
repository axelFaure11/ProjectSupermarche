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
public class Value<E> {
    
    E v;

    public Value(E value) {
        this.v = value;
    }

    public E getValue() {
        return v;
    }

    public void setValue(E value) {
        this.v = value;
    }
    
    
}
