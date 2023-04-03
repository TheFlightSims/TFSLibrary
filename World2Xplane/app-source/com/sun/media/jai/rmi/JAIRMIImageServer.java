/*      */ package com.sun.media.jai.rmi;
/*      */ 
/*      */ import com.sun.media.jai.remote.JAIServerConfigurationSpi;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.Service;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Image;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.net.InetAddress;
/*      */ import java.net.UnknownHostException;
/*      */ import java.rmi.Naming;
/*      */ import java.rmi.RMISecurityManager;
/*      */ import java.rmi.RemoteException;
/*      */ import java.rmi.server.UnicastRemoteObject;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.CollectionImage;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.OpImage;
/*      */ import javax.media.jai.OperationDescriptor;
/*      */ import javax.media.jai.OperationNode;
/*      */ import javax.media.jai.OperationRegistry;
/*      */ import javax.media.jai.ParameterListDescriptor;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.PropertySource;
/*      */ import javax.media.jai.RenderableOp;
/*      */ import javax.media.jai.RenderedOp;
/*      */ import javax.media.jai.RenderingChangeEvent;
/*      */ import javax.media.jai.registry.CRIFRegistry;
/*      */ import javax.media.jai.remote.NegotiableCapability;
/*      */ import javax.media.jai.remote.NegotiableCapabilitySet;
/*      */ import javax.media.jai.remote.RemoteImagingException;
/*      */ import javax.media.jai.remote.SerializableRenderedImage;
/*      */ import javax.media.jai.remote.SerializableState;
/*      */ import javax.media.jai.remote.SerializerFactory;
/*      */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*      */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*      */ import javax.media.jai.tilecodec.TileEncoder;
/*      */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class JAIRMIImageServer extends UnicastRemoteObject implements ImageServer {
/*      */   private boolean DEBUG = true;
/*      */   
/*  113 */   public static final Object NULL_PROPERTY = RMIImageImpl.NULL_PROPERTY;
/*      */   
/*  116 */   private static long idCounter = 0L;
/*      */   
/*  122 */   private static Hashtable nodes = new Hashtable();
/*      */   
/*  127 */   private static Hashtable negotiated = new Hashtable();
/*      */   
/*  133 */   private static Hashtable refCount = new Hashtable();
/*      */   
/*      */   private static PlanarImage getSource(Long id) throws RemoteException {
/*  142 */     Object obj = null;
/*  143 */     if (nodes == null || (obj = nodes.get(id)) == null)
/*  145 */       throw new RemoteException(JaiI18N.getString("RMIImageImpl2")); 
/*  147 */     return (PlanarImage)obj;
/*      */   }
/*      */   
/*      */   private static PropertySource getPropertySource(Long id) throws RemoteException {
/*  159 */     Object obj = nodes.get(id);
/*  160 */     return (PropertySource)obj;
/*      */   }
/*      */   
/*      */   public JAIRMIImageServer(int serverport) throws RemoteException {
/*  168 */     super(serverport);
/*      */   }
/*      */   
/*      */   public synchronized Long getRemoteID() throws RemoteException {
/*  178 */     return new Long(++idCounter);
/*      */   }
/*      */   
/*      */   public synchronized void dispose(Long id) throws RemoteException {
/*  187 */     int count = ((Integer)refCount.get(id)).intValue();
/*  189 */     if (count == 1) {
/*  193 */       if (nodes != null) {
/*  194 */         nodes.remove(id);
/*  195 */         negotiated.remove(id);
/*      */       } 
/*  198 */       refCount.remove(id);
/*      */     } else {
/*  203 */       count--;
/*  204 */       if (count == 0)
/*  205 */         refCount.remove(id); 
/*  207 */       refCount.put(id, new Integer(count));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void incrementRefCount(Long id) throws RemoteException {
/*  216 */     Integer iCount = (Integer)refCount.get(id);
/*  217 */     int count = 0;
/*  218 */     if (iCount != null)
/*  219 */       count = iCount.intValue(); 
/*  221 */     count++;
/*  222 */     refCount.put(id, new Integer(count));
/*      */   }
/*      */   
/*      */   public Object getProperty(Long id, String name) throws RemoteException {
/*  230 */     PropertySource ps = getPropertySource(id);
/*  231 */     Object property = ps.getProperty(name);
/*  233 */     if (property == null || property.equals(Image.UndefinedProperty))
/*  235 */       property = NULL_PROPERTY; 
/*  238 */     return property;
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames(Long id) throws RemoteException {
/*  248 */     PropertySource ps = getPropertySource(id);
/*  249 */     return ps.getPropertyNames();
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames(String opName) throws RemoteException {
/*  260 */     return CRIFRegistry.get(null, opName).getPropertyNames();
/*      */   }
/*      */   
/*      */   public int getMinX(Long id) throws RemoteException {
/*  265 */     return getSource(id).getMinX();
/*      */   }
/*      */   
/*      */   public int getMaxX(Long id) throws RemoteException {
/*  271 */     return getSource(id).getMaxX();
/*      */   }
/*      */   
/*      */   public int getMinY(Long id) throws RemoteException {
/*  277 */     return getSource(id).getMinY();
/*      */   }
/*      */   
/*      */   public int getMaxY(Long id) throws RemoteException {
/*  283 */     return getSource(id).getMaxY();
/*      */   }
/*      */   
/*      */   public int getWidth(Long id) throws RemoteException {
/*  289 */     return getSource(id).getWidth();
/*      */   }
/*      */   
/*      */   public int getHeight(Long id) throws RemoteException {
/*  295 */     return getSource(id).getHeight();
/*      */   }
/*      */   
/*      */   public int getTileWidth(Long id) throws RemoteException {
/*  301 */     return getSource(id).getTileWidth();
/*      */   }
/*      */   
/*      */   public int getTileHeight(Long id) throws RemoteException {
/*  307 */     return getSource(id).getTileHeight();
/*      */   }
/*      */   
/*      */   public int getTileGridXOffset(Long id) throws RemoteException {
/*  315 */     return getSource(id).getTileGridXOffset();
/*      */   }
/*      */   
/*      */   public int getTileGridYOffset(Long id) throws RemoteException {
/*  323 */     return getSource(id).getTileGridYOffset();
/*      */   }
/*      */   
/*      */   public int getMinTileX(Long id) throws RemoteException {
/*  329 */     return getSource(id).getMinTileX();
/*      */   }
/*      */   
/*      */   public int getNumXTiles(Long id) throws RemoteException {
/*  338 */     return getSource(id).getNumXTiles();
/*      */   }
/*      */   
/*      */   public int getMinTileY(Long id) throws RemoteException {
/*  344 */     return getSource(id).getMinTileY();
/*      */   }
/*      */   
/*      */   public int getNumYTiles(Long id) throws RemoteException {
/*  353 */     return getSource(id).getNumYTiles();
/*      */   }
/*      */   
/*      */   public int getMaxTileX(Long id) throws RemoteException {
/*  359 */     return getSource(id).getMaxTileX();
/*      */   }
/*      */   
/*      */   public int getMaxTileY(Long id) throws RemoteException {
/*  365 */     return getSource(id).getMaxTileY();
/*      */   }
/*      */   
/*      */   public SerializableState getSampleModel(Long id) throws RemoteException {
/*  370 */     return SerializerFactory.getState(getSource(id).getSampleModel(), null);
/*      */   }
/*      */   
/*      */   public SerializableState getColorModel(Long id) throws RemoteException {
/*  376 */     return SerializerFactory.getState(getSource(id).getColorModel(), null);
/*      */   }
/*      */   
/*      */   public Rectangle getBounds(Long id) throws RemoteException {
/*  382 */     return getSource(id).getBounds();
/*      */   }
/*      */   
/*      */   public SerializableState getTile(Long id, int tileX, int tileY) throws RemoteException {
/*  398 */     Raster r = getSource(id).getTile(tileX, tileY);
/*  399 */     return SerializerFactory.getState(r, null);
/*      */   }
/*      */   
/*      */   public byte[] getCompressedTile(Long id, int x, int y) throws RemoteException {
/*  416 */     TileCodecParameterList tcpl = null;
/*  417 */     TileEncoderFactory tef = null;
/*  418 */     NegotiableCapability codecCap = null;
/*  420 */     if (negotiated != null)
/*  421 */       codecCap = ((NegotiableCapabilitySet)negotiated.get(id)).getNegotiatedValue("tileCodec"); 
/*  425 */     if (codecCap != null) {
/*  427 */       String category = codecCap.getCategory();
/*  428 */       String capabilityName = codecCap.getCapabilityName();
/*  429 */       List generators = codecCap.getGenerators();
/*  432 */       for (Iterator i = generators.iterator(); i.hasNext(); ) {
/*  434 */         Class factory = i.next();
/*  435 */         if (tef == null && TileEncoderFactory.class.isAssignableFrom(factory))
/*      */           try {
/*  438 */             tef = (TileEncoderFactory)factory.newInstance();
/*  439 */           } catch (InstantiationException ie) {
/*  440 */             throw new RuntimeException(ie.getMessage());
/*  441 */           } catch (IllegalAccessException iae) {
/*  442 */             throw new RuntimeException(iae.getMessage());
/*      */           }  
/*      */       } 
/*  447 */       if (tef == null)
/*  448 */         throw new RuntimeException(JaiI18N.getString("JAIRMIImageServer0")); 
/*  452 */       TileCodecDescriptor tcd = (TileCodecDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor("tileEncoder", capabilityName);
/*  457 */       if (!tcd.includesSampleModelInfo() || !tcd.includesLocationInfo())
/*  459 */         throw new RuntimeException(JaiI18N.getString("JAIRMIImageServer1")); 
/*  463 */       ParameterListDescriptor pld = tcd.getParameterListDescriptor("tileEncoder");
/*  466 */       tcpl = new TileCodecParameterList(capabilityName, new String[] { "tileEncoder" }, pld);
/*  470 */       if (pld != null) {
/*  472 */         String[] paramNames = pld.getParamNames();
/*  476 */         if (paramNames != null)
/*  477 */           for (int j = 0; j < paramNames.length; j++) {
/*      */             Object currValue;
/*  478 */             String currParam = paramNames[j];
/*      */             try {
/*  480 */               currValue = codecCap.getNegotiatedValue(currParam);
/*  481 */             } catch (IllegalArgumentException iae) {}
/*  484 */             tcpl.setParameter(currParam, currValue);
/*      */           }  
/*      */       } 
/*  489 */       Raster r = getSource(id).getTile(x, y);
/*  490 */       ByteArrayOutputStream stream = new ByteArrayOutputStream();
/*  491 */       TileEncoder encoder = tef.createEncoder(stream, tcpl, r.getSampleModel());
/*      */       try {
/*  495 */         encoder.encode(r);
/*  496 */       } catch (IOException ioe) {
/*  497 */         throw new RuntimeException(ioe.getMessage());
/*      */       } 
/*  500 */       return stream.toByteArray();
/*      */     } 
/*  502 */     throw new RuntimeException(JaiI18N.getString("JAIRMIImageServer2"));
/*      */   }
/*      */   
/*      */   public SerializableState getData(Long id) throws RemoteException {
/*  513 */     return SerializerFactory.getState(getSource(id).getData(), null);
/*      */   }
/*      */   
/*      */   public SerializableState getData(Long id, Rectangle bounds) throws RemoteException {
/*  527 */     if (bounds == null)
/*  528 */       return getData(id); 
/*  530 */     bounds = bounds.intersection(getBounds(id));
/*  531 */     return SerializerFactory.getState(getSource(id).getData(bounds), null);
/*      */   }
/*      */   
/*      */   public SerializableState copyData(Long id, Rectangle bounds) throws RemoteException {
/*  542 */     return getData(id, bounds);
/*      */   }
/*      */   
/*      */   public void createRenderedOp(Long id, String opName, ParameterBlock pb, SerializableState hints) throws RemoteException {
/*  558 */     RenderingHints rh = (RenderingHints)hints.getObject();
/*  562 */     JAIRMIUtil.checkServerParameters(pb, nodes);
/*  564 */     RenderedOp node = new RenderedOp(opName, pb, rh);
/*  568 */     node.removeSinks();
/*  570 */     nodes.put(id, node);
/*      */   }
/*      */   
/*      */   public boolean getRendering(Long id) throws RemoteException {
/*  579 */     RenderedOp op = getNode(id);
/*  580 */     if (op.getRendering() == null)
/*  581 */       return false; 
/*  583 */     return true;
/*      */   }
/*      */   
/*      */   public RenderedOp getNode(Long id) throws RemoteException {
/*  592 */     return (RenderedOp)nodes.get(id);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderedSource(Long id, RenderedImage source, int index) throws RemoteException {
/*  603 */     PlanarImage pi = PlanarImage.wrapRenderedImage(source);
/*  605 */     Object obj = nodes.get(id);
/*  607 */     if (obj instanceof RenderedOp) {
/*  608 */       RenderedOp op = (RenderedOp)obj;
/*  609 */       op.setSource(pi, index);
/*  610 */       ((PlanarImage)op.getSourceObject(index)).removeSinks();
/*  611 */     } else if (obj instanceof RenderableOp) {
/*  612 */       ((RenderableOp)obj).setSource(pi, index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setRenderedSource(Long id, RenderedOp source, int index) throws RemoteException {
/*  624 */     Object obj = nodes.get(id);
/*  625 */     if (obj instanceof RenderedOp) {
/*  626 */       RenderedOp op = (RenderedOp)obj;
/*  627 */       op.setSource(source.getRendering(), index);
/*  628 */       ((PlanarImage)op.getSourceObject(index)).removeSinks();
/*  629 */     } else if (obj instanceof RenderableOp) {
/*  630 */       ((RenderableOp)obj).setSource(source.getRendering(), index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setRenderedSource(Long id, Long sourceId, int index) throws RemoteException {
/*  643 */     Object obj = nodes.get(id);
/*  644 */     if (obj instanceof RenderedOp) {
/*  645 */       RenderedOp op = (RenderedOp)obj;
/*  646 */       op.setSource(nodes.get(sourceId), index);
/*  647 */       ((PlanarImage)nodes.get(sourceId)).removeSinks();
/*  648 */     } else if (obj instanceof RenderableOp) {
/*  649 */       ((RenderableOp)obj).setSource(nodes.get(sourceId), index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setRenderedSource(Long id, Long sourceId, String serverName, String opName, int index) throws RemoteException {
/*  664 */     Object obj = nodes.get(id);
/*  666 */     if (obj instanceof RenderedOp) {
/*  667 */       RenderedOp node = (RenderedOp)obj;
/*  668 */       node.setSource((PlanarImage)new RMIServerProxy(serverName + "::" + sourceId, opName, null), index);
/*  672 */       ((PlanarImage)node.getSourceObject(index)).removeSinks();
/*  673 */     } else if (obj instanceof RenderableOp) {
/*  674 */       ((RenderableOp)obj).setSource(new RMIServerProxy(serverName + "::" + sourceId, opName, null), index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getRenderableMinX(Long id) throws RemoteException {
/*  693 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  694 */     return ri.getMinX();
/*      */   }
/*      */   
/*      */   public float getRenderableMinY(Long id) throws RemoteException {
/*  705 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  706 */     return ri.getMinY();
/*      */   }
/*      */   
/*      */   public float getRenderableWidth(Long id) throws RemoteException {
/*  716 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  717 */     return ri.getWidth();
/*      */   }
/*      */   
/*      */   public float getRenderableHeight(Long id) throws RemoteException {
/*  727 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  728 */     return ri.getHeight();
/*      */   }
/*      */   
/*      */   public RenderedImage createScaledRendering(Long id, int w, int h, SerializableState hintsState) throws RemoteException {
/*  762 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  763 */     RenderingHints hints = (RenderingHints)hintsState.getObject();
/*  764 */     RenderedImage rendering = ri.createScaledRendering(w, h, hints);
/*  765 */     if (rendering instanceof java.io.Serializable)
/*  766 */       return rendering; 
/*  768 */     return (RenderedImage)new SerializableRenderedImage(rendering);
/*      */   }
/*      */   
/*      */   public RenderedImage createDefaultRendering(Long id) throws RemoteException {
/*  785 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  786 */     RenderedImage rendering = ri.createDefaultRendering();
/*  787 */     if (rendering instanceof java.io.Serializable)
/*  788 */       return rendering; 
/*  790 */     return (RenderedImage)new SerializableRenderedImage(rendering);
/*      */   }
/*      */   
/*      */   public RenderedImage createRendering(Long id, SerializableState renderContextState) throws RemoteException {
/*  813 */     RenderableImage ri = (RenderableImage)nodes.get(id);
/*  814 */     RenderContext renderContext = (RenderContext)renderContextState.getObject();
/*  816 */     RenderedImage rendering = ri.createRendering(renderContext);
/*  817 */     if (rendering instanceof java.io.Serializable)
/*  818 */       return rendering; 
/*  820 */     return (RenderedImage)new SerializableRenderedImage(rendering);
/*      */   }
/*      */   
/*      */   public synchronized void createRenderableOp(Long id, String opName, ParameterBlock pb) throws RemoteException {
/*  839 */     RenderableOp node = new RenderableOp(opName, pb);
/*  840 */     nodes.put(id, node);
/*      */   }
/*      */   
/*      */   public synchronized Long getRendering(Long id, SerializableState rcs) throws RemoteException {
/*  849 */     RenderableOp op = (RenderableOp)nodes.get(id);
/*  850 */     PlanarImage pi = PlanarImage.wrapRenderedImage(op.createRendering((RenderContext)rcs.getObject()));
/*  853 */     Long renderingID = getRemoteID();
/*  854 */     nodes.put(renderingID, pi);
/*  857 */     setServerNegotiatedValues(renderingID, (NegotiableCapabilitySet)negotiated.get(id));
/*  859 */     return renderingID;
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableSource(Long id, Long sourceId, int index) throws RemoteException {
/*  871 */     RenderableOp node = (RenderableOp)nodes.get(id);
/*  872 */     Object obj = nodes.get(sourceId);
/*  873 */     if (obj instanceof RenderableOp) {
/*  874 */       node.setSource(obj, index);
/*  875 */     } else if (obj instanceof RenderedImage) {
/*  876 */       node.setSource(PlanarImage.wrapRenderedImage((RenderedImage)obj), index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableSource(Long id, Long sourceId, String serverName, String opName, int index) throws RemoteException {
/*  892 */     RenderableOp node = (RenderableOp)nodes.get(id);
/*  893 */     node.setSource(new RMIServerProxy(serverName + "::" + sourceId, opName, null), index);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableRMIServerProxyAsSource(Long id, Long sourceId, String serverName, String opName, int index) throws RemoteException {
/*  912 */     RenderableOp node = (RenderableOp)nodes.get(id);
/*  913 */     node.setSource(new RenderableRMIServerProxy(serverName, opName, null, sourceId), index);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableSource(Long id, RenderableOp source, int index) throws RemoteException {
/*  927 */     RenderableOp op = (RenderableOp)nodes.get(id);
/*  928 */     op.setSource(source, index);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableSource(Long id, SerializableRenderableImage s, int index) throws RemoteException {
/*  938 */     RenderableOp op = (RenderableOp)nodes.get(id);
/*  939 */     op.setSource(s, index);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderableSource(Long id, RenderedImage source, int index) throws RemoteException {
/*  950 */     PlanarImage pi = PlanarImage.wrapRenderedImage(source);
/*  951 */     RenderableOp op = (RenderableOp)nodes.get(id);
/*  952 */     op.setSource(pi, index);
/*      */   }
/*      */   
/*      */   public SerializableState mapRenderContext(int id, Long nodeId, String operationName, SerializableState rcs) throws RemoteException {
/*  965 */     RenderableOp rop = (RenderableOp)nodes.get(nodeId);
/*  968 */     ContextualRenderedImageFactory crif = CRIFRegistry.get(rop.getRegistry(), operationName);
/*  971 */     if (crif == null)
/*  972 */       throw new RuntimeException(JaiI18N.getString("JAIRMIImageServer3")); 
/*  976 */     RenderContext rc = crif.mapRenderContext(id, (RenderContext)rcs.getObject(), rop.getParameterBlock(), (RenderableImage)rop);
/*  981 */     return SerializerFactory.getState(rc, null);
/*      */   }
/*      */   
/*      */   public SerializableState getBounds2D(Long nodeId, String operationName) throws RemoteException {
/*  992 */     RenderableOp rop = (RenderableOp)nodes.get(nodeId);
/*  995 */     ContextualRenderedImageFactory crif = CRIFRegistry.get(rop.getRegistry(), operationName);
/*  998 */     if (crif == null)
/*  999 */       throw new RuntimeException(JaiI18N.getString("JAIRMIImageServer3")); 
/* 1003 */     Rectangle2D r2D = crif.getBounds2D(rop.getParameterBlock());
/* 1006 */     return SerializerFactory.getState(r2D, null);
/*      */   }
/*      */   
/*      */   public boolean isDynamic(String opName) throws RemoteException {
/* 1017 */     return CRIFRegistry.get(null, opName).isDynamic();
/*      */   }
/*      */   
/*      */   public boolean isDynamic(Long id) throws RemoteException {
/* 1027 */     RenderableImage node = (RenderableImage)nodes.get(id);
/* 1028 */     return node.isDynamic();
/*      */   }
/*      */   
/*      */   public String[] getServerSupportedOperationNames() throws RemoteException {
/* 1036 */     return JAI.getDefaultInstance().getOperationRegistry().getDescriptorNames(OperationDescriptor.class);
/*      */   }
/*      */   
/*      */   public List getOperationDescriptors() throws RemoteException {
/* 1045 */     return JAI.getDefaultInstance().getOperationRegistry().getDescriptors(OperationDescriptor.class);
/*      */   }
/*      */   
/*      */   public synchronized SerializableState getInvalidRegion(Long id, ParameterBlock oldParamBlock, SerializableState oldRHints, ParameterBlock newParamBlock, SerializableState newRHints) throws RemoteException {
/* 1070 */     RenderingHints oldHints = (RenderingHints)oldRHints.getObject();
/* 1071 */     RenderingHints newHints = (RenderingHints)newRHints.getObject();
/* 1073 */     RenderedOp op = (RenderedOp)nodes.get(id);
/* 1075 */     OperationDescriptor od = (OperationDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor("rendered", op.getOperationName());
/* 1079 */     boolean samePBs = false;
/* 1080 */     if (oldParamBlock == newParamBlock)
/* 1081 */       samePBs = true; 
/* 1083 */     Vector oldSources = oldParamBlock.getSources();
/* 1084 */     oldParamBlock.removeSources();
/* 1085 */     Vector oldReplacedSources = JAIRMIUtil.replaceIdWithSources(oldSources, nodes, op.getOperationName(), op.getRenderingHints());
/* 1090 */     oldParamBlock.setSources(oldReplacedSources);
/* 1092 */     if (samePBs) {
/* 1093 */       newParamBlock = oldParamBlock;
/*      */     } else {
/* 1095 */       Vector newSources = newParamBlock.getSources();
/* 1096 */       newParamBlock.removeSources();
/* 1097 */       Vector newReplacedSources = JAIRMIUtil.replaceIdWithSources(newSources, nodes, op.getOperationName(), op.getRenderingHints());
/* 1103 */       newParamBlock.setSources(newReplacedSources);
/*      */     } 
/* 1106 */     Object invalidRegion = od.getInvalidRegion("rendered", oldParamBlock, oldHints, newParamBlock, newHints, (OperationNode)op);
/* 1113 */     SerializableState shapeState = SerializerFactory.getState(invalidRegion, null);
/* 1116 */     return shapeState;
/*      */   }
/*      */   
/*      */   public Rectangle mapSourceRect(Long id, Rectangle sourceRect, int sourceIndex) throws RemoteException {
/* 1137 */     RenderedOp op = (RenderedOp)nodes.get(id);
/* 1138 */     OpImage rendering = (OpImage)op.getRendering();
/* 1139 */     return rendering.mapSourceRect(sourceRect, sourceIndex);
/*      */   }
/*      */   
/*      */   public Rectangle mapDestRect(Long id, Rectangle destRect, int sourceIndex) throws RemoteException {
/* 1157 */     RenderedOp op = (RenderedOp)nodes.get(id);
/* 1158 */     OpImage rendering = (OpImage)op.getRendering();
/* 1159 */     return rendering.mapDestRect(destRect, sourceIndex);
/*      */   }
/*      */   
/*      */   public synchronized Long handleEvent(Long renderedOpID, String propName, Object oldValue, Object newValue) throws RemoteException {
/* 1169 */     RenderedOp op = (RenderedOp)nodes.get(renderedOpID);
/* 1170 */     PlanarImage rendering = op.getRendering();
/* 1173 */     Long id = getRemoteID();
/* 1175 */     nodes.put(id, rendering);
/* 1178 */     setServerNegotiatedValues(id, (NegotiableCapabilitySet)negotiated.get(renderedOpID));
/* 1186 */     if (propName.equals("operationname")) {
/* 1188 */       op.setOperationName((String)newValue);
/* 1190 */     } else if (propName.equals("parameterblock")) {
/* 1192 */       ParameterBlock newPB = (ParameterBlock)newValue;
/* 1193 */       Vector newSrcs = newPB.getSources();
/* 1194 */       newPB.removeSources();
/* 1196 */       JAIRMIUtil.checkServerParameters(newPB, nodes);
/* 1198 */       Vector replacedSources = JAIRMIUtil.replaceIdWithSources(newSrcs, nodes, op.getOperationName(), op.getRenderingHints());
/* 1203 */       newPB.setSources(replacedSources);
/* 1205 */       op.setParameterBlock(newPB);
/* 1208 */       Vector newSources = newPB.getSources();
/* 1209 */       if (newSources != null && newSources.size() > 0) {
/* 1210 */         Iterator it = newSources.iterator();
/* 1211 */         while (it.hasNext()) {
/* 1212 */           Object src = it.next();
/* 1213 */           if (src instanceof PlanarImage) {
/* 1214 */             ((PlanarImage)src).removeSinks();
/*      */             continue;
/*      */           } 
/* 1215 */           if (src instanceof CollectionImage)
/* 1216 */             ((CollectionImage)src).removeSinks(); 
/*      */         } 
/*      */       } 
/* 1221 */     } else if (propName.equals("sources")) {
/* 1223 */       Vector replacedSources = JAIRMIUtil.replaceIdWithSources((Vector)newValue, nodes, op.getOperationName(), op.getRenderingHints());
/* 1228 */       op.setSources(replacedSources);
/* 1231 */       if (replacedSources != null && replacedSources.size() > 0) {
/* 1232 */         Iterator it = replacedSources.iterator();
/* 1233 */         while (it.hasNext()) {
/* 1234 */           Object src = it.next();
/* 1235 */           if (src instanceof PlanarImage) {
/* 1236 */             ((PlanarImage)src).removeSinks();
/*      */             continue;
/*      */           } 
/* 1237 */           if (src instanceof CollectionImage)
/* 1238 */             ((CollectionImage)src).removeSinks(); 
/*      */         } 
/*      */       } 
/* 1244 */     } else if (propName.equals("parameters")) {
/* 1246 */       Vector parameters = (Vector)newValue;
/* 1247 */       JAIRMIUtil.checkServerParameters(parameters, nodes);
/* 1248 */       op.setParameters(parameters);
/* 1250 */     } else if (propName.equals("renderinghints")) {
/* 1252 */       SerializableState newState = (SerializableState)newValue;
/* 1253 */       op.setRenderingHints((RenderingHints)newState.getObject());
/*      */     } 
/* 1256 */     return id;
/*      */   }
/*      */   
/*      */   public synchronized Long handleEvent(Long renderedOpID, int srcIndex, SerializableState srcInvalidRegion, Object oldRendering) throws RemoteException {
/* 1269 */     RenderedOp op = (RenderedOp)nodes.get(renderedOpID);
/* 1270 */     PlanarImage rendering = op.getRendering();
/* 1273 */     Long id = getRemoteID();
/* 1275 */     nodes.put(id, rendering);
/* 1278 */     setServerNegotiatedValues(id, (NegotiableCapabilitySet)negotiated.get(renderedOpID));
/* 1281 */     PlanarImage oldSrcRendering = null, newSrcRendering = null;
/* 1282 */     String serverNodeDesc = null;
/* 1283 */     Object src = null;
/* 1285 */     if (oldRendering instanceof String) {
/* 1287 */       serverNodeDesc = (String)oldRendering;
/* 1288 */       int index = serverNodeDesc.indexOf("::");
/* 1289 */       boolean diffServer = (index != -1);
/* 1291 */       if (diffServer) {
/* 1294 */         RMIServerProxy rMIServerProxy = new RMIServerProxy(serverNodeDesc, op.getOperationName(), op.getRenderingHints());
/*      */       } else {
/* 1299 */         src = nodes.get(Long.valueOf(serverNodeDesc));
/* 1301 */         if (src instanceof RenderedOp) {
/* 1302 */           oldSrcRendering = ((RenderedOp)src).getRendering();
/*      */         } else {
/* 1304 */           oldSrcRendering = PlanarImage.wrapRenderedImage((RenderedImage)src);
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1310 */       oldSrcRendering = PlanarImage.wrapRenderedImage((RenderedImage)oldRendering);
/*      */     } 
/* 1314 */     Object srcObj = op.getSource(srcIndex);
/* 1315 */     if (srcObj instanceof RenderedOp) {
/* 1316 */       newSrcRendering = ((RenderedOp)srcObj).getRendering();
/* 1317 */     } else if (srcObj instanceof RenderedImage) {
/* 1318 */       newSrcRendering = PlanarImage.wrapRenderedImage((RenderedImage)srcObj);
/*      */     } 
/* 1322 */     Shape invalidRegion = (Shape)srcInvalidRegion.getObject();
/* 1324 */     RenderingChangeEvent rcEvent = new RenderingChangeEvent((RenderedOp)op.getSource(srcIndex), oldSrcRendering, newSrcRendering, invalidRegion);
/* 1329 */     op.propertyChange((PropertyChangeEvent)rcEvent);
/* 1331 */     return id;
/*      */   }
/*      */   
/*      */   public synchronized NegotiableCapabilitySet getServerCapabilities() {
/* 1340 */     OperationRegistry registry = JAI.getDefaultInstance().getOperationRegistry();
/* 1346 */     String modeName = "tileEncoder";
/* 1347 */     String[] descriptorNames = registry.getDescriptorNames(modeName);
/* 1348 */     TileEncoderFactory tef = null;
/* 1351 */     NegotiableCapabilitySet capabilities = new NegotiableCapabilitySet(false);
/* 1355 */     for (int i = 0; i < descriptorNames.length; i++) {
/* 1357 */       Iterator it = registry.getFactoryIterator(modeName, descriptorNames[i]);
/* 1358 */       while (it.hasNext()) {
/* 1359 */         tef = it.next();
/* 1360 */         capabilities.add(tef.getEncodeCapability());
/*      */       } 
/*      */     } 
/* 1364 */     return capabilities;
/*      */   }
/*      */   
/*      */   public void setServerNegotiatedValues(Long id, NegotiableCapabilitySet negotiatedValues) throws RemoteException {
/* 1377 */     if (negotiatedValues != null) {
/* 1378 */       negotiated.put(id, negotiatedValues);
/*      */     } else {
/* 1380 */       negotiated.remove(id);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 1404 */     if (System.getSecurityManager() == null)
/* 1405 */       System.setSecurityManager(new RMISecurityManager()); 
/* 1409 */     Iterator spiIter = Service.providers(JAIServerConfigurationSpi.class);
/* 1410 */     JAI jai = JAI.getDefaultInstance();
/* 1412 */     while (spiIter.hasNext()) {
/* 1414 */       JAIServerConfigurationSpi serverSpi = spiIter.next();
/* 1416 */       serverSpi.updateServer(jai);
/*      */     } 
/* 1420 */     String host = null;
/* 1421 */     int rmiRegistryPort = 1099;
/* 1422 */     int serverport = 0;
/* 1424 */     if (args.length != 0)
/* 1428 */       for (int i = 0; i < args.length; i++) {
/* 1430 */         if (args[i].equalsIgnoreCase("-help")) {
/* 1432 */           System.out.println("Usage: java -Djava.rmi.server.codebase=file:$JAI/lib/jai.jar \\");
/* 1433 */           System.out.println("-Djava.rmi.server.useCodebaseOnly=false \\");
/* 1434 */           System.out.println("-Djava.security.policy=file:`pwd`/policy \\");
/* 1435 */           System.out.println("com.sun.media.jai.rmi.JAIRMIImageServer \\");
/* 1436 */           System.out.println("\nwhere options are:");
/* 1437 */           System.out.println("\t-host <string> The server name or server IP address");
/* 1438 */           System.out.println("\t-port <integer> The port that rmiregistry is running on");
/* 1439 */           System.out.println("\t-rmiRegistryPort <integer> Same as -port option");
/* 1440 */           System.out.println("\t-serverPort <integer> The port that the server should listen on, for connections from clients");
/* 1441 */           System.out.println("\t-cacheMemCapacity <long> The memory capacity in bytes.");
/* 1442 */           System.out.println("\t-cacheMemThreshold <float> The memory threshold, which is the fractional amount of cache memory to retain during tile removal");
/* 1443 */           System.out.println("\t-disableDefaultCache Disable use of default tile cache. Tiles are not stored.");
/* 1444 */           System.out.println("\t-schedulerParallelism <integer> The degree of parallelism of the default TileScheduler");
/* 1445 */           System.out.println("\t-schedulerPrefetchParallelism <integer> The degree of parallelism of the default TileScheduler for tile prefetching");
/* 1446 */           System.out.println("\t-schedulerPriority <integer> The priority of tile scheduling for the default TileScheduler");
/* 1447 */           System.out.println("\t-schedulerPrefetchPriority <integer> The priority of tile prefetch scheduling for the default TileScheduler");
/* 1448 */           System.out.println("\t-defaultTileSize <integer>x<integer> The default tile dimensions in the form <xSize>x<ySize>");
/* 1449 */           System.out.println("\t-defaultRenderingSize <integer>x<integer> The default size to render a RenderableImage to, in the form <xSize>x<ySize>");
/* 1450 */           System.out.println("\t-serializeDeepCopy <boolean> Whether a deep copy of the image data should be used when serializing images");
/* 1451 */           System.out.println("\t-tileCodecFormat <string> The default format to be used for tile serialization via TileCodecs");
/* 1452 */           System.out.println("\t-retryInterval <integer> The retry interval value to be used for dealing with network errors during remote imaging");
/* 1453 */           System.out.println("\t-numRetries <integer> The number of retries to be used for dealing with network errors during remote imaging");
/* 1455 */         } else if (args[i].equalsIgnoreCase("-host")) {
/* 1457 */           host = args[++i];
/* 1459 */         } else if (args[i].equalsIgnoreCase("-port") || args[i].equalsIgnoreCase("-rmiRegistryPort")) {
/* 1462 */           rmiRegistryPort = Integer.parseInt(args[++i]);
/* 1464 */         } else if (args[i].equalsIgnoreCase("-serverport")) {
/* 1466 */           serverport = Integer.parseInt(args[++i]);
/* 1468 */         } else if (args[i].equalsIgnoreCase("-cacheMemCapacity")) {
/* 1470 */           jai.getTileCache().setMemoryCapacity(Long.parseLong(args[++i]));
/* 1473 */         } else if (args[i].equalsIgnoreCase("-cacheMemThreshold")) {
/* 1475 */           jai.getTileCache().setMemoryThreshold(Float.parseFloat(args[++i]));
/* 1478 */         } else if (args[i].equalsIgnoreCase("-disableDefaultCache")) {
/* 1480 */           JAI.disableDefaultTileCache();
/* 1482 */         } else if (args[i].equalsIgnoreCase("-schedulerParallelism")) {
/* 1484 */           jai.getTileScheduler().setParallelism(Integer.parseInt(args[++i]));
/* 1487 */         } else if (args[i].equalsIgnoreCase("-schedulerPrefetchParallelism")) {
/* 1489 */           jai.getTileScheduler().setPrefetchParallelism(Integer.parseInt(args[++i]));
/* 1492 */         } else if (args[i].equalsIgnoreCase("-schedulerPriority")) {
/* 1494 */           jai.getTileScheduler().setPriority(Integer.parseInt(args[++i]));
/* 1497 */         } else if (args[i].equalsIgnoreCase("-schedulerPrefetchPriority")) {
/* 1499 */           jai.getTileScheduler().setPrefetchPriority(Integer.parseInt(args[++i]));
/* 1502 */         } else if (args[i].equalsIgnoreCase("-defaultTileSize")) {
/* 1504 */           String value = args[++i].toLowerCase();
/* 1505 */           int xpos = value.indexOf("x");
/* 1506 */           int xSize = Integer.parseInt(value.substring(0, xpos));
/* 1507 */           int ySize = Integer.parseInt(value.substring(xpos + 1));
/* 1509 */           JAI.setDefaultTileSize(new Dimension(xSize, ySize));
/* 1511 */         } else if (args[i].equalsIgnoreCase("-defaultRenderingSize")) {
/* 1513 */           String value = args[++i].toLowerCase();
/* 1514 */           int xpos = value.indexOf("x");
/* 1515 */           int xSize = Integer.parseInt(value.substring(0, xpos));
/* 1516 */           int ySize = Integer.parseInt(value.substring(xpos + 1));
/* 1518 */           JAI.setDefaultRenderingSize(new Dimension(xSize, ySize));
/* 1520 */         } else if (args[i].equalsIgnoreCase("-serializeDeepCopy")) {
/* 1522 */           jai.setRenderingHint(JAI.KEY_SERIALIZE_DEEP_COPY, Boolean.valueOf(args[++i]));
/* 1525 */         } else if (args[i].equalsIgnoreCase("-tileCodecFormat")) {
/* 1527 */           jai.setRenderingHint(JAI.KEY_TILE_CODEC_FORMAT, args[++i]);
/* 1529 */         } else if (args[i].equalsIgnoreCase("-retryInterval")) {
/* 1531 */           jai.setRenderingHint(JAI.KEY_RETRY_INTERVAL, Integer.valueOf(args[++i]));
/* 1534 */         } else if (args[i].equalsIgnoreCase("-numRetries")) {
/* 1536 */           jai.setRenderingHint(JAI.KEY_NUM_RETRIES, Integer.valueOf(args[++i]));
/*      */         } 
/*      */       }  
/* 1543 */     if (host == null)
/*      */       try {
/* 1545 */         host = InetAddress.getLocalHost().getHostAddress();
/* 1546 */       } catch (UnknownHostException e) {
/* 1547 */         String message = JaiI18N.getString("RMIImageImpl1");
/* 1548 */         sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/*      */       }  
/* 1558 */     System.out.println(JaiI18N.getString("RMIImageImpl3") + " " + host + ":" + rmiRegistryPort);
/*      */     try {
/* 1562 */       JAIRMIImageServer im = new JAIRMIImageServer(serverport);
/* 1563 */       String serverName = new String("rmi://" + host + ":" + rmiRegistryPort + "/" + "JAIRMIRemoteServer1.1");
/* 1567 */       System.out.println(JaiI18N.getString("RMIImageImpl4") + " \"" + serverName + "\".");
/* 1569 */       Naming.rebind(serverName, im);
/* 1570 */       System.out.println(JaiI18N.getString("RMIImageImpl5"));
/* 1571 */     } catch (Exception e) {
/* 1572 */       String message = JaiI18N.getString("RMIImageImpl1");
/* 1573 */       sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void sendExceptionToListener(String message, Exception e) {
/* 1584 */     ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 1586 */     listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), JAIRMIImageServer.class, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\JAIRMIImageServer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */