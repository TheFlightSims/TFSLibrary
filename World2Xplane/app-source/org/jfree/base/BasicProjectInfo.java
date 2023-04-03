/*     */ package org.jfree.base;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BasicProjectInfo extends Library {
/*     */   private String copyright;
/*     */   
/*  65 */   private List libraries = new ArrayList();
/*     */   
/*     */   public BasicProjectInfo() {}
/*     */   
/*     */   public BasicProjectInfo(String name, String version, String licence, String info) {
/*  78 */     this();
/*  79 */     setName(name);
/*  80 */     setVersion(version);
/*  81 */     setLicenceName(licence);
/*  82 */     setInfo(info);
/*     */   }
/*     */   
/*     */   public BasicProjectInfo(String name, String version, String info, String copyright, String licenceName) {
/*  97 */     this(name, version, licenceName, info);
/*  98 */     setCopyright(copyright);
/*     */   }
/*     */   
/*     */   public String getCopyright() {
/* 107 */     return this.copyright;
/*     */   }
/*     */   
/*     */   public void setCopyright(String copyright) {
/* 116 */     this.copyright = copyright;
/*     */   }
/*     */   
/*     */   public void setInfo(String info) {
/* 125 */     super.setInfo(info);
/*     */   }
/*     */   
/*     */   public void setLicenceName(String licence) {
/* 134 */     super.setLicenceName(licence);
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 143 */     super.setName(name);
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/* 152 */     super.setVersion(version);
/*     */   }
/*     */   
/*     */   public Library[] getLibraries() {
/* 161 */     return (Library[])this.libraries.toArray((Object[])new Library[this.libraries.size()]);
/*     */   }
/*     */   
/*     */   public void addLibrary(Library library) {
/* 170 */     if (library == null)
/* 171 */       throw new NullPointerException(); 
/* 173 */     this.libraries.add(library);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\BasicProjectInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */