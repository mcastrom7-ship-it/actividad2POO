package com.sistema.investigacion.poo;

/**
 * TEMA: SOBRECARGA DE MÉTODOS (METHOD OVERLOADING)
 * 
 * Concepto:
 * Ocurre cuando dos o más métodos dentro de la misma clase comparten el mismo
 * nombre, pero difieren en la cantidad, orden o tipos de sus parámetros.
 * Es un tipo de polimorfismo en tiempo de compilación (estático).
 */
class CalculadoraFinanciera {

    // 1. Sobrecarga: Sumar dos números enteros
    public int sumar(int a, int b) {
        return a + b;
    }

    // 2. Sobrecarga: Sumar tres números enteros
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    // 3. Sobrecarga: Sumar dos números decimales
    public double sumar(double a, double b) {
        return a + b;
    }

    // 4. Sobrecarga: Calcular interés con o sin plazo en meses
    public double calcularInteres(double capital, double tasa) {
        return capital * (tasa / 100.0);
    }

    public double calcularInteres(double capital, double tasa, int meses) {
        return (capital * (tasa / 100.0) * meses) / 12.0;
    }
}

public class EjemploSobrecarga {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DE SOBRECARGA DE MÉTODOS ---");
        CalculadoraFinanciera calc = new CalculadoraFinanciera();

        System.out.println("sumar(5, 10)                 = " + calc.sumar(5, 10));
        System.out.println("sumar(5, 10, 20)             = " + calc.sumar(5, 10, 20));
        System.out.println("sumar(15.5, 4.3)             = " + calc.sumar(15.5, 4.3));
        System.out.println("calcularInteres($1M, 2%)     = $" + calc.calcularInteres(1_000_000, 2.0));
        System.out.println("calcularInteres($1M, 2%, 6m) = $" + calc.calcularInteres(1_000_000, 2.0, 6));
        System.out.println();
    }
}
