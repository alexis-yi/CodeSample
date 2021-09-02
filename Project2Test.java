//Name: Alexis Yi
//UMN ID: yixxx258
//Test class based off of code from project handout
//there is code added to test isEmpty method, and more parts of the get, put and height methods
//comments are attached to each piece of added code to explain it

class CornedBeefHash
{
    private final static String[] reserved =
            { "abstract",     "assert",       "boolean",     "break",
                    "byte",         "case",         "catch",       "char",
                    "class",        "const",        "continue",    "default",
                    "do",           "double",       "else",        "extends",
                    "false",        "final",        "finally",     "float",
                    "for",          "goto",         "if",          "implements",
                    "import",       "instanceof",   "int",         "interface",
                    "long",         "native",       "new",         "null",
                    "package",      "private",      "protected",   "public",
                    "return",       "short",        "static",      "super",
                    "switch",       "synchronized", "this",        "throw",
                    "throws",       "transient",    "true",        "try",
                    "var",          "void",         "volatile",    "while" };

    public static void main(String [] args)
    {
        HBST<Integer, Integer> intToInt = new HBST<Integer, Integer>();
        System.out.println(intToInt.isEmpty()); //test isEmpty() method, should print true
        System.out.println(intToInt.height()); //test height() method on empty HBST, should print 0
        for (int key = -10; key <= 10; key += 1)
        {
            intToInt.put(key, key * key);
        }
        System.out.println(intToInt.height());
        for (int key = -10; key <= 10; key += 1)
        {
            System.out.format("%3d %3d", key, intToInt.get(key));
            System.out.println();
        }
        System.out.println(intToInt.isEmpty()); //test the isEmpty() method, should print false
        //try-catch block to test error message in get method, should print No such key exists.
        try
        {
            intToInt.get(-11);
        }
        catch (IllegalArgumentException nokey)
        {
            System.out.println("No such key exists.");
        }
        System.out.println(intToInt.get(-10)); //should print 100
        intToInt.put(-10, 44);  //test put method to see if it replaces properly
        System.out.println(intToInt.get(-10)); //should print 44
        System.out.println();

        HBST<String, Integer> stringToInt = new HBST<String, Integer>();
        System.out.println(stringToInt.isEmpty()); //test the isEmpty() method, should print true
        System.out.println(stringToInt.height()); //test height() method when HBST is empty, should print 0
        for (int index = 0; index < reserved.length; index += 1)
        {
            stringToInt.put(reserved[index], index);
        }
        System.out.println(stringToInt.height());
        for (int index = 0; index < reserved.length; index += 1)
        {
            String name = reserved[index];
            System.out.format("%02d %s", stringToInt.get(name), name);
            System.out.println();
        }
        System.out.println(stringToInt.isEmpty()); //added to test the isEmpty() method, should print false
        //try-catch block added to test error message in get method, should print No such key exists.
        try
        {
            stringToInt.get("hello");
        }
        catch (IllegalArgumentException nokey)
        {
            System.out.println("No such key exists.");
        }
        System.out.println(stringToInt.get("synchronized")); //should print 41
        stringToInt.put("synchronized", 81); //test put method to see if it replaces properly
        System.out.println(stringToInt.get("synchronized")); //should print 81
        System.out.println();
    }
}
