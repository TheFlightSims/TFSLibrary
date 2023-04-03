/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class RenderedImageAdapter extends PlanarImage {
/*     */   protected RenderedImage theImage;
/*     */   
/*     */   private Rectangle tileIndexBounds;
/*     */   
/*     */   static String[] mergePropertyNames(String[] localNames, String[] sourceNames) {
/*  71 */     String[] names = null;
/*  72 */     if (localNames == null || localNames.length == 0) {
/*  73 */       names = sourceNames;
/*  74 */     } else if (sourceNames == null || sourceNames.length == 0) {
/*  75 */       names = localNames;
/*     */     } else {
/*  80 */       Set nameSet = new HashSet((localNames.length + sourceNames.length) / 2);
/*  83 */       int numSourceNames = sourceNames.length;
/*  84 */       for (int i = 0; i < numSourceNames; i++)
/*  85 */         nameSet.add(new CaselessStringKey(sourceNames[i])); 
/*  89 */       int numLocalNames = localNames.length;
/*  90 */       for (int j = 0; j < numLocalNames; j++)
/*  91 */         nameSet.add(new CaselessStringKey(localNames[j])); 
/*  95 */       int numNames = nameSet.size();
/*  96 */       CaselessStringKey[] caselessNames = new CaselessStringKey[numNames];
/*  97 */       nameSet.toArray(caselessNames);
/*  98 */       names = new String[numNames];
/*  99 */       for (int k = 0; k < numNames; k++)
/* 100 */         names[k] = caselessNames[k].getName(); 
/*     */     } 
/* 105 */     if (names != null && names.length == 0)
/* 106 */       names = null; 
/* 109 */     return names;
/*     */   }
/*     */   
/*     */   public RenderedImageAdapter(RenderedImage im) {
/* 120 */     super((im != null) ? new ImageLayout(im) : null, null, null);
/* 121 */     if (im == null)
/* 122 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 124 */     this.theImage = im;
/* 126 */     this.tileIndexBounds = new Rectangle(this.theImage.getMinTileX(), this.theImage.getMinTileY(), this.theImage.getNumXTiles(), this.theImage.getNumYTiles());
/*     */   }
/*     */   
/*     */   public final RenderedImage getWrappedImage() {
/* 139 */     return this.theImage;
/*     */   }
/*     */   
/*     */   public final Raster getTile(int x, int y) {
/* 148 */     return this.tileIndexBounds.contains(x, y) ? this.theImage.getTile(x, y) : null;
/*     */   }
/*     */   
/*     */   public final Raster getData() {
/* 153 */     return this.theImage.getData();
/*     */   }
/*     */   
/*     */   public final Raster getData(Rectangle rect) {
/* 158 */     return this.theImage.getData(rect);
/*     */   }
/*     */   
/*     */   public final WritableRaster copyData(WritableRaster raster) {
/* 163 */     return this.theImage.copyData(raster);
/*     */   }
/*     */   
/*     */   public final String[] getPropertyNames() {
/* 172 */     return mergePropertyNames(super.getPropertyNames(), this.theImage.getPropertyNames());
/*     */   }
/*     */   
/*     */   public final Object getProperty(String name) {
/* 185 */     Object property = super.getProperty(name);
/* 188 */     if (property == Image.UndefinedProperty)
/* 189 */       property = this.theImage.getProperty(name); 
/* 192 */     return property;
/*     */   }
/*     */   
/*     */   public final Class getPropertyClass(String name) {
/* 210 */     Class propClass = super.getPropertyClass(name);
/* 213 */     if (propClass == null) {
/* 215 */       Object propValue = getProperty(name);
/* 217 */       if (propValue != Image.UndefinedProperty)
/* 219 */         propClass = propValue.getClass(); 
/*     */     } 
/* 223 */     return propClass;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderedImageAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */