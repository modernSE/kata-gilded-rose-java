package com.gildedrose;

class GildedRose {
    
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for(Item item : this.items){
            ItemStatesUpdater itemStatesUpdater = new ItemStatesUpdater(item);
            switch (item.name) {
                case "Aged Brie":
                    itemStatesUpdater.increaseQuality();
                    itemStatesUpdater.reduceSellIn();
                    itemStatesUpdater.increaseQualityIfSellValueLessThen(0);
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    itemStatesUpdater.increaseQuality();
                    itemStatesUpdater.increaseQualityIfSellValueLessThen(11);
                    itemStatesUpdater.increaseQualityIfSellValueLessThen( 6);
                    itemStatesUpdater.reduceSellIn();
                    itemStatesUpdater.setQualityToZeroIfSellInNegative();
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    break;    
            
                default:
                    itemStatesUpdater.reduceQuality();
                    itemStatesUpdater.reduceSellIn();
                    itemStatesUpdater.reduceQualityIfSellValueLessThen( 0);
                    break;
            }
        }
       
    }

}