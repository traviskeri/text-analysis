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
	
	public void wordToWord() {
		
	}
	
	public static void main (String[] args) {
	if(args.length!=3) {
		System.err.println("The incorrect number of arguments are being passed through. Please try again.");
		System.exit(0);
	}
	DocumentCorrelator doc = new DocumentCorrelator();
	WordCount hamlet = new WordCount("hamlet");
	WordCount theNewAtlantis = new WordCount("the-new-atlantis");
	//avl
	String[] freq1 = {args[0],"-frequency",args[1]};
	String[] freq2 = {args[0],"-frequency",args[2]};
	//pass false so it wont print extra things
	DataCount<String>[] hamletData = hamlet.countWords(freq1,false);//file1
	DataCount<String>[] theNewAtlantisData = theNewAtlantis.countWords(freq2,false);//file2
	doc.printFreqs(hamletData, theNewAtlantisData);
	
	
	BinarySearchTree bst = new BinarySearchTree();
	}
}
