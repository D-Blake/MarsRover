package Interfaces;

public interface IEventListener<T> {

	void subscribe(T listener);
	void unsubscribe(T listener);
	void notifyListeners(T listener);
}
