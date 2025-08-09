import java.util.Comparator;

public class ClientEnAttente implements Comparable<ClientEnAttente> {

private Client client;
private int numero;
private static int numeroSuivant =1 ;

    public ClientEnAttente(Client client) {
        this.client = client;
        this.numero = numeroSuivant;
        numeroSuivant++;
    }

    public Client getClient() {
        return client;
    }

    public int  compareTo(ClientEnAttente autre){
    if (this.client.getPriorite() > autre.client.getPriorite()) {
        return -1; // this est prioritaire
    }
    if (this.client.getPriorite() < autre.client.getPriorite()) {
        return 1; // autre est prioritaire
    }

    // Si priorités égales, on regarde le plus ancien
    if (this.numero < autre.numero) {
        return -1; // this est plus ancien
    }
    if (this.numero > autre.numero) {
        return 1; // autre est plus ancien
    }

    return 0; // exactement identiques (rare mais propre)


}
}
