import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Node {
	int etat;
	Node pere=null;
	List<Node> fils=new ArrayList<>();
	int cout;
	
	
	public Node(int state) {
		pere=null;
		this.etat=state;
	}
	
	public Node() {
	}
	
	public int Transformer( int tab[]) {
		int q,reste;
		int index=-1;
		int x=this.etat;
		for(int i=0;i<9;i++) {
			q=x/10;
			reste=x-q*10;
			tab[9-i-1]=reste;
			if(tab[9-i-1]==0) {
			index=9-i-1;
			}
			x=q;
		}
		return index;
		
	}
	
	
	
	public void DeplacerHaut() {
		int tab_state[]=new int[9];
		int index,tmp;
		index=Transformer(tab_state);
		if(index-3>=0) {
			tab_state[index]=tab_state[index-3];
			tab_state[index-3]=0;
			int x=0;
			for(int i=0;i<9;i++) {
				x=(int) (x+tab_state[9-i-1]*Math.pow(10, i));
			}
			Node n=new Node(x);
			n.pere=this;
			fils.add(n);	
		}
		
	}
	
	public void DeplacerBas() {
		int tab_state[]=new int[9];
		int index,tmp;
		index=Transformer(tab_state);
		if(index+3<=8) {
			tab_state[index]=tab_state[index+3];
			tab_state[index+3]=0;
			int x=0;
			for(int i=0;i<9;i++) {
				x=(int) (x+tab_state[9-i-1]*Math.pow(10, i));
			}
			Node n=new Node(x);
			n.pere=this;
			fils.add(n);	
		}
		
	}
	
	public void DeplacerDroite() {
		int tab_state[]=new int[9];
		int index,tmp;
		index=Transformer(tab_state);
		if(index%3!=2) {
			tab_state[index]=tab_state[index+1];
			tab_state[index+1]=0;
			int x=0;
			for(int i=0;i<9;i++) {
				x=(int) (x+tab_state[9-i-1]*Math.pow(10, i));
			}
			Node n=new Node(x);
			n.pere=this;
			fils.add(n);	
		}
		
	}
	
	public void DeplacerGauche() {
		int tab_state[]=new int[9];
		int index,tmp;
		index=Transformer(tab_state);
		if(index%3!=0) {
			tab_state[index]=tab_state[index-1];
			tab_state[index-1]=0;
			int x=0;
			for(int i=0;i<9;i++) {
				x=(int) (x+tab_state[9-i-1]*Math.pow(10, i));
			}
			Node n=new Node(x);
			n.pere=this;
			fils.add(n);	
		}
		
	}
	
	public void GetSuccesseur() {
		this.DeplacerHaut();
		this.DeplacerBas();
		this.DeplacerDroite();
		this.DeplacerGauche();
	}
	
	public int Hachage() {
		int cle=0;
		int q,reste;
		int x=this.etat;
		for(int i=0;i<9;i++) {
			q=x/10;
			reste=x-q*10;
			cle=(int) (cle+reste*Math.pow(8-i, 6));
			x=q;
		}
		
		return cle;
	}
	
	// Premiere heuristique retourne le nombre de cases mal placées dans l'etat
	/*public int Heuristique1(Node but) {
		int resultat=0;
		int x=this.etat;
		int goal=but.etat;
		int q1,q2,reste1,reste2;
		for(int i=0;i<9;i++) {
			q1=x/10;
			reste1=x-q1*10;
			q2=goal/10;
			reste2=goal-q2*10;
			if(reste1!=reste2) {
				resultat++;
			}
			
		}
		
		return resultat;
	}*/
	
	public int Heuristique1(Node but) {
		int resultat=0;
		int tab_state[]=new int[9];
		int tab_state1[]=new int[9];
		int index;
		index=this.Transformer(tab_state);
		index=but.Transformer(tab_state1);
		for(int i=0;i<9;i++) {
			if(tab_state[i]!=tab_state1[i]) {
				resultat++;
			}
		}
		return resultat;
		
	}
	
	public int Heuristique2(Node but){
        int resultat=0;
        int tab_state[]=new int[9];
    int tab_state1[]=new int[9];
        but.Transformer(tab_state1);
        this.Transformer(tab_state);
        int i,j,y=0;
        for ( i=0;i<9;i++){
            int a=tab_state[i];
            for( j=0;j<9;j++){
                if(tab_state1[j]==a){
                    y=j;
                    break;
                }
            }
                resultat=resultat+Math.abs((i%3)-(y%3))+Math.abs((i/3)-(y/3));
            
        }
        return resultat;
    }
	
	public int Cout() {
		int cout=0;
		Node tmp=this.pere;
		while(tmp!=null) {
			cout++;
			tmp=tmp.pere;
		}
		return cout;
	}
	
	public String toString() {
		int tab_state[]=new int[9];
		int index;
		index=Transformer(tab_state);
		String output = "";

        for (int x = 0; x < 9; x++) {
            
            output += tab_state[x];
            if(x%3==2) {
            	output += "\n";
            }
            
            
        }

        return output;
		
	}
	
    
	
	
	
   

}
