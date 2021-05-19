package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AdministratorShowDashBoardTest extends AcmePlannerTest{
	
	@Test
	public void positiveDashBoardShow() {
		super.signIn("administrator","administrator");
		
		this.navigateHome();
		super.clickOnMenu("Administrator", "Dashboard");
		assert super.getCurrentUrl().equals("/administrator/dashboard/show");
		
		super.signOut();
	}
	
}
