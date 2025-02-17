package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
     static {
         ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
         REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), 'Java (programming language)')]/../../a[span[contains(@class, 'mf-icon mf-icon-unStar-progressive ')]]";
     }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
