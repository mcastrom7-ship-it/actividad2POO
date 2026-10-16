package com.sistema.investigacion.app;

import com.sistema.investigacion.patrones.PatronAdapter;
import com.sistema.investigacion.patrones.PatronSingleton;
import com.sistema.investigacion.patrones.PatronStrategy;
import com.sistema.investigacion.poo.*;

/**
 * Clase ejecutora que demuestra en consola todos los temas del PUNTO 2:
 * 1. Herencia
 * 2. Sobrecarga de Métodos
 * 3. Sobreescritura de Métodos
 * 4. Clases Abstractas
 * 5. Interfaces en Java
 * 6. Modificador 'static'
 * 7. Patrones de Diseño (Singleton, Adapter, Strategy)
 */
public class DemoPuntoDos {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("  PUNTO 2: INVESTIGACIÓN Y DEMOSTRACIÓN DE POO Y PATRONES");
        System.out.println("==================================================================\n");

        // 1. Herencia
        EjemploHerencia.ejecutarEjemplo();

        // 2. Sobrecarga
        EjemploSobrecarga.ejecutarEjemplo();

        // 3. Sobreescritura
        EjemploSobreescritura.ejecutarEjemplo();

        // 4. Clases Abstractas
        EjemploClaseAbstracta.ejecutarEjemplo();

        // 5. Interfaces
        EjemploInterface.ejecutarEjemplo();

        // 6. Modificador static
        EjemploStatic.ejecutarEjemplo();

        // 7. Patrones de Diseño
        PatronSingleton.ejecutarEjemplo();
        PatronAdapter.ejecutarEjemplo();
        PatronStrategy.ejecutarEjemplo();

        System.out.println("==================================================================");
        System.out.println("  ✓ TODOS LOS EJEMPLOS DEL PUNTO 2 SE EJECUTARON CON ÉXITO");
        System.out.println("==================================================================");
    }
}
