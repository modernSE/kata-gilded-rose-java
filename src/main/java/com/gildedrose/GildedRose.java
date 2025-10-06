package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    Item[] items;
    final static String BRIE = "Aged Brie";
    final static String SULFURAS = "Sulfuras, Hand of Ragnaros";
    final static String PASS = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean stringContainsElementFromList(String element, List<String> list){

        for (String listEntry : list) {
            if (element.contains(listEntry)) {
                return true;
            }
        }
        return false;

    }

    public void updateQuality() {
        for (Item item : items) {
            if (!stringContainsElementFromList(item.name, List.of(PASS, BRIE))) {
                temp4(item); 
            } else {
                temp3(item);
            }
            
            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            temp5(item);
        }
    }

    private void temp4(Item item) {
        
            if (!(item.quality > 0)) {return;}
            if (!item.name.equals(SULFURAS)) {
                item.quality = item.quality - 1;
            }
        
    }

    private void temp3(Item item) {

        if (!(item.quality < 50)) {
                    return;
        }
        item.quality = item.quality + 1;

        if (!item.name.equals(PASS)) {return;}
        if (!(item.quality < 50)){
            return;
        }
            int newQuality = item.quality;
            if (item.sellIn < 6){
                newQuality = item.quality + 2;
            } else if (item.sellIn < 11){
                newQuality = item.quality + 1;
            }
            item.quality = newQuality;
    }

    public void temp5(Item item){
        if (!(item.sellIn < 0)) {return;}
        if (item.name.equals(BRIE)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        return;    
        }

        if (item.name.equals(PASS)) {
            item.quality = item.quality - item.quality;
            return;
        }

        if (item.quality > 0 && (!item.name.equals(SULFURAS))) {
            item.quality = item.quality - 1;
        }
    }
        
        
}