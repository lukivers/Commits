package core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Async
    public void sendRegistrationEmail(String email, String username) {
        try {
            log.info("=== АСИНХРОННАЯ ОТПРАВКА EMAIL ===");
            log.info("Отправка приветственного письма на: {}", email);
            log.info("Пользователь: {}", username);
            log.info("Тема: Добро пожаловать на Online Course Platform!");
            log.info("Текст: Уважаемый {}, спасибо за регистрацию! Теперь вы можете записываться на курсы.", username);

            Thread.sleep(1200); // имитация отправки
            log.info("✅ Письмо успешно отправлено (асинхронно) на {}", email);
        } catch (Exception e) {
            log.error("Ошибка при отправке email регистрации для {}", email, e);
        }
    }

    @Async
    public void sendEnrollmentNotification(String studentEmail, String studentName, String courseTitle) {
        try {
            log.info("=== АСИНХРОННАЯ ОТПРАВКА УВЕДОМЛЕНИЯ О ЗАПИСИ ===");
            log.info("Получатель: {} ({})", studentName, studentEmail);
            log.info("Курс: {}", courseTitle);
            log.info("Текст: Уважаемый {}, вы успешно записаны на курс '{}'.", studentName, courseTitle);

            Thread.sleep(1000);
            log.info("✅ Уведомление о записи отправлено");
        } catch (Exception e) {
            log.error("Ошибка при отправке уведомления о записи", e);
        }
    }
}