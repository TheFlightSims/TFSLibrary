/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.io.ExpandedTabWriter;
/*     */ 
/*     */ public final class GraphicsUtilities {
/*     */   private static final int TAB_WIDTH = 4;
/*     */   
/*     */   public static void paintStackTrace(Graphics2D graphics, Rectangle widgetBounds, Throwable exception) {
/*  78 */     String message = printStackTrace(exception);
/*  84 */     double width = 0.0D, height = 0.0D;
/*  85 */     List<GlyphVector> glyphs = new ArrayList<GlyphVector>();
/*  86 */     List<Rectangle2D> bounds = new ArrayList<Rectangle2D>();
/*  87 */     int length = message.length();
/*  88 */     Font font = graphics.getFont();
/*  89 */     FontRenderContext context = graphics.getFontRenderContext();
/*     */     int i;
/*  90 */     for (i = 0; i < length; ) {
/*  91 */       int ir = message.indexOf('\r', i);
/*  92 */       int in = message.indexOf('\n', i);
/*  93 */       if (ir < 0)
/*  93 */         ir = length; 
/*  94 */       if (in < 0)
/*  94 */         in = length; 
/*  95 */       int irn = Math.min(ir, in);
/*  96 */       GlyphVector line = font.createGlyphVector(context, message.substring(i, irn));
/*  97 */       Rectangle2D rect = line.getVisualBounds();
/*  98 */       double w = rect.getWidth();
/*  99 */       if (w > width)
/*  99 */         width = w; 
/* 100 */       height += rect.getHeight();
/* 101 */       glyphs.add(line);
/* 102 */       bounds.add(rect);
/* 103 */       i = ((Math.abs(ir - in) <= 1) ? Math.max(ir, in) : irn) + 1;
/*     */     } 
/* 108 */     float xpos = (float)(0.5D * (widgetBounds.width - width));
/* 109 */     float ypos = (float)(0.5D * (widgetBounds.height - height));
/* 110 */     int size = glyphs.size();
/* 111 */     for (int j = 0; j < size; j++) {
/* 112 */       GlyphVector line = glyphs.get(j);
/* 113 */       Rectangle2D rect = bounds.get(j);
/* 114 */       ypos = (float)(ypos + rect.getHeight());
/* 115 */       graphics.drawGlyphVector(line, xpos, ypos);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String printStackTrace(Throwable exception) {
/* 125 */     StringWriter writer = new StringWriter();
/* 126 */     exception.printStackTrace(new PrintWriter((Writer)new ExpandedTabWriter(writer, 4)));
/* 127 */     return writer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\GraphicsUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */