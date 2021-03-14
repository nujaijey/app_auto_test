package day3;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SpecialElement {
    private static AndroidDriver<WebElement> androidDriver;
    public static void main(String[] args) throws Exception {
        // 初始化配置对象DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // 1、找到要测试的设备deviceName
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
        // 2、确定测试平台platformName android/ios
        desiredCapabilities.setCapability("platformName", "Android");
        // 3、指定要测试的应用appPackage
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        // 4、启动应用程序配置信息appActivity
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");
        // 设置automationName为UIAutomator2，支持toast获取
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        // 初始化驱动对象，把这个配置对象信息发送给Appium Server
        // 第一个参数：Appium Server通讯地址 ip:端口；第二个参数：配置信息
        androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(5000);
        getToast();

    }

    public static void getToast() throws Exception {
        // 登录
        // 我的柠檬id  com.lemon.lemonban:id/navigation_my
        androidDriver.findElementById("com.lemon.lemonban:id/navigation_my").click();
        Thread.sleep(1000);
        // 点击头像登录id  com.lemon.lemonban:id/fragment_my_lemon_avatar_layout
        androidDriver.findElementById("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout").click();
        Thread.sleep(1000);
        // 手机号码输入框id  com.lemon.lemonban:id/et_mobile
        androidDriver.findElementById("com.lemon.lemonban:id/et_mobile").sendKeys("13323234545");
        // 密码输入框id  com.lemon.lemonban:id/et_password
        androidDriver.findElementById("com.lemon.lemonban:id/et_password").sendKeys("123456");
        // 登录按钮id  com.lemon.lemonban:id/btn_login
        androidDriver.findElementByXPath("//android.widget.Button[@index='0']").click();
        // 在点击登录之后获取toast信息，可用于做断言
//        String actualToast = androidDriver.findElementByXPath("//*[contains(@text,'错误的账号')]").getText();
        By toastBy = By.xpath("//*[contains(@text,'错误的账号')]");
        String actualToast = getElement(toastBy).getText();
        System.out.println(actualToast);
    }

    /**
     * 智能等待
     * @param by
     * @return 元素
     */
    public static WebElement getElement(By by) {
        // 1、创建WebDriverWait对象
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);
        return  webDriverWait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver webDriver) {
                return androidDriver.findElement(by);
            }
        });

    }
}
