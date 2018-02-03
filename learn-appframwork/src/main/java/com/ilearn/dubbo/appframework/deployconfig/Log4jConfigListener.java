/**
 *
 */
package com.ilearn.dubbo.appframework.deployconfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author George 2016-8-24 上午9:05:28 <br>
 *         生成Token
 */
public class Log4jConfigListener implements ServletContextListener {
    
    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
        Log4jWebConfigurer.initLogging(event.getServletContext());
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
        // TODO Auto-generated method stub
        Log4jWebConfigurer.shutdownLogging(event.getServletContext());
    }

}
