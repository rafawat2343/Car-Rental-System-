package backendCode;

import java.util.ArrayList;
import java.io.*;

public class LoginInfo implements Serializable{
 
	private String userName,password;

	public LoginInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + "]";
	}
	
	public static boolean isLoginInfoEmpty()
	{
		boolean flag = false;
		File file = new File("LoginInfo.bin");
		FileInputStream fis = null;
		if(file.exists())
		{
			try {
				fis = new FileInputStream(file);
				if(fis.available()==0)
				{
					flag = true;
				}
				else
				{
					flag = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			flag = true;
		}
		
		return flag;
	}
	public static LoginInfo view() {
	    LoginInfo loginInfo = null;
	    ObjectInputStream ois = null;

	    try {
	        File file = new File("LoginInfo.bin");
	        if (!file.exists()) {
	            return null; 
	        }

	        FileInputStream fis = new FileInputStream(file);
	        ois = new ObjectInputStream(fis);

	        boolean EOF = false;
	        while (!EOF) {
	            try {
	                loginInfo = (LoginInfo) ois.readObject();
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

	    return loginInfo;
	}

	public void updateLoginInfo()
	{
		LoginInfo loginInfo = LoginInfo.view();
		if (loginInfo == null) {
	        loginInfo = new LoginInfo("", "");
	    }
		loginInfo.setUserName(this.getUserName());
		loginInfo.setPassword(this.getPassword());
		
		
		File file = new File("LoginInfo.bin");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
		
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			oos= new ObjectOutputStream(fos);
			oos.writeObject(loginInfo);
			fos.close();
		}
		catch (FileNotFoundException e) {
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
}
