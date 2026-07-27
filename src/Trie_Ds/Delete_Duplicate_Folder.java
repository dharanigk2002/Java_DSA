package Trie_Ds;

/*
Delete Duplicate Folders in System

Due to a bug, there are many duplicate folders in a file system. You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.

For example, ["one", "two", "three"] represents the path "/one/two/three".
Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.

For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
/a
/a/x
/a/x/y
/a/z
/b
/b/x
/b/x/y
/b/z
However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.

Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.

https://leetcode.com/problems/delete-duplicate-folders-in-system/description/
*/

import java.util.*;

class Node {
    String name;
    String signature;
    boolean delete;

    final Map<String, Node> children;

    public Node(String name) {
        this.name=name;
        signature="";
        delete=false;
        children=new HashMap();
    }
}

public class Delete_Duplicate_Folder {
    private Node root;
    private Map<String, Integer> freq;
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        root=new Node("/");
        freq=new HashMap();
        for(List<String> nodes:paths)
            insert(nodes);
        generateSignature(root);
        markDuplicates(root);
        List<List<String>> list=new ArrayList();
        print(root, list, new ArrayList());
        return list;
    }

    private void print(Node node, List<List<String>> list, List<String> ds) {
        for(Node child:node.children.values()) {
            if(child.delete) continue;
            ds.add(child.name);
            list.add(new ArrayList<>(ds));
            print(child, list, ds);
            ds.remove(ds.size()-1);
        }
    }

    private void insert(List<String> nodes) {
        Node temp=root;
        for(String node:nodes)
            temp=temp.children.computeIfAbsent(node, k->new Node(node));
    }

    private String generateSignature(Node node) {
        if(node.children.isEmpty())
            return node.signature;
        List<String> nodes=new ArrayList<>(node.children.keySet());
        Collections.sort(nodes);
        StringBuilder sb=new StringBuilder();
        for(String child:nodes) {
            Node childNode=node.children.get(child);
            sb.append("(").append(child).append(generateSignature(childNode)).append(")");
        }
        String sign=sb.toString();
        node.signature=sign;
        if(node!=root)
            freq.merge(sign, 1, Integer::sum);
        return sign;
    }

    private void markDuplicates(Node node) {
        List<String> list=new ArrayList(node.children.keySet());
        Collections.sort(list);
        for(String s:list) {
            Node child=node.children.get(s);
            if(freq.getOrDefault(child.signature, 0)>1)
                child.delete=true;
            else
                markDuplicates(child);
        }
    }

    static void main() {
        // paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
        List<List<String>> paths = List.of(
                List.of("a"),
                List.of("c"),
                List.of("a", "b"),
                List.of("c", "b"),
                List.of("a", "b", "x"),
                List.of("a", "b", "x", "y"),
                List.of("w"),
                List.of("w", "y")
                );
        var deleteFolder=new Delete_Duplicate_Folder();
        IO.println(deleteFolder.deleteDuplicateFolder(paths));
    }
}
