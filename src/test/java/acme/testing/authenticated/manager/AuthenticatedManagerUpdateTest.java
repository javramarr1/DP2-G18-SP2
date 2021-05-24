package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerUpdateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex,final String company, final String sector) {
		assert !StringHelper.isBlank(company);	
		assert !StringHelper.isBlank(sector);	
		
		super.signUp("maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com");
		super.signIn("maria@mail.com", "maria@mail.com");
		
		super.clickOnMenu("Account", "Become a manager");
		super.fillInputBoxIn("company", "google");
		super.fillInputBoxIn("sector", "it");
		super.clickOnSubmitButton("Register");

		super.clickOnMenu("Account", "Change data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "Change data");
		super.checkInputBoxHasValue("company", "googleupd");
		super.checkInputBoxHasValue("sector", "itupd");
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateNegative(final int recordIndex,final String company, final String sector) {	
		
		super.signIn("maria@mail.com", "maria@mail.com");
		
		super.clickOnMenu("Account", "Change data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		
		super.signOut();
		
	}

}
