package org.bigdatacenter.naver_crawling;

/**
 * Created by HP1 on 5/23/2017.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// Trie Node, which stores a character and the children in a HashMap
class TriesNode {
    public TriesNode(char ch)  {
        value = ch;
        children = new HashMap<>();
        bIsEnd = false;
    }
    public HashMap<Character,TriesNode> getChildren() {   return children;  }
    public char getValue()                           {   return value;     }
    public void setIsEnd(boolean val)                {   bIsEnd = val;     }
    public boolean isEnd()                           {   return bIsEnd;    }

    private char value;
    private HashMap<Character,TriesNode> children;
    private boolean bIsEnd;
}

// Implements the actual Trie
class Trie {
    // Constructor
    public Trie()   {     root = new TriesNode((char)0);       }

    // Method to insert a new word to Trie
    public void insert(String word)  {

        // Find length of the given word
        int length = word.length();
        TriesNode crawl = root;

        // Traverse through all characters of given word
        for( int level = 0; level < length; level++)
        {
            HashMap<Character,TriesNode> child = crawl.getChildren();
            char ch = word.charAt(level);

            // If there is already a child for current character of given word
            if( child.containsKey(ch))
                crawl = child.get(ch);
            else   // Else create a child
            {
                TriesNode temp = new TriesNode(ch);
                child.put( ch, temp );
                crawl = temp;
            }
        }

        // Set bIsEnd true for last character
        crawl.setIsEnd(true);
    }

    // The main method that finds out the longest string 'input'
    public String getMatchingPrefix(String input)  {
        String result = ""; // Initialize resultant string
        int length = input.length();  // Find length of the input string

        // Initialize reference to traverse through Trie
        TriesNode crawl = root;

        // Iterate through all characters of input string 'str' and traverse
        // down the Trie
        int level, prevMatch = 0;
        for( level = 0 ; level < length; level++ )
        {
            // Find current character of str
            char ch = input.charAt(level);
            System.out.println(level + " ==> " + ch);

            // HashMap of current Trie node to traverse down
            HashMap<Character,TriesNode> child = crawl.getChildren();

            // See if there is a Trie edge for the current character
            if( child.containsKey(ch) )
            {
                result += ch;          //Update result
                crawl = child.get(ch); //Update crawl to move down in Trie


                // If this is end of a word, then update prevMatch
                if( crawl.isEnd() )
                    prevMatch = level + 1;
            }
            else  break;
        }

        // If the last processed character did not match end of a word,
        // return the previously matching prefix
        if( !crawl.isEnd() ) {
            System.out.println("IF");
            return result.substring(0, prevMatch);
        }
        else{
            System.out.println("ELSE");
            return result;
        }
    }

    private TriesNode root;
}

// Testing class
public class Test {
    public static void main(String[] args) {
        Trie dict = new Trie();
        try(BufferedReader br = new BufferedReader(new FileReader("OUTPUT.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                try {
                    String str = line.split(" ")[0];
                    if(!str.equals("#")){
                        dict.insert(str);
                    }
                }catch(Exception ex){}
            }
            String everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String input = "បើសិនជាបានដឹងរឿងពិតនោះ គាត់សក្តិសមនឹងជាប់ទោសលើសពីមួយជិវិតទៅទៀត។ គាត់សាហាវណាស់";
        while(true) {
            System.out.print(input + ":   ");
            String strMatch = dict.getMatchingPrefix(input);
            if(!strMatch.equals("")){
                System.out.println(strMatch);
                input = input.substring(strMatch.length(), input.length());
            }else{
                break;
            }
        }

        input = "កងពល";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "are";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "arex";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "basemexz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));

        input = "xyz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));
    }
}
