package com.todo.app.common.response;

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
@ApiModel(value = "Error")
public class Error {
	@ApiModelProperty(value = "Code", required = true)
	private String code;

	@ApiModelProperty(value = "Description", required = true)
	private String description;
}