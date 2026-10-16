package com.sistema.investigacion.poo;

/**
 * TEMA: INTERFACES EN JAVA
 * 
 * Concepto:
 * Una interfaz es un contrato estricto que especifica un conjunto de métodos
 * que una clase debe implementar, desacoplando la definición del comportamiento
 * de su implementación concreta.
 * 
 * En Java, una clase solo puede heredar de una superclase ('single inheritance'),
 * pero puede implementar múltiples interfaces ('multiple interface inheritance').
 */

// Interfaz 1: Contrato para exportar datos
interface Exportable {
    String exportarAJson();
    String exportarACsv();
}

// Interfaz 2: Contrato para persistencia
interface Persistente {
    void guardarEnBaseDatos();
}

// Clase que implementa múltiples interfaces
class Producto implements Exportable, Persistente {
    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String exportarAJson() {
        return String.format("{\"codigo\": \"%s\", \"nombre\": \"%s\", \"precio\": %.2f}",
                codigo, nombre, precio);
    }

    @Override
    public String exportarACsv() {
        return String.format("%s;%s;%.2f", codigo, nombre, precio);
    }

    @Override
    public void guardarEnBaseDatos() {
        System.out.println("Guardando producto [" + nombre + "] en tabla 'productos'...");
    }
}

public class EjemploInterface {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DE INTERFACES ---");
        Producto laptop = new Producto("P-101", "Portátil Dell Inspiron", 3_200_000.0);

        laptop.guardarEnBaseDatos();
        System.out.println("JSON: " + laptop.exportarAJson());
        System.out.println("CSV : " + laptop.exportarACsv());
        System.out.println();
    }
}
