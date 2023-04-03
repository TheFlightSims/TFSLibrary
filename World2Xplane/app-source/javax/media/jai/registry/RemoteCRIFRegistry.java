/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.remote.RemoteCRIF;
/*     */ import javax.media.jai.remote.RemoteRenderedImage;
/*     */ 
/*     */ public final class RemoteCRIFRegistry {
/*     */   private static final String MODE_NAME = "remoteRenderable";
/*     */   
/*     */   public static void register(OperationRegistry registry, String protocolName, RemoteCRIF rcrif) {
/*  57 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  60 */     registry.registerFactory("remoteRenderable", protocolName, null, rcrif);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String protocolName, RemoteCRIF rcrif) {
/*  89 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  92 */     registry.unregisterFactory("remoteRenderable", protocolName, null, rcrif);
/*     */   }
/*     */   
/*     */   public static RemoteCRIF get(OperationRegistry registry, String protocolName) {
/* 113 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 116 */     return (RemoteCRIF)registry.getFactory("remoteRenderable", protocolName);
/*     */   }
/*     */   
/*     */   public static RemoteRenderedImage create(OperationRegistry registry, String protocolName, String serverName, String operationName, RenderContext renderContext, ParameterBlock paramBlock) {
/* 163 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 166 */     Object[] args = { serverName, operationName, renderContext, paramBlock };
/* 171 */     return (RemoteRenderedImage)registry.invokeFactory("remoteRenderable", protocolName, args);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RemoteCRIFRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */