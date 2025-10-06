package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void tryEnhanceQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void updateQualityForAgedBrie(Item agedBrie) {
        assert agedBrie.name.equals("Aged Brie");

        agedBrie.sellIn -= 1;

        tryEnhanceQuality(agedBrie);

        if (agedBrie.sellIn < 0) {
            tryEnhanceQuality(agedBrie);
        }
    }

    private void updateQualityForBackstagePasses(Item backstagePasses) {
        assert backstagePasses.name.equals("Backstage passes to a TAFKAL80ETC concert");

        tryEnhanceQuality(backstagePasses);

        if (backstagePasses.sellIn < 11) {
            tryEnhanceQuality(backstagePasses);
        }
        if (backstagePasses.sellIn < 6) {
            tryEnhanceQuality(backstagePasses);
        }

        backstagePasses.sellIn -= 1;

        if (backstagePasses.sellIn < 0) {
            backstagePasses.quality = 0;
        }
    }

    private void updateQualityForSulfuras(Item sulfuras) {
        assert sulfuras.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void updateQualityDefault(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }

        item.sellIn -= 1;

        if (item.quality > 0 && item.sellIn < 0) {
            item.quality -= 1;
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Aged Brie":
                    updateQualityForAgedBrie(item);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateQualityForBackstagePasses(item);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    updateQualityForSulfuras(item);
                    break;
                default:
                    updateQualityDefault(item);
            }
        }
    }
}