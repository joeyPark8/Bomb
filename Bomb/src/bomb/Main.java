package bomb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Bomb is activated");
        getCommand("bomb").setExecutor(this::onCommand);
        getCommand("bomb").setTabCompleter(this::onTabComplete);
    }

    @Override
    public void onDisable() {
        System.out.println("Bomb is deactivated");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("bomb")) {
            if (args.length == 0) {
                sender.sendMessage("Let's explode!!!");
                return false;
            }

            //explosion
            else if (args[0].equalsIgnoreCase("explosion")) {
                //selector target
                if (args[1].equalsIgnoreCase("@all")) {
                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);

                    if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                        for (int i = 0; i < players.length; i++) {
                            player.getWorld().createExplosion(players[i].getLocation(), Integer.parseInt(args[2]));
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
                    return false;
                }
                else if (args[1].equalsIgnoreCase("@local")) {
                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            player.getWorld().createExplosion(player.getLocation(), Integer.parseInt(args[2]));
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    return false;
                }
                else if (args[1].equalsIgnoreCase("@random")) {
                    return false;
                }

                //player target
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Your target " + args[1] + " is not online");
                }
                else {
                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            player.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
                }
            }

            //primed tnt
            else if (args[0].equalsIgnoreCase("tnt")) {
                //selector target
                if (args[1].equalsIgnoreCase("@all")) {
                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);

                    if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                        for (int j = 0; j < Integer.parseInt(args[2]); j++) {
                            for (int i = 0; i < players.length; i++) {
                                player.getWorld().spawnEntity(players[i].getLocation(), EntityType.PRIMED_TNT);
                            }
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
                    return false;
                }
                else if (args[1].equalsIgnoreCase("@local")) {
                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                                player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    return false;
                }
                else if (args[1].equalsIgnoreCase("@random")) {
                    return false;
                }

                //player target
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Your target " + args[1] + " is not online");
                }
                else {
                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                                player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
                }
            }
        }
        else if (command.getName().equalsIgnoreCase("getbomb")) {
            return false;
        }
        return true;
    }
    boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("bomb")) {
            if (args.length == 1) {
                List<String> targets = new ArrayList<>();
                targets.add("explosion");
                targets.add("tnt");

                return targets;
            }
            if (args.length == 2) {
                List<String> targets = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                for (int i = 0; i < players.length; i++) {
                    targets.add(players[i].getName());
                }

                targets.add("@all");
                targets.add("@local");
                targets.add("@random");

                return targets;
            }
        }
        return null;
    }
}
