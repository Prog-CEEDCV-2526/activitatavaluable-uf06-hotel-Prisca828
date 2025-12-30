package com.hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


/**
 * Gestió de reserves d'un hotel.
 */
public class App {

    // --------- CONSTANTS I VARIABLES GLOBALS ---------

    // Tipus d'habitació
    public static final String TIPUS_ESTANDARD = "Estàndard";
    public static final String TIPUS_SUITE = "Suite";
    public static final String TIPUS_DELUXE = "Deluxe";

    // Serveis addicionals
    public static final String SERVEI_ESMORZAR = "Esmorzar";
    public static final String SERVEI_GIMNAS = "Gimnàs";
    public static final String SERVEI_SPA = "Spa";
    public static final String SERVEI_PISCINA = "Piscina";

    // Capacitat inicial
    public static final int CAPACITAT_ESTANDARD = 30;
    public static final int CAPACITAT_SUITE = 20;
    public static final int CAPACITAT_DELUXE = 10;

    // IVA
    public static final float IVA = 1.21f;

    // Scanner únic
    public static Scanner sc = new Scanner(System.in);

    // HashMaps de consulta
    public static HashMap<String, Float> preusHabitacions = new HashMap<>();
    public static HashMap<String, Float> preusServeis = new HashMap<>();
    public static HashMap<String, Integer> capacitatInicial = new HashMap<>();
    

    // HashMaps dinàmics
    public static HashMap<String,Integer> disponibilitatHabitacions = new HashMap<>();
    public static HashMap<Integer, ArrayList<String>> reserves = new HashMap<>();

    // Generador de nombres aleatoris per als codis de reserva
    public static Random random = new Random();

    // --------- MÈTODE MAIN ---------

    /**
     * Mètode principal. Mostra el menú en un bucle i gestiona l'opció triada
     * fins que l'usuari decideix eixir.
     */
    public static void main(String[] args) {
        inicialitzarPreus();

        int opcio ;
        do {
            mostrarMenu();
            opcio = llegirEnter("Seleccione una opció: ");
            gestionarOpcio(opcio);
        } while (opcio != 6);

        System.out.println("Eixint del sistema... Gràcies per utilitzar el gestor de reserves!");
    }

    // --------- MÈTODES DEMANATS ---------

    /**
     * Configura els preus de les habitacions, serveis addicionals i
     * les capacitats inicials en els HashMaps corresponents.
     */
    public static void inicialitzarPreus() {
        // Preus habitacions
        preusHabitacions.put(TIPUS_ESTANDARD, 50f);
        preusHabitacions.put(TIPUS_SUITE, 100f);
        preusHabitacions.put(TIPUS_DELUXE, 150f);

        // Capacitats inicials
        capacitatInicial.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        capacitatInicial.put(TIPUS_SUITE, CAPACITAT_SUITE);
        capacitatInicial.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Disponibilitat inicial (comença igual que la capacitat)
        disponibilitatHabitacions.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        disponibilitatHabitacions.put(TIPUS_SUITE, CAPACITAT_SUITE);
        disponibilitatHabitacions.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Preus serveis
        preusServeis.put(SERVEI_ESMORZAR, 10f);
        preusServeis.put(SERVEI_GIMNAS, 15f);
        preusServeis.put(SERVEI_SPA, 20f);
        preusServeis.put(SERVEI_PISCINA, 25f);
    }

