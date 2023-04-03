/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ 
/*     */ public class NullCRIF extends CRIFImpl {
/*  68 */   private static RenderedImage sourcelessImage = null;
/*     */   
/*     */   public static final synchronized void setSourcelessImage(RenderedImage im) {
/*  86 */     sourcelessImage = im;
/*     */   }
/*     */   
/*     */   public static final synchronized RenderedImage getSourcelessImage() {
/*  96 */     return sourcelessImage;
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 109 */     return (args.getNumSources() == 0) ? getSourcelessImage() : args.getRenderedSource(0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\NullCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */