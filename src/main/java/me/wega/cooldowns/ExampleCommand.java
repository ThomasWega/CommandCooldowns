package me.wega.cooldowns;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ExampleCommand implements CommandExecutor {

    private final HashMap<UUID, Long> cooldown;

    public ExampleCommand(){
        this.cooldown = new HashMap<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){

            if(!this.cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000){
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    player.sendMessage("Example Message DEFAULT");
            }
            else if(!this.cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 5000 && sender.hasPermission("vip")){
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                player.sendMessage("Example Message VIP");
            }
            else{
                if(sender.hasPermission("vip")){
                    float wait = ((5000 - (System.currentTimeMillis() - cooldown.get(player.getUniqueId()))) / 1000.0f);
                    player.sendMessage(ChatColor.RED + "You can't use Example Message again for another " + String.format("%.1f", wait) + " seconds!");
                }
                else {
                    float wait = ((10000 - (System.currentTimeMillis() - cooldown.get(player.getUniqueId()))) / 1000.0f);
                    player.sendMessage(ChatColor.RED + "You can't use Example Message again for another " + String.format("%.1f", wait) + " seconds!\n" + ChatColor.RED + "Buy VIP for shorter cooldowns.");
                }
            }


        }

        return true;
    }
}
