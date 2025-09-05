package backendCode;
import java.io.*;
import java.util.ArrayList;

public class Customer extends Person implements Serializable{
	private int bill;

	public Customer(int id, String NID, String name, String contact_no, int bill) {
		super(id, NID, name, contact_no);
		this.bill = bill;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}
	
	@Override
    public String toString() {
        return super.toString() + "\nCustomer Bill=" + bill + "\n";
    }

	public static ArrayList<Customer> view() {
		ArrayList<Customer> customerList = new ArrayList<>(0);
		ObjectInputStream ois = null;
		try {
			File file = new File("Customer.bin");
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			boolean EOF = false;
			while (!EOF) {
				try {
					Customer myObj = (Customer) ois.readObject();
					customerList.add(myObj);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return customerList;
	}

	@Override
	public void add() {
		ArrayList<Customer> customer = Customer.view();
		if (customer.isEmpty()) {
			this.id = 1;
		} else {
			this.id = customer.get(customer.size() - 1).id + 1;
			;
		}
		customer.add(this);

		File file = new File("Customer.bin");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i <customer.size(); i++) {
				oos.writeObject(customer.get(i));
			}
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	@Override
	public void update() {
		ArrayList<Customer> customer= Customer.view();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).id == id) {
				customer.set(i, this);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("Customer.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < customer.size(); i++) {
				oos.writeObject(customer.get(i));
			}
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void delete() {
		ArrayList<Customer> customer = Customer.view();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).id == id) {
				customer.remove(i);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("Customer.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < customer.size(); i++) {
				oos.writeObject(customer.get(i));
			}
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public static Customer search(int id) {
		ArrayList<Customer> customer = Customer.view();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).id == id) {
				return customer.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<Customer> search(String name) {
		ArrayList<Customer> customer= Customer.view();
		ArrayList<Customer> s = new ArrayList<Customer>();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).name.equalsIgnoreCase(name)) {
				s.add(customer.get(i));
			}
		}
		return s;
	}	

	public static Customer searchByNID(String nid) {
		ArrayList<Customer> customer = Customer.view();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).NID == nid) {
				return customer.get(i);
			}
		}
		return null;
	}

}
