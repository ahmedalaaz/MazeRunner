
package model;


public interface Subject {
        public void addObserver(Observer ob);
	public void removeObserver(Observer ob);
	public void notifyObservers(int health, int score,int lives);
}
