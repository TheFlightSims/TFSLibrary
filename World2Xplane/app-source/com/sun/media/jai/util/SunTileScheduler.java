/*      */ package com.sun.media.jai.util;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.Raster;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.OpImage;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.TileCache;
/*      */ import javax.media.jai.TileComputationListener;
/*      */ import javax.media.jai.TileRequest;
/*      */ import javax.media.jai.TileScheduler;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public final class SunTileScheduler implements TileScheduler {
/*      */   private static final int NUM_THREADS_DEFAULT = 2;
/*      */   
/*      */   private static final int NUM_PREFETCH_THREADS_DEFAULT = 1;
/*      */   
/*  500 */   private static int numInstances = 0;
/*      */   
/*  505 */   private static String name = JaiI18N.getString("SunTileSchedulerName");
/*      */   
/*      */   private ThreadGroup rootGroup;
/*      */   
/*      */   private ThreadGroup standardGroup;
/*      */   
/*      */   private ThreadGroup prefetchGroup;
/*      */   
/*  520 */   private int parallelism = 2;
/*      */   
/*  523 */   private int prefetchParallelism = 1;
/*      */   
/*  526 */   private int priority = 5;
/*      */   
/*  529 */   private int prefetchPriority = 1;
/*      */   
/*  532 */   private LinkedList queue = null;
/*      */   
/*  535 */   private LinkedList prefetchQueue = null;
/*      */   
/*  542 */   private Vector workers = new Vector();
/*      */   
/*  549 */   private Vector prefetchWorkers = new Vector();
/*      */   
/*  557 */   private int numWorkerThreads = 0;
/*      */   
/*  565 */   private int numPrefetchThreads = 0;
/*      */   
/*  575 */   private Map tilesInProgress = new HashMap();
/*      */   
/*  586 */   Map tileRequests = new HashMap();
/*      */   
/*  595 */   Map tileJobs = new HashMap();
/*      */   
/*      */   private String nameOfThisInstance;
/*      */   
/*      */   static Object tileKey(PlanarImage owner, int tileX, int tileY) {
/*  611 */     long idx = tileY * owner.getNumXTiles() + tileX;
/*  613 */     BigInteger imageID = (BigInteger)owner.getImageID();
/*  614 */     byte[] buf = imageID.toByteArray();
/*  615 */     int length = buf.length;
/*  616 */     byte[] buf1 = new byte[length + 8];
/*  617 */     System.arraycopy(buf, 0, buf1, 0, length);
/*  618 */     for (int i = 7, j = 0; i >= 0; i--, j += 8)
/*  619 */       buf1[length++] = (byte)(int)(idx >> j); 
/*  620 */     return new BigInteger(buf1);
/*      */   }
/*      */   
/*      */   static Set getListeners(List reqList) {
/*  629 */     int numReq = reqList.size();
/*  630 */     HashSet listeners = null;
/*  631 */     for (int j = 0; j < numReq; j++) {
/*  632 */       Request req = reqList.get(j);
/*  634 */       if (req.listeners != null && !req.listeners.isEmpty()) {
/*  635 */         if (listeners == null)
/*  636 */           listeners = new HashSet(); 
/*  638 */         listeners.addAll(req.listeners);
/*      */       } 
/*      */     } 
/*  642 */     return listeners;
/*      */   }
/*      */   
/*      */   private static String getStackTraceString(Throwable e) {
/*  650 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
/*  651 */     PrintStream printStream = new PrintStream(byteStream);
/*  652 */     e.printStackTrace(printStream);
/*  653 */     printStream.flush();
/*  654 */     String stackTraceString = byteStream.toString();
/*  655 */     printStream.close();
/*  656 */     return stackTraceString;
/*      */   }
/*      */   
/*      */   public SunTileScheduler(int parallelism, int priority, int prefetchParallelism, int prefetchPriority) {
/*  672 */     this();
/*  674 */     setParallelism(parallelism);
/*  675 */     setPriority(priority);
/*  676 */     setPrefetchParallelism(prefetchParallelism);
/*  677 */     setPrefetchPriority(prefetchPriority);
/*      */   }
/*      */   
/*      */   public SunTileScheduler() {
/*  685 */     this.queue = new LinkedList();
/*  686 */     this.prefetchQueue = new LinkedList();
/*  688 */     this.nameOfThisInstance = name + numInstances;
/*  689 */     this.rootGroup = new ThreadGroup(this.nameOfThisInstance);
/*  690 */     this.rootGroup.setDaemon(true);
/*  692 */     this.standardGroup = new ThreadGroup(this.rootGroup, this.nameOfThisInstance + "Standard");
/*  694 */     this.standardGroup.setDaemon(true);
/*  696 */     this.prefetchGroup = new ThreadGroup(this.rootGroup, this.nameOfThisInstance + "Prefetch");
/*  698 */     this.prefetchGroup.setDaemon(true);
/*  700 */     numInstances++;
/*      */   }
/*      */   
/*      */   Exception compute(PlanarImage owner, Point[] tileIndices, Raster[] tiles, int offset, int numTiles, Request request) {
/*  709 */     Exception exception = null;
/*  711 */     int j = offset;
/*  712 */     if (request == null || request.listeners == null) {
/*  713 */       for (int i = 0; i < numTiles; i++, j++) {
/*  714 */         Point p = tileIndices[j];
/*      */         try {
/*  717 */           tiles[j] = owner.getTile(p.x, p.y);
/*  718 */         } catch (Exception e) {
/*  719 */           exception = e;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  726 */       Request[] reqs = { request };
/*  727 */       for (int i = 0; i < numTiles; i++, j++) {
/*  728 */         Point p = tileIndices[j];
/*  731 */         Integer tileStatus = new Integer(1);
/*  733 */         request.tileStatus.put(p, tileStatus);
/*      */         try {
/*  736 */           tiles[j] = owner.getTile(p.x, p.y);
/*  737 */           Iterator iter = request.listeners.iterator();
/*  738 */           while (iter.hasNext()) {
/*  740 */             tileStatus = new Integer(2);
/*  742 */             request.tileStatus.put(p, tileStatus);
/*  744 */             TileComputationListener listener = iter.next();
/*  746 */             listener.tileComputed(this, (TileRequest[])reqs, owner, p.x, p.y, tiles[j]);
/*      */           } 
/*  752 */         } catch (Exception e) {
/*  753 */           exception = e;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  796 */     if (exception != null && request != null && request.listeners != null) {
/*  797 */       int lastOffset = j;
/*  798 */       int numFailed = numTiles - lastOffset - offset;
/*  802 */       for (int i = 0, k = lastOffset; i < numFailed; i++) {
/*  803 */         Integer tileStatus = new Integer(4);
/*  805 */         request.tileStatus.put(tileIndices[k++], tileStatus);
/*      */       } 
/*  809 */       Request[] reqs = { request };
/*  810 */       for (int m = 0, n = lastOffset; m < numFailed; m++) {
/*  811 */         Point p = tileIndices[n++];
/*  812 */         Iterator iter = request.listeners.iterator();
/*  813 */         while (iter.hasNext()) {
/*  814 */           TileComputationListener listener = iter.next();
/*  816 */           listener.tileComputationFailure(this, (TileRequest[])reqs, owner, p.x, p.y, exception);
/*      */         } 
/*      */       } 
/*      */     } 
/*  850 */     return exception;
/*      */   }
/*      */   
/*      */   public Raster scheduleTile(OpImage owner, int tileX, int tileY) {
/*  877 */     if (owner == null)
/*  878 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler1")); 
/*  882 */     Raster tile = null;
/*  885 */     Object tileID = tileKey((PlanarImage)owner, tileX, tileY);
/*  888 */     boolean computeTile = false;
/*  889 */     Object[] cache = null;
/*  890 */     synchronized (this.tilesInProgress) {
/*  891 */       if (computeTile = !this.tilesInProgress.containsKey(tileID)) {
/*  893 */         this.tilesInProgress.put(tileID, cache = new Object[1]);
/*      */       } else {
/*  896 */         cache = (Object[])this.tilesInProgress.get(tileID);
/*      */       } 
/*      */     } 
/*  900 */     if (computeTile) {
/*      */       try {
/*      */         try {
/*  904 */           tile = owner.computeTile(tileX, tileY);
/*  905 */         } catch (OutOfMemoryError e) {
/*  907 */           TileCache tileCache = owner.getTileCache();
/*  908 */           if (tileCache != null) {
/*  909 */             tileCache.flush();
/*  910 */             System.gc();
/*      */           } 
/*  914 */           tile = owner.computeTile(tileX, tileY);
/*      */         } 
/*  916 */       } catch (Throwable e) {
/*  918 */         if (e instanceof Error)
/*  919 */           throw (Error)e; 
/*  920 */         if (e instanceof RuntimeException) {
/*  921 */           sendExceptionToListener(JaiI18N.getString("SunTileScheduler6"), e);
/*      */         } else {
/*  924 */           String message = JaiI18N.getString("SunTileScheduler6");
/*  925 */           sendExceptionToListener(message, (Throwable)new ImagingException(message, e));
/*      */         } 
/*      */       } finally {
/*  933 */         synchronized (cache) {
/*  935 */           cache[0] = (tile != null) ? tile : new Object();
/*  938 */           cache.notifyAll();
/*  940 */           synchronized (this.tilesInProgress) {
/*  942 */             this.tilesInProgress.remove(tileID);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  947 */       synchronized (cache) {
/*  950 */         if (cache[0] == null)
/*      */           try {
/*  953 */             cache.wait();
/*  954 */           } catch (Exception e) {} 
/*  960 */         if (cache[0] instanceof Raster) {
/*  961 */           tile = (Raster)cache[0];
/*      */         } else {
/*  963 */           throw new RuntimeException(JaiI18N.getString("SunTileScheduler5"));
/*      */         } 
/*      */       } 
/*      */     } 
/*  968 */     return tile;
/*      */   }
/*      */   
/*      */   private Object scheduleJob(PlanarImage owner, Point[] tileIndices, boolean isBlocking, boolean isPrefetch, TileComputationListener[] listeners) {
/*      */     TileJob[] arrayOfTileJob;
/*  998 */     if (owner == null || tileIndices == null)
/* 1000 */       throw new IllegalArgumentException(); 
/* 1001 */     if ((isBlocking || isPrefetch) && listeners != null)
/* 1003 */       throw new IllegalArgumentException(); 
/* 1004 */     if (isBlocking && isPrefetch)
/* 1005 */       throw new IllegalArgumentException(); 
/* 1008 */     int numTiles = tileIndices.length;
/* 1009 */     Raster[] tiles = new Raster[numTiles];
/* 1010 */     Object returnValue = tiles;
/* 1012 */     int numThreads = 0;
/* 1013 */     Job[] jobs = null;
/* 1014 */     int numJobs = 0;
/* 1016 */     synchronized (getWorkers(isPrefetch)) {
/* 1017 */       numThreads = getNumThreads(isPrefetch);
/* 1019 */       if (numThreads > 0)
/* 1020 */         if (numTiles <= numThreads || (!isBlocking && !isPrefetch)) {
/* 1023 */           jobs = new Job[numTiles];
/* 1025 */           if (!isBlocking && !isPrefetch) {
/* 1026 */             Request request = new Request(this, owner, tileIndices, listeners);
/* 1030 */             returnValue = request;
/* 1033 */             while (numJobs < numTiles) {
/* 1034 */               Point p = tileIndices[numJobs];
/* 1036 */               Object tileID = tileKey(owner, p.x, p.y);
/* 1038 */               synchronized (this.tileRequests) {
/* 1039 */                 List reqList = null;
/* 1040 */                 if (this.tileRequests.containsKey(tileID)) {
/* 1043 */                   reqList = (List)this.tileRequests.get(tileID);
/* 1044 */                   reqList.add(request);
/* 1045 */                   numTiles--;
/*      */                 } else {
/* 1048 */                   reqList = new ArrayList();
/* 1049 */                   reqList.add(request);
/* 1050 */                   this.tileRequests.put(tileID, reqList);
/* 1052 */                   jobs[numJobs] = new RequestJob(this, owner, p.x, p.y, tiles, numJobs);
/* 1057 */                   this.tileJobs.put(tileID, jobs[numJobs]);
/* 1059 */                   addJob(jobs[numJobs++], false);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } else {
/* 1064 */             while (numJobs < numTiles) {
/* 1065 */               jobs[numJobs] = new TileJob(this, isBlocking, owner, tileIndices, tiles, numJobs, 1);
/* 1072 */               addJob(jobs[numJobs++], isPrefetch);
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1078 */           float frac = 1.0F / 2.0F * numThreads;
/* 1083 */           int minTilesPerThread = (numThreads == 1) ? numTiles : Math.min(Math.max(1, (int)(frac * numTiles / 2.0F + 0.5F)), numTiles);
/* 1094 */           int maxNumJobs = (numThreads == 1) ? 1 : (int)(numTiles / minTilesPerThread + 0.5F);
/* 1096 */           arrayOfTileJob = new TileJob[maxNumJobs];
/* 1099 */           int numTilesQueued = 0;
/* 1100 */           int numTilesLeft = numTiles - numTilesQueued;
/* 1105 */           while (numTilesLeft > 0) {
/* 1108 */             int numTilesInThread = (int)(frac * numTilesLeft + 0.5F);
/* 1112 */             if (numTilesInThread < minTilesPerThread)
/* 1113 */               numTilesInThread = minTilesPerThread; 
/* 1117 */             if (numTilesInThread > numTilesLeft)
/* 1118 */               numTilesInThread = numTilesLeft; 
/* 1124 */             numTilesLeft -= numTilesInThread;
/* 1128 */             if (numTilesLeft < minTilesPerThread) {
/* 1129 */               numTilesInThread += numTilesLeft;
/* 1130 */               numTilesLeft = 0;
/*      */             } 
/* 1134 */             arrayOfTileJob[numJobs] = new TileJob(this, isBlocking, owner, tileIndices, tiles, numTilesQueued, numTilesInThread);
/* 1143 */             addJob(arrayOfTileJob[numJobs++], isPrefetch);
/* 1146 */             numTilesQueued += numTilesInThread;
/*      */           } 
/*      */         }  
/*      */     } 
/* 1152 */     if (numThreads != 0) {
/* 1156 */       if (isBlocking) {
/* 1157 */         LinkedList jobQueue = getQueue(isPrefetch);
/* 1159 */         for (int i = 0; i < numJobs; i++) {
/* 1160 */           synchronized (this) {
/* 1161 */             while (arrayOfTileJob[i].notDone()) {
/*      */               try {
/* 1163 */                 wait();
/* 1164 */               } catch (InterruptedException ie) {}
/*      */             } 
/*      */           } 
/* 1172 */           Exception e = arrayOfTileJob[i].getException();
/* 1174 */           if (e != null) {
/* 1177 */             String message = JaiI18N.getString("SunTileScheduler7");
/* 1178 */             sendExceptionToListener(message, (Throwable)new ImagingException(message, e));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1188 */       Request request = null;
/* 1189 */       if (!isBlocking && !isPrefetch) {
/* 1190 */         request = new Request(this, owner, tileIndices, listeners);
/* 1191 */         returnValue = request;
/*      */       } 
/* 1195 */       Exception e = compute(owner, tileIndices, tiles, 0, numTiles, request);
/* 1200 */       if (e != null) {
/* 1201 */         String message = JaiI18N.getString("SunTileScheduler7");
/* 1202 */         sendExceptionToListener(message, (Throwable)new ImagingException(message, e));
/*      */       } 
/*      */     } 
/* 1211 */     return returnValue;
/*      */   }
/*      */   
/*      */   public Raster[] scheduleTiles(OpImage owner, Point[] tileIndices) {
/* 1224 */     if (owner == null || tileIndices == null)
/* 1225 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler0")); 
/* 1227 */     return (Raster[])scheduleJob((PlanarImage)owner, tileIndices, true, false, null);
/*      */   }
/*      */   
/*      */   public TileRequest scheduleTiles(PlanarImage target, Point[] tileIndices, TileComputationListener[] tileListeners) {
/* 1240 */     if (target == null || tileIndices == null)
/* 1241 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler4")); 
/* 1243 */     return (TileRequest)scheduleJob(target, tileIndices, false, false, tileListeners);
/*      */   }
/*      */   
/*      */   public void cancelTiles(TileRequest request, Point[] tileIndices) {
/* 1267 */     if (request == null)
/* 1268 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler3")); 
/* 1271 */     Request req = (Request)request;
/* 1272 */     synchronized (this.tileRequests) {
/*      */       Point[] indices;
/* 1274 */       List reqIndexList = req.indices;
/* 1278 */       if (tileIndices != null && tileIndices.length > 0) {
/* 1280 */         List tileIndexList = Arrays.asList(tileIndices);
/* 1283 */         tileIndexList.retainAll(reqIndexList);
/* 1285 */         indices = tileIndexList.<Point>toArray(new Point[0]);
/*      */       } else {
/* 1287 */         indices = reqIndexList.<Point>toArray(new Point[0]);
/*      */       } 
/* 1291 */       int numTiles = indices.length;
/* 1294 */       Integer tileStatus = new Integer(3);
/* 1297 */       for (int i = 0; i < numTiles; i++) {
/* 1298 */         Point p = indices[i];
/* 1301 */         Object tileID = tileKey(req.image, p.x, p.y);
/* 1304 */         List reqList = (List)this.tileRequests.get(tileID);
/* 1307 */         if (reqList != null) {
/* 1312 */           reqList.remove(req);
/* 1316 */           if (reqList.isEmpty()) {
/* 1317 */             synchronized (this.queue) {
/* 1318 */               Object job = this.tileJobs.remove(tileID);
/* 1319 */               if (job != null)
/* 1320 */                 this.queue.remove(job); 
/*      */             } 
/* 1323 */             this.tileRequests.remove(tileID);
/*      */           } 
/* 1327 */           req.tileStatus.put(p, tileStatus);
/* 1330 */           if (req.listeners != null) {
/* 1331 */             TileRequest[] reqArray = { req };
/* 1332 */             Iterator iter = req.listeners.iterator();
/* 1333 */             while (iter.hasNext()) {
/* 1334 */               TileComputationListener listener = iter.next();
/* 1336 */               listener.tileCancelled(this, reqArray, req.image, p.x, p.y);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void prefetchTiles(PlanarImage owner, Point[] tileIndices) {
/* 1352 */     if (owner == null || tileIndices == null)
/* 1353 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler0")); 
/* 1355 */     scheduleJob(owner, tileIndices, false, true, null);
/*      */   }
/*      */   
/*      */   public void setParallelism(int parallelism) {
/* 1381 */     if (parallelism < 0)
/* 1382 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler2")); 
/* 1384 */     this.parallelism = parallelism;
/*      */   }
/*      */   
/*      */   public int getParallelism() {
/* 1391 */     return this.parallelism;
/*      */   }
/*      */   
/*      */   public void setPrefetchParallelism(int parallelism) {
/* 1399 */     if (parallelism < 0)
/* 1400 */       throw new IllegalArgumentException(JaiI18N.getString("SunTileScheduler2")); 
/* 1402 */     this.prefetchParallelism = parallelism;
/*      */   }
/*      */   
/*      */   public int getPrefetchParallelism() {
/* 1410 */     return this.prefetchParallelism;
/*      */   }
/*      */   
/*      */   public void setPriority(int priority) {
/* 1428 */     this.priority = Math.max(Math.min(priority, 10), 1);
/*      */   }
/*      */   
/*      */   public int getPriority() {
/* 1436 */     return this.priority;
/*      */   }
/*      */   
/*      */   public void setPrefetchPriority(int priority) {
/* 1449 */     this.prefetchPriority = Math.max(Math.min(priority, 10), 1);
/*      */   }
/*      */   
/*      */   public int getPrefetchPriority() {
/* 1458 */     return this.prefetchPriority;
/*      */   }
/*      */   
/*      */   private void createThreadGroup(boolean isPrefetch) {
/* 1470 */     if (this.rootGroup == null || this.rootGroup.isDestroyed()) {
/* 1471 */       this.rootGroup = new ThreadGroup(this.nameOfThisInstance);
/* 1472 */       this.rootGroup.setDaemon(true);
/*      */     } 
/* 1475 */     if (isPrefetch && (this.prefetchGroup == null || this.prefetchGroup.isDestroyed())) {
/* 1477 */       this.prefetchGroup = new ThreadGroup(this.rootGroup, this.nameOfThisInstance + "Prefetch");
/* 1479 */       this.prefetchGroup.setDaemon(true);
/*      */     } 
/* 1482 */     if (!isPrefetch && (this.standardGroup == null || this.standardGroup.isDestroyed())) {
/* 1484 */       this.standardGroup = new ThreadGroup(this.rootGroup, this.nameOfThisInstance + "Standard");
/* 1486 */       this.standardGroup.setDaemon(true);
/*      */     } 
/* 1489 */     Vector thr = getWorkers(isPrefetch);
/* 1490 */     int size = thr.size();
/* 1492 */     for (int i = size - 1; i >= 0; i--) {
/* 1493 */       Thread t = thr.get(i);
/* 1494 */       if (!t.isAlive())
/* 1495 */         thr.remove(t); 
/*      */     } 
/* 1498 */     if (isPrefetch) {
/* 1499 */       this.numPrefetchThreads = thr.size();
/*      */     } else {
/* 1501 */       this.numWorkerThreads = thr.size();
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getNumThreads(boolean isPrefetch) {
/*      */     int nthr, prll, prty;
/* 1513 */     createThreadGroup(isPrefetch);
/* 1516 */     Vector thr = getWorkers(isPrefetch);
/* 1522 */     if (isPrefetch) {
/* 1523 */       nthr = this.numPrefetchThreads;
/* 1524 */       prll = this.prefetchParallelism;
/* 1525 */       prty = this.prefetchPriority;
/*      */     } else {
/* 1527 */       nthr = this.numWorkerThreads;
/* 1528 */       prll = this.parallelism;
/* 1529 */       prty = this.priority;
/*      */     } 
/* 1533 */     if (nthr > 0 && ((Thread)thr.get(0)).getPriority() != prty) {
/* 1535 */       int size = thr.size();
/* 1536 */       for (int i = 0; i < size; i++) {
/* 1537 */         Thread t = thr.get(i);
/* 1538 */         if (t != null && t.getThreadGroup() != null)
/* 1539 */           t.setPriority(prty); 
/*      */       } 
/*      */     } 
/* 1544 */     if (nthr < prll) {
/* 1547 */       while (nthr < prll) {
/* 1548 */         Thread t = new WorkerThread(isPrefetch ? this.prefetchGroup : this.standardGroup, this, isPrefetch);
/* 1552 */         t.setPriority(prty);
/* 1553 */         thr.add(t);
/* 1554 */         nthr++;
/*      */       } 
/*      */     } else {
/* 1560 */       while (nthr > prll) {
/* 1561 */         addJob(WorkerThread.TERMINATE, isPrefetch);
/* 1562 */         nthr--;
/*      */       } 
/*      */     } 
/* 1567 */     if (isPrefetch) {
/* 1568 */       this.numPrefetchThreads = nthr;
/*      */     } else {
/* 1570 */       this.numWorkerThreads = nthr;
/*      */     } 
/* 1573 */     return nthr;
/*      */   }
/*      */   
/*      */   Vector getWorkers(boolean isPrefetch) {
/* 1578 */     return isPrefetch ? this.workers : this.prefetchWorkers;
/*      */   }
/*      */   
/*      */   LinkedList getQueue(boolean isPrefetch) {
/* 1583 */     return isPrefetch ? this.prefetchQueue : this.queue;
/*      */   }
/*      */   
/*      */   private void addJob(Object job, boolean isPrefetch) {
/* 1588 */     if (job == null || (job != WorkerThread.TERMINATE && !(job instanceof Job)))
/* 1591 */       throw new IllegalArgumentException(); 
/*      */     LinkedList jobQueue;
/* 1595 */     synchronized (jobQueue = getQueue(isPrefetch)) {
/* 1596 */       if (isPrefetch || jobQueue.isEmpty() || job instanceof RequestJob) {
/* 1600 */         jobQueue.addLast(job);
/*      */       } else {
/* 1604 */         boolean inserted = false;
/* 1605 */         for (int idx = jobQueue.size() - 1; idx >= 0; idx--) {
/* 1606 */           if (jobQueue.get(idx) instanceof TileJob) {
/* 1607 */             jobQueue.add(idx + 1, job);
/* 1608 */             inserted = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1612 */         if (!inserted)
/* 1613 */           jobQueue.addFirst(job); 
/*      */       } 
/* 1616 */       jobQueue.notify();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 1622 */     terminateAll(false);
/* 1623 */     terminateAll(true);
/* 1624 */     super.finalize();
/*      */   }
/*      */   
/*      */   private void terminateAll(boolean isPrefetch) {
/* 1629 */     synchronized (getWorkers(isPrefetch)) {
/* 1630 */       int numThreads = isPrefetch ? this.numPrefetchThreads : this.numWorkerThreads;
/* 1632 */       for (int i = 0; i < numThreads; i++) {
/* 1633 */         addJob(WorkerThread.TERMINATE, isPrefetch);
/* 1634 */         if (isPrefetch) {
/* 1635 */           this.numPrefetchThreads--;
/*      */         } else {
/* 1637 */           this.numWorkerThreads--;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Throwable e) {
/* 1644 */     ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 1646 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\SunTileScheduler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */