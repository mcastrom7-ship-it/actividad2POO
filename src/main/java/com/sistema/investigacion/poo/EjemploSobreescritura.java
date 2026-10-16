package com.sistema.investigacion.poo;

/**
 * TEMA: SOBREESCRITURA DE MÉTODOS (METHOD OVERRIDING)
 * 
 * Concepto:
 * Ocurre cuando una subclase proporciona una implementación específica de un método
 * que ya ha sido definido en su superclase, conservando exactamente el mismo nombre,
 * tipo de retorno y lista de parámetros.
 * 
 * Se documenta con la anotación '@Override' y es el núcleo del polimorfismo dinámico
 * (en tiempo de ejecución).
 */

// Superclase
class Notificacion {
    public void enviar(String mensaje) {
        System.out.println("[NOTIFICACIÓN GENERAL]: Enviando mensaje estándar -> " + mensaje);
    }
}

// Subclase 1 que sobreescribe el método
class NotificacionEmail extends Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("[EMAIL - SMTP]: Enviando correo con formato HTML -> " + mensaje);
    }
}

// Subclase 2 que sobreescribe el método
class NotificacionSMS extends Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("[SMS - GSM Gateway]: Enviando mensaje corto de texto -> " + mensaje);
    }
}

public class EjemploSobreescritura {
    public static void ejecutarEjemplo() {
        System.out.println("--- DEMOSTRACIÓN DE SOBREESCRITURA DE MÉTODOS ---");
        // Demostración de Polimorfismo Dinámico
        Notificacion notif1 = new Notificacion();
        Notificacion notif2 = new NotificacionEmail();
        Notificacion notif3 = new NotificacionSMS();

        notif1.enviar("Mantenimiento programado");
        notif2.enviar("Su factura electrónica está lista");
        notif3.enviar("Su código de verificación OTP es 4892");
        System.out.println();
    }
}
