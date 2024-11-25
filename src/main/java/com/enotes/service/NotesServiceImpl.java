package com.enotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enotes.entity.Notes;
import com.enotes.entity.User;
import com.enotes.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService {
	
	@Autowired 
	private NotesRepository notesRepository;

	@Override
	public Notes saveNotes(Notes notes) {
	
		return notesRepository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		// TODO Auto-generated method stub
		return notesRepository.findById(id).orElse(null);
	}

	@Override
	public List<Notes> getNotesByUser(User user) {
		// TODO Auto-generated method stub
		return notesRepository.findByUser(user);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		Notes notes = notesRepository.findById(id).orElse(null);
		if(notes != null) {
			notesRepository.delete(notes);
			return true;
		}
		return false;
	}
	
	

}
