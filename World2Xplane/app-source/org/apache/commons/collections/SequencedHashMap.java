/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.list.UnmodifiableList;
/*      */ 
/*      */ public class SequencedHashMap implements Map, Cloneable, Externalizable {
/*      */   private Entry sentinel;
/*      */   
/*      */   private HashMap entries;
/*      */   
/*      */   private static class Entry implements Map.Entry, KeyValue {
/*      */     private final Object key;
/*      */     
/*      */     private Object value;
/*      */     
/*   84 */     Entry next = null;
/*      */     
/*   85 */     Entry prev = null;
/*      */     
/*      */     public Entry(Object key, Object value) {
/*   88 */       this.key = key;
/*   89 */       this.value = value;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*   94 */       return this.key;
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*   99 */       return this.value;
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  104 */       Object oldValue = this.value;
/*  105 */       this.value = value;
/*  106 */       return oldValue;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  111 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  115 */       if (obj == null)
/*  116 */         return false; 
/*  117 */       if (obj == this)
/*  118 */         return true; 
/*  119 */       if (!(obj instanceof Map.Entry))
/*  120 */         return false; 
/*  122 */       Map.Entry other = (Map.Entry)obj;
/*  125 */       if ((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey()))
/*  125 */         if ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())); 
/*  125 */       return false;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  130 */       return "[" + getKey() + "=" + getValue() + "]";
/*      */     }
/*      */   }
/*      */   
/*      */   private static final Entry createSentinel() {
/*  140 */     Entry s = new Entry(null, null);
/*  141 */     s.prev = s;
/*  142 */     s.next = s;
/*  143 */     return s;
/*      */   }
/*      */   
/*  162 */   private transient long modCount = 0L;
/*      */   
/*      */   private static final int KEY = 0;
/*      */   
/*      */   private static final int VALUE = 1;
/*      */   
/*      */   private static final int ENTRY = 2;
/*      */   
/*      */   private static final int REMOVED_MASK = -2147483648;
/*      */   
/*      */   private static final long serialVersionUID = 3380552487888102930L;
/*      */   
/*      */   public SequencedHashMap() {
/*  169 */     this.sentinel = createSentinel();
/*  170 */     this.entries = new HashMap();
/*      */   }
/*      */   
/*      */   public SequencedHashMap(int initialSize) {
/*  182 */     this.sentinel = createSentinel();
/*  183 */     this.entries = new HashMap(initialSize);
/*      */   }
/*      */   
/*      */   public SequencedHashMap(int initialSize, float loadFactor) {
/*  197 */     this.sentinel = createSentinel();
/*  198 */     this.entries = new HashMap(initialSize, loadFactor);
/*      */   }
/*      */   
/*      */   public SequencedHashMap(Map m) {
/*  207 */     this();
/*  208 */     putAll(m);
/*      */   }
/*      */   
/*      */   private void removeEntry(Entry entry) {
/*  216 */     entry.next.prev = entry.prev;
/*  217 */     entry.prev.next = entry.next;
/*      */   }
/*      */   
/*      */   private void insertEntry(Entry entry) {
/*  225 */     entry.next = this.sentinel;
/*  226 */     entry.prev = this.sentinel.prev;
/*  227 */     this.sentinel.prev.next = entry;
/*  228 */     this.sentinel.prev = entry;
/*      */   }
/*      */   
/*      */   public int size() {
/*  238 */     return this.entries.size();
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  247 */     return (this.sentinel.next == this.sentinel);
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  255 */     return this.entries.containsKey(key);
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  270 */     if (value == null) {
/*  271 */       for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  272 */         if (pos.getValue() == null)
/*  273 */           return true; 
/*      */       } 
/*      */     } else {
/*  276 */       for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  277 */         if (value.equals(pos.getValue()))
/*  278 */           return true; 
/*      */       } 
/*      */     } 
/*  281 */     return false;
/*      */   }
/*      */   
/*      */   public Object get(Object o) {
/*  289 */     Entry entry = (Entry)this.entries.get(o);
/*  290 */     if (entry == null)
/*  291 */       return null; 
/*  293 */     return entry.getValue();
/*      */   }
/*      */   
/*      */   public Map.Entry getFirst() {
/*  310 */     return isEmpty() ? null : this.sentinel.next;
/*      */   }
/*      */   
/*      */   public Object getFirstKey() {
/*  330 */     return this.sentinel.next.getKey();
/*      */   }
/*      */   
/*      */   public Object getFirstValue() {
/*  350 */     return this.sentinel.next.getValue();
/*      */   }
/*      */   
/*      */   public Map.Entry getLast() {
/*  377 */     return isEmpty() ? null : this.sentinel.prev;
/*      */   }
/*      */   
/*      */   public Object getLastKey() {
/*  397 */     return this.sentinel.prev.getKey();
/*      */   }
/*      */   
/*      */   public Object getLastValue() {
/*  417 */     return this.sentinel.prev.getValue();
/*      */   }
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  424 */     this.modCount++;
/*  426 */     Object oldValue = null;
/*  429 */     Entry e = (Entry)this.entries.get(key);
/*  432 */     if (e != null) {
/*  434 */       removeEntry(e);
/*  437 */       oldValue = e.setValue(value);
/*      */     } else {
/*  446 */       e = new Entry(key, value);
/*  447 */       this.entries.put(key, e);
/*      */     } 
/*  452 */     insertEntry(e);
/*  454 */     return oldValue;
/*      */   }
/*      */   
/*      */   public Object remove(Object key) {
/*  461 */     Entry e = removeImpl(key);
/*  462 */     return (e == null) ? null : e.getValue();
/*      */   }
/*      */   
/*      */   private Entry removeImpl(Object key) {
/*  470 */     Entry e = (Entry)this.entries.remove(key);
/*  471 */     if (e == null)
/*  472 */       return null; 
/*  473 */     this.modCount++;
/*  474 */     removeEntry(e);
/*  475 */     return e;
/*      */   }
/*      */   
/*      */   public void putAll(Map t) {
/*  489 */     Iterator iter = t.entrySet().iterator();
/*  490 */     while (iter.hasNext()) {
/*  491 */       Map.Entry entry = iter.next();
/*  492 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clear() {
/*  500 */     this.modCount++;
/*  503 */     this.entries.clear();
/*  506 */     this.sentinel.next = this.sentinel;
/*  507 */     this.sentinel.prev = this.sentinel;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  514 */     if (obj == null)
/*  515 */       return false; 
/*  516 */     if (obj == this)
/*  517 */       return true; 
/*  519 */     if (!(obj instanceof Map))
/*  520 */       return false; 
/*  522 */     return entrySet().equals(((Map)obj).entrySet());
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  529 */     return entrySet().hashCode();
/*      */   }
/*      */   
/*      */   public String toString() {
/*  540 */     StringBuffer buf = new StringBuffer();
/*  541 */     buf.append('[');
/*  542 */     for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  543 */       buf.append(pos.getKey());
/*  544 */       buf.append('=');
/*  545 */       buf.append(pos.getValue());
/*  546 */       if (pos.next != this.sentinel)
/*  547 */         buf.append(','); 
/*      */     } 
/*  550 */     buf.append(']');
/*  552 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public Set keySet() {
/*  559 */     return new AbstractSet(this) {
/*      */         private final SequencedHashMap this$0;
/*      */         
/*      */         public Iterator iterator() {
/*  563 */           return new SequencedHashMap.OrderedIterator(this.this$0, 0);
/*      */         }
/*      */         
/*      */         public boolean remove(Object o) {
/*  566 */           SequencedHashMap.Entry e = this.this$0.removeImpl(o);
/*  567 */           return (e != null);
/*      */         }
/*      */         
/*      */         public void clear() {
/*  572 */           this.this$0.clear();
/*      */         }
/*      */         
/*      */         public int size() {
/*  575 */           return this.this$0.size();
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  578 */           return this.this$0.isEmpty();
/*      */         }
/*      */         
/*      */         public boolean contains(Object o) {
/*  581 */           return this.this$0.containsKey(o);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public Collection values() {
/*  591 */     return new AbstractCollection(this) {
/*      */         private final SequencedHashMap this$0;
/*      */         
/*      */         public Iterator iterator() {
/*  594 */           return new SequencedHashMap.OrderedIterator(this.this$0, 1);
/*      */         }
/*      */         
/*      */         public boolean remove(Object value) {
/*  600 */           if (value == null) {
/*  601 */             for (SequencedHashMap.Entry pos = this.this$0.sentinel.next; pos != this.this$0.sentinel; pos = pos.next) {
/*  602 */               if (pos.getValue() == null) {
/*  603 */                 this.this$0.removeImpl(pos.getKey());
/*  604 */                 return true;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  608 */             for (SequencedHashMap.Entry pos = this.this$0.sentinel.next; pos != this.this$0.sentinel; pos = pos.next) {
/*  609 */               if (value.equals(pos.getValue())) {
/*  610 */                 this.this$0.removeImpl(pos.getKey());
/*  611 */                 return true;
/*      */               } 
/*      */             } 
/*      */           } 
/*  616 */           return false;
/*      */         }
/*      */         
/*      */         public void clear() {
/*  621 */           this.this$0.clear();
/*      */         }
/*      */         
/*      */         public int size() {
/*  624 */           return this.this$0.size();
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  627 */           return this.this$0.isEmpty();
/*      */         }
/*      */         
/*      */         public boolean contains(Object o) {
/*  630 */           return this.this$0.containsValue(o);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public Set entrySet() {
/*  639 */     return new AbstractSet(this) {
/*      */         private final SequencedHashMap this$0;
/*      */         
/*      */         private SequencedHashMap.Entry findEntry(Object o) {
/*  642 */           if (o == null)
/*  643 */             return null; 
/*  644 */           if (!(o instanceof Map.Entry))
/*  645 */             return null; 
/*  647 */           Map.Entry e = (Map.Entry)o;
/*  648 */           SequencedHashMap.Entry entry = (SequencedHashMap.Entry)this.this$0.entries.get(e.getKey());
/*  649 */           if (entry != null && entry.equals(e))
/*  650 */             return entry; 
/*  652 */           return null;
/*      */         }
/*      */         
/*      */         public Iterator iterator() {
/*  657 */           return new SequencedHashMap.OrderedIterator(this.this$0, 2);
/*      */         }
/*      */         
/*      */         public boolean remove(Object o) {
/*  660 */           SequencedHashMap.Entry e = findEntry(o);
/*  661 */           if (e == null)
/*  662 */             return false; 
/*  664 */           return (this.this$0.removeImpl(e.getKey()) != null);
/*      */         }
/*      */         
/*      */         public void clear() {
/*  669 */           this.this$0.clear();
/*      */         }
/*      */         
/*      */         public int size() {
/*  672 */           return this.this$0.size();
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  675 */           return this.this$0.isEmpty();
/*      */         }
/*      */         
/*      */         public boolean contains(Object o) {
/*  678 */           return (findEntry(o) != null);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   private class OrderedIterator implements Iterator {
/*      */     private int returnType;
/*      */     
/*      */     private SequencedHashMap.Entry pos;
/*      */     
/*      */     private transient long expectedModCount;
/*      */     
/*      */     private final SequencedHashMap this$0;
/*      */     
/*      */     public OrderedIterator(SequencedHashMap this$0, int returnType) {
/*  720 */       this.this$0 = this$0;
/*      */       this.pos = this.this$0.sentinel;
/*      */       this.expectedModCount = this.this$0.modCount;
/*  731 */       this.returnType = returnType | Integer.MIN_VALUE;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  742 */       return (this.pos.next != this.this$0.sentinel);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  757 */       if (this.this$0.modCount != this.expectedModCount)
/*  758 */         throw new ConcurrentModificationException(); 
/*  760 */       if (this.pos.next == this.this$0.sentinel)
/*  761 */         throw new NoSuchElementException(); 
/*  765 */       this.returnType &= Integer.MAX_VALUE;
/*  767 */       this.pos = this.pos.next;
/*  768 */       switch (this.returnType) {
/*      */         case 0:
/*  770 */           return this.pos.getKey();
/*      */         case 1:
/*  772 */           return this.pos.getValue();
/*      */         case 2:
/*  774 */           return this.pos;
/*      */       } 
/*  777 */       throw new Error("bad iterator type: " + this.returnType);
/*      */     }
/*      */     
/*      */     public void remove() {
/*  794 */       if ((this.returnType & Integer.MIN_VALUE) != 0)
/*  795 */         throw new IllegalStateException("remove() must follow next()"); 
/*  797 */       if (this.this$0.modCount != this.expectedModCount)
/*  798 */         throw new ConcurrentModificationException(); 
/*  801 */       this.this$0.removeImpl(this.pos.getKey());
/*  804 */       this.expectedModCount++;
/*  807 */       this.returnType |= Integer.MIN_VALUE;
/*      */     }
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  829 */     SequencedHashMap map = (SequencedHashMap)super.clone();
/*  832 */     map.sentinel = createSentinel();
/*  836 */     map.entries = new HashMap();
/*  839 */     map.putAll(this);
/*  849 */     return map;
/*      */   }
/*      */   
/*      */   private Map.Entry getEntry(int index) {
/*  859 */     Entry pos = this.sentinel;
/*  861 */     if (index < 0)
/*  862 */       throw new ArrayIndexOutOfBoundsException(index + " < 0"); 
/*  866 */     int i = -1;
/*  867 */     while (i < index - 1 && pos.next != this.sentinel) {
/*  868 */       i++;
/*  869 */       pos = pos.next;
/*      */     } 
/*  874 */     if (pos.next == this.sentinel)
/*  875 */       throw new ArrayIndexOutOfBoundsException(index + " >= " + (i + 1)); 
/*  878 */     return pos.next;
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/*  890 */     return getEntry(index).getKey();
/*      */   }
/*      */   
/*      */   public Object getValue(int index) {
/*  902 */     return getEntry(index).getValue();
/*      */   }
/*      */   
/*      */   public int indexOf(Object key) {
/*  912 */     Entry e = (Entry)this.entries.get(key);
/*  913 */     if (e == null)
/*  914 */       return -1; 
/*  916 */     int pos = 0;
/*  917 */     while (e.prev != this.sentinel) {
/*  918 */       pos++;
/*  919 */       e = e.prev;
/*      */     } 
/*  921 */     return pos;
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/*  930 */     return keySet().iterator();
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object key) {
/*  941 */     return indexOf(key);
/*      */   }
/*      */   
/*      */   public List sequence() {
/*  959 */     List l = new ArrayList(size());
/*  960 */     Iterator iter = keySet().iterator();
/*  961 */     while (iter.hasNext())
/*  962 */       l.add(iter.next()); 
/*  965 */     return UnmodifiableList.decorate(l);
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/*  979 */     return remove(get(index));
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  992 */     int size = in.readInt();
/*  993 */     for (int i = 0; i < size; i++) {
/*  994 */       Object key = in.readObject();
/*  995 */       Object value = in.readObject();
/*  996 */       put(key, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/* 1007 */     out.writeInt(size());
/* 1008 */     for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/* 1009 */       out.writeObject(pos.getKey());
/* 1010 */       out.writeObject(pos.getValue());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\SequencedHashMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */