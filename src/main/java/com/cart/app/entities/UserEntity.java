package  com.cart.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_USER")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name ="USER_NAME", nullable = false)
	private String userName;
	
	@Column(name ="FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name ="LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name ="MEMBER_TYPE", nullable = false)
	private String memberType;
	
	@Column(name ="ACCOUNT_NUMBER", nullable = false)
	private String accountNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="REGISTRATION_DATE", nullable = false, unique = false, length = 10)
	private Date registrationDate;


}
