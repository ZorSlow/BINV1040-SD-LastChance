import java.util.HashMap;
import java.util.LinkedList;

public class DicoSD {
	
	//TODO
	// suivez l'implementation proposee dans l'enonce!
	HashMap<String, LinkedList<String>> mapSite;
	// Au depart le dico est vide
	public DicoSD() {
		//TODO
		mapSite = new HashMap<>();
	}
	/**
	 * ajout dans le dico une association sd-url si cette association n'est pas encore presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si cette association n'etait pas encore presente dans le dico, false sinon
	 */
	public boolean ajouter(String sd, String url) {
		LinkedList<String> listeLiens = mapSite.get(sd);

		if (listeLiens == null) { // la clé n'existe pas encore
			listeLiens = new LinkedList<>();
			listeLiens.add(url);
			mapSite.put(sd, listeLiens);
			return true;
		}
		if (!listeLiens.contains(url)) { // clé existe mais URL pas encore dedans
			listeLiens.add(url);
			return true;
		}
		return false; // clé et URL déjà présentes
		//TODO
	}
	
	/**
	 * verifie si la structure de donnees se trouve dans le dico
	 * cette structure de donnees doit posseder au moins une url!
	 * @param sd
	 * @return true si sd est present, false sinon
	 */
	public boolean contient(String sd){
		return mapSite.containsKey(sd) && !mapSite.get(sd).isEmpty();
		//TODO
	}
	
	
	/**
	 * renvoie tous les urls associes a la structure de donnees passee en parametre
	 * @param sd
	 * @return une chaine de caracteres avec les urls selon le format : [urlPile1, urlPile2] ou [] si la structure de donnees n'existe pas
	 */
	public String lesURLs(String sd){
		if (!contient(sd))
			return "[]";
		return mapSite.get(sd).toString();
		//TODO
	}
	
	/**
	 * supprime dans le dico l'association sd-url si celle-ci est presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si l'association etait presente dans le dico, false sinon
	 */
	public boolean supprimer(String sd, String url) {
		LinkedList<String> listeLiens = mapSite.get(sd);
		if (listeLiens == null) {
			return false; // sd absent
		}

		if (listeLiens.remove(url)) { // remove retourne true si l'élément était présent
			// Optionnel : si la liste devient vide, retirer complètement la clé
			if (listeLiens.isEmpty()) {
				mapSite.remove(sd);
			}
			return true;
		}

		return false; // url absente dans cette liste
	}
		//TODO
}
