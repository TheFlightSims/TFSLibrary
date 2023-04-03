/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ final class Path {
/*     */   private final String first;
/*     */   
/*     */   private final Path remainder;
/*     */   
/*     */   Path(String first, Path remainder) {
/*  17 */     this.first = first;
/*  18 */     this.remainder = remainder;
/*     */   }
/*     */   
/*     */   Path(String... elements) {
/*  22 */     if (elements.length == 0)
/*  23 */       throw new ConfigException.BugOrBroken("empty path"); 
/*  24 */     this.first = elements[0];
/*  25 */     if (elements.length > 1) {
/*  26 */       PathBuilder pb = new PathBuilder();
/*  27 */       for (int i = 1; i < elements.length; i++)
/*  28 */         pb.appendKey(elements[i]); 
/*  30 */       this.remainder = pb.result();
/*     */     } else {
/*  32 */       this.remainder = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   Path(List<Path> pathsToConcat) {
/*  38 */     this(pathsToConcat.iterator());
/*     */   }
/*     */   
/*     */   Path(Iterator<Path> i) {
/*  43 */     if (!i.hasNext())
/*  44 */       throw new ConfigException.BugOrBroken("empty path"); 
/*  46 */     Path firstPath = i.next();
/*  47 */     this.first = firstPath.first;
/*  49 */     PathBuilder pb = new PathBuilder();
/*  50 */     if (firstPath.remainder != null)
/*  51 */       pb.appendPath(firstPath.remainder); 
/*  53 */     while (i.hasNext())
/*  54 */       pb.appendPath(i.next()); 
/*  56 */     this.remainder = pb.result();
/*     */   }
/*     */   
/*     */   String first() {
/*  60 */     return this.first;
/*     */   }
/*     */   
/*     */   Path remainder() {
/*  68 */     return this.remainder;
/*     */   }
/*     */   
/*     */   Path parent() {
/*  76 */     if (this.remainder == null)
/*  77 */       return null; 
/*  79 */     PathBuilder pb = new PathBuilder();
/*  80 */     Path p = this;
/*  81 */     while (p.remainder != null) {
/*  82 */       pb.appendKey(p.first);
/*  83 */       p = p.remainder;
/*     */     } 
/*  85 */     return pb.result();
/*     */   }
/*     */   
/*     */   String last() {
/*  93 */     Path p = this;
/*  94 */     while (p.remainder != null)
/*  95 */       p = p.remainder; 
/*  97 */     return p.first;
/*     */   }
/*     */   
/*     */   Path prepend(Path toPrepend) {
/* 101 */     PathBuilder pb = new PathBuilder();
/* 102 */     pb.appendPath(toPrepend);
/* 103 */     pb.appendPath(this);
/* 104 */     return pb.result();
/*     */   }
/*     */   
/*     */   int length() {
/* 108 */     int count = 1;
/* 109 */     Path p = this.remainder;
/* 110 */     while (p != null) {
/* 111 */       count++;
/* 112 */       p = p.remainder;
/*     */     } 
/* 114 */     return count;
/*     */   }
/*     */   
/*     */   Path subPath(int removeFromFront) {
/* 118 */     int count = removeFromFront;
/* 119 */     Path p = this;
/* 120 */     while (p != null && count > 0) {
/* 121 */       count--;
/* 122 */       p = p.remainder;
/*     */     } 
/* 124 */     return p;
/*     */   }
/*     */   
/*     */   Path subPath(int firstIndex, int lastIndex) {
/* 128 */     if (lastIndex < firstIndex)
/* 129 */       throw new ConfigException.BugOrBroken("bad call to subPath"); 
/* 131 */     Path from = subPath(firstIndex);
/* 132 */     PathBuilder pb = new PathBuilder();
/* 133 */     int count = lastIndex - firstIndex;
/* 134 */     while (count > 0) {
/* 135 */       count--;
/* 136 */       pb.appendKey(from.first());
/* 137 */       from = from.remainder();
/* 138 */       if (from == null)
/* 139 */         throw new ConfigException.BugOrBroken("subPath lastIndex out of range " + lastIndex); 
/*     */     } 
/* 141 */     return pb.result();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 146 */     if (other instanceof Path) {
/* 147 */       Path that = (Path)other;
/* 148 */       return (this.first.equals(that.first) && ConfigImplUtil.equalsHandlingNull(this.remainder, that.remainder));
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 158 */     return 41 * (41 + this.first.hashCode()) + ((this.remainder == null) ? 0 : this.remainder.hashCode());
/*     */   }
/*     */   
/*     */   static boolean hasFunkyChars(String s) {
/* 165 */     int length = s.length();
/* 167 */     if (length == 0)
/* 168 */       return false; 
/* 174 */     char first = s.charAt(0);
/* 175 */     if (!Character.isLetter(first))
/* 176 */       return true; 
/* 178 */     for (int i = 1; i < length; ) {
/* 179 */       char c = s.charAt(i);
/* 181 */       if (Character.isLetterOrDigit(c) || c == '-' || c == '_') {
/*     */         i++;
/*     */         continue;
/*     */       } 
/* 184 */       return true;
/*     */     } 
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   private void appendToStringBuilder(StringBuilder sb) {
/* 190 */     if (hasFunkyChars(this.first) || this.first.isEmpty()) {
/* 191 */       sb.append(ConfigImplUtil.renderJsonString(this.first));
/*     */     } else {
/* 193 */       sb.append(this.first);
/*     */     } 
/* 194 */     if (this.remainder != null) {
/* 195 */       sb.append(".");
/* 196 */       this.remainder.appendToStringBuilder(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 202 */     StringBuilder sb = new StringBuilder();
/* 203 */     sb.append("Path(");
/* 204 */     appendToStringBuilder(sb);
/* 205 */     sb.append(")");
/* 206 */     return sb.toString();
/*     */   }
/*     */   
/*     */   String render() {
/* 214 */     StringBuilder sb = new StringBuilder();
/* 215 */     appendToStringBuilder(sb);
/* 216 */     return sb.toString();
/*     */   }
/*     */   
/*     */   static Path newKey(String key) {
/* 220 */     return new Path(key, null);
/*     */   }
/*     */   
/*     */   static Path newPath(String path) {
/* 224 */     return Parser.parsePath(path);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Path.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */