
package com.mycompany.parqueaderoproject;

import java.io.Serializable;

/**
 *
 * @author animu
 */
public class Vehiculo implements Serializable{
     private String marca;
    private String modelo;
    private String placa;
    private int horaIngreso;
    private int horaSalida;

    // Constructores, getters y setters

    // Otros métodos específicos si es necesario

    public Vehiculo(String marca, String modelo, String placa, int horaIngreso, int horaSalida) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(int horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }
}
