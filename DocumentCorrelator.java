//1. LSI with 1% of top and .001% of bottom what if words don't match? Lets say we have one file with 1000 unique words and another
//with 5000 unique words and they only have 950 match do we take off the top 1% of the words with the highest frequencies from this 950?
//2. If the files don't have a matching word should is it possible for this to be put in the result of the running sum?  
import java.util.Arrays;
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
	
	//met diff with lSI
	public double findMetDifLSI(DataCount<String>[] file1, DataCount<String>[] file2) {
		file1 = latentSemanticIndexing(file1);
		file2 = latentSemanticIndexing(file2);
		return findMetDif(file1, file2);
	}
	
	//using this helped condense the code
	public double findMetDif(DataCount<String>[] file1, DataCount<String>[] file2) {
		return (file1.length>=file2.length)? getMetDif(file1, file2): getMetDif(file2, file1);
	}
	
	//we need to return the  metric difference 
	public double getMetDif(DataCount<String>[] biggerFile, DataCount<String>[] smallerFile) {
		double metDif = 0;
		for (int i=0; i<biggerFile.length-1; i++){
           for (int j=0; j<smallerFile.length-1; j++){
              if (biggerFile[i].data.equals(smallerFile[j].data)) {
            	  	metDif += Math.pow(((biggerFile[i].count / biggerFile[0].totalWordCount) - (smallerFile[j].count / smallerFile[0].totalWordCount)), 2);
                System.out.println(metDif*100);
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
	            	  	System.out.print(" | "+String.format("%-15s", smallerFile[j].data)+"|"+String.format("%-15s", smallerFile[j].count+" Smaller")+"|"
	            	  	+String.format("%-15s", biggerFile[i].count+ " Bigger") + "\n");
	              }
	           }
	        }
		System.out.println("Please go to top to see a key to explain this table");
		return 0;
	}
	
	
	
		//get the number of word for a specific count
		public int numWordsWithCount(DataCount<String>[] dataCount,int freq) {
			int numSameFreq=0;
			for(DataCount<String> c: dataCount)
				numSameFreq += (c.count==freq)? 1: 0;
			return numSameFreq;
		}
		
		//take in 1 dataCount and then cut off words 1% above and 0.01% below
		public DataCount<String>[] cutOffTopAndBot(DataCount<String>[] dataCount) {
			//int divTop = (int)(dataCount.length / Math.pow(10, Math.log10(dataCount.length)-2));
			int divTop = (int)(dataCount.length / Math.pow(10, Math.log10(dataCount.length)-2));
			int cutTop = (dataCount.length%divTop>=50)? dataCount.length/divTop+1: dataCount.length/divTop;
			System.out.println("The Top 1%: "+cutTop);
			int divBot = (int)(dataCount.length / Math.pow(10, Math.log10(dataCount.length)));
			System.out.println(divBot);
			int cutBot = (dataCount.length%divBot>=50)? dataCount.length/divBot+1: dataCount.length/divBot;
			System.out.println("The Bot 0.01%: "+cutBot);
			return dataCount;
		}
		
		//take in 1 dataCount and then remove normalized word frequencies for the top 1% and bottom .01%
		public DataCount<String>[] latentSemanticIndexing(DataCount<String>[] dataCount) {
			int indexTop=0; int indexBot =dataCount.length-1;
			int totalWords = totalWords(dataCount);
			double maxTop = (totalWords*0.01);
			double minBot = (totalWords*0.0001);
			double checkTop = maxTop%1;
			double checkBot = minBot%1;
			int chopTop = (checkTop>=.5)?(int)maxTop+1: (int)maxTop;//frequency can't be higher then
			int chopBot = (checkBot>=.5)?(int)minBot+1: (int)minBot;//frequency can't be lower then
			System.out.println("divTop="+maxTop+" checkTop="+checkTop+" totTop="+chopTop);
			System.out.println("divBot="+ minBot+" checkBop="+checkBot+" totBot="+chopBot);
			while(chopTop<dataCount[indexTop].count) 
				indexTop++;
			while(chopBot>dataCount[indexBot].count) 
				indexBot--;
			DataCount[] newDataCount = Arrays.copyOfRange(dataCount, indexTop, indexBot);
			newDataCount[0].totalWordCount = dataCount[0].totalWordCount;
			System.out.println(newDataCount[0].totalWordCount+" the new totalWordCount");
//			newDataCount[0].totalWordCount = indexBot-indexTop;
//			System.out.println(newDataCount[0].totalWordCount+" the new totalWordCount");
			return newDataCount;
		}
		
		public int totalWords(DataCount<String>[] dataCount){
			int total = 0;
			for(DataCount<String> c: dataCount)
				total+=c.count;
			return total;
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
//	doc.printFreqs(hamletData, theNewAtlantisData);
	doc.findMetDif(hamletData, theNewAtlantisData);
	doc.findMetDifLSI(hamletData, theNewAtlantisData);
//	doc.compareSameWords(hamletData, theNewAtlantisData);
//	System.out.println(doc.numWordsWithCount(hamletData, 102));//when hamlet.txt passed with 102 or 99 we should get back 2
//	System.out.println(doc.totalWords(hamletData));
	System.out.println(doc.latentSemanticIndexing(hamletData));
	//doc.latentSemanticIndexing(hamletData).countWords(freq1, false);
	
	}
}
//DUE to totalWordCount being set to an int and not double in data count we ran into this problem (unit testing)
//System.out.println(biggerFile[0].totalWordCount + "    " + smallerFile[0].totalWordCount);
//	bigger = biggerFile[i].count / biggerFile[0].totalWordCount;
//	smaller = smallerFile[j].count / smallerFile[0].totalWordCount;
//	//System.out.println(bigger+"="biggerFile[i] +":bigger");
//	System.out.println(smaller+"="+smallerFile[j].count+"/"+smallerFile[0].totalWordCount+":smaller");
//cur=Math.pow(((biggerFile[i].count / biggerFile[0].totalWordCount) - (smallerFile[j].count / smallerFile[0].totalWordCount)), 2);
//System.out.println(cur+": this is cur");
// System.out.println(biggerFile[i].count + ": " + biggerFile[i].data);
// System.out.println(smallerFile[j].count + ": " + smallerFile[j].data); 