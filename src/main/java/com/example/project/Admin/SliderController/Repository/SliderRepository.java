package com.example.project.Admin.SliderController.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.SliderController.Model.Slider;

@Repository
public interface SliderRepository extends JpaRepository<Slider,Integer>{
    @Query(value = "SELECT * FROM slider where  `slider_id` <> 0 ORDER BY `slider_id` asc ",nativeQuery = true)
    List<Slider> findPreviewSlider();
     @Query(value = "SELECT * FROM slider where  `slider_id` = 0 ",nativeQuery = true)
    List<Slider> findHiddenList();
    
}
 