/*    */ package com.world2xplane.Network;
/*    */ 
/*    */ import com.world2xplane.DataStore.DataStore;
/*    */ import com.world2xplane.Rules.GeneratorStore;
/*    */ import com.world2xplane.Rules.Rule;
/*    */ import com.world2xplane.XPlane.DSFTile;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
/*    */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*    */ 
/*    */ public abstract class NetworkItem extends Rule {
/*    */   protected DSFTile dsfTile;
/*    */   
/*    */   protected DataStore dataStore;
/*    */   
/* 50 */   DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*    */   
/* 52 */   protected NumberFormat numberFormat = new DecimalFormat("#.###########", this.formatSymbols);
/*    */   
/* 57 */   private Integer networkNumber = Integer.valueOf(0);
/*    */   
/*    */   public NetworkItem(GeneratorStore generatorStore) {
/* 60 */     super(generatorStore);
/*    */   }
/*    */   
/*    */   public void init(DSFTile dsfTile, DataStore dataStore) {
/* 66 */     this.dsfTile = dsfTile;
/* 67 */     this.dataStore = dataStore;
/*    */   }
/*    */   
/*    */   public abstract NetworkItem initialise();
/*    */   
/*    */   public abstract void finished();
/*    */   
/*    */   public abstract boolean nodeRead(HashMap<String, String> paramHashMap, long paramLong, double paramDouble1, double paramDouble2);
/*    */   
/*    */   public abstract boolean wayRead(HashMap<String, String> paramHashMap, Osmformat.Way paramWay);
/*    */   
/*    */   public Integer getDefinitionNumber(Object osmObject) {
/* 80 */     return Integer.valueOf(0);
/*    */   }
/*    */   
/*    */   public abstract long writeToDSF(FileOutputStream paramFileOutputStream, long paramLong, NetworkDelegate paramNetworkDelegate) throws IOException;
/*    */   
/*    */   public abstract int getCount();
/*    */   
/*    */   public void setNetworkNumber(Integer networkNumber) {
/* 90 */     this.networkNumber = networkNumber;
/*    */   }
/*    */   
/*    */   public Integer getNetworkNumber() {
/* 94 */     return this.networkNumber;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Network\NetworkItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */