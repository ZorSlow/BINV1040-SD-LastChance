import java.util.HashMap;

/**
 *
 * @author 													-------> METTEZ ICI VOS NOM ET PRENOM
 *
 *
 */

public class Entrepot {

	//TODO
	private Hangar[] tableHangar ;
    private 	int nombreHangarsLibres ;
	private HashMap<Integer,Societe> mapSociete;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if(nombreHangars<=0)
			throw new IllegalArgumentException();
		// TODO
		tableHangar = new Hangar[nombreHangars];
		nombreHangarsLibres = nombreHangars;
		mapSociete = new HashMap<>(nombreHangars);
	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){
		//TODO
		return nombreHangarsLibres;

	}


	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){
		//TODO
		return mapSociete.size();

	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		//TODO

		return mapSociete.get(numeroSociete);

	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		// TODO
		// Pour une meilleure repartition des hangars occupes dans l'entrepot,
		// veuillez suivre les indications donnees dans l'enonce!
			if (nombreHangarsLibres == 0)
				return -1; // plus aucun hangar disponible

			// Vérifie si la société existe déjà, sinon la crée
			Societe societe = mapSociete.get(numeroSociete);
			if (societe == null) {
				societe = new Societe(numeroSociete, nomSociete);
				mapSociete.put(numeroSociete, societe);
			}

			// Point de départ basé sur numeroSociete (répartition circulaire)
			int start = numeroSociete % tableHangar.length;

			// Parcours circulaire pour trouver un hangar libre
		for (int i = 0; i < tableHangar.length; i++) {
			int index = (start + i) % tableHangar.length;

			// Si aucun Hangar n’existe encore à cet index, on en crée un
			if (tableHangar[index] == null) {
				tableHangar[index] = new Hangar(numeroSociete);
			}

			// Vérifie si ce hangar est libre (pas de société associée)
			if (tableHangar[index].getSociete() == null) {
				tableHangar[index].setSociete(societe);
				societe.ajouterHangar(index);
				nombreHangarsLibres--;
				return index;
			}
		}

			// Aucun hangar libre trouvé (normalement déjà géré par le test initial)
			return -1;
		}



}
