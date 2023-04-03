/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import com.sun.media.jai.util.Service;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderedImageFactory;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.io.OutputStream;
/*      */ import java.io.StringWriter;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.registry.CIFRegistry;
/*      */ import javax.media.jai.registry.CRIFRegistry;
/*      */ import javax.media.jai.registry.RIFRegistry;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class OperationRegistry implements Externalizable {
/*  233 */   static String JAI_REGISTRY_FILE = "META-INF/javax.media.jai.registryFile.jai";
/*      */   
/*  236 */   static String USR_REGISTRY_FILE = "META-INF/registryFile.jai";
/*      */   
/*      */   private Hashtable descriptors;
/*      */   
/*      */   private Hashtable factories;
/*      */   
/*      */   private FactoryCache getFactoryCache(String modeName) {
/*  257 */     CaselessStringKey key = new CaselessStringKey(modeName);
/*  259 */     FactoryCache fc = (FactoryCache)this.factories.get(key);
/*  261 */     if (fc == null)
/*  263 */       if (RegistryMode.getMode(modeName) != null) {
/*  264 */         this.factories.put(key, fc = new FactoryCache(modeName));
/*      */       } else {
/*  267 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry0", new Object[] { modeName }));
/*      */       }  
/*  272 */     return fc;
/*      */   }
/*      */   
/*      */   private DescriptorCache getDescriptorCache(String modeName) {
/*  282 */     CaselessStringKey key = new CaselessStringKey(modeName);
/*  284 */     DescriptorCache dc = (DescriptorCache)this.descriptors.get(key);
/*  286 */     if (dc == null)
/*  288 */       if (RegistryMode.getMode(modeName) != null) {
/*  289 */         this.descriptors.put(key, dc = new DescriptorCache(modeName));
/*      */       } else {
/*  292 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry0", new Object[] { modeName }));
/*      */       }  
/*  297 */     return dc;
/*      */   }
/*      */   
/*      */   private void initialize() {
/*  310 */     this.descriptors = new Hashtable();
/*  314 */     this.factories = new Hashtable();
/*      */   }
/*      */   
/*      */   public OperationRegistry() {
/*  325 */     initialize();
/*      */   }
/*      */   
/*      */   public static OperationRegistry getThreadSafeOperationRegistry() {
/*  338 */     return new ThreadSafeOperationRegistry();
/*      */   }
/*      */   
/*      */   static OperationRegistry initializeRegistry() {
/*      */     try {
/*  354 */       InputStream url = PropertyUtil.getFileFromClasspath(JAI_REGISTRY_FILE);
/*  356 */       if (url == null)
/*  357 */         throw new RuntimeException(JaiI18N.getString("OperationRegistry1")); 
/*  360 */       OperationRegistry registry = new ThreadSafeOperationRegistry();
/*  362 */       if (url != null)
/*  363 */         RegistryFileParser.loadOperationRegistry(registry, (ClassLoader)null, url); 
/*  365 */       registry.registerServices(null);
/*  366 */       return registry;
/*  368 */     } catch (IOException ioe) {
/*  369 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/*  371 */       String message = JaiI18N.getString("OperationRegistry2");
/*  372 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, ioe), OperationRegistry.class, false);
/*  375 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/*  390 */     StringWriter sw = new StringWriter();
/*      */     try {
/*  393 */       RegistryFileParser.writeOperationRegistry(this, new BufferedWriter(sw));
/*  395 */       return sw.getBuffer().toString();
/*  397 */     } catch (Exception e) {
/*  398 */       return "\n[ERROR!] " + e.getMessage();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeToStream(OutputStream out) throws IOException {
/*  416 */     if (out == null)
/*  417 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  419 */     RegistryFileParser.writeOperationRegistry(this, out);
/*      */   }
/*      */   
/*      */   public void initializeFromStream(InputStream in) throws IOException {
/*  446 */     if (in == null)
/*  447 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  449 */     initialize();
/*  450 */     updateFromStream(in);
/*      */   }
/*      */   
/*      */   public void updateFromStream(InputStream in) throws IOException {
/*  480 */     if (in == null)
/*  481 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  483 */     RegistryFileParser.loadOperationRegistry(this, (ClassLoader)null, in);
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  512 */     if (in == null)
/*  513 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  515 */     byte[] barray = (byte[])in.readObject();
/*  516 */     InputStream s = new ByteArrayInputStream(barray);
/*  517 */     initializeFromStream(s);
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/*  602 */     if (out == null)
/*  603 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  605 */     ByteArrayOutputStream bstream = new ByteArrayOutputStream();
/*  606 */     writeToStream(bstream);
/*  607 */     out.writeObject(bstream.toByteArray());
/*      */   }
/*      */   
/*      */   public void removeRegistryMode(String modeName) {
/*  632 */     if (getDescriptorCache(modeName) != null)
/*  633 */       this.descriptors.remove(new CaselessStringKey(modeName)); 
/*  635 */     if (getFactoryCache(modeName) != null)
/*  636 */       this.factories.remove(new CaselessStringKey(modeName)); 
/*      */   }
/*      */   
/*      */   public String[] getRegistryModes() {
/*  648 */     Enumeration e = this.descriptors.keys();
/*  649 */     int size = this.descriptors.size();
/*  650 */     String[] names = new String[size];
/*  652 */     for (int i = 0; i < size; i++) {
/*  653 */       CaselessStringKey key = e.nextElement();
/*  654 */       names[i] = key.getName();
/*      */     } 
/*  657 */     return names;
/*      */   }
/*      */   
/*      */   public void registerDescriptor(RegistryElementDescriptor descriptor) {
/*  686 */     if (descriptor == null)
/*  687 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  689 */     String[] supportedModes = descriptor.getSupportedModes();
/*  691 */     String descriptorName = descriptor.getName();
/*      */     int i;
/*  695 */     for (i = 0; i < supportedModes.length; i++) {
/*  696 */       if (RegistryMode.getMode(supportedModes[i]) == null)
/*  697 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry3", new Object[] { descriptorName, supportedModes[i] })); 
/*      */     } 
/*  703 */     for (i = 0; i < supportedModes.length; i++) {
/*  705 */       DescriptorCache dc = getDescriptorCache(supportedModes[i]);
/*  707 */       dc.addDescriptor(descriptor);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void unregisterDescriptor(RegistryElementDescriptor descriptor) {
/*  730 */     if (descriptor == null)
/*  731 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  733 */     String descriptorName = descriptor.getName();
/*  735 */     String[] supportedModes = descriptor.getSupportedModes();
/*      */     int i;
/*  739 */     for (i = 0; i < supportedModes.length; i++) {
/*  740 */       if (RegistryMode.getMode(supportedModes[i]) == null)
/*  741 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry3", new Object[] { descriptorName, supportedModes[i] })); 
/*      */     } 
/*  747 */     for (i = 0; i < supportedModes.length; i++) {
/*  749 */       DescriptorCache dc = getDescriptorCache(supportedModes[i]);
/*  751 */       dc.removeDescriptor(descriptor);
/*      */     } 
/*      */   }
/*      */   
/*      */   public RegistryElementDescriptor getDescriptor(Class descriptorClass, String descriptorName) {
/*  780 */     if (descriptorClass == null || descriptorName == null)
/*  781 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  783 */     String[] supportedModes = RegistryMode.getModeNames(descriptorClass);
/*  785 */     if (supportedModes == null)
/*  786 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry4", new Object[] { descriptorClass.getName() })); 
/*  793 */     for (int i = 0; i < supportedModes.length; i++) {
/*  795 */       DescriptorCache dc = getDescriptorCache(supportedModes[i]);
/*      */       RegistryElementDescriptor red;
/*  797 */       if ((red = dc.getDescriptor(descriptorName)) != null)
/*  798 */         return red; 
/*      */     } 
/*  801 */     return null;
/*      */   }
/*      */   
/*      */   public List getDescriptors(Class descriptorClass) {
/*  821 */     if (descriptorClass == null)
/*  822 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  824 */     String[] supportedModes = RegistryMode.getModeNames(descriptorClass);
/*  826 */     if (supportedModes == null)
/*  827 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry4", new Object[] { descriptorClass.getName() })); 
/*  832 */     HashSet set = new HashSet();
/*  835 */     for (int i = 0; i < supportedModes.length; i++) {
/*  837 */       DescriptorCache dc = getDescriptorCache(supportedModes[i]);
/*      */       List list;
/*  839 */       if ((list = dc.getDescriptors()) != null)
/*  840 */         set.addAll(list); 
/*      */     } 
/*  843 */     return new ArrayList(set);
/*      */   }
/*      */   
/*      */   public String[] getDescriptorNames(Class descriptorClass) {
/*  863 */     List dlist = getDescriptors(descriptorClass);
/*  865 */     if (dlist != null) {
/*  867 */       Iterator diter = dlist.iterator();
/*  869 */       String[] names = new String[dlist.size()];
/*  870 */       int i = 0;
/*  872 */       while (diter.hasNext()) {
/*  873 */         RegistryElementDescriptor red = diter.next();
/*  876 */         names[i++] = red.getName();
/*      */       } 
/*  879 */       return names;
/*      */     } 
/*  882 */     return null;
/*      */   }
/*      */   
/*      */   public RegistryElementDescriptor getDescriptor(String modeName, String descriptorName) {
/*  907 */     DescriptorCache dc = getDescriptorCache(modeName);
/*  909 */     if (dc != null)
/*  910 */       return dc.getDescriptor(descriptorName); 
/*  912 */     return null;
/*      */   }
/*      */   
/*      */   public List getDescriptors(String modeName) {
/*  928 */     DescriptorCache dc = getDescriptorCache(modeName);
/*  930 */     if (dc != null)
/*  931 */       return dc.getDescriptors(); 
/*  933 */     return null;
/*      */   }
/*      */   
/*      */   public String[] getDescriptorNames(String modeName) {
/*  949 */     DescriptorCache dc = getDescriptorCache(modeName);
/*  951 */     if (dc != null)
/*  952 */       return dc.getDescriptorNames(); 
/*  954 */     return null;
/*      */   }
/*      */   
/*      */   public void setProductPreference(String modeName, String descriptorName, String preferredProductName, String otherProductName) {
/*  991 */     DescriptorCache dc = getDescriptorCache(modeName);
/*  993 */     if (dc != null)
/*  994 */       dc.setProductPreference(descriptorName, preferredProductName, otherProductName); 
/*      */   }
/*      */   
/*      */   public void unsetProductPreference(String modeName, String descriptorName, String preferredProductName, String otherProductName) {
/* 1027 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1029 */     if (dc != null)
/* 1030 */       dc.unsetProductPreference(descriptorName, preferredProductName, otherProductName); 
/*      */   }
/*      */   
/*      */   public void clearProductPreferences(String modeName, String descriptorName) {
/* 1053 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1055 */     if (dc != null)
/* 1056 */       dc.clearProductPreferences(descriptorName); 
/*      */   }
/*      */   
/*      */   public String[][] getProductPreferences(String modeName, String descriptorName) {
/* 1080 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1082 */     if (dc != null)
/* 1083 */       return dc.getProductPreferences(descriptorName); 
/* 1085 */     return (String[][])null;
/*      */   }
/*      */   
/*      */   public Vector getOrderedProductList(String modeName, String descriptorName) {
/* 1114 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1116 */     if (dc != null)
/* 1117 */       return dc.getOrderedProductList(descriptorName); 
/* 1119 */     return null;
/*      */   }
/*      */   
/*      */   String getLocalName(String modeName, Object factoryInstance) {
/* 1135 */     FactoryCache fc = getFactoryCache(modeName);
/* 1137 */     if (fc != null)
/* 1138 */       return fc.getLocalName(factoryInstance); 
/* 1140 */     return null;
/*      */   }
/*      */   
/*      */   public void registerFactory(String modeName, String descriptorName, String productName, Object factory) {
/* 1169 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1170 */     FactoryCache fc = getFactoryCache(modeName);
/* 1172 */     if (dc.getDescriptor(descriptorName) == null)
/* 1173 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1178 */     if (factory == null)
/* 1179 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1182 */     if (dc.arePreferencesSupported) {
/* 1184 */       OperationGraph og = dc.addProduct(descriptorName, productName);
/* 1187 */       if (og == null)
/* 1188 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1193 */       og.addOp(new PartialOrderNode(factory, factory.getClass().getName()));
/*      */     } 
/* 1197 */     fc.addFactory(descriptorName, productName, factory);
/*      */   }
/*      */   
/*      */   public void unregisterFactory(String modeName, String descriptorName, String productName, Object factory) {
/* 1229 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1230 */     FactoryCache fc = getFactoryCache(modeName);
/* 1232 */     if (dc.getDescriptor(descriptorName) == null)
/* 1233 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1238 */     if (factory == null)
/* 1239 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1242 */     fc.removeFactory(descriptorName, productName, factory);
/* 1244 */     if (dc.arePreferencesSupported) {
/* 1246 */       OperationGraph og = dc.lookupProduct(descriptorName, productName);
/* 1249 */       if (og == null)
/* 1250 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1255 */       og.removeOp(factory);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setFactoryPreference(String modeName, String descriptorName, String productName, Object preferredOp, Object otherOp) {
/* 1290 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1291 */     FactoryCache fc = getFactoryCache(modeName);
/* 1293 */     if (dc.getDescriptor(descriptorName) == null)
/* 1294 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1301 */     fc.setPreference(descriptorName, productName, preferredOp, otherOp);
/* 1304 */     if (dc.arePreferencesSupported) {
/* 1306 */       OperationGraph og = dc.lookupProduct(descriptorName, productName);
/* 1309 */       if (og == null)
/* 1310 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1315 */       og.setPreference(preferredOp, otherOp);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void unsetFactoryPreference(String modeName, String descriptorName, String productName, Object preferredOp, Object otherOp) {
/* 1350 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1351 */     FactoryCache fc = getFactoryCache(modeName);
/* 1353 */     if (dc.getDescriptor(descriptorName) == null)
/* 1354 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1361 */     fc.unsetPreference(descriptorName, productName, preferredOp, otherOp);
/* 1364 */     if (dc.arePreferencesSupported) {
/* 1366 */       OperationGraph og = dc.lookupProduct(descriptorName, productName);
/* 1369 */       if (og == null)
/* 1370 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1375 */       og.unsetPreference(preferredOp, otherOp);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clearFactoryPreferences(String modeName, String descriptorName, String productName) {
/* 1402 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1403 */     FactoryCache fc = getFactoryCache(modeName);
/* 1405 */     if (dc.getDescriptor(descriptorName) == null)
/* 1406 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1411 */     Object[][] prefs = fc.getPreferences(descriptorName, productName);
/* 1413 */     if (prefs != null) {
/* 1415 */       OperationGraph og = dc.lookupProduct(descriptorName, productName);
/* 1418 */       if (og == null)
/* 1419 */         throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1424 */       for (int i = 0; i < prefs.length; i++)
/* 1425 */         og.unsetPreference(prefs[i][0], prefs[i][1]); 
/*      */     } 
/* 1429 */     fc.clearPreferences(descriptorName, productName);
/*      */   }
/*      */   
/*      */   public Object[][] getFactoryPreferences(String modeName, String descriptorName, String productName) {
/* 1455 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1456 */     FactoryCache fc = getFactoryCache(modeName);
/* 1458 */     if (dc.getDescriptor(descriptorName) == null)
/* 1459 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1464 */     return fc.getPreferences(descriptorName, productName);
/*      */   }
/*      */   
/*      */   public List getOrderedFactoryList(String modeName, String descriptorName, String productName) {
/* 1499 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1500 */     FactoryCache fc = getFactoryCache(modeName);
/* 1502 */     if (dc.getDescriptor(descriptorName) == null)
/* 1503 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1508 */     if (dc.arePreferencesSupported) {
/* 1510 */       OperationGraph og = dc.lookupProduct(descriptorName, productName);
/* 1513 */       if (og == null)
/* 1514 */         return null; 
/* 1516 */       Vector v = og.getOrderedOperationList();
/* 1518 */       if (v == null || v.size() <= 0)
/* 1519 */         return null; 
/* 1521 */       ArrayList list = new ArrayList(v.size());
/* 1523 */       for (int i = 0; i < v.size(); i++)
/* 1524 */         list.add(((PartialOrderNode)v.elementAt(i)).getData()); 
/* 1527 */       return list;
/*      */     } 
/* 1530 */     return fc.getFactoryList(descriptorName, productName);
/*      */   }
/*      */   
/*      */   public Iterator getFactoryIterator(String modeName, String descriptorName) {
/* 1563 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1564 */     FactoryCache fc = getFactoryCache(modeName);
/* 1566 */     if (dc.getDescriptor(descriptorName) == null)
/* 1567 */       throw new IllegalArgumentException(JaiI18N.formatMsg("OperationRegistry5", new Object[] { descriptorName, modeName })); 
/* 1572 */     if (dc.arePreferencesSupported) {
/* 1573 */       Vector v = getOrderedProductList(modeName, descriptorName);
/* 1575 */       if (v == null || v.size() <= 0)
/* 1576 */         return null; 
/* 1578 */       ArrayList arrayList = new ArrayList();
/* 1582 */       for (int i = 0; i < v.size(); i++) {
/* 1583 */         List plist = getOrderedFactoryList(modeName, descriptorName, v.get(i));
/* 1585 */         if (plist != null)
/* 1586 */           arrayList.addAll(plist); 
/*      */       } 
/* 1589 */       return arrayList.iterator();
/*      */     } 
/* 1592 */     List list = fc.getFactoryList(descriptorName, null);
/* 1594 */     if (list != null)
/* 1595 */       return list.iterator(); 
/* 1598 */     return null;
/*      */   }
/*      */   
/*      */   public Object getFactory(String modeName, String descriptorName) {
/* 1624 */     Iterator it = getFactoryIterator(modeName, descriptorName);
/* 1626 */     if (it != null && it.hasNext())
/* 1627 */       return it.next(); 
/* 1629 */     return null;
/*      */   }
/*      */   
/*      */   public Object invokeFactory(String modeName, String descriptorName, Object[] args) {
/* 1658 */     Iterator it = getFactoryIterator(modeName, descriptorName);
/* 1660 */     if (it == null)
/* 1661 */       return null; 
/* 1663 */     FactoryCache fc = getFactoryCache(modeName);
/* 1664 */     ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 1666 */     Exception savedOne = null;
/* 1668 */     while (it.hasNext()) {
/* 1670 */       Object factory = it.next();
/*      */       try {
/*      */         Object obj;
/* 1674 */         if ((obj = fc.invoke(factory, args)) != null)
/* 1675 */           return obj; 
/* 1676 */         savedOne = null;
/* 1677 */       } catch (Exception e) {
/* 1678 */         listener.errorOccurred(JaiI18N.getString("OperationRegistry6") + " \"" + descriptorName + "\"", e, this, false);
/* 1681 */         savedOne = e;
/*      */       } 
/*      */     } 
/* 1686 */     if (savedOne != null)
/* 1687 */       throw new ImagingException(JaiI18N.getString("OperationRegistry7") + " \"" + descriptorName + "\"", savedOne); 
/* 1691 */     return null;
/*      */   }
/*      */   
/*      */   public void addPropertyGenerator(String modeName, String descriptorName, PropertyGenerator generator) {
/* 1730 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1732 */     if (dc != null)
/* 1733 */       dc.addPropertyGenerator(descriptorName, generator); 
/*      */   }
/*      */   
/*      */   public void removePropertyGenerator(String modeName, String descriptorName, PropertyGenerator generator) {
/* 1762 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1764 */     if (dc != null)
/* 1765 */       dc.removePropertyGenerator(descriptorName, generator); 
/*      */   }
/*      */   
/*      */   public void copyPropertyFromSource(String modeName, String descriptorName, String propertyName, int sourceIndex) {
/* 1796 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1798 */     if (dc != null)
/* 1799 */       dc.copyPropertyFromSource(descriptorName, propertyName, sourceIndex); 
/*      */   }
/*      */   
/*      */   public void suppressProperty(String modeName, String descriptorName, String propertyName) {
/* 1828 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1830 */     if (dc != null)
/* 1831 */       dc.suppressProperty(descriptorName, propertyName); 
/*      */   }
/*      */   
/*      */   public void suppressAllProperties(String modeName, String descriptorName) {
/* 1857 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1859 */     if (dc != null)
/* 1860 */       dc.suppressAllProperties(descriptorName); 
/*      */   }
/*      */   
/*      */   public void clearPropertyState(String modeName) {
/* 1878 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1880 */     if (dc != null)
/* 1881 */       dc.clearPropertyState(); 
/*      */   }
/*      */   
/*      */   public String[] getGeneratedPropertyNames(String modeName, String descriptorName) {
/* 1908 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1910 */     if (dc != null)
/* 1911 */       return dc.getGeneratedPropertyNames(descriptorName); 
/* 1913 */     return null;
/*      */   }
/*      */   
/*      */   public PropertySource getPropertySource(String modeName, String descriptorName, Object op, Vector sources) {
/* 1952 */     DescriptorCache dc = getDescriptorCache(modeName);
/* 1954 */     if (dc != null)
/* 1955 */       return dc.getPropertySource(descriptorName, op, sources); 
/* 1957 */     return null;
/*      */   }
/*      */   
/*      */   public PropertySource getPropertySource(OperationNode op) {
/* 1978 */     if (op == null)
/* 1979 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1982 */     ParameterBlock pb = op.getParameterBlock();
/* 1983 */     Vector pv = (pb == null) ? null : pb.getSources();
/* 1990 */     if (pv == null)
/* 1991 */       pv = new Vector(); 
/* 1994 */     return getPropertySource(op.getRegistryModeName(), op.getOperationName(), op, pv);
/*      */   }
/*      */   
/*      */   public void registerServices(ClassLoader cl) throws IOException {
/*      */     Enumeration en;
/*      */     Iterator spitr;
/* 2025 */     if (cl == null) {
/* 2026 */       en = ClassLoader.getSystemResources(USR_REGISTRY_FILE);
/*      */     } else {
/* 2028 */       en = cl.getResources(USR_REGISTRY_FILE);
/*      */     } 
/* 2030 */     while (en.hasMoreElements()) {
/* 2031 */       URL url = en.nextElement();
/* 2033 */       RegistryFileParser.loadOperationRegistry(this, cl, url);
/*      */     } 
/* 2040 */     if (cl == null) {
/* 2041 */       spitr = Service.providers(OperationRegistrySpi.class);
/*      */     } else {
/* 2043 */       spitr = Service.providers(OperationRegistrySpi.class, cl);
/*      */     } 
/* 2045 */     while (spitr.hasNext()) {
/* 2047 */       OperationRegistrySpi ospi = spitr.next();
/* 2048 */       ospi.updateRegistry(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void registerOperationDescriptor(OperationDescriptor odesc, String operationName) {
/* 2072 */     registerDescriptor(odesc);
/*      */   }
/*      */   
/*      */   public void unregisterOperationDescriptor(String operationName) {
/* 2089 */     String[] operationModes = RegistryMode.getModeNames(OperationDescriptor.class);
/* 2094 */     for (int i = 0; i < operationModes.length; i++) {
/*      */       RegistryElementDescriptor red;
/* 2095 */       if ((red = getDescriptor(operationModes[i], operationName)) != null)
/* 2096 */         unregisterDescriptor(red); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public OperationDescriptor getOperationDescriptor(String operationName) {
/* 2118 */     return (OperationDescriptor)getDescriptor(OperationDescriptor.class, operationName);
/*      */   }
/*      */   
/*      */   public Vector getOperationDescriptors() {
/* 2136 */     List list = getDescriptors(OperationDescriptor.class);
/* 2138 */     return (list == null) ? null : new Vector(list);
/*      */   }
/*      */   
/*      */   public String[] getOperationNames() {
/* 2153 */     return getDescriptorNames(OperationDescriptor.class);
/*      */   }
/*      */   
/*      */   public void registerRIF(String operationName, String productName, RenderedImageFactory RIF) {
/* 2176 */     registerFactory("rendered", operationName, productName, RIF);
/*      */   }
/*      */   
/*      */   public void unregisterRIF(String operationName, String productName, RenderedImageFactory RIF) {
/* 2197 */     unregisterFactory("rendered", operationName, productName, RIF);
/*      */   }
/*      */   
/*      */   public void registerCRIF(String operationName, ContextualRenderedImageFactory CRIF) {
/* 2218 */     registerFactory("renderable", operationName, null, CRIF);
/*      */   }
/*      */   
/*      */   public void unregisterCRIF(String operationName, ContextualRenderedImageFactory CRIF) {
/* 2238 */     unregisterFactory("renderable", operationName, null, CRIF);
/*      */   }
/*      */   
/*      */   public void registerCIF(String operationName, String productName, CollectionImageFactory CIF) {
/* 2259 */     registerFactory("collection", operationName, productName, CIF);
/*      */   }
/*      */   
/*      */   public void unregisterCIF(String operationName, String productName, CollectionImageFactory CIF) {
/* 2280 */     unregisterFactory("collection", operationName, productName, CIF);
/*      */   }
/*      */   
/*      */   public void setProductPreference(String operationName, String preferredProductName, String otherProductName) {
/* 2305 */     setProductPreference("rendered", operationName, preferredProductName, otherProductName);
/*      */   }
/*      */   
/*      */   public void unsetProductPreference(String operationName, String preferredProductName, String otherProductName) {
/* 2328 */     unsetProductPreference("rendered", operationName, preferredProductName, otherProductName);
/*      */   }
/*      */   
/*      */   public void clearProductPreferences(String operationName) {
/* 2347 */     clearProductPreferences("rendered", operationName);
/*      */   }
/*      */   
/*      */   public String[][] getProductPreferences(String operationName) {
/* 2367 */     return getProductPreferences("rendered", operationName);
/*      */   }
/*      */   
/*      */   public Vector getOrderedProductList(String operationName) {
/* 2390 */     return getOrderedProductList("rendered", operationName);
/*      */   }
/*      */   
/*      */   public void setRIFPreference(String operationName, String productName, RenderedImageFactory preferredRIF, RenderedImageFactory otherRIF) {
/* 2418 */     setFactoryPreference("rendered", operationName, productName, preferredRIF, otherRIF);
/*      */   }
/*      */   
/*      */   public void setCIFPreference(String operationName, String productName, CollectionImageFactory preferredCIF, CollectionImageFactory otherCIF) {
/* 2445 */     setFactoryPreference("collection", operationName, productName, preferredCIF, otherCIF);
/*      */   }
/*      */   
/*      */   public void unsetRIFPreference(String operationName, String productName, RenderedImageFactory preferredRIF, RenderedImageFactory otherRIF) {
/* 2471 */     unsetFactoryPreference("rendered", operationName, productName, preferredRIF, otherRIF);
/*      */   }
/*      */   
/*      */   public void unsetCIFPreference(String operationName, String productName, CollectionImageFactory preferredCIF, CollectionImageFactory otherCIF) {
/* 2497 */     unsetFactoryPreference("collection", operationName, productName, preferredCIF, otherCIF);
/*      */   }
/*      */   
/*      */   public void clearRIFPreferences(String operationName, String productName) {
/* 2518 */     clearFactoryPreferences("rendered", operationName, productName);
/*      */   }
/*      */   
/*      */   public void clearCIFPreferences(String operationName, String productName) {
/* 2539 */     clearFactoryPreferences("collection", operationName, productName);
/*      */   }
/*      */   
/*      */   public void clearOperationPreferences(String operationName, String productName) {
/* 2561 */     String[] operationModes = RegistryMode.getModeNames(OperationDescriptor.class);
/* 2564 */     for (int i = 0; i < operationModes.length; i++) {
/* 2566 */       DescriptorCache dc = getDescriptorCache(operationModes[i]);
/* 2568 */       if (dc.arePreferencesSupported)
/* 2571 */         if (getDescriptor(operationModes[i], operationName) != null)
/* 2574 */           clearFactoryPreferences(operationModes[i], operationName, productName);  
/*      */     } 
/*      */   }
/*      */   
/*      */   public Vector getOrderedRIFList(String operationName, String productName) {
/* 2603 */     List list = getOrderedFactoryList("rendered", operationName, productName);
/* 2606 */     return (list == null) ? null : new Vector(list);
/*      */   }
/*      */   
/*      */   public Vector getOrderedCIFList(String operationName, String productName) {
/* 2633 */     List list = getOrderedFactoryList("collection", operationName, productName);
/* 2636 */     return (list == null) ? null : new Vector(list);
/*      */   }
/*      */   
/*      */   public PlanarImage create(String operationName, ParameterBlock paramBlock, RenderingHints renderHints) {
/* 2669 */     return PlanarImage.wrapRenderedImage(RIFRegistry.create(this, operationName, paramBlock, renderHints));
/*      */   }
/*      */   
/*      */   public ContextualRenderedImageFactory createRenderable(String operationName, ParameterBlock paramBlock) {
/* 2693 */     return CRIFRegistry.get(this, operationName);
/*      */   }
/*      */   
/*      */   public CollectionImage createCollection(String operationName, ParameterBlock args, RenderingHints hints) {
/* 2723 */     return CIFRegistry.create(this, operationName, args, hints);
/*      */   }
/*      */   
/*      */   public void clearPropertyState() {
/* 2740 */     clearPropertyState("rendered");
/*      */   }
/*      */   
/*      */   public void addPropertyGenerator(String operationName, PropertyGenerator generator) {
/* 2759 */     addPropertyGenerator("rendered", operationName, generator);
/*      */   }
/*      */   
/*      */   public void removePropertyGenerator(String operationName, PropertyGenerator generator) {
/* 2780 */     removePropertyGenerator("rendered", operationName, generator);
/*      */   }
/*      */   
/*      */   public void suppressProperty(String operationName, String propertyName) {
/* 2800 */     suppressProperty("rendered", operationName, propertyName);
/*      */   }
/*      */   
/*      */   public void suppressAllProperties(String operationName) {
/* 2818 */     suppressAllProperties("rendered", operationName);
/*      */   }
/*      */   
/*      */   public void copyPropertyFromSource(String operationName, String propertyName, int sourceIndex) {
/* 2842 */     copyPropertyFromSource("rendered", operationName, propertyName, sourceIndex);
/*      */   }
/*      */   
/*      */   public String[] getGeneratedPropertyNames(String operationName) {
/* 2863 */     return getGeneratedPropertyNames("rendered", operationName);
/*      */   }
/*      */   
/*      */   public PropertySource getPropertySource(RenderedOp op) {
/* 2885 */     return RIFRegistry.getPropertySource(op);
/*      */   }
/*      */   
/*      */   public PropertySource getPropertySource(RenderableOp op) {
/* 2907 */     return CRIFRegistry.getPropertySource(op);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */