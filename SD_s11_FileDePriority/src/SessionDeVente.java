import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class SessionDeVente {
	
	private LinkedHashSet<String> fileAttente;
	private HashMap<String, Commande> mapClientCommande;
	private ArrayList<Commande> listeCommandes;
	private int nombreCasiersRestants;
	public final static int MAX_CASIERS_CLIENT = 3;
	
	
	/**
	 * debute une session de vente
	 * @param nombreCasiersMisEnVente le nombre de casiers mis en vente
	 * @throws IllegalArgumentException s'il n'y a pas au moins un casier a vendre 
	 */
	public SessionDeVente(int nombreCasiersMisEnVente) {
		if(nombreCasiersMisEnVente<=0)
			throw new IllegalArgumentException();
		this.nombreCasiersRestants = nombreCasiersMisEnVente;
		fileAttente =new LinkedHashSet<>();
		mapClientCommande = new HashMap<String, Commande>();
		listeCommandes = new ArrayList<Commande>();
	}

	
	public int getNombreCasiersRestants() {
		return nombreCasiersRestants;
	}


	/**
	 * ajoute, si possible, le client dans la file d'attente
	 * le client ne peut pas deja y etre
	 * le client n'a pas encore fait de commande
	 * il reste encore des casiers a vendre
	 * @param client le client a ajouter
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 */
	public boolean placerDansFileAttente(String client){
		//TODO
		if (client == null || client.isEmpty() )throw new IllegalArgumentException();
		if (mapClientCommande.containsKey(client))
			return false;
		if (fileAttente.contains(client))
			return false;
		if (nombreCasiersRestants ==0)
			return false;

		return fileAttente.add(client);

	}


	/**
	 * retire de la file d'attente le client de tete
	 * @return le client de tete ou null si la file est vide
	 */
	public String selectionnerClientSuivant(){
		//TODO
		if (fileAttente.isEmpty())
			return null;
		String premierClient = fileAttente.iterator().next();
		fileAttente.remove(premierClient);
		return premierClient;
	}


	/**
	 * ajoute, si possible, une nouvelle commande  
	 * le nombre de casiers restants doit etre suffisant pour satisfaire completement la commande
	 * (il n'y a pas de commande partielle)
	 * @param client le client qui fait la demande
	 * @param nombreCasiersDemandes le nombre de casiers demandes
	 * @return true si la commande a pu etre faite, false sinon
	 * @throws IllegalArgumentException si le client est null ou vide
	 *  	ou si le nombre de casiers demandes n'est pas valide
	 * @throws IllegalStateException si le client a deja fait une commande  
	 */
	public boolean passerCommande(String client, int nombreCasiersDemandes){
		//TODO
		if (client == null || client.isEmpty() || nombreCasiersDemandes <=0 || nombreCasiersDemandes > MAX_CASIERS_CLIENT)throw new IllegalArgumentException();
		if (mapClientCommande.containsKey(client))throw new IllegalStateException();
		if (nombreCasiersDemandes > nombreCasiersRestants)
			return false;
		Commande commande = new Commande(client,nombreCasiersDemandes);

		mapClientCommande.put(client,commande);
		listeCommandes.add(commande);
		nombreCasiersRestants -= nombreCasiersDemandes;
		return true;
	}	
		

	public String toString(){
		// cette methode ne sera pas evaluee
		// elle peut-etre interessante a appeler en cas de bug
		return "le nombre de casiers restants : "+ nombreCasiersRestants 
				+ "\nla file d'attente : "+ fileAttente +  "\nles commandes " + listeCommandes + "\nle map client-commande"+ mapClientCommande;
	}

}

		
	
	
	
	
	

