/*     */ package com.google.common.net;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Strings;
/*     */ import java.io.Serializable;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ 
/*     */ @Beta
/*     */ @Immutable
/*     */ @GwtCompatible
/*     */ public final class HostAndPort implements Serializable {
/*     */   private static final int NO_PORT = -1;
/*     */   
/*     */   private final String host;
/*     */   
/*     */   private final int port;
/*     */   
/*     */   private final boolean hasBracketlessColons;
/*     */   
/*     */   private static final long serialVersionUID = 0L;
/*     */   
/*     */   private HostAndPort(String host, int port, boolean hasBracketlessColons) {
/*  81 */     this.host = host;
/*  82 */     this.port = port;
/*  83 */     this.hasBracketlessColons = hasBracketlessColons;
/*     */   }
/*     */   
/*     */   public String getHostText() {
/*  94 */     return this.host;
/*     */   }
/*     */   
/*     */   public boolean hasPort() {
/*  99 */     return (this.port >= 0);
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 110 */     Preconditions.checkState(hasPort());
/* 111 */     return this.port;
/*     */   }
/*     */   
/*     */   public int getPortOrDefault(int defaultPort) {
/* 118 */     return hasPort() ? this.port : defaultPort;
/*     */   }
/*     */   
/*     */   public static HostAndPort fromParts(String host, int port) {
/* 134 */     Preconditions.checkArgument(isValidPort(port), "Port out of range: %s", new Object[] { Integer.valueOf(port) });
/* 135 */     HostAndPort parsedHost = fromString(host);
/* 136 */     Preconditions.checkArgument(!parsedHost.hasPort(), "Host has a port: %s", new Object[] { host });
/* 137 */     return new HostAndPort(parsedHost.host, port, parsedHost.hasBracketlessColons);
/*     */   }
/*     */   
/*     */   public static HostAndPort fromHost(String host) {
/* 152 */     HostAndPort parsedHost = fromString(host);
/* 153 */     Preconditions.checkArgument(!parsedHost.hasPort(), "Host has a port: %s", new Object[] { host });
/* 154 */     return parsedHost;
/*     */   }
/*     */   
/*     */   public static HostAndPort fromString(String hostPortString) {
/*     */     String host;
/* 168 */     Preconditions.checkNotNull(hostPortString);
/* 170 */     String portString = null;
/* 171 */     boolean hasBracketlessColons = false;
/* 173 */     if (hostPortString.startsWith("[")) {
/* 174 */       String[] hostAndPort = getHostAndPortFromBracketedHost(hostPortString);
/* 175 */       host = hostAndPort[0];
/* 176 */       portString = hostAndPort[1];
/*     */     } else {
/* 178 */       int colonPos = hostPortString.indexOf(':');
/* 179 */       if (colonPos >= 0 && hostPortString.indexOf(':', colonPos + 1) == -1) {
/* 181 */         host = hostPortString.substring(0, colonPos);
/* 182 */         portString = hostPortString.substring(colonPos + 1);
/*     */       } else {
/* 185 */         host = hostPortString;
/* 186 */         hasBracketlessColons = (colonPos >= 0);
/*     */       } 
/*     */     } 
/* 190 */     int port = -1;
/* 191 */     if (!Strings.isNullOrEmpty(portString)) {
/* 194 */       Preconditions.checkArgument(!portString.startsWith("+"), "Unparseable port number: %s", new Object[] { hostPortString });
/*     */       try {
/* 196 */         port = Integer.parseInt(portString);
/* 197 */       } catch (NumberFormatException e) {
/* 198 */         throw new IllegalArgumentException("Unparseable port number: " + hostPortString);
/*     */       } 
/* 200 */       Preconditions.checkArgument(isValidPort(port), "Port number out of range: %s", new Object[] { hostPortString });
/*     */     } 
/* 203 */     return new HostAndPort(host, port, hasBracketlessColons);
/*     */   }
/*     */   
/*     */   private static String[] getHostAndPortFromBracketedHost(String hostPortString) {
/* 214 */     int colonIndex = 0;
/* 215 */     int closeBracketIndex = 0;
/* 216 */     boolean hasPort = false;
/* 217 */     Preconditions.checkArgument((hostPortString.charAt(0) == '['), "Bracketed host-port string must start with a bracket: %s", new Object[] { hostPortString });
/* 219 */     colonIndex = hostPortString.indexOf(':');
/* 220 */     closeBracketIndex = hostPortString.lastIndexOf(']');
/* 221 */     Preconditions.checkArgument((colonIndex > -1 && closeBracketIndex > colonIndex), "Invalid bracketed host/port: %s", new Object[] { hostPortString });
/* 224 */     String host = hostPortString.substring(1, closeBracketIndex);
/* 225 */     if (closeBracketIndex + 1 == hostPortString.length())
/* 226 */       return new String[] { host, "" }; 
/* 228 */     Preconditions.checkArgument((hostPortString.charAt(closeBracketIndex + 1) == ':'), "Only a colon may follow a close bracket: %s", new Object[] { hostPortString });
/* 230 */     for (int i = closeBracketIndex + 2; i < hostPortString.length(); i++) {
/* 231 */       Preconditions.checkArgument(Character.isDigit(hostPortString.charAt(i)), "Port must be numeric: %s", new Object[] { hostPortString });
/*     */     } 
/* 234 */     return new String[] { host, hostPortString.substring(closeBracketIndex + 2) };
/*     */   }
/*     */   
/*     */   public HostAndPort withDefaultPort(int defaultPort) {
/* 249 */     Preconditions.checkArgument(isValidPort(defaultPort));
/* 250 */     if (hasPort() || this.port == defaultPort)
/* 251 */       return this; 
/* 253 */     return new HostAndPort(this.host, defaultPort, this.hasBracketlessColons);
/*     */   }
/*     */   
/*     */   public HostAndPort requireBracketsForIPv6() {
/* 272 */     Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", new Object[] { this.host });
/* 273 */     return this;
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 278 */     if (this == other)
/* 279 */       return true; 
/* 281 */     if (other instanceof HostAndPort) {
/* 282 */       HostAndPort that = (HostAndPort)other;
/* 283 */       return (Objects.equal(this.host, that.host) && this.port == that.port && this.hasBracketlessColons == that.hasBracketlessColons);
/*     */     } 
/* 287 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 292 */     return Objects.hashCode(new Object[] { this.host, Integer.valueOf(this.port), Boolean.valueOf(this.hasBracketlessColons) });
/*     */   }
/*     */   
/*     */   public String toString() {
/* 299 */     StringBuilder builder = new StringBuilder(this.host.length() + 8);
/* 300 */     if (this.host.indexOf(':') >= 0) {
/* 301 */       builder.append('[').append(this.host).append(']');
/*     */     } else {
/* 303 */       builder.append(this.host);
/*     */     } 
/* 305 */     if (hasPort())
/* 306 */       builder.append(':').append(this.port); 
/* 308 */     return builder.toString();
/*     */   }
/*     */   
/*     */   private static boolean isValidPort(int port) {
/* 313 */     return (port >= 0 && port <= 65535);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\net\HostAndPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */