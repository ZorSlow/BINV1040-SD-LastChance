import java.util.Objects;

public class Client {
    private String nom;
    private int priorite;

    public Client(String nom, int priorite) {
        this.nom = nom;
        this.priorite = priorite;
    }

    public String getNom() {
        return nom;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void ajouterPriorite(){
            priorite++;

    }
    public void decrementerPriorite(){
        if (priorite == 0)
            return;
        priorite--;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nom, client.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", priorite=" + priorite +
                '}';
    }
}
