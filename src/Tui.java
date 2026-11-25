import java.util.ArrayList;

public class Tui {
    public void mostrarMenu() {
        System.out.println();
        System.out.println("Gestor de Catalogo");
        System.out.println("---------------------");
        System.out.println(" 1. Guardar un nuevo Libro");
        System.out.println(" 2. Buscar Libros");
        System.out.println(" 3. Actualizar un libro");
        System.out.println(" 4. Eliminar un libro");
        System.out.println(" 5. Salir");
        System.out.println();
    }

    public void mostrarMenuConsulta() {
        System.out.println(" 1. Ver todos los libros");
        System.out.println(" 2. Buscar por ISBN");
        System.out.println(" 3. Buscar por título");
    }

    public void mostrarPregunta(String pregunta) {
        System.out.print(pregunta);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarTabla(ArrayList<String[]> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros");
            return;
        }
        System.out.println("ISBN | Título | Autor | Género | Precio | Año");
        for (String[] libro : libros) {System.out.println(libro[0] + " | " + libro[1] + " | " + libro[2] + " | " + libro[3] + " | " + libro[4] + " € | " + libro[5]);
        }
    }
}