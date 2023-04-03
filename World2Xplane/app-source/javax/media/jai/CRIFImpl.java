/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public abstract class CRIFImpl implements ContextualRenderedImageFactory {
/*  53 */   protected String operationName = null;
/*     */   
/*     */   public CRIFImpl() {
/*  59 */     this.operationName = null;
/*     */   }
/*     */   
/*     */   public CRIFImpl(String operationName) {
/*  67 */     this.operationName = operationName;
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*  96 */     RenderingHints renderHints = renderContext.getRenderingHints();
/*  97 */     if (this.operationName != null) {
/*     */       RenderedImage rendering;
/*  98 */       OperationRegistry registry = (renderHints == null) ? null : (OperationRegistry)renderHints.get(JAI.KEY_OPERATION_REGISTRY);
/* 104 */       if (registry == null) {
/* 106 */         rendering = JAI.create(this.operationName, paramBlock, renderHints);
/*     */       } else {
/* 114 */         OperationDescriptor odesc = (OperationDescriptor)registry.getDescriptor(OperationDescriptor.class, this.operationName);
/* 117 */         if (odesc == null)
/* 118 */           throw new IllegalArgumentException(this.operationName + ": " + JaiI18N.getString("JAI0")); 
/* 123 */         if (!odesc.isModeSupported("rendered"))
/* 124 */           throw new IllegalArgumentException(this.operationName + ": " + JaiI18N.getString("JAI1")); 
/* 129 */         if (!RenderedImage.class.isAssignableFrom(odesc.getDestClass("rendered")))
/* 130 */           throw new IllegalArgumentException(this.operationName + ": " + JaiI18N.getString("JAI2")); 
/* 137 */         StringBuffer msg = new StringBuffer();
/* 138 */         paramBlock = (ParameterBlock)paramBlock.clone();
/* 139 */         if (!odesc.validateArguments("rendered", paramBlock, msg))
/* 141 */           throw new IllegalArgumentException(msg.toString()); 
/* 145 */         rendering = new RenderedOp(registry, this.operationName, paramBlock, renderHints);
/*     */       } 
/* 150 */       if (rendering != null) {
/* 153 */         if (rendering instanceof RenderedOp)
/*     */           try {
/* 155 */             rendering = ((RenderedOp)rendering).getRendering();
/* 156 */           } catch (Exception e) {
/* 157 */             ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/* 159 */             String message = JaiI18N.getString("CRIFImpl0") + this.operationName;
/* 161 */             listener.errorOccurred(message, e, this, false);
/*     */           }  
/* 165 */         return rendering;
/*     */       } 
/*     */     } 
/* 170 */     return create(paramBlock, renderHints);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 193 */     return renderContext;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 206 */     int numSources = paramBlock.getNumSources();
/* 208 */     if (numSources == 0)
/* 209 */       return null; 
/* 212 */     RenderableImage src = paramBlock.getRenderableSource(0);
/* 213 */     Rectangle2D.Float box1 = new Rectangle2D.Float(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 218 */     for (int i = 1; i < numSources; i++) {
/* 219 */       src = paramBlock.getRenderableSource(i);
/* 220 */       Rectangle2D.Float box2 = new Rectangle2D.Float(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 223 */       box1 = (Rectangle2D.Float)box1.createIntersection(box2);
/* 224 */       if (box1.isEmpty())
/*     */         break; 
/*     */     } 
/* 229 */     return box1;
/*     */   }
/*     */   
/*     */   public Object getProperty(ParameterBlock paramBlock, String name) {
/* 249 */     return Image.UndefinedProperty;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 261 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDynamic() {
/* 274 */     return false;
/*     */   }
/*     */   
/*     */   public abstract RenderedImage create(ParameterBlock paramParameterBlock, RenderingHints paramRenderingHints);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CRIFImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */