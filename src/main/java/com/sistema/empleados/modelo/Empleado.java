package com.sistema.empleados.modelo;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Superclase abstracta que define los atributos y métodos comunes
 * para todos los tipos de empleados en la organización.
 * 
 * Aplica los principios de POO:
 * - Abstracción: define el contrato de métodos de cálculo.
 * - Encapsulamiento: atributos protegidos/privados con getters y setters.
 * - Herencia: base común para las 4 subclases de empleados.
 * - Polimorfismo: cada subclase implementa su propio cálculo salarial.
 */
public abstract class Empleado {

    // Atributos comunes a todo empleado
    protected String nombre;
    protected String identificacion;
    protected int aniosEnEmpresa;
    protected double salarioBase;

    // Deducciones obligatorias de ley (4% para salud y 4% para pensión)
    public static final double PORCENTAJE_SALUD = 0.04;
    public static final double PORCENTAJE_PENSION = 0.04;

    // Formateador estándar de moneda para presentación en consola
    protected static final NumberFormat FORMATO_MONEDA = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    /**
     * Constructor general de la superclase Empleado.
     * 
     * @param nombre Nombre completo del colaborador
     * @param identificacion Documento de identidad (cédula)
     * @param aniosEnEmpresa Antigüedad en años
     * @param salarioBase Salario base convenido
     */
    public Empleado(String nombre, String identificacion, int aniosEnEmpresa, double salarioBase) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.aniosEnEmpresa = Math.max(0, aniosEnEmpresa);
        this.salarioBase = Math.max(0.0, salarioBase);
    }

    // ==========================================
    // MÉTODOS ABSTRACTOS (POLIMORFISMO)
    // ==========================================

    /**
     * Calcula el salario básico o devengado ordinario según el contrato.
     * 
     * @return Monto del salario devengado ordinario
     */
    public abstract double calcularSalarioBruto();

    /**
     * Calcula los bonos o beneficios adicionales según las políticas del contrato.
     * 
     * @return Monto de la bonificación o beneficio adicional
     */
    public abstract double calcularBonificacion();

    /**
     * Entrega una descripción legible del beneficio o bono aplicado.
     * 
     * @return Detalle explicativo de la bonificación
     */
    public abstract String getDetalleBeneficios();

    // ==========================================
    // MÉTODOS CONCRETOS DE LIQUIDACIÓN DE NÓMINA
    // ==========================================

    /**
     * Calcula el total devengado en el período:
     * Total Devengado = Salario Bruto Ordinario + Bonificaciones / Beneficios.
     * 
     * @return Total devengado antes de deducciones
     */
    public double calcularTotalDevengado() {
        return calcularSalarioBruto() + calcularBonificacion();
    }

    /**
     * Calcula la deducción obligatoria para Salud (4% de ley).
     * Se aplica sobre el total devengado.
     * 
     * @return Valor descontado para salud
     */
    public double calcularDeduccionSalud() {
        return calcularTotalDevengado() * PORCENTAJE_SALUD;
    }

    /**
     * Calcula la deducción obligatoria para Pensión (4% de ley).
     * Se aplica sobre el total devengado.
     * 
     * @return Valor descontado para pensión
     */
    public double calcularDeduccionPension() {
        return calcularTotalDevengado() * PORCENTAJE_PENSION;
    }

    /**
     * Calcula el total de deducciones obligatorias (Salud 4% + Pensión 4% = 8%).
     * 
     * @return Total de deducciones retenidas
     */
    public double calcularTotalDeducciones() {
        return calcularDeduccionSalud() + calcularDeduccionPension();
    }

    /**
     * Calcula el salario neto final a consignar al trabajador:
     * Salario Neto = Total Devengado - Total Deducciones.
     * 
     * @return Salario neto a pagar
     */
    public double calcularSalarioNeto() {
        return calcularTotalDevengado() - calcularTotalDeducciones();
    }

    /**
     * Imprime en consola la colilla o comprobante formal de liquidación de nómina.
     */
    public void mostrarDetalleLiquidacion() {
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| COMPROBANTE DE LIQUIDACION - %-34s |%n", this.getClass().getSimpleName().toUpperCase());
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| Colaborador   : %-47s |%n", nombre);
        System.out.printf("| Identificacion: %-47s |%n", identificacion);
        System.out.printf("| Antiguedad    : %-47s |%n", aniosEnEmpresa + " anio(s)");
        System.out.printf("| Salario Base  : %-47s |%n", FORMATO_MONEDA.format(salarioBase));
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| (+) Salario Bruto Ordinario : %-33s |%n", FORMATO_MONEDA.format(calcularSalarioBruto()));
        System.out.printf("| (+) Beneficios / Bonos      : %-33s |%n", FORMATO_MONEDA.format(calcularBonificacion()));
        System.out.printf("|     Detalle: %-49s |%n", truncarTexto(getDetalleBeneficios(), 49));
        System.out.printf("| (=) TOTAL DEVENGADO         : %-33s |%n", FORMATO_MONEDA.format(calcularTotalDevengado()));
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| (-) Deduccion Salud (4%%)    : %-33s |%n", FORMATO_MONEDA.format(calcularDeduccionSalud()));
        System.out.printf("| (-) Deduccion Pension (4%%)  : %-33s |%n", FORMATO_MONEDA.format(calcularDeduccionPension()));
        System.out.printf("| (=) TOTAL DEDUCCIONES (8%%)  : %-33s |%n", FORMATO_MONEDA.format(calcularTotalDeducciones()));
        System.out.println("+-----------------------------------------------------------------+");
        System.out.printf("| >>> NETO A PAGAR            : %-33s |%n", FORMATO_MONEDA.format(calcularSalarioNeto()));
        System.out.println("+-----------------------------------------------------------------+\n");
    }

    private String truncarTexto(String texto, int longitudMax) {
        if (texto == null) return "";
        if (texto.length() <= longitudMax) return texto;
        return texto.substring(0, longitudMax - 3) + "...";
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getAniosEnEmpresa() {
        return aniosEnEmpresa;
    }

    public void setAniosEnEmpresa(int aniosEnEmpresa) {
        this.aniosEnEmpresa = Math.max(0, aniosEnEmpresa);
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = Math.max(0.0, salarioBase);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Neto: %s", 
                nombre, this.getClass().getSimpleName(), FORMATO_MONEDA.format(calcularSalarioNeto()));
    }
}
