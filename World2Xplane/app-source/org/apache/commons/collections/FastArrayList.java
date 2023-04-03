/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ public class FastArrayList extends ArrayList {
/*      */   protected ArrayList list;
/*      */   
/*      */   protected boolean fast;
/*      */   
/*      */   public FastArrayList() {
/*  117 */     this.list = null;
/*  126 */     this.fast = false;
/*      */     this.list = new ArrayList();
/*      */   }
/*      */   
/*      */   public FastArrayList(int capacity) {
/*      */     this.list = null;
/*  126 */     this.fast = false;
/*      */     this.list = new ArrayList(capacity);
/*      */   }
/*      */   
/*      */   public FastArrayList(Collection collection) {
/*      */     this.list = null;
/*  126 */     this.fast = false;
/*      */     this.list = new ArrayList(collection);
/*      */   }
/*      */   
/*      */   public boolean getFast() {
/*  135 */     return this.fast;
/*      */   }
/*      */   
/*      */   public void setFast(boolean fast) {
/*  144 */     this.fast = fast;
/*      */   }
/*      */   
/*      */   public boolean add(Object element) {
/*  158 */     if (this.fast)
/*  159 */       synchronized (this) {
/*  160 */         ArrayList temp = (ArrayList)this.list.clone();
/*  161 */         boolean result = temp.add(element);
/*  162 */         this.list = temp;
/*  163 */         return result;
/*      */       }  
/*  166 */     synchronized (this.list) {
/*  167 */       return this.list.add(element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void add(int index, Object element) {
/*  185 */     if (this.fast) {
/*  186 */       synchronized (this) {
/*  187 */         ArrayList temp = (ArrayList)this.list.clone();
/*  188 */         temp.add(index, element);
/*  189 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  192 */       synchronized (this.list) {
/*  193 */         this.list.add(index, element);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection collection) {
/*  209 */     if (this.fast)
/*  210 */       synchronized (this) {
/*  211 */         ArrayList temp = (ArrayList)this.list.clone();
/*  212 */         boolean result = temp.addAll(collection);
/*  213 */         this.list = temp;
/*  214 */         return result;
/*      */       }  
/*  217 */     synchronized (this.list) {
/*  218 */       return this.list.addAll(collection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection collection) {
/*  237 */     if (this.fast)
/*  238 */       synchronized (this) {
/*  239 */         ArrayList temp = (ArrayList)this.list.clone();
/*  240 */         boolean result = temp.addAll(index, collection);
/*  241 */         this.list = temp;
/*  242 */         return result;
/*      */       }  
/*  245 */     synchronized (this.list) {
/*  246 */       return this.list.addAll(index, collection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clear() {
/*  262 */     if (this.fast) {
/*  263 */       synchronized (this) {
/*  264 */         ArrayList temp = (ArrayList)this.list.clone();
/*  265 */         temp.clear();
/*  266 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  269 */       synchronized (this.list) {
/*  270 */         this.list.clear();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object clone() {
/*  283 */     FastArrayList results = null;
/*  284 */     if (this.fast) {
/*  285 */       results = new FastArrayList(this.list);
/*      */     } else {
/*  287 */       synchronized (this.list) {
/*  288 */         results = new FastArrayList(this.list);
/*      */       } 
/*      */     } 
/*  291 */     results.setFast(getFast());
/*  292 */     return results;
/*      */   }
/*      */   
/*      */   public boolean contains(Object element) {
/*  304 */     if (this.fast)
/*  305 */       return this.list.contains(element); 
/*  307 */     synchronized (this.list) {
/*  308 */       return this.list.contains(element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection collection) {
/*  323 */     if (this.fast)
/*  324 */       return this.list.containsAll(collection); 
/*  326 */     synchronized (this.list) {
/*  327 */       return this.list.containsAll(collection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void ensureCapacity(int capacity) {
/*  343 */     if (this.fast) {
/*  344 */       synchronized (this) {
/*  345 */         ArrayList temp = (ArrayList)this.list.clone();
/*  346 */         temp.ensureCapacity(capacity);
/*  347 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  350 */       synchronized (this.list) {
/*  351 */         this.list.ensureCapacity(capacity);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/*  369 */     if (o == this)
/*  370 */       return true; 
/*  371 */     if (!(o instanceof List))
/*  372 */       return false; 
/*  373 */     List lo = (List)o;
/*  376 */     if (this.fast) {
/*  377 */       ListIterator li1 = this.list.listIterator();
/*  378 */       ListIterator li2 = lo.listIterator();
/*  379 */       while (li1.hasNext() && li2.hasNext()) {
/*  380 */         Object o1 = li1.next();
/*  381 */         Object o2 = li2.next();
/*  382 */         if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/*      */           continue; 
/*  383 */         return false;
/*      */       } 
/*  385 */       return (!li1.hasNext() && !li2.hasNext());
/*      */     } 
/*  387 */     synchronized (this.list) {
/*  388 */       ListIterator li1 = this.list.listIterator();
/*  389 */       ListIterator li2 = lo.listIterator();
/*  390 */       while (li1.hasNext() && li2.hasNext()) {
/*  391 */         Object o1 = li1.next();
/*  392 */         Object o2 = li2.next();
/*  393 */         if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/*      */           continue; 
/*  394 */         return false;
/*      */       } 
/*  396 */       return (!li1.hasNext() && !li2.hasNext());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/*  412 */     if (this.fast)
/*  413 */       return this.list.get(index); 
/*  415 */     synchronized (this.list) {
/*  416 */       return this.list.get(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  430 */     if (this.fast) {
/*  431 */       int hashCode = 1;
/*  432 */       Iterator i = this.list.iterator();
/*  433 */       while (i.hasNext()) {
/*  434 */         Object o = i.next();
/*  435 */         hashCode = 31 * hashCode + ((o == null) ? 0 : o.hashCode());
/*      */       } 
/*  437 */       return hashCode;
/*      */     } 
/*  439 */     synchronized (this.list) {
/*  440 */       int hashCode = 1;
/*  441 */       Iterator i = this.list.iterator();
/*  442 */       while (i.hasNext()) {
/*  443 */         Object o = i.next();
/*  444 */         hashCode = 31 * hashCode + ((o == null) ? 0 : o.hashCode());
/*      */       } 
/*  446 */       return hashCode;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int indexOf(Object element) {
/*  462 */     if (this.fast)
/*  463 */       return this.list.indexOf(element); 
/*  465 */     synchronized (this.list) {
/*  466 */       return this.list.indexOf(element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  478 */     if (this.fast)
/*  479 */       return this.list.isEmpty(); 
/*  481 */     synchronized (this.list) {
/*  482 */       return this.list.isEmpty();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/*  507 */     if (this.fast)
/*  508 */       return new ListIter(this, 0); 
/*  510 */     return this.list.iterator();
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object element) {
/*  524 */     if (this.fast)
/*  525 */       return this.list.lastIndexOf(element); 
/*  527 */     synchronized (this.list) {
/*  528 */       return this.list.lastIndexOf(element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/*  553 */     if (this.fast)
/*  554 */       return new ListIter(this, 0); 
/*  556 */     return this.list.listIterator();
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int index) {
/*  582 */     if (this.fast)
/*  583 */       return new ListIter(this, index); 
/*  585 */     return this.list.listIterator(index);
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/*  600 */     if (this.fast)
/*  601 */       synchronized (this) {
/*  602 */         ArrayList temp = (ArrayList)this.list.clone();
/*  603 */         Object result = temp.remove(index);
/*  604 */         this.list = temp;
/*  605 */         return result;
/*      */       }  
/*  608 */     synchronized (this.list) {
/*  609 */       return this.list.remove(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean remove(Object element) {
/*  624 */     if (this.fast)
/*  625 */       synchronized (this) {
/*  626 */         ArrayList temp = (ArrayList)this.list.clone();
/*  627 */         boolean result = temp.remove(element);
/*  628 */         this.list = temp;
/*  629 */         return result;
/*      */       }  
/*  632 */     synchronized (this.list) {
/*  633 */       return this.list.remove(element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection collection) {
/*  651 */     if (this.fast)
/*  652 */       synchronized (this) {
/*  653 */         ArrayList temp = (ArrayList)this.list.clone();
/*  654 */         boolean result = temp.removeAll(collection);
/*  655 */         this.list = temp;
/*  656 */         return result;
/*      */       }  
/*  659 */     synchronized (this.list) {
/*  660 */       return this.list.removeAll(collection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection collection) {
/*  678 */     if (this.fast)
/*  679 */       synchronized (this) {
/*  680 */         ArrayList temp = (ArrayList)this.list.clone();
/*  681 */         boolean result = temp.retainAll(collection);
/*  682 */         this.list = temp;
/*  683 */         return result;
/*      */       }  
/*  686 */     synchronized (this.list) {
/*  687 */       return this.list.retainAll(collection);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object set(int index, Object element) {
/*  709 */     if (this.fast)
/*  710 */       return this.list.set(index, element); 
/*  712 */     synchronized (this.list) {
/*  713 */       return this.list.set(index, element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int size() {
/*  725 */     if (this.fast)
/*  726 */       return this.list.size(); 
/*  728 */     synchronized (this.list) {
/*  729 */       return this.list.size();
/*      */     } 
/*      */   }
/*      */   
/*      */   public List subList(int fromIndex, int toIndex) {
/*  749 */     if (this.fast)
/*  750 */       return new SubList(this, fromIndex, toIndex); 
/*  752 */     return this.list.subList(fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/*  763 */     if (this.fast)
/*  764 */       return this.list.toArray(); 
/*  766 */     synchronized (this.list) {
/*  767 */       return this.list.toArray();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] array) {
/*  788 */     if (this.fast)
/*  789 */       return this.list.toArray(array); 
/*  791 */     synchronized (this.list) {
/*  792 */       return this.list.toArray(array);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/*  804 */     StringBuffer sb = new StringBuffer("FastArrayList[");
/*  805 */     sb.append(this.list.toString());
/*  806 */     sb.append("]");
/*  807 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public void trimToSize() {
/*  819 */     if (this.fast) {
/*  820 */       synchronized (this) {
/*  821 */         ArrayList temp = (ArrayList)this.list.clone();
/*  822 */         temp.trimToSize();
/*  823 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  826 */       synchronized (this.list) {
/*  827 */         this.list.trimToSize();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private class SubList implements List {
/*      */     private int first;
/*      */     
/*      */     private int last;
/*      */     
/*      */     private List expected;
/*      */     
/*      */     private final FastArrayList this$0;
/*      */     
/*      */     public SubList(FastArrayList this$0, int first, int last) {
/*  842 */       this.this$0 = this$0;
/*  843 */       this.first = first;
/*  844 */       this.last = last;
/*  845 */       this.expected = this$0.list;
/*      */     }
/*      */     
/*      */     private List get(List l) {
/*  849 */       if (this.this$0.list != this.expected)
/*  850 */         throw new ConcurrentModificationException(); 
/*  852 */       return l.subList(this.first, this.last);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  856 */       if (this.this$0.fast) {
/*  857 */         synchronized (this.this$0) {
/*  858 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  859 */           get(temp).clear();
/*  860 */           this.last = this.first;
/*  861 */           this.this$0.list = temp;
/*  862 */           this.expected = temp;
/*      */         } 
/*      */       } else {
/*  865 */         synchronized (this.this$0.list) {
/*  866 */           get(this.expected).clear();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*  872 */       if (this.this$0.fast)
/*  873 */         synchronized (this.this$0) {
/*  874 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  875 */           boolean r = get(temp).remove(o);
/*  876 */           if (r)
/*  876 */             this.last--; 
/*  877 */           this.this$0.list = temp;
/*  878 */           this.expected = temp;
/*  879 */           return r;
/*      */         }  
/*  882 */       synchronized (this.this$0.list) {
/*  883 */         return get(this.expected).remove(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection o) {
/*  889 */       if (this.this$0.fast)
/*  890 */         synchronized (this.this$0) {
/*  891 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  892 */           List sub = get(temp);
/*  893 */           boolean r = sub.removeAll(o);
/*  894 */           if (r)
/*  894 */             this.last = this.first + sub.size(); 
/*  895 */           this.this$0.list = temp;
/*  896 */           this.expected = temp;
/*  897 */           return r;
/*      */         }  
/*  900 */       synchronized (this.this$0.list) {
/*  901 */         return get(this.expected).removeAll(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection o) {
/*  907 */       if (this.this$0.fast)
/*  908 */         synchronized (this.this$0) {
/*  909 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  910 */           List sub = get(temp);
/*  911 */           boolean r = sub.retainAll(o);
/*  912 */           if (r)
/*  912 */             this.last = this.first + sub.size(); 
/*  913 */           this.this$0.list = temp;
/*  914 */           this.expected = temp;
/*  915 */           return r;
/*      */         }  
/*  918 */       synchronized (this.this$0.list) {
/*  919 */         return get(this.expected).retainAll(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int size() {
/*  925 */       if (this.this$0.fast)
/*  926 */         return get(this.expected).size(); 
/*  928 */       synchronized (this.this$0.list) {
/*  929 */         return get(this.expected).size();
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  936 */       if (this.this$0.fast)
/*  937 */         return get(this.expected).isEmpty(); 
/*  939 */       synchronized (this.this$0.list) {
/*  940 */         return get(this.expected).isEmpty();
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/*  946 */       if (this.this$0.fast)
/*  947 */         return get(this.expected).contains(o); 
/*  949 */       synchronized (this.this$0.list) {
/*  950 */         return get(this.expected).contains(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection o) {
/*  956 */       if (this.this$0.fast)
/*  957 */         return get(this.expected).containsAll(o); 
/*  959 */       synchronized (this.this$0.list) {
/*  960 */         return get(this.expected).containsAll(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] toArray(Object[] o) {
/*  966 */       if (this.this$0.fast)
/*  967 */         return get(this.expected).toArray(o); 
/*  969 */       synchronized (this.this$0.list) {
/*  970 */         return get(this.expected).toArray(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  976 */       if (this.this$0.fast)
/*  977 */         return get(this.expected).toArray(); 
/*  979 */       synchronized (this.this$0.list) {
/*  980 */         return get(this.expected).toArray();
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  987 */       if (o == this)
/*  987 */         return true; 
/*  988 */       if (this.this$0.fast)
/*  989 */         return get(this.expected).equals(o); 
/*  991 */       synchronized (this.this$0.list) {
/*  992 */         return get(this.expected).equals(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  998 */       if (this.this$0.fast)
/*  999 */         return get(this.expected).hashCode(); 
/* 1001 */       synchronized (this.this$0.list) {
/* 1002 */         return get(this.expected).hashCode();
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean add(Object o) {
/* 1008 */       if (this.this$0.fast)
/* 1009 */         synchronized (this.this$0) {
/* 1010 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1011 */           boolean r = get(temp).add(o);
/* 1012 */           if (r)
/* 1012 */             this.last++; 
/* 1013 */           this.this$0.list = temp;
/* 1014 */           this.expected = temp;
/* 1015 */           return r;
/*      */         }  
/* 1018 */       synchronized (this.this$0.list) {
/* 1019 */         return get(this.expected).add(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection o) {
/* 1025 */       if (this.this$0.fast)
/* 1026 */         synchronized (this.this$0) {
/* 1027 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1028 */           boolean r = get(temp).addAll(o);
/* 1029 */           if (r)
/* 1029 */             this.last += o.size(); 
/* 1030 */           this.this$0.list = temp;
/* 1031 */           this.expected = temp;
/* 1032 */           return r;
/*      */         }  
/* 1035 */       synchronized (this.this$0.list) {
/* 1036 */         return get(this.expected).addAll(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void add(int i, Object o) {
/* 1042 */       if (this.this$0.fast) {
/* 1043 */         synchronized (this.this$0) {
/* 1044 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1045 */           get(temp).add(i, o);
/* 1046 */           this.last++;
/* 1047 */           this.this$0.list = temp;
/* 1048 */           this.expected = temp;
/*      */         } 
/*      */       } else {
/* 1051 */         synchronized (this.this$0.list) {
/* 1052 */           get(this.expected).add(i, o);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean addAll(int i, Collection o) {
/* 1058 */       if (this.this$0.fast)
/* 1059 */         synchronized (this.this$0) {
/* 1060 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1061 */           boolean r = get(temp).addAll(i, o);
/* 1062 */           this.this$0.list = temp;
/* 1063 */           if (r)
/* 1063 */             this.last += o.size(); 
/* 1064 */           this.expected = temp;
/* 1065 */           return r;
/*      */         }  
/* 1068 */       synchronized (this.this$0.list) {
/* 1069 */         return get(this.expected).addAll(i, o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object remove(int i) {
/* 1075 */       if (this.this$0.fast)
/* 1076 */         synchronized (this.this$0) {
/* 1077 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1078 */           Object o = get(temp).remove(i);
/* 1079 */           this.last--;
/* 1080 */           this.this$0.list = temp;
/* 1081 */           this.expected = temp;
/* 1082 */           return o;
/*      */         }  
/* 1085 */       synchronized (this.this$0.list) {
/* 1086 */         return get(this.expected).remove(i);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object set(int i, Object a) {
/* 1092 */       if (this.this$0.fast)
/* 1093 */         synchronized (this.this$0) {
/* 1094 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1095 */           Object o = get(temp).set(i, a);
/* 1096 */           this.this$0.list = temp;
/* 1097 */           this.expected = temp;
/* 1098 */           return o;
/*      */         }  
/* 1101 */       synchronized (this.this$0.list) {
/* 1102 */         return get(this.expected).set(i, a);
/*      */       } 
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/* 1109 */       return new SubListIter(this, 0);
/*      */     }
/*      */     
/*      */     public ListIterator listIterator() {
/* 1113 */       return new SubListIter(this, 0);
/*      */     }
/*      */     
/*      */     public ListIterator listIterator(int i) {
/* 1117 */       return new SubListIter(this, i);
/*      */     }
/*      */     
/*      */     public Object get(int i) {
/* 1122 */       if (this.this$0.fast)
/* 1123 */         return get(this.expected).get(i); 
/* 1125 */       synchronized (this.this$0.list) {
/* 1126 */         return get(this.expected).get(i);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int indexOf(Object o) {
/* 1132 */       if (this.this$0.fast)
/* 1133 */         return get(this.expected).indexOf(o); 
/* 1135 */       synchronized (this.this$0.list) {
/* 1136 */         return get(this.expected).indexOf(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int lastIndexOf(Object o) {
/* 1143 */       if (this.this$0.fast)
/* 1144 */         return get(this.expected).lastIndexOf(o); 
/* 1146 */       synchronized (this.this$0.list) {
/* 1147 */         return get(this.expected).lastIndexOf(o);
/*      */       } 
/*      */     }
/*      */     
/*      */     public List subList(int f, int l) {
/* 1154 */       if (this.this$0.list != this.expected)
/* 1155 */         throw new ConcurrentModificationException(); 
/* 1157 */       return new SubList(this.this$0, this.first + f, f + l);
/*      */     }
/*      */     
/*      */     private class SubListIter implements ListIterator {
/*      */       private List expected;
/*      */       
/*      */       private ListIterator iter;
/*      */       
/*      */       private int lastReturnedIndex;
/*      */       
/*      */       private final FastArrayList.SubList this$1;
/*      */       
/*      */       public SubListIter(FastArrayList.SubList this$0, int i) {
/* 1168 */         this.this$1 = this$0;
/*      */         this.lastReturnedIndex = -1;
/* 1169 */         this.expected = this$0.this$0.list;
/* 1170 */         this.iter = this$0.get(this.expected).listIterator(i);
/*      */       }
/*      */       
/*      */       private void checkMod() {
/* 1174 */         if (this.this$1.this$0.list != this.expected)
/* 1175 */           throw new ConcurrentModificationException(); 
/*      */       }
/*      */       
/*      */       List get() {
/* 1180 */         return this.this$1.get(this.expected);
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/* 1184 */         checkMod();
/* 1185 */         return this.iter.hasNext();
/*      */       }
/*      */       
/*      */       public Object next() {
/* 1189 */         checkMod();
/* 1190 */         this.lastReturnedIndex = this.iter.nextIndex();
/* 1191 */         return this.iter.next();
/*      */       }
/*      */       
/*      */       public boolean hasPrevious() {
/* 1195 */         checkMod();
/* 1196 */         return this.iter.hasPrevious();
/*      */       }
/*      */       
/*      */       public Object previous() {
/* 1200 */         checkMod();
/* 1201 */         this.lastReturnedIndex = this.iter.previousIndex();
/* 1202 */         return this.iter.previous();
/*      */       }
/*      */       
/*      */       public int previousIndex() {
/* 1206 */         checkMod();
/* 1207 */         return this.iter.previousIndex();
/*      */       }
/*      */       
/*      */       public int nextIndex() {
/* 1211 */         checkMod();
/* 1212 */         return this.iter.nextIndex();
/*      */       }
/*      */       
/*      */       public void remove() {
/* 1216 */         checkMod();
/* 1217 */         if (this.lastReturnedIndex < 0)
/* 1218 */           throw new IllegalStateException(); 
/* 1220 */         get().remove(this.lastReturnedIndex);
/* 1221 */         this.this$1.last--;
/* 1222 */         this.expected = this.this$1.this$0.list;
/* 1223 */         this.iter = get().listIterator(this.lastReturnedIndex);
/* 1224 */         this.lastReturnedIndex = -1;
/*      */       }
/*      */       
/*      */       public void set(Object o) {
/* 1228 */         checkMod();
/* 1229 */         if (this.lastReturnedIndex < 0)
/* 1230 */           throw new IllegalStateException(); 
/* 1232 */         get().set(this.lastReturnedIndex, o);
/* 1233 */         this.expected = this.this$1.this$0.list;
/* 1234 */         this.iter = get().listIterator(previousIndex() + 1);
/*      */       }
/*      */       
/*      */       public void add(Object o) {
/* 1238 */         checkMod();
/* 1239 */         int i = nextIndex();
/* 1240 */         get().add(i, o);
/* 1241 */         this.this$1.last++;
/* 1242 */         this.expected = this.this$1.this$0.list;
/* 1243 */         this.iter = get().listIterator(i + 1);
/* 1244 */         this.lastReturnedIndex = -1;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private class ListIter implements ListIterator {
/*      */     private List expected;
/*      */     
/*      */     private ListIterator iter;
/*      */     
/*      */     private int lastReturnedIndex;
/*      */     
/*      */     private final FastArrayList this$0;
/*      */     
/*      */     public ListIter(FastArrayList this$0, int i) {
/* 1261 */       this.this$0 = this$0;
/*      */       this.lastReturnedIndex = -1;
/* 1262 */       this.expected = this$0.list;
/* 1263 */       this.iter = get().listIterator(i);
/*      */     }
/*      */     
/*      */     private void checkMod() {
/* 1267 */       if (this.this$0.list != this.expected)
/* 1268 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */     
/*      */     List get() {
/* 1273 */       return this.expected;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1277 */       return this.iter.hasNext();
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1281 */       this.lastReturnedIndex = this.iter.nextIndex();
/* 1282 */       return this.iter.next();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1286 */       return this.iter.hasPrevious();
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1290 */       this.lastReturnedIndex = this.iter.previousIndex();
/* 1291 */       return this.iter.previous();
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1295 */       return this.iter.previousIndex();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1299 */       return this.iter.nextIndex();
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1303 */       checkMod();
/* 1304 */       if (this.lastReturnedIndex < 0)
/* 1305 */         throw new IllegalStateException(); 
/* 1307 */       get().remove(this.lastReturnedIndex);
/* 1308 */       this.expected = this.this$0.list;
/* 1309 */       this.iter = get().listIterator(this.lastReturnedIndex);
/* 1310 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1314 */       checkMod();
/* 1315 */       if (this.lastReturnedIndex < 0)
/* 1316 */         throw new IllegalStateException(); 
/* 1318 */       get().set(this.lastReturnedIndex, o);
/* 1319 */       this.expected = this.this$0.list;
/* 1320 */       this.iter = get().listIterator(previousIndex() + 1);
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1324 */       checkMod();
/* 1325 */       int i = nextIndex();
/* 1326 */       get().add(i, o);
/* 1327 */       this.expected = this.this$0.list;
/* 1328 */       this.iter = get().listIterator(i + 1);
/* 1329 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\FastArrayList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */