package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Recruiting implements java.io.Serializable {

	// Fields

     private Long id; 
     private String qyuname; 
     private String qytname; 
     private String rtitle; 
     private String infotype; 
     private String btype; 
     private String stype; 
     private String nums; 
     private String addrs; 
     private String education; 
     private String skills; 
     private String sal; 
     private String remo; 
     private String savetime; 
     private String sysuserkey; 
     private String clicknums; 
     private String btypekey; 
     private String stypekey; 


	// Constructors

	/** default constructor */
	public Recruiting() {
	}

	/** minimal constructor */
	public Recruiting(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Recruiting(
    Long id, 
    String qyuname, 
    String qytname, 
    String rtitle, 
    String infotype, 
    String btype, 
    String stype, 
    String nums, 
    String addrs, 
    String education, 
    String skills, 
    String sal, 
    String remo, 
    String savetime, 
    String sysuserkey, 
    String clicknums, 
    String btypekey, 
    String stypekey ) 
	{
     this.id = id; 
     this.qyuname = qyuname; 
     this.qytname = qytname; 
     this.rtitle = rtitle; 
     this.infotype = infotype; 
     this.btype = btype; 
     this.stype = stype; 
     this.nums = nums; 
     this.addrs = addrs; 
     this.education = education; 
     this.skills = skills; 
     this.sal = sal; 
     this.remo = remo; 
     this.savetime = savetime; 
     this.sysuserkey = sysuserkey; 
     this.clicknums = clicknums; 
     this.btypekey = btypekey; 
     this.stypekey = stypekey; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getQyuname(){ 
       return this.qyuname; 
    } 

    public void setQyuname(String qyuname){ 
        this.qyuname=qyuname; 
    } 

    public String getQytname(){ 
       return this.qytname; 
    } 

    public void setQytname(String qytname){ 
        this.qytname=qytname; 
    } 

    public String getRtitle(){ 
       return this.rtitle; 
    } 

    public void setRtitle(String rtitle){ 
        this.rtitle=rtitle; 
    } 

    public String getInfotype(){ 
       return this.infotype; 
    } 

    public void setInfotype(String infotype){ 
        this.infotype=infotype; 
    } 

    public String getBtype(){ 
       return this.btype; 
    } 

    public void setBtype(String btype){ 
        this.btype=btype; 
    } 

    public String getStype(){ 
       return this.stype; 
    } 

    public void setStype(String stype){ 
        this.stype=stype; 
    } 

    public String getNums(){ 
       return this.nums; 
    } 

    public void setNums(String nums){ 
        this.nums=nums; 
    } 

    public String getAddrs(){ 
       return this.addrs; 
    } 

    public void setAddrs(String addrs){ 
        this.addrs=addrs; 
    } 

    public String getEducation(){ 
       return this.education; 
    } 

    public void setEducation(String education){ 
        this.education=education; 
    } 

    public String getSkills(){ 
       return this.skills; 
    } 

    public void setSkills(String skills){ 
        this.skills=skills; 
    } 

    public String getSal(){ 
       return this.sal; 
    } 

    public void setSal(String sal){ 
        this.sal=sal; 
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

    public String getClicknums(){ 
       return this.clicknums; 
    } 

    public void setClicknums(String clicknums){ 
        this.clicknums=clicknums; 
    } 

    public String getBtypekey(){ 
       return this.btypekey; 
    } 

    public void setBtypekey(String btypekey){ 
        this.btypekey=btypekey; 
    } 

    public String getStypekey(){ 
       return this.stypekey; 
    } 

    public void setStypekey(String stypekey){ 
        this.stypekey=stypekey; 
    } 



}