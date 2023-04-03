/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.TileObserver;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SnapshotImage extends PlanarImage implements TileObserver {
/*     */   private PlanarImage source;
/*     */   
/* 324 */   private Snapshot tail = null;
/*     */   
/* 327 */   private HashSet activeTiles = new HashSet();
/*     */   
/*     */   public SnapshotImage(PlanarImage source) {
/* 337 */     super(new ImageLayout(source), null, null);
/* 340 */     this.source = source;
/* 344 */     if (source instanceof WritableRenderedImage) {
/* 345 */       WritableRenderedImage wri = (WritableRenderedImage)source;
/* 346 */       wri.addTileObserver(this);
/* 348 */       Point[] pts = wri.getWritableTileIndices();
/* 349 */       if (pts != null) {
/* 350 */         int num = pts.length;
/* 351 */         for (int i = 0; i < num; i++) {
/* 353 */           Point p = pts[i];
/* 354 */           this.activeTiles.add(new Point(p.x, p.y));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected PlanarImage getTrueSource() {
/* 368 */     return this.source;
/*     */   }
/*     */   
/*     */   void setTail(Snapshot tail) {
/* 378 */     this.tail = tail;
/*     */   }
/*     */   
/*     */   Snapshot getTail() {
/* 387 */     return this.tail;
/*     */   }
/*     */   
/*     */   private Raster createTileCopy(int tileX, int tileY) {
/* 399 */     int x = tileXToX(tileX);
/* 400 */     int y = tileYToY(tileY);
/* 401 */     Point p = new Point(x, y);
/* 403 */     WritableRaster tile = RasterFactory.createWritableRaster(this.sampleModel, p);
/* 405 */     this.source.copyData(tile);
/* 406 */     return tile;
/*     */   }
/*     */   
/*     */   public PlanarImage createSnapshot() {
/* 421 */     if (this.source instanceof WritableRenderedImage) {
/* 423 */       Snapshot snap = new Snapshot(this);
/* 426 */       Iterator iter = this.activeTiles.iterator();
/* 427 */       while (iter.hasNext()) {
/* 428 */         Point p = iter.next();
/* 431 */         Raster tile = createTileCopy(p.x, p.y);
/* 432 */         snap.addTile(tile, p.x, p.y);
/*     */       } 
/* 436 */       if (this.tail == null) {
/* 437 */         this.tail = snap;
/*     */       } else {
/* 439 */         this.tail.setNext(snap);
/* 440 */         snap.setPrev(this.tail);
/* 441 */         this.tail = snap;
/*     */       } 
/* 445 */       return new SnapshotProxy(snap);
/*     */     } 
/* 447 */     return this.source;
/*     */   }
/*     */   
/*     */   public void tileUpdate(WritableRenderedImage source, int tileX, int tileY, boolean willBeWritable) {
/* 464 */     if (willBeWritable) {
/* 466 */       if (this.tail != null && !this.tail.hasTile(tileX, tileY))
/* 467 */         this.tail.addTile(createTileCopy(tileX, tileY), tileX, tileY); 
/* 470 */       this.activeTiles.add(new Point(tileX, tileY));
/*     */     } else {
/* 473 */       this.activeTiles.remove(new Point(tileX, tileY));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 486 */     return this.source.getTile(tileX, tileY);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\SnapshotImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */