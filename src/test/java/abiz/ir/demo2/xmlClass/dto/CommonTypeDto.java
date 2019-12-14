package abiz.ir.demo2.xmlClass.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CommonTypeDto
 */


public class CommonTypeDto {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("code")
  private Long code = null;

  @JsonIgnore
  @Valid
  private List<CommonValueDto> commonValues = null;

  public CommonTypeDto id(Long id) {
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

  public CommonTypeDto key(String key) {
    this.key = key;
    return this;
  }

  /**
   * CommonTypeDto key
   * @return key
   **/
  @ApiModelProperty(value = "CommonTypeDto key")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public CommonTypeDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * CommonTypeDto title
   * @return title
   **/
  @ApiModelProperty(value = "CommonTypeDto title")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CommonTypeDto commonValues(List<CommonValueDto> commonValues) {
    this.commonValues = commonValues;
    return this;
  }

  public CommonTypeDto addCommonValuesItem(CommonValueDto commonValuesItem) {
    if (this.commonValues == null) {
      this.commonValues = new ArrayList<CommonValueDto>();
    }
    this.commonValues.add(commonValuesItem);
    return this;
  }

  /**
   * Get commonValues
   * @return commonValues
   **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CommonValueDto> getCommonValues() {
    return commonValues;
  }

  public void setCommonValues(List<CommonValueDto> commonValues) {
    this.commonValues = commonValues;
  }

  @ApiModelProperty(value = "")

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
    CommonTypeDto commonTypeDto = (CommonTypeDto) o;
    return Objects.equals(this.id, commonTypeDto.id) &&
            Objects.equals(this.key, commonTypeDto.key) &&
            Objects.equals(this.title, commonTypeDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonTypeDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

