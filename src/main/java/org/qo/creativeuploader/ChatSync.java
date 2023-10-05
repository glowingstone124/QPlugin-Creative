package org.qo.creativeuploader;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.TimerTask;

public class ChatSync implements Listener {

    public void ChatSyncer(String data) throws Exception {
        Request.sendPostRequest("http://127.0.0.1:8080/qo/upload/CreativeMSG", data);
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) throws Exception {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();
        String formattedMessage = "[creative]" + "<" + playerName + ">: " + message;
        ChatSyncer(formattedMessage);
    }
    public static TimerTask broadcast() throws Exception {
        String message = Request.sendGetRequest("http://127.0.0.1:8080/qo/download/SurvivalMSG");
        Bukkit.getServer().broadcastMessage(message);
        return null;
    }
}
