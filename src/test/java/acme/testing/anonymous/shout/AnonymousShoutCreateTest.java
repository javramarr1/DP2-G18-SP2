package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest  extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex,final String author,final String text,final String info,
		final String xxxCreationDate, final String xxxMoment, final String xxxMoney, final String flag) {
		assert !StringHelper.isBlank(author);		
		assert !StringHelper.isBlank(text);	
		
		super.clickOnMenu("Anonymous", "Shout!");
		

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.creationDate", xxxCreationDate);
		super.fillInputBoxIn("xxx.moment", xxxMoment);
		super.fillInputBoxIn("xxx.money", xxxMoney);
		super.fillInputBoxIn("xxx.flag", flag);
		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List-Shout");
		

		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, xxxCreationDate);
		super.checkColumnHasValue(recordIndex, 5, xxxMoment);
		super.checkColumnHasValue(recordIndex, 6, xxxMoney);
		super.checkColumnHasValue(recordIndex, 7, flag);



	
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex,final String author,final String text,final String info ) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	

}
