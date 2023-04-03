/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.net.InetAddress;
/*     */ import java.rmi.Naming;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.remote.RemoteImagingException;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class RenderableRMIServerProxy implements RenderableImage {
/*     */   private String serverName;
/*     */   
/*     */   private String operationName;
/*     */   
/*     */   private ParameterBlock paramBlock;
/*     */   
/*     */   private ImageServer imageServer;
/*     */   
/*     */   public Long id;
/*     */   
/*  52 */   private static final Class NULL_PROPERTY_CLASS = JAIRMIImageServer.NULL_PROPERTY.getClass();
/*     */   
/*     */   private ImagingListener listener;
/*     */   
/*     */   public RenderableRMIServerProxy(String serverName, String operationName, ParameterBlock paramBlock, Long opID) {
/*  68 */     this.serverName = serverName;
/*  69 */     this.operationName = operationName;
/*  70 */     this.paramBlock = paramBlock;
/*  71 */     this.imageServer = getImageServer(serverName);
/*  72 */     this.id = opID;
/*  73 */     this.listener = ImageUtil.getImagingListener((RenderingHints)null);
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) throws RemoteImagingException {
/*     */     try {
/* 100 */       Object property = this.imageServer.getProperty(this.id, name);
/* 101 */       if (NULL_PROPERTY_CLASS.isInstance(property))
/* 102 */         property = Image.UndefinedProperty; 
/* 104 */       return property;
/* 105 */     } catch (RemoteException re) {
/* 106 */       String message = JaiI18N.getString("JAIRMICRIF7");
/* 107 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 112 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() throws RemoteImagingException {
/*     */     try {
/* 121 */       return this.imageServer.getPropertyNames(this.id);
/* 122 */     } catch (RemoteException re) {
/* 123 */       String message = JaiI18N.getString("JAIRMICRIF8");
/* 124 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 129 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDynamic() throws RemoteImagingException {
/*     */     try {
/* 144 */       return this.imageServer.isDynamic(this.id);
/* 145 */     } catch (RemoteException re) {
/* 146 */       String message = JaiI18N.getString("JAIRMICRIF9");
/* 147 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 152 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getWidth() throws RemoteImagingException {
/*     */     try {
/* 164 */       return this.imageServer.getRenderableWidth(this.id);
/* 165 */     } catch (RemoteException re) {
/* 166 */       String message = JaiI18N.getString("RenderableRMIServerProxy0");
/* 167 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 172 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getHeight() throws RemoteImagingException {
/*     */     try {
/* 183 */       return this.imageServer.getRenderableHeight(this.id);
/* 184 */     } catch (RemoteException re) {
/* 185 */       String message = JaiI18N.getString("RenderableRMIServerProxy0");
/* 186 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 191 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getMinX() throws RemoteImagingException {
/*     */     try {
/* 201 */       return this.imageServer.getRenderableMinX(this.id);
/* 202 */     } catch (RemoteException re) {
/* 203 */       String message = JaiI18N.getString("RenderableRMIServerProxy1");
/* 204 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 209 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getMinY() throws RemoteImagingException {
/*     */     try {
/* 219 */       return this.imageServer.getRenderableMinY(this.id);
/* 220 */     } catch (RemoteException re) {
/* 221 */       String message = JaiI18N.getString("RenderableRMIServerProxy1");
/* 222 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 227 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Long getRMIID() {
/* 235 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getServerName() {
/* 242 */     return this.serverName;
/*     */   }
/*     */   
/*     */   public String getOperationName() {
/* 249 */     return this.operationName;
/*     */   }
/*     */   
/*     */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) throws RemoteImagingException {
/* 282 */     SerializableState ss = SerializerFactory.getState(hints, null);
/*     */     try {
/* 285 */       return this.imageServer.createScaledRendering(this.id, w, h, ss);
/* 286 */     } catch (RemoteException re) {
/* 287 */       String message = JaiI18N.getString("RMIServerProxy10");
/* 288 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 293 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RenderedImage createDefaultRendering() throws RemoteImagingException {
/*     */     try {
/* 309 */       return this.imageServer.createDefaultRendering(this.id);
/* 310 */     } catch (RemoteException re) {
/* 311 */       String message = JaiI18N.getString("RMIServerProxy10");
/* 312 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 317 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext renderContext) throws RemoteImagingException {
/* 338 */     SerializableState ss = SerializerFactory.getState(renderContext, null);
/*     */     try {
/* 340 */       return this.imageServer.createRendering(this.id, ss);
/* 341 */     } catch (RemoteException re) {
/* 342 */       String message = JaiI18N.getString("RMIServerProxy10");
/* 343 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/* 348 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected synchronized ImageServer getImageServer(String serverName) {
/* 369 */     if (this.imageServer == null) {
/* 371 */       if (serverName == null)
/*     */         try {
/* 373 */           serverName = InetAddress.getLocalHost().getHostAddress();
/* 374 */         } catch (Exception e) {
/* 375 */           String message = JaiI18N.getString("RMIServerProxy11");
/* 376 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */         }  
/* 384 */       String serviceName = new String("rmi://" + serverName + "/" + "JAIRMIRemoteServer1.1");
/* 389 */       this.imageServer = null;
/*     */       try {
/* 391 */         this.imageServer = (ImageServer)Naming.lookup(serviceName);
/* 392 */       } catch (Exception e) {
/* 393 */         String message = JaiI18N.getString("RMIServerProxy12");
/* 394 */         this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */       } 
/*     */     } 
/* 401 */     return this.imageServer;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderableRMIServerProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */