package com.sistema.empleados.servicio;

import com.sistema.empleados.modelo.Empleado;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Servicio encargado de gestionar la colección de empleados y liquidar
 * la nómina global de la empresa aplicando polimorfismo.
 */
public class GestorNomina {

    private final List<Empleado> empleados;
    private static final NumberFormat FORMATO_MONEDA = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public GestorNomina() {
        this.empleados = new ArrayList<>();
    }

    /**
     * Agrega un nuevo empleado a la nómina (acepta cualquier subclase de Empleado).
     * 
     * @param empleado Instancia de Empleado o cualquiera de sus subclases
     */
    public void agregarEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleados.add(empleado);
        }
    }

    public List<Empleado> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }

    /**
     * Imprime el detalle individual de liquidación para cada empleado.
     * Demostración clara de POLIMORFISMO: se invoca el método sobre la referencia
     * Empleado y Java ejecuta la versión específica correspondiente a cada tipo.
     */
    public void liquidarTodos() {
        System.out.println("\n" + "=".repeat(75));
        System.out.println("          LIQUIDACIÓN INDIVIDUAL DETALLADA DE NÓMINA");
        System.out.println("=".repeat(75) + "\n");

        for (Empleado emp : empleados) {
            emp.mostrarDetalleLiquidacion();
        }
    }

    /**
     * Genera un reporte consolidado con los totales globales de nómina de la empresa.
     */
    public void generarReporteConsolidado() {
        double totalBruto = 0.0;
        double totalBonos = 0.0;
        double totalDevengado = 0.0;
        double totalSalud = 0.0;
        double totalPension = 0.0;
        double totalDeducciones = 0.0;
        double totalNeto = 0.0;

        System.out.println("\n" + "=".repeat(105));
        System.out.println("                           RESUMEN GENERAL Y CONSOLIDADO DE NÓMINA EMPRESARIAL");
        System.out.println("=".repeat(105));
        System.out.printf("%-18s | %-16s | %-14s | %-13s | %-12s | %-12s | %-14s%n",
                "COLABORADOR", "TIPO EMPLEADO", "BRUTO", "BONIFICACIÓN", "SALUD (4%)", "PENSIÓN (4%)", "NETO A PAGAR");
        System.out.println("-".repeat(105));

        for (Empleado emp : empleados) {
            double bruto = emp.calcularSalarioBruto();
            double bono = emp.calcularBonificacion();
            double devengado = emp.calcularTotalDevengado();
            double salud = emp.calcularDeduccionSalud();
            double pension = emp.calcularDeduccionPension();
            double deducciones = emp.calcularTotalDeducciones();
            double neto = emp.calcularSalarioNeto();

            totalBruto += bruto;
            totalBonos += bono;
            totalDevengado += devengado;
            totalSalud += salud;
            totalPension += pension;
            totalDeducciones += deducciones;
            totalNeto += neto;

            System.out.printf("%-18s | %-16s | %14s | %13s | %12s | %12s | %14s%n",
                    truncar(emp.getNombre(), 18),
                    truncar(emp.getClass().getSimpleName().replace("Empleado", ""), 16),
                    FORMATO_MONEDA.format(bruto),
                    FORMATO_MONEDA.format(bono),
                    FORMATO_MONEDA.format(salud),
                    FORMATO_MONEDA.format(pension),
                    FORMATO_MONEDA.format(neto));
        }

        System.out.println("=".repeat(105));
        System.out.printf("%-18s | %-16s | %14s | %13s | %12s | %12s | %14s%n",
                "TOTALES GENERALES", empleados.size() + " empleados",
                FORMATO_MONEDA.format(totalBruto),
                FORMATO_MONEDA.format(totalBonos),
                FORMATO_MONEDA.format(totalSalud),
                FORMATO_MONEDA.format(totalPension),
                FORMATO_MONEDA.format(totalNeto));
        System.out.println("=".repeat(105));
        System.out.printf(" >> TOTAL DEVENGADO EMPRESA  : %s%n", FORMATO_MONEDA.format(totalDevengado));
        System.out.printf(" >> TOTAL DEDUCCIONES DE LEY : %s (Salud: %s, Pensión: %s)%n", 
                FORMATO_MONEDA.format(totalDeducciones), FORMATO_MONEDA.format(totalSalud), FORMATO_MONEDA.format(totalPension));
        System.out.printf(" >> TOTAL DESEMBOLSO NETO    : %s%n", FORMATO_MONEDA.format(totalNeto));
        System.out.println("=".repeat(105) + "\n");
    }

    private String truncar(String texto, int max) {
        if (texto == null) return "";
        if (texto.length() <= max) return texto;
        return texto.substring(0, max - 2) + "..";
    }
}
