package bdd;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class Account extends StoryBase {

	@When("I open '$page'")
	public void whenIOpen(final String page) {
		System.out.println("as");
	}

	@When("I enter my $'demo@tlen.pl' and '$passwprd' then click on the '$Register' button")
	public void whenIClickOnTheButton(final String email,
			final String password, final String button) {
		System.out.println("as");
	}

	@Then("I should see main page")
	public void thenIShouldSeeAnError() {
		System.out.println("as");
	}

}
