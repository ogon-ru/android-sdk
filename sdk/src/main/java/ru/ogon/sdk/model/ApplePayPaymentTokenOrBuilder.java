// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.ogon.sdk.model;

public interface ApplePayPaymentTokenOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb.ApplePayPaymentToken)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string payment_data = 1;</code>
   * @return The paymentData.
   */
  java.lang.String getPaymentData();
  /**
   * <code>string payment_data = 1;</code>
   * @return The bytes for paymentData.
   */
  com.google.protobuf.ByteString
      getPaymentDataBytes();

  /**
   * <code>string transaction_identifier = 2;</code>
   * @return The transactionIdentifier.
   */
  java.lang.String getTransactionIdentifier();
  /**
   * <code>string transaction_identifier = 2;</code>
   * @return The bytes for transactionIdentifier.
   */
  com.google.protobuf.ByteString
      getTransactionIdentifierBytes();

  /**
   * <code>.pb.ApplePaymentMethod payment_method = 3;</code>
   * @return Whether the paymentMethod field is set.
   */
  boolean hasPaymentMethod();
  /**
   * <code>.pb.ApplePaymentMethod payment_method = 3;</code>
   * @return The paymentMethod.
   */
  ru.ogon.sdk.model.ApplePaymentMethod getPaymentMethod();
  /**
   * <code>.pb.ApplePaymentMethod payment_method = 3;</code>
   */
  ru.ogon.sdk.model.ApplePaymentMethodOrBuilder getPaymentMethodOrBuilder();
}
