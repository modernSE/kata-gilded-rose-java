package com.gildedrose;

class GildedRose {
    private static final int QUALITY_LIMIT = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (isNotSpecialItem(i) && hasPositiveQuality(i)) {
                decrementQuality(i);
            } else {
                if (hasQualityBelowLimit(i)) {
                    incrementQuality(i);

                    if (isBackstagePass(i)) {
                        updateBackstagePassQuality(i);
                    }
                }
            }

            if (isNotSulfuras(i)) {
                decrementSellIn(i);
            }

            if (items[i].sellIn < 0) {
                if (isNotAgedBrie(i)) {
                    if (isBackstagePass(i)) {
                        items[i].quality = 0;
                    } else {
                        if (hasPositiveQuality(i) && isNotSulfuras(i)) {
                            decrementQuality(i);
                        }
                    }
                } else {
                    if (hasQualityBelowLimit(i)) {
                        incrementQuality(i);
                    }
                }
            }
        }
    }

    private void updateBackstagePassQuality(int i) {
        if (items[i].sellIn < 11 && hasQualityBelowLimit(i)) {
            incrementQuality(i);
        }

        if (items[i].sellIn < 6 && hasQualityBelowLimit(i)) {
            incrementQuality(i);
        }
    }

    private void decrementSellIn(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void decrementQuality(int i) {
        items[i].quality = items[i].quality - 1;
    }

    private void incrementQuality(int i) {
        items[i].quality = items[i].quality + 1;
    }

    private boolean hasQualityBelowLimit(int i) {
        return items[i].quality < QUALITY_LIMIT;
    }

    private boolean hasPositiveQuality(int i) {
        return items[i].quality > 0;
    }

    private boolean isNotSulfuras(int i) {
        return !items[i].name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePass(int i) {
        return items[i].name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isNotAgedBrie(int i) {
        return !items[i].name.equals("Aged Brie");
    }

    private boolean isNotSpecialItem(int i) {
        return isNotAgedBrie(i) && !isBackstagePass(i) && isNotSulfuras(i);
    }
}