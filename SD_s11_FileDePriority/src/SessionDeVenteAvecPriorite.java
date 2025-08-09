import java.util.*;

public class SessionDeVenteAvecPriorite {

    private PriorityQueue<ClientEnAttente> filePriorite;
    private HashMap<Client, CommandeAvePriorite> mapClientCommandeAvecPriorite;
    private int nombreCasiersRestants;
    public final static int MAX_CASIERS_CLIENT = 3;

    public SessionDeVenteAvecPriorite(int nombreCasiersMisEnVente) {
        if (nombreCasiersMisEnVente <= 0)
            throw new IllegalArgumentException("Nombre de casiers invalide.");
        this.nombreCasiersRestants = nombreCasiersMisEnVente;
        this.filePriorite = new PriorityQueue<>();
        this.mapClientCommandeAvecPriorite = new HashMap<>();
    }

    public boolean placerDansFileAttente(Client client) {
        if (client == null)
            throw new IllegalArgumentException();

        if (mapClientCommandeAvecPriorite.containsKey(client))
            return false;

        for (ClientEnAttente c : filePriorite) {
            if (c.getClient().equals(client))
                return false;
        }

        if (nombreCasiersRestants <= 0)
            return false;

        filePriorite.add(new ClientEnAttente(client));
        return true;
    }

    public Client selectionnerClientSuivant() {
        if (filePriorite.isEmpty())
            return null;

        ClientEnAttente suivant = filePriorite.poll(); // retire et retourne
        return suivant.getClient();
    }

    public boolean passerCommande(Client client, int nombreCasiersDemandes) {
        if (client == null || nombreCasiersDemandes <= 0 || nombreCasiersDemandes > MAX_CASIERS_CLIENT)
            throw new IllegalArgumentException();

        if (mapClientCommandeAvecPriorite.containsKey(client))
            throw new IllegalStateException("Le client a déjà commandé");

        if (nombreCasiersDemandes > nombreCasiersRestants)
            return false;

        CommandeAvePriorite commande = new CommandeAvePriorite(client, nombreCasiersDemandes);
        mapClientCommandeAvecPriorite.put(client, commande);
        nombreCasiersRestants -= nombreCasiersDemandes;
        client.decrementerPriorite();
        return true;
    }

    public void cloturerSession() {
        while (!filePriorite.isEmpty()) {
            ClientEnAttente enAttente = filePriorite.poll(); // on retire les clients restants
            Client client = enAttente.getClient();
            if (!mapClientCommandeAvecPriorite.containsKey(client)) {
                client.ajouterPriorite();
            }
        }
    }

    public int getNombreCasiersRestants() {
        return nombreCasiersRestants;
    }

    @Override
    public String toString() {
        return "Casiers restants : " + nombreCasiersRestants +
                "\nCommandes : " + mapClientCommandeAvecPriorite.keySet();
    }
}