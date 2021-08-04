package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ForwardButton extends CustomButton {

    /**
     * Creates a page with the specified index, material, and SwiftGui.
     *
     * @param index    the index position of the button
     * @param material the material that represents the button
     * @param swiftGui the instance of the SwiftGui this button is in
     */
    public ForwardButton(int index, Material material, SwiftGui swiftGui) {
        super(index, material, swiftGui);
    }

    /**
     * User defined method to control what happens after the button is clicked.
     *
     * @param event the event that has fired when the button is clicked.
     */
    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
