package student.service.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import student.service.enums.ERole;
import student.service.entity.RoleEntity;
import student.service.entity.UserEntity;
import student.service.payload.request.LoginRequest;
import student.service.payload.request.SignupRequest;
import student.service.payload.response.JwtResponse;
import student.service.payload.response.MessageResponse;
import student.service.dao.RoleDao;
import student.service.dao.UserDao;
import student.service.security.jwt.JwtUtils;
import student.service.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}

	@PostMapping("/signup")
	public MessageResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userDao.existsByUsername(signUpRequest.getUsername())) 
			return new MessageResponse("Error: Username is already taken!");

		if (userDao.existsByEmail(signUpRequest.getEmail()))
			return new MessageResponse("Error: Email is already in use!");

		// Create new user's account
		UserEntity userEntity = new UserEntity(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<RoleEntity> roleEntities = new HashSet<>();

		if (strRoles == null) {
			RoleEntity userRole = roleDao.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roleEntities.add(userRole);
		} 
		else 
		{
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleDao.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntities.add(adminRole);

					break;
				case "mod":
					RoleEntity modRole = roleDao.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntities.add(modRole);

					break;
				default:
					RoleEntity userRole = roleDao.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntities.add(userRole);
				}
			});
		}

		userEntity.setRoles(roleEntities);
		userDao.save(userEntity);

		return new MessageResponse("User registered successfully!");
	}
}
