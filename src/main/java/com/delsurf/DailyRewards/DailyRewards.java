package com.delsurf.DailyRewards;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewards extends JavaPlugin {
	@Override
    public void onEnable() {
		getLogger().info("onEnable has been invoked!");
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("dr")) {
    		if (sender instanceof Player) {
    			
                Player player = (Player) sender;
                
                getLogger().info("onCommand has been invoked by " + player.getDisplayName() + "!");
                
                if (args.length == 0) {
                	sender.sendMessage("What is the square root of 100?");
                	return false;
                }
                
                String answer = args[0];
                
                boolean isAnswerCorrect = this.isAnswerCorrect(answer);
                
	            if (isAnswerCorrect == true) {
	                String msg = "'" + answer + "' is Correct! You have earned 1 Diamond!";
	                player.sendMessage(msg);
	                PlayerInventory inventory = player.getInventory(); // The player's inventory
	                ItemStack itemstack = new ItemStack(Material.DIAMOND, 1); // A stack of diamonds (or not)
	                inventory.addItem(itemstack); // Adds the stack to the player's inventory
                } else {                	
                	sender.sendMessage("'" + answer + "' is Incorrect.");
                }
            } else {
                sender.sendMessage("You must be a player!");
                return false;
            }
    		return true;
    	}
    	
        // do something
        return false;
    }
    
    public boolean isAnswerCorrect(String answer) {
    	String expectedAnswer = "10";
    	
    	if (answer.equals(expectedAnswer)) {
    		return true;
    	}
    	
    	return false;
    }
}
