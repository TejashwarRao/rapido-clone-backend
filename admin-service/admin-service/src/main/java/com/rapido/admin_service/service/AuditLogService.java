package com.rapido.admin_service.service;

import com.rapido.admin_service.entity.AuditLog;
import com.rapido.admin_service.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLog saveLog(String adminEmail, String action, String description) {

        AuditLog log = AuditLog.builder()
                .adminEmail(adminEmail)
                .action(action)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        return auditLogRepository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}