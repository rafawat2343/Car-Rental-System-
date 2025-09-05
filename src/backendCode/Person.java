package backendCode;

import java.io.*;

public abstract class Person implements Serializable {
	
	protected int id;
	protected String NID, name, contact_no;
	
	public Person(int id, String NID, String name, String contact_no) {
		super();
		this.id = id;
		this.NID = NID;
		this.name = name;
		this.contact_no = contact_no;
	}

	public int getId() {
		return id;
	}

	public String getNID() {
		return NID;
	}

	public String getName() {
		return name;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNID(String NID) {
		this.NID = NID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	public abstract void add();
	public abstract void update();
	public abstract void delete();
	
	@Override
	public String toString() {
		return "\nPerson ID=" + id + " \nName=" + name +" \nNID=" + NID + " \nContact_no=" + contact_no ;
	}
	
	public static boolean isNIDValid(String NID)
	{
		boolean flag = true;
		if(NID.length() == 13|| NID.length() == 10)
		{
			for(int i=0;i<NID.length();i++)
			{
				if(!Character.isDigit(NID.charAt(i))) {
					flag = false;
					break;
				}
			}
		}
		else
		{
			flag = false;
		}
		
		return flag;
	}
	public static boolean isContactNoValid(String contact_no)
	{
		boolean flag = true;
		if(contact_no.length() == 11 )
		{
			if(contact_no.substring(0,2).equals("01"))
			{
				for(int i=0; i<contact_no.length(); i++)
				{
					if(!Character.isDigit(contact_no.charAt(i)))
					{
						flag = false;
						break;
					}
				}
			}
			else
			{
				flag = false;
			}
		}
		else
		{
			flag = false;
		}
		
		return flag;
	}

	public static boolean isNameVald(String name)
	{
		boolean flag = false;
		for(int i=0; i<name.length();i++)
		{
			if(Character.isLetter(name.charAt(i)) || name.charAt(i)==' '|| name.charAt(i)=='.')
			{
				flag = true;
			}
			else
			{
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	public static boolean isIdValid(String id)
	{
		boolean flag = true;
		for(int i=0; i<id.length();i++)
		{
			if(!Character.isDigit(id.charAt(i)))
			{
				flag = false;
				break;
			}
		}
		if(flag)
		{
			if(Integer.parseInt(id)<0)
			{
				flag = false;
			}
		}
		
		return flag;
	}
}
