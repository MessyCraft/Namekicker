package com.github.messycraft.namekicker.bukkit.event;

import com.github.messycraft.namekicker.bukkit.Namekicker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

import static org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER;
import static com.github.messycraft.namekicker.bukkit.Namekicker.replaceColor;

public class check implements Listener {
    Plugin m = Namekicker.getProvidingPlugin(Namekicker.class);
    @EventHandler
    public void main(PlayerLoginEvent e) {
        String name = e.getPlayer().getName();
        int length = name.length();
        boolean canpass = true;

        if (length > m.getConfig().getInt("ID-maximum-length")) {
            e.disallow(KICK_OTHER, replaceColor(
                    m.getConfig().getString("ID-maximum-length-kickresult")));
            canpass = false;
        }

        if (canpass && length < m.getConfig().getInt("ID-minimum-length")) {
            e.disallow(KICK_OTHER, replaceColor(
                    m.getConfig().getString("ID-minimum-length-kickresult")));
            canpass = false;
        }

        if (canpass && containsIgnoreCase(m.getConfig().getStringList("illegal-list"),name)) {
            String R = m.getConfig().getString("illegal-kickresult").replaceAll("<R>",name);
            e.disallow(KICK_OTHER, replaceColor(R));
            canpass = false;
        }

        if (canpass && !containsIgnoreCaseStr(name,m.getConfig().getStringList("illegal-contains-list")).equals("")) {
            String R = m.getConfig().getString("illegal-contains-kickresult").replaceAll("<R>",
                    containsIgnoreCaseStr(name,m.getConfig().getStringList("illegal-contains-list")));
            e.disallow(KICK_OTHER, replaceColor(R));
            canpass = false;
        }

        if (canpass && !IgnoreCaseStrBeginning(name,m.getConfig().getStringList("illegal-beginning-list")).equals("")) {
            String R = m.getConfig().getString("illegal-beginning-kickresult").replaceAll("<R>",
                    IgnoreCaseStrBeginning(name,m.getConfig().getStringList("illegal-beginning-list")));
            e.disallow(KICK_OTHER, replaceColor(R));
            canpass = false;
        }

        if (canpass && !IgnoreCaseStrEnd(name,m.getConfig().getStringList("illegal-end-list")).equals("")) {
            String R = m.getConfig().getString("illegal-end-kickresult").replaceAll("<R>",
                    IgnoreCaseStrEnd(name,m.getConfig().getStringList("illegal-end-list")));
            e.disallow(KICK_OTHER, replaceColor(R));
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
