package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private boolean isConcert(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private int increaseQuality(Item item, int amount) {
        return item.quality + amount;
    }

    private int decreaseQuality(Item item, int amount) {
        return item.quality - amount;
    }

    private int decreaseExpiry(Item item, int amount) {
        return item.sellIn - amount;
    }

    private boolean isBelowQualityLimit(Item item) {
        return item.quality < 50;
    }

    private boolean hasQuality(Item item) {
        return item.quality > 0;
    }

    private boolean isExpiringIn(Item item, int days) {
        return item.sellIn < days;
    }

    private void updateQualityForItem(Item currentItem) {
        if (isBrie(currentItem) || isConcert(currentItem)) {
            if (isBelowQualityLimit(currentItem)) {
                currentItem.quality = increaseQuality(currentItem, 1);
            }
            if (isConcert(currentItem) && isBelowQualityLimit(currentItem)) {
                if (isExpiringIn(currentItem, 11)) {
                    currentItem.quality = increaseQuality(currentItem, 1);
                }
                if (isExpiringIn(currentItem, 6)) {
                    currentItem.quality = increaseQuality(currentItem, 1);
                }
            }
        } else if (hasQuality(currentItem) && !isSulfuras(currentItem)) {
            currentItem.quality = decreaseQuality(currentItem, 1);

        }
    }

    private int progressExpiry(Item currentItem) {
        if (isSulfuras(currentItem)) {
            return currentItem.sellIn;
        }

        return decreaseExpiry(currentItem, 1);
    }

    private int handleExpiredItem(Item currentItem) {
        if (!isExpired(currentItem)) {
            return currentItem.quality;
        }

        if (isBrie(currentItem) && isBelowQualityLimit(currentItem)) {
            return increaseQuality(currentItem, 1);
        } else {
            if (isConcert(currentItem)) {
                return 0;
            } else if (hasQuality(currentItem) && !isSulfuras(currentItem)) {
                return decreaseQuality(currentItem, 1);
            }
        }
        
        return currentItem.quality;

    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            var currentItem = items[i];
            updateQualityForItem(currentItem);
            currentItem.sellIn = progressExpiry(currentItem);
            currentItem.quality = handleExpiredItem(currentItem);
        }
    }
}