/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.metadata.quality.QuantitativeResult;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.MemberName;
/*     */ import org.opengis.util.Record;
/*     */ import org.opengis.util.RecordType;
/*     */ 
/*     */ public class QuantitativeResultImpl extends ResultImpl implements QuantitativeResult {
/*     */   private static final long serialVersionUID = 1230713599561236060L;
/*     */   
/*     */   private List<Record> values;
/*     */   
/*     */   private RecordType valueType;
/*     */   
/*     */   private Unit valueUnit;
/*     */   
/*     */   private InternationalString errorStatistic;
/*     */   
/*     */   public QuantitativeResultImpl() {}
/*     */   
/*     */   public QuantitativeResultImpl(QuantitativeResult source) {
/*  82 */     super((Result)source);
/*     */   }
/*     */   
/*     */   public QuantitativeResultImpl(double[] values) {
/*  89 */     setValues(values);
/*     */   }
/*     */   
/*     */   public synchronized List<Record> getValues() {
/*  96 */     return this.values = nonNullList(this.values, Record.class);
/*     */   }
/*     */   
/*     */   public synchronized void setValues(List<Record> newValues) {
/* 105 */     this.values = copyList(newValues, this.values, Record.class);
/*     */   }
/*     */   
/*     */   public synchronized void setValues(double[] newValues) {
/*     */     List<Record> records;
/* 113 */     if (newValues == null) {
/* 114 */       records = null;
/*     */     } else {
/* 116 */       Record[] data = new Record[newValues.length];
/* 117 */       for (int i = 0; i < newValues.length; i++)
/* 118 */         data[i] = new SimpleRecord(newValues[i]); 
/* 120 */       records = Arrays.asList(data);
/*     */     } 
/* 122 */     setValues(records);
/*     */   }
/*     */   
/*     */   private static final class SimpleRecord implements Record, Serializable {
/*     */     private final Map<MemberName, Object> map;
/*     */     
/*     */     public SimpleRecord(double value) {
/* 134 */       this.map = Collections.singletonMap((MemberName)null, Double.valueOf(value));
/*     */     }
/*     */     
/*     */     public RecordType getRecordType() {
/* 137 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Map<MemberName, Object> getAttributes() {
/* 141 */       return this.map;
/*     */     }
/*     */     
/*     */     public Object locate(MemberName name) {
/* 145 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void set(MemberName name, Object value) {
/* 149 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 154 */       if (other instanceof SimpleRecord)
/* 155 */         return this.map.equals(((SimpleRecord)other).map); 
/* 157 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 162 */       return this.map.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   public RecordType getValueType() {
/* 170 */     return this.valueType;
/*     */   }
/*     */   
/*     */   public synchronized void setValueType(RecordType newValue) {
/* 177 */     checkWritePermission();
/* 178 */     this.valueType = newValue;
/*     */   }
/*     */   
/*     */   public Unit getValueUnit() {
/* 185 */     return this.valueUnit;
/*     */   }
/*     */   
/*     */   public synchronized void setValueUnit(Unit newValue) {
/* 192 */     checkWritePermission();
/* 193 */     this.valueUnit = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getErrorStatistic() {
/* 200 */     return this.errorStatistic;
/*     */   }
/*     */   
/*     */   public synchronized void setErrorStatistic(InternationalString newValue) {
/* 207 */     checkWritePermission();
/* 208 */     this.errorStatistic = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\QuantitativeResultImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */