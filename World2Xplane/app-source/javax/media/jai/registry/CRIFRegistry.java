/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PropertySource;
/*     */ import javax.media.jai.RenderableOp;
/*     */ 
/*     */ public final class CRIFRegistry {
/*     */   private static final String MODE_NAME = "renderable";
/*     */   
/*     */   public static void register(OperationRegistry registry, String operationName, ContextualRenderedImageFactory crif) {
/*  62 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  65 */     registry.registerFactory("renderable", operationName, null, crif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String operationName, ContextualRenderedImageFactory crif) {
/*  91 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  94 */     registry.unregisterFactory("renderable", operationName, null, crif);
/*     */   }
/*     */   
/*     */   public static ContextualRenderedImageFactory get(OperationRegistry registry, String operationName) {
/* 117 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 120 */     return (ContextualRenderedImageFactory)registry.getFactory("renderable", operationName);
/*     */   }
/*     */   
/*     */   public static RenderedImage create(OperationRegistry registry, String operationName, RenderContext context, ParameterBlock paramBlock) {
/* 149 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 152 */     Object[] args = { context, paramBlock };
/* 154 */     return (RenderedImage)registry.invokeFactory("renderable", operationName, args);
/*     */   }
/*     */   
/*     */   public static PropertySource getPropertySource(RenderableOp op) {
/* 174 */     if (op == null)
/* 175 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("Generic0")); 
/* 178 */     return op.getRegistry().getPropertySource((OperationNode)op);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\CRIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */