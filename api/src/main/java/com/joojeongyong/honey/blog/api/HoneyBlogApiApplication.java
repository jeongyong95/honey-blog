package com.joojeongyong.honey.blog.api;

import com.joojeongyong.honey.blog.RootMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {RootMarker.class})
public class HoneyBlogApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(HoneyBlogApiApplication.class);
	}
}
