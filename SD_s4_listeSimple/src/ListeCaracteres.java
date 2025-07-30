import java.util.NoSuchElementException;
import java.util.ArrayList;

public class ListeCaracteres {

    private NoeudCaractere tete;
    // N'ajoutez pas d'autres attributs


    public ListeCaracteres() {
        this.tete = null;
    }


    /**
     * verifie la presence du caractere passe en parametre dans la liste
     *
     * @param caractereRecherche
     * @return true si le caractere est present dans la liste, false sinon
     */
    public boolean contient(char caractereRecherche) {
        //TODO
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            if (baladeur.caractere == caractereRecherche)
                return true;
            baladeur = baladeur.suivant;
        }
        return false;

    }


    /**
     * calcule le nombre de fois qu'apparait le caractere passe en parametre dans la liste
     *
     * @param caractereRecherche
     * @return le nombre d'occurrences du caractere
     */
    public int nombreOccurrences(char caractereRecherche) {
        //TODO
        NoeudCaractere baladeur = tete;
        int nombreOccurrences =0;
        while (baladeur != null) {
            if (baladeur.caractere == caractereRecherche)
                nombreOccurrences++;
            baladeur = baladeur.suivant;
        }

        return nombreOccurrences;

    }


    /**
     * remplace la 1ere occurrences du caractere a remplacer par un nouveau caractere
     *
     * @param caractereARemplacer le caractere a remplacer
     * @param nouveauCaractere    le nouveau caractere
     */
    public void remplacer(char caractereARemplacer, char nouveauCaractere) {
        //TODO
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            if (baladeur.caractere == caractereARemplacer) {
                baladeur.caractere = nouveauCaractere;
                baladeur = null;
            }else
                baladeur = baladeur.suivant;
        }
    }


    /**
     * remplace toutes les occurrences du caractere a remplacer par un nouveau caractere
     *
     * @param caractereARemplacer le caractere a remplacer
     * @param nouveauCaractere    le nouveau caractere
     */
    public void remplacerTout(char caractereARemplacer, char nouveauCaractere) {
        //TODO
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            if (baladeur.caractere == caractereARemplacer) {
                baladeur.caractere = nouveauCaractere;
            }
            baladeur = baladeur.suivant;
        }
    }


    /**
     * recherche le plus grand caractere de la liste ('a'<'b'< ...)
     *
     * @return le plus grand caractere
     * @throws NoSuchElementException si la liste est vide
     */
    public char max() throws NoSuchElementException {
        // TODO
        // rmq : le plus petit caractere : ' '
        if (tete == null)throw new NoSuchElementException();
        NoeudCaractere baladeur = tete;
        char plusGrandCaractere = tete.caractere;
        while (baladeur != null) {
            if (baladeur.caractere > plusGrandCaractere) {
                plusGrandCaractere = baladeur.caractere;
            }
            baladeur = baladeur.suivant;
        }
        return plusGrandCaractere;

    }


    /**
     * cree une arrayList contenant les caracteres de la liste
     * L'ordre doit etre respecte (une liste est une structure lineaire)
     *
     * @return l'arrayList creee
     */
    public ArrayList<Character> enArrayList() {
        // TODO
        // add() est en O(1)!
        ArrayList<Character> listeCaracteres = new ArrayList<>();
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            listeCaracteres.add(baladeur.caractere);
            baladeur = baladeur.suivant;
        }
        return listeCaracteres;

    }


    /**
     * verifie si les 2 listes contiennent les memes caracteres et ceci dans le meme ordre
     * Une liste est une structure LINEAIRE!
     *
     * @param l la liste a comparer a la liste courante
     * @return true si les 2 listes sont les memes, false sinon
     * @throws IllegalArgumentException si l est null
     */
    public boolean estEgalA(ListeCaracteres l) {
        if (l == null)
            throw new IllegalArgumentException();
        // TODO
        // N'utilisez pas la methode toString()!
        NoeudCaractere b1 = tete;
        NoeudCaractere b2 = l.tete;
        while (b1 != null && b2 != null){
            if (b1.caractere != b2.caractere) return false;
            b1 = b1.suivant;
            b2 = b2.suivant;
        }
        return b1 == null && b2 == null;

    }


    /**
     * cree une liste qui est une copie de la liste courante (meme ordre)
     *
     * @return une copie de la liste courante
     */
    public ListeCaracteres clone() {
        // TODO
        // DEFI!
            ListeCaracteres copie = new ListeCaracteres();
            ArrayList<Character> temp = this.enArrayList();
            for (int i = temp.size() - 1; i >= 0; i--) {
                copie.tete = new NoeudCaractere(temp.get(i), copie.tete);
            }
            return copie;
    }


    /**
     * supprime une fois le caractere passe en parametre
     * si le caractere se trouve plusieurs fois, c est sa premiere occurrence qui sera supprimee
     *
     * @param caractereASupprimer
     * @return true si le caractere etait bien present dans la liste, false sinon
     */
    public boolean supprimerPremiereOccurrence(char caractereASupprimer) {

        if (tete == null)
            return false;

        if (tete.caractere == caractereASupprimer) {
            this.tete = this.tete.suivant;
            return true;
        }

        NoeudCaractere precedent = this.tete;
        NoeudCaractere baladeur = this.tete.suivant;
        while (baladeur != null) {
            if (baladeur.caractere == caractereASupprimer) {
                precedent.suivant = baladeur.suivant;
                return true;
            }
            precedent = precedent.suivant;
            baladeur = baladeur.suivant;
        }

        return false;
    }

    /**
     * supprime le caractere autant de fois qu'il est present dans la liste
     *
     * @param caractereASupprimer
     * @return le nombre de suppressions effectuees
     */
    public int supprimerToutesLesOccurrences(char caractereASupprimer) {
        // TODO
        // DEFI!
        // La solution de la methode supprimerPremiereOccurrence() est donnee ci-dessus
        return 0;

    }


    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public ListeCaracteres(char[] tableCaracteres) {
        if (tableCaracteres == null)
            throw new IllegalArgumentException();
        for (int i = tableCaracteres.length - 1; i >= 0; i--) {
            this.tete = new NoeudCaractere(tableCaracteres[i], tete);
        }
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public String toString() {
        String aRenvoyer = "";
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            aRenvoyer += " " + baladeur.caractere;
            baladeur = baladeur.suivant;
        }
        return aRenvoyer;
    }

    // A NE PAS MODIFIER --> POUR LES TESTS !!!
    public void remplacerToutParX() {
        NoeudCaractere baladeur = tete;
        while (baladeur != null) {
            baladeur.caractere = 'x';
            baladeur = baladeur.suivant;
        }
    }

    private class NoeudCaractere {
        private char caractere;
        private NoeudCaractere suivant;

        public NoeudCaractere(char caractere, NoeudCaractere suivant) {
            this.caractere = caractere;
            this.suivant = suivant;
        }

    }
}
