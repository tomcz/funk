package org.javafunk.datastructures;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.Literals.tuple;

public class ThreeTupleTest {
    @Test
    public void shouldReturnTheFirstObject() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple = tuple(5, "Five", true);

        // When
        Integer first = tuple.first();

        //Then
        assertThat(first, is(5));
    }

    @Test
    public void shouldReturnTheSecondObject() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple = tuple(5, "Five", true);

        // When
        String second = tuple.second();

        //Then
        assertThat(second, is("Five"));
    }

    @Test
    public void shouldReturnTheThirdObject() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple = tuple(5, "Five", true);

        // When
        Boolean third = tuple.third();

        //Then
        assertThat(third, is(true));
    }

    @Test
    public void shouldUseTheStringValueOfTheFirstSecondAndThirdInToString() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple = tuple(5, "Five", true);

        // When
        String stringRepresentation = tuple.toString();

        // Then
        assertThat(stringRepresentation, is("(5, Five, true)"));
    }

    @Test
    public void shouldBeEqualIfHasSameFirstSecondAndThird() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple1 = tuple(5, "Five", true);
        ThreeTuple<Integer, String, Boolean> tuple2 = tuple(5, "Five", true);

        // When
        Boolean isEqual = tuple1.equals(tuple2);

        // Then
        assertThat(isEqual, is(true));
    }

    @Test
    public void shouldNotBeEqualIfHasDifferentFirst() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple1 = tuple(5, "Five", true);
        ThreeTuple<Integer, String, Boolean> tuple2 = tuple(10, "Five", true);

        // When
        Boolean isEqual = tuple1.equals(tuple2);

        // Then
        assertThat(isEqual, is(false));
    }

    @Test
    public void shouldNotBeEqualIfHasDifferentSecond() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple1 = tuple(5, "Five", true);
        ThreeTuple<Integer, String, Boolean> tuple2 = tuple(5, "Ten", true);

        // When
        Boolean isEqual = tuple1.equals(tuple2);

        // Then
        assertThat(isEqual, is(false));
    }

    @Test
    public void shouldNotBeEqualIfHasDifferentThird() {
        // Given
        ThreeTuple<Integer, String, Boolean> tuple1 = tuple(5, "Five", true);
        ThreeTuple<Integer, String, Boolean> tuple2 = tuple(5, "Five", false);

        // When
        Boolean isEqual = tuple1.equals(tuple2);

        // Then
        assertThat(isEqual, is(false));
    }
}
