package com.gildedrose;

public class ItemStatesUpdater {

    private static final int MAX_ITEM_QUALITY = 50;
    
    private Item item;

    public ItemStatesUpdater(Item item) {
        this.item = item;
    }

    public void reduceSellIn(){
        if(item.sellIn > 0){
            item.sellIn--;
        }
    }

    public void increaseQuality() {
        if(item.quality < MAX_ITEM_QUALITY){
            item.quality++;
        }
    }

    public void reduceQuality() {
        if(item.quality > 0){
            item.quality--;
        }
    }

    public void increaseQualityIfSellValueLessThen(int sellValue) {
        if (item.sellIn < sellValue)
            increaseQuality();
    }

    public void reduceQualityIfSellValueLessThen(int sellValue) {
        if (item.sellIn < sellValue)
            reduceQuality();
    }

    public void setQualityToZeroIfSellInNegative() {
        if(item.sellIn < 0){
            item.quality = 0;
        }
    }


}