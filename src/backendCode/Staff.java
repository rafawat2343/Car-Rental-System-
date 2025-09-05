package backendCode;

import java.io.*;
import java.util.ArrayList;

public class Staff extends Person implements Serializable {
	private double salary;

	public Staff(int id, String NID, String name, String contact_no, double salary) {
		super(id, NID, name, contact_no);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + "\nStaff " + " Salary=" + salary + "\n";
	}
	
	public static ArrayList<Staff> view() {
		ArrayList<Staff> staffList = new ArrayList<>(0);
		ObjectInputStream ois = null;
		try {
			File file = new File("Staff.bin");
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			boolean EOF = false;
			while (!EOF) {
				try {
					Staff myObj = (Staff) ois.readObject();
					staffList.add(myObj);
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
		return staffList;
	}

	@Override
	public void add() {
		ArrayList<Staff> staff = Staff.view();
		if (staff.isEmpty()) {
			this.id = 1;
		} else {
			this.id = staff.get(staff.size() - 1).id + 1;
			;
		}
		staff.add(this);

		File file = new File("Staff.bin");
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
			for (int i = 0; i <staff.size(); i++) {
				oos.writeObject(staff.get(i));
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
		ArrayList<Staff> staff= Staff.view();
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).id == id) {
				staff.set(i, this);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("Staff.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < staff.size(); i++) {
				oos.writeObject(staff.get(i));
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
		ArrayList<Staff> staff = Staff.view();
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).id == id) {
				staff.remove(i);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("Staff.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < staff.size(); i++) {
				oos.writeObject(staff.get(i));
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

	public static Staff search(int id) {
		ArrayList<Staff> staff = Staff.view();
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).id == id) {
				return staff.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<Staff> search(String name) {
		ArrayList<Staff> staff= Staff.view();
		ArrayList<Staff> s = new ArrayList<Staff>();
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).name.equalsIgnoreCase(name)) {
				s.add(staff.get(i));
			}
		}
		return s;
	}

	public static Staff searchByNID(String nid) {
		ArrayList<Staff> staff = Staff.view();
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).NID == nid) {
				return staff.get(i);
			}
		}
		return null;
	}

	
	
	public ArrayList<Car> getAllCars(){
		ArrayList<Car> cars = Car.view();
		ArrayList<Car> car1 = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarOwner().id == id) {
                car1.add(cars.get(i));
            }
        }
        return car1;
	}
}
