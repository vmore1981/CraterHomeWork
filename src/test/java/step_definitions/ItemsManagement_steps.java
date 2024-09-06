package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.User_management_page;
import pages.Dashboard_page;
import pages.Items_page;
import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;

public class ItemsManagement_steps {

	User_management_page acp = new User_management_page();
	Dashboard_page dash_page = new Dashboard_page();
	BrowserUtils utils = new BrowserUtils();
	Items_page items = new Items_page();
	String itemname = "";
	int itemprice;
	BrowserUtils actionutils = new BrowserUtils();
	

	
	

		
	@Given("As an entity user, I am logged in")
	public void as_an_entity_user_i_am_logged_in() {
	
		
		Driver.getDriver().get(DataReader.getProperty("url"));
		
		Assert.assertTrue(acp.login_username.isDisplayed());
		Assert.assertTrue(acp.login_password.isDisplayed());
		Assert.assertTrue(acp.login_Btn.isDisplayed());
		Assert.assertTrue(acp.login_forgetPassword_link.isDisplayed());
		
		
		actionutils.sendkeysWithActionsClass(acp.login_username, DataReader.getProperty("username"));
		actionutils.sendkeysWithActionsClass(acp.login_password, DataReader.getProperty("password"));
		
		actionutils.clickWithActionsClass(acp.login_Btn);
		
		actionutils.waitUntilUrlChanged("dashboard");
		String current_url = Driver.getDriver().getCurrentUrl();
		Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_url);
		Assert.assertTrue(current_url.contains("dashboard"));
	
	}
	
	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
			dash_page.Items_tab.click();	
			utils.waitUntilUrlChanged("items");
			Assert.assertTrue(items.items_items_text.isDisplayed());
			
	}

	
	@When("I am click on Add Item button")
	public void i_am_click_on_add_item_button() {
			items.items_add_item_button.click();
	}

	
	@Then("I should be on New Item Page")
	public void i_should_be_on_new_item_page() {
			utils.waitUntilUrlChanged("items/create");
			Assert.assertTrue(items.items_Input_page_newItem_text.isDisplayed());
	}

	
	@When("I provide Item name {string} and price {int} unit {string} and description {string}")
	public void i_provide_item_name_and_price_unit_and_description(String item_name, int item_price, String item_unit, String item_des) {
		itemname = item_name; 
		itemprice = item_price;
		
		utils.waitUntilUrlChanged("items/create");
		Assert.assertTrue(items.items_Input_page_newItem_text.isDisplayed());
		
		items.items_input_page_name_box.sendKeys(itemname);
		items.items_input_page_price_box.sendKeys(Integer.toString(item_price));
		
		items.items_input_page_unit_dropdown.click();
		utils.waitForElementToBeVisible(items.items_input_page_unit_pc_option);
		items.items_input_page_unit_pc_option.click();
		items.items_input_page_description.sendKeys(item_des);
		
	}

	
	@When("I click Save Item button")
	public void i_click_save_item_button() {
		
		items.items_page_saveltem_btn.click();
		utils.waitUntilUrlChanged("items");
		Assert.assertTrue(items.items_items_text.isDisplayed());

	}

	@Then("The item is added to the item list table")
	public void the_item_is_added_to_the_item_list_table() throws InterruptedException {
		
		Assert.assertTrue(items.items_success_message.isDisplayed());
		items.items_success_message.click();
		utils.waitForElementToBeVisible(items.items_page_filter_btn);
		items.items_page_filter_btn.click();
		
		utils.waitForElementToBeVisible(items.items_page_filter_name_box);
		items.items_page_filter_name_box.sendKeys(itemname);
		
		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+itemname+"']")).isDisplayed());
				
	}
	

	@When("I select the item {string}")
	public void i_select_the_item(String string) {

		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+string+"']"));
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+string+"']")).isDisplayed());
		Driver.getDriver().findElement(By.xpath("//a[text()='"+string+"']")).click();
		utils.waitUntilUrlChanged("edit");
		
	}

	@Then("I should be on item details page")
	public void i_should_be_on_item_details_page() {

		utils.waitForElementToBeVisible(items.items_page_edit_item_headerText);
		Assert.assertTrue(items.items_page_edit_item_headerText.isDisplayed());
		
		
	}

	@When("I update the item price to {int} dollars")
	public void i_update_the_item_price_to_dollars(Integer newPrice) {

		itemprice = newPrice;

		items.items_input_page_price_box.clear();
		items.items_input_page_price_box.sendKeys(Integer.toString(newPrice));
		
				
	}

	@When("I click Update Item button")
	public void i_click_update_item_button() {

		items.items_page_update_item_btn.click();
		
		
	}

	@Then("the Item price is updated to {int} dollars")
	public void the_item_price_is_updated_to_dollars(Integer int1) {

		Assert.assertTrue(items.items_success_message.isDisplayed());
		items.items_success_message.click();

		utils.waitForElementToBeVisible(items.items_page_filter_btn);
		items.items_page_filter_btn.click();
		
		utils.waitForElementToBeVisible(items.items_page_filter_price_box);
		items.items_page_filter_price_box.sendKeys(Integer.toString(int1));
		
		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+itemname+"']")).isDisplayed());
		
	}
	
	
}
