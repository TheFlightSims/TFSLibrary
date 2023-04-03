/*     */ package org.jfree.base;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import org.jfree.base.config.HierarchicalConfiguration;
/*     */ import org.jfree.base.config.PropertyFileConfiguration;
/*     */ import org.jfree.base.config.SystemPropertyConfiguration;
/*     */ import org.jfree.base.modules.PackageManager;
/*     */ import org.jfree.base.modules.SubSystem;
/*     */ import org.jfree.util.Configuration;
/*     */ import org.jfree.util.ExtendedConfiguration;
/*     */ import org.jfree.util.ExtendedConfigurationWrapper;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class AbstractBoot implements SubSystem {
/*     */   private ExtendedConfigurationWrapper extWrapper;
/*     */   
/*     */   private PackageManager packageManager;
/*     */   
/*     */   private Configuration globalConfig;
/*     */   
/*     */   private boolean bootInProgress;
/*     */   
/*     */   private boolean bootDone;
/*     */   
/*     */   public synchronized PackageManager getPackageManager() {
/* 103 */     if (this.packageManager == null)
/* 104 */       this.packageManager = PackageManager.createInstance(this); 
/* 106 */     return this.packageManager;
/*     */   }
/*     */   
/*     */   public synchronized Configuration getGlobalConfig() {
/* 115 */     if (this.globalConfig == null) {
/* 116 */       this.globalConfig = loadConfiguration();
/* 117 */       start();
/*     */     } 
/* 119 */     return this.globalConfig;
/*     */   }
/*     */   
/*     */   public final synchronized boolean isBootInProgress() {
/* 128 */     return this.bootInProgress;
/*     */   }
/*     */   
/*     */   public final synchronized boolean isBootDone() {
/* 137 */     return this.bootDone;
/*     */   }
/*     */   
/*     */   protected abstract Configuration loadConfiguration();
/*     */   
/*     */   public final void start() {
/* 152 */     synchronized (this) {
/* 153 */       if (isBootInProgress() || isBootDone())
/*     */         return; 
/* 156 */       this.bootInProgress = true;
/*     */     } 
/* 160 */     BootableProjectInfo info = getProjectInfo();
/* 161 */     if (info != null) {
/* 162 */       Log.info(info.getName() + " " + info.getVersion());
/* 163 */       BootableProjectInfo[] childs = info.getDependencies();
/* 164 */       for (int i = 0; i < childs.length; i++) {
/* 165 */         AbstractBoot boot = loadBooter(childs[i].getBootClass());
/* 166 */         if (boot != null)
/* 167 */           boot.start(); 
/*     */       } 
/*     */     } 
/* 171 */     performBoot();
/* 173 */     synchronized (this) {
/* 174 */       this.bootInProgress = false;
/* 175 */       this.bootDone = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void performBoot();
/*     */   
/*     */   protected abstract BootableProjectInfo getProjectInfo();
/*     */   
/*     */   protected AbstractBoot loadBooter(String classname) {
/* 199 */     if (classname == null)
/* 200 */       return null; 
/*     */     try {
/* 203 */       Class c = ObjectUtilities.getClassLoader(getClass()).loadClass(classname);
/* 205 */       Method m = c.getMethod("getInstance", (Class[])null);
/* 206 */       return (AbstractBoot)m.invoke(null, (Object[])null);
/* 208 */     } catch (Exception e) {
/* 209 */       Log.info("Unable to boot dependent class: " + classname);
/* 210 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Configuration createDefaultHierarchicalConfiguration(String staticConfig, String userConfig, boolean addSysProps) {
/* 234 */     HierarchicalConfiguration globalConfig = new HierarchicalConfiguration();
/* 237 */     if (staticConfig != null) {
/* 238 */       PropertyFileConfiguration rootProperty = new PropertyFileConfiguration();
/* 240 */       rootProperty.load(staticConfig);
/* 241 */       globalConfig.insertConfiguration((HierarchicalConfiguration)rootProperty);
/* 242 */       globalConfig.insertConfiguration((HierarchicalConfiguration)getPackageManager().getPackageConfiguration());
/*     */     } 
/* 245 */     if (userConfig != null) {
/* 247 */       PropertyFileConfiguration baseProperty = new PropertyFileConfiguration();
/* 249 */       baseProperty.load(userConfig);
/* 250 */       globalConfig.insertConfiguration((HierarchicalConfiguration)baseProperty);
/*     */     } 
/* 252 */     SystemPropertyConfiguration systemConfig = new SystemPropertyConfiguration();
/* 254 */     globalConfig.insertConfiguration((HierarchicalConfiguration)systemConfig);
/* 255 */     return (Configuration)globalConfig;
/*     */   }
/*     */   
/*     */   public synchronized ExtendedConfiguration getExtendedConfig() {
/* 265 */     if (this.extWrapper == null)
/* 267 */       this.extWrapper = new ExtendedConfigurationWrapper(getGlobalConfig()); 
/* 269 */     return (ExtendedConfiguration)this.extWrapper;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\AbstractBoot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */