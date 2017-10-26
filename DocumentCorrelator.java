import java.util.List;

public class DocumentCorrelator {
	
//	private <T<String>> chooseDataStruct(String dataStruct){
//		switch(dataStruct) {
//			case "-a":
//				return new AVL<String>();
//			case "-h":
//				return new BinarySearchTree<String>();
//			default:
//				return new BinarySearchTree<String>();
//		}
//	}
	
	//uses freq
	private void assignFreq(WordCount wordCount) {
		
	}
	
	//uses num_unique
	private void assignUniq() {
		
	}
	
	public static void main (String[] args) {
	if(args.length!=3) {
		System.err.println("The incorrect number of arguments are bing passed through. Please try again.");
		System.exit(0);
	}
	
	WordCount hamlet = new WordCount("hamlet", "-b");
	WordCount theNewAtlantis = new WordCount("the-new-atlantis", "-b");
	
	//avl
	String[] avlFreq = {"-a","-frequency","hamlet.txt"};
	AVL avlHamlet = new AVL();
	
	//hamlet.countWords(avlFreq);
	
	//hash
	
	
	//bst
	BinarySearchTree bst = new BinarySearchTree();
	}
}
