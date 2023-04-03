/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NavigableSet;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class Pump {
/*     */   static void copy(DB db1, DB db2) {
/*  31 */     copy(Store.forDB(db1), Store.forDB(db2));
/*  32 */     db2.engine.clearCache();
/*  33 */     db2.reinit();
/*     */   }
/*     */   
/*     */   static void copy(Store s1, Store s2) {
/*  39 */     long maxRecid = s1.getMaxRecid();
/*     */     long recid;
/*  40 */     for (recid = 1L; recid <= maxRecid; recid++) {
/*  41 */       ByteBuffer bb = s1.getRaw(recid);
/*  43 */       if (bb != null)
/*  44 */         s2.updateRaw(recid, bb); 
/*     */     } 
/*  48 */     for (Iterator<Long> iter = s1.getFreeRecids(); iter.hasNext();)
/*  49 */       s2.delete(((Long)iter.next()).longValue(), null); 
/*     */   }
/*     */   
/*     */   public static <E> Iterator<E> sort(Iterator<E> source, boolean mergeDuplicates, int batchSize, Comparator<? super Object> comparator, final Serializer<Object> serializer) {
/*  67 */     if (batchSize <= 0)
/*  67 */       throw new IllegalArgumentException(); 
/*  68 */     if (comparator == null)
/*  69 */       comparator = BTreeMap.COMPARABLE_COMPARATOR; 
/*  70 */     if (source == null)
/*  71 */       source = Fun.EMPTY_ITERATOR; 
/*  73 */     int counter = 0;
/*  74 */     Object[] presort = new Object[batchSize];
/*  75 */     final List<File> presortFiles = new ArrayList<File>();
/*  76 */     List<Integer> presortCount2 = new ArrayList<Integer>();
/*     */     try {
/*  79 */       while (source.hasNext()) {
/*  80 */         presort[counter] = source.next();
/*  81 */         counter++;
/*  83 */         if (counter >= batchSize) {
/*  85 */           Arrays.sort(presort, comparator);
/*  88 */           File f = File.createTempFile("mapdb", "sort");
/*  89 */           f.deleteOnExit();
/*  90 */           presortFiles.add(f);
/*  91 */           DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
/*  92 */           for (Object e : presort)
/*  93 */             serializer.serialize(out, e); 
/*  95 */           out.close();
/*  96 */           presortCount2.add(Integer.valueOf(counter));
/*  97 */           Arrays.fill(presort, Integer.valueOf(0));
/*  98 */           counter = 0;
/*     */         } 
/*     */       } 
/* 102 */       if (presortFiles.isEmpty()) {
/* 104 */         Arrays.sort(presort, 0, counter, comparator);
/* 105 */         return (Iterator)arrayIterator(presort, 0, counter);
/*     */       } 
/* 108 */       final int[] presortCount = new int[presortFiles.size()];
/* 109 */       for (int i = 0; i < presortCount.length; ) {
/* 109 */         presortCount[i] = ((Integer)presortCount2.get(i)).intValue();
/* 109 */         i++;
/*     */       } 
/* 111 */       Iterator[] iterators = new Iterator[presortFiles.size() + 1];
/* 112 */       final DataInputStream[] ins = new DataInputStream[presortFiles.size()];
/* 113 */       for (int j = 0; j < presortFiles.size(); j++) {
/* 114 */         ins[j] = new DataInputStream(new BufferedInputStream(new FileInputStream(presortFiles.get(j))));
/* 115 */         final int pos = j;
/* 116 */         iterators[j] = new Iterator() {
/*     */             public boolean hasNext() {
/* 119 */               return (presortCount[pos] > 0);
/*     */             }
/*     */             
/*     */             public Object next() {
/*     */               try {
/* 124 */                 Object ret = serializer.deserialize(ins[pos], -1);
/* 125 */                 presortCount[pos] = presortCount[pos] - 1;
/* 125 */                 if (presortCount[pos] - 1 == 0) {
/* 126 */                   ins[pos].close();
/* 127 */                   ((File)presortFiles.get(pos)).delete();
/*     */                 } 
/* 129 */                 return ret;
/* 130 */               } catch (IOException e) {
/* 131 */                 throw new IOError(e);
/*     */               } 
/*     */             }
/*     */             
/*     */             public void remove() {}
/*     */           };
/*     */       } 
/* 143 */       Arrays.sort(presort, 0, counter, comparator);
/* 144 */       iterators[iterators.length - 1] = arrayIterator(presort, 0, counter);
/* 147 */       return (Iterator)sort(comparator, mergeDuplicates, iterators);
/* 149 */     } catch (IOException e) {
/* 150 */       throw new IOError(e);
/*     */     } finally {
/* 152 */       for (File f : presortFiles)
/* 152 */         f.delete(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <E> Iterator<E> sort(Comparator comparator, final boolean mergeDuplicates, Iterator... iterators) {
/* 168 */     final Comparator comparator2 = (comparator == null) ? BTreeMap.COMPARABLE_COMPARATOR : comparator;
/* 169 */     return new Iterator<E>() {
/* 171 */         final NavigableSet<Fun.Tuple2<Object, Integer>> items = new TreeSet<Fun.Tuple2<Object, Integer>>((Comparator)new Fun.Tuple2Comparator<Object, Object>(comparator2, null));
/*     */         
/* 174 */         Object next = this;
/*     */         
/*     */         public boolean hasNext() {
/* 187 */           return (this.next != null);
/*     */         }
/*     */         
/*     */         public E next() {
/* 191 */           if (this.next == null)
/* 192 */             throw new NoSuchElementException(); 
/* 194 */           Object oldNext = this.next;
/* 196 */           Fun.Tuple2<Object, Integer> lo = this.items.pollFirst();
/* 197 */           if (lo == null) {
/* 198 */             this.next = null;
/* 199 */             return (E)oldNext;
/*     */           } 
/* 202 */           this.next = lo.a;
/* 204 */           if (oldNext != this && comparator2.compare(oldNext, this.next) > 0)
/* 205 */             throw new IllegalArgumentException("One of the iterators is not sorted"); 
/* 208 */           Iterator iter = iterators[((Integer)lo.b).intValue()];
/* 209 */           if (iter.hasNext())
/* 210 */             this.items.add(Fun.t2(iter.next(), (Integer)lo.b)); 
/* 213 */           if (mergeDuplicates)
/*     */             while (true) {
/* 215 */               Set<Fun.Tuple2<Object, Integer>> subset = this.items.subSet(Fun.t2(this.next, null), (Fun.Tuple2)Fun.t2(this.next, Fun.HI));
/* 218 */               if (subset.isEmpty())
/*     */                 break; 
/* 220 */               List<? extends Fun.Tuple2<Object, Integer>> toadd = new ArrayList();
/* 221 */               for (Fun.Tuple2<Object, Integer> t : subset) {
/* 222 */                 iter = iterators[((Integer)t.b).intValue()];
/* 223 */                 if (iter.hasNext())
/* 224 */                   toadd.add(Fun.t2(iter.next(), t.b)); 
/*     */               } 
/* 226 */               subset.clear();
/* 227 */               this.items.addAll(toadd);
/*     */             }  
/* 232 */           return (E)oldNext;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 236 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <E> Iterator<E> merge(Iterator... iters) {
/* 252 */     if (iters.length == 0)
/* 253 */       return Fun.EMPTY_ITERATOR; 
/* 255 */     return new Iterator<E>() {
/*     */         int i;
/*     */         
/*     */         Object next;
/*     */         
/*     */         public boolean hasNext() {
/* 264 */           return (this.next != null);
/*     */         }
/*     */         
/*     */         public E next() {
/* 268 */           if (this.next == null)
/* 269 */             throw new NoSuchElementException(); 
/* 272 */           while (!iters[this.i].hasNext()) {
/* 273 */             this.i++;
/* 274 */             if (this.i == iters.length) {
/* 276 */               Object object = this.next;
/* 277 */               this.next = null;
/* 278 */               return (E)object;
/*     */             } 
/*     */           } 
/* 283 */           Object ret = this.next;
/* 284 */           this.next = iters[this.i].next();
/* 285 */           return (E)ret;
/*     */         }
/*     */         
/*     */         public void remove() {
/* 289 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <E, K, V> long buildTreeMap(Iterator<E> source, Engine engine, Fun.Function1<K, E> keyExtractor, Fun.Function1<V, E> valueExtractor, boolean ignoreDuplicates, int nodeSize, boolean valuesStoredOutsideNodes, long counterRecid, BTreeKeySerializer<K> keySerializer, Serializer<V> valueSerializer, Comparator<K> comparator) {
/* 333 */     if (comparator == null)
/* 334 */       comparator = BTreeMap.COMPARABLE_COMPARATOR; 
/* 336 */     double NODE_LOAD = 0.75D;
/* 338 */     Serializer<BTreeMap.BNode> nodeSerializer = new BTreeMap.NodeSerializer<Object, Object>(valuesStoredOutsideNodes, keySerializer, valueSerializer, comparator, 0);
/* 341 */     int nload = (int)(nodeSize * 0.75D);
/* 342 */     ArrayList<ArrayList<Object>> dirKeys = arrayList(arrayList(null));
/* 343 */     ArrayList<ArrayList<Long>> dirRecids = arrayList(arrayList(Long.valueOf(0L)));
/* 345 */     long counter = 0L;
/* 347 */     long nextNode = 0L;
/* 350 */     List<K> keys = arrayList(null);
/* 351 */     ArrayList<Object> values = new ArrayList();
/* 353 */     K oldKey = null;
/* 354 */     while (source.hasNext()) {
/*     */       int j;
/* 356 */       label78: for (j = 0; j < nload && source.hasNext(); j++) {
/* 357 */         counter++;
/* 358 */         E next = source.next();
/* 359 */         if (next == null)
/* 359 */           throw new NullPointerException("source returned null element"); 
/* 360 */         K key = (keyExtractor == null) ? (K)next : keyExtractor.run(next);
/* 361 */         int compared = (oldKey == null) ? -1 : comparator.compare(key, oldKey);
/* 362 */         while (ignoreDuplicates && compared == 0) {
/* 364 */           if (!source.hasNext())
/*     */             break label78; 
/* 365 */           next = source.next();
/* 366 */           if (next == null)
/* 366 */             throw new NullPointerException("source returned null element"); 
/* 367 */           key = (keyExtractor == null) ? (K)next : keyExtractor.run(next);
/* 368 */           compared = comparator.compare(key, oldKey);
/*     */         } 
/* 371 */         if (oldKey != null && compared >= 0)
/* 372 */           throw new IllegalArgumentException("Keys in 'source' iterator are not reverse sorted"); 
/* 373 */         oldKey = key;
/* 374 */         keys.add(key);
/* 376 */         Object val = (valueExtractor != null) ? valueExtractor.run(next) : BTreeMap.EMPTY;
/* 377 */         if (val == null)
/* 377 */           throw new NullPointerException("extractValue returned null value"); 
/* 378 */         if (valuesStoredOutsideNodes) {
/* 379 */           long recid = engine.put(val, valueSerializer);
/* 380 */           val = new BTreeMap.ValRef(recid);
/*     */         } 
/* 382 */         values.add(val);
/*     */       } 
/* 386 */       if (!source.hasNext()) {
/* 387 */         keys.add(null);
/* 388 */         values.add(null);
/*     */       } 
/* 391 */       Collections.reverse(keys);
/* 393 */       Object nextVal = values.remove(values.size() - 1);
/* 394 */       Collections.reverse(values);
/* 399 */       BTreeMap.LeafNode node = new BTreeMap.LeafNode(keys.toArray(), values.toArray(), nextNode);
/* 400 */       nextNode = engine.put(node, nodeSerializer);
/* 401 */       K nextKey = keys.get(0);
/* 402 */       keys.clear();
/* 404 */       keys.add(nextKey);
/* 405 */       keys.add(nextKey);
/* 407 */       values.clear();
/* 408 */       values.add(nextVal);
/* 410 */       ((ArrayList<Object>)dirKeys.get(0)).add(node.keys()[0]);
/* 411 */       ((ArrayList<Long>)dirRecids.get(0)).add(Long.valueOf(nextNode));
/* 414 */       for (int k = 0; k < dirKeys.size() && (
/* 415 */         (ArrayList)dirKeys.get(k)).size() >= nload; k++) {
/* 417 */         Collections.reverse(dirKeys.get(k));
/* 418 */         Collections.reverse(dirRecids.get(k));
/* 420 */         BTreeMap.DirNode dirNode = new BTreeMap.DirNode(((ArrayList)dirKeys.get(k)).toArray(), dirRecids.get(k));
/* 421 */         long dirRecid = engine.put(dirNode, nodeSerializer);
/* 422 */         Object dirStart = ((ArrayList)dirKeys.get(k)).get(0);
/* 423 */         ((ArrayList)dirKeys.get(k)).clear();
/* 424 */         ((ArrayList<Object>)dirKeys.get(k)).add(dirStart);
/* 425 */         ((ArrayList)dirRecids.get(k)).clear();
/* 426 */         ((ArrayList<Long>)dirRecids.get(k)).add(Long.valueOf(dirRecid));
/* 429 */         if (dirKeys.size() == k + 1) {
/* 430 */           dirKeys.add(arrayList(dirStart));
/* 431 */           dirRecids.add(arrayList(Long.valueOf(dirRecid)));
/*     */         } else {
/* 433 */           ((ArrayList<Object>)dirKeys.get(k + 1)).add(dirStart);
/* 434 */           ((ArrayList<Long>)dirRecids.get(k + 1)).add(Long.valueOf(dirRecid));
/*     */         } 
/*     */       } 
/*     */     } 
/* 440 */     for (int i = 0; i < dirKeys.size() - 1; i++) {
/* 442 */       ArrayList<Object> keys2 = dirKeys.get(i);
/* 443 */       Collections.reverse(keys2);
/* 444 */       Collections.reverse(dirRecids.get(i));
/* 446 */       if (keys2.size() > 2 && keys2.get(0) == null && keys2.get(1) == null) {
/* 447 */         keys2.remove(0);
/* 448 */         ((ArrayList)dirRecids.get(i)).remove(0);
/*     */       } 
/* 452 */       BTreeMap.DirNode dirNode = new BTreeMap.DirNode(keys2.toArray(), dirRecids.get(i));
/* 453 */       long dirRecid = engine.put(dirNode, nodeSerializer);
/* 454 */       Object dirStart = keys2.get(0);
/* 455 */       ((ArrayList<Object>)dirKeys.get(i + 1)).add(dirStart);
/* 456 */       ((ArrayList<Long>)dirRecids.get(i + 1)).add(Long.valueOf(dirRecid));
/*     */     } 
/* 461 */     int len = dirKeys.size() - 1;
/* 462 */     Collections.reverse(dirKeys.get(len));
/* 463 */     Collections.reverse(dirRecids.get(len));
/* 466 */     if (counterRecid != 0L)
/* 467 */       engine.update(counterRecid, Long.valueOf(counter), Serializer.LONG); 
/* 469 */     BTreeMap.DirNode dir = new BTreeMap.DirNode(((ArrayList)dirKeys.get(len)).toArray(), dirRecids.get(len));
/* 470 */     long rootRecid = engine.put(dir, nodeSerializer);
/* 471 */     return engine.put(Long.valueOf(rootRecid), Serializer.LONG);
/*     */   }
/*     */   
/*     */   private static <E> ArrayList<E> arrayList(E item) {
/* 476 */     ArrayList<E> ret = new ArrayList<E>();
/* 477 */     ret.add(item);
/* 478 */     return ret;
/*     */   }
/*     */   
/*     */   private static <E> Iterator<E> arrayIterator(final Object[] array, final int fromIndex, final int toIndex) {
/* 482 */     return new Iterator<E>() {
/* 484 */         int index = fromIndex;
/*     */         
/*     */         public boolean hasNext() {
/* 488 */           return (this.index < toIndex);
/*     */         }
/*     */         
/*     */         public E next() {
/* 493 */           if (this.index >= toIndex)
/* 493 */             throw new NoSuchElementException(); 
/* 494 */           return (E)array[this.index++];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 499 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Pump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */