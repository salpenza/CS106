package sortcomparison;

import java.util.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		for (int i = 1; i < numberToSort; i++) {
			for (int j = 0; j < i; j++) {
				if (data.get(firstIndex + j).compareTo(data.get(firstIndex + i)) > 0)
					Collections.swap(data, firstIndex + i, firstIndex + j);
			}
		}
	}


	@Override
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (firstIndex < (firstIndex + numberToSort - 1))
			if (numberToSort < 16) {
				insertionSort(data, firstIndex, numberToSort);
			} else {
				int pivot = partition(data, firstIndex, numberToSort);
				int leftSegmentSize = pivot - firstIndex;
				int rightSegmentSize = numberToSort - leftSegmentSize - 1;
				quickSort(data, firstIndex, leftSegmentSize);
				quickSort(data, pivot + 1, rightSegmentSize);
			}
	}


	@Override
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition) {

		String pivot = data.get(firstIndex);
		int TooBigNdx = firstIndex + 1;
		int TooSmallNdx = firstIndex + numberToPartition - 1;

		while (TooBigNdx < TooSmallNdx) {
			while (TooBigNdx < TooSmallNdx && data.get(TooBigNdx).compareTo(pivot) <= 0)
				TooBigNdx++;
			while (TooSmallNdx > firstIndex && data.get(TooSmallNdx).compareTo(pivot) > 0)
				TooSmallNdx--;
			if (TooBigNdx < TooSmallNdx)
				Collections.swap(data, TooBigNdx, TooSmallNdx);
		}
		if (pivot.compareTo(data.get(TooSmallNdx)) >= 0) {
			Collections.swap(data, firstIndex, TooSmallNdx);
			return TooSmallNdx;
		}
		return firstIndex;
	}


	@Override
	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (numberToSort <= 15)
			insertionSort(data, firstIndex, numberToSort);
		else {
			int sizeleft = (numberToSort / 2);
			int sizeright = (numberToSort / 2) + (numberToSort % 2);
			mergeSort(data, firstIndex, sizeleft);
			mergeSort(data, (firstIndex + sizeleft), sizeright);
			merge(data, firstIndex, sizeleft, sizeright);
		}
	}


	@Override
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {

		ArrayList<String> temp = new ArrayList<String>();
		int ndxtemp = 0, ndxleft = firstIndex, ndxright = (firstIndex + leftSegmentSize), comp, ndx;
		String left, right;
		int sizeleft = leftSegmentSize, sizeright = rightSegmentSize;
		while ((sizeleft > 0) || (sizeright > 0)) {
			if ((sizeleft > 0) && (sizeright > 0)) {
				left = data.get(ndxleft);
				right = data.get(ndxright);
				comp = left.compareTo(right);
				if (comp <= 0) {
					temp.add(data.get(ndxleft++));
					sizeleft--;
				} else {
					temp.add(data.get(ndxright++));
					sizeright--;
				}
			} else {
				if (sizeleft > 0) {
					temp.add(data.get(ndxleft++));
					sizeleft--;
				} else {
					temp.add(data.get(ndxright++));
					sizeright--;
				}
			}
		}
		ndx = firstIndex;
		for (ndxtemp = 0; ndxtemp < temp.size(); ndxtemp++)

			data.set(ndx++, temp.get(ndxtemp));
	}


	@Override
	public void heapSort(ArrayList<String> data) {

	}


	@Override
	public void heapify(ArrayList<String> data) {

	}

}
