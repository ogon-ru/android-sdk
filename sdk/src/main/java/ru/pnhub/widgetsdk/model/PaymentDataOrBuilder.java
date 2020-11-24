// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.pnhub.widgetsdk.model;

public interface PaymentDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb.PaymentData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint32 api_version = 1;</code>
   * @return The apiVersion.
   */
  int getApiVersion();

  /**
   * <code>uint32 api_version_minor = 2;</code>
   * @return The apiVersionMinor.
   */
  int getApiVersionMinor();

  /**
   * <code>string email = 3;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 3;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>.pb.Address shipping_address = 4;</code>
   * @return Whether the shippingAddress field is set.
   */
  boolean hasShippingAddress();
  /**
   * <code>.pb.Address shipping_address = 4;</code>
   * @return The shippingAddress.
   */
  ru.pnhub.widgetsdk.model.Address getShippingAddress();
  /**
   * <code>.pb.Address shipping_address = 4;</code>
   */
  ru.pnhub.widgetsdk.model.AddressOrBuilder getShippingAddressOrBuilder();

  /**
   * <code>.pb.PaymentMethodData payment_method_data = 5;</code>
   * @return Whether the paymentMethodData field is set.
   */
  boolean hasPaymentMethodData();
  /**
   * <code>.pb.PaymentMethodData payment_method_data = 5;</code>
   * @return The paymentMethodData.
   */
  ru.pnhub.widgetsdk.model.PaymentMethodData getPaymentMethodData();
  /**
   * <code>.pb.PaymentMethodData payment_method_data = 5;</code>
   */
  ru.pnhub.widgetsdk.model.PaymentMethodDataOrBuilder getPaymentMethodDataOrBuilder();

  /**
   * <code>.pb.SelectionOptionData shipping_option_data = 6;</code>
   * @return Whether the shippingOptionData field is set.
   */
  boolean hasShippingOptionData();
  /**
   * <code>.pb.SelectionOptionData shipping_option_data = 6;</code>
   * @return The shippingOptionData.
   */
  ru.pnhub.widgetsdk.model.SelectionOptionData getShippingOptionData();
  /**
   * <code>.pb.SelectionOptionData shipping_option_data = 6;</code>
   */
  ru.pnhub.widgetsdk.model.SelectionOptionDataOrBuilder getShippingOptionDataOrBuilder();
}