package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive(final int recordIndex, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");		
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("threshold", threshold);	
		super.clickOnSubmitButton("Update");
		super.checkColumnHasValue(recordIndex, 0, threshold);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("threshold", threshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updateNegative(final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");		
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("threshold", threshold);	
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		super.signOut();
	}
}
