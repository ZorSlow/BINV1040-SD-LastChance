// implementation de l'interface File via une table circulaire

public class FileImplViaTable<E> implements File<E>{

	private Object[] table;  // ne modifiez pas cet identifiant, la classe test l'utilise					
	private int indiceTete;  // ne modifiez pas cet identifiant, la classe test l'utilise			
	private int taille;		// ne modifiez pas cet identifiant, la classe test l'utilise	
	// N'ajoutez pas d'autres attributs, la classe test risquerait de ne pas fonctionner
	

	public FileImplViaTable(){
		table = new Object[4];
		taille = 0;
		indiceTete = 0;
	}

	public int taille(){
		return taille;
	}

	public boolean estVide(){
		return taille == 0;
	}


	public E premier()throws FileVideException{
		if(taille==0)
			throw new FileVideException();
		return (E)table[indiceTete];
	}


	public E defile() throws FileVideException{
		if (estVide())throw new FileVideException();
		E element =(E) table[indiceTete];
		indiceTete = (indiceTete+1)% table.length;
		taille--;
		return element;

		//TODO

	}


	public void enfile(E element){
		//TODO
		if (table.length == taille){
		Object[] tTable = new  Object[table.length *2];
			for (int i = 0; i < taille; i++) {
				tTable [i] = table[(indiceTete+i)%table.length];
			}
			table  = tTable;
			indiceTete = 0;
		}
		int indiceQueu = (indiceTete + taille) % table.length;
		table[indiceQueu] = element;
		taille++;
	}

}
