package com.gildedrose;

import java.util.Set;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    Item[] items;
    Set<String> qualityStableItemNames = Set.of();

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQuality(items[i]);
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals(AGED_BRIE)
                || item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
            increaseQuality(item);

            if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                if (item.sellIn < 11) {
                    increaseQuality(item);
                }

                if (item.sellIn < 6) {
                    increaseQuality(item);
                }

            }
        } else if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            decreaseQuality(item);
        }

        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                    if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                        decreaseQuality(item);
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                increaseQuality(item);
            }
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }
}