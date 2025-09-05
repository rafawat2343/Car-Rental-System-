package backendCode;

import java.io.*;
import java.util.ArrayList;

public class Booking implements Serializable {
	private int id;
	private Customer customer;
	private Car car;
	private long rentTime, returnTime;

	public Booking() {
	}

	public Booking(int id, Customer customer, Car car, long rentTime, long returnTime) {
		super();
		this.id = id;
		this.customer = customer;
		this.car = car;
		this.rentTime = rentTime;
		this.returnTime = returnTime;
	}

	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Car getCar() {
		return car;
	}

	public long getRentTime() {
		return rentTime;
	}

	public long getReturnTime() {
		return returnTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setRentTime(long rentTime) {
		this.rentTime = rentTime;
	}

	public void setReturnTime(long returnTime) {
		this.returnTime = returnTime;
	}

	@Override
	public String toString() {
		return "Booking " + "ID=" + id + " \ncustomer=" + customer.toString() + " \ncar=" + car.toString()
				+ " \nRentTime=" + rentTime + " , ReturnTime=" + returnTime + "\n";
	}

	public static ArrayList<Booking> view() {
		ArrayList<Booking> bookingList = new ArrayList<>(0);
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("Booking.bin"));
			boolean EOF = false;
			while (!EOF) {
				try {
					Booking myObj = (Booking) inputStream.readObject();
					bookingList.add(myObj);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return bookingList;
	}

	public void add() {
		ArrayList<Booking> booking = Booking.view();
		if (booking.isEmpty()) {
			this.id = 1;
		} else {
			this.id = booking.get(booking.size() - 1).id + 1;
		}
		this.returnTime = 0;
		booking.add(this);
		File file = new File("Booking.bin");
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
			for (int i = 0; i < booking.size(); i++) {
				outputStream.writeObject(booking.get(i));
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
		ArrayList<Booking> booking = Booking.view();
		for (int i = 0; i < booking.size(); i++) {
			if (booking.get(i).id == id) {
				booking.set(i, this);
			}
		}

		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("Booking.bin"));
			for (int i = 0; i < booking.size(); i++) {
				outputStream.writeObject(booking.get(i));
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
		ArrayList<Booking> booking = Booking.view();
		for (int i = 0; i < booking.size() - 1; i++) {
			if ((booking.get(i).id == id)) {
				for (int j = i; j < booking.size() - 1; j++) {
					booking.set(j, (booking.get(j + 1)));
				}

			}
		}

		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("Booking.bin"));
			for (int i = 0; i < booking.size() - 1; i++) {
				outputStream.writeObject(booking.get(i));
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

	public int calculateBill() {
		long rentTime = this.getRentTime();
		long returnTime = this.getReturnTime();
		long totalTime = returnTime - rentTime;
		totalTime = totalTime / (1000 * 60 * 60);
		int rentPerHour = this.getCar().getRentPerHour();
		if (totalTime != 0) {
			return (int) (rentPerHour * totalTime);
		} else {
			return rentPerHour;
		}
	}

	public static ArrayList<Booking> searchByCustomerID(int CustomerID) {
		ArrayList<Booking> bookingList = new ArrayList<>(0);
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("Booking.bin"));
			boolean EOF = false;
			while (!EOF) {
				try {
					Booking myObj = (Booking) inputStream.readObject();
					if (myObj.customer.getId() == CustomerID) {
						bookingList.add(myObj);
					}
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return bookingList;
	}

	public static ArrayList<Booking> searchByCarRegNo(String CarRegNo) {
		ArrayList<Booking> bookingList = new ArrayList<>(0);
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("Booking.bin"));
			boolean EOF = false;
			while (!EOF) {
				try {
					Booking myObj = (Booking) inputStream.readObject();
					if (myObj.car.getRegNo().equalsIgnoreCase(CarRegNo)) {
						bookingList.add(myObj);
					}
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return bookingList;
	}

	public static ArrayList<Booking> searchByCarID(int carID) {
		ArrayList<Booking> bookingList = new ArrayList<>(0);
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("Booking.bin"));
			boolean EOF = false;
			while (!EOF) {
				try {
					Booking myObj = (Booking) inputStream.readObject();
					if (myObj.car.getId() == carID) {
						bookingList.add(myObj);
					}
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return bookingList;
	}

	public static ArrayList<Car> getBookedCars() {
		ArrayList<Car> bookedCars = new ArrayList<>();
		ArrayList<Booking> bookings = Booking.view();
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).returnTime == 0) {
				bookedCars.add(bookings.get(i).car);
			}
		}
		return bookedCars;
	}

	public static ArrayList<Car> getUnbookedCars() {
		ArrayList<Car> allCars = Car.view();
		ArrayList<Car> bookedCars = Booking.getBookedCars();
		for (int i = 0; i < bookedCars.size(); i++) {
			allCars.remove(bookedCars.get(i));
		}
		return allCars;
	}

}
