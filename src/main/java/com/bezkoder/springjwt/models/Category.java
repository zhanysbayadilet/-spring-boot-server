package com.bezkoder.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(	name = "category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "category_name")
        })
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

        public Category() {}

        public Category(String category_name, String category_img) {
                this.category_name = category_name;
                this.category_img = category_img;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getCategory_name() {
                return category_name;
        }

        public void setCategory_name(String category_name) {
                this.category_name = category_name;
        }

        public String getCategory_img() {
                return category_img;
        }

        public void setCategory_img(String category_img) {
                this.category_img = category_img;
        }

        public String getCategory_description() {
                return category_description;
        }

        public void setCategory_description(String category_description) {
                this.category_description = category_description;
        }
}
