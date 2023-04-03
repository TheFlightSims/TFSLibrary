/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class RenderedImageList extends CollectionImage implements List, RenderedImage, Serializable {
/*     */   protected RenderedImageList() {}
/*     */   
/*     */   public RenderedImageList(List renderedImageList) {
/*  96 */     if (renderedImageList == null)
/*  97 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList0")); 
/* 100 */     if (renderedImageList.isEmpty())
/* 101 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList1")); 
/* 104 */     Iterator iter = renderedImageList.iterator();
/* 105 */     this.imageCollection = new Vector();
/* 107 */     while (iter.hasNext()) {
/* 108 */       Object item = iter.next();
/* 110 */       if (item instanceof RenderedImage) {
/* 111 */         this.imageCollection.add(item);
/*     */         continue;
/*     */       } 
/* 113 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList2"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private List getList() {
/* 124 */     return (List)this.imageCollection;
/*     */   }
/*     */   
/*     */   public RenderedImage getPrimaryImage() {
/* 133 */     return getList().get(0);
/*     */   }
/*     */   
/*     */   public int getMinX() {
/* 142 */     return ((RenderedImage)getList().get(0)).getMinX();
/*     */   }
/*     */   
/*     */   public int getMinY() {
/* 150 */     return ((RenderedImage)getList().get(0)).getMinY();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 158 */     return ((RenderedImage)getList().get(0)).getWidth();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 166 */     return ((RenderedImage)getList().get(0)).getHeight();
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 174 */     return ((RenderedImage)getList().get(0)).getTileWidth();
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/* 182 */     return ((RenderedImage)getList().get(0)).getTileHeight();
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 191 */     return ((RenderedImage)getList().get(0)).getTileGridXOffset();
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 200 */     return ((RenderedImage)getList().get(0)).getTileGridYOffset();
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/* 209 */     return ((RenderedImage)getList().get(0)).getMinTileX();
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/* 218 */     return ((RenderedImage)getList().get(0)).getNumXTiles();
/*     */   }
/*     */   
/*     */   public int getMinTileY() {
/* 227 */     return ((RenderedImage)getList().get(0)).getMinTileY();
/*     */   }
/*     */   
/*     */   public int getNumYTiles() {
/* 236 */     return ((RenderedImage)getList().get(0)).getNumYTiles();
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 244 */     return ((RenderedImage)getList().get(0)).getSampleModel();
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 252 */     return ((RenderedImage)getList().get(0)).getColorModel();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 270 */     if (name == null)
/* 271 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList0")); 
/* 274 */     return ((RenderedImage)getList().get(0)).getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 287 */     return ((RenderedImage)getList().get(0)).getPropertyNames();
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 295 */     return ((RenderedImage)getList().get(0)).getSources();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 309 */     return ((RenderedImage)getList().get(0)).getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 321 */     return ((RenderedImage)getList().get(0)).getData();
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle bounds) {
/* 334 */     return ((RenderedImage)getList().get(0)).getData(bounds);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster dest) {
/* 356 */     return ((RenderedImage)getList().get(0)).copyData(dest);
/*     */   }
/*     */   
/*     */   public void add(int index, Object element) {
/* 370 */     if (element instanceof RenderedImage) {
/* 371 */       if (index >= 0 && index <= this.imageCollection.size()) {
/* 372 */         ((List)this.imageCollection).add(index, element);
/*     */       } else {
/* 374 */         throw new IndexOutOfBoundsException(JaiI18N.getString("RenderedImageList3"));
/*     */       } 
/*     */     } else {
/* 377 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList2"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/* 393 */     if (index < 0 || index > this.imageCollection.size())
/* 394 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RenderedImageList3")); 
/* 398 */     Vector temp = null;
/* 399 */     Iterator iter = c.iterator();
/* 401 */     while (iter.hasNext()) {
/* 402 */       Object o = iter.next();
/* 404 */       if (o instanceof RenderedImage) {
/* 405 */         if (temp == null)
/* 406 */           temp = new Vector(); 
/* 409 */         temp.add(o);
/*     */       } 
/*     */     } 
/* 413 */     return ((List)this.imageCollection).addAll(index, temp);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 427 */     if (index < 0 || index >= this.imageCollection.size())
/* 428 */       throw new IndexOutOfBoundsException(JaiI18N.getString("RenderedImageList3")); 
/* 431 */     return ((List)this.imageCollection).get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object o) {
/* 437 */     return ((List)this.imageCollection).indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 443 */     return ((List)this.imageCollection).lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 447 */     return ((List)this.imageCollection).listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 451 */     return ((List)this.imageCollection).listIterator(index);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 455 */     return ((List)this.imageCollection).remove(index);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/* 459 */     if (element instanceof RenderedImage)
/* 460 */       return ((List)this.imageCollection).set(index, element); 
/* 463 */     throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList2"));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 467 */     return ((List)this.imageCollection).subList(fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 482 */     if (o == null)
/* 483 */       throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList0")); 
/* 486 */     if (o instanceof RenderedImage) {
/* 487 */       this.imageCollection.add(o);
/* 488 */       return true;
/*     */     } 
/* 490 */     throw new IllegalArgumentException(JaiI18N.getString("RenderedImageList2"));
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/* 502 */     Iterator iter = c.iterator();
/* 503 */     boolean status = false;
/* 505 */     while (iter.hasNext()) {
/* 506 */       Object o = iter.next();
/* 508 */       if (o instanceof RenderedImage) {
/* 509 */         this.imageCollection.add(o);
/* 510 */         status = true;
/*     */       } 
/*     */     } 
/* 514 */     return status;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderedImageList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */