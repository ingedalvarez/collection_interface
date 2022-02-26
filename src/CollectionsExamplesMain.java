import java.util.*;
import java.util.stream.Collectors;


public class CollectionsExamplesMain {
    public static void main(String[] args) {
        /*A Collection represents a group of objects known as its elements. The Collection interface is used to pass
        around collections of objects where maximum generality is desired. For example, by convention all
        general-purpose collection implementations have a constructor that takes a Collection argument.
        */
        Collection<Integer> c = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> cList = new ArrayList<>(c); //List take a collection c and build a List
        System.out.println(" c contains 3: " + c.contains(3));
        System.out.println(" cList contains 3: " + cList.contains(3));
        System.out.format(" c is empty: %b%n c size: %d%n c is iqual to cList:  %b%n c elements: %s%n", c.isEmpty(), c.size(), c.equals(cList), Arrays.toString(c.toArray()));

        /*
        Traversing Collections
        There are three ways to traverse collections: (1) using aggregate operations (2) with the for-each construct
        and (3) by using Iterators.
        Aggregate Operations

        In JDK 8 and later, the preferred method of iterating over a collection is to obtain a stream and perform
        aggregate operations on it.
        */

        System.out.printf("%n%s: ", "Stream");
        c.stream().filter(e -> e % 2 == 0).forEach(e -> System.out.format("%d - ", e));

        /*
         * Likewise, you could easily request a parallel stream, which might make sense if the collection is large enough
         * and your computer has enough cores: */
        System.out.printf("%n%s: ", "Parallel Stream");
        c.parallelStream().filter(e -> e % 2 == 0).forEach(e -> System.out.format("%d - ", e));

        String joined = c.stream().map(Object::toString).collect(Collectors.joining(", "));
        System.out.printf("%n%s %s%n", "Integer to String:", joined);

        Integer total = c.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("total: " + total);

        /*
         * The Collections framework has always provided a number of so-called "bulk operations" as part of its API.
         * These include methods that operate on entire collections, such as containsAll, addAll, removeAll, etc.
         * bulk operations (containsAll, addAll, etc.) is that the old versions are all mutative, meaning that they
         * all modify the underlying collection. In contrast, the new aggregate operations
         * do not modify the underlying collection.
         * */
        Collection<Integer> d = Arrays.asList(13, 14, 15, 16);
        System.out.println("Collection cList: " + cList);
        System.out.println("Collection d: " + d);
        cList.addAll(d);
        System.out.println("Collection cList after addAll d: " + cList);

        /*for-each Construct
         * The for-each construct allows you to concisely traverse a collection or array using a for loop
         * */
        System.out.println("Elements in cList traverse using for loop:");
        for (Integer i : cList) {
            System.out.format("%d - ", i);
        }

        /*
          Iterators
          An Iterator is an object that enables you to traverse through a collection and to remove elements from
          the collection selectively, if desired. You get an Iterator for a collection by calling its iterator method.
          The following is the Iterator interface.

                public interface Iterator<E> {
                boolean hasNext();
                E next();
                void remove(); //optional
                }

          The remove method removes the last element that was returned by next from the underlying Collection.
          The remove method may be called only once per call to next and throws an exception if this rule is violated.

          Note that Iterator.remove is the only safe way to modify a collection during iteration;
          the behavior is unspecified if the underlying collection is modified in any other way while
          the iteration is in progress.

          Use Iterator instead of the for-each construct when you need to:

          Remove the current element. The for-each construct hides the iterator, so you cannot call remove.
          Therefore, the for-each construct is not usable for filtering.
          Iterate over multiple collections in parallel.

        */


        boolean remove16 = cList.removeAll(Collections.singleton(16));
        System.out.println("\nremove 16 successful: " + remove16);
        System.out.println("Elements cList: " + cList);
        Collection<Integer> rem = Arrays.asList(9, 10, 11);
        System.out.println("Collection rem: " + rem);
        boolean removeRem = cList.removeAll(rem);
        System.out.println("remove all rem succesfull: " + removeRem);
        System.out.println("Elements cList: " + cList);

        filter(cList);
        System.out.println("Elements of cList after filter method: ");
        System.out.println(cList);

       /*
       * Collection Interface Array Operations

        * The toArray methods are provided as a bridge between collections and older APIs that expect arrays on input.
        * The array operations allow the contents of a Collection to be translated into an array. The simple form with
        * no arguments creates a new array of Object. The more complex form allows the caller to provide an array or
        * to choose the runtime type of the output array.
       * */
        Object[] arrayObj = c.toArray();

        for(int i = 0; i<arrayObj.length; i++){
            System.out.format("%d - ",arrayObj[i]);
        }

        Integer[] arrayInt = c.toArray(new Integer[0]);
        System.out.println();

        for(int i = 0; i<arrayInt.length; i++){
            System.out.format("%d - ",arrayInt[i]);
        }
        System.out.println();
        System.out.println("Elements of c: "+c);
        System.out.println("size of c: "+c.size());


    }

    static <T extends Integer> void filter(Collection<T> c) {
        for (Iterator<T> it = c.iterator(); it.hasNext(); ) {
            Integer n = it.next();
            if (!(n % 2 == 0)) it.remove();
        }


    }
}
