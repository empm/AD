- **Serializar** es cuando conviertes tus objetos `Persona` en un archivo XML (cuando añades un contacto).
- **Deserializar** es cuando lees el archivo `agenda.xml` y lo conviertes en una lista de objetos `Persona` (cuando muestras los contactos).

La **serialización** es el proceso de convertir un objeto en un formato que pueda ser almacenado o transmitido, y luego reconstruido (deserializado) más tarde. Se utiliza en muchas aplicaciones para guardar el estado de un objeto en un archivo, enviarlo a través de una red o almacenarlo en una base de datos.

- **Definición:** Serializar un objeto significa convertir sus datos en una secuencia de bytes o texto. Para XML, la serialización convierte un objeto en un documento XML.
- **Deserialización:** Es el proceso inverso, donde se toma ese XML y se convierte de nuevo en un objeto de código.

### Para qué se usa
1. **Almacenamiento:** Guardar objetos en archivos o bases de datos para ser reutilizados en el futuro.
2. **Transmisión de datos:** Enviar objetos a través de redes (ej., en aplicaciones web, servicios REST, etc.).
3. **Interoperabilidad:** Compartir datos entre diferentes plataformas o lenguajes que puedan interpretar el formato XML.

# Cómo se serializa en XML
- El objeto se convierte en un documento XML donde cada atributo y valor del objeto se representa con etiquetas. 
- La clase del objeto se convierte en el **tag raíz**.
  
Ejemplo sencillo de un objeto persona:

```xml
<Persona>
  <Nombre>Juan</Nombre>
  <Edad>31</Edad>
</Persona>
```

Si la clase tiene una lista de objetos, se crea una etiqueta para cada elemento de la lista:

```xml
<Persona>
  <Nombre>Juan</Nombre>
  <Edad>31</Edad>
  <Hobbies>
    <Hobby>Fútbol</Hobby>
    <Hobby>Leer</Hobby>
  </Hobbies>
</Persona>
```

---
### Dependencias en Maven

En Maven, las **dependencias** son bibliotecas externas o módulos que tu proyecto necesita para funcionar. Estas dependencias se gestionan en el archivo `pom.xml` (Project Object Model), que es el archivo de configuración principal de un proyecto Maven.

#### ¿Cómo funcionan?
- **Repositorio central**: Maven obtiene las dependencias desde un repositorio central en línea (Maven Central) o desde un repositorio privado si se configura.
- **Gestión automática**: Al agregar una dependencia en el `pom.xml`, Maven descarga y gestiona automáticamente la biblioteca y sus versiones.

#### Ejemplo de dependencia en `pom.xml`:
Aquí te muestro cómo añadir la dependencia de **XStream**, que es una biblioteca común para serializar/deserializar objetos en XML:

```xml
<dependencies>
    <dependency>
        <groupId>com.thoughtworks.xstream</groupId>
        <artifactId>xstream</artifactId>
        <version>1.4.18</version>
    </dependency>
</dependencies>
```

Con esto, Maven descargará la biblioteca y la hará disponible para tu proyecto.

---

### Ejemplo de Serialización en XML usando XStream en Java

1. **Añadir la dependencia**:
   Primero asegúrate de que la dependencia de XStream está en tu `pom.xml` como se muestra arriba.

2. **Código para serializar y deserializar un objeto**:

```java
import com.thoughtworks.xstream.XStream;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto
        Persona persona = new Persona("Juan", 31);

        // Inicializar XStream
        XStream xstream = new XStream();

        // Serializar el objeto a XML
        String xml = xstream.toXML(persona);
        System.out.println("Objeto serializado a XML:");
        System.out.println(xml);

        // Deserializar el XML a objeto
        Persona personaDeserializada = (Persona) xstream.fromXML(xml);
        System.out.println("Objeto deserializado desde XML:");
        System.out.println(personaDeserializada);
    }
}

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
```

#### Explicación:
- **XStream**: Es una biblioteca que permite la conversión de objetos a XML y viceversa. Aquí se usa el método `toXML()` para serializar un objeto y `fromXML()` para deserializar.
- **Serialización**: El objeto `persona` se convierte en una cadena XML que representa los atributos de la clase.
- **Deserialización**: El XML vuelve a convertirse en un objeto `Persona` utilizando `fromXML()`.

#### Salida del código:
```xml
Objeto serializado a XML:
<Persona>
  <nombre>Juan</nombre>
  <edad>31</edad>
</Persona>

Objeto deserializado desde XML:
Persona{nombre='Juan', edad=31}
```

# Cómo serializar en JSON
Aquí te doy un ejemplo básico de cómo usar la librería Jackson (disponible en [GitHub](https://github.com/FasterXML/jackson)) para realizar la serialización (convertir objetos a JSON) y deserialización (convertir JSON a objetos).

### Paso 1: Agregar la dependencia de Jackson a tu proyecto

Si usas Maven, agrega la dependencia de Jackson en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>
</dependency>
```

### Paso 2: Crear la clase de modelo

Crea una clase `Persona` con algunos atributos:

```java
package actividad02;

public class Persona {
    private String nombre;
    private String telefono;
    private String email;

    // Constructor
    public Persona(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor vacío (necesario para la deserialización)
    public Persona() {
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

### Paso 3: Serialización de objetos a JSON

Aquí convertimos un objeto de tipo `Persona` a formato JSON usando Jackson:

```java
package actividad02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        // Crear objeto Persona
        Persona persona = new Persona("Raul", "654123789", "raul@gmail.com");

        // Crear el ObjectMapper (herramienta principal de Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convertir el objeto Persona a JSON (Serialización)
            String jsonString = objectMapper.writeValueAsString(persona);
            System.out.println("Objeto Persona en formato JSON: ");
            System.out.println(jsonString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
```

Salida esperada:

```json
Objeto Persona en formato JSON:
{"nombre":"Raul","telefono":"654123789","email":"raul@gmail.com"}
```

### Paso 4: Deserialización de JSON a objeto

Ahora, deserialicemos ese JSON de nuevo a un objeto `Persona`:

```java
package actividad02;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        // JSON como string
        String jsonString = "{\"nombre\":\"Raul\",\"telefono\":\"654123789\",\"email\":\"raul@gmail.com\"}";

        // Crear el ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convertir el JSON a objeto Persona (Deserialización)
            Persona persona = objectMapper.readValue(jsonString, Persona.class);
            System.out.println("Objeto Persona deserializado desde JSON: ");
            System.out.println(persona);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Salida esperada:

```text
Objeto Persona deserializado desde JSON:
Persona{nombre='Raul', telefono='654123789', email='raul@gmail.com'}
```

### Explicación

1. **ObjectMapper** es la clase principal de Jackson que se utiliza tanto para la serialización como para la deserialización.
2. El método `writeValueAsString` convierte un objeto en una cadena JSON.
3. El método `readValue` convierte una cadena JSON en un objeto de una clase especificada (en este caso, `Persona`).

### Manejo de excepciones

Jackson maneja varios errores posibles durante el proceso de serialización y deserialización. Siempre es útil capturar posibles excepciones como `JsonProcessingException` para la serialización o `IOException` para la deserialización de archivos.

### Aplicación con archivos

Si deseas serializar y deserializar desde un archivo, puedes usar los métodos `writeValue` y `readValue` de `ObjectMapper` para trabajar con archivos directamente:

- **Serialización a archivo**:

```java
objectMapper.writeValue(new File("persona.json"), persona);
```

- **Deserialización desde archivo**:

```java
Persona persona = objectMapper.readValue(new File("persona.json"), Persona.class);
```

### Resumen

- **Serialización**: Convertir objetos Java a JSON.
- **Deserialización**: Convertir JSON a objetos Java.
- Usa Jackson y su clase `ObjectMapper` para manejar estas conversiones de forma fácil y eficiente.