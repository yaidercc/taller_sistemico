/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 57314
 */
public class NodoArbolBalanceado {

    double saldo;
    int genero;
    String correo;
    String nombre;
    String apellido;
    double codigo;
    int tipocuenta;
    int altura;

    NodoArbolBalanceado hijoizquierdo;
    NodoArbolBalanceado hijoderecho;

    public NodoArbolBalanceado(int genero, int tipocuenta, String nombre, String apellido, String correo,double codigo) {
        this.saldo = 0;
        this.codigo = codigo;
        this.genero = genero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tipocuenta = tipocuenta;
        hijoderecho = null;
        hijoizquierdo = null;
        altura = 1;
    }

    public boolean EsHoja() {
        return hijoizquierdo == null && hijoderecho == null;
    }

    public void actualizaraltura() {
        if (EsHoja()) {
            altura = 1;
        } else if (hijoderecho == null) {
            altura = 1 + hijoizquierdo.altura;
        } else if (hijoizquierdo == null) {
            altura = 1 + hijoderecho.altura;
        } else {
            altura = 1 + Math.max(hijoderecho.altura, hijoizquierdo.altura);
        }

    }
    //factor de balance

    public int factorbalance() {
        if (EsHoja()) {
            return 0;
        } else if (hijoderecho == null) {
            return -hijoizquierdo.altura;
        } else if (hijoizquierdo == null) {
            return hijoderecho.altura;
        } else {
            return hijoderecho.altura - hijoizquierdo.altura;

        }

    }
}
