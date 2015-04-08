package Files;

import java.lang.reflect.Array;
import java.util.Queue;

public class BinarySearchTree <Key extends Comparable<Key> ,Value> {

    private Key key;
    private Value value;
    private int size;

    private BinarySearchTree<Key, Value> left;
    private BinarySearchTree<Key, Value> right;

    public BinarySearchTree(){
        //I put these here just to explicitly tell a user that there null
        left = null;
        right = null;
        size = 0;
    }

    public void put(Key k, Value v){
        if(key == null){
            key = k;
            value = v;
            size = 1;
            return;
        }
        if(key.compareTo(k)==0){
            //if the key is the same thing replace the value with the new value
            value = v;
            return;
        }
        if(key.compareTo(k) > 0) {
            if (left == null) left = new BinarySearchTree();
            left.put(k, v);
        }else {
            if (right == null) right = new BinarySearchTree();
            right.put(k, v);
        }

        size = ((left==null)? 0:left.getSize()) + 1 + ((right==null)? 0:right.getSize());
    }//end function


    public Value getValue(){ return this.value; }
    public Value get(){ return value;}
    public Value get(Key k){
        BinarySearchTree<Key, Value> a = getNode(k);
        if(a == null) return null;
        return a.getValue();
    }

    public BinarySearchTree getNode(Key k){
        //this function returns the node with the given key
        if(key.compareTo(k) > 0){
            if(left == null) return null;
            return left.getNode(k);
        }
        if(key.compareTo(k) < 0){
            if(right == null) return null;
            return right.getNode(k);
        }
        return this;
    }

    public Key getKey(){ return key; }

    private void keys(Queue q, Key low, Key high){
        if(key == null) return ; // if the key is null do nothing

        if(left != null)//make sure the left branch exists
            if(key.compareTo(low) >= 0){ // go left if key >= low
                left.keys(q, low, high);// find all the keys on the left branch
            }

        //if the current key is within the range then add it to the iterator object
        if(key.compareTo(high) <= 0 && key.compareTo(low) >= 0) q.add(value);

        if(right != null)//make sure the right branch exists
            if(key.compareTo(high) <= 0){//check if the high value is larger then the key
                right.keys(q, low, high);//find all the keys in the right branch
            }
        return;
    }//end keys

    public Value[] keysAsArray(Class<Value> valueClass, Key low, Key high){
        Value[] valuesArray = (Value[]) Array.newInstance(valueClass, getSize(low, high));
        keysAsArray(valuesArray, low, high, 0);
        return valuesArray;
    }
    public int keysAsArray(Value[] arr, Key low, Key high, int index){
        if(key == null) return index;

        if(left != null && key.compareTo(low) >= 0) index = left.keysAsArray(arr, low, high, index);
        if(key.compareTo(high) <= 0 && key.compareTo(low) >= 0) { arr[index] = value; index++; }
        if(right != null && key.compareTo(high) <= 0) index = right.keysAsArray(arr, low, high, index);
        return index;
    }

    public int getSize(){ return size; }
    public int getSize(Key low, Key high){
        int totalSize = 0;
        if(key == null) return 0;

        if(left != null)//make sure the left branch exists
            if(key.compareTo(low) >= 0){ // go left if key >= low
                //add all the lows
                totalSize += left.getSize(low, high);
            }

        //if the current key is within the range then add it to the iterator object
        if(key.compareTo(high) <= 0 && key.compareTo(low) >= 0) totalSize = totalSize+1;

        if(right != null)//make sure the right branch exists
            if(key.compareTo(high) <= 0){//check if the high value is larger then the key
                //right.keys(q, low, high);//find all the keys in the right branch
                totalSize += right.getSize(low, high);
            }

        return totalSize;
    }

    //these two functions were made to look at the tree in a more effective manor
    @Override
    public String toString(){ return toString(0); } // the default toString method will call the second one
    public String toString(int recursDepth){
        if(key == null) return ""; // if the key is empty then return an empty string
        String base =  "data: ("+ key.toString()+","+ value+" size: "+size+")" ; // this is the base of the data
        if(left == null && right == null)  return base; //return the base if the current node does not have any branches

        //if the left branch does not exist then print the base then in a new line print a series of spaces depending on the recursive depth
        //and then print right branch
        if(left == null) return base + "\n" +
            tabs(recursDepth+1) + "" + "bigger branch: " + right.toString(recursDepth + 1) + " ";

        //if the right side branch does not exist then do the same thing as above but with the right
        if(right == null)
            return base + "\n"+
            tabs(recursDepth+1) + "" + "smaller branch: " + left.toString(recursDepth+1) + " ";

        //do the same thing as above except on both branches
        return base + "\n" +
                tabs(recursDepth+1) + "bigger branch: " + right.toString(recursDepth+1) + "\n" +
                tabs(recursDepth+1) + "smaller branch: " + left.toString(recursDepth+1) + "";
    }
    public static String tabs(int number){
        String s = " "; // starts with tabbing the first branch by 2 spaces
        while (number > 0){ // returns the number of spaces being equal to number*2
            s += "  ";
            number--;
        }
        return s;//returns the string
    }

    public Value getMin(){
        if(left != null) return left.getMin();
        return value;
    }
    public BinarySearchTree getMinNode(){
        if(left != null) return left.getMinNode();
        return this;
    }
    public void removeMin(){
        if(left == null){
            if(right == null){ size = 0; key = null; value = null; return;}
            key = right.key;
            value = right.value;
            size = right.size;

            left = right.left;
            right = right.right;
            return;
        }

        if(left.left != null) { left.removeMin(); return;}//the left branch doesnt have a left branch
        if(left.right == null){ left = null; return; }//the left branch was a leaf node
        left = left.right;
    }

    public static void main(String[] args){
       // System.out.println("--" + tabs(0) + "--");

        //test one
        BinarySearchTree<String, Integer> test = new BinarySearchTree<String, Integer>();
        if(!test.toString().equals("")) System.out.println("Test one has failed");

        //test two
        test = new BinarySearchTree<String, Integer>();
        test.put("1", 10);
        //System.out.println("test: " + test.toString());
        //if(!test.toString().equals("(k: 1,10)")) System.out.println("Test two has failed");
        //if(!test.toString().equals("1,"))

        //test three
        test = new BinarySearchTree<String, Integer>();
        test.put("b", 10);
        test.put("a", 10);
        test.put("c", 10);
        //System.out.println(test.toString());

        //test four
        BinarySearchTree<Integer, Integer> test2 = new BinarySearchTree<Integer, Integer>();
        test2.put(10, 1);
        test2.put(5,  2);
        test2.put(15, 3);
        test2.put(20, 4);
        test2.put(1,  5);
        System.out.println(test2.toString());

        System.out.println("key: 10   " + test2.get(10));
        System.out.println("key: 5    " + test2.get(5));
        System.out.println("key: 15   " + test2.get(15));
        System.out.println("key: 20   " + test2.get(20));
        System.out.println("key: 1    " + test2.get(1));
    }

}//end class
