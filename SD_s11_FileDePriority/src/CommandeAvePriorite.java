public class CommandeAvePriorite {
    private Client client;
    private int nombreCasiersDemandes;

    public CommandeAvePriorite(Client client, int nombreCasiersDemandes) {
        this.client = client;
        this.nombreCasiersDemandes = nombreCasiersDemandes;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNombreCasiersDemandes() {
        return nombreCasiersDemandes;
    }

    public void setNombreCasiersDemandes(int nombreCasiersDemandes) {
        this.nombreCasiersDemandes = nombreCasiersDemandes;
    }
}
