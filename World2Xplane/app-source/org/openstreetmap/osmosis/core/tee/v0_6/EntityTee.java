/*     */ package org.openstreetmap.osmosis.core.tee.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.SinkMultiSource;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*     */ 
/*     */ public class EntityTee implements SinkMultiSource {
/*  33 */   private List<ProxySinkSource> sinkList = new ArrayList<ProxySinkSource>();
/*     */   
/*     */   public EntityTee(int outputCount) {
/*  35 */     for (int i = 0; i < outputCount; i++)
/*  36 */       this.sinkList.add(new ProxySinkSource()); 
/*     */   }
/*     */   
/*     */   public Source getSource(int index) {
/*  45 */     if (index < 0 || index >= this.sinkList.size())
/*  46 */       throw new OsmosisRuntimeException("Source index " + index + " is in the range 0 to " + (this.sinkList.size() - 1) + "."); 
/*  50 */     return (Source)this.sinkList.get(index);
/*     */   }
/*     */   
/*     */   public int getSourceCount() {
/*  58 */     return this.sinkList.size();
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {
/*  66 */     for (ProxySinkSource sink : this.sinkList)
/*  67 */       sink.initialize(metaData); 
/*     */   }
/*     */   
/*     */   public void process(EntityContainer entityContainer) {
/*  76 */     for (ProxySinkSource sink : this.sinkList) {
/*  79 */       entityContainer.getEntity().makeReadOnly();
/*  81 */       sink.process(entityContainer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {
/*  90 */     for (ProxySinkSource sink : this.sinkList)
/*  91 */       sink.complete(); 
/*     */   }
/*     */   
/*     */   public void release() {
/* 100 */     for (ProxySinkSource sink : this.sinkList)
/* 101 */       sink.release(); 
/*     */   }
/*     */   
/*     */   private static class ProxySinkSource implements SinkSource {
/*     */     private Sink sink;
/*     */     
/*     */     public void setSink(Sink sink) {
/* 127 */       this.sink = sink;
/*     */     }
/*     */     
/*     */     public void initialize(Map<String, Object> metaData) {
/* 135 */       this.sink.initialize(metaData);
/*     */     }
/*     */     
/*     */     public void process(EntityContainer entityContainer) {
/* 143 */       this.sink.process(entityContainer);
/*     */     }
/*     */     
/*     */     public void complete() {
/* 151 */       this.sink.complete();
/*     */     }
/*     */     
/*     */     public void release() {
/* 159 */       this.sink.release();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\tee\v0_6\EntityTee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */