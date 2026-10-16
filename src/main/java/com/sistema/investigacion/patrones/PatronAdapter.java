package com.sistema.investigacion.patrones;

/**
 * PATRÓN ESTRUCTURAL: ADAPTER (ADAPTADOR)
 * 
 * Propósito (según Refactoring Guru):
 * Permite que objetos con interfaces incompatibles puedan colaborar entre sí,
 * actuando como un intermediario o conversor entre dos sistemas.
 */

// 1. Interfaz esperada por el cliente moderno
interface ProcesadorPago {
    void procesarPago(double montoPesos);
}

// 2. Servicio externo / heredado incompatible (Adaptee)
class PasarelaPagoAntigua {
    public void cobrarEnCentavos(long totalCentavos) {
        System.out.println(">> [PASARELA ANTIGUA]: Cobrando " + totalCentavos + " centavos a través de API SOAP legado.");
    }
}

// 3. El Adaptador: implementa la interfaz moderna y traduce las peticiones a la clase antigua
class AdaptadorPasarela implements ProcesadorPago {
    private final PasarelaPagoAntigua pasarelaAntigua;

    public AdaptadorPasarela(PasarelaPagoAntigua pasarelaAntigua) {
        this.pasarelaAntigua = pasarelaAntigua;
    }

    @Override
    public void procesarPago(double montoPesos) {
        // Conversión de pesos a centavos requerida por el servicio legado
        long centavos = (long) (montoPesos * 100);
        System.out.printf("[ADAPTER]: Traduciendo pago de $%.2f pesos a %d centavos...%n", montoPesos, centavos);
        pasarelaAntigua.cobrarEnCentavos(centavos);
    }
}

public class PatronAdapter {
    public static void ejecutarEjemplo() {
        System.out.println("--- PATRÓN ESTRUCTURAL: ADAPTER ---");
        // El cliente usa la interfaz ProcesadorPago sin saber cómo funciona internamente la pasarela antigua
        PasarelaPagoAntigua sistemaAntiguo = new PasarelaPagoAntigua();
        ProcesadorPago procesador = new AdaptadorPasarela(sistemaAntiguo);

        procesador.procesarPago(150_000.0);
        System.out.println();
    }
}
