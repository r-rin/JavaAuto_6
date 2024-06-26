import com.github.rin.javaauto.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class SeleniumTest {
    WebDriver webDriver;

    @BeforeAll
    static void init() {
        Main.main(new String[] {});
    }

    @BeforeEach
    void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        webDriver = new ChromeDriver(options);
    }

    @Test
    void testWrongUUIDBookView() {
        webDriver.get("http://localhost:8080/books/view/1234abcd");
        WebElement pageBody = webDriver.findElement(By.tagName("body"));
        Assertions.assertTrue(pageBody.getText().contains("Book not found."));
    }

    @Test
    void createValidBook() {
        final String uniqueAuthor = getRandomString(25);
        final String uniqueTitle = getRandomString(25);

        createBook(uniqueTitle, uniqueAuthor);

        checkStringsOnPage("http://localhost:8080/books", uniqueAuthor, uniqueTitle);
    }

    @Test
    void deleteBookTest() {
        final String uniqueAuthor = getRandomString(25);
        final String uniqueTitle = getRandomString(25);

        createBook(uniqueTitle, uniqueAuthor);

        checkStringsOnPage("http://localhost:8080/books", uniqueAuthor, uniqueTitle);

        List<WebElement> rows = webDriver.findElements(By.xpath("//table/tbody/tr"));
        WebElement createdBookRow = null;
        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(uniqueTitle) && rowText.contains(uniqueAuthor)) {
                createdBookRow = row;
                break;
            }
        }

        Assertions.assertNotNull(createdBookRow);

        WebElement deleteButton = createdBookRow.findElement(By.xpath(".//form/button[text()='Delete']"));
        deleteButton.click();

        checkStringsNotOnPage("http://localhost:8080/books", uniqueAuthor, uniqueTitle);
    }

    @AfterEach
    void stopDriver() {
        webDriver.close();
    }

    //PRIVATE METHODS

    private void createBook(String uniqueTitle, String uniqueAuthor) {
        webDriver.get("http://localhost:8080/books/create");

        WebElement titleField = webDriver.findElement(By.id("title"));
        titleField.sendKeys(uniqueTitle);

        WebElement authorField = webDriver.findElement(By.id("author"));
        authorField.sendKeys(uniqueAuthor);

        WebElement publishYearField = webDriver.findElement(By.id("publishYear"));
        publishYearField.sendKeys("2024");

        WebElement descriptionField = webDriver.findElement(By.id("description"));
        descriptionField.sendKeys("Selenium test book.");

        WebElement submitButton = webDriver.findElement(By.tagName("button"));
        submitButton.click();
    }

    private void checkStringsOnPage(String url, String... values) {
        webDriver.get(url);
        WebElement pageBody = webDriver.findElement(By.tagName("body"));
        String bodyText = pageBody.getText();

        for (String value : values) {
            Assertions.assertTrue(bodyText.contains(value));
        }
    }

    private void checkStringsNotOnPage(String url, String... values) {
        webDriver.get(url);
        WebElement pageBody = webDriver.findElement(By.tagName("body"));
        String bodyText = pageBody.getText();

        for (String value : values) {
            Assertions.assertFalse(bodyText.contains(value));
        }
    }

    public static String getRandomString(int strLength) {

        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] randomBytes = new byte[strLength];
        secureRandom.nextBytes(randomBytes);

        return bytesToHex(randomBytes);
    }

    static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
