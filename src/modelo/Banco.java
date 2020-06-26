/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author 57314
 */
public class Banco {

    int tamaño;
    CuentaBancaria raiz;

    public Banco() {
        raiz = null;
        tamaño = 0;
    }

    public void imprimir() {
         imprimir(raiz, "");
    }

    private void imprimir(CuentaBancaria r, String espacios) {
        if (r != null) {
            if (r.getHijoizquierdo() != null) {
                imprimir(r.getHijoderecho(), espacios + "   ");

            }
            System.out.println(espacios + r.getCodigo());
            imprimir(r.getHijoizquierdo(), espacios + "   ");
        }

    }

    public boolean agregar(int genero, int tipocuenta, String nombre, String apellido, String correo, double codigo) {
        boolean inserto = insertar(genero, tipocuenta, nombre, apellido, correo, codigo);
        if (inserto) {
            balancear(codigo);
        }

        return inserto;
    }

    public boolean insertar(int genero, int tipocuenta, String nombre, String apellido, String correo, double codigo) {
        if (raiz == null) {
            raiz = new CuentaBancaria(genero, tipocuenta, nombre, apellido, correo, codigo);
            tamaño = 1;
            return true;
        } else {
            CuentaBancaria actual = raiz;

            while (true) {
                if (codigo < actual.getCodigo()) {
                    if (actual.getHijoizquierdo() == null) {
                        actual.setHijoizquierdo(new CuentaBancaria(genero, tipocuenta, nombre, apellido, correo, codigo));
                        tamaño++;
                        return true;
                    } else {
                        actual = actual.getHijoizquierdo();
                    }
                } else if (codigo > actual.getCodigo()) {
                    if (actual.getHijoderecho() == null) {
                        actual.setHijoderecho(new CuentaBancaria(genero, tipocuenta, nombre, apellido, correo, codigo));
                        tamaño++;
                        return true;
                    } else {
                        actual = actual.getHijoderecho();
                    }
                } else {// No se permiten datos repetidos
                    return false;
                }
            }// Fin del WHILE
        }
    }

    private void balancear(double codigo) {
        ArrayList<CuentaBancaria> lista = camino(codigo);
        int indiceUltimo = lista.size() - 1;

        for (int i = indiceUltimo; i >= 0; i--) {
            CuentaBancaria A = lista.get(i);
            A.actualizaraltura();

            CuentaBancaria padreA = null;

            if (A != raiz) {
                padreA = lista.get(i - 1);
            }

            if (A.factorbalance() == -2) {
                if (A.getHijoizquierdo().factorbalance() <= 0) {
                    balanceLL(A, padreA);
                } else {
                    balanceLR(A, padreA);
                }
            } else if (A.factorbalance() == 2) {
                // Ejercicio
                if (A.getHijoderecho().factorbalance() >= 0) {
                    balanceRR(A, padreA);
                } else {
                    balanceRL(A, padreA);
                }
            }
        }// Fin del for
    }

    public ArrayList<CuentaBancaria> camino(double codigo) {
        ArrayList<CuentaBancaria> lista = new ArrayList<>();

        CuentaBancaria actual = raiz;

        while (actual != null) {
            lista.add(actual);
            if (codigo < actual.getCodigo()) {
                actual = actual.getHijoizquierdo();
            } else if (codigo > actual.getCodigo()) {
                actual = actual.getHijoderecho();
            } else {
                break;
            }
        }

        if (actual == null) {
            lista.clear();
        }

        return lista;
    }

    private void balanceLL(CuentaBancaria A, CuentaBancaria padreA) {
        CuentaBancaria B = A.getHijoderecho();

        if (A == raiz) {
            raiz = B;
        } else if (padreA.getHijoizquierdo() == A) {
            padreA.setHijoizquierdo(B);
        } else {
            padreA.setHijoderecho(B);
        }

        A.setHijoderecho(B.getHijoderecho());
        B.setHijoderecho(A);

        A.actualizaraltura();
        B.actualizaraltura();
    }

    private void balanceLR(CuentaBancaria A, CuentaBancaria padreA) {
        CuentaBancaria B = A.getHijoizquierdo();
        CuentaBancaria C = B.getHijoderecho();

        if (A == raiz) {
            raiz = C;
        } else if (padreA.getHijoizquierdo() == A) {
            padreA.setHijoizquierdo(C);
        } else {
            padreA.setHijoderecho(C);
        }

        A.setHijoizquierdo(C.getHijoderecho());
        B.setHijoderecho(C.getHijoizquierdo());
        C.setHijoizquierdo(B);
        C.setHijoderecho(A);

        A.actualizaraltura();
        B.actualizaraltura();
        C.actualizaraltura();
    }

    private void balanceRR(CuentaBancaria A, CuentaBancaria padreA) {
        CuentaBancaria B = A.getHijoizquierdo();

        if (A == raiz) {
            raiz = B;
        } else if (padreA.getHijoizquierdo() == A) {
            padreA.setHijoizquierdo(B);
        } else {
            padreA.setHijoderecho(B);
        }

        A.setHijoizquierdo(B.getHijoizquierdo());
        B.setHijoizquierdo(A);
        A.actualizaraltura();
        B.actualizaraltura();
    }

