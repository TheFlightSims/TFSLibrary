/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class BlockContainer extends AbstractBlock implements Block, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 8199508075695195293L;
/*     */   
/*     */   private List blocks;
/*     */   
/*     */   private Arrangement arrangement;
/*     */   
/*     */   public BlockContainer() {
/*  85 */     this(new BorderArrangement());
/*     */   }
/*     */   
/*     */   public BlockContainer(Arrangement arrangement) {
/*  95 */     if (arrangement == null)
/*  96 */       throw new IllegalArgumentException("Null 'arrangement' argument."); 
/*  98 */     this.arrangement = arrangement;
/*  99 */     this.blocks = new ArrayList();
/*     */   }
/*     */   
/*     */   public Arrangement getArrangement() {
/* 108 */     return this.arrangement;
/*     */   }
/*     */   
/*     */   public void setArrangement(Arrangement arrangement) {
/* 117 */     if (arrangement == null)
/* 118 */       throw new IllegalArgumentException("Null 'arrangement' argument."); 
/* 120 */     this.arrangement = arrangement;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 130 */     return this.blocks.isEmpty();
/*     */   }
/*     */   
/*     */   public List getBlocks() {
/* 140 */     return Collections.unmodifiableList(this.blocks);
/*     */   }
/*     */   
/*     */   public void add(Block block) {
/* 149 */     add(block, (Object)null);
/*     */   }
/*     */   
/*     */   public void add(Block block, Object key) {
/* 159 */     this.blocks.add(block);
/* 160 */     this.arrangement.add(block, key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 167 */     this.blocks.clear();
/* 168 */     this.arrangement.clear();
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 181 */     return this.arrangement.arrange(this, g2, constraint);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 191 */     draw(g2, area, (Object)null);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 206 */     EntityBlockParams ebp = null;
/* 207 */     StandardEntityCollection sec = null;
/* 208 */     if (params instanceof EntityBlockParams) {
/* 209 */       ebp = (EntityBlockParams)params;
/* 210 */       if (ebp.getGenerateEntities())
/* 211 */         sec = new StandardEntityCollection(); 
/*     */     } 
/* 214 */     Rectangle2D contentArea = (Rectangle2D)area.clone();
/* 215 */     contentArea = trimMargin(contentArea);
/* 216 */     drawBorder(g2, contentArea);
/* 217 */     contentArea = trimBorder(contentArea);
/* 218 */     contentArea = trimPadding(contentArea);
/* 219 */     AffineTransform saved = g2.getTransform();
/* 220 */     g2.translate(contentArea.getX(), contentArea.getY());
/* 221 */     Iterator iterator = this.blocks.iterator();
/* 222 */     while (iterator.hasNext()) {
/* 223 */       Block block = iterator.next();
/* 224 */       Object r = block.draw(g2, block.getBounds(), params);
/* 225 */       if (sec != null && 
/* 226 */         r instanceof EntityBlockResult) {
/* 227 */         EntityBlockResult ebr = (EntityBlockResult)r;
/* 228 */         EntityCollection ec = ebr.getEntityCollection();
/* 229 */         sec.addAll(ec);
/*     */       } 
/*     */     } 
/* 233 */     g2.setTransform(saved);
/* 234 */     BlockResult result = null;
/* 235 */     if (sec != null) {
/* 236 */       result = new BlockResult();
/* 237 */       result.setEntityCollection((EntityCollection)sec);
/*     */     } 
/* 239 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 250 */     if (obj == this)
/* 251 */       return true; 
/* 253 */     if (!(obj instanceof BlockContainer))
/* 254 */       return false; 
/* 256 */     if (!super.equals(obj))
/* 257 */       return false; 
/* 259 */     BlockContainer that = (BlockContainer)obj;
/* 260 */     if (!this.arrangement.equals(that.arrangement))
/* 261 */       return false; 
/* 263 */     if (!this.blocks.equals(that.blocks))
/* 264 */       return false; 
/* 266 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 277 */     Object clone = super.clone();
/* 279 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\BlockContainer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */