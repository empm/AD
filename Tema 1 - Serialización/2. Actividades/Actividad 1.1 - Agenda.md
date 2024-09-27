Crear un programa que muestre datos de contacto (Nombre,
Teléfono y e-mail) de un fichero.
También se deben poder añadir contactos al fichero de
agenda.
Usar serialización de XML


---
### Paso 1: Desglosar el enunciado

Lo primero que debes hacer es **entender claramente qué se te pide**. Para ello, hazte estas preguntas:

1. **¿Qué datos necesitas manejar?**  
   Piensa en qué estructura o clase vas a usar para representar los contactos (Nombre, Teléfono y e-mail).

2. **¿Qué operaciones necesitas implementar?**  
   Identifica las acciones que debes realizar sobre los contactos y el fichero. Aquí, por ejemplo, parece que necesitas:
   - Mostrar los contactos almacenados.
   - Añadir nuevos contactos.

### Paso 2: Identificar los métodos principales

Después de desglosar el enunciado y entender qué datos necesitas manejar y qué operaciones vas a implementar, es hora de definir las funciones o métodos que realizarán estas operaciones. Para este caso, necesitas:

1. **Mostrar los contactos almacenados**: Leer y deserializar el archivo XML.
2. **Añadir nuevos contactos**: Serializar un nuevo contacto al archivo XML.

Pregúntate ahora:

1. **¿Cómo vas a implementar la lectura de contactos?**  
   Aquí necesitas un método que lea el archivo XML y convierta los datos en una lista de contactos (deserialización).

2. **¿Cómo vas a implementar la escritura de nuevos contactos?**  
   Necesitas otro método que permita añadir un nuevo contacto a la lista de contactos existente y luego sobrescribir el archivo XML (serialización).

### Paso 3: Describir las interacciones

Piensa en cómo va a ser la interacción con el programa desde la perspectiva del usuario. Al elegir una opción del menú, ocurren las siguientes interacciones:

1. **Leer contactos**:
   - Se debe llamar a la función de deserialización para cargar y mostrar los contactos en la consola.

2. **Añadir un nuevo contacto**:
   - Se pide al usuario que ingrese el nombre, teléfono y correo.
   - Se llama a la función de deserialización para obtener la lista actual de contactos.
   - Se añade el nuevo contacto a esa lista.
   - Finalmente, se vuelve a llamar a la función de serialización para guardar la lista actualizada en el archivo.

### Paso 4: Detallar las validaciones necesarias

Es importante que consideres las validaciones básicas:

1. **Entrada de datos**:  
   Debes verificar que el teléfono ingresado por el usuario siga un formato numérico, que el email contenga un `@`, y que no haya campos vacíos.

2. **Manejo de errores en archivos**:  
   ¿Qué ocurre si el archivo XML no existe o no puede abrirse? Debes manejar esos casos adecuadamente, devolviendo una lista vacía si no se encuentra el archivo, o mostrando un mensaje de error en caso de que la operación de lectura o escritura falle.

### Paso 5: Implementar la estructura del menú

El menú interactivo debe reflejar estas opciones y estar conectado con los métodos que implementan las operaciones clave. Debe:

1. Mostrar las opciones del menú.
2. Tomar la opción seleccionada por el usuario.
3. Ejecutar la operación correspondiente:
   - Llamar al método que deserializa y muestra los contactos.
   - Llamar al método que pide los datos, deserializa los contactos, añade el nuevo contacto a la lista y luego serializa la lista actualizada.

### Paso 6: Proceso completo de trabajo

Ahora tienes claro qué pasos debes implementar. Así sería el desglose de tu programa:

1. **Definir la clase `Persona`** con los atributos `nombre`, `telefono`, `email`.
2. **Deserialización**: Crear un método que lea y convierta el XML en una lista de objetos `Persona`.
3. **Serialización**: Crear un método que convierta la lista de objetos `Persona` en XML y lo guarde en el archivo.
4. **Menú interactivo**: Implementar un menú que permita al usuario elegir entre:
   - Leer contactos.
   - Añadir un nuevo contacto.
   - Salir del programa.

### Paso 7: Escribir el código paso a paso

Al finalizar el desglose, tendrás una estructura clara y lógica de cómo proceder con la implementación. Luego puedes escribir el código de cada método, asegurándote de incluir validaciones y manejo de excepciones, y finalmente implementar la funcionalidad del menú.
