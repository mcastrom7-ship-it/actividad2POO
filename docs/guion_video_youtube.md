# Guion Estructurado para Video de YouTube

**Título recomendado para el video:**  
`Sistema de Liquidación de Nómina en Java con NetBeans | Diagrama de Clases UML y POO`

**Duración estimada:** 8 a 12 minutos  
**Herramientas recomendadas para grabar:** OBS Studio o la barra de juegos de Windows (`Win + G`), NetBeans IDE, micrófono claro.

---

## Estructura del Video (Minuto a Minuto)

```
[00:00 - 01:15] Introducción y Presentación Personal
[01:15 - 03:30] Explicación del Diagrama de Clases UML y Arquitectura POO
[03:30 - 07:30] Recorrido Técnico del Código Fuente en NetBeans
[07:30 - 10:15] Demostración en Vivo y Ejecución del Programa
[10:15 - 11:30] Conclusiones y Cierre
```

---

## 🎬 SECCIÓN 1: INTRODUCCIÓN (00:00 - 01:15)

**En Pantalla:** Cámara web del estudiante o diapositiva de portada con el nombre de la institución, la asignatura, el título del proyecto y el nombre del estudiante.  

> **[ESTUDIANTE FRENTE A CÁMARA O VOZ EN OFF]:**  
> *"Hola a todos y bienvenidos a este video. Mi nombre es [Tu Nombre Completo], estudiante de [Tu Carrera, ej. Ingeniería de Sistemas / Tecnología en Desarrollo de Software] de la [Nombre de tu Universidad / Institución].*  
>  
> *El día de hoy voy a exponer la solución y desarrollo de un sistema de gestión y liquidación de empleados desarrollado en lenguaje **Java**, utilizando el entorno de desarrollo **Apache NetBeans**.*  
>  
> *El objetivo principal de esta práctica es modelar una empresa donde conviven cuatro modalidades contractuales de empleados: **Asalariados**, **Por Horas**, **Por Comisión** y **Temporales**, aplicando de forma rigurosa los cuatro pilares de la Programación Orientada a Objetos: **Abstracción, Encapsulamiento, Herencia y Polimorfismo**, además de calcular las deducciones de ley para salud y pensión del cuatro por ciento y los beneficios particulares de cada tipo de contrato.*  
>  
> *Acompáñenme a ver primero el diagrama de clases UML y posteriormente el código fuente y su ejecución."*

---

## 🎬 SECCIÓN 2: DIAGRAMA DE CLASES UML (01:15 - 03:30)

**En Pantalla:** Compartir pantalla mostrando el Diagrama de Clases UML (puedes mostrar la imagen generada o la vista previa en pantalla completa).

> **[ESTUDIANTE]:**  
> *"Como podemos observar en nuestro diagrama de clases, el corazón de nuestra solución es la superclase abstracta llamada `Empleado`.*  
>  
> *Esta clase representa la **Abstracción** del modelo. En ella definimos los atributos comunes que todo trabajador posee en la organización:*
> 1. `nombre`: de tipo String.
> 2. `identificacion`: el documento de identidad.
> 3. `aniosEnEmpresa`: la antigüedad del colaborador.
> 4. `salarioBase`: el valor convenido como base contractual.  
>  
> *Además, definimos dos constantes de ley fundamentales: `PORCENTAJE_SALUD = 0.04` y `PORCENTAJE_PENSION = 0.04`, que representan el 4% obligatorio de deducciones de ley.*  
>  
> *A nivel de métodos, la superclase declara tres métodos abstractos que obligan a sus subclases a implementar su lógica específica mediante **Polimorfismo**:*
> - `calcularSalarioBruto()`
> - `calcularBonificacion()`
> - `getDetalleBeneficios()`  
>  
> *Y métodos concretos reutilizables como `calcularDeduccionSalud()`, `calcularDeduccionPension()`, `calcularTotalDeducciones()` y `calcularSalarioNeto()`.*  
>  
> *Mediante **Herencia** (representada por las flechas de generalización), derivamos cuatro subclases especializadas:*
> 1. `EmpleadoAsalariado`: recibe un sueldo fijo mensual y obtiene un bono anual del 10% si su antigüedad supera los cinco años.
> 2. `EmpleadoPorHoras`: liquida su salario en función de las horas laboradas y la tarifa acordada, donde las horas que superan la jornada estándar de 40 horas se pagan con un incremento del 50%.
> 3. `EmpleadoPorComision`: percibe un sueldo base más un porcentaje de comisión sobre sus ventas. Si sus ventas sobrepasan los dos millones de pesos ($2.000.000), se hace acreedor a un bono adicional del 5%.
> 4. `EmpleadoTemporal`: posee un contrato con duración en meses establecida y, por política estricta, no percibe bonos ni beneficios adicionales.*  
>  
> *Por último, la clase `GestorNomina` mantiene una colección polimórfica de tipo `List<Empleado>`, permitiendo procesar y liquidar a cualquier colaborador sin importar su subclase concreta."*

