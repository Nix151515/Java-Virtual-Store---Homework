package tema;

import java.util.*;


public class Customer implements Observer{
	String name;
	ShoppingCart shopcart;
	WishList wl;
	Vector <Notification> notifications=new Vector <Notification>();;
	
	public Customer()
	{
		
	}
	
	public Customer(String name,ShoppingCart sh,WishList wl)
	{
		this.name=name;
		shopcart=sh;
		this.wl=wl;
	}
	
	public Customer(String name,ShoppingCart sh)
	{
		this.name=name;
		shopcart=sh;
		wl=new WishList();
	}
	
	public void update(Notification notif) 
	{
		if(notifications.contains(notif))
			return;
		else
			notifications.add(notif);
	}
	
	public String toString()
	{
		return name;
	}
	
	public void showCart()
	{
		Vector<Item> v=new Vector<Item>();
		ItemList.Node aux=shopcart.first.next;
		while(aux.next!=null)
		{
			v.add(aux.item);
			aux=aux.next;
		}
		Collections.sort(v,new CartComparator());
		if(!v.contains(null))
			System.out.println(v);
		else
			System.out.println("[]");
	}
	
	
	public void showWish()
	{
		Vector<Item> v=new Vector<Item>();
		ItemList.Node aux=wl.first.next;
		while(aux.next!=null)
		{
			v.add(aux.item);
			aux=aux.next;
		}
			
		Collections.sort(v,new WishComparator());
		if(!v.contains(null))
			System.out.println(v);
		else
			System.out.println("[]");
	}
	
	public int nrShopCart()
	{
		return shopcart.size;
	}
	
	public int nrWishList()
	{
		return wl.size;
	}
	
	public Vector<Notification> getNotif()
	{
		return notifications;
	}
	
	public void addWish(Item i,Department d)
	{
		wl.add(i);
		if(!d.observers.contains(this))
		{
			d.addObserver(this);
		}
	}
	
	public void removeWish(Item i,Department d)
	{
		if(!wl.contains(i))
			return;
		wl.remove(i);
		if(wl.isEmpty())
		{
			d.removeObserver(this);
			return;
		}
		ItemList.Node contor=this.wl.first.next;
		while(contor.next!=null)
		{
			if(d.contains(contor.item))
				return;
			contor=contor.next;
		}
		d.removeObserver(this);
	}
	
}

