package backendCode;

import java.io.*;
import java.util.ArrayList;

public class CarOwner extends Person implements Serializable {
	private double balance;

	public CarOwner(int id, String NID, String name, String contact_no, double balance) {
		super(id, NID, name, contact_no);
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return super.toString() + " \nCarOwner " + "Balance=" + balance + "\n";
	}

	public static ArrayList<CarOwner> view() {
		ArrayList<CarOwner> carOwnerList = new ArrayList<>(0);
		ObjectInputStream ois = null;
		try {
			File file = new File("CarOwner.bin");
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			boolean EOF = false;
			while (!EOF) {
				try {
					CarOwner myObj = (CarOwner) ois.readObject();
					carOwnerList.add(myObj);
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
		return carOwnerList;
	}

	@Override
	public void add() {
		ArrayList<CarOwner> carOwner = CarOwner.view();
		if (carOwner.isEmpty()) {
			this.id = 1;
		} else {
			this.id = carOwner.get(carOwner.size() - 1).id + 1;
			;
		}
		carOwner.add(this);

		File file = new File("CarOwner.bin");
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
			for (int i = 0; i < carOwner.size(); i++) {
				oos.writeObject(carOwner.get(i));
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
		ArrayList<CarOwner> carOwner = CarOwner.view();
		for (int i = 0; i < carOwner.size(); i++) {
			if (carOwner.get(i).id == id) {
				carOwner.set(i, this);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("CarOwner.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < carOwner.size(); i++) {
				oos.writeObject(carOwner.get(i));
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
		ArrayList<CarOwner> carOwner = CarOwner.view();
		for (int i = 0; i < carOwner.size(); i++) {
			if (carOwner.get(i).id == id) {
				carOwner.remove(i);
			}
		}
		ObjectOutputStream oos = null;
		try {
			File file = new File("CarOwner.bin");
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < carOwner.size(); i++) {
				oos.writeObject(carOwner.get(i));
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

	public static CarOwner search(int id) {
		ArrayList<CarOwner> carOwner = CarOwner.view();
		for (int i = 0; i < carOwner.size(); i++) {
			if (carOwner.get(i).id == id) {
				return carOwner.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<CarOwner> search(String name) {
		ArrayList<CarOwner> carOwner = CarOwner.view();
		ArrayList<CarOwner> s = new ArrayList<CarOwner>();
		for (int i = 0; i < carOwner.size(); i++) {
			if (carOwner.get(i).name.equalsIgnoreCase(name)) {
				s.add(carOwner.get(i));
			}
		}
		return s;
	}

	public static CarOwner searchByNID(String nid) {
		ArrayList<CarOwner> carOwner = CarOwner.view();
		for (int i = 0; i < carOwner.size(); i++) {
			if (carOwner.get(i).NID == nid) {
				return carOwner.get(i);
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
