package org.bestest.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Goal which verifies compatibility between Selenium WebDriver version and browser version.
 *
 * @goal check-chrome
 * 
 * @phase process-sources
 */
public class MyMojo extends AbstractMojo
{
    /**
     * @parameter property="project"
     * @required
     */
    private MavenProject mavenProject;

    private final String chromeDriverVersionKey = "chrome.driver.version";
    private final String chromeBrowserVersionKey = "chrome.browser.version";
    private HashMap<String, List<String>> chromeDriverVersionToBrowserVersion = new HashMap<String, List<String>>();

    public void execute() throws MojoExecutionException
    {
        if (mavenProject.getProperties().isEmpty() || mavenProject.getProperties().getProperty(chromeDriverVersionKey) == null) {
            getLog().error(chromeDriverVersionKey + " must be defined in pom properties..!");
        }
        else if (mavenProject.getProperties().getProperty(chromeBrowserVersionKey) == null) {
            getLog().error(chromeBrowserVersionKey + " must be defined in pom properties..!");
        }

        String cdVersion = mavenProject.getProperties().getProperty(chromeDriverVersionKey);
        String chromeVersion = mavenProject.getProperties().getProperty(chromeBrowserVersionKey);

        chromeDriverVersionToBrowserVersion.put("2.25", Arrays.asList( "53", "54", "55"));
        chromeDriverVersionToBrowserVersion.put("2.24", Arrays.asList( "52", "53", "54"));
        chromeDriverVersionToBrowserVersion.put("2.23", Arrays.asList( "51", "52", "53"));
        chromeDriverVersionToBrowserVersion.put("2.22", Arrays.asList( "49", "50", "51", "52"));
        chromeDriverVersionToBrowserVersion.put("2.21", Arrays.asList( "46", "47", "48", "49", "50"));
        chromeDriverVersionToBrowserVersion.put("2.20", Arrays.asList( "43", "44", "45", "46", "47", "48"));
        chromeDriverVersionToBrowserVersion.put("2.19", Arrays.asList( "43", "44", "45", "46", "47"));
        chromeDriverVersionToBrowserVersion.put("2.18", Arrays.asList( "43", "44", "45", "46"));

        if (!chromeDriverVersionToBrowserVersion.get(cdVersion).contains(chromeVersion)) {
            getLog().error("Your Chrome Driver version doesn't work with your Chrome version!");
            getLog().error("Use this map: " + Arrays.asList(chromeDriverVersionToBrowserVersion));
        }
    }
}
