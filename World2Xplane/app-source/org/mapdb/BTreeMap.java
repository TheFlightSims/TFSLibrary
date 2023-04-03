/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NavigableMap;
/*      */ import java.util.NavigableSet;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.SortedMap;
/*      */ import java.util.SortedSet;
/*      */ import java.util.TreeMap;
/*      */ import java.util.concurrent.ConcurrentNavigableMap;
/*      */ import java.util.concurrent.CopyOnWriteArrayList;
/*      */ import java.util.concurrent.locks.LockSupport;
/*      */ 
/*      */ public class BTreeMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V>, Bind.MapWithModificationListener<K, V> {
/*   99 */   public static final Comparator COMPARABLE_COMPARATOR = new Comparator<Comparable>() {
/*      */       public int compare(Comparable<Comparable> o1, Comparable o2) {
/*  102 */         return o1.compareTo(o2);
/*      */       }
/*      */     };
/*      */   
/*  107 */   protected static final Object EMPTY = new Object();
/*      */   
/*      */   protected static final int B_TREE_NODE_LEAF_LR = 180;
/*      */   
/*      */   protected static final int B_TREE_NODE_LEAF_L = 181;
/*      */   
/*      */   protected static final int B_TREE_NODE_LEAF_R = 182;
/*      */   
/*      */   protected static final int B_TREE_NODE_LEAF_C = 183;
/*      */   
/*      */   protected static final int B_TREE_NODE_DIR_LR = 184;
/*      */   
/*      */   protected static final int B_TREE_NODE_DIR_L = 185;
/*      */   
/*      */   protected static final int B_TREE_NODE_DIR_R = 186;
/*      */   
/*      */   protected static final int B_TREE_NODE_DIR_C = 187;
/*      */   
/*      */   protected final long rootRecidRef;
/*      */   
/*      */   protected final BTreeKeySerializer keySerializer;
/*      */   
/*      */   protected final Serializer<V> valueSerializer;
/*      */   
/*      */   protected final Comparator comparator;
/*      */   
/*  133 */   protected final LongConcurrentHashMap<Thread> nodeLocks = new LongConcurrentHashMap<Thread>();
/*      */   
/*      */   protected final int maxNodeSize;
/*      */   
/*      */   protected final Engine engine;
/*      */   
/*      */   protected final boolean hasValues;
/*      */   
/*      */   protected final boolean valsOutsideNodes;
/*      */   
/*      */   protected final List<Long> leftEdges;
/*      */   
/*      */   private final KeySet keySet;
/*      */   
/*  152 */   private final EntrySet entrySet = new EntrySet<K, V>(this);
/*      */   
/*  154 */   private final Values values = new Values<V>((ConcurrentNavigableMap)this);
/*      */   
/*  156 */   private final ConcurrentNavigableMap<K, V> descendingMap = new DescendingMap<K, V>(this, null, true, null, false);
/*      */   
/*      */   protected final Atomic.Long counter;
/*      */   
/*      */   protected final int numberOfNodeMetas;
/*      */   
/*      */   protected final Serializer<BNode> nodeSerializer;
/*      */   
/*      */   protected final Object modListenersLock;
/*      */   
/*      */   protected Bind.MapListener<K, V>[] modListeners;
/*      */   
/*      */   protected static SortedMap<String, Object> preinitCatalog(DB db) {
/*  165 */     Long rootRef = db.getEngine().<Long>get(1L, Serializer.LONG);
/*  167 */     if (rootRef == null) {
/*  168 */       if (db.getEngine().isReadOnly())
/*  169 */         return Collections.unmodifiableSortedMap(new TreeMap<String, Object>()); 
/*  171 */       NodeSerializer<Object, Object> rootSerializer = new NodeSerializer<Object, Object>(false, BTreeKeySerializer.STRING, db.getDefaultSerializer(), COMPARABLE_COMPARATOR, 0);
/*  173 */       BNode root = new LeafNode(new Object[] { null, null }, new Object[0], 0L);
/*  174 */       rootRef = Long.valueOf(db.getEngine().put(root, rootSerializer));
/*  175 */       db.getEngine().update(1L, rootRef, Serializer.LONG);
/*  176 */       db.getEngine().commit();
/*      */     } 
/*  178 */     return new BTreeMap<String, Object>(db.engine, 1L, 32, false, 0L, BTreeKeySerializer.STRING, db.getDefaultSerializer(), COMPARABLE_COMPARATOR, 0, false);
/*      */   }
/*      */   
/*      */   protected static final class ValRef {
/*      */     final long recid;
/*      */     
/*      */     public ValRef(long recid) {
/*  192 */       this.recid = recid;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  197 */       throw new IllegalAccessError();
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  202 */       throw new IllegalAccessError();
/*      */     }
/*      */     
/*      */     public String toString() {
/*  207 */       return "BTreeMap-ValRer[" + this.recid + "]";
/*      */     }
/*      */   }
/*      */   
/*      */   protected static interface BNode {
/*      */     boolean isLeaf();
/*      */     
/*      */     Object[] keys();
/*      */     
/*      */     Object[] vals();
/*      */     
/*      */     Object highKey();
/*      */     
/*      */     long[] child();
/*      */     
/*      */     long next();
/*      */   }
/*      */   
/*      */   protected static final class DirNode implements BNode {
/*      */     final Object[] keys;
/*      */     
/*      */     final long[] child;
/*      */     
/*      */     DirNode(Object[] keys, long[] child) {
/*  227 */       this.keys = keys;
/*  228 */       this.child = child;
/*      */     }
/*      */     
/*      */     DirNode(Object[] keys, List<Long> child) {
/*  232 */       this.keys = keys;
/*  233 */       this.child = new long[child.size()];
/*  234 */       for (int i = 0; i < child.size(); i++)
/*  235 */         this.child[i] = ((Long)child.get(i)).longValue(); 
/*      */     }
/*      */     
/*      */     public boolean isLeaf() {
/*  240 */       return false;
/*      */     }
/*      */     
/*      */     public Object[] keys() {
/*  242 */       return this.keys;
/*      */     }
/*      */     
/*      */     public Object[] vals() {
/*  243 */       return null;
/*      */     }
/*      */     
/*      */     public Object highKey() {
/*  245 */       return this.keys[this.keys.length - 1];
/*      */     }
/*      */     
/*      */     public long[] child() {
/*  247 */       return this.child;
/*      */     }
/*      */     
/*      */     public long next() {
/*  249 */       return this.child[this.child.length - 1];
/*      */     }
/*      */     
/*      */     public String toString() {
/*  252 */       return "Dir(K" + Arrays.toString(this.keys) + ", C" + Arrays.toString(this.child) + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   protected static final class LeafNode implements BNode {
/*      */     final Object[] keys;
/*      */     
/*      */     final Object[] vals;
/*      */     
/*      */     final long next;
/*      */     
/*      */     LeafNode(Object[] keys, Object[] vals, long next) {
/*  264 */       this.keys = keys;
/*  265 */       this.vals = vals;
/*  266 */       this.next = next;
/*  267 */       assert vals == null || keys.length == vals.length + 2;
/*      */     }
/*      */     
/*      */     public boolean isLeaf() {
/*  270 */       return true;
/*      */     }
/*      */     
/*      */     public Object[] keys() {
/*  272 */       return this.keys;
/*      */     }
/*      */     
/*      */     public Object[] vals() {
/*  273 */       return this.vals;
/*      */     }
/*      */     
/*      */     public Object highKey() {
/*  275 */       return this.keys[this.keys.length - 1];
/*      */     }
/*      */     
/*      */     public long[] child() {
/*  277 */       return null;
/*      */     }
/*      */     
/*      */     public long next() {
/*  278 */       return this.next;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  281 */       return "Leaf(K" + Arrays.toString(this.keys) + ", V" + Arrays.toString(this.vals) + ", L=" + this.next + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class NodeSerializer<A, B> implements Serializer<BNode> {
/*      */     protected final boolean hasValues;
/*      */     
/*      */     protected final boolean valsOutsideNodes;
/*      */     
/*      */     protected final BTreeKeySerializer keySerializer;
/*      */     
/*      */     protected final Serializer<Object> valueSerializer;
/*      */     
/*      */     protected final Comparator comparator;
/*      */     
/*      */     protected final int numberOfNodeMetas;
/*      */     
/*      */     public NodeSerializer(boolean valsOutsideNodes, BTreeKeySerializer keySerializer, Serializer<Object> valueSerializer, Comparator comparator, int numberOfNodeMetas) {
/*  298 */       assert keySerializer != null;
/*  299 */       assert comparator != null;
/*  300 */       this.hasValues = (valueSerializer != null);
/*  301 */       this.valsOutsideNodes = valsOutsideNodes;
/*  302 */       this.keySerializer = keySerializer;
/*  303 */       this.valueSerializer = valueSerializer;
/*  304 */       this.comparator = comparator;
/*  305 */       this.numberOfNodeMetas = numberOfNodeMetas;
/*      */     }
/*      */     
/*      */     public void serialize(DataOutput out, BTreeMap.BNode value) throws IOException {
/*      */       int header;
/*  310 */       boolean isLeaf = value.isLeaf();
/*  313 */       assert (value.keys()).length <= 255;
/*  314 */       assert isLeaf || (value.child()).length == (value.keys()).length;
/*  315 */       assert !isLeaf || !this.hasValues || (value.vals()).length == (value.keys()).length - 2;
/*  316 */       assert isLeaf || value.highKey() == null || value.child()[(value.child()).length - 1] != 0L;
/*  334 */       boolean left = (value.keys()[0] == null);
/*  335 */       boolean right = (value.keys()[(value.keys()).length - 1] == null);
/*  340 */       if (isLeaf) {
/*  341 */         if (right) {
/*  342 */           if (left) {
/*  343 */             header = 180;
/*      */           } else {
/*  345 */             header = 182;
/*      */           } 
/*  347 */         } else if (left) {
/*  348 */           header = 181;
/*      */         } else {
/*  350 */           header = 183;
/*      */         } 
/*  353 */       } else if (right) {
/*  354 */         if (left) {
/*  355 */           header = 184;
/*      */         } else {
/*  357 */           header = 186;
/*      */         } 
/*  359 */       } else if (left) {
/*  360 */         header = 185;
/*      */       } else {
/*  362 */         header = 187;
/*      */       } 
/*  368 */       out.write(header);
/*  369 */       out.write((value.keys()).length);
/*  372 */       for (int i = 0; i < this.numberOfNodeMetas; i++)
/*  373 */         DataOutput2.packLong(out, 0L); 
/*  377 */       if (isLeaf) {
/*  378 */         DataOutput2.packLong(out, ((BTreeMap.LeafNode)value).next);
/*      */       } else {
/*  380 */         for (long child : ((BTreeMap.DirNode)value).child)
/*  381 */           DataOutput2.packLong(out, child); 
/*      */       } 
/*  385 */       this.keySerializer.serialize(out, left ? 1 : 0, right ? ((value.keys()).length - 1) : (value.keys()).length, value.keys());
/*  389 */       if (isLeaf)
/*  390 */         if (this.hasValues) {
/*  391 */           for (Object val : value.vals()) {
/*  392 */             assert val != null;
/*  393 */             if (this.valsOutsideNodes) {
/*  394 */               long recid = ((BTreeMap.ValRef)val).recid;
/*  395 */               DataOutput2.packLong(out, recid);
/*      */             } else {
/*  397 */               this.valueSerializer.serialize(out, val);
/*      */             } 
/*      */           } 
/*      */         } else {
/*  402 */           boolean[] bools = new boolean[(value.vals()).length];
/*  403 */           for (int j = 0; j < bools.length; j++)
/*  404 */             bools[j] = (value.vals()[j] != null); 
/*  407 */           byte[] bb = SerializerBase.booleanToByteArray(bools);
/*  408 */           out.write(bb);
/*      */         }  
/*      */     }
/*      */     
/*      */     public BTreeMap.BNode deserialize(DataInput in, int available) throws IOException {
/*  416 */       int header = in.readUnsignedByte();
/*  417 */       int size = in.readUnsignedByte();
/*  420 */       for (int i = 0; i < this.numberOfNodeMetas; i++)
/*  421 */         DataInput2.unpackLong(in); 
/*  426 */       boolean isLeaf = (header == 183 || header == 181 || header == 180 || header == 182);
/*  429 */       int start = (header == 181 || header == 180 || header == 185 || header == 184) ? 1 : 0;
/*  433 */       int end = (header == 182 || header == 180 || header == 186 || header == 184) ? (size - 1) : size;
/*  438 */       if (isLeaf) {
/*  439 */         long next = DataInput2.unpackLong(in);
/*  440 */         Object[] arrayOfObject1 = this.keySerializer.deserialize(in, start, end, size);
/*  441 */         assert arrayOfObject1.length == size;
/*  442 */         Object[] vals = new Object[size - 2];
/*  443 */         if (this.hasValues) {
/*  444 */           for (int k = 0; k < size - 2; k++) {
/*  445 */             if (this.valsOutsideNodes) {
/*  446 */               long recid = DataInput2.unpackLong(in);
/*  447 */               vals[k] = (recid == 0L) ? null : new BTreeMap.ValRef(recid);
/*      */             } else {
/*  449 */               vals[k] = this.valueSerializer.deserialize(in, -1);
/*      */             } 
/*      */           } 
/*      */         } else {
/*  454 */           boolean[] bools = SerializerBase.readBooleanArray(vals.length, in);
/*  455 */           for (int k = 0; k < bools.length; k++) {
/*  456 */             if (bools[k])
/*  457 */               vals[k] = BTreeMap.EMPTY; 
/*      */           } 
/*      */         } 
/*  460 */         return new BTreeMap.LeafNode(arrayOfObject1, vals, next);
/*      */       } 
/*  462 */       long[] child = new long[size];
/*  463 */       for (int j = 0; j < size; j++)
/*  464 */         child[j] = DataInput2.unpackLong(in); 
/*  465 */       Object[] keys = this.keySerializer.deserialize(in, start, end, size);
/*  466 */       assert keys.length == size;
/*  467 */       return new BTreeMap.DirNode(keys, child);
/*      */     }
/*      */     
/*      */     public int fixedSize() {
/*  473 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static long createRootRef(Engine engine, BTreeKeySerializer keySer, Serializer valueSer, Comparator comparator, int numberOfNodeMetas) {
/*  552 */     LeafNode emptyRoot = new LeafNode(new Object[] { null, null }, new Object[0], 0L);
/*  554 */     long rootRecidVal = engine.put(emptyRoot, (Serializer)new NodeSerializer<Object, Object>(false, keySer, valueSer, comparator, numberOfNodeMetas));
/*  555 */     return engine.put(Long.valueOf(rootRecidVal), Serializer.LONG);
/*      */   }
/*      */   
/*      */   protected final int findChildren(Object key, Object[] keys) {
/*  565 */     int left = 0;
/*  566 */     if (keys[0] == null)
/*  566 */       left++; 
/*  567 */     int right = (keys[keys.length - 1] == null) ? (keys.length - 1) : keys.length;
/*      */     while (true) {
/*  573 */       int middle = (left + right) / 2;
/*  574 */       if (keys[middle] == null)
/*  574 */         return middle; 
/*  575 */       if (this.comparator.compare(keys[middle], key) < 0) {
/*  576 */         left = middle + 1;
/*      */       } else {
/*  578 */         right = middle;
/*      */       } 
/*  580 */       if (left >= right)
/*  581 */         return right; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public V get(Object key) {
/*  589 */     return (V)get(key, true);
/*      */   }
/*      */   
/*      */   protected Object get(Object key, boolean expandValue) {
/*  593 */     if (key == null)
/*  593 */       throw new NullPointerException(); 
/*  594 */     K v = (K)key;
/*  595 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/*  597 */     BNode A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  600 */     while (!A.isLeaf()) {
/*  601 */       current = nextDir((DirNode)A, v);
/*  602 */       A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/*  606 */     LeafNode leaf = (LeafNode)A;
/*  607 */     int pos = findChildren(v, leaf.keys);
/*  608 */     while (pos == leaf.keys.length) {
/*  610 */       leaf = (LeafNode)this.engine.<BNode>get(leaf.next, this.nodeSerializer);
/*  611 */       pos = findChildren(v, leaf.keys);
/*      */     } 
/*  614 */     if (pos == leaf.keys.length - 1)
/*  615 */       return null; 
/*  618 */     if (leaf.keys[pos] != null && 0 == this.comparator.compare(v, (K)leaf.keys[pos])) {
/*  619 */       Object ret = leaf.vals[pos - 1];
/*  620 */       return expandValue ? valExpand(ret) : ret;
/*      */     } 
/*  622 */     return null;
/*      */   }
/*      */   
/*      */   protected V valExpand(Object ret) {
/*  626 */     if (this.valsOutsideNodes && ret != null) {
/*  627 */       long recid = ((ValRef)ret).recid;
/*  628 */       ret = this.engine.get(recid, this.valueSerializer);
/*      */     } 
/*  630 */     return (V)ret;
/*      */   }
/*      */   
/*      */   protected long nextDir(DirNode d, Object key) {
/*  634 */     int pos = findChildren(key, d.keys) - 1;
/*  635 */     if (pos < 0)
/*  635 */       pos = 0; 
/*  636 */     return d.child[pos];
/*      */   }
/*      */   
/*      */   public V put(K key, V value) {
/*  642 */     if (key == null || value == null)
/*  642 */       throw new NullPointerException(); 
/*  643 */     return put2(key, value, false);
/*      */   }
/*      */   
/*      */   protected V put2(K key, V value2, boolean putOnlyIfAbsent) {
/*      */     ValRef valRef;
/*  647 */     K v = key;
/*  648 */     if (v == null)
/*  648 */       throw new IllegalArgumentException("null key"); 
/*  649 */     if (value2 == null)
/*  649 */       throw new IllegalArgumentException("null value"); 
/*  651 */     V value = value2;
/*  652 */     if (this.valsOutsideNodes) {
/*  653 */       long recid = this.engine.put(value2, this.valueSerializer);
/*  654 */       valRef = new ValRef(recid);
/*      */     } 
/*  657 */     int stackPos = -1;
/*  658 */     long[] stackVals = new long[4];
/*  660 */     long rootRecid = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/*  661 */     long current = rootRecid;
/*  663 */     BNode A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  664 */     while (!A.isLeaf()) {
/*  665 */       long t = current;
/*  666 */       current = nextDir((DirNode)A, v);
/*  667 */       assert current > 0L : A;
/*  668 */       if (current != A.child()[(A.child()).length - 1]) {
/*  672 */         stackPos++;
/*  673 */         if (stackVals.length == stackPos)
/*  674 */           stackVals = Arrays.copyOf(stackVals, stackVals.length * 2); 
/*  675 */         stackVals[stackPos] = t;
/*      */       } 
/*  677 */       A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/*  679 */     int level = 1;
/*  681 */     long p = 0L;
/*      */     try {
/*      */       BNode B;
/*      */       long q;
/*      */       while (true) {
/*  686 */         lock(this.nodeLocks, current);
/*  687 */         boolean found = true;
/*  688 */         A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  689 */         int pos = findChildren(v, A.keys());
/*  691 */         if (pos < (A.keys()).length - 1 && v != null && A.keys()[pos] != null && 0 == this.comparator.compare(v, (K)A.keys()[pos])) {
/*  694 */           Object oldVal = A.vals()[pos - 1];
/*  695 */           if (putOnlyIfAbsent) {
/*  697 */             unlock(this.nodeLocks, current);
/*  699 */             return valExpand(oldVal);
/*      */           } 
/*  702 */           Object[] vals = Arrays.copyOf(A.vals(), (A.vals()).length);
/*  703 */           vals[pos - 1] = valRef;
/*  705 */           A = new LeafNode(Arrays.copyOf(A.keys(), (A.keys()).length), vals, ((LeafNode)A).next);
/*  706 */           assert this.nodeLocks.get(current) == Thread.currentThread();
/*  707 */           this.engine.update(current, A, this.nodeSerializer);
/*  709 */           V ret = valExpand(oldVal);
/*  710 */           notify(key, ret, value2);
/*  711 */           unlock(this.nodeLocks, current);
/*  713 */           return ret;
/*      */         } 
/*  717 */         if (A.highKey() != null && this.comparator.compare(v, (K)A.highKey()) > 0) {
/*  719 */           unlock(this.nodeLocks, current);
/*  720 */           found = false;
/*  721 */           int pos2 = findChildren(v, A.keys());
/*  722 */           while (A != null && pos2 == (A.keys()).length) {
/*  724 */             long next = A.next();
/*  726 */             if (next == 0L)
/*      */               break; 
/*  727 */             current = next;
/*  728 */             A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  729 */             pos2 = findChildren(v, A.keys());
/*      */           } 
/*      */         } 
/*  735 */         if (found) {
/*  738 */           if ((A.keys()).length - (A.isLeaf() ? 2 : 1) < this.maxNodeSize) {
/*  739 */             pos = findChildren(v, A.keys());
/*  740 */             Object[] arrayOfObject = arrayPut(A.keys(), pos, v);
/*  742 */             if (A.isLeaf()) {
/*  743 */               Object[] arrayOfObject1 = arrayPut(A.vals(), pos - 1, valRef);
/*  744 */               LeafNode n = new LeafNode(arrayOfObject, arrayOfObject1, ((LeafNode)A).next);
/*  745 */               assert this.nodeLocks.get(current) == Thread.currentThread();
/*  746 */               this.engine.update(current, n, this.nodeSerializer);
/*      */             } else {
/*  748 */               assert p != 0L;
/*  749 */               long[] arrayOfLong = arrayLongPut(A.child(), pos, p);
/*  750 */               DirNode d = new DirNode(arrayOfObject, arrayOfLong);
/*  751 */               assert this.nodeLocks.get(current) == Thread.currentThread();
/*  752 */               this.engine.update(current, d, this.nodeSerializer);
/*      */             } 
/*  755 */             notify(key, null, value2);
/*  756 */             unlock(this.nodeLocks, current);
/*  758 */             return null;
/*      */           } 
/*  761 */           pos = findChildren(v, A.keys());
/*  762 */           Object[] keys = arrayPut(A.keys(), pos, v);
/*  763 */           Object[] vals = A.isLeaf() ? arrayPut(A.vals(), pos - 1, valRef) : null;
/*  764 */           long[] child = A.isLeaf() ? null : arrayLongPut(A.child(), pos, p);
/*  765 */           int splitPos = keys.length / 2;
/*  767 */           if (A.isLeaf()) {
/*  768 */             Object[] vals2 = Arrays.copyOfRange(vals, splitPos, vals.length);
/*  770 */             B = new LeafNode(Arrays.copyOfRange(keys, splitPos, keys.length), vals2, ((LeafNode)A).next);
/*      */           } else {
/*  775 */             B = new DirNode(Arrays.copyOfRange(keys, splitPos, keys.length), Arrays.copyOfRange(child, splitPos, keys.length));
/*      */           } 
/*  778 */           q = this.engine.put(B, this.nodeSerializer);
/*  779 */           if (A.isLeaf()) {
/*  780 */             Object[] keys2 = Arrays.copyOf(keys, splitPos + 2);
/*  781 */             keys2[keys2.length - 1] = keys2[keys2.length - 2];
/*  782 */             Object[] vals2 = Arrays.copyOf(vals, splitPos);
/*  785 */             A = new LeafNode(keys2, vals2, q);
/*      */           } else {
/*  787 */             long[] child2 = Arrays.copyOf(child, splitPos + 1);
/*  788 */             child2[splitPos] = q;
/*  789 */             A = new DirNode(Arrays.copyOf(keys, splitPos + 1), child2);
/*      */           } 
/*  791 */           assert this.nodeLocks.get(current) == Thread.currentThread();
/*  792 */           this.engine.update(current, A, this.nodeSerializer);
/*  794 */           if (current != rootRecid) {
/*  795 */             unlock(this.nodeLocks, current);
/*  796 */             p = q;
/*  797 */             v = (K)A.highKey();
/*  798 */             level++;
/*  799 */             if (stackPos != -1) {
/*  800 */               current = stackVals[stackPos--];
/*      */             } else {
/*  803 */               current = ((Long)this.leftEdges.get(level - 1)).longValue();
/*      */             } 
/*  805 */             assert current > 0L;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*  807 */       BNode R = new DirNode(new Object[] { A.keys()[0], A.highKey(), B.isLeaf() ? null : B.highKey() }, new long[] { current, q, 0L });
/*  811 */       lock(this.nodeLocks, this.rootRecidRef);
/*  812 */       unlock(this.nodeLocks, current);
/*  813 */       long newRootRecid = this.engine.put(R, this.nodeSerializer);
/*  815 */       assert this.nodeLocks.get(this.rootRecidRef) == Thread.currentThread();
/*  816 */       this.engine.update(this.rootRecidRef, Long.valueOf(newRootRecid), Serializer.LONG);
/*  818 */       this.leftEdges.add(Long.valueOf(newRootRecid));
/*  820 */       notify(key, null, value2);
/*  821 */       unlock(this.nodeLocks, this.rootRecidRef);
/*  823 */       return null;
/*  827 */     } catch (RuntimeException e) {
/*  828 */       unlockAll(this.nodeLocks);
/*  829 */       throw e;
/*  830 */     } catch (Exception e) {
/*  831 */       unlockAll(this.nodeLocks);
/*  832 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static class BTreeIterator {
/*      */     final BTreeMap m;
/*      */     
/*      */     BTreeMap.LeafNode currentLeaf;
/*      */     
/*      */     Object lastReturnedKey;
/*      */     
/*      */     int currentPos;
/*      */     
/*      */     final Object hi;
/*      */     
/*      */     final boolean hiInclusive;
/*      */     
/*      */     BTreeIterator(BTreeMap m) {
/*  848 */       this.m = m;
/*  849 */       this.hi = null;
/*  850 */       this.hiInclusive = false;
/*  851 */       pointToStart();
/*      */     }
/*      */     
/*      */     BTreeIterator(BTreeMap m, Object lo, boolean loInclusive, Object hi, boolean hiInclusive) {
/*  856 */       this.m = m;
/*  857 */       if (lo == null) {
/*  858 */         pointToStart();
/*      */       } else {
/*  860 */         Fun.Tuple2<Integer, BTreeMap.LeafNode> l = m.findLargerNode(lo, loInclusive);
/*  861 */         this.currentPos = (l != null) ? ((Integer)l.a).intValue() : -1;
/*  862 */         this.currentLeaf = (l != null) ? (BTreeMap.LeafNode)l.b : null;
/*      */       } 
/*  865 */       this.hi = hi;
/*  866 */       this.hiInclusive = hiInclusive;
/*  867 */       if (hi != null && this.currentLeaf != null) {
/*  869 */         Object key = this.currentLeaf.keys[this.currentPos];
/*  870 */         int c = m.comparator.compare(key, hi);
/*  871 */         if (c > 0 || (c == 0 && !hiInclusive)) {
/*  873 */           this.currentLeaf = null;
/*  874 */           this.currentPos = -1;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void pointToStart() {
/*  883 */       long rootRecid = ((Long)this.m.engine.<Long>get(this.m.rootRecidRef, Serializer.LONG)).longValue();
/*  884 */       BTreeMap.BNode node = this.m.engine.<BTreeMap.BNode>get(rootRecid, this.m.nodeSerializer);
/*  885 */       while (!node.isLeaf())
/*  886 */         node = this.m.engine.<BTreeMap.BNode>get(node.child()[0], this.m.nodeSerializer); 
/*  888 */       this.currentLeaf = (BTreeMap.LeafNode)node;
/*  889 */       this.currentPos = 1;
/*  891 */       while (this.currentLeaf.keys.length == 2) {
/*  893 */         if (this.currentLeaf.next == 0L) {
/*  894 */           this.currentLeaf = null;
/*      */           return;
/*      */         } 
/*  897 */         this.currentLeaf = (BTreeMap.LeafNode)this.m.engine.<BTreeMap.BNode>get(this.currentLeaf.next, this.m.nodeSerializer);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  903 */       return (this.currentLeaf != null);
/*      */     }
/*      */     
/*      */     public void remove() {
/*  907 */       if (this.lastReturnedKey == null)
/*  907 */         throw new IllegalStateException(); 
/*  908 */       this.m.remove(this.lastReturnedKey);
/*  909 */       this.lastReturnedKey = null;
/*      */     }
/*      */     
/*      */     protected void advance() {
/*  913 */       if (this.currentLeaf == null)
/*      */         return; 
/*  914 */       this.lastReturnedKey = this.currentLeaf.keys[this.currentPos];
/*  915 */       this.currentPos++;
/*  916 */       if (this.currentPos == this.currentLeaf.keys.length - 1) {
/*  918 */         if (this.currentLeaf.next == 0L) {
/*  919 */           this.currentLeaf = null;
/*  920 */           this.currentPos = -1;
/*      */           return;
/*      */         } 
/*  923 */         this.currentPos = 1;
/*  924 */         this.currentLeaf = (BTreeMap.LeafNode)this.m.engine.<BTreeMap.BNode>get(this.currentLeaf.next, this.m.nodeSerializer);
/*  925 */         while (this.currentLeaf.keys.length == 2) {
/*  926 */           if (this.currentLeaf.next == 0L) {
/*  927 */             this.currentLeaf = null;
/*  928 */             this.currentPos = -1;
/*      */             return;
/*      */           } 
/*  931 */           this.currentLeaf = (BTreeMap.LeafNode)this.m.engine.<BTreeMap.BNode>get(this.currentLeaf.next, this.m.nodeSerializer);
/*      */         } 
/*      */       } 
/*  934 */       if (this.hi != null && this.currentLeaf != null) {
/*  936 */         Object key = this.currentLeaf.keys[this.currentPos];
/*  937 */         int c = this.m.comparator.compare(key, this.hi);
/*  938 */         if (c > 0 || (c == 0 && !this.hiInclusive)) {
/*  940 */           this.currentLeaf = null;
/*  941 */           this.currentPos = -1;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public V remove(Object key) {
/*  949 */     return remove2(key, null);
/*      */   }
/*      */   
/*      */   private V remove2(Object key, Object value) {
/*  953 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/*  955 */     BNode A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  956 */     while (!A.isLeaf()) {
/*  957 */       current = nextDir((DirNode)A, key);
/*  958 */       A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/*      */     try {
/*      */       while (true) {
/*  964 */         lock(this.nodeLocks, current);
/*  965 */         A = this.engine.<BNode>get(current, this.nodeSerializer);
/*  966 */         int pos = findChildren(key, A.keys());
/*  967 */         if (pos < (A.keys()).length && key != null && A.keys()[pos] != null && 0 == this.comparator.compare(key, A.keys()[pos])) {
/*  970 */           if (pos == (A.keys()).length - 1 && value == null) {
/*  971 */             unlock(this.nodeLocks, current);
/*  972 */             return null;
/*      */           } 
/*  976 */           Object oldVal = A.vals()[pos - 1];
/*  977 */           oldVal = valExpand(oldVal);
/*  978 */           if (value != null && !value.equals(oldVal)) {
/*  979 */             unlock(this.nodeLocks, current);
/*  980 */             return null;
/*      */           } 
/*  983 */           Object[] keys2 = new Object[(A.keys()).length - 1];
/*  984 */           System.arraycopy(A.keys(), 0, keys2, 0, pos);
/*  985 */           System.arraycopy(A.keys(), pos + 1, keys2, pos, keys2.length - pos);
/*  987 */           Object[] vals2 = new Object[(A.vals()).length - 1];
/*  988 */           System.arraycopy(A.vals(), 0, vals2, 0, pos - 1);
/*  989 */           System.arraycopy(A.vals(), pos, vals2, pos - 1, vals2.length - pos - 1);
/*  992 */           A = new LeafNode(keys2, vals2, ((LeafNode)A).next);
/*  993 */           assert this.nodeLocks.get(current) == Thread.currentThread();
/*  994 */           this.engine.update(current, A, this.nodeSerializer);
/*  995 */           notify((K)key, (V)oldVal, null);
/*  996 */           unlock(this.nodeLocks, current);
/*  997 */           return (V)oldVal;
/*      */         } 
/*  999 */         unlock(this.nodeLocks, current);
/* 1001 */         if (A.highKey() != null && this.comparator.compare(key, A.highKey()) > 0) {
/* 1002 */           int pos2 = findChildren(key, A.keys());
/* 1003 */           while (pos2 == (A.keys()).length) {
/* 1005 */             current = ((LeafNode)A).next;
/* 1006 */             A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */           } 
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/* 1009 */       return null;
/* 1013 */     } catch (RuntimeException e) {
/* 1014 */       unlockAll(this.nodeLocks);
/* 1015 */       throw e;
/* 1016 */     } catch (Exception e) {
/* 1017 */       unlockAll(this.nodeLocks);
/* 1018 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1025 */     Iterator<K> iter = keyIterator();
/* 1026 */     while (iter.hasNext()) {
/* 1027 */       iter.next();
/* 1028 */       iter.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   static class BTreeKeyIterator<K> extends BTreeIterator implements Iterator<K> {
/*      */     BTreeKeyIterator(BTreeMap m) {
/* 1036 */       super(m);
/*      */     }
/*      */     
/*      */     BTreeKeyIterator(BTreeMap m, Object lo, boolean loInclusive, Object hi, boolean hiInclusive) {
/* 1040 */       super(m, lo, loInclusive, hi, hiInclusive);
/*      */     }
/*      */     
/*      */     public K next() {
/* 1045 */       if (this.currentLeaf == null)
/* 1045 */         throw new NoSuchElementException(); 
/* 1046 */       K ret = (K)this.currentLeaf.keys[this.currentPos];
/* 1047 */       advance();
/* 1048 */       return ret;
/*      */     }
/*      */   }
/*      */   
/*      */   static class BTreeValueIterator<V> extends BTreeIterator implements Iterator<V> {
/*      */     BTreeValueIterator(BTreeMap m) {
/* 1055 */       super(m);
/*      */     }
/*      */     
/*      */     BTreeValueIterator(BTreeMap m, Object lo, boolean loInclusive, Object hi, boolean hiInclusive) {
/* 1059 */       super(m, lo, loInclusive, hi, hiInclusive);
/*      */     }
/*      */     
/*      */     public V next() {
/* 1064 */       if (this.currentLeaf == null)
/* 1064 */         throw new NoSuchElementException(); 
/* 1065 */       Object ret = this.currentLeaf.vals[this.currentPos - 1];
/* 1066 */       advance();
/* 1067 */       return (V)this.m.valExpand(ret);
/*      */     }
/*      */   }
/*      */   
/*      */   static class BTreeEntryIterator<K, V> extends BTreeIterator implements Iterator<Map.Entry<K, V>> {
/*      */     BTreeEntryIterator(BTreeMap m) {
/* 1075 */       super(m);
/*      */     }
/*      */     
/*      */     BTreeEntryIterator(BTreeMap m, Object lo, boolean loInclusive, Object hi, boolean hiInclusive) {
/* 1079 */       super(m, lo, loInclusive, hi, hiInclusive);
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> next() {
/* 1084 */       if (this.currentLeaf == null)
/* 1084 */         throw new NoSuchElementException(); 
/* 1085 */       K ret = (K)this.currentLeaf.keys[this.currentPos];
/* 1086 */       Object val = this.currentLeaf.vals[this.currentPos - 1];
/* 1087 */       advance();
/* 1088 */       return this.m.makeEntry(ret, this.m.valExpand(val));
/*      */     }
/*      */   }
/*      */   
/*      */   protected Map.Entry<K, V> makeEntry(Object key, Object value) {
/* 1099 */     assert !(value instanceof ValRef);
/* 1100 */     return new AbstractMap.SimpleImmutableEntry<K, V>((K)key, (V)value);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1106 */     return !keyIterator().hasNext();
/*      */   }
/*      */   
/*      */   public int size() {
/* 1111 */     long size = sizeLong();
/* 1112 */     if (size > 2147483647L)
/* 1112 */       return Integer.MAX_VALUE; 
/* 1113 */     return (int)size;
/*      */   }
/*      */   
/*      */   public long sizeLong() {
/* 1118 */     if (this.counter != null)
/* 1119 */       return this.counter.get(); 
/* 1121 */     long size = 0L;
/* 1122 */     BTreeIterator iter = new BTreeIterator(this);
/* 1123 */     while (iter.hasNext()) {
/* 1124 */       iter.advance();
/* 1125 */       size++;
/*      */     } 
/* 1127 */     return size;
/*      */   }
/*      */   
/*      */   public V putIfAbsent(K key, V value) {
/* 1132 */     if (key == null || value == null)
/* 1132 */       throw new NullPointerException(); 
/* 1133 */     return put2(key, value, true);
/*      */   }
/*      */   
/*      */   public boolean remove(Object key, Object value) {
/* 1138 */     if (key == null)
/* 1138 */       throw new NullPointerException(); 
/* 1139 */     if (value == null)
/* 1139 */       return false; 
/* 1140 */     return (remove2(key, value) != null);
/*      */   }
/*      */   
/*      */   public boolean replace(K key, V oldValue, V newValue) {
/* 1145 */     if (key == null || oldValue == null || newValue == null)
/* 1145 */       throw new NullPointerException(); 
/* 1147 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1149 */     BNode node = this.engine.<BNode>get(current, this.nodeSerializer);
/* 1151 */     while (!node.isLeaf()) {
/* 1152 */       current = nextDir((DirNode)node, key);
/* 1153 */       node = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/* 1156 */     lock(this.nodeLocks, current);
/*      */     try {
/* 1159 */       LeafNode leaf = (LeafNode)this.engine.<BNode>get(current, this.nodeSerializer);
/* 1160 */       int pos = findChildren(key, leaf.keys);
/* 1162 */       while (pos == leaf.keys.length) {
/* 1164 */         lock(this.nodeLocks, leaf.next);
/* 1165 */         unlock(this.nodeLocks, current);
/* 1166 */         current = leaf.next;
/* 1167 */         leaf = (LeafNode)this.engine.<BNode>get(current, this.nodeSerializer);
/* 1168 */         pos = findChildren(key, leaf.keys);
/*      */       } 
/* 1171 */       boolean ret = false;
/* 1172 */       if (key != null && leaf.keys[pos] != null && this.comparator.compare(key, (K)leaf.keys[pos]) == 0) {
/* 1174 */         Object val = leaf.vals[pos - 1];
/* 1175 */         val = valExpand(val);
/* 1176 */         if (oldValue.equals(val)) {
/* 1177 */           Object[] vals = Arrays.copyOf(leaf.vals, leaf.vals.length);
/* 1178 */           notify(key, oldValue, newValue);
/* 1179 */           vals[pos - 1] = newValue;
/* 1180 */           if (this.valsOutsideNodes) {
/* 1181 */             long recid = this.engine.put(newValue, this.valueSerializer);
/* 1182 */             vals[pos - 1] = new ValRef(recid);
/*      */           } 
/* 1185 */           leaf = new LeafNode(Arrays.copyOf(leaf.keys, leaf.keys.length), vals, leaf.next);
/* 1187 */           assert this.nodeLocks.get(current) == Thread.currentThread();
/* 1188 */           this.engine.update(current, leaf, this.nodeSerializer);
/* 1190 */           ret = true;
/*      */         } 
/*      */       } 
/* 1193 */       unlock(this.nodeLocks, current);
/* 1194 */       return ret;
/* 1195 */     } catch (RuntimeException e) {
/* 1196 */       unlockAll(this.nodeLocks);
/* 1197 */       throw e;
/* 1198 */     } catch (Exception e) {
/* 1199 */       unlockAll(this.nodeLocks);
/* 1200 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public V replace(K key, V value) {
/* 1206 */     if (key == null || value == null)
/* 1206 */       throw new NullPointerException(); 
/* 1207 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1209 */     BNode node = this.engine.<BNode>get(current, this.nodeSerializer);
/* 1211 */     while (!node.isLeaf()) {
/* 1212 */       current = nextDir((DirNode)node, key);
/* 1213 */       node = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/* 1216 */     lock(this.nodeLocks, current);
/*      */     try {
/* 1219 */       LeafNode leaf = (LeafNode)this.engine.<BNode>get(current, this.nodeSerializer);
/* 1220 */       int pos = findChildren(key, leaf.keys);
/* 1222 */       while (pos == leaf.keys.length) {
/* 1224 */         lock(this.nodeLocks, leaf.next);
/* 1225 */         unlock(this.nodeLocks, current);
/* 1226 */         current = leaf.next;
/* 1227 */         leaf = (LeafNode)this.engine.<BNode>get(current, this.nodeSerializer);
/* 1228 */         pos = findChildren(key, leaf.keys);
/*      */       } 
/* 1231 */       Object ret = null;
/* 1232 */       if (key != null && leaf.keys()[pos] != null && 0 == this.comparator.compare(key, (K)leaf.keys[pos])) {
/* 1234 */         Object[] vals = Arrays.copyOf(leaf.vals, leaf.vals.length);
/* 1235 */         Object oldVal = vals[pos - 1];
/* 1236 */         ret = valExpand(oldVal);
/* 1237 */         notify(key, (V)ret, value);
/* 1238 */         vals[pos - 1] = value;
/* 1239 */         if (this.valsOutsideNodes && value != null) {
/* 1240 */           long recid = this.engine.put(value, this.valueSerializer);
/* 1241 */           vals[pos - 1] = new ValRef(recid);
/*      */         } 
/* 1244 */         leaf = new LeafNode(Arrays.copyOf(leaf.keys, leaf.keys.length), vals, leaf.next);
/* 1245 */         assert this.nodeLocks.get(current) == Thread.currentThread();
/* 1246 */         this.engine.update(current, leaf, this.nodeSerializer);
/*      */       } 
/* 1250 */       unlock(this.nodeLocks, current);
/* 1251 */       return (V)ret;
/* 1252 */     } catch (RuntimeException e) {
/* 1253 */       unlockAll(this.nodeLocks);
/* 1254 */       throw e;
/* 1255 */     } catch (Exception e) {
/* 1256 */       unlockAll(this.nodeLocks);
/* 1257 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Comparator<? super K> comparator() {
/* 1265 */     return this.comparator;
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> firstEntry() {
/* 1271 */     long rootRecid = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1272 */     BNode n = this.engine.<BNode>get(rootRecid, this.nodeSerializer);
/* 1273 */     while (!n.isLeaf())
/* 1274 */       n = this.engine.<BNode>get(n.child()[0], this.nodeSerializer); 
/* 1276 */     LeafNode l = (LeafNode)n;
/* 1278 */     while (l.keys.length == 2) {
/* 1279 */       if (l.next == 0L)
/* 1279 */         return null; 
/* 1280 */       l = (LeafNode)this.engine.<BNode>get(l.next, this.nodeSerializer);
/*      */     } 
/* 1282 */     return makeEntry(l.keys[1], valExpand(l.vals[0]));
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> pollFirstEntry() {
/*      */     Map.Entry<K, V> e;
/*      */     do {
/* 1289 */       e = firstEntry();
/* 1290 */     } while (e != null && !remove(e.getKey(), e.getValue()));
/* 1291 */     return e;
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> pollLastEntry() {
/*      */     Map.Entry<K, V> e;
/*      */     do {
/* 1299 */       e = lastEntry();
/* 1300 */     } while (e != null && !remove(e.getKey(), e.getValue()));
/* 1301 */     return e;
/*      */   }
/*      */   
/*      */   protected Map.Entry<K, V> findSmaller(K key, boolean inclusive) {
/* 1308 */     if (key == null)
/* 1308 */       throw new NullPointerException(); 
/* 1309 */     long rootRecid = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1310 */     BNode n = this.engine.<BNode>get(rootRecid, this.nodeSerializer);
/* 1312 */     Map.Entry<K, V> k = findSmallerRecur(n, key, inclusive);
/* 1313 */     if (k == null || k.getValue() == null)
/* 1313 */       return null; 
/* 1314 */     return k;
/*      */   }
/*      */   
/*      */   private Map.Entry<K, V> findSmallerRecur(BNode n, K key, boolean inclusive) {
/* 1318 */     boolean leaf = n.isLeaf();
/* 1319 */     int start = leaf ? ((n.keys()).length - 2) : ((n.keys()).length - 1);
/* 1320 */     int end = leaf ? 1 : 0;
/* 1321 */     int res = inclusive ? 1 : 0;
/* 1322 */     for (int i = start; i >= end; i--) {
/* 1323 */       Object key2 = n.keys()[i];
/* 1324 */       int comp = (key2 == null) ? -1 : this.comparator.compare(key2, key);
/* 1325 */       if (comp < res) {
/* 1326 */         if (leaf)
/* 1327 */           return (key2 == null) ? null : makeEntry(key2, valExpand(n.vals()[i - 1])); 
/* 1330 */         long recid = n.child()[i];
/* 1331 */         if (recid != 0L) {
/* 1332 */           BNode n2 = this.engine.<BNode>get(recid, this.nodeSerializer);
/* 1333 */           Map.Entry<K, V> ret = findSmallerRecur(n2, key, inclusive);
/* 1334 */           if (ret != null)
/* 1334 */             return ret; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1339 */     return null;
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> lastEntry() {
/* 1345 */     long rootRecid = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1346 */     BNode n = this.engine.<BNode>get(rootRecid, this.nodeSerializer);
/* 1347 */     Map.Entry<K, V> e = lastEntryRecur(n);
/* 1348 */     if (e != null && e.getValue() == null)
/* 1348 */       return null; 
/* 1349 */     return e;
/*      */   }
/*      */   
/*      */   private Map.Entry<K, V> lastEntryRecur(BNode n) {
/* 1354 */     if (n.isLeaf()) {
/* 1356 */       if (n.next() != 0L) {
/* 1357 */         BNode n2 = this.engine.<BNode>get(n.next(), this.nodeSerializer);
/* 1358 */         Map.Entry<K, V> ret = lastEntryRecur(n2);
/* 1359 */         if (ret != null)
/* 1360 */           return ret; 
/*      */       } 
/* 1364 */       for (int i = (n.keys()).length - 2; i > 0; i--) {
/* 1365 */         Object k = n.keys()[i];
/* 1366 */         if (k != null && (n.vals()).length > 0) {
/* 1367 */           Object val = valExpand(n.vals()[i - 1]);
/* 1368 */           if (val != null)
/* 1369 */             return makeEntry(k, val); 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1375 */       for (int i = (n.child()).length - 1; i >= 0; i--) {
/* 1376 */         long childRecid = n.child()[i];
/* 1377 */         if (childRecid != 0L) {
/* 1378 */           BNode n2 = this.engine.<BNode>get(childRecid, this.nodeSerializer);
/* 1379 */           Map.Entry<K, V> ret = lastEntryRecur(n2);
/* 1380 */           if (ret != null)
/* 1381 */             return ret; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1384 */     return null;
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> lowerEntry(K key) {
/* 1389 */     if (key == null)
/* 1389 */       throw new NullPointerException(); 
/* 1390 */     return findSmaller(key, false);
/*      */   }
/*      */   
/*      */   public K lowerKey(K key) {
/* 1395 */     Map.Entry<K, V> n = lowerEntry(key);
/* 1396 */     return (n == null) ? null : n.getKey();
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> floorEntry(K key) {
/* 1401 */     if (key == null)
/* 1401 */       throw new NullPointerException(); 
/* 1402 */     return findSmaller(key, true);
/*      */   }
/*      */   
/*      */   public K floorKey(K key) {
/* 1407 */     Map.Entry<K, V> n = floorEntry(key);
/* 1408 */     return (n == null) ? null : n.getKey();
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> ceilingEntry(K key) {
/* 1413 */     if (key == null)
/* 1413 */       throw new NullPointerException(); 
/* 1414 */     return findLarger(key, true);
/*      */   }
/*      */   
/*      */   protected Map.Entry<K, V> findLarger(K key, boolean inclusive) {
/* 1418 */     if (key == null)
/* 1418 */       return null; 
/* 1420 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1422 */     BNode A = this.engine.<BNode>get(current, this.nodeSerializer);
/* 1425 */     while (!A.isLeaf()) {
/* 1426 */       current = nextDir((DirNode)A, key);
/* 1427 */       A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/* 1431 */     LeafNode leaf = (LeafNode)A;
/* 1433 */     int comp = inclusive ? 1 : 0;
/*      */     while (true) {
/* 1435 */       for (int i = 1; i < leaf.keys.length - 1; i++) {
/* 1436 */         if (leaf.keys[i] != null)
/* 1438 */           if (this.comparator.compare((T)key, (T)leaf.keys[i]) < comp)
/* 1439 */             return makeEntry(leaf.keys[i], valExpand(leaf.vals[i - 1]));  
/*      */       } 
/* 1444 */       if (leaf.next == 0L)
/* 1444 */         return null; 
/* 1445 */       leaf = (LeafNode)this.engine.<BNode>get(leaf.next, this.nodeSerializer);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Fun.Tuple2<Integer, LeafNode> findLargerNode(K key, boolean inclusive) {
/* 1451 */     if (key == null)
/* 1451 */       return null; 
/* 1453 */     long current = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 1455 */     BNode A = this.engine.<BNode>get(current, this.nodeSerializer);
/* 1458 */     while (!A.isLeaf()) {
/* 1459 */       current = nextDir((DirNode)A, key);
/* 1460 */       A = this.engine.<BNode>get(current, this.nodeSerializer);
/*      */     } 
/* 1464 */     LeafNode leaf = (LeafNode)A;
/* 1466 */     int comp = inclusive ? 1 : 0;
/*      */     while (true) {
/* 1468 */       for (int i = 1; i < leaf.keys.length - 1; i++) {
/* 1469 */         if (leaf.keys[i] != null)
/* 1471 */           if (this.comparator.compare((T)key, (T)leaf.keys[i]) < comp)
/* 1472 */             return Fun.t2(Integer.valueOf(i), leaf);  
/*      */       } 
/* 1475 */       if (leaf.next == 0L)
/* 1475 */         return null; 
/* 1476 */       leaf = (LeafNode)this.engine.<BNode>get(leaf.next, this.nodeSerializer);
/*      */     } 
/*      */   }
/*      */   
/*      */   public K ceilingKey(K key) {
/* 1484 */     if (key == null)
/* 1484 */       throw new NullPointerException(); 
/* 1485 */     Map.Entry<K, V> n = ceilingEntry(key);
/* 1486 */     return (n == null) ? null : n.getKey();
/*      */   }
/*      */   
/*      */   public Map.Entry<K, V> higherEntry(K key) {
/* 1491 */     if (key == null)
/* 1491 */       throw new NullPointerException(); 
/* 1492 */     return findLarger(key, false);
/*      */   }
/*      */   
/*      */   public K higherKey(K key) {
/* 1497 */     if (key == null)
/* 1497 */       throw new NullPointerException(); 
/* 1498 */     Map.Entry<K, V> n = higherEntry(key);
/* 1499 */     return (n == null) ? null : n.getKey();
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/* 1504 */     if (key == null)
/* 1504 */       throw new NullPointerException(); 
/* 1505 */     return (get(key, false) != null);
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/* 1510 */     if (value == null)
/* 1510 */       throw new NullPointerException(); 
/* 1511 */     Iterator<V> valueIter = valueIterator();
/* 1512 */     while (valueIter.hasNext()) {
/* 1513 */       if (value.equals(valueIter.next()))
/* 1514 */         return true; 
/*      */     } 
/* 1516 */     return false;
/*      */   }
/*      */   
/*      */   public K firstKey() {
/* 1522 */     Map.Entry<K, V> e = firstEntry();
/* 1523 */     if (e == null)
/* 1523 */       throw new NoSuchElementException(); 
/* 1524 */     return e.getKey();
/*      */   }
/*      */   
/*      */   public K lastKey() {
/* 1529 */     Map.Entry<K, V> e = lastEntry();
/* 1530 */     if (e == null)
/* 1530 */       throw new NoSuchElementException(); 
/* 1531 */     return e.getKey();
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
/* 1540 */     if (fromKey == null || toKey == null)
/* 1541 */       throw new NullPointerException(); 
/* 1542 */     return new SubMap<K, V>(this, fromKey, fromInclusive, toKey, toInclusive);
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> headMap(K toKey, boolean inclusive) {
/* 1549 */     if (toKey == null)
/* 1550 */       throw new NullPointerException(); 
/* 1551 */     return new SubMap<K, V>(this, null, false, toKey, inclusive);
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
/* 1558 */     if (fromKey == null)
/* 1559 */       throw new NullPointerException(); 
/* 1560 */     return new SubMap<K, V>(this, fromKey, inclusive, null, false);
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> subMap(K fromKey, K toKey) {
/* 1566 */     return subMap(fromKey, true, toKey, false);
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> headMap(K toKey) {
/* 1571 */     return headMap(toKey, false);
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> tailMap(K fromKey) {
/* 1576 */     return tailMap(fromKey, true);
/*      */   }
/*      */   
/*      */   Iterator<K> keyIterator() {
/* 1581 */     return new BTreeKeyIterator<K>(this);
/*      */   }
/*      */   
/*      */   Iterator<V> valueIterator() {
/* 1585 */     return new BTreeValueIterator<V>(this);
/*      */   }
/*      */   
/*      */   Iterator<Map.Entry<K, V>> entryIterator() {
/* 1589 */     return new BTreeEntryIterator<K, V>(this);
/*      */   }
/*      */   
/*      */   public NavigableSet<K> keySet() {
/* 1597 */     return this.keySet;
/*      */   }
/*      */   
/*      */   public NavigableSet<K> navigableKeySet() {
/* 1602 */     return this.keySet;
/*      */   }
/*      */   
/*      */   public Collection<V> values() {
/* 1607 */     return this.values;
/*      */   }
/*      */   
/*      */   public Set<Map.Entry<K, V>> entrySet() {
/* 1612 */     return this.entrySet;
/*      */   }
/*      */   
/*      */   public ConcurrentNavigableMap<K, V> descendingMap() {
/* 1617 */     return this.descendingMap;
/*      */   }
/*      */   
/*      */   public NavigableSet<K> descendingKeySet() {
/* 1622 */     return this.descendingMap.keySet();
/*      */   }
/*      */   
/*      */   static <E> List<E> toList(Collection<E> c) {
/* 1627 */     List<E> list = new ArrayList<E>();
/* 1628 */     for (E e : c)
/* 1629 */       list.add(e); 
/* 1631 */     return list;
/*      */   }
/*      */   
/*      */   static final class KeySet<E> extends AbstractSet<E> implements NavigableSet<E> {
/*      */     protected final ConcurrentNavigableMap<E, Object> m;
/*      */     
/*      */     private final boolean hasValues;
/*      */     
/*      */     KeySet(ConcurrentNavigableMap<E, Object> map, boolean hasValues) {
/* 1641 */       this.m = map;
/* 1642 */       this.hasValues = hasValues;
/*      */     }
/*      */     
/*      */     public int size() {
/* 1645 */       return this.m.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1647 */       return this.m.isEmpty();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 1649 */       return this.m.containsKey(o);
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 1651 */       return (this.m.remove(o) != null);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1653 */       this.m.clear();
/*      */     }
/*      */     
/*      */     public E lower(E e) {
/* 1655 */       return this.m.lowerKey(e);
/*      */     }
/*      */     
/*      */     public E floor(E e) {
/* 1657 */       return this.m.floorKey(e);
/*      */     }
/*      */     
/*      */     public E ceiling(E e) {
/* 1659 */       return this.m.ceilingKey(e);
/*      */     }
/*      */     
/*      */     public E higher(E e) {
/* 1661 */       return this.m.higherKey(e);
/*      */     }
/*      */     
/*      */     public Comparator<? super E> comparator() {
/* 1663 */       return this.m.comparator();
/*      */     }
/*      */     
/*      */     public E first() {
/* 1665 */       return this.m.firstKey();
/*      */     }
/*      */     
/*      */     public E last() {
/* 1667 */       return this.m.lastKey();
/*      */     }
/*      */     
/*      */     public E pollFirst() {
/* 1670 */       Map.Entry<E, Object> e = this.m.pollFirstEntry();
/* 1671 */       return (e == null) ? null : e.getKey();
/*      */     }
/*      */     
/*      */     public E pollLast() {
/* 1675 */       Map.Entry<E, Object> e = this.m.pollLastEntry();
/* 1676 */       return (e == null) ? null : e.getKey();
/*      */     }
/*      */     
/*      */     public Iterator<E> iterator() {
/* 1680 */       if (this.m instanceof BTreeMap)
/* 1681 */         return ((BTreeMap)this.m).keyIterator(); 
/* 1682 */       if (this.m instanceof BTreeMap.SubMap)
/* 1683 */         return ((BTreeMap.SubMap)this.m).keyIterator(); 
/* 1685 */       return ((BTreeMap.DescendingMap)this.m).keyIterator();
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/* 1689 */       if (o == this)
/* 1690 */         return true; 
/* 1691 */       if (!(o instanceof Set))
/* 1692 */         return false; 
/* 1693 */       Collection<?> c = (Collection)o;
/*      */       try {
/* 1695 */         return (containsAll(c) && c.containsAll(this));
/* 1696 */       } catch (ClassCastException unused) {
/* 1697 */         return false;
/* 1698 */       } catch (NullPointerException unused) {
/* 1699 */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/* 1703 */       return BTreeMap.<E>toList(this).toArray();
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] a) {
/* 1705 */       return (T[])BTreeMap.<E>toList(this).toArray((Object[])a);
/*      */     }
/*      */     
/*      */     public Iterator<E> descendingIterator() {
/* 1708 */       return descendingSet().iterator();
/*      */     }
/*      */     
/*      */     public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
/* 1715 */       return new KeySet(this.m.subMap(fromElement, fromInclusive, toElement, toInclusive), this.hasValues);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> headSet(E toElement, boolean inclusive) {
/* 1720 */       return new KeySet(this.m.headMap(toElement, inclusive), this.hasValues);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
/* 1724 */       return new KeySet(this.m.tailMap(fromElement, inclusive), this.hasValues);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> subSet(E fromElement, E toElement) {
/* 1728 */       return subSet(fromElement, true, toElement, false);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> headSet(E toElement) {
/* 1732 */       return headSet(toElement, false);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> tailSet(E fromElement) {
/* 1736 */       return tailSet(fromElement, true);
/*      */     }
/*      */     
/*      */     public NavigableSet<E> descendingSet() {
/* 1740 */       return new KeySet(this.m.descendingMap(), this.hasValues);
/*      */     }
/*      */     
/*      */     public boolean add(E k) {
/* 1745 */       if (this.hasValues)
/* 1746 */         throw new UnsupportedOperationException(); 
/* 1748 */       return (this.m.put(k, BTreeMap.EMPTY) == null);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class Values<E> extends AbstractCollection<E> {
/*      */     private final ConcurrentNavigableMap<Object, E> m;
/*      */     
/*      */     Values(ConcurrentNavigableMap<Object, E> map) {
/* 1755 */       this.m = map;
/*      */     }
/*      */     
/*      */     public Iterator<E> iterator() {
/* 1759 */       if (this.m instanceof BTreeMap)
/* 1760 */         return ((BTreeMap)this.m).valueIterator(); 
/* 1762 */       return ((BTreeMap.SubMap)this.m).valueIterator();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1766 */       return this.m.isEmpty();
/*      */     }
/*      */     
/*      */     public int size() {
/* 1770 */       return this.m.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 1774 */       return this.m.containsValue(o);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1778 */       this.m.clear();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/* 1781 */       return BTreeMap.<E>toList(this).toArray();
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] a) {
/* 1783 */       return (T[])BTreeMap.<E>toList(this).toArray((Object[])a);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class EntrySet<K1, V1> extends AbstractSet<Map.Entry<K1, V1>> {
/*      */     private final ConcurrentNavigableMap<K1, V1> m;
/*      */     
/*      */     EntrySet(ConcurrentNavigableMap<K1, V1> map) {
/* 1789 */       this.m = map;
/*      */     }
/*      */     
/*      */     public Iterator<Map.Entry<K1, V1>> iterator() {
/* 1794 */       if (this.m instanceof BTreeMap)
/* 1795 */         return ((BTreeMap<K1, V1>)this.m).entryIterator(); 
/* 1796 */       if (this.m instanceof BTreeMap.SubMap)
/* 1797 */         return ((BTreeMap.SubMap<K1, V1>)this.m).entryIterator(); 
/* 1799 */       return ((BTreeMap.DescendingMap<K1, V1>)this.m).entryIterator();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 1804 */       if (!(o instanceof Map.Entry))
/* 1805 */         return false; 
/* 1806 */       Map.Entry<K1, V1> e = (Map.Entry<K1, V1>)o;
/* 1807 */       K1 key = e.getKey();
/* 1808 */       if (key == null)
/* 1808 */         return false; 
/* 1809 */       V1 v = this.m.get(key);
/* 1810 */       return (v != null && v.equals(e.getValue()));
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 1814 */       if (!(o instanceof Map.Entry))
/* 1815 */         return false; 
/* 1816 */       Map.Entry<K1, V1> e = (Map.Entry<K1, V1>)o;
/* 1817 */       K1 key = e.getKey();
/* 1818 */       if (key == null)
/* 1818 */         return false; 
/* 1819 */       return this.m.remove(key, e.getValue());
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1824 */       return this.m.isEmpty();
/*      */     }
/*      */     
/*      */     public int size() {
/* 1828 */       return this.m.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1832 */       this.m.clear();
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/* 1836 */       if (o == this)
/* 1837 */         return true; 
/* 1838 */       if (!(o instanceof Set))
/* 1839 */         return false; 
/* 1840 */       Collection<?> c = (Collection)o;
/*      */       try {
/* 1842 */         return (containsAll(c) && c.containsAll(this));
/* 1843 */       } catch (ClassCastException unused) {
/* 1844 */         return false;
/* 1845 */       } catch (NullPointerException unused) {
/* 1846 */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/* 1850 */       return BTreeMap.<K1>toList((Collection)this).toArray();
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] a) {
/* 1852 */       return (T[])BTreeMap.<K1>toList((Collection)this).toArray((Object[])a);
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class SubMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V> {
/*      */     protected final BTreeMap<K, V> m;
/*      */     
/*      */     protected final K lo;
/*      */     
/*      */     protected final boolean loInclusive;
/*      */     
/*      */     protected final K hi;
/*      */     
/*      */     protected final boolean hiInclusive;
/*      */     
/*      */     public SubMap(BTreeMap<K, V> m, K lo, boolean loInclusive, K hi, boolean hiInclusive) {
/* 1868 */       this.m = m;
/* 1869 */       this.lo = lo;
/* 1870 */       this.loInclusive = loInclusive;
/* 1871 */       this.hi = hi;
/* 1872 */       this.hiInclusive = hiInclusive;
/* 1873 */       if (lo != null && hi != null && m.comparator.compare(lo, hi) > 0)
/* 1874 */         throw new IllegalArgumentException(); 
/*      */     }
/*      */     
/*      */     public boolean containsKey(Object key) {
/* 1885 */       if (key == null)
/* 1885 */         throw new NullPointerException(); 
/* 1886 */       K k = (K)key;
/* 1887 */       return (inBounds(k) && this.m.containsKey(k));
/*      */     }
/*      */     
/*      */     public V get(Object key) {
/* 1892 */       if (key == null)
/* 1892 */         throw new NullPointerException(); 
/* 1893 */       K k = (K)key;
/* 1894 */       return !inBounds(k) ? null : this.m.get(k);
/*      */     }
/*      */     
/*      */     public V put(K key, V value) {
/* 1899 */       checkKeyBounds(key);
/* 1900 */       return this.m.put(key, value);
/*      */     }
/*      */     
/*      */     public V remove(Object key) {
/* 1905 */       K k = (K)key;
/* 1906 */       return !inBounds(k) ? null : this.m.remove(k);
/*      */     }
/*      */     
/*      */     public int size() {
/* 1911 */       Iterator<K> i = keyIterator();
/* 1912 */       int counter = 0;
/* 1913 */       while (i.hasNext()) {
/* 1914 */         counter++;
/* 1915 */         i.next();
/*      */       } 
/* 1917 */       return counter;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1922 */       return !keyIterator().hasNext();
/*      */     }
/*      */     
/*      */     public boolean containsValue(Object value) {
/* 1927 */       if (value == null)
/* 1927 */         throw new NullPointerException(); 
/* 1928 */       Iterator<V> i = valueIterator();
/* 1929 */       while (i.hasNext()) {
/* 1930 */         if (value.equals(i.next()))
/* 1931 */           return true; 
/*      */       } 
/* 1933 */       return false;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1938 */       Iterator<K> i = keyIterator();
/* 1939 */       while (i.hasNext()) {
/* 1940 */         i.next();
/* 1941 */         i.remove();
/*      */       } 
/*      */     }
/*      */     
/*      */     public V putIfAbsent(K key, V value) {
/* 1950 */       checkKeyBounds(key);
/* 1951 */       return this.m.putIfAbsent(key, value);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key, Object value) {
/* 1956 */       K k = (K)key;
/* 1957 */       return (inBounds(k) && this.m.remove(k, value));
/*      */     }
/*      */     
/*      */     public boolean replace(K key, V oldValue, V newValue) {
/* 1962 */       checkKeyBounds(key);
/* 1963 */       return this.m.replace(key, oldValue, newValue);
/*      */     }
/*      */     
/*      */     public V replace(K key, V value) {
/* 1968 */       checkKeyBounds(key);
/* 1969 */       return this.m.replace(key, value);
/*      */     }
/*      */     
/*      */     public Comparator<? super K> comparator() {
/* 1976 */       return this.m.comparator();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> lowerEntry(K key) {
/* 1983 */       if (key == null)
/* 1983 */         throw new NullPointerException(); 
/* 1984 */       if (tooLow(key))
/* 1984 */         return null; 
/* 1986 */       if (tooHigh(key))
/* 1987 */         return lastEntry(); 
/* 1989 */       Map.Entry<K, V> r = this.m.lowerEntry(key);
/* 1990 */       return (r != null && !tooLow(r.getKey())) ? r : null;
/*      */     }
/*      */     
/*      */     public K lowerKey(K key) {
/* 1995 */       Map.Entry<K, V> n = lowerEntry(key);
/* 1996 */       return (n == null) ? null : n.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> floorEntry(K key) {
/* 2001 */       if (key == null)
/* 2001 */         throw new NullPointerException(); 
/* 2002 */       if (tooLow(key))
/* 2002 */         return null; 
/* 2004 */       if (tooHigh(key))
/* 2005 */         return lastEntry(); 
/* 2008 */       Map.Entry<K, V> ret = this.m.floorEntry(key);
/* 2009 */       if (ret != null && tooLow(ret.getKey()))
/* 2009 */         return null; 
/* 2010 */       return ret;
/*      */     }
/*      */     
/*      */     public K floorKey(K key) {
/* 2016 */       Map.Entry<K, V> n = floorEntry(key);
/* 2017 */       return (n == null) ? null : n.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> ceilingEntry(K key) {
/* 2022 */       if (key == null)
/* 2022 */         throw new NullPointerException(); 
/* 2023 */       if (tooHigh(key))
/* 2023 */         return null; 
/* 2025 */       if (tooLow(key))
/* 2026 */         return firstEntry(); 
/* 2029 */       Map.Entry<K, V> ret = this.m.ceilingEntry(key);
/* 2030 */       if (ret != null && tooHigh(ret.getKey()))
/* 2030 */         return null; 
/* 2031 */       return ret;
/*      */     }
/*      */     
/*      */     public K ceilingKey(K key) {
/* 2036 */       Map.Entry<K, V> k = ceilingEntry(key);
/* 2037 */       return (k != null) ? k.getKey() : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> higherEntry(K key) {
/* 2042 */       Map.Entry<K, V> r = this.m.higherEntry(key);
/* 2043 */       return (r != null && inBounds(r.getKey())) ? r : null;
/*      */     }
/*      */     
/*      */     public K higherKey(K key) {
/* 2048 */       Map.Entry<K, V> k = higherEntry(key);
/* 2049 */       return (k != null) ? k.getKey() : null;
/*      */     }
/*      */     
/*      */     public K firstKey() {
/* 2055 */       Map.Entry<K, V> e = firstEntry();
/* 2056 */       if (e == null)
/* 2056 */         throw new NoSuchElementException(); 
/* 2057 */       return e.getKey();
/*      */     }
/*      */     
/*      */     public K lastKey() {
/* 2062 */       Map.Entry<K, V> e = lastEntry();
/* 2063 */       if (e == null)
/* 2063 */         throw new NoSuchElementException(); 
/* 2064 */       return e.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> firstEntry() {
/* 2070 */       Map.Entry<K, V> k = (this.lo == null) ? this.m.firstEntry() : this.m.findLarger(this.lo, this.loInclusive);
/* 2074 */       return (k != null && inBounds(k.getKey())) ? k : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> lastEntry() {
/* 2080 */       Map.Entry<K, V> k = (this.hi == null) ? this.m.lastEntry() : this.m.findSmaller(this.hi, this.hiInclusive);
/* 2085 */       return (k != null && inBounds(k.getKey())) ? k : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> pollFirstEntry() {
/*      */       Map.Entry<K, V> e;
/*      */       do {
/* 2091 */         e = firstEntry();
/* 2092 */       } while (e != null && !remove(e.getKey(), e.getValue()));
/* 2093 */       return e;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> pollLastEntry() {
/*      */       Map.Entry<K, V> e;
/*      */       do {
/* 2101 */         e = lastEntry();
/* 2102 */       } while (e != null && !remove(e.getKey(), e.getValue()));
/* 2103 */       return e;
/*      */     }
/*      */     
/*      */     private SubMap<K, V> newSubMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
/* 2126 */       if (this.lo != null)
/* 2127 */         if (fromKey == null) {
/* 2128 */           fromKey = this.lo;
/* 2129 */           fromInclusive = this.loInclusive;
/*      */         } else {
/* 2132 */           int c = this.m.comparator.compare(fromKey, this.lo);
/* 2133 */           if (c < 0 || (c == 0 && !this.loInclusive && fromInclusive))
/* 2134 */             throw new IllegalArgumentException("key out of range"); 
/*      */         }  
/* 2137 */       if (this.hi != null)
/* 2138 */         if (toKey == null) {
/* 2139 */           toKey = this.hi;
/* 2140 */           toInclusive = this.hiInclusive;
/*      */         } else {
/* 2143 */           int c = this.m.comparator.compare(toKey, this.hi);
/* 2144 */           if (c > 0 || (c == 0 && !this.hiInclusive && toInclusive))
/* 2145 */             throw new IllegalArgumentException("key out of range"); 
/*      */         }  
/* 2148 */       return new SubMap(this.m, fromKey, fromInclusive, toKey, toInclusive);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
/* 2157 */       if (fromKey == null || toKey == null)
/* 2158 */         throw new NullPointerException(); 
/* 2159 */       return newSubMap(fromKey, fromInclusive, toKey, toInclusive);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> headMap(K toKey, boolean inclusive) {
/* 2165 */       if (toKey == null)
/* 2166 */         throw new NullPointerException(); 
/* 2167 */       return newSubMap(null, false, toKey, inclusive);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> tailMap(K fromKey, boolean inclusive) {
/* 2173 */       if (fromKey == null)
/* 2174 */         throw new NullPointerException(); 
/* 2175 */       return newSubMap(fromKey, inclusive, null, false);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> subMap(K fromKey, K toKey) {
/* 2180 */       return subMap(fromKey, true, toKey, false);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> headMap(K toKey) {
/* 2185 */       return headMap(toKey, false);
/*      */     }
/*      */     
/*      */     public SubMap<K, V> tailMap(K fromKey) {
/* 2190 */       return tailMap(fromKey, true);
/*      */     }
/*      */     
/*      */     public ConcurrentNavigableMap<K, V> descendingMap() {
/* 2195 */       return new BTreeMap.DescendingMap<K, V>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive);
/*      */     }
/*      */     
/*      */     public NavigableSet<K> navigableKeySet() {
/* 2200 */       return new BTreeMap.KeySet<K>((ConcurrentNavigableMap)this, this.m.hasValues);
/*      */     }
/*      */     
/*      */     private boolean tooLow(K key) {
/* 2209 */       if (this.lo != null) {
/* 2210 */         int c = this.m.comparator.compare(key, this.lo);
/* 2211 */         if (c < 0 || (c == 0 && !this.loInclusive))
/* 2212 */           return true; 
/*      */       } 
/* 2214 */       return false;
/*      */     }
/*      */     
/*      */     private boolean tooHigh(K key) {
/* 2218 */       if (this.hi != null) {
/* 2219 */         int c = this.m.comparator.compare(key, this.hi);
/* 2220 */         if (c > 0 || (c == 0 && !this.hiInclusive))
/* 2221 */           return true; 
/*      */       } 
/* 2223 */       return false;
/*      */     }
/*      */     
/*      */     private boolean inBounds(K key) {
/* 2227 */       return (!tooLow(key) && !tooHigh(key));
/*      */     }
/*      */     
/*      */     private void checkKeyBounds(K key) throws IllegalArgumentException {
/* 2231 */       if (key == null)
/* 2232 */         throw new NullPointerException(); 
/* 2233 */       if (!inBounds(key))
/* 2234 */         throw new IllegalArgumentException("key out of range"); 
/*      */     }
/*      */     
/*      */     public NavigableSet<K> keySet() {
/* 2243 */       return new BTreeMap.KeySet<K>((ConcurrentNavigableMap)this, this.m.hasValues);
/*      */     }
/*      */     
/*      */     public NavigableSet<K> descendingKeySet() {
/* 2248 */       return (new BTreeMap.DescendingMap<K, Object>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive)).keySet();
/*      */     }
/*      */     
/*      */     public Set<Map.Entry<K, V>> entrySet() {
/* 2255 */       return new BTreeMap.EntrySet<K, V>(this);
/*      */     }
/*      */     
/*      */     Iterator<K> keyIterator() {
/* 2261 */       return new BTreeMap.BTreeKeyIterator<K>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive);
/*      */     }
/*      */     
/*      */     Iterator<V> valueIterator() {
/* 2265 */       return new BTreeMap.BTreeValueIterator<V>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive);
/*      */     }
/*      */     
/*      */     Iterator<Map.Entry<K, V>> entryIterator() {
/* 2269 */       return new BTreeMap.BTreeEntryIterator<K, V>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive);
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class DescendingMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V> {
/*      */     protected final BTreeMap<K, V> m;
/*      */     
/*      */     protected final K lo;
/*      */     
/*      */     protected final boolean loInclusive;
/*      */     
/*      */     protected final K hi;
/*      */     
/*      */     protected final boolean hiInclusive;
/*      */     
/*      */     public DescendingMap(BTreeMap<K, V> m, K lo, boolean loInclusive, K hi, boolean hiInclusive) {
/* 2286 */       this.m = m;
/* 2287 */       this.lo = lo;
/* 2288 */       this.loInclusive = loInclusive;
/* 2289 */       this.hi = hi;
/* 2290 */       this.hiInclusive = hiInclusive;
/* 2291 */       if (lo != null && hi != null && m.comparator.compare(lo, hi) > 0)
/* 2292 */         throw new IllegalArgumentException(); 
/*      */     }
/*      */     
/*      */     public boolean containsKey(Object key) {
/* 2303 */       if (key == null)
/* 2303 */         throw new NullPointerException(); 
/* 2304 */       K k = (K)key;
/* 2305 */       return (inBounds(k) && this.m.containsKey(k));
/*      */     }
/*      */     
/*      */     public V get(Object key) {
/* 2310 */       if (key == null)
/* 2310 */         throw new NullPointerException(); 
/* 2311 */       K k = (K)key;
/* 2312 */       return !inBounds(k) ? null : this.m.get(k);
/*      */     }
/*      */     
/*      */     public V put(K key, V value) {
/* 2317 */       checkKeyBounds(key);
/* 2318 */       return this.m.put(key, value);
/*      */     }
/*      */     
/*      */     public V remove(Object key) {
/* 2323 */       K k = (K)key;
/* 2324 */       return !inBounds(k) ? null : this.m.remove(k);
/*      */     }
/*      */     
/*      */     public int size() {
/* 2329 */       Iterator<K> i = keyIterator();
/* 2330 */       int counter = 0;
/* 2331 */       while (i.hasNext()) {
/* 2332 */         counter++;
/* 2333 */         i.next();
/*      */       } 
/* 2335 */       return counter;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 2340 */       return !keyIterator().hasNext();
/*      */     }
/*      */     
/*      */     public boolean containsValue(Object value) {
/* 2345 */       if (value == null)
/* 2345 */         throw new NullPointerException(); 
/* 2346 */       Iterator<V> i = valueIterator();
/* 2347 */       while (i.hasNext()) {
/* 2348 */         if (value.equals(i.next()))
/* 2349 */           return true; 
/*      */       } 
/* 2351 */       return false;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 2356 */       Iterator<K> i = keyIterator();
/* 2357 */       while (i.hasNext()) {
/* 2358 */         i.next();
/* 2359 */         i.remove();
/*      */       } 
/*      */     }
/*      */     
/*      */     public V putIfAbsent(K key, V value) {
/* 2368 */       checkKeyBounds(key);
/* 2369 */       return this.m.putIfAbsent(key, value);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key, Object value) {
/* 2374 */       K k = (K)key;
/* 2375 */       return (inBounds(k) && this.m.remove(k, value));
/*      */     }
/*      */     
/*      */     public boolean replace(K key, V oldValue, V newValue) {
/* 2380 */       checkKeyBounds(key);
/* 2381 */       return this.m.replace(key, oldValue, newValue);
/*      */     }
/*      */     
/*      */     public V replace(K key, V value) {
/* 2386 */       checkKeyBounds(key);
/* 2387 */       return this.m.replace(key, value);
/*      */     }
/*      */     
/*      */     public Comparator<? super K> comparator() {
/* 2394 */       return this.m.comparator();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> higherEntry(K key) {
/* 2401 */       if (key == null)
/* 2401 */         throw new NullPointerException(); 
/* 2402 */       if (tooLow(key))
/* 2402 */         return null; 
/* 2404 */       if (tooHigh(key))
/* 2405 */         return firstEntry(); 
/* 2407 */       Map.Entry<K, V> r = this.m.lowerEntry(key);
/* 2408 */       return (r != null && !tooLow(r.getKey())) ? r : null;
/*      */     }
/*      */     
/*      */     public K lowerKey(K key) {
/* 2413 */       Map.Entry<K, V> n = lowerEntry(key);
/* 2414 */       return (n == null) ? null : n.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> ceilingEntry(K key) {
/* 2419 */       if (key == null)
/* 2419 */         throw new NullPointerException(); 
/* 2420 */       if (tooLow(key))
/* 2420 */         return null; 
/* 2422 */       if (tooHigh(key))
/* 2423 */         return firstEntry(); 
/* 2426 */       Map.Entry<K, V> ret = this.m.floorEntry(key);
/* 2427 */       if (ret != null && tooLow(ret.getKey()))
/* 2427 */         return null; 
/* 2428 */       return ret;
/*      */     }
/*      */     
/*      */     public K floorKey(K key) {
/* 2434 */       Map.Entry<K, V> n = floorEntry(key);
/* 2435 */       return (n == null) ? null : n.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> floorEntry(K key) {
/* 2440 */       if (key == null)
/* 2440 */         throw new NullPointerException(); 
/* 2441 */       if (tooHigh(key))
/* 2441 */         return null; 
/* 2443 */       if (tooLow(key))
/* 2444 */         return lastEntry(); 
/* 2447 */       Map.Entry<K, V> ret = this.m.ceilingEntry(key);
/* 2448 */       if (ret != null && tooHigh(ret.getKey()))
/* 2448 */         return null; 
/* 2449 */       return ret;
/*      */     }
/*      */     
/*      */     public K ceilingKey(K key) {
/* 2454 */       Map.Entry<K, V> k = ceilingEntry(key);
/* 2455 */       return (k != null) ? k.getKey() : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> lowerEntry(K key) {
/* 2460 */       Map.Entry<K, V> r = this.m.higherEntry(key);
/* 2461 */       return (r != null && inBounds(r.getKey())) ? r : null;
/*      */     }
/*      */     
/*      */     public K higherKey(K key) {
/* 2466 */       Map.Entry<K, V> k = higherEntry(key);
/* 2467 */       return (k != null) ? k.getKey() : null;
/*      */     }
/*      */     
/*      */     public K firstKey() {
/* 2473 */       Map.Entry<K, V> e = firstEntry();
/* 2474 */       if (e == null)
/* 2474 */         throw new NoSuchElementException(); 
/* 2475 */       return e.getKey();
/*      */     }
/*      */     
/*      */     public K lastKey() {
/* 2480 */       Map.Entry<K, V> e = lastEntry();
/* 2481 */       if (e == null)
/* 2481 */         throw new NoSuchElementException(); 
/* 2482 */       return e.getKey();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> lastEntry() {
/* 2488 */       Map.Entry<K, V> k = (this.lo == null) ? this.m.firstEntry() : this.m.findLarger(this.lo, this.loInclusive);
/* 2492 */       return (k != null && inBounds(k.getKey())) ? k : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> firstEntry() {
/* 2498 */       Map.Entry<K, V> k = (this.hi == null) ? this.m.lastEntry() : this.m.findSmaller(this.hi, this.hiInclusive);
/* 2503 */       return (k != null && inBounds(k.getKey())) ? k : null;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> pollFirstEntry() {
/*      */       Map.Entry<K, V> e;
/*      */       do {
/* 2509 */         e = firstEntry();
/* 2510 */       } while (e != null && !remove(e.getKey(), e.getValue()));
/* 2511 */       return e;
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> pollLastEntry() {
/*      */       Map.Entry<K, V> e;
/*      */       do {
/* 2519 */         e = lastEntry();
/* 2520 */       } while (e != null && !remove(e.getKey(), e.getValue()));
/* 2521 */       return e;
/*      */     }
/*      */     
/*      */     private DescendingMap<K, V> newSubMap(K toKey, boolean toInclusive, K fromKey, boolean fromInclusive) {
/* 2545 */       if (this.lo != null)
/* 2546 */         if (fromKey == null) {
/* 2547 */           fromKey = this.lo;
/* 2548 */           fromInclusive = this.loInclusive;
/*      */         } else {
/* 2551 */           int c = this.m.comparator.compare(fromKey, this.lo);
/* 2552 */           if (c < 0 || (c == 0 && !this.loInclusive && fromInclusive))
/* 2553 */             throw new IllegalArgumentException("key out of range"); 
/*      */         }  
/* 2556 */       if (this.hi != null)
/* 2557 */         if (toKey == null) {
/* 2558 */           toKey = this.hi;
/* 2559 */           toInclusive = this.hiInclusive;
/*      */         } else {
/* 2562 */           int c = this.m.comparator.compare(toKey, this.hi);
/* 2563 */           if (c > 0 || (c == 0 && !this.hiInclusive && toInclusive))
/* 2564 */             throw new IllegalArgumentException("key out of range"); 
/*      */         }  
/* 2567 */       return new DescendingMap(this.m, fromKey, fromInclusive, toKey, toInclusive);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
/* 2576 */       if (fromKey == null || toKey == null)
/* 2577 */         throw new NullPointerException(); 
/* 2578 */       return newSubMap(fromKey, fromInclusive, toKey, toInclusive);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> headMap(K toKey, boolean inclusive) {
/* 2584 */       if (toKey == null)
/* 2585 */         throw new NullPointerException(); 
/* 2586 */       return newSubMap(null, false, toKey, inclusive);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> tailMap(K fromKey, boolean inclusive) {
/* 2592 */       if (fromKey == null)
/* 2593 */         throw new NullPointerException(); 
/* 2594 */       return newSubMap(fromKey, inclusive, null, false);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> subMap(K fromKey, K toKey) {
/* 2599 */       return subMap(fromKey, true, toKey, false);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> headMap(K toKey) {
/* 2604 */       return headMap(toKey, false);
/*      */     }
/*      */     
/*      */     public DescendingMap<K, V> tailMap(K fromKey) {
/* 2609 */       return tailMap(fromKey, true);
/*      */     }
/*      */     
/*      */     public ConcurrentNavigableMap<K, V> descendingMap() {
/* 2614 */       if (this.lo == null && this.hi == null)
/* 2614 */         return this.m; 
/* 2615 */       return this.m.subMap(this.lo, this.loInclusive, this.hi, this.hiInclusive);
/*      */     }
/*      */     
/*      */     public NavigableSet<K> navigableKeySet() {
/* 2620 */       return new BTreeMap.KeySet<K>((ConcurrentNavigableMap)this, this.m.hasValues);
/*      */     }
/*      */     
/*      */     private boolean tooLow(K key) {
/* 2629 */       if (this.lo != null) {
/* 2630 */         int c = this.m.comparator.compare(key, this.lo);
/* 2631 */         if (c < 0 || (c == 0 && !this.loInclusive))
/* 2632 */           return true; 
/*      */       } 
/* 2634 */       return false;
/*      */     }
/*      */     
/*      */     private boolean tooHigh(K key) {
/* 2638 */       if (this.hi != null) {
/* 2639 */         int c = this.m.comparator.compare(key, this.hi);
/* 2640 */         if (c > 0 || (c == 0 && !this.hiInclusive))
/* 2641 */           return true; 
/*      */       } 
/* 2643 */       return false;
/*      */     }
/*      */     
/*      */     private boolean inBounds(K key) {
/* 2647 */       return (!tooLow(key) && !tooHigh(key));
/*      */     }
/*      */     
/*      */     private void checkKeyBounds(K key) throws IllegalArgumentException {
/* 2651 */       if (key == null)
/* 2652 */         throw new NullPointerException(); 
/* 2653 */       if (!inBounds(key))
/* 2654 */         throw new IllegalArgumentException("key out of range"); 
/*      */     }
/*      */     
/*      */     public NavigableSet<K> keySet() {
/* 2663 */       return new BTreeMap.KeySet<K>((ConcurrentNavigableMap)this, this.m.hasValues);
/*      */     }
/*      */     
/*      */     public NavigableSet<K> descendingKeySet() {
/* 2668 */       return new BTreeMap.KeySet<K>((ConcurrentNavigableMap)descendingMap(), this.m.hasValues);
/*      */     }
/*      */     
/*      */     public Set<Map.Entry<K, V>> entrySet() {
/* 2675 */       return new BTreeMap.EntrySet<K, V>(this);
/*      */     }
/*      */     
/*      */     abstract class Iter<E> implements Iterator<E> {
/* 2684 */       Map.Entry<K, V> current = BTreeMap.DescendingMap.this.firstEntry();
/*      */       
/* 2685 */       Map.Entry<K, V> last = null;
/*      */       
/*      */       public boolean hasNext() {
/* 2690 */         return (this.current != null);
/*      */       }
/*      */       
/*      */       public void advance() {
/* 2695 */         if (this.current == null)
/* 2695 */           throw new NoSuchElementException(); 
/* 2696 */         this.last = this.current;
/* 2697 */         this.current = BTreeMap.DescendingMap.this.higherEntry(this.current.getKey());
/*      */       }
/*      */       
/*      */       public void remove() {
/* 2702 */         if (this.last == null)
/* 2702 */           throw new IllegalStateException(); 
/* 2703 */         BTreeMap.DescendingMap.this.remove(this.last.getKey());
/* 2704 */         this.last = null;
/*      */       }
/*      */     }
/*      */     
/*      */     Iterator<K> keyIterator() {
/* 2709 */       return new Iter<K>() {
/*      */           public K next() {
/* 2712 */             advance();
/* 2713 */             return (K)this.last.getKey();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     Iterator<V> valueIterator() {
/* 2719 */       return new Iter<V>() {
/*      */           public V next() {
/* 2723 */             advance();
/* 2724 */             return (V)this.last.getValue();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     Iterator<Map.Entry<K, V>> entryIterator() {
/* 2730 */       return new Iter<Map.Entry<K, V>>() {
/*      */           public Map.Entry<K, V> next() {
/* 2733 */             advance();
/* 2734 */             return this.last;
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */   
/*      */   public NavigableMap<K, V> snapshot() {
/* 2753 */     Engine snapshot = TxEngine.createSnapshotFor(this.engine);
/* 2755 */     return new BTreeMap(snapshot, this.rootRecidRef, this.maxNodeSize, this.valsOutsideNodes, (this.counter == null) ? 0L : this.counter.recid, this.keySerializer, this.valueSerializer, this.comparator, this.numberOfNodeMetas, false);
/*      */   }
/*      */   
/*      */   public BTreeMap(Engine engine, long rootRecidRef, int maxNodeSize, boolean valsOutsideNodes, long counterRecid, BTreeKeySerializer<K> keySerializer, Serializer<V> valueSerializer, Comparator<K> comparator, int numberOfNodeMetas, boolean disableLocks) {
/* 2762 */     this.modListenersLock = new Object();
/* 2763 */     this.modListeners = (Bind.MapListener<K, V>[])new Bind.MapListener[0];
/*      */     if (maxNodeSize % 2 != 0)
/*      */       throw new IllegalArgumentException("maxNodeSize must be dividable by 2"); 
/*      */     if (maxNodeSize < 6)
/*      */       throw new IllegalArgumentException("maxNodeSize too low"); 
/*      */     if (maxNodeSize > 126)
/*      */       throw new IllegalArgumentException("maxNodeSize too high"); 
/*      */     if (rootRecidRef <= 0L || counterRecid < 0L || numberOfNodeMetas < 0)
/*      */       throw new IllegalArgumentException(); 
/*      */     if (keySerializer == null)
/*      */       throw new NullPointerException(); 
/*      */     if (comparator == null)
/*      */       throw new NullPointerException(); 
/*      */     SerializerBase.assertSerializable(keySerializer);
/*      */     SerializerBase.assertSerializable(valueSerializer);
/*      */     SerializerBase.assertSerializable(comparator);
/*      */     this.rootRecidRef = rootRecidRef;
/*      */     this.hasValues = (valueSerializer != null);
/*      */     this.valsOutsideNodes = valsOutsideNodes;
/*      */     this.engine = engine;
/*      */     this.maxNodeSize = maxNodeSize;
/*      */     this.comparator = comparator;
/*      */     this.numberOfNodeMetas = numberOfNodeMetas;
/*      */     Comparator<K> requiredComparator = keySerializer.getComparator();
/*      */     if (requiredComparator != null && !requiredComparator.equals(comparator))
/*      */       throw new IllegalArgumentException("KeySerializers requires its own comparator"); 
/*      */     this.keySerializer = keySerializer;
/*      */     this.valueSerializer = valueSerializer;
/*      */     this.nodeSerializer = new NodeSerializer<Object, Object>(valsOutsideNodes, keySerializer, valueSerializer, comparator, numberOfNodeMetas);
/*      */     this.keySet = new KeySet<K>((ConcurrentNavigableMap)this, this.hasValues);
/*      */     if (counterRecid != 0L) {
/*      */       this.counter = new Atomic.Long(engine, counterRecid);
/*      */       Bind.size(this, this.counter);
/*      */     } else {
/*      */       this.counter = null;
/*      */     } 
/*      */     ArrayList<Long> leftEdges2 = new ArrayList();
/*      */     long r = ((Long)engine.<Long>get(rootRecidRef, Serializer.LONG)).longValue();
/*      */     while (true) {
/*      */       BNode n = engine.<BNode>get(r, this.nodeSerializer);
/*      */       leftEdges2.add(Long.valueOf(r));
/*      */       if (n.isLeaf())
/*      */         break; 
/*      */       r = n.child()[0];
/*      */     } 
/*      */     Collections.reverse(leftEdges2);
/*      */     this.leftEdges = new CopyOnWriteArrayList<Long>(leftEdges2);
/*      */   }
/*      */   
/*      */   public void modificationListenerAdd(Bind.MapListener<K, V> listener) {
/* 2767 */     synchronized (this.modListenersLock) {
/* 2768 */       Bind.MapListener[] arrayOfMapListener = Arrays.<Bind.MapListener>copyOf((Bind.MapListener[])this.modListeners, this.modListeners.length + 1);
/* 2770 */       arrayOfMapListener[arrayOfMapListener.length - 1] = listener;
/* 2771 */       this.modListeners = (Bind.MapListener<K, V>[])arrayOfMapListener;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void modificationListenerRemove(Bind.MapListener<K, V> listener) {
/* 2778 */     synchronized (this.modListenersLock) {
/* 2779 */       for (int i = 0; i < this.modListeners.length; i++) {
/* 2780 */         if (this.modListeners[i] == listener)
/* 2780 */           this.modListeners[i] = null; 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void notify(K key, V oldValue, V newValue) {
/* 2786 */     assert !(oldValue instanceof ValRef);
/* 2787 */     assert !(newValue instanceof ValRef);
/* 2789 */     Bind.MapListener<K, V>[] modListeners2 = this.modListeners;
/* 2790 */     for (Bind.MapListener<K, V> listener : modListeners2) {
/* 2791 */       if (listener != null)
/* 2792 */         listener.update(key, oldValue, newValue); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() {
/* 2802 */     this.engine.close();
/*      */   }
/*      */   
/*      */   public Engine getEngine() {
/* 2806 */     return this.engine;
/*      */   }
/*      */   
/*      */   public void printTreeStructure() {
/* 2811 */     long rootRecid = ((Long)this.engine.<Long>get(this.rootRecidRef, Serializer.LONG)).longValue();
/* 2812 */     printRecur(this, rootRecid, "");
/*      */   }
/*      */   
/*      */   private static void printRecur(BTreeMap m, long recid, String s) {
/* 2816 */     BNode n = m.engine.<BNode>get(recid, m.nodeSerializer);
/* 2817 */     System.out.println(s + recid + "-" + n);
/* 2818 */     if (!n.isLeaf())
/* 2819 */       for (int i = 0; i < (n.child()).length - 1; i++) {
/* 2820 */         long recid2 = n.child()[i];
/* 2821 */         if (recid2 != 0L)
/* 2822 */           printRecur(m, recid2, s + "  "); 
/*      */       }  
/*      */   }
/*      */   
/*      */   protected static long[] arrayLongPut(long[] array, int pos, long value) {
/* 2829 */     long[] ret = Arrays.copyOf(array, array.length + 1);
/* 2830 */     if (pos < array.length)
/* 2831 */       System.arraycopy(array, pos, ret, pos + 1, array.length - pos); 
/* 2833 */     ret[pos] = value;
/* 2834 */     return ret;
/*      */   }
/*      */   
/*      */   protected static Object[] arrayPut(Object[] array, int pos, Object value) {
/* 2839 */     Object[] ret = Arrays.copyOf(array, array.length + 1);
/* 2840 */     if (pos < array.length)
/* 2841 */       System.arraycopy(array, pos, ret, pos + 1, array.length - pos); 
/* 2843 */     ret[pos] = value;
/* 2844 */     return ret;
/*      */   }
/*      */   
/*      */   protected static void assertNoLocks(LongConcurrentHashMap<Thread> locks) {
/* 2848 */     LongMap.LongMapIterator<Thread> i = locks.longMapIterator();
/* 2849 */     Thread t = null;
/* 2850 */     while (i.moveToNext()) {
/* 2851 */       if (t == null)
/* 2852 */         t = Thread.currentThread(); 
/* 2853 */       if (i.value() == t)
/* 2854 */         throw new AssertionError("Node " + i.key() + " is still locked"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static void unlock(LongConcurrentHashMap<Thread> locks, long recid) {
/* 2861 */     Thread t = locks.remove(recid);
/* 2862 */     assert t == Thread.currentThread() : "unlocked wrong thread";
/*      */   }
/*      */   
/*      */   protected static void unlockAll(LongConcurrentHashMap<Thread> locks) {
/* 2866 */     Thread t = Thread.currentThread();
/* 2867 */     LongMap.LongMapIterator<Thread> iter = locks.longMapIterator();
/* 2868 */     while (iter.moveToNext()) {
/* 2869 */       if (iter.value() == t)
/* 2870 */         iter.remove(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static void lock(LongConcurrentHashMap<Thread> locks, long recid) {
/* 2877 */     Thread currentThread = Thread.currentThread();
/* 2879 */     assert locks.get(recid) != currentThread : "node already locked by current thread: " + recid;
/* 2881 */     while (locks.putIfAbsent(recid, currentThread) != null)
/* 2882 */       LockSupport.parkNanos(10L); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\BTreeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */