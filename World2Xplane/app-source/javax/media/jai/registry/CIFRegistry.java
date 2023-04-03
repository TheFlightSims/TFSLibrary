/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.media.jai.CollectionImage;
/*     */ import javax.media.jai.CollectionImageFactory;
/*     */ import javax.media.jai.CollectionOp;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PropertySource;
/*     */ 
/*     */ public final class CIFRegistry {
/*     */   private static final String MODE_NAME = "collection";
/*     */   
/*     */   public static void register(OperationRegistry registry, String operationName, String productName, CollectionImageFactory cif) {
/*  65 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  68 */     registry.registerFactory("collection", operationName, productName, cif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String operationName, String productName, CollectionImageFactory cif) {
/*  96 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  99 */     registry.unregisterFactory("collection", operationName, productName, cif);
/*     */   }
/*     */   
/*     */   public static void setPreference(OperationRegistry registry, String operationName, String productName, CollectionImageFactory preferredCIF, CollectionImageFactory otherCIF) {
/* 130 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 133 */     registry.setFactoryPreference("collection", operationName, productName, preferredCIF, otherCIF);
/*     */   }
/*     */   
/*     */   public static void unsetPreference(OperationRegistry registry, String operationName, String productName, CollectionImageFactory preferredCIF, CollectionImageFactory otherCIF) {
/* 165 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 168 */     registry.unsetFactoryPreference("collection", operationName, productName, preferredCIF, otherCIF);
/*     */   }
/*     */   
/*     */   public static void clearPreferences(OperationRegistry registry, String operationName, String productName) {
/* 193 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 196 */     registry.clearFactoryPreferences("collection", operationName, productName);
/*     */   }
/*     */   
/*     */   public static List getOrderedList(OperationRegistry registry, String operationName, String productName) {
/* 226 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 229 */     return registry.getOrderedFactoryList("collection", operationName, productName);
/*     */   }
/*     */   
/*     */   public static Iterator getIterator(OperationRegistry registry, String operationName) {
/* 260 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 263 */     return registry.getFactoryIterator("collection", operationName);
/*     */   }
/*     */   
/*     */   public static CollectionImageFactory get(OperationRegistry registry, String operationName) {
/* 289 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 292 */     return (CollectionImageFactory)registry.getFactory("collection", operationName);
/*     */   }
/*     */   
/*     */   public static CollectionImage create(OperationRegistry registry, String operationName, ParameterBlock paramBlock, RenderingHints renderHints) {
/* 327 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 330 */     Object[] args = { paramBlock, renderHints };
/* 332 */     return (CollectionImage)registry.invokeFactory("collection", operationName, args);
/*     */   }
/*     */   
/*     */   public static PropertySource getPropertySource(CollectionOp op) {
/* 354 */     if (op == null)
/* 355 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("Generic0")); 
/* 358 */     if (op.isRenderable())
/* 359 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("CIFRegistry0")); 
/* 362 */     return op.getRegistry().getPropertySource((OperationNode)op);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\CIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */