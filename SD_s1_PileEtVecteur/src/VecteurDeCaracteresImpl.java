import java.util.Arrays;

public class VecteurDeCaracteresImpl implements VecteurDeCaracteres{
	
	private static final int CAPACITE_MIN = 4;		// taille physique de depart pour le constructeur sans parametre
	private char[] table;
	private int taille;

	/**
	 * construit un vecteur avec 0 caractere mais pouvant contenir jusqu'a CAPACITE_MIN caracteres
	 */
	public VecteurDeCaracteresImpl() {
		this.table=new char[CAPACITE_MIN];
		this.taille=0;
	}

	/**
	 * construit un vecteur avec 0 caractere mais pouvant contenir jusqu'a capacite caracteres
	 * @param capacite capacite de depart
	 * @throws IllegalArgumentException si la capacite de depart est negative ou nulle
	 */
	public VecteurDeCaracteresImpl(int capacite) {
		if(capacite<=0){
			throw new IllegalArgumentException();
		}
		this.table=new char[capacite];
		this.taille=0;
	}

	// taille logique de la table
	public int taille() {
		return taille;
	}
	
	
	public boolean estVide() {
		return taille == 0;
		//TODO

	}

	
	public char element(int rang) throws VecteurOutException {
		if (rang < 0 || rang >= taille)throw new VecteurOutException();
		return table[rang];
		// TODO 
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface VecteurDeCaracteres)

	} 


	public void insere(int rang, char caractere) throws VecteurOutException {
		// TODO 
		// l'ordre des caracteres doit etre conserve --> decalages!!!
		// il ne peut y avoir des trous
		// Si la table est pleine, il faut doubler sa capacite
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface VecteurDeCaracteres)
		if (rang < 0 || rang > taille)throw new VecteurOutException();
		if (taille == table.length) {
			table = Arrays.copyOf(table,table.length*2);
		}
		// décalage vers la droite (de la fin vers rang)
		for (int i = taille; i > rang; i--) {
			table[i] = table[i-1];

		}
		table[rang] = caractere;
		taille++;
	}

	public void ajoute(char caractere) {
		// TODO
		if (taille >= table.length){
			table = Arrays.copyOf(table, table.length *2);
		}
		table[taille] = caractere;
		taille++;
	}

	public char remplace(int rang, char caractere)throws VecteurOutException {
		if (rang < 0 || rang >= taille ) throw new VecteurOutException();
		char caracRemplace = table[rang];
		table[rang] = caractere;
		return caracRemplace;
		// TODO 
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface VecteurDeCaracteres)

	}

	public char supprime(int rang) throws VecteurOutException {
		if (rang < 0 || rang >= taille ) throw new VecteurOutException();
		char caractSupprimer = table[rang];
		for (int i = rang; i < taille-1 ; i++) {
			table[i] = table[i+1];
		}
		taille--;
		return caractSupprimer;
		// TODO 
		// l'ordre des caracteres doit etre conserve --> decalages!!!
		// il ne peut y avoir des trous
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface VecteurDeCaracteres)

	}

	// A ne pas modifier!!! Va servir pour les tests
	/**
	 * constructeur par recopie
	 * @param capacite taille physique du vecteur
	 * @param tableARecopier table contenant les caracteres a recopier
	 * @throws IllegalArgumentException si la capacite de depart est negative ou nulle
	 *                               ou si la table a recopier est null
	 *                               ou si taille physique < taille logique
	 */
	public VecteurDeCaracteresImpl(int capacite,char[]tableARecopier){
		if(capacite<=0)
			throw new IllegalArgumentException();
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		if(tableARecopier.length>capacite)
			throw new IllegalArgumentException();
		this.table=new char[capacite];
		this.taille=tableARecopier.length;
		for (int i = 0; i < tableARecopier.length; i++) {
			table[i]=tableARecopier[i];
		}
	}

	// A ne pas modifier!!! Va servir pour les tests
	public String toString(){
		String aRenvoyer="";
		if(taille>0){
			aRenvoyer+="'"+table[0]+"'";
			for (int i = 1; i < taille; i++) {
				aRenvoyer+=" "+"'"+ table[i]+"'";
			}
		}
		return aRenvoyer;
	}

}
