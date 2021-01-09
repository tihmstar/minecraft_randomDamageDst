package net.tihmstar.randomDamageDst;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EventListener implements Listener {
    private final Random rd;

    EventListener(){
        rd = new Random();
    }

    @EventHandler
    public void onEntityDamageDeal(EntityDamageByEntityEvent e){
        Entity target = e.getEntity();
        if (!(target instanceof LivingEntity)) return; //only redirect damage taken by living entities
        //redirect damage

        Location targetLocation = target.getLocation();
        List<Entity> entities = targetLocation.getWorld().getNearbyEntities(targetLocation, 20, 10, 20)
                .stream().filter(p -> p instanceof LivingEntity).collect(Collectors.toList());

        int newTargetIndex = rd.nextInt(entities.size());

        LivingEntity newTarget = (LivingEntity)entities.get(newTargetIndex);
        if (target != newTarget){ //make sure we don't end up where we started, otherwise we might aswell skip the next step
            e.setCancelled(true);
            newTarget.damage(e.getDamage(),e.getDamager());
        }
    }
}
