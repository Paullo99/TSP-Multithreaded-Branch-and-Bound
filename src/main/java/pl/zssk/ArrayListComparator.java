package pl.zssk;

import java.util.ArrayList;
import java.util.Comparator;

//Klasa do porównywania List przechowuj¹cych obiekty typu Integer
public class ArrayListComparator implements Comparator<ArrayList<Integer>> {

	@Override
	public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
		return Integer.compare(o1.get(0), o2.get(0));
	}

}
