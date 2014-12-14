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

public class PCGBasic {

    private PCGBasic() { }

    public static PCGState init(long initstate, long initseq) {
        PCGState s0 = new PCGState(0, (initseq << 1) | 1);
        PCGState s1 = s0.nextInt().newState;
        PCGState s2 = new PCGState(s1.state + initstate, s1.inc);
        return s2.nextInt().newState;
    }
}
