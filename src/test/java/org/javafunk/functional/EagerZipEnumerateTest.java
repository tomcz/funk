package org.javafunk.functional;

import org.junit.Test;
import org.javafunk.datastructures.TwoTuple;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.Literals.listWith;
import static org.javafunk.Literals.tuple;

public class EagerZipEnumerateTest {
    @Test
    public void shouldZipIterables() {
        // Given
        Iterable<Integer> iterable1 = listWith(1, 2, 3);
        Iterable<String> iterable2 = listWith("First", "Second", "Third");
        Collection<TwoTuple<Integer, String>> expectedOutput = listWith(
                tuple(1, "First"),
                tuple(2, "Second"),
                tuple(3, "Third"));

        // When
        Collection<TwoTuple<Integer, String>> actualOutput = Eager.zip(iterable1, iterable2);

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void shouldEnumerateIterable() {
        // Given
        Iterable<String> iterable = listWith("a", "b", "c");
        Collection<TwoTuple<Integer, String>> expectedOutput = listWith(
                tuple(0, "a"),
                tuple(1, "b"),
                tuple(2, "c"));

        // When
        Collection<TwoTuple<Integer, String>> actualOutput = Eager.enumerate(iterable);

        // Then
        assertThat(actualOutput, is(expectedOutput));
    }
}
