/*
 * Copyright (C) 2011 Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk.datastructures;

public interface Range<T> extends Iterable<T> {
    boolean contains(Object other);
    T getStart();
    T getEnd();
    T getStep();
}
