package com.example.kotlinstarter;

public class Singleton {

    private static volatile Singleton mInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (mInstance == null) {
            synchronized (Singleton.class) {
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }

        return mInstance;
    }
}
