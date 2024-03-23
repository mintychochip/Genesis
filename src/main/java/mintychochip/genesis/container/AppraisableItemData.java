package mintychochip.genesis.container;

import mintychochip.genesis.container.items.interfaces.Appraisable;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import mintychochip.genesis.util.Rarity;

public class AppraisableItemData extends ItemData implements Appraisable {
    private Rarity rarity;
    @Override
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
