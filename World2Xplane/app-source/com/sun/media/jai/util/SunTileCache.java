/*      */ package com.sun.media.jai.util;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Observable;
/*      */ import java.util.SortedSet;
/*      */ import java.util.TreeSet;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.EnumeratedParameter;
/*      */ import javax.media.jai.TileCache;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public final class SunTileCache extends Observable implements TileCache, CacheDiagnostics {
/*      */   private static final long DEFAULT_MEMORY_CAPACITY = 16777216L;
/*      */   
/*      */   private static final int DEFAULT_HASHTABLE_CAPACITY = 1009;
/*      */   
/*      */   private static final float LOAD_FACTOR = 0.5F;
/*      */   
/*      */   private Hashtable cache;
/*      */   
/*      */   private SortedSet cacheSortedSet;
/*      */   
/*      */   private long memoryCapacity;
/*      */   
/*   86 */   private long memoryUsage = 0L;
/*      */   
/*   89 */   private float memoryThreshold = 0.75F;
/*      */   
/*   92 */   private long timeStamp = 0L;
/*      */   
/*   97 */   private Comparator comparator = null;
/*      */   
/*  100 */   private SunCachedTile first = null;
/*      */   
/*  103 */   private SunCachedTile last = null;
/*      */   
/*  106 */   private long tileCount = 0L;
/*      */   
/*  109 */   private long hitCount = 0L;
/*      */   
/*  112 */   private long missCount = 0L;
/*      */   
/*      */   private boolean diagnostics = false;
/*      */   
/*      */   private static final int ADD = 0;
/*      */   
/*      */   private static final int REMOVE = 1;
/*      */   
/*      */   private static final int REMOVE_FROM_FLUSH = 2;
/*      */   
/*      */   private static final int REMOVE_FROM_MEMCON = 3;
/*      */   
/*      */   private static final int UPDATE_FROM_ADD = 4;
/*      */   
/*      */   private static final int UPDATE_FROM_GETTILE = 5;
/*      */   
/*      */   private static final int ABOUT_TO_REMOVE = 6;
/*      */   
/*      */   public static EnumeratedParameter[] getCachedTileActions() {
/*  137 */     return new EnumeratedParameter[] { new EnumeratedParameter("add", 0), new EnumeratedParameter("remove", 1), new EnumeratedParameter("remove_by_flush", 2), new EnumeratedParameter("remove_by_memorycontrol", 3), new EnumeratedParameter("timestamp_update_by_add", 4), new EnumeratedParameter("timestamp_update_by_gettile", 5), new EnumeratedParameter("preremove", 6) };
/*      */   }
/*      */   
/*      */   public SunTileCache() {
/*  154 */     this(16777216L);
/*      */   }
/*      */   
/*      */   public SunTileCache(long memoryCapacity) {
/*  166 */     if (memoryCapacity < 0L)
/*  167 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileCache")); 
/*  170 */     this.memoryCapacity = memoryCapacity;
/*  174 */     this.cache = new Hashtable(1009, 0.5F);
/*      */   }
/*      */   
/*      */   public void add(RenderedImage owner, int tileX, int tileY, Raster tile) {
/*  195 */     add(owner, tileX, tileY, tile, (Object)null);
/*      */   }
/*      */   
/*      */   public synchronized void add(RenderedImage owner, int tileX, int tileY, Raster tile, Object tileCacheMetric) {
/*  218 */     if (this.memoryCapacity == 0L)
/*      */       return; 
/*  224 */     Object key = SunCachedTile.hashKey(owner, tileX, tileY);
/*  225 */     SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  227 */     if (ct != null) {
/*  229 */       ct.timeStamp = this.timeStamp++;
/*  231 */       if (ct != this.first) {
/*  233 */         if (ct == this.last) {
/*  234 */           this.last = ct.previous;
/*  235 */           this.last.next = null;
/*      */         } else {
/*  237 */           ct.previous.next = ct.next;
/*  238 */           ct.next.previous = ct.previous;
/*      */         } 
/*  241 */         ct.previous = null;
/*  242 */         ct.next = this.first;
/*  244 */         this.first.previous = ct;
/*  245 */         this.first = ct;
/*      */       } 
/*  248 */       this.hitCount++;
/*  250 */       if (this.diagnostics) {
/*  251 */         ct.action = 4;
/*  252 */         setChanged();
/*  253 */         notifyObservers(ct);
/*      */       } 
/*      */     } else {
/*  257 */       ct = new SunCachedTile(owner, tileX, tileY, tile, tileCacheMetric);
/*  261 */       if (this.memoryUsage + ct.memorySize > this.memoryCapacity && ct.memorySize > (long)((float)this.memoryCapacity * this.memoryThreshold))
/*      */         return; 
/*  266 */       ct.timeStamp = this.timeStamp++;
/*  267 */       ct.previous = null;
/*  268 */       ct.next = this.first;
/*  270 */       if (this.first == null && this.last == null) {
/*  271 */         this.first = ct;
/*  272 */         this.last = ct;
/*      */       } else {
/*  274 */         this.first.previous = ct;
/*  275 */         this.first = ct;
/*      */       } 
/*  279 */       if (this.cache.put(ct.key, ct) == null) {
/*  280 */         this.memoryUsage += ct.memorySize;
/*  281 */         this.tileCount++;
/*  284 */         if (this.cacheSortedSet != null)
/*  285 */           this.cacheSortedSet.add(ct); 
/*  288 */         if (this.diagnostics) {
/*  289 */           ct.action = 0;
/*  290 */           setChanged();
/*  291 */           notifyObservers(ct);
/*      */         } 
/*      */       } 
/*  296 */       if (this.memoryUsage > this.memoryCapacity)
/*  297 */         memoryControl(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void remove(RenderedImage owner, int tileX, int tileY) {
/*  312 */     if (this.memoryCapacity == 0L)
/*      */       return; 
/*  316 */     Object key = SunCachedTile.hashKey(owner, tileX, tileY);
/*  317 */     SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  319 */     if (ct != null) {
/*  326 */       ct.action = 6;
/*  327 */       setChanged();
/*  328 */       notifyObservers(ct);
/*  330 */       ct = (SunCachedTile)this.cache.remove(key);
/*  333 */       if (ct != null) {
/*  334 */         this.memoryUsage -= ct.memorySize;
/*  335 */         this.tileCount--;
/*  337 */         if (this.cacheSortedSet != null)
/*  338 */           this.cacheSortedSet.remove(ct); 
/*  341 */         if (ct == this.first) {
/*  342 */           if (ct == this.last) {
/*  343 */             this.first = null;
/*  344 */             this.last = null;
/*      */           } else {
/*  346 */             this.first = ct.next;
/*  347 */             this.first.previous = null;
/*      */           } 
/*  349 */         } else if (ct == this.last) {
/*  350 */           this.last = ct.previous;
/*  351 */           this.last.next = null;
/*      */         } else {
/*  353 */           ct.previous.next = ct.next;
/*  354 */           ct.next.previous = ct.previous;
/*      */         } 
/*  369 */         if (this.diagnostics) {
/*  370 */           ct.action = 1;
/*  371 */           setChanged();
/*  372 */           notifyObservers(ct);
/*      */         } 
/*  375 */         ct.previous = null;
/*  376 */         ct.next = null;
/*  377 */         ct = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Raster getTile(RenderedImage owner, int tileX, int tileY) {
/*  397 */     Raster tile = null;
/*  399 */     if (this.memoryCapacity == 0L)
/*  400 */       return null; 
/*  403 */     Object key = SunCachedTile.hashKey(owner, tileX, tileY);
/*  404 */     SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  406 */     if (ct == null) {
/*  407 */       this.missCount++;
/*      */     } else {
/*  409 */       tile = ct.getTile();
/*  412 */       ct.timeStamp = this.timeStamp++;
/*  414 */       if (ct != this.first) {
/*  416 */         if (ct == this.last) {
/*  417 */           this.last = ct.previous;
/*  418 */           this.last.next = null;
/*      */         } else {
/*  420 */           ct.previous.next = ct.next;
/*  421 */           ct.next.previous = ct.previous;
/*      */         } 
/*  424 */         ct.previous = null;
/*  425 */         ct.next = this.first;
/*  427 */         this.first.previous = ct;
/*  428 */         this.first = ct;
/*      */       } 
/*  431 */       this.hitCount++;
/*  433 */       if (this.diagnostics) {
/*  434 */         ct.action = 5;
/*  435 */         setChanged();
/*  436 */         notifyObservers(ct);
/*      */       } 
/*      */     } 
/*  440 */     return tile;
/*      */   }
/*      */   
/*      */   public synchronized Raster[] getTiles(RenderedImage owner) {
/*  453 */     Raster[] tiles = null;
/*  455 */     if (this.memoryCapacity == 0L)
/*  456 */       return null; 
/*  459 */     int size = Math.min(owner.getNumXTiles() * owner.getNumYTiles(), (int)this.tileCount);
/*  462 */     if (size > 0) {
/*  463 */       int minTx = owner.getMinTileX();
/*  464 */       int minTy = owner.getMinTileY();
/*  465 */       int maxTx = minTx + owner.getNumXTiles();
/*  466 */       int maxTy = minTy + owner.getNumYTiles();
/*  469 */       Vector temp = new Vector(10, 20);
/*  471 */       for (int y = minTy; y < maxTy; y++) {
/*  472 */         for (int x = minTx; x < maxTx; x++) {
/*  476 */           Raster raster = null;
/*  477 */           Object key = SunCachedTile.hashKey(owner, x, y);
/*  478 */           SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  480 */           if (ct == null) {
/*  481 */             raster = null;
/*  482 */             this.missCount++;
/*      */           } else {
/*  484 */             raster = ct.getTile();
/*  487 */             ct.timeStamp = this.timeStamp++;
/*  489 */             if (ct != this.first) {
/*  491 */               if (ct == this.last) {
/*  492 */                 this.last = ct.previous;
/*  493 */                 this.last.next = null;
/*      */               } else {
/*  495 */                 ct.previous.next = ct.next;
/*  496 */                 ct.next.previous = ct.previous;
/*      */               } 
/*  499 */               ct.previous = null;
/*  500 */               ct.next = this.first;
/*  502 */               this.first.previous = ct;
/*  503 */               this.first = ct;
/*      */             } 
/*  506 */             this.hitCount++;
/*  508 */             if (this.diagnostics) {
/*  509 */               ct.action = 5;
/*  510 */               setChanged();
/*  511 */               notifyObservers(ct);
/*      */             } 
/*      */           } 
/*  516 */           if (raster != null)
/*  517 */             temp.add(raster); 
/*      */         } 
/*      */       } 
/*  522 */       int tmpsize = temp.size();
/*  523 */       if (tmpsize > 0)
/*  524 */         tiles = temp.<Raster>toArray(new Raster[tmpsize]); 
/*      */     } 
/*  528 */     return tiles;
/*      */   }
/*      */   
/*      */   public void removeTiles(RenderedImage owner) {
/*  538 */     if (this.memoryCapacity > 0L) {
/*  539 */       int minTx = owner.getMinTileX();
/*  540 */       int minTy = owner.getMinTileY();
/*  541 */       int maxTx = minTx + owner.getNumXTiles();
/*  542 */       int maxTy = minTy + owner.getNumYTiles();
/*  544 */       for (int y = minTy; y < maxTy; y++) {
/*  545 */         for (int x = minTx; x < maxTx; x++)
/*  546 */           remove(owner, x, y); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void addTiles(RenderedImage owner, Point[] tileIndices, Raster[] tiles, Object tileCacheMetric) {
/*  568 */     if (this.memoryCapacity == 0L)
/*      */       return; 
/*  573 */     for (int i = 0; i < tileIndices.length; i++) {
/*  574 */       int tileX = (tileIndices[i]).x;
/*  575 */       int tileY = (tileIndices[i]).y;
/*  576 */       Raster tile = tiles[i];
/*  578 */       Object key = SunCachedTile.hashKey(owner, tileX, tileY);
/*  579 */       SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  581 */       if (ct != null) {
/*  583 */         ct.timeStamp = this.timeStamp++;
/*  585 */         if (ct != this.first) {
/*  587 */           if (ct == this.last) {
/*  588 */             this.last = ct.previous;
/*  589 */             this.last.next = null;
/*      */           } else {
/*  591 */             ct.previous.next = ct.next;
/*  592 */             ct.next.previous = ct.previous;
/*      */           } 
/*  595 */           ct.previous = null;
/*  596 */           ct.next = this.first;
/*  598 */           this.first.previous = ct;
/*  599 */           this.first = ct;
/*      */         } 
/*  602 */         this.hitCount++;
/*  604 */         if (this.diagnostics) {
/*  605 */           ct.action = 4;
/*  606 */           setChanged();
/*  607 */           notifyObservers(ct);
/*      */         } 
/*      */       } else {
/*  611 */         ct = new SunCachedTile(owner, tileX, tileY, tile, tileCacheMetric);
/*  615 */         if (this.memoryUsage + ct.memorySize > this.memoryCapacity && ct.memorySize > (long)((float)this.memoryCapacity * this.memoryThreshold))
/*      */           return; 
/*  620 */         ct.timeStamp = this.timeStamp++;
/*  621 */         ct.previous = null;
/*  622 */         ct.next = this.first;
/*  624 */         if (this.first == null && this.last == null) {
/*  625 */           this.first = ct;
/*  626 */           this.last = ct;
/*      */         } else {
/*  628 */           this.first.previous = ct;
/*  629 */           this.first = ct;
/*      */         } 
/*  633 */         if (this.cache.put(ct.key, ct) == null) {
/*  634 */           this.memoryUsage += ct.memorySize;
/*  635 */           this.tileCount++;
/*  638 */           if (this.cacheSortedSet != null)
/*  639 */             this.cacheSortedSet.add(ct); 
/*  642 */           if (this.diagnostics) {
/*  643 */             ct.action = 0;
/*  644 */             setChanged();
/*  645 */             notifyObservers(ct);
/*      */           } 
/*      */         } 
/*  650 */         if (this.memoryUsage > this.memoryCapacity)
/*  651 */           memoryControl(); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Raster[] getTiles(RenderedImage owner, Point[] tileIndices) {
/*  669 */     if (this.memoryCapacity == 0L)
/*  670 */       return null; 
/*  673 */     Raster[] tiles = new Raster[tileIndices.length];
/*  675 */     for (int i = 0; i < tiles.length; i++) {
/*  676 */       int tileX = (tileIndices[i]).x;
/*  677 */       int tileY = (tileIndices[i]).y;
/*  679 */       Object key = SunCachedTile.hashKey(owner, tileX, tileY);
/*  680 */       SunCachedTile ct = (SunCachedTile)this.cache.get(key);
/*  682 */       if (ct == null) {
/*  683 */         tiles[i] = null;
/*  684 */         this.missCount++;
/*      */       } else {
/*  686 */         tiles[i] = ct.getTile();
/*  689 */         ct.timeStamp = this.timeStamp++;
/*  691 */         if (ct != this.first) {
/*  693 */           if (ct == this.last) {
/*  694 */             this.last = ct.previous;
/*  695 */             this.last.next = null;
/*      */           } else {
/*  697 */             ct.previous.next = ct.next;
/*  698 */             ct.next.previous = ct.previous;
/*      */           } 
/*  701 */           ct.previous = null;
/*  702 */           ct.next = this.first;
/*  704 */           this.first.previous = ct;
/*  705 */           this.first = ct;
/*      */         } 
/*  708 */         this.hitCount++;
/*  710 */         if (this.diagnostics) {
/*  711 */           ct.action = 5;
/*  712 */           setChanged();
/*  713 */           notifyObservers(ct);
/*      */         } 
/*      */       } 
/*      */     } 
/*  718 */     return tiles;
/*      */   }
/*      */   
/*      */   public synchronized void flush() {
/*  733 */     Enumeration keys = this.cache.keys();
/*  736 */     this.hitCount = 0L;
/*  737 */     this.missCount = 0L;
/*  739 */     while (keys.hasMoreElements()) {
/*  740 */       Object key = keys.nextElement();
/*  741 */       SunCachedTile ct = (SunCachedTile)this.cache.remove(key);
/*  744 */       if (ct != null) {
/*  745 */         this.memoryUsage -= ct.memorySize;
/*  746 */         this.tileCount--;
/*  748 */         if (ct == this.first) {
/*  749 */           if (ct == this.last) {
/*  750 */             this.first = null;
/*  751 */             this.last = null;
/*      */           } else {
/*  753 */             this.first = ct.next;
/*  754 */             this.first.previous = null;
/*      */           } 
/*  756 */         } else if (ct == this.last) {
/*  757 */           this.last = ct.previous;
/*  758 */           this.last.next = null;
/*      */         } else {
/*  760 */           ct.previous.next = ct.next;
/*  761 */           ct.next.previous = ct.previous;
/*      */         } 
/*  764 */         ct.previous = null;
/*  765 */         ct.next = null;
/*  768 */         if (this.diagnostics) {
/*  769 */           ct.action = 2;
/*  770 */           setChanged();
/*  771 */           notifyObservers(ct);
/*      */         } 
/*      */       } 
/*      */     } 
/*  776 */     if (this.memoryCapacity > 0L)
/*  777 */       this.cache = new Hashtable(1009, 0.5F); 
/*  780 */     if (this.cacheSortedSet != null) {
/*  781 */       this.cacheSortedSet.clear();
/*  782 */       this.cacheSortedSet = Collections.synchronizedSortedSet(new TreeSet(this.comparator));
/*      */     } 
/*  786 */     this.tileCount = 0L;
/*  787 */     this.timeStamp = 0L;
/*  788 */     this.memoryUsage = 0L;
/*      */   }
/*      */   
/*      */   public int getTileCapacity() {
/*  799 */     return 0;
/*      */   }
/*      */   
/*      */   public void setTileCapacity(int tileCapacity) {}
/*      */   
/*      */   public long getMemoryCapacity() {
/*  816 */     return this.memoryCapacity;
/*      */   }
/*      */   
/*      */   public void setMemoryCapacity(long memoryCapacity) {
/*  833 */     if (memoryCapacity < 0L)
/*  834 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileCache")); 
/*  835 */     if (memoryCapacity == 0L)
/*  836 */       flush(); 
/*  839 */     this.memoryCapacity = memoryCapacity;
/*  841 */     if (this.memoryUsage > memoryCapacity)
/*  842 */       memoryControl(); 
/*      */   }
/*      */   
/*      */   public void enableDiagnostics() {
/*  848 */     this.diagnostics = true;
/*      */   }
/*      */   
/*      */   public void disableDiagnostics() {
/*  853 */     this.diagnostics = false;
/*      */   }
/*      */   
/*      */   public long getCacheTileCount() {
/*  857 */     return this.tileCount;
/*      */   }
/*      */   
/*      */   public long getCacheMemoryUsed() {
/*  861 */     return this.memoryUsage;
/*      */   }
/*      */   
/*      */   public long getCacheHitCount() {
/*  865 */     return this.hitCount;
/*      */   }
/*      */   
/*      */   public long getCacheMissCount() {
/*  869 */     return this.missCount;
/*      */   }
/*      */   
/*      */   public void resetCounts() {
/*  878 */     this.hitCount = 0L;
/*  879 */     this.missCount = 0L;
/*      */   }
/*      */   
/*      */   public void setMemoryThreshold(float mt) {
/*  888 */     if (mt < 0.0F || mt > 1.0F)
/*  889 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileCache")); 
/*  891 */     this.memoryThreshold = mt;
/*  892 */     memoryControl();
/*      */   }
/*      */   
/*      */   public float getMemoryThreshold() {
/*  902 */     return this.memoryThreshold;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  907 */     return getClass().getName() + "@" + Integer.toHexString(hashCode()) + ": memoryCapacity = " + Long.toHexString(this.memoryCapacity) + " memoryUsage = " + Long.toHexString(this.memoryUsage) + " #tilesInCache = " + Integer.toString(this.cache.size());
/*      */   }
/*      */   
/*      */   public Object getCachedObject() {
/*  915 */     return this.cache;
/*      */   }
/*      */   
/*      */   public synchronized void memoryControl() {
/*  924 */     if (this.cacheSortedSet == null) {
/*  925 */       standard_memory_control();
/*      */     } else {
/*  927 */       custom_memory_control();
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void standard_memory_control() {
/*  933 */     long limit = (long)((float)this.memoryCapacity * this.memoryThreshold);
/*  935 */     while (this.memoryUsage > limit && this.last != null) {
/*  936 */       SunCachedTile ct = (SunCachedTile)this.cache.get(this.last.key);
/*  938 */       if (ct != null) {
/*  939 */         ct = (SunCachedTile)this.cache.remove(this.last.key);
/*  941 */         this.memoryUsage -= this.last.memorySize;
/*  942 */         this.tileCount--;
/*  944 */         this.last = this.last.previous;
/*  946 */         if (this.last != null) {
/*  947 */           this.last.next.previous = null;
/*  948 */           this.last.next = null;
/*      */         } else {
/*  950 */           this.first = null;
/*      */         } 
/*  954 */         if (this.diagnostics) {
/*  955 */           ct.action = 3;
/*  956 */           setChanged();
/*  957 */           notifyObservers(ct);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void custom_memory_control() {
/*  965 */     long limit = (long)((float)this.memoryCapacity * this.memoryThreshold);
/*  966 */     Iterator iter = this.cacheSortedSet.iterator();
/*  969 */     while (iter.hasNext() && this.memoryUsage > limit) {
/*  970 */       SunCachedTile ct = iter.next();
/*  972 */       this.memoryUsage -= ct.memorySize;
/*  973 */       this.tileCount--;
/*      */       try {
/*  977 */         iter.remove();
/*  978 */       } catch (ConcurrentModificationException e) {
/*  979 */         ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/*  981 */         listener.errorOccurred(JaiI18N.getString("SunTileCache0"), e, this, false);
/*      */       } 
/*  987 */       if (ct == this.first) {
/*  988 */         if (ct == this.last) {
/*  989 */           this.first = null;
/*  990 */           this.last = null;
/*      */         } else {
/*  992 */           this.first = ct.next;
/*  994 */           if (this.first != null) {
/*  995 */             this.first.previous = null;
/*  996 */             this.first.next = ct.next.next;
/*      */           } 
/*      */         } 
/*  999 */       } else if (ct == this.last) {
/* 1000 */         this.last = ct.previous;
/* 1002 */         if (this.last != null) {
/* 1003 */           this.last.next = null;
/* 1004 */           this.last.previous = ct.previous.previous;
/*      */         } 
/*      */       } else {
/* 1007 */         SunCachedTile ptr = this.first.next;
/* 1009 */         while (ptr != null) {
/* 1011 */           if (ptr == ct) {
/* 1012 */             if (ptr.previous != null)
/* 1013 */               ptr.previous.next = ptr.next; 
/* 1016 */             if (ptr.next != null)
/* 1017 */               ptr.next.previous = ptr.previous; 
/*      */             break;
/*      */           } 
/* 1023 */           ptr = ptr.next;
/*      */         } 
/*      */       } 
/* 1028 */       this.cache.remove(ct.key);
/* 1031 */       if (this.diagnostics) {
/* 1032 */         ct.action = 3;
/* 1033 */         setChanged();
/* 1034 */         notifyObservers(ct);
/*      */       } 
/*      */     } 
/* 1041 */     if (this.memoryUsage > limit)
/* 1042 */       standard_memory_control(); 
/*      */   }
/*      */   
/*      */   public synchronized void setTileComparator(Comparator c) {
/* 1056 */     this.comparator = c;
/* 1058 */     if (this.comparator == null) {
/* 1060 */       if (this.cacheSortedSet != null) {
/* 1061 */         this.cacheSortedSet.clear();
/* 1062 */         this.cacheSortedSet = null;
/*      */       } 
/*      */     } else {
/* 1066 */       this.cacheSortedSet = Collections.synchronizedSortedSet(new TreeSet(this.comparator));
/* 1068 */       Enumeration keys = this.cache.keys();
/* 1070 */       while (keys.hasMoreElements()) {
/* 1071 */         Object key = keys.nextElement();
/* 1072 */         Object ct = this.cache.get(key);
/* 1073 */         this.cacheSortedSet.add(ct);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Comparator getTileComparator() {
/* 1084 */     return this.comparator;
/*      */   }
/*      */   
/*      */   public void dump() {
/* 1090 */     System.out.println("first = " + this.first);
/* 1091 */     System.out.println("last  = " + this.last);
/* 1093 */     Iterator iter = this.cacheSortedSet.iterator();
/* 1094 */     int k = 0;
/* 1096 */     while (iter.hasNext()) {
/* 1097 */       SunCachedTile ct = iter.next();
/* 1098 */       System.out.println(k++);
/* 1099 */       System.out.println(ct);
/*      */     } 
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1104 */     ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 1106 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\SunTileCache.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */