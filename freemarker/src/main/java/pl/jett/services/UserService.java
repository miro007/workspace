package pl.jett.services;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


import pl.jett.dao.DaoFactory;
import pl.jett.domain.Group;
import pl.jett.domain.User;

@Component("userService")
public class UserService  {

	private PasswordEncoder encoder = new ShaPasswordEncoder();
	
	@Autowired
	private DaoFactory daoFactory;
	
	public User findByUsername(String username) {
		User user = daoFactory.getDao(User.class).findOneByCriteria(Restrictions.eq("username",  username));
		return user;
	}
	
	@Transactional
	public void installNewUser(User user) {
		user.setSalt( String.valueOf(System.currentTimeMillis()) );
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getSalt()));
		daoFactory.getDao(User.class).save(user);
	}

	@Transactional
	public void installNewGroup(Group group) {
		daoFactory.getDao(Group.class).save(group);
	}


}
