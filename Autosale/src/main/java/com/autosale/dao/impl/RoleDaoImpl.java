package com.autosale.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autosale.dao.RoleDao;
import com.autosale.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public Role getRoleById(int roleId) {
		return em.find(Role.class, roleId);
	}

}
