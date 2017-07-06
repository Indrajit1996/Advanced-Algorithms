/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rohanp
 */
import java.util.*;
public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        
        
        
        
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;
                t.occ=t.occ+1;
        }
        
        
    }
    
    public int getNum(String word)
    {
        TrieNode t = searchNode(word);
        if(t.isLeaf)
        {
            return t.occ;
        }
        else
        {
            return 0;
        }
    }
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
 
        if(t != null && t.isLeaf) 
            return true;
        else
            return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) 
            return false;
        else
            return true;
    }
 
    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
 
        return t;
    }
    public void disp2()
    {
        root = disp(root);
    }
    String s = "";
    public TrieNode disp(TrieNode t)
    {
        Map<Character,TrieNode> children = t.children;
       /* if(t.isLeaf)
        {
            System.out.println(s);
        }*/
        
        for(Map.Entry<Character, TrieNode> entry : children.entrySet()) {
    Character key = entry.getKey();
    TrieNode value = entry.getValue();
        s=s+key;
        System.out.println(key);
        return disp(value);
        
        
        }
        return root;
    }
    public static void main(String[] args)
    {
        Trie t = new Trie();
        String j = "";
        String s = new String("\"The Wolf and the Lamb \\n\" +\n" +
"\" \\n\" +\n" +
"\"WOLF, meeting with a Lamb astray from the fold, resolved not to\\n\" +\n" +
"\"lay violent hands on him, but to find some plea to justify to the\\n\" +\n" +
"\"Lamb the Wolf's right to eat him.  He thus addressed him:\\n\" +\n" +
"\"\\\"Sirrah, last year you grossly insulted me.\\\"  \\\"Indeed,\\\" bleated\\n\" +\n" +
"\"the Lamb in a mournful tone of voice, \\\"I was not then born.\\\"  Then\\n\" +\n" +
"\"said the Wolf, \\\"You feed in my pasture.\\\"  \\\"No, good sir,\\\" replied\\n\" +\n" +
"\"the Lamb, \\\"I have not yet tasted grass.\\\"  Again said the Wolf,\\n\" +\n" +
"\"\\\"You drink of my well.\\\"  \\\"No,\\\" exclaimed the Lamb, \\\"I never yet\\n\" +\n" +
"\"drank water, for as yet my mother's milk is both food and drink\\n\" +\n" +
"\"to me.\\\"  Upon which the Wolf seized him and ate him up, saying,\\n\" +\n" +
"\"\\\"Well! I won't remain supperless, even though you refute every\\n\" +\n" +
"\"one of my imputations.\\\"  The tyrant will always find a pretext for\\n\" +\n" +
"\"his tyranny.\\n\" +\n" +
"\"\\n\" +\n" +
"\"\\n\" +\n" +
"\"The Bat and the Weasels \\n\" +\n" +
"\" \\n\" +\n" +
"\"A BAT who fell upon the ground and was caught by a Weasel pleaded\\n\" +\n" +
"\"to be spared his life.  The Weasel refused, saying that he was by\\n\" +\n" +
"\"nature the enemy of all birds.  The Bat assured him that he was\\n\" +\n" +
"\"not a bird, but a mouse, and thus was set free.  Shortly\\n\" +\n" +
"\"afterwards the Bat again fell to the ground and was caught by\\n\" +\n" +
"\"another Weasel, whom he likewise entreated not to eat him.  The\\n\" +\n" +
"\"Weasel said that he had a special hostility to mice.  The Bat\\n\" +\n" +
"\"assured him that he was not a mouse, but a bat, and thus a second\\n\" +\n" +
"\"time escaped.  \\n\" +\n" +
"\" \\n\" + \"It is wise to turn circumstances to good account.  \"");
        for (int i=0;i<s.length();i++)
        {
            if (s.charAt(i)==' ')
            {
                
                t.insert(j);
                j="";
            }
            else
            {
            j = j+s.charAt(i);
          
            }
        }
        t.insert(j);
      // System.out.println(t.getNum("a"));
        t.disp2();
    }
}
