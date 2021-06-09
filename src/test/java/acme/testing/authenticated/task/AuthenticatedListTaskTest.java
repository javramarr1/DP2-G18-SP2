package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedListTaskTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex,final String title,
		final String start_date, final String end_date,
		final String workload, final String description,
		final String op_link, final String is_private) {	
		
		if(recordIndex==0)
		super.signUp("maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com", "maria@mail.com");
		super.signIn("maria@mail.com", "maria@mail.com");
		super.clickOnMenu("Authenticated user", "List public finished tasks");
		
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


	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
		public void negativeAuthenticatedListandShowTask(final int recordIndex, final String title, final String startMoment, final String endMoment,final String description ,final String workload,final String status,final String link, final String id) {
			super.signIn("administrator", "administrator");
			super.signOut();
			final String path="/authenticated/task/list";
			final String query=null;
			
			super.navigate(path, query);
			
			super.checkErrorsExist();
			super.checkPanicExists();
			
			final String query2="id="+id;
			final String path2="/authenticated/task/list/show";

			super.navigate(path2, query2);
			
			super.checkPanicExists();
	}

}
