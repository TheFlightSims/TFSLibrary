/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class NullOpImage extends PointOpImage {
/*     */   protected int computeType;
/*     */   
/*     */   private static ImageLayout layoutHelper(RenderedImage source, ImageLayout layout) {
/*  47 */     ImageLayout il = new ImageLayout(source);
/*  51 */     if (layout != null && layout.isValid(512)) {
/*  52 */       ColorModel colorModel = layout.getColorModel(null);
/*  53 */       if (JDKWorkarounds.areCompatibleDataModels(source.getSampleModel(), colorModel))
/*  55 */         il.setColorModel(colorModel); 
/*     */     } 
/*  59 */     return il;
/*     */   }
/*     */   
/*     */   public NullOpImage(RenderedImage source, ImageLayout layout, Map configuration, int computeType) {
/* 100 */     super(PlanarImage.wrapRenderedImage(source).createSnapshot(), layoutHelper(source, layout), configuration, false);
/* 105 */     if (computeType != 1 && computeType != 2 && computeType != 3)
/* 108 */       throw new IllegalArgumentException(JaiI18N.getString("NullOpImage0")); 
/* 111 */     this.computeType = computeType;
/*     */   }
/*     */   
/*     */   public NullOpImage(RenderedImage source, TileCache cache, int computeType, ImageLayout layout) {
/* 148 */     this(source, layout, (cache != null) ? new RenderingHints(JAI.KEY_TILE_CACHE, cache) : null, computeType);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 162 */     return getSource(0).getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   protected synchronized Hashtable getProperties() {
/* 177 */     return getSource(0).getProperties();
/*     */   }
/*     */   
/*     */   protected synchronized void setProperties(Hashtable properties) {
/* 185 */     getSource(0).setProperties(properties);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 193 */     return getSource(0).getPropertyNames();
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 202 */     return getSource(0).getPropertyNames(prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String name) {
/* 211 */     return getSource(0).getPropertyClass(name);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 220 */     return getSource(0).getProperty(name);
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 227 */     getSource(0).setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void removeProperty(String name) {
/* 236 */     getSource(0).removeProperty(name);
/*     */   }
/*     */   
/*     */   public int getOperationComputeType() {
/* 248 */     return this.computeType;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\NullOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */