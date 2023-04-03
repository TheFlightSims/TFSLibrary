/*     */ package org.jfree.chart.servlet;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class DisplayChart extends HttpServlet {
/*     */   public void init() throws ServletException {}
/*     */   
/*     */   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 104 */     HttpSession session = request.getSession();
/* 105 */     String filename = request.getParameter("filename");
/* 107 */     if (filename == null)
/* 108 */       throw new ServletException("Parameter 'filename' must be supplied"); 
/* 113 */     filename = ServletUtilities.searchReplace(filename, "..", "");
/* 116 */     File file = new File(System.getProperty("java.io.tmpdir"), filename);
/* 117 */     if (!file.exists())
/* 118 */       throw new ServletException("File '" + file.getAbsolutePath() + "' does not exist"); 
/* 125 */     boolean isChartInUserList = false;
/* 126 */     ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute("JFreeChart_Deleter");
/* 129 */     if (chartDeleter != null)
/* 130 */       isChartInUserList = chartDeleter.isChartAvailable(filename); 
/* 133 */     boolean isChartPublic = false;
/* 134 */     if (filename.length() >= 6 && 
/* 135 */       filename.substring(0, 6).equals("public"))
/* 136 */       isChartPublic = true; 
/* 140 */     boolean isOneTimeChart = false;
/* 141 */     if (filename.startsWith(ServletUtilities.getTempOneTimeFilePrefix()))
/* 142 */       isOneTimeChart = true; 
/* 145 */     if (isChartInUserList || isChartPublic || isOneTimeChart) {
/* 147 */       ServletUtilities.sendTempFile(file, response);
/* 148 */       if (isOneTimeChart)
/* 149 */         file.delete(); 
/*     */     } else {
/* 153 */       throw new ServletException("Chart image not found");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\servlet\DisplayChart.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */