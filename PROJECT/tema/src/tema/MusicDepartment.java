package tema;

public class MusicDepartment extends Department{
	
	public MusicDepartment(String name,int id)
	{
		super(name,id);
	}
	
	
	public MusicDepartment()
	{
		
	}
	
	void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
