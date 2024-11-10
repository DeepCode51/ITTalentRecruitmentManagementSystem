package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Exams implements java.io.Serializable {

	// Fields

     private Long id; 
     private String frgroupno; 
     private String qyuname; 
     private String touname; 
     private String etitle; 
     private String examdtl; 
     private String savetime; 
     private String tounamekey; 
     private String examdtlkey; 
     private String sysuserkey;
    private Integer grade;


	// Constructors

	/** default constructor */
	public Exams() {
	}

	/** minimal constructor */
	public Exams(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Exams(
    Long id, 
    String frgroupno, 
    String qyuname, 
    String touname, 
    String etitle, 
    String examdtl, 
    String savetime, 
    String tounamekey, 
    String examdtlkey, 
    String sysuserkey,
    Integer grade )
	{
     this.id = id; 
     this.frgroupno = frgroupno; 
     this.qyuname = qyuname; 
     this.touname = touname; 
     this.etitle = etitle; 
     this.examdtl = examdtl; 
     this.savetime = savetime; 
     this.tounamekey = tounamekey; 
     this.examdtlkey = examdtlkey; 
     this.sysuserkey = sysuserkey;
     this.grade = grade;
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

    public String getQyuname(){ 
       return this.qyuname; 
    } 

    public void setQyuname(String qyuname){ 
        this.qyuname=qyuname; 
    } 

    public String getTouname(){ 
       return this.touname; 
    } 

    public void setTouname(String touname){ 
        this.touname=touname; 
    } 

    public String getEtitle(){ 
       return this.etitle; 
    } 

    public void setEtitle(String etitle){ 
        this.etitle=etitle; 
    } 

    public String getExamdtl(){ 
       return this.examdtl; 
    } 

    public void setExamdtl(String examdtl){ 
        this.examdtl=examdtl; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getTounamekey(){ 
       return this.tounamekey; 
    } 

    public void setTounamekey(String tounamekey){ 
        this.tounamekey=tounamekey; 
    } 

    public String getExamdtlkey(){ 
       return this.examdtlkey; 
    } 

    public void setExamdtlkey(String examdtlkey){ 
        this.examdtlkey=examdtlkey; 
    } 

    public String getSysuserkey(){ 
       return this.sysuserkey; 
    } 

    public void setSysuserkey(String sysuserkey){ 
        this.sysuserkey=sysuserkey; 
    }

    public Integer getGrade() {return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}