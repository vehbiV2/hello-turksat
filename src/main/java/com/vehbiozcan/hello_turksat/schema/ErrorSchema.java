package com.vehbiozcan.hello_turksat.schema;

import com.vehbiozcan.hello_turksat.entity.RootEntity;
import com.vehbiozcan.hello_turksat.exception.ApiError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


public abstract class ErrorSchema extends RootEntity<ApiError> {
}
