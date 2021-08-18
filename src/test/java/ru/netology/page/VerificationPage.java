package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement button = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage verify (String codeVer) {
        codeField.setValue(codeVer);
        button.click();
        return new DashboardPage();
    }
}
