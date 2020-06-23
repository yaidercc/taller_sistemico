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

    private void balanceLL(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoizquierdo;

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

    private void balanceRR(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoizquierdo;
        if (A == raiz) {
            B = raiz;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = B;
        } else {
            padreA.hijoderecho = B;
        }

        A.hijoderecho = B.hijoizquierdo;
        B.hijoderecho = A;
    }

    private void balanceRL(NodoArbolBalanceado A, NodoArbolBalanceado padreA) {
        NodoArbolBalanceado B = A.hijoderecho;
        NodoArbolBalanceado C = B.hijoizquierdo;
        if (A == raiz) {
            B = raiz;
        } else if (padreA.hijoizquierdo == A) {
            padreA.hijoizquierdo = C;
        } else {
            padreA.hijoderecho = C;
        }
        A.hijoderecho = C.hijoizquierdo;
        B.hijoderecho = C.hijoderecho;
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

    public int corriente() {
        return corriente(raiz);
    }

    private int corriente(NodoArbolBalanceado r) {
        if (r != null) {
            if (r.tipocuenta == 2) {
                System.out.println("coerrientes: " + r.codigo);
                return corriente(r.hijoderecho) + corriente(r.hijoizquierdo);
            } else {
                return corriente(r.hijoderecho) + corriente(r.hijoizquierdo);
            }
        } else {
            return 0;
        }
    }
}
