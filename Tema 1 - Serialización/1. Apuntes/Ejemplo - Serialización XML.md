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
import java.util.ArrayList;

public static void main(String[] args) {
	ArrayList<Persona> listaPersonas = new ArrayList<>();

	Persona p1 = new Persona("Manuel", "654654564", "email.com");
	Persona p2 = new Persona("Marron", "654654564", "email.com");
	Persona p3 = new Persona("Miquel", "654654564", "email.com");
 
	listaPersonas.add(p1);
	listaPersonas.add(p2);
	listaPersonas.add(p3);

	serializarXML(listaPersonas);
	desserializarXml(listaPersonas);

}
```

```java
public static void serializarXML(ArrayList<Persona> listaPersonas) {

	// Iniciar lib
	XStream xs = new XStream();

	// Serializar - add
	String xml = xs.toXML(listaPersonas);

	System.out.println("Convirtiendo array de personas a xml");
	System.out.println(xml);

}
```

## Deserializar
> Convertir de texto XML a objeto.

```java
public static void desserializarXml(ArrayList<Persona> listaPersonas){

	// Iniciar lib
	XStream xs = new XStream();

	String xml = xs.toXML(listaPersonas); 

	// Permitir cualquier clase en XStream
	xs.addPermission(AnyTypePermission.ANY);

	// Crear alias
	xs.alias("Contacto", Persona.class);

	// deserializar XML - convertir xml en lista

	ArrayList<Persona> newArray = (ArrayList<Persona>) xs.fromXML(xml);


	System.out.println("Contactos agenda: \n");

	for (Persona p : listaPersonas ){
		System.out.println(p.getNombre());
		System.out.println(p.getTelefono());
		System.out.println(p.getEmail());
	}

}
````
