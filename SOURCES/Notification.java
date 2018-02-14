
public class Notification {
	
	String date;
	enum NotificationType{
	ADD, REMOVE, MODIFY;
	};
	
	NotificationType ntype;
	public int dep_id;
	public int prod_id;
	
	public Notification(String data)
	{
		date=data;
	}
	public Notification()
	{
		
	}
	
	public Notification(NotificationType c,int dID,int pID)
	{
		ntype = c;	
		dep_id = dID;
		prod_id = pID;
	}
	
	public Notification(NotificationType c)
	{
		ntype = c;	
	}
	public String toString()
	{
		return this.ntype+";"+this.prod_id+";"+this.dep_id;
	}
	
}
