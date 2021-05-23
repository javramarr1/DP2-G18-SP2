package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamDeleteTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deletePositive(final int recordIndex, final String word, final String wordnext) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "List spam words");
		super.checkColumnHasValue(recordIndex, 0, word);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("word", word);
		super.clickOnSubmitButton("Delete");
		super.checkColumnHasValue(recordIndex, 0, wordnext);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("word", wordnext);
		super.signOut();
	}
}