    /**
     * Mostra el menú principal amb les opcions disponibles per a l'usuari.
     */
    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Reservar una habitació");
        System.out.println("2. Alliberar una habitació");
        System.out.println("3. Consultar disponibilitat");
        System.out.println("4. Llistar reserves per tipus");
        System.out.println("5. Obtindre una reserva");
        System.out.println("6. Eixir");
    }

    /**
     * Processa l'opció seleccionada per l'usuari i crida el mètode corresponent.
     */

       public static void inicialitzarDades() {
         // Capacitat inicial
        
         capacitatInicial.put ("Estandard", 30);
         capacitatInicial.put ("Suite", 20);
         capacitatInicial.put ("Deluxe", 10);
         // Preus habitacions
       
         preusHabitacions.put ("Estandard", 50f);
         preusHabitacions.put ("Suite", 100f);
         preusHabitacions.put ("Deluxe", 150f);
        // Disponibilitat inicial
        disponibilitatHabitacions.put ("Estandard", 30);
        disponibilitatHabitacions.put ("Suite", 20);
        disponibilitatHabitacions.put ("Deluxe", 10);
       
         // Serveis addicionals
        
         preusServeis.put ("Esmorzar", 10f);
         preusServeis.put ("Gimnas", 15f);
         preusServeis.put ("Spa", 20f);
         preusServeis.put ("Piscina", 25f);  
    }
       static void gestionarOpcio(int opcio) {
        switch (opcio) {
            case 1:
                reservarHabitacio();
                break;
            case 2:
                alliberarHabitacio();
                break;
            case 3:
                consultarDisponibilitat();
                break;
            case 4:
                obtindreReservaPerTipus();
                break;
            case 5:
                obtindreReserva();
                break;
            case 6:
                System.out.println("Eixint del sistema...");
                break;
            default:
                System.out.println("Opció no vàlida. Torna-ho a intentar.");
        }
    }

    /**
     * Gestiona tot el procés de reserva: selecció del tipus d'habitació,
     * serveis addicionals, càlcul del preu total i generació del codi de reserva.
     */
    public static void reservarHabitacio() {
        System.out.println("\n===== RESERVAR HABITACIÓ =====");
       String tipus = seleccionarTipusHabitacioDisponible();
        if (tipus == null) return;

        ArrayList<String> serveis = seleccionarServeis();
        float preu = calcularPreuTotal(tipus, serveis);
        int codi = generarCodiReserva();

        Reserva reserva = new Reserva(tipus, serveis, preu);
        reserves.put(codi, reserva);

        disponibilitatHabitacions.put(tipus, disponibilitatHabitacions.get(tipus) - 1);

        System.out.println("Reserva creada. Codi: " + codi);
    }
            String tipusHabitacio = seleccionarTipusHabitacioDisponible();
            // Actualitzar disponibilitat
            disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) - 1);

            // Guardar reserva
            class Reserva{
    private String tipusHabitacio;
    private ArrayList<String> serveis;
    private float preuTotal;

    public Reserva(String tipusHabitacio, ArrayList<String> serveis, float preuTotal) {
        this.tipusHabitacio = tipusHabitacio;
        this.serveis = serveis;
        this.preuTotal = preuTotal;
    }

    public String getTipusHabitacio() {
        return tipusHabitacio;
    }

    public ArrayList<String> getServeisAddicionals() {
        return serveis;
    }

    public float getPreuTotal() {
        return preuTotal;
    }
}
           
            // Mostrar informació de la reserva
            System.out.println("Reserva realitzada correctament!");
            System.out.println("Codi de reserva: " + codiReserva);
            System.out.println("Tipus d'habitació: " + tipusHabitacio);
            System.out.println("Serveis addicionals: " + String.join(", ", serveis));
            System.out.println("Preu total (amb IVA): " + preuTotal + "€");
    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus.
     */
    public static String seleccionarTipusHabitacio(){ 
        //TODO
         System.out.println("Tipus disponible:");
            System.out.println("1. Estàndard");
            System.out.println("2. Suite");
            System.out.println("3. Deluxe");

            int opcio = llegirEnter ("Selecciona un tipus d'habitació (1-3): ");
            switch (opcio) {
                case 1:
                    return TIPUS_ESTANDARD;
                case 2:
                    return TIPUS_SUITE;
                case 3:
                    return TIPUS_DELUXE;
                default:
                    System.out.println("Opció no vàlida.");
                    return null;
        }

    }

    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    public static String seleccionarTipusHabitacioDisponible() {
        System.out.println("\nTipus d'habitació disponibles:");
        for (String tipus : preusHabitacions.keySet()) {
            mostrarInfoTipus(tipus);
        }
        String tipus = seleccionarTipusHabitacio();
        if (tipus != null && preusHabitacions.get(tipus) > 0) {
            return tipus;
        }
        if (tipus != null) {
            System.out.println("No queden habitacions disponibles d'aquest tipus.");
            return null;
    }
       

    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
   
    static ArrayList<String> seleccionarServeis() {

        ArrayList<String> serveisSeleccionats = new ArrayList<>();
        int opcio;
        do {
            System.out.println ("0.Finalitzar selecció de serveis");
            System.out.println ("1.Esmorzar");
            System.out.println ("2.Gimnàs");
            System.out.println ("3.Spa");
            System.out.println ("4.Piscina");
            opcio = llegirEnter ("Selecciona un servei addicional (0-4): ");
            switch (opcio) {
                case 1:
                    if (!serveisSeleccionats.contains(SERVEI_ESMORZAR)) {
                        serveisSeleccionats.add(SERVEI_ESMORZAR);
                        System.out.println("Servei d'esmorzar afegit.");
                    } else {
                        System.out.println("Aquest servei ja ha estat seleccionat.");
                    }
                    break;
                case 2:
                    if (!serveisSeleccionats.contains(SERVEI_GIMNAS)) {
                        serveisSeleccionats.add(SERVEI_GIMNAS);
                        System.out.println("Servei de gimnàs afegit.");
                    } else {
                        System.out.println("Aquest servei ja ha estat seleccionat.");
                    }
                    break;
                case 3:
                    if (!serveisSeleccionats.contains(SERVEI_SPA)) {
                        serveisSeleccionats.add(SERVEI_SPA);
                        System.out.println("Servei de spa afegit.");
                    } else {
                        System.out.println("Aquest servei ja ha estat seleccionat.");
                    }
                    break;
                case 4:
                    if (!serveisSeleccionats.contains(SERVEI_PISCINA)) {
                        serveisSeleccionats.add(SERVEI_PISCINA);
                        System.out.println("Servei de piscina afegit.");
                    } else {
                        System.out.println("Aquest servei ja ha estat seleccionat.");
                    }
                    break;
                case 0:
                    System.out.println("Selecció de serveis finalitzada.");
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a intentar.");
            }
        } while (opcio != 0);
        return serveisSeleccionats;
    }
        static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        float subtotal = preusHabitacions.get(tipusHabitacio);
        for (String servei : serveisSeleccionats) {
            subtotal += preusServeis.get(servei);
        }
        return subtotal * (1 + IVA);
    }
    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal ( ArrayList<String> serveisSeleccionats, String tipusHabitacio) {
        float preuHabitacio = preusHabitacions.get(tipusHabitacio);
        if (preuHabitacio == 0) {
            return 0;
        }
        float preuServeis = 0;
        for (String servei : serveisSeleccionats) {
            switch (servei) {
                case "Esmostar":
                    preuServeis += 10;
                    break;
                case "Gimnàs":
                    preuServeis += 15;
                    break;
                case "Spa":
                    preuServeis += 20;
                    break;
                case "Piscina":
                    preuServeis += 25;
                    break;
            }
        }
        return (preuHabitacio + preuServeis) * 1.21f; // IVA del 21%
    }

    /**
     * Genera i retorna un codi de reserva únic de tres xifres
     * (entre 100 i 999) que no estiga repetit.
     */
    public static int generarCodiReserva() {
        //TODO:
        int codi;
        do {
            codi = random.nextInt(900) + 100; // Genera un número entre 100 i 999
        } while (reserves.containsKey(codi)); // Comprova que no estiga repetit
        return codi;
    }

    /**
     * Permet alliberar una habitació utilitzant el codi de reserva
     * i actualitza la disponibilitat.
     */
     public static void alliberarHabitacio() {
        int codi = llegirEnter("Codi reserva: ");
        if (reserves.containsKey(codi)) {
            Reserva r = reserves.remove(codi);
            disponibilitatHabitacions.put(
                    r.getTipusHabitacio(),
                    disponibilitatHabitacions.get(r.getTipusHabitacio()) + 1
            );
            System.out.println("Habitació alliberada.");
        }
    }
    /**
     * Mostra la disponibilitat actual de les habitacions (lliures i ocupades).
     */
    public static void consultarDisponibilitat() {
        // TODO: Mostrar lliures i ocupades
        System.out.println("\n===== DISPONIBILITAT HABITACIONS =====");
        System.out.println("Tipus\tLliures\tOcupades"); 
        for (String tipus : preusHabitacions.keySet()) {
            mostrarDisponibilitatTipus(tipus);
        }
        System.out.println();

    }

    /**
     * Funció recursiva. Mostra les dades de totes les reserves
     * associades a un tipus d'habitació.
     */
    public static void llistarReservesPerTipus(int[] codis, String tipus) {
         // TODO: Implementar recursivitat
         if (codis.length == 0) {
             System.out.println("No hi ha reserves per a aquest tipus d'habitació.");
             return;
         }
         int codi = codis[0];
         if (reserves.containsKey(codi)) {
             Reserva reserva = reserves.get(codi);
             if (reserva.getTipusHabitacio().equals(tipus)) {
                 mostrarDadesReserva(codi);
             }
         }
         llistarReservesPerTipus(java.util.Arrays.copyOfRange(codis, 1, codis.length), tipus);
    }

    /**
     * Permet consultar els detalls d'una reserva introduint el codi.
     */
    public static void obtindreReserva() {
        System.out.println("\n===== CONSULTAR RESERVA =====");
        // TODO: Mostrar dades d'una reserva concreta
        System.out.print("Introdueix el codi de reserva: ");
        int codi = sc.nextInt();    
        if (reserves.containsKey(codi)) {
            mostrarDadesReserva(codi);
        } else {
            System.out.println("No s'ha trobat cap reserva amb aquest codi.");
        }
 
    }

    /**
     * Mostra totes les reserves existents per a un tipus d'habitació
     * específic.
     */
    public static void obtindreReservaPerTipus() {
        System.out.println("\n===== CONSULTAR RESERVES PER TIPUS =====");
        // TODO: Llistar reserves per tipus
        String tipus = seleccionarTipusHabitacio();
        int[] codis = reserves.keySet().stream().mapToInt(Integer::intValue).toArray();
        llistarReservesPerTipus(codis, tipus);  
    }

    /**
     * Consulta i mostra en detall la informació d'una reserva.
     */
    public static void mostrarDadesReserva(int codi) {
       // TODO: Imprimir tota la informació d'una reserva
         HashMap<Integer, ArrayList<String>> reserves = App.reserves;
            System.out.println("Codi de reserva: " + codi);
            System.out.println("Tipus d'habitació: " + reserva.getTipusHabitacio());
            System.out.println("Serveis addicionals: " + String.join(", ", reserva.getServeisAddicionals()));
            System.out.println("Preu total: " + reserva.getPreuTotal() + "€");  
    }

    // --------- MÈTODES AUXILIARS (PER MILLORAR LEGIBILITAT) ---------

    /**
     * Llig un enter per teclat mostrant un missatge i gestiona possibles
     * errors d'entrada.
     */
    static int llegirEnter(String missatge) {
        int valor = 0;
        boolean correcte = false;
        while (!correcte) {
                System.out.print(missatge);
                valor = sc.nextInt();
                correcte = true;
        }
        return valor;
    }

    /**
     * Mostra per pantalla informació d'un tipus d'habitació: preu i
     * habitacions disponibles.
     */
    static void mostrarInfoTipus(String tipus) {
        int disponibles = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        float preu = preusHabitacions.get(tipus);
        System.out.println("- " + tipus + " (" + disponibles + " disponibles de " + capacitat + ") - " + preu + "€");
    }

    /**
     * Mostra la disponibilitat (lliures i ocupades) d'un tipus d'habitació.
     */
    static void mostrarDisponibilitatTipus(String tipus) {
        int lliures = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        int ocupades = capacitat - lliures;

        String etiqueta = tipus;
        if (etiqueta.length() < 8) {
            etiqueta = etiqueta + "\t"; // per a quadrar la taula
        }

        System.out.println(etiqueta + "\t" + lliures + "\t" + ocupades);
    }
}


