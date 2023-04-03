/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ 
/*     */ public class DeferredProperty extends DeferredData implements PropertyChangeListener {
/*     */   protected transient PropertySource propertySource;
/*     */   
/*     */   protected String propertyName;
/*     */   
/*     */   public DeferredProperty(PropertySource propertySource, String propertyName, Class propertyClass) {
/*  57 */     super(propertyClass);
/*  59 */     if (propertySource == null || propertyName == null)
/*  60 */       throw new IllegalArgumentException(JaiI18N.getString("DeferredData0")); 
/*  63 */     String[] propertyNames = propertySource.getPropertyNames();
/*  64 */     boolean isPropertyEmitted = false;
/*  65 */     if (propertyNames != null) {
/*  66 */       int length = propertyNames.length;
/*  67 */       for (int i = 0; i < length; i++) {
/*  68 */         if (propertyName.equalsIgnoreCase(propertyNames[i])) {
/*  69 */           isPropertyEmitted = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     if (!isPropertyEmitted)
/*  76 */       throw new IllegalArgumentException(JaiI18N.getString("DeferredProperty0")); 
/*  79 */     if (propertySource instanceof PropertyChangeEmitter) {
/*  80 */       PropertyChangeEmitter pce = (PropertyChangeEmitter)propertySource;
/*  82 */       pce.addPropertyChangeListener(propertyName, this);
/*     */     } 
/*  85 */     this.propertySource = propertySource;
/*  86 */     this.propertyName = propertyName;
/*     */   }
/*     */   
/*     */   public PropertySource getPropertySource() {
/*  93 */     return this.propertySource;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 100 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   protected Object computeData() {
/* 108 */     return this.propertySource.getProperty(this.propertyName);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 123 */     if (obj == null || !(obj instanceof DeferredProperty))
/* 124 */       return false; 
/* 127 */     DeferredProperty dp = (DeferredProperty)obj;
/* 129 */     return (this.propertyName.equalsIgnoreCase(dp.getPropertyName()) && this.propertySource.equals(dp.getPropertySource()) && (!isValid() || !dp.isValid() || this.data.equals(dp.getData())));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 138 */     return this.propertySource.hashCode() ^ this.propertyName.toLowerCase().hashCode();
/*     */   }
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent evt) {
/* 169 */     if (evt.getSource() == this.propertySource)
/* 170 */       if (evt instanceof RenderingChangeEvent) {
/* 171 */         setData(null);
/* 172 */       } else if (evt instanceof PropertySourceChangeEvent && this.propertyName.equalsIgnoreCase(evt.getPropertyName())) {
/* 174 */         Object newValue = evt.getNewValue();
/* 175 */         setData((newValue == Image.UndefinedProperty) ? null : newValue);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\DeferredProperty.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */