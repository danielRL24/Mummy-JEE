package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")
    , @NamedQuery(name = "Task.findByTitle", query = "SELECT t FROM Task t WHERE t.title = :title")
    , @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description")
    , @NamedQuery(name = "Task.findByBegin", query = "SELECT t FROM Task t WHERE t.begin = :begin")
    , @NamedQuery(name = "Task.findByEnd", query = "SELECT t FROM Task t WHERE t.end = :end")
    , @NamedQuery(name = "Task.findByCategory", query = "SELECT t FROM Task t WHERE t.category = :category")
    , @NamedQuery(name = "Task.findByProgression", query = "SELECT t FROM Task t WHERE t.progression = :progression")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "title")
    private String title;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "begin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @Size(max = 100)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "progression")
    private short progression;
    @JoinColumn(name = "fk_creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User fkCreator;
    @OneToMany(mappedBy = "fkParent")
    private Collection<Task> taskCollection;
    @JoinColumn(name = "fk_parent", referencedColumnName = "id")
    @ManyToOne
    private Task fkParent;
    @JoinColumn(name = "fk_priority", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Priority fkPriority;
    @JoinColumn(name = "fk_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status fkStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTask")
    private Collection<Participant> participantCollection;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Integer id, String title, Date begin, Date end, short progression) {
        this.id = id;
        this.title = title;
        this.begin = begin;
        this.end = end;
        this.progression = progression;
    }
    
    /**
     * return time left between actual time and end task's time
     * @return array with [sec,min,h,days]
     */
    public List getTimeLeft()
    {
        //current date
        Date date = new Date();
        
        List<Integer> list= new ArrayList<Integer>();
        long diff= this.getEnd().getTime()-date.getTime();

        //avoid negativ values
        if(diff<0)
        {
            diff=0;
        }
            
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (3600 * 1000)%24;
        int diffInDays = (int)((diff) / (1000 * 3600 * 24));
        
        
//        list.add((int)diff);
        list.add((int)diffSeconds);
        list.add((int)diffMinutes);
        list.add((int)diffHours);
        list.add(diffInDays);
        
        return list;

    }
    public int getLength()
    {
        return (int)((getEnd().getTime()-getBegin().getTime())/(3600*24*1000));
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public short getProgression() {
        return progression;
    }

    public void setProgression(short progression) {
        this.progression = progression;
    }

    public User getFkCreator() {
        return fkCreator;
    }

    public void setFkCreator(User fkCreator) {
        this.fkCreator = fkCreator;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public Task getFkParent() {
        return fkParent;
    }

    public void setFkParent(Task fkParent) {
        this.fkParent = fkParent;
    }

    public Priority getFkPriority() {
        return fkPriority;
    }

    public void setFkPriority(Priority fkPriority) {
        this.fkPriority = fkPriority;
    }

    public Status getFkStatus() {
        return fkStatus;
    }

    public void setFkStatus(Status fkStatus) {
        this.fkStatus = fkStatus;
    }

    public Collection<Participant> getParticipantCollection() {
        return participantCollection;
    }

    public void setParticipantCollection(Collection<Participant> participantCollection) {
        this.participantCollection = participantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Task[ id=" + id + " ]";
    }
    
}
