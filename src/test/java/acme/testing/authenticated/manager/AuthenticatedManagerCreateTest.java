package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex,final String company, final String sector) {
		assert !StringHelper.isBlank(company);	
		assert !StringHelper.isBlank(sector);	
		
		super.signUp("maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com");
		super.signIn("maria@mail.com", "maria@mail.com");
		
		super.clickOnMenu("Account", "Become a manager");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");

		super.clickOnMenu("Account", "Change data");
		super.checkInputBoxHasValue("company", "google");
		super.checkInputBoxHasValue("sector", "it");
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String company, final String sector) {	
		
		super.signUp("maria2@mail.com", "maria2@mail.com", "maria2@mail.com", "maria2@mail.com", "maria2@mail.com");
		super.signIn("maria2@mail.com", "maria2@mail.com");
		
		super.clickOnMenu("Account", "Become a manager");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();
		
		super.signOut();
		
	}

}