    private void balanceRL(CuentaBancaria A, CuentaBancaria padreA) {
        CuentaBancaria B = A.getHijoderecho();
        CuentaBancaria C = B.getHijoizquierdo();
        if (A == raiz) {
            raiz = C;
        } else if (padreA.getHijoizquierdo() == A) {
            padreA.setHijoizquierdo(C);
        } else {
            padreA.setHijoizquierdo(C);
        }
        A.setHijoderecho(C.getHijoizquierdo());
        B.setHijoizquierdo(C.getHijoderecho());
        C.setHijoizquierdo(A);
        C.setHijoderecho(B);

        A.actualizaraltura();
        B.actualizaraltura();
        C.actualizaraltura();

    }

    public boolean eliminar(double codigo) {
        return eliminar(codigo, raiz, null);
    }

    private boolean eliminar(double codigo, CuentaBancaria r, CuentaBancaria n) {
        if (r != null) {
            if (r.getCodigo() > codigo) {
                n = r;
                return eliminar(codigo, r.getHijoizquierdo(), n);
            } else if (r.getCodigo() < codigo) {
                n = r;
                return eliminar(codigo, r.getHijoderecho(), n);
            } else {
                if (r == raiz) {
                    raiz = null;
                } else {
                    if (n.getCodigo() > codigo) {
                        n.setHijoizquierdo(null);
                    } else {
                        n.setHijoizquierdo(null);
                    }
                }
            }
        }
        return false;
    }

    public double promedioDineroMujeres() {
        return promedioDineroMujeres(raiz, 0, 0);
    }

    private static double promedioDineroMujeres(CuentaBancaria r, double dinero, int cantidad) {
        if (r != null) {
            if (r.getGenero() == 1 && r.getTipocuenta() == 1) {
                dinero += r.getSaldo();
                return promedioDineroMujeres(r.getHijoderecho(), dinero, cantidad + 1) + promedioDineroMujeres(r.getHijoizquierdo(), dinero, cantidad + 1);
            } else {
                return promedioDineroMujeres(r.getHijoizquierdo(), dinero, cantidad + 1) + promedioDineroMujeres(r.getHijoizquierdo(), dinero, cantidad + 1);
            }
        } else {
            return dinero / cantidad;
        }
    }

    public CuentaBancaria CadenaDeBusqueda(String cadena) {
        Banco cuenta = new Banco();
        return CadenaDeBusqueda(raiz, cadena, cuenta);
    }

    private CuentaBancaria CadenaDeBusqueda(CuentaBancaria r, String cadena, Banco cuenta) {
        if (r != null) {
            CadenaDeBusqueda(r.getHijoizquierdo(), cadena, cuenta);
            if (r.getNombre().equalsIgnoreCase(cadena) || r.getApellido().equalsIgnoreCase(cadena) || r.getCorreo().equalsIgnoreCase(cadena)) {
                cuenta.agregar(r.getGenero(), r.getTipocuenta(), r.getNombre(), r.getApellido(), r.getCorreo(), r.getCodigo());
                return cuenta.raiz;
            }
            CadenaDeBusqueda(r.getHijoderecho(), cadena, cuenta);
        }
        return cuenta.raiz;
    }

    public boolean Consignaciones(double cuenta, double dinero) {
        return Consignaciones(raiz, cuenta, dinero);
    }

    private boolean Consignaciones(CuentaBancaria r, double cuenta, double dinero) {
        if (r != null) {
            if (r.getCodigo() > cuenta) {
                return Consignaciones(r.getHijoizquierdo(), cuenta, dinero);
            } else if (r.getCodigo() < cuenta) {
                return Consignaciones(r.getHijoizquierdo(), cuenta, dinero);
            } else {
                r.setSaldo(r.getSaldo() + dinero);
                System.out.println("saldo actual: " + r.getSaldo());
                return true;
            }
        }
        return false;
    }

    public boolean Retiros(String correo, double Nrocuenta, double dinero) {
        return Retiros(raiz, correo, Nrocuenta, dinero);
    }

    private boolean Retiros(CuentaBancaria r, String correo, double Nrocuenta, double dinero) {
        if (r != null) {
            if (r.getCodigo() > Nrocuenta) {
                return Retiros(r.getHijoizquierdo(), correo, Nrocuenta, dinero);
            } else if (r.getCodigo() < Nrocuenta) {
                return Retiros(r.getHijoderecho(), correo, Nrocuenta, dinero);
            } else {
                if (correo.equals(r.getCorreo()) && dinero <= r.getSaldo()) {
                    r.setSaldo(r.getSaldo() - dinero);
                    System.out.println("transaccion exitosa!");
                    System.out.println("saldo actual: " + r.getSaldo());
                    return true;
                } else {
                    System.out.println("algo salio mal!");
                    return false;
                }
            }
        }
        return false;
    }
    public CuentaBancaria retornarCorrientes(){
        Banco lista = new Banco();        
        return retornarCorrientes(raiz, lista);
    }
    private CuentaBancaria retornarCorrientes(CuentaBancaria r, Banco lista){        
        if (r != null) {
            retornarCorrientes(r.getHijoizquierdo(), lista);
            if (r.getTipocuenta() == 2) {
                lista.agregar(r.getGenero(), r.getTipocuenta(), r.getNombre(), r.getApellido(), r.getCorreo(), r.getCodigo());
            }
            retornarCorrientes(r.getHijoderecho(), lista);
        }
        return lista.raiz;
    }
    public double DineroTotal(){
        return DineroTotal(raiz,0);
    }
    
    private static double DineroTotal(CuentaBancaria r,double suma){
        if(r!=null){
            return DineroTotal(r.getHijoizquierdo(),suma+=r.getSaldo())+DineroTotal(r.getHijoderecho(), suma+=r.getSaldo());
        }
        return suma;
    }

}
