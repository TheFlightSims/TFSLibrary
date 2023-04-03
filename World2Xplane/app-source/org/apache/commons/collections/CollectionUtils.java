/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.collection.PredicatedCollection;
/*      */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*      */ import org.apache.commons.collections.collection.TransformedCollection;
/*      */ import org.apache.commons.collections.collection.TypedCollection;
/*      */ import org.apache.commons.collections.collection.UnmodifiableBoundedCollection;
/*      */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*      */ 
/*      */ public class CollectionUtils {
/*   61 */   private static Integer INTEGER_ONE = new Integer(1);
/*      */   
/*   69 */   public static final Collection EMPTY_COLLECTION = UnmodifiableCollection.decorate(new ArrayList());
/*      */   
/*      */   public static Collection union(Collection a, Collection b) {
/*   91 */     ArrayList list = new ArrayList();
/*   92 */     Map mapa = getCardinalityMap(a);
/*   93 */     Map mapb = getCardinalityMap(b);
/*   94 */     Set elts = new HashSet(a);
/*   95 */     elts.addAll(b);
/*   96 */     Iterator it = elts.iterator();
/*   97 */     while (it.hasNext()) {
/*   98 */       Object obj = it.next();
/*   99 */       for (int i = 0, m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++)
/*  100 */         list.add(obj); 
/*      */     } 
/*  103 */     return list;
/*      */   }
/*      */   
/*      */   public static Collection intersection(Collection a, Collection b) {
/*  121 */     ArrayList list = new ArrayList();
/*  122 */     Map mapa = getCardinalityMap(a);
/*  123 */     Map mapb = getCardinalityMap(b);
/*  124 */     Set elts = new HashSet(a);
/*  125 */     elts.addAll(b);
/*  126 */     Iterator it = elts.iterator();
/*  127 */     while (it.hasNext()) {
/*  128 */       Object obj = it.next();
/*  129 */       for (int i = 0, m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++)
/*  130 */         list.add(obj); 
/*      */     } 
/*  133 */     return list;
/*      */   }
/*      */   
/*      */   public static Collection disjunction(Collection a, Collection b) {
/*  154 */     ArrayList list = new ArrayList();
/*  155 */     Map mapa = getCardinalityMap(a);
/*  156 */     Map mapb = getCardinalityMap(b);
/*  157 */     Set elts = new HashSet(a);
/*  158 */     elts.addAll(b);
/*  159 */     Iterator it = elts.iterator();
/*  160 */     while (it.hasNext()) {
/*  161 */       Object obj = it.next();
/*  162 */       for (int i = 0, m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)) - Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++)
/*  163 */         list.add(obj); 
/*      */     } 
/*  166 */     return list;
/*      */   }
/*      */   
/*      */   public static Collection subtract(Collection a, Collection b) {
/*  181 */     ArrayList list = new ArrayList(a);
/*  182 */     for (Iterator it = b.iterator(); it.hasNext();)
/*  183 */       list.remove(it.next()); 
/*  185 */     return list;
/*      */   }
/*      */   
/*      */   public static boolean containsAny(Collection coll1, Collection coll2) {
/*  201 */     if (coll1.size() < coll2.size()) {
/*  202 */       for (Iterator it = coll1.iterator(); it.hasNext();) {
/*  203 */         if (coll2.contains(it.next()))
/*  204 */           return true; 
/*      */       } 
/*      */     } else {
/*  208 */       for (Iterator it = coll2.iterator(); it.hasNext();) {
/*  209 */         if (coll1.contains(it.next()))
/*  210 */           return true; 
/*      */       } 
/*      */     } 
/*  214 */     return false;
/*      */   }
/*      */   
/*      */   public static Map getCardinalityMap(Collection coll) {
/*  229 */     Map count = new HashMap();
/*  230 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/*  231 */       Object obj = it.next();
/*  232 */       Integer c = (Integer)count.get(obj);
/*  233 */       if (c == null) {
/*  234 */         count.put(obj, INTEGER_ONE);
/*      */         continue;
/*      */       } 
/*  236 */       count.put(obj, new Integer(c.intValue() + 1));
/*      */     } 
/*  239 */     return count;
/*      */   }
/*      */   
/*      */   public static boolean isSubCollection(Collection a, Collection b) {
/*  255 */     Map mapa = getCardinalityMap(a);
/*  256 */     Map mapb = getCardinalityMap(b);
/*  257 */     Iterator it = a.iterator();
/*  258 */     while (it.hasNext()) {
/*  259 */       Object obj = it.next();
/*  260 */       if (getFreq(obj, mapa) > getFreq(obj, mapb))
/*  261 */         return false; 
/*      */     } 
/*  264 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isProperSubCollection(Collection a, Collection b) {
/*  289 */     return (a.size() < b.size() && isSubCollection(a, b));
/*      */   }
/*      */   
/*      */   public static boolean isEqualCollection(Collection a, Collection b) {
/*  305 */     if (a.size() != b.size())
/*  306 */       return false; 
/*  308 */     Map mapa = getCardinalityMap(a);
/*  309 */     Map mapb = getCardinalityMap(b);
/*  310 */     if (mapa.size() != mapb.size())
/*  311 */       return false; 
/*  313 */     Iterator it = mapa.keySet().iterator();
/*  314 */     while (it.hasNext()) {
/*  315 */       Object obj = it.next();
/*  316 */       if (getFreq(obj, mapa) != getFreq(obj, mapb))
/*  317 */         return false; 
/*      */     } 
/*  320 */     return true;
/*      */   }
/*      */   
/*      */   public static int cardinality(Object obj, Collection coll) {
/*  333 */     if (coll instanceof Set)
/*  334 */       return coll.contains(obj) ? 1 : 0; 
/*  336 */     if (coll instanceof Bag)
/*  337 */       return ((Bag)coll).getCount(obj); 
/*  339 */     int count = 0;
/*  340 */     if (obj == null) {
/*  341 */       for (Iterator it = coll.iterator(); it.hasNext();) {
/*  342 */         if (it.next() == null)
/*  343 */           count++; 
/*      */       } 
/*      */     } else {
/*  347 */       for (Iterator it = coll.iterator(); it.hasNext();) {
/*  348 */         if (obj.equals(it.next()))
/*  349 */           count++; 
/*      */       } 
/*      */     } 
/*  353 */     return count;
/*      */   }
/*      */   
/*      */   public static Object find(Collection collection, Predicate predicate) {
/*  367 */     if (collection != null && predicate != null)
/*  368 */       for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
/*  369 */         Object item = iter.next();
/*  370 */         if (predicate.evaluate(item))
/*  371 */           return item; 
/*      */       }  
/*  375 */     return null;
/*      */   }
/*      */   
/*      */   public static void forAllDo(Collection collection, Closure closure) {
/*  387 */     if (collection != null && closure != null)
/*  388 */       for (Iterator it = collection.iterator(); it.hasNext();)
/*  389 */         closure.execute(it.next());  
/*      */   }
/*      */   
/*      */   public static void filter(Collection collection, Predicate predicate) {
/*  404 */     if (collection != null && predicate != null)
/*  405 */       for (Iterator it = collection.iterator(); it.hasNext();) {
/*  406 */         if (!predicate.evaluate(it.next()))
/*  407 */           it.remove(); 
/*      */       }  
/*      */   }
/*      */   
/*      */   public static void transform(Collection collection, Transformer transformer) {
/*  430 */     if (collection != null && transformer != null)
/*  431 */       if (collection instanceof List) {
/*  432 */         List list = (List)collection;
/*  433 */         for (ListIterator it = list.listIterator(); it.hasNext();)
/*  434 */           it.set(transformer.transform(it.next())); 
/*      */       } else {
/*  437 */         Collection resultCollection = collect(collection, transformer);
/*  438 */         collection.clear();
/*  439 */         collection.addAll(resultCollection);
/*      */       }  
/*      */   }
/*      */   
/*      */   public static int countMatches(Collection inputCollection, Predicate predicate) {
/*  454 */     int count = 0;
/*  455 */     if (inputCollection != null && predicate != null)
/*  456 */       for (Iterator it = inputCollection.iterator(); it.hasNext();) {
/*  457 */         if (predicate.evaluate(it.next()))
/*  458 */           count++; 
/*      */       }  
/*  462 */     return count;
/*      */   }
/*      */   
/*      */   public static boolean exists(Collection collection, Predicate predicate) {
/*  475 */     if (collection != null && predicate != null)
/*  476 */       for (Iterator it = collection.iterator(); it.hasNext();) {
/*  477 */         if (predicate.evaluate(it.next()))
/*  478 */           return true; 
/*      */       }  
/*  482 */     return false;
/*      */   }
/*      */   
/*      */   public static Collection select(Collection inputCollection, Predicate predicate) {
/*  497 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  498 */     select(inputCollection, predicate, answer);
/*  499 */     return answer;
/*      */   }
/*      */   
/*      */   public static void select(Collection inputCollection, Predicate predicate, Collection outputCollection) {
/*  514 */     if (inputCollection != null && predicate != null)
/*  515 */       for (Iterator iter = inputCollection.iterator(); iter.hasNext(); ) {
/*  516 */         Object item = iter.next();
/*  517 */         if (predicate.evaluate(item))
/*  518 */           outputCollection.add(item); 
/*      */       }  
/*      */   }
/*      */   
/*      */   public static Collection selectRejected(Collection inputCollection, Predicate predicate) {
/*  536 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  537 */     selectRejected(inputCollection, predicate, answer);
/*  538 */     return answer;
/*      */   }
/*      */   
/*      */   public static void selectRejected(Collection inputCollection, Predicate predicate, Collection outputCollection) {
/*  552 */     if (inputCollection != null && predicate != null)
/*  553 */       for (Iterator iter = inputCollection.iterator(); iter.hasNext(); ) {
/*  554 */         Object item = iter.next();
/*  555 */         if (!predicate.evaluate(item))
/*  556 */           outputCollection.add(item); 
/*      */       }  
/*      */   }
/*      */   
/*      */   public static Collection collect(Collection inputCollection, Transformer transformer) {
/*  574 */     ArrayList answer = new ArrayList(inputCollection.size());
/*  575 */     collect(inputCollection, transformer, answer);
/*  576 */     return answer;
/*      */   }
/*      */   
/*      */   public static Collection collect(Iterator inputIterator, Transformer transformer) {
/*  590 */     ArrayList answer = new ArrayList();
/*  591 */     collect(inputIterator, transformer, answer);
/*  592 */     return answer;
/*      */   }
/*      */   
/*      */   public static Collection collect(Collection inputCollection, Transformer transformer, Collection outputCollection) {
/*  609 */     if (inputCollection != null)
/*  610 */       return collect(inputCollection.iterator(), transformer, outputCollection); 
/*  612 */     return outputCollection;
/*      */   }
/*      */   
/*      */   public static Collection collect(Iterator inputIterator, Transformer transformer, Collection outputCollection) {
/*  629 */     if (inputIterator != null && transformer != null)
/*  630 */       while (inputIterator.hasNext()) {
/*  631 */         Object item = inputIterator.next();
/*  632 */         Object value = transformer.transform(item);
/*  633 */         outputCollection.add(value);
/*      */       }  
/*  636 */     return outputCollection;
/*      */   }
/*      */   
/*      */   public static boolean addIgnoreNull(Collection collection, Object object) {
/*  650 */     return (object == null) ? false : collection.add(object);
/*      */   }
/*      */   
/*      */   public static void addAll(Collection collection, Iterator iterator) {
/*  661 */     while (iterator.hasNext())
/*  662 */       collection.add(iterator.next()); 
/*      */   }
/*      */   
/*      */   public static void addAll(Collection collection, Enumeration enumeration) {
/*  674 */     while (enumeration.hasMoreElements())
/*  675 */       collection.add(enumeration.nextElement()); 
/*      */   }
/*      */   
/*      */   public static void addAll(Collection collection, Object[] elements) {
/*  687 */     for (int i = 0, size = elements.length; i < size; i++)
/*  688 */       collection.add(elements[i]); 
/*      */   }
/*      */   
/*      */   public static Object index(Object obj, int idx) {
/*  715 */     return index(obj, new Integer(idx));
/*      */   }
/*      */   
/*      */   public static Object index(Object obj, Object index) {
/*  742 */     if (obj instanceof Map) {
/*  743 */       Map map = (Map)obj;
/*  744 */       if (map.containsKey(index))
/*  745 */         return map.get(index); 
/*      */     } 
/*  748 */     int idx = -1;
/*  749 */     if (index instanceof Integer)
/*  750 */       idx = ((Integer)index).intValue(); 
/*  752 */     if (idx < 0)
/*  753 */       return obj; 
/*  755 */     if (obj instanceof Map) {
/*  756 */       Map map = (Map)obj;
/*  757 */       Iterator iterator = map.keySet().iterator();
/*  758 */       return index(iterator, idx);
/*      */     } 
/*  760 */     if (obj instanceof List)
/*  761 */       return ((List)obj).get(idx); 
/*  763 */     if (obj instanceof Object[])
/*  764 */       return ((Object[])obj)[idx]; 
/*  766 */     if (obj instanceof Enumeration) {
/*  767 */       Enumeration it = (Enumeration)obj;
/*  768 */       while (it.hasMoreElements()) {
/*  769 */         idx--;
/*  770 */         if (idx == -1)
/*  771 */           return it.nextElement(); 
/*  773 */         it.nextElement();
/*      */       } 
/*      */     } else {
/*  777 */       if (obj instanceof Iterator)
/*  778 */         return index((Iterator)obj, idx); 
/*  780 */       if (obj instanceof Collection) {
/*  781 */         Iterator iterator = ((Collection)obj).iterator();
/*  782 */         return index(iterator, idx);
/*      */       } 
/*      */     } 
/*  784 */     return obj;
/*      */   }
/*      */   
/*      */   private static Object index(Iterator iterator, int idx) {
/*  788 */     while (iterator.hasNext()) {
/*  789 */       idx--;
/*  790 */       if (idx == -1)
/*  791 */         return iterator.next(); 
/*  793 */       iterator.next();
/*      */     } 
/*  796 */     return iterator;
/*      */   }
/*      */   
/*      */   public static Object get(Object object, int index) {
/*  830 */     if (index < 0)
/*  831 */       throw new IndexOutOfBoundsException("Index cannot be negative: " + index); 
/*  833 */     if (object instanceof Map) {
/*  834 */       Map map = (Map)object;
/*  835 */       Iterator iterator = map.entrySet().iterator();
/*  836 */       return get(iterator, index);
/*      */     } 
/*  837 */     if (object instanceof List)
/*  838 */       return ((List)object).get(index); 
/*  839 */     if (object instanceof Object[])
/*  840 */       return ((Object[])object)[index]; 
/*  841 */     if (object instanceof Iterator) {
/*  842 */       Iterator it = (Iterator)object;
/*  843 */       while (it.hasNext()) {
/*  844 */         index--;
/*  845 */         if (index == -1)
/*  846 */           return it.next(); 
/*  848 */         it.next();
/*      */       } 
/*  851 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index);
/*      */     } 
/*  852 */     if (object instanceof Collection) {
/*  853 */       Iterator iterator = ((Collection)object).iterator();
/*  854 */       return get(iterator, index);
/*      */     } 
/*  855 */     if (object instanceof Enumeration) {
/*  856 */       Enumeration it = (Enumeration)object;
/*  857 */       while (it.hasMoreElements()) {
/*  858 */         index--;
/*  859 */         if (index == -1)
/*  860 */           return it.nextElement(); 
/*  862 */         it.nextElement();
/*      */       } 
/*  865 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index);
/*      */     } 
/*  866 */     if (object == null)
/*  867 */       throw new IllegalArgumentException("Unsupported object type: null"); 
/*      */     try {
/*  870 */       return Array.get(object, index);
/*  871 */     } catch (IllegalArgumentException ex) {
/*  872 */       throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int size(Object object) {
/*  895 */     int total = 0;
/*  896 */     if (object instanceof Map) {
/*  897 */       total = ((Map)object).size();
/*  898 */     } else if (object instanceof Collection) {
/*  899 */       total = ((Collection)object).size();
/*  900 */     } else if (object instanceof Object[]) {
/*  901 */       total = ((Object[])object).length;
/*  902 */     } else if (object instanceof Iterator) {
/*  903 */       Iterator it = (Iterator)object;
/*  904 */       while (it.hasNext()) {
/*  905 */         total++;
/*  906 */         it.next();
/*      */       } 
/*  908 */     } else if (object instanceof Enumeration) {
/*  909 */       Enumeration it = (Enumeration)object;
/*  910 */       while (it.hasMoreElements()) {
/*  911 */         total++;
/*  912 */         it.nextElement();
/*      */       } 
/*      */     } else {
/*  914 */       if (object == null)
/*  915 */         throw new IllegalArgumentException("Unsupported object type: null"); 
/*      */       try {
/*  918 */         total = Array.getLength(object);
/*  919 */       } catch (IllegalArgumentException ex) {
/*  920 */         throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */       } 
/*      */     } 
/*  923 */     return total;
/*      */   }
/*      */   
/*      */   public static boolean sizeIsEmpty(Object object) {
/*  947 */     if (object instanceof Collection)
/*  948 */       return ((Collection)object).isEmpty(); 
/*  949 */     if (object instanceof Map)
/*  950 */       return ((Map)object).isEmpty(); 
/*  951 */     if (object instanceof Object[])
/*  952 */       return (((Object[])object).length == 0); 
/*  953 */     if (object instanceof Iterator)
/*  954 */       return !((Iterator)object).hasNext(); 
/*  955 */     if (object instanceof Enumeration)
/*  956 */       return !((Enumeration)object).hasMoreElements(); 
/*  957 */     if (object == null)
/*  958 */       throw new IllegalArgumentException("Unsupported object type: null"); 
/*      */     try {
/*  961 */       return (Array.getLength(object) == 0);
/*  962 */     } catch (IllegalArgumentException ex) {
/*  963 */       throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(Collection coll) {
/*  979 */     return (coll == null || coll.isEmpty());
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(Collection coll) {
/*  992 */     return !isEmpty(coll);
/*      */   }
/*      */   
/*      */   public static void reverseArray(Object[] array) {
/* 1002 */     int i = 0;
/* 1003 */     int j = array.length - 1;
/* 1006 */     while (j > i) {
/* 1007 */       Object tmp = array[j];
/* 1008 */       array[j] = array[i];
/* 1009 */       array[i] = tmp;
/* 1010 */       j--;
/* 1011 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final int getFreq(Object obj, Map freqMap) {
/* 1016 */     Integer count = (Integer)freqMap.get(obj);
/* 1017 */     if (count != null)
/* 1018 */       return count.intValue(); 
/* 1020 */     return 0;
/*      */   }
/*      */   
/*      */   public static boolean isFull(Collection coll) {
/* 1039 */     if (coll == null)
/* 1040 */       throw new NullPointerException("The collection must not be null"); 
/* 1042 */     if (coll instanceof BoundedCollection)
/* 1043 */       return ((BoundedCollection)coll).isFull(); 
/*      */     try {
/* 1046 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/* 1047 */       return bcoll.isFull();
/* 1049 */     } catch (IllegalArgumentException ex) {
/* 1050 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int maxSize(Collection coll) {
/* 1070 */     if (coll == null)
/* 1071 */       throw new NullPointerException("The collection must not be null"); 
/* 1073 */     if (coll instanceof BoundedCollection)
/* 1074 */       return ((BoundedCollection)coll).maxSize(); 
/*      */     try {
/* 1077 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/* 1078 */       return bcoll.maxSize();
/* 1080 */     } catch (IllegalArgumentException ex) {
/* 1081 */       return -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Collection retainAll(Collection collection, Collection retain) {
/* 1102 */     return ListUtils.retainAll(collection, retain);
/*      */   }
/*      */   
/*      */   public static Collection removeAll(Collection collection, Collection remove) {
/* 1122 */     return ListUtils.retainAll(collection, remove);
/*      */   }
/*      */   
/*      */   public static Collection synchronizedCollection(Collection collection) {
/* 1149 */     return SynchronizedCollection.decorate(collection);
/*      */   }
/*      */   
/*      */   public static Collection unmodifiableCollection(Collection collection) {
/* 1162 */     return UnmodifiableCollection.decorate(collection);
/*      */   }
/*      */   
/*      */   public static Collection predicatedCollection(Collection collection, Predicate predicate) {
/* 1179 */     return PredicatedCollection.decorate(collection, predicate);
/*      */   }
/*      */   
/*      */   public static Collection typedCollection(Collection collection, Class type) {
/* 1192 */     return TypedCollection.decorate(collection, type);
/*      */   }
/*      */   
/*      */   public static Collection transformedCollection(Collection collection, Transformer transformer) {
/* 1208 */     return TransformedCollection.decorate(collection, transformer);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\CollectionUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */