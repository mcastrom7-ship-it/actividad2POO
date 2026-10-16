package com.sistema.empleados.app;

import com.sistema.empleados.modelo.*;
import com.sistema.empleados.servicio.GestorNomina;

import java.util.Scanner;

/**
 * Clase principal de ejecución del proyecto.
 * 
 * Contiene:
 * 1. Casos de prueba preconfigurados que validan todas las reglas de negocio
 *    exigidas en la actividad (bono antigüedad >5 años, recargo 50% en horas >40,
 *    bono 5% por ventas >$2.000.000, temporal sin bonos, deducciones 4% salud y pensión).
 * 2. Menú de consola interactivo para permitir pruebas en vivo.
 */
public class Main {

    private static final GestorNomina gestor = new GestorNomina();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Cargar datos de prueba que demuestran todas las modalidades
        cargarDatosPrueba();

        System.out.println("==================================================================");
        System.out.println("  SISTEMA DE GESTIÓN Y LIQUIDACIÓN DE NÓMINA DE EMPLEADOS");
        System.out.println("  Proyecto Java con Programación Orientada a Objetos (POO)");
        System.out.println("==================================================================");

        boolean salir = false;
        while (!salir) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ver liquidación detallada (comprobantes individuales de cada empleado)");
            System.out.println("2. Ver consolidado general de nómina (tabla resumen y totales)");
            System.out.println("3. Registrar un nuevo empleado");
            System.out.println("4. Ejecutar demostración completa de Nómina (Punto 1)");
            System.out.println("5. Ejecutar demostración de POO y Patrones de Diseño (Punto 2)");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    gestor.liquidarTodos();
                    break;
                case "2":
                    gestor.generarReporteConsolidado();
                    break;
                case "3":
                    registrarNuevoEmpleado();
                    break;
                case "4":
                    ejecutarDemostracionCompleta();
                    break;
                case "5":
                    com.sistema.investigacion.app.DemoPuntoDos.main(new String[]{});
                    break;
                case "6":
                    salir = true;
                    System.out.println("\n¡Gracias por utilizar el Sistema de Nómina! Cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Ingrese un número entre 1 y 6.");
                    break;
            }
        }
    }

    /**
     * Carga colaboradores de prueba diseñados para comprobar todas las condiciones.
     */
    private static void cargarDatosPrueba() {
        // 1. Empleado Asalariado con MÁS de 5 años (recibe bono del 10%)
        gestor.agregarEmpleado(new EmpleadoAsalariado(
                "Carlos Gómez", "CC-101725489", 7, 3_500_000.0));

        // 2. Empleado Asalariado con 5 o MENOS años (NO recibe bono del 10%)
        gestor.agregarEmpleado(new EmpleadoAsalariado(
                "Mariana Ruiz", "CC-102589741", 3, 2_800_000.0));

        // 3. Empleado Por Horas con MÁS de 40 horas (48 horas: 40 normales + 8 extras con 50% incremento)
        gestor.agregarEmpleado(new EmpleadoPorHoras(
                "Andrés Valencia", "CC-109845612", 4, 48.0, 30_000.0));

        // 4. Empleado Por Horas con 40 o MENOS horas (38 horas ordinarias)
        gestor.agregarEmpleado(new EmpleadoPorHoras(
                "Paola Herrera", "CC-103698745", 2, 38.0, 25_000.0));

        // 5. Empleado Por Comisión con ventas SUPERIORES a $2.000.000 (obtiene bono del 5%)
        gestor.agregarEmpleado(new EmpleadoPorComision(
                "Felipe Jaramillo", "CC-104587123", 5, 1_300_000.0, 4_500_000.0, 5.0));

        // 6. Empleado Por Comisión con ventas INFERIORES a $2.000.000 (sin bono adicional)
        gestor.agregarEmpleado(new EmpleadoPorComision(
                "Diana Montoya", "CC-108745963", 1, 1_300_000.0, 1_500_000.0, 5.0));

        // 7. Empleado Temporal (contrato a 6 meses, sin bonos ni beneficios adicionales)
        gestor.agregarEmpleado(new EmpleadoTemporal(
                "Mateo Restrepo", "CC-106541289", 0, 1_800_000.0, 6));
    }

    /**
     * Muestra de forma inmediata tanto los comprobantes como la tabla consolidada.
     */
    private static void ejecutarDemostracionCompleta() {
        System.out.println("\n>>> INICIANDO DEMOSTRACIÓN COMPLETA DEL SISTEMA <<<\n");
        gestor.liquidarTodos();
        gestor.generarReporteConsolidado();
    }

    /**
     * Permite registrar interactivamente un nuevo empleado de cualquiera de las 4 modalidades.
     */
    private static void registrarNuevoEmpleado() {
        try {
            System.out.println("\n--- REGISTRO DE NUEVO EMPLEADO ---");
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Número de identificación: ");
            String id = scanner.nextLine().trim();

            System.out.print("Años en la empresa: ");
            int anios = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Seleccione el tipo de empleado:");
            System.out.println("1. Empleado Asalariado");
            System.out.println("2. Empleado Por Horas");
            System.out.println("3. Empleado Por Comisión");
            System.out.println("4. Empleado Temporal");
            System.out.print("Tipo: ");
            int tipo = Integer.parseInt(scanner.nextLine().trim());

            Empleado nuevo = null;

            switch (tipo) {
                case 1:
                    System.out.print("Salario mensual fijo: $");
                    double salAsalariado = Double.parseDouble(scanner.nextLine().trim());
                    nuevo = new EmpleadoAsalariado(nombre, id, anios, salAsalariado);
                    break;
                case 2:
                    System.out.print("Horas trabajadas: ");
                    double horas = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Tarifa por hora: $");
                    double tarifa = Double.parseDouble(scanner.nextLine().trim());
                    nuevo = new EmpleadoPorHoras(nombre, id, anios, horas, tarifa);
                    break;
                case 3:
                    System.out.print("Salario base: $");
                    double salBaseCom = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Total ventas realizadas: $");
                    double ventas = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Porcentaje de comisión ordinaria (ej: 5.0): ");
                    double pctComision = Double.parseDouble(scanner.nextLine().trim());
                    nuevo = new EmpleadoPorComision(nombre, id, anios, salBaseCom, ventas, pctComision);
                    break;
                case 4:
                    System.out.print("Salario mensual estipulado: $");
                    double salTemporal = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Duración del contrato en meses: ");
                    int meses = Integer.parseInt(scanner.nextLine().trim());
                    nuevo = new EmpleadoTemporal(nombre, id, anios, salTemporal, meses);
                    break;
                default:
                    System.out.println("Tipo no válido. Registro cancelado.");
                    return;
            }

            gestor.agregarEmpleado(nuevo);
            System.out.println("\n✓ ¡Empleado registrado exitosamente! Mostrando su comprobante:");
            nuevo.mostrarDetalleLiquidacion();

        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de número ingresado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al registrar el empleado: " + e.getMessage());
        }
    }
}
