import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar {
	Node etatInitial;
	Node etatfinal;
	//Queue<Node> ouvert=new LinkedList<Node>();
	public PriorityQueue<Node> ouvert;
	public HashMap<Integer,Node> ferme; 
	public List<Node> CheminResollution=new ArrayList<Node>();
	int nbrNoeuds;
	
	public Astar(Node initial , Node but) {
		this.etatInitial=initial;
		this.etatfinal=but;
		ferme = new HashMap<Integer,Node>();
	}
	
	public void Recherche_Astar(int choix) {
		Comparator<Node> coutComparator1 = new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return (n1.Heuristique1(etatfinal)+n1.cout) - (n2.Heuristique1(etatfinal)+n2.cout);
            }
        };
                // Deuxieme comparator
                Comparator<Node> coutComparator2 = new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return (n1.Heuristique2(etatfinal)+n1.cout) - (n2.Heuristique2(etatfinal)+n2.cout);
            }
        };
             
                this.nbrNoeuds=0;
        if(choix==1){
            ouvert =new PriorityQueue<>(coutComparator1);
        }else{
            ouvert =new PriorityQueue<>(coutComparator2);
        }
		// Inserer l'etat initial dans la file 
		List<Node> successeur=new ArrayList<Node>();
		ouvert.add(etatInitial);
		Node courrant=new Node();
		boolean trouve=false;
		while(!ouvert.isEmpty() && !trouve) {
			courrant=ouvert.poll();
                        
			System.out.println(courrant.toString());
			//int j=(courrant.Heuristique(etatfinal)+courrant.Cout());
			//System.out.println("Cout= "+ j);
			ferme.put(courrant.Hachage(), courrant);
			//System.out.println("etat but !");
			// Si on est arrivé a l'etat final
			if(courrant.etat==etatfinal.etat) {
				List<Node> Resollution=new ArrayList<Node>();
				CheminDeResollution(this.CheminResollution,courrant);
				
				
				System.out.println("etat but !");
				trouve=true;
			}else {// si l'etat courrant n'est pas l'etat but
                            this.nbrNoeuds++;
				courrant.GetSuccesseur();
				successeur=courrant.fils;
				for(Node tmp: successeur) {
					boolean visite=ferme.containsKey(tmp.Hachage());
					if(!visite) {// si le fils n'existe pas dans la table ferme
						if(!ouvert.contains(tmp)) {
							//tmp.cout=tmp.Heuristique1(this.etatfinal)+tmp.Cout();
							ouvert.add(tmp);
							
						}
					}
				}
				
				
			}
			
			
		}
		
	}
	
	public void CheminDeResollution(List<Node> chemin,Node but) {
		Node tmp=new Node();
		tmp.etat=but.etat;
		tmp.pere=but.pere;
		chemin.add(tmp);
		while(tmp.pere!=null) {
			tmp=tmp.pere;
			chemin.add(tmp);
		}
		
	}

}
