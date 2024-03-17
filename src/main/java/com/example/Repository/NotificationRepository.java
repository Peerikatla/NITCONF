package com.example.Repository;

import com.example.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Notification findByNotificationId(int notificationId);

    @Query("SELECT n FROM Notification n WHERE n.user.userid = :userid")
    List<Notification> findNotificationsByUserId(@Param("userid") Integer userid);

}
