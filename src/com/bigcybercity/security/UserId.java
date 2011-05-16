/** ------------------------------------------------------------
 * UserId.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

/** 
 * This classes extends Spring Security's User class.  It is stored in 
 * each web session, so adding fields to this class will make them available
 * in the server's session object.  See the projects security xml 
 * configuration files for its use.
 */
public class UserId extends User {
	
	private static final long serialVersionUID = -8275492272371421013L;
	private long id;

	public UserId( long id, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, GrantedAuthority[] authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.id = id;
	}
	
	/** 
	 * Sets the user's unique id in the server's session object
	 * @param id the user's id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** 
	 * 
	 * @return the user's unique id
	 */
	public long getId() {
		return this.id;
	}
	
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(": ");
        sb.append("Id: ").append(this.id).append("; ");
        return sb.toString();
    }
    

}

