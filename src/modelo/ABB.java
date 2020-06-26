/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.NoSuchElementException;

/**
 *
 * @author 57314
 */
public class ABB {

    private NodoBinario raiz;
    private int tamaño;

    public ABB() {
        raiz = null;
        tamaño = 0;
    }

    public void imprimir() {
        imprimir(raiz, "");
    }

    private void imprimir(NodoBinario r, String espacios) {
        if (r != null) {
            imprimir(r.hijoderecho, espacios + "   ");
            System.out.println(espacios + r.dato);
            imprimir(r.hijoizquierdo, espacios + "   ");
        }
    }

    public boolean agregar(int dato) {
        //verifica que la raiz tenga datos
        if (raiz == null) {
            //si no tiene, le asigna un dato
            raiz = new NodoBinario(dato);
            // se aumenta la cantidad de datos
            tamaño = 1;
            //acaba el metodo
            return true;
        } else {
            //iterador o indice
            NodoBinario actual = raiz;
            while (true) {
                if (dato < actual.dato) {//se decide si es hijoizquiedo o derecho
                    if (actual.hijoizquierdo == null) {// verifica que no tenga hijo izquierdo
                        actual.hijoizquierdo = new NodoBinario(dato);//le da un hijo izquierdo
                        tamaño++;// aumenta tamaño del arbol
                        return true;//termina el metodo
                    } else {
                        actual = actual.hijoizquierdo;
                    }

                } else if (dato > actual.dato) {
                    if (actual.hijoderecho == null) {
                        actual.hijoderecho = new NodoBinario(dato);//le da un hijo derecho
                        tamaño++;// aumenta tamaño del arbol
                        return true;//termina el metodo
                    } else {
                        actual = actual.hijoderecho;
                    }
                } else {
                    return false;
                }
            }
        }
    }
      public double ancestro(int e1, int e2) {
        if (e1 != raiz.dato && e2 != raiz.dato) {
            NodoBinario actual = raiz;
            NodoBinario padre = null;
            if (e1 > raiz.dato && e2 < raiz.dato || e1 < raiz.dato && e2 > raiz.dato) {
                return raiz.dato;
            } else {
                while (true) {
                    if (e1 > e2) {
                        if (actual.dato > e1) {
                            padre = actual;
                            actual = actual.hijoizquierdo;
                        } else if (actual.dato < e1) {
                            padre = actual;
                            actual = actual.hijoderecho;
                        } else {
                            return padre.dato;
                        }
                    } else if (e1 < e2) {
                        if (actual.dato > e2) {
                            padre = actual;
                            actual = actual.hijoizquierdo;
                        } else if (actual.dato < e2) {
                            padre = actual;
                            actual = actual.hijoderecho;
                        } else {
                            return padre.dato;
                        }
                    }
                }
            }
        }
        return 0;

    }
}