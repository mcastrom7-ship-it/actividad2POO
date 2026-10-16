package com.sistema.empleados.modelo;

/**
 * Representa a un empleado contratado bajo la modalidad por horas.
 * 
 * Reglas de negocio:
 * 1. Su remuneración básica depende del número de horas trabajadas y la tarifa acordada.
 * 2. Beneficio contractual: Las horas que excedan la jornada estándar de 40 horas
 *    reciben un incremento (recargo) del 50% sobre el valor hora normal.
 */
public class EmpleadoPorHoras extends Empleado {

    private double horasTrabajadas;
    private double tarifaPorHora;

    public static final double JORNADA_ORDINARIA_MAX = 40.0;
    public static final double PORCENTAJE_RECARGO_EXTRA = 0.50; // 50% adicional

    /**
     * Constructor para EmpleadoPorHoras.
     * 
     * @param nombre Nombre del empleado
     * @param identificacion Cédula o identificación
     * @param aniosEnEmpresa Años en la empresa
     * @param horasTrabajadas Horas totales laboradas en el período
     * @param tarifaPorHora Tarifa en pesos por hora normal
     */
    public EmpleadoPorHoras(String nombre, String identificacion, int aniosEnEmpresa, 
                            double horasTrabajadas, double tarifaPorHora) {
        super(nombre, identificacion, aniosEnEmpresa, Math.min(horasTrabajadas, JORNADA_ORDINARIA_MAX) * tarifaPorHora);
        this.horasTrabajadas = Math.max(0, horasTrabajadas);
        this.tarifaPorHora = Math.max(0, tarifaPorHora);
    }

    /**
     * Retorna las horas que exceden las 40 horas semanales/mensuales de jornada estándar.
     * 
     * @return Horas extras trabajadas
     */
    public double getHorasExtras() {
        return Math.max(0, horasTrabajadas - JORNADA_ORDINARIA_MAX);
    }

    /**
     * Calcula el salario bruto ordinario (todas las horas a la tarifa normal pactada).
     */
    @Override
    public double calcularSalarioBruto() {
        return horasTrabajadas * tarifaPorHora;
    }

    /**
     * Beneficio adicional contractual:
     * El incremento del 50% sobre el valor hora de las horas extras trabajadas.
     * (El valor ordinario ya está en el salario bruto, aquí se suma el 50% de beneficio).
     */
    @Override
    public double calcularBonificacion() {
        double horasExtras = getHorasExtras();
        if (horasExtras > 0) {
            return horasExtras * (tarifaPorHora * PORCENTAJE_RECARGO_EXTRA);
        }
        return 0.0;
    }

    @Override
    public String getDetalleBeneficios() {
        double extras = getHorasExtras();
        if (extras > 0) {
            return String.format("%.1f horas extras con recargo contractual del 50%%", extras);
        }
        return "Jornada ordinaria (sin horas extras en el período)";
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = Math.max(0, horasTrabajadas);
        this.salarioBase = Math.min(this.horasTrabajadas, JORNADA_ORDINARIA_MAX) * this.tarifaPorHora;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = Math.max(0, tarifaPorHora);
        this.salarioBase = Math.min(this.horasTrabajadas, JORNADA_ORDINARIA_MAX) * this.tarifaPorHora;
    }
}
