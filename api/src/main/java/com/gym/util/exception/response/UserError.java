package com.gym.util.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserError{

    public String message;

    public Long timestamp;
}
