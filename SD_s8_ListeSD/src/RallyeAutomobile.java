import java.util.ArrayList;
import java.util.HashSet;

public class RallyeAutomobile {

	private ListeSDImpl<String> pilotes;
	private HashSet<String> pilotesHorsCourse = new HashSet<>();
	private ArrayList<String> classement = new ArrayList<>();

	/**
	 * place les pilotes passes en parametre en course
	 * @param lesPilotes la table avec les pilotes tries selon l'ordre des departs
	 * @throws IllegalArgumentException : la table ne peut etre null,
	 *                                    elle ne peut contenir des null,
	 *                                    elle ne peut contenir des homonymes.
	 */
	public RallyeAutomobile(String[] lesPilotes) {
		if (lesPilotes == null)
			throw new IllegalArgumentException("lesPilotes==null");
		pilotes = new ListeSDImpl<>();
		for (String p : lesPilotes) {
			if (p == null) throw new IllegalArgumentException("lesPilotes[i]==null");
			if (!pilotes.insererEnQueue(p)) {
				throw new IllegalArgumentException("Deux pilotes portent le meme nom");
			}
		}
	}

	public int nbPilotesEnCourse() {
		return pilotes.taille();
	}

	public boolean estEnCourse(String pilote) {
		return pilotes.contient(pilote);
	}

	public String donnerPiloteDeTete() {
		if (pilotes.estVide())
			return null;
		return pilotes.premier();
	}

	public boolean avancerPilote(String pilote) {
		if (!estEnCourse(pilote))
			return false;

		String precedent = pilotes.donnerPrecedent(pilote);
		if (precedent == null) {
			// pilote déjà en tête
			return false;
		}

		// On permute les valeurs dans la liste
		return pilotes.permuter(pilote, precedent);
	}

	/**
	 * Supprimer un pilote de la course (abandon)
	 */
	public boolean supprimer(String pilote) {
		if (pilotes.supprimer(pilote)) {
			pilotesHorsCourse.add(pilote);
			return true;
		}
		return false;
	}

	/**
	 * Faire franchir la ligne d’arrivée au pilote en tête
	 */
	public String franchirLigneArrivee() {
		String tete = donnerPiloteDeTete();
		if (tete == null)
			return null;
		pilotes.supprimer(tete);
		classement.add(tete);
		return tete;
	}

	public ArrayList<String> getClassement() {
		return classement;
	}

	public boolean aFranchiLaLigne(String pilote) {
		return classement.contains(pilote);
	}

	public HashSet<String> getPilotesHorsCourse() {
		return pilotesHorsCourse;
	}

	public boolean remettreDansCourse(String pilote, String apres) {
		if (estEnCourse(pilote) || aFranchiLaLigne(pilote))
			return false; // déjà en course ou arrivé

		if (!pilotes.contient(apres))
			return false; // référence incorrecte

		if (!pilotesHorsCourse.contains(pilote))
			return false; // ce pilote ne faisait pas partie de la course

		// Réinsertion
		boolean ok = pilotes.insererApres(apres, pilote);
		if (ok) {
			pilotesHorsCourse.remove(pilote);
		}
		return ok;
	}

	/**
	 * Retourne la position d’un pilote dans la course (1 = tête).
	 * @param pilote le nom du pilote
	 * @return la position ou -1 si absent
	 */
	public int getPosition(String pilote) {
		if (!pilotes.contient(pilote))
			return -1;

		int pos = 1;
		for (String p : pilotes) {
			if (p.equals(pilote))
				return pos;
			pos++;
		}
		return -1;
	}

	public String toString() {
		return pilotes.toString();
	}
}