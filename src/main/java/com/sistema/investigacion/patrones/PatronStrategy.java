package com.sistema.investigacion.patrones;

/**
 * PATRÓN DE COMPORTAMIENTO: STRATEGY (ESTRATEGIA)
 * 
 * Propósito (según Refactoring Guru):
 * Permite definir una familia de algoritmos, colocar cada uno de ellos en una clase
 * separada y hacer sus objetos intercambiables dinámicamente en tiempo de ejecución.
 */

// 1. Interfaz común para todas las estrategias
interface EstrategiaDescuento {
    double aplicarDescuento(double montoTotal);
    String getNombreEstrategia();
}

// 2. Estrategia Concreta A: Sin descuento
class DescuentoRegular implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double montoTotal) {
        return montoTotal; // 0% de descuento
    }

    @Override
    public String getNombreEstrategia() {
        return "Precio regular (0% descuento)";
    }
}

// 3. Estrategia Concreta B: Descuento Temporada / Black Friday (30%)
class DescuentoBlackFriday implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double montoTotal) {
        return montoTotal * 0.70; // 30% de descuento
    }

    @Override
    public String getNombreEstrategia() {
        return "Promoción Black Friday (30% descuento)";
    }
}

// 4. Estrategia Concreta C: Descuento Cliente VIP (15%)
class DescuentoClienteVIP implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double montoTotal) {
        return montoTotal * 0.85; // 15% de descuento
    }

    @Override
    public String getNombreEstrategia() {
        return "Membresía VIP (15% descuento)";
    }
}

// 5. Contexto: Quien usa la estrategia
class CarritoCompras {
    private EstrategiaDescuento estrategia;

    public CarritoCompras(EstrategiaDescuento estrategiaInicial) {
        this.estrategia = estrategiaInicial;
    }

    // Permite cambiar la estrategia en caliente (en tiempo de ejecución)
    public void setEstrategia(EstrategiaDescuento nuevaEstrategia) {
        this.estrategia = nuevaEstrategia;
    }

    public void liquidarCompra(double total) {
        double totalFinal = estrategia.aplicarDescuento(total);
        System.out.printf("[STRATEGY - %s]%n  Subtotal: $%.2f -> Total a Pagar: $%.2f%n",
                estrategia.getNombreEstrategia(), total, totalFinal);
    }
}

public class PatronStrategy {
    public static void ejecutarEjemplo() {
        System.out.println("--- PATRÓN DE COMPORTAMIENTO: STRATEGY ---");
        double compra = 500_000.0;

        CarritoCompras carrito = new CarritoCompras(new DescuentoRegular());
        carrito.liquidarCompra(compra);

        // Cambiamos a la estrategia VIP dinámicamente
        carrito.setEstrategia(new DescuentoClienteVIP());
        carrito.liquidarCompra(compra);

        // Cambiamos a la estrategia Black Friday
        carrito.setEstrategia(new DescuentoBlackFriday());
        carrito.liquidarCompra(compra);
        System.out.println();
    }
}
