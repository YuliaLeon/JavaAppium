package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for searching articles")
public class SearchTestsTask extends CoreTestCase {


    @Test
    @Feature(value = "Search")
    @DisplayName("Check an article not to present after clearing search field")
    @Description("Search an article, wait for it to present, clear search field and check the article disappearance")
    @Step("Starting test testArticleNotPresent")
    @Severity(value = SeverityLevel.NORMAL)
    public void testArticleNotPresent() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Automation");
        SearchPageObject.waitForSearchResult("Automation");
        SearchPageObject.waitForSearchResult("Automation bias");
        SearchPageObject.waitForSearchResult("Automation Master");
        SearchPageObject.clearSearchInput();
        SearchPageObject.waitForSearchResultToDisappear("Automation");
        SearchPageObject.waitForSearchResultToDisappear("Automation bias");
        SearchPageObject.waitForSearchResultToDisappear("Automation Master");
    }
}
