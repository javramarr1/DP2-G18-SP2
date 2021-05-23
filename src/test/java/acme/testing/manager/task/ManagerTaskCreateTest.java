package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createPositive(final int recordIndex,final String title,
		final String description,final String start_date, 
		final String end_date,final String workload,  
		final String op_link, final String is_private) 
	{
		assert !StringHelper.isBlank(title);		
		assert !StringHelper.isBlank(start_date);	
		assert !StringHelper.isBlank(end_date);	
		assert !StringHelper.isBlank(workload);	
		assert !StringHelper.isBlank(description);	
		assert !StringHelper.isBlank(is_private);	
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("start_date", start_date);
		super.fillInputBoxIn("end_date", end_date);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("op_link", op_link);
		super.fillInputBoxIn("is_private", is_private);
		
		super.clickOnSubmitButton("Create Task");

		super.clickOnMenu("Manager", "List tasks");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start_date);
		super.checkColumnHasValue(recordIndex, 2, end_date);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, op_link);
		
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start_date", start_date);
		super.checkInputBoxHasValue("end_date", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("op_link", op_link);
		super.checkInputBoxHasValue("is_private", is_private);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String title,
		final String start_date, final String end_date,
		final String workload, final String description,
		final String op_link, final String is_private) 
	{	
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("start_date", start_date);
		super.fillInputBoxIn("end_date", end_date);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("op_link", op_link);
		super.fillInputBoxIn("is_private", is_private);
		super.clickOnSubmitButton("Create Task");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
