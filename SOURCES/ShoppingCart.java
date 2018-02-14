
import java.util.*;


public class ShoppingCart extends ItemList implements Visitor{

	double buget;
	public ShoppingCart(double p)
	{
		buget = p;
	}
	

	@Override
	public void visit(VideoDepartment videoDep) 
	{
		double max = 0;
		Node contor = videoDep.first.next;
		while(contor.item != null)
		{
			if(max < contor.item.getPrice())
				max = contor.item.getPrice();
			contor = contor.next;
		}
		
		
		if(videoDep.getTotalPrice()<max)
		{
			contor = videoDep.first.next;
			while(contor.item!=null)
			{
				if(this.contains(contor.item))
				{
					Item vechi = contor.item;
					Item nou = new Item();
					nou.setName(vechi.getName());
					nou.setId(vechi.getId());
					nou.setPrice(vechi.getPrice()-0.15*vechi.getPrice());
					this.remove(vechi);
					this.add(nou);
				}
				
				contor = contor.next;
			}
		}
		this.buget = this.buget+0.05*videoDep.getTotalPrice();
		
	}

	@Override
	public void visit(BookDepartment bookDep) {
		Node contor = bookDep.first.next;
		while(contor.item != null)
		{
			if(this.contains(contor.item))
			{	
				
				Item vechi = contor.item;
				Item nou = new Item();
				nou.setName(vechi.getName());
				nou.setId(vechi.getId());
				nou.setPrice(vechi.getPrice()-0.1*vechi.getPrice());
				this.remove(vechi);
				this.add(nou);
				
				
			}
			contor = contor.next;
		}

	}

	
	
	@Override
	public void visit(SoftwareDepartment softDep) 
	{
		boolean ok = true;
		Node contor = softDep.first.next;
		while(contor.item!=null)
		{
			
			if(this.buget-softDep.getTotalPrice()<contor.item.getPrice())
			{
				ok = false;
			}
			contor = contor.next;
		}
		
		if(ok == true)
		{
			contor = softDep.first.next;
			while(contor.item != null)
			{
				if(this.contains(contor.item))
				{
					Item vechi = contor.item;
					Item nou = new Item();
					nou.setName(vechi.getName());
					nou.setId(vechi.getId());
					nou.setPrice(vechi.getPrice()-0.2*vechi.getPrice());
					this.remove(vechi);
					this.add(nou);
				}

				contor = contor.next;
			}
		}

	}

	@Override
	public void visit(MusicDepartment musicDep) {
		
		Node contor = musicDep.first.next;
		double totalPrice  =0;
		while(contor.item != null)
		{
			if(this.contains(contor.item))
				totalPrice = totalPrice+contor.item.getPrice();
			contor = contor.next;
		}
		this.buget = this.buget+0.1*totalPrice;

	}
	
	
	
	
	public boolean add(Item item)
	{
		if(this.getTotalPrice()+item.getPrice() > this.buget)
		{
			System.out.println("Nu e buget suficient pt "+item.getName());
			return false;
		}
		else
		{	
			super.add(item);
			return true;
		}
	}
	
	public String toString()
	{
		if(this.isEmpty())
			return "[]";
		else
		return "";
	}
	
	

}

class CartComparator implements Comparator<Item>
{

	@Override
	public int compare(Item item1, Item item2)
	{
		if(item1.getPrice() > item2.getPrice())
			return 1;
		if(item1.getPrice() < item2.getPrice())
			return -1;
		
		return (item1.getName()).compareTo(item2.getName());
	}
}

