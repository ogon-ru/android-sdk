// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.ogon.sdk.model;

/**
 * Protobuf type {@code pb.TokenizationSpecification}
 */
public final class TokenizationSpecification extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb.TokenizationSpecification)
    TokenizationSpecificationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TokenizationSpecification.newBuilder() to construct.
  private TokenizationSpecification(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TokenizationSpecification() {
    type_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TokenizationSpecification();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TokenizationSpecification(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              parameters_ = com.google.protobuf.MapField.newMapField(
                  ParametersDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
            parameters__ = input.readMessage(
                ParametersDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            parameters_.getMutableMap().put(
                parameters__.getKey(), parameters__.getValue());
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 2:
        return internalGetParameters();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ru.ogon.sdk.model.TokenizationSpecification.class, ru.ogon.sdk.model.TokenizationSpecification.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private volatile java.lang.Object type_;
  /**
   * <code>string type = 1;</code>
   * @return The type.
   */
  @java.lang.Override
  public java.lang.String getType() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <code>string type = 1;</code>
   * @return The bytes for type.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTypeBytes() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PARAMETERS_FIELD_NUMBER = 2;
  private static final class ParametersDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, java.lang.String> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, java.lang.String>newDefaultInstance(
                ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_ParametersEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  private com.google.protobuf.MapField<
      java.lang.String, java.lang.String> parameters_;
  private com.google.protobuf.MapField<java.lang.String, java.lang.String>
  internalGetParameters() {
    if (parameters_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          ParametersDefaultEntryHolder.defaultEntry);
    }
    return parameters_;
  }

  public int getParametersCount() {
    return internalGetParameters().getMap().size();
  }
  /**
   * <code>map&lt;string, string&gt; parameters = 2;</code>
   */

  @java.lang.Override
  public boolean containsParameters(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetParameters().getMap().containsKey(key);
  }
  /**
   * Use {@link #getParametersMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, java.lang.String> getParameters() {
    return getParametersMap();
  }
  /**
   * <code>map&lt;string, string&gt; parameters = 2;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, java.lang.String> getParametersMap() {
    return internalGetParameters().getMap();
  }
  /**
   * <code>map&lt;string, string&gt; parameters = 2;</code>
   */
  @java.lang.Override

  public java.lang.String getParametersOrDefault(
      java.lang.String key,
      java.lang.String defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetParameters().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, string&gt; parameters = 2;</code>
   */
  @java.lang.Override

  public java.lang.String getParametersOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetParameters().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, type_);
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetParameters(),
        ParametersDefaultEntryHolder.defaultEntry,
        2);
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, type_);
    }
    for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
         : internalGetParameters().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
      parameters__ = ParametersDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, parameters__);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof ru.ogon.sdk.model.TokenizationSpecification)) {
      return super.equals(obj);
    }
    ru.ogon.sdk.model.TokenizationSpecification other = (ru.ogon.sdk.model.TokenizationSpecification) obj;

    if (!getType()
        .equals(other.getType())) return false;
    if (!internalGetParameters().equals(
        other.internalGetParameters())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    if (!internalGetParameters().getMap().isEmpty()) {
      hash = (37 * hash) + PARAMETERS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetParameters().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.TokenizationSpecification parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(ru.ogon.sdk.model.TokenizationSpecification prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code pb.TokenizationSpecification}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb.TokenizationSpecification)
      ru.ogon.sdk.model.TokenizationSpecificationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetParameters();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMutableParameters();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ru.ogon.sdk.model.TokenizationSpecification.class, ru.ogon.sdk.model.TokenizationSpecification.Builder.class);
    }

    // Construct using ru.ogon.sdk.model.TokenizationSpecification.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      type_ = "";

      internalGetMutableParameters().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_TokenizationSpecification_descriptor;
    }

    @java.lang.Override
    public ru.ogon.sdk.model.TokenizationSpecification getDefaultInstanceForType() {
      return ru.ogon.sdk.model.TokenizationSpecification.getDefaultInstance();
    }

    @java.lang.Override
    public ru.ogon.sdk.model.TokenizationSpecification build() {
      ru.ogon.sdk.model.TokenizationSpecification result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public ru.ogon.sdk.model.TokenizationSpecification buildPartial() {
      ru.ogon.sdk.model.TokenizationSpecification result = new ru.ogon.sdk.model.TokenizationSpecification(this);
      int from_bitField0_ = bitField0_;
      result.type_ = type_;
      result.parameters_ = internalGetParameters();
      result.parameters_.makeImmutable();
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ru.ogon.sdk.model.TokenizationSpecification) {
        return mergeFrom((ru.ogon.sdk.model.TokenizationSpecification)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ru.ogon.sdk.model.TokenizationSpecification other) {
      if (other == ru.ogon.sdk.model.TokenizationSpecification.getDefaultInstance()) return this;
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      internalGetMutableParameters().mergeFrom(
          other.internalGetParameters());
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      ru.ogon.sdk.model.TokenizationSpecification parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ru.ogon.sdk.model.TokenizationSpecification) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object type_ = "";
    /**
     * <code>string type = 1;</code>
     * @return The type.
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string type = 1;</code>
     * @return The bytes for type.
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string type = 1;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string type = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>string type = 1;</code>
     * @param value The bytes for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> parameters_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetParameters() {
      if (parameters_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            ParametersDefaultEntryHolder.defaultEntry);
      }
      return parameters_;
    }
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetMutableParameters() {
      onChanged();;
      if (parameters_ == null) {
        parameters_ = com.google.protobuf.MapField.newMapField(
            ParametersDefaultEntryHolder.defaultEntry);
      }
      if (!parameters_.isMutable()) {
        parameters_ = parameters_.copy();
      }
      return parameters_;
    }

    public int getParametersCount() {
      return internalGetParameters().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */

    @java.lang.Override
    public boolean containsParameters(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetParameters().getMap().containsKey(key);
    }
    /**
     * Use {@link #getParametersMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getParameters() {
      return getParametersMap();
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getParametersMap() {
      return internalGetParameters().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */
    @java.lang.Override

    public java.lang.String getParametersOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetParameters().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */
    @java.lang.Override

    public java.lang.String getParametersOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetParameters().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearParameters() {
      internalGetMutableParameters().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */

    public Builder removeParameters(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableParameters().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String>
    getMutableParameters() {
      return internalGetMutableParameters().getMutableMap();
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */
    public Builder putParameters(
        java.lang.String key,
        java.lang.String value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableParameters().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; parameters = 2;</code>
     */

    public Builder putAllParameters(
        java.util.Map<java.lang.String, java.lang.String> values) {
      internalGetMutableParameters().getMutableMap()
          .putAll(values);
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:pb.TokenizationSpecification)
  }

  // @@protoc_insertion_point(class_scope:pb.TokenizationSpecification)
  private static final ru.ogon.sdk.model.TokenizationSpecification DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ru.ogon.sdk.model.TokenizationSpecification();
  }

  public static ru.ogon.sdk.model.TokenizationSpecification getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TokenizationSpecification>
      PARSER = new com.google.protobuf.AbstractParser<TokenizationSpecification>() {
    @java.lang.Override
    public TokenizationSpecification parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TokenizationSpecification(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TokenizationSpecification> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TokenizationSpecification> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public ru.ogon.sdk.model.TokenizationSpecification getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

