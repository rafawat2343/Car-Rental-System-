package backendCode;

import java.io.*;
import java.util.ArrayList;

public class Car implements Serializable {
	private int id;
	private String maker, name, colour, type;
	int seatingCapacity;
	String model, condition, regNo;
	private int rentPerHour;
	private CarOwner carOwner;
	private Staff staff;

	public Car() {
	}

	public Car(int id, String maker, String name, String colour, String type, int seatingCapacity, String model,
			String condition, String regNo, int rentPerHour, CarOwner carOwner,Staff staff) {
		super();
		this.id = id;
		this.maker = maker;
		this.name = name;
		this.colour = colour;
		this.type = type;
		this.seatingCapacity = seatingCapacity;
		this.model = model;
		this.condition = condition;
		this.regNo = regNo;
		this.rentPerHour = rentPerHour;
		this.carOwner = carOwner;
		this.staff=staff;
	}

	public int getId() {
		return id;
	}

	public String getMaker() {
		return maker;
	}

	public String getName() {
		return name;
	}

	public String getColour() {
		return colour;
	}

	public String getType() {
		return type;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public String getModel() {
		return model;
	}

	public String getCondition() {
		return condition;
	}

	public String getRegNo() {
		return regNo;
	}

	public int getRentPerHour() {
		return rentPerHour;
	}

	public CarOwner getCarOwner() {
		return carOwner;
	}
	
	public Staff getStaff()
	{
		return staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public void setRentPerHour(int rentPerHour) {
		this.rentPerHour = rentPerHour;
	}

	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}
	
	public void setStaff(Staff staff)
	{
		this.staff=staff;
	}

	@Override
	public String toString() {
		return "Car ID=" + id + "\nMaker=" + maker + "\nName=" + name + "\nColour=" + colour + ", \nType="
				+ type + "\nSeatingCapacity=" + seatingCapacity + "\nModel=" + model + "\nCondition=" + condition
				+ "\nRegNo=" + regNo + "\nRentPerHour=" + rentPerHour + ", \ncarOwner=" + carOwner.toString()
				+ "\n";
	}

	public static ArrayList<Car> view() {
		ArrayList<Car> carList = new ArrayList<>(0);
		ObjectInputStream ois = null;
		try {
			File file = new File("Car.bin");
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			boolean EOF = false;
			while (!EOF) {
				try {
					Car myObj = (Car) ois.readObject();
					carList.add(myObj);
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
		return carList;
	}

	public void add() {
		ArrayList<Car> car = Car.view();
		if (car.isEmpty()) {
			this.id = 1;
		} else {
			this.id = car.get(car.size() - 1).id + 1;
		}
		car.add(this);
		File file = new File("Car.bin");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			for (int i = 0; i < car.size(); i++) {
				outputStream.writeObject(car.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public void update() {
		ArrayList<Car> car = Car.view();
		for (int i = 0; i < car.size(); i++) {
			if (car.get(i).id == id) {
				car.set(i, this);
			}
		}

		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("Car.bin"));
			for (int i = 0; i < car.size(); i++) {
				outputStream.writeObject(car.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public void delete() {

		ArrayList<Car> car = Car.view();
		for (int i = 0; i < car.size(); i++) {
			if ((car.get(i).id == id)) {
				car.remove(i);
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("Car.bin"));
			for (int i = 0; i < car.size(); i++) {
				outputStream.writeObject(car.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public static ArrayList<Car> search(String name) {
		ArrayList<Car> car = Car.view();
		ArrayList<Car> s = new ArrayList<>();
		for (int i = 0; i < car.size(); i++) {
			if (car.get(i).name.equalsIgnoreCase(name)) {
				s.add(car.get(i));
			}
		}
		return s;
	}

	public static Car search(int id) {
		ArrayList<Car> car = Car.view();
		for (int i = 0; i < car.size(); i++) {
			if (car.get(i).id == id) {
				return car.get(i);
			}
		}
		return null;
	}

	public static Car searchByRegNo(String regNo) {
		ArrayList<Car> car = Car.view();
		for (int i = 0; i < car.size(); i++) {
			if (car.get(i).regNo.equalsIgnoreCase(regNo)) {
				return car.get(i);
			}
		}
		return null;
	}

	public static boolean isNameValid(String name) {
		boolean flag = false;
		for (int i = 0; i < name.length(); i++) {
			if (Character.isLetter(name.charAt(i)) || Character.isDigit(name.charAt(i)) || name.charAt(i) == ' ') {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static boolean isRegNoValid(String regNo) {
		String[] token = regNo.split("-");
		if (token.length == 2) {
			for (int i = 0; i < token[0].length(); i++) {
				if (!Character.isLetter(token[0].charAt(i))) {
					return false;
				}
			}
			for (int i = 0; i < token[1].length(); i++) {
				if (!Character.isDigit(token[1].charAt(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean isRented() {
		ArrayList<Car> BookedCars = Booking.getBookedCars();
		for (int i = 0; i < BookedCars.size(); i++) {
			if (BookedCars.get(i).id == this.id) {
				return true;
			}
		}
		return false;
	}

}
