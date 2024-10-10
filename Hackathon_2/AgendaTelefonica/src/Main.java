import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contacto contacto = new Contacto();
        Persona persona = new Persona();
        int opcion = 0; // Menu tamaño agenda
        int opcion2; // Menu agenda telefonica
        int tamaño=0;


        // Menu tamaño de agenda de contactos, se pregunta al usuario o sino toma valor por defecto
        //se valida que la entrada sea numerica
        do {
            System.out.println("\nBienvenido a la agenda telefonica -*AET*-");
            System.out.println("¿Deseas elegir el tamaño de tu agenda?");
            System.out.println("1. Si \n" +
                    "2. No");
            try {
                opcion = sc.nextInt();
                if (opcion == 1) {
                    System.out.println("\nIngresa el tamaño de tu agenda telefonica \n");
                    tamaño = sc.nextInt();
                    contacto.inicializarArray(tamaño);
                    opcion = 2;
                    System.out.println("\nEl tamaño de tu agenda sera maximo de " + tamaño + " contactos \n");
                    esperarEnter();
                } else if (opcion == 2) {
                    tamaño = 10;
                    contacto.inicializarArray(tamaño);
                    System.out.println(" \nEl tamaño de tu agenda sera maximo de " + tamaño + " contactos \n");
                    esperarEnter();
                } else {
                    System.out.println("\nOpcion no valida \n");
                    esperarEnter();
                }
            } catch (Exception e) {
                System.out.println("\nOpcion no valida \nDebes ingresar 1 o 2 segun la opcion que deseas \n");
                esperarEnter();
                sc.next();
                opcion = 0;
            }
        } while (opcion != 2);

        // Menu agenda telefonica, se le muestra al usuario las diferentes opciones
        //se valida que la entrada sea numerica
        do {
            System.out.println("\nIngresa la opcion que deseas \n" +
                    "1. Ver lista de contactos \n" +
                    "2. Agregar contacto \n" +
                    "3. Buscar contacto \n" +
                    "4. Modificar telefono de contacto \n" +
                    "5. Eliminar contacto");
            System.out.println("0. Salir \n");
            try {
                opcion2 = sc.nextInt();
                sc.nextLine();
                switch (opcion2) {
                    case 0:
                        System.out.println("Gracias por utilizar la agenda telefonica -*AET*- \n" +
                                "Vuelve pronto...");
                        break;
                    case 1:
                        contacto.listarContactos();
                        esperarEnter();
                        break;
                    case 2:
                        if (contacto.agendaLlena()) {
                            persona = pedirDatosSt();
                            contacto.añadirContacto(persona, pedirTelefono());
                            contacto.espaciosLibres();
                            esperarEnter();
                        }else {
                            System.out.println("Tu agenda esta llena");
                        }
                        break;
                    case 3:
                        persona = pedirDatosSt();
                        contacto.buscarContacto(persona.getNombre(), persona.getApellido());
                        esperarEnter();
                        break;
                    case 4:
                        persona = pedirDatosSt();
                        contacto.modificarTelefono(persona.getNombre(), persona.getApellido(), pedirTelefono());
                        esperarEnter();
                        break;
                    case 5:
                        persona = pedirDatosSt();
                        contacto.eliminarContacto(persona);
                        esperarEnter();
                        break;
                    default:
                        System.out.println("\n1. Opcion no valida \nDebes ingresar un numero de 0 a 5 segun la opcion que deseas \n");
                        break;
                }
            }catch (Exception e) {
                System.out.println("\nOpcion no valida \nDebes ingresar un numero de 0 a 5 segun la opcion que deseas \n");
                System.out.println(e.getMessage());
                esperarEnter();
                sc.next();
                opcion2 = -1;
            }
        }while(opcion2!=0);
    }

    // Funcion para que el usuario vea el mensaje y luego presione Enter
    public static void esperarEnter() {
        System.out.println("Presiona 'Enter' para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Funcion para que el usuario ingrese en numero de telefono
    public static String pedirTelefono(){
        Scanner sc = new Scanner(System.in);
        String telefono;
        boolean continuar = false;

        do {
            System.out.println("Ingresa el telefono del contacto: ");
            telefono=sc.nextLine();
            if (telefono.matches(".*[a-zA-Z].*")){
                System.out.println("El telefono no debe contener letras");
                continuar = true;
            }else {continuar = false;}
        }while (continuar);

        return telefono;
    }

    // Funcion para que el usuario
    public static Persona pedirDatosSt() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;
        Persona persona = new Persona();
        String nombre;
        String apellido;

        do {
            System.out.println("Ingresa el nombre del contacto: ");
            nombre=sc.nextLine();
            if (nombre.equals("")){
                System.out.println("Nombre no debe estar vacio");
                continuar=true;
            }else {continuar = false;}
        }while (continuar);

        //continuar = true;

        do {
            System.out.println("Ingresa el apellido del contacto: ");
            apellido=sc.nextLine();
            if (apellido.equals("")){
                System.out.println("Apellido no debe estar vacio");
                continuar = true;
            }else {continuar = false;}
        }while (continuar);

        persona = new Persona(nombre, apellido);
        return persona;
    }

}