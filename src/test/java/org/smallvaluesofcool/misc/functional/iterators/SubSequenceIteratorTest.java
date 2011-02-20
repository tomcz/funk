package org.smallvaluesofcool.misc.functional.iterators;

import org.junit.Test;
import org.smallvaluesofcool.misc.functional.functors.PredicateFunction;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;
import static org.smallvaluesofcool.misc.Literals.listWith;

public class SubSequenceIteratorTest {
    @Test
    public void shouldTakeElementsFromTheIteratorBetweenTheSpecifiedStartAndStopWithTheSpecifiedStep() throws Exception {
        // Given
        Iterator<String> input = listWith("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k").iterator();

        // When
        Iterator<String> subSequenceIterator = new SubSequenceIterator<String>(input, 2, 9, 3);

        // Then
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is("c"));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is("f"));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is("i"));
        assertThat(subSequenceIterator.hasNext(), is(false));        
    }

    @Test
    public void shouldDefaultToAStepOfOneIfNoneSupplied() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();

        // When
        Iterator<Integer> subSequenceIterator = new SubSequenceIterator<Integer>(input, 1, 4);

        // Then
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(1));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(2));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(3));
        assertThat(subSequenceIterator.hasNext(), is(false));
    }

    @Test
    public void shouldAllowNextToBeCalledWithoutCallingHasNext() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();

        // When
        Iterator<Integer> subSequenceIterator = new SubSequenceIterator<Integer>(input, 1, 4);

        // Then
        assertThat(subSequenceIterator.next(), is(1));
        assertThat(subSequenceIterator.next(), is(2));
        assertThat(subSequenceIterator.next(), is(3));
    }

    @Test
    public void shouldAllowHasNextToBeCalledMultipleTimesWithoutProgressingTheIterator() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();

        // When
        Iterator<Integer> subSequenceIterator = new SubSequenceIterator<Integer>(input, 1, 4);

        // Then
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(1));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(2));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.hasNext(), is(true));
        assertThat(subSequenceIterator.next(), is(3));
        assertThat(subSequenceIterator.hasNext(), is(false));
        assertThat(subSequenceIterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowANoSuchElementExceptionWhenStopHasBeenReached() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();

        // When
        Iterator<Integer> subSequenceIterator = new SubSequenceIterator<Integer>(input, 1, 2);

        subSequenceIterator.next();
        subSequenceIterator.next();

        // Then a NoSuchElementException is thrown
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowANoSuchElementExceptionIfTheUnderlyingIteratorIsExhausted() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();

        // When
        Iterator<Integer> subSequenceIterator = new SubSequenceIterator<Integer>(input, 3, 10);

        subSequenceIterator.next();
        subSequenceIterator.next();
        subSequenceIterator.next();

        // Then a NoSuchElementException is thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionIfANegativeStartIsSupplied() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();
        Integer start = -5, stop = 2, step = 2;

        // When
        new SubSequenceIterator<Integer>(input, start, stop, step);

        // Then an IllegalArgumentException should be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionIfANonPositiveStopIsSupplied() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();
        Integer start = 0, stop = 0, step = 2;

        // When
        new SubSequenceIterator<Integer>(input, start, stop, step);

        // Then an IllegalArgumentException should be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionIfANonPositiveStepIsSupplied() throws Exception {
        // Given
        Iterator<Integer> input = listWith(0, 1, 2, 3, 4).iterator();
        Integer start = 1, stop = 2, step = 0;

        // When
        new SubSequenceIterator<Integer>(input, start, stop, step);

        // Then an IllegalArgumentException should be thrown
    }

    @Test
    public void shouldAllowNullValuesInTheIterator() throws Exception {
        // Given
        Iterable<Integer> input = listWith(1, 2, null, 3, 4);

        // When
        Iterator<Integer> iterator = new SubSequenceIterator<Integer>(input.iterator(), 1, 4);

        // Then
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(nullValue()));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void shouldRemoveTheElementFromTheUnderlyingIterator() throws Exception {
        // Given
        Iterable<String> input = listWith("a", "aa", "aaa", "aaaa");
        Iterable<String> expectedOutput = listWith("a", "aaa", "aaaa");

        // When
        Iterator<String> iterator = new SubSequenceIterator<String>(input.iterator(), 1, 3);

        iterator.next();
        iterator.remove();

        // Then
        assertThat(input, is(expectedOutput));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnIllegalStateExceptionIfRemoveIsCalledBeforeNext() throws Exception {
        // Given
        Iterable<String> input = listWith("a", "aa", "aaa", "aaaa");

        // When
        Iterator<String> iterator = new SubSequenceIterator<String>(input.iterator(), 1, 3);

        iterator.hasNext();
        iterator.remove();

        // Then an IllegalStateException should be thrown
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnIllegalStateExceptionIfRemoveIsCalledMoreThanOnceInARow() throws Exception {
        // Given
        Iterable<String> input = listWith("a", "aa", "aaa", "aaaa");

        // When
        Iterator<String> iterator = new SubSequenceIterator<String>(input.iterator(), 1, 3);

        iterator.next();
        iterator.remove();
        iterator.remove();

        // Then an IllegalStateException should be thrown
    }

    @Test
    public void shouldNotRemoveAnElementAfterStopHasBeenReached() throws Exception {
        // Given
        Collection<String> input = listWith("a", "aa", "aaa", "aaaa");
        Collection<String> expectedResult = listWith("a", "aaa", "aaaa");

        // When
        Iterator<String> iterator = new SubSequenceIterator<String>(input.iterator(), 1, 2);

        // Then
        assertThat(iterator.next(), is("aa"));
        iterator.remove();
        assertThat(iterator.hasNext(), is(false));

        try {
            iterator.remove();
            fail("Expected an IllegalStateException");
        } catch (IllegalStateException exception) {
            // continue
        }

        assertThat(input, is(expectedResult));
    }

    @Test
    public void shouldNotRemoveAnElementAfterStopHasBeenReachedEvenIfNextHasBeenCalled() throws Exception {
        // Given
        Collection<String> input = listWith("a", "aa", "aaa", "aaaa");
        Collection<String> expectedResult = listWith("a", "aaa", "aaaa");

        // When
        Iterator<String> iterator = new SubSequenceIterator<String>(input.iterator(), 1, 2);

        // Then
        assertThat(iterator.next(), is("aa"));
        iterator.remove();

        try {
            iterator.next();
            fail("Expected a NoSuchElementException");
        } catch (NoSuchElementException exception) {
            // continue
        }

        try {
            iterator.remove();
            fail("Expected an IllegalStateException");
        } catch (IllegalStateException exception) {
            // continue
        }

        assertThat(input, is(expectedResult));
    }
}
