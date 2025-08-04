public class GestionRallyeAutomobile {

	private static RallyeAutomobile rallye;
	private static MonScanner scanner = new MonScanner("pilotes.txt");

	public static void main(String[] args) {

		System.out.println("*******************************************");
		System.out.println("Programme de gestion d'un rallye automobile");
		System.out.println("*******************************************");
		System.out.println();
		System.out.print("Entrez le nombre de pilotes : ");
		int nbCoureurs = scanner.nextInt();
		initialiserLaCourse(nbCoureurs);

		int choix = 0;
		do {
			afficherMenu();
			choix = scanner.nextInt();
			scanner.nextLine();
			System.out.println();
			switch (choix) {
				case 1:
					afficherCourse();
					break;
				case 2:
					afficherPremier();
					break;
				case 3:
					avancerUnPilote();
					break;
				case 4:
					retirerUnPilote();
					break;
				case 5:
					afficherPositionPilote();
					break;
				case 6:
					franchirLigneArrivee();
					break;
				case 7:
					remettreUnPilote();
					break;
				case 8:
					afficherPilotesHorsCourse();
					break;
				case 9:
					afficherClassement();
					break;
				case 10:
					verifierPiloteArrive();
					break;
				default:
					System.out.println("Choix inconnu");
			}
		} while (rallye.nbPilotesEnCourse() != 0);

		System.out.println();
		System.out.println("La course est terminée !");
		System.out.println();
	}

	private static void afficherMenu() {
		System.out.println();
		System.out.println("1 -> Afficher toute la course");
		System.out.println("2 -> Afficher le pilote en tête");
		System.out.println("3 -> Enregistrer un dépassement");
		System.out.println("4 -> Retirer un pilote de la course");
		System.out.println("5 -> Donner la position d’un pilote");
		System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
		System.out.println("7 -> Remettre un pilote dans la course (après un autre pilote)");
		System.out.println("8 -> Afficher les pilotes hors course");
		System.out.println("9 -> Afficher le classement");
		System.out.println("10 -> Vérifier si le pilote a franchi la ligne d’arrivée");
		System.out.println();
		System.out.print("Votre choix : ");
	}

	private static void initialiserLaCourse(int nbCoureurs) {
		String[] pilotes = new String[nbCoureurs];
		for (int i = 0; i < nbCoureurs; i++) {
			System.out.print("Entrez le nom du pilote numéro " + (i + 1) + " : ");
			pilotes[i] = scanner.next();
			scanner.nextLine();
		}
		try {
			rallye = new RallyeAutomobile(pilotes);
		} catch (Exception e) {
			System.out.println("La course n'a pas pu être initialisée");
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static void afficherCourse() {
		System.out.println(rallye);
	}

	private static void afficherPremier() {
		String premier = rallye.donnerPiloteDeTete();
		if (premier == null)
			System.out.println("Il n'y a pas de pilote dans la course");
		else
			System.out.println("Le pilote en première position est " + premier + ".");
	}

	private static void avancerUnPilote() {
		System.out.print("Entrez le nom du pilote qui dépasse : ");
		String nom = scanner.next();
		if (!rallye.avancerPilote(nom))
			System.out.println("Ce pilote n'existe pas ou est déjà en tête.");
		else
			System.out.println("Ce pilote a été avancé.");
	}

	private static void retirerUnPilote() {
		System.out.print("Entrez le nom du pilote à retirer : ");
		String nom = scanner.next();
		if (rallye.supprimer(nom))
			System.out.println("Le pilote " + nom + " a été retiré.");
		else
			System.out.println("Ce pilote n'est pas dans la course.");
	}

	private static void afficherPositionPilote() {
		System.out.print("Entrez le nom du pilote : ");
		String nom = scanner.next();
		int pos = rallye.getPosition(nom);
		if (pos == -1)
			System.out.println("Ce pilote n'est pas dans la course.");
		else
			System.out.println("Le pilote " + nom + " est en position " + pos + ".");
	}

	private static void franchirLigneArrivee() {
		String premier = rallye.franchirLigneArrivee();
		if (premier == null)
			System.out.println("Il n'y a pas de pilote en course.");
		else
			System.out.println("Le pilote " + premier + " a franchi la ligne d'arrivée !");
	}

	private static void remettreUnPilote() {
		System.out.print("Nom du pilote à remettre : ");
		String pilote = scanner.next();
		System.out.print("Nom du pilote après lequel il sera inséré : ");
		String apres = scanner.next();
		if (rallye.remettreDansCourse(pilote, apres))
			System.out.println("Le pilote " + pilote + " a été remis dans la course après " + apres + ".");
		else
			System.out.println("Impossible de remettre ce pilote.");
	}

	private static void afficherPilotesHorsCourse() {
		System.out.println("Pilotes hors course : " + rallye.getPilotesHorsCourse());
	}

	private static void afficherClassement() {
		System.out.println("Classement actuel : " + rallye.getClassement());
	}

	private static void verifierPiloteArrive() {
		System.out.print("Entrez le nom du pilote : ");
		String nom = scanner.next();
		if (rallye.aFranchiLaLigne(nom))
			System.out.println("Le pilote " + nom + " a franchi la ligne d'arrivée.");
		else
			System.out.println("Le pilote " + nom + " n'a pas encore franchi la ligne d'arrivée.");
	}
}