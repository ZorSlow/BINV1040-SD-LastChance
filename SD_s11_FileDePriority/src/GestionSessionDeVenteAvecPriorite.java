import java.util.HashMap;
import java.util.Scanner;

public class GestionSessionDeVenteAvecPriorite {

    private static HashMap<String, Client> baseClients = new HashMap<>();
    private static SessionDeVenteAvecPriorite session;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans l'application Brasserie (Priorité)");
        System.out.print("Entrez le nombre de casiers à vendre pour cette session : ");
        int nbCasiers = scanner.nextInt();
        scanner.nextLine(); // flush

        session = new SessionDeVenteAvecPriorite(nbCasiers);

        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    enregistrerClient(scanner);
                    break;
                case "2":
                    mettreClientDansFile(scanner);
                    break;
                case "3":
                    selectionnerClient();
                    break;
                case "4":
                    passerCommande(scanner);
                    break;
                case "5":
                    session.cloturerSession();
                    System.out.println("Session clôturée !");
                    break;
                case "6":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        System.out.println("Fin de l'application.");
    }

    private static void afficherMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Enregistrer un client");
        System.out.println("2. Mettre un client dans la file d'attente");
        System.out.println("3. Sélectionner le client suivant");
        System.out.println("4. Passer une commande");
        System.out.println("5. Clôturer la session");
        System.out.println("6. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void enregistrerClient(Scanner scanner) {
        System.out.print("Login (identifiant unique) : ");
        String login = scanner.nextLine().trim();

        if (baseClients.containsKey(login)) {
            System.out.println("Ce login est déjà enregistré !");
            return;
        }

        System.out.print("Nom du client : ");
        String nom = scanner.nextLine().trim();

        System.out.print("Priorité initiale (par défaut = 3) : ");
        int priorite;
        try {
            priorite = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            priorite = 3;
        }

        baseClients.put(login, new Client(nom, priorite));
        System.out.println("Client enregistré avec succès !");
    }

    private static void mettreClientDansFile(Scanner scanner) {
        System.out.print("Login du client : ");
        String login = scanner.nextLine().trim();

        Client client = baseClients.get(login);
        if (client == null) {
            System.out.println("Client non enregistré !");
            return;
        }

        boolean ajouté = session.placerDansFileAttente(client);
        if (ajouté) {
            System.out.println("Client placé dans la file d'attente.");
        } else {
            System.out.println("Client déjà en file ou a déjà commandé, ou plus de casiers.");
        }
    }

    private static void selectionnerClient() {
        Client client = session.selectionnerClientSuivant();
        if (client == null) {
            System.out.println("Aucun client en attente.");
        } else {
            System.out.println("Client sélectionné : " + client.getNom() +
                    " (priorité : " + client.getPriorite() + ")");
        }
    }

    private static void passerCommande(Scanner scanner) {
        System.out.print("Login du client : ");
        String login = scanner.nextLine().trim();

        Client client = baseClients.get(login);
        if (client == null) {
            System.out.println("Client non enregistré !");
            return;
        }

        System.out.print("Nombre de casiers demandés : ");
        int nb;
        try {
            nb = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Entrée invalide.");
            return;
        }

        try {
            boolean ok = session.passerCommande(client, nb);
            if (ok) {
                System.out.println("Commande enregistrée !");
            } else {
                System.out.println("Commande refusée (pas assez de casiers).");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}