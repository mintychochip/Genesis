package mintychochip.genesis.container.items.interfaces;

import mintychochip.genesis.util.Rarity;

public interface Appraisable {

    Rarity rarity = Rarity.DEFAULT;
    void setRarity(Rarity rarity);
    default Rarity getRarity() {
        return rarity;
    }
}
