/*     */ package org.postgresql.core.v3;
/*     */ 
/*     */ import java.lang.ref.PhantomReference;
/*     */ import org.postgresql.core.Field;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.Utils;
/*     */ 
/*     */ class SimpleQuery implements V3Query {
/*     */   private final String[] fragments;
/*     */   
/*     */   private final ProtocolConnectionImpl protoConnection;
/*     */   
/*     */   private String statementName;
/*     */   
/*     */   private byte[] encodedStatementName;
/*     */   
/*     */   private Field[] fields;
/*     */   
/*     */   private boolean portalDescribed;
/*     */   
/*     */   private boolean statementDescribed;
/*     */   
/*     */   private PhantomReference cleanupRef;
/*     */   
/*     */   private int[] preparedTypes;
/*     */   
/*     */   SimpleQuery(String[] fragments, ProtocolConnectionImpl protoConnection) {
/*  28 */     this.fragments = fragments;
/*  29 */     this.protoConnection = protoConnection;
/*     */   }
/*     */   
/*     */   public ParameterList createParameterList() {
/*  33 */     if (this.fragments.length == 1)
/*  34 */       return NO_PARAMETERS; 
/*  36 */     return new SimpleParameterList(this.fragments.length - 1, this.protoConnection);
/*     */   }
/*     */   
/*     */   public String toString(ParameterList parameters) {
/*  40 */     StringBuffer sbuf = new StringBuffer(this.fragments[0]);
/*  41 */     for (int i = 1; i < this.fragments.length; i++) {
/*  43 */       if (parameters == null) {
/*  44 */         sbuf.append('?');
/*     */       } else {
/*  46 */         sbuf.append(parameters.toString(i));
/*     */       } 
/*  47 */       sbuf.append(this.fragments[i]);
/*     */     } 
/*  49 */     return sbuf.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return toString(null);
/*     */   }
/*     */   
/*     */   public void close() {
/*  57 */     unprepare();
/*     */   }
/*     */   
/*     */   public SimpleQuery[] getSubqueries() {
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   String[] getFragments() {
/*  73 */     return this.fragments;
/*     */   }
/*     */   
/*     */   void setStatementName(String statementName) {
/*  79 */     this.statementName = statementName;
/*  80 */     this.encodedStatementName = Utils.encodeUTF8(statementName);
/*     */   }
/*     */   
/*     */   void setStatementTypes(int[] paramTypes) {
/*  84 */     this.preparedTypes = paramTypes;
/*     */   }
/*     */   
/*     */   int[] getStatementTypes() {
/*  88 */     return this.preparedTypes;
/*     */   }
/*     */   
/*     */   String getStatementName() {
/*  92 */     return this.statementName;
/*     */   }
/*     */   
/*     */   boolean isPreparedFor(int[] paramTypes) {
/*  96 */     if (this.statementName == null)
/*  97 */       return false; 
/* 100 */     for (int i = 0; i < paramTypes.length; i++) {
/* 101 */       if (paramTypes[i] != 0 && paramTypes[i] != this.preparedTypes[i])
/* 102 */         return false; 
/*     */     } 
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   boolean hasUnresolvedTypes() {
/* 108 */     if (this.preparedTypes == null)
/* 109 */       return true; 
/* 111 */     for (int i = 0; i < this.preparedTypes.length; i++) {
/* 112 */       if (this.preparedTypes[i] == 0)
/* 113 */         return true; 
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   byte[] getEncodedStatementName() {
/* 120 */     return this.encodedStatementName;
/*     */   }
/*     */   
/*     */   void setFields(Field[] fields) {
/* 124 */     this.fields = fields;
/*     */   }
/*     */   
/*     */   Field[] getFields() {
/* 127 */     return this.fields;
/*     */   }
/*     */   
/*     */   boolean isPortalDescribed() {
/* 132 */     return this.portalDescribed;
/*     */   }
/*     */   
/*     */   void setPortalDescribed(boolean portalDescribed) {
/* 135 */     this.portalDescribed = portalDescribed;
/*     */   }
/*     */   
/*     */   boolean isStatementDescribed() {
/* 141 */     return this.statementDescribed;
/*     */   }
/*     */   
/*     */   void setStatementDescribed(boolean statementDescribed) {
/* 144 */     this.statementDescribed = statementDescribed;
/*     */   }
/*     */   
/*     */   void setCleanupRef(PhantomReference cleanupRef) {
/* 148 */     if (this.cleanupRef != null) {
/* 149 */       this.cleanupRef.clear();
/* 150 */       this.cleanupRef.enqueue();
/*     */     } 
/* 152 */     this.cleanupRef = cleanupRef;
/*     */   }
/*     */   
/*     */   void unprepare() {
/* 156 */     if (this.cleanupRef != null) {
/* 158 */       this.cleanupRef.clear();
/* 159 */       this.cleanupRef.enqueue();
/* 160 */       this.cleanupRef = null;
/*     */     } 
/* 163 */     this.statementName = null;
/* 164 */     this.encodedStatementName = null;
/* 165 */     this.fields = null;
/* 166 */     this.portalDescribed = false;
/* 167 */     this.statementDescribed = false;
/*     */   }
/*     */   
/* 180 */   static final SimpleParameterList NO_PARAMETERS = new SimpleParameterList(0, null);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\SimpleQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */