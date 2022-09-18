package simu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticArrayList<T extends Number & Comparable<T>> extends ArrayList<T> {

	private static final long serialVersionUID = 1L; // TODO: Check what this _actually_ means

	public ArithmeticArrayList() {
		super();
	}

	public ArithmeticArrayList(Collection<? extends T> c) {
		super(c);
	}

	public T findMedian() {
		List<T> sorted = this.stream().sorted().collect(Collectors.toList());
		int middleIndex = (int) Math.floor(sorted.size() / 2);

		return sorted.get(middleIndex);
	}

	public double findAverage() {
		double keskiarvo = 0;
		for (int i = 0; i < this.size(); i++) {
			keskiarvo = keskiarvo + this.get(i).doubleValue();
		}
		return keskiarvo / this.size();
	}
}
