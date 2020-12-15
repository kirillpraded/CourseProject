package by.praded.repository;

import by.praded.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Kiryl Praded
 * 25.11.2020
 */
public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
