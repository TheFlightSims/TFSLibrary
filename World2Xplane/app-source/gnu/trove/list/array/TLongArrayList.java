/*      */ package gnu.trove.list.array;
/*      */ 
/*      */ import gnu.trove.TLongCollection;
/*      */ import gnu.trove.function.TLongFunction;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.iterator.TLongIterator;
/*      */ import gnu.trove.list.TLongList;
/*      */ import gnu.trove.procedure.TLongProcedure;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class TLongArrayList implements TLongList, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*      */   protected long[] _data;
/*      */   
/*      */   protected int _pos;
/*      */   
/*      */   protected static final int DEFAULT_CAPACITY = 10;
/*      */   
/*      */   protected long no_entry_value;
/*      */   
/*      */   public TLongArrayList() {
/*   67 */     this(10, 0L);
/*      */   }
/*      */   
/*      */   public TLongArrayList(int capacity) {
/*   79 */     this(capacity, 0L);
/*      */   }
/*      */   
/*      */   public TLongArrayList(int capacity, long no_entry_value) {
/*   91 */     this._data = new long[capacity];
/*   92 */     this._pos = 0;
/*   93 */     this.no_entry_value = no_entry_value;
/*      */   }
/*      */   
/*      */   public TLongArrayList(TLongCollection collection) {
/*  103 */     this(collection.size());
/*  104 */     addAll(collection);
/*      */   }
/*      */   
/*      */   public TLongArrayList(long[] values) {
/*  118 */     this(values.length);
/*  119 */     add(values);
/*      */   }
/*      */   
/*      */   protected TLongArrayList(long[] values, long no_entry_value, boolean wrap) {
/*  123 */     if (!wrap)
/*  124 */       throw new IllegalStateException("Wrong call"); 
/*  126 */     if (values == null)
/*  127 */       throw new IllegalArgumentException("values can not be null"); 
/*  129 */     this._data = values;
/*  130 */     this._pos = values.length;
/*  131 */     this.no_entry_value = no_entry_value;
/*      */   }
/*      */   
/*      */   public static TLongArrayList wrap(long[] values) {
/*  144 */     return wrap(values, 0L);
/*      */   }
/*      */   
/*      */   public static TLongArrayList wrap(long[] values, long no_entry_value) {
/*  158 */     return new TLongArrayList(values, no_entry_value, true) {
/*      */         public void ensureCapacity(int capacity) {
/*  164 */           if (capacity > this._data.length)
/*  165 */             throw new IllegalStateException("Can not grow ArrayList wrapped external array"); 
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public long getNoEntryValue() {
/*  172 */     return this.no_entry_value;
/*      */   }
/*      */   
/*      */   public void ensureCapacity(int capacity) {
/*  184 */     if (capacity > this._data.length) {
/*  185 */       int newCap = Math.max(this._data.length << 1, capacity);
/*  186 */       long[] tmp = new long[newCap];
/*  187 */       System.arraycopy(this._data, 0, tmp, 0, this._data.length);
/*  188 */       this._data = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int size() {
/*  195 */     return this._pos;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  201 */     return (this._pos == 0);
/*      */   }
/*      */   
/*      */   public void trimToSize() {
/*  209 */     if (this._data.length > size()) {
/*  210 */       long[] tmp = new long[size()];
/*  211 */       toArray(tmp, 0, tmp.length);
/*  212 */       this._data = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean add(long val) {
/*  221 */     ensureCapacity(this._pos + 1);
/*  222 */     this._data[this._pos++] = val;
/*  223 */     return true;
/*      */   }
/*      */   
/*      */   public void add(long[] vals) {
/*  229 */     add(vals, 0, vals.length);
/*      */   }
/*      */   
/*      */   public void add(long[] vals, int offset, int length) {
/*  235 */     ensureCapacity(this._pos + length);
/*  236 */     System.arraycopy(vals, offset, this._data, this._pos, length);
/*  237 */     this._pos += length;
/*      */   }
/*      */   
/*      */   public void insert(int offset, long value) {
/*  243 */     if (offset == this._pos) {
/*  244 */       add(value);
/*      */       return;
/*      */     } 
/*  247 */     ensureCapacity(this._pos + 1);
/*  249 */     System.arraycopy(this._data, offset, this._data, offset + 1, this._pos - offset);
/*  251 */     this._data[offset] = value;
/*  252 */     this._pos++;
/*      */   }
/*      */   
/*      */   public void insert(int offset, long[] values) {
/*  258 */     insert(offset, values, 0, values.length);
/*      */   }
/*      */   
/*      */   public void insert(int offset, long[] values, int valOffset, int len) {
/*  264 */     if (offset == this._pos) {
/*  265 */       add(values, valOffset, len);
/*      */       return;
/*      */     } 
/*  269 */     ensureCapacity(this._pos + len);
/*  271 */     System.arraycopy(this._data, offset, this._data, offset + len, this._pos - offset);
/*  273 */     System.arraycopy(values, valOffset, this._data, offset, len);
/*  274 */     this._pos += len;
/*      */   }
/*      */   
/*      */   public long get(int offset) {
/*  280 */     if (offset >= this._pos)
/*  281 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  283 */     return this._data[offset];
/*      */   }
/*      */   
/*      */   public long getQuick(int offset) {
/*  291 */     return this._data[offset];
/*      */   }
/*      */   
/*      */   public long set(int offset, long val) {
/*  297 */     if (offset >= this._pos)
/*  298 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  301 */     long prev_val = this._data[offset];
/*  302 */     this._data[offset] = val;
/*  303 */     return prev_val;
/*      */   }
/*      */   
/*      */   public long replace(int offset, long val) {
/*  309 */     if (offset >= this._pos)
/*  310 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  312 */     long old = this._data[offset];
/*  313 */     this._data[offset] = val;
/*  314 */     return old;
/*      */   }
/*      */   
/*      */   public void set(int offset, long[] values) {
/*  320 */     set(offset, values, 0, values.length);
/*      */   }
/*      */   
/*      */   public void set(int offset, long[] values, int valOffset, int length) {
/*  326 */     if (offset < 0 || offset + length > this._pos)
/*  327 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  329 */     System.arraycopy(values, valOffset, this._data, offset, length);
/*      */   }
/*      */   
/*      */   public void setQuick(int offset, long val) {
/*  337 */     this._data[offset] = val;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  343 */     clear(10);
/*      */   }
/*      */   
/*      */   public void clear(int capacity) {
/*  352 */     this._data = new long[capacity];
/*  353 */     this._pos = 0;
/*      */   }
/*      */   
/*      */   public void reset() {
/*  363 */     this._pos = 0;
/*  364 */     Arrays.fill(this._data, this.no_entry_value);
/*      */   }
/*      */   
/*      */   public void resetQuick() {
/*  377 */     this._pos = 0;
/*      */   }
/*      */   
/*      */   public boolean remove(long value) {
/*  383 */     for (int index = 0; index < this._pos; index++) {
/*  384 */       if (value == this._data[index]) {
/*  385 */         remove(index, 1);
/*  386 */         return true;
/*      */       } 
/*      */     } 
/*  389 */     return false;
/*      */   }
/*      */   
/*      */   public long removeAt(int offset) {
/*  395 */     long old = get(offset);
/*  396 */     remove(offset, 1);
/*  397 */     return old;
/*      */   }
/*      */   
/*      */   public void remove(int offset, int length) {
/*  403 */     if (length == 0)
/*      */       return; 
/*  404 */     if (offset < 0 || offset >= this._pos)
/*  405 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  408 */     if (offset == 0) {
/*  410 */       System.arraycopy(this._data, length, this._data, 0, this._pos - length);
/*  412 */     } else if (this._pos - length != offset) {
/*  418 */       System.arraycopy(this._data, offset + length, this._data, offset, this._pos - offset + length);
/*      */     } 
/*  421 */     this._pos -= length;
/*      */   }
/*      */   
/*      */   public TLongIterator iterator() {
/*  430 */     return new TLongArrayIterator(0);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection<?> collection) {
/*  436 */     for (Object element : collection) {
/*  437 */       if (element instanceof Long) {
/*  438 */         long c = ((Long)element).longValue();
/*  439 */         if (!contains(c))
/*  440 */           return false; 
/*      */         continue;
/*      */       } 
/*  443 */       return false;
/*      */     } 
/*  447 */     return true;
/*      */   }
/*      */   
/*      */   public boolean containsAll(TLongCollection collection) {
/*  453 */     if (this == collection)
/*  454 */       return true; 
/*  456 */     TLongIterator iter = collection.iterator();
/*  457 */     while (iter.hasNext()) {
/*  458 */       long element = iter.next();
/*  459 */       if (!contains(element))
/*  460 */         return false; 
/*      */     } 
/*  463 */     return true;
/*      */   }
/*      */   
/*      */   public boolean containsAll(long[] array) {
/*  469 */     for (int i = array.length; i-- > 0;) {
/*  470 */       if (!contains(array[i]))
/*  471 */         return false; 
/*      */     } 
/*  474 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection<? extends Long> collection) {
/*  480 */     boolean changed = false;
/*  481 */     for (Long element : collection) {
/*  482 */       long e = element.longValue();
/*  483 */       if (add(e))
/*  484 */         changed = true; 
/*      */     } 
/*  487 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean addAll(TLongCollection collection) {
/*  493 */     boolean changed = false;
/*  494 */     TLongIterator iter = collection.iterator();
/*  495 */     while (iter.hasNext()) {
/*  496 */       long element = iter.next();
/*  497 */       if (add(element))
/*  498 */         changed = true; 
/*      */     } 
/*  501 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean addAll(long[] array) {
/*  507 */     boolean changed = false;
/*  508 */     for (long element : array) {
/*  509 */       if (add(element))
/*  510 */         changed = true; 
/*      */     } 
/*  513 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection<?> collection) {
/*  520 */     boolean modified = false;
/*  521 */     TLongIterator iter = iterator();
/*  522 */     while (iter.hasNext()) {
/*  523 */       if (!collection.contains(Long.valueOf(iter.next()))) {
/*  524 */         iter.remove();
/*  525 */         modified = true;
/*      */       } 
/*      */     } 
/*  528 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(TLongCollection collection) {
/*  534 */     if (this == collection)
/*  535 */       return false; 
/*  537 */     boolean modified = false;
/*  538 */     TLongIterator iter = iterator();
/*  539 */     while (iter.hasNext()) {
/*  540 */       if (!collection.contains(iter.next())) {
/*  541 */         iter.remove();
/*  542 */         modified = true;
/*      */       } 
/*      */     } 
/*  545 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(long[] array) {
/*  551 */     boolean changed = false;
/*  552 */     Arrays.sort(array);
/*  553 */     long[] data = this._data;
/*  555 */     for (int i = this._pos; i-- > 0;) {
/*  556 */       if (Arrays.binarySearch(array, data[i]) < 0) {
/*  557 */         remove(i, 1);
/*  558 */         changed = true;
/*      */       } 
/*      */     } 
/*  561 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection<?> collection) {
/*  567 */     boolean changed = false;
/*  568 */     for (Object element : collection) {
/*  569 */       if (element instanceof Long) {
/*  570 */         long c = ((Long)element).longValue();
/*  571 */         if (remove(c))
/*  572 */           changed = true; 
/*      */       } 
/*      */     } 
/*  576 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean removeAll(TLongCollection collection) {
/*  582 */     if (collection == this) {
/*  583 */       clear();
/*  584 */       return true;
/*      */     } 
/*  586 */     boolean changed = false;
/*  587 */     TLongIterator iter = collection.iterator();
/*  588 */     while (iter.hasNext()) {
/*  589 */       long element = iter.next();
/*  590 */       if (remove(element))
/*  591 */         changed = true; 
/*      */     } 
/*  594 */     return changed;
/*      */   }
/*      */   
/*      */   public boolean removeAll(long[] array) {
/*  600 */     boolean changed = false;
/*  601 */     for (int i = array.length; i-- > 0;) {
/*  602 */       if (remove(array[i]))
/*  603 */         changed = true; 
/*      */     } 
/*  606 */     return changed;
/*      */   }
/*      */   
/*      */   public void transformValues(TLongFunction function) {
/*  612 */     for (int i = this._pos; i-- > 0;)
/*  613 */       this._data[i] = function.execute(this._data[i]); 
/*      */   }
/*      */   
/*      */   public void reverse() {
/*  620 */     reverse(0, this._pos);
/*      */   }
/*      */   
/*      */   public void reverse(int from, int to) {
/*  626 */     if (from == to)
/*      */       return; 
/*  629 */     if (from > to)
/*  630 */       throw new IllegalArgumentException("from cannot be greater than to"); 
/*  632 */     for (int i = from, j = to - 1; i < j; i++, j--)
/*  633 */       swap(i, j); 
/*      */   }
/*      */   
/*      */   public void shuffle(Random rand) {
/*  640 */     for (int i = this._pos; i-- > 1;)
/*  641 */       swap(i, rand.nextInt(i)); 
/*      */   }
/*      */   
/*      */   private void swap(int i, int j) {
/*  653 */     long tmp = this._data[i];
/*  654 */     this._data[i] = this._data[j];
/*  655 */     this._data[j] = tmp;
/*      */   }
/*      */   
/*      */   public TLongList subList(int begin, int end) {
/*  663 */     if (end < begin)
/*  664 */       throw new IllegalArgumentException("end index " + end + " greater than begin index " + begin); 
/*  667 */     if (begin < 0)
/*  668 */       throw new IndexOutOfBoundsException("begin index can not be < 0"); 
/*  670 */     if (end > this._data.length)
/*  671 */       throw new IndexOutOfBoundsException("end index < " + this._data.length); 
/*  673 */     TLongArrayList list = new TLongArrayList(end - begin);
/*  674 */     for (int i = begin; i < end; i++)
/*  675 */       list.add(this._data[i]); 
/*  677 */     return list;
/*      */   }
/*      */   
/*      */   public long[] toArray() {
/*  683 */     return toArray(0, this._pos);
/*      */   }
/*      */   
/*      */   public long[] toArray(int offset, int len) {
/*  689 */     long[] rv = new long[len];
/*  690 */     toArray(rv, offset, len);
/*  691 */     return rv;
/*      */   }
/*      */   
/*      */   public long[] toArray(long[] dest) {
/*  697 */     int len = dest.length;
/*  698 */     if (dest.length > this._pos) {
/*  699 */       len = this._pos;
/*  700 */       dest[len] = this.no_entry_value;
/*      */     } 
/*  702 */     toArray(dest, 0, len);
/*  703 */     return dest;
/*      */   }
/*      */   
/*      */   public long[] toArray(long[] dest, int offset, int len) {
/*  709 */     if (len == 0)
/*  710 */       return dest; 
/*  712 */     if (offset < 0 || offset >= this._pos)
/*  713 */       throw new ArrayIndexOutOfBoundsException(offset); 
/*  715 */     System.arraycopy(this._data, offset, dest, 0, len);
/*  716 */     return dest;
/*      */   }
/*      */   
/*      */   public long[] toArray(long[] dest, int source_pos, int dest_pos, int len) {
/*  722 */     if (len == 0)
/*  723 */       return dest; 
/*  725 */     if (source_pos < 0 || source_pos >= this._pos)
/*  726 */       throw new ArrayIndexOutOfBoundsException(source_pos); 
/*  728 */     System.arraycopy(this._data, source_pos, dest, dest_pos, len);
/*  729 */     return dest;
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  738 */     if (other == this)
/*  739 */       return true; 
/*  741 */     if (other instanceof TLongArrayList) {
/*  742 */       TLongArrayList that = (TLongArrayList)other;
/*  743 */       if (that.size() != size())
/*  743 */         return false; 
/*  745 */       for (int i = this._pos; i-- > 0;) {
/*  746 */         if (this._data[i] != that._data[i])
/*  747 */           return false; 
/*      */       } 
/*  750 */       return true;
/*      */     } 
/*  753 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  760 */     int h = 0;
/*  761 */     for (int i = this._pos; i-- > 0;)
/*  762 */       h += HashFunctions.hash(this._data[i]); 
/*  764 */     return h;
/*      */   }
/*      */   
/*      */   public boolean forEach(TLongProcedure procedure) {
/*  772 */     for (int i = 0; i < this._pos; i++) {
/*  773 */       if (!procedure.execute(this._data[i]))
/*  774 */         return false; 
/*      */     } 
/*  777 */     return true;
/*      */   }
/*      */   
/*      */   public boolean forEachDescending(TLongProcedure procedure) {
/*  783 */     for (int i = this._pos; i-- > 0;) {
/*  784 */       if (!procedure.execute(this._data[i]))
/*  785 */         return false; 
/*      */     } 
/*  788 */     return true;
/*      */   }
/*      */   
/*      */   public void sort() {
/*  796 */     Arrays.sort(this._data, 0, this._pos);
/*      */   }
/*      */   
/*      */   public void sort(int fromIndex, int toIndex) {
/*  802 */     Arrays.sort(this._data, fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   public void fill(long val) {
/*  810 */     Arrays.fill(this._data, 0, this._pos, val);
/*      */   }
/*      */   
/*      */   public void fill(int fromIndex, int toIndex, long val) {
/*  816 */     if (toIndex > this._pos) {
/*  817 */       ensureCapacity(toIndex);
/*  818 */       this._pos = toIndex;
/*      */     } 
/*  820 */     Arrays.fill(this._data, fromIndex, toIndex, val);
/*      */   }
/*      */   
/*      */   public int binarySearch(long value) {
/*  828 */     return binarySearch(value, 0, this._pos);
/*      */   }
/*      */   
/*      */   public int binarySearch(long value, int fromIndex, int toIndex) {
/*  834 */     if (fromIndex < 0)
/*  835 */       throw new ArrayIndexOutOfBoundsException(fromIndex); 
/*  837 */     if (toIndex > this._pos)
/*  838 */       throw new ArrayIndexOutOfBoundsException(toIndex); 
/*  841 */     int low = fromIndex;
/*  842 */     int high = toIndex - 1;
/*  844 */     while (low <= high) {
/*  845 */       int mid = low + high >>> 1;
/*  846 */       long midVal = this._data[mid];
/*  848 */       if (midVal < value) {
/*  849 */         low = mid + 1;
/*      */         continue;
/*      */       } 
/*  851 */       if (midVal > value) {
/*  852 */         high = mid - 1;
/*      */         continue;
/*      */       } 
/*  855 */       return mid;
/*      */     } 
/*  858 */     return -(low + 1);
/*      */   }
/*      */   
/*      */   public int indexOf(long value) {
/*  864 */     return indexOf(0, value);
/*      */   }
/*      */   
/*      */   public int indexOf(int offset, long value) {
/*  870 */     for (int i = offset; i < this._pos; i++) {
/*  871 */       if (this._data[i] == value)
/*  872 */         return i; 
/*      */     } 
/*  875 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(long value) {
/*  881 */     return lastIndexOf(this._pos, value);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(int offset, long value) {
/*  887 */     for (int i = offset; i-- > 0;) {
/*  888 */       if (this._data[i] == value)
/*  889 */         return i; 
/*      */     } 
/*  892 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean contains(long value) {
/*  898 */     return (lastIndexOf(value) >= 0);
/*      */   }
/*      */   
/*      */   public TLongList grep(TLongProcedure condition) {
/*  904 */     TLongArrayList list = new TLongArrayList();
/*  905 */     for (int i = 0; i < this._pos; i++) {
/*  906 */       if (condition.execute(this._data[i]))
/*  907 */         list.add(this._data[i]); 
/*      */     } 
/*  910 */     return list;
/*      */   }
/*      */   
/*      */   public TLongList inverseGrep(TLongProcedure condition) {
/*  916 */     TLongArrayList list = new TLongArrayList();
/*  917 */     for (int i = 0; i < this._pos; i++) {
/*  918 */       if (!condition.execute(this._data[i]))
/*  919 */         list.add(this._data[i]); 
/*      */     } 
/*  922 */     return list;
/*      */   }
/*      */   
/*      */   public long max() {
/*  928 */     if (size() == 0)
/*  929 */       throw new IllegalStateException("cannot find maximum of an empty list"); 
/*  931 */     long max = Long.MIN_VALUE;
/*  932 */     for (int i = 0; i < this._pos; i++) {
/*  933 */       if (this._data[i] > max)
/*  934 */         max = this._data[i]; 
/*      */     } 
/*  937 */     return max;
/*      */   }
/*      */   
/*      */   public long min() {
/*  943 */     if (size() == 0)
/*  944 */       throw new IllegalStateException("cannot find minimum of an empty list"); 
/*  946 */     long min = Long.MAX_VALUE;
/*  947 */     for (int i = 0; i < this._pos; i++) {
/*  948 */       if (this._data[i] < min)
/*  949 */         min = this._data[i]; 
/*      */     } 
/*  952 */     return min;
/*      */   }
/*      */   
/*      */   public long sum() {
/*  958 */     long sum = 0L;
/*  959 */     for (int i = 0; i < this._pos; i++)
/*  960 */       sum += this._data[i]; 
/*  962 */     return sum;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  971 */     StringBuilder buf = new StringBuilder("{");
/*  972 */     for (int i = 0, end = this._pos - 1; i < end; i++) {
/*  973 */       buf.append(this._data[i]);
/*  974 */       buf.append(", ");
/*      */     } 
/*  976 */     if (size() > 0)
/*  977 */       buf.append(this._data[this._pos - 1]); 
/*  979 */     buf.append("}");
/*  980 */     return buf.toString();
/*      */   }
/*      */   
/*      */   class TLongArrayIterator implements TLongIterator {
/*  988 */     private int cursor = 0;
/*      */     
/*  995 */     int lastRet = -1;
/*      */     
/*      */     TLongArrayIterator(int index) {
/*  999 */       this.cursor = index;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1005 */       return (this.cursor < TLongArrayList.this.size());
/*      */     }
/*      */     
/*      */     public long next() {
/*      */       try {
/* 1012 */         long next = TLongArrayList.this.get(this.cursor);
/* 1013 */         this.lastRet = this.cursor++;
/* 1014 */         return next;
/* 1015 */       } catch (IndexOutOfBoundsException e) {
/* 1016 */         throw new NoSuchElementException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1023 */       if (this.lastRet == -1)
/* 1024 */         throw new IllegalStateException(); 
/*      */       try {
/* 1027 */         TLongArrayList.this.remove(this.lastRet, 1);
/* 1028 */         if (this.lastRet < this.cursor)
/* 1029 */           this.cursor--; 
/* 1030 */         this.lastRet = -1;
/* 1031 */       } catch (IndexOutOfBoundsException e) {
/* 1032 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/* 1040 */     out.writeByte(0);
/* 1043 */     out.writeInt(this._pos);
/* 1046 */     out.writeLong(this.no_entry_value);
/* 1049 */     int len = this._data.length;
/* 1050 */     out.writeInt(len);
/* 1051 */     for (int i = 0; i < len; i++)
/* 1052 */       out.writeLong(this._data[i]); 
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 1061 */     in.readByte();
/* 1064 */     this._pos = in.readInt();
/* 1067 */     this.no_entry_value = in.readLong();
/* 1070 */     int len = in.readInt();
/* 1071 */     this._data = new long[len];
/* 1072 */     for (int i = 0; i < len; i++)
/* 1073 */       this._data[i] = in.readLong(); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\array\TLongArrayList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */