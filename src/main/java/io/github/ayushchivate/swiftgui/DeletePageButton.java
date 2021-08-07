package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Map;

public class DeletePageButton extends CustomButton {

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index    the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button is in
     */
    public DeletePageButton(int index, Material material, SwiftGui swiftGui) {
        super(index, material, swiftGui);
    }

    /**
     * User defined method to control what happens after the button is clicked.
     *
     * @param event the event that has fired when the button is clicked.
     */
    @Override
    public void onClick(InventoryClickEvent event) {

        /* get the map of pages*/
        Map<Integer, Page> pages = this.swiftGui.getPages();

        /* get the page number of the page that needs to be deleted */
        int pageNumberToBeDeleted = this.page.getPageNumber();

        /* remove the page from the map */
        pages.remove(pageNumberToBeDeleted);

        if (this.swiftGui.isAscending() || this.swiftGui.isDescending()) {
            /* rename all pages greater than the deleted page */
            for (Map.Entry<Integer, Page> entry : pages.entrySet()) {
                int key = entry.getKey();
                Page page = entry.getValue();
                if (key < pageNumberToBeDeleted) {
                    /* change the key */
                    pages.put(key - 1, pages.remove(key));
                    page.setPageNumber(key - 1);
                    if (this.swiftGui.isAscending()) page.renameAscending();
                    else if (this.swiftGui.isDescending()) page.renameDescending();
                }
            }
        }
    }
}
