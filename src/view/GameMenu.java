package view;


public class GameMenu {
    public void displayMainMenu() {
        System.out.println("\n--- MENU DEL JUEGO ---");
        System.out.println("1. Seleccionar Personaje");
        System.out.println("2. Ver Estadisticas del Personaje");
        System.out.println("3. Iniciar Batalla");
        System.out.println("4. Mostrar Records");
        System.out.println("5. Salir");
        System.out.println("Elige una opción: ");
    }

    public void displayCharacterSelectionMenu() {
        System.out.println("\n--- SELECCION DE PERSONAJE ---");
        System.out.println("1. Jugador Guerrero");
        System.out.println("2. Jugador Mago");
        System.out.println("3. Jugador Arquero");
        System.out.print("Elige tu personaje: ");
    }

    public void displayInvalidOptionMessage() {
        System.out.println("Opción no válida, intenta de nuevo.");
    }

    public void displayNoCharacterSelectedMessage() {
        System.out.println("¡Primero debes seleccionar un personaje!");
    }

    public void displayExitMessage() {
        System.out.println("Gracias por jugar. ¡Hasta la proxima!");
    }

    public void displayCharacterSelectedMessage(String characterName) {
        System.out.println("¡Has seleccionado a " + characterName + "!");
    }

    public void displayDefaultCharacterSelectedMessage() {
        System.out.println("Opción no válida, seleccionando Guerrero por defecto.");
    }
}