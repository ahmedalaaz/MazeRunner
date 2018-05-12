package model;

import java.util.ArrayList;

public class Caretaker {
	public static ArrayList<Memento> chekPoints = new ArrayList<>();

	public void addMemento(Memento memento) {
		chekPoints.add(memento);
	}

	public Memento getMemento(int index) {
		return chekPoints.get(index);
	}

	public void removeMemento(int index) {
		chekPoints.remove(index);
	}

}
