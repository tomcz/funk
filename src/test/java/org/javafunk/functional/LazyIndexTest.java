package org.javafunk.functional;

import org.javafunk.datastructures.TwoTuple;
import org.javafunk.functional.functors.Indexer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javafunk.Literals.listWith;
import static org.javafunk.Literals.twoTuple;

public class LazyIndexTest {
    @Test
    public void shouldReturnTwoTuplesWithTheIndexFirstAndTheElementSecond() throws Exception {
        // Given
        Iterable<String> input = listWith("apple", "pear", "lemon");

        // When
        Iterable<TwoTuple<Integer, String>> outputIterable = Lazy.index(input, new Indexer<String, Integer>(){
            public Integer index(String item) {
                return item.length();
            }
        });
        Iterator<TwoTuple<Integer,String>> outputIterator = outputIterable.iterator();

        // Then
        assertThat(outputIterator.hasNext(), is(true));
        assertThat(outputIterator.next(), is(twoTuple(5, "apple")));
        assertThat(outputIterator.hasNext(), is(true));
        assertThat(outputIterator.next(), is(twoTuple(4, "pear")));
        assertThat(outputIterator.hasNext(), is(true));
        assertThat(outputIterator.next(), is(twoTuple(5, "lemon")));
        assertThat(outputIterator.hasNext(), is(false));
    }

    @Test
    public void shouldAllowIteratorToBeCalledMultipleTimesReturningDifferentIterators() throws Exception {
        // Given
        Iterable<String> input = listWith("apple", "pear", "lemon");

        // When
        Iterable<TwoTuple<Integer, String>> iterable = Lazy.index(input, new Indexer<String, Integer>(){
            public Integer index(String item) {
                return item.length();
            }
        });

        Iterator<TwoTuple<Integer, String>> iterator1 = iterable.iterator();
        Iterator<TwoTuple<Integer, String>> iterator2 = iterable.iterator();

        // Then
        assertThat(iterator1.next(), is(twoTuple(5, "apple")));
        assertThat(iterator1.next(), is(twoTuple(4, "pear")));
        assertThat(iterator2.next(), is(twoTuple(5, "apple")));
        assertThat(iterator1.next(), is(twoTuple(5, "lemon")));
        assertThat(iterator2.next(), is(twoTuple(4, "pear")));
        assertThat(iterator2.next(), is(twoTuple(5, "lemon")));
    }
}
