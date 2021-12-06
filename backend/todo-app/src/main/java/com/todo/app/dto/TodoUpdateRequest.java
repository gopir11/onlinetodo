package com.todo.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

import static com.todo.app.common.util.DateUtil.UI_DATE_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoUpdateRequest {

	@ApiModelProperty(value = "To do identifier", required = true)
	@NonNull
	private Long id;

	@ApiModelProperty(value = "To do status")
	private boolean done;

	@ApiModelProperty(value = "To do description", required = true)
	@NonNull
	private String description;

	@ApiModelProperty(value = "To do description", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UI_DATE_FORMAT)
	@NonNull
	private LocalDateTime targetDate;
}
