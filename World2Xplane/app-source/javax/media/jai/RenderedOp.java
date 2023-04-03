/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import java.awt.Image;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.registry.RIFRegistry;
/*      */ import javax.media.jai.remote.PlanarImageServerProxy;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class RenderedOp extends PlanarImage implements OperationNode, PropertyChangeListener, Serializable {
/*      */   protected OperationNodeSupport nodeSupport;
/*      */   
/*      */   protected transient PropertySource thePropertySource;
/*      */   
/*      */   protected transient PlanarImage theImage;
/*      */   
/*      */   private transient RenderingHints oldHints;
/*      */   
/*      */   private static List synthProps;
/*      */   
/*  331 */   private Hashtable synthProperties = null;
/*      */   
/*  334 */   private static Set nodeEventNames = null;
/*      */   
/*      */   private boolean isDisposed = false;
/*      */   
/*      */   static {
/*  342 */     CaselessStringKey[] propKeys = { new CaselessStringKey("image_width"), new CaselessStringKey("image_height"), new CaselessStringKey("image_min_x_coord"), new CaselessStringKey("image_min_y_coord"), new CaselessStringKey("tile_cache"), new CaselessStringKey("tile_cache_key") };
/*  351 */     synthProps = Arrays.asList(propKeys);
/*  353 */     nodeEventNames = new HashSet();
/*  354 */     nodeEventNames.add("operationname");
/*  355 */     nodeEventNames.add("operationregistry");
/*  356 */     nodeEventNames.add("parameterblock");
/*  357 */     nodeEventNames.add("sources");
/*  358 */     nodeEventNames.add("parameters");
/*  359 */     nodeEventNames.add("renderinghints");
/*      */   }
/*      */   
/*      */   public RenderedOp(OperationRegistry registry, String opName, ParameterBlock pb, RenderingHints hints) {
/*  400 */     super(new ImageLayout(), null, null);
/*  402 */     if (pb == null) {
/*  404 */       pb = new ParameterBlock();
/*      */     } else {
/*  407 */       pb = (ParameterBlock)pb.clone();
/*      */     } 
/*  410 */     if (hints != null)
/*  412 */       hints = (RenderingHints)hints.clone(); 
/*  415 */     this.nodeSupport = new OperationNodeSupport(getRegistryModeName(), opName, registry, pb, hints, this.eventManager);
/*  426 */     addPropertyChangeListener("OperationName", this);
/*  427 */     addPropertyChangeListener("OperationRegistry", this);
/*  428 */     addPropertyChangeListener("ParameterBlock", this);
/*  429 */     addPropertyChangeListener("Sources", this);
/*  430 */     addPropertyChangeListener("Parameters", this);
/*  431 */     addPropertyChangeListener("RenderingHints", this);
/*  434 */     Vector nodeSources = pb.getSources();
/*  435 */     if (nodeSources != null) {
/*  436 */       Iterator it = nodeSources.iterator();
/*  437 */       while (it.hasNext()) {
/*  438 */         Object src = it.next();
/*  439 */         if (src instanceof PlanarImage) {
/*  440 */           ((PlanarImage)src).addSink(this);
/*      */           continue;
/*      */         } 
/*  441 */         if (src instanceof CollectionImage)
/*  442 */           ((CollectionImage)src).addSink(this); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public RenderedOp(String opName, ParameterBlock pb, RenderingHints hints) {
/*  483 */     this((OperationRegistry)null, opName, pb, hints);
/*      */   }
/*      */   
/*      */   private class TCL implements TileComputationListener {
/*      */     RenderedOp node;
/*      */     
/*      */     private final RenderedOp this$0;
/*      */     
/*      */     private TCL(RenderedOp this$0, RenderedOp node) {
/*  495 */       RenderedOp.this = RenderedOp.this;
/*  496 */       this.node = node;
/*      */     }
/*      */     
/*      */     public void tileComputed(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY, Raster tile) {
/*  503 */       if (image == RenderedOp.this.theImage) {
/*  505 */         TileComputationListener[] listeners = RenderedOp.this.getTileComputationListeners();
/*  508 */         if (listeners != null) {
/*  509 */           int numListeners = listeners.length;
/*  511 */           for (int i = 0; i < numListeners; i++)
/*  512 */             listeners[i].tileComputed(this.node, requests, image, tileX, tileY, tile); 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void tileCancelled(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY) {
/*  522 */       if (image == RenderedOp.this.theImage) {
/*  524 */         TileComputationListener[] listeners = RenderedOp.this.getTileComputationListeners();
/*  527 */         if (listeners != null) {
/*  528 */           int numListeners = listeners.length;
/*  530 */           for (int i = 0; i < numListeners; i++)
/*  531 */             listeners[i].tileCancelled(this.node, requests, image, tileX, tileY); 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void tileComputationFailure(Object eventSource, TileRequest[] requests, PlanarImage image, int tileX, int tileY, Throwable situation) {
/*  543 */       if (image == RenderedOp.this.theImage) {
/*  545 */         TileComputationListener[] listeners = RenderedOp.this.getTileComputationListeners();
/*  548 */         if (listeners != null) {
/*  549 */           int numListeners = listeners.length;
/*  551 */           for (int i = 0; i < numListeners; i++)
/*  552 */             listeners[i].tileComputationFailure(this.node, requests, image, tileX, tileY, situation); 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public String getRegistryModeName() {
/*  569 */     return RegistryMode.getMode("rendered").getName();
/*      */   }
/*      */   
/*      */   public synchronized OperationRegistry getRegistry() {
/*  580 */     return this.nodeSupport.getRegistry();
/*      */   }
/*      */   
/*      */   public synchronized void setRegistry(OperationRegistry registry) {
/*  597 */     this.nodeSupport.setRegistry(registry);
/*      */   }
/*      */   
/*      */   public synchronized String getOperationName() {
/*  605 */     return this.nodeSupport.getOperationName();
/*      */   }
/*      */   
/*      */   public synchronized void setOperationName(String opName) {
/*  623 */     this.nodeSupport.setOperationName(opName);
/*      */   }
/*      */   
/*      */   public synchronized ParameterBlock getParameterBlock() {
/*  628 */     return (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/*      */   }
/*      */   
/*      */   public synchronized void setParameterBlock(ParameterBlock pb) {
/*  664 */     Vector nodeSources = this.nodeSupport.getParameterBlock().getSources();
/*  665 */     if (nodeSources != null && nodeSources.size() > 0) {
/*  666 */       Iterator it = nodeSources.iterator();
/*  667 */       while (it.hasNext()) {
/*  668 */         Object src = it.next();
/*  669 */         if (src instanceof PlanarImage) {
/*  670 */           ((PlanarImage)src).removeSink(this);
/*      */           continue;
/*      */         } 
/*  671 */         if (src instanceof CollectionImage)
/*  672 */           ((CollectionImage)src).removeSink(this); 
/*      */       } 
/*      */     } 
/*  677 */     if (pb != null) {
/*  678 */       Vector newSources = pb.getSources();
/*  679 */       if (newSources != null && newSources.size() > 0) {
/*  680 */         Iterator it = newSources.iterator();
/*  681 */         while (it.hasNext()) {
/*  682 */           Object src = it.next();
/*  683 */           if (src instanceof PlanarImage) {
/*  684 */             ((PlanarImage)src).addSink(this);
/*      */             continue;
/*      */           } 
/*  685 */           if (src instanceof CollectionImage)
/*  686 */             ((CollectionImage)src).addSink(this); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  692 */     this.nodeSupport.setParameterBlock((pb == null) ? new ParameterBlock() : (ParameterBlock)pb.clone());
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  702 */     RenderingHints hints = this.nodeSupport.getRenderingHints();
/*  703 */     return (hints == null) ? null : (RenderingHints)hints.clone();
/*      */   }
/*      */   
/*      */   public synchronized void setRenderingHints(RenderingHints hints) {
/*  720 */     if (hints != null)
/*  721 */       hints = (RenderingHints)hints.clone(); 
/*  723 */     this.nodeSupport.setRenderingHints(hints);
/*      */   }
/*      */   
/*      */   public synchronized PlanarImage createInstance() {
/*  770 */     return createInstance(false);
/*      */   }
/*      */   
/*      */   protected synchronized PlanarImage createInstance(boolean isNodeRendered) {
/*  786 */     ParameterBlock pb = new ParameterBlock();
/*  787 */     Vector parameters = this.nodeSupport.getParameterBlock().getParameters();
/*  790 */     pb.setParameters(ImageUtil.evaluateParameters(parameters));
/*  792 */     int numSources = getNumSources();
/*  793 */     for (int i = 0; i < numSources; i++) {
/*  794 */       Object source = getNodeSource(i);
/*  795 */       Object ai = null;
/*  797 */       if (source instanceof RenderedOp) {
/*  798 */         RenderedOp src = (RenderedOp)source;
/*  799 */         ai = isNodeRendered ? src.getRendering() : src.createInstance();
/*  802 */       } else if (source instanceof CollectionOp) {
/*  803 */         ai = ((CollectionOp)source).getCollection();
/*  804 */       } else if (source instanceof RenderedImage || source instanceof java.util.Collection) {
/*  810 */         ai = source;
/*      */       } else {
/*  813 */         ai = source;
/*      */       } 
/*  815 */       pb.addSource(ai);
/*      */     } 
/*  819 */     RenderedImage rendering = RIFRegistry.create(getRegistry(), this.nodeSupport.getOperationName(), pb, this.nodeSupport.getRenderingHints());
/*  826 */     if (rendering == null)
/*  827 */       throw new RuntimeException(JaiI18N.getString("RenderedOp0")); 
/*  843 */     PlanarImage instance = PlanarImage.wrapRenderedImage(rendering);
/*  846 */     this.oldHints = (this.nodeSupport.getRenderingHints() == null) ? null : (RenderingHints)this.nodeSupport.getRenderingHints().clone();
/*  849 */     return instance;
/*      */   }
/*      */   
/*      */   protected synchronized void createRendering() {
/*  866 */     if (this.theImage == null) {
/*  867 */       setImageLayout(new ImageLayout(this.theImage = createInstance(true)));
/*  869 */       if (this.theImage != null)
/*  871 */         this.theImage.addTileComputationListener(new TCL(this)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public PlanarImage getRendering() {
/*  888 */     createRendering();
/*  889 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public PlanarImage getCurrentRendering() {
/*  900 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public PlanarImage getNewRendering() {
/*  934 */     if (this.theImage == null)
/*  935 */       return getRendering(); 
/*  939 */     PlanarImage theOldImage = this.theImage;
/*  942 */     this.theImage = null;
/*  949 */     createRendering();
/*  953 */     resetProperties(true);
/*  956 */     RenderingChangeEvent rcEvent = new RenderingChangeEvent(this, theOldImage, this.theImage, null);
/*  960 */     this.eventManager.firePropertyChange(rcEvent);
/*  963 */     Vector sinks = getSinks();
/*  964 */     if (sinks != null) {
/*  965 */       int numSinks = sinks.size();
/*  966 */       for (int i = 0; i < numSinks; i++) {
/*  967 */         Object sink = sinks.get(i);
/*  968 */         if (sink instanceof PropertyChangeListener)
/*  969 */           ((PropertyChangeListener)sink).propertyChange(rcEvent); 
/*      */       } 
/*      */     } 
/*  974 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public synchronized void propertyChange(PropertyChangeEvent evt) {
/* 1005 */     Object evtSrc = evt.getSource();
/* 1006 */     Vector nodeSources = this.nodeSupport.getParameterBlock().getSources();
/* 1010 */     String propName = evt.getPropertyName().toLowerCase(Locale.ENGLISH);
/* 1012 */     if (this.theImage != null && ((evt instanceof PropertyChangeEventJAI && evtSrc == this && !(evt instanceof PropertySourceChangeEvent) && nodeEventNames.contains(propName)) || ((evt instanceof RenderingChangeEvent || evt instanceof CollectionChangeEvent || (evt instanceof PropertyChangeEventJAI && evtSrc instanceof RenderedImage && propName.equals("invalidregion"))) && nodeSources.contains(evtSrc)))) {
/* 1025 */       PlanarImage theOldImage = this.theImage;
/* 1028 */       boolean fireEvent = false;
/* 1031 */       Shape invalidRegion = null;
/* 1033 */       if (evtSrc == this && (propName.equals("operationname") || propName.equals("operationregistry"))) {
/* 1039 */         fireEvent = true;
/* 1040 */         this.theImage = null;
/* 1042 */       } else if (evt instanceof RenderingChangeEvent || (evtSrc instanceof RenderedImage && propName.equals("invalidregion"))) {
/* 1047 */         fireEvent = true;
/* 1049 */         Shape srcInvalidRegion = null;
/* 1051 */         if (evt instanceof RenderingChangeEvent) {
/* 1053 */           RenderingChangeEvent rcEvent = (RenderingChangeEvent)evt;
/* 1056 */           srcInvalidRegion = rcEvent.getInvalidRegion();
/* 1059 */           if (srcInvalidRegion == null)
/* 1060 */             srcInvalidRegion = ((PlanarImage)rcEvent.getOldValue()).getBounds(); 
/*      */         } else {
/* 1065 */           srcInvalidRegion = (Shape)evt.getNewValue();
/* 1068 */           if (srcInvalidRegion == null) {
/* 1069 */             RenderedImage rSrc = (RenderedImage)evtSrc;
/* 1070 */             srcInvalidRegion = new Rectangle(rSrc.getMinX(), rSrc.getMinY(), rSrc.getWidth(), rSrc.getHeight());
/*      */           } 
/*      */         } 
/* 1077 */         if (!(this.theImage instanceof OpImage)) {
/* 1080 */           this.theImage = null;
/*      */         } else {
/* 1084 */           OpImage oldOpImage = (OpImage)this.theImage;
/* 1087 */           Rectangle srcInvalidBounds = srcInvalidRegion.getBounds();
/* 1093 */           if (srcInvalidBounds.isEmpty()) {
/* 1094 */             int x = oldOpImage.tileXToX(oldOpImage.getMinTileX());
/* 1095 */             int y = oldOpImage.tileYToY(oldOpImage.getMinTileY());
/* 1096 */             int w = oldOpImage.getNumXTiles() * oldOpImage.getTileWidth();
/* 1098 */             int h = oldOpImage.getNumYTiles() * oldOpImage.getTileHeight();
/* 1100 */             Rectangle tileBounds = new Rectangle(x, y, w, h);
/* 1101 */             Rectangle imageBounds = oldOpImage.getBounds();
/* 1102 */             if (!tileBounds.equals(imageBounds)) {
/* 1103 */               Area tmpArea = new Area(tileBounds);
/* 1104 */               tmpArea.subtract(new Area(imageBounds));
/* 1105 */               srcInvalidRegion = tmpArea;
/* 1106 */               srcInvalidBounds = srcInvalidRegion.getBounds();
/*      */             } 
/*      */           } 
/* 1112 */           boolean saveAllTiles = false;
/* 1113 */           ArrayList validTiles = null;
/* 1114 */           if (srcInvalidBounds.isEmpty()) {
/* 1115 */             invalidRegion = srcInvalidRegion;
/* 1116 */             saveAllTiles = true;
/*      */           } else {
/* 1119 */             int idx = nodeSources.indexOf(evtSrc);
/* 1122 */             Rectangle dstRegionBounds = oldOpImage.mapSourceRect(srcInvalidBounds, idx);
/* 1126 */             if (dstRegionBounds == null)
/* 1127 */               dstRegionBounds = oldOpImage.getBounds(); 
/* 1131 */             Point[] indices = getTileIndices(dstRegionBounds);
/* 1132 */             int numIndices = (indices != null) ? indices.length : 0;
/* 1133 */             GeneralPath gp = null;
/* 1135 */             for (int i = 0; i < numIndices; i++) {
/* 1136 */               if (i % 1000 == 0 && gp != null)
/* 1137 */                 gp = new GeneralPath(new Area(gp)); 
/* 1139 */               Rectangle dstRect = getTileRect((indices[i]).x, (indices[i]).y);
/* 1141 */               Rectangle srcRect = oldOpImage.mapDestRect(dstRect, idx);
/* 1143 */               if (srcRect == null) {
/* 1144 */                 gp = null;
/*      */                 break;
/*      */               } 
/* 1147 */               if (srcInvalidRegion.intersects(srcRect)) {
/* 1148 */                 if (gp == null) {
/* 1149 */                   gp = new GeneralPath(dstRect);
/*      */                 } else {
/* 1151 */                   gp.append(dstRect, false);
/*      */                 } 
/*      */               } else {
/* 1154 */                 if (validTiles == null)
/* 1155 */                   validTiles = new ArrayList(); 
/* 1157 */                 validTiles.add(indices[i]);
/*      */               } 
/*      */             } 
/* 1161 */             invalidRegion = (gp == null) ? null : new Area(gp);
/*      */           } 
/* 1165 */           this.theImage = null;
/* 1168 */           TileCache oldCache = oldOpImage.getTileCache();
/* 1172 */           if (oldCache != null && (saveAllTiles || validTiles != null)) {
/* 1175 */             createRendering();
/* 1179 */             if (this.theImage instanceof OpImage && ((OpImage)this.theImage).getTileCache() != null) {
/* 1181 */               OpImage newOpImage = (OpImage)this.theImage;
/* 1182 */               TileCache newCache = newOpImage.getTileCache();
/* 1184 */               Object tileCacheMetric = newOpImage.getTileCacheMetric();
/* 1187 */               if (saveAllTiles) {
/* 1188 */                 Raster[] tiles = oldCache.getTiles(oldOpImage);
/* 1190 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/* 1192 */                 for (int i = 0; i < numTiles; i++) {
/* 1193 */                   Raster tile = tiles[i];
/* 1194 */                   int tx = newOpImage.XToTileX(tile.getMinX());
/* 1196 */                   int ty = newOpImage.YToTileY(tile.getMinY());
/* 1198 */                   newCache.add(newOpImage, tx, ty, tile, tileCacheMetric);
/*      */                 } 
/*      */               } else {
/* 1203 */                 int numValidTiles = validTiles.size();
/* 1204 */                 for (int i = 0; i < numValidTiles; i++) {
/* 1205 */                   Point tileIndex = validTiles.get(i);
/* 1206 */                   Raster tile = oldCache.getTile(oldOpImage, tileIndex.x, tileIndex.y);
/* 1210 */                   if (tile != null)
/* 1211 */                     newCache.add(newOpImage, tileIndex.x, tileIndex.y, tile, tileCacheMetric); 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1223 */         ParameterBlock oldPB = null;
/* 1224 */         ParameterBlock newPB = null;
/* 1226 */         boolean checkInvalidRegion = false;
/* 1227 */         if (propName.equals("parameterblock")) {
/* 1228 */           oldPB = (ParameterBlock)evt.getOldValue();
/* 1229 */           newPB = (ParameterBlock)evt.getNewValue();
/* 1230 */           checkInvalidRegion = true;
/* 1231 */         } else if (propName.equals("sources")) {
/* 1233 */           Vector params = this.nodeSupport.getParameterBlock().getParameters();
/* 1235 */           oldPB = new ParameterBlock((Vector)evt.getOldValue(), params);
/* 1237 */           newPB = new ParameterBlock((Vector)evt.getNewValue(), params);
/* 1239 */           checkInvalidRegion = true;
/* 1240 */         } else if (propName.equals("parameters")) {
/* 1242 */           oldPB = new ParameterBlock(nodeSources, (Vector)evt.getOldValue());
/* 1244 */           newPB = new ParameterBlock(nodeSources, (Vector)evt.getNewValue());
/* 1246 */           checkInvalidRegion = true;
/* 1247 */         } else if (propName.equals("renderinghints")) {
/* 1248 */           oldPB = newPB = this.nodeSupport.getParameterBlock();
/* 1249 */           checkInvalidRegion = true;
/* 1250 */         } else if (evt instanceof CollectionChangeEvent) {
/* 1254 */           int collectionIndex = nodeSources.indexOf(evtSrc);
/* 1255 */           Vector oldSources = (Vector)nodeSources.clone();
/* 1256 */           Vector newSources = (Vector)nodeSources.clone();
/* 1257 */           oldSources.set(collectionIndex, evt.getOldValue());
/* 1258 */           newSources.set(collectionIndex, evt.getNewValue());
/* 1260 */           Vector params = this.nodeSupport.getParameterBlock().getParameters();
/* 1263 */           oldPB = new ParameterBlock(oldSources, params);
/* 1264 */           newPB = new ParameterBlock(newSources, params);
/* 1266 */           checkInvalidRegion = true;
/*      */         } 
/* 1269 */         if (checkInvalidRegion) {
/* 1271 */           fireEvent = true;
/* 1274 */           OperationRegistry registry = this.nodeSupport.getRegistry();
/* 1275 */           OperationDescriptor odesc = (OperationDescriptor)registry.getDescriptor(OperationDescriptor.class, this.nodeSupport.getOperationName());
/* 1280 */           oldPB = ImageUtil.evaluateParameters(oldPB);
/* 1281 */           newPB = ImageUtil.evaluateParameters(newPB);
/* 1284 */           invalidRegion = (Shape)odesc.getInvalidRegion("rendered", oldPB, this.oldHints, newPB, this.nodeSupport.getRenderingHints(), this);
/* 1292 */           if (invalidRegion == null || !(this.theImage instanceof OpImage)) {
/* 1296 */             this.theImage = null;
/*      */           } else {
/* 1300 */             OpImage oldRendering = (OpImage)this.theImage;
/* 1301 */             this.theImage = null;
/* 1302 */             createRendering();
/* 1306 */             if (this.theImage instanceof OpImage && oldRendering.getTileCache() != null && ((OpImage)this.theImage).getTileCache() != null) {
/* 1309 */               OpImage newRendering = (OpImage)this.theImage;
/* 1312 */               TileCache oldCache = oldRendering.getTileCache();
/* 1313 */               TileCache newCache = newRendering.getTileCache();
/* 1314 */               Object tileCacheMetric = newRendering.getTileCacheMetric();
/* 1320 */               if (invalidRegion.getBounds().isEmpty()) {
/* 1321 */                 int x = oldRendering.tileXToX(oldRendering.getMinTileX());
/* 1323 */                 int y = oldRendering.tileYToY(oldRendering.getMinTileY());
/* 1325 */                 int w = oldRendering.getNumXTiles() * oldRendering.getTileWidth();
/* 1327 */                 int h = oldRendering.getNumYTiles() * oldRendering.getTileHeight();
/* 1329 */                 Rectangle tileBounds = new Rectangle(x, y, w, h);
/* 1331 */                 Rectangle imageBounds = oldRendering.getBounds();
/* 1333 */                 if (!tileBounds.equals(imageBounds)) {
/* 1334 */                   Area tmpArea = new Area(tileBounds);
/* 1335 */                   tmpArea.subtract(new Area(imageBounds));
/* 1336 */                   invalidRegion = tmpArea;
/*      */                 } 
/*      */               } 
/* 1340 */               if (invalidRegion.getBounds().isEmpty()) {
/* 1342 */                 Raster[] tiles = oldCache.getTiles(oldRendering);
/* 1344 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/* 1346 */                 for (int i = 0; i < numTiles; i++) {
/* 1347 */                   Raster tile = tiles[i];
/* 1348 */                   int tx = newRendering.XToTileX(tile.getMinX());
/* 1350 */                   int ty = newRendering.YToTileY(tile.getMinY());
/* 1352 */                   newCache.add(newRendering, tx, ty, tile, tileCacheMetric);
/*      */                 } 
/*      */               } else {
/* 1359 */                 Raster[] tiles = oldCache.getTiles(oldRendering);
/* 1361 */                 int numTiles = (tiles == null) ? 0 : tiles.length;
/* 1363 */                 for (int i = 0; i < numTiles; i++) {
/* 1364 */                   Raster tile = tiles[i];
/* 1365 */                   Rectangle bounds = tile.getBounds();
/* 1366 */                   if (!invalidRegion.intersects(bounds))
/* 1367 */                     newCache.add(newRendering, newRendering.XToTileX(bounds.x), newRendering.YToTileY(bounds.y), tile, tileCacheMetric); 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1382 */       createRendering();
/* 1385 */       if (fireEvent) {
/* 1388 */         resetProperties(true);
/* 1391 */         RenderingChangeEvent rcEvent = new RenderingChangeEvent(this, theOldImage, this.theImage, invalidRegion);
/* 1396 */         this.eventManager.firePropertyChange(rcEvent);
/* 1399 */         Vector sinks = getSinks();
/* 1400 */         if (sinks != null) {
/* 1401 */           int numSinks = sinks.size();
/* 1402 */           for (int i = 0; i < numSinks; i++) {
/* 1403 */             Object sink = sinks.get(i);
/* 1404 */             if (sink instanceof PropertyChangeListener)
/* 1405 */               ((PropertyChangeListener)sink).propertyChange(rcEvent); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void addNodeSource(Object source) {
/* 1426 */     addSource(source);
/*      */   }
/*      */   
/*      */   public synchronized void setNodeSource(Object source, int index) {
/* 1446 */     setSource(source, index);
/*      */   }
/*      */   
/*      */   public synchronized Object getNodeSource(int index) {
/* 1459 */     return this.nodeSupport.getParameterBlock().getSource(index);
/*      */   }
/*      */   
/*      */   public synchronized int getNumParameters() {
/* 1469 */     return this.nodeSupport.getParameterBlock().getNumParameters();
/*      */   }
/*      */   
/*      */   public synchronized Vector getParameters() {
/* 1478 */     Vector params = this.nodeSupport.getParameterBlock().getParameters();
/* 1479 */     return (params == null) ? null : (Vector)params.clone();
/*      */   }
/*      */   
/*      */   public synchronized byte getByteParameter(int index) {
/* 1494 */     return this.nodeSupport.getParameterBlock().getByteParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized char getCharParameter(int index) {
/* 1509 */     return this.nodeSupport.getParameterBlock().getCharParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized short getShortParameter(int index) {
/* 1524 */     return this.nodeSupport.getParameterBlock().getShortParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized int getIntParameter(int index) {
/* 1539 */     return this.nodeSupport.getParameterBlock().getIntParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized long getLongParameter(int index) {
/* 1555 */     return this.nodeSupport.getParameterBlock().getLongParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized float getFloatParameter(int index) {
/* 1570 */     return this.nodeSupport.getParameterBlock().getFloatParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized double getDoubleParameter(int index) {
/* 1585 */     return this.nodeSupport.getParameterBlock().getDoubleParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized Object getObjectParameter(int index) {
/* 1600 */     return this.nodeSupport.getParameterBlock().getObjectParameter(index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameters(Vector parameters) {
/* 1617 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 1619 */     pb.setParameters(parameters);
/* 1620 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(byte param, int index) {
/* 1635 */     setParameter(new Byte(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(char param, int index) {
/* 1650 */     setParameter(new Character(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(short param, int index) {
/* 1662 */     setParameter(new Short(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(int param, int index) {
/* 1674 */     setParameter(new Integer(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(long param, int index) {
/* 1686 */     setParameter(new Long(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(float param, int index) {
/* 1698 */     setParameter(new Float(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(double param, int index) {
/* 1710 */     setParameter(new Double(param), index);
/*      */   }
/*      */   
/*      */   public synchronized void setParameter(Object param, int index) {
/* 1730 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 1732 */     pb.set(param, index);
/* 1733 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public synchronized void setRenderingHint(RenderingHints.Key key, Object value) {
/* 1753 */     if (key == null || value == null)
/* 1754 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1757 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1758 */     if (rh == null) {
/* 1759 */       this.nodeSupport.setRenderingHints(new RenderingHints(key, value));
/*      */     } else {
/* 1761 */       rh.put(key, value);
/* 1762 */       this.nodeSupport.setRenderingHints(rh);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Object getRenderingHint(RenderingHints.Key key) {
/* 1775 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 1776 */     return (rh == null) ? null : rh.get(key);
/*      */   }
/*      */   
/*      */   private synchronized void createPropertySource() {
/* 1783 */     if (this.thePropertySource == null) {
/* 1785 */       PropertySource defaultPS = new PropertySource(this) {
/*      */           private final RenderedOp this$0;
/*      */           
/*      */           public String[] getPropertyNames() {
/* 1791 */             return this.this$0.getRendering().getPropertyNames();
/*      */           }
/*      */           
/*      */           public String[] getPropertyNames(String prefix) {
/* 1795 */             return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*      */           }
/*      */           
/*      */           public Class getPropertyClass(String name) {
/* 1800 */             return null;
/*      */           }
/*      */           
/*      */           public Object getProperty(String name) {
/* 1808 */             return this.this$0.getRendering().getProperty(name);
/*      */           }
/*      */         };
/* 1814 */       this.thePropertySource = this.nodeSupport.getPropertySource(this, defaultPS);
/* 1817 */       this.properties.addProperties(this.thePropertySource);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized void resetProperties(boolean resetPropertySource) {
/* 1831 */     this.properties.clearCachedProperties();
/* 1832 */     if (resetPropertySource && this.thePropertySource != null) {
/* 1833 */       this.synthProperties = null;
/* 1834 */       this.properties.removePropertySource(this.thePropertySource);
/* 1835 */       this.thePropertySource = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized String[] getPropertyNames() {
/* 1851 */     createPropertySource();
/* 1854 */     Vector names = new Vector(synthProps);
/* 1857 */     CaselessStringKey key = new CaselessStringKey("");
/* 1861 */     String[] localNames = this.properties.getPropertyNames();
/* 1862 */     if (localNames != null) {
/* 1863 */       int length = localNames.length;
/* 1864 */       for (int i = 0; i < length; i++) {
/* 1865 */         key.setName(localNames[i]);
/* 1868 */         if (!names.contains(key))
/* 1869 */           names.add(key.clone()); 
/*      */       } 
/*      */     } 
/* 1875 */     String[] propertyNames = null;
/* 1876 */     int numNames = names.size();
/* 1877 */     if (numNames > 0) {
/* 1878 */       propertyNames = new String[numNames];
/* 1879 */       for (int i = 0; i < numNames; i++)
/* 1880 */         propertyNames[i] = ((CaselessStringKey)names.get(i)).getName(); 
/*      */     } 
/* 1884 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public Class getPropertyClass(String name) {
/* 1901 */     createPropertySource();
/* 1902 */     return this.properties.getPropertyClass(name);
/*      */   }
/*      */   
/*      */   private synchronized void createSynthProperties() {
/* 1907 */     if (this.synthProperties == null) {
/* 1908 */       this.synthProperties = new Hashtable();
/* 1909 */       this.synthProperties.put(new CaselessStringKey("image_width"), new Integer(this.theImage.getWidth()));
/* 1911 */       this.synthProperties.put(new CaselessStringKey("image_height"), new Integer(this.theImage.getHeight()));
/* 1913 */       this.synthProperties.put(new CaselessStringKey("image_min_x_coord"), new Integer(this.theImage.getMinX()));
/* 1915 */       this.synthProperties.put(new CaselessStringKey("image_min_y_coord"), new Integer(this.theImage.getMinY()));
/* 1918 */       if (this.theImage instanceof OpImage) {
/* 1919 */         this.synthProperties.put(new CaselessStringKey("tile_cache_key"), this.theImage);
/* 1921 */         Object tileCache = ((OpImage)this.theImage).getTileCache();
/* 1922 */         this.synthProperties.put(new CaselessStringKey("tile_cache"), (tileCache == null) ? Image.UndefinedProperty : tileCache);
/* 1926 */       } else if (this.theImage instanceof PlanarImageServerProxy) {
/* 1927 */         this.synthProperties.put(new CaselessStringKey("tile_cache_key"), this.theImage);
/* 1929 */         Object tileCache = ((PlanarImageServerProxy)this.theImage).getTileCache();
/* 1931 */         this.synthProperties.put(new CaselessStringKey("tile_cache"), (tileCache == null) ? Image.UndefinedProperty : tileCache);
/*      */       } else {
/* 1936 */         Object tileCacheKey = this.theImage.getProperty("tile_cache_key");
/* 1937 */         this.synthProperties.put(new CaselessStringKey("tile_cache_key"), (tileCacheKey == null) ? Image.UndefinedProperty : tileCacheKey);
/* 1941 */         Object tileCache = this.theImage.getProperty("tile_cache");
/* 1942 */         this.synthProperties.put(new CaselessStringKey("tile_cache"), (tileCache == null) ? Image.UndefinedProperty : tileCache);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized Object getProperty(String name) {
/* 1965 */     if (name == null)
/* 1966 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1968 */     createPropertySource();
/* 1969 */     CaselessStringKey key = new CaselessStringKey(name);
/* 1973 */     if (synthProps.contains(key)) {
/* 1974 */       createRendering();
/* 1977 */       createSynthProperties();
/* 1978 */       return this.synthProperties.get(key);
/*      */     } 
/* 1982 */     Object value = this.properties.getProperty(name);
/* 1985 */     if (value == Image.UndefinedProperty)
/* 1986 */       value = this.thePropertySource.getProperty(name); 
/* 1991 */     if (value != Image.UndefinedProperty && name.equalsIgnoreCase("roi") && value instanceof ROI) {
/* 1994 */       ROI roi = (ROI)value;
/* 1995 */       Rectangle imageBounds = getBounds();
/* 1996 */       if (!imageBounds.contains(roi.getBounds()))
/* 1997 */         value = roi.intersect(new ROIShape(imageBounds)); 
/*      */     } 
/* 2001 */     return value;
/*      */   }
/*      */   
/*      */   public synchronized void setProperty(String name, Object value) {
/* 2024 */     if (name == null)
/* 2025 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2026 */     if (value == null)
/* 2027 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2030 */     if (synthProps.contains(new CaselessStringKey(name)))
/* 2031 */       throw new RuntimeException(JaiI18N.getString("RenderedOp4")); 
/* 2034 */     createPropertySource();
/* 2035 */     super.setProperty(name, value);
/*      */   }
/*      */   
/*      */   public void removeProperty(String name) {
/* 2052 */     if (name == null)
/* 2053 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2056 */     if (synthProps.contains(new CaselessStringKey(name)))
/* 2057 */       throw new RuntimeException(JaiI18N.getString("RenderedOp4")); 
/* 2060 */     createPropertySource();
/* 2061 */     this.properties.removeProperty(name);
/*      */   }
/*      */   
/*      */   public synchronized Object getDynamicProperty(String name) {
/* 2083 */     createPropertySource();
/* 2084 */     return this.thePropertySource.getProperty(name);
/*      */   }
/*      */   
/*      */   public synchronized void addPropertyGenerator(PropertyGenerator pg) {
/* 2099 */     this.nodeSupport.addPropertyGenerator(pg);
/*      */   }
/*      */   
/*      */   public synchronized void copyPropertyFromSource(String propertyName, int sourceIndex) {
/* 2117 */     this.nodeSupport.copyPropertyFromSource(propertyName, sourceIndex);
/*      */   }
/*      */   
/*      */   public synchronized void suppressProperty(String name) {
/* 2141 */     if (name == null)
/* 2142 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2144 */     if (synthProps.contains(new CaselessStringKey(name)))
/* 2145 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedOp5")); 
/* 2148 */     this.nodeSupport.suppressProperty(name);
/*      */   }
/*      */   
/*      */   public int getMinX() {
/* 2161 */     createRendering();
/* 2162 */     return this.theImage.getMinX();
/*      */   }
/*      */   
/*      */   public int getMinY() {
/* 2170 */     createRendering();
/* 2171 */     return this.theImage.getMinY();
/*      */   }
/*      */   
/*      */   public int getWidth() {
/* 2179 */     createRendering();
/* 2180 */     return this.theImage.getWidth();
/*      */   }
/*      */   
/*      */   public int getHeight() {
/* 2188 */     createRendering();
/* 2189 */     return this.theImage.getHeight();
/*      */   }
/*      */   
/*      */   public int getTileWidth() {
/* 2197 */     createRendering();
/* 2198 */     return this.theImage.getTileWidth();
/*      */   }
/*      */   
/*      */   public int getTileHeight() {
/* 2206 */     createRendering();
/* 2207 */     return this.theImage.getTileHeight();
/*      */   }
/*      */   
/*      */   public int getTileGridXOffset() {
/* 2215 */     createRendering();
/* 2216 */     return this.theImage.getTileGridXOffset();
/*      */   }
/*      */   
/*      */   public int getTileGridYOffset() {
/* 2224 */     createRendering();
/* 2225 */     return this.theImage.getTileGridYOffset();
/*      */   }
/*      */   
/*      */   public SampleModel getSampleModel() {
/* 2233 */     createRendering();
/* 2234 */     return this.theImage.getSampleModel();
/*      */   }
/*      */   
/*      */   public ColorModel getColorModel() {
/* 2242 */     createRendering();
/* 2243 */     return this.theImage.getColorModel();
/*      */   }
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 2256 */     createRendering();
/* 2257 */     return this.theImage.getTile(tileX, tileY);
/*      */   }
/*      */   
/*      */   public Raster getData() {
/* 2265 */     createRendering();
/* 2266 */     return this.theImage.getData();
/*      */   }
/*      */   
/*      */   public Raster getData(Rectangle rect) {
/* 2275 */     createRendering();
/* 2276 */     return this.theImage.getData(rect);
/*      */   }
/*      */   
/*      */   public WritableRaster copyData() {
/* 2284 */     createRendering();
/* 2285 */     return this.theImage.copyData();
/*      */   }
/*      */   
/*      */   public WritableRaster copyData(WritableRaster raster) {
/* 2298 */     createRendering();
/* 2299 */     return this.theImage.copyData(raster);
/*      */   }
/*      */   
/*      */   public Raster[] getTiles(Point[] tileIndices) {
/* 2315 */     createRendering();
/* 2316 */     return this.theImage.getTiles(tileIndices);
/*      */   }
/*      */   
/*      */   public TileRequest queueTiles(Point[] tileIndices) {
/* 2336 */     createRendering();
/* 2337 */     return this.theImage.queueTiles(tileIndices);
/*      */   }
/*      */   
/*      */   public void cancelTiles(TileRequest request, Point[] tileIndices) {
/* 2354 */     if (request == null)
/* 2355 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 2357 */     createRendering();
/* 2358 */     this.theImage.cancelTiles(request, tileIndices);
/*      */   }
/*      */   
/*      */   public void prefetchTiles(Point[] tileIndices) {
/* 2373 */     createRendering();
/* 2374 */     this.theImage.prefetchTiles(tileIndices);
/*      */   }
/*      */   
/*      */   public synchronized void addSource(PlanarImage source) {
/* 2408 */     Object sourceObject = source;
/* 2409 */     addSource(sourceObject);
/*      */   }
/*      */   
/*      */   public synchronized void setSource(PlanarImage source, int index) {
/* 2444 */     Object sourceObject = source;
/* 2445 */     setSource(sourceObject, index);
/*      */   }
/*      */   
/*      */   public PlanarImage getSource(int index) {
/* 2481 */     return (PlanarImage)this.nodeSupport.getParameterBlock().getSource(index);
/*      */   }
/*      */   
/*      */   public synchronized boolean removeSource(PlanarImage source) {
/* 2511 */     Object sourceObject = source;
/* 2512 */     return removeSource(sourceObject);
/*      */   }
/*      */   
/*      */   public synchronized void addSource(Object source) {
/* 2532 */     if (source == null)
/* 2533 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2535 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 2537 */     pb.addSource(source);
/* 2538 */     this.nodeSupport.setParameterBlock(pb);
/* 2540 */     if (source instanceof PlanarImage) {
/* 2541 */       ((PlanarImage)source).addSink(this);
/* 2542 */     } else if (source instanceof CollectionImage) {
/* 2543 */       ((CollectionImage)source).addSink(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void setSource(Object source, int index) {
/* 2570 */     if (source == null)
/* 2571 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2573 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 2576 */     if (index < pb.getNumSources()) {
/* 2577 */       Object priorSource = pb.getSource(index);
/* 2578 */       if (priorSource instanceof PlanarImage) {
/* 2579 */         ((PlanarImage)priorSource).removeSink(this);
/* 2580 */       } else if (priorSource instanceof CollectionImage) {
/* 2581 */         ((CollectionImage)priorSource).removeSink(this);
/*      */       } 
/*      */     } 
/* 2585 */     pb.setSource(source, index);
/* 2586 */     this.nodeSupport.setParameterBlock(pb);
/* 2588 */     if (source instanceof PlanarImage) {
/* 2589 */       ((PlanarImage)source).addSink(this);
/* 2590 */     } else if (source instanceof CollectionImage) {
/* 2591 */       ((CollectionImage)source).addSink(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized boolean removeSource(Object source) {
/* 2612 */     if (source == null)
/* 2613 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2616 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 2619 */     Vector nodeSources = pb.getSources();
/* 2620 */     if (nodeSources.contains(source))
/* 2621 */       if (source instanceof PlanarImage) {
/* 2622 */         ((PlanarImage)source).removeSink(this);
/* 2623 */       } else if (source instanceof CollectionImage) {
/* 2624 */         ((CollectionImage)source).removeSink(this);
/*      */       }  
/* 2628 */     boolean result = nodeSources.remove(source);
/* 2629 */     this.nodeSupport.setParameterBlock(pb);
/* 2631 */     return result;
/*      */   }
/*      */   
/*      */   public PlanarImage getSourceImage(int index) {
/* 2650 */     return (PlanarImage)this.nodeSupport.getParameterBlock().getSource(index);
/*      */   }
/*      */   
/*      */   public synchronized Object getSourceObject(int index) {
/* 2666 */     return this.nodeSupport.getParameterBlock().getSource(index);
/*      */   }
/*      */   
/*      */   public int getNumSources() {
/* 2678 */     return this.nodeSupport.getParameterBlock().getNumSources();
/*      */   }
/*      */   
/*      */   public synchronized Vector getSources() {
/* 2687 */     Vector srcs = this.nodeSupport.getParameterBlock().getSources();
/* 2688 */     return (srcs == null) ? null : (Vector)srcs.clone();
/*      */   }
/*      */   
/*      */   public synchronized void setSources(List sourceList) {
/* 2709 */     if (sourceList == null)
/* 2710 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2713 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 2716 */     Iterator it = pb.getSources().iterator();
/* 2717 */     while (it.hasNext()) {
/* 2718 */       Object priorSource = it.next();
/* 2719 */       if (!sourceList.contains(priorSource)) {
/* 2720 */         if (priorSource instanceof PlanarImage) {
/* 2721 */           ((PlanarImage)priorSource).removeSink(this);
/*      */           continue;
/*      */         } 
/* 2722 */         if (priorSource instanceof CollectionImage)
/* 2723 */           ((CollectionImage)priorSource).removeSink(this); 
/*      */       } 
/*      */     } 
/* 2728 */     pb.removeSources();
/* 2730 */     int size = sourceList.size();
/* 2731 */     for (int i = 0; i < size; i++) {
/* 2732 */       Object src = sourceList.get(i);
/* 2733 */       pb.addSource(src);
/* 2734 */       if (src instanceof PlanarImage) {
/* 2735 */         ((PlanarImage)src).addSink(this);
/* 2736 */       } else if (src instanceof CollectionImage) {
/* 2737 */         ((CollectionImage)src).addSink(this);
/*      */       } 
/*      */     } 
/* 2741 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public synchronized void removeSources() {
/* 2755 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 2757 */     Iterator it = pb.getSources().iterator();
/* 2758 */     while (it.hasNext()) {
/* 2759 */       Object priorSource = it.next();
/* 2760 */       if (priorSource instanceof PlanarImage) {
/* 2761 */         ((PlanarImage)priorSource).removeSink(this);
/* 2762 */       } else if (priorSource instanceof CollectionImage) {
/* 2763 */         ((CollectionImage)priorSource).removeSink(this);
/*      */       } 
/* 2765 */       it.remove();
/*      */     } 
/* 2767 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public synchronized void addSink(PlanarImage sink) {
/* 2796 */     if (sink == null)
/* 2797 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2799 */     super.addSink(sink);
/*      */   }
/*      */   
/*      */   public synchronized boolean removeSink(PlanarImage sink) {
/* 2827 */     if (sink == null)
/* 2828 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2830 */     return super.removeSink(sink);
/*      */   }
/*      */   
/*      */   public void removeSinks() {
/* 2839 */     super.removeSinks();
/*      */   }
/*      */   
/*      */   public boolean addSink(Object sink) {
/* 2855 */     if (sink == null)
/* 2856 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2858 */     return super.addSink(sink);
/*      */   }
/*      */   
/*      */   public boolean removeSink(Object sink) {
/* 2873 */     if (sink == null)
/* 2874 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2876 */     return super.removeSink(sink);
/*      */   }
/*      */   
/*      */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/* 2910 */     if (destPt == null)
/* 2911 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2912 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 2913 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 2916 */     createRendering();
/* 2918 */     if (this.theImage != null && this.theImage instanceof OpImage)
/* 2919 */       return ((OpImage)this.theImage).mapDestPoint(destPt, sourceIndex); 
/* 2922 */     return destPt;
/*      */   }
/*      */   
/*      */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/* 2955 */     if (sourcePt == null)
/* 2956 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2957 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 2958 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 2961 */     createRendering();
/* 2963 */     if (this.theImage != null && this.theImage instanceof OpImage)
/* 2964 */       return ((OpImage)this.theImage).mapSourcePoint(sourcePt, sourceIndex); 
/* 2967 */     return sourcePt;
/*      */   }
/*      */   
/*      */   public synchronized void dispose() {
/* 2986 */     if (this.isDisposed)
/*      */       return; 
/* 2990 */     this.isDisposed = true;
/* 2992 */     if (this.theImage != null)
/* 2993 */       this.theImage.dispose(); 
/* 2996 */     super.dispose();
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 3004 */     out.defaultWriteObject();
/* 3007 */     out.writeObject(this.eventManager);
/* 3008 */     out.writeObject(this.properties);
/*      */   }
/*      */   
/*      */   private synchronized void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 3018 */     in.defaultReadObject();
/* 3021 */     this.eventManager = (PropertyChangeSupportJAI)in.readObject();
/* 3022 */     this.properties = (WritablePropertySourceImpl)in.readObject();
/* 3025 */     OperationDescriptor odesc = (OperationDescriptor)getRegistry().getDescriptor("rendered", this.nodeSupport.getOperationName());
/* 3029 */     if (odesc.isImmediate())
/* 3030 */       createRendering(); 
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 3035 */     ImagingListener listener = (ImagingListener)getRenderingHints().get(JAI.KEY_IMAGING_LISTENER);
/* 3038 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderedOp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */