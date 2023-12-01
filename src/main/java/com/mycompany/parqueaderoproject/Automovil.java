
package com.mycompany.parqueaderoproject;

/**
 *
 * @author animu
 */
public class Automovil extends Vehiculo{
     private int numeroPuertas;
    // Constructores, getters y setters

    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int horaIngreso, int horaSalida) {
        super(marca, modelo, placa, horaIngreso, horaSalida);
        this.numeroPuertas = numeroPuertas;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
}
