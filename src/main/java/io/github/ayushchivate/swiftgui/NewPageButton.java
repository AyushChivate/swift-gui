package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class NewPageButton extends CustomButton {

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index    the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button is in
     */
    public NewPageButton(int index, Material material, SwiftGui swiftGui) {
        super(index, material, swiftGui);
    }

    /**
     * User defined method to control what happens after the button is clicked.
     *
     * @param event the event that has fired when the button is clicked.
     */
    @Override
    public void onClick(InventoryClickEvent event) {

        Player player;

        try {
            /* get the player */
            player = (Player) event.getWhoClicked();
        } catch (ClassCastException e) {
            return;
        }

        /* get the map of pages*/
        Map<Integer, Page> pages = this.swiftGui.getPages();

        /* get the number of rows this inventory has */
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;
        int rows = clickedInventory.getSize() / 9;

        /* add new page to the SwiftGui */
        Page page = this.swiftGui.addNewPage(rows, this.page.getName());

        /* open the previous page for the player */
        page.openInventory(player);
    }
}
