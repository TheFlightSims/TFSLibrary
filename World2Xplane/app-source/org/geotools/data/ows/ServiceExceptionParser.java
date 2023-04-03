/*     */ package org.geotools.data.ows;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.ows.ServiceException;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.input.SAXBuilder;
/*     */ 
/*     */ public class ServiceExceptionParser {
/*     */   public static ServiceException parse(InputStream inputStream) throws JDOMException, IOException {
/*  54 */     SAXBuilder builder = new SAXBuilder();
/*  55 */     Document document = builder.build(inputStream);
/*  57 */     Element root = document.getRootElement();
/*  58 */     List<Element> serviceExceptions = root.getChildren("ServiceException");
/*  63 */     List<ServiceException> codes = new ArrayList();
/*  64 */     List<ServiceException> noCodes = new ArrayList();
/*  65 */     for (int i = 0; i < serviceExceptions.size(); i++) {
/*  66 */       Element element = serviceExceptions.get(i);
/*  67 */       ServiceException exception = parseSE(element);
/*  68 */       if (exception.getCode() != null && exception.getCode().length() != 0) {
/*  69 */         codes.add(exception);
/*     */       } else {
/*  71 */         noCodes.add(exception);
/*     */       } 
/*     */     } 
/*  78 */     ServiceException firstException = null;
/*  79 */     ServiceException recentException = null;
/*     */     int j;
/*  80 */     for (j = 0; j < codes.size(); j++) {
/*  81 */       ServiceException exception = codes.get(j);
/*  82 */       if (firstException == null) {
/*  83 */         firstException = exception;
/*  84 */         recentException = exception;
/*     */       } else {
/*  86 */         recentException.setNext(exception);
/*  87 */         recentException = exception;
/*     */       } 
/*     */     } 
/*  90 */     codes = null;
/*  91 */     for (j = 0; j < noCodes.size(); j++) {
/*  92 */       ServiceException exception = noCodes.get(j);
/*  93 */       if (firstException == null) {
/*  94 */         firstException = exception;
/*  95 */         recentException = exception;
/*     */       } else {
/*  97 */         recentException.setNext(exception);
/*  98 */         recentException = exception;
/*     */       } 
/*     */     } 
/* 101 */     noCodes = null;
/* 103 */     return firstException;
/*     */   }
/*     */   
/*     */   private static ServiceException parseSE(Element element) {
/* 107 */     String errorMessage = element.getText();
/* 108 */     String code = element.getAttributeValue("code");
/* 110 */     return new ServiceException(errorMessage, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\ServiceExceptionParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */