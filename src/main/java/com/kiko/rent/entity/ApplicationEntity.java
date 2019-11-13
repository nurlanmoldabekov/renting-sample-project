package com.kiko.rent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kiko.rent.model.enums.ApplicationStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_applications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationEntity {
 
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
    @Column(name = "vtext", length = 255)
    private String text;

    @Column(name = "vstatus", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "ntimeslot", nullable = false)
    private Integer timeSlot;

    @Column(name = "ddate", nullable = false)
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dcreatedate", nullable = false, updatable = false)
    @CreatedDate
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="nnewuserid", nullable=false)
    private UserEntity newUser;

    @ManyToOne
    @JoinColumn(name="nolduserid", nullable=false)
    private UserEntity oldUser;

    @ManyToOne
    @JoinColumn(name="nflatid", nullable=false)
    private FlatEntity flat;


}