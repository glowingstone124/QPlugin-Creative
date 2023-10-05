package org.qo.creativeuploader;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new MSPTCalculator(),this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        try {
            getServer().getPluginManager().registerEvents(new ChatSync(), this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Timer timer = new Timer();
        timer.schedule(new StatusUploader(), 1000, 3000);
        try {
            timer.schedule(ChatSync.broadcast(), 500, 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
