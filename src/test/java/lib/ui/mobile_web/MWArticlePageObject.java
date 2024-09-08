package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
     static {
         TITLE = "css:#content h1";
         TITLE_AFTER_CLICK_MW = "xpath://span[@class='mw-page-title-main'][contains(text(), '{TITLE_AFTER_CLICK}')]";
         FOOTER_ELEMENT = "css:footer";
         ADD_TO_MY_LIST_BUTTON = "xpath://span[@class='minerva-icon minerva-icon--star-base20']";
         OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://span[@class='minerva-icon minerva-icon--unStar-progressive']";
         ARTICLE_TITLE_MW = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{ARTICLE_TITLE}')]";
     }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}


