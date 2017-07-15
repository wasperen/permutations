Some time ago I needed to iterate through permutations and combinations of certain elements. Quickly I found [this Wikipedia description](http://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm#Even.27s_speedup) of a clever algorithm to generate all swaps that are required to iterate through all possible permutations.

So I created two Iterators: <code>PermutationsSwapIterator</code> and <code>PermutationsIterator</code>. The first one generates a sequence of <code>Swap</code>s that you need to conduct to walk through all permutations of your List. The second one just returns all permutations of the elements in your list.

Example of use:

```java
public void testPermutationSwapIterator() {
    List<String> strings = new LinkedList<String>();
    strings.add("aap");
    strings.add("noot");
    strings.add("mies");
    Iterator<PermutationsSwapIterator.Swap<String>> iter =
        new PermutationsSwapIterator<String>(strings);
		
    System.out.println(strings);
    while (iter.hasNext()) {
        Swap<String> swap = iter.next();
        System.out.println(swap.getLeft()+" <=> "+swap.getRight());
    }
}
```

This will output:

     [aap, noot, mies]
     mies <=> noot
     mies <=> aap
     noot <=> aap
     mies <=> noot
     mies <=> aap

As you see, these are all the swaps that you need to do to derive all possible permutations of the list of strings.

If you are interested in these permutations themselves, you can use the <code>PermutationIterator</code>, like so:

```java
public void testPermutationIterator() {
    List<String> strings = new LinkedList<String>();
    strings.add("aap");
    strings.add("noot");
    strings.add("mies");
    Iterator<List<String>> iter = 
            new PermutationsIterator<String>(strings);
		
    while (iter.hasNext()) {
        List<String> permutation = iter.next();
        System.out.println(permutation);
    }
}
```

Which will output:

     [aap, noot, mies]
     [aap, mies, noot]
     [noot, mies, aap]
     [mies, noot, aap]
     [mies, aap, noot]
     [noot, aap, mies]

Essentially this is your ```List``` with the ```Swap```s from the ```PermutationsSwapIterator``` applied to it.
