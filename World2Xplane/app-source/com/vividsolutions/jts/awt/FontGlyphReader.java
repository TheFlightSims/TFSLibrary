/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.awt.Font;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FontGlyphReader {
/*     */   public static final String FONT_SERIF = "Serif";
/*     */   
/*     */   public static final String FONT_SANSERIF = "SanSerif";
/*     */   
/*     */   public static final String FONT_MONOSPACED = "Monospaced";
/*     */   
/*     */   private static final double FLATNESS_FACTOR = 400.0D;
/*     */   
/*     */   public static Geometry read(String text, String fontName, int pointSize, GeometryFactory geomFact) {
/*  76 */     return read(text, new Font(fontName, 0, pointSize), geomFact);
/*     */   }
/*     */   
/*     */   public static Geometry read(String text, Font font, GeometryFactory geomFact) {
/*  90 */     double flatness = font.getSize() / 400.0D;
/*  91 */     return read(text, font, flatness, geomFact);
/*     */   }
/*     */   
/*     */   public static Geometry read(String text, Font font, double flatness, GeometryFactory geomFact) {
/* 105 */     char[] chs = text.toCharArray();
/* 106 */     FontRenderContext fontContext = new FontRenderContext(null, false, true);
/* 107 */     GlyphVector gv = font.createGlyphVector(fontContext, chs);
/* 108 */     List<Geometry> polys = new ArrayList();
/* 109 */     for (int i = 0; i < gv.getNumGlyphs(); i++) {
/* 110 */       Geometry geom = ShapeReader.read(gv.getGlyphOutline(i), flatness, geomFact);
/* 111 */       for (int j = 0; j < geom.getNumGeometries(); j++)
/* 112 */         polys.add(geom.getGeometryN(j)); 
/*     */     } 
/* 115 */     return geomFact.buildGeometry(polys);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\FontGlyphReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */