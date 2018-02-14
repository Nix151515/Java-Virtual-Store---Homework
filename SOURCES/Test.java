
import java.io.*;

public class Test {

	public static void main(String[] args) 
	{
		int i;
		
		try
		{
			
		FileReader f1 = new FileReader ("store.txt");
		BufferedReader b = new BufferedReader (f1);
		Department music,book,soft,video;
		Store store = Store.getInstance(b.readLine());
		String words[];
		String s;
		
		s = b.readLine();
		while(s!=null)
		{
			
			words = s.split(";");

			switch(words[0])
			{
				case "MusicDepartment" :
					music = new MusicDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(music);	
					break;
				case "VideoDepartment" :
					video = new VideoDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(video);	
					break;
				case "SoftwareDepartment" :
					soft = new SoftwareDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(soft);	
					break;
				case "BookDepartment" :
					book = new BookDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(book);	
					break;
			}	

			int lg = Integer.parseInt(b.readLine());
			s = b.readLine();
			for(i = 0;i<lg;i++)
			{
	
				words = s.split(";");	
				store.departments.lastElement().add(new Item(words[0],Integer.parseInt(words[1]),Double.parseDouble(words[2])));
				s = b.readLine();
			}
			
		}
				
		b.close();
		f1.close();
		

		
		FileReader f2 = new FileReader("customers.txt");
		b = new BufferedReader (f2);
		int lg = Integer.parseInt(b.readLine());
		s = b.readLine();

		for(i = 0 ; i < lg ; i++)
		{
			words = s.split(";");	
			Customer c = new Customer(words[0],store.getShoppingCart(Double.parseDouble(words[1])));
			switch(words[2])
			{
				case "A":
				{
					c.wl.strategy = new StrategyA();
					break;
				}
				case "B":
				{
					c.wl.strategy = new StrategyB();
					break;
				}
				case "C":
				{
					c.wl.strategy = new StrategyC();
					break;
				}
			}
			store.enter(c);
			s = b.readLine();
		}
		
		b.close();
		f2.close();
		
		
		
		FileReader f3 = new FileReader("events.txt");
		b = new BufferedReader (f3);
		lg = Integer.parseInt(b.readLine());
		s = b.readLine();

		for(i = 0 ; i < lg ; i++)
		
		{
			words = s.split(";");				
			switch(words[0])
			{
				case "addItem" :
				{
					if(words[2].equals("WishList"))
					{
						store.getCustomer(words[3]).addWish(store.getItem(Integer.parseInt(words[1])),store.getDepartmentFromItemId(Integer.parseInt(words[1])));
					}
					else
					{
						store.getCustomer(words[3]).shopcart.add(store.getItem(Integer.parseInt(words[1])));
					}
					break;
				}
					
				case "delItem" :
				{
					if(words[2].equals("WishList"))
					{
						store.getCustomer(words[3]).removeWish(store.getItem(Integer.parseInt(words[1])),store.getDepartmentFromItemId(Integer.parseInt(words[1])));
					}
					else
					{
						store.getCustomer(words[3]).shopcart.remove(store.getItem(Integer.parseInt(words[1])));
					}
					break;
				}
					
				case "addProduct" :
				{
					store.getDepartment(Integer.parseInt(words[1])).addItem(new Item(words[4],Integer.parseInt(words[2]),Double.parseDouble(words[3])));
					break;
				}
				
				case "modifyProduct" :
				{
					int item_id = Integer.parseInt(words[2]);
					int dep_id = Integer.parseInt(words[1]);
					double pret = Double.parseDouble(words[3]);
					store.modifyItem(dep_id, item_id,pret);
					break;
				}
				case "delProduct" :
				{
					store.delProduct(Integer.parseInt(words[1]),store.getDepartmentFromItemId(Integer.parseInt(words[1])));
					break;
				}	
				case "getItems" :
				{
					if(words[1].equals("WishList"))
					{
						store.getCustomer(words[2]).showWish();
					}
					else
					{
						store.getCustomer(words[2]).showCart();
					}
					break;
				}
				case "getTotal" :
				{
					double p;
					if(words[1].equals("WishList"))
					{
						p = store.getCustomer(words[2]).wl.getTotalPrice();
					}
					else
					{
						p = store.getCustomer(words[2]).shopcart.getTotalPrice();
					}
					System.out.println(String.format("%.2f", p).replace(',', '.'));
					break;
				}
					
				case "accept" :
				{
				    store.getDepartment(Integer.parseInt(words[1])).accept(store.getCustomer(words[2]).shopcart);
					break;
				}
				
				case "getObservers" :
				{
				
					System.out.println(store.getDepartment(Integer.parseInt(words[1])).getObservers());
					break;
				}
				
				case "getNotifications" :
				{
					System.out.println(store.getCustomer(words[1]).getNotif());
					break;
				}
				
				case "getItem" :
				{
					Item aux = store.getCustomer(words[1]).wl.strategy.execute(store.getCustomer(words[1]).wl);
					System.out.println(aux);
					if(store.getCustomer(words[1]).shopcart.buget>aux.getPrice())
					{
						store.getCustomer(words[1]).removeWish(store.getItem(aux.getId()),store.getDepartmentFromItemId(aux.getId()));
						store.getCustomer(words[1]).shopcart.add(store.getItem(aux.getId()));
					}
					break;
				}
				
				
				default:
				{
					System.out.println("Instructiunea nu exista");
					break;
				}
				
			}
			
			
			s = b.readLine();
		}
		
		
		b.close();
		f3.close();
		}
		catch(IOException e)
		{
			System.out.println("Input gresit");
		}
	
	}

}


