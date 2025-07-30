import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Algorithme de tri : UnshuffleSort 

 * Cet algorithme de tri necessite l�utilisation d�une liste de deques.
 * Cet algorithme de tri comporte deux etapes. La premiere consiste a repartir 
 * les entiers a trier dans un nombre variable de deques. Lorsque tous
 * les entiers auront ete repartis, la deuxieme etape se chargera de remplir la
 * table a renvoyer.
 * 
 * Les 2 etapes sont basees sur le principe suivant : La liste des deques devra
 * toujours etre triee en utilisant le premier entier de chaque deque comme clef de tri.
 * Les deques aussi sont tries.
 * 
 * 
 */
public class UnshuffleSort {
	
	private LinkedList<ArrayDeque<Integer>>  listeDeDeques;

	public UnshuffleSort() {
		this.listeDeDeques = new LinkedList<ArrayDeque<Integer>>();
	}

	/**
	 * tri de la table d'entiers re�ue en parametre
	 * 
	 * @param tableATrier la table a trier
	 * @return table contenant les entiers tries
	 */
	public int[] trier(int[] tableATrier) {
		remplirDeques(tableATrier);
		return viderDeques(tableATrier.length);
	}

	/**
	 * 1ere etape du tri : repartition des entiers dans des deques
	 * @param tableATrier la table a trier
	 */
	private void remplirDeques(int[]tableATrier) {	
		// Pour le debug:
		System.out.println("etape1");

		// TODO
		// pour plus de lisibilite cette methode pourrait appeler la methode suivante :
		for (int entier : tableATrier){
			placerEntier(entier);
		}
	}

	public void placerEntier(int entier) {

		// TODO
		// methode private --> public car verifiee par la classe TestEtape1UnshuffleSort
		// Pour le debug:
		System.out.println(listeDeDeques);
		// Essayer de placer l'entier dans un des deques existants
		for (ArrayDeque<Integer> deque : listeDeDeques) {
			if (entier >= deque.getLast()) {
				deque.addLast(entier);
				return; // on a trouvé la place -> on sort
			}
		}
		// Si on arrive ici, aucun deque ne convient, on en crée un nouveau
		ArrayDeque<Integer> nouveauDeque = new ArrayDeque<>();
		nouveauDeque.add(entier);
		listeDeDeques.addLast(nouveauDeque);
	}
	

	/**
	 * 2eme etape du tri : on vide les deques
	 * 
	 * @param taille nombre d'entiers de la table a trier
	 * @return table contenant les entiers tries
	 */
	private int[] viderDeques(int taille) {
		
		// Pour le debug:
		System.out.println("etape2");
		int [] resultat = new int[taille];
		for (int i = 0; i < taille; i++) {
			resultat[i] = supprimerPlusPetitEntier();
			if (!listeDeDeques.isEmpty()) {
				reorganiserListe();
			}
		}
		return resultat;
		// TODO


		// pour plus de lisibilite cette methode pourrait appeler les methodes suivantes :

	}
	
	private int supprimerPlusPetitEntier(){

		// supprime et renvoie l'entier supprime
		// On suppose que listeDeDeques n'est jamais vide quand on appelle cette méthode
		ArrayDeque<Integer> premierDeque = listeDeDeques.getFirst();
		int entier = premierDeque.removeFirst();
		// Si le deque est vide après suppression, on le retire de la liste
		if (premierDeque.isEmpty()) {
			listeDeDeques.removeFirst();
		}
		return entier;
	}
	
	private void reorganiserListe(){

		// Pour le debug:
		System.out.println(listeDeDeques);
		// pour plus de lisibilite cette methode pourrait appeler la methode suivante :
		// Retirer le premier deque temporairement
		if (listeDeDeques.size()<=1)
			return;
		ArrayDeque<Integer> premierDeque = listeDeDeques.removeFirst();
		int clef = premierDeque.getFirst();

		// Trouver la position où insérer
		int index = 0;
		for (ArrayDeque<Integer> deque : listeDeDeques) {
			if (clef >= deque.getFirst()) {
				index++;
			}
		}

		// Réinsérer à la position trouvée
		listeDeDeques.add(index, premierDeque);
	}

	private void deplacerPremierDeque() {

	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String toString(){
		return listeDeDeques.toString();
	}

}
