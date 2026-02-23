package ui.pages.mainpanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.elements.FavoriteProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class FavoriteProjectsPage extends BaseMainPanel {
    private static final String FAVORITE_PROJECTS_PAGE_URL = "/favorite/projects?mode=builds";
    private static final By FAVORITE_PROJECT_ITEM = By.cssSelector("[data-test='subproject']");

    public FavoriteProjectsPage() {
        super();
    }

    public static FavoriteProjectsPage open() {
        var page = Selenide.open(FAVORITE_PROJECTS_PAGE_URL, FavoriteProjectsPage.class);
        $(MAIN_CONTENT_ROOT).shouldBe(Condition.visible);
        return page;
    }

    public List<FavoriteProjectElement> getFavoriteProjects() {
        $(MAIN_CONTENT_ROOT).$(FAVORITE_PROJECT_ITEM).shouldBe(Condition.visible);
        return getPageElements($(MAIN_CONTENT_ROOT).$$(FAVORITE_PROJECT_ITEM), FavoriteProjectElement::new);
    }
}
