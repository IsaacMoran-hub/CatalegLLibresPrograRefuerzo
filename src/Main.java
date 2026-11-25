import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

    public static Scanner scanner = new Scanner(System.in);
    public static Tui tui = new Tui();
    public static FileController fichero = new FileController("catalogo.txt");
    public static MemoryController memoria;

    public static void main(String[] args) {
        ArrayList<String[]> libros = new ArrayList<>();
        if (fichero.existe()) {
            try {
                libros = fichero.cargar();
            } catch (IOException e) {
                tui.mostrarMensaje("No se puede leer el fichero");
            }
        }
        memoria = new MemoryController(libros);

        int opcion = 0;
        while (opcion != 5) {
            tui.mostrarMenu();
            opcion = leerOpcion("Elige una opción de la 1 -5: ", 5);
            if (opcion == 1) {
                alta();
            } else if (opcion == 2) {
                consulta();
            } else if (opcion == 3) {
                modificacion();
            } else if (opcion == 4) {
                tui.mostrarMensaje("No ahi esta opcion");
            }
        }
        guardar();
        tui.mostrarMensaje("Adios");
    }

    public static void alta() {
        String isbn = leerTexto("ISBN: ");
        if (memoria.buscarPorIsbn(isbn) != null) {
            tui.mostrarMensaje("Ese ISBN ya existe, prueba con otro");
            return;
        }
        memoria.agregar(pedirDatos(isbn));
        guardar();
        tui.mostrarMensaje("Libro añadido");
    }

    public static void consulta() {
        tui.mostrarMenuConsulta();
        int opcion = leerOpcion("Elige una opción de 1-3: ", 3);
        ArrayList<String[]> resultado = new ArrayList<>();
        if (opcion == 1) {
            resultado = memoria.obtenerLibros();
        } else if (opcion == 2) {
            String[] libro = memoria.buscarPorIsbn(leerTexto("ISBN: "));
            if (libro != null) {
                resultado.add(libro);
            }
        } else {
            resultado = memoria.buscarPorTitulo(leerTexto("Título: "));
        }
        tui.mostrarTabla(resultado);
    }

    public static void modificacion() {
        String[] libro = memoria.buscarPorIsbn(leerTexto("ISBN del libro a modificar: "));
        if (libro == null) {
            tui.mostrarMensaje("No ahi libro con ese ISBN");
            return;
        }
        memoria.modificar(libro, pedirDatos(libro[0]));
        guardar();
        tui.mostrarMensaje("Hecho, libro modificado");
    }

    public static String[] pedirDatos(String isbn) {
        String titulo = leerTexto("Título: ");
        String autor = leerTexto("Autor: ");
        String genero = leerTexto("Género: ");
        double precio = leerPrecio();
        int anio = leerAnio();
        return new String[] { isbn, titulo, autor, genero, String.valueOf(precio), String.valueOf(anio) };
    }

    public static void guardar() {
        try {
            fichero.guardar(memoria.obtenerLibros());
        } catch (IOException e) {
            tui.mostrarMensaje("No se ha podido guardar");
        }
    }

    public static String leerTexto(String pregunta) {
        String texto = "";
        while (texto.isEmpty()) {
            tui.mostrarPregunta(pregunta);
            texto = scanner.nextLine().trim().replace(";", ",");
        }
        return texto;
    }

    public static double leerPrecio() {
        while (true) {
            tui.mostrarPregunta("Precio € : ");
            try {
                double precio = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (precio > 0) {
                    return precio;
                }
            } catch (NumberFormatException e) {
            }
            tui.mostrarMensaje("El precio tiene que ser un número mayor que 0");
        }
    }

    public static int leerAnio() {
        while (true) {
            tui.mostrarPregunta("Año de publicación: ");
            try {
                int anio = Integer.parseInt(scanner.nextLine().trim());
                if (anio >= 1950) {
                    return anio;
                }
            } catch (NumberFormatException e) {
            }
            tui.mostrarMensaje("ha de ser un numero y no puede ser menor a 1950");
        }
    }

    public static int leerOpcion(String pregunta, int maximo) {
        while (true) {
            tui.mostrarPregunta(pregunta);
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion >= 1 && opcion <= maximo) {
                    return opcion;
                }
            } catch (NumberFormatException e) {
            }
            tui.mostrarMensaje("Escoge otra opcion valida");
        }
    }

