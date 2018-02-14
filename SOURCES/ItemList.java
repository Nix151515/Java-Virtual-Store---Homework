
import java.util.*;


public abstract class ItemList 
{
	Comparator<?> cmp;
	Node current = new Node();
	Node first = new Node();
	Node last = new Node();
	int size = 0;
	
	
	public ItemList()
	{
		first.next = current;
		last.prev = current;
		current.next = last;
		current.prev = first;
	}
	
	
	static class Node
	{
		Item item;
		int index;
		Node prev;
		Node next;
	}
	
	public Item getItemFromId(int id)
	{
		Node contor = this.first.next;
		while(contor.next != null)
		{
			if(contor.item.getId() == id)
				return contor.item;
			contor = contor.next;
		}
		return null;
	}
	public boolean add(Item item)
	{
		size++;
		if(current.item == null)
		{
			current.item = item;
			current.index = 1;
		}
		else
		{
			Node nou = new Node();
			nou.next = last;
			nou.prev = current;
			nou.index = nou.prev.index+1;
			nou.item = item;
			
			last.prev = nou;
			current.next = nou;
			current = nou;
		}
		return true;
	}
	
	
	public Item getItem(int index)
	{
		Node contor = this.first.next;
		while(contor.index != index && contor.next!= null)
		{
			contor=contor.next;
		}
		return contor.item;
	}
	
	
	public Node getNode(int index) 
	{
		Node contor = this.first.next;
		while(contor.index != index && contor.next!= null)
		{
			contor = contor.next;
		}
		return contor;
	}
	
	public int indexOf(Item item)
	{
		Node contor = this.first.next;
		while(contor.item != item && contor.next!=null)
		{
			contor = contor.next;
		}
		return contor.index;
	}
	
	public int indexOf(Node node)
	{
		Node contor=this.first.next;
		while(contor != node && contor.next != null)
		{
			contor = contor.next;
		}
		return contor.index;
	} 
	
	public boolean contains(Item item)
	{
		Node contor = this.first.next;
		while(contor.next != null)
		{
			if(contor.item ==i tem)
				return true;
			contor=contor.next;
		}
		return false;
	}
	
	public boolean contains(Node node)
	{
		Node contor = this.first.next;
		while(contor.next != null)
		{
			if(contor == node)
				return true;
			contor=contor.next;
		}
		return false;
	}
	
	
	public Item remove(int index)
	{
		Node contor = getNode(index);
		if(contor.index == current.index)
		{
			Node aux = contor;
			current.item = null;
			
			if(aux.prev != first)
			{
				current = current.prev;
			}
			current.next = last;
			this.repairIndex();
			
			return aux.item;
		}
		
		if(this.contains(contor))
		{
				contor.prev.next = contor.next;
				contor.next.prev = contor.prev;
				this.repairIndex();
				return contor.item;
		}
		else
		{
			System.out.println("Nu exista element cu acel index");
			return null;
		}
	}
	
	
	public boolean remove(Item item)
	{
		int index = this.indexOf(item);
		Item s = this.remove(index);
		if(s == null)
		{
			return false;
		}
		else
			return true;
	}
	

	public boolean isEmpty()
	{
		if(first.next.item==null)
			return true;
		return false;
	}
	
	
	public double getTotalPrice()
	{
		double total = 0;
		Node contor = this.first.next;
		while(contor.item != null)
		{
			total = total+contor.item.getPrice();
			contor = contor.next;
		}
		return total;
	}
	
	
	public void repairIndex()
	{
		Node contor = this.first.next;
		contor.index = 1;
		while(contor.next!=null)
		{	
			contor.next.index = contor.index+1;
			contor = contor.next;
		}
	}
	
	
	void printList()
	{
		Node contor = this.first.next;
		while(contor.next!=null)
		{
			System.out.print(contor.item);
			contor = contor.next;
		}
		System.out.println();
	}
	
	
	 public ListIterator<Item> listIterator (int index)
	 {
		 return new ItemIterator(index);
	 }
	 public ListIterator<Item> listIterator ()
	 {
		 return new ItemIterator();
	 }
	
	
	 public boolean addAll(Collection<? extends Item> c)
	 {
		 for (Iterator<? extends Item> iterator = c.iterator(); iterator.hasNext();)
		 {
			 try
			 {
				 this.add(iterator.next());
			 }
			 catch(Exception e)
			 {
				 System.out.println("Nu s-a putut introduce");
				 return false;
			 }
		 }
		 return true;
	 }
	
	 public boolean removeAll(Collection<? extends Item> c )
	 {
		 for (Iterator<? extends Item> iterator = c.iterator(); iterator.hasNext();)
		 {
			 try
			 {
				 this.remove(iterator.next());
			 }
			 catch(Exception e)
			 {
				 System.out.println("Nu s-a putut sterge");
				 return false;
			 }
		 }
		 return true;
	 }
	
	// 				Clasa ItemIterator
	
	
	class ItemIterator implements ListIterator<Item>
	{
		Node cursor;
		public ItemIterator()
		{
			cursor = first;
		}
		public ItemIterator(int index)
		{
			cursor = first;
			int i = 1;
			while(i != index)
			{
				if(i > index)
					break;
				i++;
				cursor = cursor.next;
			}
		}

		@Override
		public void add(Item item) 
		{
			size++;
			if(cursor.item == null)
			{
				cursor.item = item;
				cursor.index = 1;
			}
			else
			{
				Node nou = new Node();
				nou.next = last;
				nou.prev = current;
				nou.index = nou.prev.index+1;
				nou.item = item;
				
				last.prev = nou;
				cursor.next = nou;
				cursor = nou;
			}
		}
		
		
		public void remove() {
			
			cursor = cursor.prev;
			cursor.next = last;
		} 
		
		public void set(Item e) {
			
			this.remove();
			this.add(e);
		}

		@Override
		public boolean hasNext() {
			if( cursor.next != last)
			{
				return true;
			}
			else
			{
				cursor = cursor.next;
				return false;
			}
		}
		
		

		@Override
		public boolean hasPrevious() {
			if( cursor.prev!= first)
				return true;
			else
				return false;
		}

		
		@Override
		public Item next() 
		{
			try
			{
				cursor = cursor.next;
				return cursor.item;
			}
			catch(Exception e)
			{
				System.out.println("This is the last node");
			}
			return null;
		}

		@Override
		public int nextIndex() {
			try
			{
				return cursor.next.index;
			}
			catch(Exception e)
			{
				System.out.println("Index does not exist");
			}
			return -100;
		}
		
		
		@Override
		public Item previous() {
			try
			{
				cursor = cursor.prev;
				return cursor.item;
			}
			catch(Exception e)
			{
				System.out.println("This is the first node");
			}
			return null;	
		}

		@Override
		public int previousIndex() {
			try
			{
				return cursor.next.index;
			}
			catch(Exception e)
			{
				System.out.println("Index does not exist");
			}
			return -100;
		}	
	}
}
