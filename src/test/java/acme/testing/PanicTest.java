package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class PanicTest extends AcmePlannerTest{
	
	@Test
	@Order(10)
	public void positivePanic() {
		super.navigate("/master/oops", "");
		super.checkPanicExists();

	}

	@Test
	@Order(10)
	public void negativePanic() {
		super.navigate("/master/welcome", "");
		super.checkNotPanicExists();

	}

}
