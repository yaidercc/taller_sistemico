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
    NodoArbolBalanceado raiz;

    public Banco() {
        raiz = null;
        tamaño = 0;
    }

    public void imprimir() {
        imprimir(raiz, "");
    }

    private void imprimir(NodoArbolBalanceado r, String espacios) {
        if (r != null) {
            imprimir(r.hijoderecho, espacios + "   ");
            System.out.println(espacios + r.codigo);
            imprimir(r.hijoizquierdo, espacios + "   ");
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
            raiz = new NodoArbolBalanceado(genero, tipocuenta, nombre, apellido, correo, codigo);
            tamaño = 1;
            return true;
        } else {
            NodoArbolBalanceado actual = raiz;

            while (true) {
                if (codigo < actual.codigo) {
                    if (actual.hijoizquierdo == null) {
                        actual.hijoizquierdo = new NodoArbolBalanceado(genero, tipocuenta, nombre, apellido, correo, codigo);
                        tamaño++;
                        return true;
                    } else {
                        actual = actual.hijoizquierdo;
                    }
                } else if (codigo > actual.codigo) {
                    if (actual.hijoderecho == null) {
                        actual.hijoderecho = new NodoArbolBalanceado(genero, tipocuenta, nombre, apellido, correo, codigo);
                        tamaño++;
                        return true;
                    } else {
                        actual = actual.hijoderecho;
                    }
                } else {// No se permiten datos repetidos
                    return false;
                }
            }// Fin del WHILE
        }
    }

    private void balancear(double codigo) {
        ArrayList<NodoArbolBalanceado> lista = camino(codigo);
        int indiceUltimo = lista.size() - 1;

        for (int i = indiceUltimo; i >= 0; i--) {
            NodoArbolBalanceado A = lista.get(i);
            A.actualizaraltura();

            NodoArbolBalanceado padreA = null;

            if (A != raiz) {
                padreA = lista.get(i - 1);
            }

            if (A.factorbalance() == -2) {
                if (A.hijoizquierdo.factorbalance() <= 0) {
                    balanceLL(A, padreA);
                } else {
                    balanceLR(A, padreA);
                }
            } else if (A.factorbalance() == 2) {
                // Ejercicio
                if (A.hijoderecho.factorbalance() >= 0) {
                    balanceRR(A, padreA);
                } else {
                    balanceRL(A, padreA);
                }
            }
        }// Fin del for
    }

    public ArrayList<NodoArbolBalanceado> camino(double codigo) {
        ArrayList<NodoArbolBalanceado> lista = new ArrayList<>();

        NodoArbolBalanceado actual = raiz;

        while (actual != null) {
            lista.add(actual);
            if (codigo < actual.codigo) {
                actual = actual.hijoizquierdo;
            } else if (codigo > actual.codigo) {
                actual = actual.hijoderecho;
            } else {
                break;
            }
        }

        if (actual == null) {
            lista.clear();
        }

        return lista;
    }

    private void balanceLL(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoderecho;

        if (A == raiz) {
            raiz = B;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = B;
        } else {
            padreA.hijoderecho = B;
        }

        A.hijoizquierdo = B.hijoderecho;
        B.hijoderecho = A;

        A.actualizaraltura();
        B.actualizaraltura();
    }

    private void balanceLR(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoizquierdo;
        NodoArbolBalanceado C = B.hijoderecho;

        if (A == raiz) {
            raiz = C;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = C;
        } else {
            padreA.hijoderecho = C;
        }

        A.hijoizquierdo = C.hijoderecho;
        B.hijoderecho = C.hijoizquierdo;
        C.hijoizquierdo = B;
        C.hijoderecho = A;

        A.actualizaraltura();
        B.actualizaraltura();
        C.actualizaraltura();
    }

    private void balanceRR(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoderecho;

        if (A == raiz) {
            raiz = B;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = B;
        } else {
            padreA.hijoderecho = B;
        }

        A.hijoderecho = B.hijoizquierdo;
        B.hijoizquierdo = A;
        A.actualizaraltura();
        B.actualizaraltura();
    }

    private void balanceRL(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoderecho;
        NodoArbolBalanceado C = B.hijoizquierdo;
        if (A == raiz) {
            raiz = C;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = C;
        } else {
            padreA.hijoizquierdo = C;
        }
        A.hijoderecho = C.hijoizquierdo;
        B.hijoizquierdo = C.hijoderecho;
        C.hijoizquierdo = A;
        C.hijoderecho = B;

        A.actualizaraltura();
        B.actualizaraltura();
        C.actualizaraltura();

    }

    public boolean eliminar(double codigo) {
        return eliminar(codigo, raiz, null);
    }

    private boolean eliminar(double codigo, NodoArbolBalanceado r, NodoArbolBalanceado n) {
        if (r != null) {
            if (r.codigo > codigo) {
                n = r;
                return eliminar(codigo, r.hijoizquierdo, n);
            } else if (r.codigo < codigo) {
                n = r;
                return eliminar(codigo, r.hijoderecho, n);
            } else {
                if (r == raiz) {
                    raiz = null;
                } else {
                    if (n.codigo > codigo) {
                        n.hijoizquierdo = null;
                    } else {
                        n.hijoderecho = null;
                    }
                }
            }
        }
        return false;
    }

    public double promedioDineroMujeres() {
        return promedioDineroMujeres(raiz, 0, 0);
    }

    private static double promedioDineroMujeres(NodoArbolBalanceado r, double dinero, int cantidad) {
        if (r != null) {
            if (r.genero == 1 && r.tipocuenta == 1) {
                dinero += r.saldo;
                return promedioDineroMujeres(r.hijoderecho, dinero, cantidad + 1) + promedioDineroMujeres(r.hijoizquierdo, dinero, cantidad + 1);
            } else {
                return promedioDineroMujeres(r.hijoderecho, dinero, cantidad + 1) + promedioDineroMujeres(r.hijoizquierdo, dinero, cantidad + 1);
            }
        } else {
            return dinero / cantidad;
        }
    }

    public double CadenaDeBusqueda(String cadena) {
        Banco aux = new Banco();
        return CadenaDeBusqueda(raiz,null, cadena, 0, 0, 0);
    }

    private double CadenaDeBusqueda(NodoArbolBalanceado r, NodoArbolBalanceado n, String cadena, int i, int j, int x) {
        String vector[] = {r.nombre, r.apellido, r.correo};
        if (r != null) {
            if (x < vector[i].length() && j < cadena.length()) {
                if (cadena.charAt(j) == vector[i].charAt(x)) {
                    return CadenaDeBusqueda(r, n, cadena, i, j + 1, x + 1);
                } else {
                    return CadenaDeBusqueda(r, n, cadena, i, j, x + 1);
                }
            } else {
                if (j == cadena.length()) {
                    return r.codigo;
                } else {
                    if (i < vector.length - 1) {
                        return CadenaDeBusqueda(r, n, cadena, i + 1, j = 0, x = 0);
                    } else {
                        return  (CadenaDeBusqueda(r.hijoderecho, n, cadena, i=0, j=0, x=0))+(CadenaDeBusqueda(r.hijoizquierdo, n, cadena, i, j, x));
                    }
                }
            }
        }
        return 0;
    }

}
