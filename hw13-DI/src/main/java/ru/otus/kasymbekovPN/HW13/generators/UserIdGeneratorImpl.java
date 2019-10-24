package ru.otus.kasymbekovPN.HW13.generators;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserIdGeneratorImpl implements UserIdGenerator {

    private static final AtomicLong USER_ID = new AtomicLong(0);

    @Override
    public long getUserId() {
        return USER_ID.incrementAndGet();
    }
}
