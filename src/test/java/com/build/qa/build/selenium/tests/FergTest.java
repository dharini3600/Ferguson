package com.build.qa.build.selenium.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic functionality and page objects
	 * as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage()).as("The website should load up with the Build.com desktop theme.")
				.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * 
	 * @assert: That the product page we land on is what is expected by checking the
	 *          product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		homePage.search.sendKeys("Moen m6702bn" + Keys.ENTER);
		Assert.assertTrue(homePage.brand.getText().contains("Moen"));
		Assert.assertTrue(homePage.property.getText().toUpperCase().contains("M6702BN"));
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the
	 * cart.
	 * @throws InterruptedException 
	 * 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException {
		driver.get(getConfiguration("BATHSINK"));
		HomePage homePage = new HomePage(driver, wait);
		Thread.sleep(5000);
		//Defined due to stale element
		WebElement product1 = driver.findElement(By.xpath("//p[@data-placement='2' and contains(@data-url,'7289400')]"));
		action.moveToElement(product1).build().perform();
		product1.click();
		performJSClick(homePage.addToCart);
		performJSClick(homePage.cart);
		Assert.assertTrue(homePage.cartProduct.isDisplayed());
	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @throws InterruptedException 
	 * 
	 * @assert that the product and cart total update as expected when the quantity
	 *         is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() throws InterruptedException {
		driver.get(getConfiguration("BATHSINK"));
		HomePage homePage = new HomePage(driver, wait);
		WebElement product1 = driver.findElement(By.xpath("//p[@data-placement='2' and contains(@data-url,'7289400')]"));
		action.moveToElement(product1).build().perform();
		product1.click();
		performJSClick(homePage.addToCart);
		Thread.sleep(2000);
		performJSClick(homePage.changeColor);
		product1 = driver.findElement(By.xpath("//p[@data-placement='2' and contains(@data-url,'7289400')]"));
		performJSClick(product1);
		performJSClick(homePage.incrCount);
		performJSClick(homePage.addToCart);
		performJSClick(homePage.cart);
		Assert.assertTrue(homePage.total.isDisplayed());
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by at least
	 * two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @throws InterruptedException 
	 * 
	 * @assert that the correct filters are being narrowed, and the result count is
	 *         correct, such that each facet selection is narrowing the product
	 *         count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() throws InterruptedException {
		driver.get(getConfiguration("BATHSINK"));
		HomePage homePage = new HomePage(driver, wait);
		homePage.whites.click();
		String currValue = getRecordsCount(homePage.totalRecords);
		homePage.blacks.click();
		String newValue = getRecordsCount(homePage.totalRecords);
		if (Integer.parseInt(newValue)> Integer.parseInt(currValue)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
}
