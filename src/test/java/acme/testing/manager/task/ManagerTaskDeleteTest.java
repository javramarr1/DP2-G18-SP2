package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deletePositive(final int recordIndex, final String title,
		final String description,final String start_date, 
		final String end_date,final String workload,  
		final String op_link, final String is_private) {		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "List tasks");	
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start_date", start_date);
		super.checkInputBoxHasValue("end_date", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("op_link", op_link);
		super.checkInputBoxHasValue("is_private", is_private);
		super.clickOnSubmitButton("Delete");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("workload", "1.50");
		super.signOut();
		
	}

}
