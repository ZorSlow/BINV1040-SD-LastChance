import java.util.*;
import java.lang.reflect.Field;

public class TestSessionDeVente {
	
	private static Scanner scanner = new Scanner(System.in);
	private static int temps = 300 ;
	private static	Class	classe =	SessionDeVente.class;
	private static	Field	fileA ;
	private static	Field	mapCC ;
	private static  Field   listeC ;
	private static  Field   nbCom ;
	private static String[] NOMS_METHODES = {"placerDansFileAttente", "selectionnerClientSuivant","passerCommande","modifierCommande()"};

	private static	void preparerField()	{
		Field[] champs	= classe.getDeclaredFields();
		for (Field f :	champs) {
			if (f.getName().equals("fileAttente")) {
				fileA = f;
				fileA.setAccessible(true);
			}else if (f.getName().equals("mapClientCommande")) {
				mapCC = f;
				mapCC.setAccessible(true);	
			} else if (f.getName().equals("listeCommandes")) {
				listeC = f;
				listeC.setAccessible(true);	
			} else if (f.getName().equals("nombreCasiersRestants")) {
				nbCom = f;
				nbCom.setAccessible(true);	
			}
		}
	}	

	public static void main(String[] args) throws IllegalAccessException {
		System.out.println("************************************************");
		System.out.println("* Programme Test pour la classe SessionDeVente *");
		System.out.println("************************************************");
		preparerField();
		int choix = 0;
		do {			
			System.out.println("1 -> Tester la methode placerDansFileAttente()");	
			System.out.println("2 -> Tester la methode selectionnerClientSuivant()");
			System.out.println("3 -> Tester la methode passerCommande()");
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			boolean testOK = true ;
			switch (choix) {
			case 1:
				testOK = testPlacerDansFileAttente() ;
				break;
			case 2:
				testOK = testSelectionnerClientSuivant() ;
				break;
			case 3:
				testOK = testerPasserCommande() ;
				break;
			default:
				return;
			}
			if (testOK)
				System.out.println("Les tests de la methode " + NOMS_METHODES[choix - 1]
						+ " ont reussi.");
			else
				System.out.println("Les tests de la methode " + NOMS_METHODES[choix - 1]
						+ " ont echoue.");
			System.out.println();
		} while (choix >= 1 && choix <= 3 );	


	}
	
