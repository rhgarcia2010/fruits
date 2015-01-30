package com.example.dbm.fruits.fruit;

import com.example.dbm.fruits.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitContent {

    /**
     * An array of sample fruits items.
     */
    public static List<FruitItem> ITEMS = new ArrayList<FruitItem>();

    /**
     * A map of sample fruits items, by ID.
     */
    public static Map<String, FruitItem> ITEM_MAP = new HashMap<String, FruitItem>();

    static {
        // Add 3 sample items.
        addItem(new FruitItem("1", "Apple", R.drawable.apple));
        addItem(new FruitItem("2", "Banana", R.drawable.banana));
        addItem(new FruitItem("3", "Orange", R.drawable.orange));
    }

    private static void addItem(FruitItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A fruit item representing a piece of content.
     */
    public static class FruitItem {
        public String id;
        public String name;
        public int image;


        public FruitItem(String id, String name, int image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
