package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex,final String title,
		final String start_date, final String end_date,
		final String workload, final String op_link) 
	{
		assert !StringHelper.isBlank(title);		
		assert !StringHelper.isBlank(start_date);	
		assert !StringHelper.isBlank(end_date);	
		assert !StringHelper.isBlank(workload);	
		
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "List tasks");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start_date);
		super.checkColumnHasValue(recordIndex, 2, end_date);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, op_link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("start_date", start_date);
		super.checkInputBoxHasValue("end_date", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("op_link", op_link);
		
		
		super.signOut();
	}
	
}
