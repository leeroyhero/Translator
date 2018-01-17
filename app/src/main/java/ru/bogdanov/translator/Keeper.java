package ru.bogdanov.translator;

import java.util.ArrayList;

import ru.bogdanov.translator.Item.Pair;

/**
 * Created by Владимир on 17.01.2018.
 */

public class Keeper {
    static ArrayList<Pair> list;

    public static ArrayList<Pair> getList() {
        return list;
    }

    public static void setList(ArrayList<Pair> list) {
        Keeper.list = list;
    }
}
