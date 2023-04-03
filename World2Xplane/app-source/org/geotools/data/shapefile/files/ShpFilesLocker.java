/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ class ShpFilesLocker {
/*  28 */   static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*  34 */   protected static final Boolean TRACE_ENABLED = Boolean.valueOf("true".equalsIgnoreCase(System.getProperty("gt2.shapefile.trace")));
/*     */   
/*     */   final URI uri;
/*     */   
/*     */   final URL url;
/*     */   
/*     */   final FileReader reader;
/*     */   
/*     */   final FileWriter writer;
/*     */   
/*     */   boolean upgraded;
/*     */   
/*     */   private Trace trace;
/*     */   
/*     */   public ShpFilesLocker(URL url, FileReader reader) {
/*  44 */     this.url = url;
/*  45 */     this.reader = reader;
/*  46 */     this.writer = null;
/*  47 */     LOGGER.fine("Read lock: " + url + " by " + reader.id());
/*  48 */     setTraceException();
/*  51 */     this.uri = getURI(url);
/*     */   }
/*     */   
/*     */   URI getURI(URL url) {
/*     */     try {
/*  56 */       return url.toURI();
/*  57 */     } catch (URISyntaxException e) {
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ShpFilesLocker(URL url, FileWriter writer) {
/*  64 */     this.url = url;
/*  65 */     this.reader = null;
/*  66 */     this.writer = writer;
/*  67 */     LOGGER.fine("Write lock: " + url + " by " + writer.id());
/*  68 */     setTraceException();
/*  71 */     this.uri = getURI(url);
/*     */   }
/*     */   
/*     */   private void setTraceException() {
/*  75 */     String type, id, name = Thread.currentThread().getName();
/*  77 */     if (this.reader != null) {
/*  78 */       type = "read";
/*  79 */       id = this.reader.id();
/*     */     } else {
/*  81 */       type = "write";
/*  82 */       id = this.writer.id();
/*     */     } 
/*  84 */     if (TRACE_ENABLED.booleanValue())
/*  85 */       this.trace = new Trace("Locking " + this.url + " for " + type + " by " + id + " in thread " + name); 
/*     */   }
/*     */   
/*     */   public Exception getTrace() {
/*  96 */     return this.trace;
/*     */   }
/*     */   
/*     */   public void compare(URL url2, Object requestor) {
/* 104 */     URL url = this.url;
/* 105 */     assert url2 == url : "Expected: " + url + " but got: " + url2;
/* 106 */     assert this.reader == null || requestor == this.reader : "Expected the requestor and the reader to be the same object: " + this.reader.id();
/* 108 */     assert this.writer == null || requestor == this.writer : "Expected the requestor and the writer to be the same object: " + this.writer.id();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     if (this.reader != null)
/* 115 */       return "read on " + this.url + " by " + this.reader.id(); 
/* 117 */     return "write on " + this.url + " by " + this.writer.id();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 123 */     int prime = 31;
/* 124 */     int result = 1;
/* 125 */     result = 31 * result + ((this.reader == null) ? 0 : this.reader.hashCode());
/* 126 */     result = 31 * result + ((this.url == null) ? 0 : this.url.hashCode());
/* 127 */     result = 31 * result + ((this.writer == null) ? 0 : this.writer.hashCode());
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 133 */     if (this == obj)
/* 134 */       return true; 
/* 135 */     if (obj == null)
/* 136 */       return false; 
/* 137 */     if (getClass() != obj.getClass())
/* 138 */       return false; 
/* 139 */     ShpFilesLocker other = (ShpFilesLocker)obj;
/* 140 */     if (this.reader == null) {
/* 141 */       if (other.reader != null)
/* 142 */         return false; 
/* 143 */     } else if (!this.reader.equals(other.reader)) {
/* 144 */       return false;
/*     */     } 
/* 145 */     if (this.url == null) {
/* 146 */       if (other.url != null)
/* 147 */         return false; 
/* 150 */     } else if (this.uri != null) {
/* 151 */       if (!this.uri.equals(other.uri))
/* 152 */         return false; 
/* 155 */     } else if (!this.url.equals(other.url)) {
/* 156 */       return false;
/*     */     } 
/* 158 */     if (this.writer == null) {
/* 159 */       if (other.writer != null)
/* 160 */         return false; 
/* 161 */     } else if (!this.writer.equals(other.writer)) {
/* 162 */       return false;
/*     */     } 
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   private static class Trace extends Exception {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Trace(String message) {
/* 171 */       super(message);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\ShpFilesLocker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */