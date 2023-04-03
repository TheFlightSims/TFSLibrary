/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import org.jfree.chart.encoders.EncoderUtil;
/*     */ import org.jfree.chart.imagemap.ImageMapUtilities;
/*     */ import org.jfree.chart.imagemap.OverLIBToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.URLTagFragmentGenerator;
/*     */ 
/*     */ public abstract class ChartUtilities {
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height) throws IOException {
/* 120 */     writeChartAsPNG(out, chart, width, height, null);
/*     */   }
/*     */   
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, boolean encodeAlpha, int compression) throws IOException {
/* 144 */     writeChartAsPNG(out, chart, width, height, null, encodeAlpha, compression);
/*     */   }
/*     */   
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 171 */     if (chart == null)
/* 172 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 174 */     BufferedImage bufferedImage = chart.createBufferedImage(width, height, info);
/* 176 */     EncoderUtil.writeBufferedImage(bufferedImage, "png", out);
/*     */   }
/*     */   
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info, boolean encodeAlpha, int compression) throws IOException {
/* 203 */     if (out == null)
/* 204 */       throw new IllegalArgumentException("Null 'out' argument."); 
/* 206 */     if (chart == null)
/* 207 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 209 */     BufferedImage chartImage = chart.createBufferedImage(width, height, 2, info);
/* 212 */     writeBufferedImageAsPNG(out, chartImage, encodeAlpha, compression);
/*     */   }
/*     */   
/*     */   public static void writeScaledChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, int widthScaleFactor, int heightScaleFactor) throws IOException {
/* 238 */     if (out == null)
/* 239 */       throw new IllegalArgumentException("Null 'out' argument."); 
/* 241 */     if (chart == null)
/* 242 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 245 */     double desiredWidth = (width * widthScaleFactor);
/* 246 */     double desiredHeight = (height * heightScaleFactor);
/* 247 */     double defaultWidth = width;
/* 248 */     double defaultHeight = height;
/* 249 */     boolean scale = false;
/* 252 */     if (widthScaleFactor != 1 || heightScaleFactor != 1)
/* 253 */       scale = true; 
/* 256 */     double scaleX = desiredWidth / defaultWidth;
/* 257 */     double scaleY = desiredHeight / defaultHeight;
/* 259 */     BufferedImage image = new BufferedImage((int)desiredWidth, (int)desiredHeight, 2);
/* 262 */     Graphics2D g2 = image.createGraphics();
/* 264 */     if (scale) {
/* 265 */       AffineTransform saved = g2.getTransform();
/* 266 */       g2.transform(AffineTransform.getScaleInstance(scaleX, scaleY));
/* 267 */       chart.draw(g2, new Rectangle2D.Double(0.0D, 0.0D, defaultWidth, defaultHeight), null, null);
/* 271 */       g2.setTransform(saved);
/* 272 */       g2.dispose();
/*     */     } else {
/* 275 */       chart.draw(g2, new Rectangle2D.Double(0.0D, 0.0D, defaultWidth, defaultHeight), null, null);
/*     */     } 
/* 280 */     out.write(encodeAsPNG(image));
/*     */   }
/*     */   
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height) throws IOException {
/* 300 */     saveChartAsPNG(file, chart, width, height, null);
/*     */   }
/*     */   
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 325 */     if (file == null)
/* 326 */       throw new IllegalArgumentException("Null 'file' argument."); 
/* 328 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/* 329 */     writeChartAsPNG(out, chart, width, height, info);
/* 330 */     out.close();
/*     */   }
/*     */   
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info, boolean encodeAlpha, int compression) throws IOException {
/* 357 */     if (file == null)
/* 358 */       throw new IllegalArgumentException("Null 'file' argument."); 
/* 360 */     if (chart == null)
/* 361 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 364 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/* 365 */     writeChartAsPNG(out, chart, width, height, info, encodeAlpha, compression);
/* 368 */     out.close();
/*     */   }
/*     */   
/*     */   public static void writeChartAsJPEG(OutputStream out, JFreeChart chart, int width, int height) throws IOException {
/* 389 */     writeChartAsJPEG(out, chart, width, height, (ChartRenderingInfo)null);
/*     */   }
/*     */   
/*     */   public static void writeChartAsJPEG(OutputStream out, float quality, JFreeChart chart, int width, int height) throws IOException {
/* 412 */     writeChartAsJPEG(out, quality, chart, width, height, null);
/*     */   }
/*     */   
/*     */   public static void writeChartAsJPEG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 439 */     if (chart == null)
/* 440 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 442 */     BufferedImage image = chart.createBufferedImage(width, height, info);
/* 443 */     EncoderUtil.writeBufferedImage(image, "jpeg", out);
/*     */   }
/*     */   
/*     */   public static void writeChartAsJPEG(OutputStream out, float quality, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 470 */     if (chart == null)
/* 471 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 473 */     BufferedImage image = chart.createBufferedImage(width, height, info);
/* 474 */     EncoderUtil.writeBufferedImage(image, "jpeg", out, quality);
/*     */   }
/*     */   
/*     */   public static void saveChartAsJPEG(File file, JFreeChart chart, int width, int height) throws IOException {
/* 494 */     saveChartAsJPEG(file, chart, width, height, (ChartRenderingInfo)null);
/*     */   }
/*     */   
/*     */   public static void saveChartAsJPEG(File file, float quality, JFreeChart chart, int width, int height) throws IOException {
/* 516 */     saveChartAsJPEG(file, quality, chart, width, height, null);
/*     */   }
/*     */   
/*     */   public static void saveChartAsJPEG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 541 */     if (file == null)
/* 542 */       throw new IllegalArgumentException("Null 'file' argument."); 
/* 544 */     if (chart == null)
/* 545 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 547 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/* 548 */     writeChartAsJPEG(out, chart, width, height, info);
/* 549 */     out.close();
/*     */   }
/*     */   
/*     */   public static void saveChartAsJPEG(File file, float quality, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws IOException {
/* 576 */     if (file == null)
/* 577 */       throw new IllegalArgumentException("Null 'file' argument."); 
/* 579 */     if (chart == null)
/* 580 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 582 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/* 583 */     writeChartAsJPEG(out, quality, chart, width, height, info);
/* 584 */     out.close();
/*     */   }
/*     */   
/*     */   public static void writeBufferedImageAsJPEG(OutputStream out, BufferedImage image) throws IOException {
/* 601 */     writeBufferedImageAsJPEG(out, 0.75F, image);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImageAsJPEG(OutputStream out, float quality, BufferedImage image) throws IOException {
/* 618 */     EncoderUtil.writeBufferedImage(image, "jpeg", out, quality);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImageAsPNG(OutputStream out, BufferedImage image) throws IOException {
/* 634 */     EncoderUtil.writeBufferedImage(image, "png", out);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImageAsPNG(OutputStream out, BufferedImage image, boolean encodeAlpha, int compression) throws IOException {
/* 654 */     EncoderUtil.writeBufferedImage(image, "png", out, compression, encodeAlpha);
/*     */   }
/*     */   
/*     */   public static byte[] encodeAsPNG(BufferedImage image) throws IOException {
/* 669 */     return EncoderUtil.encode(image, "png");
/*     */   }
/*     */   
/*     */   public static byte[] encodeAsPNG(BufferedImage image, boolean encodeAlpha, int compression) throws IOException {
/* 686 */     return EncoderUtil.encode(image, "png", compression, encodeAlpha);
/*     */   }
/*     */   
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, boolean useOverLibForToolTips) throws IOException {
/*     */     StandardToolTipTagFragmentGenerator standardToolTipTagFragmentGenerator;
/* 708 */     ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
/* 709 */     if (useOverLibForToolTips) {
/* 710 */       OverLIBToolTipTagFragmentGenerator overLIBToolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
/*     */     } else {
/* 714 */       standardToolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
/*     */     } 
/* 717 */     ImageMapUtilities.writeImageMap(writer, name, info, (ToolTipTagFragmentGenerator)standardToolTipTagFragmentGenerator, (URLTagFragmentGenerator)new StandardURLTagFragmentGenerator());
/*     */   }
/*     */   
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator) throws IOException {
/* 741 */     writer.println(ImageMapUtilities.getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator));
/*     */   }
/*     */   
/*     */   public static String getImageMap(String name, ChartRenderingInfo info) {
/* 757 */     return ImageMapUtilities.getImageMap(name, info, (ToolTipTagFragmentGenerator)new StandardToolTipTagFragmentGenerator(), (URLTagFragmentGenerator)new StandardURLTagFragmentGenerator());
/*     */   }
/*     */   
/*     */   public static String getImageMap(String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator) {
/* 780 */     return ImageMapUtilities.getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */