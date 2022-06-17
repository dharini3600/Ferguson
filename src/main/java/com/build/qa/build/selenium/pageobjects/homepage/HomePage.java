package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {

	private By homePageWrapper;

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");
	}

	public boolean onHomePage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
	}

	@FindBy(xpath = "//div[@id='react-type-ahead-normal']//input[@name='search']")
	public WebElement search;

	@FindBy(xpath = "//h2[@class='product__brand']")
	public WebElement brand;

	@FindBy(xpath = "//p[@class='product__prop']")
	public WebElement property;

	@FindBy(xpath = "//p[@data-placement='2' and contains(@data-url,'7289400')]")
	public WebElement product;

	@FindBy(xpath = "//span[text()='Add to Cart']")
	public WebElement addToCart;

	@FindBy(xpath = "//li//span[@class='count']")
	public WebElement cart;

	@FindBy(xpath = "//a[contains(@title,'Single Handle Monoblock Bathroom Sink Faucet in Polished Chrome')]")
	public WebElement cartProduct;

	// @FindBy(xpath = "//p[@data-placement='2' and
	// contains(@data-url,'7289400')]//parent::div//parent::div//div[@class='colorCheck']/label[contains(@data-popover-html,'Brushed
	// Nickel')]")
	@FindBy(xpath = "//a[contains(@href,'7289400')]//following-sibling::div/label[contains(@data-popover-html,'Brushed Nickel')]")
	public WebElement changeColor;

	@FindBy(xpath = "//button[contains(text(),'+')]")
	public WebElement incrCount;

	@FindBy(xpath = "//b[contains(text(),'$344.76')]")
	public WebElement total;
	
	@FindBy(xpath ="//div[@class='ri-nav-ul-li-content']/p[contains(text(),'Whites')]")
	public WebElement whites;
	
	@FindBy(xpath ="//div[@class='ri-nav-ul-li-content']/p[contains(text(),'Blacks')]")
	public WebElement blacks;
	
	@FindBy(xpath = "//div[@class='word total-record']")
	public WebElement totalRecords;
	
	
}
