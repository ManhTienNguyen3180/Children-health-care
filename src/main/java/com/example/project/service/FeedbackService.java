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

    public List<feedbackreservation> fechFeedbackList(){
        return repo.findAll();
    }

    public List<Object[]> getAll(){
        return repo.getAll();
    }

    public Page<Object[]> findPaginated(int pageNo, int pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.paginated(pageable);
    }

    public Object getFeedbackDetail(int id){
        return repo.getFeedbackDetail(id);
    }

    public List<Object[]> getService(int id){
        return repo.getService(id);
    }

    public List<Object[]> getFeedbackByDoc(int id){
        return repo.getFeedbackbyDoc(id);
    }
    
    public List<Object[]> getFeedbackHome(int id){
        return repo.getFeedbackHome(id);
    }

    public Page<Object[]> paginatedRating(int pageNo, int pageSize,int s){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.paginatedRating(pageable,s);
    }

    public List<Integer> ratings(){
        return repo.Rating();
    }

    public List<Object[]> getFeedbackByS(int id){
        return repo.getFeedbackByService(id);
    }

}
