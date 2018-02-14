
public interface Subject {

	void addObserver(Customer cust);
	void removeObserver(Customer cust);
	void notifyAllObservers(Notification n);
}
