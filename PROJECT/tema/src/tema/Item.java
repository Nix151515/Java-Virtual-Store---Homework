package tema;

public class Item {

	private String name;
	private int id;
	private double pret;
	public Item(String nm,int ID,double pret)
	{
		name=nm;
		id=ID;
		this.pret=pret;
	}
	public Item()
	{
	
	}
	public boolean equals(Item a)
	{
		if(this.name.equals(a.name) && this.id==a.id && this.pret==a.pret)
		{
			return true;
		}
		return false;
	}
	public String toString()
	{
		String s;
		s=name+";"+id+";"+String.format("%.2f", pret);
		return s.replace(',', '.');
	}
	
	public double getPrice()
	{
		return pret;
	}
	
	public void setPrice(double pret)
	{
		this.pret=pret;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	
	
	
	
	
	
	
	
	
	
}
