package tema;

public class BookDepartment extends Department{

	public BookDepartment(String name,int id)
	{
		super(name,id);
	}
	
	public BookDepartment()
	{
		
	}
	
	
	@Override
	void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
	

}
