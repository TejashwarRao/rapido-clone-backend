package com.rapido.admin_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledAnalyticsJob {

    @Scheduled(cron = "0 0 0 * * *")
    public void runNightlyAnalyticsJob() {
        System.out.println("Nightly analytics aggregation job completed");
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void createDailyAnalyticsSnapshot() {
        System.out.println("Daily analytics snapshot created");
    }
}