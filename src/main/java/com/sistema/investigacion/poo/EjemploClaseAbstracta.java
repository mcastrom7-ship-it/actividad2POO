package com.sistema.investigacion.poo;

/**
 * TEMA: CLASES ABSTRACTAS EN JAVA
 * 
 * Concepto:
 * Una clase abstracta es una clase que no puede ser instanciada directamente con 'new'.
 * Actúa como una plantilla o molde para otras clases.
 * Puede contener:
 * - Métodos abstractos (declaraciones sin cuerpo que las subclases DEBEN implementar).
 * - Métodos concretos (con código ya implementado y reutilizable).
 * - Atributos de estado y constructores.
 */

// Clase abstracta base
abstract class FiguraGeometrica {
    protected String color;

    public FiguraGeometrica(String color) {
        this.color = color;
    }

    // Método abstracto obligatorio
    public abstract double calcularArea();

    // Método abstracto obligatorio
    public abstract double calcularPerimetro();

    // Método concreto reutilizable por cualquier figura
    public void mostrarInfo() {
        System.out.printf("Figura de color %s | Área: %.2f | Perímetro: %.2f%n",
                color, calcularArea(), calcularPerimetro());
    }
}

// Subclase concreta Círculo
class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo(String color, double radio) {
        super(color);
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}

// Subclase concreta Rectángulo
class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo(String color, double base, double altura) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }
}

public class EjemploClaseAbstracta {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DE CLASES ABSTRACTAS ---");
        // FiguraGeometrica fig = new FiguraGeometrica("Rojo"); // ERROR: No se puede instanciar

        FiguraGeometrica circulo = new Circulo("Azul", 5.0);
        FiguraGeometrica rectangulo = new Rectangulo("Verde", 4.0, 6.0);

        circulo.mostrarInfo();
        rectangulo.mostrarInfo();
        System.out.println();
    }
}
