package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for saving articles to lists")
public class MyListsTestsTask extends CoreTestCase {

    private static final String
            login = "YuliaTest",
            password = "TestAuto20";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "SaveList")})
    @DisplayName("Save and delete articles from save list")
    @Description("We search an article, create new list, add the article to the list, then add the second article and delete one of the articles")
    @Step("Starting test testSaveAndDeleteArticlesInMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveAndDeleteArticlesInMyList() throws InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);


        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitleByXpath();
        String name_of_folder = "Learning programming";

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitleByXpath()
            );
        }
        NavigationUI.clickReturnButton();
        SearchPageObject.clearSearchInput();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyCreatedList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI.openNavigation();
        NavigationUI.clickToMyLists();
        ArticlePageObject.clickViewListButton();
        MyListsPageObject.waitForArticleToAppearByTitle("Java (programming language)");
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        ArticlePageObject.compareArticleTitles("Appium");
        ArticlePageObject.ifArticleIsStillSaved("Appium");
    }
}
