/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.net.URL;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ class RegistryFileParser {
/*     */   private URL url;
/*     */   
/*     */   private InputStream is;
/*     */   
/*     */   private ClassLoader classLoader;
/*     */   
/*     */   private OperationRegistry or;
/*     */   
/*     */   private StreamTokenizer st;
/*     */   
/*     */   private int token;
/*     */   
/*     */   private int lineno;
/*     */   
/*     */   private Hashtable localNamesTable;
/*     */   
/*     */   static void loadOperationRegistry(OperationRegistry or, ClassLoader cl, InputStream is) throws IOException {
/*  47 */     (new RegistryFileParser(or, cl, is)).parseFile();
/*     */   }
/*     */   
/*     */   static void loadOperationRegistry(OperationRegistry or, ClassLoader cl, URL url) throws IOException {
/*  57 */     (new RegistryFileParser(or, cl, url)).parseFile();
/*     */   }
/*     */   
/*     */   private RegistryFileParser(OperationRegistry or, ClassLoader cl, URL url) throws IOException {
/*  84 */     this(or, cl, url.openStream());
/*  85 */     this.url = url;
/*     */   }
/*     */   
/*     */   private RegistryFileParser(OperationRegistry or, ClassLoader cl, InputStream is) throws IOException {
/* 513 */     this.headerLinePrinted = false;
/*     */     if (or == null)
/*     */       or = JAI.getDefaultInstance().getOperationRegistry(); 
/*     */     this.is = is;
/*     */     this.url = null;
/*     */     this.or = or;
/*     */     this.classLoader = cl;
/*     */     BufferedReader reader = new BufferedReader(new InputStreamReader(is));
/*     */     this.st = new StreamTokenizer(reader);
/*     */     this.st.commentChar(35);
/*     */     this.st.eolIsSignificant(true);
/*     */     this.st.slashSlashComments(true);
/*     */     this.st.slashStarComments(true);
/*     */     this.token = this.st.ttype;
/*     */     this.lineno = -1;
/*     */     this.localNamesTable = new Hashtable();
/*     */     String[] modeNames = RegistryMode.getModeNames();
/*     */     for (int i = 0; i < modeNames.length; i++)
/*     */       this.localNamesTable.put(new CaselessStringKey(modeNames[i]), new Hashtable()); 
/*     */   }
/*     */   
/*     */   private int skipEmptyTokens() throws IOException {
/*     */     while (this.st.sval == null) {
/*     */       if (this.token == -1)
/*     */         return this.token; 
/*     */       this.token = this.st.nextToken();
/*     */     } 
/*     */     return this.token;
/*     */   }
/*     */   
/*     */   private String[] getNextLine() throws IOException {
/*     */     if (skipEmptyTokens() == -1)
/*     */       return null; 
/*     */     Vector v = new Vector();
/*     */     this.lineno = this.st.lineno();
/*     */     while (this.token != 10 && this.token != -1) {
/*     */       if (this.st.sval != null)
/*     */         v.addElement(this.st.sval); 
/*     */       this.token = this.st.nextToken();
/*     */     } 
/*     */     if (v.size() == 0)
/*     */       return null; 
/*     */     return v.<String>toArray(new String[0]);
/*     */   }
/*     */   
/*     */   private static String[][] aliases = new String[][] { { "odesc", "descriptor" }, { "rif", "rendered" }, { "crif", "renderable" }, { "cif", "collection" } };
/*     */   
/*     */   private boolean headerLinePrinted;
/*     */   
/*     */   private String mapName(String key) {
/*     */     for (int i = 0; i < aliases.length; i++) {
/*     */       if (key.equalsIgnoreCase(aliases[i][0]))
/*     */         return aliases[i][1]; 
/*     */     } 
/*     */     return key;
/*     */   }
/*     */   
/*     */   private Object getInstance(String className) {
/*     */     try {
/*     */       Class descriptorClass = null;
/*     */       String errorMsg = null;
/*     */       if (this.classLoader != null)
/*     */         try {
/*     */           descriptorClass = Class.forName(className, true, this.classLoader);
/*     */         } catch (Exception e) {
/*     */           errorMsg = e.getMessage();
/*     */         }  
/*     */       if (descriptorClass == null)
/*     */         try {
/*     */           descriptorClass = Class.forName(className);
/*     */         } catch (Exception e) {
/*     */           errorMsg = e.getMessage();
/*     */         }  
/*     */       if (descriptorClass == null)
/*     */         try {
/*     */           descriptorClass = Class.forName(className, true, ClassLoader.getSystemClassLoader());
/*     */         } catch (Exception e) {
/*     */           errorMsg = e.getMessage();
/*     */         }  
/*     */       if (descriptorClass == null) {
/*     */         registryFileError(errorMsg);
/*     */         return null;
/*     */       } 
/*     */       return descriptorClass.newInstance();
/*     */     } catch (Exception e) {
/*     */       registryFileError(e.getMessage());
/*     */       e.printStackTrace();
/*     */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean parseFile() throws IOException {
/*     */     if (this.token == -1)
/*     */       return true; 
/*     */     this.token = this.st.nextToken();
/*     */     while (this.token != -1) {
/*     */       String[] keys;
/*     */       if ((keys = getNextLine()) == null)
/*     */         break; 
/*     */       String key = mapName(keys[0]);
/*     */       if (key.equalsIgnoreCase("registryMode")) {
/*     */         RegistryMode registryMode = (RegistryMode)getInstance(keys[1]);
/*     */         if (registryMode != null && !RegistryMode.addMode(registryMode))
/*     */           registryFileError(JaiI18N.getString("RegistryFileParser10")); 
/*     */         continue;
/*     */       } 
/*     */       if (key.equalsIgnoreCase("descriptor")) {
/*     */         registerDescriptor(keys);
/*     */         continue;
/*     */       } 
/*     */       RegistryMode mode;
/*     */       if ((mode = RegistryMode.getMode(key)) != null) {
/*     */         registerFactory(mode, keys);
/*     */         continue;
/*     */       } 
/*     */       if (key.equalsIgnoreCase("pref")) {
/*     */         key = mapName(keys[1]);
/*     */         if (key.equalsIgnoreCase("product")) {
/*     */           setProductPreference(RegistryMode.getMode("rendered"), keys);
/*     */           continue;
/*     */         } 
/*     */         if ((mode = RegistryMode.getMode(key)) != null) {
/*     */           setFactoryPreference(mode, keys);
/*     */           continue;
/*     */         } 
/*     */         registryFileError(JaiI18N.getString("RegistryFileParser4"));
/*     */         continue;
/*     */       } 
/*     */       if (key.equalsIgnoreCase("productPref")) {
/*     */         key = mapName(keys[1]);
/*     */         if ((mode = RegistryMode.getMode(key)) != null) {
/*     */           setProductPreference(mode, keys);
/*     */           continue;
/*     */         } 
/*     */         registryFileError(JaiI18N.getString("RegistryFileParser5"));
/*     */         continue;
/*     */       } 
/*     */       registryFileError(JaiI18N.getString("RegistryFileParser6"));
/*     */     } 
/*     */     if (this.url != null)
/*     */       this.is.close(); 
/*     */     return true;
/*     */   }
/*     */   
/*     */   private void registerDescriptor(String[] keys) {
/*     */     if (keys.length >= 2) {
/*     */       RegistryElementDescriptor red = (RegistryElementDescriptor)getInstance(keys[1]);
/*     */       if (red != null)
/*     */         try {
/*     */           this.or.registerDescriptor(red);
/*     */         } catch (Exception e) {
/*     */           registryFileError(e.getMessage());
/*     */         }  
/*     */     } else {
/*     */       registryFileError(JaiI18N.getString("RegistryFileParser1"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerFactory(RegistryMode mode, String[] keys) {
/*     */     if (mode.arePreferencesSupported()) {
/*     */       if (keys.length >= 5) {
/*     */         Object factory;
/*     */         if ((factory = getInstance(keys[1])) != null)
/*     */           try {
/*     */             this.or.registerFactory(mode.getName(), keys[3], keys[2], factory);
/*     */             mapLocalNameToObject(mode.getName(), keys[4], factory);
/*     */           } catch (Exception e) {
/*     */             registryFileError(e.getMessage());
/*     */           }  
/*     */       } else {
/*     */         registryFileError(JaiI18N.getString("RegistryFileParser2"));
/*     */       } 
/*     */     } else if (keys.length >= 3) {
/*     */       Object factory;
/*     */       if ((factory = getInstance(keys[1])) != null)
/*     */         try {
/*     */           this.or.registerFactory(mode.getName(), keys[2], null, factory);
/*     */         } catch (Exception e) {
/*     */           registryFileError(e.getMessage());
/*     */         }  
/*     */     } else {
/*     */       registryFileError(JaiI18N.getString("RegistryFileParser3"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setProductPreference(RegistryMode mode, String[] keys) {
/*     */     String modeName = mode.getName();
/*     */     if (mode.arePreferencesSupported()) {
/*     */       if (keys.length >= 5) {
/*     */         try {
/*     */           this.or.setProductPreference(modeName, keys[2], keys[3], keys[4]);
/*     */         } catch (Exception e) {
/*     */           registryFileError(e.getMessage());
/*     */         } 
/*     */       } else {
/*     */         registryFileError(JaiI18N.getString("RegistryFileParser5"));
/*     */       } 
/*     */     } else {
/*     */       registryFileError(JaiI18N.getString("RegistryFileParser9"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setFactoryPreference(RegistryMode mode, String[] keys) {
/*     */     String modeName = mode.getName();
/*     */     if (mode.arePreferencesSupported()) {
/*     */       if (keys.length >= 6) {
/*     */         Object preferred = getObjectFromLocalName(modeName, keys[4]);
/*     */         Object other = getObjectFromLocalName(modeName, keys[5]);
/*     */         if (preferred != null && other != null)
/*     */           try {
/*     */             this.or.setFactoryPreference(modeName, keys[2], keys[3], preferred, other);
/*     */           } catch (Exception e) {
/*     */             registryFileError(e.getMessage());
/*     */           }  
/*     */       } else {
/*     */         registryFileError(JaiI18N.getString("RegistryFileParser4"));
/*     */       } 
/*     */     } else {
/*     */       registryFileError(JaiI18N.getString("RegistryFileParser7"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void mapLocalNameToObject(String modeName, String localName, Object factory) {
/*     */     Hashtable modeTable = (Hashtable)this.localNamesTable.get(new CaselessStringKey(modeName));
/*     */     modeTable.put(new CaselessStringKey(localName), factory);
/*     */   }
/*     */   
/*     */   private Object getObjectFromLocalName(String modeName, String localName) {
/*     */     Hashtable modeTable = (Hashtable)this.localNamesTable.get(new CaselessStringKey(modeName));
/*     */     Object obj = modeTable.get(new CaselessStringKey(localName));
/*     */     if (obj == null)
/*     */       registryFileError(localName + ": " + JaiI18N.getString("RegistryFileParser8")); 
/*     */     return obj;
/*     */   }
/*     */   
/*     */   private void registryFileError(String msg) {
/* 520 */     if (!this.headerLinePrinted) {
/* 522 */       if (this.url != null)
/* 523 */         errorMsg(JaiI18N.getString("RegistryFileParser11"), new Object[] { this.url.getPath() }); 
/* 527 */       this.headerLinePrinted = true;
/*     */     } 
/* 530 */     errorMsg(JaiI18N.getString("RegistryFileParser0"), new Object[] { new Integer(this.lineno) });
/* 533 */     if (msg != null)
/* 534 */       errorMsg(msg, null); 
/*     */   }
/*     */   
/*     */   private void errorMsg(String key, Object[] args) {
/* 542 */     MessageFormat mf = new MessageFormat(key);
/* 543 */     mf.setLocale(Locale.getDefault());
/* 545 */     if (System.err != null)
/* 546 */       System.err.println(mf.format(args)); 
/*     */   }
/*     */   
/*     */   static void writeOperationRegistry(OperationRegistry or, OutputStream os) throws IOException {
/* 555 */     writeOperationRegistry(or, new BufferedWriter(new OutputStreamWriter(os)));
/*     */   }
/*     */   
/*     */   static void writeOperationRegistry(OperationRegistry or, BufferedWriter bw) throws IOException {
/* 566 */     Iterator dcit = RegistryMode.getDescriptorClasses().iterator();
/* 568 */     String tab = "  ";
/* 570 */     while (dcit.hasNext()) {
/* 572 */       Class descriptorClass = dcit.next();
/* 574 */       List descriptors = or.getDescriptors(descriptorClass);
/* 578 */       bw.write("#");
/* 578 */       bw.newLine();
/* 579 */       bw.write("# Descriptors corresponding to class : " + descriptorClass.getName());
/* 579 */       bw.newLine();
/* 580 */       bw.write("#");
/* 580 */       bw.newLine();
/* 582 */       if (descriptors == null || descriptors.size() <= 0) {
/* 583 */         bw.write("# <EMPTY>");
/* 583 */         bw.newLine();
/*     */       } else {
/* 586 */         Iterator it = descriptors.iterator();
/* 588 */         while (it.hasNext()) {
/* 589 */           bw.write("descriptor" + tab);
/* 590 */           bw.write(it.next().getClass().getName());
/* 591 */           bw.newLine();
/*     */         } 
/*     */       } 
/* 594 */       bw.newLine();
/* 600 */       String[] modeNames = RegistryMode.getModeNames(descriptorClass);
/* 605 */       for (int i = 0; i < modeNames.length; i++) {
/* 606 */         bw.write("#");
/* 606 */         bw.newLine();
/* 607 */         bw.write("# Factories registered under mode : " + modeNames[i]);
/* 607 */         bw.newLine();
/* 608 */         bw.write("#");
/* 608 */         bw.newLine();
/* 610 */         RegistryMode mode = RegistryMode.getMode(modeNames[i]);
/* 612 */         boolean prefs = mode.arePreferencesSupported();
/* 614 */         String[] descriptorNames = or.getDescriptorNames(modeNames[i]);
/*     */         boolean empty;
/*     */         int j;
/* 618 */         for (j = 0, empty = true; j < descriptorNames.length; j++) {
/* 620 */           if (prefs) {
/* 621 */             Vector productVector = or.getOrderedProductList(modeNames[i], descriptorNames[j]);
/* 625 */             if (productVector != null) {
/* 628 */               String[] productNames = (String[])productVector.toArray((Object[])new String[0]);
/* 633 */               for (int k = 0; k < productNames.length; k++) {
/* 635 */                 List factoryList = or.getOrderedFactoryList(modeNames[i], descriptorNames[j], productNames[k]);
/* 640 */                 Iterator fit = factoryList.iterator();
/* 642 */                 while (fit.hasNext()) {
/* 643 */                   Object instance = fit.next();
/* 645 */                   if (instance == null)
/*     */                     continue; 
/* 648 */                   bw.write(modeNames[i] + tab);
/* 649 */                   bw.write(instance.getClass().getName() + tab);
/* 650 */                   bw.write(productNames[k] + tab);
/* 651 */                   bw.write(descriptorNames[j] + tab);
/* 652 */                   bw.write(or.getLocalName(modeNames[i], instance));
/* 653 */                   bw.newLine();
/* 655 */                   empty = false;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } else {
/* 659 */             Iterator fit = or.getFactoryIterator(modeNames[i], descriptorNames[j]);
/* 662 */             while (fit.hasNext()) {
/* 663 */               Object instance = fit.next();
/* 665 */               if (instance == null)
/*     */                 continue; 
/* 668 */               bw.write(modeNames[i] + tab);
/* 669 */               bw.write(instance.getClass().getName() + tab);
/* 670 */               bw.write(descriptorNames[j]);
/* 671 */               bw.newLine();
/* 673 */               empty = false;
/*     */             } 
/*     */           } 
/*     */         } 
/* 678 */         if (empty) {
/* 679 */           bw.write("# <EMPTY>");
/* 679 */           bw.newLine();
/*     */         } 
/* 681 */         bw.newLine();
/* 685 */         if (!prefs) {
/* 686 */           bw.write("#");
/* 686 */           bw.newLine();
/* 687 */           bw.write("# Preferences not supported for mode : " + modeNames[i]);
/* 687 */           bw.newLine();
/* 688 */           bw.write("#");
/* 688 */           bw.newLine();
/* 689 */           bw.newLine();
/*     */         } else {
/* 694 */           bw.write("#");
/* 694 */           bw.newLine();
/* 695 */           bw.write("# Product preferences for mode : " + modeNames[i]);
/* 695 */           bw.newLine();
/* 696 */           bw.write("#");
/* 696 */           bw.newLine();
/* 698 */           for (j = 0, empty = true; j < descriptorNames.length; j++) {
/* 700 */             String[][] productPrefs = or.getProductPreferences(modeNames[i], descriptorNames[j]);
/* 703 */             if (productPrefs != null)
/* 706 */               for (int k = 0; k < productPrefs.length; k++) {
/* 707 */                 bw.write("productPref" + tab);
/* 708 */                 bw.write(modeNames[i] + tab);
/* 709 */                 bw.write(descriptorNames[j] + tab);
/* 710 */                 bw.write(productPrefs[k][0] + tab);
/* 711 */                 bw.write(productPrefs[k][1]);
/* 712 */                 bw.newLine();
/* 714 */                 empty = false;
/*     */               }  
/*     */           } 
/* 718 */           if (empty) {
/* 719 */             bw.write("# <EMPTY>");
/* 719 */             bw.newLine();
/*     */           } 
/* 721 */           bw.newLine();
/* 724 */           bw.write("#");
/* 724 */           bw.newLine();
/* 725 */           bw.write("# Factory preferences for mode : " + modeNames[i]);
/* 725 */           bw.newLine();
/* 726 */           bw.write("#");
/* 726 */           bw.newLine();
/* 729 */           for (j = 0, empty = true; j < descriptorNames.length; j++) {
/* 731 */             if (prefs) {
/* 732 */               Vector productVector = or.getOrderedProductList(modeNames[i], descriptorNames[j]);
/* 736 */               if (productVector != null) {
/* 739 */                 String[] productNames = (String[])productVector.toArray((Object[])new String[0]);
/* 744 */                 for (int k = 0; k < productNames.length; k++) {
/* 746 */                   Object[][] fprefs = or.getFactoryPreferences(modeNames[i], descriptorNames[j], productNames[k]);
/* 749 */                   if (fprefs != null)
/* 752 */                     for (int l = 0; l < fprefs.length; l++) {
/* 753 */                       bw.write("pref" + tab);
/* 754 */                       bw.write(modeNames[i] + tab);
/* 755 */                       bw.write(descriptorNames[j] + tab);
/* 756 */                       bw.write(productNames[k] + tab);
/* 757 */                       bw.write(or.getLocalName(modeNames[i], fprefs[l][0]) + tab);
/* 758 */                       bw.write(or.getLocalName(modeNames[i], fprefs[l][1]));
/* 759 */                       bw.newLine();
/* 761 */                       empty = false;
/*     */                     }  
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 767 */           if (empty) {
/* 768 */             bw.write("# <EMPTY>");
/* 768 */             bw.newLine();
/*     */           } 
/* 770 */           bw.newLine();
/*     */         } 
/*     */       } 
/*     */     } 
/* 774 */     bw.flush();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RegistryFileParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */