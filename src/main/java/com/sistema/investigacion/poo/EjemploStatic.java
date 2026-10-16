package com.sistema.investigacion.poo;

/**
 * TEMA: MODIFICADOR 'static' EN JAVA
 * 
 * Concepto:
 * El modificador 'static' indica que un miembro (variable o método) pertenece
 * a la CLASE en sí misma y no a ninguna instancia (objeto) individual.
 * 
 * Características clave:
 * 1. Variable static: existe una única copia en memoria compartida por todos los objetos.
 * 2. Método static: puede invocarse directamente con el nombre de la clase sin instanciar 'new'.
 * 3. No puede acceder directamente a variables de instancia ('this') desde un contexto estático.
 */
class CuentaBancaria {

    // Variable de instancia: cada cuenta tiene su propio saldo y titular
    private String titular;
    private double saldo;

    // Variable estática: compartida por TODAS las cuentas (contador global)
    public static int totalCuentasCreadas = 0;

    // Constante estática: compartida e inmutable
    public static final double TASA_INTERES_GLOBAL = 0.05; // 5%

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        // Cada vez que se crea una cuenta, incrementamos el contador global de la clase
        totalCuentasCreadas++;
    }

    // Método de instancia
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.printf("[%s] Depósito de $%.2f. Nuevo saldo: $%.2f%n", titular, monto, saldo);
        }
    }

    // Método estático de utilidad: no necesita una cuenta específica para operar
    public static double calcularRendimientoProyectado(double monto, int anios) {
        return monto * Math.pow(1 + TASA_INTERES_GLOBAL, anios);
    }
}

public class EjemploStatic {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DEL MODIFICADOR 'static' ---");
        System.out.println("Cuentas iniciales: " + CuentaBancaria.totalCuentasCreadas);

        CuentaBancaria c1 = new CuentaBancaria("Laura Pérez", 500_000.0);
        CuentaBancaria c2 = new CuentaBancaria("Juan Morales", 1_200_000.0);
        CuentaBancaria c3 = new CuentaBancaria("Sofía Castro", 800_000.0);

        // Acceso a la variable estática a través de la clase
        System.out.println("Total de cuentas creadas en el sistema: " + CuentaBancaria.totalCuentasCreadas);

        // Invocación de método estático sin instanciar ningún objeto especial
        double proyectado = CuentaBancaria.calcularRendimientoProyectado(1_000_000.0, 3);
        System.out.printf("Proyección estática de $1.000.000 a 3 años al %.0f%%: $%.2f%n",
                CuentaBancaria.TASA_INTERES_GLOBAL * 100, proyectado);
        System.out.println();
    }
}
