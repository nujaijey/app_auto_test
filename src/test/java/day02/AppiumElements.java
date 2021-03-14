package day02;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class AppiumElements {
    private static AndroidDriver<WebElement> androidDriver;
    public static void main(String[] args) throws Exception {
        // 初始化配置对象DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // 1、找到要测试的设备deviceName
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        // 2、确定测试平台platformName android/ios
        desiredCapabilities.setCapability("platformName","Android");
        // 3、指定要测试的应用appPackage
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        // 4、启动应用程序配置信息appActivity
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
        // 设置automationName为UIAutomator2，支持toast获取
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        // 初始化驱动对象，把这个配置对象信息发送给Appium Server
        // 第一个参数：Appium Server通讯地址 ip:端口；第二个参数：配置信息
        androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        Thread.sleep(5000);

        // text文本值定位方式
        // 不支持findElementByName方法（弃用）
//        androidDriver.findElementByName("全程班").click();
        // 不支持解决方法：使用UIAutomator定位text
//        androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"全程班\")").click();
//        Thread.sleep(3000);
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
        androidDriver.findElementById("com.lemon.lemonban:id/et_password").sendKeys("234545");
        // 登录按钮id  com.lemon.lemonban:id/btn_login
        androidDriver.findElementByXPath("//android.widget.Button[@index='0']").click();
        Thread.sleep(3000);
        // 题库id  com.lemon.lemonban:id/navigation_tiku
        androidDriver.findElementById("com.lemon.lemonban:id/navigation_tiku").click();
        Thread.sleep(1000);

        // AccessibilityId定位（UIAutomatorViewer没有这个属性，Appium inspector才有）
//        androidDriver.findElementByAccessibilityId("题库").click();

        // content-desc定位
//        androidDriver.findElementByAndroidUIAutomator("new UiSelector().description(\"题库\")").click();

        // 坐标定位
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.tap(PointOption.point(200, 1870)).perform();

//        SwipeDown();
    }
    // 通用的向下滑动的方法
    public static void SwipeDown() {
        // 向下滑动手势操作
        // 1、创建TouchAction对象
        TouchAction touchAction = new TouchAction(androidDriver);
        // 2、创建duration对象
        Duration duration = Duration.ofMillis(300);
        // 滑动开始点：x轴的值为屏幕宽度的1/2，y轴的值为屏幕高度的1/4
        int width = androidDriver.manage().window().getSize().getWidth();
        int height = androidDriver.manage().window().getSize().getHeight();
        int startX = width/2;
        int startY = height/4;
        // 滑动结束点
        int endX = width/2;
        int endY = height*3/4;
        touchAction.press(PointOption.point(startX,startY))  // 按压开始点
                .waitAction(WaitOptions.waitOptions(duration))  // 设置滑动时间间隔
                .moveTo(PointOption.point(endX,endY))  // 滑动到结束点
                .release()  // 抬起手指
                .perform();  // 生效
        
    }

}
