/*      */ package com.sun.media.jai.rmi;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Image;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.net.InetAddress;
/*      */ import java.rmi.Naming;
/*      */ import java.rmi.RemoteException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.OperationNode;
/*      */ import javax.media.jai.ParameterListDescriptor;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.PropertyChangeEventJAI;
/*      */ import javax.media.jai.RenderableOp;
/*      */ import javax.media.jai.RenderedOp;
/*      */ import javax.media.jai.RenderingChangeEvent;
/*      */ import javax.media.jai.remote.NegotiableCapability;
/*      */ import javax.media.jai.remote.NegotiableCapabilitySet;
/*      */ import javax.media.jai.remote.PlanarImageServerProxy;
/*      */ import javax.media.jai.remote.RemoteImagingException;
/*      */ import javax.media.jai.remote.RemoteRenderedOp;
/*      */ import javax.media.jai.remote.SerializableRenderedImage;
/*      */ import javax.media.jai.remote.SerializableState;
/*      */ import javax.media.jai.remote.SerializerFactory;
/*      */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*      */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*      */ import javax.media.jai.tilecodec.TileDecoder;
/*      */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class RMIServerProxy extends PlanarImageServerProxy {
/*   66 */   private ImageServer remoteImage = null;
/*      */   
/*      */   private Long id;
/*      */   
/*   75 */   private Long renderingID = null;
/*      */   
/*      */   private boolean preferencesSet;
/*      */   
/*      */   private NegotiableCapabilitySet negPref;
/*      */   
/*   86 */   private static final Class NULL_PROPERTY_CLASS = JAIRMIImageServer.NULL_PROPERTY.getClass();
/*      */   
/*      */   private ImagingListener listener;
/*      */   
/*      */   public RMIServerProxy(String serverName, String opName, RenderingHints hints) {
/*  100 */     super(serverName, "jairmi", opName, null, hints);
/*  105 */     int index = serverName.indexOf("::");
/*  106 */     boolean remoteChaining = (index != -1);
/*  108 */     if (!remoteChaining)
/*  110 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage1")); 
/*  114 */     if (remoteChaining) {
/*  117 */       this.id = Long.valueOf(serverName.substring(index + 2));
/*  118 */       serverName = serverName.substring(0, index);
/*  119 */       this.serverName = serverName;
/*      */     } 
/*  122 */     this.listener = ImageUtil.getImagingListener(hints);
/*  124 */     this.remoteImage = getImageServer(serverName);
/*  126 */     if (this.preferencesSet)
/*  127 */       super.setNegotiationPreferences(this.negPref); 
/*      */     try {
/*  132 */       this.remoteImage.incrementRefCount(this.id);
/*  133 */     } catch (RemoteException re) {
/*  134 */       System.err.println(JaiI18N.getString("RMIServerProxy2"));
/*      */     } 
/*      */   }
/*      */   
/*      */   public RMIServerProxy(String serverName, ParameterBlock pb, String opName, RenderingHints hints) {
/*  148 */     super(serverName, "jairmi", opName, pb, hints);
/*  153 */     int index = serverName.indexOf("::");
/*  154 */     boolean remoteChaining = (index != -1);
/*  156 */     if (!remoteChaining)
/*  158 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage1")); 
/*  162 */     if (remoteChaining) {
/*  165 */       this.id = Long.valueOf(serverName.substring(index + 2));
/*  166 */       serverName = serverName.substring(0, index);
/*  167 */       this.serverName = serverName;
/*      */     } 
/*  170 */     this.listener = ImageUtil.getImagingListener(hints);
/*  172 */     this.remoteImage = getImageServer(serverName);
/*  174 */     if (this.preferencesSet)
/*  175 */       super.setNegotiationPreferences(this.negPref); 
/*      */     try {
/*  180 */       this.remoteImage.incrementRefCount(this.id);
/*  181 */     } catch (RemoteException re) {
/*  182 */       System.err.println(JaiI18N.getString("RMIServerProxy2"));
/*      */     } 
/*      */   }
/*      */   
/*      */   public RMIServerProxy(String serverName, String operationName, ParameterBlock paramBlock, RenderingHints hints) {
/*  193 */     super(serverName, "jairmi", operationName, paramBlock, hints);
/*  195 */     this.listener = ImageUtil.getImagingListener(hints);
/*  198 */     this.remoteImage = getImageServer(serverName);
/*  201 */     getRMIID();
/*  205 */     if (this.preferencesSet)
/*  206 */       super.setNegotiationPreferences(this.negPref); 
/*  209 */     ParameterBlock newPB = (ParameterBlock)paramBlock.clone();
/*  210 */     newPB.removeSources();
/*  213 */     JAIRMIUtil.checkClientParameters(newPB, serverName);
/*      */     try {
/*  216 */       SerializableState rhs = SerializerFactory.getState(hints, null);
/*  217 */       this.remoteImage.createRenderedOp(this.id, operationName, newPB, rhs);
/*  218 */     } catch (RemoteException e) {
/*  219 */       String message = JaiI18N.getString("RMIServerProxy5");
/*  220 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */     } 
/*  229 */     int size = getNumSources();
/*  230 */     for (int i = 0; i < size; i++) {
/*  232 */       PlanarImage planarImage = getSource(i);
/*  234 */       if (planarImage instanceof RMIServerProxy) {
/*      */         try {
/*  236 */           RMIServerProxy rop = (RMIServerProxy)planarImage;
/*  237 */           if (rop.serverName.equalsIgnoreCase(this.serverName)) {
/*  239 */             this.remoteImage.setRenderedSource(this.id, rop.getRMIID(), i);
/*      */           } else {
/*  241 */             this.remoteImage.setRenderedSource(this.id, rop.getRMIID(), rop.serverName, rop.operationName, i);
/*      */           } 
/*  247 */         } catch (RemoteException e) {
/*  248 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  249 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(e), this, false);
/*      */         } 
/*  255 */       } else if (planarImage instanceof RenderedOp) {
/*      */         SerializableRenderedImage serializableRenderedImage;
/*  261 */         RenderedOp rop = (RenderedOp)planarImage;
/*  262 */         PlanarImage planarImage1 = rop.getRendering();
/*  263 */         if (!(planarImage1 instanceof java.io.Serializable))
/*  264 */           serializableRenderedImage = new SerializableRenderedImage((RenderedImage)planarImage1); 
/*      */         try {
/*  267 */           this.remoteImage.setRenderedSource(this.id, (RenderedImage)serializableRenderedImage, i);
/*  268 */         } catch (RemoteException e) {
/*  269 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  270 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         } 
/*  279 */       } else if (planarImage instanceof RenderedImage) {
/*      */         try {
/*  281 */           if (planarImage instanceof java.io.Serializable) {
/*  282 */             this.remoteImage.setRenderedSource(this.id, (RenderedImage)planarImage, i);
/*      */           } else {
/*  284 */             this.remoteImage.setRenderedSource(this.id, (RenderedImage)new SerializableRenderedImage((RenderedImage)planarImage), i);
/*      */           } 
/*  290 */         } catch (RemoteException e) {
/*  291 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  292 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     try {
/*  305 */       this.remoteImage.incrementRefCount(this.id);
/*  306 */     } catch (RemoteException re) {
/*  307 */       System.err.println(JaiI18N.getString("RMIServerProxy2"));
/*      */     } 
/*      */   }
/*      */   
/*      */   public RMIServerProxy(PlanarImageServerProxy oldRendering, OperationNode node, String newServerName) {
/*  321 */     this(newServerName, node.getOperationName(), node.getParameterBlock(), node.getRenderingHints());
/*      */   }
/*      */   
/*      */   public RMIServerProxy(PlanarImageServerProxy oldRendering, OperationNode node, PropertyChangeEventJAI event) {
/*  336 */     super(((RemoteRenderedOp)node).getServerName(), "jairmi", node.getOperationName(), node.getParameterBlock(), node.getRenderingHints());
/*  342 */     this.listener = ImageUtil.getImagingListener(node.getRenderingHints());
/*  345 */     this.remoteImage = getImageServer(this.serverName);
/*  347 */     RMIServerProxy oldRMISP = null;
/*  348 */     if (oldRendering instanceof RMIServerProxy) {
/*  349 */       oldRMISP = (RMIServerProxy)oldRendering;
/*      */     } else {
/*  351 */       System.err.println(JaiI18N.getString("RMIServerProxy3"));
/*      */     } 
/*  354 */     Long opID = oldRMISP.getRMIID();
/*  356 */     String propName = event.getPropertyName();
/*  357 */     if (event instanceof RenderingChangeEvent) {
/*  359 */       RenderingChangeEvent rce = (RenderingChangeEvent)event;
/*  362 */       int idx = ((RenderedOp)node).getSources().indexOf(rce.getSource());
/*  364 */       PlanarImage oldSrcRendering = (PlanarImage)event.getOldValue();
/*  366 */       Object oldSrc = null;
/*  367 */       String serverNodeDesc = null;
/*  368 */       if (oldSrcRendering instanceof RMIServerProxy) {
/*  370 */         RMIServerProxy oldSrcRMISP = (RMIServerProxy)oldSrcRendering;
/*  372 */         if (!oldSrcRMISP.getServerName().equalsIgnoreCase(this.serverName)) {
/*  374 */           serverNodeDesc = oldSrcRMISP.getServerName() + "::" + oldSrcRMISP.getRMIID();
/*      */         } else {
/*  377 */           serverNodeDesc = oldSrcRMISP.getRMIID().toString();
/*      */         } 
/*  379 */         oldSrc = serverNodeDesc;
/*  380 */       } else if (oldSrcRendering instanceof java.io.Serializable) {
/*  381 */         oldSrc = oldSrcRendering;
/*      */       } else {
/*  383 */         oldSrc = new SerializableRenderedImage((RenderedImage)oldSrcRendering);
/*      */       } 
/*  386 */       Object srcInvalidRegion = rce.getInvalidRegion();
/*  387 */       SerializableState shapeState = SerializerFactory.getState(srcInvalidRegion, null);
/*  390 */       Long oldRenderingID = null;
/*      */       try {
/*  392 */         oldRenderingID = this.remoteImage.handleEvent(opID, idx, shapeState, oldSrc);
/*  397 */       } catch (RemoteException re) {
/*  398 */         String message = JaiI18N.getString("RMIServerProxy7");
/*  399 */         this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */       } 
/*  406 */       oldRMISP.id = oldRenderingID;
/*  407 */       this.id = opID;
/*      */     } else {
/*  418 */       Object oldValue = null, newValue = null;
/*  420 */       if (propName.equals("operationname")) {
/*  422 */         oldValue = event.getOldValue();
/*  423 */         newValue = event.getNewValue();
/*  425 */       } else if (propName.equals("parameterblock")) {
/*  427 */         ParameterBlock oldPB = (ParameterBlock)event.getOldValue();
/*  428 */         Vector oldSrcs = oldPB.getSources();
/*  429 */         oldPB.removeSources();
/*  431 */         ParameterBlock newPB = (ParameterBlock)event.getNewValue();
/*  432 */         Vector newSrcs = newPB.getSources();
/*  433 */         newPB.removeSources();
/*  436 */         JAIRMIUtil.checkClientParameters(oldPB, this.serverName);
/*  437 */         JAIRMIUtil.checkClientParameters(newPB, this.serverName);
/*  439 */         oldPB.setSources(JAIRMIUtil.replaceSourcesWithId(oldSrcs, this.serverName));
/*  441 */         newPB.setSources(JAIRMIUtil.replaceSourcesWithId(newSrcs, this.serverName));
/*  444 */         oldValue = oldPB;
/*  445 */         newValue = newPB;
/*  447 */       } else if (propName.equals("sources")) {
/*  449 */         Vector oldSrcs = (Vector)event.getOldValue();
/*  450 */         Vector newSrcs = (Vector)event.getNewValue();
/*  452 */         oldValue = JAIRMIUtil.replaceSourcesWithId(oldSrcs, this.serverName);
/*  454 */         newValue = JAIRMIUtil.replaceSourcesWithId(newSrcs, this.serverName);
/*  457 */       } else if (propName.equals("parameters")) {
/*  459 */         Vector oldParameters = (Vector)event.getOldValue();
/*  460 */         Vector newParameters = (Vector)event.getNewValue();
/*  463 */         JAIRMIUtil.checkClientParameters(oldParameters, this.serverName);
/*  464 */         JAIRMIUtil.checkClientParameters(newParameters, this.serverName);
/*  466 */         oldValue = oldParameters;
/*  467 */         newValue = newParameters;
/*  469 */       } else if (propName.equals("renderinghints")) {
/*  471 */         RenderingHints oldRH = (RenderingHints)event.getOldValue();
/*  472 */         RenderingHints newRH = (RenderingHints)event.getNewValue();
/*  474 */         oldValue = SerializerFactory.getState(oldRH, null);
/*  475 */         newValue = SerializerFactory.getState(newRH, null);
/*      */       } else {
/*  477 */         throw new RemoteImagingException(JaiI18N.getString("RMIServerProxy4"));
/*      */       } 
/*  481 */       Long oldRenderingID = null;
/*      */       try {
/*  484 */         oldRenderingID = this.remoteImage.handleEvent(opID, propName, oldValue, newValue);
/*  489 */         this.remoteImage.incrementRefCount(oldRenderingID);
/*  490 */       } catch (RemoteException re) {
/*  491 */         String message = JaiI18N.getString("RMIServerProxy7");
/*  492 */         this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */       } 
/*  498 */       oldRMISP.id = oldRenderingID;
/*  499 */       this.id = opID;
/*      */     } 
/*  504 */     if (this.preferencesSet)
/*  505 */       super.setNegotiationPreferences(this.negPref); 
/*      */   }
/*      */   
/*      */   public RMIServerProxy(String serverName, String operationName, ParameterBlock pb, RenderingHints hints, Long id) {
/*  518 */     super(serverName, "jairmi", operationName, pb, hints);
/*  520 */     this.listener = ImageUtil.getImagingListener(hints);
/*  523 */     this.remoteImage = getImageServer(serverName);
/*  525 */     this.id = id;
/*      */   }
/*      */   
/*      */   public RMIServerProxy(String serverName, String operationName, ParameterBlock paramBlock, RenderContext rc, boolean isRender) {
/*  538 */     super(serverName, "jairmi", operationName, paramBlock, null);
/*  540 */     this.listener = ImageUtil.getImagingListener(rc.getRenderingHints());
/*  543 */     this.remoteImage = getImageServer(serverName);
/*  546 */     getRMIID();
/*  548 */     if (this.preferencesSet)
/*  549 */       super.setNegotiationPreferences(this.negPref); 
/*  553 */     ParameterBlock newPB = (ParameterBlock)paramBlock.clone();
/*  554 */     newPB.removeSources();
/*      */     try {
/*  565 */       this.remoteImage.createRenderableOp(this.id, operationName, newPB);
/*  566 */     } catch (RemoteException e) {
/*  567 */       String message = JaiI18N.getString("RMIServerProxy8");
/*  568 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */     } 
/*  575 */     int size = getNumSources();
/*  577 */     for (int i = 0; i < size; i++) {
/*  579 */       Vector sources = paramBlock.getSources();
/*  580 */       Object source = sources.elementAt(i);
/*  582 */       if (source instanceof RMIServerProxy) {
/*      */         try {
/*  584 */           RMIServerProxy rop = (RMIServerProxy)source;
/*  586 */           if (rop.serverName.equals(this.serverName)) {
/*  587 */             this.remoteImage.setRenderableSource(this.id, rop.getRMIID(), i);
/*      */           } else {
/*  589 */             this.remoteImage.setRenderableSource(this.id, rop.getRMIID(), rop.serverName, rop.operationName, i);
/*      */           } 
/*  593 */         } catch (RemoteException e) {
/*  594 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  595 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         } 
/*  604 */       } else if (source instanceof RenderableOp) {
/*      */         try {
/*  606 */           this.remoteImage.setRenderableSource(this.id, (RenderableOp)source, i);
/*  609 */         } catch (RemoteException e) {
/*  610 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  611 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         } 
/*  620 */       } else if (source instanceof RenderedImage) {
/*      */         try {
/*  622 */           this.remoteImage.setRenderableSource(this.id, (RenderedImage)new SerializableRenderedImage((RenderedImage)source), i);
/*  627 */         } catch (RemoteException e) {
/*  628 */           String message = JaiI18N.getString("RMIServerProxy6");
/*  629 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     try {
/*  642 */       this.remoteImage.incrementRefCount(this.id);
/*  643 */     } catch (RemoteException e) {
/*  644 */       String message = JaiI18N.getString("RMIServerProxy9");
/*  645 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */     } 
/*  658 */     if (isRender)
/*      */       try {
/*  660 */         this.renderingID = this.remoteImage.getRendering(this.id, SerializerFactory.getState(rc, null));
/*  665 */         this.remoteImage.incrementRefCount(this.renderingID);
/*  666 */       } catch (RemoteException e) {
/*  667 */         String message = JaiI18N.getString("RMIServerProxy10");
/*  668 */         this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected synchronized ImageServer getImageServer(String serverName) {
/*  694 */     if (this.remoteImage == null) {
/*  696 */       if (serverName == null)
/*      */         try {
/*  698 */           serverName = InetAddress.getLocalHost().getHostAddress();
/*  699 */         } catch (Exception e) {
/*  700 */           String message = JaiI18N.getString("RMIServerProxy11");
/*  701 */           this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */         }  
/*  709 */       String serviceName = new String("rmi://" + serverName + "/" + "JAIRMIRemoteServer1.1");
/*  714 */       this.remoteImage = null;
/*      */       try {
/*  716 */         this.remoteImage = (ImageServer)Naming.lookup(serviceName);
/*  717 */       } catch (Exception e) {
/*  718 */         String message = JaiI18N.getString("RMIServerProxy12");
/*  719 */         this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*      */       } 
/*      */     } 
/*  726 */     return this.remoteImage;
/*      */   }
/*      */   
/*      */   public synchronized Long getRMIID() {
/*  735 */     if (this.id != null)
/*  736 */       return this.id; 
/*      */     try {
/*  740 */       this.id = this.remoteImage.getRemoteID();
/*  741 */       return this.id;
/*  742 */     } catch (Exception e) {
/*  743 */       String message = JaiI18N.getString("RMIServerProxy13");
/*  744 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*  750 */       return this.id;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Long getRenderingID() {
/*  754 */     return this.renderingID;
/*      */   }
/*      */   
/*      */   public boolean canBeRendered() {
/*  759 */     boolean cbr = true;
/*  760 */     getImageServer(this.serverName);
/*      */     try {
/*  762 */       cbr = this.remoteImage.getRendering(getRMIID());
/*  763 */     } catch (RemoteException re) {
/*  764 */       String message = JaiI18N.getString("RMIServerProxy10");
/*  765 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */     } 
/*  771 */     return cbr;
/*      */   }
/*      */   
/*      */   protected void finalize() {
/*      */     try {
/*  780 */       this.remoteImage.dispose(this.id);
/*  781 */     } catch (Exception e) {}
/*  785 */     dispose();
/*      */   }
/*      */   
/*      */   public ImageLayout getImageLayout() throws RemoteImagingException {
/*  797 */     ImageLayout layout = new ImageLayout();
/*      */     try {
/*  799 */       layout.setMinX(this.remoteImage.getMinX(this.id));
/*  800 */       layout.setMinY(this.remoteImage.getMinY(this.id));
/*  801 */       layout.setWidth(this.remoteImage.getWidth(this.id));
/*  802 */       layout.setHeight(this.remoteImage.getHeight(this.id));
/*  803 */       layout.setTileWidth(this.remoteImage.getTileWidth(this.id));
/*  804 */       layout.setTileHeight(this.remoteImage.getTileHeight(this.id));
/*  805 */       layout.setTileGridXOffset(this.remoteImage.getTileGridXOffset(this.id));
/*  806 */       layout.setTileGridYOffset(this.remoteImage.getTileGridYOffset(this.id));
/*  808 */       SerializableState smState = this.remoteImage.getSampleModel(this.id);
/*  809 */       layout.setSampleModel((SampleModel)smState.getObject());
/*  810 */       SerializableState cmState = this.remoteImage.getColorModel(this.id);
/*  811 */       layout.setColorModel((ColorModel)cmState.getObject());
/*  812 */       return layout;
/*  813 */     } catch (RemoteException re) {
/*  814 */       String message = JaiI18N.getString("RMIServerProxy14");
/*  815 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*  818 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) throws RemoteImagingException {
/*  834 */     if (tileX < getMinTileX() || tileX > getMaxTileX() || tileY < getMinTileY() || tileY > getMaxTileY())
/*  836 */       return null; 
/*  841 */     NegotiableCapability codecCap = getNegotiatedValue("tileCodec");
/*  843 */     TileDecoderFactory tdf = null;
/*  844 */     TileCodecParameterList tcpl = null;
/*  846 */     if (codecCap != null) {
/*  848 */       String category = codecCap.getCategory();
/*  849 */       String capabilityName = codecCap.getCapabilityName();
/*  850 */       List generators = codecCap.getGenerators();
/*  853 */       for (Iterator i = generators.iterator(); i.hasNext(); ) {
/*  854 */         Class factory = i.next();
/*  855 */         if (tdf == null && TileDecoderFactory.class.isAssignableFrom(factory))
/*      */           try {
/*  859 */             tdf = (TileDecoderFactory)factory.newInstance();
/*  860 */           } catch (InstantiationException ie) {
/*  861 */             throw new RemoteImagingException(ImageUtil.getStackTraceString(ie));
/*  862 */           } catch (IllegalAccessException iae) {
/*  863 */             throw new RemoteImagingException(ImageUtil.getStackTraceString(iae));
/*      */           }  
/*      */       } 
/*  868 */       if (tdf == null)
/*  869 */         throw new RemoteImagingException(JaiI18N.getString("RMIServerProxy0")); 
/*  873 */       TileCodecDescriptor tcd = (TileCodecDescriptor)this.registry.getDescriptor("tileDecoder", capabilityName);
/*  877 */       if (!tcd.includesSampleModelInfo() || !tcd.includesLocationInfo())
/*  879 */         throw new RemoteImagingException(JaiI18N.getString("RMIServerProxy1")); 
/*  883 */       ParameterListDescriptor pld = tcd.getParameterListDescriptor("tileDecoder");
/*  886 */       tcpl = new TileCodecParameterList(capabilityName, new String[] { "tileDecoder" }, pld);
/*  892 */       if (pld != null) {
/*  894 */         String[] paramNames = pld.getParamNames();
/*  897 */         if (paramNames != null)
/*  898 */           for (int j = 0; j < paramNames.length; j++) {
/*      */             Object currValue;
/*  899 */             String currParam = paramNames[j];
/*      */             try {
/*  901 */               currValue = codecCap.getNegotiatedValue(currParam);
/*  902 */             } catch (IllegalArgumentException iae) {}
/*  908 */             tcpl.setParameter(currParam, currValue);
/*      */           }  
/*      */       } 
/*      */     } 
/*      */     try {
/*  916 */       if (codecCap != null) {
/*  917 */         byte[] ctile = this.remoteImage.getCompressedTile(this.id, tileX, tileY);
/*  920 */         ByteArrayInputStream stream = new ByteArrayInputStream(ctile);
/*  921 */         TileDecoder decoder = tdf.createDecoder(stream, tcpl);
/*      */         try {
/*  923 */           return decoder.decode();
/*  924 */         } catch (IOException ioe) {
/*  925 */           throw new RemoteImagingException(ImageUtil.getStackTraceString(ioe));
/*      */         } 
/*      */       } 
/*  929 */       SerializableState rp = this.remoteImage.getTile(this.id, tileX, tileY);
/*  930 */       return (Raster)rp.getObject();
/*  932 */     } catch (RemoteException e) {
/*  933 */       String message = JaiI18N.getString("RMIServerProxy15");
/*  934 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*  940 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getRemoteProperty(String name) throws RemoteImagingException {
/*      */     try {
/*  946 */       Object property = this.remoteImage.getProperty(this.id, name);
/*  947 */       if (NULL_PROPERTY_CLASS.isInstance(property))
/*  948 */         property = Image.UndefinedProperty; 
/*  950 */       return property;
/*  951 */     } catch (RemoteException re) {
/*  952 */       String message = JaiI18N.getString("RMIServerProxy16");
/*  953 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*  959 */       return Image.UndefinedProperty;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String[] getRemotePropertyNames() throws RemoteImagingException {
/*      */     try {
/*  972 */       return this.remoteImage.getPropertyNames(this.id);
/*  973 */     } catch (RemoteException re) {
/*  974 */       String message = JaiI18N.getString("RMIServerProxy17");
/*  975 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*  981 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) throws RemoteImagingException {
/* 1008 */     Rectangle dstRect = null;
/*      */     try {
/* 1011 */       dstRect = this.remoteImage.mapSourceRect(this.id, sourceRect, sourceIndex);
/* 1012 */     } catch (RemoteException re) {
/* 1013 */       String message = JaiI18N.getString("RMIServerProxy18");
/* 1014 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */     } 
/* 1020 */     return dstRect;
/*      */   }
/*      */   
/*      */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) throws RemoteImagingException {
/* 1045 */     Rectangle srcRect = null;
/*      */     try {
/* 1048 */       srcRect = this.remoteImage.mapDestRect(this.id, destRect, sourceIndex);
/* 1049 */     } catch (RemoteException re) {
/* 1050 */       String message = JaiI18N.getString("RMIServerProxy18");
/* 1051 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */     } 
/* 1057 */     return srcRect;
/*      */   }
/*      */   
/*      */   public void setNegotiationPreferences(NegotiableCapabilitySet preferences) {
/* 1062 */     if (this.remoteImage == null) {
/* 1063 */       this.negPref = preferences;
/* 1064 */       this.preferencesSet = true;
/*      */     } else {
/* 1066 */       super.setNegotiationPreferences(preferences);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setServerNegotiatedValues(NegotiableCapabilitySet negotiatedValues) throws RemoteImagingException {
/*      */     try {
/* 1080 */       this.remoteImage.setServerNegotiatedValues(this.id, negotiatedValues);
/* 1081 */     } catch (RemoteException re) {
/* 1082 */       String message = JaiI18N.getString("RMIServerProxy19");
/* 1083 */       this.listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, re), this, false);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RMIServerProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */