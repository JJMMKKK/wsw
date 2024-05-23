package org.example.gpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GptRepository extends JpaRepository<Gptanswervo, Integer> {
}
