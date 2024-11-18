package ui.pages.mainpanel;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.elements.FavoriteProjectElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class FavoriteProjectsPage extends BaseMainPanel {
    private static final String FAVORITE_PROJECTS_PAGE_URL = "/favorite/projects?mode=builds";
    private static final By favoriteProjectItem = FavoriteProjectElement.getFAVORITE_PROJECT_ITEM_ROOT();

    public FavoriteProjectsPage() {
        super();
    }

    public static FavoriteProjectsPage open() {
        var page = Selenide.open(FAVORITE_PROJECTS_PAGE_URL, FavoriteProjectsPage.class);

        Wait()
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ROOT));

        return page;
    }

    public List<FavoriteProjectElement> getFavoriteProjects() {
        getInteractableElement(ROOT, favoriteProjectItem);
        return getPageElements($(ROOT).$$(favoriteProjectItem), FavoriteProjectElement::new);
    }
}
