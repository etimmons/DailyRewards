package com.delsurf.DailyRewards;

import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewards extends JavaPlugin {
	
	public Hashtable<UUID,Boolean> playerAnswerMap = new Hashtable<UUID, Boolean>();
	
	@Override
    public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		// TODO: Read today's json file to see who has answered a question
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    	// TODO: Save playersAnswerMap to json file dated today
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("dr")) {
    		if (sender instanceof Player) {
    			
                Player player = (Player) sender;
                
                getLogger().info("onCommand has been invoked by " + player.getDisplayName() + " (" + player.getUniqueId().toString() + ")!");
                
                if (args.length == 0) {
                	sender.sendMessage("What is the square root of 100?");
                	return false;
                }
                
                if (this.hasPlayerAnsweredQuestion(player)) {
                	player.sendMessage("You have already answered the question today.");
                	return true;
                }
                
                String answer = String.join(" ", args);
                
	            if (this.isAnswerCorrect(answer)) {
	                String msg = "'" + answer + "' is Correct! You have earned 1 Diamond!";
	                player.sendMessage(msg);
	                PlayerInventory inventory = player.getInventory(); // The player's inventory
	                ItemStack itemstack = new ItemStack(Material.DIAMOND, 1); // A stack of diamonds (or not)
	                inventory.addItem(itemstack); // Adds the stack to the player's inventory
	                this.playerAnswerMap.put(player.getUniqueId(), true);
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
    
    public boolean hasPlayerAnsweredQuestion(Player player) {	
    	UUID uuid = player.getUniqueId();
    	
    	getLogger().info(uuid.toString());
    	
    	boolean hasAnswered = false;
    	
    	hasAnswered = this.playerAnswerMap.containsKey(uuid);
    	
    	if (hasAnswered) {
    		return true;
    	}
    	
    	return false;
    }
}
