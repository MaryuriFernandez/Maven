
package com.mycompany.parqueaderoproject;

/**
 *
 * @author animu
 */
public class Motocicleta extends Vehiculo{
     private int cilindrada;
    // Constructores, getters y setters

    public Motocicleta(int cilindrada, String marca, String modelo, String placa, int horaIngreso, int horaSalida) {
        super(marca, modelo, placa, horaIngreso, horaSalida);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
