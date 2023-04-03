/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.BreakIterator;
/*     */ import org.jfree.base.BaseBoot;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ 
/*     */ public class TextUtilities {
/*  84 */   protected static final LogContext logger = Log.createContext(TextUtilities.class);
/*     */   
/*     */   private static boolean useDrawRotatedStringWorkaround;
/*     */   
/*     */   private static boolean useFontMetricsGetStringBounds;
/*     */   
/*     */   static {
/*     */     boolean isJava14;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 102 */       Class.forName("java.nio.Buffer");
/* 103 */       isJava14 = true;
/* 105 */     } catch (ClassNotFoundException e) {
/* 106 */       isJava14 = false;
/*     */     } 
/* 109 */     String configRotatedStringWorkaround = BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.text.UseDrawRotatedStringWorkaround", "auto");
/* 112 */     if (configRotatedStringWorkaround.equals("auto")) {
/* 114 */       useDrawRotatedStringWorkaround = !isJava14;
/*     */     } else {
/* 118 */       useDrawRotatedStringWorkaround = configRotatedStringWorkaround.equals("true");
/*     */     } 
/* 122 */     String configFontMetricsStringBounds = BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.text.UseFontMetricsGetStringBounds", "auto");
/* 125 */     if (configFontMetricsStringBounds.equals("auto")) {
/* 127 */       useFontMetricsGetStringBounds = !isJava14;
/*     */     } else {
/* 131 */       useFontMetricsGetStringBounds = configFontMetricsStringBounds.equals("true");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint) {
/* 155 */     if (text == null)
/* 156 */       throw new IllegalArgumentException("Null 'text' argument."); 
/* 158 */     TextBlock result = new TextBlock();
/* 159 */     String input = text;
/* 160 */     boolean moreInputToProcess = (text.length() > 0);
/* 161 */     int start = 0;
/* 162 */     while (moreInputToProcess) {
/* 163 */       int index = input.indexOf("\n");
/* 164 */       if (index > 0) {
/* 165 */         String line = input.substring(0, index);
/* 166 */         if (index < input.length() - 1) {
/* 167 */           result.addLine(line, font, paint);
/* 168 */           input = input.substring(index + 1);
/*     */           continue;
/*     */         } 
/* 171 */         moreInputToProcess = false;
/*     */         continue;
/*     */       } 
/* 174 */       if (index == 0) {
/* 175 */         if (index < input.length() - 1) {
/* 176 */           input = input.substring(index + 1);
/*     */           continue;
/*     */         } 
/* 179 */         moreInputToProcess = false;
/*     */         continue;
/*     */       } 
/* 183 */       result.addLine(input, font, paint);
/* 184 */       moreInputToProcess = false;
/*     */     } 
/* 187 */     return result;
/*     */   }
/*     */   
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint, float maxWidth, TextMeasurer measurer) {
/* 208 */     return createTextBlock(text, font, paint, maxWidth, 2147483647, measurer);
/*     */   }
/*     */   
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint, float maxWidth, int maxLines, TextMeasurer measurer) {
/* 233 */     TextBlock result = new TextBlock();
/* 234 */     BreakIterator iterator = BreakIterator.getLineInstance();
/* 235 */     iterator.setText(text);
/* 236 */     int current = 0;
/* 237 */     int lines = 0;
/* 238 */     int length = text.length();
/* 239 */     while (current < length && lines < maxLines) {
/* 240 */       int next = nextLineBreak(text, current, maxWidth, iterator, measurer);
/* 243 */       if (next == -1) {
/* 244 */         result.addLine(text.substring(current), font, paint);
/* 245 */         return result;
/*     */       } 
/* 247 */       result.addLine(text.substring(current, next), font, paint);
/* 248 */       lines++;
/* 249 */       current = next;
/* 250 */       while (text.charAt(current) == '\n' && current < text.length())
/* 251 */         current++; 
/*     */     } 
/* 254 */     if (current < length) {
/* 255 */       TextLine lastLine = result.getLastLine();
/* 256 */       TextFragment lastFragment = lastLine.getLastTextFragment();
/* 257 */       String oldStr = lastFragment.getText();
/* 258 */       String newStr = "...";
/* 259 */       if (oldStr.length() > 3)
/* 260 */         newStr = oldStr.substring(0, oldStr.length() - 3) + "..."; 
/* 263 */       lastLine.removeFragment(lastFragment);
/* 264 */       TextFragment newFragment = new TextFragment(newStr, lastFragment.getFont(), lastFragment.getPaint());
/* 267 */       lastLine.addFragment(newFragment);
/*     */     } 
/* 269 */     return result;
/*     */   }
/*     */   
/*     */   private static int nextLineBreak(String text, int start, float width, BreakIterator iterator, TextMeasurer measurer) {
/* 289 */     int current = start;
/* 291 */     float x = 0.0F;
/* 292 */     boolean firstWord = true;
/* 293 */     int newline = text.indexOf('\n', start);
/* 294 */     if (newline < 0)
/* 295 */       newline = Integer.MAX_VALUE; 
/*     */     int end;
/* 297 */     while ((end = iterator.next()) != -1) {
/* 298 */       if (end > newline)
/* 299 */         return newline; 
/* 301 */       x += measurer.getStringWidth(text, current, end);
/* 302 */       if (x > width) {
/* 303 */         if (firstWord) {
/* 304 */           while (measurer.getStringWidth(text, start, end) > width) {
/* 305 */             end--;
/* 306 */             if (end <= start)
/* 307 */               return end; 
/*     */           } 
/* 310 */           return end;
/*     */         } 
/* 313 */         end = iterator.previous();
/* 314 */         return end;
/*     */       } 
/* 318 */       firstWord = false;
/* 319 */       current = end;
/*     */     } 
/* 321 */     return -1;
/*     */   }
/*     */   
/*     */   public static Rectangle2D getTextBounds(String text, Graphics2D g2, FontMetrics fm) {
/*     */     Rectangle2D bounds;
/* 338 */     if (useFontMetricsGetStringBounds) {
/* 339 */       bounds = fm.getStringBounds(text, g2);
/* 343 */       LineMetrics lm = fm.getFont().getLineMetrics(text, g2.getFontRenderContext());
/* 345 */       bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth(), lm.getHeight());
/*     */     } else {
/* 349 */       double width = fm.stringWidth(text);
/* 350 */       double height = fm.getHeight();
/* 351 */       if (logger.isDebugEnabled())
/* 352 */         logger.debug("Height = " + height); 
/* 354 */       bounds = new Rectangle2D.Double(0.0D, -fm.getAscent(), width, height);
/*     */     } 
/* 358 */     return bounds;
/*     */   }
/*     */   
/*     */   public static Rectangle2D drawAlignedString(String text, Graphics2D g2, float x, float y, TextAnchor anchor) {
/* 379 */     Rectangle2D textBounds = new Rectangle2D.Double();
/* 380 */     float[] adjust = deriveTextBoundsAnchorOffsets(g2, text, anchor, textBounds);
/* 384 */     textBounds.setRect((x + adjust[0]), (y + adjust[1] + adjust[2]), textBounds.getWidth(), textBounds.getHeight());
/* 388 */     g2.drawString(text, x + adjust[0], y + adjust[1]);
/* 389 */     return textBounds;
/*     */   }
/*     */   
/*     */   private static float[] deriveTextBoundsAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor, Rectangle2D textBounds) {
/* 413 */     float[] result = new float[3];
/* 414 */     FontRenderContext frc = g2.getFontRenderContext();
/* 415 */     Font f = g2.getFont();
/* 416 */     FontMetrics fm = g2.getFontMetrics(f);
/* 417 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 418 */     LineMetrics metrics = f.getLineMetrics(text, frc);
/* 419 */     float ascent = metrics.getAscent();
/* 420 */     result[2] = -ascent;
/* 421 */     float halfAscent = ascent / 2.0F;
/* 422 */     float descent = metrics.getDescent();
/* 423 */     float leading = metrics.getLeading();
/* 424 */     float xAdj = 0.0F;
/* 425 */     float yAdj = 0.0F;
/* 427 */     if (anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.CENTER || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.HALF_ASCENT_CENTER) {
/* 433 */       xAdj = (float)-bounds.getWidth() / 2.0F;
/* 436 */     } else if (anchor == TextAnchor.TOP_RIGHT || anchor == TextAnchor.CENTER_RIGHT || anchor == TextAnchor.BOTTOM_RIGHT || anchor == TextAnchor.BASELINE_RIGHT || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 442 */       xAdj = (float)-bounds.getWidth();
/*     */     } 
/* 446 */     if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.TOP_RIGHT) {
/* 450 */       yAdj = -descent - leading + (float)bounds.getHeight();
/* 453 */     } else if (anchor == TextAnchor.HALF_ASCENT_LEFT || anchor == TextAnchor.HALF_ASCENT_CENTER || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 457 */       yAdj = halfAscent;
/* 460 */     } else if (anchor == TextAnchor.CENTER_LEFT || anchor == TextAnchor.CENTER || anchor == TextAnchor.CENTER_RIGHT) {
/* 464 */       yAdj = -descent - leading + (float)(bounds.getHeight() / 2.0D);
/* 467 */     } else if (anchor == TextAnchor.BASELINE_LEFT || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.BASELINE_RIGHT) {
/* 471 */       yAdj = 0.0F;
/* 474 */     } else if (anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BOTTOM_RIGHT) {
/* 478 */       yAdj = -metrics.getDescent() - metrics.getLeading();
/*     */     } 
/* 481 */     if (textBounds != null)
/* 482 */       textBounds.setRect(bounds); 
/* 484 */     result[0] = xAdj;
/* 485 */     result[1] = yAdj;
/* 486 */     return result;
/*     */   }
/*     */   
/*     */   public static void setUseDrawRotatedStringWorkaround(boolean use) {
/* 500 */     useDrawRotatedStringWorkaround = use;
/*     */   }
/*     */   
/*     */   public static void drawRotatedString(String text, Graphics2D g2, double angle, float x, float y) {
/* 520 */     drawRotatedString(text, g2, x, y, angle, x, y);
/*     */   }
/*     */   
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float textX, float textY, double angle, float rotateX, float rotateY) {
/* 545 */     if (text == null || text.equals(""))
/*     */       return; 
/* 549 */     AffineTransform saved = g2.getTransform();
/* 552 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, rotateX, rotateY);
/* 555 */     g2.transform(rotate);
/* 557 */     if (useDrawRotatedStringWorkaround) {
/* 559 */       TextLayout tl = new TextLayout(text, g2.getFont(), g2.getFontRenderContext());
/* 562 */       tl.draw(g2, textX, textY);
/*     */     } else {
/* 566 */       g2.drawString(text, textX, textY);
/*     */     } 
/* 568 */     g2.setTransform(saved);
/*     */   }
/*     */   
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, float rotationX, float rotationY) {
/* 594 */     if (text == null || text.equals(""))
/*     */       return; 
/* 597 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/* 600 */     drawRotatedString(text, g2, x + textAdj[0], y + textAdj[1], angle, rotationX, rotationY);
/*     */   }
/*     */   
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, TextAnchor rotationAnchor) {
/* 627 */     if (text == null || text.equals(""))
/*     */       return; 
/* 630 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/* 633 */     float[] rotateAdj = deriveRotationAnchorOffsets(g2, text, rotationAnchor);
/* 636 */     drawRotatedString(text, g2, x + textAdj[0], y + textAdj[1], angle, x + textAdj[0] + rotateAdj[0], y + textAdj[1] + rotateAdj[1]);
/*     */   }
/*     */   
/*     */   public static Shape calculateRotatedStringBounds(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, TextAnchor rotationAnchor) {
/* 662 */     if (text == null || text.equals(""))
/* 663 */       return null; 
/* 665 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/* 668 */     if (logger.isDebugEnabled())
/* 669 */       logger.debug("TextBoundsAnchorOffsets = " + textAdj[0] + ", " + textAdj[1]); 
/* 673 */     float[] rotateAdj = deriveRotationAnchorOffsets(g2, text, rotationAnchor);
/* 676 */     if (logger.isDebugEnabled())
/* 677 */       logger.debug("RotationAnchorOffsets = " + rotateAdj[0] + ", " + rotateAdj[1]); 
/* 681 */     Shape result = calculateRotatedStringBounds(text, g2, x + textAdj[0], y + textAdj[1], angle, x + textAdj[0] + rotateAdj[0], y + textAdj[1] + rotateAdj[1]);
/* 685 */     return result;
/*     */   }
/*     */   
/*     */   private static float[] deriveTextBoundsAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor) {
/* 705 */     float[] result = new float[2];
/* 706 */     FontRenderContext frc = g2.getFontRenderContext();
/* 707 */     Font f = g2.getFont();
/* 708 */     FontMetrics fm = g2.getFontMetrics(f);
/* 709 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 710 */     LineMetrics metrics = f.getLineMetrics(text, frc);
/* 711 */     float ascent = metrics.getAscent();
/* 712 */     float halfAscent = ascent / 2.0F;
/* 713 */     float descent = metrics.getDescent();
/* 714 */     float leading = metrics.getLeading();
/* 715 */     float xAdj = 0.0F;
/* 716 */     float yAdj = 0.0F;
/* 718 */     if (anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.CENTER || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.HALF_ASCENT_CENTER) {
/* 724 */       xAdj = (float)-bounds.getWidth() / 2.0F;
/* 727 */     } else if (anchor == TextAnchor.TOP_RIGHT || anchor == TextAnchor.CENTER_RIGHT || anchor == TextAnchor.BOTTOM_RIGHT || anchor == TextAnchor.BASELINE_RIGHT || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 733 */       xAdj = (float)-bounds.getWidth();
/*     */     } 
/* 737 */     if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.TOP_RIGHT) {
/* 741 */       yAdj = -descent - leading + (float)bounds.getHeight();
/* 744 */     } else if (anchor == TextAnchor.HALF_ASCENT_LEFT || anchor == TextAnchor.HALF_ASCENT_CENTER || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 748 */       yAdj = halfAscent;
/* 751 */     } else if (anchor == TextAnchor.CENTER_LEFT || anchor == TextAnchor.CENTER || anchor == TextAnchor.CENTER_RIGHT) {
/* 755 */       yAdj = -descent - leading + (float)(bounds.getHeight() / 2.0D);
/* 758 */     } else if (anchor == TextAnchor.BASELINE_LEFT || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.BASELINE_RIGHT) {
/* 762 */       yAdj = 0.0F;
/* 765 */     } else if (anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BOTTOM_RIGHT) {
/* 769 */       yAdj = -metrics.getDescent() - metrics.getLeading();
/*     */     } 
/* 772 */     result[0] = xAdj;
/* 773 */     result[1] = yAdj;
/* 774 */     return result;
/*     */   }
/*     */   
/*     */   private static float[] deriveRotationAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor) {
/* 792 */     float[] result = new float[2];
/* 793 */     FontRenderContext frc = g2.getFontRenderContext();
/* 794 */     LineMetrics metrics = g2.getFont().getLineMetrics(text, frc);
/* 795 */     FontMetrics fm = g2.getFontMetrics();
/* 796 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 797 */     float ascent = metrics.getAscent();
/* 798 */     float halfAscent = ascent / 2.0F;
/* 799 */     float descent = metrics.getDescent();
/* 800 */     float leading = metrics.getLeading();
/* 801 */     float xAdj = 0.0F;
/* 802 */     float yAdj = 0.0F;
/* 804 */     if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.CENTER_LEFT || anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BASELINE_LEFT || anchor == TextAnchor.HALF_ASCENT_LEFT) {
/* 810 */       xAdj = 0.0F;
/* 813 */     } else if (anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.CENTER || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.HALF_ASCENT_CENTER) {
/* 819 */       xAdj = (float)bounds.getWidth() / 2.0F;
/* 822 */     } else if (anchor == TextAnchor.TOP_RIGHT || anchor == TextAnchor.CENTER_RIGHT || anchor == TextAnchor.BOTTOM_RIGHT || anchor == TextAnchor.BASELINE_RIGHT || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 828 */       xAdj = (float)bounds.getWidth();
/*     */     } 
/* 832 */     if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.TOP_RIGHT) {
/* 836 */       yAdj = descent + leading - (float)bounds.getHeight();
/* 839 */     } else if (anchor == TextAnchor.CENTER_LEFT || anchor == TextAnchor.CENTER || anchor == TextAnchor.CENTER_RIGHT) {
/* 843 */       yAdj = descent + leading - (float)(bounds.getHeight() / 2.0D);
/* 846 */     } else if (anchor == TextAnchor.HALF_ASCENT_LEFT || anchor == TextAnchor.HALF_ASCENT_CENTER || anchor == TextAnchor.HALF_ASCENT_RIGHT) {
/* 850 */       yAdj = -halfAscent;
/* 853 */     } else if (anchor == TextAnchor.BASELINE_LEFT || anchor == TextAnchor.BASELINE_CENTER || anchor == TextAnchor.BASELINE_RIGHT) {
/* 857 */       yAdj = 0.0F;
/* 860 */     } else if (anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BOTTOM_RIGHT) {
/* 864 */       yAdj = metrics.getDescent() + metrics.getLeading();
/*     */     } 
/* 867 */     result[0] = xAdj;
/* 868 */     result[1] = yAdj;
/* 869 */     return result;
/*     */   }
/*     */   
/*     */   public static Shape calculateRotatedStringBounds(String text, Graphics2D g2, float textX, float textY, double angle, float rotateX, float rotateY) {
/* 896 */     if (text == null || text.equals(""))
/* 897 */       return null; 
/* 899 */     FontMetrics fm = g2.getFontMetrics();
/* 900 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 901 */     AffineTransform translate = AffineTransform.getTranslateInstance(textX, textY);
/* 904 */     Shape translatedBounds = translate.createTransformedShape(bounds);
/* 905 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, rotateX, rotateY);
/* 908 */     Shape result = rotate.createTransformedShape(translatedBounds);
/* 909 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean getUseFontMetricsGetStringBounds() {
/* 921 */     return useFontMetricsGetStringBounds;
/*     */   }
/*     */   
/*     */   public static void setUseFontMetricsGetStringBounds(boolean use) {
/* 932 */     useFontMetricsGetStringBounds = use;
/*     */   }
/*     */   
/*     */   public static boolean isUseDrawRotatedStringWorkaround() {
/* 936 */     return useDrawRotatedStringWorkaround;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */