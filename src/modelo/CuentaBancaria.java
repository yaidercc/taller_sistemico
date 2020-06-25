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
public class CuentaBancaria {

    private double saldo;
    private int genero;
    private String correo;
    private String nombre;
    private String apellido;
    private double codigo;
    private int tipocuenta;
    private int altura;

    private CuentaBancaria hijoizquierdo;
    private CuentaBancaria hijoderecho;

    public CuentaBancaria(int genero, int tipocuenta, String nombre, String apellido, String correo, double codigo) {
        this.saldo = 12000;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }

    public int getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(int tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public CuentaBancaria getHijoizquierdo() {
        return hijoizquierdo;
    }

    public void setHijoizquierdo(CuentaBancaria hijoizquierdo) {
        this.hijoizquierdo = hijoizquierdo;
    }

    public CuentaBancaria getHijoderecho() {
        return hijoderecho;
    }

    public void setHijoderecho(CuentaBancaria hijoderecho) {
        this.hijoderecho = hijoderecho;
    }
    
    public boolean Retiros() {
        
        return true;
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
