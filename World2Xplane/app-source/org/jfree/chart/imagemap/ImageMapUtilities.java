/*     */ package org.jfree.chart.imagemap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.util.StringUtils;
/*     */ 
/*     */ public class ImageMapUtilities {
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info) throws IOException {
/*  78 */     writeImageMap(writer, name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
/*     */   }
/*     */   
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, boolean useOverLibForToolTips) throws IOException {
/* 103 */     ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
/* 104 */     if (useOverLibForToolTips) {
/* 105 */       toolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
/*     */     } else {
/* 109 */       toolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
/*     */     } 
/* 112 */     writeImageMap(writer, name, info, toolTipTagFragmentGenerator, new StandardURLTagFragmentGenerator());
/*     */   }
/*     */   
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator) throws IOException {
/* 136 */     writer.println(getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator));
/*     */   }
/*     */   
/*     */   public static String getImageMap(String name, ChartRenderingInfo info) {
/* 153 */     return getImageMap(name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
/*     */   }
/*     */   
/*     */   public static String getImageMap(String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator) {
/* 176 */     StringBuffer sb = new StringBuffer();
/* 177 */     sb.append("<map id=\"" + name + "\" name=\"" + name + "\">");
/* 178 */     sb.append(StringUtils.getLineSeparator());
/* 179 */     EntityCollection entities = info.getEntityCollection();
/* 180 */     if (entities != null) {
/* 181 */       int count = entities.getEntityCount();
/* 182 */       for (int i = count - 1; i >= 0; i--) {
/* 183 */         ChartEntity entity = entities.getEntity(i);
/* 184 */         if (entity.getToolTipText() != null || entity.getURLText() != null) {
/* 186 */           String area = entity.getImageMapAreaTag(toolTipTagFragmentGenerator, urlTagFragmentGenerator);
/* 189 */           if (area.length() > 0) {
/* 190 */             sb.append(area);
/* 191 */             sb.append(StringUtils.getLineSeparator());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 196 */     sb.append("</map>");
/* 197 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\imagemap\ImageMapUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */