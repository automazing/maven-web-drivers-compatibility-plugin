# maven-web-drivers-compatibility-plugin
Use This Maven Plugin to you have the correct chromedriver for the Chrome browser version installed and for other drivers in the future.

For now it's usage is by setting these properties in your pom.xml:
```xml
<properties>
    <chrome.driver.version>2.25</chrome.driver.version>
    <chrome.browser.version>54</chrome.browser.version>
</properties>
```

Part of the TODOs is implementing auto checking installed versions. #1

After cloning, you need to run `mvn install` and then run it within your project:
`mvn org.bestest.plugins:maven-web-drivers-compatibility-plugin:check-chrome`
