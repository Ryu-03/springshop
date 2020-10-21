package springshop.domain;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "usr")
public class User implements Serializable{
	
	@Id
	private String userId;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

}
