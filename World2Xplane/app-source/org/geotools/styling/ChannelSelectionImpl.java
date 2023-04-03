/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.style.ChannelSelection;
/*     */ import org.opengis.style.SelectedChannelType;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class ChannelSelectionImpl implements ChannelSelection {
/*     */   private SelectedChannelType gray;
/*     */   
/*     */   private SelectedChannelType red;
/*     */   
/*     */   private SelectedChannelType blue;
/*     */   
/*     */   private SelectedChannelType green;
/*     */   
/*     */   public SelectedChannelType getGrayChannel() {
/*  39 */     return this.gray;
/*     */   }
/*     */   
/*     */   public SelectedChannelType[] getRGBChannels() {
/*  52 */     return new SelectedChannelType[] { this.red, this.green, this.blue };
/*     */   }
/*     */   
/*     */   public SelectedChannelType[] getSelectedChannels() {
/*  56 */     SelectedChannelType[] ret = null;
/*  57 */     if (this.gray != null) {
/*  58 */       ret = new SelectedChannelType[] { this.gray };
/*  59 */     } else if (this.red != null || this.green != null || this.blue != null) {
/*  60 */       ret = new SelectedChannelType[] { this.red, this.green, this.blue };
/*     */     } 
/*  63 */     return ret;
/*     */   }
/*     */   
/*     */   public void setGrayChannel(SelectedChannelType gray) {
/*  67 */     this.gray = gray;
/*     */   }
/*     */   
/*     */   public void setGrayChannel(SelectedChannelType gray) {
/*  70 */     this.gray = new SelectedChannelTypeImpl(gray);
/*     */   }
/*     */   
/*     */   public void setRGBChannels(SelectedChannelType[] channels) {
/*  74 */     if (channels.length != 3)
/*  75 */       throw new IllegalArgumentException("Three channels are required in setRGBChannels, got " + channels.length); 
/*  79 */     this.red = channels[0];
/*  80 */     this.green = channels[1];
/*  81 */     this.blue = channels[2];
/*     */   }
/*     */   
/*     */   public void setRGBChannels(SelectedChannelType red, SelectedChannelType green, SelectedChannelType blue) {
/*  86 */     this.red = red;
/*  87 */     this.green = green;
/*  88 */     this.blue = blue;
/*     */   }
/*     */   
/*     */   public void setRGBChannels(SelectedChannelType red, SelectedChannelType green, SelectedChannelType blue) {
/*  91 */     this.red = new SelectedChannelTypeImpl(red);
/*  92 */     this.green = new SelectedChannelTypeImpl(green);
/*  93 */     this.blue = new SelectedChannelTypeImpl(blue);
/*     */   }
/*     */   
/*     */   public void setSelectedChannels(SelectedChannelType[] channels) {
/*  97 */     if (channels.length == 1) {
/*  98 */       this.gray = channels[0];
/*  99 */     } else if (channels.length == 3) {
/* 100 */       this.red = channels[0];
/* 101 */       this.green = channels[1];
/* 102 */       this.blue = channels[2];
/*     */     } else {
/* 104 */       throw new IllegalArgumentException("Wrong number of elements in setSelectedChannels, expected 1 or 3, got " + channels.length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 111 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 116 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 120 */     visitor.visit(this, null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 125 */     int PRIME = 1000003;
/* 126 */     int result = 0;
/* 128 */     if (this.gray != null)
/* 129 */       result = 1000003 * result + this.gray.hashCode(); 
/* 132 */     if (this.red != null)
/* 133 */       result = 1000003 * result + this.red.hashCode(); 
/* 136 */     if (this.blue != null)
/* 137 */       result = 1000003 * result + this.blue.hashCode(); 
/* 140 */     if (this.green != null)
/* 141 */       result = 1000003 * result + this.green.hashCode(); 
/* 144 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 149 */     if (this == obj)
/* 150 */       return true; 
/* 153 */     if (obj instanceof ChannelSelectionImpl) {
/* 154 */       ChannelSelectionImpl other = (ChannelSelectionImpl)obj;
/* 156 */       return (Utilities.equals(this.gray, other.gray) && Utilities.equals(this.red, other.red) && Utilities.equals(this.blue, other.blue) && Utilities.equals(this.green, other.green));
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   static ChannelSelectionImpl cast(ChannelSelection channel) {
/* 166 */     if (channel == null)
/* 167 */       return null; 
/* 169 */     if (channel instanceof ChannelSelectionImpl)
/* 170 */       return (ChannelSelectionImpl)channel; 
/* 173 */     ChannelSelectionImpl copy = new ChannelSelectionImpl();
/* 174 */     if (channel.getGrayChannel() != null) {
/* 175 */       copy.setGrayChannel(channel.getGrayChannel());
/*     */     } else {
/* 178 */       SelectedChannelType[] rgb = channel.getRGBChannels();
/* 179 */       copy.setRGBChannels(rgb[0], rgb[1], rgb[2]);
/*     */     } 
/* 181 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ChannelSelectionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */