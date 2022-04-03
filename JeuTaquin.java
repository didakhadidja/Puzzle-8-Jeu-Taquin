/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Khadija
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager; 
public class JeuTaquin extends javax.swing.JFrame {

	
	public String timespeed="moyen";
	
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(51, 0, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("1");
        //jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(null));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    private JPanel contentPane;
	private JTextField nbretat;
	private JTextField tempsexecution;
	private JTextField txtInitial;
	private JTextField txtFinal;
    public Node etatInitial=new Node();
    public Node etatFinal=new Node();
   // public String timespeed="moyen";
    Thread animationThread;
    public boolean ready=false ;
   
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JeuTaquin frame = new JeuTaquin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
/**
 * @throws InterruptedException ***********************************************************************************/
	 public void setTimerSpeed(String speed) throws InterruptedException{
	       if(speed.equals("moyen")) {
	    	   Thread.sleep(700);
	    	   }else {
	    	   if(speed.equals("lent")) {
	    		   Thread.sleep(1100);
	    		   
	    	   }else {
	    		   
	    		   Thread.sleep(200);
	    	   }
	    	   
	       }
	    }	
/******************************LA METHODE CHOISIR *************************************/
	public void choisirMethode(String methode ) {
		fermerpanel.show();
		
		
	   if (methode.equals("A*")) {
		/**********************************************************************************/
		   //APanel.show();
		   int choix=1;
	    	Astar a=new Astar(etatInitial,etatFinal);
	    	long startTime = System.nanoTime();
	    	
	    	
	    	 a.Recherche_Astar(1);
			 tempsexecution.setText(""+(System.nanoTime()-startTime)+" ns");
			List<Node> chemin=new ArrayList<>();
			Node tmp=new Node();
			chemin=a.CheminResollution;
			 nbretat.setText(""+chemin.size());
			 fermerlabel.setText(""+a.ferme.size());
			for(int j=0;j<chemin.size();j++) {
				tmp=chemin.get(chemin.size()-j-1);
				tmp.Transformer(state);
				//etatInitial.Transformer(state);
				try {
					setTimerSpeed(timespeed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 for(int i=0;i<9;i++) {
					 tab[i].setText(""+state[i]);
					 //tab[i].setBounds(m,n,60,60);
					 tab[i].setVisible(true);
					 if(state[i]==0) {
						 tab[i].setVisible(false);
					 }
				 }
				 
			    System.out.println("attendre");
			}
			
	    	
	    	
		   /*******************************************************************************/
		   
	   }else {
		   if(methode.equals("BFS")) {
			   
		/****************************************************************************************/
			   Bfs b=new Bfs();
		         Node resultat=new Node();
		         long startTime = System.nanoTime();
		         
		        resultat=b.StartBfs(etatInitial,etatFinal);
		         
		        tempsexecution.setText(""+(System.nanoTime()-startTime)+" ns");
		         b.CheminDuResolution(resultat);
		         
		         int size=b.getChemin().size();
		         nbretat.setText(""+size);
		         Node tmp=new Node();
		         fermerlabel.setText(""+b.getFerme().size());
		         for(int j=0;j<size;j++) {
						tmp=b.getChemin().get(size-j-1);
						tmp.Transformer(state);
						//etatInitial.Transformer(state);
						try {
							setTimerSpeed(timespeed);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 for(int i=0;i<9;i++) {
							 tab[i].setText(""+state[i]);
							 //tab[i].setBounds(m,n,60,60);
							 tab[i].setVisible(true);
							 if(state[i]==0) {
								 tab[i].setVisible(false);
							 }
						 }
						 
					    System.out.println("attendre");
					}
/*********************************************************************************/			   
			   
		   }else {
/*********************************************************************************/
			   Dfs b=new Dfs();
		         Node resultat=new Node();
		         long startTime = System.nanoTime();
		        resultat=b.StartDfs(etatInitial,etatFinal);
		        tempsexecution.setText(""+(System.nanoTime()-startTime)+" ns");
		         b.CheminDuResolution(resultat);
		         int size=b.way.size();
		         nbretat.setText(""+size);
		         fermerlabel.setText(""+b.ferme.size());
		         Node tmp=new Node();
		         for(int j=0;j<size;j++) {
						tmp=b.way.get(size-j-1);
						tmp.Transformer(state);
						//etatInitial.Transformer(state);
						try {
							setTimerSpeed(timespeed);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 for(int i=0;i<9;i++) {
							 tab[i].setText(""+state[i]);
							 //tab[i].setBounds(m,n,60,60);
							 tab[i].setVisible(true);
							 if(state[i]==0) {
								 tab[i].setVisible(false);
							 }
						 }
						 
					    System.out.println("attendre");
					}		   
			   
/************************************************************************************/			   
			   
			   
			   
			   
			   
			   
		   }
		   
		   
		   
	   }
		
	}
	
	
	
	int state[]=new int[9];
	JButton tab[];
	public JeuTaquin() {
		setTitle("Jeu Taquin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		APanel = new JPanel();
		layeredPane.add(APanel, "name_90808392720000");
		APanel.setLayout(null);
		
		JRadioButton h1 = new JRadioButton("H 1");
		h1.setBounds(57, 7, 57, 23);
		APanel.add(h1);
		
		JRadioButton h2 = new JRadioButton("H 2");
		h2.setBounds(162, 7, 52, 23);
		APanel.add(h2);
		ButtonGroup g=new ButtonGroup();
		g.add(h1);
		g.add(h2);
		
		
		*/
		JComboBox methode = new JComboBox();
		methode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m=methode.getSelectedItem().toString();
				if(m.equals("DFS")) { switchpanel(dfsPanel);}else { 
					if(m.equals("A*")) {switchpanel(APanel);}else {switchpanel(panel);}
				}
			}
		});
		methode.setModel(new DefaultComboBoxModel(new String[] {"----", "BFS", "DFS", "A*"}));
		methode.setBounds(458, 183, 125, 28);
		contentPane.add(methode);
		
		JLabel lblNewLabel = new JLabel("Choisissez un algorithme :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(458, 150, 155, 34);
		contentPane.add(lblNewLabel);
		
		Node noeud=new Node(123456780);
		
		noeud.Transformer(state);
		tab=new JButton[9];
		int x=65;
		int y=100;
		for(int i=0;i<9;i++) {
			MyButton btnNewButtonn =  new MyButton();
			btnNewButtonn.setLabel(""+state[i]);
			btnNewButtonn.setForeground(Color.WHITE);
			btnNewButtonn.setFont(new Font("Tahoma", Font.BOLD, 38));
			//btnNewButtonn.setBackground(new Color(72, 61, 139));
			btnNewButtonn.setBounds(x, y,80, 80);
			btnNewButtonn.disable();
			
			if(state[i]==0) {
				btnNewButtonn.setVisible(false);
			}
			tab[i]=btnNewButtonn;
			if(i%3==2) {
				y=y+95;
				x=65;
			}else {
				x=x+95;
			}
			
			
		}
		
		for(int i=0;i<9;i++) {
			contentPane.add(tab[i]);
		}
		
		
		nbretat = new JTextField();
		nbretat.setEditable(false);
		nbretat.setForeground(new Color(0, 0, 0));
		nbretat.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		nbretat.setHorizontalAlignment(SwingConstants.CENTER);
		nbretat.setBounds(458, 307, 125, 28);
		contentPane.add(nbretat);
		nbretat.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				animationThread= new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String m=methode.getSelectedItem().toString();
						if(!m.equals("----")) {
							if(ready && !(txtInitial.getText().equals(""))&& !(txtFinal.getText().equals(""))) {
								choisirMethode(m);
							}
						}else {
							if(m.equals("----")) {
								JOptionPane.showMessageDialog(null,"choisissez une methode de résolution ","warning",JOptionPane.INFORMATION_MESSAGE);									
							}
		
					
						}
						
					}//fin run
					
				});
				animationThread.start();
			}
		});
		btnStart.setBounds(458, 249, 125, 28);
		contentPane.add(btnStart);
		
		JLabel lblNombreDtapes = new JLabel("Nombre d'\u00E9tapes :");
		lblNombreDtapes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreDtapes.setBounds(458, 277, 139, 28);
		contentPane.add(lblNombreDtapes);
		
		JLabel lblTempsDexecution = new JLabel("Temps d'execution :");
		lblTempsDexecution.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTempsDexecution.setBounds(458, 335, 139, 28);
		contentPane.add(lblTempsDexecution);
		
		tempsexecution = new JTextField();
		tempsexecution.setEditable(false);
		tempsexecution.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		tempsexecution.setHorizontalAlignment(SwingConstants.CENTER);
		tempsexecution.setColumns(10);
		tempsexecution.setBounds(458, 364, 125, 28);
		contentPane.add(tempsexecution);
		
		txtInitial = new JTextField();
		txtInitial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			 char c=e.getKeyChar();
			 if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SPACE) 
					 ||(c==KeyEvent.VK_DELETE))) 
			 {e.consume();}
			}
			
			
		});
		txtInitial.setBounds(458, 33, 125, 28);
		contentPane.add(txtInitial);
		txtInitial.setColumns(10);
		
		txtFinal = new JTextField();
		txtFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				 char c=e.getKeyChar();
				 
				 if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SPACE) ||(c==KeyEvent.VK_DELETE))) 
				 {e.consume();}
			}
		});
		txtFinal.setColumns(10);
		txtFinal.setBounds(458, 72, 125, 28);
		contentPane.add(txtFinal);
		
		JButton btnInitialiser = new JButton("Initialiser");
		btnInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermerpanel.hide();
				if(txtInitial.getText().equals("")||txtFinal.getText().equals("")) {
					//JOptionPane.showMessageDialog(this,"hhh");
					JOptionPane.showMessageDialog(null,"un des champs est vide","warning",JOptionPane.INFORMATION_MESSAGE);
				}else{
				
				if((txtInitial.getText().length()<8) ||
						(txtFinal.getText().length()<8) ) {
					JOptionPane.showMessageDialog(null,"l'etat initial ou but doivent contenir au minimum 8 chiffres","warning",JOptionPane.INFORMATION_MESSAGE);
					}else {
						if((txtInitial.getText().length()>9)  ||
								(txtFinal.getText().length()>9 )) {
							JOptionPane.showMessageDialog(null,"l'etat initial ou but doivent contenir au maximum 9 chiffres","warning",JOptionPane.INFORMATION_MESSAGE);
						}else {	
							
						int init =Integer.parseInt(txtInitial.getText());
						int f=Integer.parseInt(txtFinal.getText());
						if(!is_valide(init) || !is_valide(f)) {
	JOptionPane.showMessageDialog(null,"Etat Initial ou But mal formé","warning",JOptionPane.INFORMATION_MESSAGE);	
						}else {
				int m=65;
				int n=100;
			 etatInitial.etat=init;
			 etatFinal.etat=f;
			 ready=true;
				//etatInitial.etat=123045678;
			 //System.out.println(etatInitial.etat);
			 etatInitial.Transformer(state);
			 for(int i=0;i<9;i++) {
				 tab[i].setText(""+state[i]);
				 //tab[i].setBounds(m,n,60,60);
				 tab[i].setVisible(true);
				 if(state[i]==0) {
					 tab[i].setVisible(false);
				 }
			 } 
			 nbretat.setText("");
			 tempsexecution.setText(""); }} }// fin de else de taille 9
			}}
		});
		btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInitialiser.setBounds(458, 111, 125, 28);
		contentPane.add(btnInitialiser);
		
		MyButton speedbtn = new MyButton(new Color(19, 175, 195));
		speedbtn.setLabel("moyen");
		speedbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(speedbtn.getLabel().toString().equals("moyen")) {speedbtn.setLabel("lent");}else 
				{ if(speedbtn.getLabel().toString().equals("lent")) {speedbtn.setLabel("rapide");}else {
					speedbtn.setLabel("moyen");
				}
				}
				timespeed=speedbtn.getLabel().toString();
			}
		});
		speedbtn.setBounds(458, 403, 125, 27);
		contentPane.add(speedbtn);
		
		JLabel lblNewLabel_1 = new JLabel(" Etat initial");
		lblNewLabel_1.setBounds(387, 40, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Etat but ");
		lblNewLabel_2.setBounds(399, 79, 49, 14);
		contentPane.add(lblNewLabel_2);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(399, 210, 242, 40);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		layeredPane.add(panel, "name_90803057616000");
		
		dfsPanel = new JPanel();
		layeredPane.add(dfsPanel, "name_90805855469600");
		dfsPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Profondeur :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(47, 12, 75, 14);
		dfsPanel.add(lblNewLabel_3);
		
		profondeur = new JTextField();
		profondeur.setFont(new Font("Tahoma", Font.PLAIN, 13));
		profondeur.setBounds(139, 8, 47, 20);
		dfsPanel.add(profondeur);
		profondeur.setColumns(10);
		
		APanel = new JPanel();
		layeredPane.add(APanel, "name_90808392720000");
		//contentPane.add(APanel);
		APanel.setLayout(null);
		
		JRadioButton h1 = new JRadioButton("H 1");
		h1.setBounds(57, 7, 57, 23);
		APanel.add(h1);
		
		JRadioButton h2 = new JRadioButton("H 2");
		h2.setBounds(162, 7, 52, 23);
		APanel.add(h2);
		ButtonGroup g=new ButtonGroup();
		g.add(h1);
		g.add(h2);
		
		
		JButton randomiser = new JButton("randomiser");
		randomiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermerpanel.hide();
				ready=true ;
				int[] init=randomize();
				int[] f=randomize();
				etatInitial.etat=to_int(init);
				 etatFinal.etat=to_int(f);
				txtInitial.setText(""+to_int(init));
				txtFinal.setText(""+to_int(f));
				for(int i=0;i<9;i++) {
					
					 tab[i].setText(""+init[i]);
					 //tab[i].setBounds(m,n,60,60);
					 tab[i].setVisible(true);
					 if(init[i]==0) {
						 tab[i].setVisible(false);
					 }
				 }
				nbretat.setText("");
				 tempsexecution.setText(""); 
			}
		});
		randomiser.setBounds(139, 46, 125, 28);
		contentPane.add(randomiser);
		
		fermerpanel = new JPanel();
		fermerpanel.setBounds(25, 377, 389, 53);
		contentPane.add(fermerpanel);
		fermerpanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("l'ensemble ferm\u00E9 : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(10, 24, 139, 14);
		fermerpanel.add(lblNewLabel_4);
		fermerpanel.hide();
		fermerlabel = new JTextField();
		fermerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		fermerlabel.setForeground(new Color(0, 0, 139));
		fermerlabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		fermerlabel.setEditable(false);
		fermerlabel.setBounds(159, 22, 115, 20);
		fermerpanel.add(fermerlabel);
		fermerlabel.setColumns(10);
	}
    public void switchpanel(JPanel p) {
    	
    	layeredPane.removeAll();
    	layeredPane.add(p);
    	layeredPane.repaint();
    	layeredPane.revalidate();
    }
      public boolean is_valide(int nbr) 
      {
  		boolean TabBool[] =new boolean[9];
  		int Tab[] =new int[9];
  		this.Transformer(Tab,nbr);
  		//long startTime = System.nanoTime();
  		for(int i=0;i<9;i++) {
  			if((Tab[i]==9)) return false ; 
  			if(TabBool[Tab[i]]) return false ;
  			TabBool[Tab[i]]=true ;
  			
  		}  
  		return true ;
      
      }   
      public int Transformer( int tab[],int nombre ) {
  		int q,reste;
  		int index=-1;
  		
  		for(int i=0;i<9;i++) {
  			q=nombre/10;
  			reste=nombre-q*10;
  			tab[9-i-1]=reste;
  			if(tab[9-i-1]==0) {
  			index=9-i-1;
  			}
  			nombre=q;
  		}
  		return index;
  		
  	}
      public int[]  randomize() {
    	  int max=8;
  		int min =0;
  		boolean TabBool[] =new boolean[9];
  		Random rand=new Random();
  		int Tab[] =new int[9];
  	
  		//long startTime = System.nanoTime();
  		for(int i=0;i<9;i++) {
  			int c;
  			while(TabBool[c=rand.nextInt(max - min + 1) + min]);
  			Tab[i]=c;
  			
  			TabBool[c]=true ;
  		}
  		
	          return Tab;  
      }
      public int to_int(int[] tab) {
    	  int x=0;
    	 
			for(int i=0;i<9;i++) {
				
				x=(int) (x+tab[9-i-1]*Math.pow(10, i));
			}
			return x ;
      }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private JLayeredPane layeredPane;
    private JTextField profondeur;
    private JPanel panel;
    private JPanel dfsPanel;
    private JPanel APanel;
    private JRadioButton h1;
    private JRadioButton h2;
    private ButtonGroup g;
    private JPanel fermerpanel;
    private JTextField fermerlabel;
}