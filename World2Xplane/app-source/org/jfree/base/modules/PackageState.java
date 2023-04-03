/*     */ package org.jfree.base.modules;
/*     */ 
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public class PackageState {
/*     */   public static final int STATE_NEW = 0;
/*     */   
/*     */   public static final int STATE_CONFIGURED = 1;
/*     */   
/*     */   public static final int STATE_INITIALIZED = 2;
/*     */   
/*     */   public static final int STATE_ERROR = -2;
/*     */   
/*     */   private final Module module;
/*     */   
/*     */   private int state;
/*     */   
/*     */   public PackageState(Module module) {
/*  81 */     this(module, 0);
/*     */   }
/*     */   
/*     */   public PackageState(Module module, int state) {
/*  93 */     if (module == null)
/*  95 */       throw new NullPointerException("Module must not be null."); 
/*  97 */     if (state != 1 && state != -2 && state != 2 && state != 0)
/* 100 */       throw new IllegalArgumentException("State is not valid"); 
/* 102 */     this.module = module;
/* 103 */     this.state = state;
/*     */   }
/*     */   
/*     */   public boolean configure(SubSystem subSystem) {
/* 116 */     if (this.state == 0)
/*     */       try {
/* 120 */         this.module.configure(subSystem);
/* 121 */         this.state = 1;
/* 122 */         return true;
/* 124 */       } catch (NoClassDefFoundError noClassDef) {
/* 126 */         Log.warn(new Log.SimpleMessage("Unable to load module classes for ", this.module.getName(), ":", noClassDef.getMessage()));
/* 128 */         this.state = -2;
/* 130 */       } catch (Exception e) {
/* 132 */         Log.warn("Unable to configure the module " + this.module.getName(), e);
/* 133 */         this.state = -2;
/*     */       }  
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   public Module getModule() {
/* 146 */     return this.module;
/*     */   }
/*     */   
/*     */   public int getState() {
/* 157 */     return this.state;
/*     */   }
/*     */   
/*     */   public boolean initialize(SubSystem subSystem) {
/* 172 */     if (this.state == 1)
/*     */       try {
/* 176 */         this.module.initialize(subSystem);
/* 177 */         this.state = 2;
/* 178 */         return true;
/* 180 */       } catch (NoClassDefFoundError noClassDef) {
/* 182 */         Log.warn(new Log.SimpleMessage("Unable to load module classes for ", this.module.getName(), ":", noClassDef.getMessage()));
/* 184 */         this.state = -2;
/* 186 */       } catch (ModuleInitializeException me) {
/* 188 */         Log.warn("Unable to initialize the module " + this.module.getName(), (Exception)me);
/* 189 */         this.state = -2;
/* 191 */       } catch (Exception e) {
/* 193 */         Log.warn("Unable to initialize the module " + this.module.getName(), e);
/* 194 */         this.state = -2;
/*     */       }  
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 210 */     if (this == o)
/* 212 */       return true; 
/* 214 */     if (!(o instanceof PackageState))
/* 216 */       return false; 
/* 219 */     PackageState packageState = (PackageState)o;
/* 221 */     if (!this.module.getModuleClass().equals(packageState.module.getModuleClass()))
/* 223 */       return false; 
/* 226 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 237 */     return this.module.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\PackageState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */