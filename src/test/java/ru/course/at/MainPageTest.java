package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.openqa.selenium.By.cssSelector;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/ru/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    By authorLink = cssSelector("a[data-test-id='user-info-username']");

    private void openAuthorProfile() {
        driver.findElement(authorLink).click();
    }

    @Test
    @DisplayName("Текст ссылки на автора поста совпадает с ником на странице автора")
    public void authorLinkNameTest() {
        String authorLinkText = driver.findElement(authorLink).getText();
        openAuthorProfile();

        WebElement userNick = driver.findElement(cssSelector("a.tm-user-card__nickname"));
        assertEquals(authorLinkText, userNick.getText().substring(1), "Текст не совпадает.");
    }

    @Test
    @DisplayName("Наличие вкладки Профиль на странице автора")
    public void authorProfileTabTest() {
        openAuthorProfile();

        List<WebElement> profileTab = driver.findElements(By.xpath("//a[contains(., 'Профиль')]"));
        assertFalse(profileTab.isEmpty(), "Вкладка Профиль отсутствует.");
    }
}