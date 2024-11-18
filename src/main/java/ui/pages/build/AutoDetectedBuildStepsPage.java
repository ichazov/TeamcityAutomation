package ui.pages.build;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class AutoDetectedBuildStepsPage extends BaseBuildPage {
    private static final By SUCCESS_MESSAGE = By.className("successMessage");

    public static AutoDetectedBuildStepsPage open() {
       return Selenide.page(AutoDetectedBuildStepsPage.class);
    }

    public boolean isSuccessMessageDisplayed() {
       return getInteractableElement(ROOT, SUCCESS_MESSAGE).isDisplayed();
    }
}
