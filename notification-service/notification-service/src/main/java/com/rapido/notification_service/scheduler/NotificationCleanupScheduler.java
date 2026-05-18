package com.rapido.notification_service.scheduler;

import com.rapido.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationCleanupScheduler {

    private final NotificationRepository notificationRepository;

    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanupOldNotifications() {

        LocalDateTime cutoff = LocalDateTime.now().minusDays(30);

        notificationRepository.deleteByCreatedAtBefore(cutoff);

        log.info("Old notifications cleaned successfully");
    }
}