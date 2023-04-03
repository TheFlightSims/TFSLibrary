/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.URL;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.KeyStroke;
/*     */ 
/*     */ public class ResourceBundleSupport {
/*     */   private ResourceBundle resources;
/*     */   
/*     */   private TreeMap cache;
/*     */   
/*     */   private TreeSet lookupPath;
/*     */   
/*     */   private String resourceBase;
/*     */   
/*     */   private Locale locale;
/*     */   
/*     */   public ResourceBundleSupport(Locale locale, String baseName) {
/* 108 */     this(locale, ResourceBundle.getBundle(baseName, locale), baseName);
/*     */   }
/*     */   
/*     */   protected ResourceBundleSupport(Locale locale, ResourceBundle resourceBundle, String baseName) {
/* 121 */     if (locale == null)
/* 122 */       throw new NullPointerException("Locale must not be null"); 
/* 124 */     if (resourceBundle == null)
/* 125 */       throw new NullPointerException("Resources must not be null"); 
/* 127 */     if (baseName == null)
/* 128 */       throw new NullPointerException("BaseName must not be null"); 
/* 130 */     this.locale = locale;
/* 131 */     this.resources = resourceBundle;
/* 132 */     this.resourceBase = baseName;
/* 133 */     this.cache = new TreeMap();
/* 134 */     this.lookupPath = new TreeSet();
/*     */   }
/*     */   
/*     */   public ResourceBundleSupport(Locale locale, ResourceBundle resourceBundle) {
/* 144 */     this(locale, resourceBundle, resourceBundle.toString());
/*     */   }
/*     */   
/*     */   public ResourceBundleSupport(String baseName) {
/* 153 */     this(Locale.getDefault(), ResourceBundle.getBundle(baseName), baseName);
/*     */   }
/*     */   
/*     */   protected ResourceBundleSupport(ResourceBundle resourceBundle, String baseName) {
/* 164 */     this(Locale.getDefault(), resourceBundle, baseName);
/*     */   }
/*     */   
/*     */   public ResourceBundleSupport(ResourceBundle resourceBundle) {
/* 173 */     this(Locale.getDefault(), resourceBundle, resourceBundle.toString());
/*     */   }
/*     */   
/*     */   protected final String getResourceBase() {
/* 182 */     return this.resourceBase;
/*     */   }
/*     */   
/*     */   public synchronized String getString(String key) {
/* 198 */     String retval = (String)this.cache.get(key);
/* 199 */     if (retval != null)
/* 200 */       return retval; 
/* 202 */     this.lookupPath.clear();
/* 203 */     return internalGetString(key);
/*     */   }
/*     */   
/*     */   protected String internalGetString(String key) {
/* 214 */     if (this.lookupPath.contains(key))
/* 215 */       throw new MissingResourceException("InfiniteLoop in resource lookup", getResourceBase(), this.lookupPath.toString()); 
/* 219 */     String fromResBundle = this.resources.getString(key);
/* 220 */     if (fromResBundle.startsWith("@@")) {
/* 222 */       int idx = fromResBundle.indexOf('@', 2);
/* 223 */       if (idx == -1)
/* 224 */         throw new MissingResourceException("Invalid format for global lookup key.", getResourceBase(), key); 
/*     */       try {
/* 228 */         ResourceBundle res = ResourceBundle.getBundle(fromResBundle.substring(2, idx));
/* 230 */         return res.getString(fromResBundle.substring(idx + 1));
/* 232 */       } catch (Exception e) {
/* 233 */         Log.error("Error during global lookup", e);
/* 234 */         throw new MissingResourceException("Error during global lookup", getResourceBase(), key);
/*     */       } 
/*     */     } 
/* 238 */     if (fromResBundle.startsWith("@")) {
/* 240 */       String newKey = fromResBundle.substring(1);
/* 241 */       this.lookupPath.add(key);
/* 242 */       String retval = internalGetString(newKey);
/* 244 */       this.cache.put(key, retval);
/* 245 */       return retval;
/*     */     } 
/* 248 */     this.cache.put(key, fromResBundle);
/* 249 */     return fromResBundle;
/*     */   }
/*     */   
/*     */   public Icon getIcon(String key, boolean large) {
/* 261 */     String name = getString(key);
/* 262 */     return createIcon(name, true, large);
/*     */   }
/*     */   
/*     */   public Icon getIcon(String key) {
/* 272 */     String name = getString(key);
/* 273 */     return createIcon(name, false, false);
/*     */   }
/*     */   
/*     */   public Integer getMnemonic(String key) {
/* 292 */     String name = getString(key);
/* 293 */     return createMnemonic(name);
/*     */   }
/*     */   
/*     */   public KeyStroke getKeyStroke(String key) {
/* 317 */     return getKeyStroke(key, getMenuKeyMask());
/*     */   }
/*     */   
/*     */   public KeyStroke getKeyStroke(String key, int mask) {
/* 341 */     String name = getString(key);
/* 342 */     return KeyStroke.getKeyStroke(createMnemonic(name).intValue(), mask);
/*     */   }
/*     */   
/*     */   public JMenu createMenu(String keyPrefix) {
/* 365 */     JMenu retval = new JMenu();
/* 366 */     retval.setText(getString(keyPrefix + ".name"));
/* 367 */     retval.setMnemonic(getMnemonic(keyPrefix + ".mnemonic").intValue());
/* 368 */     return retval;
/*     */   }
/*     */   
/*     */   public URL getResourceURL(String key) {
/* 386 */     String name = getString(key);
/* 387 */     URL in = Thread.currentThread().getContextClassLoader().getResource(name);
/* 388 */     if (in == null)
/* 389 */       Log.warn("Unable to find file in the class path: " + name + "; key=" + key); 
/* 391 */     return in;
/*     */   }
/*     */   
/*     */   private ImageIcon createIcon(String resourceName, boolean scale, boolean large) {
/* 407 */     URL in = Thread.currentThread().getContextClassLoader().getResource(resourceName);
/* 408 */     if (in == null) {
/* 409 */       Log.warn("Unable to find file in the class path: " + resourceName);
/* 410 */       return new ImageIcon(createTransparentImage(1, 1));
/*     */     } 
/* 412 */     Image img = Toolkit.getDefaultToolkit().createImage(in);
/* 413 */     if (img == null) {
/* 414 */       Log.warn("Unable to instantiate the image: " + resourceName);
/* 415 */       return new ImageIcon(createTransparentImage(1, 1));
/*     */     } 
/* 417 */     if (scale) {
/* 418 */       if (large)
/* 419 */         return new ImageIcon(img.getScaledInstance(24, 24, 4)); 
/* 421 */       return new ImageIcon(img.getScaledInstance(16, 16, 4));
/*     */     } 
/* 423 */     return new ImageIcon(img);
/*     */   }
/*     */   
/*     */   private Integer createMnemonic(String keyString) {
/* 434 */     if (keyString == null)
/* 435 */       throw new NullPointerException("Key is null."); 
/* 437 */     if (keyString.length() == 0)
/* 438 */       throw new IllegalArgumentException("Key is empty."); 
/* 440 */     int character = keyString.charAt(0);
/* 441 */     if (keyString.startsWith("VK_"))
/*     */       try {
/* 443 */         Field f = KeyEvent.class.getField(keyString);
/* 444 */         Integer keyCode = (Integer)f.get(null);
/* 445 */         character = keyCode.intValue();
/* 447 */       } catch (Exception nsfe) {} 
/* 451 */     return new Integer(character);
/*     */   }
/*     */   
/*     */   private int getMenuKeyMask() {
/*     */     try {
/* 461 */       return Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
/* 463 */     } catch (UnsupportedOperationException he) {
/* 466 */       return 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   private BufferedImage createTransparentImage(int width, int height) {
/* 478 */     BufferedImage img = new BufferedImage(width, height, 2);
/* 479 */     int[] data = img.getRGB(0, 0, width, height, null, 0, width);
/* 480 */     Arrays.fill(data, 0);
/* 481 */     img.setRGB(0, 0, width, height, data, 0, width);
/* 482 */     return img;
/*     */   }
/*     */   
/*     */   public Icon createTransparentIcon(int width, int height) {
/* 493 */     return new ImageIcon(createTransparentImage(width, height));
/*     */   }
/*     */   
/*     */   public String formatMessage(String key, Object parameter) {
/* 504 */     return formatMessage(getString(key), new Object[] { parameter });
/*     */   }
/*     */   
/*     */   public String formatMessage(String key, Object par1, Object par2) {
/* 518 */     return formatMessage(getString(key), new Object[] { par1, par2 });
/*     */   }
/*     */   
/*     */   public String formatMessage(String key, Object[] parameters) {
/* 529 */     MessageFormat format = new MessageFormat(getString(key));
/* 530 */     format.setLocale(getLocale());
/* 531 */     return format.format(parameters);
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 539 */     return this.locale;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ResourceBundleSupport.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */