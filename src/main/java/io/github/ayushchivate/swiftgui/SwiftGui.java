package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/*
 * TODO: Add page persistence when opening up a page. Make it an option.
 * TODO: Make it so that the add page or delete button when alone is in the center but when both are added, put a
 *  space between them.
 * TODO: Add a gui menu in game to make menus.
 * TODO: Add saving features.
 */

/**
 * A container class that holds multiple pages.
 */
public class SwiftGui {

    /**
     * A map of pages and their corresponding page number in this SwiftGui.
     */
    private Map<Integer, Page> pages;

    /**
     * An instance of the plugin used to register events.
     */
    private static JavaPlugin plugin;

    /**
     *  Determines if this SwiftGui is in ascending order.
     */
    private boolean isAscending = false;

    /**
     *  Determines if this SwiftGui is in descending order.
     */
    private boolean isDescending = false;

    /**
     * Creates an empty SwiftGui with no pages.
     */
    public SwiftGui() {
        this(0, -1, "");
    }

    /**
     * Creates a SwiftGui with the specified number of pages.
     * Each page will have the specified number of rows.
     * None of the pages will have a name, so it must be set individually for each page.
     *
     * @param numberOfPages the number of pages to be added to this SwiftGui
     * @param numberOfRows  the number of rows in each page that is added
     */
    public SwiftGui(int numberOfPages, int numberOfRows) {
        this(numberOfRows, numberOfPages, "");
    }

    /**
     * Creates a SwiftGui with the specified number of pages.
     * Each page will have the specified number of rows.
     * Every page will have the name that is specified.
     *
     * @param numberOfPages the number of pages to be added to this SwiftGui
     * @param numberOfRows  the number of rows in each page that is added
     * @param pagesName     the name for all the pages that are added
     * @throws IllegalArgumentException if the number of rows or the number of pages is negative
     */
    public SwiftGui(int numberOfPages, int numberOfRows, @NotNull String pagesName) {

        this.pages = new HashMap<>();

        /* temporarily set number of rows to 1 if it is -1 */
        if (numberOfRows == -1) numberOfRows = 1;

        /* prevent the number of rows or pages from being negative */
        if (numberOfPages < 0) {
            throw new IllegalArgumentException("numberOfPages cannot be negative");
        } else if (numberOfRows <= 0) {
            throw new IllegalArgumentException("numberOfRows must be a positive multiple of 9");
        }

        /* create pages and add them to the map */
        for (int i = 1; i <= numberOfPages; i++) {
            this.pages.put(i, new Page(i, numberOfRows, pagesName));
        }
    }

    /**
     * Sets the instance of the plugin.
     *
     * @param plugin the instance of the plugin
     */
    public static void setPluginInstance(JavaPlugin plugin) {
        SwiftGui.plugin = plugin;
    }

    /**
     * Get the instance of the plugin.
     *
     * @return the instance of the plugin
     */
    protected static JavaPlugin getPluginInstance() {
        return plugin;
    }

    /**
     * Gets if this SwiftGui is in ascending order or not.
     *
     * @return the ascending order status
     */
    protected boolean isAscending() {
        return isAscending;
    }

    /**
     * Gets if this SwiftGui is in descending order or not.
     *
     * @return the descending order status
     */
    protected boolean isDescending() {
        return isDescending;
    }

    /**
     * Opens the inventory for the specified player
     *
     * @param player the player who's inventory should be opened
     */
    public void open(Player player) {
        Page page = this.pages.get(1);
        page.openInventory(player);
    }

    /**
     * Adds a new page to this SwiftGui.
     * The page does not have a name, so it must be set separately.
     *
     * @param numberOfRows the number of rows in the page
     * @return the page that was added
     */
    public Page addNewPage(int numberOfRows) {
        return addNewPage(numberOfRows, "");
    }

    /**
     * Adds a new page to this SwiftGui.
     * The page will have the name that is specified.
     *
     * @param numberOfRows the number of rows in the page
     * @param pageName the name of the page
     * @return the page that was added
     */
    public Page addNewPage(int numberOfRows, @NotNull String pageName) {

        /* Make sure the number of rows is valid */
        if (numberOfRows <= 0) {
            throw new IllegalArgumentException("numberOfRows must be a positive multiple of 9");
        }

        /* the page number of the new page is the current size of the map plus 1 */
        int pageNumber = this.pages.size() + 1;

        /* create a page and put it in the map */
        Page page = new Page(pageNumber, numberOfRows, pageName);
        this.pages.put(pageNumber, page);

        /* Number in ascending or descending order if needed */
        if (this.isAscending) {
            page.numberAscendingOrder();
        } else if (this.isDescending) {
            page.numberDescendingOrder();
        }

        return page;
    }

