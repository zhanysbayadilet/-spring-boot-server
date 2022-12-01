package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> getAllUser() {
		return ConvertUtils.convertUserListToDtoList(userRepository.findAll());
	}

	public UserDTO getUser(Long id) {
		return ConvertUtils.convertToUserDTO(userRepository.findById(id).orElseThrow());
	}

	@Transactional
	public UserDTO saveUser(UserDTO userDTO) {
		User user = ConvertUtils.convertToUser(userDTO);
		return ConvertUtils.convertToUserDTO(userRepository.save(user));
	}

	@Transactional
	public Boolean deleteUserById(Long id) {
		userRepository.deleteById(id);
		return null;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public List<TournamentDTO> getUserTournaments(Long user_id) {
		User user = userRepository.findById(user_id).orElse(null);
		if(user == null)
			return null;
		return ConvertUtils.convertTournamentListToDtoList(user.getTournaments());
	}

    public Integer getCountUsers() {
		return userRepository.getCountUsers();
    }
}
