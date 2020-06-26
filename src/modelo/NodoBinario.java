/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 
 */
public class NodoBinario {
    NodoBinario hijoizquierdo;
    NodoBinario hijoderecho;
    int dato;

    public NodoBinario(int dato) {
        this.dato = dato;
        hijoizquierdo = null;
        hijoderecho = null;
    }

    public NodoBinario getHijoizquierdo() {
        return hijoizquierdo;
    }

    public void setHijoizquierdo(NodoBinario hijoizquierdo) {
        this.hijoizquierdo = hijoizquierdo;
    }

    public NodoBinario getHijoderecho() {
        return hijoderecho;
    }

    public void setHijoderecho(NodoBinario hijoderecho) {
        this.hijoderecho = hijoderecho;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
    

    public boolean esHoja() {
        return hijoizquierdo == null && hijoderecho == null;//genera true o false
    }

}
