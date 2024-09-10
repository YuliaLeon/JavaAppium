package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTestsTask extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check for element appearance")
    @Description("Search an article, click article title and go to the detailed screen of the article")
    @Step("Starting test testIsElementPresent")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testIsElementPresent() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.assertForElementPresent("Java (programming language)");
    }
}
