/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class PiecewiseDescriptor extends OperationDescriptorImpl {
/*  87 */   private static final String[][] resources = new String[][] { { "GlobalName", "Piecewise" }, { "LocalName", "Piecewise" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PiecewiseDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PiecewiseDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", "The breakpoint array." } };
/*     */   
/*  98 */   private static final Class[] paramClasses = new Class[] { (array$$$F == null) ? (array$$$F = class$("[[[F")) : array$$$F };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  99 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  99 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 103 */   private static final String[] paramNames = new String[] { "breakPoints" };
/*     */   
/* 108 */   private static final Object[] paramDefaults = new Object[] { { { { 0.0F, 255.0F }, { 0.0F, 255.0F } } } };
/*     */   
/* 112 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$$$F;
/*     */   
/*     */   public PiecewiseDescriptor() {
/* 119 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 136 */     if (!super.validateArguments(modeName, args, msg))
/* 137 */       return false; 
/* 140 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 141 */       return true; 
/* 144 */     RenderedImage src = args.getRenderedSource(0);
/* 146 */     float[][][] breakPoints = (float[][][])args.getObjectParameter(0);
/* 154 */     if (breakPoints.length != 1 && breakPoints.length != src.getSampleModel().getNumBands()) {
/* 157 */       msg.append(getName() + " " + JaiI18N.getString("PiecewiseDescriptor1"));
/* 159 */       return false;
/*     */     } 
/* 161 */     int numBands = breakPoints.length;
/*     */     int b;
/* 162 */     for (b = 0; b < numBands; b++) {
/* 163 */       if ((breakPoints[b]).length != 2) {
/* 165 */         msg.append(getName() + " " + JaiI18N.getString("PiecewiseDescriptor2"));
/* 167 */         return false;
/*     */       } 
/* 168 */       if ((breakPoints[b][0]).length != (breakPoints[b][1]).length) {
/* 171 */         msg.append(getName() + " " + JaiI18N.getString("PiecewiseDescriptor3"));
/* 173 */         return false;
/*     */       } 
/*     */     } 
/* 176 */     for (b = 0; b < numBands; b++) {
/* 177 */       int count = (breakPoints[b][0]).length - 1;
/* 178 */       float[] x = breakPoints[b][0];
/* 179 */       for (int i = 0; i < count; i++) {
/* 180 */         if (x[i] >= x[i + 1]) {
/* 182 */           msg.append(getName() + " " + JaiI18N.getString("PiecewiseDescriptor4"));
/* 184 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, float[][][] breakPoints, RenderingHints hints) {
/* 216 */     ParameterBlockJAI pb = new ParameterBlockJAI("Piecewise", "rendered");
/* 220 */     pb.setSource("source0", source0);
/* 222 */     pb.setParameter("breakPoints", breakPoints);
/* 224 */     return JAI.create("Piecewise", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, float[][][] breakPoints, RenderingHints hints) {
/* 249 */     ParameterBlockJAI pb = new ParameterBlockJAI("Piecewise", "renderable");
/* 253 */     pb.setSource("source0", source0);
/* 255 */     pb.setParameter("breakPoints", breakPoints);
/* 257 */     return JAI.createRenderable("Piecewise", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PiecewiseDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */