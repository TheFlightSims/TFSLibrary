/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.list.FixedSizeList;
/*     */ import org.apache.commons.collections.list.LazyList;
/*     */ import org.apache.commons.collections.list.PredicatedList;
/*     */ import org.apache.commons.collections.list.SynchronizedList;
/*     */ import org.apache.commons.collections.list.TransformedList;
/*     */ import org.apache.commons.collections.list.TypedList;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class ListUtils {
/*  53 */   public static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*     */   
/*     */   public static List intersection(List list1, List list2) {
/*  72 */     ArrayList result = new ArrayList();
/*  73 */     Iterator iterator = list2.iterator();
/*  75 */     while (iterator.hasNext()) {
/*  76 */       Object o = iterator.next();
/*  78 */       if (list1.contains(o))
/*  79 */         result.add(o); 
/*     */     } 
/*  83 */     return result;
/*     */   }
/*     */   
/*     */   public static List subtract(List list1, List list2) {
/* 102 */     ArrayList result = new ArrayList(list1);
/* 103 */     Iterator iterator = list2.iterator();
/* 105 */     while (iterator.hasNext())
/* 106 */       result.remove(iterator.next()); 
/* 109 */     return result;
/*     */   }
/*     */   
/*     */   public static List sum(List list1, List list2) {
/* 122 */     return subtract(union(list1, list2), intersection(list1, list2));
/*     */   }
/*     */   
/*     */   public static List union(List list1, List list2) {
/* 136 */     ArrayList result = new ArrayList(list1);
/* 137 */     result.addAll(list2);
/* 138 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean isEqualList(Collection list1, Collection list2) {
/* 171 */     if (list1 == list2)
/* 172 */       return true; 
/* 174 */     if (list1 == null || list2 == null || list1.size() != list2.size())
/* 175 */       return false; 
/* 178 */     Iterator it1 = list1.iterator();
/* 179 */     Iterator it2 = list2.iterator();
/* 180 */     Object obj1 = null;
/* 181 */     Object obj2 = null;
/* 183 */     while (it1.hasNext() && it2.hasNext()) {
/* 184 */       obj1 = it1.next();
/* 185 */       obj2 = it2.next();
/* 187 */       if ((obj1 == null) ? (obj2 == null) : obj1.equals(obj2))
/*     */         continue; 
/* 188 */       return false;
/*     */     } 
/* 192 */     return (!it1.hasNext() && !it2.hasNext());
/*     */   }
/*     */   
/*     */   public static int hashCodeForList(Collection list) {
/* 208 */     if (list == null)
/* 209 */       return 0; 
/* 211 */     int hashCode = 1;
/* 212 */     Iterator it = list.iterator();
/* 213 */     Object obj = null;
/* 215 */     while (it.hasNext()) {
/* 216 */       obj = it.next();
/* 217 */       hashCode = 31 * hashCode + ((obj == null) ? 0 : obj.hashCode());
/*     */     } 
/* 219 */     return hashCode;
/*     */   }
/*     */   
/*     */   public static List retainAll(Collection collection, Collection retain) {
/* 239 */     List list = new ArrayList(Math.min(collection.size(), retain.size()));
/* 241 */     for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
/* 242 */       Object obj = iter.next();
/* 243 */       if (retain.contains(obj))
/* 244 */         list.add(obj); 
/*     */     } 
/* 247 */     return list;
/*     */   }
/*     */   
/*     */   public static List removeAll(Collection collection, Collection remove) {
/* 267 */     List list = new ArrayList();
/* 268 */     for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
/* 269 */       Object obj = iter.next();
/* 270 */       if (!remove.contains(obj))
/* 271 */         list.add(obj); 
/*     */     } 
/* 274 */     return list;
/*     */   }
/*     */   
/*     */   public static List synchronizedList(List list) {
/* 301 */     return SynchronizedList.decorate(list);
/*     */   }
/*     */   
/*     */   public static List unmodifiableList(List list) {
/* 314 */     return UnmodifiableList.decorate(list);
/*     */   }
/*     */   
/*     */   public static List predicatedList(List list, Predicate predicate) {
/* 331 */     return PredicatedList.decorate(list, predicate);
/*     */   }
/*     */   
/*     */   public static List typedList(List list, Class type) {
/* 344 */     return TypedList.decorate(list, type);
/*     */   }
/*     */   
/*     */   public static List transformedList(List list, Transformer transformer) {
/* 360 */     return TransformedList.decorate(list, transformer);
/*     */   }
/*     */   
/*     */   public static List lazyList(List list, Factory factory) {
/* 393 */     return LazyList.decorate(list, factory);
/*     */   }
/*     */   
/*     */   public static List fixedSizeList(List list) {
/* 407 */     return FixedSizeList.decorate(list);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ListUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */