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
@ApiModel(value="ResponseEntity")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    @ApiModelProperty(value = "Data", required = true)
    private T data;

	@ApiModelProperty(value = "MetaData", required = true)
    MetaData metaData;

	@ApiModelProperty(value = "Error")
	Error error;

	public Response(T data, MetaData metaData) {
		this.data = data;
		this.metaData = metaData;
	}
}