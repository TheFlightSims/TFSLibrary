/*     */ package org.jfree.chart.servlet;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpSessionBindingEvent;
/*     */ import javax.servlet.http.HttpSessionBindingListener;
/*     */ 
/*     */ public class ChartDeleter implements HttpSessionBindingListener {
/*  61 */   private List chartNames = new ArrayList();
/*     */   
/*     */   public void addChart(String filename) {
/*  77 */     this.chartNames.add(filename);
/*     */   }
/*     */   
/*     */   public boolean isChartAvailable(String filename) {
/*  89 */     return this.chartNames.contains(filename);
/*     */   }
/*     */   
/*     */   public void valueBound(HttpSessionBindingEvent event) {}
/*     */   
/*     */   public void valueUnbound(HttpSessionBindingEvent event) {
/* 110 */     Iterator iter = this.chartNames.listIterator();
/* 111 */     while (iter.hasNext()) {
/* 112 */       String filename = iter.next();
/* 113 */       File file = new File(System.getProperty("java.io.tmpdir"), filename);
/* 116 */       if (file.exists())
/* 117 */         file.delete(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\servlet\ChartDeleter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */