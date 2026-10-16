package com.sistema.empleados.modelo;

/**
 * Representa a un empleado contratado por un período de tiempo definido.
 * 
 * Reglas de negocio:
 * 1. Tiene un contrato de duración limitada (ej. 3, 6 o 12 meses).
 * 2. Su salario bruto corresponde a su salario base convenido.
 * 3. NO recibe bonos ni beneficios adicionales.
 * 4. Aplica las deducciones obligatorias de ley (4% para salud y 4% para pensión).
 */
public class EmpleadoTemporal extends Empleado {

    private int mesesContrato;

    /**
     * Constructor para EmpleadoTemporal.
     * 
     * @param nombre Nombre del empleado temporal
     * @param identificacion Cédula o ID
     * @param aniosEnEmpresa Antigüedad en años (usualmente 0 o 1)
     * @param salarioBase Salario mensual pactado
     * @param mesesContrato Duración pactada del contrato en meses
     */
    public EmpleadoTemporal(String nombre, String identificacion, int aniosEnEmpresa, 
                            double salarioBase, int mesesContrato) {
        super(nombre, identificacion, aniosEnEmpresa, salarioBase);
        this.mesesContrato = Math.max(1, mesesContrato);
    }

    /**
     * El salario bruto corresponde a su salario base estipulado en el contrato temporal.
     */
    @Override
    public double calcularSalarioBruto() {
        return salarioBase;
    }

    /**
     * Por política contractual, el personal temporal NO tiene derecho a bonificaciones.
     * Retorna 0.0.
     */
    @Override
    public double calcularBonificacion() {
        return 0.0;
    }

    @Override
    public String getDetalleBeneficios() {
        return String.format("Contrato temporal a término (%d meses) - Excluido de bonos y beneficios", mesesContrato);
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public int getMesesContrato() {
        return mesesContrato;
    }

    public void setMesesContrato(int mesesContrato) {
        this.mesesContrato = Math.max(1, mesesContrato);
    }
}
