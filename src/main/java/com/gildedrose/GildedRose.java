package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (var item : items) {
            if (isAgedBrie(item)) {
                updateBrie(item);
            } else if (isBackstagePass(item)) {
                updateBackstagePass(item);
            } else if (isSulfuras(item)) {
                updateSulfuras(item);
            } else {
                updateGenericItem(item);
            }
        }
    }

    private void updateGenericItem(Item item) {
        tryDecrementQuality(item);
        item.sellIn--;

        if (item.sellIn < 0) {
            tryDecrementQuality(item);
        }
    }

    private void updateSulfuras(Item item) {
        tryIncrementQuality(item);
    }

    private void updateBrie(Item item) {
        tryIncrementQuality(item);
        item.sellIn--;
        if (item.sellIn < 0) {
            tryIncrementQuality(item);
        }
    }

    private void updateBackstagePass(Item item) {
        tryIncrementQuality(item);

        if (item.sellIn < 11) {
            tryIncrementQuality(item);
        }

        if (item.sellIn < 6) {
            tryIncrementQuality(item);
        }

        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void tryIncrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void tryDecrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}