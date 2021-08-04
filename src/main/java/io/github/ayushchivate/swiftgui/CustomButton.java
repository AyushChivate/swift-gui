package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    /**
     * The SwiftGui this button belongs to.
     */
    SwiftGui swiftGui;

    /**
     * The page this button belongs to.
     */
    Page page;

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button is in
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
    int getIndex() {
        return index;
    }

    /**
     * Get the material of the button.
     */
    Material getMaterial() {
        return material;
    }

    /**
     * Set the page this button belongs to.
     *
     * @param page the page this button belongs to
     */
    void setPage(Page page) {
        this.page = page;
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

        /* check if the player clicked in the inventory this button is in */
        String thisInventoryName = this.page.getName();
        String otherInventoryName = event.getView().getTitle();
        if (!thisInventoryName.equals(otherInventoryName)) {
            return;
        }

        /* check if the player clicked on the index of this button */
        if (this.index != event.getSlot()) {
            return;
        }

        /* check if the item the player clicked is the same item as this button */
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        if (this.material != event.getCurrentItem().getType()) {
            return;
        }

        /* execute user-defined code */
        onClick(event);
    }
}
