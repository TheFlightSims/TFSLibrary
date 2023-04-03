/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImagingListenerImpl;
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import com.sun.media.jai.util.SunTileCache;
/*      */ import com.sun.media.jai.util.SunTileScheduler;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.remote.NegotiableCapabilitySet;
/*      */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public final class JAI {
/*      */   private static final int HINT_IMAGE_LAYOUT = 101;
/*      */   
/*      */   private static final int HINT_INTERPOLATION = 102;
/*      */   
/*      */   private static final int HINT_OPERATION_REGISTRY = 103;
/*      */   
/*      */   private static final int HINT_OPERATION_BOUND = 104;
/*      */   
/*      */   private static final int HINT_BORDER_EXTENDER = 105;
/*      */   
/*      */   private static final int HINT_TILE_CACHE = 106;
/*      */   
/*      */   private static final int HINT_TILE_SCHEDULER = 107;
/*      */   
/*      */   private static final int HINT_DEFAULT_COLOR_MODEL_ENABLED = 108;
/*      */   
/*      */   private static final int HINT_DEFAULT_COLOR_MODEL_METHOD = 109;
/*      */   
/*      */   private static final int HINT_TILE_CACHE_METRIC = 110;
/*      */   
/*      */   private static final int HINT_SERIALIZE_DEEP_COPY = 111;
/*      */   
/*      */   private static final int HINT_TILE_CODEC_FORMAT = 112;
/*      */   
/*      */   private static final int HINT_TILE_ENCODING_PARAM = 113;
/*      */   
/*      */   private static final int HINT_TILE_DECODING_PARAM = 114;
/*      */   
/*      */   private static final int HINT_RETRY_INTERVAL = 115;
/*      */   
/*      */   private static final int HINT_NUM_RETRIES = 116;
/*      */   
/*      */   private static final int HINT_NEGOTIATION_PREFERENCES = 117;
/*      */   
/*      */   private static final int HINT_DEFAULT_RENDERING_SIZE = 118;
/*      */   
/*      */   private static final int HINT_COLOR_MODEL_FACTORY = 119;
/*      */   
/*      */   private static final int HINT_REPLACE_INDEX_COLOR_MODEL = 120;
/*      */   
/*      */   private static final int HINT_TILE_FACTORY = 121;
/*      */   
/*      */   private static final int HINT_TILE_RECYCLER = 122;
/*      */   
/*      */   private static final int HINT_CACHED_TILE_RECYCLING_ENABLED = 123;
/*      */   
/*      */   private static final int HINT_TRANSFORM_ON_COLORMAP = 124;
/*      */   
/*      */   private static final int HINT_IMAGING_LISTENER = 125;
/*      */   
/*  183 */   public static RenderingHints.Key KEY_IMAGE_LAYOUT = new RenderingKey(101, ImageLayout.class);
/*      */   
/*  194 */   public static RenderingHints.Key KEY_INTERPOLATION = new RenderingKey(102, Interpolation.class);
/*      */   
/*  204 */   public static RenderingHints.Key KEY_OPERATION_REGISTRY = new RenderingKey(103, OperationRegistry.class);
/*      */   
/*  216 */   public static RenderingHints.Key KEY_OPERATION_BOUND = new RenderingKey(104, Integer.class);
/*      */   
/*  224 */   public static RenderingHints.Key KEY_BORDER_EXTENDER = new RenderingKey(105, BorderExtender.class);
/*      */   
/*  239 */   public static RenderingHints.Key KEY_TILE_CACHE = new RenderingKey(106, TileCache.class);
/*      */   
/*  252 */   public static RenderingHints.Key KEY_TILE_CACHE_METRIC = new RenderingKey(110, Object.class);
/*      */   
/*  266 */   public static RenderingHints.Key KEY_TILE_SCHEDULER = new RenderingKey(107, TileScheduler.class);
/*      */   
/*  281 */   public static RenderingHints.Key KEY_DEFAULT_COLOR_MODEL_ENABLED = new RenderingKey(108, Boolean.class);
/*      */   
/*  299 */   public static RenderingHints.Key KEY_DEFAULT_COLOR_MODEL_METHOD = new RenderingKey(109, Method.class);
/*      */   
/*  312 */   public static final RenderingHints.Key KEY_DEFAULT_RENDERING_SIZE = new RenderingKey(118, Dimension.class);
/*      */   
/*  324 */   public static RenderingHints.Key KEY_COLOR_MODEL_FACTORY = new RenderingKey(119, ColorModelFactory.class);
/*      */   
/*  364 */   public static RenderingHints.Key KEY_REPLACE_INDEX_COLOR_MODEL = new RenderingKey(120, Boolean.class);
/*      */   
/*  379 */   public static RenderingHints.Key KEY_TILE_FACTORY = new RenderingKey(121, TileFactory.class);
/*      */   
/*  393 */   public static RenderingHints.Key KEY_TILE_RECYCLER = new RenderingKey(122, TileRecycler.class);
/*      */   
/*  406 */   public static RenderingHints.Key KEY_CACHED_TILE_RECYCLING_ENABLED = new RenderingKey(123, Boolean.class);
/*      */   
/*  418 */   public static RenderingHints.Key KEY_SERIALIZE_DEEP_COPY = new RenderingKey(111, Boolean.class);
/*      */   
/*  430 */   public static RenderingHints.Key KEY_TILE_CODEC_FORMAT = new RenderingKey(112, String.class);
/*      */   
/*  442 */   public static RenderingHints.Key KEY_TILE_ENCODING_PARAM = new RenderingKey(113, TileCodecParameterList.class);
/*      */   
/*  454 */   public static RenderingHints.Key KEY_TILE_DECODING_PARAM = new RenderingKey(114, TileCodecParameterList.class);
/*      */   
/*  468 */   public static RenderingHints.Key KEY_RETRY_INTERVAL = new RenderingKey(115, Integer.class);
/*      */   
/*  482 */   public static RenderingHints.Key KEY_NUM_RETRIES = new RenderingKey(116, Integer.class);
/*      */   
/*  496 */   public static RenderingHints.Key KEY_NEGOTIATION_PREFERENCES = new RenderingKey(117, NegotiableCapabilitySet.class);
/*      */   
/*  512 */   public static RenderingHints.Key KEY_TRANSFORM_ON_COLORMAP = new RenderingKey(124, Boolean.class);
/*      */   
/*  524 */   public static RenderingHints.Key KEY_IMAGING_LISTENER = new RenderingKey(125, ImagingListener.class);
/*      */   
/*      */   private static final int DEFAULT_TILE_SIZE = 512;
/*      */   
/*  536 */   private static Dimension defaultTileSize = new Dimension(512, 512);
/*      */   
/*  543 */   private static Dimension defaultRenderingSize = new Dimension(0, 512);
/*      */   
/*      */   private OperationRegistry operationRegistry;
/*      */   
/*      */   private TileScheduler tileScheduler;
/*      */   
/*      */   private TileCache tileCache;
/*      */   
/*      */   private RenderingHints renderingHints;
/*      */   
/*  558 */   private ImagingListener imagingListener = (ImagingListener)ImagingListenerImpl.getInstance();
/*      */   
/*  560 */   private static JAI defaultInstance = new JAI(OperationRegistry.initializeRegistry(), (TileScheduler)new SunTileScheduler(), (TileCache)new SunTileCache(), new RenderingHints(null));
/*      */   
/*      */   private JAI(OperationRegistry operationRegistry, TileScheduler tileScheduler, TileCache tileCache, RenderingHints renderingHints) {
/*  571 */     this.operationRegistry = operationRegistry;
/*  572 */     this.tileScheduler = tileScheduler;
/*  573 */     this.tileCache = tileCache;
/*  574 */     this.renderingHints = renderingHints;
/*  576 */     this.renderingHints.put(KEY_OPERATION_REGISTRY, operationRegistry);
/*  577 */     this.renderingHints.put(KEY_TILE_CACHE, tileCache);
/*  578 */     this.renderingHints.put(KEY_TILE_SCHEDULER, tileScheduler);
/*  580 */     TileFactory rtf = new RecyclingTileFactory();
/*  581 */     this.renderingHints.put(KEY_TILE_FACTORY, rtf);
/*  582 */     this.renderingHints.put(KEY_TILE_RECYCLER, rtf);
/*  583 */     this.renderingHints.put(KEY_CACHED_TILE_RECYCLING_ENABLED, Boolean.FALSE);
/*  585 */     this.renderingHints.put(KEY_IMAGING_LISTENER, this.imagingListener);
/*      */   }
/*      */   
/*      */   public static final String getBuildVersion() {
/*      */     try {
/*  595 */       InputStream is = JAI.class.getResourceAsStream("buildVersion");
/*  596 */       if (is == null)
/*  597 */         is = PropertyUtil.getFileFromClasspath("javax/media/jai/buildVersion"); 
/*  599 */       BufferedReader reader = new BufferedReader(new InputStreamReader(is));
/*  602 */       StringWriter sw = new StringWriter();
/*  603 */       BufferedWriter writer = new BufferedWriter(sw);
/*  606 */       boolean append = false;
/*      */       String str;
/*  608 */       while ((str = reader.readLine()) != null) {
/*  609 */         if (append)
/*  609 */           writer.newLine(); 
/*  611 */         writer.write(str);
/*  612 */         append = true;
/*      */       } 
/*  615 */       writer.close();
/*  616 */       return sw.getBuffer().toString();
/*  618 */     } catch (Exception e) {
/*  619 */       return JaiI18N.getString("JAI13");
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final void disableDefaultTileCache() {
/*  629 */     TileCache tmp = defaultInstance.getTileCache();
/*  630 */     if (tmp != null)
/*  631 */       tmp.flush(); 
/*  633 */     defaultInstance.renderingHints.remove(KEY_TILE_CACHE);
/*      */   }
/*      */   
/*      */   public static final void enableDefaultTileCache() {
/*  642 */     defaultInstance.renderingHints.put(KEY_TILE_CACHE, defaultInstance.getTileCache());
/*      */   }
/*      */   
/*      */   public static final void setDefaultTileSize(Dimension tileDimensions) {
/*  659 */     if (tileDimensions != null && (tileDimensions.width <= 0 || tileDimensions.height <= 0))
/*  661 */       throw new IllegalArgumentException(); 
/*  664 */     defaultTileSize = (tileDimensions != null) ? (Dimension)tileDimensions.clone() : null;
/*      */   }
/*      */   
/*      */   public static final Dimension getDefaultTileSize() {
/*  677 */     return (defaultTileSize != null) ? (Dimension)defaultTileSize.clone() : null;
/*      */   }
/*      */   
/*      */   public static final void setDefaultRenderingSize(Dimension defaultSize) {
/*  705 */     if (defaultSize != null && defaultSize.width <= 0 && defaultSize.height <= 0)
/*  708 */       throw new IllegalArgumentException(JaiI18N.getString("JAI8")); 
/*  711 */     defaultRenderingSize = (defaultSize == null) ? null : new Dimension(defaultSize);
/*      */   }
/*      */   
/*      */   public static final Dimension getDefaultRenderingSize() {
/*  724 */     return (defaultRenderingSize == null) ? null : new Dimension(defaultRenderingSize);
/*      */   }
/*      */   
/*      */   public static JAI getDefaultInstance() {
/*  741 */     return defaultInstance;
/*      */   }
/*      */   
/*      */   static RenderingHints mergeRenderingHints(RenderingHints defaultHints, RenderingHints hints) {
/*      */     RenderingHints mergedHints;
/*  755 */     if (hints == null || hints.isEmpty()) {
/*  756 */       mergedHints = defaultHints;
/*  757 */     } else if (defaultHints == null || defaultHints.isEmpty()) {
/*  758 */       mergedHints = hints;
/*      */     } else {
/*  760 */       mergedHints = new RenderingHints(defaultHints);
/*  761 */       mergedHints.add(hints);
/*      */     } 
/*  764 */     return mergedHints;
/*      */   }
/*      */   
/*      */   public JAI() {
/*  775 */     this.operationRegistry = defaultInstance.operationRegistry;
/*  776 */     this.tileScheduler = defaultInstance.tileScheduler;
/*  777 */     this.tileCache = defaultInstance.tileCache;
/*  778 */     this.renderingHints = (RenderingHints)defaultInstance.renderingHints.clone();
/*      */   }
/*      */   
/*      */   public OperationRegistry getOperationRegistry() {
/*  791 */     return this.operationRegistry;
/*      */   }
/*      */   
/*      */   public void setOperationRegistry(OperationRegistry operationRegistry) {
/*  800 */     if (operationRegistry == null)
/*  801 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  803 */     this.operationRegistry = operationRegistry;
/*  804 */     this.renderingHints.put(KEY_OPERATION_REGISTRY, operationRegistry);
/*      */   }
/*      */   
/*      */   public TileScheduler getTileScheduler() {
/*  809 */     return this.tileScheduler;
/*      */   }
/*      */   
/*      */   public void setTileScheduler(TileScheduler tileScheduler) {
/*  820 */     if (tileScheduler == null)
/*  821 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  823 */     this.tileScheduler = tileScheduler;
/*  824 */     this.renderingHints.put(KEY_TILE_SCHEDULER, tileScheduler);
/*      */   }
/*      */   
/*      */   public TileCache getTileCache() {
/*  829 */     return this.tileCache;
/*      */   }
/*      */   
/*      */   public void setTileCache(TileCache tileCache) {
/*  841 */     if (tileCache == null)
/*  842 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  844 */     this.tileCache = tileCache;
/*  845 */     this.renderingHints.put(KEY_TILE_CACHE, tileCache);
/*      */   }
/*      */   
/*      */   public static TileCache createTileCache(int tileCapacity, long memCapacity) {
/*  867 */     if (memCapacity < 0L)
/*  868 */       throw new IllegalArgumentException(JaiI18N.getString("JAI10")); 
/*  870 */     return (TileCache)new SunTileCache(memCapacity);
/*      */   }
/*      */   
/*      */   public static TileCache createTileCache(long memCapacity) {
/*  890 */     if (memCapacity < 0L)
/*  891 */       throw new IllegalArgumentException(JaiI18N.getString("JAI10")); 
/*  893 */     return (TileCache)new SunTileCache(memCapacity);
/*      */   }
/*      */   
/*      */   public static TileCache createTileCache() {
/*  909 */     return (TileCache)new SunTileCache();
/*      */   }
/*      */   
/*      */   public static TileScheduler createTileScheduler() {
/*  924 */     return (TileScheduler)new SunTileScheduler();
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, ParameterBlock args, RenderingHints hints) {
/*  973 */     return defaultInstance.createNS(opName, args, hints);
/*      */   }
/*      */   
/*      */   public RenderedOp createNS(String opName, ParameterBlock args, RenderingHints hints) {
/* 1057 */     if (opName == null)
/* 1058 */       throw new IllegalArgumentException(JaiI18N.getString("JAI14")); 
/* 1059 */     if (args == null)
/* 1060 */       throw new IllegalArgumentException(JaiI18N.getString("JAI15")); 
/* 1063 */     String modeName = "rendered";
/* 1066 */     OperationDescriptor odesc = (OperationDescriptor)this.operationRegistry.getDescriptor(modeName, opName);
/* 1069 */     if (odesc == null)
/* 1070 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI0")); 
/* 1074 */     if (!RenderedImage.class.isAssignableFrom(odesc.getDestClass(modeName)))
/* 1075 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI2")); 
/* 1084 */     StringBuffer msg = new StringBuffer();
/* 1085 */     args = (ParameterBlock)args.clone();
/* 1086 */     if (!odesc.validateArguments(modeName, args, msg))
/* 1087 */       throw new IllegalArgumentException(msg.toString()); 
/* 1091 */     RenderingHints mergedHints = mergeRenderingHints(this.renderingHints, hints);
/* 1093 */     RenderedOp op = new RenderedOp(this.operationRegistry, opName, args, mergedHints);
/* 1097 */     if (odesc.isImmediate()) {
/* 1098 */       PlanarImage im = null;
/* 1099 */       im = op.getRendering();
/* 1100 */       if (im == null)
/* 1102 */         return null; 
/*      */     } 
/* 1107 */     return op;
/*      */   }
/*      */   
/*      */   public static Collection createCollection(String opName, ParameterBlock args, RenderingHints hints) {
/* 1153 */     return defaultInstance.createCollectionNS(opName, args, hints);
/*      */   }
/*      */   
/*      */   public Collection createCollectionNS(String opName, ParameterBlock args, RenderingHints hints) {
/* 1234 */     if (opName == null)
/* 1235 */       throw new IllegalArgumentException(JaiI18N.getString("JAI14")); 
/* 1236 */     if (args == null)
/* 1237 */       throw new IllegalArgumentException(JaiI18N.getString("JAI15")); 
/* 1240 */     String modeName = "collection";
/* 1243 */     OperationDescriptor odesc = (OperationDescriptor)this.operationRegistry.getDescriptor(modeName, opName);
/* 1246 */     if (odesc == null)
/* 1247 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI0")); 
/* 1251 */     Class destClass = odesc.getDestClass(modeName);
/* 1253 */     if (!RenderedImage.class.isAssignableFrom(destClass) && !CollectionImage.class.isAssignableFrom(destClass))
/* 1255 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI5")); 
/* 1260 */     RenderingHints mergedHints = mergeRenderingHints(this.renderingHints, hints);
/* 1266 */     StringBuffer msg = new StringBuffer();
/* 1267 */     args = (ParameterBlock)args.clone();
/* 1268 */     if (odesc.validateArguments(modeName, args, msg)) {
/* 1269 */       if (RenderedImage.class.isAssignableFrom(destClass)) {
/* 1270 */         Vector v = new Vector(1);
/* 1271 */         v.add(new RenderedOp(this.operationRegistry, opName, args, mergedHints));
/* 1273 */         return v;
/*      */       } 
/* 1275 */       CollectionOp cOp = new CollectionOp(this.operationRegistry, opName, args, mergedHints);
/* 1279 */       if (odesc.isImmediate()) {
/* 1280 */         Collection coll = null;
/* 1281 */         coll = cOp.getCollection();
/* 1282 */         if (coll == null)
/* 1283 */           return null; 
/*      */       } 
/* 1287 */       return cOp;
/*      */     } 
/* 1291 */     int numSources = odesc.getNumSources();
/* 1292 */     Vector sources = args.getSources();
/* 1298 */     Iterator[] iters = new Iterator[numSources];
/* 1299 */     Iterator iter = null;
/* 1300 */     int size = Integer.MAX_VALUE;
/* 1301 */     for (int i = 0; i < numSources; i++) {
/* 1302 */       Object s = sources.elementAt(i);
/* 1303 */       if (s instanceof Collection) {
/* 1304 */         iters[i] = ((Collection)s).iterator();
/* 1305 */         if (iter == null || ((Collection)s).size() < size) {
/* 1306 */           iter = iters[i];
/* 1307 */           size = ((Collection)s).size();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1312 */     if (iter == null)
/* 1317 */       throw new IllegalArgumentException(msg.toString()); 
/* 1321 */     Collection col = null;
/* 1322 */     for (int j = 0; j < numSources; j++) {
/* 1323 */       Object s = sources.elementAt(j);
/* 1324 */       if (s instanceof Collection)
/*      */         try {
/* 1326 */           col = (Collection)s.getClass().newInstance();
/*      */           break;
/* 1328 */         } catch (Exception e) {
/* 1330 */           sendExceptionToListener(JaiI18N.getString("JAI16") + s.getClass().getName(), e);
/*      */         }  
/*      */     } 
/* 1336 */     if (col == null)
/* 1337 */       col = new Vector(); 
/* 1341 */     Class[] sourceClasses = odesc.getSourceClasses(modeName);
/* 1343 */     while (iter.hasNext()) {
/* 1344 */       ParameterBlock pb = new ParameterBlock();
/* 1345 */       pb.setParameters(args.getParameters());
/* 1347 */       for (int k = 0; k < numSources; k++) {
/* 1349 */         Object nextSource = null;
/* 1350 */         if (iters[k] == null) {
/* 1351 */           nextSource = sources.elementAt(k);
/*      */         } else {
/* 1353 */           nextSource = iters[k].next();
/*      */         } 
/* 1360 */         if (!sourceClasses[k].isAssignableFrom(nextSource.getClass()) && !(nextSource instanceof Collection))
/* 1362 */           throw new IllegalArgumentException(msg.toString()); 
/* 1364 */         pb.addSource(nextSource);
/*      */       } 
/* 1367 */       Collection c = createCollectionNS(opName, pb, mergedHints);
/* 1368 */       if (c instanceof Vector && c.size() == 1 && ((Vector)c).elementAt(0) instanceof RenderedOp) {
/* 1371 */         col.add(((Vector)c).elementAt(0));
/*      */         continue;
/*      */       } 
/* 1373 */       col.add(c);
/*      */     } 
/* 1377 */     return col;
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, ParameterBlock args) {
/* 1395 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param) {
/* 1406 */     ParameterBlock args = new ParameterBlock();
/* 1407 */     args.add(param);
/* 1408 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param1, Object param2) {
/* 1421 */     ParameterBlock args = new ParameterBlock();
/* 1422 */     args.add(param1);
/* 1423 */     args.add(param2);
/* 1424 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param1, int param2) {
/* 1440 */     ParameterBlock args = new ParameterBlock();
/* 1441 */     args.add(param1);
/* 1442 */     args.add(param2);
/* 1443 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param1, Object param2, Object param3) {
/* 1460 */     ParameterBlock args = new ParameterBlock();
/* 1461 */     args.add(param1);
/* 1462 */     args.add(param2);
/* 1463 */     args.add(param3);
/* 1464 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, int param1, int param2, Object param3) {
/* 1482 */     ParameterBlock args = new ParameterBlock();
/* 1483 */     args.add(param1);
/* 1484 */     args.add(param2);
/* 1485 */     args.add(param3);
/* 1486 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param1, Object param2, Object param3, Object param4) {
/* 1505 */     ParameterBlock args = new ParameterBlock();
/* 1506 */     args.add(param1);
/* 1507 */     args.add(param2);
/* 1508 */     args.add(param3);
/* 1509 */     args.add(param4);
/* 1510 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Object param1, int param2, Object param3, int param4) {
/* 1529 */     ParameterBlock args = new ParameterBlock();
/* 1530 */     args.add(param1);
/* 1531 */     args.add(param2);
/* 1532 */     args.add(param3);
/* 1533 */     args.add(param4);
/* 1534 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src) {
/* 1545 */     ParameterBlock args = new ParameterBlock();
/* 1546 */     args.addSource(src);
/* 1547 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, Collection srcCol) {
/* 1560 */     ParameterBlock args = new ParameterBlock();
/* 1561 */     args.addSource(srcCol);
/* 1562 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param) {
/* 1577 */     ParameterBlock args = new ParameterBlock();
/* 1578 */     args.addSource(src);
/* 1579 */     args.add(param);
/* 1580 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, int param) {
/* 1597 */     ParameterBlock args = new ParameterBlock();
/* 1598 */     args.addSource(src);
/* 1599 */     args.add(param);
/* 1600 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2) {
/* 1617 */     ParameterBlock args = new ParameterBlock();
/* 1618 */     args.addSource(src);
/* 1619 */     args.add(param1);
/* 1620 */     args.add(param2);
/* 1621 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, float param2) {
/* 1640 */     ParameterBlock args = new ParameterBlock();
/* 1641 */     args.addSource(src);
/* 1642 */     args.add(param1);
/* 1643 */     args.add(param2);
/* 1644 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2, Object param3) {
/* 1663 */     ParameterBlock args = new ParameterBlock();
/* 1664 */     args.addSource(src);
/* 1665 */     args.add(param1);
/* 1666 */     args.add(param2);
/* 1667 */     args.add(param3);
/* 1668 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, int param2, int param3) {
/* 1689 */     ParameterBlock args = new ParameterBlock();
/* 1690 */     args.addSource(src);
/* 1691 */     args.add(param1);
/* 1692 */     args.add(param2);
/* 1693 */     args.add(param3);
/* 1694 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, float param1, float param2, Object param3) {
/* 1715 */     ParameterBlock args = new ParameterBlock();
/* 1716 */     args.addSource(src);
/* 1717 */     args.add(param1);
/* 1718 */     args.add(param2);
/* 1719 */     args.add(param3);
/* 1720 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2, Object param3, Object param4) {
/* 1743 */     ParameterBlock args = new ParameterBlock();
/* 1744 */     args.addSource(src);
/* 1745 */     args.add(param1);
/* 1746 */     args.add(param2);
/* 1747 */     args.add(param3);
/* 1748 */     args.add(param4);
/* 1749 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2, int param3, int param4) {
/* 1772 */     ParameterBlock args = new ParameterBlock();
/* 1773 */     args.addSource(src);
/* 1774 */     args.add(param1);
/* 1775 */     args.add(param2);
/* 1776 */     args.add(param3);
/* 1777 */     args.add(param4);
/* 1778 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, int param1, int param2, int param3, int param4) {
/* 1801 */     ParameterBlock args = new ParameterBlock();
/* 1802 */     args.addSource(src);
/* 1803 */     args.add(param1);
/* 1804 */     args.add(param2);
/* 1805 */     args.add(param3);
/* 1806 */     args.add(param4);
/* 1807 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, float param1, float param2, float param3, Object param4) {
/* 1830 */     ParameterBlock args = new ParameterBlock();
/* 1831 */     args.addSource(src);
/* 1832 */     args.add(param1);
/* 1833 */     args.add(param2);
/* 1834 */     args.add(param3);
/* 1835 */     args.add(param4);
/* 1836 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2, Object param3, Object param4, Object param5) {
/* 1861 */     ParameterBlock args = new ParameterBlock();
/* 1862 */     args.addSource(src);
/* 1863 */     args.add(param1);
/* 1864 */     args.add(param2);
/* 1865 */     args.add(param3);
/* 1866 */     args.add(param4);
/* 1867 */     args.add(param5);
/* 1868 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, float param1, float param2, float param3, float param4, Object param5) {
/* 1892 */     ParameterBlock args = new ParameterBlock();
/* 1893 */     args.addSource(src);
/* 1894 */     args.add(param1);
/* 1895 */     args.add(param2);
/* 1896 */     args.add(param3);
/* 1897 */     args.add(param4);
/* 1898 */     args.add(param5);
/* 1899 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, float param1, int param2, float param3, float param4, Object param5) {
/* 1924 */     ParameterBlock args = new ParameterBlock();
/* 1925 */     args.addSource(src);
/* 1926 */     args.add(param1);
/* 1927 */     args.add(param2);
/* 1928 */     args.add(param3);
/* 1929 */     args.add(param4);
/* 1930 */     args.add(param5);
/* 1931 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, Object param1, Object param2, Object param3, Object param4, Object param5, Object param6) {
/* 1958 */     ParameterBlock args = new ParameterBlock();
/* 1959 */     args.addSource(src);
/* 1960 */     args.add(param1);
/* 1961 */     args.add(param2);
/* 1962 */     args.add(param3);
/* 1963 */     args.add(param4);
/* 1964 */     args.add(param5);
/* 1965 */     args.add(param6);
/* 1966 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src, int param1, int param2, int param3, int param4, int param5, Object param6) {
/* 1992 */     ParameterBlock args = new ParameterBlock();
/* 1993 */     args.addSource(src);
/* 1994 */     args.add(param1);
/* 1995 */     args.add(param2);
/* 1996 */     args.add(param3);
/* 1997 */     args.add(param4);
/* 1998 */     args.add(param5);
/* 1999 */     args.add(param6);
/* 2000 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src1, RenderedImage src2) {
/* 2014 */     ParameterBlock args = new ParameterBlock();
/* 2015 */     args.addSource(src1);
/* 2016 */     args.addSource(src2);
/* 2017 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static RenderedOp create(String opName, RenderedImage src1, RenderedImage src2, Object param1, Object param2, Object param3, Object param4) {
/* 2043 */     ParameterBlock args = new ParameterBlock();
/* 2044 */     args.addSource(src1);
/* 2045 */     args.addSource(src2);
/* 2046 */     args.add(param1);
/* 2047 */     args.add(param2);
/* 2048 */     args.add(param3);
/* 2049 */     args.add(param4);
/* 2050 */     return create(opName, args, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public static Collection createCollection(String opName, ParameterBlock args) {
/* 2062 */     return createCollection(opName, args, null);
/*      */   }
/*      */   
/*      */   public static RenderableOp createRenderable(String opName, ParameterBlock args, RenderingHints hints) {
/* 2108 */     return defaultInstance.createRenderableNS(opName, args, hints);
/*      */   }
/*      */   
/*      */   public static RenderableOp createRenderable(String opName, ParameterBlock args) {
/* 2148 */     return defaultInstance.createRenderableNS(opName, args, null);
/*      */   }
/*      */   
/*      */   public RenderableOp createRenderableNS(String opName, ParameterBlock args, RenderingHints hints) {
/* 2222 */     if (opName == null)
/* 2223 */       throw new IllegalArgumentException(JaiI18N.getString("JAI14")); 
/* 2224 */     if (args == null)
/* 2225 */       throw new IllegalArgumentException(JaiI18N.getString("JAI15")); 
/* 2228 */     String modeName = "renderable";
/* 2231 */     OperationDescriptor odesc = (OperationDescriptor)this.operationRegistry.getDescriptor(modeName, opName);
/* 2234 */     if (odesc == null)
/* 2235 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI0")); 
/* 2239 */     if (!RenderableImage.class.isAssignableFrom(odesc.getDestClass(modeName)))
/* 2240 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI4")); 
/* 2249 */     StringBuffer msg = new StringBuffer();
/* 2250 */     args = (ParameterBlock)args.clone();
/* 2251 */     if (!odesc.validateArguments(modeName, args, msg))
/* 2252 */       throw new IllegalArgumentException(msg.toString()); 
/* 2256 */     RenderableOp op = new RenderableOp(this.operationRegistry, opName, args, mergeRenderingHints(this.renderingHints, hints));
/* 2261 */     return op;
/*      */   }
/*      */   
/*      */   public RenderableOp createRenderableNS(String opName, ParameterBlock args) {
/* 2334 */     return createRenderableNS(opName, args, null);
/*      */   }
/*      */   
/*      */   public static Collection createRenderableCollection(String opName, ParameterBlock args, RenderingHints hints) {
/* 2380 */     return defaultInstance.createRenderableCollectionNS(opName, args, hints);
/*      */   }
/*      */   
/*      */   public static Collection createRenderableCollection(String opName, ParameterBlock args) {
/* 2422 */     return defaultInstance.createRenderableCollectionNS(opName, args, null);
/*      */   }
/*      */   
/*      */   public Collection createRenderableCollectionNS(String opName, ParameterBlock args) {
/* 2498 */     return createRenderableCollectionNS(opName, args, null);
/*      */   }
/*      */   
/*      */   public Collection createRenderableCollectionNS(String opName, ParameterBlock args, RenderingHints hints) {
/* 2581 */     if (opName == null)
/* 2582 */       throw new IllegalArgumentException(JaiI18N.getString("JAI14")); 
/* 2583 */     if (args == null)
/* 2584 */       throw new IllegalArgumentException(JaiI18N.getString("JAI15")); 
/* 2587 */     String modeName = "renderableCollection";
/* 2590 */     OperationDescriptor odesc = (OperationDescriptor)this.operationRegistry.getDescriptor(modeName, opName);
/* 2593 */     if (odesc == null)
/* 2594 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI0")); 
/* 2598 */     Class destClass = odesc.getDestClass(modeName);
/* 2600 */     if (!RenderableImage.class.isAssignableFrom(destClass) && !CollectionImage.class.isAssignableFrom(destClass))
/* 2602 */       throw new IllegalArgumentException(opName + ": " + JaiI18N.getString("JAI6")); 
/* 2611 */     StringBuffer msg = new StringBuffer();
/* 2612 */     args = (ParameterBlock)args.clone();
/* 2613 */     RenderingHints mergedHints = mergeRenderingHints(this.renderingHints, hints);
/* 2615 */     if (odesc.validateArguments(modeName, args, msg)) {
/* 2616 */       if (RenderableImage.class.isAssignableFrom(destClass)) {
/* 2617 */         Vector v = new Vector(1);
/* 2618 */         RenderableOp op = new RenderableOp(this.operationRegistry, opName, args, mergedHints);
/* 2621 */         v.add(op);
/* 2622 */         return v;
/*      */       } 
/* 2624 */       CollectionOp cOp = new CollectionOp(this.operationRegistry, opName, args, mergedHints, true);
/* 2629 */       if (odesc.isImmediate()) {
/* 2630 */         Collection coll = null;
/* 2631 */         coll = cOp.getCollection();
/* 2632 */         if (coll == null)
/* 2633 */           return null; 
/*      */       } 
/* 2637 */       return cOp;
/*      */     } 
/* 2641 */     int numSources = odesc.getNumSources();
/* 2642 */     Vector sources = args.getSources();
/* 2648 */     Iterator[] iters = new Iterator[numSources];
/* 2649 */     Iterator iter = null;
/* 2650 */     int size = Integer.MAX_VALUE;
/* 2651 */     for (int i = 0; i < numSources; i++) {
/* 2652 */       Object s = sources.elementAt(i);
/* 2653 */       if (s instanceof Collection) {
/* 2654 */         iters[i] = ((Collection)s).iterator();
/* 2655 */         if (iter == null || ((Collection)s).size() < size) {
/* 2656 */           iter = iters[i];
/* 2657 */           size = ((Collection)s).size();
/*      */         } 
/*      */       } 
/*      */     } 
/* 2662 */     if (iter == null)
/* 2667 */       throw new IllegalArgumentException(msg.toString()); 
/* 2671 */     Collection col = null;
/* 2672 */     for (int j = 0; j < numSources; j++) {
/* 2673 */       Object s = sources.elementAt(j);
/* 2674 */       if (s instanceof Collection)
/*      */         try {
/* 2676 */           col = (Collection)s.getClass().newInstance();
/*      */           break;
/* 2678 */         } catch (Exception e) {
/* 2680 */           sendExceptionToListener(JaiI18N.getString("JAI16") + s.getClass().getName(), e);
/*      */         }  
/*      */     } 
/* 2686 */     if (col == null)
/* 2687 */       col = new Vector(); 
/* 2691 */     Class[] sourceClasses = odesc.getSourceClasses(modeName);
/* 2693 */     while (iter.hasNext()) {
/* 2694 */       ParameterBlock pb = new ParameterBlock();
/* 2695 */       pb.setParameters(args.getParameters());
/* 2697 */       for (int k = 0; k < numSources; k++) {
/* 2699 */         Object nextSource = null;
/* 2700 */         if (iters[k] == null) {
/* 2701 */           nextSource = sources.elementAt(k);
/*      */         } else {
/* 2703 */           nextSource = iters[k].next();
/*      */         } 
/* 2710 */         if (!sourceClasses[k].isAssignableFrom(nextSource.getClass()) && !(nextSource instanceof Collection))
/* 2712 */           throw new IllegalArgumentException(msg.toString()); 
/* 2714 */         pb.addSource(nextSource);
/*      */       } 
/* 2717 */       Collection c = createRenderableCollectionNS(opName, pb, mergedHints);
/* 2719 */       if (c instanceof Vector && c.size() == 1 && ((Vector)c).elementAt(0) instanceof RenderableOp) {
/* 2722 */         col.add(((Vector)c).elementAt(0));
/*      */         continue;
/*      */       } 
/* 2724 */       col.add(c);
/*      */     } 
/* 2728 */     return col;
/*      */   }
/*      */   
/*      */   static class RenderingKey extends RenderingHints.Key {
/* 2742 */     private static Class JAIclass = (JAI.class$javax$media$jai$JAI == null) ? (JAI.class$javax$media$jai$JAI = JAI.class$("javax.media.jai.JAI")) : JAI.class$javax$media$jai$JAI;
/*      */     
/*      */     private Class objectClass;
/*      */     
/*      */     RenderingKey(int privateKey, Class objectClass) {
/* 2747 */       super(privateKey);
/* 2748 */       this.objectClass = objectClass;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object val) {
/* 2752 */       return this.objectClass.isInstance(val);
/*      */     }
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/* 2764 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */   public void setRenderingHints(RenderingHints hints) {
/* 2778 */     if (hints == null)
/* 2779 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2781 */     this.renderingHints = hints;
/*      */   }
/*      */   
/*      */   public void clearRenderingHints() {
/* 2789 */     this.renderingHints = new RenderingHints(null);
/*      */   }
/*      */   
/*      */   public Object getRenderingHint(RenderingHints.Key key) {
/* 2801 */     if (key == null)
/* 2802 */       throw new IllegalArgumentException(JaiI18N.getString("JAI7")); 
/* 2804 */     return this.renderingHints.get(key);
/*      */   }
/*      */   
/*      */   public void setRenderingHint(RenderingHints.Key key, Object value) {
/* 2819 */     if (key == null)
/* 2820 */       throw new IllegalArgumentException(JaiI18N.getString("JAI7")); 
/* 2822 */     if (value == null)
/* 2823 */       throw new IllegalArgumentException(JaiI18N.getString("JAI9")); 
/*      */     try {
/* 2826 */       this.renderingHints.put(key, value);
/* 2827 */     } catch (Exception e) {
/* 2828 */       throw new IllegalArgumentException(e.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeRenderingHint(RenderingHints.Key key) {
/* 2837 */     this.renderingHints.remove(key);
/*      */   }
/*      */   
/*      */   public void setImagingListener(ImagingListener listener) {
/*      */     ImagingListenerImpl imagingListenerImpl;
/* 2853 */     if (listener == null)
/* 2854 */       imagingListenerImpl = ImagingListenerImpl.getInstance(); 
/* 2855 */     this.renderingHints.put(KEY_IMAGING_LISTENER, imagingListenerImpl);
/* 2856 */     this.imagingListener = (ImagingListener)imagingListenerImpl;
/*      */   }
/*      */   
/*      */   public ImagingListener getImagingListener() {
/* 2867 */     return this.imagingListener;
/*      */   }
/*      */   
/*      */   private void sendExceptionToListener(String message, Exception e) {
/* 2871 */     ImagingListener listener = getImagingListener();
/* 2872 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\JAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */