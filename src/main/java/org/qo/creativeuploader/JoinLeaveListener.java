package org.qo.creativeuploader;
import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws Exception {
        event.getPlayer().sendMessage(Component.text("请稍等，我们需要对您的身份进行验证"));
        BindResponse relationship = new Gson().fromJson(Request.sendGetRequest("http://127.0.0.1:8080/qo/download/registry?name=" + event.getPlayer().getName()), BindResponse.class);
        if (relationship.code == 1) {
            event.getPlayer().kick(Component.text("验证失败，请在QQ群下载QOapp并且进入 ").append(Component.text("bind界面绑定你的游戏名:" + event.getPlayer().getName()).decorate(TextDecoration.BOLD)).append(Component.text(" 并重试！")));
        } else if (relationship.frozen) {
            event.getPlayer().kick(Component.text("验证失败，原因：您的账户已经被冻结！ ").append(Component.text("您的游戏名：" + event.getPlayer().getName()).decorate(TextDecoration.BOLD)).append(Component.text(" 请私聊群主：1294915648了解更多")));
        } else {
            event.getPlayer().sendMessage(Component.text("验证通过，欢迎回到Quantum Original Creative！").appendNewline().append(Component.text("QQ: " + relationship.qq).color(TextColor.color(114, 114, 114))));
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws Exception {

    }
}
