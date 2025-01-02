package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreVisibilityTest {
    /**
     * We don't want to leak numbers out to consuming applications.
     * @throws NoSuchMethodException if getScore does not exist
     */
    @Test
    void testExternalPackagesCannotSeeScore() throws NoSuchMethodException {
        Class<?> clazz = StatusClassification.class;
        Method method = clazz.getDeclaredMethod("getScore");
        int modifiers = method.getModifiers();

        // package private should be false on all
        assertThat(Modifier.isPublic(modifiers)).isFalse();
        assertThat(Modifier.isPrivate(modifiers)).isFalse();
        assertThat(Modifier.isProtected(modifiers)).isFalse();
    }
}
