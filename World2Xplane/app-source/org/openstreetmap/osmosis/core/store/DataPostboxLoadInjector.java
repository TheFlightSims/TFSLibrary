/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.openstreetmap.osmosis.core.buffer.v0_6.EntityBuffer;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.CommonEntityData;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Node;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.OsmUser;
/*    */ import org.openstreetmap.osmosis.core.misc.v0_6.NullWriter;
/*    */ import org.openstreetmap.osmosis.core.progress.v0_6.EntityProgressLogger;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ 
/*    */ public final class DataPostboxLoadInjector implements Runnable {
/*    */   private EntityBuffer buffer;
/*    */   
/*    */   private EntityProgressLogger progressLogger;
/*    */   
/*    */   private NullWriter nullWriter;
/*    */   
/*    */   public static void main(String[] args) {
/* 33 */     (new DataPostboxLoadInjector()).run();
/*    */   }
/*    */   
/*    */   private DataPostboxLoadInjector() {
/* 38 */     this.buffer = new EntityBuffer(10000);
/* 39 */     this.progressLogger = new EntityProgressLogger(5000, null);
/* 40 */     this.buffer.setSink((Sink)this.progressLogger);
/* 41 */     this.nullWriter = new NullWriter();
/* 42 */     this.progressLogger.setSink((Sink)this.nullWriter);
/*    */   }
/*    */   
/*    */   public void run() {
/* 53 */     Thread t1 = new Thread(new Writer(), "input");
/* 54 */     Thread t2 = new Thread((Runnable)this.buffer, "output");
/* 56 */     t1.start();
/* 57 */     t2.start();
/*    */     try {
/* 60 */       t1.join();
/* 61 */     } catch (InterruptedException e) {
/* 62 */       e.printStackTrace();
/*    */     } 
/*    */     try {
/* 66 */       t2.join();
/* 67 */     } catch (InterruptedException e) {
/* 68 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private class Writer implements Runnable {
/* 77 */     private Node node = new Node(new CommonEntityData(1L, 2, new Date(), OsmUser.NONE, 3L), 10.0D, 10.0D);
/*    */     
/* 78 */     private NodeContainer nodeContainer = new NodeContainer(this.node);
/*    */     
/*    */     public void run() {
/*    */       while (true)
/* 83 */         DataPostboxLoadInjector.this.buffer.process((EntityContainer)this.nodeContainer); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\DataPostboxLoadInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */