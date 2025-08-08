import java.util.Comparator;

public class ComparateurEtudiantParMatricule implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant e1, Etudiant e2) {
        return Integer.compare(e1.getNumeroMatricule(), e2.getNumeroMatricule());
    }
}

