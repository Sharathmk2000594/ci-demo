package com.demo;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("cannot divide by zero");
        }
        return a / b;
    }
       
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println("2 + 3 = " + c.add(2, 3));
    }
        public static String grade(Integer score) {
        String result = null;
        if (score >= 90) {
            result = "A";
        } else if (score >= 75) {
            result = "B";
        } else if (score >= 50) {
            result = "C";
        }
        return result.toUpperCase();
    }
}