---

## 🎬 SECCIÓN 3: RECORRIDO DEL CÓDIGO EN NETBEANS (03:30 - 07:30)

**En Pantalla:** Mostrar la ventana de Apache NetBeans. Hacer zoom al código (`Alt + Rueda del ratón` en NetBeans) para que se lea con total claridad en el video.

### 1. Paquetes y Superclase `Empleado.java`
> **[ESTUDIANTE]:**  
> *"Pasemos ahora al entorno de NetBeans. Como buena práctica de arquitectura limpia, organizamos el proyecto en tres paquetes:*
> - `com.sistema.empleados.modelo`: para las entidades del dominio.
> - `com.sistema.empleados.servicio`: para la lógica de gestión de nómina.
> - `com.sistema.empleados.app`: para la clase principal ejecutable.  
>  
> *Abramos la clase `Empleado.java`. Aquí podemos notar el uso de la palabra reservada `abstract`. Los atributos están protegidos con `protected` para permitir la herencia directa. Miren aquí el cálculo de las deducciones:*
> ```java
> public double calcularDeduccionSalud() {
>     return calcularTotalDevengado() * PORCENTAJE_SALUD;
> }
> public double calcularDeduccionPension() {
>     return calcularTotalDevengado() * PORCENTAJE_PENSION;
> }
> ```
> *Ambas aplican el 4% que exige el requerimiento, garantizando que el total de deducciones sea del 8% sobre el total devengado."*

### 2. Subclase `EmpleadoAsalariado.java`
> **[ESTUDIANTE]:**  
> *"Veamos la primera subclase: `EmpleadoAsalariado`. Extiende de `Empleado`. Su salario bruto es directamente el salario mensual fijo. Y observemos el método `calcularBonificacion()`:*
> ```java
> if (aniosEnEmpresa > ANIOS_REQUERIDOS_BONO) { // > 5 años
>     return salarioBase * PORCENTAJE_BONO_ANTIGUEDAD; // 10%
> }
> return 0.0;
> ```
> *Cumple al 100% con la regla: si lleva más de cinco años, gana el 10% de bono; si no, cero."*

### 3. Subclase `EmpleadoPorHoras.java`
> **[ESTUDIANTE]:**  
> *"Ahora abramos `EmpleadoPorHoras`. Esta clase añade los atributos privados `horasTrabajadas` y `tarifaPorHora`. En `calcularSalarioBruto()` calculamos las horas totales por la tarifa, y en `calcularBonificacion()` otorgamos el beneficio de recargo contractual:*
> ```java
> double horasExtras = getHorasExtras(); // Horas adicionales a 40
> if (horasExtras > 0) {
>     return horasExtras * (tarifaPorHora * 0.50); // Recargo del 50%
> }
> ```
> *Así, cada hora después de la número 40 se liquida con el 150% de su valor."*

### 4. Subclase `EmpleadoPorComision.java`
> **[ESTUDIANTE]:**  
> *"Revisemos `EmpleadoPorComision`. Agrega `ventasRealizadas` y `porcentajeComision`. Su salario ordinario suma el sueldo base más el porcentaje de comisión. Y para el bono aplicamos la condición requerida:*
> ```java
> if (ventasRealizadas > META_VENTAS_BONO) { // > $2.000.000
>     return ventasRealizadas * PORCENTAJE_BONO_META; // 5%
> }
> ```
> *Si el asesor vende más de dos millones, recibe automáticamente el 5% adicional."*

### 5. Subclase `EmpleadoTemporal.java`
> **[ESTUDIANTE]:**  
> *"Por último, tenemos a `EmpleadoTemporal`. Maneja la duración del contrato en meses. En cumplimiento con la especificación de que no recibe bonos ni beneficios adicionales, el método `calcularBonificacion()` retorna exactamente `0.0`. Sin embargo, sí se le aplican sus deducciones obligatorias de ley de salud y pensión."*

