package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.entities.UserAccountStatus;
import acme.testing.AcmePlannerTest;

public class AdministratorUserAccountsUpdateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccounts/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updatePositive(final int recordIndex, final String username, 
		final String name,final String surname, final String email,
		final String status, final String newStatus) 
	{
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);	
		super.checkColumnHasValue(recordIndex, 2, surname);	
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("newStatus", newStatus);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		if(newStatus.equals(UserAccountStatus.KEEP.toString())) {
			super.checkInputBoxHasValue("status", status);
			
			
		}else {
			super.checkInputBoxHasValue("status", newStatus);
		}
	}

}
