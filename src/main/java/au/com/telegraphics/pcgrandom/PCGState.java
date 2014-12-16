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

/**
 * State of PCG RNG. Immutable and threadsafe.
 */
public final class PCGState {
    final long state, inc;

    PCGState(long state, long inc) {
        this.state = state;
        this.inc = inc;
    }

    /**
     * Produce the next 32 bit integer in the random sequence,
     * and the updated state.
     * @return PCGInt wrapping the integer, and new state
     */
    public PCGInt nextInt() {
        int xorshifted = (int)(((state >>> 18) ^ state) >>> 27);
        int rot = (int)(state >>> 59);
        return new PCGInt(
            Integer.rotateRight(xorshifted, rot),
            new PCGState(state*6364136223846793005L + inc, inc)
        );
    }

    /**
     * Initialise a PCG state.
     * @param initstate seed value for this stream
     * @param initseq sequence index for non-overlapping streams
     * @return
     */
    public static PCGState init(long initstate, long initseq) {
        PCGState s0 = new PCGState(0, (initseq << 1) | 1);
        PCGState s1 = s0.nextInt().newState;
        PCGState s2 = new PCGState(s1.state + initstate, s1.inc);
        return s2.nextInt().newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCGState pcgState = (PCGState) o;
        return inc == pcgState.inc && state == pcgState.state;
    }

    @Override
    public int hashCode() {
        return 31*(int)(state ^ (state >>> 32)) + (int)(inc ^ (inc >>> 32));
    }
}
