package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sysuser implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String upass; 
     private String utype; 
     private String tname; 
     private String filename; 
     private String sex; 
     private String email; 
     private String tel; 
     private String addrs; 
     private String status; 
     private String founddate; 
     private String companytype; 
     private String mainbuss; 
     private String houhold; 
     private String birth; 
     private String wei; 
     private String hei; 
     private String health; 
     private String school; 
     private String specialty; 
     private String education; 
     private String cbdate; 
     private String skill; 
     private String hobby; 
     private String experience; 
     private String languages; 
     private String intention; 
     private String remo; 
     private String filename2; 
     private String docname3; 
     private String savetime; 
     private String passques; 
     private String passans; 
     private String clicknums; 


	// Constructors

	/** default constructor */
	public Sysuser() {
	}

	/** minimal constructor */
	public Sysuser(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Sysuser(
    Long id, 
    String uname, 
    String upass, 
    String utype, 
    String tname, 
    String filename, 
    String sex, 
    String email, 
    String tel, 
    String addrs, 
    String status, 
    String founddate, 
    String companytype, 
    String mainbuss, 
    String houhold, 
    String birth, 
    String wei, 
    String hei, 
    String health, 
    String school, 
    String specialty, 
    String education, 
    String cbdate, 
    String skill, 
    String hobby, 
    String experience, 
    String languages, 
    String intention, 
    String remo, 
    String filename2, 
    String docname3, 
    String savetime, 
    String passques, 
    String passans, 
    String clicknums ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.upass = upass; 
     this.utype = utype; 
     this.tname = tname; 
     this.filename = filename; 
     this.sex = sex; 
     this.email = email; 
     this.tel = tel; 
     this.addrs = addrs; 
     this.status = status; 
     this.founddate = founddate; 
     this.companytype = companytype; 
     this.mainbuss = mainbuss; 
     this.houhold = houhold; 
     this.birth = birth; 
     this.wei = wei; 
     this.hei = hei; 
     this.health = health; 
     this.school = school; 
     this.specialty = specialty; 
     this.education = education; 
     this.cbdate = cbdate; 
     this.skill = skill; 
     this.hobby = hobby; 
     this.experience = experience; 
     this.languages = languages; 
     this.intention = intention; 
     this.remo = remo; 
     this.filename2 = filename2; 
     this.docname3 = docname3; 
     this.savetime = savetime; 
     this.passques = passques; 
     this.passans = passans; 
     this.clicknums = clicknums; 
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

    public String getUpass(){ 
       return this.upass; 
    } 

    public void setUpass(String upass){ 
        this.upass=upass; 
    } 

    public String getUtype(){ 
       return this.utype; 
    } 

    public void setUtype(String utype){ 
        this.utype=utype; 
    } 

    public String getTname(){ 
       return this.tname; 
    } 

    public void setTname(String tname){ 
        this.tname=tname; 
    } 

    public String getFilename(){ 
       return this.filename; 
    } 

    public void setFilename(String filename){ 
        this.filename=filename; 
    } 

    public String getSex(){ 
       return this.sex; 
    } 

    public void setSex(String sex){ 
        this.sex=sex; 
    } 

    public String getEmail(){ 
       return this.email; 
    } 

    public void setEmail(String email){ 
        this.email=email; 
    } 

    public String getTel(){ 
       return this.tel; 
    } 

    public void setTel(String tel){ 
        this.tel=tel; 
    } 

    public String getAddrs(){ 
       return this.addrs; 
    } 

    public void setAddrs(String addrs){ 
        this.addrs=addrs; 
    } 

    public String getStatus(){ 
       return this.status; 
    } 

    public void setStatus(String status){ 
        this.status=status; 
    } 

    public String getFounddate(){ 
       return this.founddate; 
    } 

    public void setFounddate(String founddate){ 
        this.founddate=founddate; 
    } 

    public String getCompanytype(){ 
       return this.companytype; 
    } 

    public void setCompanytype(String companytype){ 
        this.companytype=companytype; 
    } 

    public String getMainbuss(){ 
       return this.mainbuss; 
    } 

    public void setMainbuss(String mainbuss){ 
        this.mainbuss=mainbuss; 
    } 

    public String getHouhold(){ 
       return this.houhold; 
    } 

    public void setHouhold(String houhold){ 
        this.houhold=houhold; 
    } 

    public String getBirth(){ 
       return this.birth; 
    } 

    public void setBirth(String birth){ 
        this.birth=birth; 
    } 

    public String getWei(){ 
       return this.wei; 
    } 

    public void setWei(String wei){ 
        this.wei=wei; 
    } 

    public String getHei(){ 
       return this.hei; 
    } 

    public void setHei(String hei){ 
        this.hei=hei; 
    } 

    public String getHealth(){ 
       return this.health; 
    } 

    public void setHealth(String health){ 
        this.health=health; 
    } 

    public String getSchool(){ 
       return this.school; 
    } 

    public void setSchool(String school){ 
        this.school=school; 
    } 

    public String getSpecialty(){ 
       return this.specialty; 
    } 

    public void setSpecialty(String specialty){ 
        this.specialty=specialty; 
    } 

    public String getEducation(){ 
       return this.education; 
    } 

    public void setEducation(String education){ 
        this.education=education; 
    } 

    public String getCbdate(){ 
       return this.cbdate; 
    } 

    public void setCbdate(String cbdate){ 
        this.cbdate=cbdate; 
    } 

    public String getSkill(){ 
       return this.skill; 
    } 

    public void setSkill(String skill){ 
        this.skill=skill; 
    } 

    public String getHobby(){ 
       return this.hobby; 
    } 

    public void setHobby(String hobby){ 
        this.hobby=hobby; 
    } 

    public String getExperience(){ 
       return this.experience; 
    } 

    public void setExperience(String experience){ 
        this.experience=experience; 
    } 

    public String getLanguages(){ 
       return this.languages; 
    } 

    public void setLanguages(String languages){ 
        this.languages=languages; 
    } 

    public String getIntention(){ 
       return this.intention; 
    } 

    public void setIntention(String intention){ 
        this.intention=intention; 
    } 

    public String getRemo(){ 
       return this.remo; 
    } 

    public void setRemo(String remo){ 
        this.remo=remo; 
    } 

    public String getFilename2(){ 
       return this.filename2; 
    } 

    public void setFilename2(String filename2){ 
        this.filename2=filename2; 
    } 

    public String getDocname3(){ 
       return this.docname3; 
    } 

    public void setDocname3(String docname3){ 
        this.docname3=docname3; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getPassques(){ 
       return this.passques; 
    } 

    public void setPassques(String passques){ 
        this.passques=passques; 
    } 

    public String getPassans(){ 
       return this.passans; 
    } 

    public void setPassans(String passans){ 
        this.passans=passans; 
    } 

    public String getClicknums(){ 
       return this.clicknums; 
    } 

    public void setClicknums(String clicknums){ 
        this.clicknums=clicknums; 
    } 



}