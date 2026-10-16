package com.sistema.investigacion.poo;

/**
 * TEMA: HERENCIA EN PROGRAMACIÓN ORIENTADA A OBJETOS
 * 
 * Concepto:
 * Mecanismo que permite a una clase (subclase / clase hija) adquirir los atributos
 * y métodos de otra clase (superclase / clase padre), promoviendo la reutilización
 * de código y la creación de jerarquías lógicas.
 * Se implementa con la palabra reservada 'extends'.
 */

// Superclase
class Vehiculo {
    protected String marca;
    protected String modelo;

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void encender() {
        System.out.println("El vehículo " + marca + " " + modelo + " ha encendido su motor.");
    }
}

// Subclase que hereda de Vehiculo
class Automovil extends Vehiculo {
    private int numeroPuertas;

    public Automovil(String marca, String modelo, int numeroPuertas) {
        super(marca, modelo); // Invoca el constructor del padre
        this.numeroPuertas = numeroPuertas;
    }

    public void activarAireAcondicionado() {
        System.out.println("Aire acondicionado encendido en el automóvil de " + numeroPuertas + " puertas.");
    }
}

public class EjemploHerencia {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DE HERENCIA ---");
        Automovil miCarro = new Automovil("Mazda", "3", 4);
        miCarro.encender(); // Método heredado de Vehiculo
        miCarro.activarAireAcondicionado(); // Método propio de Automovil
        System.out.println();
    }
}
