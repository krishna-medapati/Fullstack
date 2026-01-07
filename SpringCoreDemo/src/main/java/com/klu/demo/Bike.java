package com.klu.demo;

public class Bike {

    private Engine engine;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void ride() {
        engine.start();
        System.out.println("Bike is moving...");
    }
}