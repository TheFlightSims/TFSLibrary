/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtIncompatible;
/*     */ 
/*     */ @Beta
/*     */ @GwtIncompatible("java.lang.System#getProperty")
/*     */ public enum StandardSystemProperty {
/*  33 */   JAVA_VERSION("java.version"),
/*  36 */   JAVA_VENDOR("java.vendor"),
/*  39 */   JAVA_VENDOR_URL("java.vendor.url"),
/*  42 */   JAVA_HOME("java.home"),
/*  45 */   JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version"),
/*  48 */   JAVA_VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"),
/*  51 */   JAVA_VM_SPECIFICATION_NAME("java.vm.specification.name"),
/*  54 */   JAVA_VM_VERSION("java.vm.version"),
/*  57 */   JAVA_VM_VENDOR("java.vm.vendor"),
/*  60 */   JAVA_VM_NAME("java.vm.name"),
/*  63 */   JAVA_SPECIFICATION_VERSION("java.specification.version"),
/*  66 */   JAVA_SPECIFICATION_VENDOR("java.specification.vendor"),
/*  69 */   JAVA_SPECIFICATION_NAME("java.specification.name"),
/*  72 */   JAVA_CLASS_VERSION("java.class.version"),
/*  75 */   JAVA_CLASS_PATH("java.class.path"),
/*  78 */   JAVA_LIBRARY_PATH("java.library.path"),
/*  81 */   JAVA_IO_TMPDIR("java.io.tmpdir"),
/*  84 */   JAVA_COMPILER("java.compiler"),
/*  87 */   JAVA_EXT_DIRS("java.ext.dirs"),
/*  90 */   OS_NAME("os.name"),
/*  93 */   OS_ARCH("os.arch"),
/*  96 */   OS_VERSION("os.version"),
/*  99 */   FILE_SEPARATOR("file.separator"),
/* 102 */   PATH_SEPARATOR("path.separator"),
/* 105 */   LINE_SEPARATOR("line.separator"),
/* 108 */   USER_NAME("user.name"),
/* 111 */   USER_HOME("user.home"),
/* 114 */   USER_DIR("user.dir");
/*     */   
/*     */   private final String key;
/*     */   
/*     */   StandardSystemProperty(String key) {
/* 119 */     this.key = key;
/*     */   }
/*     */   
/*     */   public String key() {
/* 126 */     return this.key;
/*     */   }
/*     */   
/*     */   public String value() {
/* 134 */     return System.getProperty(this.key);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 141 */     return key() + "=" + value();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\StandardSystemProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */