
package com.mycompany.parqueaderoproject;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Iterator;

public class ParqueaderoProject {
    
    public static void main(String[] args) {
        Gson gson = new Gson();
           // Cargar vehículos desde archivo si existe
        LinkedList<Vehiculo> todos = cargarListaDesdeArchivo();
        //LinkedList <Vehiculo> todos = new LinkedList<>();
        LinkedList <Vehiculo> vehiculosFuera = new LinkedList<>();
        
    
        
        //apenas se ejecute lo primero que va a hacer es cargar los objetos que estan en el archivo con la lista todos
        //por si se llegan a necesitar
        
        
        int valorHoraAuto = 5000;
        int valorHoraMoto = 2000;
        
        //_________________________________________________________________________________________________________________
        // Automoviles que estan en el parqueadero
        get("/Vehiculos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(todos);
        });
        
        
        //___________________________________________________________________________________________________________________
        // Listar vehiculos que salieron del parqueadero
        get("/vehiculosFuera", (req, res) -> {
            res.type("application/json");
            
            for (Vehiculo vehiculo : todos) {
                if(vehiculo.getHoraSalida() != 0){
                    vehiculosFuera.add(vehiculo);
                    guardarListaEnArchivo(vehiculosFuera);
                }
            }
            return gson.toJson(vehiculosFuera);
        });

        //_____________________________________________________________________________________________________________________
        // Guardar vehiculos
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            
             // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);

            Iterator<Vehiculo> iterator = todos.iterator();
            boolean placaExiste = false;

            while (iterator.hasNext()) {
                Vehiculo obj = iterator.next();

                    if (obj.getPlaca().equalsIgnoreCase(placa)) {
                        placaExiste = true;
                        break; // Terminar el bucle al encontrar la placa existente
                    }
            }

            if (placaExiste) {
                return "No se puede agregar el vehículo automóvil, la placa ya existe";
            } else {
                Vehiculo nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa, hour, 0);
                
                todos.add(nuevoAuto);
                guardarListaEnArchivo(todos);
                return gson.toJson(nuevoAuto);
            }     
        });
        
        get("/agregarMotocicleta/:marca/:modelo/:placa/:cilindrada", (req, res) -> {
            
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            
             // No olvidar convertir en integer los string numericos que llegan por url
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));

            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);

            Iterator<Vehiculo> iterator = todos.iterator();
            boolean placaExiste = false;

            while (iterator.hasNext()) {
                Vehiculo obj = iterator.next();

                    if (obj.getPlaca().equalsIgnoreCase(placa)) {
                        placaExiste = true;
                        break; // Terminar el bucle al encontrar la placa existente
                    }
            }

            if (placaExiste) {
                return "No se puede agregar el vehículo motocicleta, la placa ya existe";
            } else {
                Vehiculo nuevaMoto = new Motocicleta(cilindrada, marca, modelo, placa, hour, 0);
                todos.add(nuevaMoto);
                guardarListaEnArchivo(todos);
                return gson.toJson(nuevaMoto);
            }
        });
        
        //______________________________________________________________________________________________________________________
        //sacar vehiculos del parqueadero
        get("/sacarAutomovil/:placa", (req, res)->{
             res.type("application/json");
    
            String placa = req.params(":placa");

            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            boolean found = false;

            for (Vehiculo objeto : todos) {
                if (objeto instanceof Automovil) {
                    if (objeto.getPlaca().equalsIgnoreCase(placa)) {
                        objeto.setHoraSalida(hour);
                        found = true;
                        break; // Encontró el automóvil, sale del bucle
                    }
                }
            }

            if (found) {
                return gson.toJson("El automovil de la placa " + placa + " salio del parqueadero a la hora: " + hour);
            } else {
                return gson.toJson("La placa ingresada no esta en el parqueadero");
            }
        });

        get("/sacarMotocicleta/:placa", (req, res)->{
            res.type("application/json");
            
            String placa = req.params(":placa");
            
             Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            boolean found = false;
         
            for(Vehiculo objeto: todos){
                if (objeto instanceof Motocicleta) {
                    if(objeto.getPlaca().equalsIgnoreCase(placa)){
                        objeto.setHoraSalida(hour);
                        found = true;
                        break; // Encontró la motocicleta, sale del bucle
                    }
                }
            }
            
            if (found) {
                return gson.toJson("La motocicleta de la placa " + placa + " salio del parqueadero a la hora: " + hour);
            } else {
                return gson.toJson("La placa ingresada no esta en el parqueadero");
            }
        });
      
        //__________________________________________________________________________________________________________________________
        //reporte de las ganancias de los vehiculos y los que siguen en el parqueadero
        get("/reporteAutomoviles", (req, res) -> {
            res.type("application/json");
            String reporte = "  ";
            
            for (Vehiculo objeto : todos) {
                if(objeto instanceof Automovil){
                    if(objeto.getHoraSalida() != 0){
                        reporte +=
                                " Placa: "+ objeto.getPlaca() +
                                " Ingreso: " + objeto.getHoraIngreso() +
                                " Salida: " + objeto.getHoraSalida() +
                                " Ganancia: " + ( objeto.getHoraSalida() - objeto.getHoraIngreso()) * valorHoraAuto;
                    } else {
                        reporte +=
                                " Placa: "+ objeto.getPlaca() +
                                " Ingreso: " + objeto.getHoraIngreso() +
                                " Salida: " + objeto.getHoraSalida() +
                                " Ganancia: Aun en el parqueadero";
                    }
                }
            }
            
            return gson.toJson(reporte);
        });
        
        get("/reporteMotocicletas", (req, res) -> {
            res.type("application/json");
            String reporte = "  ";
            
            for (Vehiculo vehiculo : todos) {
                if(vehiculo instanceof Motocicleta){
                    if(vehiculo.getHoraSalida() != 0){
                        reporte +=
                                " Placa: "+ vehiculo.getPlaca() +
                                " Ingreso: " + vehiculo.getHoraIngreso() +
                                " Salida: " + vehiculo.getHoraSalida() +
                                " Ganancia: " + ( vehiculo.getHoraSalida() - vehiculo.getHoraIngreso()) * valorHoraMoto;
                    } else {
                        reporte +=
                                " Placa: "+ vehiculo.getPlaca() +
                                " Ingreso: " + vehiculo.getHoraIngreso() +
                                " Salida: " + vehiculo.getHoraSalida() +
                                " Ganancia: Aun esta en el parqueadero";
                    }
                }
            }
            
            return gson.toJson(reporte);
        });
        
    }
    static void guardarListaEnArchivo(LinkedList<Vehiculo> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EnParqueadero.obj"))) {
            oos.writeObject(lista);
            System.out.println("Lista guardada en ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static LinkedList<Vehiculo> cargarListaDesdeArchivo() {
        LinkedList<Vehiculo> lista = new LinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EnParqueadero.obj"))) {
            lista = (LinkedList<Vehiculo>) ois.readObject();
            System.out.println("Lista cargada desde " );
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
