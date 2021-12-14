// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.ogon.sdk.model;

public interface ApplePayPaymentDataRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb.ApplePayPaymentDataRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string country_code = 1;</code>
   * @return The countryCode.
   */
  java.lang.String getCountryCode();
  /**
   * <code>string country_code = 1;</code>
   * @return The bytes for countryCode.
   */
  com.google.protobuf.ByteString
      getCountryCodeBytes();

  /**
   * <code>string currency_code = 2;</code>
   * @return The currencyCode.
   */
  java.lang.String getCurrencyCode();
  /**
   * <code>string currency_code = 2;</code>
   * @return The bytes for currencyCode.
   */
  com.google.protobuf.ByteString
      getCurrencyCodeBytes();

  /**
   * <code>.pb.ApplePayTotal total = 3;</code>
   * @return Whether the total field is set.
   */
  boolean hasTotal();
  /**
   * <code>.pb.ApplePayTotal total = 3;</code>
   * @return The total.
   */
  ru.ogon.sdk.model.ApplePayTotal getTotal();
  /**
   * <code>.pb.ApplePayTotal total = 3;</code>
   */
  ru.ogon.sdk.model.ApplePayTotalOrBuilder getTotalOrBuilder();
}
