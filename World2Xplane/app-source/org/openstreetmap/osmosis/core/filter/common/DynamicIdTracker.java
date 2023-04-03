/*     */ package org.openstreetmap.osmosis.core.filter.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class DynamicIdTracker implements IdTracker {
/*     */   static final int SEGMENT_SIZE = 1024;
/*     */   
/*  30 */   private List<DynamicIdTrackerSegment> segments = new ArrayList<DynamicIdTrackerSegment>();
/*     */   
/*     */   private int calculateOffset(long id) {
/*  38 */     int offset = (int)(id % 1024L);
/*  42 */     if (offset < 0)
/*  43 */       offset = 1024 + offset; 
/*  46 */     return offset;
/*     */   }
/*     */   
/*     */   private DynamicIdTrackerSegment getSegment(long base, boolean createIfMissing) {
/*  55 */     DynamicIdTrackerSegment segment = null;
/*  59 */     int intervalBegin = 0;
/*  60 */     int intervalEnd = this.segments.size();
/*     */     boolean searchComplete;
/*  61 */     for (searchComplete = false; !searchComplete; ) {
/*  67 */       int intervalSize = intervalEnd - intervalBegin;
/*  72 */       if (intervalSize == 0) {
/*  73 */         if (createIfMissing) {
/*  74 */           segment = new DynamicIdTrackerSegment(base);
/*  75 */           this.segments.add(intervalBegin, segment);
/*     */         } 
/*  78 */         searchComplete = true;
/*     */         continue;
/*     */       } 
/*  80 */       if (intervalSize >= 2) {
/*  84 */         int intervalMid = intervalSize / 2 + intervalBegin;
/*  88 */         DynamicIdTrackerSegment currentSegment = this.segments.get(intervalMid);
/*  89 */         long currentBase = currentSegment.getBase();
/*  90 */         if (currentBase == base) {
/*  91 */           segment = currentSegment;
/*  92 */           searchComplete = true;
/*     */           continue;
/*     */         } 
/*  93 */         if (currentBase < base) {
/*  94 */           intervalBegin = intervalMid + 1;
/*     */           continue;
/*     */         } 
/*  96 */         intervalEnd = intervalMid;
/*     */         continue;
/*     */       } 
/* 101 */       for (; intervalBegin < intervalEnd; intervalBegin++) {
/* 104 */         DynamicIdTrackerSegment currentSegment = this.segments.get(intervalBegin);
/* 105 */         long currentBase = currentSegment.getBase();
/* 107 */         if (currentBase == base) {
/* 108 */           segment = currentSegment;
/*     */           break;
/*     */         } 
/* 111 */         if (currentBase > base) {
/* 113 */           if (createIfMissing) {
/* 114 */             segment = new DynamicIdTrackerSegment(base);
/* 115 */             this.segments.add(intervalBegin, segment);
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/* 122 */       if (segment == null && createIfMissing) {
/* 123 */         segment = new DynamicIdTrackerSegment(base);
/* 124 */         this.segments.add(intervalBegin, segment);
/*     */       } 
/* 127 */       searchComplete = true;
/*     */     } 
/* 131 */     return segment;
/*     */   }
/*     */   
/*     */   public boolean get(long id) {
/* 144 */     int offset = calculateOffset(id);
/* 145 */     long base = id - offset;
/* 146 */     DynamicIdTrackerSegment segment = getSegment(base, false);
/* 148 */     if (segment != null)
/* 149 */       return segment.get(offset); 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public void set(long id) {
/* 165 */     int offset = calculateOffset(id);
/* 166 */     long base = id - offset;
/* 167 */     DynamicIdTrackerSegment segment = getSegment(base, true);
/* 169 */     segment.set(offset);
/*     */   }
/*     */   
/*     */   public void setAll(IdTracker idTracker) {
/* 178 */     for (Long id : idTracker)
/* 179 */       set(id.longValue()); 
/*     */   }
/*     */   
/*     */   public Iterator<Long> iterator() {
/* 189 */     return new SegmentIdIterator(this.segments.iterator());
/*     */   }
/*     */   
/*     */   private static class SegmentIdIterator implements Iterator<Long> {
/*     */     private Iterator<DynamicIdTrackerSegment> segments;
/*     */     
/*     */     private Iterator<Long> currentSegmentIds;
/*     */     
/*     */     private long currentSegmentBase;
/*     */     
/*     */     public SegmentIdIterator(Iterator<DynamicIdTrackerSegment> segments) {
/* 206 */       this.segments = segments;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       while (true) {
/* 216 */         if (this.currentSegmentIds == null)
/* 217 */           if (this.segments.hasNext()) {
/* 218 */             DynamicIdTrackerSegment segment = this.segments.next();
/* 220 */             this.currentSegmentIds = segment.iterator();
/* 221 */             this.currentSegmentBase = segment.getBase();
/*     */           } else {
/* 223 */             return false;
/*     */           }  
/* 227 */         if (this.currentSegmentIds.hasNext())
/* 228 */           return true; 
/* 230 */         this.currentSegmentIds = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Long next() {
/* 241 */       if (hasNext())
/* 242 */         return Long.valueOf(((Long)this.currentSegmentIds.next()).longValue() + this.currentSegmentBase); 
/* 245 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 255 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\DynamicIdTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */