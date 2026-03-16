package kronos.org;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoAttendanceSystem {	
		
		public static void main(String[]args) throws InterruptedException, AWTException
		{
				
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			driver.manage().window().maximize();
			
			driver.get("https://mytime.aka.corp.amazon.com/");
			driver.navigate().refresh();
			Robot R = new Robot();
						
			driver.findElement(By.xpath("//div[@title=\"My Timestamp\"]")).click();		
			
			Date date = new Date();
			String Time = new SimpleDateFormat("HH:mm:ss aa").format(new Date());
			
			Thread.sleep(10000);
			
			for(int i=1; i<=4; i++) {	
				R.keyPress(KeyEvent.VK_TAB);
				R.keyRelease(KeyEvent.VK_TAB);
			}
			
//			R.keyPress(KeyEvent.VK_ENTER);
//			R.keyRelease(KeyEvent.VK_ENTER);
			
			String user = driver.findElement(By.xpath("/html/body/krn-app/krn-navigator-container/ui-view/krn-header-container/krn-user-info/span/span")).getText();
			
			String record = driver.findElement(By.xpath("//span[contains(text(),'Last Timestamp:')]")).getText();
//			System.out.println(record);
			
			if(record == "Last Timestamp:") {
				String done = "Unable to Login, Please try again";

				SystemTray tray = SystemTray.getSystemTray();
			        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
			        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
			        trayIcon.setImageAutoSize(true);
			        trayIcon.setToolTip("System tray icon demo");
			        tray.add(trayIcon);
			        trayIcon.displayMessage("Hello," + user , done, MessageType.INFO);
				
			}
			else {	        
			        if(Time.contains("PM")) {
						String done = "you have Logged out successfully";
						 
						SystemTray tray = SystemTray.getSystemTray();
					        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
					        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
					        trayIcon.setImageAutoSize(true);
					        trayIcon.setToolTip("System tray icon demo");
					        tray.add(trayIcon);
					        trayIcon.displayMessage("Hello," + user , done, MessageType.INFO);
						}
						
					else {
						String done = "you have Logged in successfully";
						
						SystemTray tray = SystemTray.getSystemTray();
					        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
					        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
					        trayIcon.setImageAutoSize(true);
					        trayIcon.setToolTip("System tray icon demo");
					        tray.add(trayIcon);
					        trayIcon.displayMessage("Hello," + user , done, MessageType.INFO);
					}
			}
			
			
			driver.close();
		}

	}
