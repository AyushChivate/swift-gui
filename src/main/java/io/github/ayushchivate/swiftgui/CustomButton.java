package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/* An abstract class that represents a user-defined button */
public abstract class CustomButton implements Listener {

    /**
     * The index this button will be placed in.
     */
    private int index;

    /**
     * The material that will represent this button.
     */
    private Material material;

    SwiftGui swiftGui;

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button will be in
     */
    public CustomButton(int index, Material material, SwiftGui swiftGui) {

        /* register the event */
        Bukkit.getPluginManager().registerEvents(this, SwiftGui.getPluginInstance());

        /* make sure the index is not negative */
        if (index < 0) {
            throw new IllegalArgumentException("CustomButton index cannot be negative. Must be a positive integer.");
        }

        /* initialize variables */
        this.index = index;
        this.material = material;
        this.swiftGui = swiftGui;
    }

    /**
     * Get the index of the button.
     */
    protected int getIndex() {
        return index;
    }

    /**
     * Get the material of the button.
     */
    protected Material getMaterial() {
        return material;
    }

    /**
     * User defined method to control what happens after the button is clicked.
     *
     * @param event the event that has fired when the button is clicked.
     */
    public abstract void onClick(InventoryClickEvent event);

    /**
     * Detects when the button is pressed.
     *
     * @param event the event that is fired when the button is clicked.
     */
    @EventHandler
    private void checkForButtonPress(InventoryClickEvent event) {

        /* check for button press */


        onClick(event);
    }
}
