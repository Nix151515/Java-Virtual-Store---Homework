

import java.util.*;


public class WishList extends ItemList
{
	public Strategy strategy;
	public WishList()
	{
		
	}
		
	public Item executeStrategy(StrategyA x)
	{
		Node aux=this.first.next;
		Item minim;
		double min = 1000000;
		int index = -1;
		while(aux.next != null)
		{
			if(min > aux.item.getPrice())
			{
				min = aux.item.getPrice();
				index = indexOf(aux.item);
			}
			aux = aux.next;
		}
		minim = getItem(index);
		return minim;
	}
	public Item executeStrategy(StrategyB x)
	{
		Vector<Item> v = new Vector<Item>();
		Node aux = this.first.next;
		while(aux.next != null)
		{
			v.add(aux.item);
			aux = aux.next;
		}
		if(v.isEmpty())
		{
			System.out.println("Nu mai sunt elemente pt getItem");
			return null;
		}
		Collections.sort(v,new WishComparator());
		return v.elementAt(0);
	}
	public Item executeStrategy(StrategyC x)
	{
		return last.prev.item;
	}
	
	
}
class WishComparator implements Comparator<Item>
{

	@Override
	public int compare(Item item1, Item item2) {
		String s1 = item1.getName();
		String s2 = item2.getName();
		return s1.compareTo(s2);
	}
	
}