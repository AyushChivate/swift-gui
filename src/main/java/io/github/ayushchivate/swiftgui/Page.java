package io.github.ayushchivate.swiftgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class Page {

    private int pageNumber;
    private int numberOfRows;
    private String name;
    private Inventory inventory;

    private final int DEFAULT_BACK_BUTTON_INDEX;
    private final int DEFAULT_FORWARD_BUTTON_INDEX;
    private final int DEFAULT_NEW_PAGE_BUTTON_INDEX;
    private final int DEFAULT_DELETE_PAGE_BUTTON_INDEX;

    private boolean inventoryHasNotBeenInitialized;

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

    protected void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    /**
     * The page will be filled with the specified material according to the border pattern
     *
     * @param borderPattern The pattern corresponding to which indexes should be filled.
     *                      1 means that index will be filled with the fillMaterial,
     *                      while 0 means that index will be left untouched.
     * @param fillMaterial  the material that will be used to fill the border
     * @param page          the page which will have its border filled
     */
    public void fillBorder(int[] borderPattern, Material fillMaterial) {

    }

    public void addBackButton() {
        addBackButton(this.DEFAULT_BACK_BUTTON_INDEX);
    }

    public void addBackButton(int index) {


    }
}
