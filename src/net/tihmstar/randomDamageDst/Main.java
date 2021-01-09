package net.tihmstar.randomDamageDst;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
