package com.example.project.Admin.SliderController.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.project.Admin.SliderController.Model.Slider;
import com.example.project.Admin.SliderController.Repository.SliderRepository;

@Service
public class SliderService {
    @Autowired
    SliderRepository repo;

    public Page<Slider> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.findAll(pageable);
    }

    public void addService(Slider slider) {
        repo.save(slider);
    }

    public List<Slider> findPreviewSlider() {
        return repo.findPreviewSlider();
    }

    public List<Slider> findAll() {
        return repo.findAll();
    }

    public Slider findById(Integer id) {
        return repo.findById(id).get();
    }

    public void save(Slider slider) {
        repo.save(slider);
    }

    public List<Slider> findHiddenSlider() {
        return repo.findHiddenList();
    }
}
