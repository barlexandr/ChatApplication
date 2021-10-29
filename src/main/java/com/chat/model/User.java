package com.chat.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "users")
public class User {
    @ApiModelProperty(
            value = "User id. Not specified at creation",
            name = "userId",
            dataType = "Integer",
            example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ApiModelProperty(
            value = "User first name",
            name = "firstName",
            dataType = "String",
            example = "Nikita")
    @Column(nullable = false, length = 64)
    private String firstName;

    @ApiModelProperty(
            value = "User last name",
            name = "lastName",
            dataType = "String",
            example = "Petrov")
    @Column(nullable = false, length = 64)
    private String lastName;
}
