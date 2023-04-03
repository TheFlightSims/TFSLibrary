/*      */ package com.google.common.collect;
/*      */ 
/*      */ import com.google.common.annotations.Beta;
/*      */ import com.google.common.annotations.GwtCompatible;
/*      */ import com.google.common.annotations.GwtIncompatible;
/*      */ import com.google.common.annotations.VisibleForTesting;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Objects;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.math.IntMath;
/*      */ import com.google.common.primitives.Ints;
/*      */ import java.io.Serializable;
/*      */ import java.math.RoundingMode;
/*      */ import java.util.AbstractList;
/*      */ import java.util.AbstractSequentialList;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.RandomAccess;
/*      */ import java.util.concurrent.CopyOnWriteArrayList;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ @GwtCompatible(emulated = true)
/*      */ public final class Lists {
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayList() {
/*   84 */     return new ArrayList<E>();
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayList(E... elements) {
/*  100 */     Preconditions.checkNotNull(elements);
/*  102 */     int capacity = computeArrayListCapacity(elements.length);
/*  103 */     ArrayList<E> list = new ArrayList<E>(capacity);
/*  104 */     Collections.addAll(list, elements);
/*  105 */     return list;
/*      */   }
/*      */   
/*      */   @VisibleForTesting
/*      */   static int computeArrayListCapacity(int arraySize) {
/*  109 */     CollectPreconditions.checkNonnegative(arraySize, "arraySize");
/*  112 */     return Ints.saturatedCast(5L + arraySize + (arraySize / 10));
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
/*  127 */     Preconditions.checkNotNull(elements);
/*  129 */     return (elements instanceof Collection) ? new ArrayList<E>(Collections2.cast(elements)) : newArrayList(elements.iterator());
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
/*  146 */     ArrayList<E> list = newArrayList();
/*  147 */     Iterators.addAll(list, elements);
/*  148 */     return list;
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
/*  174 */     CollectPreconditions.checkNonnegative(initialArraySize, "initialArraySize");
/*  175 */     return new ArrayList<E>(initialArraySize);
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> ArrayList<E> newArrayListWithExpectedSize(int estimatedSize) {
/*  196 */     return new ArrayList<E>(computeArrayListCapacity(estimatedSize));
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> LinkedList<E> newLinkedList() {
/*  211 */     return new LinkedList<E>();
/*      */   }
/*      */   
/*      */   @GwtCompatible(serializable = true)
/*      */   public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
/*  223 */     LinkedList<E> list = newLinkedList();
/*  224 */     Iterables.addAll(list, elements);
/*  225 */     return list;
/*      */   }
/*      */   
/*      */   @GwtIncompatible("CopyOnWriteArrayList")
/*      */   public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
/*  239 */     return new CopyOnWriteArrayList<E>();
/*      */   }
/*      */   
/*      */   @GwtIncompatible("CopyOnWriteArrayList")
/*      */   public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> elements) {
/*  254 */     Collection<? extends E> elementsCollection = (elements instanceof Collection) ? Collections2.<E>cast(elements) : newArrayList(elements);
/*  257 */     return new CopyOnWriteArrayList<E>(elementsCollection);
/*      */   }
/*      */   
/*      */   public static <E> List<E> asList(@Nullable E first, E[] rest) {
/*  277 */     return new OnePlusArrayList<E>(first, rest);
/*      */   }
/*      */   
/*      */   private static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
/*      */     final E first;
/*      */     
/*      */     final E[] rest;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     OnePlusArrayList(@Nullable E first, E[] rest) {
/*  287 */       this.first = first;
/*  288 */       this.rest = (E[])Preconditions.checkNotNull(rest);
/*      */     }
/*      */     
/*      */     public int size() {
/*  291 */       return this.rest.length + 1;
/*      */     }
/*      */     
/*      */     public E get(int index) {
/*  295 */       Preconditions.checkElementIndex(index, size());
/*  296 */       return (index == 0) ? this.first : this.rest[index - 1];
/*      */     }
/*      */   }
/*      */   
/*      */   public static <E> List<E> asList(@Nullable E first, @Nullable E second, E[] rest) {
/*  320 */     return new TwoPlusArrayList<E>(first, second, rest);
/*      */   }
/*      */   
/*      */   private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
/*      */     final E first;
/*      */     
/*      */     final E second;
/*      */     
/*      */     final E[] rest;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     TwoPlusArrayList(@Nullable E first, @Nullable E second, E[] rest) {
/*  331 */       this.first = first;
/*  332 */       this.second = second;
/*  333 */       this.rest = (E[])Preconditions.checkNotNull(rest);
/*      */     }
/*      */     
/*      */     public int size() {
/*  336 */       return this.rest.length + 2;
/*      */     }
/*      */     
/*      */     public E get(int index) {
/*  339 */       switch (index) {
/*      */         case 0:
/*  341 */           return this.first;
/*      */         case 1:
/*  343 */           return this.second;
/*      */       } 
/*  346 */       Preconditions.checkElementIndex(index, size());
/*  347 */       return this.rest[index - 2];
/*      */     }
/*      */   }
/*      */   
/*      */   static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> lists) {
/*  410 */     return CartesianList.create(lists);
/*      */   }
/*      */   
/*      */   static <B> List<List<B>> cartesianProduct(List<? extends B>... lists) {
/*  470 */     return cartesianProduct(Arrays.asList(lists));
/*      */   }
/*      */   
/*      */   public static <F, T> List<T> transform(List<F> fromList, Function<? super F, ? extends T> function) {
/*  508 */     return (fromList instanceof RandomAccess) ? new TransformingRandomAccessList<F, T>(fromList, function) : new TransformingSequentialList<F, T>(fromList, function);
/*      */   }
/*      */   
/*      */   private static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
/*      */     final List<F> fromList;
/*      */     
/*      */     final Function<? super F, ? extends T> function;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     TransformingSequentialList(List<F> fromList, Function<? super F, ? extends T> function) {
/*  525 */       this.fromList = (List<F>)Preconditions.checkNotNull(fromList);
/*  526 */       this.function = (Function<? super F, ? extends T>)Preconditions.checkNotNull(function);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  534 */       this.fromList.clear();
/*      */     }
/*      */     
/*      */     public int size() {
/*  537 */       return this.fromList.size();
/*      */     }
/*      */     
/*      */     public ListIterator<T> listIterator(int index) {
/*  540 */       return new TransformedListIterator<F, T>(this.fromList.listIterator(index)) {
/*      */           T transform(F from) {
/*  543 */             return (T)Lists.TransformingSequentialList.this.function.apply(from);
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */   
/*      */   private static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
/*      */     final List<F> fromList;
/*      */     
/*      */     final Function<? super F, ? extends T> function;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     TransformingRandomAccessList(List<F> fromList, Function<? super F, ? extends T> function) {
/*  566 */       this.fromList = (List<F>)Preconditions.checkNotNull(fromList);
/*  567 */       this.function = (Function<? super F, ? extends T>)Preconditions.checkNotNull(function);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  570 */       this.fromList.clear();
/*      */     }
/*      */     
/*      */     public T get(int index) {
/*  573 */       return (T)this.function.apply(this.fromList.get(index));
/*      */     }
/*      */     
/*      */     public Iterator<T> iterator() {
/*  576 */       return listIterator();
/*      */     }
/*      */     
/*      */     public ListIterator<T> listIterator(int index) {
/*  579 */       return new TransformedListIterator<F, T>(this.fromList.listIterator(index)) {
/*      */           T transform(F from) {
/*  582 */             return (T)Lists.TransformingRandomAccessList.this.function.apply(from);
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  587 */       return this.fromList.isEmpty();
/*      */     }
/*      */     
/*      */     public T remove(int index) {
/*  590 */       return (T)this.function.apply(this.fromList.remove(index));
/*      */     }
/*      */     
/*      */     public int size() {
/*  593 */       return this.fromList.size();
/*      */     }
/*      */   }
/*      */   
/*      */   public static <T> List<List<T>> partition(List<T> list, int size) {
/*  617 */     Preconditions.checkNotNull(list);
/*  618 */     Preconditions.checkArgument((size > 0));
/*  619 */     return (list instanceof RandomAccess) ? new RandomAccessPartition<T>(list, size) : new Partition<T>(list, size);
/*      */   }
/*      */   
/*      */   private static class Partition<T> extends AbstractList<List<T>> {
/*      */     final List<T> list;
/*      */     
/*      */     final int size;
/*      */     
/*      */     Partition(List<T> list, int size) {
/*  629 */       this.list = list;
/*  630 */       this.size = size;
/*      */     }
/*      */     
/*      */     public List<T> get(int index) {
/*  634 */       Preconditions.checkElementIndex(index, size());
/*  635 */       int start = index * this.size;
/*  636 */       int end = Math.min(start + this.size, this.list.size());
/*  637 */       return this.list.subList(start, end);
/*      */     }
/*      */     
/*      */     public int size() {
/*  641 */       return IntMath.divide(this.list.size(), this.size, RoundingMode.CEILING);
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  645 */       return this.list.isEmpty();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
/*      */     RandomAccessPartition(List<T> list, int size) {
/*  652 */       super(list, size);
/*      */     }
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static ImmutableList<Character> charactersOf(String string) {
/*  663 */     return new StringAsImmutableList((String)Preconditions.checkNotNull(string));
/*      */   }
/*      */   
/*      */   private static final class StringAsImmutableList extends ImmutableList<Character> {
/*      */     private final String string;
/*      */     
/*      */     StringAsImmutableList(String string) {
/*  673 */       this.string = string;
/*      */     }
/*      */     
/*      */     public int indexOf(@Nullable Object object) {
/*  677 */       return (object instanceof Character) ? this.string.indexOf(((Character)object).charValue()) : -1;
/*      */     }
/*      */     
/*      */     public int lastIndexOf(@Nullable Object object) {
/*  682 */       return (object instanceof Character) ? this.string.lastIndexOf(((Character)object).charValue()) : -1;
/*      */     }
/*      */     
/*      */     public ImmutableList<Character> subList(int fromIndex, int toIndex) {
/*  688 */       Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
/*  689 */       return Lists.charactersOf(this.string.substring(fromIndex, toIndex));
/*      */     }
/*      */     
/*      */     boolean isPartialView() {
/*  693 */       return false;
/*      */     }
/*      */     
/*      */     public Character get(int index) {
/*  697 */       Preconditions.checkElementIndex(index, size());
/*  698 */       return Character.valueOf(this.string.charAt(index));
/*      */     }
/*      */     
/*      */     public int size() {
/*  702 */       return this.string.length();
/*      */     }
/*      */   }
/*      */   
/*      */   @Beta
/*      */   public static List<Character> charactersOf(CharSequence sequence) {
/*  718 */     return new CharSequenceAsList((CharSequence)Preconditions.checkNotNull(sequence));
/*      */   }
/*      */   
/*      */   private static final class CharSequenceAsList extends AbstractList<Character> {
/*      */     private final CharSequence sequence;
/*      */     
/*      */     CharSequenceAsList(CharSequence sequence) {
/*  726 */       this.sequence = sequence;
/*      */     }
/*      */     
/*      */     public Character get(int index) {
/*  730 */       Preconditions.checkElementIndex(index, size());
/*  731 */       return Character.valueOf(this.sequence.charAt(index));
/*      */     }
/*      */     
/*      */     public int size() {
/*  735 */       return this.sequence.length();
/*      */     }
/*      */   }
/*      */   
/*      */   public static <T> List<T> reverse(List<T> list) {
/*  752 */     if (list instanceof ImmutableList)
/*  753 */       return ((ImmutableList<T>)list).reverse(); 
/*  754 */     if (list instanceof ReverseList)
/*  755 */       return ((ReverseList<T>)list).getForwardList(); 
/*  756 */     if (list instanceof RandomAccess)
/*  757 */       return new RandomAccessReverseList<T>(list); 
/*  759 */     return new ReverseList<T>(list);
/*      */   }
/*      */   
/*      */   private static class ReverseList<T> extends AbstractList<T> {
/*      */     private final List<T> forwardList;
/*      */     
/*      */     ReverseList(List<T> forwardList) {
/*  767 */       this.forwardList = (List<T>)Preconditions.checkNotNull(forwardList);
/*      */     }
/*      */     
/*      */     List<T> getForwardList() {
/*  771 */       return this.forwardList;
/*      */     }
/*      */     
/*      */     private int reverseIndex(int index) {
/*  775 */       int size = size();
/*  776 */       Preconditions.checkElementIndex(index, size);
/*  777 */       return size - 1 - index;
/*      */     }
/*      */     
/*      */     private int reversePosition(int index) {
/*  781 */       int size = size();
/*  782 */       Preconditions.checkPositionIndex(index, size);
/*  783 */       return size - index;
/*      */     }
/*      */     
/*      */     public void add(int index, @Nullable T element) {
/*  787 */       this.forwardList.add(reversePosition(index), element);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  791 */       this.forwardList.clear();
/*      */     }
/*      */     
/*      */     public T remove(int index) {
/*  795 */       return this.forwardList.remove(reverseIndex(index));
/*      */     }
/*      */     
/*      */     protected void removeRange(int fromIndex, int toIndex) {
/*  799 */       subList(fromIndex, toIndex).clear();
/*      */     }
/*      */     
/*      */     public T set(int index, @Nullable T element) {
/*  803 */       return this.forwardList.set(reverseIndex(index), element);
/*      */     }
/*      */     
/*      */     public T get(int index) {
/*  807 */       return this.forwardList.get(reverseIndex(index));
/*      */     }
/*      */     
/*      */     public int size() {
/*  811 */       return this.forwardList.size();
/*      */     }
/*      */     
/*      */     public List<T> subList(int fromIndex, int toIndex) {
/*  815 */       Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
/*  816 */       return Lists.reverse(this.forwardList.subList(reversePosition(toIndex), reversePosition(fromIndex)));
/*      */     }
/*      */     
/*      */     public Iterator<T> iterator() {
/*  821 */       return listIterator();
/*      */     }
/*      */     
/*      */     public ListIterator<T> listIterator(int index) {
/*  825 */       int start = reversePosition(index);
/*  826 */       final ListIterator<T> forwardIterator = this.forwardList.listIterator(start);
/*  827 */       return new ListIterator<T>() {
/*      */           boolean canRemoveOrSet;
/*      */           
/*      */           public void add(T e) {
/*  832 */             forwardIterator.add(e);
/*  833 */             forwardIterator.previous();
/*  834 */             this.canRemoveOrSet = false;
/*      */           }
/*      */           
/*      */           public boolean hasNext() {
/*  838 */             return forwardIterator.hasPrevious();
/*      */           }
/*      */           
/*      */           public boolean hasPrevious() {
/*  842 */             return forwardIterator.hasNext();
/*      */           }
/*      */           
/*      */           public T next() {
/*  846 */             if (!hasNext())
/*  847 */               throw new NoSuchElementException(); 
/*  849 */             this.canRemoveOrSet = true;
/*  850 */             return forwardIterator.previous();
/*      */           }
/*      */           
/*      */           public int nextIndex() {
/*  854 */             return Lists.ReverseList.this.reversePosition(forwardIterator.nextIndex());
/*      */           }
/*      */           
/*      */           public T previous() {
/*  858 */             if (!hasPrevious())
/*  859 */               throw new NoSuchElementException(); 
/*  861 */             this.canRemoveOrSet = true;
/*  862 */             return forwardIterator.next();
/*      */           }
/*      */           
/*      */           public int previousIndex() {
/*  866 */             return nextIndex() - 1;
/*      */           }
/*      */           
/*      */           public void remove() {
/*  870 */             CollectPreconditions.checkRemove(this.canRemoveOrSet);
/*  871 */             forwardIterator.remove();
/*  872 */             this.canRemoveOrSet = false;
/*      */           }
/*      */           
/*      */           public void set(T e) {
/*  876 */             Preconditions.checkState(this.canRemoveOrSet);
/*  877 */             forwardIterator.set(e);
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */   
/*      */   private static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
/*      */     RandomAccessReverseList(List<T> forwardList) {
/*  886 */       super(forwardList);
/*      */     }
/*      */   }
/*      */   
/*      */   static int hashCodeImpl(List<?> list) {
/*  895 */     int hashCode = 1;
/*  896 */     for (Object o : list) {
/*  897 */       hashCode = 31 * hashCode + ((o == null) ? 0 : o.hashCode());
/*  899 */       hashCode = hashCode ^ 0xFFFFFFFF ^ 0xFFFFFFFF;
/*      */     } 
/*  902 */     return hashCode;
/*      */   }
/*      */   
/*      */   static boolean equalsImpl(List<?> list, @Nullable Object object) {
/*  909 */     if (object == Preconditions.checkNotNull(list))
/*  910 */       return true; 
/*  912 */     if (!(object instanceof List))
/*  913 */       return false; 
/*  916 */     List<?> o = (List)object;
/*  918 */     return (list.size() == o.size() && Iterators.elementsEqual(list.iterator(), o.iterator()));
/*      */   }
/*      */   
/*      */   static <E> boolean addAllImpl(List<E> list, int index, Iterable<? extends E> elements) {
/*  927 */     boolean changed = false;
/*  928 */     ListIterator<E> listIterator = list.listIterator(index);
/*  929 */     for (E e : elements) {
/*  930 */       listIterator.add(e);
/*  931 */       changed = true;
/*      */     } 
/*  933 */     return changed;
/*      */   }
/*      */   
/*      */   static int indexOfImpl(List<?> list, @Nullable Object element) {
/*  940 */     ListIterator<?> listIterator = list.listIterator();
/*  941 */     while (listIterator.hasNext()) {
/*  942 */       if (Objects.equal(element, listIterator.next()))
/*  943 */         return listIterator.previousIndex(); 
/*      */     } 
/*  946 */     return -1;
/*      */   }
/*      */   
/*      */   static int lastIndexOfImpl(List<?> list, @Nullable Object element) {
/*  953 */     ListIterator<?> listIterator = list.listIterator(list.size());
/*  954 */     while (listIterator.hasPrevious()) {
/*  955 */       if (Objects.equal(element, listIterator.previous()))
/*  956 */         return listIterator.nextIndex(); 
/*      */     } 
/*  959 */     return -1;
/*      */   }
/*      */   
/*      */   static <E> ListIterator<E> listIteratorImpl(List<E> list, int index) {
/*  966 */     return (new AbstractListWrapper<E>(list)).listIterator(index);
/*      */   }
/*      */   
/*      */   static <E> List<E> subListImpl(List<E> list, int fromIndex, int toIndex) {
/*      */     List<E> wrapper;
/*  975 */     if (list instanceof RandomAccess) {
/*  976 */       wrapper = new RandomAccessListWrapper<E>(list) {
/*      */           private static final long serialVersionUID = 0L;
/*      */           
/*      */           public ListIterator<E> listIterator(int index) {
/*  978 */             return this.backingList.listIterator(index);
/*      */           }
/*      */         };
/*      */     } else {
/*  984 */       wrapper = new AbstractListWrapper<E>(list) {
/*      */           private static final long serialVersionUID = 0L;
/*      */           
/*      */           public ListIterator<E> listIterator(int index) {
/*  986 */             return this.backingList.listIterator(index);
/*      */           }
/*      */         };
/*      */     } 
/*  992 */     return wrapper.subList(fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   private static class AbstractListWrapper<E> extends AbstractList<E> {
/*      */     final List<E> backingList;
/*      */     
/*      */     AbstractListWrapper(List<E> backingList) {
/*  999 */       this.backingList = (List<E>)Preconditions.checkNotNull(backingList);
/*      */     }
/*      */     
/*      */     public void add(int index, E element) {
/* 1003 */       this.backingList.add(index, element);
/*      */     }
/*      */     
/*      */     public boolean addAll(int index, Collection<? extends E> c) {
/* 1007 */       return this.backingList.addAll(index, c);
/*      */     }
/*      */     
/*      */     public E get(int index) {
/* 1011 */       return this.backingList.get(index);
/*      */     }
/*      */     
/*      */     public E remove(int index) {
/* 1015 */       return this.backingList.remove(index);
/*      */     }
/*      */     
/*      */     public E set(int index, E element) {
/* 1019 */       return this.backingList.set(index, element);
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 1023 */       return this.backingList.contains(o);
/*      */     }
/*      */     
/*      */     public int size() {
/* 1027 */       return this.backingList.size();
/*      */     }
/*      */   }
/*      */   
/*      */   private static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
/*      */     RandomAccessListWrapper(List<E> backingList) {
/* 1034 */       super(backingList);
/*      */     }
/*      */   }
/*      */   
/*      */   static <T> List<T> cast(Iterable<T> iterable) {
/* 1042 */     return (List<T>)iterable;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\Lists.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */