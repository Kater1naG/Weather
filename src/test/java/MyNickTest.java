import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyNickTest {

//    TC_1_1 - Тест кейс


//    1. Открыть страницу
//    2. Набрать в строке поиска город Paris
//    3. Нажать пункт меню Search
//    4. Из выпадающего списка выбрать Paris, FR
//    5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                 "C:/Users/karin/OneDrive/Desktop/JAVA/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);

        WebElement searchCityField = driver.findElement(
                By.xpath("//input[@placeholder = 'Search city']")
        );
        Thread.sleep(5000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);

        WebElement choiceParisFr = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        choiceParisFr.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );


        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);



        driver.quit();          //выйти из браузера
        //driver.close();         // закрыть браузер



    }


//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой
// https://openweathermap.org/guide и что title этой страницы
// OpenWeatherMap API guide - OpenWeatherMap
//

    @Test
    public void testRedirectToGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/karin/OneDrive/Desktop/JAVA/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String reUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);

        WebElement searchGuide = driver.findElement(
                By.xpath("//a[@href='/guide']")
        );
        Thread.sleep(5000);
        searchGuide.click();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl,reUrl);

//        WebElement searchTitle = driver.findElement(
//                By.xpath("//title")
//        );

        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);

       driver.quit();

    }

    //    TC_11_02
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testImperialF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/karin/OneDrive/Desktop/JAVA/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String tempButton = "Imperial: °F, mph";
        String expectedResult = "°F";

        driver.get(url);

        WebElement imperialF = driver.findElement(
                By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[3]")
        );
        Thread.sleep(5000);
        imperialF.click();

        WebElement tempF = driver.findElement(
                By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span")
        );

        String letterF = tempF.getText();
         //letterF.substring(0,2);
        Thread.sleep(5000);
       String actualResult = letterF.substring(2);

        Thread.sleep(5000);
       Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }


//    TC_11_03
//      1.  Открыть базовую ссылку
//      2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies
//          which are essential for the site to work. We also use non-essential cookies to help us
//          improve our services. Any data collected is anonymised.
//          You can allow all cookies or manage them individually.”
//      3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”


    @Test
    public void testBelowButtonsAndText() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/karin/OneDrive/Desktop/JAVA/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedButtons = 2;
        String expectedResult = "We use cookies"
                + " which are essential for the site to work. We also use non-essential cookies to help us"
                + " improve our services. Any data collected is anonymised."
                + " You can allow all cookies or manage them individually.";

        driver.get(url);

        WebElement panelOfCookies = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']")
        );

        WebElement textElementOfPanel = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']/div/div/div/div/p")
        );

        String actualResult = textElementOfPanel.getText();

        int buttonsOfPanel  =  driver.findElements(
                By.tagName("button")
        ).size();

        //Assert.assertTrue(url.contains(panelOfCookies));

        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(buttonsOfPanel, expectedButtons);

        driver.quit();

    }
}

