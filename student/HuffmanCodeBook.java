package student;

import provided.BinarySequence;

/**
 * Max Locketz.
 * CSCI 1913
 */

public class HuffmanCodeBook {
    /**
     * BinarySequence array keys that stores the binary sequences that respond to characters.
     */
    private BinarySequence[] keys = new BinarySequence[5];
    /**
     * char array that stores characters in the codebook.
     */
    private char[] data = new char[5];
    /**
     * An int representing how much of the array is being use to store data.
     */
    private int len = 0;
    /**
     * Adds a given encoded character to the codebook.
     * If you try to add a character when the arrays are out of room, it doubles the size of the arrays.
     * After adding it, it sorts the new codebook.
     * @param c - character to add.
     * @param seq - BinarySequence that corresponds to the character.
     */
    public void addSequence(char c, BinarySequence seq) {
        if (len >= keys.length) {
            BinarySequence[] newKeys = new BinarySequence[len * 2];
            char[] newData = new char[len * 2];
            for (int i = 0; i < len; i++) {
                newKeys[i] = keys[i];
                newData[i] = data[i];
            }
            keys = newKeys;
            data = newData;
        }
        keys[len] = seq;
        data[len] = c;
        len++;
        sortBook();
    }

    /**
     * Uses binary search to determine if a given character is encoded in the codebook.
     * @param letter - the char to check if the book has it.
     * @return a boolean indicating if the codebook has the char
     */
    public boolean contains(char letter) {
        int high = len - 1;
        int low = 0;
        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (data[mid] < letter) {
                low = mid + 1;
            } else if (data[mid] > letter) {
                high = mid - 1;
            } else if (data[mid] == letter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a codebook contains all the characters in a String.
     * Loops through the characters of a String and runs the "contains" method on each of them.
     * @param letters - A string of characters to check for.
     * @return a boolean indicating if the codebook contains all the characters.
     */
    public boolean containsAll(String letters) {
        int count = 0;
        for (int i = 0; i < letters.length(); i++) {
            if (contains(letters.charAt(i))) {
                count++;
            }
        }
        return count >= letters.length();
    }

    /**
     * Uses binary search to get the corresponding binary sequence for a given character.
     * @param c - the character to return the sequence of.
     * @return - the binary sequence representing the character.
     */
    public BinarySequence getSequence(char c) {
        int high = len;
        int low = 0;
        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (data[mid] < c) {
                low = mid + 1;
            } else if (data[mid] > c) {
                high = mid - 1;
            } else if (data[mid] == c) {
                return keys[mid];
            }
        }
        return null;
    }

    /**
     * Combines the binary sequence code for specific characters and returns the full sequence.
     * @param s - the string to encode.
     * @return - a binary sequence representing the string "s"
     */
    public BinarySequence encode(String s) {
        BinarySequence seq = new BinarySequence();
        for (int i = 0; i < s.length(); i++) {
            seq.append(getSequence(s.charAt(i)));
        }
        return seq;
    }

    /**
     * Gets the length of the codebook.
     * @return int len
     */
    public int getLen() {
        return len;
    }

    /**
     * Gets the binary sequence at a specific index.
     * @param index to search for
     * @return binary sequence.
     */
    public BinarySequence getKey(int index) {
        return keys[index];
    }

    /**
     * Gets the character at a specific index.
     * @param index to search for
     * @return char.
     */
    public char getData(int index) {
        return data[index];
    }

    /**
     * Sorts the codebook by character in the Data array using insertion sort.
     */
    private void sortBook() {
        for (int i = 1; i < len; i++) {
            char letter = data[i];
            BinarySequence key = keys[i];
            int j = i - 1;
            while (j >= 0 && data[j] > letter) {
                data[j + 1] = data[j];
                keys[j + 1] = keys[j];
                j = j - 1;
            }
            data[j + 1] = letter;
            keys[j + 1] = key;
        }
    }
}
