package tema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ShopWindow extends JFrame{

	Store store;
	JTabbedPane tp=new JTabbedPane();
	JFrame f1,f2,f3;
	JTextField t1,t2,t3;
	JLabel l1,l2,l3;
	JLabel price,name,budget,department_id,id,nume_prod;
	JLabel pricewl,namewl,department_idwl,idwl,nume_prodwl;
	JTextField pricetfwl,nametfwl,department_idtfwl,nume_prodtfwl,idtfwl;
	JTextField pricetf,nametf,budgettf,department_idtf,nume_prodtf,idtf;
	JList<?> shoplist,listash,listawl,clienti,shoplist2,shoplist3,clienti2;
	JList<?> lista30,lista31,lista32;
	JScrollPane scroll,scroll2,scroll3,scroll4,scroll5,scroll6,scroll7;
	JPanel panel10,panel11,panel12,panel20,panel21,panel22,panel30,panel31,panel32;
	JButton alfabetic = new JButton("Alfabetic");
	JButton pretCresc = new JButton("Crescator");
	JButton pretDesc = new JButton("Descrescator");
	JButton addItem = new JButton("Adauga");
	JButton delItem = new JButton("Sterge");
	JButton editItem = new JButton("Editeaza");
	JButton addCart = new JButton("Adauga in cos");
	JButton delCart = new JButton("Sterge din cos");
	JButton addWishList = new JButton("Adauga la dorinte");
	JButton delWishList = new JButton("Sterge dorinta");
	JButton buy = new JButton("Cumpara produsele");
	Vector <Item>v2 = new Vector<Item>();
	Vector <Item>v3 = new Vector<Item>();
	Vector <Item>v4 = new Vector<Item>();
	Vector <Department>deps = new Vector<Department>();
	Vector <Customer> cust = new Vector<Customer>();
	
	
	public static void main(String[] args) {
		new ShopWindow();

	}
	public ShopWindow()
	{
		
		int i;
		FileReader file1;
		BufferedReader b;
		Department music,book,soft,video;
		Store store = null;
		try
		{
			
		file1 = new FileReader ("store.txt");
		b = new BufferedReader (file1);
		store=Store.getInstance(b.readLine());
		String words[];
		String s;
		
		s = b.readLine();
		while(s != null)
		{ 
			
			words = s.split(";");

			switch(words[0])
			{
				case "MusicDepartment" :
					music = new MusicDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(music);
					deps.add(music);
					break;
				case "VideoDepartment" :
					video = new VideoDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(video);	
					deps.add(video);
					break;
				case "SoftwareDepartment" :
					soft = new SoftwareDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(soft);	
					deps.add(soft);
					break;
				case "BookDepartment" :
					book = new BookDepartment(words[0],Integer.parseInt(words[1]));
					store.addDepartment(book);
					deps.add(book);
					break;
			}	
			int lg = Integer.parseInt(b.readLine());
			s = b.readLine();
			for(i = 0 ; i < lg ; i++)
			{
				words = s.split(";");	
				v2.addElement(new Item(words[0],Integer.parseInt(words[1]),Double.parseDouble(words[2])));
				store.departments.lastElement().add(new Item(words[0],Integer.parseInt(words[1]),Double.parseDouble(words[2])));
				deps.lastElement().add(new Item(words[0],Integer.parseInt(words[1]),Double.parseDouble(words[2])));
				s = b.readLine();
			}
			
		}
				
		b.close();
		file1.close();
		

		FileReader file2 = new FileReader("customers.txt");
		b = new BufferedReader (file2);
		int lg = Integer.parseInt(b.readLine());
		s = b.readLine();

		for(i = 0 ; i < lg ; i++)
		{
			words = s.split(";");	
			Customer c = new Customer(words[0],store.getShoppingCart(Double.parseDouble(words[1])));
			store.enter(c);
			s = b.readLine();
		}
		b.close();
		file2.close();
		}
		catch(IOException e)
		{
			System.out.println("Nu merge boss");
		}
				
		this.setTitle("Magazin online");
		this.setSize(780,500);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(tp);
		
		panel10 = new JPanel();
		panel11 = new JPanel();
		panel12 = new JPanel();
		panel20 = new JPanel();
		panel21 = new JPanel();
		panel22 = new JPanel();
		panel30 = new JPanel();
		panel31 = new JPanel();
		panel32 = new JPanel();
		
		f1 = new JFrame();
		f2 = new JFrame();
		f3 = new JFrame();
		
	//	System.out.println(deps);
		
		t1 = new JTextField("",15);
        l1 = new JLabel("Denumire");
        t2 = new JTextField("",15);
        l2 = new JLabel("      Id       ");
        t3 = new JTextField("",15);
        l3 = new JLabel("     Pret    ");
        
        
        price = new JLabel("Pret");
        budget = new JLabel(" Bugetul clientului");
        department_id = new JLabel("Department ID");
        id = new JLabel("ID");
        nume_prod = new JLabel("Denumire produs");
        
        
        
        pricewl = new JLabel("            Pret        ");
        department_idwl = new JLabel("Department ID");
        idwl = new JLabel("             ID            ");
        nume_prodwl = new JLabel("Denumire produs");
        
        
        
        pricetfwl = new JTextField("",15);
        department_idtfwl = new JTextField("",15);
        idtfwl = new JTextField("",15);
        nume_prodtfwl = new JTextField("",15);
        
        pricetf = new JTextField("",15);
        budgettf = new JTextField("",15);
        department_idtf = new JTextField("",15);
        idtf = new JTextField("",15);
        nume_prodtf = new JTextField("",15);
        
        pricetf.setEditable(false);
        budgettf.setEditable(false);
        department_idtf.setEditable(false);
        idtf.setEditable(false);
        nume_prodtf.setEditable(false);
        
        pricetfwl.setEditable(false);
        department_idtfwl.setEditable(false);
        idtfwl.setEditable(false);
        nume_prodtfwl.setEditable(false);
        
		
		shoplist=new JList<Item>(v2);
		shoplist.setVisibleRowCount(22);
        scroll=new JScrollPane(shoplist);
        scroll.setViewportView(shoplist);
        
        
		f1.add(scroll);
		panel11.add(delItem);
		panel11.add(addItem);
		panel11.add(editItem);
		panel12.add(l1);
		panel12.add(t1);
		panel12.add(l2);
		panel12.add(t2);
		panel12.add(l3);
		panel12.add(t3);
		panel12.setLayout(new FlowLayout());

		panel11.add(alfabetic);
		panel11.add(pretCresc);
		panel11.add(pretDesc);
		
		
		f1.add(panel12);
		f1.add(panel11);
		f1.setLayout(new GridLayout());

		
		ListenForButton blistener=new ListenForButton();
		alfabetic.addActionListener(blistener);
		pretCresc.addActionListener(blistener);
		pretDesc.addActionListener(blistener);
		addItem.addActionListener(blistener);
		delItem.addActionListener(blistener);
		editItem.addActionListener(blistener);
		addCart.addActionListener(blistener);
		delCart.addActionListener(blistener);
		addWishList.addActionListener(blistener);
		delWishList.addActionListener(blistener);
		buy.addActionListener(blistener);
		
		
		tp.addTab("Magazin",f1.getContentPane());
		tp.addTab("ShoppingCart",f2.getContentPane());
		tp.addTab("WishList",f3.getContentPane());
		tp.setVisible(true);
		this.setVisible(true);
		
		
		shoplist2 = new JList<Item>(v2);
		listash = new JList<Object>();
       	clienti = new JList<Customer>(store.customers);
    	clienti2 = new JList<Customer>(store.customers);
    	cust = store.customers;
       	ListListener listenlist = new ListListener();
       	clienti.addListSelectionListener(listenlist);
       	clienti2.addListSelectionListener(listenlist);

       	
        scroll2 = new JScrollPane(listash);
        scroll2.setViewportView(listash);
        scroll4 = new JScrollPane(shoplist2);
        scroll4.setViewportView(shoplist2);
        scroll3 = new JScrollPane(clienti);
        scroll3.setViewportView(clienti);
       
        
        
        
        panel22.add(nume_prod);
        panel22.add(nume_prodtf);
        panel22.add(id);
        panel22.add(idtf);
        panel22.add(department_id);
        panel22.add(department_idtf);
        panel22.add(price);
        panel22.add(pricetf);
        panel22.add(budget);
        panel22.add(budgettf);
        
        
        panel22.add(addCart);
        panel22.add(delCart);
        panel22.add(buy);
        
        f2.add(scroll3);
        f2.add(scroll4);
        f2.add(panel22);
        f2.add(scroll2);
        
        f2.setLayout(new GridLayout());
        
   
        shoplist3 = new JList<Item>(v2);
		listawl = new JList<Object>();
		
		
		scroll7 = new JScrollPane(listawl);
        scroll7.setViewportView(listawl);
        scroll6 = new JScrollPane(shoplist3);
        scroll6.setViewportView(shoplist3);
        scroll5 = new JScrollPane(clienti2);
        scroll5.setViewportView(clienti2);
        
        panel30.add(addWishList);
        panel30.add(delWishList);
        panel30.add(nume_prodwl);
        panel30.add(nume_prodtfwl);
        panel30.add(idwl);
        panel30.add(idtfwl);
        panel30.add(department_idwl);
        panel30.add(department_idtfwl);
        panel30.add(pricewl);
        panel30.add(pricetfwl);
        
        f3.add(scroll5);
        f3.add(scroll6);
        f3.add(panel30);
        f3.add(scroll7);
        f3.setLayout(new GridLayout());
		
       	shoplist2.addListSelectionListener(listenlist);
       	shoplist3.addListSelectionListener(listenlist);
       	
       	f1.pack();
       	f2.pack();
       	f3.pack();
       
	}
	
	private class ListenForButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource() == alfabetic)
			{
				Collections.sort(v2,new WishComparator());
				shoplist = new JList<Item>(v2);
				scroll.setViewportView(shoplist);
				
			}
			
			if(e.getSource() == pretCresc)
			{
				Collections.sort(v2,new CartComparator());
				shoplist=new JList<Item>(v2);
				scroll.setViewportView(shoplist);
			}
			
			if(e.getSource() == pretDesc)
			{
				Collections.sort(v2,new pretDesc());
				shoplist = new JList<Item>(v2);
				scroll.setViewportView(shoplist);
		
			}
			
			if(e.getSource() == addItem)
			{
				
				boolean ok = true;
				try
				{
					String nume = t1.getText();
					Double pret = Double.parseDouble(t3.getText());
					int id = Integer.parseInt(t2.getText());
					
					Item s = new Item(nume,id,pret);
					
				
					for(Item i:v2)
					{
						if(i.equals(s))
						{
							ok = false;
							break;
						}
					}
					if(ok == true)			
						v2.add(s);
					else
						JOptionPane.showMessageDialog(null, "Element existent");
				}
				catch(Exception h)
				{
					JOptionPane.showMessageDialog(null, "Input gresit");
				}
				shoplist = new JList<Item>(v2);
				scroll.setViewportView(shoplist);			
				}

				
			
			if(e.getSource() == delItem)
			{
				
				int index = shoplist.getSelectedIndex();
				
				
				if (index >= 0) 
				{
					   for(Customer c:cust)
					   {
						   if(!c.shopcart.isEmpty())
						   {
							   ItemList.Node iterator = c.shopcart.first.next;
							   while(iterator.next!=null)
							   {
								   if(iterator.item.equals(v2.elementAt(index)))
								   {
								       iterator = iterator.next;
									   c.shopcart.remove(iterator.prev.item);
									   
									   v3.removeAllElements();
										ItemList.Node aux = c.shopcart.first.next;
										while(aux.next!=null)
										{
											v3.add(aux.item);
											aux = aux.next;
										}
										listash = new JList<Item>(v3);
									    scroll2.setViewportView(listash);
									   
								   }
								   else
									   iterator=iterator.next;
							   }
						   }
						}
					  
					   
					   for(Customer c:cust)
					   {
						   if(!c.wl.isEmpty())
						   {
							   ItemList.Node iterator = c.wl.first.next;
							   while(iterator.next != null)
							   {
								   if(iterator.item.equals(v2.elementAt(index)))
								   {
								       iterator = iterator.next;
									   c.wl.remove(iterator.prev.item);
									   
									   v4.removeAllElements();
										ItemList.Node aux = c.wl.first.next;
										while(aux.next != null)
										{
											v4.add(aux.item);
											aux = aux.next;
										}
										listawl=new JList<Item>(v4);
									    scroll7.setViewportView(listawl);
									   
								   }
								   else
									   iterator = iterator.next;
							   }
						   }
						}
					   
					}
				
				   v2.remove(index);
				   shoplist = new JList<Item>(v2);
				   scroll.setViewportView(shoplist);
					
			}
			
			if(e.getSource() == editItem)
			{
				int index = shoplist.getSelectedIndex();
				if (index > 0) 
				{
					try
					{
						Item aux = v2.elementAt(index);
						String nume = t1.getText();
						Double pret = Double.parseDouble(t3.getText());
						int id = Integer.parseInt(t2.getText());
					
						aux.setName(nume);
						aux.setPrice(pret);
						aux.setId(id);
					}
					catch(Exception x)
					{
						System.out.println("Umple toate campurile");
						JOptionPane.showMessageDialog(null, "Eroare");
					}
					
				   shoplist = new JList<Item>(v2);
				   scroll.setViewportView(shoplist);
				}	
			}
			
			if(e.getSource() == buy)
			{
				Customer c = (Customer)clienti.getSelectedValue();
				double rest = c.shopcart.buget-c.shopcart.getTotalPrice();
				c.shopcart = new ShoppingCart(rest);
				budgettf.setText(String.valueOf(c.shopcart.buget));
				v3.removeAllElements();
				listash = new JList<Item>(v3);
				scroll2.setViewportView(listash);
			}
			
			
			if(e.getSource() == addCart)
			{
			
				try
				{
					Customer nume = (Customer)clienti.getSelectedValue();
					Item nou = new Item();
					nou.setId(((Item)shoplist2.getSelectedValue()).getId());
					nou.setName(((Item)shoplist2.getSelectedValue()).getName());
					nou.setPrice(((Item)shoplist2.getSelectedValue()).getPrice());
					nume.shopcart.add(nou);
				
					ItemList.Node aux = nume.shopcart.first.next;
					while(aux.next != null)
					{
						if(!v3.contains(aux.item))
							v3.add(aux.item);
						aux = aux.next;
					}
				}
				catch(Exception k)
				{
					JOptionPane.showMessageDialog(null, "Selecteaza un client si un produs");
				}
				listash = new JList<Item>(v3);
				scroll2.setViewportView(listash);			
			}
			
			if(e.getSource() == delCart)
			{
				Customer nume = (Customer) clienti.getSelectedValue();
				if(nume.shopcart.contains((Item)listash.getSelectedValue()))
				{
					nume.shopcart.remove((Item)listash.getSelectedValue());
					v3.remove(listash.getSelectedValue());
				}
				listash = new JList<Item>(v3);
				scroll2.setViewportView(listash);
			}
			
			
			
			if(e.getSource() == addWishList)
			{
				try{
					Customer nume = (Customer) clienti2.getSelectedValue();
					
					Item nou = new Item();
					nou.setId(((Item)shoplist3.getSelectedValue()).getId());
					nou.setName(((Item)shoplist3.getSelectedValue()).getName());
					nou.setPrice(((Item)shoplist3.getSelectedValue()).getPrice());
					nume.wl.add(nou);
					
					ItemList.Node aux = nume.wl.first.next;
					while(aux.next != null)
					{
						if(!v4.contains(aux.item))
							v4.add(aux.item);
						aux = aux.next;
					}
					}
					catch(Exception k)
					{
						JOptionPane.showMessageDialog(null, "Selecteaza un client si un produs");
					}
				
					listawl = new JList<Item>(v4);
					scroll7.setViewportView(listawl);	
			}
			
			if(e.getSource() == delWishList)
			{
				Customer nume = (Customer) clienti2.getSelectedValue();
				if(nume.wl.contains((Item)listawl.getSelectedValue()))
				{
					nume.wl.remove((Item)listawl.getSelectedValue());
					v4.remove(listawl.getSelectedValue());
				}
				
					listawl = new JList<Item>(v4);
					scroll7.setViewportView(listawl);
			}
		}
	}
	
	
	
	private class ListListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() == clienti)
			{
				listash = new JList<Item>();
				scroll2.setViewportView(listash);
				v3.removeAllElements();
				Customer nume = (Customer)clienti.getSelectedValue();
				budgettf.setText(String.valueOf(nume.shopcart.buget));
				ItemList.Node aux = nume.shopcart.first.next;
				while(aux.next != null)
				{
					v3.add(aux.item);
					aux = aux.next;
				}
				if(!v3.contains(null))
				{
					listash = new JList<Item>(v3);
					scroll2.setViewportView(listash);
				}			
			}
			
			if(e.getSource() == shoplist2)
			{
				Item aux = (Item)shoplist2.getSelectedValue();
				
				for(Department d :deps)
				{
						ItemList.Node iterator = d.first.next;
						while(iterator.next!=null)
						{
							if(iterator.item.equals(aux))
								department_idtf.setText(String.valueOf(d.id)+" ("+d.name+")");
							iterator = iterator.next;
						}

				}
				pricetf.setText(String.valueOf(aux.getPrice()));
				idtf.setText(String.valueOf(aux.getId()));
				nume_prodtf.setText(aux.getName());
				
			}
			if(e.getSource() == shoplist3)
			{
				Item aux = (Item)shoplist3.getSelectedValue();
				
				for(Department d :deps)
				{
						ItemList.Node iterator = d.first.next;
						while(iterator.next != null)
						{
							if(iterator.item.equals(aux))
								department_idtfwl.setText(String.valueOf(d.id)+" ("+d.name+")");
							iterator = iterator.next;
						}

				}
				pricetfwl.setText(String.valueOf(aux.getPrice()));
				idtfwl.setText(String.valueOf(aux.getId()));
				nume_prodtfwl.setText(aux.getName());
				
			}
			
			
			if(e.getSource() == clienti2)
			{
				listawl = new JList<Item>();
				scroll7.setViewportView(listawl);
				v4.removeAllElements();
				Customer nume = (Customer)clienti2.getSelectedValue();
				ItemList.Node aux = nume.wl.first.next;
				
				while(aux.next != null)
				{
					v4.add(aux.item);
					aux = aux.next;
				}
				
				
				
				if(!v4.contains(null))
				{
					listawl = new JList<Item>(v4);
					scroll7.setViewportView(listawl);
				}

					
					
					
			}
			
		}
	
	}
	
}

class pretDesc implements Comparator<Item>
{

	public int compare(Item item1, Item item2) {
		double s1 = item1.getPrice();
		double s2 = item2.getPrice();
		if(s1 > s2)
			return -1;
		if(s1 < s2)
			return 1;
		return -(item1.getName()).compareTo(item2.getName());
	}
	
}

