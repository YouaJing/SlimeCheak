package tcc.youajing.slimecheak;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;


public class SlimeListener implements org.bukkit.event.Listener {
    private final SlimeCheak plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public SlimeListener(SlimeCheak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerArmorChange(PlayerArmorChangeEvent event) {


        Player player = event.getPlayer();
        ItemStack newItem;
        ItemStack oldItem;
        PlayerArmorChangeEvent.SlotType slotType = event.getSlotType();

        if (slotType == PlayerArmorChangeEvent.SlotType.HEAD) {
            newItem = event.getNewItem();
            if (newItem.getType() == Material.SLIME_BALL) {
                if (player.getLocation().getChunk().isSlimeChunk()) {
                    Component mainTitle = miniMessage.deserialize("<bold><gradient:#DFFFCD:#deecdd>喜！找到史莱姆区块");
                    Component subTitle = miniMessage.deserialize("<bold><gradient:#DFFFCD:#deecdd>哟西的捏");
                    Title title = Title.title(mainTitle, subTitle);
                    player.showTitle(title);
                }else {
                    Component mainTitle = miniMessage.deserialize("<bold><gradient:#FF1361:#BC1717>你被史莱姆吃掉了");
                    Component subTitle = miniMessage.deserialize("<bold><gradient:#FF1361:#BC1717>||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                    Title title = Title.title(mainTitle, subTitle);
                    player.showTitle(title);
                }
                newItem.setAmount(newItem.getAmount() - 1);
                if (newItem.getAmount() <= 0) {
                    player.getInventory().setHelmet(null);
                } else {
                    player.getInventory().setHelmet(null);
                    player.getInventory().addItem(newItem);
                }
            }
        }




    }


}
