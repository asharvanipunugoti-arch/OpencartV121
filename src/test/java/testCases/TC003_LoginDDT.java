package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;




public class TC003_LoginDDT extends BaseClass{

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")  //getting dp from diff class
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		
		logger.info("******Started TC003_LoginDDT**********");
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetPage= myacc.isAccountPageExists();
		
		
		/*
		Data is valid---login success---test pass---logout
		Data is valid---login fail---test fail

		Data is invalid---login success---test fail---logout
		Data is invalid---login fails----test pass

		*/
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage == true)
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage == true)
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
		logger.info("******Finished TC003_LoginDDT**********");
		
		
	}
	
	
	
	
	
	
}
