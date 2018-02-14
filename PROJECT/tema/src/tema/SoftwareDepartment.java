package tema;

public class SoftwareDepartment extends Department{
	
	public SoftwareDepartment(String name,int id)
	{
		super(name,id);
	}
	
	
	public SoftwareDepartment()
	{
		
	}
	
	void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
