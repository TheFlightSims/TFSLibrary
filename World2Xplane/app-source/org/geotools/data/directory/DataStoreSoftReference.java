/*    */ package org.geotools.data.directory;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import org.geotools.data.DataStore;
/*    */ import org.geotools.util.WeakCollectionCleaner;
/*    */ 
/*    */ public class DataStoreSoftReference extends SoftReference<DataStore> {
/*    */   public DataStoreSoftReference(DataStore referent) {
/* 16 */     super(referent, WeakCollectionCleaner.DEFAULT.getReferenceQueue());
/*    */   }
/*    */   
/*    */   public void clear() {
/* 21 */     DataStore store = get();
/* 22 */     if (store != null)
/* 23 */       store.dispose(); 
/* 24 */     super.clear();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DataStoreSoftReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */