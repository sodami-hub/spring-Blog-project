package com.estsoft.springprojectblogexam.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ExternalAPIDTO {
    private String title;
    private String body;

    @Override
    public String toString() {
        return "ExternalAPIDTO{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
