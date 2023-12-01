
package com.mycompany.parqueaderoproject;

/**
 *
 * @author Maryuri Fernandez Salazar
 */
public class Motocicleta extends Vehiculo{
     private int cilindrada;

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
