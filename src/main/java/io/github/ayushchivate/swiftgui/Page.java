package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
     * @param pageNumber   the page number of this page
     * @param numberOfRows the number of rows of this page
     * @param name         the name of this page
     */
    protected Page(int pageNumber, int numberOfRows, @NotNull String name) {

        /* make sure the page number and number of rows is positive */
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("pageNumber cannot be less than or equal to zero. " +
                    "Must be a positive integer");
        } else if (numberOfRows <= 0) {
            throw new IllegalArgumentException("numberOfRows cannot be less than or equal to zero. " +
                    "Must be a positive multiple of 9.");
        }

        /* initialize fields */
        this.pageNumber = pageNumber;
        this.numberOfRows = numberOfRows;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, numberOfRows * 9, name);
        this.inventoryHasNotBeenInitialized = name.equals("");

        /* initialize default indexes */
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
        addBackButton(this.DEFAULT_BACK_BUTTON_INDEX);
    }

    /**
     * Adds a back button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addBackButton(int index) {
        this.inventory.setItem(index, new ItemStack(Material.ARROW));
        CustomButton backButton = new BackButton(index, Material.ARROW);
    }

    /**
     * Adds a delete page button to this page in the default index which is the fourth slot of the last row.
     */
    public void addDeletePageButton() {
        addDeletePageButton(this.DEFAULT_DELETE_PAGE_BUTTON_INDEX);
    }

    /**
     * Adds a delete page button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addDeletePageButton(int index) {
        this.inventory.setItem(index, new ItemStack(Material.TNT));
        CustomButton deletePageButton = new DeletePageButton(index, Material.TNT);
    }

    /**
     * Adds a new page button to this page in the default index which is the sixth slot of the last row.
     */
    public void addNewPageButton() {
        addNewPageButton(this.DEFAULT_NEW_PAGE_BUTTON_INDEX);
    }

    /**
     * Adds a new page button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addNewPageButton(int index) {
        this.inventory.setItem(index, new ItemStack(Material.PAPER));
        CustomButton newPageButton = new NewPageButton(index, Material.PAPER);
    }

    /**
     * Adds a forward button to this page in the default index which is the last slot of the last row.
     */
    public void addForwardButton() {
        addForwardButton(this.DEFAULT_FORWARD_BUTTON_INDEX);
    }

    /**
     * Adds a forward button to this page at the specified index.
     *
     * @param index the index in the page where the button should appear.
     */
    public void addForwardButton(int index) {
        this.inventory.setItem(index, new ItemStack(Material.ARROW));
        CustomButton forwardButton = new ForwardButton(index, Material.ARROW);
    }

    /**
     * Adds a customButton to this page at the specified index.
     *
     * @param customButton the type of button to be added
     */
    public void addCustomButton(CustomButton customButton) {
        this.inventory.setItem(customButton.getIndex(), new ItemStack(customButton.getMaterial()));
    }

    /**
     * Numbers this page in ascending order in correspondence to the other pages in the SwiftGui.
     * A dash followed by the page number is appended to the page name.
     */
    protected void renameAscending() {

        /* change the page name */
        this.name = this.name + " - " + this.pageNumber;

        /* get the contents of the old inventory*/
        ItemStack[] contents = this.inventory.getContents();
        /* reassign this page's inventory to a new inventory*/
        this.inventory = Bukkit.createInventory(null, this.numberOfRows, this.name);
        /* populate new inventory with the old inventory's materials */
        this.inventory.addItem(contents);
    }

    /**
     * Numbers this page in descending order in correspondence to the other pages in the SwiftGui.
     * A dash followed by the page number is appended to the page name.
     */
    protected void renameDescending(int size) {

        /* change the page name */
        this.name = this.name + " - " + (size - this.pageNumber);

        /* get the contents of the old inventory*/
        ItemStack[] contents = this.inventory.getContents();
        /* reassign this page's inventory to a new inventory*/
        this.inventory = Bukkit.createInventory(null, this.numberOfRows, this.name);
        /* populate new inventory with the old inventory's materials */
        this.inventory.addItem(contents);
    }

    /**
     * The page will be filled with the specified material according to the border pattern
     *
     * @param borderPattern The pattern corresponding to which indexes should be filled.
     *                      1 means that index will be filled with the fillMaterial,
     *                      while 0 means that index will be left untouched.
     * @param fillMaterial  the material that will be used to fill the border
     */
    public void fillBorder(int[][] borderPattern, Material fillMaterial) {

        /* make sure the border pattern has the same dimensions as this page */

        if (borderPattern.length != this.numberOfRows) {
            throw new IllegalArgumentException("borderPattern must have the same number of rows as the page");
        }

        for (int[] row : borderPattern) {
            if (row.length != 9) {
                throw new IllegalArgumentException("borderPattern must have the same number of columns as the page");
            }
        }

        /* get the contents of the old inventory*/
        ItemStack[] contents = this.inventory.getContents();
        /* reassign this page's inventory to a new inventory*/
        this.inventory = Bukkit.createInventory(null, this.numberOfRows, this.name);

        /* fill the border */
        int index = 0;

        for (int[] row : borderPattern) {
            for (int value : row) {
                if (value == 1) {
                    this.inventory.setItem(index, new ItemStack(fillMaterial));
                }
                index++;
            }
        }

        /* populate new inventory with the old inventory's materials */
        this.inventory.addItem(contents);
    }
}
