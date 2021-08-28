package me.epicgodmc.blockstackerx.hook.hologram;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.Common;

public class StackerHologram {

    private final HologramHook hookInstance;
    private final String format;
    private Object hologram;


    public StackerHologram(HologramHook hookInstance, String format) {
        this.hookInstance = hookInstance;
        this.format = format;
    }


    public void create(int value, Location location) {
        Common.runLater(new BukkitRunnable() {
            Object hologram;

            @Override
            public void run() {
                hologram = hookInstance.createHologram(location, Common.toList(format.replace("{value}", String.valueOf(value))));
                StackerHologram.this.hologram = hologram;
            }
        });
    }


    public void update(int value) {
        this.update(0, format.replace("{value}", String.valueOf(value)));
    }

    public void update(int line, String text) {
        this.hologram = hookInstance.updateLine(hologram, line, text);
    }

    public void delete() {
        hookInstance.deleteHologram(hologram);
    }
}