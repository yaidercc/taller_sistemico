/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lopez_cordoba;

import modelo.Banco;
import modelo.CuentaBancaria;

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

        ban.agregar(1, 2, "mar", "arb", "marc", 6);
        ban.agregar(2, 1, "juliana", "sala", "sabe", 10);
        ban.agregar(2, 1, "lui", "fe mosu", "lufe", 8);
        ban.agregar(1, 2, "luis", "fer mo", "lufa", 7);
        /* try {
            Banco cuenta = new Banco();
            cuenta.setRaiz(ban.CadenaDeBusqueda("ma"));
            cuenta.imprimir();
        } catch (Exception e) {
            System.err.println("no existe la cuenta");
        }*/
        ban.Consignaciones(8, 1000);
        ban.Retiros("marc", 6, 200);
        System.out.println("PROMEDIO: "+ban.promedioDineroMujeres());
        System.out.println("dinero total: "+ban.DineroTotal());
        ban.imprimir();
    }

}
