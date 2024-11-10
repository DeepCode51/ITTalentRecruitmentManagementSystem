package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Examdtl implements java.io.Serializable {

	// Fields

     private Long id; 
     private String frgroupno; 
     private String ititle; 
     private String itema; 
     private String itemb; 
     private String itemc; 
     private String itemd; 
     private String banswer; 
     private String canswer; 


	// Constructors

	/** default constructor */
	public Examdtl() {
	}

	/** minimal constructor */
	public Examdtl(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Examdtl(
    Long id, 
    String frgroupno, 
    String ititle, 
    String itema, 
    String itemb, 
    String itemc, 
    String itemd, 
    String banswer, 
    String canswer ) 
	{
     this.id = id; 
     this.frgroupno = frgroupno; 
     this.ititle = ititle; 
     this.itema = itema; 
     this.itemb = itemb; 
     this.itemc = itemc; 
     this.itemd = itemd; 
     this.banswer = banswer; 
     this.canswer = canswer; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getFrgroupno(){ 
       return this.frgroupno; 
    } 

    public void setFrgroupno(String frgroupno){ 
        this.frgroupno=frgroupno; 
    } 

    public String getItitle(){ 
       return this.ititle; 
    } 

    public void setItitle(String ititle){ 
        this.ititle=ititle; 
    } 

    public String getItema(){ 
       return this.itema; 
    } 

    public void setItema(String itema){ 
        this.itema=itema; 
    } 

    public String getItemb(){ 
       return this.itemb; 
    } 

    public void setItemb(String itemb){ 
        this.itemb=itemb; 
    } 

    public String getItemc(){ 
       return this.itemc; 
    } 

    public void setItemc(String itemc){ 
        this.itemc=itemc; 
    } 

    public String getItemd(){ 
       return this.itemd; 
    } 

    public void setItemd(String itemd){ 
        this.itemd=itemd; 
    } 

    public String getBanswer(){ 
       return this.banswer; 
    } 

    public void setBanswer(String banswer){ 
        this.banswer=banswer; 
    } 

    public String getCanswer(){ 
       return this.canswer; 
    } 

    public void setCanswer(String canswer){ 
        this.canswer=canswer; 
    } 



}