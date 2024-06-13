package himedia.myhome.dao;

import java.util.List;

import himedia.myhome.vo.UserVo;

public interface UsersDao {
	public List<UserVo> getList();	//	목록
	public boolean insert(UserVo userVo);	//	insert
	public boolean update(UserVo userVo);	//	update
	public boolean delete(Long no);			//	delete
	public UserVo getUserByIdAndPassword (String email, String password);	//	Login용
}
