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
        public String grade(int score) {
        String result;
        if (score >= 90) {
            result = "A";
        } else if (score >= 80) {
            result = "B";
        } else if (score >= 70) {
            result = "C";
        } else if (score >= 60) {
            result = "D";
        } else if (score >= 50) {
            result = "E";
        } else if (score >= 40) {
            result = "F";
        } else {
            result = "G";
        }
        String label = null;
        if (score > 100) {
            label = "invalid";
        }
        return result + label.length();
    }
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println("2 + 3 = " + c.add(2, 3));
    }
}