---

## 🎬 SECCIÓN 4: EJECUCIÓN EN VIVO Y PRUEBAS (07:30 - 10:15)

**En Pantalla:** Hacer clic en el botón verde "Run Project" (o presionar `F6`) en NetBeans. Mostrar la consola inferior de NetBeans.

> **[ESTUDIANTE]:**  
> *"Procedemos a ejecutar el proyecto en NetBeans. Como podemos observar en la consola, el sistema inicia desplegando un menú interactivo y cargando los casos de prueba para cada regla de negocio.*  
>  
> *Vamos a seleccionar la opción **4: Ejecutar demostración completa**.*  
>  
> *[Hacer clic en la consola, escribir `4` y presionar Enter]*  
>  
> *Examinemos los resultados generados:*
> 1. *Caso 1: **Carlos Gómez** (Asalariado con 7 años de antigüedad): Al superar los 5 años, vemos que se le asigna su bono del 10% ($350.000) sobre su sueldo de $3.500.000. Se deducen $154.000 de salud y $154.000 de pensión, arrojando un neto de $3.542.000.*
> 2. *Caso 2: **Mariana Ruiz** (Asalariada con 3 años): No supera los 5 años, por lo que su bono es $0.*
> 3. *Caso 3: **Andrés Valencia** (Por horas con 48 horas trabajadas a $30.000 la hora): Tiene 8 horas extras. El sistema liquida sus 8 horas con el incremento del 50%, reflejando un beneficio de $120.000.*
> 4. *Caso 4: **Paola Herrera** (Por horas con 38 horas): Al no superar las 40 horas, no genera recargos.*
> 5. *Caso 5: **Felipe Jaramillo** (Comisión con $4.500.000 en ventas): Superó con creces la meta de dos millones, por lo cual el sistema le liquida su comisión base más el bono de desempeño del 5% ($225.000).*
> 6. *Caso 6: **Diana Montoya** (Comisión con $1.500.000 en ventas): No superó la meta de $2.000.000, por lo que no recibe bono.*
> 7. *Caso 7: **Mateo Restrepo** (Temporal con contrato a 6 meses): Recibe su salario base estipulado de $1.800.000, sin ningún bono, pero con sus respectivas deducciones de ley del 4% para salud y 4% para pensión.*  
>  
> *Y al final de la pantalla podemos apreciar la **Tabla Consolidada de Nómina Empresarial**, donde el polimorfismo calcula de forma limpia los totales de la empresa: el total bruto, total de bonificaciones otorgadas, total de salud, total de pensión y el gran total neto desembolsado."*

---

## 🎬 SECCIÓN 5: CONCLUSIONES Y CIERRE (10:15 - 11:30)

**En Pantalla:** Regresar a la cámara web o mostrar una diapositiva final de agradecimiento.

> **[ESTUDIANTE]:**  
> *"Como conclusiones de este desarrollo:*
> 1. *La **POO** nos permitió crear un sistema escalable y modular, donde si la empresa crea una nueva modalidad de contrato (por ejemplo, practicante o teletrabajador), bastará con crear una nueva subclase de `Empleado` sin modificar el código existente.*
> 2. *El **Polimorfismo** simplificó drásticamente el cálculo de la nómina general, permitiendo que una sola lista procese contratos heterogéneos.*
> 3. *El **Encapsulamiento** y las constantes de ley aseguraron que las deducciones obligatorias de salud y pensión del 4% se calculen de manera estandarizada y sin errores humanos.*  
>  
> *Muchísimas gracias por su atención. Si tienen alguna pregunta o sugerencia, pueden dejarla en la caja de comentarios. No olviden darle 'Me gusta' y suscribirse. ¡Hasta la próxima!"*

---

## 💡 Consejos Clave para Obtener la Máxima Calificación

1. **Resolución de NetBeans:** Antes de grabar, entra en NetBeans y aumenta la fuente del editor en `Tools > Options > Fonts & Colors > Font: 16 o 18 pt`. En los videos de YouTube, las fuentes pequeñas no se distinguen en celulares.
2. **Audio Nítido:** Utiliza auriculares con micrófono cerca de la boca y graba en un lugar libre de ruidos o eco.
3. **Fluidez:** Lee el guion 2 o 3 veces antes de grabar para que suene con total naturalidad y dominio del tema.