    /**
     * Adds the specified number of pages to this SwiftGui.
     * The pages do not have a name and must be set at some point.
     *
     * @param numberOfPages the number of pages to be added
     * @param numberOfRows the number of rows in the pages
     * @return a list of the pages that were created
     */
    public Page[] addNewPages(int numberOfPages, int numberOfRows) {
        return addNewPages(numberOfRows, numberOfPages, "");
    }

    /**
     * Adds the specified number of pages to this SwiftGui.
     * The pages will have the name that is specified.
     *
     * @param numberOfPages the number of pages to be added
     * @param numberOfRows the number of rows in the pages
     * @param pagesName the name for all the pages that are added
     * @return a list of the pages that were created
     */
    public Page[] addNewPages(int numberOfPages, int numberOfRows, @NotNull String pagesName) {

        Page[] addedPages = new Page[numberOfPages];

        /* prevent the number of rows or pages from being negative */
        if (numberOfPages < 0) {
            throw new IllegalArgumentException("numberOfPages cannot be negative");
        } else if (numberOfRows <= 0) {
            throw new IllegalArgumentException("numberOfRows must be a positive multiple of 9");
        }

        /* create pages and add them to the map */
        for (int i = 0; i < numberOfPages; i++) {

            /* the page number of the new page is the current size of the map plus 1 */
            int pageNumber = this.pages.size() + 1;

            /* create a page and put it in the map and array */
            Page page = new Page(pageNumber, numberOfRows, pagesName);
            this.pages.put(pageNumber, page);
            addedPages[i] = page;

            /* Number in ascending or descending order if needed */
            if (this.isAscending) {
                page.numberAscendingOrder();
            } else if (this.isDescending) {
                page.numberDescendingOrder();
            }
        }

        return addedPages;
    }

    /**
     * Returns all the pages in this SwiftGui
     *
     * @return a list of all the pages in this SwiftGui
     */
    public Page[] getAllPages() {

        Page[] allPages = new Page[this.pages.size()];
        
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            allPages[entry.getKey()] = entry.getValue();
        }

        return allPages;
    }

    /**
     * Adds a back button to all the pages in the default index which is the first slot of the last row.
     */
    public void addBackButtonAll() {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addBackButton();
        }
    }

    /**
     * Adds a back button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addBackButtonAll(int index) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addBackButton(index);
        }
    }

    /**
     * Adds a forward button to all the pages in the default index which is the last slot of the last row.
     */
    public void addForwardButtonAll() {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addForwardButton();
        }
    }

    /**
     * Adds a forward button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addForwardButtonAll(int index) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addForwardButton(index);
        }
    }

    /**
     * Adds a new page button to all the pages in the default index which is the sixth index of the last row.
     */
    public void addNewPageButtonAll() {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addNewPageButton();
        }
    }

    /**
     * Adds a new page button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addNewPageButtonAll(int index) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addNewPageButton(index);
        }
    }

    /**
     * Adds a delete page button to all the pages in the default index which is the fourth index of the last row.
     */
    public void addDeletePageButtonAll() {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addDeletePageButton();
        }
    }

    /**
     * Adds a delete page button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addDeletePageButtonAll(int index) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addDeletePageButton(index);
        }
    }

    /**
     * Adds a customButton to all the pages at the specified index.
     *
     * @param customButton the type of button to be added
     * @param index        the index in the page where the button should appear
     */
    public void addCustomButtonAll(CustomButton customButton, int index) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().addCustomButton(customButton, index);
        }
    }

    /**
     * Numbers all existing pages in ascending order.
     * Any pages added after this method is ran will also be added in ascending order.
     * A dash and number will be added after the page name.
     */
    public void numberAscendingOrderAll() {
        this.isAscendingOrder = true;
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().numberAscendingOrder();
        }
    }

    /**
     * Numbers all existing pages in descending order.
     * Any pages added after this method is ran will also be added in descending order.
     * A dash and number will be added after the page name.
     */
    public void numberDescendingOrderAll() {
        this.isDescending = true;
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().numberDescendingOrder();
        }
    }

    /**
     * Fills all pages with the specified material according to the border pattern
     *
     * @param borderPattern The pattern corresponding to which indexes should be filled.
     *                      1 means that index will be filled with the fillMaterial,
     *                      while 0 means that index will be left untouched.
     * @param fillMaterial  the material that will be used to fill the border
     */
    public void fillBorderAll(int[] borderPattern, Material fillMaterial) {
        for (Map.Entry<Integer, Page> entry : this.pages.entrySet()) {
            entry.getValue().fillBorder(borderPattern, fillMaterial);
        }
    }
}
