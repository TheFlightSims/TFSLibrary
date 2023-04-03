/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ public final class RWLock {
/*     */   private boolean allowUpgrades;
/*     */   
/*  46 */   private static int READ = 1;
/*     */   
/*  47 */   private static int WRITE = 2;
/*     */   
/*  49 */   private static int NOT_FOUND = -1;
/*     */   
/*  53 */   private WaitingList waitingList = new WaitingList();
/*     */   
/*     */   private class WaitingList extends LinkedList {
/*     */     private final RWLock this$0;
/*     */     
/*     */     private WaitingList(RWLock this$0) {
/*  55 */       RWLock.this = RWLock.this;
/*     */     }
/*     */     
/*     */     int indexOfFirstWriter() {
/*  60 */       ListIterator iter = listIterator(0);
/*  61 */       int index = 0;
/*  63 */       while (iter.hasNext()) {
/*  64 */         if (((RWLock.ReaderWriter)iter.next()).lockType == RWLock.WRITE)
/*  65 */           return index; 
/*  66 */         index++;
/*     */       } 
/*  69 */       return RWLock.NOT_FOUND;
/*     */     }
/*     */     
/*     */     int indexOfLastGranted() {
/*  75 */       ListIterator iter = listIterator(size());
/*  76 */       int index = size() - 1;
/*  78 */       while (iter.hasPrevious()) {
/*  79 */         if (((RWLock.ReaderWriter)iter.previous()).granted == true)
/*  80 */           return index; 
/*  81 */         index--;
/*     */       } 
/*  84 */       return RWLock.NOT_FOUND;
/*     */     }
/*     */     
/*     */     int findMe() {
/*  89 */       return indexOf(new RWLock.ReaderWriter());
/*     */     }
/*     */   }
/*     */   
/*     */   private class ReaderWriter {
/*     */     Thread key;
/*     */     
/*     */     int lockType;
/*     */     
/*     */     int lockCount;
/*     */     
/*     */     boolean granted;
/*     */     
/*     */     private final RWLock this$0;
/*     */     
/*     */     ReaderWriter() {
/* 102 */       this(0);
/*     */     }
/*     */     
/*     */     ReaderWriter(int type) {
/* 105 */       RWLock.this = RWLock.this;
/* 107 */       this.key = Thread.currentThread();
/* 108 */       this.lockType = type;
/* 109 */       this.lockCount = 0;
/* 110 */       this.granted = false;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 119 */       return (this.key == ((ReaderWriter)o).key);
/*     */     }
/*     */   }
/*     */   
/*     */   public RWLock(boolean allowUpgrades) {
/* 131 */     this.allowUpgrades = allowUpgrades;
/*     */   }
/*     */   
/*     */   public RWLock() {
/* 139 */     this(true);
/*     */   }
/*     */   
/*     */   public synchronized boolean forReading(int waitTime) {
/* 153 */     ReaderWriter element = null;
/* 157 */     int index = this.waitingList.findMe();
/* 159 */     if (index != NOT_FOUND) {
/* 160 */       element = (ReaderWriter)this.waitingList.get(index);
/*     */     } else {
/* 163 */       element = new ReaderWriter(READ);
/* 164 */       this.waitingList.add((E)element);
/*     */     } 
/* 170 */     if (element.lockCount > 0) {
/* 171 */       element.lockCount++;
/* 172 */       return true;
/*     */     } 
/* 175 */     long startTime = System.currentTimeMillis();
/* 176 */     long endTime = waitTime + startTime;
/*     */     do {
/* 179 */       int nextWriter = this.waitingList.indexOfFirstWriter();
/* 181 */       index = this.waitingList.findMe();
/* 185 */       if (nextWriter == NOT_FOUND || nextWriter > index) {
/* 186 */         element.lockCount++;
/* 187 */         element.granted = true;
/* 188 */         return true;
/*     */       } 
/* 195 */       if (waitTime == 0) {
/* 196 */         this.waitingList.remove(element);
/* 197 */         return false;
/*     */       } 
/*     */       try {
/* 203 */         if (waitTime < 0) {
/* 204 */           wait();
/*     */         } else {
/* 207 */           long delta = endTime - System.currentTimeMillis();
/* 209 */           if (delta > 0L)
/* 209 */             wait(delta); 
/*     */         } 
/* 211 */       } catch (InterruptedException e) {
/* 214 */         System.err.println(element.key.getName() + " : interrupted while waiting for a READ lock!");
/*     */       } 
/* 218 */     } while (waitTime < 0 || endTime > System.currentTimeMillis());
/* 221 */     this.waitingList.remove(element);
/* 224 */     notifyAll();
/* 227 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized boolean forReading() {
/* 238 */     return forReading(-1);
/*     */   }
/*     */   
/*     */   public synchronized boolean forWriting(int waitTime) throws UpgradeNotAllowed {
/* 260 */     ReaderWriter element = null;
/* 264 */     int index = this.waitingList.findMe();
/* 266 */     if (index != NOT_FOUND) {
/* 267 */       element = (ReaderWriter)this.waitingList.get(index);
/*     */     } else {
/* 270 */       element = new ReaderWriter(WRITE);
/* 271 */       this.waitingList.add((E)element);
/*     */     } 
/* 275 */     if (element.granted == true && element.lockType == READ)
/*     */       try {
/* 278 */         if (!upgrade(waitTime))
/* 279 */           return false; 
/* 281 */       } catch (LockNotHeld e) {
/* 282 */         return false;
/*     */       }  
/* 289 */     if (element.lockCount > 0) {
/* 290 */       element.lockCount++;
/* 291 */       return true;
/*     */     } 
/* 294 */     long startTime = System.currentTimeMillis();
/* 295 */     long endTime = waitTime + startTime;
/*     */     do {
/* 300 */       index = this.waitingList.findMe();
/* 303 */       if (index == 0) {
/* 304 */         element.lockCount++;
/* 305 */         element.granted = true;
/* 306 */         return true;
/*     */       } 
/* 313 */       if (waitTime == 0) {
/* 314 */         this.waitingList.remove(element);
/* 315 */         return false;
/*     */       } 
/*     */       try {
/* 321 */         if (waitTime < 0) {
/* 322 */           wait();
/*     */         } else {
/* 325 */           long delta = endTime - System.currentTimeMillis();
/* 327 */           if (delta > 0L)
/* 327 */             wait(delta); 
/*     */         } 
/* 329 */       } catch (InterruptedException e) {
/* 332 */         System.err.println(element.key.getName() + " : interrupted while waiting for a WRITE lock!");
/*     */       } 
/* 336 */     } while (waitTime < 0 || endTime > System.currentTimeMillis());
/* 339 */     this.waitingList.remove(element);
/* 342 */     notifyAll();
/* 345 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized boolean forWriting() throws UpgradeNotAllowed {
/* 360 */     return forWriting(-1);
/*     */   }
/*     */   
/*     */   public synchronized boolean upgrade(int waitTime) throws UpgradeNotAllowed, LockNotHeld {
/* 384 */     if (!this.allowUpgrades)
/* 385 */       throw new UpgradeNotAllowed(this); 
/* 388 */     int index = this.waitingList.findMe();
/* 390 */     if (index == NOT_FOUND)
/* 391 */       throw new LockNotHeld(this); 
/* 395 */     ReaderWriter element = (ReaderWriter)this.waitingList.get(index);
/* 397 */     if (element.lockType == WRITE)
/* 398 */       return true; 
/* 401 */     int lastGranted = this.waitingList.indexOfLastGranted();
/* 405 */     if (lastGranted == NOT_FOUND)
/* 406 */       throw new LockNotHeld(this); 
/* 410 */     if (index != lastGranted) {
/* 411 */       this.waitingList.remove(index);
/* 412 */       ListIterator iter = this.waitingList.listIterator(lastGranted);
/* 413 */       iter.add((E)element);
/*     */     } 
/* 420 */     element.lockType = WRITE;
/* 422 */     long startTime = System.currentTimeMillis();
/* 423 */     long endTime = waitTime + startTime;
/*     */     do {
/* 426 */       index = this.waitingList.findMe();
/* 428 */       if (index == 0)
/* 429 */         return true; 
/* 435 */       if (waitTime == 0) {
/* 438 */         element.lockType = READ;
/* 442 */         return false;
/*     */       } 
/*     */       try {
/* 448 */         if (waitTime < 0) {
/* 449 */           wait();
/*     */         } else {
/* 451 */           long delta = endTime - System.currentTimeMillis();
/* 453 */           if (delta > 0L)
/* 453 */             wait(delta); 
/*     */         } 
/* 455 */       } catch (InterruptedException e) {
/* 458 */         System.err.println(element.key.getName() + " : interrupted while waiting to UPGRADE lock!");
/*     */       } 
/* 462 */     } while (waitTime < 0 || endTime > System.currentTimeMillis());
/* 465 */     element.lockType = READ;
/* 469 */     notifyAll();
/* 472 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized boolean upgrade() throws UpgradeNotAllowed, LockNotHeld {
/* 488 */     return upgrade(-1);
/*     */   }
/*     */   
/*     */   public synchronized boolean downgrade() throws LockNotHeld {
/* 506 */     int index = this.waitingList.findMe();
/* 508 */     if (index == NOT_FOUND)
/* 509 */       throw new LockNotHeld(this); 
/* 512 */     ReaderWriter e = (ReaderWriter)this.waitingList.get(index);
/* 515 */     if (e.lockType == WRITE) {
/* 516 */       e.lockType = READ;
/* 517 */       notifyAll();
/*     */     } 
/* 520 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized void release() throws LockNotHeld {
/* 537 */     int index = this.waitingList.findMe();
/* 539 */     if (index == NOT_FOUND)
/* 540 */       throw new LockNotHeld(this); 
/* 543 */     ReaderWriter e = (ReaderWriter)this.waitingList.get(index);
/* 547 */     if (--e.lockCount == 0) {
/* 548 */       this.waitingList.remove(index);
/* 549 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public class UpgradeNotAllowed extends RuntimeException {
/*     */     private final RWLock this$0;
/*     */     
/*     */     public UpgradeNotAllowed(RWLock this$0) {
/* 557 */       this.this$0 = this$0;
/*     */     }
/*     */   }
/*     */   
/*     */   public class LockNotHeld extends RuntimeException {
/*     */     private final RWLock this$0;
/*     */     
/*     */     public LockNotHeld(RWLock this$0) {
/* 564 */       this.this$0 = this$0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\RWLock.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */