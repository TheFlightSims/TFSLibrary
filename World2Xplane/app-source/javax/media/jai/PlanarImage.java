/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.DataBufferUtils;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.WritableRenderedImage;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public abstract class PlanarImage implements ImageJAI, RenderedImage {
/*      */   private Object UID;
/*      */   
/*      */   protected int minX;
/*      */   
/*      */   protected int minY;
/*      */   
/*      */   protected int width;
/*      */   
/*      */   protected int height;
/*      */   
/*  211 */   private Rectangle bounds = new Rectangle();
/*      */   
/*      */   protected int tileGridXOffset;
/*      */   
/*      */   protected int tileGridYOffset;
/*      */   
/*      */   protected int tileWidth;
/*      */   
/*      */   protected int tileHeight;
/*      */   
/*  236 */   protected SampleModel sampleModel = null;
/*      */   
/*  241 */   protected ColorModel colorModel = null;
/*      */   
/*  252 */   protected TileFactory tileFactory = null;
/*      */   
/*  255 */   private Vector sources = null;
/*      */   
/*  258 */   private Vector sinks = null;
/*      */   
/*  265 */   protected PropertyChangeSupportJAI eventManager = null;
/*      */   
/*  272 */   protected WritablePropertySourceImpl properties = null;
/*      */   
/*  278 */   private SnapshotImage snapshot = null;
/*      */   
/*      */   private WeakReference weakThis;
/*      */   
/*  284 */   private Set tileListeners = null;
/*      */   
/*      */   private boolean disposed = false;
/*      */   
/*      */   private static final int MIN_ARRAYCOPY_SIZE = 64;
/*      */   
/*      */   public PlanarImage() {
/*  299 */     this.weakThis = new WeakReference(this);
/*  302 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  305 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*  308 */     this.UID = ImageUtil.generateID(this);
/*      */   }
/*      */   
/*      */   public PlanarImage(ImageLayout layout, Vector sources, Map properties) {
/*  392 */     this();
/*  395 */     if (layout != null)
/*  396 */       setImageLayout(layout); 
/*  402 */     if (sources != null)
/*  403 */       setSources(sources); 
/*  406 */     if (properties != null) {
/*  408 */       this.properties.addProperties(properties);
/*  411 */       if (properties.containsKey(JAI.KEY_TILE_FACTORY)) {
/*  412 */         Object factoryValue = properties.get(JAI.KEY_TILE_FACTORY);
/*  416 */         if (factoryValue instanceof TileFactory)
/*  417 */           this.tileFactory = (TileFactory)factoryValue; 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void setImageLayout(ImageLayout layout) {
/*  479 */     if (layout == null)
/*  480 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  483 */     if (layout.isValid(1))
/*  484 */       this.minX = layout.getMinX(null); 
/*  486 */     if (layout.isValid(2))
/*  487 */       this.minY = layout.getMinY(null); 
/*  489 */     if (layout.isValid(4))
/*  490 */       this.width = layout.getWidth(null); 
/*  492 */     if (layout.isValid(8))
/*  493 */       this.height = layout.getHeight(null); 
/*  497 */     if (layout.isValid(16))
/*  498 */       this.tileGridXOffset = layout.getTileGridXOffset(null); 
/*  500 */     if (layout.isValid(32))
/*  501 */       this.tileGridYOffset = layout.getTileGridYOffset(null); 
/*  503 */     if (layout.isValid(64)) {
/*  504 */       this.tileWidth = layout.getTileWidth(null);
/*      */     } else {
/*  506 */       this.tileWidth = this.width;
/*      */     } 
/*  508 */     if (layout.isValid(128)) {
/*  509 */       this.tileHeight = layout.getTileHeight(null);
/*      */     } else {
/*  511 */       this.tileHeight = this.height;
/*      */     } 
/*  515 */     if (layout.isValid(256))
/*  516 */       this.sampleModel = layout.getSampleModel(null); 
/*  520 */     if (this.sampleModel != null && this.tileWidth > 0 && this.tileHeight > 0 && (this.sampleModel.getWidth() != this.tileWidth || this.sampleModel.getHeight() != this.tileHeight))
/*  523 */       this.sampleModel = this.sampleModel.createCompatibleSampleModel(this.tileWidth, this.tileHeight); 
/*  529 */     if (layout.isValid(512))
/*  530 */       this.colorModel = layout.getColorModel(null); 
/*  532 */     if (this.colorModel != null && this.sampleModel != null && 
/*  533 */       !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  535 */       throw new IllegalArgumentException(JaiI18N.getString("PlanarImage5")); 
/*      */   }
/*      */   
/*      */   public static PlanarImage wrapRenderedImage(RenderedImage image) {
/*  575 */     if (image == null)
/*  576 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  579 */     if (image instanceof PlanarImage)
/*  580 */       return (PlanarImage)image; 
/*  581 */     if (image instanceof WritableRenderedImage)
/*  582 */       return new WritableRenderedImageAdapter((WritableRenderedImage)image); 
/*  585 */     return new RenderedImageAdapter(image);
/*      */   }
/*      */   
/*      */   public PlanarImage createSnapshot() {
/*  600 */     if (this instanceof WritableRenderedImage) {
/*  601 */       if (this.snapshot == null)
/*  602 */         synchronized (this) {
/*  603 */           this.snapshot = new SnapshotImage(this);
/*      */         }  
/*  606 */       return this.snapshot.createSnapshot();
/*      */     } 
/*  609 */     return this;
/*      */   }
/*      */   
/*      */   public int getMinX() {
/*  618 */     return this.minX;
/*      */   }
/*      */   
/*      */   public int getMaxX() {
/*  630 */     return getMinX() + getWidth();
/*      */   }
/*      */   
/*      */   public int getMinY() {
/*  638 */     return this.minY;
/*      */   }
/*      */   
/*      */   public int getMaxY() {
/*  650 */     return getMinY() + getHeight();
/*      */   }
/*      */   
/*      */   public int getWidth() {
/*  658 */     return this.width;
/*      */   }
/*      */   
/*      */   public int getHeight() {
/*  666 */     return this.height;
/*      */   }
/*      */   
/*      */   public int getNumBands() {
/*  678 */     return getSampleModel().getNumBands();
/*      */   }
/*      */   
/*      */   public Rectangle getBounds() {
/*  701 */     synchronized (this.bounds) {
/*  702 */       this.bounds.setBounds(getMinX(), getMinY(), getWidth(), getHeight());
/*      */     } 
/*  705 */     return this.bounds;
/*      */   }
/*      */   
/*      */   public int getTileGridXOffset() {
/*  713 */     return this.tileGridXOffset;
/*      */   }
/*      */   
/*      */   public int getTileGridYOffset() {
/*  721 */     return this.tileGridYOffset;
/*      */   }
/*      */   
/*      */   public int getTileWidth() {
/*  729 */     return this.tileWidth;
/*      */   }
/*      */   
/*      */   public int getTileHeight() {
/*  737 */     return this.tileHeight;
/*      */   }
/*      */   
/*      */   public int getMinTileX() {
/*  748 */     return XToTileX(getMinX(), getTileGridXOffset(), getTileWidth());
/*      */   }
/*      */   
/*      */   public int getMaxTileX() {
/*  759 */     return XToTileX(getMinX() + getWidth() - 1, getTileGridXOffset(), getTileWidth());
/*      */   }
/*      */   
/*      */   public int getNumXTiles() {
/*  772 */     int x = getMinX();
/*  773 */     int tx = getTileGridXOffset();
/*  774 */     int tw = getTileWidth();
/*  775 */     return XToTileX(x + getWidth() - 1, tx, tw) - XToTileX(x, tx, tw) + 1;
/*      */   }
/*      */   
/*      */   public int getMinTileY() {
/*  786 */     return YToTileY(getMinY(), getTileGridYOffset(), getTileHeight());
/*      */   }
/*      */   
/*      */   public int getMaxTileY() {
/*  797 */     return YToTileY(getMinY() + getHeight() - 1, getTileGridYOffset(), getTileHeight());
/*      */   }
/*      */   
/*      */   public int getNumYTiles() {
/*  810 */     int y = getMinY();
/*  811 */     int ty = getTileGridYOffset();
/*  812 */     int th = getTileHeight();
/*  813 */     return YToTileY(y + getHeight() - 1, ty, th) - YToTileY(y, ty, th) + 1;
/*      */   }
/*      */   
/*      */   public static int XToTileX(int x, int tileGridXOffset, int tileWidth) {
/*  828 */     x -= tileGridXOffset;
/*  829 */     if (x < 0)
/*  830 */       x += 1 - tileWidth; 
/*  832 */     return x / tileWidth;
/*      */   }
/*      */   
/*      */   public static int YToTileY(int y, int tileGridYOffset, int tileHeight) {
/*  847 */     y -= tileGridYOffset;
/*  848 */     if (y < 0)
/*  849 */       y += 1 - tileHeight; 
/*  851 */     return y / tileHeight;
/*      */   }
/*      */   
/*      */   public int XToTileX(int x) {
/*  869 */     return XToTileX(x, getTileGridXOffset(), getTileWidth());
/*      */   }
/*      */   
/*      */   public int YToTileY(int y) {
/*  887 */     return YToTileY(y, getTileGridYOffset(), getTileHeight());
/*      */   }
/*      */   
/*      */   public static int tileXToX(int tx, int tileGridXOffset, int tileWidth) {
/*  896 */     return tx * tileWidth + tileGridXOffset;
/*      */   }
/*      */   
/*      */   public static int tileYToY(int ty, int tileGridYOffset, int tileHeight) {
/*  905 */     return ty * tileHeight + tileGridYOffset;
/*      */   }
/*      */   
/*      */   public int tileXToX(int tx) {
/*  921 */     return tileXToX(tx, getTileGridXOffset(), getTileWidth());
/*      */   }
/*      */   
/*      */   public int tileYToY(int ty) {
/*  937 */     return tileYToY(ty, getTileGridYOffset(), getTileHeight());
/*      */   }
/*      */   
/*      */   public Rectangle getTileRect(int tileX, int tileY) {
/*  958 */     return getBounds().intersection(new Rectangle(tileXToX(tileX), tileYToY(tileY), getTileWidth(), getTileHeight()));
/*      */   }
/*      */   
/*      */   public SampleModel getSampleModel() {
/*  968 */     return this.sampleModel;
/*      */   }
/*      */   
/*      */   public ColorModel getColorModel() {
/*  976 */     return this.colorModel;
/*      */   }
/*      */   
/*      */   public static ColorModel getDefaultColorModel(int dataType, int numBands) {
/* 1009 */     if (dataType < 0 || dataType == 2 || dataType > 5 || numBands < 1 || numBands > 4)
/* 1013 */       return null; 
/* 1016 */     ColorSpace cs = (numBands <= 2) ? ColorSpace.getInstance(1003) : ColorSpace.getInstance(1000);
/* 1020 */     boolean useAlpha = (numBands == 2 || numBands == 4);
/* 1021 */     int transparency = useAlpha ? 3 : 1;
/* 1024 */     return RasterFactory.createComponentColorModel(dataType, cs, useAlpha, false, transparency);
/*      */   }
/*      */   
/*      */   public static ColorModel createColorModel(SampleModel sm) {
/* 1069 */     if (sm == null)
/* 1070 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1073 */     int bands = sm.getNumBands();
/* 1074 */     if (bands < 1 || bands > 4)
/* 1075 */       return null; 
/* 1078 */     if (sm instanceof ComponentSampleModel)
/* 1079 */       return getDefaultColorModel(sm.getDataType(), bands); 
/* 1081 */     if (sm instanceof SinglePixelPackedSampleModel) {
/* 1082 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/* 1085 */       int[] bitMasks = sppsm.getBitMasks();
/* 1086 */       int rmask = 0;
/* 1087 */       int gmask = 0;
/* 1088 */       int bmask = 0;
/* 1089 */       int amask = 0;
/* 1091 */       int numBands = bitMasks.length;
/* 1092 */       if (numBands <= 2) {
/* 1093 */         rmask = gmask = bmask = bitMasks[0];
/* 1094 */         if (numBands == 2)
/* 1095 */           amask = bitMasks[1]; 
/*      */       } else {
/* 1098 */         rmask = bitMasks[0];
/* 1099 */         gmask = bitMasks[1];
/* 1100 */         bmask = bitMasks[2];
/* 1101 */         if (numBands == 4)
/* 1102 */           amask = bitMasks[3]; 
/*      */       } 
/* 1106 */       int[] sampleSize = sppsm.getSampleSize();
/* 1107 */       int bits = 0;
/* 1108 */       for (int i = 0; i < sampleSize.length; i++)
/* 1109 */         bits += sampleSize[i]; 
/* 1112 */       return new DirectColorModel(bits, rmask, gmask, bmask, amask);
/*      */     } 
/* 1114 */     if (ImageUtil.isBinary(sm)) {
/* 1115 */       byte[] comp = { 0, -1 };
/* 1117 */       return new IndexColorModel(1, 2, comp, comp, comp);
/*      */     } 
/* 1120 */     return null;
/*      */   }
/*      */   
/*      */   public TileFactory getTileFactory() {
/* 1130 */     return this.tileFactory;
/*      */   }
/*      */   
/*      */   public int getNumSources() {
/* 1139 */     return (this.sources == null) ? 0 : this.sources.size();
/*      */   }
/*      */   
/*      */   public Vector getSources() {
/* 1147 */     if (getNumSources() == 0)
/* 1148 */       return null; 
/* 1150 */     synchronized (this.sources) {
/* 1151 */       return (Vector)this.sources.clone();
/*      */     } 
/*      */   }
/*      */   
/*      */   public PlanarImage getSource(int index) {
/* 1173 */     if (this.sources == null)
/* 1174 */       throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("PlanarImage0")); 
/* 1178 */     synchronized (this.sources) {
/* 1179 */       return this.sources.get(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void setSources(List sourceList) {
/* 1200 */     if (sourceList == null)
/* 1201 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1204 */     int size = sourceList.size();
/* 1206 */     synchronized (this) {
/* 1207 */       if (this.sources != null) {
/* 1209 */         Iterator it = this.sources.iterator();
/* 1210 */         while (it.hasNext()) {
/* 1211 */           Object src = it.next();
/* 1212 */           if (src instanceof PlanarImage)
/* 1213 */             ((PlanarImage)src).removeSink(this); 
/*      */         } 
/*      */       } 
/* 1217 */       this.sources = new Vector(size);
/*      */     } 
/* 1220 */     synchronized (this.sources) {
/* 1221 */       for (int i = 0; i < size; i++) {
/* 1222 */         Object sourceElement = sourceList.get(i);
/* 1223 */         if (sourceElement == null)
/* 1224 */           throw new IllegalArgumentException(JaiI18N.getString("PlanarImage7")); 
/* 1227 */         this.sources.add((sourceElement instanceof RenderedImage) ? wrapRenderedImage((RenderedImage)sourceElement) : sourceElement);
/* 1232 */         if (sourceElement instanceof PlanarImage)
/* 1233 */           ((PlanarImage)sourceElement).addSink(this); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void removeSources() {
/* 1244 */     if (this.sources != null)
/* 1245 */       synchronized (this) {
/* 1246 */         if (this.sources != null) {
/* 1248 */           Iterator it = this.sources.iterator();
/* 1249 */           while (it.hasNext()) {
/* 1250 */             Object src = it.next();
/* 1251 */             if (src instanceof PlanarImage)
/* 1252 */               ((PlanarImage)src).removeSink(this); 
/*      */           } 
/*      */         } 
/* 1256 */         this.sources = null;
/*      */       }  
/*      */   }
/*      */   
/*      */   public PlanarImage getSourceImage(int index) {
/* 1277 */     if (this.sources == null)
/* 1278 */       throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("PlanarImage0")); 
/* 1282 */     synchronized (this.sources) {
/* 1283 */       return this.sources.get(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getSourceObject(int index) {
/* 1303 */     if (this.sources == null)
/* 1304 */       throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("PlanarImage0")); 
/* 1308 */     synchronized (this.sources) {
/* 1309 */       return this.sources.get(index);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void addSource(Object source) {
/* 1328 */     if (source == null)
/* 1329 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1332 */     if (this.sources == null)
/* 1333 */       synchronized (this) {
/* 1334 */         this.sources = new Vector();
/*      */       }  
/* 1338 */     synchronized (this.sources) {
/* 1340 */       this.sources.add((source instanceof RenderedImage) ? wrapRenderedImage((RenderedImage)source) : source);
/*      */     } 
/* 1345 */     if (source instanceof PlanarImage)
/* 1346 */       ((PlanarImage)source).addSink(this); 
/*      */   }
/*      */   
/*      */   protected void setSource(Object source, int index) {
/* 1372 */     if (source == null)
/* 1373 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1376 */     if (this.sources == null)
/* 1377 */       throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("PlanarImage0")); 
/* 1381 */     synchronized (this.sources) {
/* 1382 */       if (index < this.sources.size() && this.sources.get(index) instanceof PlanarImage)
/* 1384 */         getSourceImage(index).removeSink(this); 
/* 1386 */       this.sources.set(index, (source instanceof RenderedImage) ? wrapRenderedImage((RenderedImage)source) : source);
/*      */     } 
/* 1390 */     if (source instanceof PlanarImage)
/* 1391 */       ((PlanarImage)source).addSink(this); 
/*      */   }
/*      */   
/*      */   protected boolean removeSource(Object source) {
/* 1411 */     if (source == null)
/* 1412 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1415 */     if (this.sources == null)
/* 1416 */       return false; 
/* 1419 */     synchronized (this.sources) {
/* 1420 */       if (source instanceof PlanarImage)
/* 1421 */         ((PlanarImage)source).removeSink(this); 
/* 1423 */       return this.sources.remove(source);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Vector getSinks() {
/* 1449 */     Vector v = null;
/* 1451 */     if (this.sinks != null) {
/* 1452 */       synchronized (this.sinks) {
/* 1453 */         int size = this.sinks.size();
/* 1454 */         v = new Vector(size);
/* 1455 */         for (int i = 0; i < size; i++) {
/* 1456 */           Object o = ((WeakReference)this.sinks.get(i)).get();
/* 1458 */           if (o != null)
/* 1459 */             v.add(o); 
/*      */         } 
/*      */       } 
/* 1464 */       if (v.size() == 0)
/* 1465 */         v = null; 
/*      */     } 
/* 1468 */     return v;
/*      */   }
/*      */   
/*      */   public synchronized boolean addSink(Object sink) {
/* 1483 */     if (sink == null)
/* 1484 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1487 */     if (this.sinks == null)
/* 1488 */       this.sinks = new Vector(); 
/* 1491 */     boolean result = false;
/* 1492 */     if (sink instanceof PlanarImage) {
/* 1493 */       result = this.sinks.add(((PlanarImage)sink).weakThis);
/*      */     } else {
/* 1495 */       result = this.sinks.add(new WeakReference(sink));
/*      */     } 
/* 1498 */     return result;
/*      */   }
/*      */   
/*      */   public synchronized boolean removeSink(Object sink) {
/* 1513 */     if (sink == null)
/* 1514 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1517 */     if (this.sinks == null)
/* 1518 */       return false; 
/* 1521 */     boolean result = false;
/* 1522 */     if (sink instanceof PlanarImage) {
/* 1523 */       result = this.sinks.remove(((PlanarImage)sink).weakThis);
/*      */     } else {
/* 1525 */       Iterator it = this.sinks.iterator();
/* 1526 */       while (it.hasNext()) {
/* 1527 */         Object referent = ((WeakReference)it.next()).get();
/* 1528 */         if (referent == sink) {
/* 1530 */           it.remove();
/* 1531 */           result = true;
/*      */           continue;
/*      */         } 
/* 1533 */         if (referent == null)
/* 1535 */           it.remove(); 
/*      */       } 
/*      */     } 
/* 1540 */     return result;
/*      */   }
/*      */   
/*      */   protected void addSink(PlanarImage sink) {
/* 1554 */     if (sink == null)
/* 1555 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1558 */     if (this.sinks == null)
/* 1559 */       synchronized (this) {
/* 1560 */         this.sinks = new Vector();
/*      */       }  
/* 1564 */     synchronized (this.sinks) {
/* 1565 */       this.sinks.add(sink.weakThis);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean removeSink(PlanarImage sink) {
/* 1585 */     if (sink == null)
/* 1586 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1589 */     if (this.sinks == null)
/* 1590 */       return false; 
/* 1593 */     synchronized (this.sinks) {
/* 1594 */       return this.sinks.remove(sink.weakThis);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeSinks() {
/* 1600 */     if (this.sinks != null)
/* 1601 */       synchronized (this) {
/* 1602 */         this.sinks = null;
/*      */       }  
/*      */   }
/*      */   
/*      */   protected Hashtable getProperties() {
/* 1612 */     return (Hashtable)this.properties.getProperties();
/*      */   }
/*      */   
/*      */   protected void setProperties(Hashtable properties) {
/* 1622 */     this.properties.addProperties(properties);
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/* 1639 */     return this.properties.getProperty(name);
/*      */   }
/*      */   
/*      */   public Class getPropertyClass(String name) {
/* 1656 */     return this.properties.getPropertyClass(name);
/*      */   }
/*      */   
/*      */   public void setProperty(String name, Object value) {
/* 1671 */     this.properties.setProperty(name, value);
/*      */   }
/*      */   
/*      */   public void removeProperty(String name) {
/* 1685 */     this.properties.removeProperty(name);
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/* 1696 */     return this.properties.getPropertyNames();
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames(String prefix) {
/* 1716 */     return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*      */   }
/*      */   
/*      */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 1726 */     this.eventManager.addPropertyChangeListener(listener);
/*      */   }
/*      */   
/*      */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 1739 */     this.eventManager.addPropertyChangeListener(propertyName.toLowerCase(), listener);
/*      */   }
/*      */   
/*      */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 1751 */     this.eventManager.removePropertyChangeListener(listener);
/*      */   }
/*      */   
/*      */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 1762 */     this.eventManager.removePropertyChangeListener(propertyName.toLowerCase(), listener);
/*      */   }
/*      */   
/*      */   private synchronized Set getTileComputationListeners(boolean createIfNull) {
/* 1767 */     if (createIfNull && this.tileListeners == null)
/* 1768 */       this.tileListeners = Collections.synchronizedSet(new HashSet()); 
/* 1770 */     return this.tileListeners;
/*      */   }
/*      */   
/*      */   public synchronized void addTileComputationListener(TileComputationListener listener) {
/* 1787 */     if (listener == null)
/* 1788 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1791 */     Set listeners = getTileComputationListeners(true);
/* 1793 */     listeners.add(listener);
/*      */   }
/*      */   
/*      */   public synchronized void removeTileComputationListener(TileComputationListener listener) {
/* 1808 */     if (listener == null)
/* 1809 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1812 */     Set listeners = getTileComputationListeners(false);
/* 1814 */     if (listeners != null)
/* 1815 */       listeners.remove(listener); 
/*      */   }
/*      */   
/*      */   public TileComputationListener[] getTileComputationListeners() {
/* 1831 */     Set listeners = getTileComputationListeners(false);
/* 1833 */     if (listeners == null)
/* 1834 */       return null; 
/* 1837 */     return (TileComputationListener[])listeners.toArray((Object[])new TileComputationListener[listeners.size()]);
/*      */   }
/*      */   
/*      */   public void getSplits(IntegerSequence xSplits, IntegerSequence ySplits, Rectangle rect) {
/* 1857 */     if (xSplits == null || ySplits == null || rect == null)
/* 1858 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1861 */     int minTileX = XToTileX(rect.x);
/* 1862 */     int maxTileX = XToTileX(rect.x + rect.width - 1);
/* 1863 */     int xTilePos = tileXToX(minTileX);
/* 1864 */     for (int i = minTileX; i <= maxTileX; i++) {
/* 1865 */       xSplits.insert(xTilePos);
/* 1866 */       xTilePos += this.tileWidth;
/*      */     } 
/* 1869 */     int minTileY = YToTileY(rect.y);
/* 1870 */     int maxTileY = YToTileY(rect.y + rect.height - 1);
/* 1871 */     int yTilePos = tileYToY(minTileY);
/* 1872 */     for (int j = minTileY; j <= maxTileY; j++) {
/* 1873 */       ySplits.insert(yTilePos);
/* 1874 */       yTilePos += this.tileHeight;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Point[] getTileIndices(Rectangle region) {
/* 1895 */     if (region == null) {
/* 1896 */       region = (Rectangle)getBounds().clone();
/*      */     } else {
/* 1897 */       if (!region.intersects(getBounds()))
/* 1898 */         return null; 
/* 1900 */       region = region.intersection(getBounds());
/* 1901 */       if (region.isEmpty())
/* 1902 */         return null; 
/*      */     } 
/* 1906 */     if (region == null) {
/* 1907 */       region = getBounds();
/*      */     } else {
/* 1909 */       Rectangle r = new Rectangle(getMinX(), getMinY(), getWidth() + 1, getHeight() + 1);
/* 1911 */       if (!region.intersects(r))
/* 1912 */         return null; 
/* 1914 */       region = region.intersection(r);
/*      */     } 
/* 1918 */     int minTileX = XToTileX(region.x);
/* 1919 */     int maxTileX = XToTileX(region.x + region.width - 1);
/* 1920 */     int minTileY = YToTileY(region.y);
/* 1921 */     int maxTileY = YToTileY(region.y + region.height - 1);
/* 1923 */     Point[] tileIndices = new Point[(maxTileY - minTileY + 1) * (maxTileX - minTileX + 1)];
/* 1926 */     int tileIndexOffset = 0;
/* 1927 */     for (int ty = minTileY; ty <= maxTileY; ty++) {
/* 1928 */       for (int tx = minTileX; tx <= maxTileX; tx++)
/* 1929 */         tileIndices[tileIndexOffset++] = new Point(tx, ty); 
/*      */     } 
/* 1933 */     return tileIndices;
/*      */   }
/*      */   
/*      */   public boolean overlapsMultipleTiles(Rectangle rect) {
/* 1945 */     if (rect == null)
/* 1946 */       throw new IllegalArgumentException("rect == null!"); 
/* 1949 */     Rectangle xsect = rect.intersection(getBounds());
/* 1953 */     return (!xsect.isEmpty() && (XToTileX(xsect.x) != XToTileX(xsect.x + xsect.width - 1) || YToTileY(xsect.y) != YToTileY(xsect.y + xsect.height - 1)));
/*      */   }
/*      */   
/*      */   protected final WritableRaster createWritableRaster(SampleModel sampleModel, Point location) {
/* 1978 */     if (sampleModel == null)
/* 1979 */       throw new IllegalArgumentException("sampleModel == null!"); 
/* 1982 */     return (this.tileFactory != null) ? this.tileFactory.createTile(sampleModel, location) : RasterFactory.createWritableRaster(sampleModel, location);
/*      */   }
/*      */   
/*      */   public Raster getData() {
/* 2016 */     return getData(null);
/*      */   }
/*      */   
/*      */   public Raster getData(Rectangle region) {
/*      */     WritableRaster dstRaster;
/* 2064 */     Rectangle b = getBounds();
/* 2066 */     if (region == null) {
/* 2067 */       region = b;
/* 2068 */     } else if (!region.intersects(b)) {
/* 2069 */       throw new IllegalArgumentException(JaiI18N.getString("PlanarImage4"));
/*      */     } 
/* 2074 */     Rectangle xsect = (region == b) ? region : region.intersection(b);
/* 2077 */     int startTileX = XToTileX(xsect.x);
/* 2078 */     int startTileY = YToTileY(xsect.y);
/* 2079 */     int endTileX = XToTileX(xsect.x + xsect.width - 1);
/* 2080 */     int endTileY = YToTileY(xsect.y + xsect.height - 1);
/* 2082 */     if (startTileX == endTileX && startTileY == endTileY && getTileRect(startTileX, startTileY).contains(region)) {
/* 2085 */       Raster tile = getTile(startTileX, startTileY);
/* 2087 */       if (this instanceof WritableRenderedImage) {
/* 2091 */         SampleModel sm = tile.getSampleModel();
/* 2092 */         if (sm.getWidth() != region.width || sm.getHeight() != region.height)
/* 2094 */           sm = sm.createCompatibleSampleModel(region.width, region.height); 
/* 2097 */         WritableRaster destinationRaster = createWritableRaster(sm, region.getLocation());
/* 2099 */         Raster sourceRaster = tile.getBounds().equals(region) ? tile : tile.createChild(region.x, region.y, region.width, region.height, region.x, region.y, null);
/* 2105 */         JDKWorkarounds.setRect(destinationRaster, sourceRaster);
/* 2106 */         return destinationRaster;
/*      */       } 
/* 2110 */       return tile.getBounds().equals(region) ? tile : tile.createChild(region.x, region.y, region.width, region.height, region.x, region.y, null);
/*      */     } 
/* 2119 */     SampleModel srcSM = getSampleModel();
/* 2120 */     int dataType = srcSM.getDataType();
/* 2121 */     int nbands = srcSM.getNumBands();
/* 2122 */     boolean isBandChild = false;
/* 2124 */     ComponentSampleModel csm = null;
/* 2125 */     int[] bandOffs = null;
/* 2127 */     boolean fastCobblePossible = false;
/* 2128 */     if (srcSM instanceof ComponentSampleModel) {
/* 2129 */       csm = (ComponentSampleModel)srcSM;
/* 2130 */       int ps = csm.getPixelStride();
/* 2131 */       boolean isBandInt = (ps == 1 && nbands > 1);
/* 2132 */       isBandChild = (ps > 1 && nbands != ps);
/* 2133 */       if (!isBandChild && !isBandInt) {
/* 2134 */         bandOffs = csm.getBandOffsets();
/*      */         int i;
/* 2136 */         for (i = 0; i < nbands && 
/* 2137 */           bandOffs[i] < nbands; i++);
/* 2141 */         if (i == nbands)
/* 2142 */           fastCobblePossible = true; 
/*      */       } 
/*      */     } 
/* 2147 */     if (fastCobblePossible) {
/*      */       try {
/* 2152 */         SampleModel interleavedSM = RasterFactory.createPixelInterleavedSampleModel(dataType, region.width, region.height, nbands, region.width * nbands, bandOffs);
/* 2160 */         dstRaster = createWritableRaster(interleavedSM, region.getLocation());
/* 2162 */       } catch (IllegalArgumentException e) {
/* 2163 */         throw new IllegalArgumentException(JaiI18N.getString("PlanarImage2"));
/*      */       } 
/* 2167 */       switch (dataType) {
/*      */         case 0:
/* 2169 */           cobbleByte(region, dstRaster);
/*      */           break;
/*      */         case 2:
/* 2172 */           cobbleShort(region, dstRaster);
/*      */           break;
/*      */         case 1:
/* 2175 */           cobbleUShort(region, dstRaster);
/*      */           break;
/*      */         case 3:
/* 2178 */           cobbleInt(region, dstRaster);
/*      */           break;
/*      */         case 4:
/* 2181 */           cobbleFloat(region, dstRaster);
/*      */           break;
/*      */         case 5:
/* 2184 */           cobbleDouble(region, dstRaster);
/*      */           break;
/*      */       } 
/*      */     } else {
/* 2190 */       SampleModel sm = this.sampleModel;
/* 2191 */       if (sm.getWidth() != region.width || sm.getHeight() != region.height)
/* 2193 */         sm = sm.createCompatibleSampleModel(region.width, region.height); 
/*      */       try {
/* 2198 */         dstRaster = createWritableRaster(sm, region.getLocation());
/* 2200 */       } catch (IllegalArgumentException e) {
/* 2201 */         throw new IllegalArgumentException(JaiI18N.getString("PlanarImage2"));
/*      */       } 
/* 2205 */       for (int j = startTileY; j <= endTileY; j++) {
/* 2206 */         for (int i = startTileX; i <= endTileX; i++) {
/* 2207 */           Raster tile = getTile(i, j);
/* 2209 */           Rectangle subRegion = region.intersection(tile.getBounds());
/* 2211 */           Raster subRaster = tile.createChild(subRegion.x, subRegion.y, subRegion.width, subRegion.height, subRegion.x, subRegion.y, null);
/* 2220 */           if (sm instanceof ComponentSampleModel && isBandChild) {
/* 2224 */             switch (sm.getDataType()) {
/*      */               case 4:
/* 2226 */                 dstRaster.setPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, subRaster.getPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, new float[nbands * subRegion.width * subRegion.height]));
/*      */                 break;
/*      */               case 5:
/* 2239 */                 dstRaster.setPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, subRaster.getPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, new double[nbands * subRegion.width * subRegion.height]));
/*      */                 break;
/*      */               default:
/* 2252 */                 dstRaster.setPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, subRaster.getPixels(subRegion.x, subRegion.y, subRegion.width, subRegion.height, new int[nbands * subRegion.width * subRegion.height]));
/*      */                 break;
/*      */             } 
/*      */           } else {
/* 2266 */             JDKWorkarounds.setRect(dstRaster, subRaster);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2273 */     return dstRaster;
/*      */   }
/*      */   
/*      */   public WritableRaster copyData() {
/* 2279 */     return copyData(null);
/*      */   }
/*      */   
/*      */   public WritableRaster copyData(WritableRaster raster) {
/*      */     Rectangle region;
/* 2310 */     if (raster == null) {
/* 2311 */       region = getBounds();
/* 2313 */       SampleModel sm = getSampleModel();
/* 2314 */       if (sm.getWidth() != region.width || sm.getHeight() != region.height)
/* 2316 */         sm = sm.createCompatibleSampleModel(region.width, region.height); 
/* 2319 */       raster = createWritableRaster(sm, region.getLocation());
/*      */     } else {
/* 2321 */       region = raster.getBounds().intersection(getBounds());
/* 2323 */       if (region.isEmpty())
/* 2324 */         return raster; 
/*      */     } 
/* 2328 */     int startTileX = XToTileX(region.x);
/* 2329 */     int startTileY = YToTileY(region.y);
/* 2330 */     int endTileX = XToTileX(region.x + region.width - 1);
/* 2331 */     int endTileY = YToTileY(region.y + region.height - 1);
/* 2333 */     SampleModel[] sampleModels = { getSampleModel() };
/* 2334 */     int tagID = RasterAccessor.findCompatibleTag(sampleModels, raster.getSampleModel());
/* 2337 */     RasterFormatTag srcTag = new RasterFormatTag(getSampleModel(), tagID);
/* 2338 */     RasterFormatTag dstTag = new RasterFormatTag(raster.getSampleModel(), tagID);
/* 2341 */     for (int ty = startTileY; ty <= endTileY; ty++) {
/* 2342 */       for (int tx = startTileX; tx <= endTileX; tx++) {
/* 2343 */         Raster tile = getTile(tx, ty);
/* 2344 */         Rectangle subRegion = region.intersection(tile.getBounds());
/* 2346 */         RasterAccessor s = new RasterAccessor(tile, subRegion, srcTag, getColorModel());
/* 2348 */         RasterAccessor d = new RasterAccessor(raster, subRegion, dstTag, null);
/* 2350 */         ImageUtil.copyRaster(s, d);
/*      */       } 
/*      */     } 
/* 2353 */     return raster;
/*      */   }
/*      */   
/*      */   public void copyExtendedData(WritableRaster dest, BorderExtender extender) {
/* 2376 */     if (dest == null || extender == null)
/* 2377 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2381 */     Rectangle destBounds = dest.getBounds();
/* 2382 */     Rectangle imageBounds = getBounds();
/* 2383 */     if (imageBounds.contains(destBounds)) {
/* 2384 */       copyData(dest);
/*      */       return;
/*      */     } 
/* 2389 */     Rectangle isect = imageBounds.intersection(destBounds);
/* 2391 */     if (!isect.isEmpty()) {
/* 2393 */       WritableRaster isectRaster = dest.createWritableChild(isect.x, isect.y, isect.width, isect.height, isect.x, isect.y, (int[])null);
/* 2398 */       copyData(isectRaster);
/*      */     } 
/* 2402 */     extender.extend(dest, this);
/*      */   }
/*      */   
/*      */   public Raster getExtendedData(Rectangle region, BorderExtender extender) {
/* 2435 */     if (region == null)
/* 2436 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2439 */     if (getBounds().contains(region))
/* 2440 */       return getData(region); 
/* 2443 */     if (extender == null)
/* 2444 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2448 */     SampleModel destSM = getSampleModel();
/* 2449 */     if (destSM.getWidth() != region.width || destSM.getHeight() != region.height)
/* 2451 */       destSM = destSM.createCompatibleSampleModel(region.width, region.height); 
/* 2456 */     WritableRaster dest = createWritableRaster(destSM, region.getLocation());
/* 2459 */     copyExtendedData(dest, extender);
/* 2460 */     return dest;
/*      */   }
/*      */   
/*      */   public BufferedImage getAsBufferedImage(Rectangle rect, ColorModel cm) {
/* 2497 */     if (cm == null) {
/* 2498 */       cm = getColorModel();
/* 2499 */       if (cm == null)
/* 2500 */         throw new IllegalArgumentException(JaiI18N.getString("PlanarImage6")); 
/*      */     } 
/* 2505 */     if (!JDKWorkarounds.areCompatibleDataModels(this.sampleModel, cm))
/* 2506 */       throw new IllegalArgumentException(JaiI18N.getString("PlanarImage3")); 
/* 2510 */     if (rect == null) {
/* 2511 */       rect = getBounds();
/*      */     } else {
/* 2513 */       rect = getBounds().intersection(rect);
/*      */     } 
/* 2517 */     SampleModel sm = (this.sampleModel.getWidth() != rect.width || this.sampleModel.getHeight() != rect.height) ? this.sampleModel.createCompatibleSampleModel(rect.width, rect.height) : this.sampleModel;
/* 2524 */     WritableRaster ras = createWritableRaster(sm, rect.getLocation());
/* 2525 */     copyData(ras);
/* 2527 */     if (rect.x != 0 || rect.y != 0)
/* 2529 */       ras = RasterFactory.createWritableChild(ras, rect.x, rect.y, rect.width, rect.height, 0, 0, null); 
/* 2534 */     return new BufferedImage(cm, ras, cm.isAlphaPremultiplied(), null);
/*      */   }
/*      */   
/*      */   public BufferedImage getAsBufferedImage() {
/* 2546 */     return getAsBufferedImage(null, null);
/*      */   }
/*      */   
/*      */   public Graphics getGraphics() {
/* 2558 */     throw new IllegalAccessError(JaiI18N.getString("PlanarImage1"));
/*      */   }
/*      */   
/*      */   public abstract Raster getTile(int paramInt1, int paramInt2);
/*      */   
/*      */   public Raster[] getTiles(Point[] tileIndices) {
/* 2594 */     if (tileIndices == null)
/* 2595 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2598 */     int size = tileIndices.length;
/* 2599 */     Raster[] tiles = new Raster[size];
/* 2601 */     for (int i = 0; i < tileIndices.length; i++) {
/* 2602 */       Point p = tileIndices[i];
/* 2603 */       tiles[i] = getTile(p.x, p.y);
/*      */     } 
/* 2606 */     return tiles;
/*      */   }
/*      */   
/*      */   public Raster[] getTiles() {
/* 2617 */     return getTiles(getTileIndices(getBounds()));
/*      */   }
/*      */   
/*      */   public TileRequest queueTiles(Point[] tileIndices) {
/* 2639 */     if (tileIndices == null)
/* 2640 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2643 */     TileComputationListener[] listeners = getTileComputationListeners();
/* 2644 */     return JAI.getDefaultInstance().getTileScheduler().scheduleTiles(this, tileIndices, listeners);
/*      */   }
/*      */   
/*      */   public void cancelTiles(TileRequest request, Point[] tileIndices) {
/* 2667 */     if (request == null)
/* 2668 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 2671 */     JAI.getDefaultInstance().getTileScheduler().cancelTiles(request, tileIndices);
/*      */   }
/*      */   
/*      */   public void prefetchTiles(Point[] tileIndices) {
/* 2692 */     if (tileIndices == null)
/* 2693 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 2696 */     JAI.getDefaultInstance().getTileScheduler().prefetchTiles(this, tileIndices);
/*      */   }
/*      */   
/*      */   public synchronized void dispose() {
/* 2719 */     if (this.disposed)
/*      */       return; 
/* 2722 */     this.disposed = true;
/* 2727 */     Vector srcs = getSources();
/* 2728 */     if (srcs != null) {
/* 2729 */       int numSources = srcs.size();
/* 2730 */       for (int i = 0; i < numSources; i++) {
/* 2731 */         Object src = srcs.get(i);
/* 2732 */         if (src instanceof PlanarImage)
/* 2733 */           ((PlanarImage)src).removeSink(this); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 2749 */     dispose();
/*      */   }
/*      */   
/*      */   private void printBounds() {
/* 2754 */     System.out.println("Bounds: [x=" + getMinX() + ", y=" + getMinY() + ", width=" + getWidth() + ", height=" + getHeight() + "]");
/*      */   }
/*      */   
/*      */   private void printTile(int i, int j) {
/* 2763 */     int xmin = i * getTileWidth() + getTileGridXOffset();
/* 2764 */     int ymin = j * getTileHeight() + getTileGridYOffset();
/* 2766 */     Rectangle imageBounds = getBounds();
/* 2767 */     Rectangle tileBounds = new Rectangle(xmin, ymin, getTileWidth(), getTileHeight());
/* 2770 */     tileBounds = tileBounds.intersection(imageBounds);
/* 2772 */     Raster tile = getTile(i, j);
/* 2774 */     Rectangle realTileBounds = new Rectangle(tile.getMinX(), tile.getMinY(), tile.getWidth(), tile.getHeight());
/* 2778 */     System.out.println("Tile bounds (actual)   = " + realTileBounds);
/* 2779 */     System.out.println("Tile bounds (computed) = " + tileBounds);
/* 2781 */     xmin = tileBounds.x;
/* 2782 */     ymin = tileBounds.y;
/* 2783 */     int xmax = tileBounds.x + tileBounds.width - 1;
/* 2784 */     int ymax = tileBounds.y + tileBounds.height - 1;
/* 2785 */     int numBands = getSampleModel().getNumBands();
/* 2786 */     int[] val = new int[numBands];
/* 2789 */     for (int pj = ymin; pj <= ymax; pj++) {
/* 2790 */       for (int pi = xmin; pi <= xmax; pi++) {
/* 2791 */         tile.getPixel(pi, pj, val);
/* 2792 */         if (numBands == 1) {
/* 2793 */           System.out.print("(" + val[0] + ") ");
/* 2794 */         } else if (numBands == 3) {
/* 2795 */           System.out.print("(" + val[0] + "," + val[1] + "," + val[2] + ") ");
/*      */         } 
/*      */       } 
/* 2799 */       System.out.println();
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2810 */     return "PlanarImage[minX=" + this.minX + " minY=" + this.minY + " width=" + this.width + " height=" + this.height + " tileGridXOffset=" + this.tileGridXOffset + " tileGridYOffset=" + this.tileGridYOffset + " tileWidth=" + this.tileWidth + " tileHeight=" + this.tileHeight + " sampleModel=" + this.sampleModel + " colorModel=" + this.colorModel + "]";
/*      */   }
/*      */   
/*      */   private void cobbleByte(Rectangle bounds, Raster dstRaster) {
/* 2824 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 2827 */     int startX = XToTileX(bounds.x);
/* 2828 */     int startY = YToTileY(bounds.y);
/* 2829 */     int rectXend = bounds.x + bounds.width - 1;
/* 2830 */     int rectYend = bounds.y + bounds.height - 1;
/* 2831 */     int endX = XToTileX(rectXend);
/* 2832 */     int endY = YToTileY(rectYend);
/* 2837 */     DataBufferByte dstDB = (DataBufferByte)dstRaster.getDataBuffer();
/* 2838 */     byte[] dst = dstDB.getData();
/* 2839 */     int dstPS = dstSM.getPixelStride();
/* 2840 */     int dstSS = dstSM.getScanlineStride();
/* 2842 */     boolean tileParamsSet = false;
/* 2843 */     ComponentSampleModel srcSM = null;
/* 2844 */     int srcPS = 0, srcSS = 0;
/* 2848 */     for (int y = startY; y <= endY; y++) {
/* 2849 */       for (int x = startX; x <= endX; x++) {
/* 2850 */         Raster tile = getTile(x, y);
/* 2851 */         if (tile != null) {
/* 2859 */           if (!tileParamsSet) {
/* 2864 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 2865 */             srcPS = srcSM.getPixelStride();
/* 2866 */             srcSS = srcSM.getScanlineStride();
/* 2867 */             tileParamsSet = true;
/*      */           } 
/* 2874 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 2875 */           int srcY1 = yOrg;
/* 2876 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 2877 */           if (bounds.y > srcY1)
/* 2877 */             srcY1 = bounds.y; 
/* 2878 */           if (rectYend < srcY2)
/* 2878 */             srcY2 = rectYend; 
/* 2879 */           int srcH = srcY2 - srcY1 + 1;
/* 2881 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 2882 */           int srcX1 = xOrg;
/* 2883 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 2884 */           if (bounds.x > srcX1)
/* 2884 */             srcX1 = bounds.x; 
/* 2885 */           if (rectXend < srcX2)
/* 2885 */             srcX2 = rectXend; 
/* 2886 */           int srcW = srcX2 - srcX1 + 1;
/* 2888 */           int dstX = srcX1 - bounds.x;
/* 2889 */           int dstY = srcY1 - bounds.y;
/* 2892 */           DataBufferByte srcDB = (DataBufferByte)tile.getDataBuffer();
/* 2893 */           byte[] src = srcDB.getData();
/* 2895 */           int nsamps = srcW * srcPS;
/* 2896 */           boolean useArrayCopy = (nsamps >= 64);
/* 2898 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 2899 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 2900 */           if (useArrayCopy) {
/* 2901 */             for (int row = 0; row < srcH; row++) {
/* 2902 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 2903 */               ySrcIdx += srcSS;
/* 2904 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 2907 */             for (int row = 0; row < srcH; row++) {
/* 2908 */               int xSrcIdx = ySrcIdx;
/* 2909 */               int xDstIdx = yDstIdx;
/* 2910 */               int xEnd = xDstIdx + nsamps;
/* 2911 */               while (xDstIdx < xEnd)
/* 2912 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 2914 */               ySrcIdx += srcSS;
/* 2915 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cobbleShort(Rectangle bounds, Raster dstRaster) {
/* 2925 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 2928 */     int startX = XToTileX(bounds.x);
/* 2929 */     int startY = YToTileY(bounds.y);
/* 2930 */     int rectXend = bounds.x + bounds.width - 1;
/* 2931 */     int rectYend = bounds.y + bounds.height - 1;
/* 2932 */     int endX = XToTileX(rectXend);
/* 2933 */     int endY = YToTileY(rectYend);
/* 2938 */     DataBufferShort dstDB = (DataBufferShort)dstRaster.getDataBuffer();
/* 2939 */     short[] dst = dstDB.getData();
/* 2940 */     int dstPS = dstSM.getPixelStride();
/* 2941 */     int dstSS = dstSM.getScanlineStride();
/* 2943 */     boolean tileParamsSet = false;
/* 2944 */     ComponentSampleModel srcSM = null;
/* 2945 */     int srcPS = 0, srcSS = 0;
/* 2949 */     for (int y = startY; y <= endY; y++) {
/* 2950 */       for (int x = startX; x <= endX; x++) {
/* 2951 */         Raster tile = getTile(x, y);
/* 2952 */         if (tile != null) {
/* 2960 */           if (!tileParamsSet) {
/* 2965 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 2966 */             srcPS = srcSM.getPixelStride();
/* 2967 */             srcSS = srcSM.getScanlineStride();
/* 2968 */             tileParamsSet = true;
/*      */           } 
/* 2975 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 2976 */           int srcY1 = yOrg;
/* 2977 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 2978 */           if (bounds.y > srcY1)
/* 2978 */             srcY1 = bounds.y; 
/* 2979 */           if (rectYend < srcY2)
/* 2979 */             srcY2 = rectYend; 
/* 2980 */           int srcH = srcY2 - srcY1 + 1;
/* 2982 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 2983 */           int srcX1 = xOrg;
/* 2984 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 2985 */           if (bounds.x > srcX1)
/* 2985 */             srcX1 = bounds.x; 
/* 2986 */           if (rectXend < srcX2)
/* 2986 */             srcX2 = rectXend; 
/* 2987 */           int srcW = srcX2 - srcX1 + 1;
/* 2989 */           int dstX = srcX1 - bounds.x;
/* 2990 */           int dstY = srcY1 - bounds.y;
/* 2993 */           DataBufferShort srcDB = (DataBufferShort)tile.getDataBuffer();
/* 2994 */           short[] src = srcDB.getData();
/* 2996 */           int nsamps = srcW * srcPS;
/* 2997 */           boolean useArrayCopy = (nsamps >= 64);
/* 2999 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 3000 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 3001 */           if (useArrayCopy) {
/* 3002 */             for (int row = 0; row < srcH; row++) {
/* 3003 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 3004 */               ySrcIdx += srcSS;
/* 3005 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 3008 */             for (int row = 0; row < srcH; row++) {
/* 3009 */               int xSrcIdx = ySrcIdx;
/* 3010 */               int xDstIdx = yDstIdx;
/* 3011 */               int xEnd = xDstIdx + nsamps;
/* 3012 */               while (xDstIdx < xEnd)
/* 3013 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 3015 */               ySrcIdx += srcSS;
/* 3016 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cobbleUShort(Rectangle bounds, Raster dstRaster) {
/* 3026 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 3029 */     int startX = XToTileX(bounds.x);
/* 3030 */     int startY = YToTileY(bounds.y);
/* 3031 */     int rectXend = bounds.x + bounds.width - 1;
/* 3032 */     int rectYend = bounds.y + bounds.height - 1;
/* 3033 */     int endX = XToTileX(rectXend);
/* 3034 */     int endY = YToTileY(rectYend);
/* 3039 */     DataBufferUShort dstDB = (DataBufferUShort)dstRaster.getDataBuffer();
/* 3040 */     short[] dst = dstDB.getData();
/* 3041 */     int dstPS = dstSM.getPixelStride();
/* 3042 */     int dstSS = dstSM.getScanlineStride();
/* 3044 */     boolean tileParamsSet = false;
/* 3045 */     ComponentSampleModel srcSM = null;
/* 3046 */     int srcPS = 0, srcSS = 0;
/* 3050 */     for (int y = startY; y <= endY; y++) {
/* 3051 */       for (int x = startX; x <= endX; x++) {
/* 3052 */         Raster tile = getTile(x, y);
/* 3053 */         if (tile != null) {
/* 3061 */           if (!tileParamsSet) {
/* 3066 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 3067 */             srcPS = srcSM.getPixelStride();
/* 3068 */             srcSS = srcSM.getScanlineStride();
/* 3069 */             tileParamsSet = true;
/*      */           } 
/* 3076 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 3077 */           int srcY1 = yOrg;
/* 3078 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 3079 */           if (bounds.y > srcY1)
/* 3079 */             srcY1 = bounds.y; 
/* 3080 */           if (rectYend < srcY2)
/* 3080 */             srcY2 = rectYend; 
/* 3081 */           int srcH = srcY2 - srcY1 + 1;
/* 3083 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 3084 */           int srcX1 = xOrg;
/* 3085 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 3086 */           if (bounds.x > srcX1)
/* 3086 */             srcX1 = bounds.x; 
/* 3087 */           if (rectXend < srcX2)
/* 3087 */             srcX2 = rectXend; 
/* 3088 */           int srcW = srcX2 - srcX1 + 1;
/* 3090 */           int dstX = srcX1 - bounds.x;
/* 3091 */           int dstY = srcY1 - bounds.y;
/* 3094 */           DataBufferUShort srcDB = (DataBufferUShort)tile.getDataBuffer();
/* 3095 */           short[] src = srcDB.getData();
/* 3097 */           int nsamps = srcW * srcPS;
/* 3098 */           boolean useArrayCopy = (nsamps >= 64);
/* 3100 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 3101 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 3102 */           if (useArrayCopy) {
/* 3103 */             for (int row = 0; row < srcH; row++) {
/* 3104 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 3105 */               ySrcIdx += srcSS;
/* 3106 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 3109 */             for (int row = 0; row < srcH; row++) {
/* 3110 */               int xSrcIdx = ySrcIdx;
/* 3111 */               int xDstIdx = yDstIdx;
/* 3112 */               int xEnd = xDstIdx + nsamps;
/* 3113 */               while (xDstIdx < xEnd)
/* 3114 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 3116 */               ySrcIdx += srcSS;
/* 3117 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cobbleInt(Rectangle bounds, Raster dstRaster) {
/* 3127 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 3130 */     int startX = XToTileX(bounds.x);
/* 3131 */     int startY = YToTileY(bounds.y);
/* 3132 */     int rectXend = bounds.x + bounds.width - 1;
/* 3133 */     int rectYend = bounds.y + bounds.height - 1;
/* 3134 */     int endX = XToTileX(rectXend);
/* 3135 */     int endY = YToTileY(rectYend);
/* 3140 */     DataBufferInt dstDB = (DataBufferInt)dstRaster.getDataBuffer();
/* 3141 */     int[] dst = dstDB.getData();
/* 3142 */     int dstPS = dstSM.getPixelStride();
/* 3143 */     int dstSS = dstSM.getScanlineStride();
/* 3145 */     boolean tileParamsSet = false;
/* 3146 */     ComponentSampleModel srcSM = null;
/* 3147 */     int srcPS = 0, srcSS = 0;
/* 3151 */     for (int y = startY; y <= endY; y++) {
/* 3152 */       for (int x = startX; x <= endX; x++) {
/* 3153 */         Raster tile = getTile(x, y);
/* 3154 */         if (tile != null) {
/* 3162 */           if (!tileParamsSet) {
/* 3167 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 3168 */             srcPS = srcSM.getPixelStride();
/* 3169 */             srcSS = srcSM.getScanlineStride();
/* 3170 */             tileParamsSet = true;
/*      */           } 
/* 3177 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 3178 */           int srcY1 = yOrg;
/* 3179 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 3180 */           if (bounds.y > srcY1)
/* 3180 */             srcY1 = bounds.y; 
/* 3181 */           if (rectYend < srcY2)
/* 3181 */             srcY2 = rectYend; 
/* 3182 */           int srcH = srcY2 - srcY1 + 1;
/* 3184 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 3185 */           int srcX1 = xOrg;
/* 3186 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 3187 */           if (bounds.x > srcX1)
/* 3187 */             srcX1 = bounds.x; 
/* 3188 */           if (rectXend < srcX2)
/* 3188 */             srcX2 = rectXend; 
/* 3189 */           int srcW = srcX2 - srcX1 + 1;
/* 3191 */           int dstX = srcX1 - bounds.x;
/* 3192 */           int dstY = srcY1 - bounds.y;
/* 3195 */           DataBufferInt srcDB = (DataBufferInt)tile.getDataBuffer();
/* 3196 */           int[] src = srcDB.getData();
/* 3198 */           int nsamps = srcW * srcPS;
/* 3199 */           boolean useArrayCopy = (nsamps >= 64);
/* 3201 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 3202 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 3203 */           if (useArrayCopy) {
/* 3204 */             for (int row = 0; row < srcH; row++) {
/* 3205 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 3206 */               ySrcIdx += srcSS;
/* 3207 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 3210 */             for (int row = 0; row < srcH; row++) {
/* 3211 */               int xSrcIdx = ySrcIdx;
/* 3212 */               int xDstIdx = yDstIdx;
/* 3213 */               int xEnd = xDstIdx + nsamps;
/* 3214 */               while (xDstIdx < xEnd)
/* 3215 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 3217 */               ySrcIdx += srcSS;
/* 3218 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cobbleFloat(Rectangle bounds, Raster dstRaster) {
/* 3228 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 3231 */     int startX = XToTileX(bounds.x);
/* 3232 */     int startY = YToTileY(bounds.y);
/* 3233 */     int rectXend = bounds.x + bounds.width - 1;
/* 3234 */     int rectYend = bounds.y + bounds.height - 1;
/* 3235 */     int endX = XToTileX(rectXend);
/* 3236 */     int endY = YToTileY(rectYend);
/* 3241 */     DataBuffer dstDB = dstRaster.getDataBuffer();
/* 3242 */     float[] dst = DataBufferUtils.getDataFloat(dstDB);
/* 3243 */     int dstPS = dstSM.getPixelStride();
/* 3244 */     int dstSS = dstSM.getScanlineStride();
/* 3246 */     boolean tileParamsSet = false;
/* 3247 */     ComponentSampleModel srcSM = null;
/* 3248 */     int srcPS = 0, srcSS = 0;
/* 3252 */     for (int y = startY; y <= endY; y++) {
/* 3253 */       for (int x = startX; x <= endX; x++) {
/* 3254 */         Raster tile = getTile(x, y);
/* 3255 */         if (tile != null) {
/* 3263 */           if (!tileParamsSet) {
/* 3268 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 3269 */             srcPS = srcSM.getPixelStride();
/* 3270 */             srcSS = srcSM.getScanlineStride();
/* 3271 */             tileParamsSet = true;
/*      */           } 
/* 3278 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 3279 */           int srcY1 = yOrg;
/* 3280 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 3281 */           if (bounds.y > srcY1)
/* 3281 */             srcY1 = bounds.y; 
/* 3282 */           if (rectYend < srcY2)
/* 3282 */             srcY2 = rectYend; 
/* 3283 */           int srcH = srcY2 - srcY1 + 1;
/* 3285 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 3286 */           int srcX1 = xOrg;
/* 3287 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 3288 */           if (bounds.x > srcX1)
/* 3288 */             srcX1 = bounds.x; 
/* 3289 */           if (rectXend < srcX2)
/* 3289 */             srcX2 = rectXend; 
/* 3290 */           int srcW = srcX2 - srcX1 + 1;
/* 3292 */           int dstX = srcX1 - bounds.x;
/* 3293 */           int dstY = srcY1 - bounds.y;
/* 3296 */           DataBuffer srcDB = tile.getDataBuffer();
/* 3297 */           float[] src = DataBufferUtils.getDataFloat(srcDB);
/* 3299 */           int nsamps = srcW * srcPS;
/* 3300 */           boolean useArrayCopy = (nsamps >= 64);
/* 3302 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 3303 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 3304 */           if (useArrayCopy) {
/* 3305 */             for (int row = 0; row < srcH; row++) {
/* 3306 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 3307 */               ySrcIdx += srcSS;
/* 3308 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 3311 */             for (int row = 0; row < srcH; row++) {
/* 3312 */               int xSrcIdx = ySrcIdx;
/* 3313 */               int xDstIdx = yDstIdx;
/* 3314 */               int xEnd = xDstIdx + nsamps;
/* 3315 */               while (xDstIdx < xEnd)
/* 3316 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 3318 */               ySrcIdx += srcSS;
/* 3319 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cobbleDouble(Rectangle bounds, Raster dstRaster) {
/* 3329 */     ComponentSampleModel dstSM = (ComponentSampleModel)dstRaster.getSampleModel();
/* 3332 */     int startX = XToTileX(bounds.x);
/* 3333 */     int startY = YToTileY(bounds.y);
/* 3334 */     int rectXend = bounds.x + bounds.width - 1;
/* 3335 */     int rectYend = bounds.y + bounds.height - 1;
/* 3336 */     int endX = XToTileX(rectXend);
/* 3337 */     int endY = YToTileY(rectYend);
/* 3342 */     DataBuffer dstDB = dstRaster.getDataBuffer();
/* 3343 */     double[] dst = DataBufferUtils.getDataDouble(dstDB);
/* 3344 */     int dstPS = dstSM.getPixelStride();
/* 3345 */     int dstSS = dstSM.getScanlineStride();
/* 3347 */     boolean tileParamsSet = false;
/* 3348 */     ComponentSampleModel srcSM = null;
/* 3349 */     int srcPS = 0, srcSS = 0;
/* 3353 */     for (int y = startY; y <= endY; y++) {
/* 3354 */       for (int x = startX; x <= endX; x++) {
/* 3355 */         Raster tile = getTile(x, y);
/* 3356 */         if (tile != null) {
/* 3364 */           if (!tileParamsSet) {
/* 3369 */             srcSM = (ComponentSampleModel)tile.getSampleModel();
/* 3370 */             srcPS = srcSM.getPixelStride();
/* 3371 */             srcSS = srcSM.getScanlineStride();
/* 3372 */             tileParamsSet = true;
/*      */           } 
/* 3379 */           int yOrg = y * this.tileHeight + this.tileGridYOffset;
/* 3380 */           int srcY1 = yOrg;
/* 3381 */           int srcY2 = srcY1 + this.tileHeight - 1;
/* 3382 */           if (bounds.y > srcY1)
/* 3382 */             srcY1 = bounds.y; 
/* 3383 */           if (rectYend < srcY2)
/* 3383 */             srcY2 = rectYend; 
/* 3384 */           int srcH = srcY2 - srcY1 + 1;
/* 3386 */           int xOrg = x * this.tileWidth + this.tileGridXOffset;
/* 3387 */           int srcX1 = xOrg;
/* 3388 */           int srcX2 = srcX1 + this.tileWidth - 1;
/* 3389 */           if (bounds.x > srcX1)
/* 3389 */             srcX1 = bounds.x; 
/* 3390 */           if (rectXend < srcX2)
/* 3390 */             srcX2 = rectXend; 
/* 3391 */           int srcW = srcX2 - srcX1 + 1;
/* 3393 */           int dstX = srcX1 - bounds.x;
/* 3394 */           int dstY = srcY1 - bounds.y;
/* 3397 */           DataBuffer srcDB = tile.getDataBuffer();
/* 3398 */           double[] src = DataBufferUtils.getDataDouble(srcDB);
/* 3400 */           int nsamps = srcW * srcPS;
/* 3401 */           boolean useArrayCopy = (nsamps >= 64);
/* 3403 */           int ySrcIdx = (srcY1 - yOrg) * srcSS + (srcX1 - xOrg) * srcPS;
/* 3404 */           int yDstIdx = dstY * dstSS + dstX * dstPS;
/* 3405 */           if (useArrayCopy) {
/* 3406 */             for (int row = 0; row < srcH; row++) {
/* 3407 */               System.arraycopy(src, ySrcIdx, dst, yDstIdx, nsamps);
/* 3408 */               ySrcIdx += srcSS;
/* 3409 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } else {
/* 3412 */             for (int row = 0; row < srcH; row++) {
/* 3413 */               int xSrcIdx = ySrcIdx;
/* 3414 */               int xDstIdx = yDstIdx;
/* 3415 */               int xEnd = xDstIdx + nsamps;
/* 3416 */               while (xDstIdx < xEnd)
/* 3417 */                 dst[xDstIdx++] = src[xSrcIdx++]; 
/* 3419 */               ySrcIdx += srcSS;
/* 3420 */               yDstIdx += dstSS;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getImageID() {
/* 3435 */     return this.UID;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PlanarImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */