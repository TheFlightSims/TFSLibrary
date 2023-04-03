/*     */ package org.jfree.base;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class BootableProjectInfo extends BasicProjectInfo {
/*  68 */   private ArrayList dependencies = new ArrayList();
/*     */   
/*     */   private String bootClass;
/*     */   
/*     */   private boolean autoBoot = true;
/*     */   
/*     */   public BootableProjectInfo() {}
/*     */   
/*     */   public BootableProjectInfo(String name, String version, String licence, String info) {
/*  82 */     this();
/*  83 */     setName(name);
/*  84 */     setVersion(version);
/*  85 */     setLicenceName(licence);
/*  86 */     setInfo(info);
/*     */   }
/*     */   
/*     */   public BootableProjectInfo(String name, String version, String info, String copyright, String licenceName) {
/* 100 */     this();
/* 101 */     setName(name);
/* 102 */     setVersion(version);
/* 103 */     setLicenceName(licenceName);
/* 104 */     setInfo(info);
/* 105 */     setCopyright(copyright);
/*     */   }
/*     */   
/*     */   public BootableProjectInfo[] getDependencies() {
/* 114 */     return (BootableProjectInfo[])this.dependencies.toArray((Object[])new BootableProjectInfo[this.dependencies.size()]);
/*     */   }
/*     */   
/*     */   public void addDependency(BootableProjectInfo projectInfo) {
/* 124 */     if (projectInfo == null)
/* 125 */       throw new NullPointerException(); 
/* 127 */     this.dependencies.add(projectInfo);
/*     */   }
/*     */   
/*     */   public String getBootClass() {
/* 136 */     return this.bootClass;
/*     */   }
/*     */   
/*     */   public void setBootClass(String bootClass) {
/* 145 */     this.bootClass = bootClass;
/*     */   }
/*     */   
/*     */   public boolean isAutoBoot() {
/* 154 */     return this.autoBoot;
/*     */   }
/*     */   
/*     */   public void setAutoBoot(boolean autoBoot) {
/* 163 */     this.autoBoot = autoBoot;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\BootableProjectInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */