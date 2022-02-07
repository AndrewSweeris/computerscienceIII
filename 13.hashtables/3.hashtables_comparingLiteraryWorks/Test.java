public class Test {

	public static void main(String[] args) {
		FileInput.init();

		ChainingHash ch = new ChainingHash();
		QPHash qp = new QPHash();

		String[] bacon = FileInput.readBacon();
		String[] hamlet = FileInput.readShakespeare();

		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		for (String str : bacon) {
			ch.insert(str);
		}
		for (String str : hamlet) {
			qp.insert(str);
		}

		double TSE = 0.0;
		String MDW = "";
		double greatestDif = -1;

		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		Object tempObj;
		double count, temp;
		for (int i = 0; i < ch.getSize(); i++) { // For ChainingHash
			tempObj = ch.getNextKey();
			if (tempObj!=null) {
			count = ch.findCount(tempObj) / ch.getSize();
			temp = Math.abs(count - qp.findCount(tempObj) / qp.getSize());
			TSE = TSE + Math.pow(temp, 2);
			if (temp>greatestDif) {
				greatestDif=temp;
				MDW=tempObj.toString();
			}}
		}

		for (int i = 0; i < qp.getSize(); i++) {
			tempObj = qp.getNextKey();
			if (tempObj!=null) {
			if (ch.findCount(tempObj)==0) {
				count = Math.pow(qp.findCount(tempObj) / qp.getSize(),2);
				TSE = TSE + Math.pow(count, 2);
				if (count>greatestDif) {
					greatestDif=count;
					MDW=tempObj.toString();
				}}
			}
		}
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
		System.out.println("Total Square Error: " + Double.toString(TSE) + "\nMost different word: " + MDW.toString());
	}

}
