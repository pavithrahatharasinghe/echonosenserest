package com.example.echonosenserest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ContextListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        PriceFetcher priceFetcher = new PriceFetcher();
        NewsFetcher newsFetcher = new NewsFetcher();

        // Schedule PriceFetcher to run every 15 minutes
        scheduler.scheduleAtFixedRate(() -> {
            try {
                priceFetcher.fetchPricesAndInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);

        // Schedule NewsFetcher to run every 15 minutes (or adjust as needed)
        scheduler.scheduleAtFixedRate(() -> {
            try {
                newsFetcher.fetchNewsAndInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
