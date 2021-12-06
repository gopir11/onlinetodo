package com.todo.app.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@ApiModel(value="ResponseEntity")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	@ApiModelProperty(value = "MetaData", required = true)
    MetaData metaData;

	@ApiModelProperty(value = "Error", required = true)
	Error error;

	public ErrorResponse(MetaData metaData, Error error) {
		this.error = error;
		this.metaData = metaData;
	}
}