# Programación funcional en java

Una de las características de la programación funcional está en que funcionan como valores. Esto significa tres cosas.

* **Almacenar funciones en variables.**
* **Pasar funciones en los parámetros.**
* **Funciones de retorno de otras funciones.**

Para entender la programación funcional es necesario volver a las versiones anteriores cuando la programación orientada a objetos en java 6 era muy crucial, siendo que los objetos y tipos primitivos son valores, podemos pasar objetos que tienen métodos, pero no métodos por sí mismos, en java 8 o posterior elimina está restricción.

## Desarrollo de un motor de cálculo

Comienza con las ventas previstas, los costos fijos y los costos incrementales. El motor combina estos para predecir el beneficio de un año. Las cantidades que nos interesan están definidas por un valor esperado para cada mes de enero a diciembre. Las ventas varían según la temporada, por la que el departamento de operaciones minoristas ha especificado una predicción diferente para cada mes. Los costos incrementales son aproximados por esta línea. Los costos fijos son constantes. El motor restará ambos costos de las ventas para predecir ganancias.

* Ventas previstas.
* Costos fijos.
* Costos incrementales.

## Expresiones lambda

Si se quieren modelar costos incrementales en función del tiempo. Hay un tipo estándar en Java 8 para una función desde int a double. Para crear uno con una expresión lambda se declara el parámetro poniéndole un nombre, luego una flecha con un guion y finalmente se usa el parámetro en una expresión.

```java
IntToDoubleFunction incrementalCosts = time -> 5.1 + 0.5 * time;
```

El mecanismo es una interfaz de un solo método. Las lambdas de java 8 son muy parecidas a la taquigrafía para una implementación anónima de una interfaz con un solo método.
Para hacer una función de un objeto a otro, se puede usar la interfaz de función genérica suministrada en java 8, el primer tipo el parámetro y el segundo el tipo de retorno.

```java
Function<String, Integer> wordCount = (String s) -> s.split("").length;
```

Se pueden hacer expresiones lambda con múltiples parámetros y el útlimo con el tipo de retorno con BiFunction.
Se pueden hacer lambdas con más de una expresión en el cuerpo, luego poner las llaves alrededor y usar la palabra clave return.

```java
BiFunction<String, Integer, Boolean> exceedsMaxLength = (s, maxLength) -> {
    int actualLength = s.length();
    return actualLength > maxLength;
};
```

### Patrón de estrategia (Strategy)

Si volvemos al modo orientado a objetos y el parámetro de constructor como un objeto, estamos pasando un objeto que sabe cómo realizar un cálculo. Este es un patrón de diseño OO documentado. El patrón de estrategia es una pieza muy pequeña de programación funcional, Dado que una estrategia realiza un cálculo y no accede ni actualiza el mundo exterior, sus datos ingresan. Es un patrón funcional. Con java 8 el patrón de estrategia es mucho menos dificultoso, antes se tenia que usar clases internas anónimas. Las expresiones lambda son más cortas y eficientes, ni siquiera es necesario crear una interfaz funcional para pasarla, se puede usar la función estándar provista en java 8.

![Alt text](/FunctionalProgramming/assets/functionalPattern.png?raw=true "Strategy Pattern")

### Diferencias subyacentes de java 6 y java 8

La diferencia entre java 6 y java 8 es más por la forma en que se ve el código, Java 8 sabe y se preocupa por las expresiones lambda y hace algunas optimizaciones. En la versión java 6, si este método principal se llamara más de una vez, se crearía una instancia de un proveedor cada vez para cada uno de estos. En java 8 el compilador reconoce que el proveedor de CalculateCosts es el mismo cada vez y reutiliza la misma instancia, el proveedor CalculateRevenue también es una constante, para el beneficio una constante no funcionará porque los valores de costos e ingresos serían diferentes cada vez. Esos valores se capturan y almacenan dentro de la lambda cada vez que se crea. Significa que este tercer proveedor siempre obtiene una nueva instancia, incluso en java 8.

Java 8 Constante

```java
final Double costs = timed("Costs calculation", TimingDemo::calculateCosts);
```

Java 8 Constante

```java
final Double revenue = timed("Revenue calculation", TimingDemo::calculateRevenue);
```

Nueva instancia

```java
final Double profit = timed("Profit calculation",
    () -> calculateProfit(costs, revenue),
    logger::info);
```

### Colecciones

En java estamos acostumbrados a usar la iteración externa, pero un estilo funcional seria la iteración interna. En la librería Guava con java 6 se logra con funciones de iterables o con fluidez, por lo que podemos encadenar las llamadas, por operaciones internas como filtro, transformación o de unión, para ser combinadas en una sola iteración y devolver un solo valor. En java 8 la funcionalidad de fluidez iterable es proporcionada por stream, en la nueva biblioteca de colecciones, una lista puede proporcionarnos una secuencia y la secuencia proporciona los métodos de iteración internos que deseamos.

**Guava** | **Java 8**
--- | --- |
`FluentIterable` | `Stream`
*filter* | *filter*
*transform* | *map*
*Joiner* | *reduce*
