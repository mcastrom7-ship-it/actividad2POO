package com.sistema.investigacion.patrones;

/**
 * PATRÓN CREACIONAL: SINGLETON
 * 
 * Propósito (según Refactoring Guru):
 * Garantiza que una clase tenga una única instancia en toda la aplicación
 * y proporciona un punto de acceso global a dicha instancia.
 * 
 * Implementación:
 * 1. Constructor privado para evitar 'new' desde fuera.
 * 2. Atributo estático privado que almacena la única instancia.
 * 3. Método estático público de acceso (getInstance()).
 */
public class PatronSingleton {

    // Instancia única (lazy / perezosa o eagerly)
    private static PatronSingleton instancia;
    private String baseDatosUrl;

    // Constructor privado: nadie puede hacer 'new PatronSingleton()'
    private PatronSingleton() {
        this.baseDatosUrl = "jdbc:postgresql://servidor-empresa.com:5432/nomina_db";
        System.out.println(">> [SINGLETON]: Conexión única a la base de datos inicializada.");
    }

    // Punto de acceso global controlado
    public static synchronized PatronSingleton getInstancia() {
        if (instancia == null) {
            instancia = new PatronSingleton();
        }
        return instancia;
    }

    public void ejecutarConsulta(String sql) {
        System.out.println("Ejecutando en [" + baseDatosUrl + "]: " + sql);
    }

    public static void ejecutarEjemplo() {
        System.out.println("--- PATRÓN CREACIONAL: SINGLETON ---");
        // Intentamos obtener la instancia dos veces desde diferentes partes del sistema
        PatronSingleton conexion1 = PatronSingleton.getInstancia();
        PatronSingleton conexion2 = PatronSingleton.getInstancia();

        conexion1.ejecutarConsulta("SELECT * FROM empleados WHERE activo = true");

        // Verificamos si ambas variables apuntan a la MISMA referencia en memoria
        System.out.println("¿conexion1 y conexion2 son la misma instancia en memoria? -> " + (conexion1 == conexion2));
        System.out.println();
    }
}
