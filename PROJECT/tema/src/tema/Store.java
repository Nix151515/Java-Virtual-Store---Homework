package tema;

import java.util.*;



public class Store 
{
	
	String name;
	Vector <Department> departments=new Vector<Department>();
	Vector <Customer> customers=new Vector<Customer>();
	
	private static Store instance = null;

	private Store(String name)
	{
		this.name=name;
	}
	public static Store getInstance(String name)
	{
		if(instance == null) 
		{
			instance = new Store(name);
		} return instance;

	}
	
	public Item getItem(int id)
	{
		for(Department dep:departments)
		{
			if(dep.getItems().contains(dep.getItemFromId(id)))
			{
				return dep.getItemFromId(id);
			}
		}
		System.out.println("Nu exista elementul in magazin");
		return null;
	}
	
	public void delProduct(int id,Department d)
	{
		for(Department dep:departments)
		{
			if(dep.contains(dep.getItemFromId(id)))
			{
				for(Customer c:customers)
				{
					if(c.wl.contains(this.getItem(id)) && this.getItem(id)!=null)
					{
						c.removeWish(this.getItem(id),this.getDepartmentFromItemId(id));;
					}
				}
				
				dep.remove(dep.getItemFromId(id));		
			}
		}
		
		Notification n=new Notification(Notification.NotificationType.REMOVE,d.id,id);
		d.notifyAllObservers(n);
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
	
	ShoppingCart getShoppingCart(double buget)
	{
		return new ShoppingCart(buget);
	}
	
	Vector<Customer> getCustomers()
	{
		return customers;
	}
	
	Customer getCustomer(String nume)
	{
		for(Customer c:customers)
		{
			if(c.name.equals(nume))
			{
				return c;
			}
		}
		System.out.println("Nu a fost gasit clientul");
		return null;
	}
	
	Vector<Department> getDepartments()
	{
		return departments;
	}
	
	void addDepartment(Department d)
	{
		if(departments.contains(d))
			return;
		departments.add(d);
	}
	
	Department getDepartmentFromItemId(int id)
	{
		for(Department dep:departments)
		{
			if(dep.getItems().contains(dep.getItemFromId(id)))
			{
				return dep;
			}
		}
		return null;
	}
	Department getDepartment(int id)
	{
		for(Department i:departments)
		{
			if(i.id==id)
			{
				return i;
			}
		}
		System.out.println("Nu exista departament id-ul "+id);
		return null;
	}

	public String toString()
	{
		return name;
	}
	
	void modifyItem(int dep_id,int item_id,double price)
	{
		Notification n=new Notification(Notification.NotificationType.MODIFY,dep_id,item_id);
		this.getDepartment(dep_id).notifyAllObservers(n);
		
		
		
		Item vechi=this.getItem(item_id);
		Item nou=new Item();
		nou.setName(vechi.getName());
		nou.setId(vechi.getId());
		nou.setPrice(price);
		
		for(Customer i:customers)
		{
			if(i.wl.contains(this.getItem(item_id)) && this.getDepartment(dep_id).observers.contains(i))
			{
				i.wl.remove(vechi);
				i.wl.add(nou);
				
			}
			if(i.shopcart.contains(this.getItem(item_id)) && this.getDepartment(dep_id).observers.contains(i))
			{
				i.shopcart.remove(vechi);
				i.shopcart.add(nou);
			}
		}
		this.getDepartment(dep_id).remove(vechi);
		this.getDepartment(dep_id).add(nou);
		
	}
	
}
