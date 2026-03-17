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
						
//			click on timestamp tab
			driver.findElement(By.xpath("//div[@title=\"My Timestamp\"]")).click();		
			
			Date date = new Date();
			String Time = new SimpleDateFormat("HH:mm:ss aa").format(new Date());
			
			Thread.sleep(3000);
//			Click record button
			driver.findElement(By.xpath("//button[@class='Record']")).click();
			
//			to get user name
			String user = driver.findElement(By.xpath("/html/body/krn-app/krn-navigator-container/ui-view/krn-header-container/krn-user-info/span/span")).getText();
			
//			Recorded time stamp
			String record = driver.findElement(By.xpath("//span[contains(text(),'Last Timestamp:')]")).getText();

			
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



