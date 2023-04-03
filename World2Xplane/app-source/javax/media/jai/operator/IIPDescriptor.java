/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.net.URL;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class IIPDescriptor extends OperationDescriptorImpl {
/* 223 */   private static final String[][] resources = new String[][] { 
/* 223 */       { "GlobalName", "IIP" }, { "LocalName", "IIP" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("IIPDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/IIPDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("IIPDescriptor1") }, { "arg1Desc", JaiI18N.getString("IIPDescriptor2") }, { "arg2Desc", JaiI18N.getString("IIPDescriptor3") }, { "arg3Desc", JaiI18N.getString("IIPDescriptor4") }, 
/* 223 */       { "arg4Desc", JaiI18N.getString("IIPDescriptor5") }, { "arg5Desc", JaiI18N.getString("IIPDescriptor6") }, { "arg6Desc", JaiI18N.getString("IIPDescriptor7") }, { "arg7Desc", JaiI18N.getString("IIPDescriptor8") }, { "arg8Desc", JaiI18N.getString("IIPDescriptor9") }, { "arg9Desc", JaiI18N.getString("IIPDescriptor10") }, { "arg10Desc", JaiI18N.getString("IIPDescriptor11") }, { "arg11Desc", JaiI18N.getString("IIPDescriptor12") }, { "arg12Desc", JaiI18N.getString("IIPDescriptor13") }, { "arg13Desc", JaiI18N.getString("IIPDescriptor14") } };
/*     */   
/* 247 */   private static final Class[] paramClasses = new Class[] { 
/* 247 */       String.class, (array$I == null) ? (array$I = class$("[I")) : array$I, Float.class, (array$F == null) ? (array$F = class$("[F")) : array$F, Float.class, Rectangle2D.Float.class, AffineTransform.class, Float.class, Rectangle2D.Float.class, Integer.class, 
/* 247 */       String.class, ICC_Profile.class, Integer.class, Integer.class };
/*     */   
/* 265 */   private static final String[] paramNames = new String[] { 
/* 265 */       "URL", "subImages", "filter", "colorTwist", "contrast", "sourceROI", "transform", "aspectRatio", "destROI", "rotation", 
/* 265 */       "mirrorAxis", "ICCProfile", "JPEGQuality", "JPEGTable" };
/*     */   
/* 287 */   private static final Object[] paramDefaults = new Object[] { 
/* 287 */       NO_PARAMETER_DEFAULT, { 0 }, new Float(0.0F), null, new Float(1.0F), null, new AffineTransform(), null, null, new Integer(0), 
/* 287 */       null, null, null, null };
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   static Class array$F;
/*     */   
/*     */   public IIPDescriptor() {
/* 306 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 314 */     return true;
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 327 */     if (index == 0 || index == 1 || index == 3 || index == 5 || index == 6 || index == 8 || index == 10 || index == 11)
/* 329 */       return null; 
/* 330 */     if (index == 2)
/* 331 */       return new Float(-3.4028235E38F); 
/* 332 */     if (index == 7)
/* 333 */       return new Float(0.0F); 
/* 334 */     if (index == 4)
/* 335 */       return new Float(1.0F); 
/* 336 */     if (index == 12 || index == 9)
/* 337 */       return new Integer(0); 
/* 338 */     if (index == 13)
/* 339 */       return new Integer(1); 
/* 341 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public Number getParamMaxValue(int index) {
/* 355 */     if (index == 0 || index == 1 || index == 3 || index == 5 || index == 6 || index == 8 || index == 10 || index == 11)
/* 357 */       return null; 
/* 358 */     if (index == 2 || index == 4 || index == 7)
/* 359 */       return new Float(Float.MAX_VALUE); 
/* 360 */     if (index == 9)
/* 361 */       return new Integer(270); 
/* 362 */     if (index == 12)
/* 363 */       return new Integer(100); 
/* 364 */     if (index == 13)
/* 365 */       return new Integer(255); 
/* 367 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 391 */     if (!super.validateParameters(args, msg))
/* 392 */       return false; 
/*     */     try {
/* 396 */       new URL((String)args.getObjectParameter(0));
/* 397 */     } catch (Exception e) {
/* 398 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor15"));
/* 400 */       return false;
/*     */     } 
/* 403 */     int[] subImages = (int[])args.getObjectParameter(1);
/* 404 */     if (subImages.length < 1)
/* 405 */       args.set(paramDefaults[1], 1); 
/* 408 */     float[] colorTwist = (float[])args.getObjectParameter(3);
/* 409 */     if (colorTwist != null) {
/* 410 */       if (colorTwist.length < 16) {
/* 411 */         msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor16"));
/* 413 */         return false;
/*     */       } 
/* 417 */       colorTwist[12] = 0.0F;
/* 418 */       colorTwist[13] = 0.0F;
/* 419 */       colorTwist[14] = 0.0F;
/* 420 */       args.set(colorTwist, 3);
/*     */     } 
/* 423 */     float contrast = args.getFloatParameter(4);
/* 424 */     if (contrast < 1.0F) {
/* 425 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor20"));
/* 427 */       return false;
/*     */     } 
/* 430 */     Rectangle2D.Float sourceROI = (Rectangle2D.Float)args.getObjectParameter(5);
/* 432 */     if (sourceROI != null && (sourceROI.getWidth() < 0.0D || sourceROI.getHeight() < 0.0D)) {
/* 434 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor17"));
/* 436 */       return false;
/*     */     } 
/* 439 */     AffineTransform tf = (AffineTransform)args.getObjectParameter(6);
/* 440 */     if (tf.getDeterminant() == 0.0D) {
/* 441 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor24"));
/* 443 */       return false;
/*     */     } 
/* 446 */     if (args.getObjectParameter(7) != null) {
/* 447 */       float aspectRatio = args.getFloatParameter(7);
/* 448 */       if (aspectRatio < 0.0F) {
/* 449 */         msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor21"));
/* 451 */         return false;
/*     */       } 
/*     */     } 
/* 455 */     Rectangle2D.Float destROI = (Rectangle2D.Float)args.getObjectParameter(8);
/* 457 */     if (destROI != null && (destROI.getWidth() < 0.0D || destROI.getHeight() < 0.0D)) {
/* 459 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor17"));
/* 461 */       return false;
/*     */     } 
/* 464 */     int rotation = args.getIntParameter(9);
/* 465 */     if (rotation != 0 && rotation != 90 && rotation != 180 && rotation != 270) {
/* 467 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor18"));
/* 469 */       return false;
/*     */     } 
/* 472 */     String mirrorAxis = (String)args.getObjectParameter(10);
/* 473 */     if (mirrorAxis != null && !mirrorAxis.equalsIgnoreCase("x") && !mirrorAxis.equalsIgnoreCase("y")) {
/* 476 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor19"));
/* 478 */       return false;
/*     */     } 
/* 481 */     if (args.getObjectParameter(12) != null) {
/* 482 */       int JPEGQuality = args.getIntParameter(12);
/* 483 */       if (JPEGQuality < 0 || JPEGQuality > 100) {
/* 484 */         msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor22"));
/* 486 */         return false;
/*     */       } 
/*     */     } 
/* 490 */     if (args.getObjectParameter(13) != null) {
/* 491 */       int JPEGIndex = args.getIntParameter(13);
/* 492 */       if (JPEGIndex < 1 || JPEGIndex > 255) {
/* 493 */         msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor23"));
/* 495 */         return false;
/*     */       } 
/*     */     } 
/* 499 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(String URL, int[] subImages, Float filter, float[] colorTwist, Float contrast, Rectangle2D.Float sourceROI, AffineTransform transform, Float aspectRatio, Rectangle2D.Float destROI, Integer rotation, String mirrorAxis, ICC_Profile ICCProfile, Integer JPEGQuality, Integer JPEGTable, RenderingHints hints) {
/* 561 */     ParameterBlockJAI pb = new ParameterBlockJAI("IIP", "rendered");
/* 565 */     pb.setParameter("URL", URL);
/* 566 */     pb.setParameter("subImages", subImages);
/* 567 */     pb.setParameter("filter", filter);
/* 568 */     pb.setParameter("colorTwist", colorTwist);
/* 569 */     pb.setParameter("contrast", contrast);
/* 570 */     pb.setParameter("sourceROI", sourceROI);
/* 571 */     pb.setParameter("transform", transform);
/* 572 */     pb.setParameter("aspectRatio", aspectRatio);
/* 573 */     pb.setParameter("destROI", destROI);
/* 574 */     pb.setParameter("rotation", rotation);
/* 575 */     pb.setParameter("mirrorAxis", mirrorAxis);
/* 576 */     pb.setParameter("ICCProfile", ICCProfile);
/* 577 */     pb.setParameter("JPEGQuality", JPEGQuality);
/* 578 */     pb.setParameter("JPEGTable", JPEGTable);
/* 580 */     return JAI.create("IIP", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(String URL, int[] subImages, Float filter, float[] colorTwist, Float contrast, Rectangle2D.Float sourceROI, AffineTransform transform, Float aspectRatio, Rectangle2D.Float destROI, Integer rotation, String mirrorAxis, ICC_Profile ICCProfile, Integer JPEGQuality, Integer JPEGTable, RenderingHints hints) {
/* 641 */     ParameterBlockJAI pb = new ParameterBlockJAI("IIP", "renderable");
/* 645 */     pb.setParameter("URL", URL);
/* 646 */     pb.setParameter("subImages", subImages);
/* 647 */     pb.setParameter("filter", filter);
/* 648 */     pb.setParameter("colorTwist", colorTwist);
/* 649 */     pb.setParameter("contrast", contrast);
/* 650 */     pb.setParameter("sourceROI", sourceROI);
/* 651 */     pb.setParameter("transform", transform);
/* 652 */     pb.setParameter("aspectRatio", aspectRatio);
/* 653 */     pb.setParameter("destROI", destROI);
/* 654 */     pb.setParameter("rotation", rotation);
/* 655 */     pb.setParameter("mirrorAxis", mirrorAxis);
/* 656 */     pb.setParameter("ICCProfile", ICCProfile);
/* 657 */     pb.setParameter("JPEGQuality", JPEGQuality);
/* 658 */     pb.setParameter("JPEGTable", JPEGTable);
/* 660 */     return JAI.createRenderable("IIP", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\IIPDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */