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
        // 
        ban.agregar(2, 1, "yaider", "cordoba cardoba", "yaiderc19@gmail.com", 1);
        ban.agregar(2, 2, "cristian", "grisales benitez", "frisvi@gmail.com", 3);
        ban.agregar(2, 2, "hildefonso", "muñoz david", "jildo@gmail.com", 5);
        ban.agregar(1, 2, "marcela", "arboleda arbelaez", "marcearbe@gmail.com", 6);
        ban.agregar(1, 2, "juliana", "salazar bedoya", "sabedoya@gmail.com", 7);
        ban.agregar(1, 2, "luisa", "fernanda moscu", "lufemos@gmail.com", 8);
        try {
            ban.CadenaDeBusqueda("juli");
        } catch (Exception e) {
            System.err.println("algo sucedio");
        }
        ban.imprimir();

    }

}
