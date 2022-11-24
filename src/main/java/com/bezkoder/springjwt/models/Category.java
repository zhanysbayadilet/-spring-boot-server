package com.bezkoder.springjwt.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(	name = "category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "category_name")
        })
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotEmpty
        @Column(name = "category_name")
        private String category_name;

        @NotEmpty
        @Column(name = "category_img")
        private String category_img;

        @NotEmpty
        @Column(name = "category_description")
        private String category_description;
}
