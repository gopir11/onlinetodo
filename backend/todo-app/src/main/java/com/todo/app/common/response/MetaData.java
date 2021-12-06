package com.todo.app.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Metadata")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaData {
	@ApiModelProperty(value = "Success", required = true)
	private boolean success = true;

	@ApiModelProperty(value = "Description", required = true)
	private String description;
}