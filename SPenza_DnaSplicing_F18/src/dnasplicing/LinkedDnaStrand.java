package dnasplicing;

public class LinkedDnaStrand implements DnaStrand {

	int nodeCount = 0;
	int appendCount = 0;
	long nucleotideCount = 0;

	String sequenceString;

	DnaNode cursor, head, tail;


	public LinkedDnaStrand(String dnaSequence) {
		// TODO Auto-generated constructor stub
		DnaNode newNode = new DnaNode(dnaSequence);
		head = newNode;
		tail = head;
		head.previous = null;
		tail.previous = null;
		cursor = head;
		sequenceString = dnaSequence;
		nodeCount++;
	}


	@Override
	public String toString() {

		StringBuffer result = new StringBuffer("");
		DnaNode n = head;
		while (n != null) {
			result.append(n.dnaSequence);
			n = n.next;
		}
		return result.toString();
	}


	@Override
	public long getNucleotideCount() {
		// TODO Auto-generated method stub
		nucleotideCount = sequenceString.length();
		return nucleotideCount;
	}


	@Override
	public void append(String dnaSequence) {
		// TODO Auto-generated method stub

		if (dnaSequence.equals("") == false) {
			if (tail == head) {
				tail = new DnaNode(dnaSequence);
				head.next = tail;
				tail.previous = head;
			} else {
				cursor = tail;
				cursor.next = new DnaNode(dnaSequence);
				cursor.next.previous = cursor;
				tail = cursor.next;
			}
			sequenceString += dnaSequence;
			appendCount++;
			nodeCount++;

		}
	}


	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		// TODO Auto-generated method stub
		LinkedDnaStrand newLinkedStrand;
		int temp = enzyme.length();
		int pos = 0;
		int i;
		int j;
		for (j = 0; j < sequenceString.length(); j++) {
			if (sequenceString.substring(j, j + temp).equals(enzyme) == true) {
				break;
			}
		}
		if (j != 0) {
			newLinkedStrand = new LinkedDnaStrand(sequenceString.substring(0, j));
			newLinkedStrand.append(splicee);
		} else {
			newLinkedStrand = new LinkedDnaStrand(splicee);
		}
		pos = j + temp;
		for (i = j + temp; i < sequenceString.length() - temp + 1; i++) {
			if (sequenceString.substring(i, i + temp).equals(enzyme) == true) {
				if (i != 0) {
					newLinkedStrand.append(sequenceString.substring(pos, i));
				}
				newLinkedStrand.append(splicee);
				i = i + temp;
				pos = i;
			}
		}
		if (sequenceString.length() - pos > 0)
			newLinkedStrand.append(sequenceString.substring(pos, sequenceString.length()));
		return newLinkedStrand;

	}


	@Override
	public DnaStrand createReversedDnaStrand() {
		// TODO Auto-generated method stub

		LinkedDnaStrand reverseDNA;
		;
		StringBuffer temp = new StringBuffer(tail.dnaSequence);
		temp.reverse();
		reverseDNA = new LinkedDnaStrand(temp.toString());
		cursor = tail.previous;

		for (int i = 1; i < nodeCount; i++) {
			temp = new StringBuffer(cursor.dnaSequence);
			temp.reverse();
			reverseDNA.append(temp.toString());
			cursor = cursor.previous;
		}
		return reverseDNA;
	}


	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return appendCount;
	}


	@Override
	public DnaNode getFirstDnaNode() {
		// TODO Auto-generated method stub
		return head;
	}


	@Override
	public int getDnaNodeCount() {
		// TODO Auto-generated method stub
		return nodeCount;
	}

}
