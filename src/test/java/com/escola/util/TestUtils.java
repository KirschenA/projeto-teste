package com.escola.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    public static void assertThatEventHasBeenDispatch(List<DomainEvent> events, Map<Class<? extends DomainEvent>, Integer> expected) {
        expected.forEach((clazz, value) -> {
            assertThat(events.stream().filter(event -> event.getClass() == clazz).count()).isEqualTo(value.intValue());
        });
    }


    public static <T extends DomainEvent> T getEvent(Collection<DomainEvent> events, Class<T> clazz){
        return (T) events.stream().filter(event -> event.getClass() == clazz).findFirst().get();
    }
}
