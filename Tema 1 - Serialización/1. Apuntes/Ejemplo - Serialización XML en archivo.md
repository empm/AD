## Dependencia XML
Al crear un proyecto en Maven, se genera un archivo pom.xml, editarlo y añadir:

```xml
<dependencies>  
	<dependency>
		<groupId>com.thoughtworks.xstream</groupId>  
        <artifactId>xstream</artifactId>  
        <version>1.4.18</version>  
    </dependency>
</dependencies>
```
## Serializar
> Convertir un objeto en un documento XML donde cada atributo y valor del objeto se representa con etiquetas. 

```java
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.security.AnyTypePermission;

	String file = "/home/user/file.xml";
	
    // Iniciar lib  
    XStream xs = new XStream();  
  
    // Serializar - add  
    String xml = xs.toXML(listaPersonas);  
  
    FileWriter fw = null;  
    try {  
        fw = new FileWriter(new File(file));  
        fw.write(xml);  
        System.out.println("\nSe guardan los datos en: " + file + "\n");  
  
    } catch (IOException e) {  
        throw new RuntimeException(e);  
    } finally {  
        try {  
            if (fw != null) fw.close();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }
```

## Deserializar
> Convertir un documento XML a objeto.

```java
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.security.AnyTypePermission;4

	String file = "/home/user/file.xml";
	
    // Iniciar lib  
    XStream xs = new XStream();  
  
    // Permitir cualquier clase en XStream  
    xs.addPermission(AnyTypePermission.ANY);  
  
    // Crear alias  
    xs.alias("Contacto", Persona.class);  
  
    // Deserializar en archivo  
    FileInputStream fis = null;  
  
    try {  
        fis = new FileInputStream(new File(file));  
  
        // deserializar XML - convertir xml en lista  
        ArrayList<Persona> arrayPersonas = (ArrayList<Persona>) xs.fromXML(fis);  
  
        System.out.println("Contactos agenda: \n");  
        for (Persona p: arrayPersonas){  
            System.out.println("Nombre: " + p.getNombre());  
            System.out.println("Telefono: " + p.getTelefono());  
            System.out.println("Email: " + p.getEmail());  
            System.out.println("-----------------------\n");  
        }  
    } catch (FileNotFoundException e) {  
        System.out.println("Archivo no encontrado: " + file);  
    }
````
