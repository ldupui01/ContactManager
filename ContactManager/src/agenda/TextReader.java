package agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TextReader {
	private boolean listContact;
	private boolean listMeeting;
	private ArrayList<String[]> stringImport;
	
	public TextReader(){
		this.listContact=false;
		this.listMeeting=false;
		this.stringImport = new ArrayList<String[]>();
	}
	
	public ArrayList<String[]> reader(String filename){
	    try {
	    	
	    	BufferedReader br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine(); 
	            if(line != null){
	            	 this.stringImport.add(this.dispatchInfo(line));
	            } 
	        }
	        br.close();
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	   return stringImport;
	}

	private String[] dispatchInfo(String line) {
		String[][] tagContact = {{"<#ID:",":ID#>"},{"<#NAME:",":NAME#>"},{"<#NOTE:",":NOTE#>"}};
		String[][] tagMeeting = {{"<#ID:",":ID#>"},{"<#DATE:",":DATE#>"},{"<#WITH:",":WITH#>"},{"<#NOTE:",":NOTE#>"}};
		String[] as = new String[5];
		int tagIndx = 0 ;
		int tagEndIndx = 0;
		
		try {
			if(listContact == true){
				as[4]="Contact";
				for(int i=0;i<3;i++){
					tagIndx = line.indexOf(tagContact[i][0]) + tagContact[i][0].length();
					tagEndIndx = line.indexOf(tagContact[i][1]);
					if (tagIndx >=0 && tagEndIndx > tagIndx){
						as[i] = line.substring(tagIndx, tagEndIndx );
					}
					
				}
				//System.out.println("Contact" + as[0] + as[1] + as[2]+ as[4]);
			}
			if(listMeeting == true){
				as[4]="Meeting";
				for(int i=0;i<4;i++){
					tagIndx = line.indexOf(tagMeeting[i][0]) + tagMeeting[i][0].length();
					tagEndIndx = line.indexOf(tagMeeting[i][1]);
					if (tagIndx >=0 && tagEndIndx > tagIndx){
						as[i] = line.substring(tagIndx, tagEndIndx );
					}
				}
				//System.out.println("Meeting" + as[0] + as[1] + as[2]+ as[3] + as[4]);
			}
			
			if (line.length()>20){
				if (line.substring(0, 21).equals("<#LIST:Contact:LIST#>")){
					this.listContact=true;
					this.listMeeting=false;
				}else if(line.substring(0, 21).equals("<#List:Meeting:LIST#>")){
					this.listContact=false;
					this.listMeeting=true;
				}
			}
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("The file is corrupted - program terminated");
			System.exit(0);
		}
		return as;
	}
}
