package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Charges implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String stype; 
     private String atype; 
     private String acct; 
     private String apass; 
     private String amt; 
     private String remo; 
     private String savetime; 
     private String sysuserkey; 


	// Constructors

	/** default constructor */
	public Charges() {
	}

	/** minimal constructor */
	public Charges(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Charges(
    Long id, 
    String uname, 
    String stype, 
    String atype, 
    String acct, 
    String apass, 
    String amt, 
    String remo, 
    String savetime, 
    String sysuserkey ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.stype = stype; 
     this.atype = atype; 
     this.acct = acct; 
     this.apass = apass; 
     this.amt = amt; 
     this.remo = remo; 
     this.savetime = savetime; 
     this.sysuserkey = sysuserkey; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getUname(){ 
       return this.uname; 
    } 

    public void setUname(String uname){ 
        this.uname=uname; 
    } 

    public String getStype(){ 
       return this.stype; 
    } 

    public void setStype(String stype){ 
        this.stype=stype; 
    } 

    public String getAtype(){ 
       return this.atype; 
    } 

    public void setAtype(String atype){ 
        this.atype=atype; 
    } 

    public String getAcct(){ 
       return this.acct; 
    } 

    public void setAcct(String acct){ 
        this.acct=acct; 
    } 

    public String getApass(){ 
       return this.apass; 
    } 

    public void setApass(String apass){ 
        this.apass=apass; 
    } 

    public String getAmt(){ 
       return this.amt; 
    } 

    public void setAmt(String amt){ 
        this.amt=amt; 
    } 

    public String getRemo(){ 
       return this.remo; 
    } 

    public void setRemo(String remo){ 
        this.remo=remo; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getSysuserkey(){ 
       return this.sysuserkey; 
    } 

    public void setSysuserkey(String sysuserkey){ 
        this.sysuserkey=sysuserkey; 
    } 



}