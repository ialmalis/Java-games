
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Kremala extends JFrame {
	
	private JPanel panel;
	private JButton easybutton;
	private JButton mediumbutton;
	private JButton hardbutton;
	private JTextField scorefield;
	private JTextField lifefield;
	private JTextField wordfield;
	private JTextField usedwordfield;
	private ArrayList<JButton> letterbuttons=new ArrayList<JButton>() ;
	private JButton newbutton;
	private JButton savebutton;
	private BorderLayout border;
	private ArrayList<String> mywordlist;
	private Score scores;
	private int lives;
	private String wordtofind;
	private int helpwins=0;
	
	public Kremala(ArrayList<String> wordlist){
		mywordlist=new ArrayList<String>(wordlist);
		JOptionPane.showMessageDialog(null, "Επιλέξτε πριν από κάθε γύρο το επίπεδο δυσκολίας και φορτώνεται η άγνωστη λέξη");
		
		panel=new JPanel();
		scorefield=new JTextField(6);
		scorefield.setEditable(false);
		wordfield=new JTextField(25);
		wordfield.setEditable(false);
		usedwordfield=new JTextField(18);
		usedwordfield.setEditable(false);
		lifefield=new JTextField(2);
		lifefield.setEditable(false);
		newbutton=new JButton("Νέο");
		savebutton=new JButton("Αποθήκευση Σκορ");
		easybutton=new JButton("Εύκολο");
		mediumbutton=new JButton("Μέτριο");
		hardbutton=new JButton("Δύσκολο");
		border=new BorderLayout();
		panel.setLayout(border);
		
		
		for(char b='Α';b<='Ω';b++)
		{
			String help=""+b;
			letterbuttons.add(new JButton(help));
		}
		
		JPanel panel2=new JPanel();
		panel2.add(new JLabel("Επίπεδο Δυσκολίας "));
		panel2.add(easybutton);
		panel2.add(mediumbutton);
		panel2.add(hardbutton);
		panel.add(panel2,BorderLayout.PAGE_START);
		
		JPanel panel3=new JPanel();
		panel3.add(new JLabel("Σκορ"));
		panel3.add(scorefield);
		panel3.add(new JLabel("Ζωές"));
		panel3.add(lifefield);
		panel.add(panel3,BorderLayout.LINE_START);
		
		JPanel panel4=new JPanel();
		panel4.add(new JLabel("Λέξη"));
		panel4.add(wordfield);
		panel4.add(new JLabel("Γράμματα"));
		panel4.add(usedwordfield);
				
		
		
		int count=0;
		for (JButton helpbutton:letterbuttons)
		{
			if (count!=17)
			panel4.add(helpbutton);
			count++;
		}
		
		panel.add(panel4,BorderLayout.CENTER);
		JPanel panel5=new JPanel();
		panel5.add(newbutton);
		panel5.add(savebutton);
		
		panel.add(panel5,BorderLayout.PAGE_END);
		
		
			
		try
		{
			Score temp=new Score();
					
		ObjectInputStream input=new ObjectInputStream(new FileInputStream("savedscores.ser"));
		temp=(Score)input.readObject();
		input.close();
		scores=new Score(temp.getScorehuman(),temp.getScorepc());
		scorefield.setText(scores.getScorehuman()+" - "+scores.getScorepc());
		
		
		}
		catch(ClassNotFoundException e)
		{
			e.getStackTrace();
		}
		
		catch(IOException e)
		{
			e.getStackTrace();
		}	
			
		for (int i=0;i<letterbuttons.size();i++)
		{
			final int j=i;
			letterbuttons.get(j).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				
				
				String help=usedwordfield.getText();
				
				Boolean test=false;
				String s2=wordfield.getText();
				
				for(int k=0;k<wordtofind.length();k++)
				{
					String s=wordtofind.substring(k, k+1);
					
					if (s.equals(letterbuttons.get(j).getText()))
					{
						test=true;
						helpwins++;
						if (k!=0)
							s2=s2.substring(0, k)+s+s2.substring(k+1);
						else
							s2=s+s2.substring(k+1);
					}
					
					
				}
					
				if (test)
				
					wordfield.setText(s2);
				else
				{
					lives-=1;
					lifefield.setText(lives+"" );
					help+=letterbuttons.get(j).getText()+",";
					usedwordfield.setText(help);
				}
				
				if (helpwins==wordtofind.length())
				{
					JOptionPane.showMessageDialog(null, "Συγχαρητήρια!!!!! Επιλέξτε επίπεδο δυσκολίας για νέα άγνωστη λέξη ");
					scores.setScorehuman(scores.getScorehuman()+1);
					scorefield.setText(scores.getScorehuman()+" - "+scores.getScorepc());
					usedwordfield.setText("");
					wordfield.setText("");
					lifefield.setText("");
				}
				if (lives==0)
				{
					JOptionPane.showMessageDialog(null, "Δυστυχώς χάσατε... Η λέξη ήταν :"+wordtofind);
					scores.setScorepc(scores.getScorepc()+1);
					scorefield.setText(scores.getScorehuman()+" - "+scores.getScorepc());
					usedwordfield.setText("");
					wordfield.setText("");
					lifefield.setText("");	
				}
						
				}});
		
		}
		
		
		savebutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try
				{
					
							
				ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("savedscores.ser"));
				output.writeObject(scores);
				output.close();
				}
				
				
				catch(IOException e1)
				{
					e1.getStackTrace();
				}	
				
														}
		});	
		
		newbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try
				{
					
				scores.setScorehuman(0);	
				scores.setScorepc(0);
				
				ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("savedscores.ser"));
				output.writeObject(scores);
				output.close();
				scorefield.setText(scores.getScorehuman()+" - "+scores.getScorepc());
				usedwordfield.setText("");
				wordfield.setText("");
				lifefield.setText("");
				
				}
				
				
				catch(IOException e1)
				{
					e1.getStackTrace();
				}	
				
														}
		});	
		
		easybutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                lives=8;
                lifefield.setText(lives+"");
                Random random=new Random();
        		int randindex=random.nextInt(mywordlist.size());
        		wordtofind=mywordlist.get(randindex);
        		String help="";
        		for (int i=0;i<wordtofind.length();i++)
        			help+="*";
        		wordfield.setText(help);
        		usedwordfield.setText("");
        		helpwins=0;	
        			
        			
        		
				
														}
		});	
		mediumbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                lives=6;
                lifefield.setText(lives+"");
                Random random=new Random();
        		int randindex=random.nextInt(mywordlist.size());
        		wordtofind=mywordlist.get(randindex);
        		String help="";
        		for (int i=0;i<wordtofind.length();i++)
        			help+="*";
        		wordfield.setText(help);
        		usedwordfield.setText("");
        		helpwins=0;	
														}
		});	
		
		hardbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                lives=4;
                lifefield.setText(lives+"");
                Random random=new Random();
        		int randindex=random.nextInt(mywordlist.size());
        		wordtofind=mywordlist.get(randindex);
        		String help="";
        		for (int i=0;i<wordtofind.length();i++)
        			help+="*";
        		wordfield.setText(help);
        		usedwordfield.setText("");	
        		helpwins=0;		
			}
		});	
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(782,300);
		this.setTitle("Κρεμάλα");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	

}
