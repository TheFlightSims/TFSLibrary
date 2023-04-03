/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class BorderDescriptor extends OperationDescriptorImpl {
/*  90 */   private static final String[][] resources = new String[][] { 
/*  90 */       { "GlobalName", "Border" }, { "LocalName", "Border" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BorderDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BorderDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("BorderDescriptor1") }, { "arg1Desc", JaiI18N.getString("BorderDescriptor2") }, { "arg2Desc", JaiI18N.getString("BorderDescriptor3") }, { "arg3Desc", JaiI18N.getString("BorderDescriptor4") }, 
/*  90 */       { "arg4Desc", JaiI18N.getString("BorderDescriptor5") } };
/*     */   
/* 105 */   private static final String[] paramNames = new String[] { "leftPad", "rightPad", "topPad", "bottomPad", "type" };
/*     */   
/* 110 */   private static final Class[] paramClasses = new Class[] { Integer.class, Integer.class, Integer.class, Integer.class, BorderExtender.class };
/*     */   
/* 117 */   private static final Object[] paramDefaults = new Object[] { new Integer(0), new Integer(0), new Integer(0), new Integer(0), BorderExtender.createInstance(0) };
/*     */   
/*     */   public BorderDescriptor() {
/* 124 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Object getInvalidRegion(String modeName, ParameterBlock oldParamBlock, RenderingHints oldHints, ParameterBlock newParamBlock, RenderingHints newHints, OperationNode node) {
/* 163 */     if (modeName == null || ((getNumSources() > 0 || getNumParameters() > 0) && (oldParamBlock == null || newParamBlock == null)))
/* 167 */       throw new IllegalArgumentException(JaiI18N.getString("BorderDescriptor6")); 
/* 170 */     int numSources = getNumSources();
/* 172 */     if (numSources > 0 && (oldParamBlock.getNumSources() != numSources || newParamBlock.getNumSources() != numSources))
/* 176 */       throw new IllegalArgumentException(JaiI18N.getString("BorderDescriptor7")); 
/* 180 */     int numParams = getParameterListDescriptor(modeName).getNumParameters();
/* 182 */     if (numParams > 0 && (oldParamBlock.getNumParameters() != numParams || newParamBlock.getNumParameters() != numParams))
/* 186 */       throw new IllegalArgumentException(JaiI18N.getString("BorderDescriptor8")); 
/* 191 */     if (!modeName.equalsIgnoreCase("rendered") || (oldHints == null && newHints != null) || (oldHints != null && newHints == null) || (oldHints != null && !oldHints.equals(newHints)) || !oldParamBlock.getSource(0).equals(newParamBlock.getSource(0)) || oldParamBlock.getIntParameter(0) != newParamBlock.getIntParameter(0) || oldParamBlock.getIntParameter(2) != newParamBlock.getIntParameter(2))
/* 200 */       return null; 
/* 203 */     Shape invalidRegion = null;
/* 205 */     if (!oldParamBlock.getObjectParameter(4).equals(newParamBlock.getObjectParameter(4))) {
/* 210 */       RenderedImage src = oldParamBlock.getRenderedSource(0);
/* 211 */       int leftPad = oldParamBlock.getIntParameter(0);
/* 212 */       int topPad = oldParamBlock.getIntParameter(2);
/* 215 */       Rectangle srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 220 */       Rectangle dstBounds = new Rectangle(srcBounds.x - leftPad, srcBounds.y - topPad, srcBounds.width + leftPad + oldParamBlock.getIntParameter(1), srcBounds.height + topPad + oldParamBlock.getIntParameter(3));
/* 229 */       Area invalidArea = new Area(dstBounds);
/* 230 */       invalidArea.subtract(new Area(srcBounds));
/* 231 */       invalidRegion = invalidArea;
/* 233 */     } else if ((newParamBlock.getIntParameter(1) < oldParamBlock.getIntParameter(1) && newParamBlock.getIntParameter(3) <= oldParamBlock.getIntParameter(3)) || (newParamBlock.getIntParameter(3) < oldParamBlock.getIntParameter(3) && newParamBlock.getIntParameter(1) <= oldParamBlock.getIntParameter(1))) {
/* 244 */       RenderedImage src = oldParamBlock.getRenderedSource(0);
/* 245 */       int leftPad = oldParamBlock.getIntParameter(0);
/* 246 */       int topPad = oldParamBlock.getIntParameter(2);
/* 249 */       Rectangle srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 254 */       Rectangle oldBounds = new Rectangle(srcBounds.x - leftPad, srcBounds.y - topPad, srcBounds.width + leftPad + oldParamBlock.getIntParameter(1), srcBounds.height + topPad + oldParamBlock.getIntParameter(3));
/* 263 */       Rectangle newBounds = new Rectangle(srcBounds.x - leftPad, srcBounds.y - topPad, srcBounds.width + leftPad + newParamBlock.getIntParameter(1), srcBounds.height + topPad + newParamBlock.getIntParameter(3));
/* 272 */       Area invalidArea = new Area(oldBounds);
/* 273 */       invalidArea.subtract(new Area(newBounds));
/* 274 */       invalidRegion = invalidArea;
/*     */     } else {
/* 279 */       invalidRegion = new Rectangle();
/*     */     } 
/* 282 */     return invalidRegion;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer leftPad, Integer rightPad, Integer topPad, Integer bottomPad, BorderExtender type, RenderingHints hints) {
/* 320 */     ParameterBlockJAI pb = new ParameterBlockJAI("Border", "rendered");
/* 324 */     pb.setSource("source0", source0);
/* 326 */     pb.setParameter("leftPad", leftPad);
/* 327 */     pb.setParameter("rightPad", rightPad);
/* 328 */     pb.setParameter("topPad", topPad);
/* 329 */     pb.setParameter("bottomPad", bottomPad);
/* 330 */     pb.setParameter("type", type);
/* 332 */     return JAI.create("Border", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BorderDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */