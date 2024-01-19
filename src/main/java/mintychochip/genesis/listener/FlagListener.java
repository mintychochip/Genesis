package mintychochip.genesis.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FlagListener implements Listener {

    private final double fallDamageCoefficient = 0.5;

    private final double flatRange = 2;
    private final double vertRange = 1;

    @EventHandler
    public void onPlayerFallOnLeaves(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getDamage() == 0) {
            return;
        }
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        double damage = event.getDamage() * fallDamageCoefficient;
        Location location = player.getLocation();
        for (double i = -vertRange; i <= vertRange; i++) {
            for (double j = -flatRange; j <= flatRange; j++) {
                for (double k = -flatRange; k <= flatRange; k++) {
                    if (location.add(i, j, k).getBlock().getType().toString().contains("LEAVES")) {
                        if (player.getHealth() == 1) {
                            event.setDamage(0);
                        } else if (damage > player.getHealth()) {
                            event.setDamage(player.getHealth() - 1);
                        } else {
                            event.setDamage(damage);
                        }
                        return;
                    }
                    location.subtract(i, j, k);
                }
            }
        }
    }
}
