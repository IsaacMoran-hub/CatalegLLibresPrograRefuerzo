import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileController {
    private Path ruta;

    public FileController(String nombreFichero) {
        this.ruta = Path.of(nombreFichero);
    }

    public boolean existe() {
        return Files.exists(ruta);
    }

    public ArrayList<String[]> cargar() throws IOException {
        ArrayList<String[]> libros = new ArrayList<>();
        for (String linea : Files.readAllLines(ruta)) {
            String[] partes = linea.split(";");
            if (partes.length == 6) {
                libros.add(partes);
            }
        }
        return libros;
    }

    public void guardar(ArrayList<String[]> libros) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        for (String[] libro : libros) {
            lineas.add(String.join(";", libro));
        }
        Files.write(ruta, lineas);
    }
}
