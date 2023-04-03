/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PropertySource;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public final class RIFRegistry {
/*     */   private static final String MODE_NAME = "rendered";
/*     */   
/*     */   public static void register(OperationRegistry registry, String operationName, String productName, RenderedImageFactory rif) {
/*  65 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  68 */     registry.registerFactory("rendered", operationName, productName, rif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String operationName, String productName, RenderedImageFactory rif) {
/*  96 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  99 */     registry.unregisterFactory("rendered", operationName, productName, rif);
/*     */   }
/*     */   
/*     */   public static void setPreference(OperationRegistry registry, String operationName, String productName, RenderedImageFactory preferredRIF, RenderedImageFactory otherRIF) {
/* 130 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 133 */     registry.setFactoryPreference("rendered", operationName, productName, preferredRIF, otherRIF);
/*     */   }
/*     */   
/*     */   public static void unsetPreference(OperationRegistry registry, String operationName, String productName, RenderedImageFactory preferredRIF, RenderedImageFactory otherRIF) {
/* 165 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 168 */     registry.unsetFactoryPreference("rendered", operationName, productName, preferredRIF, otherRIF);
/*     */   }
/*     */   
/*     */   public static void clearPreferences(OperationRegistry registry, String operationName, String productName) {
/* 193 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 196 */     registry.clearFactoryPreferences("rendered", operationName, productName);
/*     */   }
/*     */   
/*     */   public static List getOrderedList(OperationRegistry registry, String operationName, String productName) {
/* 226 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 229 */     return registry.getOrderedFactoryList("rendered", operationName, productName);
/*     */   }
/*     */   
/*     */   public static Iterator getIterator(OperationRegistry registry, String operationName) {
/* 260 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 263 */     return registry.getFactoryIterator("rendered", operationName);
/*     */   }
/*     */   
/*     */   public static RenderedImageFactory get(OperationRegistry registry, String operationName) {
/* 289 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 292 */     return (RenderedImageFactory)registry.getFactory("rendered", operationName);
/*     */   }
/*     */   
/*     */   public static RenderedImage create(OperationRegistry registry, String operationName, ParameterBlock paramBlock, RenderingHints renderHints) {
/* 327 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 330 */     Object[] args = { paramBlock, renderHints };
/* 332 */     return (RenderedImage)registry.invokeFactory("rendered", operationName, args);
/*     */   }
/*     */   
/*     */   public static PropertySource getPropertySource(RenderedOp op) {
/* 352 */     if (op == null)
/* 353 */       throw new IllegalArgumentException("op - " + JaiI18N.getString("Generic0")); 
/* 356 */     return op.getRegistry().getPropertySource((OperationNode)op);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */