public class ComparateurEtudiantOrdrArrivee {
    public int compare(Etudiant e1, Etudiant e2) {
        // Si mêmes coordonnées (equals), considérer identiques
        if (e1.equals(e2)) {
            return 0;
        }
        // Sinon, forcer le LinkedHashSet à ne pas réordonner
        return 1;
    }
}
