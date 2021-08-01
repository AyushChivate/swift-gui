package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class CustomButton implements Listener {

    private int index;
    private Material material;

    public CustomButton(int index, Material material) {

        Bukkit.getPluginManager().registerEvents(this, SwiftGui.getPluginInstance());

        if (index < 0) {
            throw new IllegalArgumentException("CustomButton index cannot be negative");
        }

        this.index = index;
        this.material = material;
    }

    protected int getIndex() {
        return index;
    }

    protected Material getMaterial() {
        return material;
    }

    @EventHandler
    public abstract void onClick(InventoryClickEvent e);
}
