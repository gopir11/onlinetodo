package com.todo.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.todo.app.common.util.DateUtil.UI_DATE_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoResponse {

	@ApiModelProperty(value = "Todo identifier", required = true)
	private Long id;

	@ApiModelProperty(value = "Todo description", required = true)
	private String description;

	@ApiModelProperty(value = "Todo status", required = true)
	private boolean done;

	@ApiModelProperty(value = "Todo target date and time", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UI_DATE_FORMAT)
	private LocalDateTime targetDate;
}
