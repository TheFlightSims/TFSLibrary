/*     */ package com.world2xplane.Rules.Regions;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public class RegionTree<V> {
/*     */   Node<V> root;
/*     */   
/*     */   public static class Node<V> {
/*     */     Envelope nodeRect;
/*     */     
/*     */     Envelope valueRect;
/*     */     
/*     */     Point2D valuePoint;
/*     */     
/*     */     Map<Envelope, V> values;
/*     */     
/*     */     Node<V> tl;
/*     */     
/*     */     Node<V> tr;
/*     */     
/*     */     Node<V> bl;
/*     */     
/*     */     Node<V> br;
/*     */     
/*     */     boolean split;
/*     */     
/*     */     boolean color = false;
/*     */     
/*     */     public Node(Envelope rect) {
/*  80 */       this.nodeRect = rect;
/*  82 */       this.valuePoint = null;
/*  83 */       this.valueRect = null;
/*  84 */       this.values = new HashMap<>();
/*  86 */       this.tl = null;
/*  87 */       this.tr = null;
/*  88 */       this.bl = null;
/*  89 */       this.br = null;
/*  91 */       this.split = false;
/*     */     }
/*     */     
/*     */     public boolean contains(Point2D p) {
/*  99 */       return this.nodeRect.contains(p.x(), p.y());
/*     */     }
/*     */     
/*     */     public boolean intersects(Envelope rect) {
/* 107 */       return this.nodeRect.intersects(rect);
/*     */     }
/*     */     
/*     */     public V find(Point2D p) {
/* 116 */       if (!this.split) {
/* 117 */         for (Map.Entry<Envelope, V> entry : this.values.entrySet()) {
/* 118 */           Envelope rect = entry.getKey();
/* 119 */           if (rect.contains(p.x(), p.y()))
/* 120 */             return entry.getValue(); 
/*     */         } 
/* 124 */         return null;
/*     */       } 
/* 127 */       if (this.tl.contains(p))
/* 128 */         return this.tl.find(p); 
/* 129 */       if (this.tr.contains(p))
/* 130 */         return this.tr.find(p); 
/* 131 */       if (this.bl.contains(p))
/* 132 */         return this.bl.find(p); 
/* 133 */       if (this.br.contains(p))
/* 134 */         return this.br.find(p); 
/* 136 */       return null;
/*     */     }
/*     */     
/*     */     public void insert(Point2D p, Envelope rect, V v, int depth) throws Exception {
/* 148 */       if (!this.nodeRect.contains(p.x(), p.y()))
/*     */         return; 
/* 155 */       if (this.valuePoint == null && !this.split) {
/* 156 */         this.values.put(rect, v);
/* 157 */         this.valueRect = rect;
/* 158 */         this.valuePoint = p;
/*     */         return;
/*     */       } 
/* 162 */       if (p.equals(this.valuePoint))
/* 163 */         throw new Exception("Specified point " + p + " is already in use."); 
/* 168 */       if (!this.split) {
/* 171 */         double totalWidth = this.nodeRect.getWidth();
/* 172 */         double totalHeight = this.nodeRect.getHeight();
/* 173 */         int w1 = (int)(totalWidth / 2.0D);
/* 174 */         int h1 = (int)(totalHeight / 2.0D);
/* 175 */         int w2 = (int)(totalWidth - w1);
/* 176 */         int h2 = (int)(totalHeight - h1);
/* 181 */         this.tl = new Node(new Envelope(this.nodeRect.getMinX(), this.nodeRect.getMinY(), this.nodeRect.getMinX() + w1, this.nodeRect.getMinY() - h1));
/* 184 */         this.tr = new Node(new Envelope(this.nodeRect.getMinX() + w1, this.nodeRect.getMinY(), this.nodeRect.getMinX() + w1 + w2, this.nodeRect.getMinY() - h1));
/* 187 */         this.bl = new Node(new Envelope(this.nodeRect.getMinX(), this.nodeRect.getMinY() - h1, this.nodeRect.getMinX() + w1, this.nodeRect.getMinY() - h1 - h2));
/* 190 */         this.br = new Node(new Envelope(this.nodeRect.getMinX() + w1, this.nodeRect.getMinY() - h1, this.nodeRect.getMinX() + w1 + w2, this.nodeRect.getMinY() - h1 - h2));
/* 194 */         this.split = true;
/* 197 */         V value = this.values.get(this.valueRect);
/* 198 */         if (this.tl.contains(this.valuePoint)) {
/* 199 */           this.tl.insert(this.valuePoint, this.valueRect, value, depth + 1);
/* 200 */         } else if (this.tr.contains(this.valuePoint)) {
/* 201 */           this.tr.insert(this.valuePoint, this.valueRect, value, depth + 1);
/* 202 */         } else if (this.bl.contains(this.valuePoint)) {
/* 203 */           this.bl.insert(this.valuePoint, this.valueRect, value, depth + 1);
/* 204 */         } else if (this.br.contains(this.valuePoint)) {
/* 205 */           this.br.insert(this.valuePoint, this.valueRect, value, depth + 1);
/*     */         } else {
/* 207 */           throw new IllegalStateException(this.valuePoint + " is not " + "in " + this.tl.nodeRect + ";\n" + "or " + this.tr.nodeRect + ";\n" + "or " + this.bl.nodeRect + ";\n" + "or " + this.br.nodeRect);
/*     */         } 
/* 216 */         for (Map.Entry<Envelope, V> entry : this.values.entrySet()) {
/* 217 */           Envelope candidate = entry.getKey();
/* 218 */           if (this.tl.intersects(candidate)) {
/* 219 */             this.tl.values.put(candidate, entry.getValue());
/* 220 */             this.tl.color = true;
/*     */           } 
/* 222 */           if (this.tr.intersects(candidate)) {
/* 223 */             this.tr.values.put(candidate, entry.getValue());
/* 224 */             this.tr.color = true;
/*     */           } 
/* 226 */           if (this.bl.intersects(candidate)) {
/* 227 */             this.bl.values.put(candidate, entry.getValue());
/* 228 */             this.bl.color = true;
/*     */           } 
/* 230 */           if (this.br.intersects(candidate)) {
/* 231 */             this.br.values.put(candidate, entry.getValue());
/* 232 */             this.br.color = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 238 */       if (this.tl.contains(p)) {
/* 239 */         this.tl.insert(p, rect, v, depth + 1);
/* 240 */       } else if (this.tr.contains(p)) {
/* 241 */         this.tr.insert(p, rect, v, depth + 1);
/* 242 */       } else if (this.bl.contains(p)) {
/* 243 */         this.bl.insert(p, rect, v, depth + 1);
/* 244 */       } else if (this.br.contains(p)) {
/* 245 */         this.br.insert(p, rect, v, depth + 1);
/*     */       } else {
/* 247 */         throw new IllegalStateException(p + " is not in " + this.tl.nodeRect + ";\n" + "or " + this.tr.nodeRect + ";\n" + "or " + this.bl.nodeRect + ";\n" + "or " + this.br.nodeRect);
/*     */       } 
/* 253 */       this.values.clear();
/* 254 */       this.valueRect = null;
/* 255 */       this.valuePoint = null;
/*     */     }
/*     */     
/*     */     public boolean findIntersections(Envelope rect) {
/* 265 */       if (!this.split) {
/* 266 */         for (Map.Entry<Envelope, V> entry : this.values.entrySet()) {
/* 267 */           Envelope candidate = entry.getKey();
/* 268 */           if (candidate.intersects(rect))
/* 269 */             return true; 
/*     */         } 
/* 272 */         return false;
/*     */       } 
/* 275 */       if (this.tl.intersects(rect) && this.tl.findIntersections(rect))
/* 276 */         return true; 
/* 277 */       if (this.tr.intersects(rect) && this.tr.findIntersections(rect))
/* 278 */         return true; 
/* 279 */       if (this.bl.intersects(rect) && this.bl.findIntersections(rect))
/* 280 */         return true; 
/* 281 */       if (this.br.intersects(rect) && this.br.findIntersections(rect))
/* 282 */         return true; 
/* 285 */       return false;
/*     */     }
/*     */     
/*     */     public void assign(Envelope rect, V value) {
/* 296 */       if (!this.split) {
/* 297 */         this.values.put(rect, value);
/* 298 */         this.color = true;
/*     */         return;
/*     */       } 
/* 302 */       if (this.tl.intersects(rect))
/* 303 */         this.tl.assign(rect, value); 
/* 305 */       if (this.tr.intersects(rect))
/* 306 */         this.tr.assign(rect, value); 
/* 308 */       if (this.bl.intersects(rect))
/* 309 */         this.bl.assign(rect, value); 
/* 311 */       if (this.br.intersects(rect))
/* 312 */         this.br.assign(rect, value); 
/*     */     }
/*     */   }
/*     */   
/*     */   public RegionTree() {
/* 324 */     this(0);
/*     */   }
/*     */   
/*     */   public RegionTree(int size) {
/* 333 */     if (size == 0) {
/* 334 */       this.root = new Node<>(new Envelope(-2.147483648E9D, 2.147483647E9D, -2.147483648E9D, 2.147483647E9D));
/*     */     } else {
/* 337 */       this.root = new Node<>(new Envelope(-size, size, size, -size));
/*     */     } 
/*     */   }
/*     */   
/*     */   public RegionTree(Envelope envelope) {
/* 343 */     this.root = new Node<>(envelope);
/*     */   }
/*     */   
/*     */   public void insert(Envelope rect, V value) throws Exception {
/* 355 */     if (this.root.findIntersections(rect))
/* 356 */       throw new Exception(); 
/* 360 */     this.root.insert(new Point2D(rect.getMinX(), rect.getMinY()), rect, value, 0);
/* 361 */     this.root.insert(new Point2D(rect.getMinX(), rect.getMaxY()), rect, value, 0);
/* 362 */     this.root.insert(new Point2D(rect.getMaxX(), rect.getMaxY()), rect, value, 0);
/* 363 */     this.root.insert(new Point2D(rect.getMaxX(), rect.getMinY()), rect, value, 0);
/* 369 */     this.root.assign(rect, value);
/*     */   }
/*     */   
/*     */   public V find(Point2D p) {
/* 382 */     Node<V> current = this.root;
/* 383 */     while (current.split) {
/* 384 */       if (!current.contains(p))
/* 385 */         return null; 
/* 391 */       Envelope rect = current.nodeRect;
/* 392 */       if (p.x() > (rect.centre()).x) {
/* 393 */         if (p.y() > (rect.centre()).y) {
/* 394 */           current = current.tr;
/*     */           continue;
/*     */         } 
/* 396 */         current = current.br;
/*     */         continue;
/*     */       } 
/* 399 */       if (p.y() > (rect.centre()).y) {
/* 400 */         current = current.tl;
/*     */         continue;
/*     */       } 
/* 402 */       current = current.bl;
/*     */     } 
/* 421 */     for (Map.Entry<Envelope, V> entry : current.values.entrySet()) {
/* 422 */       Envelope rect = entry.getKey();
/* 423 */       if (rect.contains(p.x(), p.y()))
/* 424 */         return entry.getValue(); 
/*     */     } 
/* 428 */     return null;
/*     */   }
/*     */   
/*     */   private static Integer linearSearch(Map<Envelope, Integer> data, Point2D p) {
/* 436 */     for (Map.Entry<Envelope, Integer> entry : data.entrySet()) {
/* 437 */       Envelope rect = entry.getKey();
/* 438 */       if (rect.contains(p.x(), p.y()))
/* 439 */         return entry.getValue(); 
/*     */     } 
/* 443 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Regions\RegionTree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */