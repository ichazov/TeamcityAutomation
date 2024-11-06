package ui.pages.mainpanel;

import com.codeborne.selenide.Selenide;
import ui.elements.FavoriteProjectElement;

import java.util.List;

public class FavoriteProjectsPage extends BaseMainPanel {
    private static final String FAVORITE_PROJECTS_PAGE_URL = "/favorite/projects?mode=builds";

    public static FavoriteProjectsPage open() {
        return Selenide.open(FAVORITE_PROJECTS_PAGE_URL, FavoriteProjectsPage.class);
    }

    public List<FavoriteProjectElement> getFavoriteProjects() {
        return getPageElements(getInteractableElement(ROOT).$$(FavoriteProjectElement.getFAVORITE_PROJECT_ITEM_ROOT()),
                FavoriteProjectElement::new
        );
    }
}
