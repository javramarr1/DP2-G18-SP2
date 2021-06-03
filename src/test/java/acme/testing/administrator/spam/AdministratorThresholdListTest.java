  
package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdListTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/threshold/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex,final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, threshold);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("threshold", threshold);
		super.signOut();
	}
}
