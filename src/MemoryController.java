import java.util.ArrayList;

public class MemoryController {
    private ArrayList<String[]> libros;

    public MemoryController(ArrayList<String[]> libros) {
        this.libros = libros;
    }

    public ArrayList<String[]> obtenerLibros() {
        return libros;
    }

    public String[] buscarPorIsbn(String isbn) {
        for (String[] libro : libros) {
            if (libro[0].equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public ArrayList<String[]> buscarPorTitulo(String titulo) {
        ArrayList<String[]> encontrados = new ArrayList<>();
        for (String[] libro : libros) {
            if (libro[1].toLowerCase().contains(titulo.toLowerCase())) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    public void agregar(String[] libro) {
        libros.add(libro);
    }

    public void modificar(String[] libro, String[] datosNuevos) {
        for (int i = 1; i < 6; i++) {
            libro[i] = datosNuevos[i];
        }
    }
}