import java.util.Comparator;

public class CompareteurEtudiantNom implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        int compareNom = o1.getNom().compareTo(o2.getNom());
        if (compareNom != 0){// ils sont différents → on trie par nom
            return compareNom;
        }
        return o1.getPrenom().compareTo(o2.getPrenom());// noms égaux → on trie par prénom
    }
}
