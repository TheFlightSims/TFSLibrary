/*     */ package org.openstreetmap.osmosis.core.filter.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.openstreetmap.osmosis.core.util.LongAsInt;
/*     */ 
/*     */ public class ListIdTracker implements IdTracker {
/*     */   private static final double LIST_SIZE_EXTENSION_FACTOR = 1.5D;
/*     */   
/*  52 */   int[] idList = new int[1];
/*     */   
/*  53 */   int idOffset = 0;
/*     */   
/*  54 */   private int maxIdAdded = Integer.MIN_VALUE;
/*     */   
/*     */   private boolean sorted = true;
/*     */   
/*     */   private void extendIdList() {
/*  66 */     int newListLength = (int)(this.idList.length * 1.5D);
/*  67 */     if (newListLength == this.idList.length)
/*  68 */       newListLength++; 
/*  71 */     int[] newIdList = new int[newListLength];
/*  73 */     System.arraycopy(this.idList, 0, newIdList, 0, this.idList.length);
/*  75 */     this.idList = newIdList;
/*     */   }
/*     */   
/*     */   private void ensureListIsSorted() {
/*  83 */     if (!this.sorted) {
/*  87 */       List<Integer> tmpList = new ArrayList<Integer>(this.idOffset);
/*     */       int i;
/*  89 */       for (i = 0; i < this.idOffset; i++)
/*  90 */         tmpList.add(Integer.valueOf(this.idList[i])); 
/*  93 */       Collections.sort(tmpList);
/*  95 */       int newIdOffset = 0;
/*  96 */       for (i = 0; i < this.idOffset; i++) {
/*  99 */         int nextValue = ((Integer)tmpList.get(i)).intValue();
/* 101 */         if (newIdOffset <= 0 || nextValue > this.idList[newIdOffset - 1])
/* 102 */           this.idList[newIdOffset++] = nextValue; 
/*     */       } 
/* 105 */       this.idOffset = newIdOffset;
/* 107 */       this.sorted = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(long id) {
/* 118 */     int integerId = LongAsInt.longToInt(id);
/* 121 */     if (this.idOffset >= this.idList.length)
/* 122 */       extendIdList(); 
/* 125 */     this.idList[this.idOffset++] = integerId;
/* 129 */     if (integerId < this.maxIdAdded) {
/* 130 */       this.sorted = false;
/*     */     } else {
/* 132 */       this.maxIdAdded = integerId;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean get(long id) {
/* 146 */     int integerId = LongAsInt.longToInt(id);
/* 150 */     ensureListIsSorted();
/* 154 */     int intervalBegin = 0;
/* 155 */     int intervalEnd = this.idOffset;
/* 156 */     boolean idFound = false;
/*     */     boolean searchComplete;
/* 157 */     for (searchComplete = false; !searchComplete; ) {
/* 161 */       int intervalSize = intervalEnd - intervalBegin;
/* 165 */       if (intervalSize >= 2) {
/* 170 */         int intervalMid = intervalSize / 2 + intervalBegin;
/* 174 */         int currentId = this.idList[intervalMid];
/* 175 */         if (currentId == integerId) {
/* 176 */           idFound = true;
/* 177 */           searchComplete = true;
/*     */           continue;
/*     */         } 
/* 178 */         if (currentId < integerId) {
/* 179 */           intervalBegin = intervalMid + 1;
/*     */           continue;
/*     */         } 
/* 181 */         intervalEnd = intervalMid;
/*     */         continue;
/*     */       } 
/* 186 */       for (int currentOffset = intervalBegin; currentOffset < intervalEnd; currentOffset++) {
/* 190 */         int currentId = this.idList[currentOffset];
/* 192 */         if (currentId == integerId) {
/* 193 */           idFound = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 198 */       searchComplete = true;
/*     */     } 
/* 202 */     return idFound;
/*     */   }
/*     */   
/*     */   public Iterator<Long> iterator() {
/* 213 */     ensureListIsSorted();
/* 215 */     return new IdIterator();
/*     */   }
/*     */   
/*     */   public void setAll(IdTracker idTracker) {
/* 224 */     for (Long id : idTracker)
/* 225 */       set(id.longValue()); 
/*     */   }
/*     */   
/*     */   private class IdIterator implements Iterator<Long> {
/* 244 */     private int iteratorOffset = 0;
/*     */     
/*     */     public boolean hasNext() {
/* 253 */       return (this.iteratorOffset < ListIdTracker.this.idOffset);
/*     */     }
/*     */     
/*     */     public Long next() {
/* 262 */       if (!hasNext())
/* 263 */         throw new NoSuchElementException(); 
/* 266 */       return Long.valueOf(ListIdTracker.this.idList[this.iteratorOffset++]);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 275 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\ListIdTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */