/*    */ package com.ctc.wstx.evt;
/*    */ 
/*    */ import com.ctc.wstx.dtd.DTDSubset;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.stream.Location;
/*    */ import org.codehaus.stax2.ri.evt.DTDEventImpl;
/*    */ 
/*    */ public class WDTD extends DTDEventImpl {
/*    */   final DTDSubset mSubset;
/*    */   
/* 31 */   List mEntities = null;
/*    */   
/* 33 */   List mNotations = null;
/*    */   
/*    */   public WDTD(Location loc, String rootName, String sysId, String pubId, String intSubset, DTDSubset dtdSubset) {
/* 45 */     super(loc, rootName, sysId, pubId, intSubset, dtdSubset);
/* 46 */     this.mSubset = dtdSubset;
/*    */   }
/*    */   
/*    */   public WDTD(Location loc, String rootName, String sysId, String pubId, String intSubset) {
/* 52 */     this(loc, rootName, sysId, pubId, intSubset, null);
/*    */   }
/*    */   
/*    */   public WDTD(Location loc, String rootName, String intSubset) {
/* 60 */     this(loc, rootName, null, null, intSubset, null);
/*    */   }
/*    */   
/*    */   public WDTD(Location loc, String fullText) {
/* 65 */     super(loc, fullText);
/* 66 */     this.mSubset = null;
/*    */   }
/*    */   
/*    */   public List getEntities() {
/* 77 */     if (this.mEntities == null && this.mSubset != null)
/* 82 */       this.mEntities = new ArrayList(this.mSubset.getGeneralEntityList()); 
/* 84 */     return this.mEntities;
/*    */   }
/*    */   
/*    */   public List getNotations() {
/* 88 */     if (this.mNotations == null && this.mSubset != null)
/* 89 */       this.mNotations = new ArrayList(this.mSubset.getNotationList()); 
/* 91 */     return this.mNotations;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\WDTD.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */