package sortcomparison;

import java.util.*;

public class BasicSorter implements Sorter {

	void swap(String[] data, int i, int j) {
		String temp = data[j];
		data[i] = data[j];
		data[j] = temp;
	}


	@Override
	public void insertionSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		for (int i = 1; i < numberToSort; i++) {
			for (int j = 0; j < i; j++) {
				if (data[firstIndex + j].compareTo(data[firstIndex + i]) > 0) {
					String temp = data[firstIndex + j];
					data[firstIndex + j] = data[firstIndex + i];
					data[firstIndex + i] = temp;
				}
			}
		}
	}


	@Override
	public void quickSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		Arrays.sort(data);
	}


	@Override
	public int partition(String[] data, int firstIndex, int numberToPartition) {
		// TODO Auto-generated method stub
		String pivot = data[firstIndex];
		int tooBigIndex = firstIndex + 1;
		int tooSmallIndex = firstIndex + numberToPartition - 1;

		while (tooBigIndex < tooSmallIndex) {

			while (tooBigIndex < tooSmallIndex && (data[tooBigIndex].compareTo(pivot)) <= 0) {
				tooBigIndex++;
			}
			while (tooSmallIndex > firstIndex && (data[tooSmallIndex].compareTo(pivot) > 0)) {
				tooSmallIndex--;
			}

			if (tooBigIndex < tooSmallIndex) {
				String temp = data[tooSmallIndex];
				data[tooSmallIndex] = data[tooBigIndex];
				data[tooBigIndex] = temp;
			}
		}

		if (pivot.compareTo(data[tooSmallIndex]) >= 0) {
			String temp = data[tooSmallIndex];
			data[tooSmallIndex] = data[firstIndex];
			data[firstIndex] = temp;
			return tooSmallIndex;
		} else {
			return firstIndex;
		}
	}


	@Override
	public void mergeSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		Arrays.parallelSort(data);
	}


	@Override
	public void merge(String[] data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();
		int ndxtemp = 0, ndxleft = firstIndex, ndxright = (firstIndex + leftSegmentSize), comp, ndx;
		String left, right;
		int sizeleft = leftSegmentSize, sizeright = rightSegmentSize;
		while ((sizeleft > 0) || (sizeright > 0)) {
			if ((sizeleft > 0) && (sizeright > 0)) {
				left = data[ndxleft];
				right = data[ndxright];
				comp = left.compareTo(right);
				if (comp <= 0) {
					temp.add(data[ndxleft++]);
					sizeleft--;
				} else {
					temp.add(data[ndxright++]);
					sizeright--;
				}
			} else {
				if (sizeleft > 0) {
					temp.add(data[ndxleft++]);
					sizeleft--;
				} else {
					temp.add(data[ndxright++]);
					sizeright--;
				}
			}
		}
		ndx = firstIndex;
		for (ndxtemp = 0; ndxtemp < temp.size(); ndxtemp++)

			data[ndx++] = temp.get(ndxtemp);
	}


	@Override
	public void heapSort(String[] data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(String[] data) {
		// TODO Auto-generated method stub

	}

}
