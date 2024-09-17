package tcc.youajing.slimecheak;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class SlimeListener implements org.bukkit.event.Listener {
    private final SlimeCheak plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public SlimeListener(SlimeCheak plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 检查是否是右键点击
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            // 检查玩家手中是否拿着粘液球
            if (player.getInventory().getItemInMainHand().getType() == Material.SLIME_BALL) {
                Vector direction = player.getLocation().getDirection();
                Vector particleLocation = player.getLocation().add(direction.multiply(1.5)).toVector();

                if (player.getLocation().getChunk().isSlimeChunk()) {
                    Component mainTitle = miniMessage.deserialize("<bold><gradient:#DFFFCD:#deecdd>喜！找到史莱姆区块");
                    Component subTitle = miniMessage.deserialize("<bold><gradient:#DFFFCD:#deecdd>哟西的捏");
                    Title title = Title.title(mainTitle, subTitle);
                    player.showTitle(title);

                    // 添加成功的粒子效果和音效
                    player.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, particleLocation.toLocation(player.getWorld()), 200, 0.5, 1.5, 0.5, 0.1);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
                } else {
                    Component mainTitle = miniMessage.deserialize("<bold><gradient:#FF1361:#BC1717>你被史莱姆吃掉了");
                    Component subTitle = miniMessage.deserialize("<bold><gradient:#FF1361:#BC1717>||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                    Title title = Title.title(mainTitle, subTitle);
                    player.showTitle(title);

                    // 添加失败的粒子效果和音效
                    player.getWorld().spawnParticle(Particle.LARGE_SMOKE, particleLocation.toLocation(player.getWorld()), 200, 0.5, 0.3, 0.5, 0.1);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                item.setAmount(item.getAmount() - 1);
                if (item.getAmount() <= 0) {
                    player.getInventory().setItemInMainHand(null);
                } else {
                    player.getInventory().setItemInMainHand(item);
                }
            }
        }
    }
}