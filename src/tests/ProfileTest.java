package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest{
	
	@Test (priority = 1)
	public void editProfileTest () throws InterruptedException {
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopupDialog();
		loginPage.login(email, password);
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Login Successfull"), "[ERROR] Login message didn't appear.");
		
		driver.get(baseUrl + "member/profile");
		profilePage.personalInformation("United Kingdom", "Manchester", "Manchester", 
						"Ronald", "Williams", "111", "9938154321", "18000");
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Setup Successfull"), "[ERROR] Setup message didn't appear.");
		authPage.logout();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Logout Successfull"), "[ERROR] Logout message didn't appear.");
		
	}
	
	@Test (priority = 2)
	public void changeProfileImageTest () throws InterruptedException, IOException {
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopupDialog();
		loginPage.login(email, password);
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Login Successfull"), "[ERROR] Login message didn't appear.");
		driver.get(baseUrl + "member/profile");
		String imagePath = new File("img/profilePicture.svg.png").getCanonicalPath();
		profilePage.uploadProfileImage(imagePath);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Profile Image Uploaded Successfully"), "[ERROR] Image upload message didn't appear.");
		notificationSystemPage.waitUntilNotificationDisappear();
		profilePage.removeProfileImage();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Profile Image Deleted Successfully"), "[ERROR] Image upload message didn't appear.");
		notificationSystemPage.waitUntilNotificationDisappear();
		authPage.logout();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Logout Successfull"), "[ERROR] Logout message didn't appear.");
	}

}
