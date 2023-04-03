/*      */ package javax.media.jai.remote;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.CollectionOp;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.OperationNode;
/*      */ import javax.media.jai.OperationRegistry;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.PropertyChangeEventJAI;
/*      */ import javax.media.jai.RegistryMode;
/*      */ import javax.media.jai.RenderedOp;
/*      */ import javax.media.jai.RenderingChangeEvent;
/*      */ import javax.media.jai.TileCache;
/*      */ import javax.media.jai.registry.RemoteRIFRegistry;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class RemoteRenderedOp extends RenderedOp implements RemoteRenderedImage {
/*      */   protected String protocolName;
/*      */   
/*      */   protected String serverName;
/*      */   
/*      */   private NegotiableCapabilitySet negotiated;
/*      */   
/*      */   private transient RenderingHints oldHints;
/*      */   
/*  173 */   private static Set nodeEventNames = null;
/*      */   
/*      */   static {
/*  176 */     nodeEventNames = new HashSet();
/*  177 */     nodeEventNames.add("protocolname");
/*  178 */     nodeEventNames.add("servername");
/*  179 */     nodeEventNames.add("protocolandservername");
/*  180 */     nodeEventNames.add("operationname");
/*  181 */     nodeEventNames.add("operationregistry");
/*  182 */     nodeEventNames.add("parameterblock");
/*  183 */     nodeEventNames.add("sources");
/*  184 */     nodeEventNames.add("parameters");
/*  185 */     nodeEventNames.add("renderinghints");
/*      */   }
/*      */   
/*      */   public RemoteRenderedOp(String protocolName, String serverName, String opName, ParameterBlock pb, RenderingHints hints) {
/*  225 */     this((OperationRegistry)null, protocolName, serverName, opName, pb, hints);
/*      */   }
/*      */   
/*      */   public RemoteRenderedOp(OperationRegistry registry, String protocolName, String serverName, String opName, ParameterBlock pb, RenderingHints hints) {
/*  271 */     super(registry, opName, pb, hints);
/*  273 */     if (protocolName == null)
/*  274 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  276 */     this.protocolName = protocolName;
/*  277 */     this.serverName = serverName;
/*  284 */     addPropertyChangeListener("ServerName", (PropertyChangeListener)this);
/*  285 */     addPropertyChangeListener("ProtocolName", (PropertyChangeListener)this);
/*  286 */     addPropertyChangeListener("ProtocolAndServerName", (PropertyChangeListener)this);
/*      */   }
/*      */   
/*      */   public String getServerName() {
/*  293 */     return this.serverName;
/*      */   }
/*      */   
/*      */   public void setServerName(String serverName) {
/*  312 */     if (serverName == null)
/*  313 */       throw new IllegalArgumentException(JaiI18N.getString("Generic2")); 
/*  315 */     if (serverName.equalsIgnoreCase(this.serverName))
/*      */       return; 
/*  317 */     String oldServerName = this.serverName;
/*  318 */     this.serverName = serverName;
/*  319 */     fireEvent("ServerName", oldServerName, serverName);
/*  320 */     this.nodeSupport.resetPropertyEnvironment(false);
/*      */   }
/*      */   
/*      */   public String getProtocolName() {
/*  328 */     return this.protocolName;
/*      */   }
/*      */   
/*      */   public void setProtocolName(String protocolName) {
/*  353 */     if (protocolName == null)
/*  354 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  356 */     if (protocolName.equalsIgnoreCase(this.protocolName))
/*      */       return; 
/*  358 */     String oldProtocolName = this.protocolName;
/*  359 */     this.protocolName = protocolName;
/*  360 */     fireEvent("ProtocolName", oldProtocolName, protocolName);
/*  361 */     this.nodeSupport.resetPropertyEnvironment(false);
/*      */   }
/*      */   
/*      */   public void setProtocolAndServerNames(String protocolName, String serverName) {
/*  393 */     if (serverName == null)
/*  394 */       throw new IllegalArgumentException(JaiI18N.getString("Generic2")); 
/*  396 */     if (protocolName == null)
/*  397 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  399 */     boolean protocolNotChanged = protocolName.equalsIgnoreCase(this.protocolName);
/*  401 */     boolean serverNotChanged = serverName.equalsIgnoreCase(this.serverName);
/*  404 */     if (protocolNotChanged) {
/*  405 */       if (serverNotChanged)
/*      */         return; 
/*  410 */       setServerName(serverName);
/*      */       return;
/*      */     } 
/*  414 */     if (serverNotChanged) {
/*  416 */       setProtocolName(protocolName);
/*      */       return;
/*      */     } 
/*  421 */     String oldProtocolName = this.protocolName;
/*  422 */     String oldServerName = this.serverName;
/*  423 */     this.protocolName = protocolName;
/*  424 */     this.serverName = serverName;
/*  427 */     fireEvent("ProtocolAndServerName", new String[] { oldProtocolName, oldServerName }, new String[] { protocolName, serverName });
/*  430 */     this.nodeSupport.resetPropertyEnvironment(false);
/*      */   }
/*      */   
/*      */   public String getRegistryModeName() {
/*  440 */     return RegistryMode.getMode("remoteRendered").getName();
/*      */   }
/*      */   
/*      */   protected synchronized PlanarImage createInstance(boolean isNodeRendered) {
/*  449 */     ParameterBlock pb = new ParameterBlock();
/*  450 */     pb.setParameters(getParameters());
/*  452 */     int numSources = getNumSources();
/*  454 */     for (int i = 0; i < numSources; i++) {
/*  456 */       Object source = getNodeSource(i);
/*  457 */       Object ai = null;
/*  458 */       if (source instanceof RenderedOp) {
/*  460 */         RenderedOp src = (RenderedOp)source;
/*  461 */         ai = isNodeRendered ? src.getRendering() : src.createInstance();
/*  465 */       } else if (source instanceof RenderedImage || source instanceof java.util.Collection) {
/*  468 */         ai = source;
/*  469 */       } else if (source instanceof CollectionOp) {
/*  470 */         ai = ((CollectionOp)source).getCollection();
/*      */       } else {
/*  473 */         ai = source;
/*      */       } 
/*  475 */       pb.addSource(ai);
/*      */     } 
/*  478 */     RemoteRenderedImage instance = RemoteRIFRegistry.create(this.nodeSupport.getRegistry(), this.protocolName, this.serverName, this.nodeSupport.getOperationName(), pb, this.nodeSupport.getRenderingHints());
/*  487 */     if (instance == null)
/*  488 */       throw new ImagingException(JaiI18N.getString("RemoteRenderedOp2")); 
/*  492 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/*  493 */     this.oldHints = (rh == null) ? null : (RenderingHints)rh.clone();
/*  496 */     return PlanarImage.wrapRenderedImage(instance);
/*      */   }
/*      */   
/*      */   public synchronized void propertyChange(PropertyChangeEvent evt) {
/*  519 */     Object evtSrc = evt.getSource();
/*  520 */     Vector nodeSources = this.nodeSupport.getParameterBlock().getSources();
/*  524 */     String propName = evt.getPropertyName().toLowerCase(Locale.ENGLISH);
/*  526 */     if (this.theImage != null && ((evt instanceof PropertyChangeEventJAI && evtSrc == this && !(evt instanceof javax.media.jai.PropertySourceChangeEvent) && nodeEventNames.contains(propName)) || ((evt instanceof RenderingChangeEvent || evt instanceof javax.media.jai.CollectionChangeEvent || (evt instanceof PropertyChangeEventJAI && evtSrc instanceof RenderedImage && propName.equals("invalidregion"))) && nodeSources.contains(evtSrc)))) {
/*  539 */       PlanarImage theOldImage = this.theImage;
/*  542 */       boolean shouldFireEvent = false;
/*  545 */       Shape invalidRegion = null;
/*  547 */       if (evtSrc == this && (propName.equals("operationregistry") || propName.equals("protocolname") || propName.equals("protocolandservername"))) {
/*  553 */         shouldFireEvent = true;
/*  554 */         this.theImage = null;
/*  556 */       } else if (evt instanceof RenderingChangeEvent || (evtSrc instanceof RenderedImage && propName.equals("invalidregion"))) {
/*  561 */         shouldFireEvent = true;
/*  562 */         Shape srcInvalidRegion = null;
/*  564 */         if (evt instanceof RenderingChangeEvent) {
/*  568 */           RenderingChangeEvent rcEvent = (RenderingChangeEvent)evt;
/*  571 */           srcInvalidRegion = rcEvent.getInvalidRegion();
/*  574 */           if (srcInvalidRegion == null)
/*  575 */             srcInvalidRegion = ((PlanarImage)rcEvent.getOldValue()).getBounds(); 
/*      */         } else {
/*  581 */           srcInvalidRegion = (Shape)evt.getNewValue();
/*  584 */           if (srcInvalidRegion == null) {
/*  585 */             RenderedImage rSrc = (RenderedImage)evtSrc;
/*  586 */             srcInvalidRegion = new Rectangle(rSrc.getMinX(), rSrc.getMinY(), rSrc.getWidth(), rSrc.getHeight());
/*      */           } 
/*      */         } 
/*  594 */         if (!(this.theImage instanceof PlanarImageServerProxy)) {
/*  597 */           this.theImage = null;
/*      */         } else {
/*  602 */           PlanarImageServerProxy oldPISP = (PlanarImageServerProxy)this.theImage;
/*  606 */           Rectangle srcInvalidBounds = srcInvalidRegion.getBounds();
/*  611 */           if (srcInvalidBounds.isEmpty()) {
/*  612 */             int x = oldPISP.tileXToX(oldPISP.getMinTileX());
/*  613 */             int y = oldPISP.tileYToY(oldPISP.getMinTileY());
/*  614 */             int w = oldPISP.getNumXTiles() * oldPISP.getTileWidth();
/*  616 */             int h = oldPISP.getNumYTiles() * oldPISP.getTileHeight();
/*  618 */             Rectangle tileBounds = new Rectangle(x, y, w, h);
/*  619 */             Rectangle imageBounds = oldPISP.getBounds();
/*  620 */             if (!tileBounds.equals(imageBounds)) {
/*  621 */               Area tmpArea = new Area(tileBounds);
/*  622 */               tmpArea.subtract(new Area(imageBounds));
/*  623 */               srcInvalidRegion = tmpArea;
/*  624 */               srcInvalidBounds = srcInvalidRegion.getBounds();
/*      */             } 
/*      */           } 
/*  630 */           boolean saveAllTiles = false;
/*  631 */           ArrayList validTiles = null;
/*  632 */           if (srcInvalidBounds.isEmpty()) {
/*  633 */             invalidRegion = srcInvalidRegion;
/*  634 */             saveAllTiles = true;
/*      */           } else {
/*  639 */             int idx = nodeSources.indexOf(evtSrc);
/*  642 */             Rectangle dstRegionBounds = oldPISP.mapSourceRect(srcInvalidBounds, idx);
/*  645 */             if (dstRegionBounds == null)
/*  646 */               dstRegionBounds = oldPISP.getBounds(); 
/*  650 */             Point[] indices = getTileIndices(dstRegionBounds);
/*  651 */             int numIndices = (indices != null) ? indices.length : 0;
/*  652 */             GeneralPath gp = null;
/*  654 */             for (int i = 0; i < numIndices; i++) {
/*  655 */               if (i % 1000 == 0 && gp != null)
/*  656 */                 gp = new GeneralPath(new Area(gp)); 
/*  658 */               Rectangle dstRect = getTileRect((indices[i]).x, (indices[i]).y);
/*  660 */               Rectangle srcRect = oldPISP.mapDestRect(dstRect, idx);
/*  662 */               if (srcRect == null) {
/*  663 */                 gp = null;
/*      */                 break;
/*      */               } 
/*  666 */               if (srcInvalidRegion.intersects(srcRect)) {
/*  667 */                 if (gp == null) {
/*  668 */                   gp = new GeneralPath(dstRect);
/*      */                 } else {
/*  670 */                   gp.append(dstRect, false);
/*      */                 } 
/*      */               } else {
/*  673 */                 if (validTiles == null)
/*  674 */                   validTiles = new ArrayList(); 
/*  676 */                 validTiles.add(indices[i]);
/*      */               } 
/*      */             } 
/*  680 */             invalidRegion = (gp == null) ? null : new Area(gp);
/*      */           } 
/*  684 */           TileCache oldCache = oldPISP.getTileCache();
/*  685 */           this.theImage = null;
/*  689 */           if (oldCache != null && (saveAllTiles || validTiles != null)) {
/*  693 */             newEventRendering(this.protocolName, oldPISP, (PropertyChangeEventJAI)evt);
/*  699 */             if (this.theImage instanceof PlanarImageServerProxy && ((PlanarImageServerProxy)this.theImage).getTileCache() != null) {
/*  702 */               PlanarImageServerProxy newPISP = (PlanarImageServerProxy)this.theImage;
/*  704 */               TileCache newCache = newPISP.getTileCache();
/*  706 */               Object tileCacheMetric = newPISP.getTileCacheMetric();
/*  709 */               if (saveAllTiles) {
/*  710 */                 Raster[] tiles = oldCache.getTiles(oldPISP);
/*  711 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/*  713 */                 for (int i = 0; i < numTiles; i++) {
/*  714 */                   Raster tile = tiles[i];
/*  715 */                   int tx = newPISP.XToTileX(tile.getMinX());
/*  716 */                   int ty = newPISP.YToTileY(tile.getMinY());
/*  717 */                   newCache.add(newPISP, tx, ty, tile, tileCacheMetric);
/*      */                 } 
/*      */               } else {
/*  722 */                 int numValidTiles = validTiles.size();
/*  723 */                 for (int i = 0; i < numValidTiles; i++) {
/*  724 */                   Point tileIndex = validTiles.get(i);
/*  725 */                   Raster tile = oldCache.getTile(oldPISP, tileIndex.x, tileIndex.y);
/*  729 */                   if (tile != null)
/*  730 */                     newCache.add(newPISP, tileIndex.x, tileIndex.y, tile, tileCacheMetric); 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/*  742 */         ParameterBlock oldPB = null;
/*  743 */         ParameterBlock newPB = null;
/*  744 */         String oldServerName = this.serverName;
/*  745 */         String newServerName = this.serverName;
/*  747 */         boolean checkInvalidRegion = false;
/*  749 */         if (propName.equals("operationname")) {
/*  751 */           if (this.theImage instanceof PlanarImageServerProxy) {
/*  752 */             newEventRendering(this.protocolName, (PlanarImageServerProxy)this.theImage, (PropertyChangeEventJAI)evt);
/*      */           } else {
/*  756 */             this.theImage = null;
/*  757 */             createRendering();
/*      */           } 
/*  763 */           shouldFireEvent = true;
/*  768 */         } else if (propName.equals("parameterblock")) {
/*  769 */           oldPB = (ParameterBlock)evt.getOldValue();
/*  770 */           newPB = (ParameterBlock)evt.getNewValue();
/*  771 */           checkInvalidRegion = true;
/*  772 */         } else if (propName.equals("sources")) {
/*  774 */           Vector params = this.nodeSupport.getParameterBlock().getParameters();
/*  776 */           oldPB = new ParameterBlock((Vector)evt.getOldValue(), params);
/*  778 */           newPB = new ParameterBlock((Vector)evt.getNewValue(), params);
/*  780 */           checkInvalidRegion = true;
/*  781 */         } else if (propName.equals("parameters")) {
/*  783 */           oldPB = new ParameterBlock(nodeSources, (Vector)evt.getOldValue());
/*  785 */           newPB = new ParameterBlock(nodeSources, (Vector)evt.getNewValue());
/*  787 */           checkInvalidRegion = true;
/*  788 */         } else if (propName.equals("renderinghints")) {
/*  789 */           oldPB = newPB = this.nodeSupport.getParameterBlock();
/*  790 */           checkInvalidRegion = true;
/*  791 */         } else if (propName.equals("servername")) {
/*  792 */           oldPB = newPB = this.nodeSupport.getParameterBlock();
/*  793 */           oldServerName = (String)evt.getOldValue();
/*  794 */           newServerName = (String)evt.getNewValue();
/*  795 */           checkInvalidRegion = true;
/*  796 */         } else if (evt instanceof javax.media.jai.CollectionChangeEvent) {
/*  799 */           int collectionIndex = nodeSources.indexOf(evtSrc);
/*  800 */           Vector oldSources = (Vector)nodeSources.clone();
/*  801 */           Vector newSources = (Vector)nodeSources.clone();
/*  802 */           oldSources.set(collectionIndex, evt.getOldValue());
/*  803 */           newSources.set(collectionIndex, evt.getNewValue());
/*  805 */           Vector params = this.nodeSupport.getParameterBlock().getParameters();
/*  808 */           oldPB = new ParameterBlock(oldSources, params);
/*  809 */           newPB = new ParameterBlock(newSources, params);
/*  811 */           checkInvalidRegion = true;
/*      */         } 
/*  814 */         if (checkInvalidRegion) {
/*  816 */           shouldFireEvent = true;
/*  819 */           OperationRegistry registry = this.nodeSupport.getRegistry();
/*  820 */           RemoteDescriptor odesc = (RemoteDescriptor)registry.getDescriptor(RemoteDescriptor.class, this.protocolName);
/*  826 */           oldPB = ImageUtil.evaluateParameters(oldPB);
/*  827 */           newPB = ImageUtil.evaluateParameters(newPB);
/*  830 */           invalidRegion = (Shape)odesc.getInvalidRegion("rendered", oldServerName, oldPB, this.oldHints, newServerName, newPB, this.nodeSupport.getRenderingHints(), (OperationNode)this);
/*  840 */           if (invalidRegion == null || !(this.theImage instanceof PlanarImageServerProxy)) {
/*  843 */             this.theImage = null;
/*      */           } else {
/*  848 */             PlanarImageServerProxy oldRendering = (PlanarImageServerProxy)this.theImage;
/*  851 */             newEventRendering(this.protocolName, oldRendering, (PropertyChangeEventJAI)evt);
/*  856 */             if (this.theImage instanceof PlanarImageServerProxy && oldRendering.getTileCache() != null && ((PlanarImageServerProxy)this.theImage).getTileCache() != null) {
/*  860 */               PlanarImageServerProxy newRendering = (PlanarImageServerProxy)this.theImage;
/*  863 */               TileCache oldCache = oldRendering.getTileCache();
/*  864 */               TileCache newCache = newRendering.getTileCache();
/*  866 */               Object tileCacheMetric = newRendering.getTileCacheMetric();
/*  872 */               if (invalidRegion.getBounds().isEmpty()) {
/*  873 */                 int x = oldRendering.tileXToX(oldRendering.getMinTileX());
/*  875 */                 int y = oldRendering.tileYToY(oldRendering.getMinTileY());
/*  877 */                 int w = oldRendering.getNumXTiles() * oldRendering.getTileWidth();
/*  879 */                 int h = oldRendering.getNumYTiles() * oldRendering.getTileHeight();
/*  881 */                 Rectangle tileBounds = new Rectangle(x, y, w, h);
/*  883 */                 Rectangle imageBounds = oldRendering.getBounds();
/*  885 */                 if (!tileBounds.equals(imageBounds)) {
/*  886 */                   Area tmpArea = new Area(tileBounds);
/*  887 */                   tmpArea.subtract(new Area(imageBounds));
/*  888 */                   invalidRegion = tmpArea;
/*      */                 } 
/*      */               } 
/*  892 */               if (invalidRegion.getBounds().isEmpty()) {
/*  895 */                 Raster[] tiles = oldCache.getTiles(oldRendering);
/*  897 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/*  899 */                 for (int i = 0; i < numTiles; i++) {
/*  900 */                   Raster tile = tiles[i];
/*  901 */                   int tx = newRendering.XToTileX(tile.getMinX());
/*  903 */                   int ty = newRendering.YToTileY(tile.getMinY());
/*  905 */                   newCache.add(newRendering, tx, ty, tile, tileCacheMetric);
/*      */                 } 
/*      */               } else {
/*  912 */                 Raster[] tiles = oldCache.getTiles(oldRendering);
/*  914 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/*  916 */                 for (int i = 0; i < numTiles; i++) {
/*  917 */                   Raster tile = tiles[i];
/*  918 */                   Rectangle bounds = tile.getBounds();
/*  919 */                   if (!invalidRegion.intersects(bounds))
/*  920 */                     newCache.add(newRendering, newRendering.XToTileX(bounds.x), newRendering.YToTileY(bounds.y), tile, tileCacheMetric); 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  936 */       if (theOldImage instanceof PlanarImageServerProxy && this.theImage == null) {
/*  938 */         newEventRendering(this.protocolName, (PlanarImageServerProxy)theOldImage, (PropertyChangeEventJAI)evt);
/*      */       } else {
/*  942 */         createRendering();
/*      */       } 
/*  946 */       if (shouldFireEvent) {
/*  950 */         resetProperties(true);
/*  953 */         RenderingChangeEvent rcEvent = new RenderingChangeEvent(this, theOldImage, this.theImage, invalidRegion);
/*  958 */         this.eventManager.firePropertyChange((PropertyChangeEvent)rcEvent);
/*  961 */         Vector sinks = getSinks();
/*  962 */         if (sinks != null) {
/*  963 */           int numSinks = sinks.size();
/*  964 */           for (int i = 0; i < numSinks; i++) {
/*  965 */             Object sink = sinks.get(i);
/*  966 */             if (sink instanceof PropertyChangeListener)
/*  967 */               ((PropertyChangeListener)sink).propertyChange((PropertyChangeEvent)rcEvent); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void newEventRendering(String protocolName, PlanarImageServerProxy oldPISP, PropertyChangeEventJAI event) {
/*  982 */     RemoteRIF rrif = (RemoteRIF)this.nodeSupport.getRegistry().getFactory("remoterendered", protocolName);
/*  984 */     this.theImage = (PlanarImage)rrif.create(oldPISP, (OperationNode)this, event);
/*      */   }
/*      */   
/*      */   private void fireEvent(String propName, Object oldVal, Object newVal) {
/*  991 */     if (this.eventManager != null) {
/*  992 */       Object eventSource = this.eventManager.getPropertyChangeEventSource();
/*  993 */       PropertyChangeEventJAI evt = new PropertyChangeEventJAI(eventSource, propName, oldVal, newVal);
/*  996 */       this.eventManager.firePropertyChange((PropertyChangeEvent)evt);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getRetryInterval() {
/* 1011 */     if (this.theImage != null)
/* 1012 */       return ((RemoteRenderedImage)this.theImage).getRetryInterval(); 
/* 1014 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1015 */     if (rh == null)
/* 1016 */       return 1000; 
/* 1018 */     Integer i = (Integer)rh.get(JAI.KEY_RETRY_INTERVAL);
/* 1019 */     if (i == null)
/* 1020 */       return 1000; 
/* 1022 */     return i.intValue();
/*      */   }
/*      */   
/*      */   public void setRetryInterval(int retryInterval) {
/* 1048 */     if (retryInterval < 0)
/* 1049 */       throw new IllegalArgumentException(JaiI18N.getString("Generic3")); 
/* 1051 */     if (this.theImage != null)
/* 1052 */       ((RemoteRenderedImage)this.theImage).setRetryInterval(retryInterval); 
/* 1055 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1056 */     if (rh == null) {
/* 1057 */       this.nodeSupport.setRenderingHints(new RenderingHints(null));
/* 1058 */       rh = this.nodeSupport.getRenderingHints();
/*      */     } 
/* 1061 */     rh.put(JAI.KEY_RETRY_INTERVAL, new Integer(retryInterval));
/*      */   }
/*      */   
/*      */   public int getNumRetries() {
/* 1075 */     if (this.theImage != null)
/* 1076 */       return ((RemoteRenderedImage)this.theImage).getNumRetries(); 
/* 1078 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1079 */     if (rh == null)
/* 1080 */       return 5; 
/* 1082 */     Integer i = (Integer)rh.get(JAI.KEY_NUM_RETRIES);
/* 1083 */     if (i == null)
/* 1084 */       return 5; 
/* 1086 */     return i.intValue();
/*      */   }
/*      */   
/*      */   public void setNumRetries(int numRetries) {
/* 1112 */     if (numRetries < 0)
/* 1113 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 1116 */     if (this.theImage != null)
/* 1117 */       ((RemoteRenderedImage)this.theImage).setNumRetries(numRetries); 
/* 1120 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1121 */     if (rh == null) {
/* 1122 */       this.nodeSupport.setRenderingHints(new RenderingHints(null));
/* 1123 */       rh = this.nodeSupport.getRenderingHints();
/*      */     } 
/* 1126 */     rh.put(JAI.KEY_NUM_RETRIES, new Integer(numRetries));
/*      */   }
/*      */   
/*      */   public void setNegotiationPreferences(NegotiableCapabilitySet preferences) {
/* 1163 */     if (this.theImage != null)
/* 1164 */       ((RemoteRenderedImage)this.theImage).setNegotiationPreferences(preferences); 
/* 1168 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1170 */     if (preferences != null) {
/* 1171 */       if (rh == null) {
/* 1172 */         this.nodeSupport.setRenderingHints(new RenderingHints(null));
/* 1173 */         rh = this.nodeSupport.getRenderingHints();
/*      */       } 
/* 1176 */       rh.put(JAI.KEY_NEGOTIATION_PREFERENCES, preferences);
/* 1179 */     } else if (rh != null) {
/* 1180 */       rh.remove(JAI.KEY_NEGOTIATION_PREFERENCES);
/*      */     } 
/* 1184 */     this.negotiated = negotiate(preferences);
/*      */   }
/*      */   
/*      */   public NegotiableCapabilitySet getNegotiationPreferences() {
/* 1193 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1194 */     return (rh == null) ? null : (NegotiableCapabilitySet)rh.get(JAI.KEY_NEGOTIATION_PREFERENCES);
/*      */   }
/*      */   
/*      */   private NegotiableCapabilitySet negotiate(NegotiableCapabilitySet prefs) {
/*      */     RemoteImagingException remoteImagingException;
/* 1201 */     OperationRegistry registry = this.nodeSupport.getRegistry();
/* 1203 */     NegotiableCapabilitySet serverCap = null;
/* 1206 */     RemoteDescriptor descriptor = (RemoteDescriptor)registry.getDescriptor(RemoteDescriptor.class, this.protocolName);
/* 1209 */     if (descriptor == null) {
/* 1210 */       Object[] msgArg0 = { new String(this.protocolName) };
/* 1211 */       MessageFormat formatter = new MessageFormat("");
/* 1212 */       formatter.setLocale(Locale.getDefault());
/* 1213 */       formatter.applyPattern(JaiI18N.getString("RemoteJAI16"));
/* 1214 */       throw new ImagingException(formatter.format(msgArg0));
/*      */     } 
/* 1217 */     int count = 0;
/* 1218 */     int numRetries = getNumRetries();
/* 1219 */     int retryInterval = getRetryInterval();
/* 1221 */     Exception rieSave = null;
/* 1222 */     while (count++ < numRetries) {
/*      */       try {
/* 1224 */         serverCap = descriptor.getServerCapabilities(this.serverName);
/*      */         break;
/* 1226 */       } catch (RemoteImagingException rie) {
/* 1228 */         System.err.println(JaiI18N.getString("RemoteJAI24"));
/* 1229 */         remoteImagingException = rie;
/*      */         try {
/* 1232 */           Thread.sleep(retryInterval);
/* 1233 */         } catch (InterruptedException ie) {
/* 1235 */           sendExceptionToListener(JaiI18N.getString("Generic5"), (Exception)new ImagingException(JaiI18N.getString("Generic5"), ie));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1241 */     if (serverCap == null && count > numRetries)
/* 1242 */       sendExceptionToListener(JaiI18N.getString("RemoteJAI18"), (Exception)remoteImagingException); 
/* 1246 */     RemoteRIF rrif = (RemoteRIF)registry.getFactory("remoteRendered", this.protocolName);
/* 1249 */     return RemoteJAI.negotiate(prefs, serverCap, rrif.getClientCapabilities());
/*      */   }
/*      */   
/*      */   public NegotiableCapabilitySet getNegotiatedValues() throws RemoteImagingException {
/* 1270 */     if (this.theImage != null)
/* 1271 */       return ((RemoteRenderedImage)this.theImage).getNegotiatedValues(); 
/* 1273 */     return this.negotiated;
/*      */   }
/*      */   
/*      */   public NegotiableCapability getNegotiatedValue(String category) throws RemoteImagingException {
/* 1295 */     if (this.theImage != null)
/* 1296 */       return ((RemoteRenderedImage)this.theImage).getNegotiatedValue(category); 
/* 1299 */     return (this.negotiated == null) ? null : this.negotiated.getNegotiatedValue(category);
/*      */   }
/*      */   
/*      */   public void setServerNegotiatedValues(NegotiableCapabilitySet negotiatedValues) throws RemoteImagingException {
/* 1318 */     if (this.theImage != null)
/* 1319 */       ((RemoteRenderedImage)this.theImage).setServerNegotiatedValues(negotiatedValues); 
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1325 */     ImagingListener listener = (ImagingListener)getRenderingHints().get(JAI.KEY_IMAGING_LISTENER);
/* 1328 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteRenderedOp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */