/*
 * Copyright (C) 2011 Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk.functors.procedures;

public interface NonaryProcedure<A, B, C, D, E, F, G, H, I> {
    void execute(A firstArgument,
                 B secondArgument,
                 C thirdArgument,
                 D fourthArgument,
                 E fifthArgument,
                 F sixthArgument,
                 G seventhArgument,
                 H eighthArgument,
                 I ninthArgument);
}
