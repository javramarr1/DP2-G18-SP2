package acme.testing.anonymous.shout;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousListTest	extends AcmePlannerTest {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex,final String moment,final String author,final String text,final String info,
		final String xxxCreationDate, final String xxxMoment, final String xxxMoney, final String flag) {		
	

		super.clickOnMenu("Anonymous", "List-Shout");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, xxxCreationDate);
		super.checkColumnHasValue(recordIndex, 5, xxxMoment);
		super.checkColumnHasValue(recordIndex, 6, xxxMoney);
		super.checkColumnHasValue(recordIndex, 7, flag);
		
	}

}
