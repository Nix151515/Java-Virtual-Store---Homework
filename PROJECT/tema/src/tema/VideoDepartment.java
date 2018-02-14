package tema;

public class VideoDepartment extends Department{

	public VideoDepartment(String name,int id)
	{
		super(name,id);
	}
	
	
	public VideoDepartment()
	{
		
	}
	
	void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
}
