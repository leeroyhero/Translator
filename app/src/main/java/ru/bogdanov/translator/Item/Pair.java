package ru.bogdanov.translator.Item;

/**
 * Created by Владимир on 16.01.2018.
 */

public class Pair {
    String russian, english;

    public Pair(String russian, String english) {
        this.russian = russian;
        this.english = english;
    }

    public Pair() {
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
