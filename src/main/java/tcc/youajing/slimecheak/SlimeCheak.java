package tcc.youajing.slimecheak;

import crypticlib.BukkitPlugin;
import org.bukkit.event.Listener;

public class SlimeCheak extends BukkitPlugin {

    @Override
    public void enable() {
        //TODO
        Listener SlimeListener = new SlimeListener(this);
        getServer().getPluginManager().registerEvents(SlimeListener, this);
    }

    @Override
    public void disable() {
        //TODO
    }

}