package acme.testing.administrator.dashboard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorShowDashBoardTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveDashBoardShow(final int recordIndex, final String value) {
		super.signIn("administrator","administrator");
		
		this.navigateHome();
		super.clickOnMenu("Administrator", "Dashboard");
		assert super.getCurrentUrl().equals("/administrator/dashboard/show");
		
		super.signOut();
	}
	
}
