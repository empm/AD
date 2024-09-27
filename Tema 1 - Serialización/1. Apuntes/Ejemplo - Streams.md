
## Ejemplo leer un fichero caracter a caracter

```java
  // Crear constructor
  String nombreFichero = "prueba.txt";

  FileReader fr = null; // Lo dejamos a null para no inicializar
  
  try {
      fr = new FileReader(nombreFichero); // se puede poner la ruta

      int caracter = fr.read();

      while (caracter != -1) { // -1 es el final del fichero
        // convertir el caracter que leemos a char
          System.out.print((char) caracter); 
          caracter = fr.read();
      }
  } catch (FileNotFoundException e){
        System.out.println("Error" + e.getMessage());
    } finally {
          try {
              if (fr != nul) {
                  fr.close();
                }
          } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
          }
    }
  
```

## Ejemplo leer un fichero linea a linea

```java
  String nombreFichero = "prueba.txt";
  BufferedReader buff = null; // Se declara a null

  try {

    buff = new BufferedReader(new FileReader(nombreFichero));
    String texto = buff.readLine(); // Lee primero para saber si es o no null
    
    while (texto != null) {
        System.out.println(texto); // Podemos imprimir la linea leida de antes o no 
        texto = buff.readLine();
    } 
  } catch (FileNotFoundException e) {
      System.out.println("Fichero no encontrado " + e.getMessage());
  } catch (Exception e) {
      System.out.println("Error lectura fichero " + e.getMessage());
  } finally {
      try {
        if (buff != nul) buff.close();
      } catch (Exception e) {
          System.out.println("Error carga fichero " + e.getMessage());
      }
    }
  }
```


## Escribir un fichero caracter a caracter

```java
  String nombreFichero = "nuevo.txt";

  FileWriter fw = null;

  try {
    fw = new FileWriter(nombreFichero);

    // Ejemplo de escribir los caracteres de la A a la Z
    for (char c = 'A'; c <= 'Z'; c++){
        fw.write(c);
      }
    } catch (Exception e) {
        System.out.println("Error de escritura: " + e.getMessage());
      } finally {
          try {
              if (fw != null) fw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar: " + e.getMessage());
              }
        }

```

## Escribir fichero string a string

```java
  String nombreFichero = "nuevo.txt";
  String textoFichero = "Texto de prueba";

  BufferedWriter bw = null;

  try {
    bw = new BufferedWriter(new FileWriter(nombreFichero));

    bw.write(textoFichero);
    bw.newLine();
    bw.write("Fin del texto");

    } catch (Exception e){
        System.out.println("Error escribir en fichero ");
        System.out.println(e.getMessage());
      } finally {
        // Cerrar el buffer
          try {
              if (bw != null) bw.close();
            } catch (Exception e) {
                System.out.println("Error cerrando fichero");
                System.out.println(e.getMessage());
              }
        }    ```
