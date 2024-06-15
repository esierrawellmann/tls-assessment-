package com.telus.assesment.dto;

import com.telus.assesment.enums.Status;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoDTO {
    private int id;
    @NotNull
    private String description;
    @NotNull
    private Status status;
}
