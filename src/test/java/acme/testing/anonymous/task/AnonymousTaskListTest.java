package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex,final String title,
		final String start_date, final String end_date,
		final String workload, final String description,
		final String op_link, final String is_private) {	
		
		super.clickOnMenu("Anonymous", "List unfinished tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start_date);	
		super.checkColumnHasValue(recordIndex, 2, end_date);	
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start_date", start_date);
		super.checkInputBoxHasValue("end_date", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("op_link", op_link);
		super.checkInputBoxHasValue("is_private", is_private);


	}
	@Test
	public void negativeShowTask() {
		super.signIn("administrator", "administrator");
		super.navigate("/anonymous/task/show", "id=23");
        
        super.checkErrorsExist();
        super.signOut();
		
	}

}


