package day02;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumElements {
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
        // 初始化驱动对象，把这个配置对象信息发送给Appium Server
        // 第一个参数：Appium Server通讯地址 ip:端口；第二个参数：配置信息
        AndroidDriver<WebElement> androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
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
    }
}
