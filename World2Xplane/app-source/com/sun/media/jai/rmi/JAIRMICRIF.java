/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.rmi.Naming;
/*     */ import java.rmi.NotBoundException;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.PropertyChangeEventJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.remote.NegotiableCapabilitySet;
/*     */ import javax.media.jai.remote.PlanarImageServerProxy;
/*     */ import javax.media.jai.remote.RemoteCRIF;
/*     */ import javax.media.jai.remote.RemoteImagingException;
/*     */ import javax.media.jai.remote.RemoteRenderableOp;
/*     */ import javax.media.jai.remote.RemoteRenderedImage;
/*     */ import javax.media.jai.remote.RemoteRenderedOp;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class JAIRMICRIF implements RemoteCRIF {
/*     */   public RenderContext mapRenderContext(String serverName, String operationName, int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) throws RemoteImagingException {
/*  89 */     RemoteRenderableOp rrop = (RemoteRenderableOp)image;
/*  90 */     RenderableRMIServerProxy rmisp = createProxy(rrop);
/*  92 */     SerializableState rcs = SerializerFactory.getState(renderContext, null);
/*     */     try {
/*  97 */       SerializableState rcpOut = rmisp.getImageServer(serverName).mapRenderContext(i, rmisp.getRMIID(), operationName, rcs);
/* 103 */     } catch (RemoteException re) {
/* 104 */       String message = JaiI18N.getString("JAIRMICRIF5");
/* 105 */       sendExceptionToListener(renderContext, message, re);
/*     */     } 
/* 109 */     return (RenderContext)rcs.getObject();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(String serverName, String operationName, ParameterBlock paramBlock) throws RemoteImagingException {
/* 133 */     SerializableState bounds = null;
/* 136 */     RemoteRenderableOp original = new RemoteRenderableOp("jairmi", serverName, operationName, paramBlock);
/* 143 */     RenderableRMIServerProxy rmisp = createProxy(original);
/*     */     try {
/* 146 */       bounds = rmisp.getImageServer(serverName).getBounds2D(rmisp.getRMIID(), operationName);
/* 149 */     } catch (RemoteException e) {
/* 150 */       String message = JaiI18N.getString("JAIRMICRIF6");
/* 151 */       sendExceptionToListener(null, message, e);
/*     */     } 
/* 155 */     return (Rectangle2D.Float)bounds.getObject();
/*     */   }
/*     */   
/*     */   public Object getProperty(String serverName, String operationName, ParameterBlock paramBlock, String name) throws RemoteImagingException {
/* 173 */     ParameterBlock pb = null;
/* 174 */     if (paramBlock == null) {
/* 175 */       pb = new ParameterBlock();
/*     */     } else {
/* 177 */       pb = (ParameterBlock)paramBlock.clone();
/*     */     } 
/* 181 */     RemoteRenderableOp original = new RemoteRenderableOp("jairmi", serverName, operationName, paramBlock);
/* 188 */     RenderableRMIServerProxy rmisp = createProxy(original);
/*     */     try {
/* 191 */       return rmisp.getProperty(name);
/* 192 */     } catch (Exception e) {
/* 193 */       String message = JaiI18N.getString("JAIRMICRIF7");
/* 194 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/* 198 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String serverName, String operationName) throws RemoteImagingException {
/* 208 */     ImageServer remoteImage = getImageServer(serverName);
/*     */     try {
/* 210 */       return remoteImage.getPropertyNames(operationName);
/* 211 */     } catch (RemoteException e) {
/* 213 */       String message = JaiI18N.getString("JAIRMICRIF8");
/* 214 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/* 218 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ImageServer getImageServer(String serverName) {
/* 223 */     if (serverName == null)
/*     */       try {
/* 225 */         serverName = InetAddress.getLocalHost().getHostAddress();
/* 226 */       } catch (UnknownHostException e) {
/* 227 */         String message = JaiI18N.getString("RMIServerProxy11");
/* 228 */         sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/*     */       }  
/* 235 */     String serviceName = new String("rmi://" + serverName + "/" + "JAIRMIRemoteServer1.1");
/*     */     try {
/* 241 */       return (ImageServer)Naming.lookup(serviceName);
/* 242 */     } catch (NotBoundException e) {
/* 243 */       String message = JaiI18N.getString("RMIServerProxy12");
/* 244 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/* 247 */     } catch (MalformedURLException e) {
/* 248 */       String message = JaiI18N.getString("RMIServerProxy12");
/* 249 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/* 251 */     } catch (RemoteException e) {
/* 252 */       String message = JaiI18N.getString("RMIServerProxy12");
/* 253 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/*     */     } 
/* 257 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDynamic(String serverName, String operationName) throws RemoteImagingException {
/* 271 */     ImageServer remoteImage = getImageServer(serverName);
/*     */     try {
/* 273 */       return remoteImage.isDynamic(operationName);
/* 274 */     } catch (RemoteException e) {
/* 275 */       String message = JaiI18N.getString("JAIRMICRIF9");
/* 276 */       sendExceptionToListener(null, message, (Exception)new RemoteImagingException(message, e));
/* 280 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RemoteRenderedImage create(String serverName, String operationName, ParameterBlock paramBlock, RenderingHints hints) throws RemoteImagingException {
/* 326 */     RMIServerProxy rmisp = new RMIServerProxy(serverName, operationName, paramBlock, hints);
/* 333 */     boolean cbr = rmisp.canBeRendered();
/* 335 */     if (!cbr)
/* 336 */       return null; 
/* 338 */     return (RemoteRenderedImage)rmisp;
/*     */   }
/*     */   
/*     */   public RemoteRenderedImage create(PlanarImageServerProxy oldRendering, OperationNode node, PropertyChangeEventJAI event) throws RemoteImagingException {
/*     */     RMIServerProxy rmisp;
/* 383 */     if (!(node instanceof RemoteRenderedOp))
/* 384 */       return null; 
/* 386 */     String propName = event.getPropertyName();
/* 388 */     if (propName.equals("servername")) {
/* 389 */       rmisp = new RMIServerProxy(oldRendering, node, (String)event.getNewValue());
/*     */     } else {
/* 392 */       if (propName.equals("operationregistry") || propName.equals("protocolname") || propName.equals("protocolandservername"))
/* 396 */         return create(((RemoteRenderedOp)node).getServerName(), node.getOperationName(), node.getParameterBlock(), node.getRenderingHints()); 
/* 402 */       rmisp = new RMIServerProxy(oldRendering, node, event);
/*     */     } 
/* 405 */     return (RemoteRenderedImage)rmisp;
/*     */   }
/*     */   
/*     */   public RemoteRenderedImage create(String serverName, String operationName, RenderContext renderContext, ParameterBlock paramBlock) throws RemoteImagingException {
/* 434 */     RMIServerProxy rmisp = new RMIServerProxy(serverName, operationName, paramBlock, renderContext, true);
/* 442 */     Long renderingID = rmisp.getRenderingID();
/* 446 */     return (RemoteRenderedImage)new RMIServerProxy(serverName + "::" + renderingID, paramBlock, operationName, renderContext.getRenderingHints());
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet getClientCapabilities() {
/* 457 */     OperationRegistry registry = JAI.getDefaultInstance().getOperationRegistry();
/* 459 */     String modeName = "tileDecoder";
/* 460 */     String[] descriptorNames = registry.getDescriptorNames(modeName);
/* 461 */     TileDecoderFactory tdf = null;
/* 467 */     NegotiableCapabilitySet capabilities = new NegotiableCapabilitySet(false);
/* 476 */     for (int i = 0; i < descriptorNames.length; i++) {
/* 478 */       Iterator it = registry.getFactoryIterator(modeName, descriptorNames[i]);
/* 479 */       while (it.hasNext()) {
/* 480 */         tdf = it.next();
/* 481 */         capabilities.add(tdf.getDecodeCapability());
/*     */       } 
/*     */     } 
/* 485 */     return capabilities;
/*     */   }
/*     */   
/*     */   private RenderableRMIServerProxy createProxy(RemoteRenderableOp rop) {
/* 497 */     ParameterBlock oldPB = rop.getParameterBlock();
/* 498 */     ParameterBlock newPB = (ParameterBlock)oldPB.clone();
/* 499 */     Vector sources = oldPB.getSources();
/* 500 */     newPB.removeSources();
/* 503 */     ImageServer remoteImage = getImageServer(rop.getServerName());
/* 504 */     ImagingListener listener = ImageUtil.getImagingListener(rop.getRenderingHints());
/* 506 */     Long opID = new Long(0L);
/*     */     try {
/* 508 */       opID = remoteImage.getRemoteID();
/* 509 */       remoteImage.createRenderableOp(opID, rop.getOperationName(), newPB);
/* 512 */     } catch (RemoteException e) {
/* 513 */       String message = JaiI18N.getString("RMIServerProxy8");
/* 514 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */     } 
/* 521 */     if (sources != null)
/* 523 */       for (int i = 0; i < sources.size(); i++) {
/* 525 */         Object source = sources.elementAt(i);
/* 527 */         if (source instanceof RemoteRenderedOp) {
/* 531 */           RMIServerProxy rmisp = (RMIServerProxy)((RemoteRenderedOp)source).getRendering();
/*     */           try {
/* 535 */             if (rmisp.getServerName().equalsIgnoreCase(rop.getServerName())) {
/* 540 */               remoteImage.setRenderedSource(opID, rmisp.getRMIID(), i);
/* 543 */               newPB.setSource(rmisp, i);
/*     */             } else {
/* 548 */               remoteImage.setRenderedSource(opID, rmisp.getRMIID(), rmisp.getServerName(), rmisp.getOperationName(), i);
/* 554 */               newPB.setSource(rmisp, i);
/*     */             } 
/* 556 */           } catch (RemoteException e) {
/* 557 */             String message = JaiI18N.getString("RMIServerProxy6");
/* 558 */             listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */           } 
/* 564 */         } else if (source instanceof RenderedOp) {
/* 570 */           PlanarImage planarImage = ((RenderedOp)source).getRendering();
/*     */           try {
/* 572 */             SerializableRenderedImage sri = new SerializableRenderedImage((RenderedImage)planarImage);
/* 574 */             remoteImage.setRenderedSource(opID, (RenderedImage)sri, i);
/* 575 */             newPB.setSource(sri, i);
/* 576 */           } catch (RemoteException e) {
/* 577 */             String message = JaiI18N.getString("RMIServerProxy6");
/* 578 */             listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */           } 
/* 584 */         } else if (source instanceof RenderedImage) {
/* 590 */           RenderedImage ri = (RenderedImage)source;
/*     */           try {
/* 592 */             SerializableRenderedImage sri = new SerializableRenderedImage(ri);
/* 594 */             remoteImage.setRenderedSource(opID, (RenderedImage)sri, i);
/* 595 */             newPB.setSource(sri, i);
/* 596 */           } catch (RemoteException e) {
/* 597 */             String message = JaiI18N.getString("RMIServerProxy6");
/* 598 */             listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */           } 
/* 604 */         } else if (source instanceof RemoteRenderableOp) {
/* 609 */           RenderableRMIServerProxy rrmisp = createProxy((RemoteRenderableOp)source);
/*     */           try {
/* 616 */             if (rrmisp.getServerName().equalsIgnoreCase(rop.getServerName())) {
/* 618 */               remoteImage.setRenderableSource(opID, rrmisp.getRMIID(), i);
/* 621 */               newPB.setSource(rrmisp, i);
/*     */             } else {
/* 626 */               remoteImage.setRenderableRMIServerProxyAsSource(opID, rrmisp.getRMIID(), rrmisp.getServerName(), rrmisp.getOperationName(), i);
/* 632 */               newPB.setSource(rrmisp, i);
/*     */             } 
/* 634 */           } catch (RemoteException e) {
/* 635 */             String message = JaiI18N.getString("RMIServerProxy6");
/* 636 */             listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */           } 
/* 642 */         } else if (source instanceof RenderableImage) {
/* 648 */           RenderableImage ri = (RenderableImage)source;
/*     */           try {
/* 650 */             SerializableRenderableImage sri = new SerializableRenderableImage(ri);
/* 652 */             remoteImage.setRenderableSource(opID, sri, i);
/* 653 */             newPB.setSource(sri, i);
/* 654 */           } catch (RemoteException e) {
/* 655 */             String message = JaiI18N.getString("RMIServerProxy6");
/* 656 */             listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */           } 
/*     */         } 
/*     */       }  
/* 668 */     RenderableRMIServerProxy finalRmisp = new RenderableRMIServerProxy(rop.getServerName(), rop.getOperationName(), newPB, opID);
/* 673 */     return finalRmisp;
/*     */   }
/*     */   
/*     */   private void sendExceptionToListener(RenderContext renderContext, String message, Exception e) {
/* 679 */     ImagingListener listener = ImageUtil.getImagingListener(renderContext);
/* 681 */     listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\JAIRMICRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */