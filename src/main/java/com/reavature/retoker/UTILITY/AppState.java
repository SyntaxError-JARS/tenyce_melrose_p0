package com.reavature.retoker.UTILITY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
 //class provides AppState methods. --TM
        private static boolean isRunning;
        //method returns a boolean valued >0. --TM

        public AppState() {
            System.out.println("Generating AppState.");
            isRunning = true;
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        }

    }

