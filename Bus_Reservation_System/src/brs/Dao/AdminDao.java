package brs.Dao;

import brs.Exception.AdminException;

public interface AdminDao {
	
	boolean validateAdmin(String userName, String password) throws AdminException;

	void registerAdmin(String userName, String password) throws AdminException;
	
}
