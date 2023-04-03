/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.iterators.ArrayIterator;
/*     */ import org.apache.commons.collections.iterators.ArrayListIterator;
/*     */ import org.apache.commons.collections.iterators.CollatingIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyListIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyMapIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*     */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*     */ import org.apache.commons.collections.iterators.FilterIterator;
/*     */ import org.apache.commons.collections.iterators.FilterListIterator;
/*     */ import org.apache.commons.collections.iterators.IteratorChain;
/*     */ import org.apache.commons.collections.iterators.IteratorEnumeration;
/*     */ import org.apache.commons.collections.iterators.ListIteratorWrapper;
/*     */ import org.apache.commons.collections.iterators.LoopingIterator;
/*     */ import org.apache.commons.collections.iterators.LoopingListIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectArrayIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectArrayListIterator;
/*     */ import org.apache.commons.collections.iterators.ObjectGraphIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonListIterator;
/*     */ import org.apache.commons.collections.iterators.TransformIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ 
/*     */ public class IteratorUtils {
/*  84 */   public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
/*     */   
/*  91 */   public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
/*     */   
/*  95 */   public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
/*     */   
/*  99 */   public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
/*     */   
/* 103 */   public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;
/*     */   
/*     */   public static ResettableIterator emptyIterator() {
/* 125 */     return EMPTY_ITERATOR;
/*     */   }
/*     */   
/*     */   public static ResettableListIterator emptyListIterator() {
/* 140 */     return EMPTY_LIST_ITERATOR;
/*     */   }
/*     */   
/*     */   public static OrderedIterator emptyOrderedIterator() {
/* 152 */     return EMPTY_ORDERED_ITERATOR;
/*     */   }
/*     */   
/*     */   public static MapIterator emptyMapIterator() {
/* 164 */     return EMPTY_MAP_ITERATOR;
/*     */   }
/*     */   
/*     */   public static OrderedMapIterator emptyOrderedMapIterator() {
/* 176 */     return EMPTY_ORDERED_MAP_ITERATOR;
/*     */   }
/*     */   
/*     */   public static ResettableIterator singletonIterator(Object object) {
/* 194 */     return (ResettableIterator)new SingletonIterator(object);
/*     */   }
/*     */   
/*     */   public static ListIterator singletonListIterator(Object object) {
/* 207 */     return (ListIterator)new SingletonListIterator(object);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object[] array) {
/* 223 */     return (ResettableIterator)new ObjectArrayIterator(array);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object array) {
/* 238 */     return (ResettableIterator)new ArrayIterator(array);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object[] array, int start) {
/* 255 */     return (ResettableIterator)new ObjectArrayIterator(array, start);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object array, int start) {
/* 273 */     return (ResettableIterator)new ArrayIterator(array, start);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object[] array, int start, int end) {
/* 291 */     return (ResettableIterator)new ObjectArrayIterator(array, start, end);
/*     */   }
/*     */   
/*     */   public static ResettableIterator arrayIterator(Object array, int start, int end) {
/* 310 */     return (ResettableIterator)new ArrayIterator(array, start, end);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object[] array) {
/* 322 */     return (ResettableListIterator)new ObjectArrayListIterator(array);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object array) {
/* 337 */     return (ResettableListIterator)new ArrayListIterator(array);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object[] array, int start) {
/* 350 */     return (ResettableListIterator)new ObjectArrayListIterator(array, start);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object array, int start) {
/* 367 */     return (ResettableListIterator)new ArrayListIterator(array, start);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object[] array, int start, int end) {
/* 382 */     return (ResettableListIterator)new ObjectArrayListIterator(array, start, end);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator arrayListIterator(Object array, int start, int end) {
/* 401 */     return (ResettableListIterator)new ArrayListIterator(array, start, end);
/*     */   }
/*     */   
/*     */   public static Iterator unmodifiableIterator(Iterator iterator) {
/* 415 */     return UnmodifiableIterator.decorate(iterator);
/*     */   }
/*     */   
/*     */   public static ListIterator unmodifiableListIterator(ListIterator listIterator) {
/* 428 */     return UnmodifiableListIterator.decorate(listIterator);
/*     */   }
/*     */   
/*     */   public static MapIterator unmodifiableMapIterator(MapIterator mapIterator) {
/* 440 */     return UnmodifiableMapIterator.decorate(mapIterator);
/*     */   }
/*     */   
/*     */   public static Iterator chainedIterator(Iterator iterator1, Iterator iterator2) {
/* 455 */     return (Iterator)new IteratorChain(iterator1, iterator2);
/*     */   }
/*     */   
/*     */   public static Iterator chainedIterator(Iterator[] iterators) {
/* 467 */     return (Iterator)new IteratorChain(iterators);
/*     */   }
/*     */   
/*     */   public static Iterator chainedIterator(Collection iterators) {
/* 480 */     return (Iterator)new IteratorChain(iterators);
/*     */   }
/*     */   
/*     */   public static Iterator collatedIterator(Comparator comparator, Iterator iterator1, Iterator iterator2) {
/* 502 */     return (Iterator)new CollatingIterator(comparator, iterator1, iterator2);
/*     */   }
/*     */   
/*     */   public static Iterator collatedIterator(Comparator comparator, Iterator[] iterators) {
/* 521 */     return (Iterator)new CollatingIterator(comparator, iterators);
/*     */   }
/*     */   
/*     */   public static Iterator collatedIterator(Comparator comparator, Collection iterators) {
/* 541 */     return (Iterator)new CollatingIterator(comparator, iterators);
/*     */   }
/*     */   
/*     */   public static Iterator objectGraphIterator(Object root, Transformer transformer) {
/* 600 */     return (Iterator)new ObjectGraphIterator(root, transformer);
/*     */   }
/*     */   
/*     */   public static Iterator transformedIterator(Iterator iterator, Transformer transform) {
/* 617 */     if (iterator == null)
/* 618 */       throw new NullPointerException("Iterator must not be null"); 
/* 620 */     if (transform == null)
/* 621 */       throw new NullPointerException("Transformer must not be null"); 
/* 623 */     return (Iterator)new TransformIterator(iterator, transform);
/*     */   }
/*     */   
/*     */   public static Iterator filteredIterator(Iterator iterator, Predicate predicate) {
/* 640 */     if (iterator == null)
/* 641 */       throw new NullPointerException("Iterator must not be null"); 
/* 643 */     if (predicate == null)
/* 644 */       throw new NullPointerException("Predicate must not be null"); 
/* 646 */     return (Iterator)new FilterIterator(iterator, predicate);
/*     */   }
/*     */   
/*     */   public static ListIterator filteredListIterator(ListIterator listIterator, Predicate predicate) {
/* 661 */     if (listIterator == null)
/* 662 */       throw new NullPointerException("ListIterator must not be null"); 
/* 664 */     if (predicate == null)
/* 665 */       throw new NullPointerException("Predicate must not be null"); 
/* 667 */     return (ListIterator)new FilterListIterator(listIterator, predicate);
/*     */   }
/*     */   
/*     */   public static ResettableIterator loopingIterator(Collection coll) {
/* 684 */     if (coll == null)
/* 685 */       throw new NullPointerException("Collection must not be null"); 
/* 687 */     return (ResettableIterator)new LoopingIterator(coll);
/*     */   }
/*     */   
/*     */   public static ResettableListIterator loopingListIterator(List list) {
/* 702 */     if (list == null)
/* 703 */       throw new NullPointerException("List must not be null"); 
/* 705 */     return (ResettableListIterator)new LoopingListIterator(list);
/*     */   }
/*     */   
/*     */   public static Iterator asIterator(Enumeration enumeration) {
/* 717 */     if (enumeration == null)
/* 718 */       throw new NullPointerException("Enumeration must not be null"); 
/* 720 */     return (Iterator)new EnumerationIterator(enumeration);
/*     */   }
/*     */   
/*     */   public static Iterator asIterator(Enumeration enumeration, Collection removeCollection) {
/* 732 */     if (enumeration == null)
/* 733 */       throw new NullPointerException("Enumeration must not be null"); 
/* 735 */     if (removeCollection == null)
/* 736 */       throw new NullPointerException("Collection must not be null"); 
/* 738 */     return (Iterator)new EnumerationIterator(enumeration, removeCollection);
/*     */   }
/*     */   
/*     */   public static Enumeration asEnumeration(Iterator iterator) {
/* 749 */     if (iterator == null)
/* 750 */       throw new NullPointerException("Iterator must not be null"); 
/* 752 */     return (Enumeration)new IteratorEnumeration(iterator);
/*     */   }
/*     */   
/*     */   public static ListIterator toListIterator(Iterator iterator) {
/* 766 */     if (iterator == null)
/* 767 */       throw new NullPointerException("Iterator must not be null"); 
/* 769 */     return (ListIterator)new ListIteratorWrapper(iterator);
/*     */   }
/*     */   
/*     */   public static Object[] toArray(Iterator iterator) {
/* 783 */     if (iterator == null)
/* 784 */       throw new NullPointerException("Iterator must not be null"); 
/* 786 */     List list = toList(iterator, 100);
/* 787 */     return list.toArray();
/*     */   }
/*     */   
/*     */   public static Object[] toArray(Iterator iterator, Class arrayClass) {
/* 804 */     if (iterator == null)
/* 805 */       throw new NullPointerException("Iterator must not be null"); 
/* 807 */     if (arrayClass == null)
/* 808 */       throw new NullPointerException("Array class must not be null"); 
/* 810 */     List list = toList(iterator, 100);
/* 811 */     return list.toArray((Object[])Array.newInstance(arrayClass, list.size()));
/*     */   }
/*     */   
/*     */   public static List toList(Iterator iterator) {
/* 825 */     return toList(iterator, 10);
/*     */   }
/*     */   
/*     */   public static List toList(Iterator iterator, int estimatedSize) {
/* 841 */     if (iterator == null)
/* 842 */       throw new NullPointerException("Iterator must not be null"); 
/* 844 */     if (estimatedSize < 1)
/* 845 */       throw new IllegalArgumentException("Estimated size must be greater than 0"); 
/* 847 */     List list = new ArrayList(estimatedSize);
/* 848 */     while (iterator.hasNext())
/* 849 */       list.add(iterator.next()); 
/* 851 */     return list;
/*     */   }
/*     */   
/*     */   public static Iterator getIterator(Object obj) {
/* 874 */     if (obj == null)
/* 875 */       return emptyIterator(); 
/* 877 */     if (obj instanceof Iterator)
/* 878 */       return (Iterator)obj; 
/* 880 */     if (obj instanceof Collection)
/* 881 */       return ((Collection)obj).iterator(); 
/* 883 */     if (obj instanceof Object[])
/* 884 */       return (Iterator)new ObjectArrayIterator((Object[])obj); 
/* 886 */     if (obj instanceof Enumeration)
/* 887 */       return (Iterator)new EnumerationIterator((Enumeration)obj); 
/* 889 */     if (obj instanceof Map)
/* 890 */       return ((Map)obj).values().iterator(); 
/* 892 */     if (obj instanceof Dictionary)
/* 893 */       return (Iterator)new EnumerationIterator(((Dictionary)obj).elements()); 
/* 895 */     if (obj != null && obj.getClass().isArray())
/* 896 */       return (Iterator)new ArrayIterator(obj); 
/*     */     try {
/* 900 */       Method method = obj.getClass().getMethod("iterator", (Class[])null);
/* 901 */       if (Iterator.class.isAssignableFrom(method.getReturnType())) {
/* 902 */         Iterator it = (Iterator)method.invoke(obj, (Object[])null);
/* 903 */         if (it != null)
/* 904 */           return it; 
/*     */       } 
/* 907 */     } catch (Exception ex) {}
/* 910 */     return singletonIterator(obj);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\IteratorUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */