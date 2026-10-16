package com.sistema.empleados.modelo;

/**
 * Representa a un empleado con contrato a término indefinido o fijo que recibe
 * un salario mensual fijo.
 * 
 * Regla de negocio:
 * Si tiene más de 5 años en la empresa, obtiene un bono del 10% sobre su salario base.
 */
public class EmpleadoAsalariado extends Empleado {

    // Porcentaje de bonificación por antigüedad mayor a 5 años
    public static final double PORCENTAJE_BONO_ANTIGUEDAD = 0.10;
    public static final int ANIOS_REQUERIDOS_BONO = 5;

    /**
     * Constructor para EmpleadoAsalariado.
     * 
     * @param nombre Nombre del empleado
     * @param identificacion Cédula o ID
     * @param aniosEnEmpresa Años de servicio
     * @param salarioBase Salario mensual fijo
     */
    public EmpleadoAsalariado(String nombre, String identificacion, int aniosEnEmpresa, double salarioBase) {
        super(nombre, identificacion, aniosEnEmpresa, salarioBase);
    }

    /**
     * El salario bruto corresponde exactamente a su salario base mensual fijo.
     */
    @Override
    public double calcularSalarioBruto() {
        return salarioBase;
    }

    /**
     * Si el empleado tiene más de 5 años en la empresa, recibe un bono del 10%.
     */
    @Override
    public double calcularBonificacion() {
        if (aniosEnEmpresa > ANIOS_REQUERIDOS_BONO) {
            return salarioBase * PORCENTAJE_BONO_ANTIGUEDAD;
        }
        return 0.0;
    }

    @Override
    public String getDetalleBeneficios() {
        if (aniosEnEmpresa > ANIOS_REQUERIDOS_BONO) {
            return String.format("Bono de antigüedad (10%% por > %d años de servicio)", ANIOS_REQUERIDOS_BONO);
        }
        return "Sin bonificación de antigüedad (antigüedad <= 5 años)";
    }
}
