package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class SpecialManageWikiaHome extends WikiBasePageObject {

	@FindBy(id = "video-games-amount")
	private WebElement videoGamesAmount;
	@FindBy(id = "entertainment-amount")
	private WebElement entertainmentAmount;
	@FindBy(id = "lifestyle-amount")
	private WebElement lifestyleAmount;

	public SpecialManageWikiaHome(WebDriver driver) {
		super(driver);
	}

	/**
	 * getting desired slot setup from http://corp.wikia.com/wiki/Special:ManageWikiaHome page
	 * @return
	 */
	public HashMap<String, Integer> getSlotSetup() {
		HashMap<String, Integer> slotSetup = new HashMap<String, Integer>();
		slotSetup.put(HubName.Video_Games.toString(), Integer.parseInt(videoGamesAmount.getAttribute("value")));
		slotSetup.put(HubName.Entertainment.toString(), Integer.parseInt(entertainmentAmount.getAttribute("value")));
		slotSetup.put(HubName.Lifestyle.toString(), Integer.parseInt(lifestyleAmount.getAttribute("value")));
		return slotSetup;
	}
}