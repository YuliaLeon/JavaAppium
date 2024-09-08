package lib.ui;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            NAVBAR_SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            VIEW_LIST_BUTTON,
            ARTICLE_TITLE_BEFORE_CLICK,
            ARTICLE_TITLE_AFTER_CLICK,
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            CREATED_LIST,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ARTICLE_TITLE_MW,
            TITLE_AFTER_CLICK_MW;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static String getArticleTitleBeforeClickByXpath(String article_title)
    {
        return ARTICLE_TITLE_BEFORE_CLICK.replace("{ARTICLE_TITLE_BEFORE_CLICK}", article_title);
    }
    private static String getSavedArticle (String article_title) {
        return ARTICLE_TITLE_MW.replace("{ARTICLE_TITLE}", article_title);
    }

    private static String getArticleTitleAfterClickByXpath(String article_title)
    {
        return ARTICLE_TITLE_AFTER_CLICK.replace("{ARTICLE_TITLE_AFTER_CLICK}", article_title);
    }

    private static String getArticleTitleAfterClick (String article_title)
    {
        return TITLE_AFTER_CLICK_MW.replace("{TITLE_AFTER_CLICK}", article_title);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find title article on page!", 15);
    }

    public String getArticleTitleByXpath()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getText();
        }

    }

    public void swipeUpToFooter()
    {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    20
            );
        } else {
            this.scrollWebPageElementTillNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

    public void addArticleToMyNewList(String name_of_folder)
    {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    NAVBAR_SAVE_BUTTON,
                    "Cannot find save button",
                    5
            );

            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to list' button",
                    10
            );

            this.waitForElementAndClick(
                    MY_LIST_NAME_INPUT,
                    "Cannot find text input field",
                    5
            );

            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    name_of_folder,
                    "Cannot enter folder name",
                    5
            );

            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot find and press OK button",
                    5
            );
        }
    }

    public void clickViewListButton()
    {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    VIEW_LIST_BUTTON,
                    "Cannot find 'View list' button",
                    5
            );
        }
    }

    public void addArticlesToMySaved()
    {
        if(Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void addArticleToMyCreatedList (String name_of_folder)
    {
        if(Platform.getInstance().isAndroid()) {
            waitForElementAndClick(
                    SAVE_BUTTON,
                    "Cannot find save button",
                    5
            );

            waitForElementAndClick(
                    ADD_TO_LIST_BUTTON,
                    "Cannot find 'Add to list' button",
                    10
            );

            waitForElementAndClick(
                    CREATED_LIST,
                    "Cannot find created list",
                    5
            );
        }
    }

    public void compareArticleTitles (String article_title)
    {
        if(Platform.getInstance().isAndroid()) {
            String first_article_xpath = getArticleTitleBeforeClickByXpath(article_title);
            String first_article_attribute = this.waitForElementAndGetAttribute(
                    first_article_xpath,
                    "text",
                    "Cannot find the article title and get attribute",
                    5
            );

            this.waitForElementAndClick(
                    first_article_xpath,
                    "Cannot click on the article title",
                    5
            );

            String second_article_xpath = getArticleTitleAfterClickByXpath(article_title);
            this.waitForElementPresent(
                    second_article_xpath,
                    "Cannot find the article title",
                    5
            );

            String second_article_attribute = this.waitForElementAndGetAttribute(
                    second_article_xpath,
                    "text",
                    "Cannot get attribute",
                    5
            );

            Assert.assertEquals(
                    "Name of the article is not the same one",
                    first_article_attribute,
                    second_article_attribute
            );
        }
    }

    public void ifArticleIsStillSaved(String article_title)
    {
        if(Platform.getInstance().isMW()) {
            String article_name = getSavedArticle(article_title);
            this.waitForElementAndClick(article_name, "Cannot find the article", 5);
            String article_title_after_clicking = getArticleTitleAfterClick(article_title);
            this.waitForElementAndClick(article_title_after_clicking, "Cannot find the article after click", 5);
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "The article is not saved", 5);
        }
    }

    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    ADD_TO_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }

    public void assertForElementPresent (String article_title)
    {
        String article_xpath = getArticleTitleAfterClickByXpath(article_title);
        this.assertElementPresent(
                By.xpath(article_xpath)
        );
    }
}

