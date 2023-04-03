/*      */ package org.geotools.data;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.MultiLineString;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.data.simple.SimpleFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureIterator;
/*      */ import org.geotools.data.simple.SimpleFeatureSource;
/*      */ import org.geotools.data.simple.SimpleFeatureStore;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.feature.FeatureCollection;
/*      */ import org.geotools.feature.IllegalAttributeException;
/*      */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.feature.simple.SimpleFeature;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.AttributeDescriptor;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.Id;
/*      */ 
/*      */ public abstract class AbstractDataStoreTest extends DataTestCase {
/*   59 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.hsql");
/*      */   
/*      */   DataStore data;
/*      */   
/*      */   public AbstractDataStoreTest(String arg0) {
/*   63 */     super(arg0);
/*      */   }
/*      */   
/*      */   public abstract DataStore createDataStore() throws Exception;
/*      */   
/*      */   public abstract DataStore tearDownDataStore(DataStore paramDataStore) throws Exception;
/*      */   
/*      */   protected void setUp() throws Exception {
/*   97 */     dataSetUp();
/*      */     try {
/*  100 */       this.data = createDataStore();
/*  101 */     } catch (Exception e) {
/*  102 */       LOGGER.log(Level.INFO, "exception while making schema", e);
/*      */     } 
/*  105 */     this.data.createSchema(this.roadType);
/*  106 */     this.data.createSchema(this.riverType);
/*  109 */     SimpleFeatureStore roads = (SimpleFeatureStore)this.data.getFeatureSource(this.roadType.getTypeName());
/*  112 */     roads.addFeatures((FeatureCollection)DataUtilities.collection(this.roadFeatures));
/*  114 */     SimpleFeatureStore rivers = (SimpleFeatureStore)this.data.getFeatureSource(this.riverType.getTypeName());
/*  117 */     rivers.addFeatures((FeatureCollection)DataUtilities.collection(this.riverFeatures));
/*  122 */     this.roadFeatures = grabArray(roads.getFeatures(), this.roadFeatures.length);
/*  123 */     this.riverFeatures = grabArray(rivers.getFeatures(), this.riverFeatures.length);
/*      */   }
/*      */   
/*      */   SimpleFeature[] grabArray(SimpleFeatureCollection features, int size) {
/*  127 */     SimpleFeature[] array = new SimpleFeature[size];
/*  128 */     array = (SimpleFeature[])features.toArray((Object[])array);
/*  129 */     assertNotNull(array);
/*  131 */     return array;
/*      */   }
/*      */   
/*      */   protected void tearDown() throws Exception {
/*  135 */     tearDownDataStore(this.data);
/*  136 */     this.data = null;
/*  137 */     super.tearDown();
/*      */   }
/*      */   
/*      */   public void testFeatureEvents() throws Exception {
/*  141 */     SimpleFeatureStore store1 = (SimpleFeatureStore)this.data.getFeatureSource(this.roadFeatures[0].getFeatureType().getTypeName());
/*  143 */     SimpleFeatureStore store2 = (SimpleFeatureStore)this.data.getFeatureSource(this.roadFeatures[0].getFeatureType().getTypeName());
/*  145 */     store1.setTransaction(new DefaultTransaction());
/*      */     class Listener implements FeatureListener {
/*      */       String name;
/*      */       
/*  148 */       List events = new ArrayList();
/*      */       
/*      */       public Listener(String name) {
/*  151 */         this.name = name;
/*      */       }
/*      */       
/*      */       public void changed(FeatureEvent featureEvent) {
/*  155 */         this.events.add(featureEvent);
/*      */       }
/*      */       
/*      */       FeatureEvent getEvent(int i) {
/*  159 */         return this.events.get(i);
/*      */       }
/*      */     };
/*  163 */     Listener listener1 = new Listener("one");
/*  164 */     Listener listener2 = new Listener(this, "two");
/*  166 */     store1.addFeatureListener(listener1);
/*  167 */     store2.addFeatureListener(listener2);
/*  169 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*  172 */     SimpleFeature feature = this.roadFeatures[0];
/*  173 */     Id fidFilter = ff.id(Collections.singleton(ff.featureId(feature.getID())));
/*  175 */     store1.removeFeatures((Filter)fidFilter);
/*  177 */     assertEquals(1, listener1.events.size());
/*  178 */     assertEquals(0, listener2.events.size());
/*  180 */     FeatureEvent event = listener1.getEvent(0);
/*  181 */     assertEquals(feature.getBounds(), event.getBounds());
/*  182 */     assertEquals(-1, event.getEventType());
/*  185 */     listener1.events.clear();
/*  186 */     listener2.events.clear();
/*  188 */     store1.getTransaction().commit();
/*  190 */     assertEquals(0, listener1.events.size());
/*  191 */     assertEquals(2, listener2.events.size());
/*  193 */     event = listener2.getEvent(0);
/*  194 */     assertEquals(feature.getBounds(), event.getBounds());
/*  195 */     assertEquals(-1, event.getEventType());
/*  198 */     listener1.events.clear();
/*  199 */     listener2.events.clear();
/*  201 */     store1.addFeatures((FeatureCollection)DataUtilities.collection(feature));
/*  203 */     assertEquals(1, listener1.events.size());
/*  204 */     event = listener1.getEvent(0);
/*  205 */     assertEquals(feature.getBounds(), event.getBounds());
/*  206 */     assertEquals(1, event.getEventType());
/*  207 */     assertEquals(0, listener2.events.size());
/*  210 */     listener1.events.clear();
/*  211 */     listener2.events.clear();
/*  213 */     store1.getTransaction().rollback();
/*  215 */     assertEquals(1, listener1.events.size());
/*  216 */     event = listener1.getEvent(0);
/*  217 */     assertNull(event.getBounds());
/*  218 */     assertEquals(0, event.getEventType());
/*  220 */     assertEquals(0, listener2.events.size());
/*      */   }
/*      */   
/*      */   public void testFixture() throws Exception {
/*  254 */     SimpleFeatureType type = DataUtilities.createType("namespace.typename", "name:String,id:0,geom:MultiLineString");
/*  256 */     assertEquals("namespace", "namespace", type.getName().getNamespaceURI());
/*  257 */     assertEquals("typename", "typename", type.getTypeName());
/*  258 */     assertEquals("attributes", 3, type.getAttributeCount());
/*  260 */     AttributeDescriptor[] a = (AttributeDescriptor[])type.getAttributeDescriptors().toArray((Object[])new AttributeDescriptor[type.getAttributeCount()]);
/*  261 */     assertEquals("a1", "name", a[0].getLocalName());
/*  262 */     assertEquals("a1", String.class, a[0].getType().getBinding());
/*  264 */     assertEquals("a2", "id", a[1].getLocalName());
/*  265 */     assertEquals("a2", Integer.class, a[1].getType().getBinding());
/*  267 */     assertEquals("a3", "geom", a[2].getLocalName());
/*  268 */     assertEquals("a3", MultiLineString.class, a[2].getType().getBinding());
/*      */   }
/*      */   
/*      */   public void testGetFeatureTypes() {
/*      */     try {
/*  275 */       String[] names = this.data.getTypeNames();
/*  276 */       assertEquals(2, names.length);
/*  277 */       assertTrue(contains((Object[])names, "ROAD"));
/*  278 */       assertTrue(contains((Object[])names, "RIVER"));
/*  279 */     } catch (IOException e) {
/*  280 */       e.printStackTrace();
/*  281 */       fail("Fail with an IOException trying to getTypeNames()");
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean contains(Object[] array, Object expected) {
/*  286 */     if (array == null || array.length == 0)
/*  287 */       return false; 
/*  290 */     for (int i = 0; i < array.length; i++) {
/*  291 */       if (array[i].equals(expected))
/*  292 */         return true; 
/*      */     } 
/*  296 */     return false;
/*      */   }
/*      */   
/*      */   boolean containsFeatureCollection(SimpleFeatureCollection fc, SimpleFeature f) {
/*  309 */     if (fc == null || fc.isEmpty())
/*  310 */       return false; 
/*  313 */     return containsFeature(fc.toArray(), f);
/*      */   }
/*      */   
/*      */   boolean containsFeature(Object[] array, Object expected) {
/*  325 */     if (array == null || array.length == 0)
/*  326 */       return false; 
/*  329 */     for (int i = 0; i < array.length; i++) {
/*  330 */       if (isFeatureEqual((SimpleFeature)array[i], (SimpleFeature)expected))
/*  331 */         return true; 
/*      */     } 
/*  335 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isFeatureEqual(SimpleFeature feature1, SimpleFeature feature2) {
/*  350 */     if (feature2 == null)
/*  351 */       return false; 
/*  354 */     if (feature2 == feature1)
/*  355 */       return true; 
/*  358 */     if (!feature2.getFeatureType().equals(feature1.getFeatureType()))
/*  359 */       return false; 
/*  365 */     for (int i = 0, ii = feature1.getAttributeCount(); i < ii; i++) {
/*  366 */       Object otherAtt = feature2.getAttribute(i);
/*  368 */       if (feature1.getAttribute(i) == null) {
/*  369 */         if (otherAtt != null)
/*  370 */           return false; 
/*  373 */       } else if (!feature1.getAttribute(i).equals(otherAtt)) {
/*  374 */         return false;
/*      */       } 
/*      */     } 
/*  379 */     return true;
/*      */   }
/*      */   
/*      */   boolean containsLax(SimpleFeature[] array, SimpleFeature expected) {
/*  391 */     if (array == null || array.length == 0)
/*  392 */       return false; 
/*  395 */     SimpleFeatureType type = expected.getFeatureType();
/*  397 */     for (int i = 0; i < array.length; i++) {
/*  398 */       if (match(array[i], expected))
/*  399 */         return true; 
/*      */     } 
/*  403 */     return false;
/*      */   }
/*      */   
/*      */   boolean match(SimpleFeature expected, SimpleFeature actual) {
/*  415 */     SimpleFeatureType type = expected.getFeatureType();
/*  417 */     for (int i = 0; i < type.getAttributeCount(); i++) {
/*  418 */       Object av = actual.getAttribute(i);
/*  419 */       Object ev = expected.getAttribute(i);
/*  421 */       if (av == null && ev != null)
/*  422 */         return false; 
/*  423 */       if (ev == null && av != null)
/*  424 */         return false; 
/*  425 */       if (!av.equals(ev))
/*  426 */         return false; 
/*      */     } 
/*  430 */     return true;
/*      */   }
/*      */   
/*      */   public void testGetSchema() throws IOException {
/*  434 */     assertEquals(this.roadType, this.data.getSchema("ROAD"));
/*  435 */     assertEquals(this.riverType, this.data.getSchema("RIVER"));
/*      */   }
/*      */   
/*      */   void assertCovers(String msg, SimpleFeatureCollection c1, SimpleFeatureCollection c2) {
/*  439 */     if (c1 == c2)
/*      */       return; 
/*  443 */     assertNotNull(msg, c1);
/*  444 */     assertNotNull(msg, c2);
/*  445 */     assertEquals(msg + " size", c1.size(), c2.size());
/*  449 */     for (SimpleFeatureIterator i = c1.features(); i.hasNext(); ) {
/*  450 */       SimpleFeature f = (SimpleFeature)i.next();
/*  451 */       assertTrue(msg + " " + f.getID(), containsFeatureCollection(c2, f));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void testGetFeatureReader() throws IOException, IllegalAttributeException {
/*  457 */     Query query = new Query(this.roadType.getTypeName());
/*  458 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  460 */     assertCovered(this.roadFeatures, reader);
/*  461 */     assertEquals(false, reader.hasNext());
/*      */   }
/*      */   
/*      */   public void testGetFeatureReaderMutability() throws IOException, IllegalAttributeException {
/*  466 */     Query query = new Query(this.roadType.getTypeName());
/*  467 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  471 */     while (reader.hasNext()) {
/*  472 */       SimpleFeature feature = reader.next();
/*  473 */       feature.setAttribute("NAME", null);
/*      */     } 
/*  476 */     reader.close();
/*  478 */     reader = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  480 */     while (reader.hasNext()) {
/*  481 */       SimpleFeature feature = reader.next();
/*  482 */       assertNotNull(feature.getAttribute("NAME"));
/*      */     } 
/*  485 */     reader.close();
/*      */     try {
/*  488 */       reader.next();
/*  489 */       fail("next should fail with an IOException or NoSuchElementException");
/*  491 */     } catch (IOException expected) {
/*      */     
/*  492 */     } catch (NoSuchElementException expected) {}
/*      */   }
/*      */   
/*      */   public void testGetFeatureReaderConcurancy() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  498 */     Query query = new Query(this.roadType.getTypeName());
/*  499 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader1 = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  501 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader2 = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  503 */     query = new Query(this.riverType.getTypeName());
/*  505 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader3 = this.data.getFeatureReader(query, Transaction.AUTO_COMMIT);
/*  508 */     while (reader1.hasNext() || reader2.hasNext() || reader3.hasNext()) {
/*  509 */       assertTrue(containsFeature((Object[])this.roadFeatures, reader1.next()));
/*  510 */       assertTrue(containsFeature((Object[])this.roadFeatures, reader2.next()));
/*  512 */       if (reader3.hasNext())
/*  513 */         assertTrue(containsFeature((Object[])this.riverFeatures, reader3.next())); 
/*      */     } 
/*      */     try {
/*  518 */       reader1.next();
/*  519 */       fail("next should fail with an IOException or NoSuchElementException");
/*  521 */     } catch (IOException expected) {
/*      */     
/*  522 */     } catch (NoSuchElementException expected) {}
/*      */     try {
/*  526 */       reader2.next();
/*  527 */       fail("next should fail with an IOException or NoSuchElementException");
/*  529 */     } catch (IOException expected) {
/*      */     
/*  530 */     } catch (NoSuchElementException expected) {}
/*      */     try {
/*  534 */       reader3.next();
/*  535 */       fail("next should fail with an IOException or NoSuchElementException");
/*  537 */     } catch (IOException expected) {
/*      */     
/*  538 */     } catch (NoSuchElementException expected) {}
/*  541 */     reader1.close();
/*  542 */     reader2.close();
/*  543 */     reader3.close();
/*      */   }
/*      */   
/*      */   public void testGetFeatureReaderFilterAutoCommit() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  548 */     SimpleFeatureType type = this.data.getSchema("ROAD");
/*  551 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/*  553 */     assertFalse(reader instanceof FilteringFeatureReader);
/*  554 */     assertEquals(type, reader.getFeatureType());
/*  555 */     assertEquals(this.roadFeatures.length, count(reader));
/*  557 */     reader = this.data.getFeatureReader(new Query("ROAD", (Filter)Filter.EXCLUDE), Transaction.AUTO_COMMIT);
/*  565 */     assertEquals(type, reader.getFeatureType());
/*  566 */     assertEquals(0, count(reader));
/*  568 */     reader = this.data.getFeatureReader(new Query("ROAD", this.rd1Filter), Transaction.AUTO_COMMIT);
/*  574 */     assertEquals(type, reader.getFeatureType());
/*  575 */     assertEquals(1, count(reader));
/*      */   }
/*      */   
/*      */   public void testGetFeatureReaderFilterTransaction() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  580 */     Transaction t = new DefaultTransaction();
/*  581 */     SimpleFeatureType type = this.data.getSchema("ROAD");
/*  584 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(new Query("ROAD", (Filter)Filter.EXCLUDE), t);
/*  588 */     assertEquals(type, reader.getFeatureType());
/*  589 */     assertEquals(0, count(reader));
/*  591 */     reader = this.data.getFeatureReader(new Query("ROAD"), t);
/*  592 */     assertTrue(reader instanceof DiffFeatureReader);
/*  593 */     assertEquals(type, reader.getFeatureType());
/*  594 */     assertEquals(this.roadFeatures.length, count(reader));
/*  596 */     reader = this.data.getFeatureReader(new Query("ROAD", this.rd1Filter), t);
/*  599 */     assertEquals(type, reader.getFeatureType());
/*  600 */     assertEquals(1, count(reader));
/*  602 */     SimpleFeatureStore store = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/*  603 */     store.setTransaction(t);
/*  604 */     store.removeFeatures(this.rd1Filter);
/*  606 */     reader = this.data.getFeatureReader(new Query("ROAD", (Filter)Filter.EXCLUDE), t);
/*  607 */     assertEquals(0, count(reader));
/*  609 */     reader = this.data.getFeatureReader(new Query("ROAD"), t);
/*  610 */     assertEquals(this.roadFeatures.length - 1, count(reader));
/*  612 */     reader = this.data.getFeatureReader(new Query("ROAD", this.rd1Filter), t);
/*  613 */     assertEquals(0, count(reader));
/*  615 */     t.rollback();
/*  616 */     reader = this.data.getFeatureReader(new Query("ROAD", (Filter)Filter.EXCLUDE), t);
/*  617 */     assertEquals(0, count(reader));
/*  619 */     reader = this.data.getFeatureReader(new Query("ROAD"), t);
/*  620 */     assertEquals(this.roadFeatures.length, count(reader));
/*  622 */     reader = this.data.getFeatureReader(new Query("ROAD", this.rd1Filter), t);
/*  623 */     assertEquals(1, count(reader));
/*      */   }
/*      */   
/*      */   void assertCovered(SimpleFeature[] features, FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  628 */     int count = 0;
/*      */     try {
/*  631 */       while (reader.hasNext()) {
/*  632 */         assertTrue(containsFeature((Object[])features, reader.next()));
/*  633 */         count++;
/*      */       } 
/*      */     } finally {}
/*  640 */     assertEquals(features.length, count);
/*      */   }
/*      */   
/*      */   boolean covers(SimpleFeatureCollection features, SimpleFeature[] array) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  660 */     int count = 0;
/*  661 */     SimpleFeatureIterator i = features.features();
/*      */     try {
/*  663 */       while (i.hasNext()) {
/*  664 */         SimpleFeature feature = (SimpleFeature)i.next();
/*  665 */         if (!containsFeature((Object[])array, feature))
/*  666 */           return false; 
/*  668 */         count++;
/*      */       } 
/*      */     } finally {
/*  671 */       i.close();
/*      */     } 
/*  673 */     return (count == array.length);
/*      */   }
/*      */   
/*      */   boolean covers(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeature[] array) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  692 */     int count = 0;
/*      */     try {
/*  695 */       while (reader.hasNext()) {
/*  696 */         SimpleFeature feature = reader.next();
/*  698 */         assertNotNull("feature", feature);
/*  699 */         if (!containsFeature((Object[])array, feature)) {
/*  700 */           fail("feature " + feature.getID() + " not listed");
/*  701 */           return false;
/*      */         } 
/*  703 */         count++;
/*      */       } 
/*      */     } finally {
/*  706 */       reader.close();
/*      */     } 
/*  708 */     assertEquals("covers", count, array.length);
/*  709 */     return true;
/*      */   }
/*      */   
/*      */   boolean covers(SimpleFeatureIterator reader, SimpleFeature[] array) throws NoSuchElementException, IOException {
/*  714 */     int count = 0;
/*      */     try {
/*  717 */       while (reader.hasNext()) {
/*  718 */         SimpleFeature feature = (SimpleFeature)reader.next();
/*  720 */         assertNotNull("feature", feature);
/*  721 */         if (!containsFeature((Object[])array, feature)) {
/*  722 */           fail("feature " + feature.getID() + " not listed");
/*  723 */           return false;
/*      */         } 
/*  725 */         count++;
/*      */       } 
/*      */     } finally {
/*  728 */       reader.close();
/*      */     } 
/*  730 */     assertEquals("covers", count, array.length);
/*  731 */     return true;
/*      */   }
/*      */   
/*      */   boolean coversLax(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeature[] array) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  737 */     int count = 0;
/*      */     try {
/*  740 */       while (reader.hasNext()) {
/*  741 */         SimpleFeature feature = reader.next();
/*  743 */         if (!containsLax(array, feature))
/*  744 */           return false; 
/*  747 */         count++;
/*      */       } 
/*      */     } finally {
/*  750 */       reader.close();
/*      */     } 
/*  752 */     return (count == array.length);
/*      */   }
/*      */   
/*      */   boolean coversLax(SimpleFeatureIterator reader, SimpleFeature[] array) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  759 */     int count = 0;
/*      */     try {
/*  762 */       while (reader.hasNext()) {
/*  763 */         SimpleFeature feature = (SimpleFeature)reader.next();
/*  765 */         if (!containsLax(array, feature))
/*  766 */           return false; 
/*  769 */         count++;
/*      */       } 
/*      */     } finally {
/*  772 */       reader.close();
/*      */     } 
/*  774 */     return (count == array.length);
/*      */   }
/*      */   
/*      */   void dump(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws NoSuchElementException, IOException, IllegalAttributeException {
/*  779 */     int count = 0;
/*      */     try {
/*  782 */       while (reader.hasNext()) {
/*  783 */         SimpleFeature feature = reader.next();
/*  784 */         System.out.println(count + " feature:" + feature);
/*  785 */         count++;
/*      */       } 
/*      */     } finally {
/*  788 */       reader.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   void dump(Object[] array) {
/*  793 */     for (int i = 0; i < array.length; i++)
/*  794 */       System.out.println(i + " feature:" + array[i]); 
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriter() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  803 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  805 */     assertEquals(this.roadFeatures.length, count(writer));
/*      */     try {
/*  808 */       writer.hasNext();
/*  809 */       fail("Should not be able to use a closed writer");
/*  810 */     } catch (IOException expected) {}
/*      */     try {
/*  814 */       writer.next();
/*  815 */       fail("Should not be able to use a closed writer");
/*  816 */     } catch (IOException expected) {}
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriterRemove() throws IOException, IllegalAttributeException {
/*  822 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  826 */     while (writer.hasNext()) {
/*  827 */       SimpleFeature feature = writer.next();
/*  829 */       if (feature.getID().equals(this.roadFeatures[0].getID()))
/*  830 */         writer.remove(); 
/*      */     } 
/*  834 */     writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  835 */     assertEquals(this.roadFeatures.length - 1, count(writer));
/*      */   }
/*      */   
/*      */   public void testGetFeaturesWriterAdd() throws IOException, IllegalAttributeException {
/*  840 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  844 */     while (writer.hasNext())
/*  845 */       SimpleFeature simpleFeature = writer.next(); 
/*  848 */     assertFalse(writer.hasNext());
/*  849 */     SimpleFeature feature = writer.next();
/*  850 */     feature.setAttributes(this.newRoad.getAttributes());
/*  851 */     writer.write();
/*  852 */     assertFalse(writer.hasNext());
/*  854 */     writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  855 */     assertEquals(this.roadFeatures.length + 1, count(writer));
/*      */   }
/*      */   
/*      */   public void testGetFeaturesWriterModify() throws IOException, IllegalAttributeException {
/*  860 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  864 */     while (writer.hasNext()) {
/*  865 */       SimpleFeature simpleFeature = writer.next();
/*  867 */       if (simpleFeature.getID().equals(this.roadFeatures[0].getID())) {
/*  868 */         simpleFeature.setAttribute("NAME", "changed");
/*  869 */         writer.write();
/*      */       } 
/*      */     } 
/*  873 */     SimpleFeature feature = null;
/*  875 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(new Query("ROAD", this.rd1Filter), Transaction.AUTO_COMMIT);
/*  878 */     if (reader.hasNext())
/*  879 */       feature = reader.next(); 
/*  883 */     assertEquals("changed", feature.getAttribute("NAME"));
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriterTypeNameTransaction() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  890 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", Transaction.AUTO_COMMIT);
/*  891 */     assertEquals(this.roadFeatures.length, count(writer));
/*  892 */     writer.close();
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriterAppendTypeNameTransaction() throws Exception {
/*  899 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriterAppend("ROAD", Transaction.AUTO_COMMIT);
/*  900 */     assertEquals(0, count(writer));
/*  901 */     writer.close();
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriterFilter() throws NoSuchElementException, IOException, IllegalAttributeException {
/*  911 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = this.data.getFeatureWriter("ROAD", (Filter)Filter.EXCLUDE, Transaction.AUTO_COMMIT);
/*  913 */     assertFalse(writer.hasNext());
/*  918 */     assertEquals(0, count(writer));
/*  920 */     writer = this.data.getFeatureWriter("ROAD", (Filter)Filter.INCLUDE, Transaction.AUTO_COMMIT);
/*  922 */     assertFalse(writer instanceof FilteringFeatureWriter);
/*  923 */     assertEquals(this.roadFeatures.length, count(writer));
/*  925 */     writer = this.data.getFeatureWriter("ROAD", this.rd1Filter, Transaction.AUTO_COMMIT);
/*  931 */     assertEquals(1, count(writer));
/*      */   }
/*      */   
/*      */   public void testGetFeatureWriterTransaction() throws Exception {
/*  940 */     Transaction t1 = new DefaultTransaction();
/*  941 */     Transaction t2 = new DefaultTransaction();
/*  942 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer1 = this.data.getFeatureWriter("ROAD", this.rd1Filter, t1);
/*  943 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer2 = this.data.getFeatureWriterAppend("ROAD", t2);
/*  945 */     SimpleFeatureType road = this.data.getSchema("ROAD");
/*  948 */     SimpleFeature[] ORIGIONAL = this.roadFeatures;
/*  949 */     SimpleFeature[] REMOVE = new SimpleFeature[ORIGIONAL.length - 1];
/*  950 */     SimpleFeature[] ADD = new SimpleFeature[ORIGIONAL.length + 1];
/*  951 */     SimpleFeature[] FINAL = new SimpleFeature[ORIGIONAL.length];
/*  954 */     int index = 0;
/*      */     int i;
/*  956 */     for (i = 0; i < ORIGIONAL.length; i++) {
/*  957 */       SimpleFeature simpleFeature = ORIGIONAL[i];
/*  959 */       if (!simpleFeature.getID().equals(this.roadFeatures[0].getID()))
/*  960 */         REMOVE[index++] = simpleFeature; 
/*      */     } 
/*  964 */     for (i = 0; i < ORIGIONAL.length; i++)
/*  965 */       ADD[i] = ORIGIONAL[i]; 
/*  968 */     ADD[i] = this.newRoad;
/*  970 */     for (i = 0; i < REMOVE.length; i++)
/*  971 */       FINAL[i] = REMOVE[i]; 
/*  974 */     FINAL[i] = this.newRoad;
/*  977 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/*  979 */     assertTrue(covers(reader, ORIGIONAL));
/*  984 */     while (writer1.hasNext()) {
/*  985 */       SimpleFeature simpleFeature = writer1.next();
/*  986 */       assertEquals(this.roadFeatures[0].getID(), simpleFeature.getID());
/*  987 */       writer1.remove();
/*      */     } 
/*  991 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/*  994 */     assertTrue(covers(reader, ORIGIONAL));
/*  996 */     reader = this.data.getFeatureReader(new Query("ROAD"), t1);
/*  997 */     assertTrue(covers(reader, REMOVE));
/* 1002 */     writer1.close();
/* 1005 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1007 */     assertTrue(covers(reader, ORIGIONAL));
/* 1008 */     reader = this.data.getFeatureReader(new Query("ROAD"), t1);
/* 1009 */     assertTrue(covers(reader, REMOVE));
/* 1014 */     SimpleFeature feature = writer2.next();
/* 1015 */     feature.setAttributes(this.newRoad.getAttributes());
/* 1016 */     writer2.write();
/* 1019 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1021 */     assertTrue(covers(reader, ORIGIONAL));
/* 1022 */     reader = this.data.getFeatureReader(new Query("ROAD"), t2);
/* 1023 */     assertTrue(coversLax(reader, ADD));
/* 1028 */     writer2.close();
/* 1031 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1033 */     assertTrue(covers(reader, ORIGIONAL));
/* 1034 */     reader = this.data.getFeatureReader(new Query("ROAD"), t2);
/* 1035 */     assertTrue(coversLax(reader, ADD));
/* 1041 */     t1.commit();
/* 1045 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1047 */     assertTrue(covers(reader, REMOVE));
/* 1048 */     reader = this.data.getFeatureReader(new Query("ROAD"), t1);
/* 1049 */     assertTrue(covers(reader, REMOVE));
/* 1050 */     reader = this.data.getFeatureReader(new Query("ROAD"), t2);
/* 1051 */     assertTrue(coversLax(reader, FINAL));
/* 1056 */     t2.commit();
/* 1059 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1061 */     reader = this.data.getFeatureReader(new Query("ROAD"), Transaction.AUTO_COMMIT);
/* 1063 */     assertTrue(coversLax(reader, FINAL));
/* 1064 */     reader = this.data.getFeatureReader(new Query("ROAD"), t1);
/* 1065 */     assertTrue(coversLax(reader, FINAL));
/* 1066 */     reader = this.data.getFeatureReader(new Query("ROAD"), t2);
/* 1067 */     assertTrue(coversLax(reader, FINAL));
/*      */   }
/*      */   
/*      */   public void atestGetFeatureSourceRoad() throws IOException {
/* 1075 */     SimpleFeatureSource road = this.data.getFeatureSource("ROAD");
/* 1077 */     assertEquals(this.roadType, road.getSchema());
/* 1078 */     assertEquals(this.data, road.getDataStore());
/* 1079 */     assertEquals(3, road.getCount(Query.ALL));
/* 1082 */     assertEquals(new Envelope(1.0D, 5.0D, 0.0D, 4.0D), road.getFeatures(Query.ALL).getBounds());
/* 1085 */     SimpleFeatureCollection all = road.getFeatures();
/* 1086 */     assertEquals(3, all.size());
/* 1087 */     assertEquals(this.roadBounds, all.getBounds());
/* 1089 */     SimpleFeatureCollection expected = DataUtilities.collection(this.roadFeatures);
/* 1091 */     assertCovers("ALL", expected, all);
/* 1092 */     assertEquals(this.roadBounds, all.getBounds());
/* 1094 */     SimpleFeatureCollection some = road.getFeatures(this.rd12Filter);
/* 1095 */     assertEquals(2, some.size());
/* 1096 */     assertEquals(this.rd12Bounds, some.getBounds());
/* 1097 */     assertEquals(some.getSchema(), road.getSchema());
/* 1103 */     Query query = new Query("ROAD", this.rd12Filter, new String[] { "NAME" });
/* 1105 */     SimpleFeatureCollection half = road.getFeatures(query);
/* 1106 */     assertEquals(2, half.size());
/* 1107 */     assertEquals(1, ((SimpleFeatureType)half.getSchema()).getAttributeCount());
/* 1109 */     SimpleFeatureIterator reader = half.features();
/* 1110 */     SimpleFeatureType type = (SimpleFeatureType)half.getSchema();
/* 1111 */     reader.close();
/* 1113 */     SimpleFeatureType actual = (SimpleFeatureType)half.getSchema();
/* 1115 */     assertEquals(type.getTypeName(), actual.getTypeName());
/* 1116 */     assertEquals(type.getName().getNamespaceURI(), actual.getName().getNamespaceURI());
/* 1117 */     assertEquals(type.getAttributeCount(), actual.getAttributeCount());
/* 1119 */     for (int i = 0; i < type.getAttributeCount(); i++)
/* 1120 */       assertEquals(type.getDescriptor(i), actual.getDescriptor(i)); 
/* 1123 */     assertNull(type.getGeometryDescriptor());
/* 1124 */     assertEquals(type.getGeometryDescriptor(), actual.getGeometryDescriptor());
/* 1125 */     assertEquals(type, actual);
/* 1127 */     ReferencedEnvelope referencedEnvelope = half.getBounds();
/* 1128 */     assertEquals(new ReferencedEnvelope(1.0D, 5.0D, 0.0D, 4.0D, null), referencedEnvelope);
/*      */   }
/*      */   
/*      */   public void testGetFeatureSourceRiver() throws NoSuchElementException, IOException, IllegalAttributeException {
/* 1133 */     SimpleFeatureSource river = this.data.getFeatureSource("RIVER");
/* 1135 */     assertEquals(this.riverType, river.getSchema());
/* 1136 */     assertEquals(this.data, river.getDataStore());
/* 1138 */     SimpleFeatureCollection all = river.getFeatures();
/* 1139 */     assertEquals(2, all.size());
/* 1140 */     assertEquals(this.riverBounds, all.getBounds());
/* 1141 */     assertTrue("RIVERS", covers(all.features(), this.riverFeatures));
/* 1143 */     SimpleFeatureCollection expected = DataUtilities.collection(this.riverFeatures);
/* 1144 */     assertCovers("ALL", expected, all);
/* 1145 */     assertEquals(this.riverBounds, all.getBounds());
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreModifyFeatures1() throws IOException {
/* 1152 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1153 */     AttributeDescriptor name = this.roadType.getDescriptor("NAME");
/* 1154 */     road.modifyFeatures(name, "changed", this.rd1Filter);
/* 1156 */     SimpleFeatureCollection results = road.getFeatures(this.rd1Filter);
/* 1157 */     assertEquals("changed", ((SimpleFeature)results.features().next()).getAttribute("NAME"));
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreModifyFeatures2() throws IOException {
/* 1161 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1162 */     AttributeDescriptor name = this.roadType.getDescriptor("NAME");
/* 1163 */     road.modifyFeatures(new AttributeDescriptor[] { name }, new Object[] { "changed" }, this.rd1Filter);
/* 1166 */     SimpleFeatureCollection results = road.getFeatures(this.rd1Filter);
/* 1167 */     assertEquals("changed", ((SimpleFeature)results.features().next()).getAttribute("NAME"));
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreRemoveFeatures() throws IOException {
/* 1171 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1173 */     road.removeFeatures(this.rd1Filter);
/* 1174 */     assertEquals(0, road.getFeatures(this.rd1Filter).size());
/* 1175 */     assertEquals(this.roadFeatures.length - 1, road.getFeatures().size());
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreAddFeatures() throws IOException {
/* 1179 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = DataUtilities.reader(new SimpleFeature[] { this.newRoad });
/* 1180 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1182 */     road.addFeatures((FeatureCollection)DataUtilities.collection(reader));
/* 1183 */     assertEquals(this.roadFeatures.length + 1, road.getFeatures().size());
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreSetFeatures() throws IOException {
/* 1187 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = DataUtilities.reader(new SimpleFeature[] { this.newRoad });
/* 1188 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1190 */     road.setFeatures(reader);
/* 1191 */     assertEquals(1, road.getFeatures().size());
/*      */   }
/*      */   
/*      */   public void testGetFeatureStoreTransactionSupport() throws Exception {
/* 1196 */     Transaction t1 = new DefaultTransaction();
/* 1197 */     Transaction t2 = new DefaultTransaction();
/* 1199 */     SimpleFeatureStore road = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1200 */     SimpleFeatureStore road1 = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1201 */     SimpleFeatureStore road2 = (SimpleFeatureStore)this.data.getFeatureSource("ROAD");
/* 1203 */     road1.setTransaction(t1);
/* 1204 */     road2.setTransaction(t2);
/* 1207 */     SimpleFeature[] ORIGIONAL = this.roadFeatures;
/* 1208 */     SimpleFeature[] REMOVE = new SimpleFeature[ORIGIONAL.length - 1];
/* 1209 */     SimpleFeature[] ADD = new SimpleFeature[ORIGIONAL.length + 1];
/* 1210 */     SimpleFeature[] FINAL = new SimpleFeature[ORIGIONAL.length];
/* 1213 */     int index = 0;
/*      */     int i;
/* 1215 */     for (i = 0; i < ORIGIONAL.length; i++) {
/* 1216 */       SimpleFeature feature = ORIGIONAL[i];
/* 1218 */       if (!feature.getID().equals(this.roadFeatures[0].getID()))
/* 1219 */         REMOVE[index++] = feature; 
/*      */     } 
/* 1223 */     for (i = 0; i < ORIGIONAL.length; i++)
/* 1224 */       ADD[i] = ORIGIONAL[i]; 
/* 1227 */     ADD[i] = this.newRoad;
/* 1229 */     for (i = 0; i < REMOVE.length; i++)
/* 1230 */       FINAL[i] = REMOVE[i]; 
/* 1233 */     FINAL[i] = this.newRoad;
/* 1236 */     assertTrue(covers(road.getFeatures().features(), ORIGIONAL));
/* 1241 */     road1.removeFeatures(this.rd1Filter);
/* 1244 */     assertTrue(covers(road.getFeatures().features(), ORIGIONAL));
/* 1245 */     assertTrue(covers(road1.getFeatures().features(), REMOVE));
/* 1250 */     SimpleFeatureCollection collection = DataUtilities.collection(new SimpleFeature[] { this.newRoad });
/* 1251 */     road2.addFeatures((FeatureCollection)collection);
/* 1254 */     assertTrue(covers(road.getFeatures().features(), ORIGIONAL));
/* 1255 */     assertTrue(covers(road1.getFeatures().features(), REMOVE));
/* 1256 */     assertTrue(coversLax(road2.getFeatures().features(), ADD));
/* 1262 */     t1.commit();
/* 1266 */     assertTrue(covers(road.getFeatures().features(), REMOVE));
/* 1267 */     assertTrue(covers(road1.getFeatures().features(), REMOVE));
/* 1268 */     assertTrue(coversLax(road2.getFeatures().features(), FINAL));
/* 1273 */     t2.commit();
/* 1276 */     assertTrue(coversLax(road.getFeatures().features(), FINAL));
/* 1277 */     assertTrue(coversLax(road1.getFeatures().features(), FINAL));
/* 1278 */     assertTrue(coversLax(road2.getFeatures().features(), FINAL));
/*      */   }
/*      */   
/*      */   boolean isLocked(String typeName, String fid) {
/* 1282 */     InProcessLockingManager lockingManager = (InProcessLockingManager)this.data.getLockingManager();
/* 1285 */     return lockingManager.isLocked(typeName, fid);
/*      */   }
/*      */   
/*      */   public void testLockFeatures() throws IOException {
/* 1296 */     FeatureLock lock = FeatureLockFactory.generate("test", 3600L);
/* 1297 */     FeatureLocking<SimpleFeatureType, SimpleFeature> road = (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.data.getFeatureSource("ROAD");
/* 1298 */     road.setFeatureLock(lock);
/* 1300 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1301 */     road.lockFeatures();
/* 1302 */     assertTrue(isLocked("ROAD", this.roadFeatures[0].getID()));
/*      */   }
/*      */   
/*      */   public void testUnLockFeatures() throws IOException {
/* 1306 */     FeatureLock lock = FeatureLockFactory.generate("test", 360000L);
/* 1307 */     FeatureLocking<SimpleFeatureType, SimpleFeature> road = (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.data.getFeatureSource("ROAD");
/* 1308 */     road.setFeatureLock(lock);
/* 1309 */     road.lockFeatures();
/*      */     try {
/* 1312 */       road.unLockFeatures();
/* 1313 */       fail("unlock should fail due on AUTO_COMMIT");
/* 1314 */     } catch (IOException expected) {}
/* 1317 */     Transaction t = new DefaultTransaction();
/* 1318 */     road.setTransaction(t);
/*      */     try {
/* 1321 */       road.unLockFeatures();
/* 1322 */       fail("unlock should fail due lack of authorization");
/* 1323 */     } catch (IOException expected) {}
/* 1326 */     t.addAuthorization(lock.getAuthorization());
/* 1327 */     road.unLockFeatures();
/*      */   }
/*      */   
/*      */   public void testLockFeatureInteraction() throws IOException {
/* 1331 */     FeatureLock lockA = FeatureLockFactory.generate("LockA", 3600L);
/* 1332 */     FeatureLock lockB = FeatureLockFactory.generate("LockB", 3600L);
/* 1333 */     Transaction t1 = new DefaultTransaction();
/* 1334 */     Transaction t2 = new DefaultTransaction();
/* 1335 */     FeatureLocking<SimpleFeatureType, SimpleFeature> road1 = (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.data.getFeatureSource("ROAD");
/* 1336 */     FeatureLocking<SimpleFeatureType, SimpleFeature> road2 = (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.data.getFeatureSource("ROAD");
/* 1337 */     road1.setTransaction(t1);
/* 1338 */     road2.setTransaction(t2);
/* 1339 */     road1.setFeatureLock(lockA);
/* 1340 */     road2.setFeatureLock(lockB);
/* 1342 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1343 */     assertFalse(isLocked("ROAD", this.roadFeatures[1].getID()));
/* 1344 */     assertFalse(isLocked("ROAD", this.roadFeatures[2].getID()));
/* 1346 */     road1.lockFeatures(this.rd1Filter);
/* 1347 */     assertTrue(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1348 */     assertFalse(isLocked("ROAD", this.roadFeatures[1].getID()));
/* 1349 */     assertFalse(isLocked("ROAD", this.roadFeatures[2].getID()));
/* 1351 */     road2.lockFeatures(this.rd2Filter);
/* 1352 */     assertTrue(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1353 */     assertTrue(isLocked("ROAD", this.roadFeatures[1].getID()));
/* 1354 */     assertFalse(isLocked("ROAD", this.roadFeatures[2].getID()));
/*      */     try {
/* 1357 */       road1.unLockFeatures(this.rd1Filter);
/* 1358 */       fail("need authorization");
/* 1359 */     } catch (IOException expected) {}
/* 1362 */     t1.addAuthorization(lockA.getAuthorization());
/*      */     try {
/* 1365 */       road1.unLockFeatures(this.rd2Filter);
/* 1366 */       fail("need correct authorization");
/* 1367 */     } catch (IOException expected) {}
/* 1370 */     road1.unLockFeatures(this.rd1Filter);
/* 1371 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1372 */     assertTrue(isLocked("ROAD", this.roadFeatures[1].getID()));
/* 1373 */     assertFalse(isLocked("ROAD", this.roadFeatures[2].getID()));
/* 1375 */     t2.addAuthorization(lockB.getAuthorization());
/* 1376 */     road2.unLockFeatures(this.rd2Filter);
/* 1377 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1378 */     assertFalse(isLocked("ROAD", this.roadFeatures[1].getID()));
/* 1379 */     assertFalse(isLocked("ROAD", this.roadFeatures[2].getID()));
/*      */   }
/*      */   
/*      */   public void testGetFeatureLockingExpire() throws Exception {
/* 1383 */     FeatureLock lock = FeatureLockFactory.generate("Timed", 1L);
/* 1384 */     FeatureLocking<SimpleFeatureType, SimpleFeature> road = (FeatureLocking<SimpleFeatureType, SimpleFeature>)this.data.getFeatureSource("ROAD");
/* 1385 */     road.setFeatureLock(lock);
/* 1386 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1387 */     road.lockFeatures(this.rd1Filter);
/* 1388 */     assertTrue(isLocked("ROAD", this.roadFeatures[0].getID()));
/* 1389 */     Thread.sleep(50L);
/* 1390 */     assertFalse(isLocked("ROAD", this.roadFeatures[0].getID()));
/*      */   }
/*      */   
/*      */   public void testCreateSchema() throws Exception {
/* 1394 */     String typename = "NewType";
/* 1395 */     SimpleFeatureType t = DataUtilities.createType(typename, "*geom:Geometry");
/* 1396 */     this.data.createSchema(t);
/* 1398 */     String[] names = this.data.getTypeNames();
/* 1399 */     boolean foundNewType = false;
/* 1401 */     for (int i = 0; i < names.length; i++) {
/* 1402 */       if (names[i].equalsIgnoreCase(typename))
/* 1403 */         foundNewType = true; 
/*      */     } 
/* 1407 */     assertTrue(foundNewType);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractDataStoreTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */