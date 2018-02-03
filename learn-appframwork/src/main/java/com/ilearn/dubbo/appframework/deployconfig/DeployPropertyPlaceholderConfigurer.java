package com.ilearn.dubbo.appframework.deployconfig;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * @author George 2016-8-24 上午9:05:28
 *         通过java.exe运行时设置-Dcsc.home=D:/development形式引入环境配目录，将会自动加载目录下面所有的*.properties文件.<br>
 *         便于使用“统一war包+三套properties配置文件”来实现布署切换<br>
 */

public class DeployPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {


    /**
     * 加载从Java.exe的命令行中以-Dcsc.home=D:/development形式注入的设置目录.
     * <p>
     * 在spring中类似如下配置： <property name="cscDeployConfigPathKey" value="csc.home"/>.
     *
     * @param configHome JVM参数-Dcsc.home=D:/development的引导key, 即为csc.home
     */
    public void setDeployConfigPathKey(String configHome) {
        String configFilePath = System.getProperty(configHome);
        if (configFilePath == null) {
            throw new IllegalArgumentException(configHome + "对应的值为null, 可能是java.exe运行时, 没有设置配置文件的目录-D" + configHome + "=D:/xxx");
        }

        try {

            //检查目录
            File file = new File(configFilePath);
            if (!file.exists() || !file.isDirectory()) {
                throw new IllegalArgumentException(configHome + "对应的值为" + configFilePath + ", 它可能不存在，或者不是一个目录.");
            }

            //找到其下所有属性文件
            File[] listPropertiesFiles = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".properties")) {
                        return true;
                    }
                    return false;
                }
            });

            //构造UrlResource资源
            List<UrlResource> listResource = null;
            if (listPropertiesFiles != null && listPropertiesFiles.length > 0) {
                listResource = new ArrayList<UrlResource>();
                for (File propertiesFile : listPropertiesFiles) {
                    URI uri = propertiesFile.toURI();
                    UrlResource urlResource = new UrlResource(uri);
                    listResource.add(urlResource);
                }
            }

            //设值
            if (listResource != null) {
                this.setLocations((Resource[]) listResource.toArray(new UrlResource[listResource.size()]));
            }

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
