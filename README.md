# Programación funcional en java

Una de las características de la programación funcional está en que funcionan como valores. Esto significa tres cosas.

* **Almacenar funciones en variables.**
* **Pasar funciones en los parámetros.**
* **Funciones de retorno de otras funciones.**

Para entender la programación funcional es necesario volver a las versiones anteriores cuando la programación orientada a objetos en java 6 era muy crucial, siendo que los objetos y primitivos son valores, podemos pasar objetos que tienen métodos, pero no métodos por sí mismos, en java 8 o posterior elimina está restricción.

## Desarrollo de un motor de cálculo

Comienza con las ventas previstas, los costos fijos y los costos incrementales. El motor combina estos para predecir el beneficio de un año. Las cantidades que nos interesan están definidas por un valor esperado para cada mes de enero a diciembre. Las ventas varían según la temporada, por la que el departamento de operaciones minoristas ha especificado una predicción diferente para cada mes. Los costos incrementales son aproximados por esta línea. Los costos fijos son constantes. El motor restará ambos costos de las ventas para predecir ganancias.

### Ventas

* Ventas previstas.
* Costos fijos.
* Costos incrementales.
