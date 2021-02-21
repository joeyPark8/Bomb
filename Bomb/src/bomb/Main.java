package bomb;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Main extends JavaPlugin implements Listener {
    Enchantment enchant = new Enchantment(NamespacedKey.minecraft("12345")) {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public int getMaxLevel() {
            return 1000000;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment enchantment) {
            return true;
        }

        @Override
        public boolean canEnchantItem(ItemStack itemStack) {
            return true;
        }
    };

    List<Location> mineLocations = new ArrayList<>();

    int time = 0;
    String text = "";
    boolean sended = false;

    @Override
    public void onEnable() {
        System.out.println("Bomb is activated");

        getCommand("bomb").setExecutor(this::onCommand);
        getCommand("bomb").setTabCompleter(this::onTabComplete);

        getCommand("timebomb").setExecutor(this::onCommand);
        getCommand("timebomb").setTabCompleter(this::onTabComplete);

        getCommand("getbomb").setExecutor(this::onCommand);
        getCommand("getbomb").setTabCompleter(this::onTabComplete);

        Bukkit.getPluginManager().registerEvents(this, this);
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
                            for (Player target : players) {
                                target.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
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
                            for (Player target : players) {
                                target.getWorld().spawnEntity(target.getLocation(), EntityType.PRIMED_TNT);
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
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (time != -1) {
                                if (time != 0) {
                                    if (time == Integer.parseInt(args[1])) {
                                        if (args[0].equalsIgnoreCase("@all")) {
                                            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                            Bukkit.getServer().getOnlinePlayers().toArray(players);

                                            for (Player target : players) {
                                                target.sendMessage("code: " + args[3] + ", time: " + time);
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
                                                cancel();
                                            }
                                            target.sendMessage("code: " + args[3] + ", time: " + time + "seconds");
                                        }
                                    }
                                    if (sended) {
                                        sended = false;
                                        cancel();
                                    }
                                    time -= 1;
                                }
                                else {
                                    if (args[0].equalsIgnoreCase("@all")) {
                                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                        Bukkit.getServer().getOnlinePlayers().toArray(players);

                                        if (isInt(args[2])) {
                                            if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 51) {
                                                for (Player target : players) {
                                                    target.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
                                                    time = Integer.parseInt(args[1]);
                                                    cancel();
                                                }
                                            }
                                            else {
                                                sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                                                time = Integer.parseInt(args[1]);
                                                cancel();
                                            }
                                        }
                                        else {
                                            sender.sendMessage(ChatColor.RED + "please write integer from 1 to 50");
                                            time = Integer.parseInt(args[1]);
                                            cancel();
                                        }
                                    }
                                    else if (args[0].equalsIgnoreCase("@local")) {
                                        player.getWorld().createExplosion(player.getLocation(), Integer.parseInt(args[2]));
                                        time = Integer.parseInt(args[1]);
                                        cancel();
                                    }
                                    else if (args[0].equalsIgnoreCase("@random")) {
                                        Random random = new Random();

                                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                        Bukkit.getServer().getOnlinePlayers().toArray(players);

                                        int num = random.nextInt(players.length);

                                        players[num].getWorld().createExplosion(players[num].getLocation(), Integer.parseInt(args[2]));
                                        time = Integer.parseInt(args[1]);
                                        cancel();
                                    }
                                    else {
                                        Player target = Bukkit.getPlayerExact(args[0]);
                                        if (target == null) {
                                            player.sendMessage(ChatColor.RED + "Your target " + args[0] + " is not online");
                                        }
                                        else {
                                            target.getWorld().createExplosion(target.getLocation(), Integer.parseInt(args[2]));
                                        }
                                        time = Integer.parseInt(args[1]);
                                        cancel();
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(this, 0, time * 2);
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
            if (args[0].equalsIgnoreCase("@all")) {
                if (args[1].equalsIgnoreCase("mine")) {
                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);

                    ItemStack mine = new ItemStack(Material.FLOWER_POT);
                    ItemMeta meta = mine.getItemMeta();

                    meta.setDisplayName("mine");
                    meta.addEnchant(enchant, 1, true);

                    for (Player i : players) {
                        i.getInventory().addItem(mine);
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Unknown type of bomb");
                }
                return false;
            }
            else if (args[0].equalsIgnoreCase("@local")) {
                if (args[1].equalsIgnoreCase("mine")) {
                    ItemStack mine = new ItemStack(Material.FLOWER_POT);
                    ItemMeta meta = mine.getItemMeta();

                    meta.setDisplayName("mine");
                    meta.addEnchant(enchant, 1, true);

                    player.getInventory().addItem(mine);
                }
                else {
                    player.sendMessage(ChatColor.RED + "Unknown type of bomb");
                }
                return false;
            }
            else if (args[0].equalsIgnoreCase("@random")) {
                Random random = new Random();

                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                return false;
            }

            //player target
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Your target " + args[1] + " is not online");
            }
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
                List<String> types = new ArrayList<>();
                types.add("explosion");
                types.add("tnt");

                List<String> result = new ArrayList<>();
                for (String a : types) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }
            if (args.length == 2) {
                List<String> targets = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                for (Player player : players) {
                    targets.add(player.getName());
                }

                targets.add("@all");
                targets.add("@local");
                targets.add("@random");

                List<String> result = new ArrayList<>();
                for (String a : targets) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }
        }
        else if (command.getName().equalsIgnoreCase("timebomb")) {
            if (args.length == 1) {
                List<String> targets = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                for (Player player : players) {
                    targets.add(player.getName());
                }

                targets.add("@all");
                targets.add("@local");
                targets.add("@random");

                List<String> result = new ArrayList<>();
                for (String a : targets) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }
        }
        else if (command.getName().equalsIgnoreCase("getbomb")) {
            if (args.length == 1) {
                List<String> targets = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                for (Player player : players) {
                    targets.add(player.getName());
                }

                targets.add("@all");
                targets.add("@local");
                targets.add("@random");

                List<String> result = new ArrayList<>();
                for (String a : targets) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }
            if (args.length == 2) {
                List<String> types = new ArrayList<>();

                types.add("mine");

                List<String> result = new ArrayList<>();
                for (String a : types) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }
        }
        return null;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase(text)) {
            sended = true;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemMeta meta = event.getItem().getItemMeta();
        if (meta.getDisplayName().equalsIgnoreCase("mine")) {
            if (meta.getEnchants().containsKey(enchant)) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    Player player = event.getPlayer();
                    Block targetBlock = player.getTargetBlock(null, 50);

                    if (!targetBlock.getType().isAir()) {
                        if (mineLocations.contains(targetBlock.getLocation())) {
                            mineLocations.add(targetBlock.getLocation());
                            player.sendMessage(ChatColor.GREEN + "Set mine at " + targetBlock.getLocation());
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Mine already exists at " + targetBlock.getLocation());
                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}