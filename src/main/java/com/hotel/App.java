package com.hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import jdk.dynalink.beans.StaticClass;

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
    public static HashMap<String, Float> preusHabitacions = new HashMap<String, Float>();
    public static HashMap<String, Integer> capacitatInicial = new HashMap<String, Integer>();
    public static HashMap<String, Float> preusServeis = new HashMap<String, Float>();

    // HashMaps dinàmics
    public static HashMap<String, Integer> disponibilitatHabitacions = new HashMap<String, Integer>();
    public static HashMap<Integer, ArrayList<String>> reserves = new HashMap<Integer, ArrayList<String>>();

    // Generador de nombres aleatoris per als codis de reserva
    public static Random random = new Random();

    // --------- MÈTODE MAIN ---------

    /**
     * Mètode principal. Mostra el menú en un bucle i gestiona l'opció triada
     * fins que l'usuari decideix eixir.
     */
    public static void main(String[] args) {
        inicialitzarPreus();

        int opcio = 0;
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
     * Configura els preus de les habitacions, serveis addicionals i
     * les capacitats inicials en els HashMaps corresponents.
     */
     //TODO:
     public static void inicialitzarus() {
    HashMap<String, Float> preusHabitacions = new HashMap<>();
       preusHabitacions.put(TIPUS_ESTANDARD, 50f);
        preusHabitacions.put(TIPUS_SUITE, 100f);
        preusHabitacions.put(TIPUS_DELUXE, 150f);
    HashMap<String, Integer> capacitatInicial = new HashMap<>();
        capacitatInicial.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        capacitatInicial.put(TIPUS_SUITE, CAPACITAT_SUITE);
        capacitatInicial.put(TIPUS_DELUXE, CAPACITAT_DELUXE);
    HashMap<String, Float> preusServeis = new HashMap<>();
        preusServeis.put(SERVEI_ESMORZAR, 10f);
        preusServeis.put(SERVEI_GIMNAS, 15f);
        preusServeis.put(SERVEI_SPA, 20f);
        preusServeis.put(SERVEI_PISCINA, 25f);
    }
    public static void gestionarOpcio(int opcio) {
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
                System.out.println("Adeu!");
                break;
            default:
            System.out.println("Opció no vàlida."); 
        }   
    }

     /**
     * Gestiona tot el procés de reserva: selecció del tipus d'habitació,
     * serveis addicionals, càlcul del preu total i generació del codi de reserva.
     */
    public static void reservarHabitacio() {
        System.out.println("\n===== RESERVAR HABITACIÓ =====");
    //TODO
        String tipusHabitacio = seleccionarTipusHabitacioDisponible();
        if (tipusHabitacio == null) {
            System.out.println("No hi ha habitacions disponibles per al tipus seleccionat.");
            return;
        }
        ArrayList<String> serveisSeleccionats = seleccionarServeis();
        float preuTotal = calcularPreuTotal(tipusHabitacio, serveisSeleccionats);
        int codiReserva = generarCodiReserva();

        // Actualitzar disponibilitat i guardar reserva          disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) - 1);
        disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) - 1);
        Reserva novaReserva = new Reserva(tipusHabitacio, serveisSeleccionats, preuTotal);
        reserves.put(codiReserva, novaReserva);

        // Mostrar confirmació
        System.out.println("Reserva realitzada amb èxit!");
        System.out.println("Codi de reserva: " + codiReserva);
        System.out.println("Preu total: " + preuTotal + "€");
    } 
        

    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus.
     */
    public static String seleccionarTipusHabitacio() {
        //TODO:
        System.out.println("Seleccione el tipo de habitación:");
        System.out.println("1. Estándar");  
        System.out.println("2. Suite");
        System.out.println("3. Deluxe");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                return TIPUS_ESTANDARD;
            case 2:
                return TIPUS_SUITE;
            case 3:
                return TIPUS_DELUXE;
            default:
                System.out.println("Opción no válida.");
                return null;
        }
        
        
    }

    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    //TODO:
    public static String seleccionarTipusHabitacioDisponible() {
        System.out.println("\nTipus d'habitació disponibles:");
     for (String tipus : disponibilitatHabitacions.keySet()) {
            System.out.println(tipus + " - " +
                    disponibilitatHabitacions.get(tipus) +
                    " disponibles - " +
                    preusHabitacions.get(tipus) + "€");
        }

        String tipus = seleccionarTipusHabitacio();
        if (tipus != null && disponibilitatHabitacions.get(tipus) > 0) {
            return tipus;
        }
        System.out.println("No hi ha habitacions disponibles d'aquest tipus."); 
        return null;
    }

    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
    public static ArrayList<String> seleccionarServeis() {
        ArrayList<String> serveisSeleccionats = new ArrayList<>();
        //TODO:
        int opcio;

        do {
            System.out.println("0. Finalitzar");
            System.out.println("1. Esmorzar");
            System.out.println("2. Gimnàs");
            System.out.println("3. Spa");
            System.out.println("4. Piscina");

            opcio = llegirEnter("Seleccione servei: ");
            switch (opcio) {
                case 1: afegirServei (serveis, SERVEI_ESMORZAR);
                case 2: afegirServei (serveis, SERVEI_GIMNAS);
                case 3: afegirServei (serveis, SERVEI_SPA);
                case 4: afegirServei (serveis, SERVEI_PISCINA);
            }
        } while (opcio != 0);

        return serveisSeleccionats;
    }
    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        //TODO:
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
        System.out.println("\n===== ALLIBERAR HABITACIÓ =====");
         // TODO: Demanar codi, tornar habitació i eliminar reserva
         System.out.print("Introdueix el codi de reserva: ");
         int codi = sc.nextInt();
         if (reserves.containsKey(codi)) {
             String tipusHabitacio = reserves.get(codi).getTipusHabitacio();
             reserves.remove(codi);
             disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) + 1);
             System.out.println("Habitació alliberada correctament.");
         } else {
             System.out.println("No s'ha trobat cap reserva amb aquest codi.");
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
         Reserva reserva = reserves.get(codi);
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
