package ui.pages.mainpanel;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.elements.FavoriteProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class FavoriteProjectsPage extends BaseMainPanel {
    private static final String FAVORITE_PROJECTS_PAGE_URL = "/favorite/projects?mode=builds";
    private static final By favoriteProjectElementRoot = FavoriteProjectElement.getROOT();

    public static FavoriteProjectsPage open() {
        return Selenide.open(FAVORITE_PROJECTS_PAGE_URL, FavoriteProjectsPage.class);
    }

    public List<FavoriteProjectElement> getFavoriteProjects() {
        getInteractableElement(favoriteProjectElementRoot);
        return getPageElements($$(favoriteProjectElementRoot), FavoriteProjectElement::new);
    }
}
