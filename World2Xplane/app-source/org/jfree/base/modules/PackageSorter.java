/*     */ package org.jfree.base.modules;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public final class PackageSorter {
/*     */   private static class SortModule implements Comparable {
/*     */     private int position;
/*     */     
/*     */     private final PackageState state;
/*     */     
/*     */     private ArrayList dependSubsystems;
/*     */     
/*     */     public SortModule(PackageState state) {
/*  93 */       this.position = -1;
/*  94 */       this.state = state;
/*     */     }
/*     */     
/*     */     public ArrayList getDependSubsystems() {
/* 105 */       return this.dependSubsystems;
/*     */     }
/*     */     
/*     */     public void setDependSubsystems(ArrayList dependSubsystems) {
/* 116 */       this.dependSubsystems = dependSubsystems;
/*     */     }
/*     */     
/*     */     public int getPosition() {
/* 128 */       return this.position;
/*     */     }
/*     */     
/*     */     public void setPosition(int position) {
/* 139 */       this.position = position;
/*     */     }
/*     */     
/*     */     public PackageState getState() {
/* 149 */       return this.state;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 161 */       StringBuffer buffer = new StringBuffer();
/* 162 */       buffer.append("SortModule: ");
/* 163 */       buffer.append(this.position);
/* 164 */       buffer.append(" ");
/* 165 */       buffer.append(this.state.getModule().getName());
/* 166 */       buffer.append(" ");
/* 167 */       buffer.append(this.state.getModule().getModuleClass());
/* 168 */       return buffer.toString();
/*     */     }
/*     */     
/*     */     public int compareTo(Object o) {
/* 184 */       SortModule otherModule = (SortModule)o;
/* 185 */       if (this.position > otherModule.position)
/* 187 */         return 1; 
/* 189 */       if (this.position < otherModule.position)
/* 191 */         return -1; 
/* 193 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void sort(List modules) {
/* 214 */     HashMap moduleMap = new HashMap();
/* 215 */     ArrayList errorModules = new ArrayList();
/* 216 */     ArrayList weightModules = new ArrayList();
/* 218 */     for (int i = 0; i < modules.size(); i++) {
/* 220 */       PackageState state = modules.get(i);
/* 221 */       if (state.getState() == -2) {
/* 223 */         errorModules.add(state);
/*     */       } else {
/* 227 */         SortModule mod = new SortModule(state);
/* 228 */         weightModules.add(mod);
/* 229 */         moduleMap.put(state.getModule().getModuleClass(), mod);
/*     */       } 
/*     */     } 
/* 233 */     SortModule[] weigths = weightModules.<SortModule>toArray(new SortModule[weightModules.size()]);
/* 236 */     for (int j = 0; j < weigths.length; j++) {
/* 238 */       SortModule sortMod = weigths[j];
/* 239 */       sortMod.setDependSubsystems(collectSubsystemModules(sortMod.getState().getModule(), moduleMap));
/*     */     } 
/* 250 */     boolean doneWork = true;
/* 251 */     while (doneWork) {
/* 253 */       doneWork = false;
/* 254 */       for (int m = 0; m < weigths.length; m++) {
/* 256 */         SortModule mod = weigths[m];
/* 257 */         int position = searchModulePosition(mod, moduleMap);
/* 258 */         if (position != mod.getPosition()) {
/* 260 */           mod.setPosition(position);
/* 261 */           doneWork = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 266 */     Arrays.sort((Object[])weigths);
/* 267 */     modules.clear();
/*     */     int k;
/* 268 */     for (k = 0; k < weigths.length; k++)
/* 270 */       modules.add(weigths[k].getState()); 
/* 272 */     for (k = 0; k < errorModules.size(); k++)
/* 274 */       modules.add(errorModules.get(k)); 
/*     */   }
/*     */   
/*     */   private static int searchModulePosition(SortModule smodule, HashMap moduleMap) {
/* 290 */     Module module = smodule.getState().getModule();
/* 291 */     int position = 0;
/* 296 */     ModuleInfo[] modInfo = module.getOptionalModules();
/*     */     int modPos;
/* 297 */     for (modPos = 0; modPos < modInfo.length; modPos++) {
/* 299 */       String moduleName = modInfo[modPos].getModuleClass();
/* 300 */       SortModule reqMod = (SortModule)moduleMap.get(moduleName);
/* 301 */       if (reqMod != null)
/* 305 */         if (reqMod.getPosition() >= position)
/* 307 */           position = reqMod.getPosition() + 1;  
/*     */     } 
/* 315 */     modInfo = module.getRequiredModules();
/* 316 */     for (modPos = 0; modPos < modInfo.length; modPos++) {
/* 318 */       String moduleName = modInfo[modPos].getModuleClass();
/* 319 */       SortModule reqMod = (SortModule)moduleMap.get(moduleName);
/* 320 */       if (reqMod.getPosition() >= position)
/* 322 */         position = reqMod.getPosition() + 1; 
/*     */     } 
/* 329 */     String subSystem = module.getSubSystem();
/* 330 */     Iterator it = moduleMap.values().iterator();
/* 331 */     while (it.hasNext()) {
/* 333 */       SortModule mod = it.next();
/* 335 */       if (mod.getState().getModule() == module)
/*     */         continue; 
/* 340 */       Module subSysMod = mod.getState().getModule();
/* 344 */       if (subSystem.equals(subSysMod.getSubSystem()))
/*     */         continue; 
/* 354 */       if (smodule.getDependSubsystems().contains(subSysMod.getSubSystem()))
/* 359 */         if (!isBaseModule(subSysMod, module))
/* 361 */           if (mod.getPosition() >= position)
/* 363 */             position = mod.getPosition() + 1;   
/*     */     } 
/* 368 */     return position;
/*     */   }
/*     */   
/*     */   private static boolean isBaseModule(Module mod, ModuleInfo mi) {
/* 381 */     ModuleInfo[] info = mod.getRequiredModules();
/*     */     int i;
/* 382 */     for (i = 0; i < info.length; i++) {
/* 384 */       if (info[i].getModuleClass().equals(mi.getModuleClass()))
/* 386 */         return true; 
/*     */     } 
/* 389 */     info = mod.getOptionalModules();
/* 390 */     for (i = 0; i < info.length; i++) {
/* 392 */       if (info[i].getModuleClass().equals(mi.getModuleClass()))
/* 394 */         return true; 
/*     */     } 
/* 397 */     return false;
/*     */   }
/*     */   
/*     */   private static ArrayList collectSubsystemModules(Module childMod, HashMap moduleMap) {
/* 411 */     ArrayList collector = new ArrayList();
/* 412 */     ModuleInfo[] info = childMod.getRequiredModules();
/*     */     int i;
/* 413 */     for (i = 0; i < info.length; i++) {
/* 415 */       SortModule dependentModule = (SortModule)moduleMap.get(info[i].getModuleClass());
/* 417 */       if (dependentModule == null) {
/* 419 */         Log.warn(new Log.SimpleMessage("A dependent module was not found in the list of known modules.", info[i].getModuleClass()));
/*     */       } else {
/* 426 */         collector.add(dependentModule.getState().getModule().getSubSystem());
/*     */       } 
/*     */     } 
/* 429 */     info = childMod.getOptionalModules();
/* 430 */     for (i = 0; i < info.length; i++) {
/* 432 */       Module dependentModule = (Module)moduleMap.get(info[i].getModuleClass());
/* 434 */       if (dependentModule == null) {
/* 436 */         Log.warn("A dependent module was not found in the list of known modules.");
/*     */       } else {
/* 439 */         collector.add(dependentModule.getSubSystem());
/*     */       } 
/*     */     } 
/* 441 */     return collector;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\PackageSorter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */