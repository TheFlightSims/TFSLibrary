/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.remote.RemoteRIF;
/*     */ import javax.media.jai.remote.RemoteRenderedImage;
/*     */ 
/*     */ public final class RemoteRIFRegistry {
/*     */   private static final String MODE_NAME = "remoteRendered";
/*     */   
/*     */   public static void register(OperationRegistry registry, String protocolName, RemoteRIF rrif) {
/*  57 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  60 */     registry.registerFactory("remoteRendered", protocolName, null, rrif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String protocolName, RemoteRIF rrif) {
/*  89 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  92 */     registry.unregisterFactory("remoteRendered", protocolName, null, rrif);
/*     */   }
/*     */   
/*     */   public static RemoteRIF get(OperationRegistry registry, String protocolName) {
/* 113 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 116 */     return (RemoteRIF)registry.getFactory("remoteRendered", protocolName);
/*     */   }
/*     */   
/*     */   public static RemoteRenderedImage create(OperationRegistry registry, String protocolName, String serverName, String operationName, ParameterBlock paramBlock, RenderingHints renderHints) {
/* 162 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 165 */     Object[] args = { serverName, operationName, paramBlock, renderHints };
/* 167 */     return (RemoteRenderedImage)registry.invokeFactory("remoteRendered", protocolName, args);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RemoteRIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */