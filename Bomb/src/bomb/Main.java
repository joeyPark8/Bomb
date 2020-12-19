package bomb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin implements Listener {
    int time = 0;
    boolean isAll = false;
    String text = "";
    Player tTarget = null;

    @Override
    public void onEnable() {
        System.out.println("Bomb is activated");

        getCommand("bomb").setExecutor(this::onCommand);
        getCommand("bomb").setTabCompleter(this::onTabComplete);

        getCommand("timebomb").setExecutor(this::onCommand);
        getCommand("timebomb").setTabCompleter(this::onTabComplete);

        getCommand("getbomb").setExecutor(this::onCommand);
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

                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            for (int i = 0; i < players.length; i++) {
                                players[i].getWorld().createExplosion(players[i].getLocation(), Integer.parseInt(args[2]));
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
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
                    Random random = new Random();

                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);

                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            player.getWorld().createExplosion(players[random.nextInt(players.length)].getLocation(), Integer.parseInt(args[2]));
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
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
                            target.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
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
                                players[i].getWorld().spawnEntity(players[i].getLocation(), EntityType.PRIMED_TNT);
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
                    Random random = new Random();

                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);

                    if (isInt(args[2])) {
                        if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                                player.getWorld().spawnEntity(players[random.nextInt(players.length)].getLocation(), EntityType.PRIMED_TNT);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                    }
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
                                target.getWorld().spawnEntity(target.getLocation(), EntityType.PRIMED_TNT);
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

        //time bomb
        else if (command.getName().equalsIgnoreCase("timebomb")) {
            if (isInt(args[1])) {
                if (Integer.parseInt(args[1]) > 0) {
                    time = Integer.parseInt(args[1]);
                    text = args[3];
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if (time != -1) {
                                if (time != 0) {
                                    if (time == Integer.parseInt(args[1])) {
                                        if (args[0].equalsIgnoreCase("@all")) {
                                            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                            Bukkit.getServer().getOnlinePlayers().toArray(players);

                                            for (int i = 0; i < players.length; i++) {
                                                players[i].sendMessage("code: " + args[3] + ", time: " + time);
                                            }
                                        }
                                        else if (args[0].equalsIgnoreCase("@local")) {
                                            player.sendMessage("code: " + args[3] + ", time: " + time);
                                        }
                                        else if (args[0].equalsIgnoreCase("@random")) {
                                            Random random = new Random();

                                            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                            Bukkit.getServer().getOnlinePlayers().toArray(players);

                                            players[random.nextInt(players.length)].sendMessage("code: " + args[3] + ", time: " + time + "seconds");
                                        }
                                        else {
                                            Player target = Bukkit.getPlayerExact(args[0]);
                                            if (target == null) {
                                                player.sendMessage(ChatColor.RED + "Your target " + args[0] + " is not online");
                                                Bukkit.getScheduler().cancelTask(-1);
                                            }
                                            target.sendMessage("code: " + args[3] + ", time: " + time + "seconds");
                                        }
                                    }
                                    time -= 1;
                                }
                                else {
                                    if (args[0].equalsIgnoreCase("@all")) {
                                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                        Bukkit.getServer().getOnlinePlayers().toArray(players);

                                        if (isInt(args[2])) {
                                            if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                                                for (int i = 0; i < players.length; i++) {
                                                    players[i].getWorld().createExplosion(players[i].getLocation(), Integer.parseInt(args[2]));
                                                    time = Integer.parseInt(args[1]);
                                                    Bukkit.getScheduler().cancelTask(-1);
                                                }
                                            }
                                            else {
                                                sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                                                time = Integer.parseInt(args[1]);
                                                Bukkit.getScheduler().cancelTask(-1);
                                            }
                                        }
                                        else {
                                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                                            time = Integer.parseInt(args[1]);
                                            Bukkit.getScheduler().cancelTask(-1);
                                        }
                                    }
                                    else if (args[0].equalsIgnoreCase("@local")) {
                                        player.getWorld().createExplosion(player.getLocation(), Integer.parseInt(args[2]));
                                        time = Integer.parseInt(args[1]);
                                        Bukkit.getScheduler().cancelTask(-1);
                                    }
                                    else if (args[0].equalsIgnoreCase("@random")) {
                                        Random random = new Random();

                                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                        Bukkit.getServer().getOnlinePlayers().toArray(players);

                                        int num = random.nextInt(players.length);

                                        players[num].getWorld().createExplosion(players[num].getLocation(), Integer.parseInt(args[2]));
                                        time = Integer.parseInt(args[1]);
                                        Bukkit.getScheduler().cancelTask(-1);
                                    }
                                    else {
                                        Player target = Bukkit.getPlayerExact(args[0]);
                                        if (target == null) {
                                            player.sendMessage(ChatColor.RED + "Your target " + args[0] + " is not online");
                                            time = Integer.parseInt(args[1]);
                                            Bukkit.getScheduler().cancelTask(-1);
                                        }
                                        else {
                                            target.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
                                            time = Integer.parseInt(args[1]);
                                            Bukkit.getScheduler().cancelTask(-1);
                                        }
                                    }
                                }
                            }
                        }
                    }, 0l, (long) time * 20);
                }
                else {
                    sender.sendMessage(ChatColor.RED + "please write integer above 0");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "please write integer above 0");
            }
        }

        //get bomb item
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
        else if (command.getName().equalsIgnoreCase("timebomb")) {
            if (args.length == 1) {
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

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer() == tTarget) {
            if (event.getMessage().equalsIgnoreCase(text)) {
                Bukkit.getScheduler().cancelTask(-1);
            }
        }
    }
}
