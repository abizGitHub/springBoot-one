package itsurena.ir.demo2.xmlClass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * CommonValueDto
 */


public class CommonValueDto {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("title")
  private String title;

  @JsonProperty("orderCode")
  private Integer orderCode;

  @JsonProperty("readOnly")
  private Boolean readOnly;

  @JsonProperty("commonType")
  private CommonTypeDto commonType = null;

  @JsonProperty("code")
  private Long code = null;

  public CommonValueDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CommonValueDto key(String key) {
    this.key = key;
    return this;
  }

  /**
   * CommonValueDto key
   * @return key
  **/
  @ApiModelProperty(value = "CommonValueDto key")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public CommonValueDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * CommonValue title
   * @return title
  **/
  @ApiModelProperty(value = "CommonValue title")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CommonValueDto orderCode(Integer orderCode) {
    this.orderCode = orderCode;
    return this;
  }

  /**
   * Get orderCode
   * @return orderCode
   **/
  @ApiModelProperty(value = "")


  public Integer getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(Integer orderCode) {
    this.orderCode = orderCode;
  }

  public CommonValueDto readOnly(Boolean readOnly) {
    this.readOnly = readOnly;
    return this;
  }

  /**
   * Get readOnly
   * @return readOnly
  **/
  @ApiModelProperty(value = "")


  public Boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly(Boolean readOnly) {
    this.readOnly = readOnly;
  }

  public CommonValueDto commonType(CommonTypeDto commonType) {
    this.commonType = commonType;
    return this;
  }

  /**
   * CommonTypeDto
   * @return commonType
   **/
  @ApiModelProperty(value = "CommonTypeDto")

  @Valid

  public CommonTypeDto getCommonType() {
    return commonType;
  }

  public void setCommonType(CommonTypeDto commonType) {
    this.commonType = commonType;
  }


  @ApiModelProperty(value = "code")

  @Valid
  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommonValueDto commonValueDto = (CommonValueDto) o;
    return Objects.equals(this.id, commonValueDto.id) &&
            Objects.equals(this.key, commonValueDto.key) &&
            Objects.equals(this.title, commonValueDto.title) &&
            Objects.equals(this.orderCode, commonValueDto.orderCode) &&
            Objects.equals(this.readOnly, commonValueDto.readOnly) &&
            Objects.equals(this.commonType, commonValueDto.commonType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, title, orderCode, readOnly, commonType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonValueDto {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    orderCode: ").append(toIndentedString(orderCode)).append("\n");
    sb.append("    readOnly: ").append(toIndentedString(readOnly)).append("\n");
    sb.append("    commonType: ").append(toIndentedString(commonType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

