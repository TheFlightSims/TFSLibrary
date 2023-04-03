/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ 
/*    */ public final class BlockContext$ {
/*    */   public static final BlockContext$ MODULE$;
/*    */   
/*    */   private final ThreadLocal<BlockContext> contextLocal;
/*    */   
/*    */   private BlockContext$() {
/* 51 */     MODULE$ = this;
/* 56 */     this.contextLocal = new ThreadLocal<BlockContext>();
/*    */   }
/*    */   
/*    */   private ThreadLocal<BlockContext> contextLocal() {
/* 56 */     return this.contextLocal;
/*    */   }
/*    */   
/*    */   public BlockContext current() {
/* 59 */     BlockContext blockContext2, blockContext1 = contextLocal().get();
/* 60 */     if (blockContext1 == null) {
/*    */       BlockContext blockContext;
/* 60 */       Thread thread = Thread.currentThread();
/* 61 */       if (thread instanceof BlockContext) {
/* 61 */         blockContext = (BlockContext)thread;
/*    */       } else {
/* 62 */         blockContext = BlockContext.DefaultBlockContext$.MODULE$;
/*    */       } 
/*    */       blockContext2 = blockContext;
/*    */     } else {
/* 64 */       blockContext2 = blockContext1;
/*    */     } 
/*    */     return blockContext2;
/*    */   }
/*    */   
/*    */   public <T> T withBlockContext(BlockContext blockContext, Function0 body) {
/* 69 */     BlockContext old = contextLocal().get();
/*    */     try {
/* 71 */       contextLocal().set(blockContext);
/* 72 */       return (T)body.apply();
/*    */     } finally {
/* 74 */       contextLocal().set(old);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\BlockContext$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */