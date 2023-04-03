/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ 
/*      */ class DescriptorCache {
/*      */   final String modeName;
/*      */   
/*      */   final RegistryMode mode;
/*      */   
/*      */   final boolean arePreferencesSupported;
/*      */   
/*      */   final boolean arePropertiesSupported;
/*      */   
/*      */   private Hashtable descriptorNames;
/*      */   
/*      */   private Hashtable products;
/*      */   
/*      */   private Hashtable productPrefs;
/*      */   
/*      */   private Hashtable properties;
/*      */   
/*      */   private Hashtable suppressed;
/*      */   
/*      */   private Hashtable sourceForProp;
/*      */   
/*      */   private Hashtable propNames;
/*      */   
/*      */   DescriptorCache(String modeName) {
/*  117 */     this.modeName = modeName;
/*  118 */     this.mode = RegistryMode.getMode(modeName);
/*  120 */     this.arePreferencesSupported = this.mode.arePreferencesSupported();
/*  121 */     this.arePropertiesSupported = this.mode.arePropertiesSupported();
/*  123 */     this.descriptorNames = new Hashtable();
/*  124 */     this.products = new Hashtable();
/*  126 */     if (this.arePreferencesSupported)
/*  127 */       this.productPrefs = new Hashtable(); 
/*  130 */     this.properties = new Hashtable();
/*  131 */     this.suppressed = new Hashtable();
/*  132 */     this.sourceForProp = new Hashtable();
/*  133 */     this.propNames = new Hashtable();
/*      */   }
/*      */   
/*      */   boolean addDescriptor(RegistryElementDescriptor rdesc) {
/*  154 */     if (rdesc == null)
/*  155 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  158 */     String descriptorName = rdesc.getName();
/*  161 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  164 */     if (this.descriptorNames.containsKey(key) == true)
/*  165 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache0", new Object[] { descriptorName, this.modeName })); 
/*  171 */     this.descriptorNames.put(key, rdesc);
/*  175 */     if (this.arePreferencesSupported)
/*  176 */       this.products.put(key, new ProductOperationGraph()); 
/*  179 */     if (!rdesc.arePropertiesSupported())
/*  180 */       return true; 
/*  184 */     PropertyGenerator[] props = rdesc.getPropertyGenerators(this.modeName);
/*  186 */     if (props != null)
/*  187 */       for (int i = 0; i < props.length; i++) {
/*  189 */         Vector v = (Vector)this.properties.get(key);
/*  190 */         if (v == null) {
/*  191 */           v = new Vector();
/*  192 */           v.addElement(props[i]);
/*  193 */           this.properties.put(key, v);
/*      */         } else {
/*  195 */           v.addElement(props[i]);
/*      */         } 
/*  198 */         v = (Vector)this.suppressed.get(key);
/*  199 */         Hashtable h = (Hashtable)this.sourceForProp.get(key);
/*  200 */         String[] names = props[i].getPropertyNames();
/*  202 */         for (int j = 0; j < names.length; j++) {
/*  203 */           CaselessStringKey name = new CaselessStringKey(names[j]);
/*  205 */           if (v != null)
/*  205 */             v.remove(name); 
/*  206 */           if (h != null)
/*  206 */             h.remove(name); 
/*      */         } 
/*      */       }  
/*  211 */     return true;
/*      */   }
/*      */   
/*      */   boolean removeDescriptor(String descriptorName) {
/*  230 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  233 */     if (!this.descriptorNames.containsKey(key))
/*  234 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache1", new Object[] { descriptorName, this.modeName })); 
/*  239 */     RegistryElementDescriptor rdesc = (RegistryElementDescriptor)this.descriptorNames.get(key);
/*  242 */     PropertyGenerator[] props = null;
/*  245 */     if (rdesc.arePropertiesSupported() == true)
/*  246 */       props = rdesc.getPropertyGenerators(this.modeName); 
/*  249 */     if (props != null)
/*  250 */       for (int i = 0; i < props.length; i++) {
/*  252 */         if (props[i] == null)
/*  253 */           throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache2", new Object[] { new Integer(i), descriptorName, this.modeName })); 
/*  260 */         Vector v = (Vector)this.properties.get(key);
/*  261 */         if (v != null)
/*  262 */           v.removeElement(props[i]); 
/*      */       }  
/*  268 */     this.descriptorNames.remove(key);
/*  270 */     if (this.arePreferencesSupported)
/*  271 */       this.products.remove(key); 
/*  273 */     return true;
/*      */   }
/*      */   
/*      */   boolean removeDescriptor(RegistryElementDescriptor rdesc) {
/*  291 */     if (rdesc == null)
/*  292 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  294 */     return removeDescriptor(rdesc.getName());
/*      */   }
/*      */   
/*      */   RegistryElementDescriptor getDescriptor(String descriptorName) {
/*  310 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  312 */     return (RegistryElementDescriptor)this.descriptorNames.get(key);
/*      */   }
/*      */   
/*      */   List getDescriptors() {
/*  323 */     ArrayList list = new ArrayList();
/*  325 */     Enumeration en = this.descriptorNames.elements();
/*  326 */     while (en.hasMoreElements())
/*  327 */       list.add(en.nextElement()); 
/*  330 */     return list;
/*      */   }
/*      */   
/*      */   String[] getDescriptorNames() {
/*  341 */     Enumeration e = this.descriptorNames.keys();
/*  342 */     int size = this.descriptorNames.size();
/*  343 */     String[] names = new String[size];
/*  345 */     for (int i = 0; i < size; i++) {
/*  346 */       CaselessStringKey key = e.nextElement();
/*  347 */       names[i] = key.getName();
/*      */     } 
/*  350 */     return names;
/*      */   }
/*      */   
/*      */   OperationGraph addProduct(String descriptorName, String productName) {
/*  369 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  371 */     if (productName == null)
/*  372 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  374 */     ProductOperationGraph pog = (ProductOperationGraph)this.products.get(key);
/*  377 */     if (pog == null)
/*  378 */       return null; 
/*  380 */     PartialOrderNode pon = pog.lookupOp(productName);
/*  382 */     if (pon == null) {
/*  383 */       pog.addProduct(productName);
/*  385 */       pon = pog.lookupOp(productName);
/*      */     } 
/*  388 */     return (OperationGraph)pon.getData();
/*      */   }
/*      */   
/*      */   boolean removeProduct(String descriptorName, String productName) {
/*  406 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  408 */     if (productName == null)
/*  409 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  411 */     ProductOperationGraph pog = (ProductOperationGraph)this.products.get(key);
/*  414 */     if (pog == null)
/*  415 */       return false; 
/*  417 */     PartialOrderNode pon = pog.lookupOp(productName);
/*  419 */     if (pon == null)
/*  420 */       return false; 
/*  422 */     pog.removeOp(productName);
/*  424 */     return true;
/*      */   }
/*      */   
/*      */   OperationGraph lookupProduct(String descriptorName, String productName) {
/*  442 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  444 */     if (productName == null)
/*  445 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  447 */     ProductOperationGraph pog = (ProductOperationGraph)this.products.get(key);
/*  450 */     if (pog == null)
/*  451 */       return null; 
/*  453 */     PartialOrderNode pon = pog.lookupOp(productName);
/*  455 */     if (pon == null)
/*  456 */       return null; 
/*  458 */     return (OperationGraph)pon.getData();
/*      */   }
/*      */   
/*      */   boolean setProductPreference(String descriptorName, String preferredProductName, String otherProductName) {
/*  486 */     if (!this.arePreferencesSupported)
/*  487 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache6", new Object[] { this.modeName })); 
/*  492 */     if (descriptorName == null || preferredProductName == null || otherProductName == null)
/*  494 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  498 */     if (preferredProductName.equalsIgnoreCase(otherProductName))
/*  499 */       return false; 
/*  503 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  505 */     if (!this.descriptorNames.containsKey(key))
/*  506 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache1", new Object[] { descriptorName, this.modeName })); 
/*  511 */     ProductOperationGraph og = (ProductOperationGraph)this.products.get(key);
/*  513 */     if (og == null)
/*  514 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache3", new Object[] { descriptorName, this.modeName })); 
/*  519 */     if (og.lookupOp(preferredProductName) == null)
/*  520 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, preferredProductName })); 
/*  526 */     if (og.lookupOp(otherProductName) == null)
/*  527 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, otherProductName })); 
/*  533 */     og.setPreference(preferredProductName, otherProductName);
/*  535 */     String[] prefs = { preferredProductName, otherProductName };
/*  538 */     if (!this.productPrefs.containsKey(key)) {
/*  539 */       Vector v = new Vector();
/*  540 */       v.addElement(prefs);
/*  542 */       this.productPrefs.put(key, v);
/*      */     } else {
/*  545 */       Vector v = (Vector)this.productPrefs.get(key);
/*  546 */       v.addElement(prefs);
/*      */     } 
/*  549 */     return true;
/*      */   }
/*      */   
/*      */   boolean unsetProductPreference(String descriptorName, String preferredProductName, String otherProductName) {
/*  575 */     if (!this.arePreferencesSupported)
/*  576 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache6", new Object[] { this.modeName })); 
/*  581 */     if (descriptorName == null || preferredProductName == null || otherProductName == null)
/*  583 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  587 */     if (preferredProductName.equalsIgnoreCase(otherProductName))
/*  588 */       return false; 
/*  592 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  594 */     if (!this.descriptorNames.containsKey(key))
/*  595 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache1", new Object[] { descriptorName, this.modeName })); 
/*  600 */     ProductOperationGraph og = (ProductOperationGraph)this.products.get(key);
/*  602 */     if (og == null)
/*  603 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache3", new Object[] { descriptorName, this.modeName })); 
/*  608 */     if (og.lookupOp(preferredProductName) == null)
/*  609 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, preferredProductName })); 
/*  615 */     if (og.lookupOp(otherProductName) == null)
/*  616 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, otherProductName })); 
/*  622 */     og.unsetPreference(preferredProductName, otherProductName);
/*  626 */     if (!this.productPrefs.containsKey(key))
/*  627 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache5", new Object[] { descriptorName, this.modeName })); 
/*  632 */     Vector v = (Vector)this.productPrefs.get(key);
/*  633 */     Iterator it = v.iterator();
/*  634 */     while (it.hasNext()) {
/*  635 */       String[] prefs = it.next();
/*  637 */       if (prefs[0].equalsIgnoreCase(preferredProductName) && prefs[1].equalsIgnoreCase(otherProductName)) {
/*  639 */         it.remove();
/*      */         break;
/*      */       } 
/*      */     } 
/*  644 */     return true;
/*      */   }
/*      */   
/*      */   boolean clearProductPreferences(String descriptorName) {
/*  660 */     if (!this.arePreferencesSupported)
/*  661 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache6", new Object[] { this.modeName })); 
/*  667 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  669 */     if (!this.descriptorNames.containsKey(key))
/*  670 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache1", new Object[] { descriptorName, this.modeName })); 
/*  675 */     ProductOperationGraph og = (ProductOperationGraph)this.products.get(key);
/*  677 */     if (og == null)
/*  678 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache3", new Object[] { descriptorName, this.modeName })); 
/*  684 */     if (!this.productPrefs.containsKey(key))
/*  685 */       return true; 
/*  687 */     Vector v = (Vector)this.productPrefs.get(key);
/*  688 */     Enumeration e = v.elements();
/*  690 */     while (e.hasMoreElements()) {
/*  691 */       String[] prefs = e.nextElement();
/*  693 */       String pref = prefs[0];
/*  694 */       String other = prefs[1];
/*  696 */       if (og.lookupOp(pref) == null)
/*  697 */         throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, pref })); 
/*  703 */       if (og.lookupOp(other) == null)
/*  704 */         throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache4", new Object[] { descriptorName, this.modeName, other })); 
/*  710 */       og.unsetPreference(pref, other);
/*      */     } 
/*  712 */     this.productPrefs.remove(key);
/*  713 */     return true;
/*      */   }
/*      */   
/*      */   String[][] getProductPreferences(String descriptorName) {
/*  730 */     if (!this.arePreferencesSupported)
/*  731 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache6", new Object[] { this.modeName })); 
/*  737 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  741 */     if (!this.productPrefs.containsKey(key))
/*  743 */       return (String[][])null; 
/*  745 */     Vector v = (Vector)this.productPrefs.get(key);
/*  746 */     int s = v.size();
/*  747 */     if (s == 0)
/*  748 */       return (String[][])null; 
/*  750 */     String[][] productPreferences = new String[s][2];
/*  751 */     int count = 0;
/*  752 */     Enumeration e = v.elements();
/*  753 */     while (e.hasMoreElements()) {
/*  754 */       String[] o = e.nextElement();
/*  755 */       productPreferences[count][0] = o[0];
/*  756 */       productPreferences[count++][1] = o[1];
/*      */     } 
/*  759 */     return productPreferences;
/*      */   }
/*      */   
/*      */   Vector getOrderedProductList(String descriptorName) {
/*  781 */     if (!this.arePreferencesSupported)
/*  782 */       return null; 
/*  785 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  787 */     if (!this.descriptorNames.containsKey(key))
/*  788 */       return null; 
/*  791 */     ProductOperationGraph productGraph = (ProductOperationGraph)this.products.get(key);
/*  795 */     if (productGraph == null)
/*  796 */       return null; 
/*  800 */     Vector v1 = productGraph.getOrderedOperationList();
/*  802 */     if (v1 == null)
/*  803 */       return null; 
/*  805 */     int size = v1.size();
/*  807 */     if (size == 0)
/*  808 */       return null; 
/*  810 */     Vector v2 = new Vector();
/*  811 */     for (int i = 0; i < size; i++)
/*  812 */       v2.addElement(((PartialOrderNode)v1.elementAt(i)).getName()); 
/*  815 */     return v2;
/*      */   }
/*      */   
/*      */   private boolean arePropertiesSupported(String descriptorName) {
/*  823 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  825 */     RegistryElementDescriptor rdesc = (RegistryElementDescriptor)this.descriptorNames.get(key);
/*  828 */     if (rdesc == null)
/*  829 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache1", new Object[] { descriptorName, this.modeName })); 
/*  834 */     return this.arePropertiesSupported;
/*      */   }
/*      */   
/*      */   void clearPropertyState() {
/*  843 */     if (!this.arePropertiesSupported)
/*  844 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/*  849 */     this.properties = new Hashtable();
/*  850 */     this.suppressed = new Hashtable();
/*      */   }
/*      */   
/*      */   void addPropertyGenerator(String descriptorName, PropertyGenerator generator) {
/*  863 */     if (descriptorName == null || generator == null)
/*  864 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  866 */     if (!arePropertiesSupported(descriptorName))
/*  867 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/*  872 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  874 */     Vector v = (Vector)this.properties.get(key);
/*  876 */     if (v == null) {
/*  877 */       v = new Vector();
/*  878 */       this.properties.put(key, v);
/*      */     } 
/*  881 */     v.addElement(generator);
/*  883 */     v = (Vector)this.suppressed.get(key);
/*  884 */     Hashtable h = (Hashtable)this.sourceForProp.get(key);
/*  886 */     String[] names = generator.getPropertyNames();
/*  888 */     for (int j = 0; j < names.length; j++) {
/*  889 */       CaselessStringKey name = new CaselessStringKey(names[j]);
/*  891 */       if (v != null)
/*  891 */         v.remove(name); 
/*  892 */       if (h != null)
/*  892 */         h.remove(name); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void hashNames(String descriptorName) {
/*  897 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  899 */     Vector c = (Vector)this.properties.get(key);
/*  900 */     Vector s = (Vector)this.suppressed.get(key);
/*  902 */     Hashtable h = new Hashtable();
/*  903 */     this.propNames.put(key, h);
/*  905 */     if (c != null)
/*  909 */       for (Iterator it = c.iterator(); it.hasNext(); ) {
/*  910 */         PropertyGenerator pg = it.next();
/*  911 */         String[] names = pg.getPropertyNames();
/*  913 */         for (int i = 0; i < names.length; i++) {
/*  914 */           CaselessStringKey name = new CaselessStringKey(names[i]);
/*  918 */           if (s == null || !s.contains(name))
/*  919 */             h.put(name, pg); 
/*      */         } 
/*      */       }  
/*  925 */     Hashtable htable = (Hashtable)this.sourceForProp.get(key);
/*  927 */     if (htable != null)
/*  928 */       for (Enumeration e = htable.keys(); e.hasMoreElements(); ) {
/*  929 */         CaselessStringKey name = e.nextElement();
/*  931 */         int i = ((Integer)htable.get(name)).intValue();
/*  933 */         PropertyGeneratorFromSource propertyGeneratorFromSource = new PropertyGeneratorFromSource(i, name.getName());
/*  936 */         h.put(name, propertyGeneratorFromSource);
/*      */       }  
/*      */   }
/*      */   
/*      */   void removePropertyGenerator(String descriptorName, PropertyGenerator generator) {
/*  955 */     if (descriptorName == null || generator == null)
/*  956 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  959 */     if (!arePropertiesSupported(descriptorName))
/*  960 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/*  965 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  967 */     Vector v = (Vector)this.properties.get(key);
/*  969 */     if (v != null)
/*  970 */       v.removeElement(generator); 
/*      */   }
/*      */   
/*      */   void suppressProperty(String descriptorName, String propertyName) {
/*  988 */     if (descriptorName == null || propertyName == null)
/*  989 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  992 */     if (!arePropertiesSupported(descriptorName))
/*  993 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/*  998 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/*  999 */     CaselessStringKey propertyKey = new CaselessStringKey(propertyName);
/* 1002 */     Vector v = (Vector)this.suppressed.get(key);
/* 1004 */     if (v == null) {
/* 1005 */       v = new Vector();
/* 1006 */       this.suppressed.put(key, v);
/*      */     } 
/* 1009 */     v.addElement(propertyKey);
/* 1011 */     Hashtable h = (Hashtable)this.sourceForProp.get(key);
/* 1013 */     if (h != null)
/* 1014 */       h.remove(propertyKey); 
/*      */   }
/*      */   
/*      */   void suppressAllProperties(String descriptorName) {
/* 1029 */     if (!arePropertiesSupported(descriptorName))
/* 1030 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/* 1039 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/* 1043 */     Vector v = (Vector)this.properties.get(key);
/* 1045 */     if (v != null)
/* 1048 */       for (Iterator it = v.iterator(); it.hasNext(); ) {
/* 1049 */         PropertyGenerator pg = it.next();
/* 1050 */         String[] propertyNames = pg.getPropertyNames();
/* 1051 */         for (int i = 0; i < propertyNames.length; i++)
/* 1052 */           suppressProperty(descriptorName, propertyNames[i]); 
/*      */       }  
/*      */   }
/*      */   
/*      */   void copyPropertyFromSource(String descriptorName, String propertyName, int sourceIndex) {
/* 1075 */     if (descriptorName == null || propertyName == null)
/* 1076 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1079 */     if (!arePropertiesSupported(descriptorName))
/* 1080 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/* 1085 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/* 1086 */     CaselessStringKey propertyKey = new CaselessStringKey(propertyName);
/* 1088 */     Hashtable h = (Hashtable)this.sourceForProp.get(key);
/* 1090 */     if (h == null) {
/* 1091 */       h = new Hashtable();
/* 1092 */       this.sourceForProp.put(key, h);
/*      */     } 
/* 1095 */     h.put(propertyKey, new Integer(sourceIndex));
/* 1097 */     Vector v = (Vector)this.suppressed.get(key);
/* 1099 */     if (v != null)
/* 1100 */       v.remove(propertyKey); 
/*      */   }
/*      */   
/*      */   String[] getGeneratedPropertyNames(String descriptorName) {
/* 1116 */     if (!arePropertiesSupported(descriptorName))
/* 1117 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/* 1122 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/* 1124 */     hashNames(descriptorName);
/* 1126 */     Hashtable h = (Hashtable)this.propNames.get(key);
/* 1128 */     if (h != null && h.size() > 0) {
/* 1129 */       String[] names = new String[h.size()];
/* 1130 */       int count = 0;
/* 1131 */       for (Enumeration e = h.keys(); e.hasMoreElements(); ) {
/* 1132 */         CaselessStringKey str = e.nextElement();
/* 1133 */         names[count++] = str.getName();
/*      */       } 
/* 1136 */       return (count > 0) ? names : null;
/*      */     } 
/* 1139 */     return null;
/*      */   }
/*      */   
/*      */   PropertySource getPropertySource(String descriptorName, Object op, Vector sources) {
/* 1173 */     if (descriptorName == null || op == null || sources == null)
/* 1174 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1177 */     if (!arePropertiesSupported(descriptorName))
/* 1178 */       throw new IllegalArgumentException(JaiI18N.formatMsg("DescriptorCache7", new Object[] { this.modeName })); 
/* 1183 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/* 1185 */     Vector pg = (Vector)this.properties.get(key);
/* 1186 */     Vector sp = (Vector)this.suppressed.get(key);
/* 1188 */     Hashtable sfp = (Hashtable)this.sourceForProp.get(key);
/* 1190 */     return new PropertyEnvironment(sources, pg, sp, sfp, op);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\DescriptorCache.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */