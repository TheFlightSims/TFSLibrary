/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class ImageMIPMap implements ImageJAI {
/*     */   protected RenderedImage highestImage;
/*     */   
/*     */   protected RenderedImage currentImage;
/*     */   
/*  57 */   protected int currentLevel = 0;
/*     */   
/*     */   protected RenderedOp downSampler;
/*     */   
/*  67 */   protected PropertyChangeSupportJAI eventManager = null;
/*     */   
/*  74 */   protected WritablePropertySourceImpl properties = null;
/*     */   
/*     */   protected ImageMIPMap() {
/*  78 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  79 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*     */   }
/*     */   
/*     */   public ImageMIPMap(RenderedImage image, AffineTransform transform, Interpolation interpolation) {
/* 103 */     this();
/* 105 */     if (image == null || transform == null)
/* 106 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 109 */     ParameterBlock pb = new ParameterBlock();
/* 110 */     pb.addSource(image);
/* 111 */     pb.add(transform);
/* 112 */     pb.add(interpolation);
/* 114 */     this.downSampler = JAI.create("affine", pb);
/* 115 */     this.downSampler.removeSources();
/* 117 */     this.highestImage = image;
/* 118 */     this.currentImage = this.highestImage;
/*     */   }
/*     */   
/*     */   public ImageMIPMap(RenderedImage image, RenderedOp downSampler) {
/* 139 */     this();
/* 140 */     if (image == null || downSampler == null)
/* 141 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 144 */     this.highestImage = image;
/* 145 */     this.currentImage = this.highestImage;
/* 146 */     this.downSampler = downSampler;
/*     */   }
/*     */   
/*     */   public ImageMIPMap(RenderedOp downSampler) {
/* 177 */     this();
/* 179 */     if (downSampler == null)
/* 180 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 183 */     if (downSampler.getNumSources() == 0)
/* 184 */       throw new IllegalArgumentException(JaiI18N.getString("ImageMIPMap0")); 
/* 189 */     RenderedOp op = downSampler;
/*     */     while (true) {
/* 191 */       Object src = op.getNodeSource(0);
/* 193 */       if (src instanceof RenderedOp) {
/* 194 */         RenderedOp srcOp = (RenderedOp)src;
/* 196 */         if (srcOp.getNumSources() == 0) {
/* 197 */           this.highestImage = srcOp;
/* 198 */           op.removeSources();
/*     */           break;
/*     */         } 
/* 201 */         op = srcOp;
/*     */         continue;
/*     */       } 
/* 203 */       if (src instanceof RenderedImage) {
/* 204 */         this.highestImage = (RenderedImage)src;
/* 205 */         op.removeSources();
/*     */         break;
/*     */       } 
/* 208 */       throw new IllegalArgumentException(JaiI18N.getString("ImageMIPMap1"));
/*     */     } 
/* 213 */     this.currentImage = this.highestImage;
/* 214 */     this.downSampler = downSampler;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 229 */     return this.properties.getPropertyNames();
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 248 */     return this.properties.getPropertyNames(prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String name) {
/* 265 */     return this.properties.getPropertyClass(name);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 280 */     return this.properties.getProperty(name);
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 295 */     this.properties.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void removeProperty(String name) {
/* 307 */     this.properties.removeProperty(name);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 317 */     this.eventManager.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 330 */     this.eventManager.addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 341 */     this.eventManager.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 352 */     this.eventManager.removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public int getCurrentLevel() {
/* 360 */     return this.currentLevel;
/*     */   }
/*     */   
/*     */   public RenderedImage getCurrentImage() {
/* 365 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   public RenderedImage getImage(int level) {
/* 376 */     if (level < 0)
/* 377 */       return null; 
/* 380 */     if (level < this.currentLevel) {
/* 381 */       this.currentImage = this.highestImage;
/* 382 */       this.currentLevel = 0;
/*     */     } 
/* 385 */     while (this.currentLevel < level)
/* 386 */       getDownImage(); 
/* 388 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   public RenderedImage getDownImage() {
/* 397 */     this.currentLevel++;
/* 400 */     RenderedOp op = duplicate(this.downSampler, vectorize(this.currentImage));
/* 401 */     this.currentImage = op.getRendering();
/* 402 */     return this.currentImage;
/*     */   }
/*     */   
/*     */   protected RenderedOp duplicate(RenderedOp op, Vector images) {
/* 423 */     if (images == null)
/* 424 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 432 */     op = new RenderedOp(op.getRegistry(), op.getOperationName(), op.getParameterBlock(), op.getRenderingHints());
/* 437 */     ParameterBlock pb = new ParameterBlock();
/* 438 */     pb.setParameters(op.getParameters());
/* 440 */     Vector srcs = op.getSources();
/* 441 */     int numSrcs = srcs.size();
/* 443 */     if (numSrcs == 0) {
/* 444 */       pb.setSources(images);
/*     */     } else {
/* 447 */       pb.addSource(duplicate(srcs.elementAt(0), images));
/* 449 */       for (int i = 1; i < numSrcs; i++)
/* 450 */         pb.addSource(srcs.elementAt(i)); 
/*     */     } 
/* 454 */     op.setParameterBlock(pb);
/* 455 */     return op;
/*     */   }
/*     */   
/*     */   public RenderableImage getAsRenderable(int numImages, float minX, float minY, float height) {
/* 487 */     Vector v = new Vector();
/* 488 */     v.add(this.currentImage);
/* 490 */     RenderedImage image = this.currentImage;
/* 491 */     for (int i = 1; i < numImages; i++) {
/* 492 */       RenderedOp op = duplicate(this.downSampler, vectorize(image));
/* 493 */       image = op.getRendering();
/* 495 */       if (image.getWidth() <= 1 || image.getHeight() <= 1)
/*     */         break; 
/* 499 */       v.add(image);
/*     */     } 
/* 502 */     return new MultiResolutionRenderableImage(v, minX, minY, height);
/*     */   }
/*     */   
/*     */   public RenderableImage getAsRenderable() {
/* 514 */     return getAsRenderable(1, 0.0F, 0.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected final Vector vectorize(RenderedImage image) {
/* 527 */     Vector v = new Vector(1);
/* 528 */     v.add(image);
/* 529 */     return v;
/*     */   }
/*     */   
/*     */   protected final Vector vectorize(RenderedImage im1, RenderedImage im2) {
/* 541 */     Vector v = new Vector(2);
/* 542 */     v.add(im1);
/* 543 */     v.add(im2);
/* 544 */     return v;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImageMIPMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */