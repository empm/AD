## Dependencia  JSON
https://github.com/FasterXML/jackson-dataformats-text/tree/master/properties

```xml
<dependencies>  
	<dependency>  
	    <groupId>com.fasterxml.jackson.dataformat</groupId>  
	    <artifactId>jackson-dataformat-properties</artifactId>  
	    <version>2.13.4</version>  
	</dependency>
</dependencies>
```

## Serializar Objeto > JSON
Convertimos un objeto de tipo `Persona` a formato JSON usando Jackson:

```java
// Imports obligatorios
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

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

Salida:
```json
Objeto Persona en formato JSON:
{"nombre":"Raul","telefono":"654123789","email":"raul@gmail.com"}
```

## Deserializar JSON > Objeto

Ahora, deserialicemos ese JSON de nuevo a un objeto `Persona`:

```java
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;  
import com.fasterxml.jackson.databind.*;

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

Salida:
```text
Objeto Persona deserializado desde JSON:
Persona{nombre='Raul', telefono='654123789', email='raul@gmail.com'}
```
