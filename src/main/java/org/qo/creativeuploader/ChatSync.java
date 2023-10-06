package org.qo.creativeuploader;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.TimerTask;

public class ChatSync extends TimerTask implements Listener{
    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("<Survival:").append(event.getPlayer().getName()).append(">").append(event.getMessage());
        Request.sendPostRequest("http://localhost:8080/qo/survival/msgupload", sb.toString());
    }

    @Override
    public void run() {
        try {
            String result = Request.sendGetRequest("http://localhost:8080/qo/creative/msgdownload");
            if(result != null&& !result.equals("")) Bukkit.broadcastMessage(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
