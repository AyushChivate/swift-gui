package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Map;

public class BackButton extends CustomButton {

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index    the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button is in
     */
    public BackButton(int index, Material material, SwiftGui swiftGui) {
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

        /* get the page number of the previous page */
        int previousPageNumber = this.page.getPageNumber() - 1;

        /* get the previous page using the previous page's page number as a key */
        Page previousPage = pages.get(previousPageNumber);

        /* make sure the previous page exists */
        if (previousPage == null) {
            return;
        }

        /* open the previous page for the player */
        previousPage.openInventory(player);
    }
}
