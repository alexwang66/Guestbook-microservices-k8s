package org.surplus.radolf.Notebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.surplus.radolf.Notebook.entity.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
}
