package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.project.Repository.FeedbackRepo;
import com.example.project.entity.feedbackreservation;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepo repo;

    public List<feedbackreservation> fechFeedbackList() {
        return repo.findAll();
    }

    public List<Object[]> getAll() {
        return repo.getAll();
    }

    public Page<Object[]> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.paginated(pageable);
    }

    public void addNewFeedback(feedbackreservation f) {
        if (repo.findByReserId(f.getReservation_id()).isPresent()) {

        } else {
            repo.save(f);
        }
    }
}
