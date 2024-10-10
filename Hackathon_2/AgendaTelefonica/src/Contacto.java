import java.util.ArrayList;
import java.util.Comparator;

public class Contacto extends Persona {

    //atributos de la clase
    private String telefono;
    static int tamaño;
    private ArrayList<Contacto> agenda = new ArrayList<>();

    //Constructores
    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String telefono) {
        super(nombre, apellido);
        this.telefono = telefono;
    }

    //Getters y Setters
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Establece un maximo de contactos
    public void inicializarArray(int tamañoLimite){
        tamaño = tamañoLimite;
    }

    //Valida si se puede agregar contactos de acuerdo al limite ingresado por el usuario
    public boolean agendaLlena(){
        // validacion ternaria
        boolean agregar = agenda.size() < tamaño ? true:false;
        return agregar;
    }

    //Valida por nombre y apellido si un contacto ya existe en la lista
    public boolean existeContacto(Persona persona){
        boolean existe = false;
        for (Persona p : agenda) {
            if (p.getNombre().equalsIgnoreCase(persona.getNombre()) && p.getApellido().equalsIgnoreCase(persona.getApellido())) {
                existe = true;
            }
        }
        return existe;
    }

    //Permite añadir un nuevo usuario con previas validadciones
    public void añadirContacto(Persona persona, String telefono){
        if (!existeContacto(persona)) {
            agenda.add(new Contacto(persona.getNombre().substring(0, 1).toUpperCase() + persona.getNombre().substring(1).toLowerCase(), persona.getApellido().substring(0, 1).toUpperCase() + persona.getApellido().substring(1).toLowerCase(), telefono));
            System.out.println("Tu nuevo contacto se agrego con Exito");
        } else {
            System.out.println("El contacto ya existe");
        }
    }

    //Visualizacion de la lista de contactos en orden alfabetico
    public void listarContactos(){
        System.out.println("Tu lista de contactos: ");
        agenda.sort(Comparator.comparing(Persona::getNombre).thenComparing(Persona::getApellido));
        for (Contacto c : agenda) {
            System.out.println(c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono());
        }
    }

    //Permite buscar un contacto por nombre y apellido y visualizar su telefono
    public void buscarContacto(String nombre, String apellido){
        Persona p = new Persona(nombre, apellido);
        if (existeContacto(p)){
            for (Contacto c : agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)){
                    System.out.println("Tel: " + c.getTelefono());
                }
            }
        }else {
            System.out.println("El contacto no se ha encontrado");
        }
    }

    //Elimina un contacto de la agenda con previa validacion
    public void eliminarContacto(Persona p){
        if (existeContacto(p)){
            for (Contacto c : agenda) {
                if (c.getNombre().equalsIgnoreCase(p.getNombre()) && c.getApellido().equalsIgnoreCase(p.getApellido())){
                    agenda.remove(agenda.indexOf(c));
                    System.out.println("El contacto fue eliminado");
                    break;
                }
            }
        }else {
            System.out.println("El contacto no se ha encontrado");
        }
    }

    //Modifica el telefono de un contacto existente
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono){
        Persona p = new Persona(nombre, apellido);
        if (existeContacto(p)){
            for (Contacto c : agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)){
                    c.setTelefono(nuevoTelefono);
                    System.out.println("Su contacto a sido actualizado");
                }
            }
        }else {
            System.out.println("El contacto no se ha encontrado");
        }
    }

    //Permite calcular el espacio disponible para agregar mas usuarios
    public void espaciosLibres(){
        int espacio = tamaño - agenda.size();
        System.out.println("espacio disponible: " + espacio);
    }
}
