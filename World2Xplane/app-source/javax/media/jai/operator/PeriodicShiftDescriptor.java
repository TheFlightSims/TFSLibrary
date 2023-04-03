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
/*     */ public class PeriodicShiftDescriptor extends OperationDescriptorImpl {
/*  87 */   private static final String[][] resources = new String[][] { { "GlobalName", "PeriodicShift" }, { "LocalName", "PeriodicShift" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PeriodicShiftDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PeriodicShiftDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("PeriodicShiftDescriptor1") }, { "arg1Desc", JaiI18N.getString("PeriodicShiftDescriptor2") } };
/*     */   
/*  99 */   private static final Class[] paramClasses = new Class[] { Integer.class, Integer.class };
/*     */   
/* 105 */   private static final String[] paramNames = new String[] { "shiftX", "shiftY" };
/*     */   
/* 110 */   private static final Object[] paramDefaults = new Object[] { null, null };
/*     */   
/* 114 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public PeriodicShiftDescriptor() {
/* 121 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 136 */     if (!super.validateArguments(modeName, args, msg))
/* 137 */       return false; 
/* 140 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 141 */       return true; 
/* 143 */     RenderedImage src = args.getRenderedSource(0);
/* 146 */     if (args.getObjectParameter(0) == null)
/* 147 */       args.set(new Integer(src.getWidth() / 2), 0); 
/* 149 */     if (args.getObjectParameter(1) == null)
/* 150 */       args.set(new Integer(src.getHeight() / 2), 1); 
/* 153 */     int shiftX = args.getIntParameter(0);
/* 154 */     int shiftY = args.getIntParameter(1);
/* 155 */     if (shiftX < 0 || shiftX >= src.getWidth() || shiftY < 0 || shiftY >= src.getHeight()) {
/* 157 */       msg.append(getName() + " " + JaiI18N.getString("PeriodicShiftDescriptor3"));
/* 159 */       return false;
/*     */     } 
/* 162 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer shiftX, Integer shiftY, RenderingHints hints) {
/* 191 */     ParameterBlockJAI pb = new ParameterBlockJAI("PeriodicShift", "rendered");
/* 195 */     pb.setSource("source0", source0);
/* 197 */     pb.setParameter("shiftX", shiftX);
/* 198 */     pb.setParameter("shiftY", shiftY);
/* 200 */     return JAI.create("PeriodicShift", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Integer shiftX, Integer shiftY, RenderingHints hints) {
/* 228 */     ParameterBlockJAI pb = new ParameterBlockJAI("PeriodicShift", "renderable");
/* 232 */     pb.setSource("source0", source0);
/* 234 */     pb.setParameter("shiftX", shiftX);
/* 235 */     pb.setParameter("shiftY", shiftY);
/* 237 */     return JAI.createRenderable("PeriodicShift", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PeriodicShiftDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */