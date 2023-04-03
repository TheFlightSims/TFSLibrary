/*     */ package org.openstreetmap.osmosis.core.tee.v0_6;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkChangeSource;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkMultiChangeSource;
/*     */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*     */ 
/*     */ public class ChangeTee implements ChangeSinkMultiChangeSource {
/*  33 */   private List<ProxyChangeSinkChangeSource> sinkList = new ArrayList<ProxyChangeSinkChangeSource>();
/*     */   
/*     */   public ChangeTee(int outputCount) {
/*  35 */     for (int i = 0; i < outputCount; i++)
/*  36 */       this.sinkList.add(new ProxyChangeSinkChangeSource()); 
/*     */   }
/*     */   
/*     */   public ChangeSource getChangeSource(int index) {
/*  45 */     if (index < 0 || index >= this.sinkList.size())
/*  46 */       throw new OsmosisRuntimeException("Source index " + index + " is in the range 0 to " + (this.sinkList.size() - 1) + "."); 
/*  50 */     return (ChangeSource)this.sinkList.get(index);
/*     */   }
/*     */   
/*     */   public int getChangeSourceCount() {
/*  58 */     return this.sinkList.size();
/*     */   }
/*     */   
/*     */   public void initialize(Map<String, Object> metaData) {
/*  66 */     for (ProxyChangeSinkChangeSource sink : this.sinkList)
/*  67 */       sink.initialize(metaData); 
/*     */   }
/*     */   
/*     */   public void process(ChangeContainer change) {
/*  76 */     for (ProxyChangeSinkChangeSource sink : this.sinkList) {
/*  79 */       change.getEntityContainer().getEntity().makeReadOnly();
/*  81 */       sink.process(change);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {
/*  90 */     for (ProxyChangeSinkChangeSource sink : this.sinkList)
/*  91 */       sink.complete(); 
/*     */   }
/*     */   
/*     */   public void release() {
/* 100 */     for (ProxyChangeSinkChangeSource sink : this.sinkList)
/* 101 */       sink.release(); 
/*     */   }
/*     */   
/*     */   private static class ProxyChangeSinkChangeSource implements ChangeSinkChangeSource {
/*     */     private ChangeSink changeSink;
/*     */     
/*     */     public void setChangeSink(ChangeSink changeSink) {
/* 127 */       this.changeSink = changeSink;
/*     */     }
/*     */     
/*     */     public void initialize(Map<String, Object> metaData) {
/* 135 */       this.changeSink.initialize(metaData);
/*     */     }
/*     */     
/*     */     public void process(ChangeContainer change) {
/* 143 */       this.changeSink.process(change);
/*     */     }
/*     */     
/*     */     public void complete() {
/* 151 */       this.changeSink.complete();
/*     */     }
/*     */     
/*     */     public void release() {
/* 159 */       this.changeSink.release();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\tee\v0_6\ChangeTee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */