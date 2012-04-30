package org.javafunk.funk.functors.adapters;

import org.javafunk.funk.functors.Indexer;
import org.javafunk.funk.functors.functions.UnaryFunction;

public class IndexerUnaryFunctionAdapter<S, T> implements UnaryFunction<S, T> {
    public static <S, T> IndexerUnaryFunctionAdapter<S, T> indexerUnaryFunction(Indexer<? super S, T> indexer) {
        return new IndexerUnaryFunctionAdapter<S, T>(indexer);
    }

    private final Indexer<? super S, T> indexer;

    public IndexerUnaryFunctionAdapter(Indexer<? super S, T> indexer) {this.indexer = indexer;}

    @Override public T call(S index) {
        return indexer.index(index);
    }
}
