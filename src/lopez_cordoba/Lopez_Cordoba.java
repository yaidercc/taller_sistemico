/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lopez_cordoba;

import modelo.Banco;

/**
 *
 * @author 57314
 */
public class Lopez_Cordoba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Banco ban = new Banco();
       ban.agregar(2, 1, "yaider", "cordoba cardoba", "yaiderc19@gmail.com", 7894561);
       ban.agregar(2, 1, "cristian", "grisales benitez", "frisvi@gmail.com", 7985461);
       ban.agregar(2, 1, "hildefonso", "muñoz david", "jildo@gmail.com", 7995454);
       double n=7995454;
       ban.eliminar(n);
       ban.imprimir();
    }
    
}
