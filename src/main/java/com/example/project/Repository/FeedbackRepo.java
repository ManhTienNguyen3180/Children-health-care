package com.example.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.feedbackreservation;

@Repository
public interface FeedbackRepo extends JpaRepository<feedbackreservation, Integer> {
        @Query(value = "select f.id, r.reservation_id,r.patient_name,d.service_name,c.name,r.date,f.date,f.rating,r.doctor_name\n"
                        + //
                        "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n"
                        + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and c.id=s.category_id\n" + //
                        "and d.service_id=s.service_id", nativeQuery = true)
        List<Object[]> getAll();

        @Query(value = "select f.id, r.reservation_id,r.patient_name,d.service_name,c.name,r.date,f.date,f.rating,r.doctor_name\n"
                        + //
                        "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n"
                        + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and c.id=s.category_id\n" + //
                        "and d.service_id=s.service_id", nativeQuery = true)
        Page<Object[]> paginated(PageRequest pageable);

        @Query(value = "select distinct f.id,c.name,r.doctor_name,r.date,f.rating,f.comment,f.date,r.total_cost,r.time,r.patient_name\n"
                        + //
                        "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n"
                        + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and c.id=s.category_id\n" + //
                        "and d.service_id=s.service_id\n" + //
                        "and f.id=?1", nativeQuery = true)
        Object getFeedbackDetail(int id);

        @Query(value = "select d.service_name from reservation r , feedbackreservation f ,reservationdetail d\n" + //
                        "where r.reservation_id = f.reservation_id\n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and f.id=?1", nativeQuery = true)
        List<Object[]> getService(int id);

        @Query(value = "select f.comment,f.date,f.rating,u.username,u.image\n" + //
                        "from reservation r , feedbackreservation f , patient p, user u\n" + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and r.patient_id=p.patient_id\n" + //
                        "and p.user_id=u.user_id\n" + //
                        "and r.doctor_id=?1", nativeQuery = true)
        List<Object[]> getFeedbackbyDoc(int id);

        @Query(value = "select f.comment,f.date,f.rating,u.username,u.image\n" + //
                        "from reservation r , feedbackreservation f , patient p, user u\n" + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and r.patient_id=p.patient_id\n" + //
                        "and p.user_id=u.user_id\n" + //
                        "limit ?1", nativeQuery = true)
        List<Object[]> getFeedbackHome(int s);

        @Query(value = "select f.id, r.reservation_id,r.patient_name,d.service_name,c.name,r.date,f.date,f.rating,r.doctor_name\n" + //
                        "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n" + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and c.id=s.category_id\n" + //
                        "and d.service_id=s.service_id\n" + //
                        "and f.rating=?1", nativeQuery = true)
        Page<Object[]> paginatedRating(PageRequest pageable,int r);

        @Query(value = "select distinct rating from feedbackreservation f", nativeQuery = true)
        List<Integer> Rating();

        @Query(value = "select f.comment,f.date,f.rating,u.username,u.image,r.doctor_name\n" + //
                        "from reservation r , feedbackreservation f ,reservationdetail d, service s,patient p, user u\n" + //
                        "where r.reservation_id = f.reservation_id \n" + //
                        "and d.reservation_id=r.reservation_id\n" + //
                        "and d.service_id=s.service_id\n" + //
                        "and r.patient_id=p.patient_id\n" + //
                        "and p.user_id=u.user_id\n" + //
                        "and s.service_id=?1", nativeQuery = true)
        List<Object[]> getFeedbackByService(int s);
}
