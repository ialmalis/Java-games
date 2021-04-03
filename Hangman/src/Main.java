import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;



public class Main {

	public static void main(String[] args) {
		
		Collection<String> ListWords=new HashSet<String>();
		
		
		try
			{
				File myfile=new File("wikipedia.txt");
				FileReader freader =new FileReader (myfile);
				BufferedReader breader=new BufferedReader(freader);
				
				
				
				String line=breader.readLine();
				while (line!=null)
				{
					
					String[] helpstring=line.split(" ");
					for (int i=0;i<helpstring.length;i++)
					{
						
						if ((helpstring[i].length() >3)&& !(containsAnyWrong(helpstring[i])))
						{
							
								helpstring[i]=replacetonous(helpstring[i]);
								helpstring[i]=helpstring[i].toUpperCase();
								if (!(checkForeign(helpstring[i])))
									ListWords.add(helpstring[i]);
							
							
						}
							
					}		
					
					line=breader.readLine();
				}
				
			
				freader.close();
				breader.close();
				
				ArrayList<String> newwordlist=new ArrayList<String>(ListWords);
				
				new Kremala(newwordlist);
			
				
			}
		catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}

	}
	
	public static boolean containsAnyWrong(String s) {
	      char[] c={'1','2','3','4','5','6','7','8','9','0','.','%','[',']','(',')','{','}',':','/','*',',','-','"','«','—'};
	      
	      	    		  
	      
	      for (int i = 0; i < s.length(); i++) 
	      {
	          char ch = s.charAt(i);
	          
	                	  
	          for (int j = 0; j < c.length; j++) 
	          {
	             
	        	  if (c[j] == ch) 
	              {
	                  return true;
	              }
	          }
	      }
	      return false;
	  }
	
	public static String replacetonous(String s){
		s=s.replace('Ü','á');
		s=s.replace('¢','Á');
		s=s.replace('Ý','å');
		s=s.replace('¸','Å');
		s=s.replace('Þ','ç');
		s=s.replace('¹','Ç');
		s=s.replace('ý','õ');
		s=s.replace('¾','Õ');
		s=s.replace('ß','é');
		s=s.replace('º','É');
		s=s.replace('ü','ï');
		s=s.replace('¼','Ï');
		s=s.replace('þ','ù');
		s=s.replace('¿','Ù');
		
		return s;
		
	}
	
	public static Boolean checkForeign(String s){
		
		 char ch = s.charAt(0);
         
   	  
		 for(char alphabet = 'A'; alphabet <= 'Z';alphabet++)
		 {
			   if (ch==alphabet)
				   return true;
		 }
     
     return false;
		
	}
	
}
