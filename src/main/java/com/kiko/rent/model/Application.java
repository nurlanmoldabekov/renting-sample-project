package com.kiko.rent.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kiko.rent.model.enums.ApplicationStatus;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Application {
    private Long id;
    private String text;
    private ApplicationStatus status;
    @NotNull(message = "timeSlot is compulsory")
    @Min(value = 0)
    @Max(value = 29)
    private Integer timeSlot;
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "date is compulsory")
    private Date date;
    private Date createDate;
    @NotNull(message = "newUserId is compulsory")
    private Long newUserId;
    @NotNull(message = "oldUserId is compulsory")
    private Long oldUserId;
    @NotNull(message = "flatId is compulsory")
    private Long flatId;

    private String timeInterpretation;
}
