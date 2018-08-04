package com.pcc.tcc.oauth.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8571261118900116242L;

    @Id
    private String id;
    private String createdAt;
    private String updatedAt;
}
