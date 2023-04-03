/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ final class Snapshot extends PlanarImage {
/*     */   SnapshotImage parent;
/*     */   
/*     */   Snapshot next;
/*     */   
/*     */   Snapshot prev;
/*     */   
/* 119 */   Hashtable tiles = new Hashtable();
/*     */   
/*     */   boolean disposed = false;
/*     */   
/*     */   Snapshot(SnapshotImage parent) {
/* 131 */     super(new ImageLayout(parent), null, null);
/* 132 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 155 */     synchronized (this.parent) {
/* 160 */       TileCopy tc = (TileCopy)this.tiles.get(new Point(tileX, tileY));
/* 161 */       if (tc != null)
/* 162 */         return tc.tile; 
/* 163 */       if (this.next != null)
/* 164 */         return this.next.getTile(tileX, tileY); 
/* 166 */       return this.parent.getTrueSource().getTile(tileX, tileY);
/*     */     } 
/*     */   }
/*     */   
/*     */   void setNext(Snapshot next) {
/* 178 */     this.next = next;
/*     */   }
/*     */   
/*     */   void setPrev(Snapshot prev) {
/* 188 */     this.prev = prev;
/*     */   }
/*     */   
/*     */   boolean hasTile(int tileX, int tileY) {
/* 200 */     TileCopy tc = (TileCopy)this.tiles.get(new Point(tileX, tileY));
/* 201 */     return (tc != null);
/*     */   }
/*     */   
/*     */   void addTile(Raster tile, int tileX, int tileY) {
/* 213 */     TileCopy tc = new TileCopy(tile, tileX, tileY);
/* 214 */     this.tiles.put(new Point(tileX, tileY), tc);
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 220 */     synchronized (this.parent) {
/* 222 */       if (this.disposed)
/*     */         return; 
/* 225 */       this.disposed = true;
/* 228 */       if (this.parent.getTail() == this)
/* 229 */         this.parent.setTail(this.prev); 
/* 233 */       if (this.prev != null)
/* 234 */         this.prev.setNext(this.next); 
/* 236 */       if (this.next != null)
/* 237 */         this.next.setPrev(this.prev); 
/* 241 */       if (this.prev != null) {
/* 243 */         Enumeration enumeration = this.tiles.elements();
/* 244 */         while (enumeration.hasMoreElements()) {
/* 245 */           TileCopy tc = enumeration.nextElement();
/* 246 */           if (!this.prev.hasTile(tc.tileX, tc.tileY))
/* 247 */             this.prev.addTile(tc.tile, tc.tileX, tc.tileY); 
/*     */         } 
/*     */       } 
/* 253 */       this.parent = null;
/* 254 */       this.next = this.prev = null;
/* 255 */       this.tiles = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\Snapshot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */