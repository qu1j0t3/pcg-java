package main.java.au.com.telegraphics.pcgrandom;

/*
 * PCG Random Number Generation.
 *
 * Copyright 2014 Melissa O'Neill <oneill@pcg-random.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information about the PCG random number generation scheme,
 * including its license and other licensing options, visit
 *
 *       http://www.pcg-random.org
 */

// Java implementation Copyright (C) 2014 Toby Thain, toby@telegraphics.com.au

public final class PCGInt {
    public final int value;
    public final PCGState newState;

    PCGInt(int value, PCGState newState) {
        this.value = value;
        this.newState = newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCGInt pcgInt = (PCGInt) o;
        return value == pcgInt.value && newState.equals(pcgInt.newState);
    }

    @Override
    public int hashCode() {
        return 31 * value + newState.hashCode();
    }
}
