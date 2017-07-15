package org.asperen.combinations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CombinationsIterator<T> implements Iterator<List<T>> {

	private final T[] values;
	private final int[] indexes;
	private int spinner;
	
	@SuppressWarnings("unchecked")
	public CombinationsIterator(List<T> values, int maxSize) {
		super();
		this.values = (T[]) values.toArray();
		this.indexes = new int[maxSize];
		nextTranche(0);
	}
	
	@SuppressWarnings("unchecked")
	public CombinationsIterator(List<T> values) {
		super();
		this.values = (T[]) values.toArray();
		this.indexes = new int[values.size()];
		nextTranche(0);
	}

	@Override
	public boolean hasNext() {
		return indexes[0] < values.length;
	}
	
	private void nextTranche(int index) {
		spinner = 1;
		indexes[0] = index;
		for (int i = 1; i < indexes.length; i++)
			indexes[i] = -1;
	}
	
	private void moveToNext() {
		if (spinner < indexes.length) {
			if (indexes[spinner] < 0)
				indexes[spinner] = indexes[spinner - 1];
			indexes[spinner]++;
			if (indexes[spinner] >= values.length) {
				if (indexes[spinner - 1] < values.length - 1) {
					indexes[spinner] = indexes[spinner - 1] + 1;
					spinner++;
					moveToNext();
				} else {
					nextTranche(indexes[0] + 1);
				}
			}
		} else {
			nextTranche(indexes[0] + 1);
		}
	}

	@Override
	public List<T> next() {
		List<T> result = new LinkedList<T>();
		for (int i = 0; i < indexes.length; i++)
			if (indexes[i] >= 0 && indexes[i] < values.length)
				result.add(values[indexes[i]]);
		moveToNext();
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("cannot remove from combinations");
	}

}
