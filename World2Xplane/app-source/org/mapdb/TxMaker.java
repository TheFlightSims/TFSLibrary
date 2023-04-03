/*     */ package org.mapdb;
/*     */ 
/*     */ public class TxMaker {
/*  28 */   protected static final Object DELETED = new Object();
/*     */   
/*     */   private final boolean txSnapshotsEnabled;
/*     */   
/*     */   private final boolean strictDBGet;
/*     */   
/*     */   protected Engine engine;
/*     */   
/*     */   public TxMaker(Engine engine) {
/*  36 */     this(engine, false, false);
/*     */   }
/*     */   
/*     */   public TxMaker(Engine engine, boolean strictDBGet, boolean txSnapshotsEnabled) {
/*  40 */     if (engine == null)
/*  40 */       throw new IllegalArgumentException(); 
/*  41 */     if (!engine.canSnapshot())
/*  42 */       throw new IllegalArgumentException("Snapshot must be enabled for TxMaker"); 
/*  43 */     if (engine.isReadOnly())
/*  44 */       throw new IllegalArgumentException("TxMaker can not be used with read-only Engine"); 
/*  45 */     this.engine = engine;
/*  46 */     this.strictDBGet = strictDBGet;
/*  47 */     this.txSnapshotsEnabled = txSnapshotsEnabled;
/*     */   }
/*     */   
/*     */   public DB makeTx() {
/*  52 */     Engine snapshot = this.engine.snapshot();
/*  53 */     if (this.txSnapshotsEnabled)
/*  54 */       snapshot = new TxEngine(snapshot, false); 
/*  55 */     return new DB(snapshot, this.strictDBGet, false);
/*     */   }
/*     */   
/*     */   public void close() {
/*  59 */     this.engine.close();
/*  60 */     this.engine = null;
/*     */   }
/*     */   
/*     */   public void execute(TxBlock txBlock) {
/*     */     while (true) {
/*  71 */       DB tx = makeTx();
/*     */       try {
/*  73 */         txBlock.tx(tx);
/*  74 */         if (!tx.isClosed())
/*  75 */           tx.commit(); 
/*     */         return;
/*  77 */       } catch (TxRollbackException e) {
/*  79 */         if (!tx.isClosed())
/*  79 */           tx.close(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> A execute(Fun.Function1<A, DB> txBlock) {
/*     */     while (true) {
/*  94 */       DB tx = makeTx();
/*     */       try {
/*  96 */         A a = txBlock.run(tx);
/*  97 */         if (!tx.isClosed())
/*  98 */           tx.commit(); 
/*  99 */         return a;
/* 100 */       } catch (TxRollbackException e) {
/* 102 */         if (!tx.isClosed())
/* 102 */           tx.close(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\TxMaker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */