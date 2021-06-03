package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountsListTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccounts/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void list(final int recordIndex, final String username, 
		final String name,final String surname, final String email,
		final String status) 
	{
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);	
		super.checkColumnHasValue(recordIndex, 2, surname);	
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("status", status);
	}

}
