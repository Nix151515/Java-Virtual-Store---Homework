
import java.util.*;


public abstract class Department extends ItemList implements Subject  {

	String name;
	int id;
	Vector<Customer> customers = new Vector<Customer>();
	Vector<Customer> observers = new Vector<Customer>();
	
	public Department(String name,int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public Department()
	{
		
	}
	
	void enter(Customer c)
	{
		if(customers.contains(c))
			return;
		else	
			customers.add(c);
	}
	
	void exit(Customer c)
	{
		if(customers.contains(c))
		{
			customers.remove(c);
		}
	}
	
	Vector<Customer> getCustomers()
	{
			return customers;	
	}
	
	int getId()
	{
		return id;
	}
	
	void addItem(Item i)
	{
		this.add(i);
		Notification n = new Notification(Notification.NotificationType.ADD,this.id,i.getId());
		this.notifyAllObservers(n);
	}
	
	void delItem(Item i)
	{
		this.remove(i);
		Notification n = new Notification(Notification.NotificationType.REMOVE,this.id,i.getId());
		this.notifyAllObservers(n);
	}

	
	ItemList getItems()
	{
		return this;
	}
	

	public String toString()
	{
		return name+" "+id;
	}
	
	public Vector<Customer> getObservers()
	{
		
		return observers;
	}
	
	public void addObserver(Customer cust)
	{
		if(!observers.contains(cust))
		 observers.add(cust);
	}
	
	public void removeObserver(Customer cust)
	{
		observers.remove(cust);
	}
	
	public void notifyAllObservers(Notification n)
	{

		for(Customer c:observers)
		{
			c.update(n);
		}
	}

	abstract void accept(Visitor visitor);

}

