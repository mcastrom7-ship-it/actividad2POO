package com.sistema.empleados.modelo;

/**
 * Representa a un empleado comercial remunerado mediante sueldo base
 * y un porcentaje de comisión sobre las ventas generadas.
 * 
 * Reglas de negocio:
 * 1. Recibe un sueldo base fijo + comisión porcentual sobre sus ventas.
 * 2. Si sus ventas superan los $2.000.000, obtiene un bono adicional del 5% sobre las ventas.
 */
public class EmpleadoPorComision extends Empleado {

    private double ventasRealizadas;
    private double porcentajeComision; // Ejemplo: 5.0 representa el 5%

    public static final double META_VENTAS_BONO = 2_000_000.0;
    public static final double PORCENTAJE_BONO_META = 0.05; // 5% adicional

    /**
     * Constructor para EmpleadoPorComision.
     * 
     * @param nombre Nombre del empleado comercial
     * @param identificacion Cédula o ID
     * @param aniosEnEmpresa Años en la empresa
     * @param salarioBase Sueldo base pactado
     * @param ventasRealizadas Total de ventas facturadas en el período
     * @param porcentajeComision Porcentaje de comisión ordinaria (ej: 4.0 para 4%)
     */
    public EmpleadoPorComision(String nombre, String identificacion, int aniosEnEmpresa, 
                               double salarioBase, double ventasRealizadas, double porcentajeComision) {
        super(nombre, identificacion, aniosEnEmpresa, salarioBase);
        this.ventasRealizadas = Math.max(0, ventasRealizadas);
        this.porcentajeComision = Math.max(0, porcentajeComision);
    }

    /**
     * Calcula el valor de la comisión ordinaria sobre las ventas.
     * 
     * @return Monto de la comisión ordinaria
     */
    public double getComisionOrdinaria() {
        return ventasRealizadas * (porcentajeComision / 100.0);
    }

    /**
     * Calcula el salario bruto ordinario: Sueldo base + Comisión sobre ventas.
     */
    @Override
    public double calcularSalarioBruto() {
        return salarioBase + getComisionOrdinaria();
    }

    /**
     * Beneficio adicional:
     * Si supera los $2.000.000 en ventas, obtiene un bono del 5% sobre el total de ventas.
     */
    @Override
    public double calcularBonificacion() {
        if (ventasRealizadas > META_VENTAS_BONO) {
            return ventasRealizadas * PORCENTAJE_BONO_META;
        }
        return 0.0;
    }

    @Override
    public String getDetalleBeneficios() {
        if (ventasRealizadas > META_VENTAS_BONO) {
            return String.format("Bono de desempeño (5%% adicional por superar meta de %s en ventas)", 
                    FORMATO_MONEDA.format(META_VENTAS_BONO));
        }
        return String.format("Sin bono de desempeño (Ventas: %s no superaron la meta de %s)", 
                FORMATO_MONEDA.format(ventasRealizadas), FORMATO_MONEDA.format(META_VENTAS_BONO));
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public double getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(double ventasRealizadas) {
        this.ventasRealizadas = Math.max(0, ventasRealizadas);
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = Math.max(0, porcentajeComision);
    }
}
