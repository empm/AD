import java.io.File;

public class BasicFile {
    public static void main(String[] args) {
        String path = "./newFile";
        File f = new File(path);
        File file = new File("./newFile/newFolder");
        System.out.println("Nombre ruta: " + f.getName()); // Muestra el nombre de la ruta
        System.out.println("Existe: " + f.exists());  // True si existe, false si no existe
        System.out.println("Se crea: " + f.mkdir());   // True si la crea
        System.out.println("Esiste: " + f.exists());  // True si existe, false si no existe
        File[] newFile = f.listFiles();
        file.mkdir();
        System.out.println("Se intenta borrar: " + f.delete());  // True si borra la carpeta
        System.out.println("Existe: " + f.exists());  // True si existe, false si no existe



        File parentFolder = new File(path);

        // Crear carpeta principal y subcarpeta de ejemplo
        if (!parentFolder.exists()) {
            parentFolder.mkdir();
        }
        File subFolder = new File(parentFolder, "newFolder");
        subFolder.mkdir();

        // Llamada al método para eliminar el contenido de la carpeta
        deleteFolderContents(parentFolder);

        // Intentar eliminar la carpeta principal ahora que está vacía
        if (parentFolder.delete()) {
            System.out.println("La carpeta principal se eliminó correctamente.");
        } else {
            System.out.println("No se pudo eliminar la carpeta principal.");
        }
    }

    // Método para borrar recursivamente el contenido de una carpeta
    public static void deleteFolderContents(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {  // Evita NullPointerException si la lista de archivos es nula
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolderContents(file); // Llamada recursiva
                    }
                    file.delete(); // Borrar archivo o carpeta vacía
                }
            }
        }
    }
    
}