	private static boolean testPlacerDansFileAttente() {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			session.placerDansFileAttente(null) ;
			System.out.println("KO : Il fallait une exception car le client est null") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.placerDansFileAttente(new String()) ;
			System.out.println("KO : Il fallait une exception car le client est vide") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : " + e.getClass() + " au lieu de IllegalArgumentException");
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>)fileA.get(session);
		    fileAttente.addLast("loic") ;
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre methode renvoie true mais le client etait deja dans la file") ;
				testOK = false ;
			} else {
				if (fileAttente.size()!=1 || !fileAttente.getFirst().equals("loic")) {
					System.out.println("KO : La liste a ete modifiee mais le client etait deja dans la file") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;

		System.out.print("Test 4 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    fileAttente.add("mathias");
			LinkedHashSet<String> fileAAttendue = (LinkedHashSet<String>) fileAttente.clone();
		    nbCom.setInt(session,0);
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre methode renvoie true mais il n'y avait plus de casier disponible") ;
				testOK = false ;
			} else {
				if (!fileAttente.toString().equals(fileAAttendue.toString())) {
					System.out.println("KO : La liste a ete modifiee mais il n'y avait plus de casier de libre") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 5 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
			fileAttente.add("mathias");
			LinkedHashSet<String> fileAAttendue = (LinkedHashSet<String>) fileAttente.clone();
		    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
			Commande commande = new Commande("loic",SessionDeVente.MAX_CASIERS_CLIENT) ;
			mapClientCommande.put("loic",commande) ;
			listeCommandes.add(commande) ;
			if (session.placerDansFileAttente(new String("loic"))) {
				System.out.println("KO : Votre methode renvoie true mais le client avait deja commande") ;
				testOK = false ;
			} else {
				if (!fileAttente.toString().equals(fileAAttendue.toString())) {
					System.out.println("KO : La liste a ete modifiee mais le client avait deja commande") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 6 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
			fileAttente.add("mathias");
		    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
			if (!session.placerDansFileAttente(new String("loic"))) {
				testOK = false ;
				System.out.println("KO : Votre methode renvoie false mais le client aurait du etre mis dans la file") ;
			} else {
				if (!fileAttente.toString().equals("[mathias, loic]")) {
					System.out.println("KO : file attendue : [mathias, loic] mais file recue : "+fileAttente) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println();
		return testOK ;
	}
	
	private static boolean testSelectionnerClientSuivant() {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			String client = session.selectionnerClientSuivant() ;
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
			if (client!=null) {
				System.out.println("KO : La file etait vide, il fallait renvoyer null, or on a recu :"+client) ;
				testOK = false ;
			} else if (fileAttente.size()!=0) {
				System.out.println("KO : La liste a ete modifiee alors qu'elle n'aurait du l'etre") ;
				testOK = false ;
			}  else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    fileAttente.addLast("loic") ;
			String client = session.selectionnerClientSuivant() ;
			if (!client.equals("loic")) {
				System.out.println("KO : client attendu = loic, mais client recu :"+client) ;
				testOK = false ;
			} else if (fileAttente.contains("loic")) {
				System.out.println("KO : Vous avez oublie de retirer le client de la file") ;
				testOK = false ;
			} else if (fileAttente.size()!=0) {
				System.out.println("KO : file attendue : [] mais file recue : "+fileAttente) ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    fileAttente.addLast("loic") ;
		    fileAttente.addLast("mathias") ;
		    fileAttente.addLast("leo") ;
		    LinkedHashSet<String> fileAttendue = new LinkedHashSet<String>() ;
		    fileAttendue.addLast("mathias") ;
		    fileAttendue.addLast("leo") ;
		    HashSet<String> ensAttendu = new HashSet<String>() ;
		    ensAttendu.add("mathias") ;
		    ensAttendu.add("leo") ;
		    String file = fileAttente.toString() ;
			String client = session.selectionnerClientSuivant() ;
			if (!client.equals("loic")) {
				System.out.println("KO : client attendu = loic, mais client recu :"+client+" or la file etait "+file) ;
				testOK = false ;
			} else if (fileAttente.contains("loic")) {
				System.out.println("KO : Vous avez oublie de retirer le client de la file") ;
				testOK = false ;
			} else if (!fileAttente.toString().equals(fileAttendue.toString())) {
				System.out.println("KO : file attendue : "+fileAttendue+" mais file recue : "+fileAttente) ;
				testOK = false ;
			}  else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
	
	public static boolean testerPasserCommande() throws IllegalAccessException {
		boolean testOK = true ;
		System.out.println() ;
		SessionDeVente session = new SessionDeVente(5) ;
		System.out.print("Test 1 : ") ;
		try {
			session.passerCommande(null,1) ;
			System.out.println("KO : Il fallait une exception car le client est null") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 2 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerCommande(new String(),1) ;
			System.out.println("KO : Il fallait une exception car le client est vide") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 3 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerCommande(new String("loic"),-1) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 4 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerCommande(new String("loic"),0) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est <=0 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;

		System.out.print("Test 5 : ") ;
		session = new SessionDeVente(5) ;
		try {
			session.passerCommande(new String("loic"),4) ;
			System.out.println("KO : Il fallait une exception car le nombre de casiers demandes est > 3 ") ;
			testOK = false ;
		} catch(IllegalArgumentException e) {
			System.out.println("OK") ;
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalArgumentException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 6 : ") ;
		session = new SessionDeVente(5) ;
		LinkedHashSet<String> fileAttente = (LinkedHashSet<String>) fileA.get(session);
	    HashMap<String, Commande> mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
		ArrayList<Commande> listeCommandes = (ArrayList<Commande>) listeC.get(session);
		Commande commande = new Commande("loic",1) ;
		listeCommandes.add(commande) ;
		mapClientCommande.put("loic",commande) ;
		ArrayList<Commande> listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		HashMap<String, Commande> mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
		try {
			session.passerCommande(new String("loic"),1) ;
			System.out.println("KO : Il fallait une IllegalStateException car le client avait deja passe une commande") ;
			testOK = false ;
		} catch(IllegalStateException e) {
			int nca = nbCom.getInt(session);
			if (fileAttente.size()!=0) {
				System.out.println("KO : La liste a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
				testOK = false ;
			} else if (!listeCommandes.equals(listeAttendue)) {
				System.out.println("KO : La liste des commandes a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
				testOK = false ;
			} else if (!mapClientCommande.equals(mapAttendu)) {
				System.out.println("KO : Le map des commandes a ete modifie alors qu'il n'aurait pas du l'etre") ;
				testOK = false ;
			} else if (nca != 5) {
				System.out.println("KO : Le nombre de casier restant a ete modifie alors qu'il n'aurait pas du l'etre") ;
				testOK = false ;
			} else {
				System.out.println("OK") ;
			}
		} catch(Exception e) {
			System.out.println("KO : Mauvais type d'exception : "+e.getClass()+" au lieu de IllegalStateException") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 7 : ") ;
		session = new SessionDeVente(5) ;
		try {
			fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",2) ;
			mapClientCommande.put("mathias",commande);
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
		    nbCom.setInt(session,0);
			if (session.passerCommande(new String("loic"),1)) {
				System.out.println("KO : Votre methode renvoie true mais il n'y avait plus de casier disponible") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : Le map des commandes a ete modifie alors qu'il n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (nca != 0) {
					System.out.println("KO : Le nombre de casier restant a ete modifie alors qu'il n'aurait pas du l'etre") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 8 : ") ;
		session = new SessionDeVente(1) ;
		try {
			fileAttente = (LinkedHashSet<String>) fileA.get(session);
			mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",2) ;
			mapClientCommande.put("mathias",commande);
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			if (session.passerCommande(new String("loic"),2)) {
				System.out.println("KO : Votre methode renvoie true mais il n'y a plus assez de casier disponible") ;
				testOK = false ;
			} else {
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a ete modifie alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : Le map des commandes a ete modifie alors qu'il n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (nca != 1) {
					System.out.println("KO : Le nombre de casiers restant a ete modifie alors qu'il n'aurait pas du l'etre") ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 9 : ") ;
		int nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+3 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			commande = new Commande("loic",2) ;
			mapAttendu.put("loic",commande) ;
			listeAttendue.add(commande) ;
			if (!session.passerCommande(new String("loic"), 2)) {
				System.out.println("KO : Votre methode renvoie false mais le client pouvait commander") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a ete modifie alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes attendue : "+listeAttendue+" mais liste recue : "+listeCommandes) ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : map commande attendu : "+mapAttendu+" mais map recu : " +mapClientCommande) ;
					testOK = false ;
				} else if (nca!=nbCasiers-2) {
					System.out.println("KO : le nombre de casiers restants n'est pas bon : attendu :"+(nbCasiers-2)+" mais rccu : "+nca) ;
					testOK = false ;
				} else {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		
		System.out.print("Test 10 : ") ;
		nbCasiers = SessionDeVente.MAX_CASIERS_CLIENT+5 ;
		session = new SessionDeVente(nbCasiers) ;
		try {
			fileAttente = (LinkedHashSet<String>) fileA.get(session);
		    mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
			listeCommandes = (ArrayList<Commande>) listeC.get(session);
			commande = new Commande("mathias",1) ;
			mapClientCommande.put("mathias",commande) ;
			listeCommandes.add(commande) ;
			commande = new Commande("chloe",3) ;
			mapClientCommande.put("cloe",commande) ;
			listeCommandes.add(commande) ;
			mapAttendu = (HashMap<String, Commande>) mapClientCommande.clone() ;
			listeAttendue = (ArrayList<Commande>) listeCommandes.clone() ;
			commande = new Commande("loic",2) ;
			mapAttendu.put("loic",commande) ;
			listeAttendue.add(commande) ;
			if (!session.passerCommande(new String("loic"), 2)) {
				System.out.println("KO : Votre methode renvoie false mais le client pouvait commander") ;
				testOK = false ;
			} else {
				mapClientCommande = (HashMap<String, Commande>) mapCC.get(session);
				listeCommandes = (ArrayList<Commande>) listeC.get(session);
				int nca = nbCom.getInt(session) ;
				if (fileAttente.size()!=0) {
					System.out.println("KO : La liste a ete modifiee alors qu'elle n'aurait pas du l'etre") ;
					testOK = false ;
				} else if (!listeCommandes.toString().equals(listeAttendue.toString())) {
					System.out.println("KO : La liste des commandes attendue : "+listeAttendue+" mais liste recue : "+listeCommandes) ;
					testOK = false ;
				} else if (!mapClientCommande.toString().equals(mapAttendu.toString())) {
					System.out.println("KO : map commande attendu : "+mapAttendu+" mais map recu : " +mapClientCommande) ;
					testOK = false ;
				} else if (nca!=nbCasiers-2) {
					System.out.println("KO : le nombre de casiers restants n'est pas bon : attendu :"+(nbCasiers-2)+" mais recu : "+nca) ;
					testOK = false ;
				} else  {
					System.out.println("OK") ;
				}
			}
		} catch(Exception e) {
			System.out.println("KO : Il ne fallait pas d'exception") ;
			e.printStackTrace();
			try {
				Thread.sleep(temps);
			} catch(InterruptedException ex) {

			}
			testOK = false ;
		}
		System.out.println() ;
		return testOK ;
	}
}