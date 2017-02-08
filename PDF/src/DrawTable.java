public class DrawTable {
	
	public static int tableSize = 300 , t = 1 , z = 0;
	public String[][] shades;
	
	public DrawTable(){

		shades = new String[tableSize][3];
		

		for (int r=0; r<shades.length; r++) {
		    for (int c=0; c<shades[r].length; c++) {
		        shades[r][c]="";
		    }
		}
			
		shades[0][0] = "ACCOUNT NAME";
		shades[0][1] = "PROBLEM LOCATION";
		shades[0][2] = "PROBLEM";

	}
	
	public void addVal(String accountName, String problemPlace , String problem){
		
	     shades[t][z] = accountName;
	     shades[t][z+1] = problemPlace;
	     shades[t][z+2] = problem;
	     
	     t++;
	}
	
	public String[][] getTable(){
		
		return shades;
		
	}
}