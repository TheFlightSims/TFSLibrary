/*     */ package org.jfree.base.modules;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.jfree.base.AbstractBoot;
/*     */ import org.jfree.base.config.HierarchicalConfiguration;
/*     */ import org.jfree.base.config.PropertyFileConfiguration;
/*     */ import org.jfree.base.log.PadMessage;
/*     */ import org.jfree.util.Configuration;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public final class PackageManager {
/*     */   private static final int RETURN_MODULE_LOADED = 0;
/*     */   
/*     */   private static final int RETURN_MODULE_UNKNOWN = 1;
/*     */   
/*     */   private static final int RETURN_MODULE_ERROR = 2;
/*     */   
/*     */   private final PackageConfiguration packageConfiguration;
/*     */   
/*     */   private final ArrayList modules;
/*     */   
/*     */   private final ArrayList initSections;
/*     */   
/*     */   private AbstractBoot booter;
/*     */   
/*     */   private static HashMap instances;
/*     */   
/*     */   public static class PackageConfiguration extends PropertyFileConfiguration {
/*     */     public void insertConfiguration(HierarchicalConfiguration config) {
/*  95 */       super.insertConfiguration(config);
/*     */     }
/*     */   }
/*     */   
/*     */   public static PackageManager createInstance(AbstractBoot booter) {
/* 144 */     if (instances == null) {
/* 145 */       instances = new HashMap();
/* 146 */       PackageManager packageManager = new PackageManager(booter);
/* 147 */       instances.put(booter, packageManager);
/* 148 */       return packageManager;
/*     */     } 
/* 150 */     PackageManager manager = (PackageManager)instances.get(booter);
/* 151 */     if (manager == null) {
/* 152 */       manager = new PackageManager(booter);
/* 153 */       instances.put(booter, manager);
/*     */     } 
/* 155 */     return manager;
/*     */   }
/*     */   
/*     */   private PackageManager(AbstractBoot booter) {
/* 164 */     if (booter == null)
/* 165 */       throw new NullPointerException(); 
/* 167 */     this.booter = booter;
/* 168 */     this.packageConfiguration = new PackageConfiguration();
/* 169 */     this.modules = new ArrayList();
/* 170 */     this.initSections = new ArrayList();
/*     */   }
/*     */   
/*     */   public boolean isModuleAvailable(ModuleInfo moduleDescription) {
/* 181 */     PackageState[] packageStates = (PackageState[])this.modules.toArray((Object[])new PackageState[this.modules.size()]);
/* 183 */     for (int i = 0; i < packageStates.length; i++) {
/* 184 */       PackageState state = packageStates[i];
/* 185 */       if (state.getModule().getModuleClass().equals(moduleDescription.getModuleClass()))
/* 186 */         return (state.getState() == 2); 
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public void load(String modulePrefix) {
/* 200 */     if (this.initSections.contains(modulePrefix))
/*     */       return; 
/* 203 */     this.initSections.add(modulePrefix);
/* 205 */     Configuration config = this.booter.getGlobalConfig();
/* 206 */     Iterator it = config.findPropertyKeys(modulePrefix);
/* 207 */     while (it.hasNext()) {
/* 208 */       String key = it.next();
/* 209 */       if (key.endsWith(".Module"))
/* 210 */         addModule(config.getConfigProperty(key)); 
/*     */     } 
/* 213 */     Log.debug("Loaded a total of " + this.modules.size() + " modules under prefix: " + modulePrefix);
/*     */   }
/*     */   
/*     */   public synchronized void initializeModules() {
/* 222 */     PackageSorter.sort(this.modules);
/*     */     int i;
/* 224 */     for (i = 0; i < this.modules.size(); i++) {
/* 225 */       PackageState mod = this.modules.get(i);
/* 226 */       if (mod.configure((SubSystem)this.booter))
/* 227 */         Log.debug(new Log.SimpleMessage("Conf: ", new PadMessage(mod.getModule().getModuleClass(), 70), " [", mod.getModule().getSubSystem(), "]")); 
/*     */     } 
/* 233 */     for (i = 0; i < this.modules.size(); i++) {
/* 234 */       PackageState mod = this.modules.get(i);
/* 235 */       if (mod.initialize((SubSystem)this.booter))
/* 236 */         Log.debug(new Log.SimpleMessage("Init: ", new PadMessage(mod.getModule().getModuleClass(), 70), " [", mod.getModule().getSubSystem(), "]")); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void addModule(String modClass) {
/* 251 */     ArrayList loadModules = new ArrayList();
/* 252 */     ModuleInfo modInfo = new DefaultModuleInfo(modClass, null, null, null);
/* 254 */     if (loadModule(modInfo, new ArrayList(), loadModules, false))
/* 255 */       for (int i = 0; i < loadModules.size(); i++) {
/* 256 */         Module mod = loadModules.get(i);
/* 257 */         this.modules.add(new PackageState(mod));
/*     */       }  
/*     */   }
/*     */   
/*     */   private int containsModule(ArrayList tempModules, ModuleInfo module) {
/* 272 */     if (tempModules != null) {
/* 273 */       ModuleInfo[] mods = (ModuleInfo[])tempModules.toArray((Object[])new ModuleInfo[tempModules.size()]);
/* 275 */       for (int j = 0; j < mods.length; j++) {
/* 276 */         if (mods[j].getModuleClass().equals(module.getModuleClass()))
/* 277 */           return 0; 
/*     */       } 
/*     */     } 
/* 282 */     PackageState[] packageStates = (PackageState[])this.modules.toArray((Object[])new PackageState[this.modules.size()]);
/* 284 */     for (int i = 0; i < packageStates.length; i++) {
/* 285 */       if (packageStates[i].getModule().getModuleClass().equals(module.getModuleClass())) {
/* 286 */         if (packageStates[i].getState() == -2)
/* 287 */           return 2; 
/* 290 */         return 0;
/*     */       } 
/*     */     } 
/* 294 */     return 1;
/*     */   }
/*     */   
/*     */   private void dropFailedModule(PackageState state) {
/* 305 */     if (!this.modules.contains(state))
/* 306 */       this.modules.add(state); 
/*     */   }
/*     */   
/*     */   private boolean loadModule(ModuleInfo moduleInfo, ArrayList incompleteModules, ArrayList modules, boolean fatal) {
/*     */     try {
/* 329 */       Class c = ObjectUtilities.getClassLoader(getClass()).loadClass(moduleInfo.getModuleClass());
/* 330 */       Module module = (Module)c.newInstance();
/* 332 */       if (!acceptVersion(moduleInfo, module)) {
/* 334 */         Log.warn("Module " + module.getName() + ": required version: " + moduleInfo + ", but found Version: \n" + module);
/* 336 */         PackageState state = new PackageState(module, -2);
/* 337 */         dropFailedModule(state);
/* 338 */         return false;
/*     */       } 
/* 341 */       int moduleContained = containsModule(modules, module);
/* 342 */       if (moduleContained == 2) {
/* 344 */         Log.debug("Indicated failure for module: " + module.getModuleClass());
/* 345 */         PackageState state = new PackageState(module, -2);
/* 346 */         dropFailedModule(state);
/* 347 */         return false;
/*     */       } 
/* 349 */       if (moduleContained == 1) {
/* 350 */         if (incompleteModules.contains(module)) {
/* 352 */           Log.error(new Log.SimpleMessage("Circular module reference: This module definition is invalid: ", module.getClass()));
/* 355 */           PackageState state = new PackageState(module, -2);
/* 356 */           dropFailedModule(state);
/* 357 */           return false;
/*     */         } 
/* 359 */         incompleteModules.add(module);
/* 360 */         ModuleInfo[] required = module.getRequiredModules();
/* 361 */         for (int i = 0; i < required.length; i++) {
/* 362 */           if (!loadModule(required[i], incompleteModules, modules, true)) {
/* 363 */             Log.debug("Indicated failure for module: " + module.getModuleClass());
/* 364 */             PackageState state = new PackageState(module, -2);
/* 365 */             dropFailedModule(state);
/* 366 */             return false;
/*     */           } 
/*     */         } 
/* 370 */         ModuleInfo[] optional = module.getOptionalModules();
/* 371 */         for (int j = 0; j < optional.length; j++) {
/* 372 */           if (!loadModule(optional[j], incompleteModules, modules, true))
/* 373 */             Log.debug(new Log.SimpleMessage("Optional module: ", optional[j].getModuleClass(), " was not loaded.")); 
/*     */         } 
/* 378 */         if (containsModule(modules, module) == 1)
/* 379 */           modules.add(module); 
/* 381 */         incompleteModules.remove(module);
/*     */       } 
/* 383 */       return true;
/* 385 */     } catch (ClassNotFoundException cnfe) {
/* 386 */       if (fatal)
/* 387 */         Log.warn(new Log.SimpleMessage("Unresolved dependency for package: ", moduleInfo.getModuleClass())); 
/* 390 */       Log.debug(new Log.SimpleMessage("ClassNotFound: ", cnfe.getMessage()));
/* 391 */       return false;
/* 393 */     } catch (Exception e) {
/* 394 */       Log.warn(new Log.SimpleMessage("Exception while loading module: ", moduleInfo), e);
/* 395 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean acceptVersion(ModuleInfo moduleRequirement, Module module) {
/* 408 */     if (moduleRequirement.getMajorVersion() == null)
/* 409 */       return true; 
/* 411 */     if (module.getMajorVersion() == null) {
/* 412 */       Log.warn("Module " + module.getName() + " does not define a major version.");
/*     */     } else {
/* 415 */       int compare = acceptVersion(moduleRequirement.getMajorVersion(), module.getMajorVersion());
/* 417 */       if (compare > 0)
/* 418 */         return false; 
/* 420 */       if (compare < 0)
/* 421 */         return true; 
/*     */     } 
/* 425 */     if (moduleRequirement.getMinorVersion() == null)
/* 426 */       return true; 
/* 428 */     if (module.getMinorVersion() == null) {
/* 429 */       Log.warn("Module " + module.getName() + " does not define a minor version.");
/*     */     } else {
/* 432 */       int compare = acceptVersion(moduleRequirement.getMinorVersion(), module.getMinorVersion());
/* 434 */       if (compare > 0)
/* 435 */         return false; 
/* 437 */       if (compare < 0)
/* 438 */         return true; 
/*     */     } 
/* 442 */     if (moduleRequirement.getPatchLevel() == null)
/* 443 */       return true; 
/* 445 */     if (module.getPatchLevel() == null) {
/* 446 */       Log.debug("Module " + module.getName() + " does not define a patch level.");
/* 449 */     } else if (acceptVersion(moduleRequirement.getPatchLevel(), module.getPatchLevel()) > 0) {
/* 451 */       Log.debug("Did not accept patchlevel: " + moduleRequirement.getPatchLevel() + " - " + module.getPatchLevel());
/* 454 */       return false;
/*     */     } 
/* 457 */     return true;
/*     */   }
/*     */   
/*     */   private int acceptVersion(String modVer, String depModVer) {
/*     */     char[] modVerArray, depVerArray;
/* 472 */     int mLength = Math.max(modVer.length(), depModVer.length());
/* 475 */     if (modVer.length() > depModVer.length()) {
/* 476 */       modVerArray = modVer.toCharArray();
/* 477 */       depVerArray = new char[mLength];
/* 478 */       int delta = modVer.length() - depModVer.length();
/* 479 */       Arrays.fill(depVerArray, 0, delta, ' ');
/* 480 */       System.arraycopy(depVerArray, delta, depModVer.toCharArray(), 0, depModVer.length());
/* 482 */     } else if (modVer.length() < depModVer.length()) {
/* 483 */       depVerArray = depModVer.toCharArray();
/* 484 */       modVerArray = new char[mLength];
/* 485 */       char[] b1 = new char[mLength];
/* 486 */       int delta = depModVer.length() - modVer.length();
/* 487 */       Arrays.fill(b1, 0, delta, ' ');
/* 488 */       System.arraycopy(b1, delta, modVer.toCharArray(), 0, modVer.length());
/*     */     } else {
/* 491 */       depVerArray = depModVer.toCharArray();
/* 492 */       modVerArray = modVer.toCharArray();
/*     */     } 
/* 494 */     return (new String(modVerArray)).compareTo(new String(depVerArray));
/*     */   }
/*     */   
/*     */   public PackageConfiguration getPackageConfiguration() {
/* 505 */     return this.packageConfiguration;
/*     */   }
/*     */   
/*     */   public Module[] getAllModules() {
/* 516 */     Module[] mods = new Module[this.modules.size()];
/* 517 */     for (int i = 0; i < this.modules.size(); i++) {
/* 518 */       PackageState state = this.modules.get(i);
/* 519 */       mods[i] = state.getModule();
/*     */     } 
/* 521 */     return mods;
/*     */   }
/*     */   
/*     */   public Module[] getActiveModules() {
/* 531 */     ArrayList mods = new ArrayList();
/* 532 */     for (int i = 0; i < this.modules.size(); i++) {
/* 533 */       PackageState state = this.modules.get(i);
/* 534 */       if (state.getState() == 2)
/* 535 */         mods.add(state.getModule()); 
/*     */     } 
/* 538 */     return mods.<Module>toArray(new Module[mods.size()]);
/*     */   }
/*     */   
/*     */   public void printUsedModules(PrintStream p) {
/* 547 */     Module[] allMods = getAllModules();
/* 548 */     ArrayList activeModules = new ArrayList();
/* 549 */     ArrayList failedModules = new ArrayList();
/*     */     int i;
/* 551 */     for (i = 0; i < allMods.length; i++) {
/* 552 */       if (isModuleAvailable(allMods[i])) {
/* 553 */         activeModules.add(allMods[i]);
/*     */       } else {
/* 556 */         failedModules.add(allMods[i]);
/*     */       } 
/*     */     } 
/* 560 */     p.print("Active modules: ");
/* 561 */     p.println(activeModules.size());
/* 562 */     p.println("----------------------------------------------------------");
/* 563 */     for (i = 0; i < activeModules.size(); i++) {
/* 564 */       Module mod = activeModules.get(i);
/* 565 */       p.print(new PadMessage(mod.getModuleClass(), 70));
/* 566 */       p.print(" [");
/* 567 */       p.print(mod.getSubSystem());
/* 568 */       p.println("]");
/* 569 */       p.print("  Version: ");
/* 570 */       p.print(mod.getMajorVersion());
/* 571 */       p.print("-");
/* 572 */       p.print(mod.getMinorVersion());
/* 573 */       p.print("-");
/* 574 */       p.print(mod.getPatchLevel());
/* 575 */       p.print(" Producer: ");
/* 576 */       p.println(mod.getProducer());
/* 577 */       p.print("  Description: ");
/* 578 */       p.println(mod.getDescription());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\PackageManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */