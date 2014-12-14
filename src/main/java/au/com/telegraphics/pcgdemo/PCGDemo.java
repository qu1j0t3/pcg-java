package main.java.au.com.telegraphics.pcgdemo;

import main.java.au.com.telegraphics.pcgrandom.*;

// Copyright (C) 2014 Toby Thain, toby@telegraphics.com.au

public class PCGDemo {
    static void printHex(int x) {
        System.out.println("  0x" + Integer.toHexString(x));
    }

    public static void main(String[] args) {
        PCGState rng = PCGState.init(42, 54);

        System.out.println("First ten 32 bit numbers:");
        int i = 0;
        for(; i < 10; ++i) {
            PCGInt result = rng.nextInt();
            rng = result.newState;
            printHex(result.value);
        }

        System.out.println("Millionth 32 bit number:");
        for(;;) {
            PCGInt result = rng.nextInt();
            rng = result.newState;
            ++i;
            if(i == 1000000) {
                printHex(result.value);
                break;
            }
        }
    }
}
