import java.util.List;
//calculate the word count for two documents
public class DocumentCorrelator {
	
	public void printFreqs(DataCount<String>[] file1, DataCount<String>[] file2) {
		int tempMin = (file1.length<file2.length)? file1.length : file2.length;
		//System.out.println(tempMin);
		System.out.println(String.format("%-13s", "file1 number")+"|"+String.format("%-15s", "file1 word")+
				"|"+String.format("%-13s", "file2 number")+"|"+String.format("%-15s", "file2 word"));
		System.out.println("-----------------------------------------------------------------");
		for(int i=0; i<=tempMin-1; i++)
			System.out.println(String.format("%-13s", file1[i].count)+"|"+String.format("%-15s", file1[i].data)+
					"|"+String.format("%-13s", file2[i].count)+"|"+String.format("%-15s", file2[i].data));
	}
	
	//using this helped condense the code
	public double findMetDif(DataCount<String>[] file1, DataCount<String>[] file2) {
		return (file1.length>=file2.length)? getMetDif(file1, file2): getMetDif(file2, file1);
	}
	
	//we need to return the  metric difference
	public double getMetDif(DataCount<String>[] biggerFile, DataCount<String>[] smallerFile) {
		double metDif = 0;
		double cur, bigger, smaller;
		for (int i=0; i<biggerFile.length-1; i++){
           for (int j=0; j<smallerFile.length-1; j++){
              if (biggerFile[i].data.equals(smallerFile[j].data)) {
            	  	metDif += Math.pow(((biggerFile[i].count / biggerFile[0].totalWordCount) - (smallerFile[j].count / smallerFile[0].totalWordCount)), 2);
                System.out.println(metDif);
                //DUE to totalWordCount being set to an int and not double in data count we ran into this problem (unit testing)
//        	    System.out.println(biggerFile[0].totalWordCount + "    " + smallerFile[0].totalWordCount);
//        	  	bigger = biggerFile[i].count / biggerFile[0].totalWordCount;
//        	  	smaller = smallerFile[j].count / smallerFile[0].totalWordCount;
//        	  	//System.out.println(bigger+"="biggerFile[i] +":bigger");
//        	  	System.out.println(smaller+"="+smallerFile[j].count+"/"+smallerFile[0].totalWordCount+":smaller");
//              cur=Math.pow(((biggerFile[i].count / biggerFile[0].totalWordCount) - (smallerFile[j].count / smallerFile[0].totalWordCount)), 2);
//              System.out.println(cur+": this is cur");
//                 System.out.println(biggerFile[i].count + ": " + biggerFile[i].data);
//                 System.out.println(smallerFile[j].count + ": " + smallerFile[j].data);      
              }
           }
        }
		return metDif;
	}
	
	public void compareSameWords(DataCount<String>[] file1, DataCount<String>[] file2) {
		int num = (file1.length>=file2.length)? printSameWords(file1, file2): printSameWords(file2, file1);
	}
	
	public int printSameWords(DataCount<String>[] biggerFile, DataCount<String>[] smallerFile) {
		double percentage;
		System.out.println("NOTE: Percentage=BiggerWordCount/SmallerWordCount");
		System.out.println(String.format("%-15s", "Percentage")+"|"+String.format("%-16s", "The Word")+
				"|"+String.format("%-15s", "Smaller File")+"|"+String.format("%-15s", "Bigger File"));
		for (int i=0; i<biggerFile.length-1; i++){
	           for (int j=0; j<smallerFile.length-1; j++){
	              if (biggerFile[i].data.equals(smallerFile[j].data)) { 
	            	  	percentage = (smallerFile[j].count>biggerFile[i].count)? (double)biggerFile[i].count/smallerFile[j].count: 
	            	  		(double)smallerFile[j].count/biggerFile[i].count;
	            	  	System.out.printf("%-6.2f %% match", percentage*100);
	            	  	System.out.print(" | "+String.format("%-15s", smallerFile[j].data)+"|"+String.format("%-15s", smallerFile[j].count)+"|"
	            	  	+String.format("%-15s", biggerFile[i].count) + "\n");
	              }
	           }
	        }
		return 0;
	}
	
	public static void main (String[] args) {
	if(args.length!=3) {
		System.err.println("The incorrect number of arguments are being passed through. Please try again.");
		System.exit(0);
	}
	DocumentCorrelator doc = new DocumentCorrelator();
	WordCount hamlet = new WordCount("hamlet");
	WordCount theNewAtlantis = new WordCount("the-new-atlantis");
	String[] freq1 = {args[0],"-frequency",args[1]};
	String[] freq2 = {args[0],"-frequency",args[2]};
	//pass false so it wont print extra things
	DataCount<String>[] hamletData = hamlet.countWords(freq1,false);//file1
	DataCount<String>[] theNewAtlantisData = theNewAtlantis.countWords(freq2,false);//file2
	doc.printFreqs(hamletData, theNewAtlantisData);
	doc.findMetDif(hamletData, theNewAtlantisData);
	doc.compareSameWords(hamletData, theNewAtlantisData);
	
	BinarySearchTree bst = new BinarySearchTree();
	}
}
