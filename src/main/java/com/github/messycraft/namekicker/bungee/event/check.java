package com.github.messycraft.namekicker.bungee.event;

import com.github.messycraft.namekicker.bungee.Main;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

import static com.github.messycraft.namekicker.bungee.Main.replaceColor;

public class check implements Listener {
    @EventHandler
    public void main(PreLoginEvent e) {
        String name = e.getConnection().getName();
        int length = name.length();
        boolean canpass = true;

        if (length > Main.config.getInt("ID-maximum-length")) {
            e.setCancelReason(new TextComponent(replaceColor(Main.config.getString("ID-maximum-length-kickresult"))));
            e.setCancelled(true);
            canpass = false;
        }

        if (canpass && length < Main.config.getInt("ID-minimum-length")) {
            e.setCancelReason(new TextComponent(replaceColor(Main.config.getString("ID-minimum-length-kickresult"))));
            e.setCancelled(true);
            canpass = false;
        }

        if (canpass && containsIgnoreCase(Main.config.getStringList("illegal-list"),name)) {
            String R = Main.config.getString("illegal-kickresult").replaceAll("<R>",name);
            e.setCancelReason(new TextComponent(replaceColor(R)));
            e.setCancelled(true);
            canpass = false;
        }

        if (canpass && !containsIgnoreCaseStr(name,Main.config.getStringList("illegal-contains-list")).equals("")) {
            String R = Main.config.getString("illegal-contains-kickresult").replaceAll("<R>",
                    containsIgnoreCaseStr(name,Main.config.getStringList("illegal-contains-list")));
            e.setCancelReason(new TextComponent(replaceColor(R)));
            e.setCancelled(true);
            canpass = false;
        }

        if (canpass && !IgnoreCaseStrBeginning(name,Main.config.getStringList("illegal-beginning-list")).equals("")) {
            String R = Main.config.getString("illegal-beginning-kickresult").replaceAll("<R>",
                    IgnoreCaseStrBeginning(name,Main.config.getStringList("illegal-beginning-list")));
            e.setCancelReason(new TextComponent(replaceColor(R)));
            e.setCancelled(true);
            canpass = false;
        }

        if (canpass && !IgnoreCaseStrEnd(name,Main.config.getStringList("illegal-end-list")).equals("")) {
            String R = Main.config.getString("illegal-end-kickresult").replaceAll("<R>",
                    IgnoreCaseStrEnd(name,Main.config.getStringList("illegal-end-list")));
            e.setCancelReason(new TextComponent(replaceColor(R)));
            e.setCancelled(true);
        }
    }

    public boolean containsIgnoreCase(List<String> list, String s) {
        int l = list.size();
        boolean pass = true;
        for (int i=0; i<l; i++) {
            if (list.get(i).equalsIgnoreCase(s)) {
                pass = false;
                break;
            }
        }
        return !pass;
    }

    public String containsIgnoreCaseStr(String s, List<String> list) {
        String str = "";
        int l = list.size();
        for (int i=0; i<l; i++) {
            if (!list.get(i).equals("")) {
                if (s.toLowerCase().contains(list.get(i).toLowerCase())) {
                    str = list.get(i);
                    break;
                }
            }
        }
        return str;
    }

    public String IgnoreCaseStrBeginning(String s, List<String> list) {
        String str = "";
        int l = list.size();
        for (int i=0; i<l; i++) {
            if (!list.get(i).equals("")) {
                if (s.substring(0, list.get(i).length()).equalsIgnoreCase(list.get(i))) {
                    str = list.get(i);
                    break;
                }
            }
        }
        return str;
    }

    public String IgnoreCaseStrEnd(String s, List<String> list) {
        String str = "";
        int l = list.size();
        for (int i=0; i<l; i++) {
            if (!list.get(i).equals("")) {
                if (s.substring(s.length() - list.get(i).length()).equalsIgnoreCase(list.get(i))) {
                    str = list.get(i);
                    break;
                }
            }
        }
        return str;
    }
}
