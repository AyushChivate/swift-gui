package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a page in a SwiftGui
 */
public class Page {

    /**
     * The page number of this page.
     */
    private int pageNumber;

    /**
     * The number of rows the inventory has in this page.
     */
    private int numberOfRows;

    /**
     * The name of the inventory in this page.
     */
    private String name;

    /**
     * The inventory of this page.
     */
    private Inventory inventory;

    /**
     * The default index of the back button.
     */
    private final int DEFAULT_BACK_BUTTON_INDEX;

    /**
     * The default index of the delete page button.
     */
    private final int DEFAULT_DELETE_PAGE_BUTTON_INDEX;

    /**
     * The default index of the new page button.
     */
    private final int DEFAULT_NEW_PAGE_BUTTON_INDEX;

    /**
     * The default index of the forward button.
     */
    private final int DEFAULT_FORWARD_BUTTON_INDEX;

    /**
     * Shows weather or not the inventory object has been set with
     */
    private boolean inventoryHasNotBeenInitialized;

    /**
     * Creates a page with the specified page number, number of rows, and name.
     *
     * @param pageNumber the page number of this page
     * @param numberOfRows the number of rows of this page
     * @param name the name of this page
     */
    protected Page(int pageNumber, int numberOfRows, @NotNull String name) {
        this.pageNumber = pageNumber;
        this.numberOfRows = numberOfRows;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, numberOfRows * 9, name);
        this.inventoryHasNotBeenInitialized = name.equals("");

        this.DEFAULT_BACK_BUTTON_INDEX = this.numberOfRows * 9 - 9;
        this.DEFAULT_FORWARD_BUTTON_INDEX = this.numberOfRows * 9 - 1;
        this.DEFAULT_NEW_PAGE_BUTTON_INDEX = this.numberOfRows * 9 - 4;
        this.DEFAULT_DELETE_PAGE_BUTTON_INDEX = this.numberOfRows * 9 - 6;
    }

    /**
     * Opens this page's inventory for the specified player.
     */
    protected void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    /**
     * Adds a back button to this page in the default index which is the first slot of the last row.
     */
    public void addBackButton() {

    }

    /**
     * Adds a back button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addBackButton(int index) {

    }

    /**
     * Adds a delete page button to this page in the default index which is the fourth slot of the last row.
     */
    public void addDeletePageButton() {

    }

    /**
     * Adds a delete page button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addDeletePageButton(int index) {

    }

    /**
     * Adds a new page button to this page in the default index which is the sixth slot of the last row.
     */
    public void addNewPageButton() {

    }

    /**
     * Adds a new page button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addNewPageButton(int index) {

    }

    /**
     * Adds a forward button to this page in the default index which is the last slot of the last row.
     */
    public void addForwardButton() {

    }

    /**
     * Adds a forward button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addForwardButton(int index) {

    }

    /**
     * Adds a customButton to this page at the specified index.
     *
     * @param customButton the type of button to be added
     * @param index        the index in the page where the button should appear
     */
    public void addCustomButton(CustomButton customButton, int index) {

    }

    /**
     * Adds a dash and the specified page number after this pages name.
     *
     * @param pageNumber the page number to be appended
     */
    protected void renameWithNumber(int pageNumber) {

    }

    /**
     * The page will be filled with the specified material according to the border pattern
     *
     * @param borderPattern The pattern corresponding to which indexes should be filled.
     *                      1 means that index will be filled with the fillMaterial,
     *                      while 0 means that index will be left untouched.
     * @param fillMaterial  the material that will be used to fill the border
     */
    public void fillBorder(int[] borderPattern, Material fillMaterial) {

    }
}
