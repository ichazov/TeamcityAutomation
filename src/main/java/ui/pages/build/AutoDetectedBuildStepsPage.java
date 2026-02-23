package ui.pages.build;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AutoDetectedBuildStepsPage extends BaseBuildPage {
    private static final By SUCCESS_MESSAGE = By.className("successMessage");

    public static AutoDetectedBuildStepsPage open() {
       return Selenide.page(AutoDetectedBuildStepsPage.class);
    }

    public boolean isSuccessMessageDisplayed() {
       return $(MAIN_CONTENT_ROOT).$(SUCCESS_MESSAGE).shouldBe(Condition.interactable).isDisplayed();
    }
}
