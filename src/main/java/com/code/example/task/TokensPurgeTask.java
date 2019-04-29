package com.code.example.task;

import com.code.example.persistence.repositories.VerificationTokenRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

/**
 * Created by veljko on 16.9.18.
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokensPurgeTask {

    private final @NonNull
    VerificationTokenRepository tokenRepository;

    @Scheduled(cron = "0 1 1 * * ?")
    public void purgeExpired() {

        log.info("Purge token start");
        Date now = Date.from(Instant.now());

        tokenRepository.deleteAllExpiredSince(now);
    }
}
