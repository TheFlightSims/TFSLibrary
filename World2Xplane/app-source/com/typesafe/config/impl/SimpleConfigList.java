/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigList;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ final class SimpleConfigList extends AbstractConfigValue implements ConfigList, Serializable {
/*     */   private static final long serialVersionUID = 2L;
/*     */   
/*     */   private final List<AbstractConfigValue> value;
/*     */   
/*     */   private final boolean resolved;
/*     */   
/*     */   SimpleConfigList(ConfigOrigin origin, List<AbstractConfigValue> value) {
/*  29 */     this(origin, value, ResolveStatus.fromValues(value));
/*     */   }
/*     */   
/*     */   SimpleConfigList(ConfigOrigin origin, List<AbstractConfigValue> value, ResolveStatus status) {
/*  35 */     super(origin);
/*  36 */     this.value = value;
/*  37 */     this.resolved = (status == ResolveStatus.RESOLVED);
/*  40 */     if (status != ResolveStatus.fromValues(value))
/*  41 */       throw new ConfigException.BugOrBroken("SimpleConfigList created with wrong resolve status: " + this); 
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/*  47 */     return ConfigValueType.LIST;
/*     */   }
/*     */   
/*     */   public List<Object> unwrapped() {
/*  52 */     List<Object> list = new ArrayList();
/*  53 */     for (AbstractConfigValue v : this.value)
/*  54 */       list.add(v.unwrapped()); 
/*  56 */     return list;
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/*  61 */     return ResolveStatus.fromBoolean(this.resolved);
/*     */   }
/*     */   
/*     */   private SimpleConfigList modify(AbstractConfigValue.NoExceptionsModifier modifier, ResolveStatus newResolveStatus) {
/*     */     try {
/*  66 */       return modifyMayThrow(modifier, newResolveStatus);
/*  67 */     } catch (RuntimeException e) {
/*  68 */       throw e;
/*  69 */     } catch (Exception e) {
/*  70 */       throw new ConfigException.BugOrBroken("unexpected checked exception", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private SimpleConfigList modifyMayThrow(AbstractConfigValue.Modifier modifier, ResolveStatus newResolveStatus) throws Exception {
/*  77 */     List<AbstractConfigValue> changed = null;
/*  78 */     int i = 0;
/*  79 */     for (AbstractConfigValue v : this.value) {
/*  80 */       AbstractConfigValue modified = modifier.modifyChildMayThrow(null, v);
/*  83 */       if (changed == null && modified != v) {
/*  84 */         changed = new ArrayList<AbstractConfigValue>();
/*  85 */         for (int j = 0; j < i; j++)
/*  86 */           changed.add(this.value.get(j)); 
/*     */       } 
/*  93 */       if (changed != null && modified != null)
/*  94 */         changed.add(modified); 
/*  97 */       i++;
/*     */     } 
/* 100 */     if (changed != null)
/* 101 */       return new SimpleConfigList(origin(), changed, newResolveStatus); 
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   SimpleConfigList resolveSubstitutions(final ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 109 */     if (this.resolved)
/* 110 */       return this; 
/* 112 */     if (context.isRestrictedToChild())
/* 115 */       return this; 
/*     */     try {
/* 118 */       return modifyMayThrow(new AbstractConfigValue.Modifier() {
/*     */             public AbstractConfigValue modifyChildMayThrow(String key, AbstractConfigValue v) throws AbstractConfigValue.NotPossibleToResolve {
/* 122 */               return context.resolve(v);
/*     */             }
/*     */           },  ResolveStatus.RESOLVED);
/* 126 */     } catch (NotPossibleToResolve e) {
/* 127 */       throw e;
/* 128 */     } catch (RuntimeException e) {
/* 129 */       throw e;
/* 130 */     } catch (Exception e) {
/* 131 */       throw new ConfigException.BugOrBroken("unexpected checked exception", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   SimpleConfigList relativized(final Path prefix) {
/* 138 */     return modify(new AbstractConfigValue.NoExceptionsModifier() {
/*     */           public AbstractConfigValue modifyChild(String key, AbstractConfigValue v) {
/* 141 */             return v.relativized(prefix);
/*     */           }
/*     */         },  resolveStatus());
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 149 */     return other instanceof SimpleConfigList;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 155 */     if (other instanceof SimpleConfigList)
/* 157 */       return (canEqual(other) && this.value.equals(((SimpleConfigList)other).value)); 
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 166 */     return this.value.hashCode();
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 171 */     if (this.value.isEmpty()) {
/* 172 */       sb.append("[]");
/*     */     } else {
/* 174 */       sb.append("[");
/* 175 */       if (options.getFormatted())
/* 176 */         sb.append('\n'); 
/* 177 */       for (AbstractConfigValue v : this.value) {
/* 178 */         if (options.getOriginComments()) {
/* 179 */           indent(sb, indent + 1, options);
/* 180 */           sb.append("# ");
/* 181 */           sb.append(v.origin().description());
/* 182 */           sb.append("\n");
/*     */         } 
/* 184 */         if (options.getComments())
/* 185 */           for (String comment : v.origin().comments()) {
/* 186 */             indent(sb, indent + 1, options);
/* 187 */             sb.append("# ");
/* 188 */             sb.append(comment);
/* 189 */             sb.append("\n");
/*     */           }  
/* 192 */         indent(sb, indent + 1, options);
/* 194 */         v.render(sb, indent + 1, atRoot, options);
/* 195 */         sb.append(",");
/* 196 */         if (options.getFormatted())
/* 197 */           sb.append('\n'); 
/*     */       } 
/* 199 */       sb.setLength(sb.length() - 1);
/* 200 */       if (options.getFormatted()) {
/* 201 */         sb.setLength(sb.length() - 1);
/* 202 */         sb.append('\n');
/* 203 */         indent(sb, indent, options);
/*     */       } 
/* 205 */       sb.append("]");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 211 */     return this.value.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 216 */     return this.value.containsAll(c);
/*     */   }
/*     */   
/*     */   public AbstractConfigValue get(int index) {
/* 221 */     return this.value.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object o) {
/* 226 */     return this.value.indexOf(o);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 231 */     return this.value.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<ConfigValue> iterator() {
/* 236 */     final Iterator<AbstractConfigValue> i = this.value.iterator();
/* 238 */     return new Iterator<ConfigValue>() {
/*     */         public boolean hasNext() {
/* 241 */           return i.hasNext();
/*     */         }
/*     */         
/*     */         public ConfigValue next() {
/* 246 */           return i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 251 */           throw SimpleConfigList.weAreImmutable("iterator().remove");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 258 */     return this.value.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   private static ListIterator<ConfigValue> wrapListIterator(final ListIterator<AbstractConfigValue> i) {
/* 263 */     return new ListIterator<ConfigValue>() {
/*     */         public boolean hasNext() {
/* 266 */           return i.hasNext();
/*     */         }
/*     */         
/*     */         public ConfigValue next() {
/* 271 */           return i.next();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 276 */           throw SimpleConfigList.weAreImmutable("listIterator().remove");
/*     */         }
/*     */         
/*     */         public void add(ConfigValue arg0) {
/* 281 */           throw SimpleConfigList.weAreImmutable("listIterator().add");
/*     */         }
/*     */         
/*     */         public boolean hasPrevious() {
/* 286 */           return i.hasPrevious();
/*     */         }
/*     */         
/*     */         public int nextIndex() {
/* 291 */           return i.nextIndex();
/*     */         }
/*     */         
/*     */         public ConfigValue previous() {
/* 296 */           return i.previous();
/*     */         }
/*     */         
/*     */         public int previousIndex() {
/* 301 */           return i.previousIndex();
/*     */         }
/*     */         
/*     */         public void set(ConfigValue arg0) {
/* 306 */           throw SimpleConfigList.weAreImmutable("listIterator().set");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public ListIterator<ConfigValue> listIterator() {
/* 313 */     return wrapListIterator(this.value.listIterator());
/*     */   }
/*     */   
/*     */   public ListIterator<ConfigValue> listIterator(int index) {
/* 318 */     return wrapListIterator(this.value.listIterator(index));
/*     */   }
/*     */   
/*     */   public int size() {
/* 323 */     return this.value.size();
/*     */   }
/*     */   
/*     */   public List<ConfigValue> subList(int fromIndex, int toIndex) {
/* 328 */     List<ConfigValue> list = new ArrayList<ConfigValue>();
/* 330 */     for (AbstractConfigValue v : this.value.subList(fromIndex, toIndex))
/* 331 */       list.add(v); 
/* 333 */     return list;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 338 */     return this.value.toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 343 */     return this.value.toArray(a);
/*     */   }
/*     */   
/*     */   private static UnsupportedOperationException weAreImmutable(String method) {
/* 347 */     return new UnsupportedOperationException("ConfigList is immutable, you can't call List.'" + method + "'");
/*     */   }
/*     */   
/*     */   public boolean add(ConfigValue e) {
/* 353 */     throw weAreImmutable("add");
/*     */   }
/*     */   
/*     */   public void add(int index, ConfigValue element) {
/* 358 */     throw weAreImmutable("add");
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends ConfigValue> c) {
/* 363 */     throw weAreImmutable("addAll");
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends ConfigValue> c) {
/* 368 */     throw weAreImmutable("addAll");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 373 */     throw weAreImmutable("clear");
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 378 */     throw weAreImmutable("remove");
/*     */   }
/*     */   
/*     */   public ConfigValue remove(int index) {
/* 383 */     throw weAreImmutable("remove");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 388 */     throw weAreImmutable("removeAll");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 393 */     throw weAreImmutable("retainAll");
/*     */   }
/*     */   
/*     */   public ConfigValue set(int index, ConfigValue element) {
/* 398 */     throw weAreImmutable("set");
/*     */   }
/*     */   
/*     */   protected SimpleConfigList newCopy(ConfigOrigin newOrigin) {
/* 403 */     return new SimpleConfigList(newOrigin, this.value);
/*     */   }
/*     */   
/*     */   final SimpleConfigList concatenate(SimpleConfigList other) {
/* 407 */     ConfigOrigin combinedOrigin = SimpleConfigOrigin.mergeOrigins(origin(), other.origin());
/* 408 */     List<AbstractConfigValue> combined = new ArrayList<AbstractConfigValue>(this.value.size() + other.value.size());
/* 410 */     combined.addAll(this.value);
/* 411 */     combined.addAll(other.value);
/* 412 */     return new SimpleConfigList(combinedOrigin, combined);
/*     */   }
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 417 */     return new SerializedConfigValue(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleConfigList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */