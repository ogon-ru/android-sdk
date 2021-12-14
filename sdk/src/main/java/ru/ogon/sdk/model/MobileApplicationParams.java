// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.ogon.sdk.model;

/**
 * Protobuf type {@code pb.MobileApplicationParams}
 */
public final class MobileApplicationParams extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb.MobileApplicationParams)
    MobileApplicationParamsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MobileApplicationParams.newBuilder() to construct.
  private MobileApplicationParams(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MobileApplicationParams() {
    userId_ = "";
    deviceId_ = "";
    confirmationId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MobileApplicationParams();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MobileApplicationParams(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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

            userId_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            deviceId_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            confirmationId_ = s;
            break;
          }
          case 32: {

            passwordEnabled_ = input.readBool();
            break;
          }
          case 40: {

            biometryEnabled_ = input.readBool();
            break;
          }
          case 48: {

            biometryAvailable_ = input.readBool();
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
    return ru.ogon.sdk.model.MobileModel.internal_static_pb_MobileApplicationParams_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ru.ogon.sdk.model.MobileModel.internal_static_pb_MobileApplicationParams_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ru.ogon.sdk.model.MobileApplicationParams.class, ru.ogon.sdk.model.MobileApplicationParams.Builder.class);
  }

  public static final int USER_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object userId_;
  /**
   * <code>string user_id = 1;</code>
   * @return The userId.
   */
  @java.lang.Override
  public java.lang.String getUserId() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userId_ = s;
      return s;
    }
  }
  /**
   * <code>string user_id = 1;</code>
   * @return The bytes for userId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUserIdBytes() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEVICE_ID_FIELD_NUMBER = 2;
  private volatile java.lang.Object deviceId_;
  /**
   * <code>string device_id = 2;</code>
   * @return The deviceId.
   */
  @java.lang.Override
  public java.lang.String getDeviceId() {
    java.lang.Object ref = deviceId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      deviceId_ = s;
      return s;
    }
  }
  /**
   * <code>string device_id = 2;</code>
   * @return The bytes for deviceId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDeviceIdBytes() {
    java.lang.Object ref = deviceId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      deviceId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONFIRMATION_ID_FIELD_NUMBER = 3;
  private volatile java.lang.Object confirmationId_;
  /**
   * <code>string confirmation_id = 3;</code>
   * @return The confirmationId.
   */
  @java.lang.Override
  public java.lang.String getConfirmationId() {
    java.lang.Object ref = confirmationId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      confirmationId_ = s;
      return s;
    }
  }
  /**
   * <code>string confirmation_id = 3;</code>
   * @return The bytes for confirmationId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getConfirmationIdBytes() {
    java.lang.Object ref = confirmationId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      confirmationId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PASSWORD_ENABLED_FIELD_NUMBER = 4;
  private boolean passwordEnabled_;
  /**
   * <code>bool password_enabled = 4;</code>
   * @return The passwordEnabled.
   */
  @java.lang.Override
  public boolean getPasswordEnabled() {
    return passwordEnabled_;
  }

  public static final int BIOMETRY_ENABLED_FIELD_NUMBER = 5;
  private boolean biometryEnabled_;
  /**
   * <code>bool biometry_enabled = 5;</code>
   * @return The biometryEnabled.
   */
  @java.lang.Override
  public boolean getBiometryEnabled() {
    return biometryEnabled_;
  }

  public static final int BIOMETRY_AVAILABLE_FIELD_NUMBER = 6;
  private boolean biometryAvailable_;
  /**
   * <code>bool biometry_available = 6;</code>
   * @return The biometryAvailable.
   */
  @java.lang.Override
  public boolean getBiometryAvailable() {
    return biometryAvailable_;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(deviceId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, deviceId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(confirmationId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, confirmationId_);
    }
    if (passwordEnabled_ != false) {
      output.writeBool(4, passwordEnabled_);
    }
    if (biometryEnabled_ != false) {
      output.writeBool(5, biometryEnabled_);
    }
    if (biometryAvailable_ != false) {
      output.writeBool(6, biometryAvailable_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(deviceId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, deviceId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(confirmationId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, confirmationId_);
    }
    if (passwordEnabled_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, passwordEnabled_);
    }
    if (biometryEnabled_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, biometryEnabled_);
    }
    if (biometryAvailable_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(6, biometryAvailable_);
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
    if (!(obj instanceof ru.ogon.sdk.model.MobileApplicationParams)) {
      return super.equals(obj);
    }
    ru.ogon.sdk.model.MobileApplicationParams other = (ru.ogon.sdk.model.MobileApplicationParams) obj;

    if (!getUserId()
        .equals(other.getUserId())) return false;
    if (!getDeviceId()
        .equals(other.getDeviceId())) return false;
    if (!getConfirmationId()
        .equals(other.getConfirmationId())) return false;
    if (getPasswordEnabled()
        != other.getPasswordEnabled()) return false;
    if (getBiometryEnabled()
        != other.getBiometryEnabled()) return false;
    if (getBiometryAvailable()
        != other.getBiometryAvailable()) return false;
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
    hash = (37 * hash) + USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getUserId().hashCode();
    hash = (37 * hash) + DEVICE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getDeviceId().hashCode();
    hash = (37 * hash) + CONFIRMATION_ID_FIELD_NUMBER;
    hash = (53 * hash) + getConfirmationId().hashCode();
    hash = (37 * hash) + PASSWORD_ENABLED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getPasswordEnabled());
    hash = (37 * hash) + BIOMETRY_ENABLED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getBiometryEnabled());
    hash = (37 * hash) + BIOMETRY_AVAILABLE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getBiometryAvailable());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ru.ogon.sdk.model.MobileApplicationParams parseFrom(
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
  public static Builder newBuilder(ru.ogon.sdk.model.MobileApplicationParams prototype) {
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
   * Protobuf type {@code pb.MobileApplicationParams}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb.MobileApplicationParams)
      ru.ogon.sdk.model.MobileApplicationParamsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_MobileApplicationParams_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_MobileApplicationParams_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ru.ogon.sdk.model.MobileApplicationParams.class, ru.ogon.sdk.model.MobileApplicationParams.Builder.class);
    }

    // Construct using ru.ogon.sdk.model.MobileApplicationParams.newBuilder()
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
      userId_ = "";

      deviceId_ = "";

      confirmationId_ = "";

      passwordEnabled_ = false;

      biometryEnabled_ = false;

      biometryAvailable_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ru.ogon.sdk.model.MobileModel.internal_static_pb_MobileApplicationParams_descriptor;
    }

    @java.lang.Override
    public ru.ogon.sdk.model.MobileApplicationParams getDefaultInstanceForType() {
      return ru.ogon.sdk.model.MobileApplicationParams.getDefaultInstance();
    }

    @java.lang.Override
    public ru.ogon.sdk.model.MobileApplicationParams build() {
      ru.ogon.sdk.model.MobileApplicationParams result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public ru.ogon.sdk.model.MobileApplicationParams buildPartial() {
      ru.ogon.sdk.model.MobileApplicationParams result = new ru.ogon.sdk.model.MobileApplicationParams(this);
      result.userId_ = userId_;
      result.deviceId_ = deviceId_;
      result.confirmationId_ = confirmationId_;
      result.passwordEnabled_ = passwordEnabled_;
      result.biometryEnabled_ = biometryEnabled_;
      result.biometryAvailable_ = biometryAvailable_;
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
      if (other instanceof ru.ogon.sdk.model.MobileApplicationParams) {
        return mergeFrom((ru.ogon.sdk.model.MobileApplicationParams)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ru.ogon.sdk.model.MobileApplicationParams other) {
      if (other == ru.ogon.sdk.model.MobileApplicationParams.getDefaultInstance()) return this;
      if (!other.getUserId().isEmpty()) {
        userId_ = other.userId_;
        onChanged();
      }
      if (!other.getDeviceId().isEmpty()) {
        deviceId_ = other.deviceId_;
        onChanged();
      }
      if (!other.getConfirmationId().isEmpty()) {
        confirmationId_ = other.confirmationId_;
        onChanged();
      }
      if (other.getPasswordEnabled() != false) {
        setPasswordEnabled(other.getPasswordEnabled());
      }
      if (other.getBiometryEnabled() != false) {
        setBiometryEnabled(other.getBiometryEnabled());
      }
      if (other.getBiometryAvailable() != false) {
        setBiometryAvailable(other.getBiometryAvailable());
      }
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
      ru.ogon.sdk.model.MobileApplicationParams parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ru.ogon.sdk.model.MobileApplicationParams) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object userId_ = "";
    /**
     * <code>string user_id = 1;</code>
     * @return The userId.
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string user_id = 1;</code>
     * @return The bytes for userId.
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string user_id = 1;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      
      userId_ = getDefaultInstance().getUserId();
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 1;</code>
     * @param value The bytes for userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      userId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object deviceId_ = "";
    /**
     * <code>string device_id = 2;</code>
     * @return The deviceId.
     */
    public java.lang.String getDeviceId() {
      java.lang.Object ref = deviceId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        deviceId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string device_id = 2;</code>
     * @return The bytes for deviceId.
     */
    public com.google.protobuf.ByteString
        getDeviceIdBytes() {
      java.lang.Object ref = deviceId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        deviceId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string device_id = 2;</code>
     * @param value The deviceId to set.
     * @return This builder for chaining.
     */
    public Builder setDeviceId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      deviceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string device_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDeviceId() {
      
      deviceId_ = getDefaultInstance().getDeviceId();
      onChanged();
      return this;
    }
    /**
     * <code>string device_id = 2;</code>
     * @param value The bytes for deviceId to set.
     * @return This builder for chaining.
     */
    public Builder setDeviceIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      deviceId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object confirmationId_ = "";
    /**
     * <code>string confirmation_id = 3;</code>
     * @return The confirmationId.
     */
    public java.lang.String getConfirmationId() {
      java.lang.Object ref = confirmationId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        confirmationId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string confirmation_id = 3;</code>
     * @return The bytes for confirmationId.
     */
    public com.google.protobuf.ByteString
        getConfirmationIdBytes() {
      java.lang.Object ref = confirmationId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        confirmationId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string confirmation_id = 3;</code>
     * @param value The confirmationId to set.
     * @return This builder for chaining.
     */
    public Builder setConfirmationId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      confirmationId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string confirmation_id = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearConfirmationId() {
      
      confirmationId_ = getDefaultInstance().getConfirmationId();
      onChanged();
      return this;
    }
    /**
     * <code>string confirmation_id = 3;</code>
     * @param value The bytes for confirmationId to set.
     * @return This builder for chaining.
     */
    public Builder setConfirmationIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      confirmationId_ = value;
      onChanged();
      return this;
    }

    private boolean passwordEnabled_ ;
    /**
     * <code>bool password_enabled = 4;</code>
     * @return The passwordEnabled.
     */
    @java.lang.Override
    public boolean getPasswordEnabled() {
      return passwordEnabled_;
    }
    /**
     * <code>bool password_enabled = 4;</code>
     * @param value The passwordEnabled to set.
     * @return This builder for chaining.
     */
    public Builder setPasswordEnabled(boolean value) {
      
      passwordEnabled_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool password_enabled = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPasswordEnabled() {
      
      passwordEnabled_ = false;
      onChanged();
      return this;
    }

    private boolean biometryEnabled_ ;
    /**
     * <code>bool biometry_enabled = 5;</code>
     * @return The biometryEnabled.
     */
    @java.lang.Override
    public boolean getBiometryEnabled() {
      return biometryEnabled_;
    }
    /**
     * <code>bool biometry_enabled = 5;</code>
     * @param value The biometryEnabled to set.
     * @return This builder for chaining.
     */
    public Builder setBiometryEnabled(boolean value) {
      
      biometryEnabled_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool biometry_enabled = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearBiometryEnabled() {
      
      biometryEnabled_ = false;
      onChanged();
      return this;
    }

    private boolean biometryAvailable_ ;
    /**
     * <code>bool biometry_available = 6;</code>
     * @return The biometryAvailable.
     */
    @java.lang.Override
    public boolean getBiometryAvailable() {
      return biometryAvailable_;
    }
    /**
     * <code>bool biometry_available = 6;</code>
     * @param value The biometryAvailable to set.
     * @return This builder for chaining.
     */
    public Builder setBiometryAvailable(boolean value) {
      
      biometryAvailable_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool biometry_available = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearBiometryAvailable() {
      
      biometryAvailable_ = false;
      onChanged();
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


    // @@protoc_insertion_point(builder_scope:pb.MobileApplicationParams)
  }

  // @@protoc_insertion_point(class_scope:pb.MobileApplicationParams)
  private static final ru.ogon.sdk.model.MobileApplicationParams DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ru.ogon.sdk.model.MobileApplicationParams();
  }

  public static ru.ogon.sdk.model.MobileApplicationParams getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MobileApplicationParams>
      PARSER = new com.google.protobuf.AbstractParser<MobileApplicationParams>() {
    @java.lang.Override
    public MobileApplicationParams parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MobileApplicationParams(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MobileApplicationParams> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MobileApplicationParams> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public ru.ogon.sdk.model.MobileApplicationParams getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
