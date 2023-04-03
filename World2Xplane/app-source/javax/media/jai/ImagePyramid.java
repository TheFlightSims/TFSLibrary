/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class ImagePyramid extends ImageMIPMap {
/*     */   protected RenderedOp upSampler;
/*     */   
/*     */   protected RenderedOp differencer;
/*     */   
/*     */   protected RenderedOp combiner;
/*     */   
/* 102 */   private Vector diffImages = new Vector();
/*     */   
/*     */   protected ImagePyramid() {}
/*     */   
/*     */   public ImagePyramid(RenderedImage image, RenderedOp downSampler, RenderedOp upSampler, RenderedOp differencer, RenderedOp combiner) {
/* 134 */     super(image, downSampler);
/* 136 */     if (upSampler == null || differencer == null || combiner == null)
/* 137 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 140 */     this.upSampler = upSampler;
/* 141 */     this.differencer = differencer;
/* 142 */     this.combiner = combiner;
/*     */   }
/*     */   
/*     */   public ImagePyramid(RenderedOp downSampler, RenderedOp upSampler, RenderedOp differencer, RenderedOp combiner) {
/* 179 */     super(downSampler);
/* 181 */     if (upSampler == null || differencer == null || combiner == null)
/* 182 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 185 */     this.upSampler = upSampler;
/* 186 */     this.differencer = differencer;
/* 187 */     this.combiner = combiner;
/*     */   }
/*     */   
/*     */   public RenderedImage getImage(int level) {
/* 199 */     if (level < 0)
/* 200 */       return null; 
/* 203 */     while (this.currentLevel < level)
/* 204 */       getDownImage(); 
/* 206 */     while (this.currentLevel > level)
/* 207 */       getUpImage(); 
/* 210 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   public RenderedImage getDownImage() {
/* 219 */     this.currentLevel++;
/* 222 */     RenderedOp downOp = duplicate(this.downSampler, vectorize(this.currentImage));
/* 225 */     RenderedOp upOp = duplicate(this.upSampler, vectorize(downOp.getRendering()));
/* 226 */     RenderedOp diffOp = duplicate(this.differencer, vectorize(this.currentImage, upOp.getRendering()));
/* 228 */     this.diffImages.add(diffOp.getRendering());
/* 230 */     this.currentImage = downOp.getRendering();
/* 231 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   public RenderedImage getUpImage() {
/* 244 */     if (this.currentLevel > 0) {
/* 245 */       this.currentLevel--;
/* 248 */       RenderedOp upOp = duplicate(this.upSampler, vectorize(this.currentImage));
/* 251 */       RenderedImage diffImage = this.diffImages.elementAt(this.currentLevel);
/* 253 */       this.diffImages.removeElementAt(this.currentLevel);
/* 255 */       RenderedOp combOp = duplicate(this.combiner, vectorize(upOp.getRendering(), diffImage));
/* 257 */       this.currentImage = combOp.getRendering();
/*     */     } 
/* 260 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   public RenderedImage getDiffImage() {
/* 272 */     RenderedOp downOp = duplicate(this.downSampler, vectorize(this.currentImage));
/* 275 */     RenderedOp upOp = duplicate(this.upSampler, vectorize(downOp.getRendering()));
/* 278 */     RenderedOp diffOp = duplicate(this.differencer, vectorize(this.currentImage, upOp.getRendering()));
/* 281 */     return diffOp.getRendering();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImagePyramid.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */