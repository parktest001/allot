import com.fasterxml.jackson.annotation.JsonSetter;

public class ConfirmationContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   
	   private long userMobileNumber;
	   

	   public ConfirmationContext(long userMobileNumber){  
	    
	      this.userMobileNumber = userMobileNumber;
	      

	   }  
	   public ConfirmationContext() {}
	   public long getUserMobileNumber() { 
	      return userMobileNumber; 
	   } 
	   @JsonSetter
	   public void setUserMobileNumber(long userMobileNumber) { 
	      this.userMobileNumber = userMobileNumber;
	   }
}