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
public class TrieNode {
    char c;
    int occ;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
    public TrieNode(char c,int occ)
    {
        this.c = c;
        this.occ= occ;
    }
    
}
