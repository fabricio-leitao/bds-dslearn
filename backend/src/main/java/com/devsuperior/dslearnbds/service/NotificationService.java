package com.devsuperior.dslearnbds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.dto.NotificationDTO;
import com.devsuperior.dslearnbds.entities.Notification;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private AuthService authService;
	
	public Page<NotificationDTO> notificationForCurrentUser(Boolean unreadOnly, Pageable pageable){
		
		User user = authService.authenticated();
		
		Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);
		
		return page.map(x -> new NotificationDTO(x));
	}
}
