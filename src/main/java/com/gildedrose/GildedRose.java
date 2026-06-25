package com.gildedrose;

class GildedRose {
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQuality(items[i]);
        }
    }

    private void updateQuality(Item item) {
        switch (item.name) {
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                updateQualityForTickets(item);
                return;
            case AGED_BRIE:
                updateQualityForFood(item);
                return;
            case SULFURAS_HAND_OF_RAGNAROS:
                return;
            default: 
            updateCommonItem(item);
        }
    }

    private void updateCommonItem(Item item) {
        item.decreaseQuality();
        item.decreaseSellDate();

        if (item.sellIn < 0) {
            item.decreaseQuality();
        }
    }

    private void updateQualityForFood(Item item) {
        item.increaseQuality();
        item.decreaseSellDate();

        if (item.sellIn < 0) {
            item.increaseQuality();
        }
    }

    private void updateQualityForTickets(Item item) {
        item.increaseQuality();
        if (item.sellIn < 11) {
            item.increaseQuality();
        }
        if (item.sellIn < 6) {
            item.increaseQuality();
        }

        item.decreaseSellDate();

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}