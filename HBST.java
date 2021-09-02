//Name: Alexis Yi
//UMN ID: yixxx258

//Test code in separate file titled 'Project2Test.java'

//Test results included in comments below the code

import java.util.Random;

class HBST<Key, Value>
{
    private class PairNode
    {
        private Key key;
        private Value value;
        private PairNode next;

        private PairNode(Key key, Value value)
        {
            this.key = key;
            this.value = value;
            next = null; //next will just point to null in the beginning
        }
    }
    private class TreeNode
    {
        private int hash;
        private PairNode pairs;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int hash)
        {   //create a TreeNode that contains no key-value pairs yet
            //to add a key-value pair, need to use separate put method
            this.hash = hash;
            this.pairs = new PairNode(null, null); //create dummy head node immediately, to reduce cases
            left = null;
            right = null;
        }
    }

    private TreeNode root;
    private Random generator;

    public HBST()
    {
        generator = new Random(); //create the instance of Random for the hash method
        root = new TreeNode(-1); //root hash is -1
        //all keys have nonnegative hashes per our hash method, so we set root's hash to -1
    }

    public void put(Key key, Value value)
    {
        int hash = hash(key); //create the hash from the key
        TreeNode where = root; //pointer to TreeNode we're looking at
        while (true)
        {   //stay in this loop so long as we have not placed the key-value pair yet
            if (hash < where.hash)
            {   //case 1: hash is less than hash of the TreeNode we're looking at, so we go to the left
                if(where.left == null)
                {   //case 1a: if where.left is null, we can place our new key-value pair there
                    where.left = new TreeNode(hash); //create new TreeNode first
                    where.left.pairs.next = new PairNode(key, value);//put our new key-value pair in the new TreeNode
                    return; //exit method
                }
                else
                {   //case 1b: where.left points to another TreeNode, so we move to that next node and continue the loop
                    where = where.left;
                }
            }
            else if (hash > where.hash)
            {   //case 2: hash is greater than the hash of the TreeNode we're looking at, so we go to the right
                if(where.right == null)
                {   //case 2a: where.right points to null, so we can add our new pair here
                    where.right = new TreeNode(hash); //create new TreeNode first
                    where.right.pairs.next = new PairNode(key, value); //put new pair in the new TreeNode
                    return; //exit method
                }
                else
                {   //case 2b: where.right points to another TreeNode, so we move to that next node and continue the loop
                    where = where.right;
                }
            }
            else
            {   //case 3: hash equals where.hash, so we enter our pair in the TreeNode we're looking at
                PairNode left = where.pairs; //pointer to previous PairNode
                PairNode right = where.pairs.next; //pointer to PairNode we're looking at
                while (right!=null)
                {
                    if (isEqual(key, right.key))
                    {   //found the matching key
                        right.value = value; //reset the value
                        return; //exit method
                    }
                    else
                    {
                        left = right;
                        right = right.next;
                    }
                }
                //will only get here if we've gone through the list and not found the key
                left.next = new PairNode(key, value);
            }
        }
    }

    public Value get(Key key)
    {
        int hash = hash(key); //create the hash
        TreeNode where = root; //pointer to TreeNode we're looking at
        while(where!= null)
        {   //do this loop so long as the TreeNode we're looking at is not null
            if (hash < where.hash)
            {   //case 1: hash is less than the hash of the TreeNode we're looking at, so we go to the left
                where = where.left;
            }
            else if (hash > where.hash)
            {   //case 2: hash is greater than the hash of the TreeNode we're looking at, so we go to the right
                where = where.right;
            }
            else
            {   //case 3: hash is equal to hash of the TreeNode we're looking at, so we try to find the key in this TreeNode
                PairNode place = where.pairs.next; //pairs is dummy PairNode so we skip it
                while (place!=null)
                {
                    if (isEqual(key, place.key))
                    {   //found the matching key
                        return place.value;
                    }
                    else
                    {
                        place = place.next;
                    }
                }
                //will only get here if the hash exists, but the specific key does not
                throw new IllegalArgumentException("Hash exists but key doesn't");
            }
        }
        //will only get here if the hash is not found
        throw new IllegalArgumentException("No such key."); //error message for when the hash is not found in the HBST

    }

    public int height()
    {   //public method that can be called
        //root is a dummy node so we don't count it; all other nodes will be to the right of root
        //use private height method created outside of this so we can do recursion
        return height(root.right);
    }
    private int height(TreeNode subtree)
    {   //private height method for recursion
        if (subtree == null)
        {   //base case:subtree is null, in which case it has 0 height
            return 0;
        }
        else
        {   //recursion case (most common case): subtree is not null, has positive height
            int left = height(subtree.left) + 1; //add 1 for the TreeNode we start at
            int right = height(subtree.right) + 1;
            //will use the height of whichever subtree (right or left) has highest height
            if (left > right)
            {
                return left;
            }
            else
            {
                return right;
            }
        }
    }
    private int hash (Key key)
    {
        if (key == null)
        {   //case 1:key is null, in which case hash(key) is 0
            return 0;
        }
        else
        {   //case 2 (most common case): key is not null
            generator.setSeed(key.hashCode()); //set the generator seed using built-in hashCode
            return Math.abs(generator.nextInt()); //use absolute value to ensure hash is always nonnegative
        }
    }
    public boolean isEmpty()
    {   //root is dummy TreeNode
        //the HBST will be empty if root.right points to nothing, because anything added must go to the right of root
        //see TreeNode constructor and HBST constructor
        return root.right == null;
    }
    private boolean isEqual(Key left, Key right)
    {
        if (left == null || right == null)
        {   //case 1: either right or left is null, in which case we use ==
            return left == right;
        }
        else
        {   //case 2: neither right nor left is null, in which case we use built-in equals() method
            return left.equals(right);
        }
    }
}

//Results from test (not including this line):

//true
//0
//8
//-10 100
// -9  81
// -8  64
// -7  49
// -6  36
// -5  25
// -4  16
// -3   9
// -2   4
// -1   1
//  0   0
//  1   1
//  2   4
//  3   9
//  4  16
//  5  25
//  6  36
//  7  49
//  8  64
//  9  81
// 10 100
//false
//No such key exists.
//100
//44
//
//true
//0
//11
//00 abstract
//01 assert
//02 boolean
//03 break
//04 byte
//05 case
//06 catch
//07 char
//08 class
//09 const
//10 continue
//11 default
//12 do
//13 double
//14 else
//15 extends
//16 false
//17 final
//18 finally
//19 float
//20 for
//21 goto
//22 if
//23 implements
//24 import
//25 instanceof
//26 int
//27 interface
//28 long
//29 native
//30 new
//31 null
//32 package
//33 private
//34 protected
//35 public
//36 return
//37 short
//38 static
//39 super
//40 switch
//41 synchronized
//42 this
//43 throw
//44 throws
//45 transient
//46 true
//47 try
//48 var
//49 void
//50 volatile
//51 while
//false
//No such key exists.
//41
//81
