/*      */ package gnu.trove.list.linked;
/*      */ 
/*      */ import gnu.trove.TDoubleCollection;
/*      */ import gnu.trove.function.TDoubleFunction;
/*      */ import gnu.trove.impl.HashFunctions;
/*      */ import gnu.trove.iterator.TDoubleIterator;
/*      */ import gnu.trove.list.TDoubleList;
/*      */ import gnu.trove.procedure.TDoubleProcedure;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class TDoubleLinkedList implements TDoubleList, Externalizable {
/*      */   double no_entry_value;
/*      */   
/*      */   int size;
/*      */   
/*   50 */   TDoubleLink head = null;
/*      */   
/*   51 */   TDoubleLink tail = this.head;
/*      */   
/*      */   public TDoubleLinkedList(double no_entry_value) {
/*   57 */     this.no_entry_value = no_entry_value;
/*      */   }
/*      */   
/*      */   public TDoubleLinkedList(TDoubleList list) {
/*   61 */     this.no_entry_value = list.getNoEntryValue();
/*   63 */     for (TDoubleIterator iterator = list.iterator(); iterator.hasNext(); ) {
/*   64 */       double next = iterator.next();
/*   65 */       add(next);
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getNoEntryValue() {
/*   71 */     return this.no_entry_value;
/*      */   }
/*      */   
/*      */   public int size() {
/*   76 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*   81 */     return (size() == 0);
/*      */   }
/*      */   
/*      */   public boolean add(double val) {
/*   86 */     TDoubleLink l = new TDoubleLink(val);
/*   87 */     if (no(this.head)) {
/*   88 */       this.head = l;
/*   89 */       this.tail = l;
/*      */     } else {
/*   91 */       l.setPrevious(this.tail);
/*   92 */       this.tail.setNext(l);
/*   94 */       this.tail = l;
/*      */     } 
/*   97 */     this.size++;
/*   98 */     return true;
/*      */   }
/*      */   
/*      */   public void add(double[] vals) {
/*  103 */     for (double val : vals)
/*  104 */       add(val); 
/*      */   }
/*      */   
/*      */   public void add(double[] vals, int offset, int length) {
/*  110 */     for (int i = 0; i < length; i++) {
/*  111 */       double val = vals[offset + i];
/*  112 */       add(val);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void insert(int offset, double value) {
/*  118 */     TDoubleLinkedList tmp = new TDoubleLinkedList();
/*  119 */     tmp.add(value);
/*  120 */     insert(offset, tmp);
/*      */   }
/*      */   
/*      */   public void insert(int offset, double[] values) {
/*  125 */     insert(offset, link(values, 0, values.length));
/*      */   }
/*      */   
/*      */   public void insert(int offset, double[] values, int valOffset, int len) {
/*  130 */     insert(offset, link(values, valOffset, len));
/*      */   }
/*      */   
/*      */   void insert(int offset, TDoubleLinkedList tmp) {
/*  134 */     TDoubleLink l = getLinkAt(offset);
/*  136 */     this.size += tmp.size;
/*  138 */     if (l == this.head) {
/*  140 */       tmp.tail.setNext(this.head);
/*  141 */       this.head.setPrevious(tmp.tail);
/*  142 */       this.head = tmp.head;
/*      */       return;
/*      */     } 
/*  147 */     if (no(l)) {
/*  148 */       if (this.size == 0) {
/*  150 */         this.head = tmp.head;
/*  151 */         this.tail = tmp.tail;
/*      */       } else {
/*  154 */         this.tail.setNext(tmp.head);
/*  155 */         tmp.head.setPrevious(this.tail);
/*  156 */         this.tail = tmp.tail;
/*      */       } 
/*      */     } else {
/*  159 */       TDoubleLink prev = l.getPrevious();
/*  160 */       l.getPrevious().setNext(tmp.head);
/*  163 */       tmp.tail.setNext(l);
/*  164 */       l.setPrevious(tmp.tail);
/*  166 */       tmp.head.setPrevious(prev);
/*      */     } 
/*      */   }
/*      */   
/*      */   static TDoubleLinkedList link(double[] values, int valOffset, int len) {
/*  171 */     TDoubleLinkedList ret = new TDoubleLinkedList();
/*  173 */     for (int i = 0; i < len; i++)
/*  174 */       ret.add(values[valOffset + i]); 
/*  177 */     return ret;
/*      */   }
/*      */   
/*      */   public double get(int offset) {
/*  182 */     if (offset > this.size)
/*  183 */       throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size); 
/*  185 */     TDoubleLink l = getLinkAt(offset);
/*  187 */     if (no(l))
/*  188 */       return this.no_entry_value; 
/*  190 */     return l.getValue();
/*      */   }
/*      */   
/*      */   public TDoubleLink getLinkAt(int offset) {
/*  204 */     if (offset >= size())
/*  205 */       return null; 
/*  207 */     if (offset <= size() >>> 1)
/*  208 */       return getLink(this.head, 0, offset, true); 
/*  210 */     return getLink(this.tail, size() - 1, offset, false);
/*      */   }
/*      */   
/*      */   private static TDoubleLink getLink(TDoubleLink l, int idx, int offset) {
/*  222 */     return getLink(l, idx, offset, true);
/*      */   }
/*      */   
/*      */   private static TDoubleLink getLink(TDoubleLink l, int idx, int offset, boolean next) {
/*  234 */     int i = idx;
/*  236 */     while (got(l)) {
/*  237 */       if (i == offset)
/*  238 */         return l; 
/*  241 */       i += next ? 1 : -1;
/*  242 */       l = next ? l.getNext() : l.getPrevious();
/*      */     } 
/*  245 */     return null;
/*      */   }
/*      */   
/*      */   public double set(int offset, double val) {
/*  251 */     if (offset > this.size)
/*  252 */       throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size); 
/*  254 */     TDoubleLink l = getLinkAt(offset);
/*  256 */     if (no(l))
/*  257 */       throw new IndexOutOfBoundsException("at offset " + offset); 
/*  259 */     double prev = l.getValue();
/*  260 */     l.setValue(val);
/*  261 */     return prev;
/*      */   }
/*      */   
/*      */   public void set(int offset, double[] values) {
/*  266 */     set(offset, values, 0, values.length);
/*      */   }
/*      */   
/*      */   public void set(int offset, double[] values, int valOffset, int length) {
/*  271 */     for (int i = 0; i < length; i++) {
/*  272 */       double value = values[valOffset + i];
/*  273 */       set(offset + i, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public double replace(int offset, double val) {
/*  279 */     return set(offset, val);
/*      */   }
/*      */   
/*      */   public void clear() {
/*  284 */     this.size = 0;
/*  286 */     this.head = null;
/*  287 */     this.tail = null;
/*      */   }
/*      */   
/*      */   public boolean remove(double value) {
/*  292 */     boolean changed = false;
/*  293 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  295 */       if (l.getValue() == value) {
/*  296 */         changed = true;
/*  298 */         removeLink(l);
/*      */       } 
/*      */     } 
/*  302 */     return changed;
/*      */   }
/*      */   
/*      */   private void removeLink(TDoubleLink l) {
/*  311 */     if (no(l))
/*      */       return; 
/*  314 */     this.size--;
/*  316 */     TDoubleLink prev = l.getPrevious();
/*  317 */     TDoubleLink next = l.getNext();
/*  319 */     if (got(prev)) {
/*  320 */       prev.setNext(next);
/*      */     } else {
/*  323 */       this.head = next;
/*      */     } 
/*  326 */     if (got(next)) {
/*  327 */       next.setPrevious(prev);
/*      */     } else {
/*  330 */       this.tail = prev;
/*      */     } 
/*  333 */     l.setNext(null);
/*  334 */     l.setPrevious(null);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection<?> collection) {
/*  339 */     if (isEmpty())
/*  340 */       return false; 
/*  342 */     for (Object o : collection) {
/*  343 */       if (o instanceof Double) {
/*  344 */         Double i = (Double)o;
/*  345 */         if (!contains(i.doubleValue()))
/*  346 */           return false; 
/*      */         continue;
/*      */       } 
/*  348 */       return false;
/*      */     } 
/*  351 */     return true;
/*      */   }
/*      */   
/*      */   public boolean containsAll(TDoubleCollection collection) {
/*  356 */     if (isEmpty())
/*  357 */       return false; 
/*  359 */     for (TDoubleIterator it = collection.iterator(); it.hasNext(); ) {
/*  360 */       double i = it.next();
/*  361 */       if (!contains(i))
/*  362 */         return false; 
/*      */     } 
/*  364 */     return true;
/*      */   }
/*      */   
/*      */   public boolean containsAll(double[] array) {
/*  369 */     if (isEmpty())
/*  370 */       return false; 
/*  372 */     for (double i : array) {
/*  373 */       if (!contains(i))
/*  374 */         return false; 
/*      */     } 
/*  376 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection<? extends Double> collection) {
/*  381 */     boolean ret = false;
/*  382 */     for (Double v : collection) {
/*  383 */       if (add(v.doubleValue()))
/*  384 */         ret = true; 
/*      */     } 
/*  387 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean addAll(TDoubleCollection collection) {
/*  392 */     boolean ret = false;
/*  393 */     for (TDoubleIterator it = collection.iterator(); it.hasNext(); ) {
/*  394 */       double i = it.next();
/*  395 */       if (add(i))
/*  396 */         ret = true; 
/*      */     } 
/*  399 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean addAll(double[] array) {
/*  404 */     boolean ret = false;
/*  405 */     for (double i : array) {
/*  406 */       if (add(i))
/*  407 */         ret = true; 
/*      */     } 
/*  410 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection<?> collection) {
/*  415 */     boolean modified = false;
/*  416 */     TDoubleIterator iter = iterator();
/*  417 */     while (iter.hasNext()) {
/*  418 */       if (!collection.contains(Double.valueOf(iter.next()))) {
/*  419 */         iter.remove();
/*  420 */         modified = true;
/*      */       } 
/*      */     } 
/*  423 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(TDoubleCollection collection) {
/*  428 */     boolean modified = false;
/*  429 */     TDoubleIterator iter = iterator();
/*  430 */     while (iter.hasNext()) {
/*  431 */       if (!collection.contains(iter.next())) {
/*  432 */         iter.remove();
/*  433 */         modified = true;
/*      */       } 
/*      */     } 
/*  436 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(double[] array) {
/*  441 */     Arrays.sort(array);
/*  443 */     boolean modified = false;
/*  444 */     TDoubleIterator iter = iterator();
/*  445 */     while (iter.hasNext()) {
/*  446 */       if (Arrays.binarySearch(array, iter.next()) < 0) {
/*  447 */         iter.remove();
/*  448 */         modified = true;
/*      */       } 
/*      */     } 
/*  451 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection<?> collection) {
/*  456 */     boolean modified = false;
/*  457 */     TDoubleIterator iter = iterator();
/*  458 */     while (iter.hasNext()) {
/*  459 */       if (collection.contains(Double.valueOf(iter.next()))) {
/*  460 */         iter.remove();
/*  461 */         modified = true;
/*      */       } 
/*      */     } 
/*  464 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean removeAll(TDoubleCollection collection) {
/*  469 */     boolean modified = false;
/*  470 */     TDoubleIterator iter = iterator();
/*  471 */     while (iter.hasNext()) {
/*  472 */       if (collection.contains(iter.next())) {
/*  473 */         iter.remove();
/*  474 */         modified = true;
/*      */       } 
/*      */     } 
/*  477 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean removeAll(double[] array) {
/*  482 */     Arrays.sort(array);
/*  484 */     boolean modified = false;
/*  485 */     TDoubleIterator iter = iterator();
/*  486 */     while (iter.hasNext()) {
/*  487 */       if (Arrays.binarySearch(array, iter.next()) >= 0) {
/*  488 */         iter.remove();
/*  489 */         modified = true;
/*      */       } 
/*      */     } 
/*  492 */     return modified;
/*      */   }
/*      */   
/*      */   public double removeAt(int offset) {
/*  497 */     TDoubleLink l = getLinkAt(offset);
/*  498 */     if (no(l))
/*  499 */       throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset); 
/*  501 */     double prev = l.getValue();
/*  502 */     removeLink(l);
/*  503 */     return prev;
/*      */   }
/*      */   
/*      */   public void remove(int offset, int length) {
/*  508 */     for (int i = 0; i < length; i++)
/*  509 */       removeAt(offset); 
/*      */   }
/*      */   
/*      */   public void transformValues(TDoubleFunction function) {
/*  515 */     for (TDoubleLink l = this.head; got(l); ) {
/*  517 */       l.setValue(function.execute(l.getValue()));
/*  519 */       l = l.getNext();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reverse() {
/*  525 */     TDoubleLink h = this.head;
/*  526 */     TDoubleLink t = this.tail;
/*  530 */     TDoubleLink l = this.head;
/*  531 */     while (got(l)) {
/*  532 */       TDoubleLink next = l.getNext();
/*  533 */       TDoubleLink prev = l.getPrevious();
/*  535 */       TDoubleLink tmp = l;
/*  536 */       l = l.getNext();
/*  538 */       tmp.setNext(prev);
/*  539 */       tmp.setPrevious(next);
/*      */     } 
/*  543 */     this.head = t;
/*  544 */     this.tail = h;
/*      */   }
/*      */   
/*      */   public void reverse(int from, int to) {
/*  549 */     if (from > to)
/*  550 */       throw new IllegalArgumentException("from > to : " + from + ">" + to); 
/*  552 */     TDoubleLink start = getLinkAt(from);
/*  553 */     TDoubleLink stop = getLinkAt(to);
/*  555 */     TDoubleLink tmp = null;
/*  557 */     TDoubleLink tmpHead = start.getPrevious();
/*  560 */     TDoubleLink l = start;
/*  561 */     while (l != stop) {
/*  562 */       TDoubleLink next = l.getNext();
/*  563 */       TDoubleLink prev = l.getPrevious();
/*  565 */       tmp = l;
/*  566 */       l = l.getNext();
/*  568 */       tmp.setNext(prev);
/*  569 */       tmp.setPrevious(next);
/*      */     } 
/*  573 */     if (got(tmp)) {
/*  574 */       tmpHead.setNext(tmp);
/*  575 */       stop.setPrevious(tmpHead);
/*      */     } 
/*  577 */     start.setNext(stop);
/*  578 */     stop.setPrevious(start);
/*      */   }
/*      */   
/*      */   public void shuffle(Random rand) {
/*  583 */     for (int i = 0; i < this.size; i++) {
/*  584 */       TDoubleLink l = getLinkAt(rand.nextInt(size()));
/*  585 */       removeLink(l);
/*  586 */       add(l.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   public TDoubleList subList(int begin, int end) {
/*  592 */     if (end < begin)
/*  593 */       throw new IllegalArgumentException("begin index " + begin + " greater than end index " + end); 
/*  596 */     if (this.size < begin)
/*  597 */       throw new IllegalArgumentException("begin index " + begin + " greater than last index " + this.size); 
/*  600 */     if (begin < 0)
/*  601 */       throw new IndexOutOfBoundsException("begin index can not be < 0"); 
/*  603 */     if (end > this.size)
/*  604 */       throw new IndexOutOfBoundsException("end index < " + this.size); 
/*  607 */     TDoubleLinkedList ret = new TDoubleLinkedList();
/*  608 */     TDoubleLink tmp = getLinkAt(begin);
/*  609 */     for (int i = begin; i < end; i++) {
/*  610 */       ret.add(tmp.getValue());
/*  611 */       tmp = tmp.getNext();
/*      */     } 
/*  614 */     return ret;
/*      */   }
/*      */   
/*      */   public double[] toArray() {
/*  619 */     return toArray(new double[this.size], 0, this.size);
/*      */   }
/*      */   
/*      */   public double[] toArray(int offset, int len) {
/*  624 */     return toArray(new double[len], offset, 0, len);
/*      */   }
/*      */   
/*      */   public double[] toArray(double[] dest) {
/*  629 */     return toArray(dest, 0, this.size);
/*      */   }
/*      */   
/*      */   public double[] toArray(double[] dest, int offset, int len) {
/*  634 */     return toArray(dest, offset, 0, len);
/*      */   }
/*      */   
/*      */   public double[] toArray(double[] dest, int source_pos, int dest_pos, int len) {
/*  639 */     if (len == 0)
/*  640 */       return dest; 
/*  642 */     if (source_pos < 0 || source_pos >= size())
/*  643 */       throw new ArrayIndexOutOfBoundsException(source_pos); 
/*  646 */     TDoubleLink tmp = getLinkAt(source_pos);
/*  647 */     for (int i = 0; i < len; i++) {
/*  648 */       dest[dest_pos + i] = tmp.getValue();
/*  649 */       tmp = tmp.getNext();
/*      */     } 
/*  652 */     return dest;
/*      */   }
/*      */   
/*      */   public boolean forEach(TDoubleProcedure procedure) {
/*  657 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  658 */       if (!procedure.execute(l.getValue()))
/*  659 */         return false; 
/*      */     } 
/*  661 */     return true;
/*      */   }
/*      */   
/*      */   public boolean forEachDescending(TDoubleProcedure procedure) {
/*  666 */     for (TDoubleLink l = this.tail; got(l); l = l.getPrevious()) {
/*  667 */       if (!procedure.execute(l.getValue()))
/*  668 */         return false; 
/*      */     } 
/*  670 */     return true;
/*      */   }
/*      */   
/*      */   public void sort() {
/*  675 */     sort(0, this.size);
/*      */   }
/*      */   
/*      */   public void sort(int fromIndex, int toIndex) {
/*  680 */     TDoubleList tmp = subList(fromIndex, toIndex);
/*  681 */     double[] vals = tmp.toArray();
/*  682 */     Arrays.sort(vals);
/*  683 */     set(fromIndex, vals);
/*      */   }
/*      */   
/*      */   public void fill(double val) {
/*  688 */     fill(0, this.size, val);
/*      */   }
/*      */   
/*      */   public void fill(int fromIndex, int toIndex, double val) {
/*  693 */     if (fromIndex < 0)
/*  694 */       throw new IndexOutOfBoundsException("begin index can not be < 0"); 
/*  698 */     TDoubleLink l = getLinkAt(fromIndex);
/*  699 */     if (toIndex > this.size) {
/*      */       int i;
/*  700 */       for (i = fromIndex; i < this.size; i++) {
/*  701 */         l.setValue(val);
/*  702 */         l = l.getNext();
/*      */       } 
/*  704 */       for (i = this.size; i < toIndex; i++)
/*  705 */         add(val); 
/*      */     } else {
/*  708 */       for (int i = fromIndex; i < toIndex; i++) {
/*  709 */         l.setValue(val);
/*  710 */         l = l.getNext();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int binarySearch(double value) {
/*  718 */     return binarySearch(value, 0, size());
/*      */   }
/*      */   
/*      */   public int binarySearch(double value, int fromIndex, int toIndex) {
/*  723 */     if (fromIndex < 0)
/*  724 */       throw new IndexOutOfBoundsException("begin index can not be < 0"); 
/*  727 */     if (toIndex > this.size)
/*  728 */       throw new IndexOutOfBoundsException("end index > size: " + toIndex + " > " + this.size); 
/*  732 */     if (toIndex < fromIndex)
/*  733 */       return -(fromIndex + 1); 
/*  738 */     int from = fromIndex;
/*  739 */     TDoubleLink fromLink = getLinkAt(fromIndex);
/*  740 */     int to = toIndex;
/*  742 */     while (from < to) {
/*  743 */       int mid = from + to >>> 1;
/*  744 */       TDoubleLink middle = getLink(fromLink, from, mid);
/*  745 */       if (middle.getValue() == value)
/*  746 */         return mid; 
/*  748 */       if (middle.getValue() < value) {
/*  749 */         from = mid + 1;
/*  750 */         fromLink = middle.next;
/*      */         continue;
/*      */       } 
/*  752 */       to = mid - 1;
/*      */     } 
/*  756 */     return -(from + 1);
/*      */   }
/*      */   
/*      */   public int indexOf(double value) {
/*  761 */     return indexOf(0, value);
/*      */   }
/*      */   
/*      */   public int indexOf(int offset, double value) {
/*  766 */     int count = offset;
/*  767 */     for (TDoubleLink l = getLinkAt(offset); got(l.getNext()); l = l.getNext()) {
/*  768 */       if (l.getValue() == value)
/*  769 */         return count; 
/*  771 */       count++;
/*      */     } 
/*  774 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(double value) {
/*  779 */     return lastIndexOf(0, value);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(int offset, double value) {
/*  784 */     if (isEmpty())
/*  785 */       return -1; 
/*  787 */     int last = -1;
/*  788 */     int count = offset;
/*  789 */     for (TDoubleLink l = getLinkAt(offset); got(l.getNext()); l = l.getNext()) {
/*  790 */       if (l.getValue() == value)
/*  791 */         last = count; 
/*  793 */       count++;
/*      */     } 
/*  796 */     return last;
/*      */   }
/*      */   
/*      */   public boolean contains(double value) {
/*  801 */     if (isEmpty())
/*  802 */       return false; 
/*  804 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  805 */       if (l.getValue() == value)
/*  806 */         return true; 
/*      */     } 
/*  808 */     return false;
/*      */   }
/*      */   
/*      */   public TDoubleIterator iterator() {
/*  814 */     return new TDoubleIterator() {
/*  815 */         TDoubleLinkedList.TDoubleLink l = TDoubleLinkedList.this.head;
/*      */         
/*      */         TDoubleLinkedList.TDoubleLink current;
/*      */         
/*      */         public double next() {
/*  819 */           if (TDoubleLinkedList.no(this.l))
/*  820 */             throw new NoSuchElementException(); 
/*  822 */           double ret = this.l.getValue();
/*  823 */           this.current = this.l;
/*  824 */           this.l = this.l.getNext();
/*  826 */           return ret;
/*      */         }
/*      */         
/*      */         public boolean hasNext() {
/*  830 */           return TDoubleLinkedList.got(this.l);
/*      */         }
/*      */         
/*      */         public void remove() {
/*  834 */           if (this.current == null)
/*  835 */             throw new IllegalStateException(); 
/*  837 */           TDoubleLinkedList.this.removeLink(this.current);
/*  838 */           this.current = null;
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public TDoubleList grep(TDoubleProcedure condition) {
/*  845 */     TDoubleList ret = new TDoubleLinkedList();
/*  846 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  847 */       if (condition.execute(l.getValue()))
/*  848 */         ret.add(l.getValue()); 
/*      */     } 
/*  850 */     return ret;
/*      */   }
/*      */   
/*      */   public TDoubleList inverseGrep(TDoubleProcedure condition) {
/*  855 */     TDoubleList ret = new TDoubleLinkedList();
/*  856 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  857 */       if (!condition.execute(l.getValue()))
/*  858 */         ret.add(l.getValue()); 
/*      */     } 
/*  860 */     return ret;
/*      */   }
/*      */   
/*      */   public double max() {
/*  865 */     double ret = Double.NEGATIVE_INFINITY;
/*  867 */     if (isEmpty())
/*  868 */       throw new IllegalStateException(); 
/*  870 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  871 */       if (ret < l.getValue())
/*  872 */         ret = l.getValue(); 
/*      */     } 
/*  875 */     return ret;
/*      */   }
/*      */   
/*      */   public double min() {
/*  880 */     double ret = Double.POSITIVE_INFINITY;
/*  882 */     if (isEmpty())
/*  883 */       throw new IllegalStateException(); 
/*  885 */     for (TDoubleLink l = this.head; got(l); l = l.getNext()) {
/*  886 */       if (ret > l.getValue())
/*  887 */         ret = l.getValue(); 
/*      */     } 
/*  890 */     return ret;
/*      */   }
/*      */   
/*      */   public double sum() {
/*  895 */     double sum = 0.0D;
/*  897 */     for (TDoubleLink l = this.head; got(l); l = l.getNext())
/*  898 */       sum += l.getValue(); 
/*  901 */     return sum;
/*      */   }
/*      */   
/*      */   static class TDoubleLink {
/*      */     double value;
/*      */     
/*      */     TDoubleLink previous;
/*      */     
/*      */     TDoubleLink next;
/*      */     
/*      */     TDoubleLink(double value) {
/*  913 */       this.value = value;
/*      */     }
/*      */     
/*      */     public double getValue() {
/*  917 */       return this.value;
/*      */     }
/*      */     
/*      */     public void setValue(double value) {
/*  921 */       this.value = value;
/*      */     }
/*      */     
/*      */     public TDoubleLink getPrevious() {
/*  925 */       return this.previous;
/*      */     }
/*      */     
/*      */     public void setPrevious(TDoubleLink previous) {
/*  929 */       this.previous = previous;
/*      */     }
/*      */     
/*      */     public TDoubleLink getNext() {
/*  933 */       return this.next;
/*      */     }
/*      */     
/*      */     public void setNext(TDoubleLink next) {
/*  937 */       this.next = next;
/*      */     }
/*      */   }
/*      */   
/*      */   class RemoveProcedure implements TDoubleProcedure {
/*      */     boolean changed = false;
/*      */     
/*      */     public boolean execute(double value) {
/*  954 */       if (TDoubleLinkedList.this.remove(value))
/*  955 */         this.changed = true; 
/*  957 */       return true;
/*      */     }
/*      */     
/*      */     public boolean isChanged() {
/*  961 */       return this.changed;
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/*  968 */     out.writeByte(0);
/*  971 */     out.writeDouble(this.no_entry_value);
/*  974 */     out.writeInt(this.size);
/*  975 */     for (TDoubleIterator iterator = iterator(); iterator.hasNext(); ) {
/*  976 */       double next = iterator.next();
/*  977 */       out.writeDouble(next);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  987 */     in.readByte();
/*  990 */     this.no_entry_value = in.readDouble();
/*  993 */     int len = in.readInt();
/*  994 */     for (int i = 0; i < len; i++)
/*  995 */       add(in.readDouble()); 
/*      */   }
/*      */   
/*      */   static boolean got(Object ref) {
/* 1000 */     return (ref != null);
/*      */   }
/*      */   
/*      */   static boolean no(Object ref) {
/* 1004 */     return (ref == null);
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/* 1009 */     if (this == o)
/* 1009 */       return true; 
/* 1010 */     if (o == null || getClass() != o.getClass())
/* 1010 */       return false; 
/* 1012 */     TDoubleLinkedList that = (TDoubleLinkedList)o;
/* 1014 */     if (this.no_entry_value != that.no_entry_value)
/* 1014 */       return false; 
/* 1015 */     if (this.size != that.size)
/* 1015 */       return false; 
/* 1017 */     TDoubleIterator iterator = iterator();
/* 1018 */     TDoubleIterator thatIterator = that.iterator();
/* 1019 */     while (iterator.hasNext()) {
/* 1020 */       if (!thatIterator.hasNext())
/* 1021 */         return false; 
/* 1023 */       if (iterator.next() != thatIterator.next())
/* 1024 */         return false; 
/*      */     } 
/* 1027 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1032 */     int result = HashFunctions.hash(this.no_entry_value);
/* 1033 */     result = 31 * result + this.size;
/* 1034 */     for (TDoubleIterator iterator = iterator(); iterator.hasNext();)
/* 1035 */       result = 31 * result + HashFunctions.hash(iterator.next()); 
/* 1038 */     return result;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1043 */     StringBuilder buf = new StringBuilder("{");
/* 1044 */     TDoubleIterator it = iterator();
/* 1045 */     while (it.hasNext()) {
/* 1046 */       double next = it.next();
/* 1047 */       buf.append(next);
/* 1048 */       if (it.hasNext())
/* 1049 */         buf.append(", "); 
/*      */     } 
/* 1051 */     buf.append("}");
/* 1052 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public TDoubleLinkedList() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\linked\TDoubleLinkedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */