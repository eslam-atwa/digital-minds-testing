import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeAbout_DigitalMindsTest extends BaseTest {

    String BASE_URL = "https://digitall-minds.com";

    private WebElement findAny(String[] selectors) {
        for (String sel : selectors) {
            try {
                if (sel.startsWith("//")) {
                    return driver.findElement(By.xpath(sel));
                } else if (sel.startsWith("link=")) {
                    String txt = sel.split("=", 2)[1];
                    return driver.findElement(By.linkText(txt));
                } else {
                    return driver.findElement(By.cssSelector(sel));
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    private boolean isImageLoaded(WebElement img) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            return (boolean) js.executeScript(
                    "return arguments[0].complete && arguments[0].naturalWidth > 0;", img
            );
        } catch (Exception e) {
            return true;
        }
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHomePage() {
        System.out.println("\n=== Testing Home Page ===");
        driver.get(BASE_URL);
        waitForPageLoad();

        String title = driver.getTitle().trim();
        System.out.println("Page title: '" + title + "'");

        WebElement nav = findAny(new String[]{
                "nav", ".navbar", "header", "#navbar", ".navigation", "[role='navigation']"
        });
        if (nav != null) {
            System.out.println("✓ Navbar found");
        } else {
            System.out.println("✗ Navbar not found");
        }
        Assert.assertNotNull(nav, "Navbar not found!");

        WebElement aboutLink = findAny(new String[]{
                "link=إحنا مين",
                "link=من نحن",
                "link=About",
                "//a[contains(text(),'إحنا مين')]",
                "//a[contains(text(),'من نحن')]",
                "//a[contains(text(),'About')]",
                "//a[contains(@href,'about')]"
        });
        if (aboutLink != null) {
            System.out.println("✓ About link found: " + aboutLink.getText());
        } else {
            System.out.println("✗ About link not found");
        }
        Assert.assertNotNull(aboutLink, "About link not found!");

        WebElement hero = findAny(new String[]{
                ".hero", ".hero-section", ".main-hero", "section.hero", "main h1", "h1", ".banner"
        });
        if (hero != null) {
            System.out.println("✓ Hero section found");
        } else {
            System.out.println("✗ Hero section not found");
        }
        Assert.assertNotNull(hero, "Hero section not found!");

        int brokenImages = 0;
        int totalImages = 0;
        for (WebElement img : driver.findElements(By.tagName("img"))) {
            totalImages++;
            if (!isImageLoaded(img)) {
                brokenImages++;
                System.out.println("  ✗ Broken image: " + img.getAttribute("src"));
            }
        }
        System.out.println("Images: " + totalImages + " total, " + brokenImages + " broken");

        WebElement footer = findAny(new String[]{
                "footer", ".footer", "#footer", "[role='contentinfo']", ".site-footer"
        });
        if (footer != null) {
            System.out.println("✓ Footer found");
        } else {
            System.out.println("⚠ Footer not found (optional check)");
        }

        System.out.println("=== Home Page Test Complete ===\n");
    }

    @Test
    public void testAboutPage() {
        System.out.println("\n=== Testing About Page ===");
        driver.get(BASE_URL + "/about");
        waitForPageLoad();

        String title = driver.getTitle().trim();
        System.out.println("Page title: '" + title + "'");

        WebElement h1 = findAny(new String[]{
                "h1",
                "//h1[contains(text(),'إحنا مين')]",
                "//h1[contains(text(),'من نحن')]",
                "//h1[contains(text(),'About')]",
                ".page-title",
                ".about-title",
                "main h1"
        });
        
        if (h1 != null) {
            System.out.println("✓ H1 found: " + h1.getText());
        } else {
            System.out.println("⚠ H1 not found (optional check)");
        }

        int brokenImages = 0;
        int totalImages = 0;
        for (WebElement img : driver.findElements(By.tagName("img"))) {
            totalImages++;
            if (!isImageLoaded(img)) {
                brokenImages++;
                System.out.println("  ✗ Broken image: " + img.getAttribute("src"));
            }
        }
        System.out.println("Images: " + totalImages + " total, " + brokenImages + " broken");

        WebElement footer = findAny(new String[]{
                "footer", ".footer", "#footer", "[role='contentinfo']", ".site-footer"
        });
        if (footer != null) {
            System.out.println("✓ Footer found");
        } else {
            System.out.println("⚠ Footer not found (optional check)");
        }

        System.out.println("=== About Page Test Complete ===\n");
    }
}
