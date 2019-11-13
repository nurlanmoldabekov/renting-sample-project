package com.kiko.rent.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_flats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlatEntity {
 
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
    @Column(name = "vaddress", length = 255, nullable = false)
    private String address;
    @ManyToOne
    @JoinColumn(name="nuserid", nullable=false)
    private UserEntity user;


}