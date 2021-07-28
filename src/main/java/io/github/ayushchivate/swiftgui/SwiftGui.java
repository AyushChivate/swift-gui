package io.github.ayushchivate.swiftgui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A container class that holds multiple pages.
 */
public class SwiftGui {

    /**
     * A map of pages and their page number in this SwiftGui
     */
    private Map<Integer, Page> pages;
    private static JavaPlugin plugin;

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

    public static void setPluginInstance(JavaPlugin plugin) {
        SwiftGui.plugin = plugin;
    }

    public static JavaPlugin getPluginInstance() {
        return plugin;
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

        if (numberOfRows <= 0) {
            throw new IllegalArgumentException("numberOfRows must be a positive multiple of 9");
        }

        int pageNumber = this.pages.size() + 1;
        Page page = new Page(pageNumber, numberOfRows, pageName);
        this.pages.put(pageNumber, page);
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
            int pageNumber = this.pages.size() + 1;
            Page page = new Page(pageNumber, numberOfRows, pagesName);
            this.pages.put(pageNumber, page);
            addedPages[i] = page;
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
     * Adds a back button to all the pages in the default index which is the first index of the last row.
     */
    public void addBackButtonAll() {
        /* have a isConsistent field in page to see if all inventories have the same amount of rows, only then can you call this method without an index. */
        addBackButtonAll();
    }

    /**
     * Adds a back button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addBackButtonAll(int index) {
        BackButton backButton = new BackButton(index, Material.ARROW);
        /*add button to pages*/
    }

    /**
     * Adds a forward button to all the pages in the default index which is the first index of the last row.
     */
    public void addForwardButtonAll() {
    }

    /**
     * Adds a forward button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addForwardButtonAll(int index) {
    }

    /**
     * Adds a new page button to all the pages in the default index which is the first index of the last row.
     */
    public void addNewPageButtonAll() {
    }

    /**
     * Adds a new page button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addNewPageButtonAll(int index) {
    }

    /**
     * Adds a delete page button to all the pages in the default index which is the first index of the last row.
     */
    public void addDeletePageButtonAll() {
    }

    /**
     * Adds a delete page button to all the pages at the specified index.
     *
     * @param index the index in the page where the button should appear
     */
    public void addDeletePageButtonAll(int index) {
    }

    /**
     * Adds a customButton to all the pages at the specified index.
     *
     * @param customButton the type of button to be added
     * @param index        the index in the page where the button should appear
     */
    public void addCustomButtonAll(CustomButton customButton, int index) {
    }

    /**
     * Numbers all existing pages in ascending order.
     * Any pages added after this method is ran will also be added in ascending order.
     * A dash and number will be added after the page name.
     */
    public void numberAscendingOrderAll() {
    }

    /**
     * Numbers all existing pages in descending order.
     * Any pages added after this method is ran will also be added in descending order.
     * A dash and number will be added after the page name.
     */
    public void numberDescendingOrderAll() {
    }

    /**
     * All the pages will be filled with the specified material according to the border pattern
     *
     * @param borderPattern The pattern corresponding to which indexes should be filled.
     *                      1 means that index will be filled with the fillMaterial,
     *                      while 0 means that index will be left untouched.
     * @param fillMaterial  the material that will be used to fill the border
     */
    public void fillBorderAll(int[] borderPattern, Material fillMaterial) {
    }

    public void open(Player player) {
        Page page = this.pages.get(1);
        page.openInventory(player);
    }


    /* TODO: Add page persistence when opening up a page*/

}
