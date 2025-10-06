package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (item.name.equals("Elixir of the Mongoose")
                    || item.name.equals("+5 Dexterity Vest")) {
                decreaseQuality(item);
            } else {
                increaseQualityByOne(item);
            }

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                increaseBackstageQuality(item);
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn >= 0) {
                continue;
            }

            if (item.name.equals("Aged Brie")) {
                increaseQualityByOne(item);
            }

            if (item.name.equals("Elixir of the Mongoose")
                    || item.name.equals("+5 Dexterity Vest")) {
                decreaseQuality(item);
            }

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality = 0;
            }

        }

    }

    private void increaseBackstageQuality(Item item) {
        if (item.sellIn < 6) {
            increaseQuality(item, 2);
        } else if (item.sellIn < 11) {
            increaseQualityByOne(item);

        }
    }

    private void increaseQuality(Item item, int amount) {
        if (item.quality < 50) {
            item.quality = Math.min(item.quality + amount, 50);
        }
    }

    private void increaseQualityByOne(Item item) {
        increaseQuality(item, 1);
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

}