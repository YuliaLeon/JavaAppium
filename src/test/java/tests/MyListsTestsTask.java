package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTestsTask extends CoreTestCase {

    private static final String
            login = "YuliaTest",
            password = "TestAuto20";

    @Test
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

            assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitleByXpath()
            );
        }
        ArticlePageObject.addArticleToMyNewList("Learning programming");
        NavigationUI.clickReturnButton();
        SearchPageObject.clearSearchInput();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.addArticleToMyCreatedList("Learning programming");
        NavigationUI.openNavigation();
        NavigationUI.clickToMyLists();
        ArticlePageObject.clickViewListButton();
        MyListsPageObject.waitForArticleToAppearByTitle("Java (programming language)");
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        ArticlePageObject.compareArticleTitles("Appium");
        ArticlePageObject.ifArticleIsStillSaved("Appium");
    }
}
