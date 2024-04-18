package com.pitang.dtos.outputs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LoginOutput")
public class LoginOutput {

    @NotEmpty
    @ApiModelProperty(example = "Hello.Word", required = true)
    private String login;

    @NotEmpty
    @ApiModelProperty(example = "P@ssw0rd", required = true)
    private String password;

}
