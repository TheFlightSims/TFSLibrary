/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.CollectionImage;
/*     */ import javax.media.jai.CollectionOp;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PropertySource;
/*     */ import javax.media.jai.RenderableCollectionImageFactory;
/*     */ 
/*     */ public final class RCIFRegistry {
/*     */   private static final String MODE_NAME = "renderableCollection";
/*     */   
/*     */   public static void register(OperationRegistry registry, String operationName, RenderableCollectionImageFactory rcif) {
/*  61 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  64 */     registry.registerFactory("renderableCollection", operationName, null, rcif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String operationName, RenderableCollectionImageFactory rcif) {
/*  90 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  93 */     registry.unregisterFactory("renderableCollection", operationName, null, rcif);
/*     */   }
/*     */   
/*     */   public static RenderableCollectionImageFactory get(OperationRegistry registry, String operationName) {
/* 116 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 119 */     return (RenderableCollectionImageFactory)registry.getFactory("renderableCollection", operationName);
/*     */   }
/*     */   
/*     */   public static CollectionImage create(OperationRegistry registry, String operationName, ParameterBlock paramBlock) {
/* 145 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 148 */     Object[] args = { paramBlock };
/* 150 */     return (CollectionImage)registry.invokeFactory("renderableCollection", operationName, args);
/*     */   }
/*     */   
/*     */   public static PropertySource getPropertySource(CollectionOp op) {
/* 172 */     if (op == null)
/* 173 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("Generic0")); 
/* 176 */     if (!op.isRenderable())
/* 177 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("CIFRegistry1")); 
/* 180 */     return op.getRegistry().getPropertySource((OperationNode)op);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RCIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */