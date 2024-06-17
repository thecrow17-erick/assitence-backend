package crow.uagrm.parcial.services.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import crow.uagrm.parcial.dto.firebase.FirebaseMessageDto;

@Service
public class FirebaseMessageService {
  @Autowired
  private FirebaseMessaging firebaseMessaging;

  public String sendNotificationByToken(FirebaseMessageDto messageDto){
    Notification notification = Notification
      .builder()
      .setTitle(messageDto.getTitle())
      .setBody(messageDto.getMessage())
      .build();

    Message message = Message
      .builder()
      .setToken(messageDto.getToken_FMC())
      .setNotification(notification)
      .build();

      try {
        firebaseMessaging.send(message);
        return "Sucess sending notification";
      } catch (FirebaseMessagingException e) {
        e.printStackTrace();
        return "Error sending notification";
      }
      
  }

}


