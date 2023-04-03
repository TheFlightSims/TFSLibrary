/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.registry.CollectionRegistryMode;
/*     */ import javax.media.jai.registry.RemoteRenderableRegistryMode;
/*     */ import javax.media.jai.registry.RemoteRenderedRegistryMode;
/*     */ import javax.media.jai.registry.RenderableCollectionRegistryMode;
/*     */ import javax.media.jai.registry.RenderableRegistryMode;
/*     */ import javax.media.jai.registry.RenderedRegistryMode;
/*     */ import javax.media.jai.registry.TileDecoderRegistryMode;
/*     */ import javax.media.jai.registry.TileEncoderRegistryMode;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class RegistryMode {
/*  67 */   private static Hashtable registryModes = new Hashtable(4);
/*     */   
/*  68 */   private static HashSet immutableNames = new HashSet();
/*     */   
/*     */   private CaselessStringKey name;
/*     */   
/*     */   private Class descriptorClass;
/*     */   
/*     */   private Class productClass;
/*     */   
/*     */   private Method factoryMethod;
/*     */   
/*     */   private boolean arePreferencesSupported;
/*     */   
/*     */   private boolean arePropertiesSupported;
/*     */   
/*     */   static {
/*  71 */     addMode((RegistryMode)new RenderedRegistryMode(), true);
/*  72 */     addMode((RegistryMode)new RenderableRegistryMode(), true);
/*  73 */     addMode((RegistryMode)new CollectionRegistryMode(), true);
/*  74 */     addMode((RegistryMode)new RenderableCollectionRegistryMode(), true);
/*  77 */     addMode((RegistryMode)new RemoteRenderedRegistryMode(), true);
/*  78 */     addMode((RegistryMode)new RemoteRenderableRegistryMode(), true);
/*  81 */     addMode((RegistryMode)new TileEncoderRegistryMode(), true);
/*  82 */     addMode((RegistryMode)new TileDecoderRegistryMode(), true);
/*     */   }
/*     */   
/*     */   private static boolean addMode(RegistryMode mode, boolean immutable) {
/*  92 */     if (registryModes.containsKey(mode.name))
/*  93 */       return false; 
/*  95 */     registryModes.put(mode.name, mode);
/*  97 */     if (immutable)
/*  98 */       immutableNames.add(mode.name); 
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public static synchronized boolean addMode(RegistryMode mode) {
/* 115 */     return addMode(mode, false);
/*     */   }
/*     */   
/*     */   public static synchronized boolean removeMode(String name) {
/* 131 */     CaselessStringKey key = new CaselessStringKey(name);
/* 133 */     if (immutableNames.contains(key))
/* 134 */       return false; 
/* 136 */     return (registryModes.remove(key) != null);
/*     */   }
/*     */   
/*     */   public static synchronized String[] getModeNames() {
/* 148 */     String[] names = new String[registryModes.size()];
/* 150 */     int i = 0;
/* 152 */     for (Enumeration e = registryModes.keys(); e.hasMoreElements(); ) {
/* 153 */       CaselessStringKey key = e.nextElement();
/* 155 */       names[i++] = key.getName();
/*     */     } 
/* 158 */     if (i <= 0)
/* 159 */       return null; 
/* 161 */     return names;
/*     */   }
/*     */   
/*     */   public static synchronized String[] getModeNames(Class descriptorClass) {
/* 177 */     String[] names = new String[registryModes.size()];
/* 179 */     int i = 0;
/* 181 */     for (Enumeration e = registryModes.elements(); e.hasMoreElements(); ) {
/* 182 */       RegistryMode mode = e.nextElement();
/* 184 */       if (mode.getDescriptorClass() == descriptorClass)
/* 185 */         names[i++] = mode.getName(); 
/*     */     } 
/* 188 */     if (i <= 0)
/* 189 */       return null; 
/* 191 */     String[] matchedNames = new String[i];
/* 193 */     for (int j = 0; j < i; j++)
/* 194 */       matchedNames[j] = names[j]; 
/* 196 */     return matchedNames;
/*     */   }
/*     */   
/*     */   public static RegistryMode getMode(String name) {
/* 204 */     CaselessStringKey key = new CaselessStringKey(name);
/* 206 */     return (RegistryMode)registryModes.get(key);
/*     */   }
/*     */   
/*     */   public static synchronized Set getDescriptorClasses() {
/* 214 */     HashSet set = new HashSet();
/* 216 */     for (Enumeration e = registryModes.elements(); e.hasMoreElements(); ) {
/* 217 */       RegistryMode mode = e.nextElement();
/* 219 */       set.add(mode.descriptorClass);
/*     */     } 
/* 222 */     return set;
/*     */   }
/*     */   
/*     */   protected RegistryMode(String name, Class descriptorClass, Class productClass, Method factoryMethod, boolean arePreferencesSupported, boolean arePropertiesSupported) {
/* 249 */     this.name = new CaselessStringKey(name);
/* 250 */     this.descriptorClass = descriptorClass;
/* 251 */     this.productClass = productClass;
/* 252 */     this.factoryMethod = factoryMethod;
/* 253 */     this.arePreferencesSupported = arePreferencesSupported;
/* 254 */     this.arePropertiesSupported = arePropertiesSupported;
/*     */   }
/*     */   
/*     */   public final String getName() {
/* 259 */     return this.name.getName();
/*     */   }
/*     */   
/*     */   public final Method getFactoryMethod() {
/* 264 */     return this.factoryMethod;
/*     */   }
/*     */   
/*     */   public final boolean arePreferencesSupported() {
/* 269 */     return this.arePreferencesSupported;
/*     */   }
/*     */   
/*     */   public final boolean arePropertiesSupported() {
/* 276 */     return this.arePropertiesSupported;
/*     */   }
/*     */   
/*     */   public final Class getDescriptorClass() {
/* 286 */     return this.descriptorClass;
/*     */   }
/*     */   
/*     */   public final Class getProductClass() {
/* 294 */     return this.productClass;
/*     */   }
/*     */   
/*     */   public final Class getFactoryClass() {
/* 302 */     return this.factoryMethod.getDeclaringClass();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */