/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Sahan Siriwardhana
 */
import java.io.IOException;
import java.util.Scanner;

public class HashTable {

    private DataItem[] hashArray;

    private int arraySize;

    private DataItem bufItem; // for deleted items

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        bufItem = new DataItem(-1); // deleted item key is -1
    }

    //---------Methode to display table----------------------
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("# ");
            }
        }
        System.out.println("");
    }
    //==========================================================

    //------------hashFUnction--------------------------------
    public int hashFunction(int key) {
        return key % arraySize;
    }
    //========================================================

    //-----------Methode to insert---------------------------
    public void insert(DataItem item) {
        int key = item.getKey();
        int hashVal = hashFunction(key); // hash the key

        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {// until empty cell or -1,
            ++hashVal; // go to next cell
            hashVal %= arraySize; // wraparound if necessary
        }
        hashArray[hashVal] = item; // insert item
    }
    //===============================================================

    //------------Methode to delete----------------------------
    public DataItem delete(int key) {
        int hashVal = hashFunction(key);

        while (hashArray[hashVal] != null) // until empty cell,
        {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = bufItem; // delete item
                return temp;
            }
            ++hashVal; // go to next cell
            hashVal %= arraySize; // wraparound if necessary
        }
        return null; // can't find item
    }
    //========================================================

    //----------------Methode to search----------------------
    public DataItem find(int key) // find item with key
    {
        int hashVal = hashFunction(key); // hash the key

        while (hashArray[hashVal] != null) // until empty cell,
        {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal]; // found, return item
            }
            ++hashVal; // go to next cell
            hashVal %= arraySize; // wraparound if necessary
        }
        return null; // can't find item
    }
    //================================================

    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int aKey, size, initSize, keysPerCell;

        size = 12;
        initSize = 8;
        keysPerCell = 10;
        HashTable theHashTable = new HashTable(size);
        Scanner sc = new Scanner(System.in);
        // int a = sc.nextInt();

        System.out.println("Please enter values :");

        for (int j = 0; j < initSize; j++) {
            aKey = sc.nextInt();
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }

        theHashTable.displayTable();

        aDataItem = new DataItem(100);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        theHashTable.delete(100);
        theHashTable.displayTable();

        //------Search------------------
        int findnum = 38;
        aDataItem = theHashTable.find(findnum);
        if (aDataItem != null) {
            System.out.println("Found " + findnum);
        } else {
            System.out.println("Could not find ");
        }
        //---------------------------------
    }
}

class DataItem {

    private int data;

    public DataItem(int d) {// contructor of DataItem
        data = d;
    }

    public int getKey() {
        return data;
    }
}




   