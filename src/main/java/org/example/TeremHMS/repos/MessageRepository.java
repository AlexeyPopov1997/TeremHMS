package org.example.TeremHMS.repos;

import org.springframework.data.repository.CrudRepository;

import org.example.TeremHMS.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
    Message findById(Long id);
}