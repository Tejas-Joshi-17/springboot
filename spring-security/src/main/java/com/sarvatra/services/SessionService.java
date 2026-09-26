package com.sarvatra.services;

import com.sarvatra.entities.Session;
import com.sarvatra.entities.User;
import com.sarvatra.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Value("${server.session.limit}")
    private int sessionLimit;

    public void generateNewSession(User user, String refreshToken) {
        List<Session> userSessions = sessionRepository.findByUser(user);

        if (userSessions.size() == sessionLimit) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.get(0);
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                            .orElseThrow(() ->
                                new SessionAuthenticationException("Session not found for refreshToken : " + refreshToken)
                            );

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }


}
