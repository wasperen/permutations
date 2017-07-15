package org.asperen.permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.asperen.permutations.PermutationsSwapIterator.Swap;

public class PermutationsIterator<T> implements Iterator<List<T>> {

	private final List<T> values;
	private final PermutationsSwapIterator<Integer> iter;
	private boolean lastPermutation = false;
	
	public PermutationsIterator(List<T> list) {
		super();
		this.values = new ArrayList<T>(list);
		
		List<Integer> indexes = new ArrayList<Integer>(values.size());
		for (int i = 0; i < values.size(); i++)
			indexes.add(i);
		this.iter = new PermutationsSwapIterator<Integer>(indexes);
	}
	
	@Override
	public boolean hasNext() {
		return iter.hasNext() || lastPermutation;
	}

	@Override
	public List<T> next() {
		List<T> result = new ArrayList<T>(values);
		
		if (!lastPermutation) {
			Swap<Integer> swap = iter.next();
			Collections.swap(values, swap.getLeft(), swap.getRight());
			lastPermutation = !iter.hasNext();
		} else
			lastPermutation = false;
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("cannot remove from permutations");
	}

}
